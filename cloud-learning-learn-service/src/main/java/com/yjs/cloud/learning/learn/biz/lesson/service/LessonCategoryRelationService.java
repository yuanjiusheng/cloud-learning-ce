package com.yjs.cloud.learning.learn.biz.lesson.service;

import com.yjs.cloud.learning.learn.biz.lesson.entity.LessonCategoryRelation;
import com.yjs.cloud.learning.learn.common.service.IBaseService;

import java.util.List;

/**
 * <p>
 * 课程类目关系 服务类
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
public interface LessonCategoryRelationService extends IBaseService<LessonCategoryRelation> {

    /**
     * 创建课程与类目的关系
     * @param lessonId 课程id
     * @param cidList 目录id
     */
    void create(Long lessonId, List<Long> cidList);

    /**
     * 更新课程与类目的关系
     * @param lessonId 课程id
     * @param cidList 目录id
     */
    void update(Long lessonId, List<Long> cidList);

    /**
     * 删除课程与分类的关系
     * @param lessonId 课程id
     */
    void delete(Long lessonId);

    /**
     * 根据课程id列表获取课程与分类关系
     * @param lessonIdList 参数
     * @return 课程与分类关系
     */
    List<LessonCategoryRelation> getByLessonIdList(List<Long> lessonIdList);
}
