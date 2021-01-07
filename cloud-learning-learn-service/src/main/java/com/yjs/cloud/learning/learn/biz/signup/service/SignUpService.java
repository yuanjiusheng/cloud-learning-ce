package com.yjs.cloud.learning.learn.biz.signup.service;

import com.yjs.cloud.learning.learn.biz.record.bean.SignUpGetPageListRequest;
import com.yjs.cloud.learning.learn.biz.record.bean.SignUpGetPageListResponse;
import com.yjs.cloud.learning.learn.biz.signup.bean.*;
import com.yjs.cloud.learning.learn.biz.signup.entity.SignUp;
import com.yjs.cloud.learning.learn.common.service.IBaseService;

import java.util.List;

/**
 * 报名服务
 *
 * @author Bill.lai
 * @since 12/14/20
 */
public interface SignUpService extends IBaseService<SignUp> {

    /**
     * 报名
     * @param signUpCreateRequest 参数
     * @return 报名记录
     */
    SignUpResponse create(SignUpCreateRequest signUpCreateRequest);

    /**
     * 取消报名
     * @param signUpDeleteRequest 参数
     */
    void cancel(SignUpDeleteRequest signUpDeleteRequest);

    /**
     * 获取课程学习人数统计列表
     * @param signUpCountListRequest 参数
     * @return 课程学习人数统计列表
     */
    List<SignUpCountResponse> getCountList(SignUpCountListRequest signUpCountListRequest);

    /**
     * 获取学习记录列表
     * @param recordGetPageListRequest 参数
     * @return 学习记录列表
     */
    SignUpGetPageListResponse getPageList(SignUpGetPageListRequest recordGetPageListRequest);

    /**
     * 根据课程id获取最新报名记录
     * @param lessonId 课程id
     * @return 报名记录
     */
    SignUpResponse getByLessonId(Long lessonId);
}
