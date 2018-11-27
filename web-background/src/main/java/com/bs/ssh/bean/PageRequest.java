package com.bs.ssh.bean;

/**
 * 分页请求
 *
 * @author Egan
 * @date 2018/11/24 16:18
 **/
public class PageRequest {
    //当前页面
    private int pageNumber;
    //单页最大记录数
    private int pageSize;

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
