package com.yjs.cloud.learning.auth.biz.role.bean;

import com.yjs.cloud.learning.auth.biz.role.entity.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 请求参数
 *
 * @author Bill.lai
 * @since 2020/9/28
 */
@ApiModel
@Data
public class RoleUserUpdateRequest {

    @ApiModelProperty(value = "用户id")
    private Long userId;

    @ApiModelProperty(value = "角色列表")
    private List<Role> roleList;

}
