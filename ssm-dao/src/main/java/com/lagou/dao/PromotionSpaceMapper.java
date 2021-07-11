package com.lagou.dao;

import com.lagou.entity.PromotionSpace;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * 广告位模块 dao
 */
public interface PromotionSpaceMapper extends Mapper<PromotionSpace> {

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
