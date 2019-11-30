package com.mallcai.fulfillment.pe.api.service.query.dto.rsp;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WareTransInfo implements Serializable {
    private Integer wareId;
    private String wareCode;
    private String wareName;
    private Date pickupDate;

    /**
     * 门店编码
     */
    private Integer storeNo;

    private Integer storeId;

    /**
     * 销售总数
     */
    private BigDecimal soldCount;
    private BigDecimal soldWeight;

    /**
     * 收货人。tbl_store.manager
     */
    private String receiver;

    /**
     * 收货人电话。tbl_store.telephone
     */
    private String tel;

    private List<GoodTransInfo> goodsList;

}
