package com.yjs.cloud.learning.message.biz.privateletter.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjs.cloud.learning.message.biz.api.member.MemberApi;
import com.yjs.cloud.learning.message.biz.api.member.bean.MemberResponse;
import com.yjs.cloud.learning.message.biz.privateletter.bean.*;
import com.yjs.cloud.learning.message.biz.privateletter.entity.PrivateLetter;
import com.yjs.cloud.learning.message.biz.privateletter.enums.PrivateLetterStatus;
import com.yjs.cloud.learning.message.biz.privateletter.mapper.PrivateLetterMapper;
import com.yjs.cloud.learning.message.biz.privateletter.bean.PrivateLetterCreateRequest;
import com.yjs.cloud.learning.message.common.entity.BaseEntity;
import com.yjs.cloud.learning.message.common.service.BaseServiceImpl;
import com.yjs.cloud.learning.message.common.util.StringUtils;
import com.yjs.cloud.learning.message.common.web.GlobalException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 服务实现
 *
 * @author Bill.lai
 * @since 12/22/20
 */
@AllArgsConstructor
@Service
public class PrivateLetterServiceImpl extends BaseServiceImpl<PrivateLetterMapper, PrivateLetter> implements PrivateLetterService {

    private final PrivateLetterMapper privateLetterMapper;
    private final MemberApi memberApi;

    @Override
    public PrivateLetterResponse create(PrivateLetterCreateRequest privateLetterCreateRequest) {
        if (privateLetterCreateRequest.getSenderId() == null) {
            throw new GlobalException("发送人不能为空");
        }
        if (privateLetterCreateRequest.getReceiverId() == null) {
            throw new GlobalException("接受人不能为空");
        }
        if (StringUtils.isEmpty(privateLetterCreateRequest.getReceiverId())) {
            throw new GlobalException("发送内容不能为空");
        }
        PrivateLetter privateLetter = privateLetterCreateRequest.convert();
        privateLetter.setStatus(PrivateLetterStatus.normal);
        save(privateLetter);
        privateLetter = getById(privateLetter.getId());
        PrivateLetterResponse response = privateLetter.convert();
        List<Long> userIdList = new ArrayList<>();
        userIdList.add(privateLetter.getSenderId());
        List<MemberResponse> memberResponses = memberApi.getByIds(userIdList);
        if (memberResponses.size() > 0) {
            response.setSender(memberResponses.get(0));
        }
        return response;
    }

    @Override
    public void delete(PrivateLetterDeleteRequest privateLetterDeleteRequest) {
        if (privateLetterDeleteRequest.getId() == null) {
            throw new GlobalException("id不能为空");
        }
        PrivateLetter privateLetter = getById(privateLetterDeleteRequest.getId());
        if (privateLetter == null) {
            throw new GlobalException("该消息不存在");
        }
        removeById(privateLetter.getId());
    }

    @Override
    public PrivateLetterResponse get(PrivateLetterGetRequest privateLetterGetRequest) {
        if (privateLetterGetRequest.getId() == null) {
            throw new GlobalException("id不能为空");
        }
        PrivateLetter privateLetter = getById(privateLetterGetRequest.getId());
        return privateLetter.convert();
    }

    @Override
    public PrivateLetterGetMemberListResponse getMemberList(PrivateLetterGetMemberListRequest privateLetterGetMemberListRequest) {
        Page<PrivateLetterResponse> page = new Page<>(privateLetterGetMemberListRequest.getCurrent(), privateLetterGetMemberListRequest.getSize());
        // 获取会员id 与 私信id
        page = privateLetterMapper.getMemberList(page, privateLetterGetMemberListRequest);
        List<PrivateLetterResponse> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(page.getRecords())) {
            List<Long> privateLetterIdList = new ArrayList<>();
            List<Long> senderIdList = new ArrayList<>();
            for (PrivateLetterResponse privateLetter : page.getRecords()) {
                privateLetterIdList.add(privateLetter.getId());
                senderIdList.add(privateLetter.getSenderId());
            }
            // 查询会员信息
            List<MemberResponse> memberList = memberApi.getByIds(senderIdList);
            Map<Long, MemberResponse> memberMap = new HashMap<>(16);
            for (MemberResponse memberResponse : memberList) {
                memberMap.put(memberResponse.getId(), memberResponse);
            }
            // 获取私信信息
            LambdaQueryWrapper<PrivateLetter> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.in(BaseEntity::getId, privateLetterIdList);
            List<PrivateLetter> list = list(lambdaQueryWrapper);
            for (PrivateLetter privateLetter : list) {
                PrivateLetterResponse privateLetterResponse = privateLetter.convert();
                MemberResponse memberResponse = memberMap.get(privateLetter.getSenderId());
                if (memberResponse == null) {
                    memberResponse = memberMap.get(privateLetter.getReceiverId());
                }
                privateLetterResponse.setSender(memberResponse);
                result.add(privateLetterResponse);
            }
        }
        PrivateLetterGetMemberListResponse response = new PrivateLetterGetMemberListResponse();
        response.setCurrent(page.getCurrent());
        response.setSize(page.getSize());
        response.setTotal(page.getTotal());
        response.setPages(page.getPages());
        response.setList(result);
        return response;
    }

    @Override
    public PrivateLetterResponse getByMember(PrivateLetterGetMemberRequest privateLetterGetMemberRequest) {
        // 获取会员id 与 私信id
        PrivateLetterResponse privateLetterResponse = privateLetterMapper.getMember(privateLetterGetMemberRequest);
        // 查询会员信息
        List<Long> senderIdList = new ArrayList<>();
        if (privateLetterResponse != null) {
            // 获取私信信息
            PrivateLetter privateLetter = getById(privateLetterResponse.getId());
            privateLetterResponse = privateLetter.convert();
            senderIdList.add(privateLetterGetMemberRequest.getMemberId());
            Map<Long, MemberResponse> memberMap = memberApi.getMemberMap(senderIdList);
            privateLetterResponse.setSender(memberMap.get(privateLetterGetMemberRequest.getMemberId()));
        } else {
            privateLetterResponse = new PrivateLetterResponse();
            senderIdList.add(privateLetterGetMemberRequest.getMemberId());
            Map<Long, MemberResponse> memberMap = memberApi.getMemberMap(senderIdList);
            privateLetterResponse.setSender(memberMap.get(privateLetterGetMemberRequest.getMemberId()));
        }
        return privateLetterResponse;
    }

    @Override
    public PrivateLetterGetSenderListResponse getLetterList(PrivateLetterGetSenderListRequest privateLetterGetSenderListRequest) {
        Page<PrivateLetterResponse> page = new Page<>(privateLetterGetSenderListRequest.getCurrent(), privateLetterGetSenderListRequest.getSize());
        // 获取私信列表
        page = privateLetterMapper.getLetterList(page, privateLetterGetSenderListRequest);
        return formatLetterList(page);
    }

    @Override
    public PrivateLetterGetSenderListResponse getNewLetterList(PrivateLetterGetSenderListRequest privateLetterGetSenderListRequest) {
        Page<PrivateLetterResponse> page = new Page<>(privateLetterGetSenderListRequest.getCurrent(), privateLetterGetSenderListRequest.getSize());
        // 获取私信列表
        page = privateLetterMapper.getNewLetterList(page, privateLetterGetSenderListRequest);
        return formatLetterList(page);
    }

    private PrivateLetterGetSenderListResponse formatLetterList(Page<PrivateLetterResponse> page) {
        if (!CollectionUtils.isEmpty(page.getRecords())) {
            List<Long> senderIdList = new ArrayList<>();
            for (PrivateLetterResponse privateLetter : page.getRecords()) {
                if (!senderIdList.contains(privateLetter.getSenderId())) {
                    senderIdList.add(privateLetter.getSenderId());
                }
                if (!senderIdList.contains(privateLetter.getReceiverId())) {
                    senderIdList.add(privateLetter.getReceiverId());
                }
            }
            // 查询会员信息
            List<MemberResponse> memberList = memberApi.getByIds(senderIdList);
            Map<Long, MemberResponse> memberMap = new HashMap<>(16);
            for (MemberResponse memberResponse : memberList) {
                memberMap.put(memberResponse.getId(), memberResponse);
            }
            for (PrivateLetterResponse record : page.getRecords()) {
                record.setSender(memberMap.get(record.getSenderId()));
                record.setReceiver(memberMap.get(record.getReceiverId()));
            }
        }
        PrivateLetterGetSenderListResponse response = new PrivateLetterGetSenderListResponse();
        response.setCurrent(page.getCurrent());
        response.setSize(page.getSize());
        response.setTotal(page.getTotal());
        response.setPages(page.getPages());
        response.setList(page.getRecords());
        return response;
    }
}
