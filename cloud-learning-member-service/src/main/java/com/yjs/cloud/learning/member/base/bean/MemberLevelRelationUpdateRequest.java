package com.yjs.cloud.learning.member.base.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 请求参数
 *
 * @author Bill.lai
 * @since 2021/3/12
 */
@ApiModel
@Data
public class MemberLevelRelationUpdateRequest {

    @ApiModelProperty(value = "会员id")
    private Long memberId;

    @ApiModelProperty(value = "会员积分")
    private Long point;

}
