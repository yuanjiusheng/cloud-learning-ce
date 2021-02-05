package com.yjs.cloud.learning.comment.biz.sensitiveword.bean;

import lombok.Data;

import java.util.List;

/**
 * 敏感词标记
 *
 * @author Bill.lai
 * @since 2/3/21
 */
@Data
public class FlagIndex {

    /**
     * 标记结果
     */
    private boolean flag;

    /**
     * 标记索引
     */
    private List<Integer> index;

}
