package com.yjs.cloud.learning.comment.biz.sensitiveword.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yjs.cloud.learning.comment.biz.sensitiveword.bean.*;
import com.yjs.cloud.learning.comment.biz.sensitiveword.context.WordContext;
import com.yjs.cloud.learning.comment.biz.sensitiveword.entity.SensitiveWord;
import com.yjs.cloud.learning.comment.biz.sensitiveword.enums.WordType;
import com.yjs.cloud.learning.comment.biz.sensitiveword.mapper.SensitiveWordMapper;
import com.yjs.cloud.learning.comment.common.service.BaseServiceImpl;
import com.yjs.cloud.learning.comment.common.util.StringUtils;
import com.yjs.cloud.learning.comment.common.web.GlobalException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 敏感词服务
 *
 * @author Bill.lai
 * @since 2/3/21
 */
@AllArgsConstructor
@Service
public class WordServiceImpl extends BaseServiceImpl<SensitiveWordMapper, SensitiveWord> implements WordService {

    private final WordContext wordContext;

    /**
     * 替换敏感词
     * @param text 输入文本
     * @return 替换后文本
     */
    @Override
    public String replace(final String text) {
        return replace(text, 0, '*');
    }

    /**
     * 替换敏感词
     * @param text   输入文本
     * @param symbol 替换符号
     * @return 替换后文本
     */
    @Override
    public String replace(final String text, final char symbol) {
        return replace(text, 0, symbol);
    }

    /**
     * 替换敏感词
     * @param text   输入文本
     * @param skip   文本距离
     * @param symbol 替换符号
     * @return 替换后文本
     */
    @Override
    public String replace(final String text, final int skip, final char symbol) {
        char[] charset = text.toCharArray();
        for (int i = 0; i < charset.length; i++) {
            FlagIndex fi = getFlagIndex(charset, i, skip);
            if (fi.isFlag()) {
                for (int j : fi.getIndex()) {
                    charset[j] = symbol;
                }
            }
        }
        return new String(charset);
    }

    /**
     * 是否包含敏感词
     * @param text 输入文本
     * @return true=包含，false=不包含
     */
    @Override
    public boolean include(final String text) {
        return include(text, 0);
    }

    /**
     * 是否包含敏感词
     * @param text 输入文本
     * @param skip 文本距离
     * @return true=包含，false=不包含
     */
    @Override
    public boolean include(final String text, final int skip) {
        boolean flag = false;
        char[] charset = text.toCharArray();
        for (int i = 0; i < charset.length; i++) {
            flag = getFlagIndex(charset, i, skip).isFlag();
            if (flag) {
                break;
            }
        }
        return flag;
    }

    /**
     * 获取敏感词数量
     * @param text 输入文本
     * @return 敏感词数量
     */
    @Override
    public int wordCount(final String text) {
        return wordCount(text, 0);
    }

    /**
     * 获取敏感词数量
     * @param text 输入文本
     * @param skip 文本距离
     * @return 敏感词数量
     */
    @Override
    public int wordCount(final String text, final int skip) {
        int count = 0;
        char[] charset = text.toCharArray();
        for (int i = 0; i < charset.length; i++) {
            FlagIndex fi = getFlagIndex(charset, i, skip);
            if (fi.isFlag()) {
                count++;
            }
        }
        return count;
    }

    /**
     * 获取敏感词列表
     * @param text 输入文本
     * @return 敏感词列表
     */
    @Override
    public List<String> wordList(final String text) {
        return wordList(text, 0);
    }

    /**
     * 获取敏感词列表
     * @param text 输入文本
     * @param skip 文本距离
     * @return 敏感词列表
     */
    @Override
    public List<String> wordList(final String text, final int skip) {
        List<String> sensitives = new ArrayList<>();
        char[] charset = text.toCharArray();
        for (int i = 0; i < charset.length; i++) {
            FlagIndex fi = getFlagIndex(charset, i, skip);
            if (fi.isFlag()) {
                StringBuilder builder = new StringBuilder();
                for (int j : fi.getIndex()) {
                    char word = text.charAt(j);
                    builder.append(word);
                }
                sensitives.add(builder.toString());
            }
        }
        return sensitives;
    }

    /**
     * 获取标记索引
     * @param charset  输入文本
     * @param begin    检测起始
     * @param skip 文本距离
     * @return  标记索引
     */
    @Override
    public FlagIndex getFlagIndex(final char[] charset, final int begin, final int skip) {
        FlagIndex fi = new FlagIndex();
        Map current = wordContext.getWordMap();
        boolean flag = false;
        int count = 0;
        List<Integer> index = new ArrayList<>();
        for (int i = begin; i < charset.length; i++) {
            char word = charset[i];
            Map mapTree = (Map) current.get(word);
            if (count > skip || (i == begin && Objects.isNull(mapTree))) {
                break;
            }
            if (Objects.nonNull(mapTree)) {
                current = mapTree;
                count = 0;
                index.add(i);
            } else {
                count++;
                if (flag && count > skip) {
                    break;
                }
            }
            if ("1".equals(current.get("isEnd"))) {
                flag = true;
            }
            if ("1".equals(current.get("isWhiteWord"))) {
                flag = false;
                break;
            }
        }

        fi.setFlag(flag);
        fi.setIndex(index);

        return fi;
    }

    /**
     * 敏感词列表
     * @param wordGetListRequest 请求参数
     * @return 敏感词列表
     */
    @Override
    public WordGetListResponse list(WordGetListRequest wordGetListRequest) {
        WordGetListResponse wordGetListResponse = new WordGetListResponse();
        Page<SensitiveWord> page = new Page<>(wordGetListRequest.getCurrent(), wordGetListRequest.getSize());
        LambdaQueryWrapper<SensitiveWord> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(wordGetListRequest.getKeyword())) {
            lambdaQueryWrapper.like(SensitiveWord::getName, wordGetListRequest.getKeyword());
        }
        page = baseMapper.selectPage(page, lambdaQueryWrapper);
        List<SensitiveWord> list = page.getRecords();
        List<SensitiveWordResponse> result = new ArrayList<>();
        for (SensitiveWord sensitiveWord : list) {
            result.add(sensitiveWord.convert());
        }
        wordGetListResponse.setList(result);
        wordGetListResponse.setTotal(page.getTotal());
        wordGetListResponse.setCurrent(page.getCurrent());
        wordGetListResponse.setSize(page.getSize());
        wordGetListResponse.setSize(page.getPages());
        return wordGetListResponse;
    }

    /**
     * 新增敏感词
     * @param wordCreateRequest 请求参数
     */
    @Override
    public void create(WordCreateRequest wordCreateRequest) {
        SensitiveWord sensitiveWord = new SensitiveWord();
        sensitiveWord.setName(wordCreateRequest.getName());
        save(sensitiveWord);
        List<String> wordList = new ArrayList<>();
        wordList.add(wordCreateRequest.getName());
        wordContext.addWord(wordList, WordType.BLACK);
    }

    /**
     * 修改敏感词
     * @param wordUpdateRequest 请求参数
     */
    @Override
    public void update(WordUpdateRequest wordUpdateRequest) {
        SensitiveWord sensitiveWord = getById(wordUpdateRequest.getId());
        if (sensitiveWord == null) {
            throw new GlobalException("敏感词不存在");
        }
        List<String> wordList = new ArrayList<>();
        wordList.add(sensitiveWord.getName());
        wordContext.removeWord(wordList, WordType.BLACK);
        sensitiveWord.setName(wordUpdateRequest.getName());
        updateById(sensitiveWord);
        wordList = new ArrayList<>();
        wordList.add(wordUpdateRequest.getName());
        wordContext.addWord(wordList, WordType.BLACK);
    }

    /**
     * 删除敏感词
     * @param wordDeleteRequest 请求参数
     */
    @Override
    public void delete(WordDeleteRequest wordDeleteRequest) {
        SensitiveWord sensitiveWord = getById(wordDeleteRequest.getId());
        if (sensitiveWord == null) {
            throw new GlobalException("敏感词不存在");
        }
        removeById(wordDeleteRequest.getId());
        List<String> wordList = new ArrayList<>();
        wordList.add(sensitiveWord.getName());
        wordContext.removeWord(wordList, WordType.BLACK);
    }
}
