package com.example.dc.bean;

/**
 * @ Author     ：duanchao
 * @ Date       ： 9:33 2020/11/2
 * @ Description：
 */


public class PageInfoBean {
    private   int  page = 1;

    private int pageSize = 10;

    private  int  totalCount;

    private int  totalPage;


    public PageInfoBean(int page, int pageSize, int totalCount, int totalPage) {
        this.page = page;
        this.pageSize = pageSize;
        this.totalCount = totalCount;
        this.totalPage = totalPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }


    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
}