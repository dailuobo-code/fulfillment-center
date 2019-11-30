package com.mallcai.fulfillment.infrastructure.object.wms;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OubDeliveryKey {
    private String warehouseCode;
    private LocalDate pickupDate;
    private Integer storeNo;
    private String skuCode;
}
