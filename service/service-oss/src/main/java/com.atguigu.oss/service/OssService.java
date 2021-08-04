package com.atguigu.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author sgd
 * @date 2021/8/4上午11:35
 */
public interface OssService {
    String uploadFileAvatar(MultipartFile file);
}
