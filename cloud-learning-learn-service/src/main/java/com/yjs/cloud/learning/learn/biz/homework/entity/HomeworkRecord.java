package com.yjs.cloud.learning.learn.biz.homework.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjs.cloud.learning.learn.biz.homework.bean.HomeworkRecordResponse;
import com.yjs.cloud.learning.learn.biz.homework.enums.HomeworkRecordStatus;
import com.yjs.cloud.learning.learn.common.entity.BaseEntity;
import com.yjs.cloud.learning.learn.common.util.BeanCopierUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 作业记录
 *
 * @author Bill.lai
 * @since 2021/4/28
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "t_homework_record")
public class HomeworkRecord extends BaseEntity {

    /**
     * 课程Id
     */
    private Long lessonId;

    /**
     * 会员Id
     */
    private Long memberId;

    /**
     * 提交内容路径
     */
    private String url;

    /**
     * 报名id
     */
    private Long signUpId;

    /**
     * 状态
     */
    private HomeworkRecordStatus status;

    public HomeworkRecordResponse convert() {
        HomeworkRecordResponse response = new HomeworkRecordResponse();
        BeanCopierUtils.copy(this, response);
        return response;
    }

}
