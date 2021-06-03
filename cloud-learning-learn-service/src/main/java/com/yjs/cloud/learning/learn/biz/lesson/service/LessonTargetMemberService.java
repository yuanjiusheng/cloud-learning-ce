package com.yjs.cloud.learning.learn.biz.lesson.service;

import com.yjs.cloud.learning.learn.biz.lesson.entity.LessonTargetMember;
import com.yjs.cloud.learning.learn.common.service.IBaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * service
 *
 * @author Bill.lai
 * @since 2021/6/1
 */
public interface LessonTargetMemberService extends IBaseService<LessonTargetMember> {

    /**
     * 保存或更新目标会员
     * @param lessonId 课程id
     * @param targetMemberIdList 目标会员id列表
     */
    @Transactional(rollbackFor = Exception.class)
    void createOrUpdate(Long lessonId, List<Long> targetMemberIdList);

    /**
     * 获取目标会员睇
     * @param lessonId 课程id
     * @return 目标会员id列表
     */
    List<Long> getTargetMemberIdList(Long lessonId);
}
