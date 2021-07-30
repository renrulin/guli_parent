package com.atguigu.eduservice.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author sgd
 * @date 2021/7/30下午3:51
 */
@Configuration
@MapperScan("com.atguigu.eduservice.mapper")
public class EduConfig {

}
