package com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject;

import lombok.Data;

import java.io.Serializable;

/**
 * ModifyLog
 * @author zhanghao
 * @date 2019-08-16 15:31:34
 */
@Data
public class ModifyLogDO implements Serializable {

    private static final long serialVersionUID = 6336314270654570634L;

    private Integer getStatus;
    private String pickupdate;

}
