package com.lagou.service;

import com.lagou.entity.PromotionSpace;

import java.util.List;

/**
 * 广告位模块 service接口
 */
public interface PromotionSpaceService {

    /**
     * 广告位列表查询
     * @return
     */
    public List<PromotionSpace> findAllPromotionSpace();

    /**
     * 添加广告位列表信息
     * @param promotionSpace
     */
    public void savePromotionSpace(PromotionSpace promotionSpace);

    /**
     * 回显广告位信息
     * @param id
     * @return
     */
    public PromotionSpace findPromotionSpaceById(int id);

    /**
     * 修改广告位列表信息
     * @param promotionSpace
     */
    public void updatePromotionSpace(PromotionSpace promotionSpace);
}
