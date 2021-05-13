package com.yjs.cloud.learning.setting.common.web;

import com.alibaba.fastjson.JSONObject;
import com.yjs.cloud.learning.setting.common.response.UnifiedResponse;
import com.yjs.cloud.learning.setting.common.util.StringUtils;
import io.swagger.annotations.Api;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.HashMap;
import java.util.Map;

/**
 * Rest 控制器切面
 *
 * @author Andrew.xiao
 * @since 2019/6/5
 */
@ControllerAdvice
public class RestControllerAdvice implements ResponseBodyAdvice<Object> {

    @ResponseBody
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public UnifiedResponse resourceNotFoundExceptionHandler(ResourceNotFoundException ex) {
        Long resourceId = ex.getResourceId();
        String resourceName = ex.getResourceName();
        return new UnifiedResponse(404, resourceName + " ("+resourceId+") not found", new HashMap<>(16));
    }

    @ResponseBody
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public UnifiedResponse resourceNotFoundExceptionHandler(UnauthorizedException ex) {
        return new UnifiedResponse(ex.getCode(), ex.getMessage(), new HashMap<>(16));
    }

    @ResponseBody
    @ExceptionHandler(GlobalException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public UnifiedResponse globalExceptionHandler(GlobalException ge) {
        return new UnifiedResponse(ge.getCode(), ge.getMessage(), new HashMap<>(16));
    }

    @ResponseBody
    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public UnifiedResponse serviceExceptionHandler(ServiceException e) {
        return new UnifiedResponse(e.getCode(), e.getMessage(), new HashMap<>(16));
    }

    @Override
    public boolean supports(MethodParameter methodParameter, @Nullable Class<? extends HttpMessageConverter<?>> aClass) {
        // 这里可以实现自己的逻辑 判断是否包装
        return methodParameter.getContainingClass().isAnnotationPresent(Api.class);
    }

    @Override
    public Object beforeBodyWrite(Object o, @Nullable MethodParameter methodParameter,
                                  @Nullable MediaType mediaType, @Nullable Class<? extends HttpMessageConverter<?>> aClass,
                                  @Nullable ServerHttpRequest serverHttpRequest, @Nullable ServerHttpResponse serverHttpResponse) {
        if(o == null){
            o = new HashMap<>(16);
        } else if(o instanceof UnifiedResponse){
            return o;
        }else{
            if(o instanceof Map){
                JSONObject map = JSONObject.parseObject(JSONObject.toJSON(o).toString());
                String errorKey = "error";
                if(!StringUtils.isEmpty(map.getString(errorKey))){
                    return new UnifiedResponse(map.getInteger("status"), map.getString("message"), new HashMap<>(1));
                }
            }
        }
        //统一包装返回
        return new UnifiedResponse(o);
    }
}
