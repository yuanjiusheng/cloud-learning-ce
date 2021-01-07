package com.yjs.cloud.learning.member.biz.level.bean;

import com.yjs.cloud.learning.member.common.response.BaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会员返回对象
 *
 * @author Bill.lai
 * @since 2019/6/6
 */
@ApiModel
@EqualsAndHashCode(callSuper = true)
@Data
public class MemberLevelResponse extends BaseResponse {

    @ApiModelProperty(value = "等级名称")
    private String name;

    @ApiModelProperty(value = "描述")
    private String description;

    @ApiModelProperty(value = "条件")
    private String conditions;

}
