package com.yjs.cloud.learning.message.biz.privateletter.service;

import com.yjs.cloud.learning.message.biz.privateletter.bean.*;
import com.yjs.cloud.learning.message.biz.privateletter.entity.PrivateLetter;
import com.yjs.cloud.learning.message.biz.privateletter.bean.PrivateLetterCreateRequest;
import com.yjs.cloud.learning.message.common.service.IBaseService;

/**
 * 服务
 *
 * @author Bill.lai
 * @since 12/22/20
 */
public interface PrivateLetterService extends IBaseService<PrivateLetter> {

    /**
     * 发送私信
     * @param privateLetterCreateRequest 请求参数
     * @return 私信信息
     */
    PrivateLetterResponse create(PrivateLetterCreateRequest privateLetterCreateRequest);

    /**
     * 删除私信
     * @param privateLetterDeleteRequest 请求参数
     */
    void delete(PrivateLetterDeleteRequest privateLetterDeleteRequest);

    /**
     * 获取私信详情
     * @param privateLetterGetRequest 请求参数
     * @return 私信信息
     */
    PrivateLetterResponse get(PrivateLetterGetRequest privateLetterGetRequest);

    /**
     * 获取会员的私信列表
     * @param privateLetterGetMemberListRequest 请求参数
     * @return 会员私信信息列表
     */
    PrivateLetterGetMemberListResponse getMemberList(PrivateLetterGetMemberListRequest privateLetterGetMemberListRequest);

    /**
     * 获取会员的私信
     * @param privateLetterGetMemberRequest 请求参数
     * @return 会员私信
     */
    PrivateLetterResponse getByMember(PrivateLetterGetMemberRequest privateLetterGetMemberRequest);

    /**
     * 获取私信内容列表
     * @param privateLetterGetSenderListRequest 请求参数
     * @return 私信信息列表
     */
    PrivateLetterGetSenderListResponse getLetterList(PrivateLetterGetSenderListRequest privateLetterGetSenderListRequest);

    /**
     * 获取最新私信内容列表
     * @param privateLetterGetSenderListRequest 请求参数
     * @return 私信信息列表
     */
    PrivateLetterGetSenderListResponse getNewLetterList(PrivateLetterGetSenderListRequest privateLetterGetSenderListRequest);
}
