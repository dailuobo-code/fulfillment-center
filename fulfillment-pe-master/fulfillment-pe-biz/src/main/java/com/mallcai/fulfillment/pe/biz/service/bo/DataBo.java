package com.mallcai.fulfillment.pe.biz.service.bo;

import com.mallcai.fulfillment.pe.infrastructure.OrderQueryDto;
import lombok.Data;

/**
 * @description: 数据查询条件、过滤条件
 * @author: chentao
 * @create: 2019-09-18 01:21:16
 */

@Data
public class DataBo {

    /**
     * 数据查询条件
     */
    private OrderQueryDto orderQueryDto;

    /**
     * 数据过滤条件
     */
    private OrderFilterDto orderFilterDto;

    public DataBo(){}

    public DataBo(OrderQueryDto orderQueryDto, OrderFilterDto orderFilterDto){

        this.orderFilterDto = orderFilterDto;
        this.orderQueryDto = orderQueryDto;
    }
}
