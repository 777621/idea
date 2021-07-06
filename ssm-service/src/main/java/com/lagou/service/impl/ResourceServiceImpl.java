package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.ResourceMapper;
import com.lagou.entity.Resource;
import com.lagou.entity.ResourceVo;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    /**
     * 分页 多条件查询资源列表
     * @param resourceVo
     * @return
     */
    @Override
    public PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo) {

        PageHelper.startPage(resourceVo.getCurrentPage(),resourceVo.getPageSize());

        List<Resource> resourceList = resourceMapper.findAllResourceByPage(resourceVo);

        PageInfo<Resource> resource = new PageInfo<>(resourceList);

        return resource;
    }

    /**
     * 添加资源信息
     * @param resource
     */
    @Override
    public void saveResource(Resource resource) {

        //封装数据
        resource.setCreatedBy("system");
        resource.setUpdatedBy("system");
        Date date = new Date();
        resource.setCreatedTime(date);
        resource.setUpdatedTime(date);

        //调用业务
        resourceMapper.saveResource(resource);
    }

    /**
     * 修改资源信息
     * @param resource
     */
    @Override
    public void updateResource(Resource resource) {

        //封装数据
        resource.setUpdatedTime(new Date());

        //调用业务
        resourceMapper.updateResource(resource);
    }

    /**
     * 删除资源信息
     * @param id
     */
    @Override
    public void deleteResource(Integer id) {

        //根据资源id删除角色 资源中间表的关联数据
        resourceMapper.deleteResourceContextRole(id);

        //删除资源信息
        resourceMapper.deleteResource(id);
    }

    /**
     * 根据资源id 回显资源信息
     * @param id
     * @return
     */
    @Override
    public Resource findResourceById(Integer id) {

        return resourceMapper.findResourceById(id);
    }
}
