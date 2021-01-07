package com.yjs.cloud.learning.usercenter.base.user.bean;

import com.yjs.cloud.learning.usercenter.common.response.PageResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用户列表请求返回结果
 *
 * @author Bill.lai
 * @since 2020/9/28
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class UserGetListResponse extends PageResponse {

    @ApiModelProperty(value = "用户列表")
    private List<UserResponse> list;

}
