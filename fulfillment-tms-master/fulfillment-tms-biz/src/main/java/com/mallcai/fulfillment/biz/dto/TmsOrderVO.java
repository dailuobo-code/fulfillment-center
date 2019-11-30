package com.mallcai.fulfillment.biz.dto;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
public class TmsOrderVO implements Serializable {
    /**
     * 订单号
     */
    private String WaybillNO;

    /**
     * 项目名称
     */
    private String ProjectName;

    /**
     * 发货方区域编码
     */
    private String ShipperAreaCode;

    /**
     * 发货方名称/联系人
     */
    private String ShipperName;

    /**
     * 发货方手机
     */
    private String ShipperPhone;

    /**
     * 发货方邮箱
     */
    private String ShipperEmail;

    /**
     * 收货方区域编码
     */
    private String ReceiverAreaCode;

    /**
     * 收货方名称/联系人
     */
    private String ReceiverName;

    /**
     * 收货方手机
     */
    private String ReceiverPhone;

    /**
     * 收货方邮箱
     */
    private String ReceiverEmail;

    /**
     * 附加要求
     */
    private String AddRequir;

    /**
     * 货运类型
     */
    private String FreightType;

    /**
     * 运输费用
     */
    private BigDecimal TransportFee;

    /**
     * 提货费
     */
    private BigDecimal CarryGoodsFee;

    /**
     * 送货费
     */
    private BigDecimal DeliveryFee;

    /**
     * 装货费
     */
    private BigDecimal LoadingFee;

    /**
     * 卸货费
     */
    private BigDecimal UnLoadingFee;

    /**
     * 其它费用
     */
    private BigDecimal OtherFee;

    /**
     * 订单备注(sku总重)
     */
    private String WaybillRemark;

    /**
     * 调度部门名称
     */
    private String DispatchPointName;

    /**
     * 库位名称
     */
    private String StorageLocation;

    /**
     * 配货员姓名
     */
    private String AllocationName;

    /**
     * 配货员电话
     */
    private String AllocationPhone;

    /**
     * 商品集合
     */
    private List<TmsSkuVO> GoodsList;
}
