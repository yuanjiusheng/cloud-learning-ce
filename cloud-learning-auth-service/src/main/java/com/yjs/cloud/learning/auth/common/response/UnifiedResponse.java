package com.yjs.cloud.learning.auth.common.response;

import lombok.Data;

/**
 * 返回对象
 *
 * @author Bill.lai
 * @since 2019-12-04
 */
@Data
public class UnifiedResponse {

    /**
     * 操作状态码
     */
    private int code = 0;

    /**
     * 操作消息
     */
    private String msg = "ok";

    /**
     * 业务数据
     */
    private Object data;

    public UnifiedResponse() {}

    public UnifiedResponse(Object data){
        this.data = data;
    }

    public UnifiedResponse(int code, String msg, Object data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
