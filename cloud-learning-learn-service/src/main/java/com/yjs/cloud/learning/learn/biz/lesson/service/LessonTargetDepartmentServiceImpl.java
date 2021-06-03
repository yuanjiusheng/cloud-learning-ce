package com.yjs.cloud.learning.learn.biz.lesson.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yjs.cloud.learning.learn.biz.lesson.entity.LessonTargetDepartment;
import com.yjs.cloud.learning.learn.biz.lesson.mapper.LessonTargetDepartmentMapper;
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
public class LessonTargetDepartmentServiceImpl extends BaseServiceImpl<LessonTargetDepartmentMapper, LessonTargetDepartment> implements LessonTargetDepartmentService {

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void createOrUpdate(Long id, List<Long> targetDepartmentIdList) {
        LambdaQueryWrapper<LessonTargetDepartment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(LessonTargetDepartment::getLessonId, id);
        remove(lambdaQueryWrapper);
        if (!CollectionUtils.isEmpty(targetDepartmentIdList)) {
            for (Long departmentId : targetDepartmentIdList) {
                LessonTargetDepartment lessonTargetDepartment = new LessonTargetDepartment();
                lessonTargetDepartment.setDepartmentId(departmentId);
                lessonTargetDepartment.setLessonId(id);
                save(lessonTargetDepartment);
            }
        }
    }

    @Override
    public List<Long> getTargetDepartmentIdList(Long lessonId) {
        LambdaQueryWrapper<LessonTargetDepartment> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(LessonTargetDepartment::getLessonId, lessonId);
        List<LessonTargetDepartment> list = list(lambdaQueryWrapper);
        List<Long> targetDepartmentIdList = new ArrayList<>();
        for (LessonTargetDepartment lessonTargetDepartment : list) {
            targetDepartmentIdList.add(lessonTargetDepartment.getDepartmentId());
        }
        return targetDepartmentIdList;
    }

}
