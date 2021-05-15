package com.yjs.cloud.learning.message.biz.privateletter.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 请求参数
 *
 * @author Bill.lai
 * @since 12/22/20
 */
@Data
@ApiModel
public class PrivateLetterGetMemberRequest {

    @ApiModelProperty(value = "发送者id", hidden = true)
    private Long senderId;

    @ApiModelProperty(value = "接收者id")
    private Long memberId;

}
