package com.lagou.entity;

/**
 * 分页参数  接收前端参数
 */
public class PromotionAdVo {

    private Integer currentPage;   //当前页

    private Integer pageSize;     //每页条数

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
}
