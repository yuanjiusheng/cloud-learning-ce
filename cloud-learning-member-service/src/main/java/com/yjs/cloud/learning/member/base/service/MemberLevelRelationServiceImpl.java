package com.yjs.cloud.learning.member.base.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yjs.cloud.learning.member.base.bean.MemberLevelRelationUpdateRequest;
import com.yjs.cloud.learning.member.base.entity.MemberLevelRelation;
import com.yjs.cloud.learning.member.base.mapper.MemberLevelRelationMapper;
import com.yjs.cloud.learning.member.biz.level.bean.MemberLevelResponse;
import com.yjs.cloud.learning.member.biz.level.entity.MemberLevel;
import com.yjs.cloud.learning.member.biz.level.service.MemberLevelService;
import com.yjs.cloud.learning.member.common.service.BaseServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * service
 *
 * @author Bill.lai
 * @since 2021/3/12
 */
@AllArgsConstructor
@Service
public class MemberLevelRelationServiceImpl extends BaseServiceImpl<MemberLevelRelationMapper, MemberLevelRelation> implements MemberLevelRelationService {

    private final MemberLevelService memberLevelService;

    @Override
    public void updateMemberLevel(MemberLevelRelationUpdateRequest memberLevelRelationUpdateRequest) {
        if (memberLevelRelationUpdateRequest.getPoint() != null) {
            LambdaQueryWrapper<MemberLevel> levelLambdaQueryWrapper = new LambdaQueryWrapper<>();
            levelLambdaQueryWrapper.lt(MemberLevel::getConditions, memberLevelRelationUpdateRequest.getPoint()).or().eq(MemberLevel::getConditions, memberLevelRelationUpdateRequest.getPoint());
            levelLambdaQueryWrapper.orderByDesc(MemberLevel::getConditions);
            List<MemberLevel> list = memberLevelService.list(levelLambdaQueryWrapper);
            if (!CollectionUtils.isEmpty(list)) {
                LambdaQueryWrapper<MemberLevelRelation> l = new LambdaQueryWrapper<>();
                l.eq(MemberLevelRelation::getMemberId, memberLevelRelationUpdateRequest.getMemberId());
                MemberLevelRelation memberLevelRelation = getOne(l);
                if (memberLevelRelation == null) {
                    memberLevelRelation = new MemberLevelRelation();
                    memberLevelRelation.setMemberId(memberLevelRelationUpdateRequest.getMemberId());
                    memberLevelRelation.setLevelId(list.get(0).getId());
                    save(memberLevelRelation);
                } else {
                    memberLevelRelation.setLevelId(list.get(0).getId());
                    updateById(memberLevelRelation);
                }
            }
        }
    }

    @Override
    public MemberLevelResponse getLevel(Long memberId) {
        LambdaQueryWrapper<MemberLevelRelation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(MemberLevelRelation::getMemberId, memberId);
        MemberLevelRelation relation = getOne(lambdaQueryWrapper);
        if (relation == null) {
            return null;
        }
        return memberLevelService.getById(relation.getLevelId()).convert();
    }

}
