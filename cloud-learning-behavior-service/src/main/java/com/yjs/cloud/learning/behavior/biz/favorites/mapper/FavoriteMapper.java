package com.yjs.cloud.learning.behavior.biz.favorites.mapper;

import com.yjs.cloud.learning.behavior.biz.favorites.bean.FavoriteCountListRequest;
import com.yjs.cloud.learning.behavior.biz.favorites.bean.FavoriteCountResponse;
import com.yjs.cloud.learning.behavior.biz.favorites.entity.Favorite;
import com.yjs.cloud.learning.behavior.common.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author Bill.lai
 * @since 12/8/20
 */
public interface FavoriteMapper extends IBaseMapper<Favorite> {

    /**
     * 获取收藏统计列表
     * @param favoriteCountListRequest 参数
     * @return 收藏统计列表
     */
    @Select("<script>" +
            "select topic_id, count(*) num from t_favorite f " +
            "where f.topic_type = #{req.topicType} " +
            "   and f.topic_id in " +
            "   <foreach collection='req.topicIdList' item='item' index='index' open='(' separator=',' close=')'> " +
            "       #{item} " +
            "   </foreach> " +
            "   <if test='req.memberId != null'> " +
            "       f.member_id = #{req.memberId} " +
            "   </if> " +
            "group by f.topic_id " +
            "</script>")
    List<FavoriteCountResponse> countList(@Param("req") FavoriteCountListRequest favoriteCountListRequest);
}
