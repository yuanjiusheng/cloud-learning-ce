package com.yjs.cloud.learning.usercenter.base.user.web;

import com.yjs.cloud.learning.usercenter.base.user.bean.*;
import com.yjs.cloud.learning.usercenter.base.user.entity.User;
import com.yjs.cloud.learning.usercenter.base.user.service.UserService;
import com.yjs.cloud.learning.usercenter.common.util.StringUtils;
import com.yjs.cloud.learning.usercenter.common.web.GlobalException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户控制器
 *
 * @author Bill.lai
 * @since 2019-08-23
 */
@Api(tags = "用户管理")
@RestController
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    @ApiOperation(value = "根据手机号码获取用户信息", notes = "根据手机号码获取用户信息", httpMethod = "GET")
    @GetMapping("/auth-api/by-mobile")
    public UserResponse getByMobile(@RequestParam(value = "mobile") String mobile) {
        return userService.getByMobile(mobile);
    }

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @GetMapping("/list")
    public UserGetListResponse getList(UserGetListRequest userGetListResponse) {
        return userService.findList(userGetListResponse);
    }

    @ApiOperation(value = "修改头像", notes = "修改头像", httpMethod = "PUT")
    @PutMapping("/auth-api/update/avatar")
    public UserResponse updateAvatar(@RequestBody UserUpdateRequest userUpdateRequest) {
        if (StringUtils.isEmpty(userUpdateRequest.getAvatar())) {
            throw new GlobalException("avatar为必填项");
        }
        return userService.updateUser(userUpdateRequest);
    }

    @ApiOperation(value = "修改姓名", notes = "修改姓名", httpMethod = "PUT")
    @PutMapping("/auth-api/update/name")
    public UserResponse updateName(@RequestBody UserUpdateRequest userUpdateRequest) {
        if (StringUtils.isEmpty(userUpdateRequest.getName())) {
            throw new GlobalException("name为必填项");
        }
        return userService.updateUser(userUpdateRequest);
    }

    @ApiOperation(value = "修改手机号", notes = "修改手机号", httpMethod = "PUT")
    @PutMapping("/auth-api/update/mobile")
    public UserResponse updateMobile(@RequestBody UserUpdateRequest userUpdateRequest) {
        if (StringUtils.isEmpty(userUpdateRequest.getMobile())) {
            throw new GlobalException("mobile为必填项");
        }
        return userService.updateUser(userUpdateRequest);
    }

    @ApiOperation(value = "修改邮箱", notes = "修改邮箱", httpMethod = "PUT")
    @PutMapping("/auth-api/update/email")
    public UserResponse updateEmail(@RequestBody UserUpdateRequest userUpdateRequest) {
        if (StringUtils.isEmpty(userUpdateRequest.getEmail())) {
            throw new GlobalException("email为必填项");
        }
        return userService.updateUser(userUpdateRequest);
    }

    @ApiOperation(value = "修改密码", notes = "修改密码", httpMethod = "PUT")
    @PutMapping("/auth-api/update/password")
    public UserResponse updatePassword(@RequestBody UserUpdateRequest userUpdateRequest) {
        if (StringUtils.isEmpty(userUpdateRequest.getPassword())) {
            throw new GlobalException("password为必填项");
        }
        if (StringUtils.isEmpty(userUpdateRequest.getOldPassword())) {
            throw new GlobalException("oldPassword为必填项");
        }
        return userService.updateUser(userUpdateRequest);
    }

    @ApiOperation(value = "根据ids获取会员信息", notes = "根据ids获取会员信息", httpMethod = "GET")
    @GetMapping("/public-api/by-ids")
    public List<UserResponse> getByIds(UserGetByIdsRequest userGetByIdsRequest) {
        if (CollectionUtils.isEmpty(userGetByIdsRequest.getIds())) {
            throw new GlobalException("ids为必填项");
        }
        return userService.getByIds(userGetByIdsRequest.getIds());
    }

    @ApiOperation(value = "获取会员信息", notes = "获取会员信息", httpMethod = "GET")
    @GetMapping("/auth-api/by-id")
    public UserResponse getById(UserGetRequest userGetRequest) {
        if (userGetRequest.getId() == null) {
            throw new GlobalException("id为必填项");
        }
        User user = userService.getById(userGetRequest.getId());
        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setAvatar(user.getAvatar());
        return response;
    }

    @ApiOperation(value = "获取会员信息", notes = "获取会员信息", httpMethod = "GET")
    @GetMapping("/auth-api/list")
    public UserGetListResponse getAuthList(UserGetListRequest userGetPageRequest) {
        UserGetListResponse pageResponse = userService.findList(userGetPageRequest);
        if (pageResponse.getList() != null) {
            List<UserResponse> list = new ArrayList<>();
            for (UserResponse user : pageResponse.getList()) {
                UserResponse response = new UserResponse();
                response.setId(user.getId());
                response.setName(user.getName());
                response.setAvatar(user.getAvatar());
                list.add(response);
            }
            pageResponse.setList(list);
        }
        return pageResponse;
    }

}

