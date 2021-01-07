package com.yjs.cloud.learning.message.biz.privateletter.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjs.cloud.learning.message.biz.privateletter.bean.PrivateLetterResponse;
import com.yjs.cloud.learning.message.biz.privateletter.enums.PrivateLetterStatus;
import com.yjs.cloud.learning.message.common.entity.BaseEntity;
import com.yjs.cloud.learning.message.common.util.BeanCopierUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * 私人信件
 *
 * @author Bill.lai
 * @since 12/22/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName("t_private_letter")
public class PrivateLetter extends BaseEntity {

    /**
     * 发送者id
     */
    private Long senderId;

    /**
     * 接受者id
     */
    private Long receiverId;

    /**
     * 私信内容
     */
    private String content;

    /**
     * 阅读时间
     */
    private LocalDateTime readTime;

    /**
     * 是否已读
     */
    private Boolean isRead;

    /**
     * 状态
     */
    private PrivateLetterStatus status;

    public PrivateLetterResponse convert() {
        PrivateLetterResponse privateLetterResponse = new PrivateLetterResponse();
        BeanCopierUtils.copy(this, privateLetterResponse);
        return privateLetterResponse;
    }

}
