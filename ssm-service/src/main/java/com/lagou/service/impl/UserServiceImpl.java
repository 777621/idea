package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.*;
import com.lagou.entity.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

    @Autowired
    private RoleMenuRelationMapper roleMenuRelationMapper;

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleResourceRelationMapper roleResourceRelationMapper;

    @Autowired
    private ResourceMapper resourceMapper;
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

        if(userVo.getUsername() != null && userVo.getUsername() != ""){

            criteria.andEqualTo("name",userVo.getUsername());
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

        //获取当前用户拥有的角色信息
        Example example = new Example(User_Role_relation.class);

        Example.Criteria criteria = example.createCriteria();

        if(id != null){

            criteria.andEqualTo("userId",id);

            //获取到关联信息中的数据
            List<User_Role_relation> roleRelations = userRoleRelationMapper.selectByExample(example);

            List<Integer> roleIds = new ArrayList<>();

            List<Menu> parentMenuList = null;

            //取出role_id 查询角色信息
            for (User_Role_relation roleRelation : roleRelations) {

                Integer roleId = roleRelation.getRoleId();

                Example example1 = new Example(Role.class);

                Example.Criteria criteria1 = example1.createCriteria();

                criteria1.andEqualTo("id",roleId);

                List<Role> roleList = roleMapper.selectByExample(example1);

                //将角色id 保存到集合中
                for (Role role : roleList) {

                    roleIds.add(role.getId());
                }

                //根据角色id 查询父菜单信息
                Example example2 = new Example(Role_menu_relation.class);

                Example.Criteria criteria2 = example2.createCriteria();

                criteria2.andIn("roleId", roleIds);

                //获取菜单角色中间表数据
                List<Role_menu_relation> menuRelationList = roleMenuRelationMapper.selectByExample(example2)
                        .stream().distinct().collect(Collectors.toList());

                //取出菜单id 查询菜单信息
                List<Integer> menuIds = new ArrayList<>();

                for (Role_menu_relation menuRelation : menuRelationList) {

                    Integer menuId = menuRelation.getMenuId();

                    menuIds.add(menuId);
                }

                //根据菜单id查询父菜单信息
                Example example3 = new Example(Menu.class);

                Example.Criteria criteria3 = example3.createCriteria();

                criteria3.andIn("id",menuIds).andEqualTo("parentId",-1);

                //获取父菜单信息
                parentMenuList = menuMapper.selectByExample(example3).stream().distinct().collect(Collectors.toList());

                for (Menu menu : parentMenuList) {
                    //根据pid 查询子菜单信息
                    Integer parentId = menu.getId();

                    //封装子菜单信息
                    Example e = new Example(Menu.class);

                    Example.Criteria c = e.createCriteria();

                    c.andEqualTo("parentId",parentId);

                    List<Menu> subMenuList = menuMapper.selectByExample(e);

                    menu.setSubMenuList(subMenuList);
                }
            }

            //根据角色id 获取资源信息
            Example example4 = new Example(Role_Resource_relation.class);

            Example.Criteria criteria4 = example4.createCriteria();

            criteria4.andIn("roleId",roleIds);

            List<Role_Resource_relation> resourceRelationList = roleResourceRelationMapper.selectByExample(example4).
                    stream().distinct().collect(Collectors.toList());

            List<Integer> resourceIds = new ArrayList<>();

            //取出资源id 查询资源信息
            for (Role_Resource_relation resourceRelation : resourceRelationList) {

                resourceIds.add(resourceRelation.getResourceId());
            }

            Example example5 = new Example(Resource.class);

            Example.Criteria criteria5 = example5.createCriteria();

            criteria5.andIn("id",resourceIds);

            List<Resource> resourceList = resourceMapper.selectByExample(example5)
                    .stream().distinct().collect(Collectors.toList());

            //封装数据
            Map<String,Object> map = new HashMap<>();

            map.put("menuList",parentMenuList);
            map.put("resourceList",resourceList);

            ResponseResult result = new ResponseResult(true, 200, "动态获取用户权限成功", map);

            return result;
        }else {
            return null;
        }

       /* //获取当前用户所拥有的角色信息
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

        return result;*/
    }
}
