package com.lagou.controller;


import com.lagou.entity.*;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色模块
 */

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /**
     * 根据条件查询角色信息
     * @param role
     * @return
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){

        List<Role> roleList = roleService.findAllRole(role);

        ResponseResult result = new ResponseResult(true, 200, "查询角色信息成功", roleList);

        return result;
    }

    /**
     * 查询所有的父子菜单信息
     * @return
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){

        //查询所有父级菜单
        List<Menu> menuList = menuService.findSubMenuListByPid(-1);

        Map<String,Object> map = new HashMap<>();

        map.put("parentMenuList",menuList);

        ResponseResult result = new ResponseResult(true, 200, "查询父子级菜单信息成功", map);

        return result;
    }

    /**
     * 根据角色id查询角色关联的菜单信息
     * @return
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){

        List<Integer> menuList = roleService.findMenuByRoleId(roleId);

        ResponseResult result = new ResponseResult(true, 200, "查询角色关联的菜单信息成功", menuList);

        return result;
    }

    /**
     * 为角色分配菜单
     * @param roleMenuVo
     * @return
     */
    @RequestMapping("/roleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){

        roleService.saveRoleContextMenu(roleMenuVo);

        ResponseResult result = new ResponseResult(true, 200, "为角色分配菜单信息成功", null);

        return result;
    }

    /**
     * 删除角色信息
     * @return
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){

        roleService.deleteRole(id);

        ResponseResult result = new ResponseResult(true, 200, "删除角色信息成功", null);

        return result;
    }

    /**
     * 添加或修改角色信息
     * @param role
     * @return
     */
    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role){
        
        if(role.getId() == null){
            
            //添加操作
            roleService.saveRole(role);

            ResponseResult result = new ResponseResult(true, 200, "添加角色信息成功", null);

            return result;
        }else {

            //修改操作
            roleService.updateRole(role);

            ResponseResult result = new ResponseResult(true, 200, "修改角色信息成功", null);

            return result;
        }
    }

    /**
     * 回显角色信息
     * @param id
     * @return
     */
    @RequestMapping("/findRoleById")
    public ResponseResult findRoleById(Integer id){

        //调用业务
        Role role = roleService.findRoleById(id);

        //返回数据
        ResponseResult result = new ResponseResult(true, 200, "回显角色信息成功", role);

        return result;
    }

    /**
     * 点击 资源分配 回显 资源分类信息(父级) 和资源信息(子级) 信息
     * @return
     */
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findAllParentAndSubResource(Integer roleId){

        //根据角色id查询资源信息和资源分类信息
        List<ResourceCategory> resourceCategoryList = roleService.findAllResourceAndCategoryById(roleId);

        return new ResponseResult(true,200,"获取当前角色所拥有的资源信息成功",resourceCategoryList);
    }



    /**
     * 重新为角色分配资源
     * @param roleResourceVo
     * @return
     */
    @RequestMapping("/roleContextResource")
    public ResponseResult roleContextResource(@RequestBody RoleResourceVo roleResourceVo){

        //调用业务
        roleService.saveRoleContextResource(roleResourceVo);

        ResponseResult result = new ResponseResult(true, 200, "为角色分配资源成功", null);

        return result;
    }
}
