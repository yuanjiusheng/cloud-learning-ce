package com.yjs.cloud.learning.auth.biz.role.bean;

import com.yjs.cloud.learning.auth.biz.authority.entity.Authority;
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
public class RoleAuthorityUpdateRequest {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "权限列表")
    private List<Authority> authorityIdList;

}
