package com.yjs.cloud.learning.behavior.common.web;

/**
 * Rest资源找不到异常
 *
 * @author Andrew.xiao
 * @since 2019/6/5
 */
public class ResourceNotFoundException extends RuntimeException{

    /**
     * 资源id
     */
    private final Long resourceId;

    /**
     * 资源名称
     */
    private final String resourceName;
    public ResourceNotFoundException(Long resourceId, String resourceName) {
        this.resourceName = resourceName;
        this.resourceId = resourceId;
    }

    Long getResourceId() {
        return resourceId;
    }

    String getResourceName() {
        return resourceName;
    }
}
