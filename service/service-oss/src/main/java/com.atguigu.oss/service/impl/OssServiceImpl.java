package com.atguigu.oss.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.oss.service.OssService;
import com.atguigu.oss.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author sgd
 * @date 2021/8/4上午11:36
 */
@Service
public class OssServiceImpl implements OssService {
    @Override
    public String uploadFileAvatar(MultipartFile file) {
        String endpoint= ConstantPropertiesUtils.END_POINT;
        String accessKeyId=ConstantPropertiesUtils.ACCESS_KEY_ID;
        String accessKeySecret=ConstantPropertiesUtils.ACCESS_KEY_SECRET;
        String bucketName=ConstantPropertiesUtils.BUCKET_NAME;

        try {
//          创建OSS实例
            OSS build = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
//          获取上传文件输入流
            InputStream inputStream = file.getInputStream();
//            获取文件名称
            String originalFilename = file.getOriginalFilename();
//            1.在文件名称里添加随机唯一的值
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            originalFilename=uuid+originalFilename;
//            2.把文件按日期进行分类
            String datePath = new DateTime().toString("yyyy/MM/dd");
            originalFilename=datePath+"/"+originalFilename;
//            调用OSS方法实现上传
//            第一个参数     bucket名称
//            第二个参数     上传到OSS文件路径和文件名称
//            第三个参数     上传文件输入流
            build. putObject(bucketName,originalFilename,inputStream);
//            关闭
            build.shutdown();
//            把上传之后的文件路径返回
            String url="https://"+bucketName+"."+endpoint+"/"+originalFilename;
            return url;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
