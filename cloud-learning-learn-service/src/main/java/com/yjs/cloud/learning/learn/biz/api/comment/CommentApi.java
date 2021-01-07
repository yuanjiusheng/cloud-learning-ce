package com.yjs.cloud.learning.learn.biz.api.comment;

import com.alibaba.fastjson.JSON;
import com.yjs.cloud.learning.learn.biz.api.comment.bean.CommentCountListRequest;
import com.yjs.cloud.learning.learn.biz.api.comment.bean.CommentCountResponse;
import com.yjs.cloud.learning.learn.common.request.service.RequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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

}
