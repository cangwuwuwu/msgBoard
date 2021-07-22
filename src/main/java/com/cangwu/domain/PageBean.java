package com.cangwu.domain;

import java.util.List;

/**
 * @Author: Cangwu
 * @Date: 2020/4/8 22:41
 */
public class PageBean<T> {
    /**
     * totalCount 总记录数
     * totalPage 总页码数
     * list<T> 每页的数据
     * currentPage 当前页码
     * rows 每页显示的纪录数
     */
    private int totalCount;
    private int totalPage;
    private List<T> list;
    private int currentPage;
    private int rows;

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
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

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public String toString() {
        return "PageBean{" +
                "totalCount=" + totalCount +
                ", totalPage=" + totalPage +
                ", list=" + list +
                ", currentPage=" + currentPage +
                ", rows=" + rows +
                '}';
    }
}
