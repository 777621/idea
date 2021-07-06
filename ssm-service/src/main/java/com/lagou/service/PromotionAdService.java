package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.entity.PromotionAd;
import com.lagou.entity.PromotionAdVo;

import java.util.List;

/**
 * 广告模块
 */
public interface PromotionAdService {

    /**
     * 分页查询广告信息
     * @return
     */
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVo promotionAdVo);

    /**
     * 添加广告信息
     * @param promotionAd
     */
    public void saveOrUpdatePromotionAd(PromotionAd promotionAd);

    /**
     * 修改广告信息
     * @param promotionAd
     */
    public void updatePromotionAd(PromotionAd promotionAd);

    /**
     * 回显广告信息
     * @param id
     * @return
     */
    public PromotionAd findPromotionAdById(int id);

    /**
     * 广告状态上下线
     * @param id
     * @param status
     */
    public void updatePromotionAdStatus(int id,int status);
}
