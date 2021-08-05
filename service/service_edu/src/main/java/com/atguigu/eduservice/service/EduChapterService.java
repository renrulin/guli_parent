package com.atguigu.eduservice.service;

import com.atguigu.eduservice.entity.EduChapter;
import com.atguigu.eduservice.entity.chapter.ChapterVo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author atguigu
 * @since 2021-08-05
 */
public interface EduChapterService extends IService<EduChapter> {

    //    课程大纲列表，根据课程ID进行查询
    List<ChapterVo> getChapterVideoByCourseId(String courseId);
}