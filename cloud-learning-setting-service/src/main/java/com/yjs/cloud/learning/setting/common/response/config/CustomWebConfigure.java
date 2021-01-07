package com.yjs.cloud.learning.setting.common.response.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义WebMvcConfigurerAdapter
 *
 * @author Bill.lai
 * @since 2019-12-10
 */
@Configuration
public class CustomWebConfigure extends WebMvcAutoConfiguration {

    /**
     * 重写HttpMessageConverters，统一返回Json
     * @return HttpMessageConverters
     */
    @Bean
    public HttpMessageConverters httpMessageConverters(){
        //创建FastJson信息转换对象
        FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
        //创建FastJson对象并设定序列化规则
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        // 中文乱码解决方案
        List<MediaType> mediaTypes = new ArrayList<>();
        //设定json格式且编码为UTF-8
        mediaTypes.add(MediaType.APPLICATION_JSON);
        fastJsonHttpMessageConverter.setSupportedMediaTypes(mediaTypes);
        //规则赋予转换对象
        fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);

        return new HttpMessageConverters(fastJsonHttpMessageConverter);
    }


}
