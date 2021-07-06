package com.lagou.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.entity.PromotionAd;
import com.lagou.entity.PromotionAdVo;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 广告模块  service
 */
@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    private PromotionAdMapper promotionAdMapper;

    /**
     * 分页查询广告信息
     * @param promotionAdVo
     * @return
     */
    @Override
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVo promotionAdVo) {

        //调用分页方法
        PageHelper.startPage(promotionAdVo.getCurrentPage(), promotionAdVo.getPageSize());

        //调用业务
        List<PromotionAd> promotionAdList = promotionAdMapper.findAllPromotionByPage();

        PageInfo<PromotionAd> pageInfo = new PageInfo<>(promotionAdList);

        return pageInfo;
    }

    /**
     * 添加广告信息
     * @param promotionAd
     */
    @Override
    public void saveOrUpdatePromotionAd(PromotionAd promotionAd) {

        //封装数据
        Date date = new Date();
        promotionAd.setCreateTime(date);
        promotionAd.setUpdateTime(date);

        //调用业务
        promotionAdMapper.savePromotionAd(promotionAd);
    }

    /**
     * 修改广告信息
     * @param promotionAd
     */
    @Override
    public void updatePromotionAd(PromotionAd promotionAd) {

        //封装数据
        promotionAd.setUpdateTime(new Date());

        //调用业务
        promotionAdMapper.updatePromotionAd(promotionAd);
    }

    /**
     * 回显广告信息
     * @param id
     * @return
     */
    @Override
    public PromotionAd findPromotionAdById(int id) {

        return promotionAdMapper.findPromotionAdById(id);
    }

    /**
     * 广告状态上下线
     * @param id
     * @param status
     */
    @Override
    public void updatePromotionAdStatus(int id, int status) {

        PromotionAd promotionAd = new PromotionAd();
        //封装数据
        promotionAd.setUpdateTime(new Date());
        promotionAd.setId(id);
        promotionAd.setStatus(status);

        //调用业务
        promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }
}
