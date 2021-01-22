package com.yjs.cloud.learning.auth.biz.role.entity;

import com.yjs.cloud.learning.auth.biz.role.bean.RoleResponse;
import com.yjs.cloud.learning.auth.common.entity.BaseEntity;
import com.yjs.cloud.learning.auth.common.util.BeanCopierUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 角色实体
 *
 * @author Andrew.xiao
 * @since 2019/6/12
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Role extends BaseEntity {

    /**
     * 角色名称
     */
    private String name;

    /**
     * code
     */
    private String code;

    /**
     * 备注
     */
    private String remark;

    public RoleResponse convert() {
        RoleResponse response = new RoleResponse();
        BeanCopierUtils.copy(this, response);
        return response;
    }

}
