package com.yjs.cloud.learning.behavior.biz.like.bean;

import com.yjs.cloud.learning.behavior.biz.like.enums.LikeTopicType;
import com.yjs.cloud.learning.behavior.common.response.BaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 返回对象
 *
 * @author Bill.lai
 * @since 2020/8/20
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class LikeResponse extends BaseResponse {

    @ApiModelProperty(value = "主题id", example = "1")
    private Long topicId;

    @ApiModelProperty(value = "主题类型", example = "lesson")
    private LikeTopicType topicType;

    @ApiModelProperty(value = "点赞状态", example = "true")
    private Boolean status;

    @ApiModelProperty(value = "用户ID")
    private Long memberId;

}
