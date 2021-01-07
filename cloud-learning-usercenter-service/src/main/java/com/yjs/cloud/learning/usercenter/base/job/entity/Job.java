package com.yjs.cloud.learning.usercenter.base.job.entity;

import com.yjs.cloud.learning.usercenter.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 职务实体
 *
 * @author bill.lai
 * @since 2019/6/12 11:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Job extends BaseEntity {

    /**
     * 编号
     */
    private String code;

    /**
     *  名称
     */
    private String name;

}
