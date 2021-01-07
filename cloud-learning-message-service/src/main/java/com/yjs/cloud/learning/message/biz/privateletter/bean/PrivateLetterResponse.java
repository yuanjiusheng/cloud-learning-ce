package com.yjs.cloud.learning.message.biz.privateletter.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yjs.cloud.learning.message.biz.api.member.bean.MemberResponse;
import com.yjs.cloud.learning.message.biz.privateletter.enums.PrivateLetterStatus;
import com.yjs.cloud.learning.message.common.response.BaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 私人信件
 *
 * @author Bill.lai
 * @since 12/22/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel
public class PrivateLetterResponse extends BaseResponse {

    @ApiModelProperty(value = "发送者id")
    private Long senderId;

    @JSONField(serialzeFeatures= SerializerFeature.DisableCircularReferenceDetect)
    @ApiModelProperty(value = "发送者")
    private MemberResponse sender;

    @ApiModelProperty(value = "接受者id")
    private Long receiverId;

    @JSONField(serialzeFeatures= SerializerFeature.DisableCircularReferenceDetect)
    @ApiModelProperty(value = "接收者")
    private MemberResponse receiver;

    @ApiModelProperty(value = "私信内容")
    private String content;

    @ApiModelProperty(value = "阅读时间")
    private LocalDateTime readTime;

    @ApiModelProperty(value = "是否已读")
    private Boolean isRead;

    @ApiModelProperty(value = "状态")
    private PrivateLetterStatus status;

}
