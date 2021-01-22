package com.yjs.cloud.learning.member.base.service;

import com.yjs.cloud.learning.member.base.bean.*;
import com.yjs.cloud.learning.member.base.entity.Member;
import com.yjs.cloud.learning.member.common.service.IBaseService;

import java.util.List;


/**
 * 会员服务
 *
 * @author Bill.lai
 * @since 2019-08-23
 */
public interface MemberService extends IBaseService<Member> {

    /**
     * 获取会员列表
     * @param memberGetPageRequest 请求参数
     * @return 会员列表
     */
    MemberGetPageResponse findList(MemberGetPageRequest memberGetPageRequest);

    /**
     * 根据手机号码获取会员信息
     * @param mobile 手机号码
     * @return 会员信息
     */
    MemberResponse getByMobile(String mobile);

    /**
     * 根据id列表获取会员信息
     * @param ids id列表
     * @return 会员信息
     */
    List<MemberResponse> getByIds(List<Long> ids);

    /**
     * 修改会员信息
     * @param memberUpdateRequest 参数
     * @return 会员信息
     */
    MemberResponse updateMember(MemberUpdateRequest memberUpdateRequest);

    /**
     * 注册会员
     * @param memberCreateRequest 参数
     * @return 会员信息
     */
    MemberResponse create(MemberCreateRequest memberCreateRequest);

    /**
     * 注册会员
     * @param memberCreateMobileRequest 参数
     * @return 会员信息
     */
    MemberResponse create(MemberCreateMobileRequest memberCreateMobileRequest);
}
