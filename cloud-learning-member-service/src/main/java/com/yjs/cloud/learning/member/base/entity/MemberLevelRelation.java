package com.yjs.cloud.learning.member.base.entity;

import com.yjs.cloud.learning.member.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * member
 *
 * @author bill.lai
 * @since 2019/6/6
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class MemberLevelRelation extends BaseEntity {

    /**
     * 会员id
     */
    private Long memberId;

    /**
     * 密码
     */
    private Long levelId;

}
