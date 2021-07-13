package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.entity.Menu;
import com.lagou.entity.PromotionAdVo;

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
    public List<Menu> findSubMenuListByPid(Integer pid);

    /**
     * 查询菜单列表
     * @return
     */
    public PageInfo<Menu> findAllMenu(PromotionAdVo promotionAdVo);

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
