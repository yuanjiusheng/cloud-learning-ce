package com.yjs.cloud.learning.member.base.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjs.cloud.learning.member.base.bean.*;
import com.yjs.cloud.learning.member.base.entity.Member;
import com.yjs.cloud.learning.member.base.enums.MemberStatus;
import com.yjs.cloud.learning.member.base.mapper.MemberMapper;
import com.yjs.cloud.learning.member.biz.level.bean.MemberLevelResponse;
import com.yjs.cloud.learning.member.biz.level.service.MemberLevelService;
import com.yjs.cloud.learning.member.common.entity.BaseEntity;
import com.yjs.cloud.learning.member.common.service.BaseServiceImpl;
import com.yjs.cloud.learning.member.common.util.RandomUtils;
import com.yjs.cloud.learning.member.common.util.StringUtils;
import com.yjs.cloud.learning.member.common.web.GlobalException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 会员服务实现
 *
 * @author Bill.lai
 * @since 2019-08-23
 */
@Service
@AllArgsConstructor
public class MemberServiceImpl extends BaseServiceImpl<MemberMapper, Member> implements MemberService {

    private final MemberMapper memberMapper;
    private final MemberLevelService memberLevelService;
    private final MemberLevelRelationService memberLevelRelationService;

    /**
     * 获取会员列表
     * @param memberGetPageRequest 请求参数
     * @return 会员列表
     */
    @Override
    public MemberGetPageResponse findList(MemberGetPageRequest memberGetPageRequest) {
        Page<MemberResponse> page = new Page<>(memberGetPageRequest.getCurrent(), memberGetPageRequest.getSize());
        page = memberMapper.findList(page, memberGetPageRequest);
        List<Long> levelIdList = new ArrayList<>();
        for (MemberResponse response : page.getRecords()) {
            levelIdList.add(response.getLevelId());
        }
        Map<Long, MemberLevelResponse> levelMap = memberLevelService.getByIds(levelIdList);
        for (MemberResponse response : page.getRecords()) {
            response.setLevel(levelMap.get(response.getLevelId()));
        }
        MemberGetPageResponse userGetListResponse = new MemberGetPageResponse();
        userGetListResponse.setList(page.getRecords());
        userGetListResponse.setCurrent(page.getCurrent());
        userGetListResponse.setSize(page.getSize());
        userGetListResponse.setPages(page.getPages());
        userGetListResponse.setTotal(page.getTotal());
        return userGetListResponse;
    }

    /**
     * 根据手机号码获取会员信息
     * @param mobile 手机号码
     * @return 会员信息
     */
    @Override
    public MemberResponse getByMobile(String mobile) {
        LambdaQueryWrapper<Member> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Member::getMobile, mobile).or().eq(Member::getEmail, mobile).or().eq(Member::getUsername, mobile);
        Member member = getOne(queryWrapper);
        if (member == null) {
            throw new GlobalException("找不到会员信息");
        }
        MemberResponse response = member.convert();
        response.setLevel(memberLevelRelationService.getLevel(response.getId()));
        return response;
    }

    /**
     * 根据id列表获取会员信息
     * @param ids id列表
     * @return 会员信息
     */
    @Override
    public List<MemberResponse> getByIds(List<Long> ids) {
        LambdaQueryWrapper<Member> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(BaseEntity::getId, ids);
        List<Member> memberList = list(lambdaQueryWrapper);
        List<MemberResponse> responseList = new ArrayList<>();
        for (Member member : memberList) {
            responseList.add(member.convert());
        }
        return responseList;
    }

    /**
     * 修改会员信息
     * @param memberUpdateRequest 参数
     * @return 会员信息
     */
    @Override
    public MemberResponse updateMember(MemberUpdateRequest memberUpdateRequest) {
        if (memberUpdateRequest.getId() == null) {
            throw new GlobalException("ID为必填项");
        }
        Member member = getById(memberUpdateRequest.getId());
        if (member == null) {
            throw new GlobalException("找不到会员信息");
        }
        if (!StringUtils.isEmpty(memberUpdateRequest.getName())) {
            member.setName(memberUpdateRequest.getName());
        }
        if (!StringUtils.isEmpty(memberUpdateRequest.getEmail())) {
            member.setEmail(memberUpdateRequest.getEmail());
        }
        if (!StringUtils.isEmpty(memberUpdateRequest.getMobile())) {
            member.setMobile(memberUpdateRequest.getMobile());
        }
        if (!StringUtils.isEmpty(memberUpdateRequest.getAvatar())) {
            member.setAvatar(memberUpdateRequest.getAvatar());
        }
        if (!StringUtils.isEmpty(memberUpdateRequest.getPassword())) {
            if(!member.getPassword().equals(memberUpdateRequest.getOldPassword())) {
                throw new GlobalException("旧密码不正确");
            }
            member.setPassword(memberUpdateRequest.getPassword());
        }
        updateById(member);
        return member.convert();
    }

    /**
     * 注册会员
     * @param memberCreateRequest 参数
     * @return 会员信息
     */
    @Override
    public MemberResponse create(MemberCreateRequest memberCreateRequest) {
        LambdaQueryWrapper<Member> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Member::getEmail, memberCreateRequest.getEmail());
        Member one = getOne(lambdaQueryWrapper);
        if (one != null) {
            throw new GlobalException("用户已经存在");
        }
        Member member = new Member();
        member.setPassword(memberCreateRequest.getPassword());
        member.setEmail(memberCreateRequest.getEmail());
        member.setName(RandomUtils.name(5));
        member.setStatus(MemberStatus.normal);
        save(member);
        member.setCode(RandomUtils.number(member.getId()));
        updateById(member);
        return member.convert();
    }

    /**
     * 注册会员
     * @param memberCreateMobileRequest 参数
     * @return 会员信息
     */
    @Override
    public MemberResponse create(MemberCreateMobileRequest memberCreateMobileRequest) {
        // todo 校验验证码
//        if (memberCreateMobileRequest.getAuthCode().equals()) {
//
//        }
        Member member = new Member();
        member.setMobile(memberCreateMobileRequest.getMobile());
        member.setName(RandomUtils.name(5));
        member.setStatus(MemberStatus.normal);
        save(member);
        member.setCode(RandomUtils.number(member.getId()));
        updateById(member);
        return member.convert();
    }

}
