package com.yjs.cloud.learning.learn.biz.record.service;

import com.yjs.cloud.learning.learn.biz.record.bean.RecordCreateRequest;
import com.yjs.cloud.learning.learn.biz.record.bean.RecordGetRequest;
import com.yjs.cloud.learning.learn.biz.record.bean.RecordResponse;
import com.yjs.cloud.learning.learn.biz.record.bean.RecordUpdateRequest;
import com.yjs.cloud.learning.learn.biz.record.entity.Record;
import com.yjs.cloud.learning.learn.biz.record.enums.RecordStatus;
import com.yjs.cloud.learning.learn.common.service.IBaseService;

import java.util.List;
import java.util.Map;

/**
 * 学习记录服务
 *
 * @author Bill.lai
 * @since 12/14/20
 */
public interface RecordService extends IBaseService<Record> {

    /**
     * 保存学习记录
     * @param recordCreateRequest 参数
     * @return 学习记录
     */
    RecordResponse create(RecordCreateRequest recordCreateRequest);

    /**
     * 更新学习记录
     * @param recordUpdateRequest 参数
     * @return 学习记录
     */
    RecordResponse update(RecordUpdateRequest recordUpdateRequest);

    /**
     * 获取学习记录
     * @param recordGetRequest 参数
     * @return 学习记录
     */
    RecordResponse get(RecordGetRequest recordGetRequest);

    /**
     * 根据课程id与报名id获取学习记录
     * @param lessonId 课程id
     * @param signUpId 报名id
     * @return 学习记录
     */
    List<RecordResponse> getByLessonIdAndSignUpId(Long lessonId, Long signUpId);

    /**
     * 获取每个章节的学习状态
     * @param lessonId 课程id
     * @return 章节状态
     */
    Map<Long, RecordStatus> getRecordStatusMapByLessonId(Long lessonId);
}
