package com.mallcai.fulfillment.infrastructure.object.tms;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DeliveryRecordPoCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DeliveryRecordPoCriteria() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCommandIdIsNull() {
            addCriterion("command_id is null");
            return (Criteria) this;
        }

        public Criteria andCommandIdIsNotNull() {
            addCriterion("command_id is not null");
            return (Criteria) this;
        }

        public Criteria andCommandIdEqualTo(String value) {
            addCriterion("command_id =", value, "commandId");
            return (Criteria) this;
        }

        public Criteria andCommandIdNotEqualTo(String value) {
            addCriterion("command_id <>", value, "commandId");
            return (Criteria) this;
        }

        public Criteria andCommandIdGreaterThan(String value) {
            addCriterion("command_id >", value, "commandId");
            return (Criteria) this;
        }

        public Criteria andCommandIdGreaterThanOrEqualTo(String value) {
            addCriterion("command_id >=", value, "commandId");
            return (Criteria) this;
        }

        public Criteria andCommandIdLessThan(String value) {
            addCriterion("command_id <", value, "commandId");
            return (Criteria) this;
        }

        public Criteria andCommandIdLessThanOrEqualTo(String value) {
            addCriterion("command_id <=", value, "commandId");
            return (Criteria) this;
        }

        public Criteria andCommandIdLike(String value) {
            addCriterion("command_id like", value, "commandId");
            return (Criteria) this;
        }

        public Criteria andCommandIdNotLike(String value) {
            addCriterion("command_id not like", value, "commandId");
            return (Criteria) this;
        }

        public Criteria andCommandIdIn(List<String> values) {
            addCriterion("command_id in", values, "commandId");
            return (Criteria) this;
        }

        public Criteria andCommandIdNotIn(List<String> values) {
            addCriterion("command_id not in", values, "commandId");
            return (Criteria) this;
        }

        public Criteria andCommandIdBetween(String value1, String value2) {
            addCriterion("command_id between", value1, value2, "commandId");
            return (Criteria) this;
        }

        public Criteria andCommandIdNotBetween(String value1, String value2) {
            addCriterion("command_id not between", value1, value2, "commandId");
            return (Criteria) this;
        }

        public Criteria andCommandTypeIsNull() {
            addCriterion("command_type is null");
            return (Criteria) this;
        }

        public Criteria andCommandTypeIsNotNull() {
            addCriterion("command_type is not null");
            return (Criteria) this;
        }

        public Criteria andCommandTypeEqualTo(String value) {
            addCriterion("command_type =", value, "commandType");
            return (Criteria) this;
        }

        public Criteria andCommandTypeNotEqualTo(String value) {
            addCriterion("command_type <>", value, "commandType");
            return (Criteria) this;
        }

        public Criteria andCommandTypeGreaterThan(String value) {
            addCriterion("command_type >", value, "commandType");
            return (Criteria) this;
        }

        public Criteria andCommandTypeGreaterThanOrEqualTo(String value) {
            addCriterion("command_type >=", value, "commandType");
            return (Criteria) this;
        }

        public Criteria andCommandTypeLessThan(String value) {
            addCriterion("command_type <", value, "commandType");
            return (Criteria) this;
        }

        public Criteria andCommandTypeLessThanOrEqualTo(String value) {
            addCriterion("command_type <=", value, "commandType");
            return (Criteria) this;
        }

        public Criteria andCommandTypeLike(String value) {
            addCriterion("command_type like", value, "commandType");
            return (Criteria) this;
        }

        public Criteria andCommandTypeNotLike(String value) {
            addCriterion("command_type not like", value, "commandType");
            return (Criteria) this;
        }

        public Criteria andCommandTypeIn(List<String> values) {
            addCriterion("command_type in", values, "commandType");
            return (Criteria) this;
        }

        public Criteria andCommandTypeNotIn(List<String> values) {
            addCriterion("command_type not in", values, "commandType");
            return (Criteria) this;
        }

        public Criteria andCommandTypeBetween(String value1, String value2) {
            addCriterion("command_type between", value1, value2, "commandType");
            return (Criteria) this;
        }

        public Criteria andCommandTypeNotBetween(String value1, String value2) {
            addCriterion("command_type not between", value1, value2, "commandType");
            return (Criteria) this;
        }

        public Criteria andStoreIdIsNull() {
            addCriterion("store_id is null");
            return (Criteria) this;
        }

        public Criteria andStoreIdIsNotNull() {
            addCriterion("store_id is not null");
            return (Criteria) this;
        }

        public Criteria andStoreIdEqualTo(Integer value) {
            addCriterion("store_id =", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotEqualTo(Integer value) {
            addCriterion("store_id <>", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThan(Integer value) {
            addCriterion("store_id >", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("store_id >=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThan(Integer value) {
            addCriterion("store_id <", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdLessThanOrEqualTo(Integer value) {
            addCriterion("store_id <=", value, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdIn(List<Integer> values) {
            addCriterion("store_id in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotIn(List<Integer> values) {
            addCriterion("store_id not in", values, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdBetween(Integer value1, Integer value2) {
            addCriterion("store_id between", value1, value2, "storeId");
            return (Criteria) this;
        }

        public Criteria andStoreIdNotBetween(Integer value1, Integer value2) {
            addCriterion("store_id not between", value1, value2, "storeId");
            return (Criteria) this;
        }

        public Criteria andWaybillNoIsNull() {
            addCriterion("waybill_no is null");
            return (Criteria) this;
        }

        public Criteria andWaybillNoIsNotNull() {
            addCriterion("waybill_no is not null");
            return (Criteria) this;
        }

        public Criteria andWaybillNoEqualTo(String value) {
            addCriterion("waybill_no =", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoNotEqualTo(String value) {
            addCriterion("waybill_no <>", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoGreaterThan(String value) {
            addCriterion("waybill_no >", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoGreaterThanOrEqualTo(String value) {
            addCriterion("waybill_no >=", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoLessThan(String value) {
            addCriterion("waybill_no <", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoLessThanOrEqualTo(String value) {
            addCriterion("waybill_no <=", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoLike(String value) {
            addCriterion("waybill_no like", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoNotLike(String value) {
            addCriterion("waybill_no not like", value, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoIn(List<String> values) {
            addCriterion("waybill_no in", values, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoNotIn(List<String> values) {
            addCriterion("waybill_no not in", values, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoBetween(String value1, String value2) {
            addCriterion("waybill_no between", value1, value2, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andWaybillNoNotBetween(String value1, String value2) {
            addCriterion("waybill_no not between", value1, value2, "waybillNo");
            return (Criteria) this;
        }

        public Criteria andPickupDateIsNull() {
            addCriterion("pickup_date is null");
            return (Criteria) this;
        }

        public Criteria andPickupDateIsNotNull() {
            addCriterion("pickup_date is not null");
            return (Criteria) this;
        }

        public Criteria andPickupDateEqualTo(Date value) {
            addCriterion("pickup_date =", value, "pickupDate");
            return (Criteria) this;
        }

        public Criteria andPickupDateNotEqualTo(Date value) {
            addCriterion("pickup_date <>", value, "pickupDate");
            return (Criteria) this;
        }

        public Criteria andPickupDateGreaterThan(Date value) {
            addCriterion("pickup_date >", value, "pickupDate");
            return (Criteria) this;
        }

        public Criteria andPickupDateGreaterThanOrEqualTo(Date value) {
            addCriterion("pickup_date >=", value, "pickupDate");
            return (Criteria) this;
        }

        public Criteria andPickupDateLessThan(Date value) {
            addCriterion("pickup_date <", value, "pickupDate");
            return (Criteria) this;
        }

        public Criteria andPickupDateLessThanOrEqualTo(Date value) {
            addCriterion("pickup_date <=", value, "pickupDate");
            return (Criteria) this;
        }

        public Criteria andPickupDateIn(List<Date> values) {
            addCriterion("pickup_date in", values, "pickupDate");
            return (Criteria) this;
        }

        public Criteria andPickupDateNotIn(List<Date> values) {
            addCriterion("pickup_date not in", values, "pickupDate");
            return (Criteria) this;
        }

        public Criteria andPickupDateBetween(Date value1, Date value2) {
            addCriterion("pickup_date between", value1, value2, "pickupDate");
            return (Criteria) this;
        }

        public Criteria andPickupDateNotBetween(Date value1, Date value2) {
            addCriterion("pickup_date not between", value1, value2, "pickupDate");
            return (Criteria) this;
        }

        public Criteria andTotalCntIsNull() {
            addCriterion("total_cnt is null");
            return (Criteria) this;
        }

        public Criteria andTotalCntIsNotNull() {
            addCriterion("total_cnt is not null");
            return (Criteria) this;
        }

        public Criteria andTotalCntEqualTo(Long value) {
            addCriterion("total_cnt =", value, "totalCnt");
            return (Criteria) this;
        }

        public Criteria andTotalCntNotEqualTo(Long value) {
            addCriterion("total_cnt <>", value, "totalCnt");
            return (Criteria) this;
        }

        public Criteria andTotalCntGreaterThan(Long value) {
            addCriterion("total_cnt >", value, "totalCnt");
            return (Criteria) this;
        }

        public Criteria andTotalCntGreaterThanOrEqualTo(Long value) {
            addCriterion("total_cnt >=", value, "totalCnt");
            return (Criteria) this;
        }

        public Criteria andTotalCntLessThan(Long value) {
            addCriterion("total_cnt <", value, "totalCnt");
            return (Criteria) this;
        }

        public Criteria andTotalCntLessThanOrEqualTo(Long value) {
            addCriterion("total_cnt <=", value, "totalCnt");
            return (Criteria) this;
        }

        public Criteria andTotalCntIn(List<Long> values) {
            addCriterion("total_cnt in", values, "totalCnt");
            return (Criteria) this;
        }

        public Criteria andTotalCntNotIn(List<Long> values) {
            addCriterion("total_cnt not in", values, "totalCnt");
            return (Criteria) this;
        }

        public Criteria andTotalCntBetween(Long value1, Long value2) {
            addCriterion("total_cnt between", value1, value2, "totalCnt");
            return (Criteria) this;
        }

        public Criteria andTotalCntNotBetween(Long value1, Long value2) {
            addCriterion("total_cnt not between", value1, value2, "totalCnt");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeIsNull() {
            addCriterion("total_volume is null");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeIsNotNull() {
            addCriterion("total_volume is not null");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeEqualTo(BigDecimal value) {
            addCriterion("total_volume =", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeNotEqualTo(BigDecimal value) {
            addCriterion("total_volume <>", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeGreaterThan(BigDecimal value) {
            addCriterion("total_volume >", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_volume >=", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeLessThan(BigDecimal value) {
            addCriterion("total_volume <", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_volume <=", value, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeIn(List<BigDecimal> values) {
            addCriterion("total_volume in", values, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeNotIn(List<BigDecimal> values) {
            addCriterion("total_volume not in", values, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_volume between", value1, value2, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalVolumeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_volume not between", value1, value2, "totalVolume");
            return (Criteria) this;
        }

        public Criteria andTotalWeightIsNull() {
            addCriterion("total_weight is null");
            return (Criteria) this;
        }

        public Criteria andTotalWeightIsNotNull() {
            addCriterion("total_weight is not null");
            return (Criteria) this;
        }

        public Criteria andTotalWeightEqualTo(BigDecimal value) {
            addCriterion("total_weight =", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightNotEqualTo(BigDecimal value) {
            addCriterion("total_weight <>", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightGreaterThan(BigDecimal value) {
            addCriterion("total_weight >", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_weight >=", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightLessThan(BigDecimal value) {
            addCriterion("total_weight <", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_weight <=", value, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightIn(List<BigDecimal> values) {
            addCriterion("total_weight in", values, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightNotIn(List<BigDecimal> values) {
            addCriterion("total_weight not in", values, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_weight between", value1, value2, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andTotalWeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_weight not between", value1, value2, "totalWeight");
            return (Criteria) this;
        }

        public Criteria andWmsParamsIsNull() {
            addCriterion("wms_params is null");
            return (Criteria) this;
        }

        public Criteria andWmsParamsIsNotNull() {
            addCriterion("wms_params is not null");
            return (Criteria) this;
        }

        public Criteria andWmsParamsEqualTo(String value) {
            addCriterion("wms_params =", value, "wmsParams");
            return (Criteria) this;
        }

        public Criteria andWmsParamsNotEqualTo(String value) {
            addCriterion("wms_params <>", value, "wmsParams");
            return (Criteria) this;
        }

        public Criteria andWmsParamsGreaterThan(String value) {
            addCriterion("wms_params >", value, "wmsParams");
            return (Criteria) this;
        }

        public Criteria andWmsParamsGreaterThanOrEqualTo(String value) {
            addCriterion("wms_params >=", value, "wmsParams");
            return (Criteria) this;
        }

        public Criteria andWmsParamsLessThan(String value) {
            addCriterion("wms_params <", value, "wmsParams");
            return (Criteria) this;
        }

        public Criteria andWmsParamsLessThanOrEqualTo(String value) {
            addCriterion("wms_params <=", value, "wmsParams");
            return (Criteria) this;
        }

        public Criteria andWmsParamsLike(String value) {
            addCriterion("wms_params like", value, "wmsParams");
            return (Criteria) this;
        }

        public Criteria andWmsParamsNotLike(String value) {
            addCriterion("wms_params not like", value, "wmsParams");
            return (Criteria) this;
        }

        public Criteria andWmsParamsIn(List<String> values) {
            addCriterion("wms_params in", values, "wmsParams");
            return (Criteria) this;
        }

        public Criteria andWmsParamsNotIn(List<String> values) {
            addCriterion("wms_params not in", values, "wmsParams");
            return (Criteria) this;
        }

        public Criteria andWmsParamsBetween(String value1, String value2) {
            addCriterion("wms_params between", value1, value2, "wmsParams");
            return (Criteria) this;
        }

        public Criteria andWmsParamsNotBetween(String value1, String value2) {
            addCriterion("wms_params not between", value1, value2, "wmsParams");
            return (Criteria) this;
        }

        public Criteria andWmsRspIsNull() {
            addCriterion("wms_rsp is null");
            return (Criteria) this;
        }

        public Criteria andWmsRspIsNotNull() {
            addCriterion("wms_rsp is not null");
            return (Criteria) this;
        }

        public Criteria andWmsRspEqualTo(String value) {
            addCriterion("wms_rsp =", value, "wmsRsp");
            return (Criteria) this;
        }

        public Criteria andWmsRspNotEqualTo(String value) {
            addCriterion("wms_rsp <>", value, "wmsRsp");
            return (Criteria) this;
        }

        public Criteria andWmsRspGreaterThan(String value) {
            addCriterion("wms_rsp >", value, "wmsRsp");
            return (Criteria) this;
        }

        public Criteria andWmsRspGreaterThanOrEqualTo(String value) {
            addCriterion("wms_rsp >=", value, "wmsRsp");
            return (Criteria) this;
        }

        public Criteria andWmsRspLessThan(String value) {
            addCriterion("wms_rsp <", value, "wmsRsp");
            return (Criteria) this;
        }

        public Criteria andWmsRspLessThanOrEqualTo(String value) {
            addCriterion("wms_rsp <=", value, "wmsRsp");
            return (Criteria) this;
        }

        public Criteria andWmsRspLike(String value) {
            addCriterion("wms_rsp like", value, "wmsRsp");
            return (Criteria) this;
        }

        public Criteria andWmsRspNotLike(String value) {
            addCriterion("wms_rsp not like", value, "wmsRsp");
            return (Criteria) this;
        }

        public Criteria andWmsRspIn(List<String> values) {
            addCriterion("wms_rsp in", values, "wmsRsp");
            return (Criteria) this;
        }

        public Criteria andWmsRspNotIn(List<String> values) {
            addCriterion("wms_rsp not in", values, "wmsRsp");
            return (Criteria) this;
        }

        public Criteria andWmsRspBetween(String value1, String value2) {
            addCriterion("wms_rsp between", value1, value2, "wmsRsp");
            return (Criteria) this;
        }

        public Criteria andWmsRspNotBetween(String value1, String value2) {
            addCriterion("wms_rsp not between", value1, value2, "wmsRsp");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andWmsReqTimeIsNull() {
            addCriterion("wms_req_time is null");
            return (Criteria) this;
        }

        public Criteria andWmsReqTimeIsNotNull() {
            addCriterion("wms_req_time is not null");
            return (Criteria) this;
        }

        public Criteria andWmsReqTimeEqualTo(Date value) {
            addCriterion("wms_req_time =", value, "wmsReqTime");
            return (Criteria) this;
        }

        public Criteria andWmsReqTimeNotEqualTo(Date value) {
            addCriterion("wms_req_time <>", value, "wmsReqTime");
            return (Criteria) this;
        }

        public Criteria andWmsReqTimeGreaterThan(Date value) {
            addCriterion("wms_req_time >", value, "wmsReqTime");
            return (Criteria) this;
        }

        public Criteria andWmsReqTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("wms_req_time >=", value, "wmsReqTime");
            return (Criteria) this;
        }

        public Criteria andWmsReqTimeLessThan(Date value) {
            addCriterion("wms_req_time <", value, "wmsReqTime");
            return (Criteria) this;
        }

        public Criteria andWmsReqTimeLessThanOrEqualTo(Date value) {
            addCriterion("wms_req_time <=", value, "wmsReqTime");
            return (Criteria) this;
        }

        public Criteria andWmsReqTimeIn(List<Date> values) {
            addCriterion("wms_req_time in", values, "wmsReqTime");
            return (Criteria) this;
        }

        public Criteria andWmsReqTimeNotIn(List<Date> values) {
            addCriterion("wms_req_time not in", values, "wmsReqTime");
            return (Criteria) this;
        }

        public Criteria andWmsReqTimeBetween(Date value1, Date value2) {
            addCriterion("wms_req_time between", value1, value2, "wmsReqTime");
            return (Criteria) this;
        }

        public Criteria andWmsReqTimeNotBetween(Date value1, Date value2) {
            addCriterion("wms_req_time not between", value1, value2, "wmsReqTime");
            return (Criteria) this;
        }

        public Criteria andTransPlanTimeIsNull() {
            addCriterion("trans_plan_time is null");
            return (Criteria) this;
        }

        public Criteria andTransPlanTimeIsNotNull() {
            addCriterion("trans_plan_time is not null");
            return (Criteria) this;
        }

        public Criteria andTransPlanTimeEqualTo(Date value) {
            addCriterion("trans_plan_time =", value, "transPlanTime");
            return (Criteria) this;
        }

        public Criteria andTransPlanTimeNotEqualTo(Date value) {
            addCriterion("trans_plan_time <>", value, "transPlanTime");
            return (Criteria) this;
        }

        public Criteria andTransPlanTimeGreaterThan(Date value) {
            addCriterion("trans_plan_time >", value, "transPlanTime");
            return (Criteria) this;
        }

        public Criteria andTransPlanTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("trans_plan_time >=", value, "transPlanTime");
            return (Criteria) this;
        }

        public Criteria andTransPlanTimeLessThan(Date value) {
            addCriterion("trans_plan_time <", value, "transPlanTime");
            return (Criteria) this;
        }

        public Criteria andTransPlanTimeLessThanOrEqualTo(Date value) {
            addCriterion("trans_plan_time <=", value, "transPlanTime");
            return (Criteria) this;
        }

        public Criteria andTransPlanTimeIn(List<Date> values) {
            addCriterion("trans_plan_time in", values, "transPlanTime");
            return (Criteria) this;
        }

        public Criteria andTransPlanTimeNotIn(List<Date> values) {
            addCriterion("trans_plan_time not in", values, "transPlanTime");
            return (Criteria) this;
        }

        public Criteria andTransPlanTimeBetween(Date value1, Date value2) {
            addCriterion("trans_plan_time between", value1, value2, "transPlanTime");
            return (Criteria) this;
        }

        public Criteria andTransPlanTimeNotBetween(Date value1, Date value2) {
            addCriterion("trans_plan_time not between", value1, value2, "transPlanTime");
            return (Criteria) this;
        }

        public Criteria andTransCarTimeIsNull() {
            addCriterion("trans_car_time is null");
            return (Criteria) this;
        }

        public Criteria andTransCarTimeIsNotNull() {
            addCriterion("trans_car_time is not null");
            return (Criteria) this;
        }

        public Criteria andTransCarTimeEqualTo(Date value) {
            addCriterion("trans_car_time =", value, "transCarTime");
            return (Criteria) this;
        }

        public Criteria andTransCarTimeNotEqualTo(Date value) {
            addCriterion("trans_car_time <>", value, "transCarTime");
            return (Criteria) this;
        }

        public Criteria andTransCarTimeGreaterThan(Date value) {
            addCriterion("trans_car_time >", value, "transCarTime");
            return (Criteria) this;
        }

        public Criteria andTransCarTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("trans_car_time >=", value, "transCarTime");
            return (Criteria) this;
        }

        public Criteria andTransCarTimeLessThan(Date value) {
            addCriterion("trans_car_time <", value, "transCarTime");
            return (Criteria) this;
        }

        public Criteria andTransCarTimeLessThanOrEqualTo(Date value) {
            addCriterion("trans_car_time <=", value, "transCarTime");
            return (Criteria) this;
        }

        public Criteria andTransCarTimeIn(List<Date> values) {
            addCriterion("trans_car_time in", values, "transCarTime");
            return (Criteria) this;
        }

        public Criteria andTransCarTimeNotIn(List<Date> values) {
            addCriterion("trans_car_time not in", values, "transCarTime");
            return (Criteria) this;
        }

        public Criteria andTransCarTimeBetween(Date value1, Date value2) {
            addCriterion("trans_car_time between", value1, value2, "transCarTime");
            return (Criteria) this;
        }

        public Criteria andTransCarTimeNotBetween(Date value1, Date value2) {
            addCriterion("trans_car_time not between", value1, value2, "transCarTime");
            return (Criteria) this;
        }

        public Criteria andTransResTimeIsNull() {
            addCriterion("trans_res_time is null");
            return (Criteria) this;
        }

        public Criteria andTransResTimeIsNotNull() {
            addCriterion("trans_res_time is not null");
            return (Criteria) this;
        }

        public Criteria andTransResTimeEqualTo(Date value) {
            addCriterion("trans_res_time =", value, "transResTime");
            return (Criteria) this;
        }

        public Criteria andTransResTimeNotEqualTo(Date value) {
            addCriterion("trans_res_time <>", value, "transResTime");
            return (Criteria) this;
        }

        public Criteria andTransResTimeGreaterThan(Date value) {
            addCriterion("trans_res_time >", value, "transResTime");
            return (Criteria) this;
        }

        public Criteria andTransResTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("trans_res_time >=", value, "transResTime");
            return (Criteria) this;
        }

        public Criteria andTransResTimeLessThan(Date value) {
            addCriterion("trans_res_time <", value, "transResTime");
            return (Criteria) this;
        }

        public Criteria andTransResTimeLessThanOrEqualTo(Date value) {
            addCriterion("trans_res_time <=", value, "transResTime");
            return (Criteria) this;
        }

        public Criteria andTransResTimeIn(List<Date> values) {
            addCriterion("trans_res_time in", values, "transResTime");
            return (Criteria) this;
        }

        public Criteria andTransResTimeNotIn(List<Date> values) {
            addCriterion("trans_res_time not in", values, "transResTime");
            return (Criteria) this;
        }

        public Criteria andTransResTimeBetween(Date value1, Date value2) {
            addCriterion("trans_res_time between", value1, value2, "transResTime");
            return (Criteria) this;
        }

        public Criteria andTransResTimeNotBetween(Date value1, Date value2) {
            addCriterion("trans_res_time not between", value1, value2, "transResTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("create_by is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("create_by is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("create_by =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("create_by <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("create_by >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("create_by >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("create_by <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("create_by <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("create_by like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("create_by not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("create_by in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("create_by not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("create_by between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("create_by not between", value1, value2, "createBy");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}