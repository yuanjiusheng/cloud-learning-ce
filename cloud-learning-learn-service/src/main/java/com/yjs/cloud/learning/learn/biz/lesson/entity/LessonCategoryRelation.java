package com.yjs.cloud.learning.learn.biz.lesson.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.yjs.cloud.learning.learn.common.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 课堂类目关系
 * </p>
 *
 * @author bill.lai
 * @since 2020-07-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("t_lesson_category_relation")
public class LessonCategoryRelation extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 课堂id
     */
    private Long lessonId;

    /**
     * 类目id
     */
    private Long categoryId;

}
