package com.yjs.cloud.learning.oss.biz.local.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.io.File;

/**
 * <p>
 * ResourceLoader资源映射
 * </p>
 *
 * @author tao.bai
 * @since 2020-06-02
 */
@AllArgsConstructor
@Configuration
public class ResourceLoader extends WebMvcConfigurationSupport {

    private final LocalConfig localConfig;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String localPath = localConfig.getFileDir().replaceAll("/", File.separator);
        registry.addResourceHandler("/public-api/file/**").addResourceLocations("file:" + localPath + File.separator);
        super.addResourceHandlers(registry);
    }

}
