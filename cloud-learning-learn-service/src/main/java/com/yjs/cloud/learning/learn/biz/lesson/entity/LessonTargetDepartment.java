package com.yjs.cloud.learning.learn.biz.lesson.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjs.cloud.learning.learn.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Bill.lai
 * @since 2021/6/1
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_lesson_target_department")
public class LessonTargetDepartment extends BaseEntity {

    /**
     * 部门id
     */
    private Long departmentId;

    /**
     * 课程id
     */
    private Long lessonId;

}
