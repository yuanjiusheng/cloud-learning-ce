package com.yjs.cloud.learning.comment.biz.like.web;

import com.yjs.cloud.learning.comment.biz.like.bean.*;
import com.yjs.cloud.learning.comment.biz.like.service.LikeService;
import com.yjs.cloud.learning.comment.common.controller.BaseController;
import com.yjs.cloud.learning.comment.common.web.GlobalException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 点赞控制器
 *
 * @author Bill.lai
 * @since 12/8/20
 */
@Api(tags = "点赞")
@AllArgsConstructor
@RestController
public class LikeController extends BaseController {

    private final LikeService likeService;

    @ApiOperation(value = "点赞", notes = "点赞", httpMethod = "POST")
    @PostMapping("/auth-api/like")
    public LikeResponse create(@RequestBody LikeCreateRequest likeCreateRequest) {
        likeCreateRequest.setMemberId(getLoginUserId());
        return likeService.create(likeCreateRequest);
    }

    @ApiOperation(value = "点赞/取消赞", notes = "点赞/取消赞", httpMethod = "PUT")
    @PutMapping("/auth-api/like")
    public LikeResponse update(@RequestBody LikeUpdateRequest likeUpdateRequest) {
        if (!likeUpdateRequest.getMemberId().equals(getLoginUserId())) {
            throw new GlobalException("没有权限");
        }
        return likeService.update(likeUpdateRequest);
    }

    @ApiOperation(value = "获取用户点赞记录", notes = "获取用户点赞记录", httpMethod = "GET")
    @GetMapping("/auth-api/like/list")
    public List<LikeResponse> get(LikeGetListRequest likeGetListRequest) {
        likeGetListRequest.setMemberId(getLoginUserId());
        if (likeGetListRequest.getMemberId() == null) {
            return null;
        }
        return likeService.getList(likeGetListRequest);
    }

    @ApiOperation(value = "获取点赞统计列表", notes = "获取点赞统计列表", httpMethod = "GET")
    @GetMapping("/public-api/like/count")
    public List<LikeCountResponse> get(LikeCountListRequest likeCountListRequest) {
        return likeService.countList(likeCountListRequest);
    }

}
