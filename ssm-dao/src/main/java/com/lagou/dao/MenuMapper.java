package com.lagou.dao;

import com.lagou.entity.Menu;

import java.util.List;

/**
 * 菜单模块 dao接口
 */
public interface MenuMapper {

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
     * 根据关联表的菜单id 清空中间表数据
     * @param MenuId
     */
    public void deleteMenuContextRole(Integer MenuId);

    /**
     * 删除菜单信息
     * @param id
     */
    public void deleteMenu(Integer id);
}
