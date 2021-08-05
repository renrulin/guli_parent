package com.atguigu.eduservice.entity.chapter;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sgd
 * @date 2021/8/5下午3:49
 */
@Data
public class ChapterVo {

    private String id;

    private String title;

    private List<VideoVo> children=new ArrayList<>();
}
