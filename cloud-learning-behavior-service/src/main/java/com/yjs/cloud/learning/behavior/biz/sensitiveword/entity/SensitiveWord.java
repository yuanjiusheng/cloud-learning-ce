package com.yjs.cloud.learning.behavior.biz.sensitiveword.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjs.cloud.learning.behavior.biz.sensitiveword.bean.SensitiveWordResponse;
import com.yjs.cloud.learning.behavior.common.entity.BaseEntity;
import com.yjs.cloud.learning.behavior.common.util.BeanCopierUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 敏感词
 *
 * @author Bill.lai
 * @since 2/5/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "t_sensitive_word")
public class SensitiveWord extends BaseEntity {

    private String name;

    public SensitiveWordResponse convert() {
        SensitiveWordResponse sensitiveWordResponse = new SensitiveWordResponse();
        BeanCopierUtils.copy(this, sensitiveWordResponse);
        return sensitiveWordResponse;
    }
}
