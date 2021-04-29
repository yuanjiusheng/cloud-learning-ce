package com.yjs.cloud.learning.learn.biz.lesson.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yjs.cloud.learning.learn.biz.lesson.bean.LessonChapterSectionCreateRequest;
import com.yjs.cloud.learning.learn.biz.lesson.bean.LessonChapterSectionDeleteRequest;
import com.yjs.cloud.learning.learn.biz.lesson.bean.LessonChapterSectionResponse;
import com.yjs.cloud.learning.learn.biz.lesson.bean.LessonChapterSectionUpdateRequest;
import com.yjs.cloud.learning.learn.biz.lesson.entity.LessonChapterSection;
import com.yjs.cloud.learning.learn.biz.lesson.mapper.LessonChapterSectionMapper;
import com.yjs.cloud.learning.learn.common.service.BaseServiceImpl;
import com.yjs.cloud.learning.learn.common.util.StringUtils;
import com.yjs.cloud.learning.learn.common.web.GlobalException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 课堂章节 服务类
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
@Service
public class LessonChapterSectionServiceImpl extends BaseServiceImpl<LessonChapterSectionMapper, LessonChapterSection> implements LessonChapterSectionService {

    /**
     * 创建课程章节内容
     * @param lessonChapterSectionCreateRequest 参数
     * @return 课程章节内容
     */
    @Override
    public LessonChapterSectionResponse create(LessonChapterSectionCreateRequest lessonChapterSectionCreateRequest){
        if (StringUtils.isEmpty(lessonChapterSectionCreateRequest.getTitle())) {
            throw new GlobalException("标题为必填参数");
        }
        if (StringUtils.isEmpty(lessonChapterSectionCreateRequest.getPhrase())) {
            throw new GlobalException("简介为必填参数");
        }
        if (StringUtils.isEmpty(lessonChapterSectionCreateRequest.getUrl())) {
            throw new GlobalException("地址为必填参数");
        }
        if (lessonChapterSectionCreateRequest.getLessonChapterId() == null) {
            throw new GlobalException("课程章节id为必填参数");
        }
        LessonChapterSection lessonChapterSection = lessonChapterSectionCreateRequest.convert();
        save(lessonChapterSection);
        return lessonChapterSection.convert();
    }

    /**
     * 修改课程章节内容
     * @param lessonChapterSectionUpdateRequest 参数
     * @return 课程章节内容
     */
    @Override
    public LessonChapterSectionResponse update(LessonChapterSectionUpdateRequest lessonChapterSectionUpdateRequest){
        if (lessonChapterSectionUpdateRequest.getId() == null) {
            throw new GlobalException("id为必填参数");
        }
        LessonChapterSection lessonChapterSection = getById(lessonChapterSectionUpdateRequest.getId());
        if (lessonChapterSection == null) {
            throw new GlobalException("找不到该章节内容");
        }
        lessonChapterSection.setPhrase(lessonChapterSectionUpdateRequest.getPhrase());
        lessonChapterSection.setTitle(lessonChapterSectionUpdateRequest.getTitle());
        lessonChapterSection.setUrl(lessonChapterSectionUpdateRequest.getUrl());
        lessonChapterSection.setTotalTime(lessonChapterSectionUpdateRequest.getTotalTime());
        updateById(lessonChapterSection);
        return lessonChapterSection.convert();
    }

    /**
     * 删除课程章节内容
     * @param lessonChapterSectionDeleteRequest 参数
     */
    @Override
    public void delete(LessonChapterSectionDeleteRequest lessonChapterSectionDeleteRequest) {
        if (lessonChapterSectionDeleteRequest.getId() == null) {
            throw new GlobalException("删除参数不能为空");
        }
        LessonChapterSection lessonChapterSection = getById(lessonChapterSectionDeleteRequest.getId());
        if (lessonChapterSection == null) {
            throw new GlobalException("找不到该章节内容");
        }
        removeById(lessonChapterSectionDeleteRequest.getId());
    }

    /**
     * 根据章节ID获取章节内容
     * @param lessonChapterIdList 参数
     * @return 章节内容
     */
    @Override
    public List<LessonChapterSection> getByChapterIdList(List<Long> lessonChapterIdList) {
        LambdaQueryWrapper<LessonChapterSection> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(LessonChapterSection::getLessonChapterId, lessonChapterIdList);
        return list(lambdaQueryWrapper);
    }

    /**
     * 删除课程章节内容
     * @param chapterId 章ID
     */
    @Override
    public void deleteByChapterId(Long chapterId) {
        LambdaQueryWrapper<LessonChapterSection> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(LessonChapterSection::getLessonChapterId, chapterId);
        removeById(chapterId);
    }

}
