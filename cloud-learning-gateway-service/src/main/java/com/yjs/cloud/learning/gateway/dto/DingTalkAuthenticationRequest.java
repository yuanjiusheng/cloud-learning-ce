package com.yjs.cloud.learning.gateway.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 钉钉认证请求实体
 *
 * @author Bill.lai
 * @since 2020/6/1
 */
@ApiModel("钉钉认证请求实体")
@Data
public class DingTalkAuthenticationRequest {

    @ApiModelProperty(value = "用户自定义字段，与获取登录二维码的state相同", example = "xxx", required = true)
    private String state;

    @ApiModelProperty(value = "钉钉授权码。每次成员授权带上的code将不一样，code只能使用一次。", example = "xxx", required = true)
    private String code;

}
