package com.lagou.controller;

import com.lagou.entity.ResourceCategory;
import com.lagou.entity.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 资源分类模块
 */
@RestController
@RequestMapping("/resourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    /**
     * 查询资源分类列表
     * @return
     */
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){

        List<ResourceCategory> resourceCategoryList = resourceCategoryService.findAllResourceCategory();

        ResponseResult result = new ResponseResult(true, 200, "查询资源列表信息成功", resourceCategoryList);

        return result;
    }

    /**
     * 添加或修改资源分类信息
     * @param resourceCategory
     * @return
     */
    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory){



        //判断条件
        if(resourceCategory.getId() == null){

            //添加操作
            resourceCategoryService.saveResourceCategory(resourceCategory);

            ResponseResult result = new ResponseResult(true, 200, "添加资源分类信息成功", null);

            return result;
        }else {

            //修改操作
            resourceCategoryService.updateResourceCategory(resourceCategory);

            ResponseResult result = new ResponseResult(true, 200, "修改资源分类信息成功", null);

            return result;
        }
    }

    /**
     * 回显资源分类信息
     * @param id
     * @return
     */
    @RequestMapping("/findResourceCategoryById")
    public ResponseResult findResourceCategoryById(Integer id){

        //调用业务
        ResourceCategory resourceCategory = resourceCategoryService.findResourceCategoryById(id);

        //返回数据
        ResponseResult result = new ResponseResult(true, 200, "回显资源分类信息成功", resourceCategory);

        return result;
    }

    /**
     * 删除资源分类信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteResourceCategoryById")
    public ResponseResult deleteResourceCategoryById(Integer id){

        //调用业务
        resourceCategoryService.deleteResourceCategoryById(id);

        //返回数据
        ResponseResult result = new ResponseResult(true, 200, "删除资源分类信息成功", null);

        return result;
    }
}
