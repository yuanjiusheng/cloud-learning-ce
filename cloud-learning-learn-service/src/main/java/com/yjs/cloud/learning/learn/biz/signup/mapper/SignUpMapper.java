package com.yjs.cloud.learning.learn.biz.signup.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjs.cloud.learning.learn.biz.record.bean.SignUpGetPageListRequest;
import com.yjs.cloud.learning.learn.biz.signup.bean.SignUpCountListRequest;
import com.yjs.cloud.learning.learn.biz.signup.bean.SignUpCountResponse;
import com.yjs.cloud.learning.learn.biz.signup.bean.SignUpResponse;
import com.yjs.cloud.learning.learn.biz.signup.entity.SignUp;
import com.yjs.cloud.learning.learn.common.mapper.IBaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * mapper 接口
 *
 * @author Bill.lai
 * @since 12/14/20
 */
public interface SignUpMapper extends IBaseMapper<SignUp> {

    /**
     * 获取课程学习人数统计列表
     * @param signUpCountListRequest 参数
     * @return 课程学习人数统计列表
     */
    @Select("<script>" +
            "select su.lesson_id, count(distinct su.member_id) num from t_sign_up su " +
            "where " +
            "   su.lesson_id in " +
            "   <foreach collection='req.lessonIdList' item='item' index='index' open='(' separator=',' close=')'> " +
            "       #{item} " +
            "   </foreach> " +
            "group by su.lesson_id " +
            "</script>")
    List<SignUpCountResponse> countList(@Param("req") SignUpCountListRequest signUpCountListRequest);

    /**
     * 获取学习记录列表
     * @param page 分页参数
     * @param signUpGetPageListRequest 参数
     * @return 学习记录列表
     */
    @Select({"<script>" +
            "select " +
            "    distinct lesson_id, max(create_time) " +
            "from t_sign_up su " +
            "where su.member_id = #{req.memberId} " +
            "group by su.lesson_id " +
            "</script>"})
    Page<SignUpResponse> getPageList(Page<SignUpResponse> page, @Param("req") SignUpGetPageListRequest signUpGetPageListRequest);

}
