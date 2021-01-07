package com.yjs.cloud.learning.learn.biz.api.favorite;

import com.alibaba.fastjson.JSON;
import com.yjs.cloud.learning.learn.biz.api.favorite.bean.FavoriteCountListRequest;
import com.yjs.cloud.learning.learn.biz.api.favorite.bean.FavoriteCountResponse;
import com.yjs.cloud.learning.learn.biz.api.favorite.bean.FavoriteGetPageListRequest;
import com.yjs.cloud.learning.learn.biz.api.favorite.bean.FavoriteGetPageListResponse;
import com.yjs.cloud.learning.learn.common.request.service.RequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 会员api
 *
 * @author Bill.lai
 * @since 12/31/20
 */
@Slf4j
@AllArgsConstructor
@Service
public class FavoriteApi {

    private final RequestService requestService;

    /**
     * 分页获取用户收藏列表
     * @param favoriteGetPageListRequest 参数
     * @return 收藏列表
     */
    public FavoriteGetPageListResponse getPageList(FavoriteGetPageListRequest favoriteGetPageListRequest) {
        return requestService.get("/comment/public-api/favorite/page/list", JSON.parseObject(JSON.toJSONString(favoriteGetPageListRequest)), FavoriteGetPageListResponse.class);
    }

    /**
     * 获取收藏主题的收藏数量
     * @param favoriteCountListRequest 参数
     * @return 收藏数量列表
     */
    public List<FavoriteCountResponse> getCountList(FavoriteCountListRequest favoriteCountListRequest) {
        return requestService.list("/comment/public-api/favorite/count", JSON.parseObject(JSON.toJSONString(favoriteCountListRequest)), FavoriteCountResponse.class);
    }

}
