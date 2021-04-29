package com.yjs.cloud.learning.learn.biz.signup.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjs.cloud.learning.learn.biz.signup.bean.SignUpResponse;
import com.yjs.cloud.learning.learn.biz.signup.enums.SignUpStatus;
import com.yjs.cloud.learning.learn.common.entity.BaseEntity;
import com.yjs.cloud.learning.learn.common.util.BeanCopierUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;

/**
 * <p>
 * 报名
 * </p>
 *
 * @author bill.lai
 * @since 2020-08-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_sign_up")
public class SignUp extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 课程id
     */
    private Long lessonId;

    /**
     * 会员ID
     */
    private Long memberId;

    /**
     * 报名状态
     */
    private SignUpStatus status;

    /**
     * 完成时间
     */
    private LocalDateTime completedTime;

    public SignUpResponse convert() {
        SignUpResponse response = new SignUpResponse();
        BeanCopierUtils.copy(this, response);
        return response;
    }
}
