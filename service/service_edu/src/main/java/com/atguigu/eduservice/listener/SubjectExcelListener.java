package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.EduSubject;
import com.atguigu.eduservice.entity.excel.SubjectData;
import com.atguigu.eduservice.service.EduSubjectService;
import com.atguigu.servicebase.exceptionhandler.GuliException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author sgd
 * @date 2021/8/4下午5:29
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {

//    因为SubjectExcelListener不能交给spring进行管理，需要自己new，不能注入其他对象
//    不能实现数据库操作
    public EduSubjectService subjectService;

    public SubjectExcelListener() {
    }

    public SubjectExcelListener(EduSubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {
        if (subjectData==null){
            throw new GuliException(20001,"文件数据为空");
        }
//        一行一行读取，每次堆取有两个值，第一个值一级分类，第二个值二级分类
//        判断一级分类是否重复
        EduSubject oneSubject = this.existOneSubject(subjectService, subjectData.getOneSubjectName());
        if(oneSubject==null){
            oneSubject=new EduSubject();
            oneSubject.setParentId("0");
            oneSubject.setTitle(subjectData.getOneSubjectName());
            subjectService.save(oneSubject);
        }
//        获取一级分类id
        String pid=oneSubject.getId();
//        添加二级目录
//        判断二级目录是否重复
        EduSubject twoSubject = this.existTwoSubject(subjectService, subjectData.getTwoSubjectName(), pid);
        if (twoSubject==null){
            twoSubject=new EduSubject();
            twoSubject.setParentId(pid);
            twoSubject.setTitle(subjectData.getTwoSubjectName());
            subjectService.save(twoSubject);
        }
    }

//    判断一级分类不能重复添加
    private EduSubject existOneSubject(EduSubjectService eduSubjectService, String name){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        EduSubject one = eduSubjectService.getOne(wrapper);
        return one;
    }

//    判断二级分类不能重复添加
    private EduSubject existTwoSubject(EduSubjectService eduSubjectService, String name,String pid){
        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);
        EduSubject two = eduSubjectService.getOne(wrapper);
        return two;
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
