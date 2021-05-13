package com.yjs.cloud.learning.learn.biz.signup.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjs.cloud.learning.learn.biz.api.member.MemberApi;
import com.yjs.cloud.learning.learn.biz.api.member.bean.MemberResponse;
import com.yjs.cloud.learning.learn.biz.homework.bean.HomeworkRecordGetRequest;
import com.yjs.cloud.learning.learn.biz.homework.bean.HomeworkRecordResponse;
import com.yjs.cloud.learning.learn.biz.homework.enums.HomeworkRecordStatus;
import com.yjs.cloud.learning.learn.biz.homework.service.HomeworkRecordService;
import com.yjs.cloud.learning.learn.biz.lesson.bean.LessonChapterGetListRequest;
import com.yjs.cloud.learning.learn.biz.lesson.bean.LessonChapterGetListResponse;
import com.yjs.cloud.learning.learn.biz.lesson.bean.LessonChapterResponse;
import com.yjs.cloud.learning.learn.biz.lesson.bean.LessonChapterSectionResponse;
import com.yjs.cloud.learning.learn.biz.lesson.entity.Lesson;
import com.yjs.cloud.learning.learn.biz.lesson.service.LessonChapterService;
import com.yjs.cloud.learning.learn.biz.lesson.service.LessonService;
import com.yjs.cloud.learning.learn.biz.record.bean.SignUpGetPageListRequest;
import com.yjs.cloud.learning.learn.biz.record.bean.SignUpGetPageListResponse;
import com.yjs.cloud.learning.learn.biz.record.enums.RecordStatus;
import com.yjs.cloud.learning.learn.biz.record.service.RecordService;
import com.yjs.cloud.learning.learn.biz.signup.bean.*;
import com.yjs.cloud.learning.learn.biz.signup.entity.SignUp;
import com.yjs.cloud.learning.learn.biz.signup.enums.SignUpStatus;
import com.yjs.cloud.learning.learn.biz.signup.mapper.SignUpMapper;
import com.yjs.cloud.learning.learn.common.entity.BaseEntity;
import com.yjs.cloud.learning.learn.common.service.BaseServiceImpl;
import com.yjs.cloud.learning.learn.common.util.StringUtils;
import com.yjs.cloud.learning.learn.common.web.GlobalException;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 报名服务实现
 *
 * @author Bill.lai
 * @since 12/14/20
 */
@Service
public class SignUpServiceImpl extends BaseServiceImpl<SignUpMapper, SignUp> implements SignUpService {

    @Resource
    private SignUpMapper signUpMapper;
    @Lazy
    @Resource
    private RecordService recordService;
    @Resource
    private LessonChapterService lessonChapterService;
    @Lazy
    @Resource
    private HomeworkRecordService homeworkRecordService;
    @Lazy
    @Resource
    private LessonService lessonService;
    @Resource
    private MemberApi memberApi;

    /**
     * 报名
     * @param signUpCreateRequest 参数
     * @return 报名记录
     */
    @Override
    public SignUpResponse create(SignUpCreateRequest signUpCreateRequest) {
        if (signUpCreateRequest.getMemberId() == null) {
            throw new GlobalException("请先登录");
        }
        if (signUpCreateRequest.getLessonId() == null) {
            throw new GlobalException("lessonId为必填项");
        }
        SignUp signUp = new SignUp();
        signUp.setLessonId(signUpCreateRequest.getLessonId());
        signUp.setMemberId(signUpCreateRequest.getMemberId());
        signUp.setStatus(SignUpStatus.signed_up);
        save(signUp);
        return signUp.convert();
    }

    /**
     * 取消报名
     * @param signUpDeleteRequest 参数
     */
    @Override
    public void cancel(SignUpDeleteRequest signUpDeleteRequest) {
        if (signUpDeleteRequest.getId() == null) {
            throw new GlobalException("id为必填项");
        }
        if (signUpDeleteRequest.getMemberId() == null) {
            throw new GlobalException("请先登录");
        }
        SignUp signUp = getById(signUpDeleteRequest.getId());
        if (signUp == null) {
            throw new GlobalException("找不到该报名记录");
        }
        if (!signUpDeleteRequest.getMemberId().equals(signUp.getMemberId())) {
            throw new GlobalException("没有权限取消报名");
        }
    }

    @Override
    public List<SignUpCountResponse> getCountList(SignUpCountListRequest signUpCountListRequest) {
        return signUpMapper.countList(signUpCountListRequest);
    }

    @Override
    public Map<Long, Long> getLessonLearnNumMap(List<Long> topicIdList) {
        SignUpCountListRequest signUpCountListRequest = new SignUpCountListRequest();
        signUpCountListRequest.setLessonIdList(topicIdList);
        List<SignUpCountResponse> learnCountList = getCountList(signUpCountListRequest);
        Map<Long, Long> learnMap = new HashMap<>(16);
        if (!CollectionUtils.isEmpty(learnCountList)) {
            for (SignUpCountResponse signUpCountResponse : learnCountList) {
                learnMap.put(signUpCountResponse.getLessonId(), signUpCountResponse.getNum());
            }
        }
        return learnMap;
    }

    /**
     * 获取学习记录列表
     * @param signUpGetPageListRequest 参数
     * @return 学习记录列表
     */
    @Override
    public SignUpGetPageListResponse getPageList(SignUpGetPageListRequest signUpGetPageListRequest) {
        Page<SignUpResponse> page = new Page<>(signUpGetPageListRequest.getCurrent(), signUpGetPageListRequest.getSize());
        page = signUpMapper.getPageList(page, signUpGetPageListRequest);
        SignUpGetPageListResponse signUpGetListResponse = new SignUpGetPageListResponse();
        signUpGetListResponse.setList(page.getRecords());
        signUpGetListResponse.setCurrent(page.getCurrent());
        signUpGetListResponse.setSize(page.getSize());
        signUpGetListResponse.setPages(page.getPages());
        signUpGetListResponse.setTotal(page.getTotal());
        return signUpGetListResponse;
    }

    /**
     * 根据课程id获取最新报名记录
     * @param lessonId 课程id
     * @param memberId 会员id
     * @return 报名记录
     */
    @Override
    public SignUpResponse getByLessonId(Long lessonId, Long memberId) {
        LambdaQueryWrapper<SignUp> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SignUp::getLessonId, lessonId);
        lambdaQueryWrapper.eq(SignUp::getMemberId, memberId);
        lambdaQueryWrapper.orderByDesc(BaseEntity::getCreateTime);
        List<SignUp> list = list(lambdaQueryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0).convert();
    }

    @Override
    public SignUpResponse get(SignUpGetRequest signUpGetRequest) {
        LambdaQueryWrapper<SignUp> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SignUp::getLessonId, signUpGetRequest.getLessonId());
        lambdaQueryWrapper.eq(SignUp::getId, signUpGetRequest.getId());
        SignUp signUp = getOne(lambdaQueryWrapper);
        if (signUp == null) {
            return null;
        }
        SignUpResponse convert = signUp.convert();
        List<Long> memberIdList = new ArrayList<>();
        memberIdList.add(convert.getMemberId());
        Map<Long, MemberResponse> memberMap = memberApi.getMemberMap(memberIdList);
        convert.setMember(memberMap.get(convert.getMemberId()));
        return convert;
    }

    @Override
    public void checkAndUpdateStatus(Long lessonId, Long memberId, Long signUpId) {
        LessonChapterGetListRequest request = new LessonChapterGetListRequest();
        request.setLessonId(lessonId);
        LessonChapterGetListResponse response = lessonChapterService.getList(request);
        if (!CollectionUtils.isEmpty(response.getList())) {
            SignUpStatus status = SignUpStatus.completed;
            // 获取课程所有章节的学习记录状态
            Map<Long, RecordStatus> recordStatusMap = recordService.getRecordStatusMapByLessonId(request.getLessonId());
            for (LessonChapterResponse lessonChapterResponse : response.getList()) {
                for (LessonChapterSectionResponse lessonChapterSectionResponse : lessonChapterResponse.getChapterSectionList()) {
                    // 状态为空，或者状态存在进行中，那么课程状态是未完成
                    if (recordStatusMap.get(lessonChapterSectionResponse.getId()) == null || RecordStatus.progressing.equals(recordStatusMap.get(lessonChapterSectionResponse.getId()))) {
                        status = SignUpStatus.signed_up;
                        break;
                    }
                }
            }
            // 如果所有章节都已学习完
            if (SignUpStatus.completed.equals(status)) {
                Lesson lesson = lessonService.getById(lessonId);
                // 判断是否有作业
                if (!StringUtils.isEmpty(lesson.getHomework())) {
                    HomeworkRecordGetRequest homeworkRecordGetRequest = new HomeworkRecordGetRequest();
                    homeworkRecordGetRequest.setLessonId(lessonId);
                    homeworkRecordGetRequest.setMemberId(memberId);
                    homeworkRecordGetRequest.setSignUpId(signUpId);
                    HomeworkRecordResponse homeworkRecordResponse = homeworkRecordService.get(homeworkRecordGetRequest);
                    // 用户未提交或者提交了还没通过
                    if (homeworkRecordResponse == null || !homeworkRecordResponse.getStatus().equals(HomeworkRecordStatus.pass_approval)) {
                        status = SignUpStatus.signed_up;
                    }
                }
            }
            SignUp signUp = getById(signUpId);
            // 完成时间
            if (signUp.getStatus().equals(SignUpStatus.signed_up) && status.equals(SignUpStatus.completed)) {
                signUp.setCompletedTime(LocalDateTime.now());
            }
            signUp.setStatus(status);
            updateById(signUp);
        }
    }

}
