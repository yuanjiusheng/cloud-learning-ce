package com.yjs.cloud.learning.oss.biz.aliyun.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 图片上传
 * </p>
 *
 * @author tao.bai
 * @since 2020-09-03
 */
public interface AliYunOssService {

    /**
     * 上传文件
     * @param file     文件
     * @param filePath 保存路劲
     * @return 图片路劲
     */
    String upload(MultipartFile file, String filePath);

    /**
     * 上传文件
     * @param source 源文件路径
     * @param filePath  图片名称(xx/xx.jpg 代表多级)
     * @return 图片地址
     */
    String upload(String source, String filePath);

    /**
     * 删除文件
     * @param path 文件完整路劲包含文件名
     */
    void delete(String path);
}
