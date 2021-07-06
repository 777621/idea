package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.entity.*;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 用户管理模块
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 分页条件查询用户信息
     * @param userVo
     * @return
     */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo){

        //调用业务
        PageInfo<User> userList = userService.findAllUserByPage(userVo);

        //返回数据
        ResponseResult result = new ResponseResult(true, 200, "分页条件查询用户信息成功", userList);

        return result;
    }

    /**
     * 用户状态设置
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(@RequestParam("id") int id,@RequestParam("status") String status){

        userService.updateUserStatus(id,status);

        ResponseResult result = new ResponseResult(true, 200, "用户状态修改成功", null);

        return result;
    }

    /**
     * 用户登录
     * @param user
     * @return
     */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request){

        User user1 = userService.login(user);

        if(user1 !=null){

            //保存用户id access_token到session
            HttpSession session = request.getSession();

            String access_token = UUID.randomUUID().toString();

            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",user1.getId());

            Map<String,Object> map = new HashMap<>();

            map.put("access_token",access_token);

            map.put("user_id",user1.getId());

            map.put("user",user1);

            ResponseResult result = new ResponseResult(true, 1, "登录成功", map);

            return result;

        }else {

            ResponseResult result = new ResponseResult(true, 400, "用户名或密码错误", null);

            return result;
        }
    }

    /**
     * 分配角色 回显
     * @param id
     * @return
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRelationRoleById(Integer id){

        List<Role> roleList = userService.findUserRelationRoleById(id);

        ResponseResult result = new ResponseResult(true, 200, "分配角色回显信息成功", roleList);

        return result;
    }

    /**
     * 重新为用户分配角色
     * @param userRoleVo
     * @return
     */
    @RequestMapping("/userContextRole")
    public ResponseResult saveUserContextRole(@RequestBody UserRoleVo userRoleVo){

        //调用业务
        userService.saveUserContextRole(userRoleVo);

        ResponseResult result = new ResponseResult(true, 200, "为用户分配角色成功", null);

        return result;
    }

    /**
     * 动态获取用户权限信息 (菜单)
     * @return
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){

        //获取请求头的token信息
        String header_token = request.getHeader("Authorization");

        //获取session中的token信息
        HttpSession session = request.getSession();
        Object session_token = session.getAttribute("access_token");

        if(header_token.equals(session_token)){

            int user_id = (int) session.getAttribute("user_id");

            ResponseResult result = userService.getUserPermissions(user_id);

            return result;
        }else {

            ResponseResult result = new ResponseResult(false, 400, "获取用户权限信息失败", null);

            return result;
        }
    }
}
