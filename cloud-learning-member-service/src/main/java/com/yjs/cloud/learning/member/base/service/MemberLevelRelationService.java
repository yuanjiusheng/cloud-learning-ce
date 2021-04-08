package com.yjs.cloud.learning.member.base.service;

import com.yjs.cloud.learning.member.base.bean.MemberLevelRelationUpdateRequest;
import com.yjs.cloud.learning.member.base.entity.MemberLevelRelation;
import com.yjs.cloud.learning.member.biz.level.bean.MemberLevelResponse;
import com.yjs.cloud.learning.member.common.service.IBaseService;

/**
 * service
 *
 * @author Bill.lai
 * @since 2021/3/12
 */
public interface MemberLevelRelationService extends IBaseService<MemberLevelRelation> {
    /**
     * 更新用户等级
     * @param memberLevelRelationUpdateRequest 请求参数
     */
    void updateMemberLevel(MemberLevelRelationUpdateRequest memberLevelRelationUpdateRequest);

    /**
     * 获取会员等级信息
     * @param memberId 会员id
     * @return 等级信息
     */
    MemberLevelResponse getLevel(Long memberId);
}
