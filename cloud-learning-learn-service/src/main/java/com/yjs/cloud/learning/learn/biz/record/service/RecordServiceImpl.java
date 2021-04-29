package com.yjs.cloud.learning.learn.biz.record.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yjs.cloud.learning.learn.biz.lesson.entity.LessonChapterSection;
import com.yjs.cloud.learning.learn.biz.lesson.service.LessonChapterSectionService;
import com.yjs.cloud.learning.learn.biz.record.bean.RecordCreateRequest;
import com.yjs.cloud.learning.learn.biz.record.bean.RecordGetRequest;
import com.yjs.cloud.learning.learn.biz.record.bean.RecordResponse;
import com.yjs.cloud.learning.learn.biz.record.bean.RecordUpdateRequest;
import com.yjs.cloud.learning.learn.biz.record.entity.Record;
import com.yjs.cloud.learning.learn.biz.record.enums.RecordStatus;
import com.yjs.cloud.learning.learn.biz.record.mapper.RecordMapper;
import com.yjs.cloud.learning.learn.biz.signup.service.SignUpService;
import com.yjs.cloud.learning.learn.common.service.BaseServiceImpl;
import com.yjs.cloud.learning.learn.common.web.GlobalException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 学习记录服务
 *
 * @author Bill.lai
 * @since 12/14/20
 */
@Service
public class RecordServiceImpl extends BaseServiceImpl<RecordMapper, Record> implements RecordService {

    @Resource
    private LessonChapterSectionService lessonChapterSectionService;
    @Lazy
    @Resource
    private SignUpService signUpService;

    /**
     * 保存学习记录
     * @param recordCreateRequest 参数
     * @return 学习记录
     */
    @Override
    public RecordResponse create(RecordCreateRequest recordCreateRequest) {
        if (recordCreateRequest.getMemberId() == null) {
            throw new GlobalException("请先登录");
        }
        if (recordCreateRequest.getLessonId() == null) {
            throw new GlobalException("课程id为必填项");
        }
        if (recordCreateRequest.getLessonChapterSectionId() == null) {
            throw new GlobalException("课程章节id为必填项");
        }
        if (recordCreateRequest.getLearnTime() == null) {
            throw new GlobalException("学习时长为必填项");
        }
        if (recordCreateRequest.getMaxProgressTime() == null) {
            throw new GlobalException("学习进度时间为必填项");
        }
        // 判断是否已经存在
        Record record = recordCreateRequest.convert();
        // 计算学习状态
        record.setStatus(calcStatus(record));
        save(record);
        return record.convert();
    }

    /**
     * 更新学习记录
     * @param recordUpdateRequest 参数
     * @return 学习记录
     */
    @Override
    public RecordResponse update(RecordUpdateRequest recordUpdateRequest) {
        if (recordUpdateRequest.getMemberId() == null) {
            throw new GlobalException("请先登录");
        }
        if (recordUpdateRequest.getId() == null) {
            throw new GlobalException("id为必填项");
        }
        Record record = getById(recordUpdateRequest.getId());
        if (record == null) {
            throw new GlobalException("找不到学习记录");
        }
        if(!record.getMemberId().equals(recordUpdateRequest.getMemberId())) {
            throw new GlobalException("无权限修改该学习记录");
        }
        if (recordUpdateRequest.getLessonChapterSectionId() == null) {
            throw new GlobalException("课程章节id为必填项");
        }
        if (recordUpdateRequest.getLessonId() == null) {
            throw new GlobalException("课程id为必填项");
        }
        if (recordUpdateRequest.getLearnTime() == null) {
            throw new GlobalException("学习时长为必填项");
        }
        record.setLearnTime(record.getLearnTime() + recordUpdateRequest.getLearnTime());
        if (record.getMaxProgressTime() < recordUpdateRequest.getMaxProgressTime()) {
            record.setMaxProgressTime(recordUpdateRequest.getMaxProgressTime());
        }
        // 如果是进行中
        if (record.getStatus().equals(RecordStatus.progressing)) {
            if (recordUpdateRequest.getMaxProgressTime() == null) {
                throw new GlobalException("学习进度时间为必填项");
            }
            record.setStatus(calcStatus(record));
            // 如果该章节已完成，那么判断整个报名记录是否是已完成
            if (record.getStatus().equals(RecordStatus.completed)) {
                signUpService.checkAndUpdateStatus(record.getLessonId(), record.getMemberId(), record.getSignUpId());
            }
        }
        updateById(record);
        return record.convert();
    }

    private RecordStatus calcStatus(Record record) {
        // 判断是否已完成，如已完成那么设置成完成状态
        LessonChapterSection chapterSection = lessonChapterSectionService.getById(record.getLessonChapterSectionId());
        // 如果学习进度时间（5秒误差）等于视频时长时间，并且学习时长（5秒误差）大于等于视频时长时间，那么就是已完成
        boolean progress = record.getMaxProgressTime() + 5 > chapterSection.getTotalTime();
        if (progress && record.getLearnTime() + 5 >= chapterSection.getTotalTime()) {
            return RecordStatus.completed;
        }
        return RecordStatus.progressing;
    }

    /**
     * 获取学习记录
     * @param recordGetRequest 参数
     * @return 学习记录
     */
    @Override
    public RecordResponse get(RecordGetRequest recordGetRequest) {
        LambdaQueryWrapper<Record> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Record::getLessonId, recordGetRequest.getLessonId());
        lambdaQueryWrapper.eq(Record::getMemberId, recordGetRequest.getMemberId());
        lambdaQueryWrapper.eq(Record::getLessonChapterSectionId, recordGetRequest.getLessonChapterSectionId());
        lambdaQueryWrapper.eq(Record::getSignUpId, recordGetRequest.getSignUpId());
        Record record = getOne(lambdaQueryWrapper);
        if (record == null) {
            return null;
        }
        return record.convert();
    }

    /**
     * 根据课程id与报名id获取学习记录
     * @param lessonId 课程id
     * @param signUpId 报名id
     * @return 学习记录
     */
    @Override
    public List<RecordResponse> getByLessonIdAndSignUpId(Long lessonId, Long signUpId) {
        LambdaQueryWrapper<Record> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Record::getLessonId, lessonId);
        lambdaQueryWrapper.eq(Record::getSignUpId, signUpId);
        List<Record> list = list(lambdaQueryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        List<RecordResponse> result = new ArrayList<>();
        for (Record record : list) {
            result.add(record.convert());
        }
        return result;
    }

    @Override
    public Map<Long, RecordStatus> getRecordStatusMapByLessonId(Long lessonId) {
        LambdaQueryWrapper<Record> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Record::getLessonId, lessonId);
        List<Record> list = list(lambdaQueryWrapper);
        Map<Long, RecordStatus> recordStatusMap = new HashMap<>();
        for (Record record : list) {
            recordStatusMap.put(record.getLessonChapterSectionId(), record.getStatus());
        }
        return recordStatusMap;
    }

}
