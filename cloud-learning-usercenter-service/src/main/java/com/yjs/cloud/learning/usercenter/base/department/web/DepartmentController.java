package com.yjs.cloud.learning.usercenter.base.department.web;

import com.yjs.cloud.learning.usercenter.base.department.bean.*;
import com.yjs.cloud.learning.usercenter.base.department.entity.Department;
import com.yjs.cloud.learning.usercenter.base.department.service.DepartmentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
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

    @ApiOperation(value = "获取部门列表", notes = "获取部门列表", httpMethod = "GET")
    @GetMapping("/department/list")
    public DepartmentGetListResponse getList(DepartmentGetListRequest departmentGetListRequest){
        return departmentService.findList(departmentGetListRequest);
    }

    @ApiOperation(value = "获取部门列表", notes = "获取部门列表", httpMethod = "GET")
    @GetMapping("/public-api/department/by-ids")
    public List<DepartmentResponse> getByIds(DepartmentGetByIdsRequest departmentGetByIdsRequest){
        return departmentService.getByIds(departmentGetByIdsRequest);
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
