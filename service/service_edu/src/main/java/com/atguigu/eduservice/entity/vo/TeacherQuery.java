package com.atguigu.eduservice.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author sgd
 * @date 2021/8/2上午9:38
 */
@Data
public class TeacherQuery {

    @ApiModelProperty(value = "讲师名称，迷糊查询")
    private String name;

    @ApiModelProperty(value = "头衔 1 高级讲师 2 首席讲师")
    private Integer level;

    @ApiModelProperty(value = "开始时间",example = "2019-01-01 10:00:00")
    private String begin;

    @ApiModelProperty(value = "结束时间",example = "2019-12-01 10:00:00")
    private String end;

}
