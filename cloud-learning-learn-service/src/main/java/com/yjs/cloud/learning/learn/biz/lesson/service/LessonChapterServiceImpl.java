package com.yjs.cloud.learning.learn.biz.lesson.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yjs.cloud.learning.learn.biz.lesson.bean.*;
import com.yjs.cloud.learning.learn.biz.lesson.entity.LessonChapter;
import com.yjs.cloud.learning.learn.biz.lesson.entity.LessonChapterSection;
import com.yjs.cloud.learning.learn.biz.lesson.mapper.LessonChapterMapper;
import com.yjs.cloud.learning.learn.common.service.BaseServiceImpl;
import com.yjs.cloud.learning.learn.common.util.StringUtils;
import com.yjs.cloud.learning.learn.common.web.GlobalException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课堂章 服务类
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
@AllArgsConstructor
@Service
public class LessonChapterServiceImpl extends BaseServiceImpl<LessonChapterMapper, LessonChapter> implements LessonChapterService {

    private final LessonChapterSectionService lessonChapterSectionService;

    /**
     * 创建课程章节
     * @param lessonChapterCreateRequest 参数
     * @return 课程章节
     */
    @Override
    public LessonChapterResponse create(LessonChapterCreateRequest lessonChapterCreateRequest) {
        if (StringUtils.isEmpty(lessonChapterCreateRequest.getTitle())) {
            throw new GlobalException("标题为必填参数");
        }
        if (StringUtils.isEmpty(lessonChapterCreateRequest.getPhrase())) {
            throw new GlobalException("简介为必填参数");
        }
        if (lessonChapterCreateRequest.getLessonId() == null) {
            throw new GlobalException("课程章节id为必填参数");
        }
        LessonChapter lessonChapter = lessonChapterCreateRequest.convert();
        save(lessonChapter);
        return lessonChapter.convert();
    }

    /**
     * 修改课程章节
     * @param lessonChapterUpdateRequest 参数
     * @return 课程章节
     */
    @Override
    public LessonChapterResponse update(LessonChapterUpdateRequest lessonChapterUpdateRequest) {
        if (StringUtils.isEmpty(lessonChapterUpdateRequest.getTitle())) {
            throw new GlobalException("标题为必填参数");
        }
        if (lessonChapterUpdateRequest.getId() == null) {
            throw new GlobalException("id为必填参数");
        }
        if (StringUtils.isEmpty(lessonChapterUpdateRequest.getPhrase())) {
            throw new GlobalException("简介为必填参数");
        }
        if (lessonChapterUpdateRequest.getLessonId() == null) {
            throw new GlobalException("课程章节id为必填参数");
        }
        LessonChapter lessonChapter = getById(lessonChapterUpdateRequest.getId());
        if (lessonChapter == null) {
            throw new GlobalException("课程章节不存在");
        }
        lessonChapter.setPhrase(lessonChapterUpdateRequest.getPhrase());
        lessonChapter.setTitle(lessonChapterUpdateRequest.getTitle());
        updateById(lessonChapter);
        return lessonChapter.convert();
    }

    /**
     * 删除课程章节
     * @param lessonChapterDeleteRequest 参数
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(LessonChapterDeleteRequest lessonChapterDeleteRequest) {
        if (lessonChapterDeleteRequest.getId() == null) {
            throw new GlobalException("id为必填参数");
        }
        LessonChapter lessonChapter = getById(lessonChapterDeleteRequest.getId());
        if (lessonChapter == null) {
            throw new GlobalException("课程章节不存在");
        }
        lessonChapterSectionService.deleteByChapterId(lessonChapter.getId());
        removeById(lessonChapter.getId());
    }

    /**
     * 获取课程章节列表
     * @param lessonChapterGetListRequest 参数
     * @return 课程章节列表
     */
    @Override
    public LessonChapterGetListResponse getList(LessonChapterGetListRequest lessonChapterGetListRequest) {
        LambdaQueryWrapper<LessonChapter> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(LessonChapter::getLessonId, lessonChapterGetListRequest.getLessonId());
        List<LessonChapter> list = list(lambdaQueryWrapper);
        LessonChapterGetListResponse lessonChapterGetListResponse = new LessonChapterGetListResponse();
        if (!CollectionUtils.isEmpty(list)) {
            List<Long> lessonChapterIdList = new ArrayList<>();
            for (LessonChapter lessonChapter : list) {
                lessonChapterIdList.add(lessonChapter.getId());
            }
            // 获取章节内容
            List<LessonChapterSection> lessonChapterSectionResponseList = lessonChapterSectionService.getByChapterIdList(lessonChapterIdList);
            Map<Long, List<LessonChapterSectionResponse>> lessonChapterSectionMap = new HashMap<>(16);
            if (!CollectionUtils.isEmpty(lessonChapterSectionResponseList)) {
                for (LessonChapterSection lessonChapterSection : lessonChapterSectionResponseList) {
                    List<LessonChapterSectionResponse> chapterSectionResponseList = lessonChapterSectionMap.computeIfAbsent(lessonChapterSection.getLessonChapterId(), k -> new ArrayList<>());
                    chapterSectionResponseList.add(lessonChapterSection.convert());
                }
            }
            List<LessonChapterResponse> result = new ArrayList<>();
            for (LessonChapter lessonChapter : list) {
                LessonChapterResponse chapterResponse = lessonChapter.convert();
                chapterResponse.setChapterSectionList(lessonChapterSectionMap.get(chapterResponse.getId()));
                result.add(chapterResponse);
            }
            lessonChapterGetListResponse.setList(result);
        }
        return lessonChapterGetListResponse;
    }

}
