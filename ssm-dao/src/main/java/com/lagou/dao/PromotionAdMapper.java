package com.lagou.dao;

import com.lagou.entity.PromotionAd;

import java.util.List;

/**
 * 广告模块
 */
public interface PromotionAdMapper {

    /**
     * 分页查询广告信息
     * @return
     */
    public List<PromotionAd> findAllPromotionByPage();

    /**
     * 添加广告信息
     * @param promotionAd
     */
    public void savePromotionAd(PromotionAd promotionAd);

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
     * 广告状态信息上下线
     * @param promotionAd
     */
    public void updatePromotionAdStatus(PromotionAd promotionAd);
}
