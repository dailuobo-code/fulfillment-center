package com.mallcai.fulfillment.pe.api.service.operate.dto.req;

import lombok.Data;

/**
 * @description: 仓库信息
 * @author: chentao
 * @create: 2019-09-12 17:36:19
 */
@Data
public class WareHouseInfo {

    /**
     * 仓库ID
     */
    private Integer wareHouseId;

    /**
     * 仓库类型,详情见WareHouseTypeEnum枚举
     */
    private Integer type;

    /**
     * 仓库路线顺序，1-n,1表示第一个
     */
    private Integer order;

    public WareHouseInfo(){}

    public WareHouseInfo(Integer wareHouseId, Integer type, Integer order){

        this.wareHouseId = wareHouseId;
        this.type = type;
        this.order = order;
    }
}
