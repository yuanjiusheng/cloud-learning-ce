package com.yjs.cloud.learning.auth.biz.role.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 角色
 *
 * @author bill.lai
 * @since 2019/6/12
 */
@ApiModel
@Data
public class RoleUpdateRequest {

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "角色名称")
    private String name;

    @ApiModelProperty(value = "角色编号")
    private String code;

    @ApiModelProperty(value = "备注")
    private String remark;

}
