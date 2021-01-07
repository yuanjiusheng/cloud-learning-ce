package com.yjs.cloud.learning.member.base.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.yjs.cloud.learning.member.base.enums.MemberStatus;
import com.yjs.cloud.learning.member.common.response.BaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDate;

/**
 * 会员返回对象
 *
 * @author Bill.lai
 * @since 2019/6/6
 */
@ApiModel
@EqualsAndHashCode(callSuper = true)
@Data
public class MemberResponse extends BaseResponse {

    @ApiModelProperty(value = "登陆账号")
    private String username;

    @Ignore
    @ApiModelProperty(value = "密码", hidden = true)
    private String password;

    @ApiModelProperty(value = "工号")
    private String code;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "状态")
    private MemberStatus status;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "办公电话")
    private String telephone;

    @ApiModelProperty(value = "移动电话")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd", timezone="GMT+8")
    @ApiModelProperty(value = "生日")
    private LocalDate birthday;

    @ApiModelProperty(value = "头像")
    private String avatar;
}
