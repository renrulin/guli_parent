package com.atguigu.eduservice.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.atguigu.eduservice.entity.excel.SubjectData;

/**
 * @author sgd
 * @date 2021/8/4下午5:29
 */
public class SubjectExcelListener extends AnalysisEventListener<SubjectData> {
    @Override
    public void invoke(SubjectData subjectData, AnalysisContext analysisContext) {

    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
