package com.yjs.cloud.learning.learn.biz.lesson.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yjs.cloud.learning.learn.biz.lesson.mapper.LessonCategoryRelationMapper;
import com.yjs.cloud.learning.learn.biz.lesson.entity.LessonCategoryRelation;
import com.yjs.cloud.learning.learn.common.service.BaseServiceImpl;
import com.yjs.cloud.learning.learn.common.web.GlobalException;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 课程类目 服务实现类
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
@Service
public class LessonCategoryRelationServiceImpl extends BaseServiceImpl<LessonCategoryRelationMapper, LessonCategoryRelation> implements LessonCategoryRelationService {

    /**
     * 创建课程与类目的关系
     * @param lessonId 课程id
     * @param cidList 目录id
     */
    @Override
    public void create(Long lessonId, List<Long> cidList) {
        if (CollectionUtils.isEmpty(cidList)) {
            throw new GlobalException("课程关联类目为空");
        }
        for (Long cid : cidList) {
            LessonCategoryRelation lessonCategoryRelation = new LessonCategoryRelation();
            lessonCategoryRelation.setCategoryId(cid);
            lessonCategoryRelation.setLessonId(lessonId);
            save(lessonCategoryRelation);
        }
    }

    /**
     * 更新课程与类目的关系
     * @param lessonId 课程id
     * @param cidList 目录id
     */
    @Override
    public void update(Long lessonId, List<Long> cidList) {
        delete(lessonId);
        create(lessonId, cidList);
    }

    /**
     * 删除课程与分类的关系
     * @param lessonId 课程id
     */
    @Override
    public void delete(Long lessonId) {
        LambdaQueryWrapper<LessonCategoryRelation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(LessonCategoryRelation::getLessonId, lessonId);
        remove(lambdaQueryWrapper);
    }

    /**
     * 根据课程id列表获取课程与分类关系
     * @param lessonIdList 参数
     * @return 课程与分类关系
     */
    @Override
    public List<LessonCategoryRelation> getByLessonIdList(List<Long> lessonIdList) {
        LambdaQueryWrapper<LessonCategoryRelation> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(LessonCategoryRelation::getLessonId, lessonIdList);
        return list(lambdaQueryWrapper);
    }

    /**
     * 根据课程id列表获取课程与分类关系
     * @param lessonIdList 参数
     * @return 课程与分类关系
     */
    @Override
    public Map<Long, List<Long>> getCidMap(List<Long> lessonIdList) {
        List<LessonCategoryRelation> lessonCategoryRelationList = getByLessonIdList(lessonIdList);
        Map<Long, List<Long>> lessonCategoryRelationMap = new HashMap<>(16);
        if (!CollectionUtils.isEmpty(lessonCategoryRelationList)) {
            for (LessonCategoryRelation lessonCategoryRelation : lessonCategoryRelationList) {
                List<Long> lessonCategoryIdList = lessonCategoryRelationMap.computeIfAbsent(lessonCategoryRelation.getLessonId(), k -> new ArrayList<>());
                lessonCategoryIdList.add(lessonCategoryRelation.getCategoryId());
            }
        }
        return lessonCategoryRelationMap;
    }

}
