package com.mallcai.fulfillment.pe.api.msg;

import com.alibaba.fastjson.JSONObject;
import com.mallcai.fulfillment.pe.api.constants.ProduceOrderExtKey;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.CommodityInfo;
import com.mallcai.fulfillment.pe.api.service.operate.dto.req.WareHouseInfo;
import com.mallcai.fulfillment.pe.common.exception.BizException;
import com.mallcai.fulfillment.pe.common.exception.Errors;
import lombok.Data;

import java.util.List;

/**
 * @description: 生产单info
 * @author: bh.zbong
 * @create: 2019-08-22 22:35:12
 */
@Data
public class ProduceOrderInfoMsg {

    /**
     * 生产订单号
     */
    private String produceOrderNo;

    /**
     * 门店ID
     */
    private Integer storeId;

    /**
     * 城市id
     */
    private Integer cityId;

    /**
     *  取货日期,格式:yyyy-MM-dd
     */
    private String pickupTime;

    /**
     * 仓库信息
     */
    List<WareHouseInfo> wareHouseInfos;

    /**
     * 客户联系方式
     */
    private String recipientsPhone;

    /**
     * 商品明细
     */
    private List<CommodityInfo> commodities;

    /**
     * 订单类型
     */
    private Integer orderType;

    /**
     * 生产单扩展字段，json格式。
     */
    private String orderExt;

    public void buildOrderExt(Integer orderType, String isGroupBuying){

        JSONObject orderExtJson = new JSONObject();

        if (orderType == null){

            throw new BizException(Errors.FULFILLMENT_SYSTEM_ERROR);
        }

        // 冻品、生鲜全部标识非团购
        if (orderType == 2 || orderType == 3) {

            orderExtJson.put(ProduceOrderExtKey.IS_GROUP_BUYING, "0");
        } else {
            orderExtJson.put(ProduceOrderExtKey.IS_GROUP_BUYING, isGroupBuying);
        }

        orderExt = orderExtJson.toJSONString();
    }

}
