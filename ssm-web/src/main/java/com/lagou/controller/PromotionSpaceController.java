package com.lagou.controller;

import com.lagou.entity.PromotionSpace;
import com.lagou.entity.ResponseResult;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/promotionSpace")
public class PromotionSpaceController {

    @Autowired
    private PromotionSpaceService promotionSpaceService;

    /**
     * 查询广告位列表信息
     * @return
     */
    @RequestMapping("/findAllPromotionSpace")
    public ResponseResult findAllPromotionSpace(){

        List<PromotionSpace> promotionSpaceList = promotionSpaceService.findAllPromotionSpace();

        ResponseResult result = new ResponseResult(true, 200, "查询广告位列表信息成功", promotionSpaceList);

        return result;
    }

    /**
     * 添加或修改广告位信息
     * @param promotionSpace
     * @return
     */
    @RequestMapping("/saveOrUpdatePromotionSpace")
    public ResponseResult saveOrUpdatePromotionSpace(@RequestBody PromotionSpace promotionSpace){

        if(promotionSpace.getId() == null){

            //添加操作
            promotionSpaceService.savePromotionSpace(promotionSpace);

            ResponseResult result = new ResponseResult(true, 200, "添加广告位信息成功", null);

            return result;
        }else {

            promotionSpaceService.updatePromotionSpace(promotionSpace);

            ResponseResult result = new ResponseResult(true, 200, "修改广告位信息成功", null);

            return result;
        }
    }

    /**
     * 回显广告位信息
     * @param id
     * @return
     */
    @RequestMapping("/findPromotionSpaceById")
    public ResponseResult findPromotionSpaceById(@RequestParam("id") int id){

        PromotionSpace promotionSpace = promotionSpaceService.findPromotionSpaceById(id);

        ResponseResult result = new ResponseResult(true, 200, "回显广告位信息成功", promotionSpace);

        return result;
    }
}
