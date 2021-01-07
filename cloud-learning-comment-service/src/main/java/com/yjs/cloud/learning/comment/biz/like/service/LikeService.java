package com.yjs.cloud.learning.comment.biz.like.service;

import com.yjs.cloud.learning.comment.biz.like.bean.*;
import com.yjs.cloud.learning.comment.biz.like.entity.Like;
import com.yjs.cloud.learning.comment.common.service.IBaseService;

import java.util.List;

/**
 * 点赞服务
 *
 * @author Bill.lai
 * @since 12/8/20
 */
public interface LikeService extends IBaseService<Like> {

    /**
     * 点赞
     * @param likeCreateRequest 参数
     * @return 点赞记录
     */
    LikeResponse create(LikeCreateRequest likeCreateRequest);

    /**
     * 点赞取消赞
     * @param likeUpdateRequest 参数
     * @return 点赞记录
     */
    LikeResponse update(LikeUpdateRequest likeUpdateRequest);

    /**
     * 获取点赞列表
     * @param likeGetListRequest 参数
     * @return 点赞列表
     */
    List<LikeResponse> getList(LikeGetListRequest likeGetListRequest);

    /**
     * 获取点赞统计列表
     * @param likeCountListRequest 参数
     * @return 点赞统计列表
     */
    List<LikeCountResponse> countList(LikeCountListRequest likeCountListRequest);
}
