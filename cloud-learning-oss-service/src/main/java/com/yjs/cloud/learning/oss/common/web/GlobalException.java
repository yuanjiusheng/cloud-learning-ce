package com.yjs.cloud.learning.oss.common.web;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 全局错误信息
 *
 * @author Bill.lai
 * @since 2019-12-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class GlobalException extends RuntimeException {

    private final int code;

    private final String message;

    public GlobalException(int code, String message){
        this.code = code;
        this.message = message;
    }

    public GlobalException(String message){
        this.code = 555;
        this.message = message;
    }

}
