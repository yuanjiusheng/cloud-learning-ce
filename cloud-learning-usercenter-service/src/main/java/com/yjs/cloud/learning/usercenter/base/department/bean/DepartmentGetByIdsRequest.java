package com.yjs.cloud.learning.usercenter.base.department.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 获取部门列表请求参数
 * @author Bill.lai
 * @since 2020/9/28
 */
@ApiModel
@Data
public class DepartmentGetByIdsRequest {

    @ApiModelProperty(value = "id列表")
    private List<Long> ids;

}
