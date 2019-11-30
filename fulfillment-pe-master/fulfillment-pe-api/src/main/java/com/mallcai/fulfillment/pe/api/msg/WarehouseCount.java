package com.mallcai.fulfillment.pe.api.msg;

import lombok.Data;

/**
 * 仓库汇总
 * @author bh.zhong
 * @date 2019/11/15 12:30 AM
 */
@Data
public class WarehouseCount {
    /**
     * 仓库id
     */
    private Integer warehouseId;
    /**
     * 总数
     */
    private Integer count;
    /**
     * 2：冻品 3：生鲜
     */
    private Integer orderType;
}
