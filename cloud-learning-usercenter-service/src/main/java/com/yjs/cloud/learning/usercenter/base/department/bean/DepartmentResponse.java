package com.yjs.cloud.learning.usercenter.base.department.bean;

import com.yjs.cloud.learning.usercenter.common.response.BaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 部门返回对象
 *
 * @author Bill.lai
 * @since 2020/9/28
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class DepartmentResponse extends BaseResponse {

    @ApiModelProperty(value = "编号")
    private String code;

    @ApiModelProperty(value = "名称")
    private String name;

    @ApiModelProperty(value = "是否启用")
    private Boolean enabled;

    @ApiModelProperty(value = "父级id", example = "0")
    private Long pid;

}
