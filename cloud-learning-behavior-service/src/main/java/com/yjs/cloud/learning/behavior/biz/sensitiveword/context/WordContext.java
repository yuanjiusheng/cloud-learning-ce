package com.yjs.cloud.learning.behavior.biz.sensitiveword.context;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.yjs.cloud.learning.behavior.biz.sensitiveword.entity.SensitiveWord;
import com.yjs.cloud.learning.behavior.biz.sensitiveword.enums.EndType;
import com.yjs.cloud.learning.behavior.biz.sensitiveword.enums.WordType;
import com.yjs.cloud.learning.behavior.biz.sensitiveword.mapper.SensitiveWordMapper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 词库上下文环境
 * 初始化敏感词库，将敏感词加入到HashMap中，构建DFA算法模型
 *
 * @author Bill.lai
 * @since 2/3/21
 */
@AllArgsConstructor
@Slf4j
@Component
@SuppressWarnings({"rawtypes", "unchecked"})
public class WordContext implements ApplicationRunner {

    private final SensitiveWordMapper sensitiveWordMapper;

    /**
     * 敏感词字典
     */
    @Getter
    private final Map wordMap = new HashMap(1024);

    /**
     * 初始化
     */
    @Override
    public void run(ApplicationArguments args) {
        try {
            // 将敏感词库加入到HashMap中
            LambdaQueryWrapper<SensitiveWord> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            List<SensitiveWord> sensitiveWordList = sensitiveWordMapper.selectList(lambdaQueryWrapper);
            if (sensitiveWordList.size() > 0) {
                List<String> words = new ArrayList<>();
                for (SensitiveWord sensitiveWord : sensitiveWordList) {
                    words.add(sensitiveWord.getName());
                }
                addWord(words, WordType.BLACK);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：<br>
     * 中 = { isEnd = 0 国 = {<br>
     * isEnd = 1 人 = {isEnd = 0 民 = {isEnd = 1} } 男 = { isEnd = 0 人 = { isEnd = 1 }
     * } } } 五 = { isEnd = 0 星 = { isEnd = 0 红 = { isEnd = 0 旗 = { isEnd = 1 } } } }
     */
    public void addWord(Iterable<String> wordList, WordType wordType) {
        Map nowMap;
        Map<String, String> newWorMap;
        // 迭代keyWordSet
        for (String key : wordList) {
            nowMap = wordMap;
            for (int i = 0; i < key.length(); i++) {
                // 转换成char型
                char keyChar = key.charAt(i);
                // 获取
                Object wordMap = nowMap.get(keyChar);
                // 如果存在该key，直接赋值
                if (wordMap != null) {
                    nowMap = (Map) wordMap;
                } else {
                    // 不存在则构建一个map，同时将isEnd设置为0，因为他不是最后一个
                    newWorMap = new HashMap<>(4);
                    // 不是最后一个
                    newWorMap.put("isEnd", String.valueOf(EndType.HAS_NEXT.ordinal()));
                    nowMap.put(keyChar, newWorMap);
                    nowMap = newWorMap;
                }

                if (i == key.length() - 1) {
                    // 最后一个
                    nowMap.put("isEnd", String.valueOf(EndType.IS_END.ordinal()));
                    nowMap.put("isWhiteWord", String.valueOf(wordType.ordinal()));
                }
            }
        }
    }

    /**
     * 在线删除敏感词
     *
     * @param wordList 敏感词列表
     * @param wordType 黑名单 BLACk，白名单WHITE
     */
    public void removeWord(Iterable<String> wordList, WordType wordType) {
        Map nowMap;
        for (String key : wordList) {
            List<Map> cacheList = new ArrayList<>();
            nowMap = wordMap;
            for (int i = 0; i < key.length(); i++) {
                char keyChar = key.charAt(i);

                Object map = nowMap.get(keyChar);
                if (map != null) {
                    nowMap = (Map) map;
                    cacheList.add(nowMap);
                } else {
                    throw new RuntimeException("操作失败，黑白名单都没有该词汇[" + key + "]");
                }

                if (i == key.length() - 1) {
                    char[] keys = key.toCharArray();
                    boolean cleanable = false;
                    char lastChar = 0;
                    for (int j = cacheList.size() - 1; j >= 0; j--) {
                        Map cacheMap = cacheList.get(j);
                        if (j == cacheList.size() - 1) {
                            if (String.valueOf(WordType.BLACK.ordinal()).equals(cacheMap.get("isWhiteWord"))) {
                                if (wordType == WordType.WHITE) {
                                    throw new RuntimeException("操作失败，白名单没有该词汇[" + key + "]");
                                }
                            }
                            if (String.valueOf(WordType.WHITE.ordinal()).equals(cacheMap.get("isWhiteWord"))) {
                                if (wordType == WordType.BLACK) {
                                    throw new RuntimeException("操作失败，黑名单没有该词汇[" + key + "]");
                                }
                            }
                            cacheMap.remove("isWhiteWord");
                            cacheMap.remove("isEnd");
                            if (cacheMap.size() == 0) {
                                cleanable = true;
                                continue;
                            }
                        }
                        if (cleanable) {
                            Object isEnd = cacheMap.get("isEnd");
                            if (String.valueOf(EndType.IS_END.ordinal()).equals(isEnd)) {
                                cleanable = false;
                            }
                            cacheMap.remove(lastChar);
                        }
                        lastChar = keys[j];
                    }

                    if (cleanable) {
                        wordMap.remove(lastChar);
                    }
                }
            }
        }
    }

}
