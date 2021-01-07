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

    @GetMapping(value = "/user/name")
    public User getByName(@RequestParam("name") String name){
        if(StringUtils.isEmpty(name)){
            throw new GlobalException("参数错误");
        }
        return userService.getByName(name);
    }

    /**
     * 根据部门获取用户
     * @param departmentId  部门id
     * @param page 分页信息
     * @param searchText 姓名搜索（模糊）
     * @return 用户列表
     */
    @GetMapping("/user/page/by-department-id")
    public IPage<User> page(Page<User> page, @RequestParam(value = "departmentId") Long departmentId,
                            @RequestParam(value = "searchText", required = false) String searchText){
        return userService.getByDepartmentId(departmentId, page, searchText);
    }

    /**
     * 根据部门获取用户
     * @param departmentIds  部门id
     * @param searchText 姓名搜索（模糊）
     * @return 用户列表
     */
    @GetMapping("/user/list/by-department-ids")
    public List<User> list(@RequestParam(value = "departmentIds") List<Long> departmentIds,
                           @RequestParam(value = "searchText", required = false) String searchText){
        return userService.list(departmentIds, searchText);
    }

    /**
     * 根据id获取用户详情，包括部门、岗位
     * @param id 用户id
     * @return 用户列表
     */
    @GetMapping("/user/detail/{id}")
    public User detail(@PathVariable(value = "id") Long id){
        return userService.detail(id);
    }

//    /**
//     * 根据手机号码获取用户详情，包括部门、岗位
//     * @param mobile 手机号码
//     * @return 用户列表
//     */
//    @GetMapping("/user/detail")
//    public User detailByMobile(@RequestParam(value = "mobile") String mobile){
//        return userService.getByMobile(mobile);
//    }

//    /**
//     * 根据id获取用户
//     * @param id 用户id
//     * @return 用户列表
//     */
//    @GetMapping("/user/{id}")
//    public User get(@PathVariable(value = "id") Long id){
//        return userService.getById(id);
//    }

    /**
     * 根据id获取用户
     * @param username 用户id
     * @return 用户列表
     */
    @GetMapping("/user/by-username")
    public User getByUsername(@RequestParam(value = "username") String username){
        return userService.getByUsername(username);
    }

    /**
     * 根据id获取用户
     * @param departmentId 用户id
     * @param userId 用户id
     * @param searchFullName 用户名称搜索关键字
     * @param searchName 搜索登录名与id
     * @return 用户列表
     */
    @GetMapping("/user-vo/list")
    public List<UserVo> getUserVoByDepartmentId(@RequestParam(value = "departmentId", required = false) Long departmentId,
                                                @RequestParam(value = "userId", required = false) Long userId,
                                                @RequestParam(value = "searchFullName", required = false) String searchFullName,
                                                @RequestParam(value = "searchName", required = false) String searchName){
        return userService.getUserVo(departmentId, userId, searchFullName, searchName);
    }

    /**
     * 根据部门获取用户
     * @param departmentId  部门id
     * @param fetchAll fetchAll 是否获取所有子部门用户（包括孙子部门用户...）
     * @return 用户列表
     */
    @GetMapping("/user/list/by-department-id")
    public List<User> list(@RequestParam(value = "departmentId") Long departmentId,
                            @RequestParam(value = "fetchAll", required = false, defaultValue = "false") Boolean fetchAll){
        return userService.getByDepartmentId(departmentId, fetchAll);
    }

    /**
     * 根据部门，关键字（手机，工号，姓名）获取用户信息
     * @param departmentId 部门
     * @param searchText 关键字
     * @return 用户列表
     */
    @GetMapping("/{departmentId}/user/by-keyword")
    public List<User> getUserByDepartmentIdAndKeyword(@PathVariable(value = "departmentId") Long departmentId,
                                                               @RequestParam(value = "searchText", required = false)String searchText) {
        return userService.getUserByDepartmentIdAndKeyword(departmentId, searchText);
    }

    @ApiOperation(value = "获取用户列表", notes = "获取用户列表")
    @GetMapping("/list")
    public UserGetListResponse getList(UserGetListRequest userGetListResponse) {
        return userService.findList(userGetListResponse);
    }

}

