package com.yjs.cloud.learning.member.biz.level.entity;

import com.yjs.cloud.learning.member.biz.level.bean.MemberLevelResponse;
import com.yjs.cloud.learning.member.common.entity.BaseEntity;
import com.yjs.cloud.learning.member.common.util.BeanCopierUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会员等级
 *
 * @author bill.lai
 * @since 2019/6/6
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MemberLevel extends BaseEntity {

    /**
     * 等级名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 条件
     */
    private Long conditions;

    public MemberLevelResponse convert() {
        MemberLevelResponse memberLevelResponse = new MemberLevelResponse();
        BeanCopierUtils.copy(this, memberLevelResponse);
        return memberLevelResponse;
    }
}
