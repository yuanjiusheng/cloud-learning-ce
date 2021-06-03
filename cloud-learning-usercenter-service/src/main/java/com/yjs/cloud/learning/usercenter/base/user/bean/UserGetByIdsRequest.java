package com.yjs.cloud.learning.usercenter.base.user.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户列表请求参数
 *
 * @author Bill.lai
 * @since 2020/9/28
 */
@ApiModel
@Data
public class UserGetByIdsRequest {

    @ApiModelProperty(value = "用户id列表")
    private List<Long> ids;

}
