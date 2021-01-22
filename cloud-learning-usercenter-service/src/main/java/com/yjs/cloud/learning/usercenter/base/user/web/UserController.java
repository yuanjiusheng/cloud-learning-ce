package com.yjs.cloud.learning.usercenter.base.user.web;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserGetListRequest;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserGetListResponse;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserResponse;
import com.yjs.cloud.learning.usercenter.base.user.entity.User;
import com.yjs.cloud.learning.usercenter.base.user.entity.UserVo;
import com.yjs.cloud.learning.usercenter.base.user.service.UserService;
import com.yjs.cloud.learning.usercenter.common.util.StringUtils;
import com.yjs.cloud.learning.usercenter.common.web.GlobalException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

}

