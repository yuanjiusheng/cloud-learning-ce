package com.yjs.cloud.learning.learn.biz.lesson.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjs.cloud.learning.learn.biz.api.comment.CommentApi;
import com.yjs.cloud.learning.learn.biz.api.comment.enums.CommentTopicType;
import com.yjs.cloud.learning.learn.biz.api.favorite.FavoriteApi;
import com.yjs.cloud.learning.learn.biz.api.favorite.bean.*;
import com.yjs.cloud.learning.learn.biz.api.favorite.enums.FavoriteTopicType;
import com.yjs.cloud.learning.learn.biz.api.like.LikeApi;
import com.yjs.cloud.learning.learn.biz.api.like.enums.LikeTopicType;
import com.yjs.cloud.learning.learn.biz.lesson.bean.*;
import com.yjs.cloud.learning.learn.biz.lesson.entity.Lesson;
import com.yjs.cloud.learning.learn.biz.lesson.enums.LessonStatus;
import com.yjs.cloud.learning.learn.biz.lesson.mapper.LessonMapper;
import com.yjs.cloud.learning.learn.biz.record.bean.RecordResponse;
import com.yjs.cloud.learning.learn.biz.record.bean.SignUpGetPageListRequest;
import com.yjs.cloud.learning.learn.biz.record.bean.SignUpGetPageListResponse;
import com.yjs.cloud.learning.learn.biz.record.service.RecordService;
import com.yjs.cloud.learning.learn.biz.signup.bean.SignUpResponse;
import com.yjs.cloud.learning.learn.biz.signup.service.SignUpService;
import com.yjs.cloud.learning.learn.common.entity.BaseEntity;
import com.yjs.cloud.learning.learn.common.request.PageRequest;
import com.yjs.cloud.learning.learn.common.response.PageResponse;
import com.yjs.cloud.learning.learn.common.service.BaseServiceImpl;
import com.yjs.cloud.learning.learn.common.util.RandomUtils;
import com.yjs.cloud.learning.learn.common.web.GlobalException;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
@AllArgsConstructor
@Service
public class LessonServiceImpl extends BaseServiceImpl<LessonMapper, Lesson> implements LessonService {

    private final LessonCategoryRelationService lessonCategoryRelationService;
    private final LessonMapper lessonMapper;
    private final FavoriteApi favoriteApi;
    private final LikeApi likeApi;
    private final CommentApi commentApi;
    @Lazy
    private final SignUpService signUpService;
    @Lazy
    private final RecordService recordService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public LessonResponse create(LessonCreateRequest lessonCreateRequest) {
        Lesson lesson = lessonCreateRequest.convert();
        lesson.setCode(RandomUtils.number(0L));
        if (lesson.getHomework() == null) {
            lesson.setHomework("");
        }
        save(lesson);
        // 保存分类
        lessonCategoryRelationService.create(lesson.getId(), lessonCreateRequest.getCidList());
        return lesson.convert();
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public LessonResponse update(LessonUpdateRequest lessonUpdateRequest) {
        Lesson lesson = getById(lessonUpdateRequest.getId());
        if (lesson == null) {
            throw new GlobalException("该课程不存在");
        }
        Lesson updateLesson = lessonUpdateRequest.convert();
        if (lesson.getHomework() == null) {
            lesson.setHomework("");
        }
        updateById(updateLesson);
        lessonCategoryRelationService.update(lesson.getId(), lessonUpdateRequest.getCidList());
        return updateLesson.convert();
    }

    @Override
    public LessonListResponse list(LessonListRequest lessonListRequest) {
        Page<LessonResponse> page = new Page<>(lessonListRequest.getCurrent(), lessonListRequest.getSize());
        page = lessonMapper.list(page, lessonListRequest);
        List<LessonResponse> records = page.getRecords();
        if (!CollectionUtils.isEmpty(records)) {
            List<Long> lessonIdList = new ArrayList<>();
            for (LessonResponse lesson : records) {
                lessonIdList.add(lesson.getId());
            }
            // 目录
            Map<Long, List<Long>> lessonCategoryRelationMap = lessonCategoryRelationService.getCidMap(lessonIdList);
            // 点赞数量
            Map<Long, Long> likeMap = likeApi.getLikeMap(lessonIdList, LikeTopicType.lesson);
            // 收藏数量
            Map<Long, Long> favoriteMap = favoriteApi.getFavoriteMap(lessonIdList, FavoriteTopicType.lesson);
            // 评论数量
            Map<Long, Long> commentMap = commentApi.getCommentMap(lessonIdList, CommentTopicType.lesson);
            // 在学人数
            Map<Long, Long> learnMap = signUpService.getLessonLearnNumMap(lessonIdList);
            for (LessonResponse lessonResponse : records) {
                // 点赞数量
                lessonResponse.setLikeNum(likeMap.get(lessonResponse.getId()));
                // 评论数量
                lessonResponse.setCommentNum(commentMap.get(lessonResponse.getId()));
                // 收藏数量
                lessonResponse.setFavoriteNum(favoriteMap.get(lessonResponse.getId()));
                // 学习人数
                lessonResponse.setLearnNum(learnMap.get(lessonResponse.getId()));
                // 目录
                lessonResponse.setCidList(lessonCategoryRelationMap.get(lessonResponse.getId()));
            }
        }
        LessonListResponse lessonListResponse = new LessonListResponse();
        lessonListResponse.setList(records);
        lessonListResponse.setPages(page.getPages());
        lessonListResponse.setSize(page.getSize());
        lessonListResponse.setCurrent(page.getCurrent());
        lessonListResponse.setTotal(page.getTotal());
        return lessonListResponse;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void delete(LessonDeleteRequest lessonDeleteRequest) {
        if (lessonDeleteRequest.getId() == null) {
            throw new GlobalException("ID为必填参数");
        }
        Lesson lesson = getById(lessonDeleteRequest.getId());
        if (lesson == null) {
            throw new GlobalException("找不到相关课程");
        }
        lesson.setStatus(LessonStatus.deleted);
        updateById(lesson);
    }

    @Override
    public void publish(LessonPublishRequest lessonPublishRequest) {
        if (lessonPublishRequest.getId() == null) {
            throw new GlobalException("ID为必填参数");
        }
        Lesson lesson = getById(lessonPublishRequest.getId());
        if (lesson == null) {
            throw new GlobalException("找不到相关课程");
        }
        lesson.setStatus(LessonStatus.published);
        updateById(lesson);
    }

    @Override
    public void unPublish(LessonPublishRequest lessonPublishRequest) {
        if (lessonPublishRequest.getId() == null) {
            throw new GlobalException("ID为必填参数");
        }
        Lesson lesson = getById(lessonPublishRequest.getId());
        if (lesson == null) {
            throw new GlobalException("找不到相关课程");
        }
        lesson.setStatus(LessonStatus.unpublished);
        updateById(lesson);
    }

    @Override
    public LessonResponse get(LessonGetRequest lessonGetRequest) {
        Lesson lesson = getById(lessonGetRequest.getId());
        if (lesson == null) {
            throw new GlobalException("课程不存在");
        }
        LessonResponse response = lesson.convert();
        List<Long> lessonIdList = new ArrayList<>();
        lessonIdList.add(lessonGetRequest.getId());
        Map<Long, List<Long>> lessonCategoryRelationMap = lessonCategoryRelationService.getCidMap(lessonIdList);
        response.setCidList(lessonCategoryRelationMap.get(lessonGetRequest.getId()));
        // 收藏数量
        Map<Long, Long> favoriteMap = favoriteApi.getFavoriteMap(lessonIdList, FavoriteTopicType.lesson);
        // 评论数量
        Map<Long, Long> commentMap = commentApi.getCommentMap(lessonIdList, CommentTopicType.lesson);
        // 点赞数量
        Map<Long, Long> likeMap = likeApi.getLikeMap(lessonIdList, LikeTopicType.lesson);
        // 在学人数
        Map<Long, Long> learnMap = signUpService.getLessonLearnNumMap(lessonIdList);
        // 点赞数量
        response.setLikeNum(likeMap.get(response.getId()));
        // 评论数量
        response.setCommentNum(commentMap.get(response.getId()));
        // 收藏数量
        response.setFavoriteNum(favoriteMap.get(response.getId()));
        // 学习人数
        response.setLearnNum(learnMap.get(response.getId()));
        if (lessonGetRequest.getMemberId() != null) {
            // 获取报名记录
            SignUpResponse signUpResponse = signUpService.getByLessonId(response.getId(), lessonGetRequest.getMemberId());
            response.setSignUp(signUpResponse);
            // 获取学习进度
            if (signUpResponse != null) {
                List<RecordResponse> recordList = recordService.getByLessonIdAndSignUpId(response.getId(), signUpResponse.getId());
                response.setRecordList(recordList);
            }
        }
        return response;
    }

    @Override
    public LessonListResponse favoriteList(LessonFavoriteListRequest lessonFavoriteListRequest) {
        FavoriteGetPageListRequest favoriteGetPageListRequest = new FavoriteGetPageListRequest();
        favoriteGetPageListRequest.setCurrent(lessonFavoriteListRequest.getCurrent());
        favoriteGetPageListRequest.setSize(lessonFavoriteListRequest.getSize());
        favoriteGetPageListRequest.setMemberId(lessonFavoriteListRequest.getMemberId());
        favoriteGetPageListRequest.setTopicType(FavoriteTopicType.lesson);
        FavoriteGetPageListResponse favoritePageList = favoriteApi.getPageList(favoriteGetPageListRequest);
        List<Long> topicIdList = new ArrayList<>();
        for (FavoriteResponse favoriteResponse : favoritePageList.getList()) {
            topicIdList.add(favoriteResponse.getTopicId());
        }
        return getByIdList(topicIdList, lessonFavoriteListRequest, favoritePageList, lessonFavoriteListRequest.getMemberId());
    }

    private LessonListResponse getByIdList(List<Long> topicIdList, PageRequest pageRequest, PageResponse pageResponse, Long memberId) {
        LessonListResponse lessonListResponse = new LessonListResponse();
        lessonListResponse.setCurrent(pageRequest.getCurrent());
        lessonListResponse.setSize(pageRequest.getSize());
        if (topicIdList.size() == 0) {
            lessonListResponse.setList(new ArrayList<>());
            lessonListResponse.setTotal(0L);
            lessonListResponse.setPages(1L);
            return lessonListResponse;
        }
        // 收藏数量
        Map<Long, Long> favoriteMap = favoriteApi.getFavoriteMap(topicIdList, FavoriteTopicType.lesson);
        // 点赞数量
        Map<Long, Long> likeMap = likeApi.getLikeMap(topicIdList, LikeTopicType.lesson);
        // 评论数量
        Map<Long, Long> commentMap = commentApi.getCommentMap(topicIdList, CommentTopicType.lesson);
        // 在学人数
        Map<Long, Long> learnMap = signUpService.getLessonLearnNumMap(topicIdList);
        LambdaQueryWrapper<Lesson> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(BaseEntity::getId, topicIdList);
        List<Lesson> list = list(lambdaQueryWrapper);
        List<LessonResponse> result = new ArrayList<>();
        for (Lesson lesson : list) {
            LessonResponse response = lesson.convert();
            // 点赞数量
            response.setLikeNum(likeMap.get(response.getId()));
            // 评论数量
            response.setCommentNum(commentMap.get(response.getId()));
            // 收藏数量
            response.setFavoriteNum(favoriteMap.get(response.getId()));
            // 学习人数
            response.setLearnNum(learnMap.get(response.getId()));
            if (memberId != null) {
                // 获取报名记录
                SignUpResponse signUpResponse = signUpService.getByLessonId(response.getId(), memberId);
                response.setSignUp(signUpResponse);
            }
            result.add(response);
        }
        lessonListResponse.setPages(pageResponse.getPages());
        lessonListResponse.setTotal(pageResponse.getTotal());
        lessonListResponse.setList(result);
        return lessonListResponse;
    }

    @Override
    public LessonListResponse learnList(LessonLearnListRequest lessonLearnListRequest) {
        SignUpGetPageListRequest signUpGetPageListRequest = new SignUpGetPageListRequest();
        signUpGetPageListRequest.setCurrent(lessonLearnListRequest.getCurrent());
        signUpGetPageListRequest.setSize(lessonLearnListRequest.getSize());
        signUpGetPageListRequest.setMemberId(lessonLearnListRequest.getMemberId());
        SignUpGetPageListResponse signUpPageList = signUpService.getPageList(signUpGetPageListRequest);
        List<Long> topicIdList = new ArrayList<>();
        for (SignUpResponse signUpResponse : signUpPageList.getList()) {
            topicIdList.add(signUpResponse.getLessonId());
        }
        return getByIdList(topicIdList, signUpGetPageListRequest, signUpPageList, lessonLearnListRequest.getMemberId());
    }

}
