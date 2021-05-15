package com.yjs.cloud.learning.oss.biz.local.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Bill.lai
 * @since 25/11/2020
 */
@ConfigurationProperties(prefix = "local")
@Component
@Data
public class LocalConfig {

    private String fileDir;

}
