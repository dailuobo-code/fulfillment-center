package com.mallcai.fulfillment.pe.api.service.operate.dto.req;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author bh.zhong
 * @date 2019/8/26 6:44 PM
 */
@Data
public class OrderAcceptRequestDTO implements Serializable {

    private static final long serialVersionUID = 3295849407960907982L;
    /**  订单ID. */
    private String orderId;

    /**  门店ID. */
    private Integer storeId;

    /**  用户ID. */
    private Integer userId;

    /**  订单名称. */
    private String orderName;

    /**  订单状态. */
    private Integer status;

    /**  第三方支付异步通知状态  1 异常(未收到第三方异步通知或异步通知结果与同步结果不一致)   2 正常(第三方支付异步结果已返回且异步通知结果与同步结果一致). */
    private String asynPayStatus;

    /** The pickup code. */
    private String pickupCode;

    /**  订单图片. */
    private String orderPic;

    /**  付款时间. */
    private String payTime;

    /**  生成时间. */
    private String generateTime;

    /**  关闭时间. */
    private String closeTime;

    /**  取货日期. */
    private String pickupTime;

    /**  订单实付金额. */
    private Integer orderPrice;

    /**   订单总价. */
    private Integer totalPrice;

    /**  创建记录用户id. */
    private Integer createUserId;

    /**  创建时间. */
    private String createTime;

    /**  更新记录用户id. */
    private Integer updateUserId;

    /**  更新记录时间. */
    private String updateTime;

    /**  取货开始时间. */
    private String pickupStartTime;

    /**  取货结束时间. */
    private String pickupEndTime;

    /**  备注. */
    private String memo;

    /**  城市id. */
    private Integer cityId;

    /**  逻辑删除标记  0 正常  1为删除状态. */
    private String isDel;

    /** The order detail lst. */
    private List<OrderDetailDTO> orderDetailLst;

    /**  取菜方式 0 自提  1代提. */
    private Integer pickupTypeId;

    /**  是否愿意帮人代提(0 不愿意,1愿意). */
    private Integer isWilling;

    /**  订单重量. */
    private Float weight;

    /**  余额扣除数. */
    private Integer balance;

    /**  失效时间的毫秒数. */
    private Long overTimeMills;

    /**  扩展字段. */
    private String extras;

    /** The evaluate content. */
    private String evaluateContent;

    /** The coupon id. */
    private Integer couponId;

    /** The coupon desc. */
    private String couponDesc;

    /** The refund status. */
    private Integer refundStatus;

    /** The pickup time id. */
    private Integer pickupTimeId;

    /** The change. */
    private Integer change;// 称重找零

    /** The version. */
    private Integer version;//订单版本号

    /** The pay type. */
    private Integer payType;

    /** The complete user id. */
    private Integer completeUserId;

    /** The complete time. */
    private String completeTime;

    /** The child status. */
    private Integer childStatus;

    /** The coupon value. */
    private Float couponValue;

    /**
     * 配送模式
     * */
    private Integer deliveryMode;

    /**
     * 收货人姓名
     * */
    private String recipients;

    /**
     * 收货人联系方式
     * */
    private String recipientsPhone;

    /**
     * 收货人地址
     * */
    private String recipientsAddr;

    /**
     * 运费
     * */
    private Integer freight;

    /**
     * 仓库id
     * */
    private Integer warehouseId;

    /**
     * 小区id
     * */
    private Integer residenceId;

    /**
     * 小区名称
     * */
    private String residenceName;

    private Integer limitFee;
    private String limitFeeDesc;


    private Integer replyStatus;

    /**
     * 订单类型
     * */
    private Integer orderType;

    /**
     * 团购ID
     * */
    private Integer groupId;

    private Float totalFull; //满减金额

    private Integer totalPointPrice; //总积分价格

    /**
     * 拆单类型
     */
    private Integer splitType;

    /**
     * 父单号
     */
    private String parentOrderNo;

    /**
     * 待评价后找零金额
     */
    private Integer changeAfterComment;

}
