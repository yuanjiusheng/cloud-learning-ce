package com.yjs.cloud.learning.learn.biz.lesson.service;

import com.yjs.cloud.learning.learn.biz.lesson.bean.LessonChapterSectionCreateRequest;
import com.yjs.cloud.learning.learn.biz.lesson.bean.LessonChapterSectionDeleteRequest;
import com.yjs.cloud.learning.learn.biz.lesson.bean.LessonChapterSectionResponse;
import com.yjs.cloud.learning.learn.biz.lesson.bean.LessonChapterSectionUpdateRequest;
import com.yjs.cloud.learning.learn.biz.lesson.entity.LessonChapterSection;
import com.yjs.cloud.learning.learn.common.service.IBaseService;

import java.util.List;

/**
 * <p>
 * 课堂章节 服务类
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
public interface LessonChapterSectionService extends IBaseService<LessonChapterSection> {

    /**
     * 创建课程章节内容
     * @param lessonChapterSectionCreateRequest 参数
     * @return 课程章节内容
     */
    LessonChapterSectionResponse create(LessonChapterSectionCreateRequest lessonChapterSectionCreateRequest);

    /**
     * 修改课程章节内容
     * @param lessonChapterSectionUpdateRequest 参数
     * @return 课程章节内容
     */
    LessonChapterSectionResponse update(LessonChapterSectionUpdateRequest lessonChapterSectionUpdateRequest);

    /**
     * 删除课程章节内容
     * @param lessonChapterSectionDeleteRequest 参数
     */
    void delete(LessonChapterSectionDeleteRequest lessonChapterSectionDeleteRequest);

    /**
     * 根据章节ID获取章节内容
     * @param lessonChapterIdList 参数
     * @return 章节内容
     */
    List<LessonChapterSection> getByChapterIdList(List<Long> lessonChapterIdList);

    /**
     * 删除课程章节内容
     * @param chapterId 章ID
     */
    void deleteByChapterId(Long chapterId);
}
