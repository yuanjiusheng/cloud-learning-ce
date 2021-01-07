package com.yjs.cloud.learning.learn.biz.lesson.service;

import com.yjs.cloud.learning.learn.biz.lesson.bean.*;
import com.yjs.cloud.learning.learn.biz.lesson.entity.LessonChapter;
import com.yjs.cloud.learning.learn.common.service.IBaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课堂章 服务类
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
public interface LessonChapterService extends IBaseService<LessonChapter> {

    /**
     * 创建课程章节
     * @param lessonChapterCreateRequest 参数
     * @return 课程章节
     */
    LessonChapterResponse create(LessonChapterCreateRequest lessonChapterCreateRequest);

    /**
     * 修改课程章节
     * @param lessonChapterUpdateRequest 参数
     * @return 课程章节
     */
    LessonChapterResponse update(LessonChapterUpdateRequest lessonChapterUpdateRequest);

    /**
     * 删除课程章节
     * @param lessonChapterDeleteRequest 参数
     */
    @Transactional(rollbackFor = Exception.class)
    void delete(LessonChapterDeleteRequest lessonChapterDeleteRequest);

    /**
     * 获取课程章节列表
     * @param lessonChapterGetListRequest 参数
     * @return 课程章节列表
     */
    LessonChapterGetListResponse getList(LessonChapterGetListRequest lessonChapterGetListRequest);
}
