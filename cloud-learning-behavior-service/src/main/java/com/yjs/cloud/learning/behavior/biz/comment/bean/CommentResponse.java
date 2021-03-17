package com.yjs.cloud.learning.behavior.biz.comment.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.yjs.cloud.learning.behavior.biz.api.member.bean.MemberResponse;
import com.yjs.cloud.learning.behavior.biz.comment.enums.CommentTopicType;
import com.yjs.cloud.learning.behavior.biz.like.bean.LikeResponse;
import com.yjs.cloud.learning.behavior.common.response.BaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 返回对象
 *
 * @author Bill.lai
 * @since 2020/8/20
 */
@EqualsAndHashCode(callSuper = true)
@ApiModel
@Data
public class CommentResponse extends BaseResponse {

    @ApiModelProperty(value = "主题id", example = "1")
    private Long topicId;

    @ApiModelProperty(value = "主题类型", example = "lesson")
    private CommentTopicType topicType;

    @ApiModelProperty(value = "评论内容", example = "1")
    private String content;

    @JSONField(serialzeFeatures= SerializerFeature.DisableCircularReferenceDetect)
    @ApiModelProperty(value = "评论人信息")
    private MemberResponse member;

    @ApiModelProperty(value = "回复评论列表")
    private List<ReplyCommentResponse> replyList;

    @ApiModelProperty(value = "会员点赞信息")
    private LikeResponse like;

    @ApiModelProperty(value = "评论点赞总数")
    private Long likeCount;

    @JSONField(serialzeFeatures = SerializerFeature.DisableCircularReferenceDetect)
    @ApiModelProperty(value = "主题信息")
    private CommentTopicResponse topic;

}
