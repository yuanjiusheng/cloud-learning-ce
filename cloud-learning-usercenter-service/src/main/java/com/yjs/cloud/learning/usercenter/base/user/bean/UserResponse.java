package com.yjs.cloud.learning.usercenter.base.user.bean;

import com.yjs.cloud.learning.usercenter.base.user.enums.UserStatus;
import com.yjs.cloud.learning.usercenter.common.response.BaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * User
 *
 * @author Andrew.xiao
 * @since 2019/6/6
 */
@ApiModel
@EqualsAndHashCode(callSuper = true)
@Data
public class UserResponse extends BaseResponse {

    @ApiModelProperty(value = "登陆账号")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "工号")
    private String code;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "状态")
    private UserStatus status;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "办公电话")
    private String telephone;

    @ApiModelProperty(value = "移动电话")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "生日")
    private LocalDate birthday;

    @ApiModelProperty(value = "身份证")
    private String idCard;

    @ApiModelProperty(value = "民族")
    private String nation;

    @ApiModelProperty(value = "籍贯")
    private String nativePlace;

    @ApiModelProperty(value = "户口地址")
    private String idCardAddress;

    @ApiModelProperty(value = "居住地")
    private String currentAddress;

    @ApiModelProperty(value = "婚姻状况")
    private String maritalStatus;

    @ApiModelProperty(value = "合同开始日期")
    private LocalDate contractStartDate;

    @ApiModelProperty(value = "合同结束日期")
    private LocalDate contractEndDate;

    @ApiModelProperty(value = "部门id")
    private String departmentId;

    @ApiModelProperty(value = "部门名称")
    private String departmentName;

}
