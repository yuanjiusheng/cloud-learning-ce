package com.yjs.cloud.learning.oss.biz.aliyun.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import com.yjs.cloud.learning.oss.biz.aliyun.config.AliYunOssConfig;
import com.yjs.cloud.learning.oss.common.util.StringUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

/**
 * <p>
 * 图片上传
 * </p>
 *
 * @author tao.bai
 * @since 2020-09-03
 */
@Slf4j
@Service
@AllArgsConstructor
public class AliYunOssServiceImpl implements AliYunOssService {

    private final AliYunOssConfig config;

    /**
     * 上传文件
     * @param file     文件
     * @param filePath 保存路劲
     * @return 图片路劲
     */
    @Override
    public String upload(MultipartFile file, String filePath) {
        OSS client = new OSSClientBuilder().build(config.getPoint(), config.getAccessKey(), config.getAccessSecret());
        try {
            byte[] content = file.getBytes();
            client.putObject(new PutObjectRequest(config.getBucketName(), filePath, new ByteArrayInputStream(content)));
            return config.getVisitPath() + filePath;
        } catch (OSSException e) {
            log.error("Error Message: " + e.getErrorMessage());
            log.error("Error Code:    " + e.getErrorCode());
            log.error("Request ID:    " + e.getRequestId());
            log.error("Host ID:       " + e.getHostId());
        } catch (IOException e) {
            log.error("文件转byte失败！");
        } finally {
            client.shutdown();
        }
        return "";
    }

    /**
     * 上传文件
     * @param source 源文件路径
     * @param filePath  图片名称(xx/xx.jpg 代表多级)
     * @return 图片地址
     */
    @Override
    public String upload(String source, String filePath) {
        OSS client = new OSSClientBuilder().build(config.getPoint(), config.getAccessKey(), config.getAccessSecret());
        try {
            client.putObject(new PutObjectRequest(config.getBucketName(), filePath, new File(source)));
            return config.getVisitPath() + filePath;
        } catch (OSSException e) {
            log.error("Error Message: " + e.getErrorMessage());
            log.error("Error Code:    " + e.getErrorCode());
            log.error("Request ID:    " + e.getRequestId());
            log.error("Host ID:       " + e.getHostId());
        } finally {
            client.shutdown();
        }
        return "";
    }

    /**
     * 删除文件
     * @param path 文件完整路劲包含文件名
     */
    @Override
    public void delete(String path) {
        if (!StringUtils.isEmpty(path)) {
            OSS client = new OSSClientBuilder().build(config.getPoint(), config.getAccessKey(), config.getAccessSecret());
            String objectName = path.substring(config.getVisitPath().length());
            try {
                client.deleteObject(config.getBucketName(), objectName);
            } catch (OSSException oe) {
                log.error("Error Message: " + oe.getErrorMessage());
                log.error("Error Code:    " + oe.getErrorCode());
                log.error("Request ID:    " + oe.getRequestId());
                log.error("Host ID:       " + oe.getHostId());
            } finally {
                client.shutdown();
            }
        }
    }
}
