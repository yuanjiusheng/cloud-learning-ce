package com.yjs.cloud.learning.message.biz.privateletter.bean;

import com.yjs.cloud.learning.message.common.request.PageRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 请求参数
 *
 * @author Bill.lai
 * @since 12/22/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class PrivateLetterGetSenderListRequest extends PageRequest {

    @ApiModelProperty(value = "当前会员id", hidden = true)
    private Long memberId;

    @ApiModelProperty(value = "聊天会员id")
    private Long senderId;

    @ApiModelProperty(value = "最小聊天id")
    private Long id = 0L;

}
