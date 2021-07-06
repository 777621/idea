package com.lagou.entity;

import java.util.List;

/**
 * 为角色分配菜单 接收前端参数
 */
public class RoleMenuVo {

    private Integer roleId;

    private List<Integer> menuIdList;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public List<Integer> getMenuIdList() {
        return menuIdList;
    }

    public void setMenuIdList(List<Integer> menuIdList) {
        this.menuIdList = menuIdList;
    }
}
