package com.yjs.cloud.learning.message.common.web;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 服务错误信息
 *
 * @author Bill.lai
 * @since 2019-12-10
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ServiceException extends RuntimeException {

    private final int code;

    private final String message;

    public ServiceException(int code, String message){
        this.code = code;
        this.message = message;
    }

    public ServiceException(String message){
        this.code = 555;
        this.message = message;
    }

}
