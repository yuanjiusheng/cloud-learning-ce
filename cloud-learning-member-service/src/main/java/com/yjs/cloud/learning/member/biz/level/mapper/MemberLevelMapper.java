package com.yjs.cloud.learning.member.biz.level.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjs.cloud.learning.member.biz.level.bean.MemberLevelResponse;
import com.yjs.cloud.learning.member.biz.level.entity.MemberLevel;
import com.yjs.cloud.learning.member.biz.level.bean.MemberLevelGetPageRequest;
import com.yjs.cloud.learning.member.common.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 会员等级Mapper
 *
 * @author Bill.lai
 * @since 12/1/20
 */
public interface MemberLevelMapper extends IBaseMapper<MemberLevel> {

    /**
     * 获取会员列表
     * @param page 分页对象
     * @param memberLevelGetPageRequest 请求参数
     * @return 会员列表
     */
    @Select({"<script>" ,
            "select " ,
            "   u.id, u.update_time, u.create_time, u.name, u.description, u.conditions " ,
            "from t_member_level u " ,
            "where 1 = 1 " ,
            "   <if test='req.keyword != null and req.keyword != \"\"'>",
            "       and (" ,
            "           u.name like concat('%', #{req.keyword}, '%') " ,
            "       ) " ,
            "   </if>" ,
            "order by u.conditions ",
            "</script>"})
    Page<MemberLevelResponse> findList(Page<MemberLevelResponse> page, @Param("req") MemberLevelGetPageRequest memberLevelGetPageRequest);
}
