package com.yjs.cloud.learning.member.biz.level.web;

import com.yjs.cloud.learning.member.biz.level.bean.*;
import com.yjs.cloud.learning.member.biz.level.service.MemberLevelService;
import com.yjs.cloud.learning.member.common.controller.BaseController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 会员等级控制器
 *
 * @author Bill.lai
 * @since 12/1/20
 */
@AllArgsConstructor
@Api(tags = "会员等级")
@RestController
public class MemberLevelController extends BaseController {

    private final MemberLevelService memberLevelService;

    @ApiOperation(value = "获取会员等级列表", notes = "获取会员等级列表", httpMethod = "GET")
    @GetMapping("/level/list")
    public MemberLevelGetPageResponse getList(MemberLevelGetPageRequest memberLevelGetPageRequest) {
        return memberLevelService.findList(memberLevelGetPageRequest);
    }

    @ApiOperation(value = "新增会员等级", notes = "新增会员等级", httpMethod = "POST")
    @PostMapping("/level")
    public MemberLevelResponse create(@RequestBody MemberLevelCreateRequest memberLevelCreateRequest) {
        return memberLevelService.create(memberLevelCreateRequest);
    }

    @ApiOperation(value = "修改会员等级", notes = "修改会员等级", httpMethod = "PUT")
    @PutMapping("/level")
    public MemberLevelResponse update(@RequestBody MemberLevelUpdateRequest memberLevelUpdateRequest) {
        return memberLevelService.update(memberLevelUpdateRequest);
    }

    @ApiOperation(value = "删除会员等级", notes = "删除会员等级", httpMethod = "DELETE")
    @DeleteMapping("/level")
    public void delete(@RequestBody MemberLevelDeleteRequest memberLevelDeleteRequest) {
        memberLevelService.delete(memberLevelDeleteRequest);
    }

}
