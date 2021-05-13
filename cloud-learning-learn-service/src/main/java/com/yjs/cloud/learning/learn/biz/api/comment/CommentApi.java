package com.yjs.cloud.learning.learn.biz.api.comment;

import com.alibaba.fastjson.JSON;
import com.yjs.cloud.learning.learn.biz.api.comment.bean.CommentCountListRequest;
import com.yjs.cloud.learning.learn.biz.api.comment.bean.CommentCountResponse;
import com.yjs.cloud.learning.learn.biz.api.comment.enums.CommentTopicType;
import com.yjs.cloud.learning.learn.common.request.service.RequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户API
 *
 * @author Bill.lai
 * @since 2020/9/25
 */
@Slf4j
@AllArgsConstructor
@Service
public class CommentApi {

    private final RequestService requestService;

    /**
     * 获取主题评论数量
     * @param commentCountListRequest 参数
     * @return 评论数量列表
     */
    public List<CommentCountResponse> getCountList(CommentCountListRequest commentCountListRequest) {
        return requestService.list("/comment/public-api/comment/count", JSON.parseObject(JSON.toJSONString(commentCountListRequest)), CommentCountResponse.class);
    }

    public Map<Long, Long> getCommentMap(List<Long> topicIdList, CommentTopicType type) {
        CommentCountListRequest commentCountListRequest = new CommentCountListRequest();
        commentCountListRequest.setTopicType(type);
        commentCountListRequest.setTopicIdList(topicIdList);
        List<CommentCountResponse> commentCountList = getCountList(commentCountListRequest);
        Map<Long, Long> commentMap = new HashMap<>(16);
        if (!CollectionUtils.isEmpty(commentCountList)) {
            for (CommentCountResponse commentCountResponse : commentCountList) {
                commentMap.put(commentCountResponse.getTopicId(), commentCountResponse.getNum());
            }
        }
        return commentMap;
    }
}
