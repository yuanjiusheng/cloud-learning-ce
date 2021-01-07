package com.yjs.cloud.learning.setting.biz.agreement.service;

import com.yjs.cloud.learning.setting.biz.agreement.bean.*;
import com.yjs.cloud.learning.setting.biz.agreement.entity.Agreement;
import com.yjs.cloud.learning.setting.common.service.IBaseService;

/**
 * 协议服务
 *
 * @author Bill.lai
 * @since 12/29/20
 */
public interface AgreementService extends IBaseService<Agreement> {

    /**
     * 新增协议
     * @param agreementCreateRequest 参数
     * @return 协议
     */
    AgreementResponse create(AgreementCreateRequest agreementCreateRequest);

    /**
     * 更新协议
     * @param agreementUpdateRequest 参数
     * @return 协议
     */
    AgreementResponse update(AgreementUpdateRequest agreementUpdateRequest);

    /**
     * 根据类型获协议
     * @param type 类型
     * @return 协议
     */
    AgreementResponse getByType(String type);

    /**
     * 获协议列表
     * @param agreementGetPageListRequest 参数
     * @return 协议列表
     */
    AgreementGetPageListResponse page(AgreementGetPageListRequest agreementGetPageListRequest);

}
