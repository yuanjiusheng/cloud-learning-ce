package com.yjs.cloud.learning.member.biz.level.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjs.cloud.learning.member.biz.level.bean.*;
import com.yjs.cloud.learning.member.biz.level.entity.MemberLevel;
import com.yjs.cloud.learning.member.biz.level.mapper.MemberLevelMapper;
import com.yjs.cloud.learning.member.common.service.BaseServiceImpl;
import com.yjs.cloud.learning.member.common.util.StringUtils;
import com.yjs.cloud.learning.member.common.web.GlobalException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 会员等级服务实现
 *
 * @author Bill.lai
 * @since 12/1/20
 */
@AllArgsConstructor
@Service
public class MemberLevelServiceImpl extends BaseServiceImpl<MemberLevelMapper, MemberLevel> implements MemberLevelService {

    private final MemberLevelMapper memberLevelMapper;

    /**
     * 获取会员等级列表
     * @param memberLevelGetPageRequest 请求参数
     * @return 会员等级列表
     */
    @Override
    public MemberLevelGetPageResponse findList(MemberLevelGetPageRequest memberLevelGetPageRequest) {
        Page<MemberLevelResponse> page = new Page<>(memberLevelGetPageRequest.getCurrent(), memberLevelGetPageRequest.getSize());
        page = memberLevelMapper.findList(page, memberLevelGetPageRequest);
        MemberLevelGetPageResponse response = new MemberLevelGetPageResponse();
        response.setList(page.getRecords());
        response.setCurrent(page.getCurrent());
        response.setSize(page.getSize());
        response.setPages(page.getPages());
        response.setTotal(page.getTotal());
        return response;
    }

    /**
     * 新增会员等级
     * @param memberLevelCreateRequest 请求参数
     * @return 会员等级
     */
    @Override
    public MemberLevelResponse create(MemberLevelCreateRequest memberLevelCreateRequest) {
        if (StringUtils.isEmpty(memberLevelCreateRequest.getName())) {
            throw new GlobalException("名称为必填项");
        }
        if (memberLevelCreateRequest.getConditions() == null) {
            throw new GlobalException("达成条件为必填项");
        }
        if (StringUtils.isEmpty(memberLevelCreateRequest.getDescription())) {
            throw new GlobalException("描述为必填项");
        }
        MemberLevel memberLevel = memberLevelCreateRequest.convert();
        save(memberLevel);
        return memberLevel.convert();
    }

    /**
     * 修改会员等级
     * @param memberLevelUpdateRequest 请求参数
     * @return 会员等级
     */
    @Override
    public MemberLevelResponse update(MemberLevelUpdateRequest memberLevelUpdateRequest) {
        if (memberLevelUpdateRequest.getId() == null) {
            throw new GlobalException("ID为必填项");
        }
        MemberLevel memberLevel = getById(memberLevelUpdateRequest.getId());
        if (memberLevel == null) {
            throw new GlobalException("找不到相关数据");
        }
        if (StringUtils.isEmpty(memberLevelUpdateRequest.getName())) {
            throw new GlobalException("名称为必填项");
        }
        memberLevel.setName(memberLevelUpdateRequest.getName());
        if (memberLevelUpdateRequest.getConditions() == null) {
            throw new GlobalException("达成条件为必填项");
        }
        memberLevel.setConditions(memberLevelUpdateRequest.getConditions());
        if (StringUtils.isEmpty(memberLevelUpdateRequest.getDescription())) {
            throw new GlobalException("描述为必填项");
        }
        memberLevel.setDescription(memberLevelUpdateRequest.getDescription());
        updateById(memberLevel);
        return memberLevel.convert();
    }

    /**
     * 删除会员等级
     * @param memberLevelDeleteRequest 请求参数
     */
    @Override
    public void delete(MemberLevelDeleteRequest memberLevelDeleteRequest) {
        if (memberLevelDeleteRequest.getId() == null) {
            throw new GlobalException("ID为必填项");
        }
        MemberLevel memberLevel = getById(memberLevelDeleteRequest.getId());
        if (memberLevel == null) {
            throw new GlobalException("找不到相关数据");
        }
        removeById(memberLevelDeleteRequest.getId());
    }

}
