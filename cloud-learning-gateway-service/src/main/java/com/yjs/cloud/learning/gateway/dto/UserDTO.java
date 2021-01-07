package com.yjs.cloud.learning.gateway.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 用户DTO
 *
 * @author Andrew.xiao
 * @since 2020/7/31
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class UserDTO {
    @JsonIgnore
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private List<String> authorities;
    @JsonIgnore
    private String bearerToken;
}

