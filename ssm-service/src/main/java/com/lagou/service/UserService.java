package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.entity.*;

import java.util.List;

/**
 * 用户管理模块 service接口
 */
public interface UserService {

    /**
     * 用户分页 条件查询
     * @param userVo
     * @return
     */
    public PageInfo<User> findAllUserByPage(UserVo userVo);

    /**
     * 用户状态设置
     * @param id
     * @param status
     */
    public void updateUserStatus(int id,String status);

    /**
     * 用户登录
     * @param user
     * @return
     */
    public User login(User user);

    /**  分配角色 回显
     * 根据用户Id 查询关联的角色信息
     * @param id
     * @return
     */
    public List<Role> findUserRelationRoleById(Integer id);



    /**
     * 重新为用户分配角色
     * @param userRoleVo
     */
    public void saveUserContextRole(UserRoleVo userRoleVo);


    /**
     * 动态获取用户菜单 权限
     * @param id
     * @return
     */
    public ResponseResult getUserPermissions(Integer id);
}
