package com.lagou.service;

import com.lagou.entity.Menu;

import java.util.List;

/**
 * 菜单模块 service接口
 */
public interface MenuService {

    /**
     * 查询所有父子菜单信息
     * @param pid
     * @return
     */
    public List<Menu> findSubMenuListByPid(int pid);

    /**
     * 查询菜单列表
     * @return
     */
    public List<Menu> findAllMenu();

    /**
     * 根据菜单id查询菜单信息
     * @param id
     * @return
     */
    public Menu findMenuById(Integer id);

    /**
     * 添加菜单信息
     * @param menu
     */
    public void saveMenu(Menu menu);

    /**
     * 修改菜单信息
     * @param menu
     */
    public void updateMenu(Menu menu);

    /**
     * 删除菜单信息
     * @param id
     */
    public void deleteMenu(Integer id);
}
