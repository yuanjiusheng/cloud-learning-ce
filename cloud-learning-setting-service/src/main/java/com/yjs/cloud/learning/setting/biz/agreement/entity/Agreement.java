package com.yjs.cloud.learning.setting.biz.agreement.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjs.cloud.learning.setting.biz.agreement.bean.AgreementResponse;
import com.yjs.cloud.learning.setting.common.entity.BaseEntity;
import com.yjs.cloud.learning.setting.common.util.BeanCopierUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 协议文章
 *
 * @author Bill.lai
 * @since 12/29/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "t_agreement")
public class Agreement extends BaseEntity {

    /**
     * 名称
     */
    private String name;

    /**
     * 文章类型，自定义
     */
    private String type;

    /**
     * 协议内容
     */
    private String content;

    public AgreementResponse convert() {
        AgreementResponse response = new AgreementResponse();
        BeanCopierUtils.copy(this, response);
        return response;
    }

}
