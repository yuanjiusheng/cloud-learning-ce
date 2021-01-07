package com.yjs.cloud.learning.comment.biz.comment.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yjs.cloud.learning.comment.biz.api.member.bean.MemberResponse;
import com.yjs.cloud.learning.comment.biz.like.bean.LikeResponse;
import com.yjs.cloud.learning.comment.common.response.BaseResponse;
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
public class ReplyCommentResponse extends BaseResponse {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "评论id", example = "1")
    private Long commentId;

    @ApiModelProperty(value = "评论回复内容", example = "1")
    private String content;

    @JSONField(serialzeFeatures= SerializerFeature.DisableCircularReferenceDetect)
    @ApiModelProperty(value = "当前评论的评论人信息")
    private MemberResponse member;

    @JSONField(serialzeFeatures= SerializerFeature.DisableCircularReferenceDetect)
    @ApiModelProperty(value = "回复的评论的用户信息")
    private MemberResponse toMember;

    @ApiModelProperty(value = "回复评论id，也就是父ID，回复评论表的评论是，值跟评论id相等")
    private Long replyCommentId;

    @ApiModelProperty(value = "会员点赞信息")
    private LikeResponse like;

    @ApiModelProperty(value = "评论点赞总数")
    private Long likeCount;

}
