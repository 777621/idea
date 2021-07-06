package com.lagou.dao;

import com.lagou.entity.*;

import java.util.List;

/**
 * 角色模块 dao接口
 */
public interface RoleMapper {

    /**
     * 根据条件查询所有角色信息
     * @return
     */
    public List<Role> findAllRole(Role role);

    /**
     * 根据角色id查询角色关联的菜单信息
     */
    public List<Integer> findMenuByRoleId(Integer roleId);

    /**
     * 根据roleId清空关联表的关联数据
     * @param roleId
     */
    public void deleteRoleContextMenu(Integer roleId);

    /**
     * 重新为角色分配菜单
     * @param role_menu_relation
     */
    public void saveRoleContextMenu(Role_menu_relation role_menu_relation);

    /**
     * 删除角色信息
     * @param roleId
     */
    public void deleteRole(Integer roleId);

    /**
     * 添加角色信息
     * @param role
     */
    public void saveRole(Role role);

    /**
     * 修改角色信息
     * @param role
     */
    public void updateRole(Role role);

    /**
     * 编辑按钮回显角色信息
     * @param id
     * @return
     */
    public Role findRoleById(Integer id);



    /**
     * 根据角色id 查询资源分类信息
     * @param roleId
     * @return
     */
    public List<ResourceCategory> findAllResourceCategoryById(Integer roleId);

    /**
     * 根据角色id 查询关联的资源信息(资源id)
     * @param roleId
     */
    public List<Resource> findAllResourceByRoleId(Integer roleId);

    /**
     * 根据角色id 清空角色资源中间表信息
     * @param roleId
     */
    public void deleteRoleContextResource(Integer roleId);

    /**
     * 重新为角色分配资源
     */
    public void saveRoleContextResource(Role_Resource_relation roleResourceRelation);
}
