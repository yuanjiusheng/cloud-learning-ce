package com.yjs.cloud.learning.learn.biz.lesson.service;

import com.yjs.cloud.learning.learn.biz.lesson.bean.*;
import com.yjs.cloud.learning.learn.biz.lesson.entity.Lesson;
import com.yjs.cloud.learning.learn.common.service.IBaseService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 课堂 服务类
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
public interface LessonService extends IBaseService<Lesson> {

    /**
     * 创建直播频道
     * @param lessonCreateRequest 直播频道参数
     * @return 频道信息
     */
    @Transactional(rollbackFor = Exception.class)
    LessonResponse create(LessonCreateRequest lessonCreateRequest);

    /**
     * 更新直播频道
     * @param lessonUpdateRequest 直播频道参数
     * @return 频道信息
     */
    @Transactional(rollbackFor = Exception.class)
    LessonResponse update(LessonUpdateRequest lessonUpdateRequest);

    /**
     * 获取直播频道列表
     * @param lessonListRequest 搜索列表参数
     * @return 频道列表
     */
    LessonListResponse list(LessonListRequest lessonListRequest);

    /**
     * 删除频道列表
     * @param lessonDeleteRequest 请求参数
     */
    @Transactional(rollbackFor = Exception.class)
    void delete(LessonDeleteRequest lessonDeleteRequest);

    /**
     * 发布课程
     * @param lessonPublishRequest 参数
     */
    void publish(LessonPublishRequest lessonPublishRequest);

    /**
     * 取消发布课程
     * @param lessonPublishRequest 参数
     */
    void unPublish(LessonPublishRequest lessonPublishRequest);

    /**
     * 获取课程信息
     * @param lessonGetRequest 请求参数
     * @return 课程信息
     */
    LessonResponse get(LessonGetRequest lessonGetRequest);

    /**
     * 获取收藏的课程列表
     * @param lessonFavoriteListRequest 参数
     * @return 收藏的课程列表
     */
    LessonListResponse favoriteList(LessonFavoriteListRequest lessonFavoriteListRequest);

    /**
     * 获取会员的学习课程列表
     * @param lessonLearnListRequest 参数
     * @return 收藏的课程列表
     */
    LessonListResponse learnList(LessonLearnListRequest lessonLearnListRequest);

}
