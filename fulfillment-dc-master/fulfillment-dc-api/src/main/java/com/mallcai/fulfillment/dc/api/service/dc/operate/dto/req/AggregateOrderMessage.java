package com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 开始集单消息,集单时可能会按照消息体中的某些字段加锁，所以
 * 增加字段时需要考虑加锁的字段key
 * @author: chentao
 * @create: 2019-10-17 17:53:32
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class AggregateOrderMessage {

    /**
     *  维度值
     */
    private String groupValue;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     *  仓库id
     */
    private Integer warehousesId;
}
