package com.yjs.cloud.learning.message.biz.privateletter.web;

import com.yjs.cloud.learning.message.biz.privateletter.bean.*;
import com.yjs.cloud.learning.message.biz.privateletter.service.PrivateLetterService;
import com.yjs.cloud.learning.message.biz.privateletter.bean.PrivateLetterCreateRequest;
import com.yjs.cloud.learning.message.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 控制器
 *
 * @author Bill.lai
 * @since 12/22/20
 */
@Api(tags = "私信")
@AllArgsConstructor
@RestController
public class PrivateLetterController extends BaseController {

    private final PrivateLetterService privateLetterService;

    @ApiOperation(value = "发送私信", notes = "发送私信", httpMethod = "POST")
    @PostMapping("/auth-api/private-letter")
    public PrivateLetterResponse create(@RequestBody PrivateLetterCreateRequest privateLetterCreateRequest) {
        privateLetterCreateRequest.setSenderId(getLoginUserId());
        return privateLetterService.create(privateLetterCreateRequest);
    }

    @ApiOperation(value = "删除私信", notes = "删除私信", httpMethod = "DELETE")
    @DeleteMapping("/auth-api/private-letter")
    public void delete(@RequestBody PrivateLetterDeleteRequest privateLetterDeleteRequest) {
        privateLetterService.delete(privateLetterDeleteRequest);
    }

    @ApiOperation(value = "获取私信详情", notes = "获取私信详情", httpMethod = "GET")
    @GetMapping("/auth-api/private-letter")
    public PrivateLetterResponse get(PrivateLetterGetRequest privateLetterGetRequest) {
        return privateLetterService.get(privateLetterGetRequest);
    }

    @ApiOperation(value = "获取会员的私信列表", notes = "获取会员的私信列表", httpMethod = "GET")
    @GetMapping("/auth-api/private-letter/member/list")
    public PrivateLetterGetMemberListResponse getMemberList(PrivateLetterGetMemberListRequest privateLetterGetMemberListRequest) {
        privateLetterGetMemberListRequest.setMemberId(getLoginUserId());
        return privateLetterService.getMemberList(privateLetterGetMemberListRequest);
    }

    @ApiOperation(value = "获取私信内容列表", notes = "获取私信内容列表", httpMethod = "GET")
    @GetMapping("/auth-api/private-letter/list")
    public PrivateLetterGetSenderListResponse getSenderList(PrivateLetterGetSenderListRequest privateLetterGetSenderListRequest) {
        privateLetterGetSenderListRequest.setMemberId(getLoginUserId());
        PrivateLetterGetSenderListResponse response = privateLetterService.getLetterList(privateLetterGetSenderListRequest);
        response.setCurrentUserId(getLoginUserId());
        return response;
    }

}
