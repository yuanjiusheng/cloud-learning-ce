package com.yjs.cloud.learning.member.base.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 用户列表请求参数
 *
 * @author Bill.lai
 * @since 2020/9/28
 */
@ApiModel
@Data
public class MemberGetByIdsRequest {

    @ApiModelProperty(value = "会员id列表")
    public List<Long> ids;

}
