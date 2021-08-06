package com.atguigu.eduservice.entity.vo;

import lombok.Data;

/**
 * @author sgd
 * @date 2021/8/6下午1:22
 */
@Data
public class CoursePublishVo {
    private String id;
    private String title;
    private String cover;
    private Integer lessonNum;
    private String subjectLevelOne;
    private String subjectLevelTwo;
    private String teacherName;
    private String price;   //只用于显示
}
