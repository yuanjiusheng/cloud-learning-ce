package com.yjs.cloud.learning.auth.biz.authority.entity;

import com.yjs.cloud.learning.auth.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 权限实体
 *
 * @author Andrew.xiao
 * @since 2019/6/6
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Authority extends BaseEntity {

    /**
     * 权限名称
     */
    private String name;

    /**
     * 别名
     */
    private String alias;

    /**
     * 上级权限
     */
    private Long pid;
}
