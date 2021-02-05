package com.yjs.cloud.learning.comment.biz.sensitiveword.service;

import com.yjs.cloud.learning.comment.biz.sensitiveword.bean.*;
import com.yjs.cloud.learning.comment.biz.sensitiveword.entity.SensitiveWord;
import com.yjs.cloud.learning.comment.common.service.IBaseService;

import java.util.List;

/**
 * 敏感词服务
 *
 * @author Bill.lai
 * @since 2/3/21
 */
public interface WordService extends IBaseService<SensitiveWord> {

    /**
     * 替换敏感词
     * @param text 输入文本
     * @return 替换后文本
     */
    String replace(String text);

    /**
     * 替换敏感词
     * @param text   输入文本
     * @param symbol 替换符号
     * @return 替换后文本
     */
    String replace(String text, char symbol);

    /**
     * 替换敏感词
     * @param text   输入文本
     * @param skip   文本距离
     * @param symbol 替换符号
     * @return 替换后文本
     */
    String replace(String text, int skip, char symbol);

    /**
     * 是否包含敏感词
     * @param text 输入文本
     * @return true=包含，false=不包含
     */
    boolean include(String text);

    /**
     * 是否包含敏感词
     * @param text 输入文本
     * @param skip 文本距离
     * @return true=包含，false=不包含
     */
    boolean include(String text, int skip);

    /**
     * 获取敏感词数量
     * @param text 输入文本
     * @return 敏感词数量
     */
    int wordCount(String text);

    /**
     * 获取敏感词数量
     * @param text 输入文本
     * @param skip 文本距离
     * @return 敏感词数量
     */
    int wordCount(String text, int skip);

    /**
     * 获取敏感词列表
     * @param text 输入文本
     * @return 敏感词列表
     */
    List<String> wordList(String text);

    /**
     * 获取敏感词列表
     * @param text 输入文本
     * @param skip 文本距离
     * @return 敏感词列表
     */
    List<String> wordList(String text, int skip);

    /**
     * 获取标记索引
     * @param charset  输入文本
     * @param begin    检测起始
     * @param skip 文本距离
     * @return  标记索引
     */
    FlagIndex getFlagIndex(char[] charset, int begin, int skip);

    /**
     * 敏感词列表
     * @param wordGetListRequest 请求参数
     * @return 敏感词列表
     */
    WordGetListResponse list(WordGetListRequest wordGetListRequest);

    /**
     * 新增敏感词
     * @param wordCreateRequest 请求参数
     */
    void create(WordCreateRequest wordCreateRequest);

    /**
     * 修改敏感词
     * @param wordUpdateRequest 请求参数
     */
    void update(WordUpdateRequest wordUpdateRequest);

    /**
     * 删除敏感词
     * @param wordDeleteRequest 请求参数
     */
    void delete(WordDeleteRequest wordDeleteRequest);
}
