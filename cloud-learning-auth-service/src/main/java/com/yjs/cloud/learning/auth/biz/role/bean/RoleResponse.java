package com.yjs.cloud.learning.auth.biz.role.bean;

import com.yjs.cloud.learning.auth.common.response.BaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色实体
 *
 * @author Andrew.xiao
 * @since 2019/6/12
 */
@ApiModel
@EqualsAndHashCode(callSuper = true)
@Data
public class RoleResponse extends BaseResponse {

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色编号")
    private String code;

    @ApiModelProperty(value = "备注")
    private String remark;

}
