package com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class OrderItemInfoDTO implements Serializable {

    private static final long serialVersionUID = -3473015458098674144L;
    private Long id;

    private String orderNo;

    private Byte orderType;

    private Byte orderSource;

    private Integer cityProductId;

    private Integer specId;

    private BigDecimal productNum;

    private Integer storeId;

    private Integer userId;

    private String pickupTime;

    private Integer cityId;

    private Integer warehouseId;

    private String productNo;

    private String itemExt;

    private Integer version;

    private Date createTime;

    private Date updateTime;

}