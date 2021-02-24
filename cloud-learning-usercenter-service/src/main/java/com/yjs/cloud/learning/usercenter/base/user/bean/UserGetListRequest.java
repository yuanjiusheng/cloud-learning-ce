package com.yjs.cloud.learning.usercenter.base.user.bean;

import com.yjs.cloud.learning.usercenter.common.request.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户列表请求参数
 *
 * @author Bill.lai
 * @since 2020/9/28
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class UserGetListRequest extends PageRequest {

    @ApiModelProperty(value = "部门id", example = "0", required = true)
    private Long departmentId = 0L;

    @ApiModelProperty(value = "是否包含子部门的用户")
    private Boolean fetchAll;

    @ApiModelProperty(value = "搜索关键字")
    private String keyword;
}
