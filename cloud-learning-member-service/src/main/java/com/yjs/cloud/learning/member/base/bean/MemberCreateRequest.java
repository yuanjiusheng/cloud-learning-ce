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
public class MemberCreateRequest {

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "确认密码")
    private String confirmPassword;

}
