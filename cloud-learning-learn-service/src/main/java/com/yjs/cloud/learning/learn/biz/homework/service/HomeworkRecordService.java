package com.yjs.cloud.learning.learn.biz.homework.service;

import com.yjs.cloud.learning.learn.biz.homework.bean.*;
import com.yjs.cloud.learning.learn.biz.homework.entity.HomeworkRecord;
import com.yjs.cloud.learning.learn.common.service.IBaseService;

/**
 * service
 *
 * @author Bill.lai
 * @since 2021/4/28
 */
public interface HomeworkRecordService extends IBaseService<HomeworkRecord> {

    /**
     * 提交作业
     * @param homeworkRecordCreateRequest 请求参数
     * @return 作业记录
     */
    HomeworkRecordResponse create(HomeworkRecordCreateRequest homeworkRecordCreateRequest);

    /**
     * 更新作业
     * @param homeworkRecordUpdateRequest 请求参数
     * @return 作业记录
     */
    HomeworkRecordResponse update(HomeworkRecordUpdateRequest homeworkRecordUpdateRequest);

    /**
     * 获取作业记录
     * @param homeworkRecordGetRequest 请求参数
     * @return 作业记录
     */
    HomeworkRecordResponse get(HomeworkRecordGetRequest homeworkRecordGetRequest);

    /**
     * 获取作业提交记录列表
     * @param homeworkRecordListRequest 请求参数
     * @return 作业提交记录列表
     */
    HomeworkRecordListResponse getList(HomeworkRecordListRequest homeworkRecordListRequest);

    /**
     * 审批作业
     * @param homeworkRecordApprovalRequest 请求作业
     * @return 作业记录
     */
    HomeworkRecordResponse approval(HomeworkRecordApprovalRequest homeworkRecordApprovalRequest);
}
