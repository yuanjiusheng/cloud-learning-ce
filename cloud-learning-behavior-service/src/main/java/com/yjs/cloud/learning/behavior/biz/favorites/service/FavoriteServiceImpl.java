package com.yjs.cloud.learning.behavior.biz.favorites.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjs.cloud.learning.behavior.biz.favorites.bean.*;
import com.yjs.cloud.learning.behavior.biz.favorites.entity.Favorite;
import com.yjs.cloud.learning.behavior.biz.favorites.mapper.FavoriteMapper;
import com.yjs.cloud.learning.behavior.common.service.BaseServiceImpl;
import com.yjs.cloud.learning.behavior.common.web.GlobalException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 收藏服务实现
 *
 * @author Bill.lai
 * @since 12/8/20
 */
@AllArgsConstructor
@Service
public class FavoriteServiceImpl extends BaseServiceImpl<FavoriteMapper, Favorite> implements FavoriteService {

    private final FavoriteMapper favoriteMapper;

    /**
     * 收藏
     * @param favoriteCreateRequest 参数
     * @return 收藏记录
     */
    @Override
    public FavoriteResponse create(FavoriteCreateRequest favoriteCreateRequest) {
        if (favoriteCreateRequest.getMemberId() == null) {
            throw new GlobalException("请先登录");
        }
        if (favoriteCreateRequest.getTopicId() == null) {
            throw new GlobalException("主题ID为必填项");
        }
        if (favoriteCreateRequest.getTopicType() == null) {
            throw new GlobalException("主题类型为必填项");
        }
        Favorite favorite = favoriteCreateRequest.convert();
        save(favorite);
        return favorite.convert();
    }

    /**
     * 取消收藏
     * @param favoriteDeleteRequest 参数
     */
    @Override
    public void delete(FavoriteDeleteRequest favoriteDeleteRequest) {
        if (favoriteDeleteRequest.getTopicId() == null && favoriteDeleteRequest.getId() == null) {
            throw new GlobalException("ID为必填项");
        }
        if (favoriteDeleteRequest.getTopicId() == null && favoriteDeleteRequest.getTopicType() == null) {
            throw new GlobalException("topicId与type为必填项");
        }
        Favorite favorite;
        if (favoriteDeleteRequest.getId() != null) {
            favorite = getById(favoriteDeleteRequest.getId());
        } else {
            LambdaQueryWrapper<Favorite> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(Favorite::getTopicId, favoriteDeleteRequest.getTopicId());
            lambdaQueryWrapper.eq(Favorite::getTopicType, favoriteDeleteRequest.getTopicType());
            favorite = getOne(lambdaQueryWrapper);
        }
        if (favorite == null) {
            throw new GlobalException("找不到当前收藏记录");
        }
        removeById(favorite.getId());
    }

    /**
     * 获取收藏列表
     * @param favoriteGetListRequest 参数
     * @return 收藏列表
     */
    @Override
    public List<FavoriteResponse> getList(FavoriteGetListRequest favoriteGetListRequest) {
        LambdaQueryWrapper<Favorite> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Favorite::getTopicType, favoriteGetListRequest.getTopicType());
        lambdaQueryWrapper.in(Favorite::getTopicId, favoriteGetListRequest.getTopicIdList());
        if (favoriteGetListRequest.getMemberId() != null) {
            lambdaQueryWrapper.eq(Favorite::getMemberId, favoriteGetListRequest.getMemberId());
        }
        List<Favorite> list = list(lambdaQueryWrapper);
        List<FavoriteResponse> result = new ArrayList<>();
        for (Favorite favorite : list) {
            result.add(favorite.convert());
        }
        return result;
    }

    /**
     * 获取收藏统计列表
     * @param favoriteCountListRequest 参数
     * @return 收藏统计列表
     */
    @Override
    public List<FavoriteCountResponse> countList(FavoriteCountListRequest favoriteCountListRequest) {
        return favoriteMapper.countList(favoriteCountListRequest);
    }

    /**
     * 获取收藏分页列表
     * @param favoriteGetPageListRequest 参数
     * @return 收藏列表
     */
    @Override
    public FavoriteGetPageListResponse getPageList(FavoriteGetPageListRequest favoriteGetPageListRequest) {
        Page<Favorite> page = new Page<>(favoriteGetPageListRequest.getCurrent(), favoriteGetPageListRequest.getSize());
        LambdaQueryWrapper<Favorite> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Favorite::getTopicType, favoriteGetPageListRequest.getTopicType());
        if (!CollectionUtils.isEmpty(favoriteGetPageListRequest.getTopicIdList())) {
            lambdaQueryWrapper.in(Favorite::getTopicId, favoriteGetPageListRequest.getTopicIdList());
        }
        lambdaQueryWrapper.eq(Favorite::getMemberId, favoriteGetPageListRequest.getMemberId());
        page = baseMapper.selectPage(page, lambdaQueryWrapper);
        List<FavoriteResponse> result = new ArrayList<>();
        for (Favorite favorite : page.getRecords()) {
            result.add(favorite.convert());
        }
        FavoriteGetPageListResponse favoriteGetPageListResponse = new FavoriteGetPageListResponse();
        favoriteGetPageListResponse.setCurrent(page.getCurrent());
        favoriteGetPageListResponse.setSize(page.getSize());
        favoriteGetPageListResponse.setTotal(page.getTotal());
        favoriteGetPageListResponse.setPages(page.getPages());
        favoriteGetPageListResponse.setList(result);
        return favoriteGetPageListResponse;
    }
}
