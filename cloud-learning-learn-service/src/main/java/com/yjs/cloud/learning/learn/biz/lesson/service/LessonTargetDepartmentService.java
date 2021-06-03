package com.yjs.cloud.learning.learn.biz.lesson.service;

import com.yjs.cloud.learning.learn.biz.lesson.entity.LessonTargetDepartment;
import com.yjs.cloud.learning.learn.common.service.IBaseService;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * service
 *
 * @author Bill.lai
 * @since 2021/6/1
 */
public interface LessonTargetDepartmentService extends IBaseService<LessonTargetDepartment> {

    /**
     * 保存或更新目标会员
     * @param id 课程id
     * @param targetDepartmentIdList 目标部门id列表
     */
    @Transactional(rollbackFor = Exception.class)
    void createOrUpdate(Long id, List<Long> targetDepartmentIdList);

    /**
     * 获取目标部门id
     * @param lessonId 课程id
     * @return 目标部门id列表
     */
    List<Long> getTargetDepartmentIdList(Long lessonId);

}
