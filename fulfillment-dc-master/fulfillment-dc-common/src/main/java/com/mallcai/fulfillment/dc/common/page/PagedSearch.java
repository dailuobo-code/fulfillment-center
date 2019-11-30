package com.mallcai.fulfillment.dc.common.page;

import java.util.List;

/**
 * 分页查询结果对象
 * <p>
 *
 * @author bh.zhong
 * @date 2019年08月13日23:43:10
 */
public class PagedSearch<R> {

    private long total;

    private List<R> data;

    public PagedSearch(long total, List<R> data) {
        this.total = total;
        this.data = data;
    }

    public long getTotal() {
        return total;
    }

    public List<R> getData() {
        return data;
    }
}