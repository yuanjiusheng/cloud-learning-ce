package com.yjs.cloud.learning.behavior.biz.like.mapper;

import com.yjs.cloud.learning.behavior.biz.like.bean.LikeCountListRequest;
import com.yjs.cloud.learning.behavior.biz.like.bean.LikeCountResponse;
import com.yjs.cloud.learning.behavior.biz.like.entity.Like;
import com.yjs.cloud.learning.behavior.common.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Bill.lai
 * @since 12/8/20
 */
public interface LikeMapper extends IBaseMapper<Like> {

    /**
     * 获取点赞统计列表
     * @param likeCountListRequest 参数
     * @return 点赞统计列表
     */
    @Select("<script>" +
            "select topic_id, count(*) num from t_like l " +
            "where l.topic_type = #{req.topicType} " +
            "   and l.status = 1 " +
            "   and l.topic_id in " +
            "   <foreach collection='req.topicIdList' item='item' index='index' open='(' separator=',' close=')'> " +
            "       #{item} " +
            "   </foreach> " +
            "   <if test='req.memberId != null'> " +
            "       l.member_id = #{req.memberId} " +
            "   </if> " +
            "group by l.topic_id " +
            "</script>")
    List<LikeCountResponse> countList(@Param("req") LikeCountListRequest likeCountListRequest);
}
