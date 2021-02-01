package com.yjs.cloud.learning.comment.biz.api.learn;

import com.yjs.cloud.learning.comment.biz.comment.bean.CommentTopicResponse;
import com.yjs.cloud.learning.comment.common.request.service.RequestService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 课程api
 *
 * @author Bill.lai
 * @since 12/31/20
 */
@Slf4j
@AllArgsConstructor
@Service
public class LessonApi {

    private final RequestService requestService;

    public CommentTopicResponse getById(Long id) {
        try {
            Map<String, Object> param = new HashMap<>(1);
            param.put("id", id);
            return requestService.get("/learn/lesson", param, CommentTopicResponse.class);
        } catch (Exception e) {
            log.error("获取课程信息Api错误，参数：{}", id, e);
        }
        return null;
    }

}
