package com.lagou.service;

import com.lagou.entity.*;

import java.util.List;

/**
 * 角色模块 service接口
 */
public interface RoleService {

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
     * 重新为角色分配菜单
     * @param roleMenuVo
     */
    public void saveRoleContextMenu(RoleMenuVo roleMenuVo);

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
     * 根据角色id 查询资源分类 和资源信息
     * @param roleId
     * @return
     */
    public List<ResourceCategory> findAllResourceAndCategoryById(Integer roleId);



    /**
     * 重新为角色分配资源
     * @param roleResourceVo
     */
    public void saveRoleContextResource(RoleResourceVo roleResourceVo);
}
