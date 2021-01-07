package com.yjs.cloud.learning.oss.biz.aliyun.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Bill.lai
 * @since 25/11/2020
 */
@ConfigurationProperties(prefix = "aliyun")
@Component
@Data
public class AliYunOssConfig {

    private String accessKey;

    private String accessSecret;

    private String point;

    private String bucketName;

    private String visitPath;

}
