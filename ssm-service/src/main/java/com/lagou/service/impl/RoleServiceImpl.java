package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.entity.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 角色模块
 */

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    /**
     * 根据条件查询角色信息
     * @param role
     * @return
     */
    @Override
    public List<Role> findAllRole(Role role) {

        return roleMapper.findAllRole(role);
    }

    /**
     * 根据角色id查询角色关联的菜单信息
     * @param roleId
     * @return
     */
    @Override
    public List<Integer> findMenuByRoleId(Integer roleId) {

        return roleMapper.findMenuByRoleId(roleId);
    }

    /**
     * 重新为角色分配菜单
     * @param roleMenuVo
     */
    @Override
    public void saveRoleContextMenu(RoleMenuVo roleMenuVo) {

        //根据角色id清空关联表中的菜单数据
        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        //遍历获取分配的菜单id
        List<Integer> menuIdList = roleMenuVo.getMenuIdList();

        for (Integer menuId : menuIdList) {

            //遍历一次往中间表添加一条数据
            Role_menu_relation roleMenuRelation = new Role_menu_relation();

            roleMenuRelation.setMenuId(menuId);
            roleMenuRelation.setRoleId(roleMenuVo.getRoleId());

            //封装其他数据
            roleMenuRelation.setCreatedBy("system");
            roleMenuRelation.setUpdatedBy("system");

            Date date = new Date();
            roleMenuRelation.setCreatedTime(date);
            roleMenuRelation.setUpdatedTime(date);

            //调用业务 执行添加操作
            roleMapper.saveRoleContextMenu(roleMenuRelation);
        }
    }

    /**
     * 删除角色信息
     * @param roleId
     */
    @Override
    public void deleteRole(Integer roleId) {

        //先清空角色菜单关联的中间表信息
        roleMapper.deleteRoleContextMenu(roleId);

        //删除角色信息
        roleMapper.deleteRole(roleId);
    }

    /**
     * 添加角色信息
     * @param role
     */
    @Override
    public void saveRole(Role role) {

        //封装数据
        role.setCreatedBy("system");
        role.setUpdatedBy("system");
        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);

        //调用业务
        roleMapper.saveRole(role);
    }

    /**
     * 修改角色信息
     * @param role
     */
    @Override
    public void updateRole(Role role) {

        //封装数据
        role.setUpdatedTime(new Date());

        //调用业务
        roleMapper.updateRole(role);
    }

    /**
     * 回显角色信息
     * @param id
     * @return
     */
    @Override
    public Role findRoleById(Integer id) {

        return roleMapper.findRoleById(id);
    }

    /**
     * 根据角色id 查询资源信息 和资源分类信息
     * @param roleId
     * @return
     */
    @Override
    public List<ResourceCategory> findAllResourceAndCategoryById(Integer roleId) {

        //查询资源信息
        List<Resource> resourceList = roleMapper.findAllResourceByRoleId(roleId);
        if(resourceList == null){
            return null;
        }
        //查询资源分类信息
        List<ResourceCategory> resourceCategoryList = roleMapper.findAllResourceCategoryById(roleId);

        //封装资源信息
        for (Resource resource : resourceList) {

            for (ResourceCategory resourceCategory : resourceCategoryList) {

                if(resourceCategory.getId().equals(resource.getCategoryId())){

                    resourceCategory.getResourceList().add(resource);
                    break;
                }
            }
        }

        return resourceCategoryList;
    }


    /**
     * 重新为角色分配资源
     * @param roleResourceVo
     */
    @Override
    public void saveRoleContextResource(RoleResourceVo roleResourceVo) {

        //根据角色id 清空角色 资源关联表的数据
        roleMapper.deleteRoleContextResource(roleResourceVo.getRoleId());

        //获取分配的资源id集合
        List<Integer> resourceIdList = roleResourceVo.getResourceIdList();

        //遍历 添加数据
        for (Integer resourceId : resourceIdList) {

            //封装数据
            Role_Resource_relation roleResourceRelation = new Role_Resource_relation();

            roleResourceRelation.setRoleId(roleResourceVo.getRoleId());
            roleResourceRelation.setResourceId(resourceId);
            roleResourceRelation.setCreatedBy("system");
            roleResourceRelation.setUpdatedBy("system");
            Date date = new Date();
            roleResourceRelation.setCreatedTime(date);
            roleResourceRelation.setUpdatedTime(date);

            //调用业务
            roleMapper.saveRoleContextResource(roleResourceRelation);
        }
    }
}
