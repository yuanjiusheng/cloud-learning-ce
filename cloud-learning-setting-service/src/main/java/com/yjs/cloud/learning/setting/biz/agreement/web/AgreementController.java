package com.yjs.cloud.learning.setting.biz.agreement.web;

import com.yjs.cloud.learning.setting.biz.agreement.bean.*;
import com.yjs.cloud.learning.setting.biz.agreement.service.AgreementService;
import com.yjs.cloud.learning.setting.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 协议控制器
 *
 * @author Bill.lai
 * @since 12/29/20
 */
@AllArgsConstructor
@RestController
@Api(tags = "协议")
public class AgreementController extends BaseController {

    private final AgreementService agreementService;

    @PostMapping("agreement")
    @ApiOperation(value = "新建协议", notes = "新建协议", httpMethod = "POST")
    public AgreementResponse create(@RequestBody AgreementCreateRequest agreementCreateRequest) {
        return agreementService.create(agreementCreateRequest);
    }

    @PutMapping("agreement")
    @ApiOperation(value = "修改协议", notes = "修改协议", httpMethod = "PUT")
    public AgreementResponse update(@RequestBody AgreementUpdateRequest agreementUpdateRequest) {
        return agreementService.update(agreementUpdateRequest);
    }

    @GetMapping("public-api/agreement")
    @ApiOperation(value = "获取协议", notes = "获取协议", httpMethod = "GET")
    public AgreementResponse update(AgreementGetRequest agreementGetRequest) {
        return agreementService.getByType(agreementGetRequest.getType());
    }

    @GetMapping("agreement/page")
    @ApiOperation(value = "获取协议列表", notes = "获取协议列表", httpMethod = "GET")
    public AgreementGetPageListResponse page(AgreementGetPageListRequest agreementGetPageListRequest) {
        return agreementService.page(agreementGetPageListRequest);
    }

}
