package com.yjs.cloud.learning.learn.biz.record.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yjs.cloud.learning.learn.biz.record.bean.RecordCreateRequest;
import com.yjs.cloud.learning.learn.biz.record.bean.RecordGetRequest;
import com.yjs.cloud.learning.learn.biz.record.bean.RecordResponse;
import com.yjs.cloud.learning.learn.biz.record.bean.RecordUpdateRequest;
import com.yjs.cloud.learning.learn.biz.record.entity.Record;
import com.yjs.cloud.learning.learn.biz.record.mapper.RecordMapper;
import com.yjs.cloud.learning.learn.common.service.BaseServiceImpl;
import com.yjs.cloud.learning.learn.common.web.GlobalException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 学习记录服务
 *
 * @author Bill.lai
 * @since 12/14/20
 */
@AllArgsConstructor
@Service
public class RecordServiceImpl extends BaseServiceImpl<RecordMapper, Record> implements RecordService {

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
        // 判断是否已经存在
        Record record = recordCreateRequest.convert();
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
        updateById(record);
        return record.convert();
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

}
