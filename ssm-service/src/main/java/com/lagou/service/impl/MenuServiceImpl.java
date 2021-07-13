package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.MenuMapper;
import com.lagou.dao.RoleMenuRelationMapper;
import com.lagou.entity.Menu;
import com.lagou.entity.PromotionAdVo;
import com.lagou.entity.Role_menu_relation;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 菜单模块 service实现类
 */
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private RoleMenuRelationMapper roleMenuRelationMapper;

    /**
     * 查询父菜单及关联的子菜单信息
     * @param pid
     * @return
     */
    @Override
    public List<Menu> findSubMenuListByPid(Integer pid) {

        //return menuMapper.findSubMenuListByPid(pid);

        Example example = new Example(Menu.class);

        Example.Criteria criteria = example.createCriteria();

        if(pid != null){

            criteria.andEqualTo("parentId",pid);
        }

        List<Menu> menuList = menuMapper.selectByExample(example);

        List<Integer> menuIds = new ArrayList<>();

        for (Menu menu1 : menuList) {

            menuIds.add(menu1.getId());

            Example example1 = new Example(Menu.class);

            Example.Criteria criteria1 = example1.createCriteria();

            criteria1.andIn("id",menuIds);

            List<Menu> subMenu = menuMapper.selectByExample(example1);

            menu1.setSubMenuList(subMenu);
        }

        return menuList;
    }

    /**
     * 查询菜单列表
     * @return
     */
    @Override
    public PageInfo<Menu> findAllMenu(PromotionAdVo promotionAdVo) {

        //return menuMapper.findAllMenu();

        PageHelper.startPage(promotionAdVo.getCurrentPage(),promotionAdVo.getPageSize());

        List<Menu> menuList = menuMapper.selectAll();

        PageInfo<Menu> info = new PageInfo<>(menuList);

        return info;
    }

    /**
     * 根据id查询菜单信息
     * @param id
     * @return
     */
    @Override
    public Menu findMenuById(Integer id) {

        //return menuMapper.findMenuById(id);

        return menuMapper.selectByPrimaryKey(id);
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

        if(menu.getParentId() == -1){

            menu.setLevel(0);
        }else {

            menu.setLevel(1);
        }

        //调用业务
        //menuMapper.saveMenu(menu);
        menuMapper.insertSelective(menu);
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
        //menuMapper.updateMenu(menu);
        menuMapper.updateByPrimaryKeySelective(menu);
    }

    /**
     * 删除菜单信息
     * @param id
     */
    @Override
    public void deleteMenu(Integer id) {

        //删除关联表的数据
        //menuMapper.deleteMenuContextRole(id);

        Example example = new Example(Role_menu_relation.class);

        Example.Criteria criteria = example.createCriteria();

        if(id != null){

            criteria.andEqualTo("menuId",id);
        }

        roleMenuRelationMapper.deleteByExample(example);

        //删除菜单信息
        menuMapper.deleteByPrimaryKey(id);
        //menuMapper.deleteMenu(id);
    }
}
