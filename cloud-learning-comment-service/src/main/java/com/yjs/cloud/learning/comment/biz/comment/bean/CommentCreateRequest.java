package com.yjs.cloud.learning.comment.biz.comment.bean;

import com.yjs.cloud.learning.comment.biz.comment.entity.Comment;
import com.yjs.cloud.learning.comment.biz.comment.enums.CommentTopicType;
import com.yjs.cloud.learning.comment.common.util.BeanCopierUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商品类目参数
 *
 * @author Bill.lai
 * @since 2020/7/7
 */
@ApiModel
@Data
public class CommentCreateRequest {

    @ApiModelProperty(value = "主题id", example = "lesson", hidden = true)
    private Long topicId;

    @ApiModelProperty(value = "主题类型", example = "lesson", hidden = true)
    private CommentTopicType topicType;

    @ApiModelProperty(value = "评论内容", example = "1", required = true)
    private String content;

    @ApiModelProperty(value = "用户id", example = "1", hidden = true)
    private Long memberId;

    public Comment convert() {
        Comment entity = new Comment();
        BeanCopierUtils.copy(this, entity);
        return entity;
    }

}
