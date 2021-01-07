package com.yjs.cloud.learning.comment.biz.favorites.web;

import com.yjs.cloud.learning.comment.biz.favorites.bean.*;
import com.yjs.cloud.learning.comment.biz.favorites.service.FavoriteService;
import com.yjs.cloud.learning.comment.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 收藏控制器
 *
 * @author Bill.lai
 * @since 12/8/20
 */
@Api(tags = "收藏")
@AllArgsConstructor
@RestController
public class FavoriteController extends BaseController {

    private final FavoriteService favoriteService;

    @ApiOperation(value = "收藏", notes = "收藏", httpMethod = "POST")
    @PostMapping("/auth-api/favorite")
    public FavoriteResponse create(@RequestBody FavoriteCreateRequest favoriteCreateRequest) {
        favoriteCreateRequest.setMemberId(getLoginUserId());
        return favoriteService.create(favoriteCreateRequest);
    }

    @ApiOperation(value = "取消收藏", notes = "取消收藏", httpMethod = "DELETE")
    @DeleteMapping("/auth-api/favorite")
    public void delete(@RequestBody FavoriteDeleteRequest favoriteDeleteRequest) {
        favoriteService.delete(favoriteDeleteRequest);
    }

    @ApiOperation(value = "获取用户收藏记录", notes = "获取用户收藏记录", httpMethod = "GET")
    @GetMapping("/auth-api/favorite/list")
    public List<FavoriteResponse> get(FavoriteGetListRequest favoriteGetListRequest) {
        favoriteGetListRequest.setMemberId(getLoginUserId());
        if (favoriteGetListRequest.getMemberId() == null) {
            return null;
        }
        return favoriteService.getList(favoriteGetListRequest);
    }

    @ApiOperation(value = "获取收藏统计列表", notes = "获取收藏统计列表", httpMethod = "GET")
    @GetMapping("/public-api/favorite/count")
    public List<FavoriteCountResponse> get(FavoriteCountListRequest favoriteCountListRequest) {
        return favoriteService.countList(favoriteCountListRequest);
    }

    @ApiOperation(value = "获取会员收藏列表", notes = "获取会员收藏列表", httpMethod = "GET")
    @GetMapping("/public-api/favorite/page/list")
    public FavoriteGetPageListResponse getPageList(FavoriteGetPageListRequest favoriteGetPageListRequest) {
        return favoriteService.getPageList(favoriteGetPageListRequest);
    }
}
