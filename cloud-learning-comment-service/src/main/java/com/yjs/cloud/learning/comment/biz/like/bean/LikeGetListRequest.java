package com.yjs.cloud.learning.comment.biz.like.bean;

import com.yjs.cloud.learning.comment.biz.like.enums.LikeTopicType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * 请求参数
 *
 * @author Bill.lai
 * @since 2020/8/20
 */
@ApiModel
@Data
public class LikeGetListRequest {

    @ApiModelProperty(value = "主题id")
    private List<Long> topicIdList;

    @ApiModelProperty(value = "主题类型", example = "lesson")
    private LikeTopicType topicType;

    @ApiModelProperty(value = "点赞状态", example = "true")
    private Boolean status;

    @ApiModelProperty(value = "用户ID", hidden = true)
    private Long memberId;

}
