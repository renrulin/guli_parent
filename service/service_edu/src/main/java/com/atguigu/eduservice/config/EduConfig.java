package com.atguigu.eduservice.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
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

    /***
     * 分页插件
     * @return {@link com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor}
     * @author      sgd
     * @date        2021/8/2 上午9:05
     * @catName  TODO
     * @apiNote       
     */
    @Bean
    public PaginationInterceptor paginationInterceptor(){
        return new PaginationInterceptor();
    }
}
