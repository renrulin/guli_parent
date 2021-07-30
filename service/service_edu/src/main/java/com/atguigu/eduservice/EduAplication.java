package com.atguigu.eduservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author sgd
 * @date 2021/7/30下午3:48
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.atguigu"})
public class EduAplication {
    public static void main(String[] args) {
        SpringApplication.run(EduAplication.class,args);
    }
}
