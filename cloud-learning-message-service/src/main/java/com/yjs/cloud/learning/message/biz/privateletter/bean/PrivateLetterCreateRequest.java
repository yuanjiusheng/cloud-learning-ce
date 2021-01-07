package com.yjs.cloud.learning.message.biz.privateletter.bean;

import com.yjs.cloud.learning.message.biz.privateletter.entity.PrivateLetter;
import com.yjs.cloud.learning.message.biz.privateletter.enums.PrivateLetterStatus;
import com.yjs.cloud.learning.message.common.util.BeanCopierUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 请求参数
 *
 * @author Bill.lai
 * @since 12/22/20
 */
@Data
@ApiModel
public class PrivateLetterCreateRequest {

    @ApiModelProperty(value = "发送者id")
    private Long senderId;

    @ApiModelProperty(value = "接受者id")
    private Long receiverId;

    @ApiModelProperty(value = "私信内容")
    private String content;

    @ApiModelProperty(value = "阅读时间")
    private LocalDateTime readTime;

    @ApiModelProperty(value = "是否已读")
    private Boolean isRead;

    @ApiModelProperty(value = "状态")
    private PrivateLetterStatus status;

    public PrivateLetter convert() {
        PrivateLetter entity = new PrivateLetter();
        BeanCopierUtils.copy(this, entity);
        return entity;
    }

}
