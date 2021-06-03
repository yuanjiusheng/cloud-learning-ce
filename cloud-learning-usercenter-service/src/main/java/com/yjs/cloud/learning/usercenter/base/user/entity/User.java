package com.yjs.cloud.learning.usercenter.base.user.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yjs.cloud.learning.usercenter.base.user.bean.UserResponse;
import com.yjs.cloud.learning.usercenter.base.user.enums.UserStatus;
import com.yjs.cloud.learning.usercenter.common.entity.BaseEntity;
import com.yjs.cloud.learning.usercenter.common.util.BeanCopierUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * User
 *
 * @author Andrew.xiao
 * @since 2019/6/6
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class User extends BaseEntity {

    /**
     * 登陆账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 工号
     */
    private String code;

    /**
     * 姓名
     */
    private String name;

    /**
     * 状态
     */
    private UserStatus status;

    /**
     * 性别
     */
    private String gender;

    /**
     * 办公电话
     */
    private String telephone;

    /**
     * 移动电话
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 生日
     */
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private LocalDate birthday;

    /**
     * 身份证
     */
    private String idCard;

    /**
     * 民族
     */
    private String nation;

    /**
     * 籍贯
     */
    private String nativePlace;

    /**
     * 户口地址
     */
    private String idCardAddress;

    /**
     * 居住地
     */
    private String currentAddress;

    /**
     * 婚姻状况
     */
    private String maritalStatus;

    /**
     * 合同开始日期
     */
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private LocalDate contractStartDate;

    /**
     * 合同结束日期
     */
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private LocalDate contractEndDate;

    /**
     * 头像
     */
    private String avatar;

    public UserResponse convert() {
        UserResponse userResponse = new UserResponse();
        BeanCopierUtils.copy(this, userResponse);
        return userResponse;
    }
}
