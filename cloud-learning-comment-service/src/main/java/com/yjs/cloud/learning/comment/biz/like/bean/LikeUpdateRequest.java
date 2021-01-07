package com.yjs.cloud.learning.comment.biz.like.bean;

import com.yjs.cloud.learning.comment.biz.like.enums.LikeTopicType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 请求参数
 *
 * @author Bill.lai
 * @since 2020/8/20
 */
@ApiModel
@Data
public class LikeUpdateRequest {

    @ApiModelProperty(value = "id", example = "1")
    private Long id;

    @ApiModelProperty(value = "主题id", example = "1")
    private Long topicId;

    @ApiModelProperty(value = "主题类型", example = "lesson")
    private LikeTopicType topicType;

    @ApiModelProperty(value = "点赞状态", example = "true")
    private Boolean status;

    @ApiModelProperty(value = "用户ID")
    private Long memberId;

}
