package com.yjs.cloud.learning.learn.biz.lesson.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yjs.cloud.learning.learn.biz.lesson.entity.LessonTargetMember;
import com.yjs.cloud.learning.learn.biz.lesson.mapper.LessonTargetMemberMapper;
import com.yjs.cloud.learning.learn.common.service.BaseServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * service
 *
 * @author Bill.lai
 * @since 2021/6/1
 */
@Service
public class LessonTargetMemberServiceImpl extends BaseServiceImpl<LessonTargetMemberMapper, LessonTargetMember> implements LessonTargetMemberService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createOrUpdate(Long id, List<Long> targetMemberIdList) {
        LambdaQueryWrapper<LessonTargetMember> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(LessonTargetMember::getLessonId, id);
        remove(lambdaQueryWrapper);
        if (!CollectionUtils.isEmpty(targetMemberIdList)) {
            for (Long memberId : targetMemberIdList) {
                LessonTargetMember lessonTargetMember = new LessonTargetMember();
                lessonTargetMember.setMemberId(memberId);
                lessonTargetMember.setLessonId(id);
                save(lessonTargetMember);
            }
        }
    }

    @Override
    public List<Long> getTargetMemberIdList(Long lessonId) {
        LambdaQueryWrapper<LessonTargetMember> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(LessonTargetMember::getLessonId, lessonId);
        List<LessonTargetMember> list = list(lambdaQueryWrapper);
        List<Long> targetMemberIdList = new ArrayList<>();
        for (LessonTargetMember lessonTargetMember : list) {
            targetMemberIdList.add(lessonTargetMember.getMemberId());
        }
        return targetMemberIdList;
    }

}
