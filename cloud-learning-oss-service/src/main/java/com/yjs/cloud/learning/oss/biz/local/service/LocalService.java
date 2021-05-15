package com.yjs.cloud.learning.oss.biz.local.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 本地
 *
 * @author Bill.lai
 * @since 2021/5/13
 */
public interface LocalService {

    /**
     * 保存文件
     * @param file 文件
     * @param filePath 保存路径
     * @return 文件地址
     */
    String upload(MultipartFile file, String filePath);
}
