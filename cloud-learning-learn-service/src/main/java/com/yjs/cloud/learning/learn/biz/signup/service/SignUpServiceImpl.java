package com.yjs.cloud.learning.learn.biz.signup.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjs.cloud.learning.learn.biz.record.bean.SignUpGetPageListRequest;
import com.yjs.cloud.learning.learn.biz.record.bean.SignUpGetPageListResponse;
import com.yjs.cloud.learning.learn.biz.signup.bean.*;
import com.yjs.cloud.learning.learn.biz.signup.entity.SignUp;
import com.yjs.cloud.learning.learn.biz.signup.enums.SignUpStatus;
import com.yjs.cloud.learning.learn.biz.signup.mapper.SignUpMapper;
import com.yjs.cloud.learning.learn.common.entity.BaseEntity;
import com.yjs.cloud.learning.learn.common.service.BaseServiceImpl;
import com.yjs.cloud.learning.learn.common.web.GlobalException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 报名服务实现
 *
 * @author Bill.lai
 * @since 12/14/20
 */
@AllArgsConstructor
@Service
public class SignUpServiceImpl extends BaseServiceImpl<SignUpMapper, SignUp> implements SignUpService {

    private final SignUpMapper signUpMapper;

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

    /**
     * 获取课程学习人数统计列表
     * @param signUpCountListRequest 参数
     * @return 课程学习人数统计列表
     */
    @Override
    public List<SignUpCountResponse> getCountList(SignUpCountListRequest signUpCountListRequest) {
        return signUpMapper.countList(signUpCountListRequest);
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
     * @return 报名记录
     */
    @Override
    public SignUpResponse getByLessonId(Long lessonId) {
        LambdaQueryWrapper<SignUp> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(SignUp::getLessonId, lessonId);
        lambdaQueryWrapper.orderByDesc(BaseEntity::getCreateTime);
        List<SignUp> list = list(lambdaQueryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }
        return list.get(0).convert();
    }

}
