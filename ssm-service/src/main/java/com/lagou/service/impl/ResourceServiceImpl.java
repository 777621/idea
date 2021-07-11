package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.ResourceMapper;
import com.lagou.dao.RoleResourceRelationMapper;
import com.lagou.entity.Resource;
import com.lagou.entity.ResourceVo;
import com.lagou.entity.Role_Resource_relation;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private RoleResourceRelationMapper roleResourceRelationMapper;

    /**
     * 分页 多条件查询资源列表
     * @param resourceVo
     * @return
     */
    @Override
    public PageInfo<Resource> findAllResourceByPage(ResourceVo resourceVo) {

        PageHelper.startPage(resourceVo.getCurrentPage(),resourceVo.getPageSize());

        //List<Resource> resourceList = resourceMapper.findAllResourceByPage(resourceVo);

        Example example = new Example(Resource.class);

        Example.Criteria criteria = example.createCriteria();

        if(resourceVo.getCategoryId() != null){

            criteria.andEqualTo("categoryId",resourceVo.getCategoryId());
        }

        if(resourceVo.getName() != null && resourceVo.getName() != ""){

            criteria.andEqualTo("name",resourceVo.getName());
        }

        if(resourceVo.getUrl() != null && resourceVo.getUrl() != ""){

            criteria.andEqualTo("url",resourceVo.getUrl());
        }

        List<Resource> resourceList = resourceMapper.selectByExample(example);

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
        //resourceMapper.saveResource(resource);
        resourceMapper.insertSelective(resource);
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
        //resourceMapper.updateResource(resource);
        resourceMapper.updateByPrimaryKeySelective(resource);
    }

    /**
     * 删除资源信息
     * @param id
     */
    @Override
    public void deleteResource(Integer id) {

        //根据资源id删除角色 资源中间表的关联数据
        //resourceMapper.deleteResourceContextRole(id);

        Example example = new Example(Role_Resource_relation.class);

        Example.Criteria criteria = example.createCriteria();

        if(id != null) {

            criteria.andEqualTo("resourceId", id);
        }

        roleResourceRelationMapper.deleteByExample(example);

        //删除资源信息
        //resourceMapper.deleteResource(id);
        resourceMapper.deleteByPrimaryKey(id);
    }

    /**
     * 根据资源id 回显资源信息
     * @param id
     * @return
     */
    @Override
    public Resource findResourceById(Integer id) {

        //return resourceMapper.findResourceById(id);
        return resourceMapper.selectByPrimaryKey(id);
    }
}
