package com.yjs.cloud.learning.gateway.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 企业微信认证请求实体
 *
 * @author Bill.lai
 * @since 2020/6/1
 */
@ApiModel("企业微信认证请求实体")
@Data
public class WorkWeChatAuthenticationRequest {

    @ApiModelProperty(value = "用户自定义字段，与获取登录二维码的state相同", example = "xxx", required = true)
    private String state;

    @ApiModelProperty(value = "通过成员授权获取到的code，最大为512字节。每次成员授权带上的code将不一样，code只能使用一次，5分钟未被使用自动过期。", example = "xxx", required = true)
    private String code;

    @ApiModelProperty(value = "企业微信的企业id（cropId）", example = "xxx", required = true)
    private String appId;

}
