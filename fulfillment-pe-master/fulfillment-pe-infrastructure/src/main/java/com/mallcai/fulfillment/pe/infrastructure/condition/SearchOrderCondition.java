package com.mallcai.fulfillment.pe.infrastructure.condition;

import com.mallcai.fulfillment.pe.common.page.PageQuery;
import lombok.Data;

/**
 * 分页查询订单信息
 * @author bh.zhong
 * @date 2019-08-14 00:03:14
 */
@Data
public class SearchOrderCondition extends PageQuery {

    /**
     * 门店ID
     */
    private Integer storeId;

}
