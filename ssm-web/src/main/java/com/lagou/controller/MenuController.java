package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.entity.Menu;
import com.lagou.entity.PromotionAdVo;
import com.lagou.entity.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 菜单模块
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /**
     * 查询菜单列表
     * @return
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(PromotionAdVo promotionAdVo){

        PageInfo<Menu> menuList = menuService.findAllMenu(promotionAdVo);

        ResponseResult result = new ResponseResult(true, 200, "查询菜单列表信息成功", menuList);

        return result;
    }

    /**
     * 回显菜单信息
     * @return
     */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){

        //判断id是否是-1
        if(id == -1){

            //添加操作 不需要回显菜单信息
            List<Menu> menuList = menuService.findSubMenuListByPid(-1);

            Map<String,Object> map = new HashMap<>();

            map.put("menuInfo",null);

            map.put("parentMenuList",menuList);

            ResponseResult result = new ResponseResult(true, 200, "添加回显信息成功", map);

            return result;
        }else {

            //修改操作
            Menu menu = menuService.findMenuById(id);

            List<Menu> menuList = menuService.findSubMenuListByPid(-1);

            Map<String,Object> map = new HashMap<>();

            map.put("menuInfo",menu);

            map.put("parentMenuList",menuList);

            ResponseResult result = new ResponseResult(true, 200, "修改回显信息成功", map);

            return result;

        }
    }

    /**
     * 添加或修改菜单信息
     * @param menu
     * @return
     */
    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu){

        //判断条件
        if(menu.getId() == null) {

            //调用添加业务
            menuService.saveMenu(menu);

            //返回数据
            ResponseResult result = new ResponseResult(true, 200, "添加菜单信息成功", null);

            return result;
        }else {

            //调用修改业务
            menuService.updateMenu(menu);

            //返回数据
            ResponseResult result = new ResponseResult(true, 200, "修改菜单信息成功", null);

            return result;
        }

    }

    /**
     * 删除菜单信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteMenuById")
    public ResponseResult deleteMenuById(Integer id){

        //调用业务
        menuService.deleteMenu(id);

        //返回数据
        ResponseResult result = new ResponseResult(true, 200, "删除菜单信息成功", null);

        return result;
    }
}
