package com.yjs.cloud.learning.setting.common.util;

import com.yjs.cloud.learning.setting.common.web.GlobalException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

/**
 * 集合工具
 * @author Bill.lai
 * @since 2019-07-12
 */
public class CollectorUtils {

    /**
     * 根据实体的某个属性将源数据列表分组成一个map
     * @param list 源数据列表
     * @param function lambda函数(类::get属性)
     * @param <T> 实体泛型
     * @param <K> 实体分组的属性（返回值的KEY）
     * @return 分组好的map
     */
    public static <T, K> Map<K, List<T>> groupingBy(List<T> list, Function<? super T, ? extends K> function){
        Map<K, List<T>> map = new HashMap<>(16);
        for (T item : list) {
            K key = function.apply(item);
            if (key == null) {
                continue;
            }
            map.computeIfAbsent(key, k -> new ArrayList<>());
            map.get(key).add(item);
        }
        return map;
    }

    /**
     * 根据实体的多个属性作为key，将源数据列表分组成一个map
     * @param list 源数据列表
     * @param functionList 多个lambda函数(类::get属性)，最终返回结果的key会是有改list的所有属性值拼接而成
     * @param <T> 实体泛型
     * @param <K> 实体分组的属性
     * @return 分组好的map
     */
    @SafeVarargs
    public static <T, K> Map<String, List<T>> groupingBy(List<T> list, Function<? super T, ? extends K>... functionList){
        if(functionList == null || functionList.length == 0){
            throw new GlobalException("参数不能为空");
        }
        Map<String, List<T>> map = new HashMap<>(16);
        for (T item : list) {
            StringBuilder key = new StringBuilder();
            for (Function<? super T, ? extends K> function : functionList) {
                K apply = function.apply(item);
                if (apply == null) {
                    key = null;
                    break;
                }
                key.append(apply).append("_");
            }
            //有一个函数属性为空的，跳过
            if (key == null || "".equals(key.toString())) {
                continue;
            }

            map.computeIfAbsent(key.toString(), k -> new ArrayList<>());
            map.get(key.toString()).add(item);
        }
        return map;
    }
}
