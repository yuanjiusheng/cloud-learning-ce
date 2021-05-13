package com.yjs.cloud.learning.learn.biz.api.like;

import com.alibaba.fastjson.JSON;
import com.yjs.cloud.learning.learn.biz.api.like.bean.LikeCountListRequest;
import com.yjs.cloud.learning.learn.biz.api.like.bean.LikeCountResponse;
import com.yjs.cloud.learning.learn.biz.api.like.enums.LikeTopicType;
import com.yjs.cloud.learning.learn.common.request.service.RequestService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public Map<Long, Long> getLikeMap(List<Long> topicIdList, LikeTopicType type) {
        LikeCountListRequest likeCountListRequest = new LikeCountListRequest();
        likeCountListRequest.setTopicType(type);
        likeCountListRequest.setTopicIdList(topicIdList);
        List<LikeCountResponse> likeCountList = getCountList(likeCountListRequest);
        Map<Long, Long> likeMap = new HashMap<>(16);
        if (!CollectionUtils.isEmpty(likeCountList)) {
            for (LikeCountResponse likeCountResponse : likeCountList) {
                likeMap.put(likeCountResponse.getTopicId(), likeCountResponse.getNum());
            }
        }
        return likeMap;
    }
}
