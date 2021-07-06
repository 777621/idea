package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.entity.Resource;
import com.lagou.entity.ResourceVo;

import java.util.List;

/**
 * 资源模块 service接口
 */
public interface ResourceService {

    /**
     * 资源信息分页 &条件查询
     * @param resourceVo
     * @return
     */
    public PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo);

    /**
     * 添加资源信息
     * @param resource
     */
    public void saveResource(Resource resource);

    /**
     * 修改资源信息
     * @param resource
     */
    public void updateResource(Resource resource);

    /**
     * 删除资源信息
     * @param id
     */
    public void deleteResource(Integer id);

    /**
     * 根据资源id 回显资源信息
     * @param id
     * @return
     */
    public Resource findResourceById(Integer id);
}
