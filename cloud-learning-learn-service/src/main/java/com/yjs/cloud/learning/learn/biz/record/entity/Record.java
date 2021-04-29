package com.yjs.cloud.learning.learn.biz.record.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjs.cloud.learning.learn.biz.record.bean.RecordResponse;
import com.yjs.cloud.learning.learn.biz.record.enums.RecordStatus;
import com.yjs.cloud.learning.learn.common.entity.BaseEntity;
import com.yjs.cloud.learning.learn.common.util.BeanCopierUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 学习记录
 *
 * @author Bill.lai
 * @since 12/14/20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_record")
public class Record extends BaseEntity {

    /**
     * 课程id
     */
    private Long lessonId;

    /**
     * 课程章节id
     */
    private Long lessonChapterSectionId;

    /**
     * 会员ID
     */
    private Long memberId;

    /**
     * 学习时长（秒）
     */
    private Long learnTime;

    /**
     * 报名ID
     */
    private Long signUpId;

    /**
     * 最大的学习进度时间
     */
    private Long maxProgressTime;

    /**
     * 状态
     */
    private RecordStatus status;

    public RecordResponse convert() {
        RecordResponse response = new RecordResponse();
        BeanCopierUtils.copy(this, response);
        return response;
    }
}
