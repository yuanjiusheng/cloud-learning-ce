package com.yjs.cloud.learning.comment.biz.favorites.service;

import com.yjs.cloud.learning.comment.biz.favorites.bean.*;
import com.yjs.cloud.learning.comment.biz.favorites.entity.Favorite;
import com.yjs.cloud.learning.comment.common.service.IBaseService;

import java.util.List;

/**
 * 收藏服务
 *
 * @author Bill.lai
 * @since 12/8/20
 */
public interface FavoriteService extends IBaseService<Favorite> {

    /**
     * 收藏
     * @param favoriteCreateRequest 参数
     * @return 收藏记录
     */
    FavoriteResponse create(FavoriteCreateRequest favoriteCreateRequest);

    /**
     * 收藏取消赞
     * @param favoriteDeleteRequest 参数
     */
    void delete(FavoriteDeleteRequest favoriteDeleteRequest);

    /**
     * 获取收藏列表
     * @param favoriteGetListRequest 参数
     * @return 收藏列表
     */
    List<FavoriteResponse> getList(FavoriteGetListRequest favoriteGetListRequest);

    /**
     * 获取收藏统计列表
     * @param favoriteCountListRequest 参数
     * @return 收藏统计列表
     */
    List<FavoriteCountResponse> countList(FavoriteCountListRequest favoriteCountListRequest);

    /**
     * 获取收藏分页列表
     * @param favoriteGetPageListRequest 参数
     * @return 收藏列表
     */
    FavoriteGetPageListResponse getPageList(FavoriteGetPageListRequest favoriteGetPageListRequest);
}
