package com.atguigu.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sgd
 * @date 2021/8/5上午10:10
 */
@Data
public class OneSubject {
    private String id;
    private String title;
    private List<TwoSubject> children=new ArrayList<>();
}
