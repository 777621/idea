package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.entity.Resource;
import com.lagou.entity.ResourceVo;
import com.lagou.entity.ResponseResult;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 资源模块
 */
@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 分页 多条件查询资源列表信息
     * @return
     */
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVo resourceVo){

        //调用业务
        PageInfo<Resource> resourceList = resourceService.findAllResourceByPage(resourceVo);

        //返回数据
        ResponseResult result = new ResponseResult(true, 200, "分页多条件查询资源列表信息成功", resourceList);

        return result;
    }

    /**
     * 添加或修改资源信息
     * @param resource
     * @return
     */
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource){

        if(resource.getId() == null){

            //添加操作
            resourceService.saveResource(resource);

            ResponseResult result = new ResponseResult(true, 200, "添加资源信息成功", null);

            return result;
        }else {

            //修改操作
            resourceService.updateResource(resource);

            ResponseResult result = new ResponseResult(true, 200, "修改资源信息成功", null);

            return result;
        }
    }

    /**
     * 删除资源信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(Integer id){

        resourceService.deleteResource(id);

        ResponseResult result = new ResponseResult(true, 200, "删除资源信息成功", null);

        return result;
    }

    /**
     * 回显资源信息
     */
    @RequestMapping("/findResourceById")
    public ResponseResult findResourceById(Integer id){

        //调用业务
        Resource resource = resourceService.findResourceById(id);

        //返回数据
        ResponseResult result = new ResponseResult(true, 200, "回显资源信息成功", resource);

        return result;
    }
}
