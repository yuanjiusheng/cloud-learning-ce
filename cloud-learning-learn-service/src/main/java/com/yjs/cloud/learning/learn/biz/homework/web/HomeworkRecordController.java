package com.yjs.cloud.learning.learn.biz.homework.web;

import com.yjs.cloud.learning.learn.biz.homework.bean.*;
import com.yjs.cloud.learning.learn.biz.homework.enums.HomeworkRecordStatus;
import com.yjs.cloud.learning.learn.biz.homework.service.HomeworkRecordService;
import com.yjs.cloud.learning.learn.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * web
 *
 * @author Bill.lai
 * @since 2021/4/28
 */
@AllArgsConstructor
@Api(tags = "作业提交记录")
@RestController
public class HomeworkRecordController extends BaseController {

    private final HomeworkRecordService homeworkRecordService;

    @ApiOperation(value = "提交作业", notes = "提交作业", httpMethod = "POST")
    @PostMapping("auth-api/homework/record")
    private HomeworkRecordResponse create(@RequestBody HomeworkRecordCreateRequest homeworkRecordCreateRequest) {
        homeworkRecordCreateRequest.setMemberId(getLoginUserId());
        return homeworkRecordService.create(homeworkRecordCreateRequest);
    }

    @ApiOperation(value = "更新作业", notes = "更新作业", httpMethod = "PUT")
    @PutMapping("auth-api/homework/record")
    private HomeworkRecordResponse update(@RequestBody HomeworkRecordUpdateRequest homeworkRecordUpdateRequest) {
        homeworkRecordUpdateRequest.setMemberId(getLoginUserId());
        return homeworkRecordService.update(homeworkRecordUpdateRequest);
    }

    @ApiOperation(value = "通过作业", notes = "通过作业", httpMethod = "PUT")
    @PutMapping("homework/record/approval/pass")
    private HomeworkRecordResponse pass(@RequestBody HomeworkRecordApprovalRequest homeworkRecordApprovalRequest) {
        homeworkRecordApprovalRequest.setStatus(HomeworkRecordStatus.pass_approval);
        return homeworkRecordService.approval(homeworkRecordApprovalRequest);
    }

    @ApiOperation(value = "驳回作业", notes = "驳回作业", httpMethod = "PUT")
    @PutMapping("homework/record/approval/reject")
    private HomeworkRecordResponse reject(@RequestBody HomeworkRecordApprovalRequest homeworkRecordApprovalRequest) {
        homeworkRecordApprovalRequest.setStatus(HomeworkRecordStatus.reject_approval);
        return homeworkRecordService.approval(homeworkRecordApprovalRequest);
    }

    @ApiOperation(value = "获取作业提交记录", notes = "获取作业提交记录", httpMethod = "GET")
    @GetMapping("auth-api/homework/record")
    private HomeworkRecordResponse get(HomeworkRecordGetRequest homeworkRecordGetRequest) {
        homeworkRecordGetRequest.setMemberId(getLoginUserId());
        return homeworkRecordService.get(homeworkRecordGetRequest);
    }

    @ApiOperation(value = "获取作业提交记录列表", notes = "获取作业提交记录列表", httpMethod = "GET")
    @GetMapping("homework/record/list")
    private HomeworkRecordListResponse list(HomeworkRecordListRequest homeworkRecordListRequest) {
        return homeworkRecordService.getList(homeworkRecordListRequest);
    }

}
