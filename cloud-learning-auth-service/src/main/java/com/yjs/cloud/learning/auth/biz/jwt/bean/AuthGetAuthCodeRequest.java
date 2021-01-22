package com.yjs.cloud.learning.auth.biz.jwt.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 获取验证码请求参数
 *
 * @author Bill.lai
 * @since 1/22/21
 */
@ApiModel
@Data
public class AuthGetAuthCodeRequest {

    @ApiModelProperty(value = "手机号码")
    private String mobile;

}
