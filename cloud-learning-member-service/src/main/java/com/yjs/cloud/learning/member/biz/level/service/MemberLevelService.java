package com.yjs.cloud.learning.member.biz.level.service;

import com.yjs.cloud.learning.member.biz.level.bean.*;
import com.yjs.cloud.learning.member.biz.level.entity.MemberLevel;
import com.yjs.cloud.learning.member.common.service.IBaseService;

import java.util.List;
import java.util.Map;

/**
 * 会员等级服务
 *
 * @author Bill.lai
 * @since 12/1/20
 */
public interface MemberLevelService extends IBaseService<MemberLevel> {

    /**
     * 获取会员等级列表
     * @param memberLevelGetPageRequest 请求参数
     * @return 会员等级列表
     */
    MemberLevelGetPageResponse findList(MemberLevelGetPageRequest memberLevelGetPageRequest);

    /**
     * 新增会员等级
     * @param memberLevelCreateRequest 请求参数
     * @return 会员等级
     */
    MemberLevelResponse create(MemberLevelCreateRequest memberLevelCreateRequest);

    /**
     * 修改会员等级
     * @param memberLevelUpdateRequest 请求参数
     * @return 会员等级
     */
    MemberLevelResponse update(MemberLevelUpdateRequest memberLevelUpdateRequest);

    /**
     * 删除会员等级
     * @param memberLevelDeleteRequest 请求参数
     */
    void delete(MemberLevelDeleteRequest memberLevelDeleteRequest);

    /**
     * 根据idList获取会员等级信息
     * @param idList 参数
     * @return 返回结果
     */
    Map<Long, MemberLevelResponse> getByIds(List<Long> idList);
}
