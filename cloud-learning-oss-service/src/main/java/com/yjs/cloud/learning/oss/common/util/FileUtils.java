package com.yjs.cloud.learning.oss.common.util;

import com.yjs.cloud.learning.oss.biz.local.config.LocalConfig;
import com.yjs.cloud.learning.oss.common.web.GlobalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * 文件工具类
 * @author bill.lai
 * @since 2020-07-09
 */
@Slf4j
public class FileUtils {


    /**
     * 保存文件
     * @param multipartFile 文件
     * @param absoluteTargetPath 保存文件的相对路径，用户指定目标文件夹
     * @return 文件路径
     */
    public static String save(MultipartFile multipartFile, String... absoluteTargetPath) {
        LocalConfig localConfig = SpringContextUtils.getBean("localConfig");
        // 保存文件根路径
        String dirPath = localConfig.getFileDir();
        // 文件名
        String fileName;
        // 如果有指定目标文件夹
        if (absoluteTargetPath != null && absoluteTargetPath.length > 0) {
            fileName = absoluteTargetPath[0].substring(absoluteTargetPath[0].lastIndexOf("/"));
            dirPath += File.separator + absoluteTargetPath[0].replaceAll(fileName, "");
            File folder = new File(dirPath);
            if (!folder.exists() && !folder.isDirectory()) {
                folder.mkdirs();
            }
        } else {
            fileName = DateUtils.parseString(new Date(), "yyyy-MM-dd") + "-" + multipartFile.getOriginalFilename();
        }
        // 文件全路径
        String filePath = dirPath + File.separator + fileName;
        try {
            // 保存文件
            multipartFile.transferTo(new File(filePath));
        } catch (IOException e) {
            log.error("保存文件出错", e);
            throw new GlobalException("保存文件出错");
        }
        return filePath;
    }

    /**
     * 删除文件或文件夹
     * @param absoluteTargetPath 相对路径
     */
    public static void delete(String absoluteTargetPath) {
        LocalConfig localConfig = SpringContextUtils.getBean("localConfig");
        absoluteTargetPath.replaceAll("/api/oss/public-api/file", "");
        String filePath = localConfig.getFileDir() + absoluteTargetPath;
        delete(new File(filePath));
    }

    /**
     * 删除文件或文件夹
     * @param file 文件
     */
    public static void delete(File file){
        //判断是否为文件夹
        if(file.isDirectory()){
            //获取该文件夹下的子文件夹
            File[] files = file.listFiles();
            if (files != null && files.length > 0) {
                //循环子文件夹重复调用delete方法
                for (File value : files) {
                    delete(value);
                }
            }
        }
        //若为空文件夹或者文件删除，File类的删除方法
        file.delete();
    }
}
