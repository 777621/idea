package com.lagou.service.impl;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.entity.ResourceCategory;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    /**
     * 查询资源分类列表
     * @return
     */
    @Override
    public List<ResourceCategory> findAllResourceCategory() {

        return resourceCategoryMapper.findAllResourceCategory();
    }

    /**
     * 添加资源分类信息
     * @param resourceCategory
     */
    @Override
    public void saveResourceCategory(ResourceCategory resourceCategory) {

        //封装数据
        resourceCategory.setCreatedBy("system");
        resourceCategory.setUpdatedBy("system");
        Date date = new Date();
        resourceCategory.setCreatedTime(date);
        resourceCategory.setUpdatedTime(date);

        //调用业务
        resourceCategoryMapper.saveResourceCategory(resourceCategory);

        System.out.println("resourceCategory::::::::::::service"+resourceCategory);
    }

    /**
     * 修改资源分类信息
     * @param resourceCategory
     */
    @Override
    public void updateResourceCategory(ResourceCategory resourceCategory) {

        //封装数据
        resourceCategory.setUpdatedTime(new Date());

        //调用业务
        resourceCategoryMapper.updateResourceCategory(resourceCategory);
    }

    /**
     * 回显资源分类信息
     * @param id
     * @return
     */
    @Override
    public ResourceCategory findResourceCategoryById(Integer id) {

        return resourceCategoryMapper.findResourceCategoryById(id);
    }

    /**
     * 删除资源分类信息
     * @param id
     */
    @Override
    public void deleteResourceCategoryById(Integer id) {

        resourceCategoryMapper.deleteResourceCategoryById(id);
    }
}
