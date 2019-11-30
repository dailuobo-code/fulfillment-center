package com.mallcai.fulfillment.infrastructure.object.pe;

import java.io.Serializable;

/**
 * @author: Liu Yang
 * @description
 * @date: 2019-11-14 16:59
 */
public class PeOrderExtend extends PeOrder implements Serializable {
    private Integer calCount;

    public Integer getCalCount() {
        return calCount;
    }

    public void setCalCount(Integer calCount) {
        this.calCount = calCount;
    }
}
