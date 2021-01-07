package com.yjs.cloud.learning.message.common.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * Service 实现超类
 * @author Andrew.xiao
 * @since 2019/6/6
 */
public class BaseServiceImpl<M extends BaseMapper<T>,T> extends ServiceImpl<M,T> {
}
