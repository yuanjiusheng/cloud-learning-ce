package com.yjs.cloud.learning.usercenter.biz.dingtalk.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Bill.lai
 * @since 1/22/21
 */
@ApiModel
@Data
public class DingTalkGetUserByCodeRequest {

    @ApiModelProperty(value = "授权码")
    private String code;

}
