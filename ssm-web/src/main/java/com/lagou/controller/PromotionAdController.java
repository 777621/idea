package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.entity.PromotionAd;
import com.lagou.entity.PromotionAdVo;
import com.lagou.entity.ResponseResult;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 广告模块
 */
@RestController
@RequestMapping("/promotionAd")
public class PromotionAdController {

    @Autowired
    private PromotionAdService promotionAdService;

    /**
     * 分页查询广告信息
     * @param promotionAdVo
     * @return
     */
    @RequestMapping("/findAllPromotionAdByPage")
    public ResponseResult findAllPromotionAdByPage(PromotionAdVo promotionAdVo){

        PageInfo<PromotionAd> adList = promotionAdService.findAllPromotionAdByPage(promotionAdVo);

        ResponseResult result = new ResponseResult(true, 200, "分页查询广告信息成功", adList);

        return result;
    }

    /**
     * 图片上传
     * @param file
     * @param request
     * @return
     */
    @RequestMapping("/promotionAdUpload")
    public ResponseResult fileUpload(@RequestParam("file") MultipartFile file, HttpServletRequest request){

        try {
            if(file.isEmpty()){

                throw new RuntimeException();
            }

            //获取项目部署路径

            String realPath = request.getServletContext().getRealPath("/");

            String webappsPath = realPath.substring(0, realPath.indexOf("ssm_web"));

            //获取原文件名
            String originalFilename = file.getOriginalFilename();

            //设置新文件名
            String newFileName = System.currentTimeMillis() + originalFilename.substring(originalFilename.lastIndexOf("."));

            // 存到upload这个目录
            String uploadPath = webappsPath +"upload\\";
            File filePath = new File(uploadPath, newFileName);

            //判断目录是否存在
            if(!filePath.getParentFile().exists()){

                filePath.getParentFile().mkdirs();

                System.out.println("创建目录："+filePath);
            }

            file.transferTo(filePath);

            //将新文件名 文件路径返回
            Map<String,String> map = new HashMap<>();

            map.put("fileName",newFileName);

            map.put("filePath","http://localhost:8080/upload/"+newFileName);

            ResponseResult result = new ResponseResult(true, 200, "图片上传成功", map);

            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 添加或修改广告信息
     * @param promotionAd
     * @return
     */
    @RequestMapping("/saveOrUpdatePromotionAd")
    public ResponseResult saveOrUpdatePromotionAd(@RequestBody PromotionAd promotionAd){

        if(promotionAd.getId() == null){

            //添加操作
            promotionAdService.saveOrUpdatePromotionAd(promotionAd);

            ResponseResult result = new ResponseResult(true, 200, "添加广告信息成功", null);

            return result;
        }else {

            //修改操作
            promotionAdService.updatePromotionAd(promotionAd);

            ResponseResult result = new ResponseResult(true, 200, "修改广告信息成功", null);

            return result;
        }
    }

    /**
     * 回显广告信息
     * @param id
     * @return
     */
    @RequestMapping("/findPromotionAdById")
    public ResponseResult findPromotionById(@RequestParam("id") int id){

        PromotionAd promotionAd = promotionAdService.findPromotionAdById(id);

        ResponseResult result = new ResponseResult(true, 200, "回显广告信息成功", promotionAd);

        return result;
    }

    /**
     * 广告状态信息上下线
     * @param id
     * @param status
     * @return
     */
    @RequestMapping("/updatePromotionAdStatus")
    public ResponseResult updatePromotionAdStatus(@RequestParam("id") int id,@RequestParam("status")int status){

        promotionAdService.updatePromotionAdStatus(id,status);

        ResponseResult result = new ResponseResult(true, 200, "广告状态信息上下线成功", null);

        return result;
    }
}
