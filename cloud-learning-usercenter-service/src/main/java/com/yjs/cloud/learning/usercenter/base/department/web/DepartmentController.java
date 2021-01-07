package com.yjs.cloud.learning.usercenter.base.department.web;

import com.yjs.cloud.learning.usercenter.base.department.bean.DepartmentGetListRequest;
import com.yjs.cloud.learning.usercenter.base.department.bean.DepartmentGetListResponse;
import com.yjs.cloud.learning.usercenter.base.department.bean.DepartmentParam;
import com.yjs.cloud.learning.usercenter.base.department.entity.Department;
import com.yjs.cloud.learning.usercenter.base.department.service.DepartmentService;
import com.yjs.cloud.learning.usercenter.common.util.StringUtils;
import com.yjs.cloud.learning.usercenter.common.web.GlobalException;
import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门控制器
 *
 * @author Bill.lai
 * @since 2019-08-23
 */
@Api(tags = "部门管理")
@RestController
@AllArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    /**
     * 获取部门
     * @param departmentGetListRequest 参数
     * @return 部门列表
     */
    @GetMapping("/department/list")
    public DepartmentGetListResponse getList(DepartmentGetListRequest departmentGetListRequest){
        return departmentService.findList(departmentGetListRequest);
    }

    /**
     * 根据名称获取部门
     * @param name 部门
     * @return 部门信息
     */
    @GetMapping("/department/by-name")
    public Department getByName(@RequestParam(value = "name") String name){
        if(StringUtils.isEmpty(name)){
            throw new GlobalException("参数错误");
        }
        return departmentService.getByName(name);
    }

    /**
     * 根据名称列表获取部门信息
     * @param nameList 部门名称列表
     * @return 部门信息列表
     */
    @PostMapping("/department/by-name-list")
    public List<Department> getByName(@RequestBody List<String> nameList){
        if(CollectionUtils.isEmpty(nameList)){
            throw new GlobalException("参数错误");
        }
        return departmentService.getByNameList(nameList);
    }

    /**
     * 获取用户直属部门的子部门
     * @param userDepartmentId 用户直属部门id
     * @param id 部门id
     * @return 部门列表
     */
    @GetMapping("/department/list/by-user-dept-id")
    public List<Department> getByUserDepartmentIdAndId(@RequestParam(value = "userDepartmentId") Long userDepartmentId,
                                                       @RequestParam(value = "id") Long id){
        return departmentService.getByUserDepartmentIdAndId(userDepartmentId, id);
    }

    /**
     * 获取用户的部门
     * @param userId 用户id
     * @return 部门
     */
    @GetMapping("/department/by-user-id")
    public Department getByUserId(@RequestParam(value = "userId") Long userId){
        return departmentService.getByUserId(userId);
    }

//    /**
//     * 获取部门列表
//     * @return 部门列表
//     */
//    @GetMapping("/department/list")
//    public List<Department> list(){
//        return departmentService.list();
//    }

//    /**
//     * 创建部门
//     * @param department 部门
//     * @return 创建后的部门
//     */
//    @PostMapping("/departments")
//    @ResponseStatus(HttpStatus.CREATED)
//    public Department insert(@RequestBody Department department) {
//        if(department.getCode() == null){
////            department.setCode(RandomUtil.get(6));
//        }
//        departmentService.save(department);
//        return department;
//    }
//
//    /**
//     * 获取单个部门
//     * @param id 部门 id
//     * @return 部门详情
//     */
//    @GetMapping(value = "/departments/{id}")
//    public Department getDepartment(@PathVariable Long id){
//        return departmentService.getById(id);
//    }
//
//    /**
//     * 修改部门全部信息
//     * @param department 部门
//     * @return 修改后的部门
//     */
//    @PutMapping("/departments")
//    public Department updateDepartment(@RequestBody Department department) {
//        departmentService.updateById(department);
//        return department;
//    }
//
//    /**
//     * 删除部门
//     * @param id  部门id
//     */
//    @DeleteMapping("/departments/{id}")
//    @ResponseStatus(HttpStatus.NO_CONTENT)
//    public void deleteDepartment(@PathVariable Long id) {
//        departmentService.removeById(id);
//    }

    @PostMapping("/departments/user-number")
    public List<Department> getUserNumber(@RequestBody DepartmentParam departmentParam) {
        return departmentService.getUserNumber(departmentParam.getDepartmentIdList(), departmentParam.getJoinDate());
    }

//    /**
//     * 获取子部门
//     * @param departmentParam 部门id列表，是否获取所有子节点（包括孙子节点...）
//     * @return 部门列表
//     */
//    @PostMapping("/department/sub-departments")
//    public List<Department> getSubDepartment(@RequestBody DepartmentParam departmentParam){
//        return departmentService.list(departmentParam.getDepartmentIdList(), departmentParam.getFetchAll());
//    }

}
