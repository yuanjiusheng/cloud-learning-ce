package com.yjs.cloud.learning.member.base.web;

import com.yjs.cloud.learning.member.base.bean.*;
import com.yjs.cloud.learning.member.base.service.MemberService;
import com.yjs.cloud.learning.member.common.util.StringUtils;
import com.yjs.cloud.learning.member.common.web.GlobalException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 用户控制器
 *
 * @author Bill.lai
 * @since 2019-08-23
 */
@Api(tags = "会员信息")
@RestController
@AllArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表", httpMethod = "GET")
    @GetMapping("/list")
    public MemberGetPageResponse getList(MemberGetPageRequest memberGetPageRequest) {
        return memberService.findList(memberGetPageRequest);
    }

    @ApiOperation(value = "根据手机号码获取会员信息", notes = "根据手机号码获取会员信息", httpMethod = "GET")
    @GetMapping("/auth-api/by-mobile")
    public MemberResponse getByMobile(@RequestParam(value = "mobile") String mobile) {
        return memberService.getByMobile(mobile);
    }

    @ApiOperation(value = "修改头像", notes = "修改头像", httpMethod = "PUT")
    @PutMapping("/auth-api/update/avatar")
    public MemberResponse updateAvatar(@RequestBody MemberUpdateRequest memberUpdateRequest) {
        if (StringUtils.isEmpty(memberUpdateRequest.getAvatar())) {
            throw new GlobalException("avatar为必填项");
        }
        return memberService.updateMember(memberUpdateRequest);
    }

    @ApiOperation(value = "修改姓名", notes = "修改姓名", httpMethod = "PUT")
    @PutMapping("/auth-api/update/name")
    public MemberResponse updateName(@RequestBody MemberUpdateRequest memberUpdateRequest) {
        if (StringUtils.isEmpty(memberUpdateRequest.getName())) {
            throw new GlobalException("name为必填项");
        }
        return memberService.updateMember(memberUpdateRequest);
    }

    @ApiOperation(value = "修改手机号", notes = "修改手机号", httpMethod = "PUT")
    @PutMapping("/auth-api/update/mobile")
    public MemberResponse updateMobile(@RequestBody MemberUpdateRequest memberUpdateRequest) {
        if (StringUtils.isEmpty(memberUpdateRequest.getMobile())) {
            throw new GlobalException("mobile为必填项");
        }
        return memberService.updateMember(memberUpdateRequest);
    }

    @ApiOperation(value = "修改邮箱", notes = "修改邮箱", httpMethod = "PUT")
    @PutMapping("/auth-api/update/email")
    public MemberResponse updateEmail(@RequestBody MemberUpdateRequest memberUpdateRequest) {
        if (StringUtils.isEmpty(memberUpdateRequest.getEmail())) {
            throw new GlobalException("email为必填项");
        }
        return memberService.updateMember(memberUpdateRequest);
    }

    @ApiOperation(value = "修改密码", notes = "修改密码", httpMethod = "PUT")
    @PutMapping("/auth-api/update/password")
    public MemberResponse updatePassword(@RequestBody MemberUpdateRequest memberUpdateRequest) {
        if (StringUtils.isEmpty(memberUpdateRequest.getPassword())) {
            throw new GlobalException("password为必填项");
        }
        if (StringUtils.isEmpty(memberUpdateRequest.getOldPassword())) {
            throw new GlobalException("oldPassword为必填项");
        }
        return memberService.updateMember(memberUpdateRequest);
    }

    @ApiOperation(value = "邮箱注册用户", notes = "邮箱注册用户", httpMethod = "POST")
    @PostMapping("/public-api/register")
    public MemberResponse register(@RequestBody MemberCreateRequest memberCreateRequest) {
        if (StringUtils.isEmpty(memberCreateRequest.getEmail())) {
            throw new GlobalException("邮箱为必填项");
        }
        if (StringUtils.isEmpty(memberCreateRequest.getPassword())) {
            throw new GlobalException("密码为必填项");
        }
        if (StringUtils.isEmpty(memberCreateRequest.getConfirmPassword())) {
            throw new GlobalException("确认密码为必填项");
        }
        if (!memberCreateRequest.getConfirmPassword().equals(memberCreateRequest.getPassword())) {
            throw new GlobalException("两次密码不一致");
        }
        return memberService.create(memberCreateRequest);
    }

    @ApiOperation(value = "手机号注册会员", notes = "手机号注册会员", httpMethod = "POST")
    @PostMapping("/public-api/register/mobile")
    public MemberResponse registerMobile(@RequestBody MemberCreateMobileRequest memberCreateMobileRequest) {
        if (StringUtils.isEmpty(memberCreateMobileRequest.getMobile())) {
            throw new GlobalException("手机号码为必填项");
        }
        if (StringUtils.isEmpty(memberCreateMobileRequest.getAuthCode())) {
            throw new GlobalException("验证码为必填项");
        }
        return memberService.create(memberCreateMobileRequest);
    }

    @ApiOperation(value = "根据ids获取会员信息", notes = "根据ids获取会员信息", httpMethod = "GET")
    @GetMapping("/auth-api/by-ids")
    public List<MemberResponse> getByIds(MemberGetByIdsRequest memberGetByIdsRequest) {
        if (CollectionUtils.isEmpty(memberGetByIdsRequest.getIds())) {
            throw new GlobalException("ids为必填项");
        }
        return memberService.getByIds(memberGetByIdsRequest.getIds());
    }

}

