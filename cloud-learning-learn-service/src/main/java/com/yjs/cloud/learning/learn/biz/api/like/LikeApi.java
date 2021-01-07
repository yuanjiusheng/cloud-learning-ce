package com.yjs.cloud.learning.learn.biz.api.like;

import com.alibaba.fastjson.JSON;
import com.yjs.cloud.learning.learn.biz.api.like.bean.LikeCountListRequest;
import com.yjs.cloud.learning.learn.biz.api.like.bean.LikeCountResponse;
import com.yjs.cloud.learning.learn.common.request.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户API
 *
 * @author Bill.lai
 * @since 12/31/20
 */
@AllArgsConstructor
@Service
public class LikeApi {

    private final RequestService requestService;

    public List<LikeCountResponse> getCountList(LikeCountListRequest likeCountListRequest) {
        return requestService.list("/comment/public-api/like/count", JSON.parseObject(JSON.toJSONString(likeCountListRequest)), LikeCountResponse.class);
    }

}
