package com.yjs.cloud.learning.learn.biz.record.web;

import com.yjs.cloud.learning.learn.biz.record.bean.RecordCreateRequest;
import com.yjs.cloud.learning.learn.biz.record.bean.RecordGetRequest;
import com.yjs.cloud.learning.learn.biz.record.bean.RecordResponse;
import com.yjs.cloud.learning.learn.biz.record.bean.RecordUpdateRequest;
import com.yjs.cloud.learning.learn.biz.record.service.RecordService;
import com.yjs.cloud.learning.learn.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 控制器
 *
 * @author Bill.lai
 * @since 12/14/20
 */
@Api(tags = "学习记录")
@AllArgsConstructor
@RestController
public class RecordController extends BaseController {

    private final RecordService recordService;

    @ApiOperation(value = "保存学习记录", httpMethod = "POST")
    @PostMapping("/auth-api/record")
    public RecordResponse create(@RequestBody RecordCreateRequest recordCreateRequest) {
        recordCreateRequest.setMemberId(getLoginUserId());
        return recordService.create(recordCreateRequest);
    }

    @ApiOperation(value = "更新学习记录", httpMethod = "PUT")
    @PutMapping("/auth-api/record")
    public RecordResponse update(@RequestBody RecordUpdateRequest recordUpdateRequest) {
        recordUpdateRequest.setMemberId(getLoginUserId());
        return recordService.update(recordUpdateRequest);
    }

    @ApiOperation(value = "获取学习记录", httpMethod = "GET")
    @GetMapping("/auth-api/record")
    public RecordResponse get(RecordGetRequest recordGetRequest) {
        recordGetRequest.setMemberId(getLoginUserId());
        return recordService.get(recordGetRequest);
    }

}
