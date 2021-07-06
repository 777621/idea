package com.lagou.service.impl;

import com.lagou.dao.MenuMapper;
import com.lagou.entity.Menu;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 菜单模块 service实现类
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    /**
     * 查询父菜单及关联的子菜单信息
     * @param pid
     * @return
     */
    @Override
    public List<Menu> findSubMenuListByPid(int pid) {

        return menuMapper.findSubMenuListByPid(pid);
    }

    /**
     * 查询菜单列表
     * @return
     */
    @Override
    public List<Menu> findAllMenu() {

        return menuMapper.findAllMenu();
    }

    /**
     * 根据id查询菜单信息
     * @param id
     * @return
     */
    @Override
    public Menu findMenuById(Integer id) {

        return menuMapper.findMenuById(id);
    }

    /**
     * 添加菜单信息
     * @param menu
     */
    @Override
    public void saveMenu(Menu menu) {

        //封装数据
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");
        Date date = new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);

        //调用业务
        menuMapper.saveMenu(menu);
    }

    /**
     * 修改菜单信息
     * @param menu
     */
    @Override
    public void updateMenu(Menu menu) {

        //封装数据
        menu.setUpdatedTime(new Date());

        //调用业务
        menuMapper.updateMenu(menu);
    }

    /**
     * 删除菜单信息
     * @param id
     */
    @Override
    public void deleteMenu(Integer id) {

        //删除关联表的数据
        menuMapper.deleteMenuContextRole(id);

        //删除菜单信息
        menuMapper.deleteMenu(id);
    }
}
