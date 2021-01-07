package com.yjs.cloud.learning.member.base.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yjs.cloud.learning.member.base.bean.MemberResponse;
import com.yjs.cloud.learning.member.base.enums.MemberStatus;
import com.yjs.cloud.learning.member.common.entity.BaseEntity;
import com.yjs.cloud.learning.member.common.util.BeanCopierUtils;
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
public class Member extends BaseEntity {

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
    private MemberStatus status;

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
     * 头像
     */
    private String avatar;

    public MemberResponse convert() {
        MemberResponse memberResponse = new MemberResponse();
        BeanCopierUtils.copy(this, memberResponse);
        return memberResponse;
    }
}
