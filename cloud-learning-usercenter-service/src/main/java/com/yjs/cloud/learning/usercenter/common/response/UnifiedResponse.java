package com.yjs.cloud.learning.usercenter.common.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 返回对象
 *
 * @author Bill.lai
 * @since 2019-12-04
 */
@ApiModel
@Data
public class UnifiedResponse {

    @ApiModelProperty(value = "操作状态码，0=成功")
    private int code = 0;

    @ApiModelProperty(value = "操作消息")
    private String msg = "ok";

    @ApiModelProperty(value = "业务数据")
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
