package com.yjs.cloud.learning.learn.biz.api.comment.bean;

import com.yjs.cloud.learning.learn.biz.api.comment.enums.CommentTopicType;
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
public class CommentCountListRequest {

    @ApiModelProperty(value = "主题id")
    private List<Long> topicIdList;

    @ApiModelProperty(value = "主题类型", example = "lesson")
    private CommentTopicType topicType;

    @ApiModelProperty(value = "用户ID", hidden = true)
    private Long memberId;
}
