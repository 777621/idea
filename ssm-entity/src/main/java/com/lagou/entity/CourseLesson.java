package com.lagou.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Date;
/**
 * 课时类
 * */
@Table(name = "course_lesson")
public class CourseLesson {

    //主键
    @Id
    @Column(name = "id")
    @GeneratedValue(generator="JDBC")
    private Integer id;

    //课程id
    @Column(name = "course_id")
    private Integer courseId;

    //章节id
    private Integer sectionId;

    //课时主题
    private String theme;

    //课时时长
    //@JsonIgnore
    private Integer duration;

    //是否免费
    private Integer isFree;

    //创建时间
    private Date createTime;

    //修改时间
    private Date updateTime;

    //是否删除
    private Integer isDel;

    //排序
    private Integer orderNum;

    //状态
    private Integer status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }


    public Integer getDuration() {
        return duration;
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

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        this.sectionId = sectionId;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getIsFree() {
        return isFree;
    }

    public void setIsFree(Integer isFree) {
        this.isFree = isFree;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(Integer orderNum) {
        this.orderNum = orderNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CourseLesson{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", sectionId=" + sectionId +
                ", theme='" + theme + '\'' +
                ", duration=" + duration +
                ", isFree=" + isFree +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDel=" + isDel +
                ", orderNum=" + orderNum +
                ", status=" + status +
                '}';
    }
}
