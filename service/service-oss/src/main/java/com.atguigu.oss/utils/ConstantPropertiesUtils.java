package com.atguigu.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author sgd
 * @date 2021/8/4上午11:20
 */
@Component
public class ConstantPropertiesUtils implements InitializingBean {

    @Value("https://oss-cn-beijing.aliyuncs.com")
    private String endpoint;

    @Value("LTAI5tFLm6E5MwRju76RcHG9")
    private String keyId;

    @Value("HG956GX8RlGbZsNg1G7AE9wZq27z7K")
    private String keySecret;

    @Value("rrl-guli")
    private String bucketName;

//  定义公开静态常量
    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        END_POINT=endpoint;
        ACCESS_KEY_ID=keyId;
        ACCESS_KEY_SECRET=keySecret;
        BUCKET_NAME=bucketName;
    }
}
