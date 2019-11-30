package com.mallcai.fulfillment.common.page;

/**
 * PageQuery
 *
 * @author zhanghao
 * @date 2019-08-14 00:26:28
 */
public class PageQuery {

    private int page;

    private int pageSize;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setPage(Integer page, int defaultPage) {
        this.page = (null == page || page < 0) ? defaultPage : page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setPageSize(Integer pageSize, int defaultPageSize) {
        this.pageSize = (null == pageSize || pageSize < 1) ? defaultPageSize : pageSize;
    }
}
