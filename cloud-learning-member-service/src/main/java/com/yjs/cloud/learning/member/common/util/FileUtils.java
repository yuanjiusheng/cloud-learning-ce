package com.yjs.cloud.learning.member.common.util;

/**
 * 文件工具类
 * @author bill.lai
 * @since 2020-07-09
 */
public class FileUtils {

    /**
     * 获取文件后缀名
     *
     * @param fileName 文件名
     * @return 后缀名（如果没有后缀名返回 空字符串）
     */
    public static String getSuffix(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            return "";
        }
        int index = fileName.lastIndexOf(".");
        if (index < 0) {
            return "";
        }
        return fileName.substring(index);
    }
}
