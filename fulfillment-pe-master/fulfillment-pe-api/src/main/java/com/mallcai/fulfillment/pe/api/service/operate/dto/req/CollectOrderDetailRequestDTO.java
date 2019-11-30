package com.mallcai.fulfillment.pe.api.service.operate.dto.req;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wangjingcheng
 */
public class CollectOrderDetailRequestDTO implements Serializable, Comparable<CollectOrderDetailRequestDTO> {

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

    public CollectOrderDetailRequestDTO() {
        this.payTime = "";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取  orderId.
     *
     * @return orderId
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * 设置 orderId.
     *
     * @param orderId the new order id
     */
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }


    /**
     * 获取  specId.
     *
     * @return specId
     */
    public Integer getSpecId() {
        return specId;
    }

    /**
     * 设置 specId.
     *
     * @param specId the new spec id
     */
    public void setSpecId(Integer specId) {
        this.specId = specId;
    }


    /**
     * 获取  productNum.
     *
     * @return productNum
     */
    public Integer getProductNum() {
        return productNum;
    }

    /**
     * 设置 productNum.
     *
     * @param productNum the new product num
     */
    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }


    /**
     * 获取  price.
     *
     * @return price
     */
    public Integer getPrice() {
        return price;
    }

    /**
     * 设置 price.
     *
     * @param price the new price
     */
    public void setPrice(Integer price) {
        this.price = price;
    }


    /**
     * 获取  cityProductId.
     *
     * @return cityProductId
     */
    public Integer getCityProductId() {
        return cityProductId;
    }

    /**
     * 设置 cityProductId.
     *
     * @param cityProductId the new city product id
     */
    public void setCityProductId(Integer cityProductId) {
        this.cityProductId = cityProductId;
    }

    /**
     * 获取  createTime
     *
     * @return createTime
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * 设置 createTime
     *
     * @param createTime
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取  updateTime
     *
     * @return updateTime
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置 updateTime
     *
     * @param updateTime
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取  userId
     *
     * @return userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置 userId
     *
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取  cityId
     *
     * @return cityId
     */
    public Integer getCityId() {
        return cityId;
    }

    /**
     * 设置 cityId
     *
     * @param cityId
     */
    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    /**
     * 获取  storeId
     *
     * @return storeId
     */
    public Integer getStoreId() {
        return storeId;
    }

    /**
     * 设置 storeId
     *
     * @param storeId
     */
    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    /**
     * 获取  isPay
     *
     * @return isPay
     */
    public String getIsPay() {
        return isPay;
    }

    /**
     * 设置 isPay
     *
     * @param isPay
     */
    public void setIsPay(String isPay) {
        this.isPay = isPay;
    }

    /**
     * 获取  payTime
     *
     * @return payTime
     */
    public String getPayTime() {
        return payTime;
    }

    /**
     * 设置 payTime
     *
     * @param payTime
     */
    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    /**
     * 获取  prodShowName
     *
     * @return prodShowName
     */
    public String getProdShowName() {
        return prodShowName;
    }

    /**
     * 设置 prodShowName
     *
     * @param prodShowName
     */
    public void setProdShowName(String prodShowName) {
        this.prodShowName = prodShowName;
    }

    @Override
    public int compareTo(CollectOrderDetailRequestDTO o) {
        return specId - o.getSpecId();
    }

    /**
     * 获取  specName
     *
     * @return specName
     */
    public String getSpecName() {
        return specName;
    }

    /**
     * 设置 specName
     *
     * @param specName
     */
    public void setSpecName(String specName) {
        this.specName = specName;
    }

    /**
     * 获取  prodIcon
     *
     * @return prodIcon
     */
    public String getProdIcon() {
        return prodIcon;
    }

    /**
     * 设置 prodIcon
     *
     * @param prodIcon
     */
    public void setProdIcon(String prodIcon) {
        this.prodIcon = prodIcon;
    }

    /**
     * 获取  starNum
     *
     * @return starNum
     */
    public Integer getStarNum() {
        return starNum;
    }

    /**
     * 设置 starNum
     *
     * @param starNum
     */
    public void setStarNum(Integer starNum) {
        this.starNum = starNum;
    }

    /**
     * 获取  changeFlag
     *
     * @return changeFlag
     */
    public Integer getChangeFlag() {
        return changeFlag;
    }

    /**
     * 设置 changeFlag
     *
     * @param changeFlag
     */
    public void setChangeFlag(Integer changeFlag) {
        this.changeFlag = changeFlag;
    }

    /**
     * 获取  weightUnit
     *
     * @return weightUnit
     */
    public String getWeightUnit() {
        return weightUnit;
    }

    /**
     * 设置 weightUnit
     *
     * @param weightUnit
     */
    public void setWeightUnit(String weightUnit) {
        this.weightUnit = weightUnit;
    }

    /**
     * 获取  numUnit
     *
     * @return numUnit
     */
    public String getNumUnit() {
        return numUnit;
    }

    /**
     * 设置 numUnit
     *
     * @param numUnit
     */
    public void setNumUnit(String numUnit) {
        this.numUnit = numUnit;
    }

    /**
     * 获取  maxNum
     *
     * @return maxNum
     */
    public Integer getMaxNum() {
        return maxNum;
    }

    /**
     * 设置 maxNum
     *
     * @param maxNum
     */
    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    /**
     * 获取  minNum
     *
     * @return minNum
     */
    public Integer getMinNum() {
        return minNum;
    }

    /**
     * 设置 minNum
     *
     * @param minNum
     */
    public void setMinNum(Integer minNum) {
        this.minNum = minNum;
    }

    /**
     * 获取  maxWeight
     *
     * @return maxWeight
     */
    public Float getMaxWeight() {
        return maxWeight;
    }

    /**
     * 设置 maxWeight
     *
     * @param maxWeight
     */
    public void setMaxWeight(Float maxWeight) {
        this.maxWeight = maxWeight;
    }

    /**
     * 获取  minWeight
     *
     * @return minWeight
     */
    public Float getMinWeight() {
        return minWeight;
    }

    /**
     * 设置 minWeight
     *
     * @param minWeight
     */
    public void setMinWeight(Float minWeight) {
        this.minWeight = minWeight;
    }

    /**
     * 获取  unitType
     *
     * @return unitType
     */
    public Integer getUnitType() {
        return unitType;
    }

    /**
     * 设置 unitType
     *
     * @param unitType
     */
    public void setUnitType(Integer unitType) {
        this.unitType = unitType;
    }

    /**
     * 获取  packageMaxWeight
     *
     * @return packageMaxWeight
     */
    public Integer getPackageMaxWeight() {
        return packageMaxWeight;
    }

    /**
     * 设置 packageMaxWeight
     *
     * @param packageMaxWeight
     */
    public void setPackageMaxWeight(Integer packageMaxWeight) {
        this.packageMaxWeight = packageMaxWeight;
    }

    public Integer getIsGift() {
        return isGift;
    }

    public void setIsGift(Integer isGift) {
        this.isGift = isGift;
    }

    public Integer getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Integer originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Integer getVipPrice() {
        return vipPrice;
    }

    public void setVipPrice(Integer vipPrice) {
        this.vipPrice = vipPrice;
    }

    public Integer getCouponPrice() {
        return couponPrice;
    }

    public void setCouponPrice(Integer couponPrice) {
        this.couponPrice = couponPrice;
    }


    public Integer getProductActivityTotalPrice() {
        return productActivityTotalPrice;
    }

    public void setProductActivityTotalPrice(Integer productActivityTotalPrice) {
        this.productActivityTotalPrice = productActivityTotalPrice;
    }

    public Integer getProductActivityDivPrice() {
        return productActivityDivPrice;
    }

    public void setProductActivityDivPrice(Integer productActivityDivPrice) {
        this.productActivityDivPrice = productActivityDivPrice;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public Integer getPointPrice() {
        return pointPrice;
    }

    public void setPointPrice(Integer pointPrice) {
        this.pointPrice = pointPrice;
    }

    public String getL1L2Names() {
        return l1L2Names;
    }

    public void setL1L2Names(String l1L2Names) {
        this.l1L2Names = l1L2Names;
    }

    public Integer getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(Integer totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public String getProductInfo() {
        return productInfo;
    }


    public Map<String, String> getProductInfoAsMap() {
        if (StringUtils.isBlank(productInfo)) {
            return new HashMap<>();
        }
        Map<String, String>
                productInfoMap = JSON.parseObject(productInfo, new TypeReference<Map<String, String>>(){});
        return productInfoMap;
    }

    public void setProductInfo(String productInfo) {
        this.productInfo = productInfo;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
