package com.yjs.cloud.learning.learn.biz.lesson.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjs.cloud.learning.learn.biz.lesson.bean.LessonChapterSectionResponse;
import com.yjs.cloud.learning.learn.common.entity.BaseEntity;
import com.yjs.cloud.learning.learn.common.util.BeanCopierUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 课程章节
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_lesson_chapter_section")
public class LessonChapterSection extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 课程ID
     */
    private Long lessonChapterId;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容路径
     */
    private String url;

    /**
     * 简介
     */
    private String phrase;

    /**
     * 内容总时间
     */
    private Long totalTime;

    public LessonChapterSectionResponse convert() {
        LessonChapterSectionResponse response = new LessonChapterSectionResponse();
        BeanCopierUtils.copy(this, response);
        return response;
    }
}
