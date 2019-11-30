package com.mallcai.fulfillment.infrastructure.object.erp;

import java.io.Serializable;

public class OmsStandardOrderExtend extends OmsStandardOrder implements Serializable {
    private Integer calCount;

    private Integer warehouseId;

    public Integer getCalCount() {
        return calCount;
    }

    public void setCalCount(Integer calCount) {
        this.calCount = calCount;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }
}