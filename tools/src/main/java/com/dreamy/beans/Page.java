package com.dreamy.beans;

/**
 * 应用分页的对象
 * Created by wangyongxing on 16/3/31.
 */

import java.io.Serializable;


public class Page implements Serializable {

    private static final Integer DEFUALT_PAGE_SIZE = 30;

    private static final Integer DEFAULT_TOTAL_NUM = 0;
    /**
     * 分页中，页面大小，即每页显示多少的数据
     */
    private Integer pageSize = DEFUALT_PAGE_SIZE;

    private Integer currentPage = 1;

    private Integer totalNum = DEFAULT_TOTAL_NUM;

    private Integer startIndex;

    private Integer endIndex;

    private Integer totalPage;

    public Page(Integer pageSize, Integer currentPage) {
        this.pageSize = pageSize;
        setCurrentPage(currentPage);
    }

    public Page(Integer pageSize, Integer currentPage, Integer totalNum) {
        super();
        this.pageSize = pageSize;
        this.currentPage = currentPage;
        this.totalNum = totalNum;
    }

    public Page() {
        setPageSize(DEFUALT_PAGE_SIZE);
    }

    public Integer getTotalPageNum() {
        if (totalNum == 0) {
            return Integer.valueOf(0);
        }
        return totalNum / pageSize + (totalNum % pageSize == 0 ? 0 : 1);
    }

    /**
     * @return the pageSize
     */
    public Integer getPageSize() {
        return pageSize;
    }

    /**
     * @param pageSize the pageSize to set
     */
    public void setPageSize(Integer pageSize) {
        if (pageSize == null) {
            this.pageSize = DEFUALT_PAGE_SIZE;
        } else {
            this.pageSize = pageSize;
        }
        caculatIndex();
    }

    /**
     * @return the currentPage
     */
    public Integer getCurrentPage() {
        return currentPage;
    }

    /**
     * @param currentPage the currentPage to set
     */
    public void setCurrentPage(Integer currentPage) {
        if (currentPage == null) {
            this.currentPage = Integer.valueOf(1);
        } else {
            this.currentPage = currentPage;
        }
        caculatIndex();
    }

    /**
     * @return the totalNum
     */
    public Integer getTotalNum() {
        return totalNum;
    }

    /**
     * @param totalNum the totalNum to set
     */
    public void setTotalNum(Integer totalNum) {
        if (totalNum == null) {
            this.totalNum = DEFAULT_TOTAL_NUM;
            return;
        }
        this.totalNum = totalNum;
    }

    private void caculatIndex() {
        if (this.currentPage == null || this.currentPage <= 1) {
            this.currentPage = 1;
        }
        if (this.pageSize == null || this.pageSize <= 0) {
            this.pageSize = DEFUALT_PAGE_SIZE;
        }
        this.startIndex = (this.currentPage - 1) * this.pageSize;
        this.endIndex = this.pageSize;
    }

    public Integer getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getEndIndex() {
        return endIndex;
    }

    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
    }

    public Integer getTotalPage() {
        return 1 + this.getTotalNum() / this.getPageSize();
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public boolean isHasNextPage() {
        return (this.currentPage<this.getTotalPage()) ? true : false;
    }

    public static void main(String[] args) {
        System.out.println(111);
    }
}

