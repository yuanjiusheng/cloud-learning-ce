package com.yjs.cloud.learning.learn.biz.lesson.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjs.cloud.learning.learn.biz.lesson.bean.LessonChapterResponse;
import com.yjs.cloud.learning.learn.common.entity.BaseEntity;
import com.yjs.cloud.learning.learn.common.util.BeanCopierUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 课程章
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_lesson_chapter")
public class LessonChapter extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 课程ID
     */
    private Long lessonId;

    /**
     * 标题
     */
    private String title;

    /**
     * 简介
     */
    private String phrase;

    public LessonChapterResponse convert() {
        LessonChapterResponse response = new LessonChapterResponse();
        BeanCopierUtils.copy(this, response);
        return response;
    }
}
