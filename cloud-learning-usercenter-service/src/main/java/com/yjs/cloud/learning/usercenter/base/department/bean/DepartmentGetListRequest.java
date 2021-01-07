package com.yjs.cloud.learning.usercenter.base.department.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 获取部门列表请求参数
 *
 * @author Bill.lai
 * @since 2020/9/28
 */
@Data
public class DepartmentGetListRequest {

    @ApiModelProperty(value = "id", example = "1")
    private Long id = 0L;

    @ApiModelProperty(value = "是否获取所有子节点（包括孙子节点...）", example = "true")
    private Boolean fetchAll = false;

}
