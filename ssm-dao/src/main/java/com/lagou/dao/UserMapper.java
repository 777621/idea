package com.lagou.dao;

import com.lagou.entity.*;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 用户管理模块 dao接口
 */
public interface UserMapper extends Mapper<User> {

    /**
     * 用户分页 条件查询
     * @param userVo
     * @return
     */
    //public List<User> findAllUserByPage(UserVo userVo);

    /**
     * 用户状态设置
     * @param user
     */
    //public void updateUserStatus(User user);

    /**
     * 用户登录
     * @param user
     * @return
     */
    //public User login(User user);


    /**
     * 根据用户id 清空用户角色关联表信息
     * @param UserId
     */
    public void deleteUserContextRoleById(Integer UserId);

    /**
     * 重新为用户分配角色
     * @param userRoleRelation
     */
    public void saveUserContextRole(User_Role_relation userRoleRelation);

    /**
     * 根据用户Id 查询关联的角色信息
     * @param id
     * @return
     */
    public List<Role> findUserRelationRoleById(Integer id);


    /**
     * 根据角色id 查询角色拥有的顶级菜单信息
     * @param ids
     * @return
     */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /**
     * 根据pid 查询关联的子菜单信息
     * @param pid
     * @return
     */
    public List<Menu> findSubMenuByPid(int pid);

    /**
     * 获取用户拥有的资源权限信息
     * @param ids
     * @return
     */
    public List<Resource> findResourceByRoleId(List<Integer> ids);
}
