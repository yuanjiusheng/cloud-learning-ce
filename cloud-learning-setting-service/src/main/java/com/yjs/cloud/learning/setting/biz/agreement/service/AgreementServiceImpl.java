package com.yjs.cloud.learning.setting.biz.agreement.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjs.cloud.learning.setting.biz.agreement.bean.*;
import com.yjs.cloud.learning.setting.biz.agreement.entity.Agreement;
import com.yjs.cloud.learning.setting.biz.agreement.mapper.AgreementMapper;
import com.yjs.cloud.learning.setting.common.service.BaseServiceImpl;
import com.yjs.cloud.learning.setting.common.util.StringUtils;
import com.yjs.cloud.learning.setting.common.web.GlobalException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 协议服务实现
 *
 * @author Bill.lai
 * @since 12/29/20
 */
@AllArgsConstructor
@Service
public class AgreementServiceImpl extends BaseServiceImpl<AgreementMapper, Agreement> implements AgreementService {

    @Override
    public AgreementResponse create(AgreementCreateRequest agreementCreateRequest) {
        if (StringUtils.isEmpty(agreementCreateRequest.getType())) {
            throw new GlobalException("类型为必填项");
        }
        if (StringUtils.isEmpty(agreementCreateRequest.getContent())) {
            throw new GlobalException("协议内容为必填项");
        }
        AgreementResponse response = getByType(agreementCreateRequest.getType());
        if (response != null) {
            throw new GlobalException("协议已存在");
        }
        Agreement agreement = new Agreement();
        agreement.setName(agreementCreateRequest.getName());
        agreement.setType(agreementCreateRequest.getType());
        agreement.setContent(agreementCreateRequest.getContent());
        save(agreement);
        return agreement.convert();
    }

    @Override
    public AgreementResponse update(AgreementUpdateRequest agreementUpdateRequest) {
        if (StringUtils.isEmpty(agreementUpdateRequest.getId())) {
            throw new GlobalException("id为必填项");
        }
        Agreement agreement = getById(agreementUpdateRequest.getId());
        if (agreement == null) {
            throw new GlobalException("协议不存在");
        }
        if (StringUtils.isEmpty(agreementUpdateRequest.getType())) {
            throw new GlobalException("类型为必填项");
        }
        if (StringUtils.isEmpty(agreementUpdateRequest.getContent())) {
            throw new GlobalException("协议内容为必填项");
        }
        agreement.setName(agreementUpdateRequest.getName());
        agreement.setContent(agreementUpdateRequest.getContent());
        updateById(agreement);
        return agreement.convert();
    }

    @Override
    public AgreementResponse getByType(String type) {
        LambdaQueryWrapper<Agreement> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Agreement::getType, type);
        Agreement agreement = getOne(lambdaQueryWrapper);
        if (agreement == null) {
            return null;
        }
        return agreement.convert();
    }

    @Override
    public AgreementGetPageListResponse page(AgreementGetPageListRequest agreementGetPageListRequest) {
        Page<Agreement> page = new Page<>(agreementGetPageListRequest.getCurrent(), agreementGetPageListRequest.getSize());
        LambdaQueryWrapper<Agreement> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        page = baseMapper.selectPage(page, lambdaQueryWrapper);
        List<AgreementResponse> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(page.getRecords())) {
            for (Agreement agreement : page.getRecords()) {
                result.add(agreement.convert());
            }
        }
        AgreementGetPageListResponse response = new AgreementGetPageListResponse();
        response.setList(result);
        response.setCurrent(page.getCurrent());
        response.setSize(page.getSize());
        response.setPages(page.getPages());
        response.setTotal(page.getTotal());
        return response;
    }

}
