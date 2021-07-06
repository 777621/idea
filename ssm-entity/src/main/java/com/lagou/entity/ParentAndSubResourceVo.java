package com.lagou.entity;

import java.util.List;

/**
 * 封装返回数据 点击分配资源 页面回显
 */
public class ParentAndSubResourceVo {

    private Integer categoryId ;  //父节点资源分类信息

    private List<Resource> resourceList;   //子节点资源信息

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public List<Resource> getResourceList() {
        return resourceList;
    }

    public void setResourceList(List<Resource> resourceList) {
        this.resourceList = resourceList;
    }
}
