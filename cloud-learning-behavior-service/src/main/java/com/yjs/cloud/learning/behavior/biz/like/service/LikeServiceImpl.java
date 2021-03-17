package com.yjs.cloud.learning.behavior.biz.like.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yjs.cloud.learning.behavior.biz.like.bean.*;
import com.yjs.cloud.learning.behavior.biz.like.entity.Like;
import com.yjs.cloud.learning.behavior.biz.like.mapper.LikeMapper;
import com.yjs.cloud.learning.behavior.common.service.BaseServiceImpl;
import com.yjs.cloud.learning.behavior.common.web.GlobalException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 点赞服务实现
 *
 * @author Bill.lai
 * @since 12/8/20
 */
@AllArgsConstructor
@Service
public class LikeServiceImpl extends BaseServiceImpl<LikeMapper, Like> implements LikeService {

    private final LikeMapper likeMapper;

    /**
     * 点赞
     * @param likeCreateRequest 参数
     * @return 点赞记录
     */
    @Override
    public LikeResponse create(LikeCreateRequest likeCreateRequest) {
        if (likeCreateRequest.getMemberId() == null) {
            throw new GlobalException("请先登录");
        }
        if (likeCreateRequest.getTopicType() == null) {
            throw new GlobalException("主题类型为必填项");
        }
        if (likeCreateRequest.getTopicId() == null) {
            throw new GlobalException("主题ID为必填项");
        }
        Like like = likeCreateRequest.convert();
        save(like);
        return like.convert();
    }

    /**
     * 点赞取消赞
     * @param likeUpdateRequest 参数
     * @return 点赞记录
     */
    @Override
    public LikeResponse update(LikeUpdateRequest likeUpdateRequest) {
        if (likeUpdateRequest.getId() == null) {
            throw new GlobalException("ID为必填项");
        }
        Like like = getById(likeUpdateRequest.getId());
        if (like == null) {
            throw new GlobalException("找不到当前点赞记录");
        }
        if (likeUpdateRequest.getTopicType() == null) {
            throw new GlobalException("主题类型为必填项");
        }
        if (likeUpdateRequest.getTopicId() == null) {
            throw new GlobalException("主题ID为必填项");
        }
        if (likeUpdateRequest.getStatus() == null) {
            throw new GlobalException("点赞状态为必填项");
        }
        like.setStatus(likeUpdateRequest.getStatus());
        updateById(like);
        return like.convert();
    }

    /**
     * 获取点赞列表
     * @param likeGetListRequest 参数
     * @return 点赞列表
     */
    @Override
    public List<LikeResponse> getList(LikeGetListRequest likeGetListRequest) {
        LambdaQueryWrapper<Like> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(Like::getTopicType, likeGetListRequest.getTopicType());
        lambdaQueryWrapper.in(Like::getTopicId, likeGetListRequest.getTopicIdList());
        if (likeGetListRequest.getMemberId() != null) {
            lambdaQueryWrapper.eq(Like::getMemberId, likeGetListRequest.getMemberId());
        }
        List<Like> list = list(lambdaQueryWrapper);
        List<LikeResponse> result = new ArrayList<>();
        for (Like like : list) {
            result.add(like.convert());
        }
        return result;
    }

    /**
     * 获取点赞统计列表
     * @param likeCountListRequest 参数
     * @return 点赞统计列表
     */
    @Override
    public List<LikeCountResponse> countList(LikeCountListRequest likeCountListRequest) {
        return likeMapper.countList(likeCountListRequest);
    }

}
