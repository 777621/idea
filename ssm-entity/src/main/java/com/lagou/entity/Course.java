package com.lagou.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * 课程类
 */
@Table(name = "course")
public class Course implements Serializable {

    //主键
    @Id
    @Column(name = "id")
    @GeneratedValue(generator="JDBC")
    private int id;

    //课程名称
    private String courseName;

    //课程一句话简介
    private String brief;

    //原价
    private Double price;

    //原价标签
    private String priceTag;

    //优惠价
    private Double discounts;

    //优惠价标签
    private String discountsTag;

    //课程内容markdown
    private String courseDescriptionMarkDown;

    //课程描述
    private String courseDescription;

    //课程分享图片url
    private String courseImgUrl;

    //是否新品
    private Integer isNew;

    //广告语
    private String isNewDes;

    //最后操作者
    private Integer lastOperatorId;

    //自动上架时间
    private Date autoOnlineTime;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

    //是否删除
    private Integer isDel;

    //总时长
    private Integer totalDuration;

    //课程列表展示图片
    private String courseListImg;

    //课程状态，0-草稿，1-上架
    private Integer status;

    //课程排序
    private Integer sortNum;

    //课程预览第一个字段
    private String previewFirstField;

    //课程预览第二个字段
    private String previewSecondField;

    //销量
    private Integer sales;


    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }


    public String getPriceTag() {
        return priceTag;
    }

    public void setPriceTag(String priceTag) {
        this.priceTag = priceTag;
    }



    public String getDiscountsTag() {
        return discountsTag;
    }

    public void setDiscountsTag(String discountsTag) {
        this.discountsTag = discountsTag;
    }

    public String getCourseDescriptionMarkDown() {
        return courseDescriptionMarkDown;
    }

    public void setCourseDescriptionMarkDown(String courseDescriptionMarkDown) {
        this.courseDescriptionMarkDown = courseDescriptionMarkDown;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getCourseImgUrl() {
        return courseImgUrl;
    }

    public void setCourseImgUrl(String courseImgUrl) {
        this.courseImgUrl = courseImgUrl;
    }

    public Double getPrice() {
        return price;
    }

    public Double getDiscounts() {
        return discounts;
    }

    public String getIsNewDes() {
        return isNewDes;
    }

    public void setIsNewDes(String isNewDes) {
        this.isNewDes = isNewDes;
    }

    public Integer getLastOperatorId() {
        return lastOperatorId;
    }

    public Date getAutoOnlineTime() {
        return autoOnlineTime;
    }

    public void setAutoOnlineTime(Date autoOnlineTime) {
        this.autoOnlineTime = autoOnlineTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }


    public String getCourseListImg() {
        return courseListImg;
    }

    public void setCourseListImg(String courseListImg) {
        this.courseListImg = courseListImg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getPreviewFirstField() {
        return previewFirstField;
    }

    public void setPreviewFirstField(String previewFirstField) {
        this.previewFirstField = previewFirstField;
    }

    public String getPreviewSecondField() {
        return previewSecondField;
    }

    public void setPreviewSecondField(String previewSecondField) {
        this.previewSecondField = previewSecondField;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setDiscounts(Double discounts) {
        this.discounts = discounts;
    }

    public Integer getIsNew() {
        return isNew;
    }

    public void setIsNew(Integer isNew) {
        this.isNew = isNew;
    }

    public void setLastOperatorId(Integer lastOperatorId) {
        this.lastOperatorId = lastOperatorId;
    }

    public Integer getTotalDuration() {
        return totalDuration;
    }

    public void setTotalDuration(Integer totalDuration) {
        this.totalDuration = totalDuration;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + id +
                ", courseName='" + courseName + '\'' +
                ", brief='" + brief + '\'' +
                ", price=" + price +
                ", priceTag='" + priceTag + '\'' +
                ", discounts=" + discounts +
                ", discountsTag='" + discountsTag + '\'' +
                ", courseDescriptionMarkDown='" + courseDescriptionMarkDown + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                ", courseImgUrl='" + courseImgUrl + '\'' +
                ", isNew=" + isNew +
                ", isNewDes='" + isNewDes + '\'' +
                ", lastOperatorId=" + lastOperatorId +
                ", autoOnlineTime=" + autoOnlineTime +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDel=" + isDel +
                ", totalDuration=" + totalDuration +
                ", courseListImg='" + courseListImg + '\'' +
                ", status=" + status +
                ", sortNum=" + sortNum +
                ", previewFirstField='" + previewFirstField + '\'' +
                ", previewSecondField='" + previewSecondField + '\'' +
                ", sales=" + sales +
                '}';
    }
}
