package com.yjs.cloud.learning.auth.biz.role.bean;

import com.yjs.cloud.learning.auth.biz.role.entity.Role;
import com.yjs.cloud.learning.auth.common.response.PageResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


/**
 * 角色分页列表请求返回结果
 *
 * @author Bill.lai
 * @since 2020/9/28
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class RoleGetPageResponse extends PageResponse {

    @ApiModelProperty(value = "角色列表")
    private List<Role> list;

}
