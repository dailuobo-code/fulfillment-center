package com.mallcai.fulfillment.pe.biz.service.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransDbDataBo {
    private Integer cityId;
    private Integer cityProductId;
    private Integer storeId;
    private BigDecimal productNum;
    private String prodInfo;
}
