package com.lagou.service.impl;

import com.lagou.dao.PromotionSpaceMapper;
import com.lagou.entity.PromotionSpace;
import com.lagou.service.PromotionSpaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class PromotionSpaceServiceImpl implements PromotionSpaceService {


    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;

    /**
     * 查询广告位列表信息
     * @return
     */
    @Override
    public List<PromotionSpace> findAllPromotionSpace() {

        return promotionSpaceMapper.findAllPromotionSpace();
    }

    /**
     * 添加广告位信息
     * @param promotionSpace
     */
    @Override
    public void savePromotionSpace(PromotionSpace promotionSpace) {

        //封装数据
        promotionSpace.setSpaceKey(UUID.randomUUID().toString());
        Date date = new Date();
        promotionSpace.setCreateTime(date);
        promotionSpace.setUpdateTime(date);
        promotionSpace.setIsDel(0);

        //调用业务
        promotionSpaceMapper.savePromotionSpace(promotionSpace);
    }

    /**
     * 回显广告位信息
     * @param id
     * @return
     */
    @Override
    public PromotionSpace findPromotionSpaceById(int id) {

        return promotionSpaceMapper.findPromotionSpaceById(id);
    }

    /**
     * 修改广告位信息
     * @param promotionSpace
     */
    @Override
    public void updatePromotionSpace(PromotionSpace promotionSpace) {

        //封装数据
        promotionSpace.setUpdateTime(new Date());

        //调用业务
        promotionSpaceMapper.updatePromotionSpace(promotionSpace);

    }
}
