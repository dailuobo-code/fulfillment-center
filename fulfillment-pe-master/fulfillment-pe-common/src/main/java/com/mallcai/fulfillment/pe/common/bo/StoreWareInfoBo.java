package com.mallcai.fulfillment.pe.common.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreWareInfoBo {
    private Integer storeId;
    private String storeName;
    private Integer storeNo;
    private String storeAddr;
    private String storePhone;
    private String StoreManager;
    private Integer wareId;
    private String wareName;
    private String wareCode;

}
