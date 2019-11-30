package com.mallcai.fulfillment.pe.api.service;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页查询DTO
 * @author zhanghao
 * @date 2019-08-13 23:52:44
 */
@Data
public class PagedDTO<T> implements Serializable {

    private static final long serialVersionUID = -1;

    /** 数据 */
    private List<T> data;

    /**
     * 总数
     */
    private Integer count;
}
