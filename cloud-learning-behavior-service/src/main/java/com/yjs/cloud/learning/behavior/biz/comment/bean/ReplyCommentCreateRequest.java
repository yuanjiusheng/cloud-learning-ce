package com.yjs.cloud.learning.behavior.biz.comment.bean;

import com.yjs.cloud.learning.behavior.biz.comment.entity.ReplyComment;
import com.yjs.cloud.learning.behavior.common.util.BeanCopierUtils;
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
public class ReplyCommentCreateRequest {

    @ApiModelProperty(value = "评论id", example = "1", required = true)
    private Long commentId;

    @ApiModelProperty(value = "评论内容", example = "1", required = true)
    private String content;

    @ApiModelProperty(value = "用户id", example = "1", hidden = true)
    private Long memberId;

    @ApiModelProperty(value = "回复的评论id，也就是父ID，回复评论表的评论是，值跟评论id相等", example = "1", required = true)
    private Long replyCommentId;

    @ApiModelProperty(value = "回复的评论的用户id", example = "1", required = true)
    private Long toMemberId;

    public ReplyComment convert() {
        ReplyComment entity = new ReplyComment();
        BeanCopierUtils.copy(this, entity);
        return entity;
    }

}
