package com.lagou.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用户管理 分页和条件查询 参数
 */
public class UserVo {

    private Integer currentPage;

    private Integer pageSize;

    private String name;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date startCreateTime;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date endCreateTime;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartCreateTime() {
        return startCreateTime;
    }

    public void setStartCreateTime(Date startCreateTime) {
        this.startCreateTime = startCreateTime;
    }

    public Date getEndCreateTime() {
        return endCreateTime;
    }

    public void setEndCreateTime(Date endCreateTime) {
        this.endCreateTime = endCreateTime;
    }
}
