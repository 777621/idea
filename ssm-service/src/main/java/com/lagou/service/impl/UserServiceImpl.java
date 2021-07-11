package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.RoleMapper;
import com.lagou.dao.UserMapper;
import com.lagou.dao.UserRoleRelationMapper;
import com.lagou.entity.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;

/**
 * 用户管理模块
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleRelationMapper userRoleRelationMapper;
    /**
     * 用户分页 条件查询
     * @param userVo
     * @return
     */
    @Override
    public PageInfo<User> findAllUserByPage(UserVo userVo) {

        PageHelper.startPage(userVo.getCurrentPage(),userVo.getPageSize());

        Example example = new Example(User.class);

        Example.Criteria criteria = example.createCriteria();

        if(userVo.getName() != null && userVo.getName() != ""){

            criteria.andEqualTo("name",userVo.getName());
        }

        if(userVo.getStartCreateTime() != null && userVo.getEndCreateTime() != null){

            criteria.andBetween("createTime",userVo.getStartCreateTime(),userVo.getEndCreateTime());
        }

        List<User> userList = userMapper.selectByExample(example);

        PageInfo<User> user = new PageInfo<>(userList);

        return user;
    }

    /**
     * 用户状态设置
     * @param id
     * @param status
     */
    @Override
    public void updateUserStatus(int id, String status) {

        //封装数据
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        user.setUpdateTime(new Date());

        //调用业务
        userMapper.updateByPrimaryKeySelective(user);
        //userMapper.updateUserStatus(user);
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @Override
    public User login(User user) {

        //调用业务
        Example example = new Example(user.getClass());

        Example.Criteria criteria = example.createCriteria();

        if(user.getPhone() !=null && user.getPhone() !="") {

            criteria.andEqualTo("phone", user.getPhone());
        }

        User user1 = userMapper.selectOneByExample(example);

        try {
            if(user1 !=null && Md5.verify(user.getPassword(),"lagou",user1.getPassword())){

                return user1;

            }else {

                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }

       /* User user1 = userMapper.login(user);

        try {
            if(user1 !=null && Md5.verify(user.getPassword(),"lagou",user1.getPassword())){

                return user1;

            }else {

                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }*/

    }

    /**
     * 分配角色 回显
     * @param id
     * @return
     */
    @Override
    public List<Role> findUserRelationRoleById(Integer id) {


        //return userMapper.findUserRelationRoleById(id);

        Example example = new Example(User_Role_relation.class);

        Example.Criteria criteria = example.createCriteria();

        if(id != null){

            criteria.andEqualTo("userId",id);
        }

        List<User_Role_relation> relationList = userRoleRelationMapper.selectByExample(example);

        List<Integer> roleIds = new ArrayList<>();

        for (User_Role_relation user_role_relation : relationList) {

            roleIds.add(user_role_relation.getRoleId());
        }

        Example example1 = new Example(Role.class);

        Example.Criteria criteria1 = example1.createCriteria();

        if(roleIds != null) {

            criteria1.andIn("id", roleIds);

        }
        List<Role> roleList = roleMapper.selectByExample(example1);

        return roleList;
    }



    /**
     * 重新为用户分配角色
     * @param userRoleVo
     */
    @Override
    public void saveUserContextRole(UserRoleVo userRoleVo) {

        //根据用户id 清空用户 角色关联表的信息
        //userMapper.deleteUserContextRoleById(userRoleVo.getUserId());

        Example example = new Example(User_Role_relation.class);

        Example.Criteria criteria = example.createCriteria();

        if(userRoleVo.getUserId() != null){

            criteria.andEqualTo("userId",userRoleVo.getUserId());
        }

        userRoleRelationMapper.deleteByExample(example);

        //遍历获取的角色id
        List<Integer> roleIdList = userRoleVo.getRoleIdList();

        for (Integer roleId : roleIdList) {

            //遍历一次往中间表添加一条记录
            User_Role_relation userRoleRelation = new User_Role_relation();

            //封装数据
            userRoleRelation.setRoleId(roleId);
            userRoleRelation.setUserId(userRoleVo.getUserId());
            userRoleRelation.setCreatedBy("system");
            userRoleRelation.setUpdatedBy("system");
            Date date = new Date();
            userRoleRelation.setCreatedTime(date);
            userRoleRelation.setUpdatedTime(date);

            //调用业务
            //userMapper.saveUserContextRole(userRoleRelation);
            userRoleRelationMapper.insertSelective(userRoleRelation);
        }
    }

    /**
     * 动态获取用户菜单权限
     * @param id
     * @return
     */
    @Override
    public ResponseResult getUserPermissions(Integer id) {

        //获取当前用户所拥有的角色信息
        List<Role> roleList = userMapper.findUserRelationRoleById(id);

        //获取角色id 保存到集合
        List<Integer> list = new ArrayList<>();

        for (Role role : roleList) {

            list.add(role.getId());
        }

        //根据角色id 查询父菜单信息
        List<Menu> parentMenuList = userMapper.findParentMenuByRoleId(list);

        //封装子菜单信息
        for (Menu menu : parentMenuList) {

            List<Menu> subMenuList = userMapper.findSubMenuByPid(menu.getId());

            menu.setSubMenuList(subMenuList);
        }

        //根据角色id 获取资源权限信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(list);

        //封装数据
        Map<String,Object> map = new HashMap<>();

        map.put("menuList",parentMenuList);   //菜单权限数据
        map.put("resourceList",resourceList);   //资源权限数据

        ResponseResult result = new ResponseResult(true, 200, "动态获取用户权限成功", map);

        return result;
    }
}
