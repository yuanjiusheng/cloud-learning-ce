package com.yjs.cloud.learning.oss.biz.local.service;

import com.yjs.cloud.learning.oss.common.util.FileUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 服务实现
 *
 * @author Bill.lai
 * @since 2021/5/13
 */
@Slf4j
@AllArgsConstructor
@Service
public class LocalServiceImpl implements LocalService {

    @Override
    public String upload(MultipartFile file, String filePath) {
        StringBuilder sb = new StringBuilder();
        FileUtils.save(file, filePath);
        sb.append("/api/oss/public-api/file").append(File.separator).append(filePath);
        return sb.toString();
    }

}
