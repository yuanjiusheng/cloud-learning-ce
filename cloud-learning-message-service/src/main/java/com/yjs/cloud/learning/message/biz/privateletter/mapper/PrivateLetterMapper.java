package com.yjs.cloud.learning.message.biz.privateletter.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjs.cloud.learning.message.biz.privateletter.bean.PrivateLetterGetMemberListRequest;
import com.yjs.cloud.learning.message.biz.privateletter.bean.PrivateLetterGetSenderListRequest;
import com.yjs.cloud.learning.message.biz.privateletter.bean.PrivateLetterResponse;
import com.yjs.cloud.learning.message.biz.privateletter.entity.PrivateLetter;
import com.yjs.cloud.learning.message.common.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Mapper
 *
 * @author Bill.lai
 * @since 12/22/20
 */
public interface PrivateLetterMapper extends IBaseMapper<PrivateLetter> {

    @Select("<script>" +
            "select sender_id, max(id) id, max(create_time) create_time " +
            "from ( " +
            "  select " +
            "     pl.id, " +
            "     pl.receiver_id sender_id, " +
            "     pl.create_time " +
            "  from t_private_letter pl " +
            "  where pl.sender_id = #{req.memberId} " +
            "  union " +
            "  select pl.id, " +
            "         pl.sender_id, " +
            "         pl.create_time " +
            "  from t_private_letter pl " +
            "  where pl.receiver_id = #{req.memberId} " +
            ") a " +
            "group by sender_id " +
            "order by create_time desc " +
            "</script>")
    Page<PrivateLetterResponse> getMemberList(Page<PrivateLetterResponse> page, @Param("req") PrivateLetterGetMemberListRequest privateLetterGetMemberListRequest);

    @Select("<script>" +
            "  select * from " +
            "( " +
            "  select pl.id, " +
            "         pl.sender_id, " +
            "         pl.receiver_id, " +
            "         pl.content, " +
            "         pl.read_time, " +
            "         pl.is_read, " +
            "         pl.status, " +
            "         pl.create_time " +
            "  from t_private_letter pl " +
            "  where pl.sender_id = #{req.memberId} " +
            "    and pl.receiver_id = #{req.senderId} " +
            "  union " +
            "  select pl.id, " +
            "         pl.sender_id, " +
            "         pl.receiver_id, " +
            "         pl.content, " +
            "         pl.read_time, " +
            "         pl.is_read, " +
            "         pl.status, " +
            "         pl.create_time " +
            "  from t_private_letter pl " +
            "  where pl.receiver_id = #{req.memberId} " +
            "    and pl.sender_id = #{req.senderId} " +
            ") pl " +
            "order by pl.create_time desc, pl.id desc " +
            "</script>")
    Page<PrivateLetterResponse> getLetterList(Page<PrivateLetterResponse> page, @Param("req") PrivateLetterGetSenderListRequest privateLetterGetSenderListRequest);
}
