package com.yjs.cloud.learning.comment.common.web;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 无权限错误信息
 *
 * @author Bill.lai
 * @since 2019-12-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UnauthorizedException extends RuntimeException {

    private final int code;

    private final String message;

    public UnauthorizedException(int code, String message){
        this.code = code;
        this.message = message;
    }

    public UnauthorizedException(String message){
        this.code = 401;
        this.message = message;
    }

}
