package com.yjs.cloud.learning.member.base.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjs.cloud.learning.member.base.bean.MemberResponse;
import com.yjs.cloud.learning.member.base.entity.Member;
import com.yjs.cloud.learning.member.base.bean.MemberGetPageRequest;
import com.yjs.cloud.learning.member.common.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * 会员Mapper
 *
 * @author Bill.lai
 * @since 2019-08-23
 */
@Repository
public interface MemberMapper extends IBaseMapper<Member> {

    /**
     * 获取会员列表
     * @param page 分页对象
     * @param memberGetListRequest 请求参数
     * @return 会员列表
     */
    @Select({"<script>" ,
            "select " +
            "   u.id, u.update_time, u.create_time, u.username, u.code, u.name, u.status, u.gender, " ,
            "   u.telephone, u.mobile, u.email, u.birthday " +
            "from t_member u " ,
            "where 1 = 1 " ,
            "   <if test='req.keyword != null and req.keyword != \"\"'>",
            "       and (" +
            "              u.mobile like concat('%', #{req.keyword}, '%') " +
            "           or u.name like concat('%', #{req.keyword}, '%') " +
            "           or u.code like concat('%', #{req.keyword}, '%') " +
            "       ) " ,
            "   </if>",
            "</script>"})
    Page<MemberResponse> findList(Page<MemberResponse> page, @Param("req") MemberGetPageRequest memberGetListRequest);

}
