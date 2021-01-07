package com.yjs.cloud.learning.usercenter.base.post.entity;

import com.yjs.cloud.learning.usercenter.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 岗位实体
 *
 * @author bill.lai
 * @since 2019/6/12 11:33
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Post extends BaseEntity {

    /**
     *  编号
     */
    private String code;

    /**
     * 名称
     */
    private String name;

}
