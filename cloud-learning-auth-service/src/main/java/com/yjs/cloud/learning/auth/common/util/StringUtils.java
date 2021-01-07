package com.yjs.cloud.learning.auth.common.util;

import org.springframework.lang.Nullable;

/**
 * 字符串工具
 *
 * @author Bill.lai
 * @since 1/7/21
 */
public class StringUtils {

    public static boolean isEmpty(@Nullable Object str) {
        return str == null || "".equals(str);
    }

}
