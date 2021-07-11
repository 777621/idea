package com.lagou.dao;

import com.lagou.entity.Resource;
import com.lagou.entity.ResourceVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 资源模块
 */
public interface ResourceMapper extends Mapper<Resource> {

    /**
     * 资源信息分页 &条件查询
     * @param resourceVo
     * @return
     */
    //public List<Resource> findAllResourceByPage(ResourceVo resourceVo);

    /**
     * 添加资源信息
     * @param resource
     */
    //public void saveResource(Resource resource);

    /**
     * 修改资源信息
     * @param resource
     */
    //public void updateResource(Resource resource);

    /**
     * 根据资源id删除角色和资源中间表关联的数据
     * @param resourceId
     */
    //public void deleteResourceContextRole(Integer resourceId);

    /**
     * 删除资源信息
     * @param id
     */
    //public void deleteResource(Integer id);

    /**
     * 根据id 回显资源信息
     * @param id
     * @return
     */
    //public Resource findResourceById(Integer id);
}
