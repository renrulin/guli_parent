package com.atguigu.eduservice.service.impl;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.EduVideo;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.atguigu.eduservice.entity.chapter.VideoVo;
import com.atguigu.eduservice.mapper.EduChapterMapper;
import com.atguigu.eduservice.service.EduChapterService;
import com.atguigu.eduservice.service.EduVideoService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author atguigu
 * @since 2021-08-05
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;
    @Override
    public List<ChapterVo> getChapterVideoByCourseId(String courseId) {

//        1.根据课程id查询课程里面所有的章节
        QueryWrapper<EduChapter> wrapper=new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        List<EduChapter> eduChapters = baseMapper.selectList(wrapper);

//        2.根据课程id查询课程里面所有小节
        QueryWrapper<EduVideo> wrapperVideo = new QueryWrapper<>();
        wrapperVideo.eq("course_id",courseId);
        List<EduVideo> list = videoService.list(wrapperVideo);

        List<ChapterVo> finalList=new ArrayList<>();

//        3.遍历查询章节list集合进行封装
        for (int i=0;i<eduChapters.size();i++){
            EduChapter eduChapter = eduChapters.get(i);
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(eduChapter,chapterVo);
            finalList.add(chapterVo);

            List<VideoVo> videoVoList=new ArrayList<>();
//        4.遍历查询小节list集合进行封装
            for (int m = 0; m < list.size(); m++) {
                EduVideo eduVideo = list.get(m);
                if (eduVideo.getChapterId().equals(eduChapter.getId())) {
                    VideoVo videoVo = new VideoVo();
                    BeanUtils.copyProperties(eduVideo,videoVo);
                    videoVoList.add(videoVo);
                }
            }
            chapterVo.setChildren(videoVoList);
        }
        return finalList;
    }

    @Override
    public boolean deleteChapter(String chapterId) {
        QueryWrapper<EduVideo> wrapper=new QueryWrapper<>();
        wrapper.eq("chapter_id",chapterId);
        int count = videoService.count(wrapper);
        if (count>0){
            throw new GuliException(20001,"不能删除");
        }
        int i = baseMapper.deleteById(chapterId);
        return i>0;
    }

    @Override
    public void removeChapterByCourseId(String courseId) {
        QueryWrapper<EduChapter> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",courseId);
        baseMapper.delete(wrapper);
    }
}
