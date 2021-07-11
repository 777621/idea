package com.lagou.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * 章节类
 * */
@Table(name = "course_section")
public class CourseSection {

    //id
    @Id
    @Column(name = "id")
    @GeneratedValue(generator="JDBC")
    private Integer id;

    //课程id
    @Column(name = "course_id")
    private Integer courseId;

    //章节名
    private String sectionName;

    //章节描述
    private String description;

    //创建时间
    private Date createTime;

    //更新时间
    private Date updateTime;

    //是否删除
    private Integer isDel;

    //排序
    private Integer orderNum;

    //状态
    private Integer status;

    //课时集合
    @Transient
    private List<CourseLesson> lessonList;

    public List<CourseLesson> getLessonList() {
        return lessonList;
    }

    public void setLessonList(List<CourseLesson> lessonList) {
        this.lessonList = lessonList;
    }


    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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
        return "CourseSection{" +
                "id=" + id +
                ", courseId=" + courseId +
                ", sectionName='" + sectionName + '\'' +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", isDel=" + isDel +
                ", orderNum=" + orderNum +
                ", status=" + status +
                ", lessonList=" + lessonList +
                '}';
    }
}
