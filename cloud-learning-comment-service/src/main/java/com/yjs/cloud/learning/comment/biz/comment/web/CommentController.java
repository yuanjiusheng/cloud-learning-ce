package com.yjs.cloud.learning.comment.biz.comment.web;

import com.yjs.cloud.learning.comment.biz.comment.bean.*;
import com.yjs.cloud.learning.comment.biz.comment.service.CommentService;
import com.yjs.cloud.learning.comment.biz.comment.service.ReplyCommentService;
import com.yjs.cloud.learning.comment.common.controller.BaseController;
import com.yjs.cloud.learning.comment.common.loginuser.dto.UserDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 评论控制器
 * </p>
 *
 * @author bill.lai
 * @since 2020-07-07
 */
@AllArgsConstructor
@RestController
@Api(tags = "评论")
public class CommentController extends BaseController {

    private final CommentService commentService;
    private final ReplyCommentService replyCommentService;

    @ApiOperation(value = "发表评论", notes = "发表评论", httpMethod = "POST")
    @PostMapping("/auth-api/comment")
    public CommentResponse create(@RequestBody CommentCreateRequest commentCreateRequest) {
        commentCreateRequest.setMemberId(getLoginUserId());
        return commentService.create(commentCreateRequest);
    }

    @ApiOperation(value = "删除评论", notes = "删除评论", httpMethod = "DELETE")
    @DeleteMapping("/auth-api/comment")
    public void delete(@RequestBody CommentDeleteRequest commentDeleteRequest) {
        commentService.delete(commentDeleteRequest);
    }

    @ApiOperation(value = "获取评论列表", notes = "获取评论列表", httpMethod = "GET")
    @GetMapping("/public-api/comment/list")
    public CommentListResponse list(CommentListRequest commentListRequest) {
        UserDTO user = getUser();
        if (user != null) {
            commentListRequest.setMemberId(user.getId());
        }
        return commentService.list(commentListRequest);
    }

    @ApiOperation(value = "发表评论回复", notes = "发表评论回复", httpMethod = "POST")
    @PostMapping("/auth-api/reply/comment")
    public ReplyCommentResponse create(@RequestBody ReplyCommentCreateRequest replyCommentCreateRequest) {
        replyCommentCreateRequest.setMemberId(getLoginUserId());
        return replyCommentService.create(replyCommentCreateRequest);
    }

    @ApiOperation(value = "删除评论回复", notes = "删除评论回复", httpMethod = "DELETE")
    @DeleteMapping("/auth-api/reply/comment")
    public void delete(@RequestBody ReplyCommentDeleteRequest replyCommentDeleteRequest) {
        replyCommentService.delete(replyCommentDeleteRequest);
    }

    @ApiOperation(value = "获取主题评论数量", notes = "获取主题评论数量", httpMethod = "GET")
    @GetMapping("/public-api/comment/count")
    public List<CommentCountResponse> countList(CommentCountListRequest commentCountListRequest) {
        return commentService.countList(commentCountListRequest);
    }
}
