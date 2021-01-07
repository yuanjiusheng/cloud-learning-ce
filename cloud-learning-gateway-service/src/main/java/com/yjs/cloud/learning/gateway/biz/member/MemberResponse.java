package com.yjs.cloud.learning.gateway.biz.member;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * 会员信息
 *
 * @author bill.lai
 * @since 2020/7/22
 */
@ApiModel
@Data
public class MemberResponse {

    @ApiModelProperty(value = "登陆账号")
    private String username;

    @ApiModelProperty(value = "工号")
    private String code;

    @ApiModelProperty(value = "姓名")
    private String name;

    @ApiModelProperty(value = "性别")
    private String gender;

    @ApiModelProperty(value = "办公电话")
    private String telephone;

    @ApiModelProperty(value = "移动电话")
    private String mobile;

    @ApiModelProperty(value = "邮箱")
    private String email;

    @ApiModelProperty(value = "生日")
    @JsonFormat(pattern="yyyy-MM-dd", timezone="GMT+8")
    private LocalDate birthday;

    @ApiModelProperty(value = "用户头像")
    private String avatar;

}
