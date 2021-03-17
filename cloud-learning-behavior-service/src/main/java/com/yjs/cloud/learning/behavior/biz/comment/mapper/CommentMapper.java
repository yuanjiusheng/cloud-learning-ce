package com.yjs.cloud.learning.behavior.biz.comment.mapper;

import com.yjs.cloud.learning.behavior.biz.comment.bean.CommentCountListRequest;
import com.yjs.cloud.learning.behavior.biz.comment.bean.CommentCountResponse;
import com.yjs.cloud.learning.behavior.biz.comment.entity.Comment;
import com.yjs.cloud.learning.behavior.common.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * <p>
 * 评论Mapper接口
 * </p>
 *
 * @author bill.lai
 * @since 2020-07-07
 */
public interface CommentMapper extends IBaseMapper<Comment> {

    /**
     * 获取收藏统计列表
     * @param commentCountListRequest 参数
     * @return 收藏统计列表
     */
    @Select("<script>" +
            "select topic_id, sum(num) num from " +
            "(" +
            "select topic_id, count(*) num from t_comment f " +
            "where f.topic_type = #{req.topicType} " +
            "   and f.topic_id in " +
            "   <foreach collection='req.topicIdList' item='item' index='index' open='(' separator=',' close=')'> " +
            "       #{item} " +
            "   </foreach> " +
            "   <if test='req.memberId != null'> " +
            "       f.member_id = #{req.memberId} " +
            "   </if> " +
            "group by f.topic_id " +
            "union " +
            "select topic_id, count(*) num from t_comment f " +
            "inner join t_reply_comment trc on f.id = trc.comment_id " +
            "where f.topic_type = #{req.topicType} " +
            "   and f.topic_id in " +
            "   <foreach collection='req.topicIdList' item='item' index='index' open='(' separator=',' close=')'> " +
            "       #{item} " +
            "   </foreach> " +
            "   <if test='req.memberId != null'> " +
            "       f.member_id = #{req.memberId} " +
            "   </if> " +
            "group by f.topic_id " +
            ") a " +
            "group by topic_id " +
            "</script>")
    List<CommentCountResponse> countList(@Param("req") CommentCountListRequest commentCountListRequest);

}
