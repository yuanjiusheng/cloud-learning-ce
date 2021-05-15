package com.yjs.cloud.learning.member.base.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 请求参数
 *
 * @author Bill.lai
 * @since 2020/9/28
 */
@ApiModel
@Data
public class MemberGetRequest {

    @ApiModelProperty(value = "会员id")
    private Long id;

}
