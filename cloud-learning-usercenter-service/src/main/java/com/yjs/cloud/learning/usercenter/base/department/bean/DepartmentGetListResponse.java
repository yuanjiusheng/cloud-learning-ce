package com.yjs.cloud.learning.usercenter.base.department.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 获取部门列表请求返回结果
 *
 * @author Bill.lai
 * @since 2020/9/28
 */
@Data
public class DepartmentGetListResponse {

    @ApiModelProperty(value = "部门列表")
    private List<DepartmentResponse> list;

}
