package com.lagou.dao;

import com.lagou.entity.ResourceCategory;

import java.util.List;

/**
 * 资源分类接口
 */
public interface ResourceCategoryMapper {

    /**
     * 查询资源分类列表
     * @return
     */
    public List<ResourceCategory> findAllResourceCategory();

    /**
     * 添加资源分类信息
     * @param resourceCategory
     */
    public void saveResourceCategory(ResourceCategory resourceCategory);

    /**
     * 修改资源分类信息
     * @param resourceCategory
     */
    public void updateResourceCategory(ResourceCategory resourceCategory);

    /**
     * 回显资源分类信息
     * @param id
     * @return
     */
    public ResourceCategory findResourceCategoryById(Integer id);

    /**
     * 删除资源分类信息
     * @param id
     */
    public void deleteResourceCategoryById(Integer id);
}
