package com.yjs.cloud.learning.usercenter.biz.workwechat.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Bill.lai
 * @since 1/22/21
 */
@ApiModel
@Data
public class WorkWeChatGetUserByCodeRequest {

    @ApiModelProperty(value = "授权码")
    private String code;

}
