package com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 门店信息
 * @author bh.zhong
 * @date 2019/8/14 2:40 PM
 */
@Data
public class StoreInfoDTO implements Serializable {
    private static final long serialVersionUID = 5201933425118477743L;
    /**
     * 城市id
     */
    private Integer cityId;
    /**
     * 门店id
     */
    private Integer storeId;
    /**
     * 当前批次，门店数量
     */
    private Integer shopCount;
    /**
     * 订单号
     */
    private List<String> orderNO;

}
