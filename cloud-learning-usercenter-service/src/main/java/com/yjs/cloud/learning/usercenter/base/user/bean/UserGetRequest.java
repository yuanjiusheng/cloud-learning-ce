package com.yjs.cloud.learning.usercenter.base.user.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 请求参数
 *
 * @author Bill.lai
 * @since 2020/9/28
 */
@ApiModel
@Data
public class UserGetRequest {

    @ApiModelProperty(value = "用户id")
    private Long id;

}
