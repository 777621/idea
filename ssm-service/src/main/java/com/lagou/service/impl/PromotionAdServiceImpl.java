package com.lagou.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.PromotionAdMapper;
import com.lagou.dao.PromotionSpaceMapper;
import com.lagou.entity.PromotionAd;
import com.lagou.entity.PromotionAdVo;
import com.lagou.entity.PromotionSpace;
import com.lagou.service.PromotionAdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Date;
import java.util.List;

/**
 * 广告模块  service
 */
@Service
public class PromotionAdServiceImpl implements PromotionAdService {

    @Autowired
    private PromotionAdMapper promotionAdMapper;

    @Autowired
    private PromotionSpaceMapper promotionSpaceMapper;
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
        //List<PromotionAd> promotionAdList = promotionAdMapper.findAllPromotionByPage();

        List<PromotionAd> promotionAdList = promotionAdMapper.selectAll();

        Example example = new Example(PromotionSpace.class);

        Example.Criteria criteria = example.createCriteria();

        for (PromotionAd promotionAd : promotionAdList) {

            if(promotionAd.getSpaceId() != null) {

                criteria.andEqualTo("id",promotionAd.getSpaceId());

                List<PromotionSpace> spaceList = promotionSpaceMapper.selectByExample(example);

                for (PromotionSpace promotionSpace : spaceList) {

                    promotionAd.setSpaceId(promotionSpace.getId());
                }
            }
        }
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
        promotionAdMapper.insertSelective(promotionAd);
        //promotionAdMapper.savePromotionAd(promotionAd);
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
        promotionAdMapper.updateByPrimaryKeySelective(promotionAd);
        //promotionAdMapper.updatePromotionAd(promotionAd);
    }

    /**
     * 回显广告信息
     * @param id
     * @return
     */
    @Override
    public PromotionAd findPromotionAdById(int id) {

        //return promotionAdMapper.findPromotionAdById(id);
        return promotionAdMapper.selectByPrimaryKey(id);
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
        promotionAdMapper.updateByPrimaryKeySelective(promotionAd);
        //promotionAdMapper.updatePromotionAdStatus(promotionAd);
    }
}
