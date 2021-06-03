package com.yjs.cloud.learning.learn.biz.lesson.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjs.cloud.learning.learn.biz.lesson.bean.LessonResponse;
import com.yjs.cloud.learning.learn.biz.lesson.enums.LessonStatus;
import com.yjs.cloud.learning.learn.biz.lesson.enums.LessonTargetType;
import com.yjs.cloud.learning.learn.common.entity.BaseEntity;
import com.yjs.cloud.learning.learn.common.util.BeanCopierUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 课程
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_lesson")
public class Lesson extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 课程名称
     */
    private String name;

    /**
     * 编号
     */
    private String code;

    /**
     * 开始时间
     */
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    private LocalDateTime endTime;

    /**
     * 封面图片
     */
    private String image;

    /**
     * 状态
     */
    private LessonStatus status;

    /**
     * 简介
     */
    private String phrase;

    /**
     * 描述
     */
    private String introduction;

    /**
     * 作业内容
     */
    private String homework;

    /**
     * 作业附件
     */
    private String homeworkAttachment;

    /**
     * 目标类型
     */
    private LessonTargetType targetType;

    public LessonResponse convert() {
        LessonResponse response = new LessonResponse();
        BeanCopierUtils.copy(this, response);
        return response;
    }
}
