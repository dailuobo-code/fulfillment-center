package com.mallcai.fulfillment.pe.biz.service.bo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AggregateInfo {

    /**
     * 集单时使用的Key
     */
    private String key;

    /**
     * 存储时使用的Key
     */
    private String storeKey;

}
