package com.yjs.cloud.learning.learn.biz.homework.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjs.cloud.learning.learn.biz.api.member.MemberApi;
import com.yjs.cloud.learning.learn.biz.api.member.bean.MemberResponse;
import com.yjs.cloud.learning.learn.biz.homework.bean.*;
import com.yjs.cloud.learning.learn.biz.homework.entity.HomeworkRecord;
import com.yjs.cloud.learning.learn.biz.homework.enums.HomeworkRecordStatus;
import com.yjs.cloud.learning.learn.biz.homework.mapper.HomeworkRecordMapper;
import com.yjs.cloud.learning.learn.biz.signup.service.SignUpService;
import com.yjs.cloud.learning.learn.common.service.BaseServiceImpl;
import com.yjs.cloud.learning.learn.common.web.GlobalException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * service impl
 *
 * @author Bill.lai
 * @since 2021/4/28
 */
@AllArgsConstructor
@Service
public class HomeworkRecordServiceImpl extends BaseServiceImpl<HomeworkRecordMapper, HomeworkRecord> implements HomeworkRecordService {

    private final MemberApi memberApi;
    private final SignUpService signUpService;

    @Override
    public HomeworkRecordResponse create(HomeworkRecordCreateRequest homeworkRecordCreateRequest) {
        if (homeworkRecordCreateRequest.getLessonId() == null) {
            throw new GlobalException("课程id为必填项");
        }
        if (homeworkRecordCreateRequest.getUrl() == null) {
            throw new GlobalException("url为必填项");
        }
        LambdaQueryWrapper<HomeworkRecord> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(HomeworkRecord::getMemberId, homeworkRecordCreateRequest.getMemberId());
        lambdaQueryWrapper.eq(HomeworkRecord::getLessonId, homeworkRecordCreateRequest.getLessonId());
        lambdaQueryWrapper.eq(HomeworkRecord::getSignUpId, homeworkRecordCreateRequest.getSignUpId());
        HomeworkRecord homeworkRecord = getOne(lambdaQueryWrapper);
        if (homeworkRecord != null) {
            throw new GlobalException("你已提交过作业");
        }
        homeworkRecord = homeworkRecordCreateRequest.convert();
        save(homeworkRecord);
        return homeworkRecord.convert();
    }

    @Override
    public HomeworkRecordResponse update(HomeworkRecordUpdateRequest homeworkRecordUpdateRequest) {
        LambdaQueryWrapper<HomeworkRecord> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(HomeworkRecord::getMemberId, homeworkRecordUpdateRequest.getMemberId());
        lambdaQueryWrapper.eq(HomeworkRecord::getSignUpId, homeworkRecordUpdateRequest.getSignUpId());
        lambdaQueryWrapper.eq(HomeworkRecord::getLessonId, homeworkRecordUpdateRequest.getLessonId());
        HomeworkRecord homeworkRecord = getOne(lambdaQueryWrapper);
        if (homeworkRecord == null) {
            throw new GlobalException("没有相关的提交记录");
        }
        homeworkRecord.setStatus(homeworkRecordUpdateRequest.getStatus());
        homeworkRecord.setUrl(homeworkRecordUpdateRequest.getUrl());
        updateById(homeworkRecord);
        return homeworkRecord.convert();
    }

    @Override
    public HomeworkRecordResponse get(HomeworkRecordGetRequest homeworkRecordGetRequest) {
        LambdaQueryWrapper<HomeworkRecord> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(HomeworkRecord::getMemberId, homeworkRecordGetRequest.getMemberId());
        lambdaQueryWrapper.eq(HomeworkRecord::getLessonId, homeworkRecordGetRequest.getLessonId());
        lambdaQueryWrapper.eq(HomeworkRecord::getSignUpId, homeworkRecordGetRequest.getSignUpId());
        HomeworkRecord homeworkRecord = getOne(lambdaQueryWrapper);
        if (homeworkRecord != null) {
            return homeworkRecord.convert();
        }
        return null;
    }

    @Override
    public HomeworkRecordListResponse getList(HomeworkRecordListRequest homeworkRecordListRequest) {
        if (homeworkRecordListRequest.getLessonId() == null) {
            throw new GlobalException("课程id为必填项");
        }
        Page<HomeworkRecord> page = new Page<>(homeworkRecordListRequest.getCurrent(), homeworkRecordListRequest.getSize());
        LambdaQueryWrapper<HomeworkRecord> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(HomeworkRecord::getLessonId, homeworkRecordListRequest.getLessonId());
        page = baseMapper.selectPage(page, lambdaQueryWrapper);
        List<HomeworkRecordResponse> responseList = new ArrayList<>();
        List<Long> memberIdList = new ArrayList<>();
        for (HomeworkRecord homeworkRecord : page.getRecords()) {
            memberIdList.add(homeworkRecord.getMemberId());
        }
        Map<Long, MemberResponse> memberMap = memberApi.getMemberMap(memberIdList);
        for (HomeworkRecord homeworkRecord : page.getRecords()) {
            HomeworkRecordResponse response = homeworkRecord.convert();
            response.setMember(memberMap.get(homeworkRecord.getMemberId()));
            responseList.add(response);
        }
        HomeworkRecordListResponse response = new HomeworkRecordListResponse();
        response.setCurrent(page.getCurrent());
        response.setSize(page.getSize());
        response.setPages(page.getPages());
        response.setTotal(page.getTotal());
        response.setList(responseList);
        return response;
    }

    @Override
    public HomeworkRecordResponse approval(HomeworkRecordApprovalRequest homeworkRecordApprovalRequest) {
        LambdaQueryWrapper<HomeworkRecord> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(HomeworkRecord::getLessonId, homeworkRecordApprovalRequest.getLessonId());
        lambdaQueryWrapper.eq(HomeworkRecord::getMemberId, homeworkRecordApprovalRequest.getMemberId());
        lambdaQueryWrapper.eq(HomeworkRecord::getSignUpId, homeworkRecordApprovalRequest.getSignUpId());
        HomeworkRecord homeworkRecord = getOne(lambdaQueryWrapper);
        if (homeworkRecord == null) {
            throw new GlobalException("没有相关的提交记录");
        }
        homeworkRecord.setStatus(homeworkRecordApprovalRequest.getStatus());
        updateById(homeworkRecord);
        if (homeworkRecord.getStatus().equals(HomeworkRecordStatus.pass_approval)) {
            signUpService.checkAndUpdateStatus(homeworkRecord.getLessonId(), homeworkRecord.getMemberId(), homeworkRecord.getSignUpId());
        }
        return homeworkRecord.convert();
    }
}
