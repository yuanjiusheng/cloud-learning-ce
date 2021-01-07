package com.yjs.cloud.learning.auth.biz.jwt.service;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import org.springframework.stereotype.Service;

/**
 * 动态密码存储服务Impl
 *
 * @author Andrew.xiao
 * @since 2020/7/22
 */
@Service
public class AuthCodeStoreServiceImpl implements AuthCodeStoreService {

    private final TimedCache<String, String> timedCache = CacheUtil.newTimedCache(1000 * 60 * 5);

    @Override
    public void save(String mobilePhone, String authCode) {
        timedCache.put(mobilePhone, authCode);
    }

    @Override
    public String get(String mobilePhone) {
        return timedCache.get(mobilePhone);
    }
}
