package com.yjs.cloud.learning.behavior.common.util;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

/**
 * json工具
 *
 * @author Bill.lai
 * @since 2019-12-30
 */
public class JsonUtils {

    public static String toJsonString(Object map){
        return JSONObject.toJSONString(map, SerializerFeature.WriteMapNullValue);
    }

}
