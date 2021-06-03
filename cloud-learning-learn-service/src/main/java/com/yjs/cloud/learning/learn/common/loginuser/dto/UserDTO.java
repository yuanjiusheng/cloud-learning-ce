package com.yjs.cloud.learning.learn.common.loginuser.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用户信息
 *
 * @author Andrew.xiao
 * @since 2020/7/17
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDTO {
    private Long id;
    private String username;
    private String password;
    private Long departmentId;
    private List<String> authorities;
    @JsonIgnore
    private String bearerToken;
}
