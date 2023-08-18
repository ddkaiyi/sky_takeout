package com.sky.config;

import com.sky.properties.AliOssProperties;
import com.sky.utils.S3FileUploader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类，用于创建S3对象
 */
@Configuration
@Slf4j
public class OssConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public S3FileUploader s3FileUploader(AliOssProperties aliOssProperties){
        log.info("开始创建文件上传工具类对象{}",aliOssProperties);
         return new S3FileUploader(aliOssProperties.getEndpoint(), aliOssProperties.getAccessKeyId(),
                  aliOssProperties.getAccessKeySecret(), aliOssProperties.getBucketName());

    }
}
