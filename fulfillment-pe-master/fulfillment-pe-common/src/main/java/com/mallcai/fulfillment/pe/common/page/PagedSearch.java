package com.mallcai.fulfillment.pe.common.page;

import java.util.List;

/**
 * 分页查询结果对象
 *
 * TODO 等统一好规范统一处理
 * @author zhanghao
 * @date 2019-08-13 23:53:00
 */
public class PagedSearch<R> {

    private long total;

    private List<R> data;

    public PagedSearch(long total, List<R> data){
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