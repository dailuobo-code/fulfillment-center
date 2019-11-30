package com.mallcai.fulfillment.pe.api.service.operate.dto.req;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author bh.zhong
 * @date 2019/8/26 6:45 PM
 */
@Data
public class OrderDetailDTO implements Serializable {

    private static final long serialVersionUID = -2532321638393963801L;
    private Integer id;

    /**
     * The order id.
     */
    private String orderId;

    /**
     * The spec id.
     */
    private Integer specId;

    /**
     * The product num.
     */
    private Integer productNum;

    /**
     * The prod show name.
     */
    private String prodShowName;

    /**
     * The price.
     */
    private Integer price;

    private Integer originalPrice;

    private Integer vipPrice;

    private Integer couponPrice;

    private Integer balance;

    /**
     * The city product id.
     */
    private Integer cityProductId;

    /**
     * The create time.
     */
    private String createTime;

    /**
     * The update time.
     */
    private String updateTime;

    /**
     * The user id.
     */
    private Integer userId;

    /**
     * The city id.
     */
    private Integer cityId;

    /**
     * The store id.
     */
    private Integer storeId;

    /**
     * The is pay.
     */
    private String isPay;

    /**
     * The pay time.
     */
    private String payTime;

    // v1.0.2 增加
    /**
     * The spec name.
     */
    private String specName;

    /**
     * The prod icon.
     */
    private String prodIcon;

    /**
     * The star num.
     */
    private Integer starNum;

    /**
     * 找零标志：1，找零；2，不找零
     */
    private Integer changeFlag;

    /**
     * 重量单位
     */
    private String weightUnit;

    /**
     * 数量单位
     */
    private String numUnit;

    /**
     * 数量上限
     */
    private Integer maxNum;

    /**
     * 数量下限
     */
    private Integer minNum;

    /**
     * 重量上限
     */
    private Float maxWeight;

    /**
     * 重量下限
     */
    private Float minWeight;

    //单位类型：1，重量单位；2，数量单位
    private Integer unitType;

    // 打包上限
    private Integer packageMaxWeight;

    /**
     * 商品信息
     * */
    private String productInfo;

    /**
     * 是否赠品：0，否；1，是
     */
    private Integer isGift;

    //订单中商品的满减总金额
    private Integer productActivityTotalPrice;
    //订单中商品满减后的单价
    private Integer productActivityDivPrice;
    //满减活动ID Full_Id
    private Integer activityId;

    private Integer pointPrice; //积分单价

    //商品类目
    private String l1L2Names;

    /**
     * 商品优惠总额，单位分
     */
    private Integer totalDiscount;

    /**
     * 开发票存储json
     */
    private String remark1;

    /**
     * 商家id
     */
    private Integer merchantId;

    public Map<String, String> getProductInfoAsMap() {
        if (StringUtils.isBlank(this.productInfo)) {
            return new HashMap();
        } else {
            Map<String, String> productInfoMap = (Map)JSON.parseObject(this.productInfo, new TypeReference<Map<String, String>>() {
            }, new Feature[0]);
            return productInfoMap;
        }
    }

}
