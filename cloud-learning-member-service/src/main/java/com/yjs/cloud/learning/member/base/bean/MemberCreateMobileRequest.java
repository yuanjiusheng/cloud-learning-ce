package com.yjs.cloud.learning.member.base.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 会员注册对象
 *
 * @author Bill.lai
 * @since 2019/6/6
 */
@ApiModel
@Data
public class MemberCreateMobileRequest {

    @ApiModelProperty(value = "手机号码")
    private String mobile;

    @ApiModelProperty(value = "验证码")
    private String authCode;

}
