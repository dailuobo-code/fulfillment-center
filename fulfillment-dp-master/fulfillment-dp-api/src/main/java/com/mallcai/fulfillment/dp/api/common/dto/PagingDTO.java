package com.mallcai.fulfillment.dp.api.common.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yl
 * @description 分页查询dto
 * @date 2019-07-15
 */
@Data
public class PagingDTO implements Serializable {

    private static final long serialVersionUID = 3058062162605289253L;
    /**
     * 偏移量
     */
    private Integer offset;

    /**
     * 每页总数
     */
    private Integer limit;
}
