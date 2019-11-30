package com.mallcai.fulfillment.infrastructure.object.wms;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OubDeliveryOrderCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OubDeliveryOrderCriteria() {
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

        protected void addCriterionForJDBCDate(String condition, Date value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value.getTime()), property);
        }

        protected void addCriterionForJDBCDate(String condition, List<Date> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            List<java.sql.Date> dateList = new ArrayList<java.sql.Date>();
            Iterator<Date> iter = values.iterator();
            while (iter.hasNext()) {
                dateList.add(new java.sql.Date(iter.next().getTime()));
            }
            addCriterion(condition, dateList, property);
        }

        protected void addCriterionForJDBCDate(String condition, Date value1, Date value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            addCriterion(condition, new java.sql.Date(value1.getTime()), new java.sql.Date(value2.getTime()), property);
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

        public Criteria andWarehouseCodeIsNull() {
            addCriterion("warehouse_code is null");
            return (Criteria) this;
        }

        public Criteria andWarehouseCodeIsNotNull() {
            addCriterion("warehouse_code is not null");
            return (Criteria) this;
        }

        public Criteria andWarehouseCodeEqualTo(String value) {
            addCriterion("warehouse_code =", value, "warehouseCode");
            return (Criteria) this;
        }

        public Criteria andWarehouseCodeNotEqualTo(String value) {
            addCriterion("warehouse_code <>", value, "warehouseCode");
            return (Criteria) this;
        }

        public Criteria andWarehouseCodeGreaterThan(String value) {
            addCriterion("warehouse_code >", value, "warehouseCode");
            return (Criteria) this;
        }

        public Criteria andWarehouseCodeGreaterThanOrEqualTo(String value) {
            addCriterion("warehouse_code >=", value, "warehouseCode");
            return (Criteria) this;
        }

        public Criteria andWarehouseCodeLessThan(String value) {
            addCriterion("warehouse_code <", value, "warehouseCode");
            return (Criteria) this;
        }

        public Criteria andWarehouseCodeLessThanOrEqualTo(String value) {
            addCriterion("warehouse_code <=", value, "warehouseCode");
            return (Criteria) this;
        }

        public Criteria andWarehouseCodeLike(String value) {
            addCriterion("warehouse_code like", value, "warehouseCode");
            return (Criteria) this;
        }

        public Criteria andWarehouseCodeNotLike(String value) {
            addCriterion("warehouse_code not like", value, "warehouseCode");
            return (Criteria) this;
        }

        public Criteria andWarehouseCodeIn(List<String> values) {
            addCriterion("warehouse_code in", values, "warehouseCode");
            return (Criteria) this;
        }

        public Criteria andWarehouseCodeNotIn(List<String> values) {
            addCriterion("warehouse_code not in", values, "warehouseCode");
            return (Criteria) this;
        }

        public Criteria andWarehouseCodeBetween(String value1, String value2) {
            addCriterion("warehouse_code between", value1, value2, "warehouseCode");
            return (Criteria) this;
        }

        public Criteria andWarehouseCodeNotBetween(String value1, String value2) {
            addCriterion("warehouse_code not between", value1, value2, "warehouseCode");
            return (Criteria) this;
        }

        public Criteria andWaveNumberIsNull() {
            addCriterion("wave_number is null");
            return (Criteria) this;
        }

        public Criteria andWaveNumberIsNotNull() {
            addCriterion("wave_number is not null");
            return (Criteria) this;
        }

        public Criteria andWaveNumberEqualTo(String value) {
            addCriterion("wave_number =", value, "waveNumber");
            return (Criteria) this;
        }

        public Criteria andWaveNumberNotEqualTo(String value) {
            addCriterion("wave_number <>", value, "waveNumber");
            return (Criteria) this;
        }

        public Criteria andWaveNumberGreaterThan(String value) {
            addCriterion("wave_number >", value, "waveNumber");
            return (Criteria) this;
        }

        public Criteria andWaveNumberGreaterThanOrEqualTo(String value) {
            addCriterion("wave_number >=", value, "waveNumber");
            return (Criteria) this;
        }

        public Criteria andWaveNumberLessThan(String value) {
            addCriterion("wave_number <", value, "waveNumber");
            return (Criteria) this;
        }

        public Criteria andWaveNumberLessThanOrEqualTo(String value) {
            addCriterion("wave_number <=", value, "waveNumber");
            return (Criteria) this;
        }

        public Criteria andWaveNumberLike(String value) {
            addCriterion("wave_number like", value, "waveNumber");
            return (Criteria) this;
        }

        public Criteria andWaveNumberNotLike(String value) {
            addCriterion("wave_number not like", value, "waveNumber");
            return (Criteria) this;
        }

        public Criteria andWaveNumberIn(List<String> values) {
            addCriterion("wave_number in", values, "waveNumber");
            return (Criteria) this;
        }

        public Criteria andWaveNumberNotIn(List<String> values) {
            addCriterion("wave_number not in", values, "waveNumber");
            return (Criteria) this;
        }

        public Criteria andWaveNumberBetween(String value1, String value2) {
            addCriterion("wave_number between", value1, value2, "waveNumber");
            return (Criteria) this;
        }

        public Criteria andWaveNumberNotBetween(String value1, String value2) {
            addCriterion("wave_number not between", value1, value2, "waveNumber");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderIsNull() {
            addCriterion("delivery_order is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderIsNotNull() {
            addCriterion("delivery_order is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderEqualTo(String value) {
            addCriterion("delivery_order =", value, "deliveryOrder");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderNotEqualTo(String value) {
            addCriterion("delivery_order <>", value, "deliveryOrder");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderGreaterThan(String value) {
            addCriterion("delivery_order >", value, "deliveryOrder");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_order >=", value, "deliveryOrder");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderLessThan(String value) {
            addCriterion("delivery_order <", value, "deliveryOrder");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderLessThanOrEqualTo(String value) {
            addCriterion("delivery_order <=", value, "deliveryOrder");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderLike(String value) {
            addCriterion("delivery_order like", value, "deliveryOrder");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderNotLike(String value) {
            addCriterion("delivery_order not like", value, "deliveryOrder");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderIn(List<String> values) {
            addCriterion("delivery_order in", values, "deliveryOrder");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderNotIn(List<String> values) {
            addCriterion("delivery_order not in", values, "deliveryOrder");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderBetween(String value1, String value2) {
            addCriterion("delivery_order between", value1, value2, "deliveryOrder");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderNotBetween(String value1, String value2) {
            addCriterion("delivery_order not between", value1, value2, "deliveryOrder");
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
            addCriterionForJDBCDate("pickup_date =", value, "pickupDate");
            return (Criteria) this;
        }

        public Criteria andPickupDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("pickup_date <>", value, "pickupDate");
            return (Criteria) this;
        }

        public Criteria andPickupDateGreaterThan(Date value) {
            addCriterionForJDBCDate("pickup_date >", value, "pickupDate");
            return (Criteria) this;
        }

        public Criteria andPickupDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("pickup_date >=", value, "pickupDate");
            return (Criteria) this;
        }

        public Criteria andPickupDateLessThan(Date value) {
            addCriterionForJDBCDate("pickup_date <", value, "pickupDate");
            return (Criteria) this;
        }

        public Criteria andPickupDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("pickup_date <=", value, "pickupDate");
            return (Criteria) this;
        }

        public Criteria andPickupDateIn(List<Date> values) {
            addCriterionForJDBCDate("pickup_date in", values, "pickupDate");
            return (Criteria) this;
        }

        public Criteria andPickupDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("pickup_date not in", values, "pickupDate");
            return (Criteria) this;
        }

        public Criteria andPickupDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("pickup_date between", value1, value2, "pickupDate");
            return (Criteria) this;
        }

        public Criteria andPickupDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("pickup_date not between", value1, value2, "pickupDate");
            return (Criteria) this;
        }

        public Criteria andPushTmsStatusIsNull() {
            addCriterion("push_tms_status is null");
            return (Criteria) this;
        }

        public Criteria andPushTmsStatusIsNotNull() {
            addCriterion("push_tms_status is not null");
            return (Criteria) this;
        }

        public Criteria andPushTmsStatusEqualTo(Byte value) {
            addCriterion("push_tms_status =", value, "pushTmsStatus");
            return (Criteria) this;
        }

        public Criteria andPushTmsStatusNotEqualTo(Byte value) {
            addCriterion("push_tms_status <>", value, "pushTmsStatus");
            return (Criteria) this;
        }

        public Criteria andPushTmsStatusGreaterThan(Byte value) {
            addCriterion("push_tms_status >", value, "pushTmsStatus");
            return (Criteria) this;
        }

        public Criteria andPushTmsStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("push_tms_status >=", value, "pushTmsStatus");
            return (Criteria) this;
        }

        public Criteria andPushTmsStatusLessThan(Byte value) {
            addCriterion("push_tms_status <", value, "pushTmsStatus");
            return (Criteria) this;
        }

        public Criteria andPushTmsStatusLessThanOrEqualTo(Byte value) {
            addCriterion("push_tms_status <=", value, "pushTmsStatus");
            return (Criteria) this;
        }

        public Criteria andPushTmsStatusIn(List<Byte> values) {
            addCriterion("push_tms_status in", values, "pushTmsStatus");
            return (Criteria) this;
        }

        public Criteria andPushTmsStatusNotIn(List<Byte> values) {
            addCriterion("push_tms_status not in", values, "pushTmsStatus");
            return (Criteria) this;
        }

        public Criteria andPushTmsStatusBetween(Byte value1, Byte value2) {
            addCriterion("push_tms_status between", value1, value2, "pushTmsStatus");
            return (Criteria) this;
        }

        public Criteria andPushTmsStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("push_tms_status not between", value1, value2, "pushTmsStatus");
            return (Criteria) this;
        }

        public Criteria andPushStoreStatusIsNull() {
            addCriterion("push_store_status is null");
            return (Criteria) this;
        }

        public Criteria andPushStoreStatusIsNotNull() {
            addCriterion("push_store_status is not null");
            return (Criteria) this;
        }

        public Criteria andPushStoreStatusEqualTo(Byte value) {
            addCriterion("push_store_status =", value, "pushStoreStatus");
            return (Criteria) this;
        }

        public Criteria andPushStoreStatusNotEqualTo(Byte value) {
            addCriterion("push_store_status <>", value, "pushStoreStatus");
            return (Criteria) this;
        }

        public Criteria andPushStoreStatusGreaterThan(Byte value) {
            addCriterion("push_store_status >", value, "pushStoreStatus");
            return (Criteria) this;
        }

        public Criteria andPushStoreStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("push_store_status >=", value, "pushStoreStatus");
            return (Criteria) this;
        }

        public Criteria andPushStoreStatusLessThan(Byte value) {
            addCriterion("push_store_status <", value, "pushStoreStatus");
            return (Criteria) this;
        }

        public Criteria andPushStoreStatusLessThanOrEqualTo(Byte value) {
            addCriterion("push_store_status <=", value, "pushStoreStatus");
            return (Criteria) this;
        }

        public Criteria andPushStoreStatusIn(List<Byte> values) {
            addCriterion("push_store_status in", values, "pushStoreStatus");
            return (Criteria) this;
        }

        public Criteria andPushStoreStatusNotIn(List<Byte> values) {
            addCriterion("push_store_status not in", values, "pushStoreStatus");
            return (Criteria) this;
        }

        public Criteria andPushStoreStatusBetween(Byte value1, Byte value2) {
            addCriterion("push_store_status between", value1, value2, "pushStoreStatus");
            return (Criteria) this;
        }

        public Criteria andPushStoreStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("push_store_status not between", value1, value2, "pushStoreStatus");
            return (Criteria) this;
        }

        public Criteria andSkuGroupCodeIsNull() {
            addCriterion("sku_group_code is null");
            return (Criteria) this;
        }

        public Criteria andSkuGroupCodeIsNotNull() {
            addCriterion("sku_group_code is not null");
            return (Criteria) this;
        }

        public Criteria andSkuGroupCodeEqualTo(String value) {
            addCriterion("sku_group_code =", value, "skuGroupCode");
            return (Criteria) this;
        }

        public Criteria andSkuGroupCodeNotEqualTo(String value) {
            addCriterion("sku_group_code <>", value, "skuGroupCode");
            return (Criteria) this;
        }

        public Criteria andSkuGroupCodeGreaterThan(String value) {
            addCriterion("sku_group_code >", value, "skuGroupCode");
            return (Criteria) this;
        }

        public Criteria andSkuGroupCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sku_group_code >=", value, "skuGroupCode");
            return (Criteria) this;
        }

        public Criteria andSkuGroupCodeLessThan(String value) {
            addCriterion("sku_group_code <", value, "skuGroupCode");
            return (Criteria) this;
        }

        public Criteria andSkuGroupCodeLessThanOrEqualTo(String value) {
            addCriterion("sku_group_code <=", value, "skuGroupCode");
            return (Criteria) this;
        }

        public Criteria andSkuGroupCodeLike(String value) {
            addCriterion("sku_group_code like", value, "skuGroupCode");
            return (Criteria) this;
        }

        public Criteria andSkuGroupCodeNotLike(String value) {
            addCriterion("sku_group_code not like", value, "skuGroupCode");
            return (Criteria) this;
        }

        public Criteria andSkuGroupCodeIn(List<String> values) {
            addCriterion("sku_group_code in", values, "skuGroupCode");
            return (Criteria) this;
        }

        public Criteria andSkuGroupCodeNotIn(List<String> values) {
            addCriterion("sku_group_code not in", values, "skuGroupCode");
            return (Criteria) this;
        }

        public Criteria andSkuGroupCodeBetween(String value1, String value2) {
            addCriterion("sku_group_code between", value1, value2, "skuGroupCode");
            return (Criteria) this;
        }

        public Criteria andSkuGroupCodeNotBetween(String value1, String value2) {
            addCriterion("sku_group_code not between", value1, value2, "skuGroupCode");
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

        public Criteria andStoreNoIsNull() {
            addCriterion("store_no is null");
            return (Criteria) this;
        }

        public Criteria andStoreNoIsNotNull() {
            addCriterion("store_no is not null");
            return (Criteria) this;
        }

        public Criteria andStoreNoEqualTo(Integer value) {
            addCriterion("store_no =", value, "storeNo");
            return (Criteria) this;
        }

        public Criteria andStoreNoNotEqualTo(Integer value) {
            addCriterion("store_no <>", value, "storeNo");
            return (Criteria) this;
        }

        public Criteria andStoreNoGreaterThan(Integer value) {
            addCriterion("store_no >", value, "storeNo");
            return (Criteria) this;
        }

        public Criteria andStoreNoGreaterThanOrEqualTo(Integer value) {
            addCriterion("store_no >=", value, "storeNo");
            return (Criteria) this;
        }

        public Criteria andStoreNoLessThan(Integer value) {
            addCriterion("store_no <", value, "storeNo");
            return (Criteria) this;
        }

        public Criteria andStoreNoLessThanOrEqualTo(Integer value) {
            addCriterion("store_no <=", value, "storeNo");
            return (Criteria) this;
        }

        public Criteria andStoreNoIn(List<Integer> values) {
            addCriterion("store_no in", values, "storeNo");
            return (Criteria) this;
        }

        public Criteria andStoreNoNotIn(List<Integer> values) {
            addCriterion("store_no not in", values, "storeNo");
            return (Criteria) this;
        }

        public Criteria andStoreNoBetween(Integer value1, Integer value2) {
            addCriterion("store_no between", value1, value2, "storeNo");
            return (Criteria) this;
        }

        public Criteria andStoreNoNotBetween(Integer value1, Integer value2) {
            addCriterion("store_no not between", value1, value2, "storeNo");
            return (Criteria) this;
        }

        public Criteria andStoreNameIsNull() {
            addCriterion("store_name is null");
            return (Criteria) this;
        }

        public Criteria andStoreNameIsNotNull() {
            addCriterion("store_name is not null");
            return (Criteria) this;
        }

        public Criteria andStoreNameEqualTo(String value) {
            addCriterion("store_name =", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotEqualTo(String value) {
            addCriterion("store_name <>", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameGreaterThan(String value) {
            addCriterion("store_name >", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameGreaterThanOrEqualTo(String value) {
            addCriterion("store_name >=", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLessThan(String value) {
            addCriterion("store_name <", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLessThanOrEqualTo(String value) {
            addCriterion("store_name <=", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameLike(String value) {
            addCriterion("store_name like", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotLike(String value) {
            addCriterion("store_name not like", value, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameIn(List<String> values) {
            addCriterion("store_name in", values, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotIn(List<String> values) {
            addCriterion("store_name not in", values, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameBetween(String value1, String value2) {
            addCriterion("store_name between", value1, value2, "storeName");
            return (Criteria) this;
        }

        public Criteria andStoreNameNotBetween(String value1, String value2) {
            addCriterion("store_name not between", value1, value2, "storeName");
            return (Criteria) this;
        }

        public Criteria andTrafficSrlNumberIsNull() {
            addCriterion("traffic_srl_number is null");
            return (Criteria) this;
        }

        public Criteria andTrafficSrlNumberIsNotNull() {
            addCriterion("traffic_srl_number is not null");
            return (Criteria) this;
        }

        public Criteria andTrafficSrlNumberEqualTo(Integer value) {
            addCriterion("traffic_srl_number =", value, "trafficSrlNumber");
            return (Criteria) this;
        }

        public Criteria andTrafficSrlNumberNotEqualTo(Integer value) {
            addCriterion("traffic_srl_number <>", value, "trafficSrlNumber");
            return (Criteria) this;
        }

        public Criteria andTrafficSrlNumberGreaterThan(Integer value) {
            addCriterion("traffic_srl_number >", value, "trafficSrlNumber");
            return (Criteria) this;
        }

        public Criteria andTrafficSrlNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("traffic_srl_number >=", value, "trafficSrlNumber");
            return (Criteria) this;
        }

        public Criteria andTrafficSrlNumberLessThan(Integer value) {
            addCriterion("traffic_srl_number <", value, "trafficSrlNumber");
            return (Criteria) this;
        }

        public Criteria andTrafficSrlNumberLessThanOrEqualTo(Integer value) {
            addCriterion("traffic_srl_number <=", value, "trafficSrlNumber");
            return (Criteria) this;
        }

        public Criteria andTrafficSrlNumberIn(List<Integer> values) {
            addCriterion("traffic_srl_number in", values, "trafficSrlNumber");
            return (Criteria) this;
        }

        public Criteria andTrafficSrlNumberNotIn(List<Integer> values) {
            addCriterion("traffic_srl_number not in", values, "trafficSrlNumber");
            return (Criteria) this;
        }

        public Criteria andTrafficSrlNumberBetween(Integer value1, Integer value2) {
            addCriterion("traffic_srl_number between", value1, value2, "trafficSrlNumber");
            return (Criteria) this;
        }

        public Criteria andTrafficSrlNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("traffic_srl_number not between", value1, value2, "trafficSrlNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberIsNull() {
            addCriterion("serial_number is null");
            return (Criteria) this;
        }

        public Criteria andSerialNumberIsNotNull() {
            addCriterion("serial_number is not null");
            return (Criteria) this;
        }

        public Criteria andSerialNumberEqualTo(Integer value) {
            addCriterion("serial_number =", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotEqualTo(Integer value) {
            addCriterion("serial_number <>", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberGreaterThan(Integer value) {
            addCriterion("serial_number >", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("serial_number >=", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberLessThan(Integer value) {
            addCriterion("serial_number <", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberLessThanOrEqualTo(Integer value) {
            addCriterion("serial_number <=", value, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberIn(List<Integer> values) {
            addCriterion("serial_number in", values, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotIn(List<Integer> values) {
            addCriterion("serial_number not in", values, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberBetween(Integer value1, Integer value2) {
            addCriterion("serial_number between", value1, value2, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andSerialNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("serial_number not between", value1, value2, "serialNumber");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNull() {
            addCriterion("city_id is null");
            return (Criteria) this;
        }

        public Criteria andCityIdIsNotNull() {
            addCriterion("city_id is not null");
            return (Criteria) this;
        }

        public Criteria andCityIdEqualTo(Integer value) {
            addCriterion("city_id =", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotEqualTo(Integer value) {
            addCriterion("city_id <>", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThan(Integer value) {
            addCriterion("city_id >", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("city_id >=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThan(Integer value) {
            addCriterion("city_id <", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdLessThanOrEqualTo(Integer value) {
            addCriterion("city_id <=", value, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdIn(List<Integer> values) {
            addCriterion("city_id in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotIn(List<Integer> values) {
            addCriterion("city_id not in", values, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdBetween(Integer value1, Integer value2) {
            addCriterion("city_id between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andCityIdNotBetween(Integer value1, Integer value2) {
            addCriterion("city_id not between", value1, value2, "cityId");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNull() {
            addCriterion("area_id is null");
            return (Criteria) this;
        }

        public Criteria andAreaIdIsNotNull() {
            addCriterion("area_id is not null");
            return (Criteria) this;
        }

        public Criteria andAreaIdEqualTo(Integer value) {
            addCriterion("area_id =", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotEqualTo(Integer value) {
            addCriterion("area_id <>", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThan(Integer value) {
            addCriterion("area_id >", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("area_id >=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThan(Integer value) {
            addCriterion("area_id <", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdLessThanOrEqualTo(Integer value) {
            addCriterion("area_id <=", value, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdIn(List<Integer> values) {
            addCriterion("area_id in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotIn(List<Integer> values) {
            addCriterion("area_id not in", values, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdBetween(Integer value1, Integer value2) {
            addCriterion("area_id between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andAreaIdNotBetween(Integer value1, Integer value2) {
            addCriterion("area_id not between", value1, value2, "areaId");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("address is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("address is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("address =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("address <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("address >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("address >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("address <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("address <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("address like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("address not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("address in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("address not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("address between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("address not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNull() {
            addCriterion("receiver is null");
            return (Criteria) this;
        }

        public Criteria andReceiverIsNotNull() {
            addCriterion("receiver is not null");
            return (Criteria) this;
        }

        public Criteria andReceiverEqualTo(String value) {
            addCriterion("receiver =", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotEqualTo(String value) {
            addCriterion("receiver <>", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThan(String value) {
            addCriterion("receiver >", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverGreaterThanOrEqualTo(String value) {
            addCriterion("receiver >=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThan(String value) {
            addCriterion("receiver <", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLessThanOrEqualTo(String value) {
            addCriterion("receiver <=", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverLike(String value) {
            addCriterion("receiver like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotLike(String value) {
            addCriterion("receiver not like", value, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverIn(List<String> values) {
            addCriterion("receiver in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotIn(List<String> values) {
            addCriterion("receiver not in", values, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverBetween(String value1, String value2) {
            addCriterion("receiver between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andReceiverNotBetween(String value1, String value2) {
            addCriterion("receiver not between", value1, value2, "receiver");
            return (Criteria) this;
        }

        public Criteria andTelIsNull() {
            addCriterion("tel is null");
            return (Criteria) this;
        }

        public Criteria andTelIsNotNull() {
            addCriterion("tel is not null");
            return (Criteria) this;
        }

        public Criteria andTelEqualTo(String value) {
            addCriterion("tel =", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotEqualTo(String value) {
            addCriterion("tel <>", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThan(String value) {
            addCriterion("tel >", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThanOrEqualTo(String value) {
            addCriterion("tel >=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThan(String value) {
            addCriterion("tel <", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThanOrEqualTo(String value) {
            addCriterion("tel <=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLike(String value) {
            addCriterion("tel like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotLike(String value) {
            addCriterion("tel not like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelIn(List<String> values) {
            addCriterion("tel in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotIn(List<String> values) {
            addCriterion("tel not in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelBetween(String value1, String value2) {
            addCriterion("tel between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotBetween(String value1, String value2) {
            addCriterion("tel not between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andSoldCountIsNull() {
            addCriterion("sold_count is null");
            return (Criteria) this;
        }

        public Criteria andSoldCountIsNotNull() {
            addCriterion("sold_count is not null");
            return (Criteria) this;
        }

        public Criteria andSoldCountEqualTo(BigDecimal value) {
            addCriterion("sold_count =", value, "soldCount");
            return (Criteria) this;
        }

        public Criteria andSoldCountNotEqualTo(BigDecimal value) {
            addCriterion("sold_count <>", value, "soldCount");
            return (Criteria) this;
        }

        public Criteria andSoldCountGreaterThan(BigDecimal value) {
            addCriterion("sold_count >", value, "soldCount");
            return (Criteria) this;
        }

        public Criteria andSoldCountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sold_count >=", value, "soldCount");
            return (Criteria) this;
        }

        public Criteria andSoldCountLessThan(BigDecimal value) {
            addCriterion("sold_count <", value, "soldCount");
            return (Criteria) this;
        }

        public Criteria andSoldCountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sold_count <=", value, "soldCount");
            return (Criteria) this;
        }

        public Criteria andSoldCountIn(List<BigDecimal> values) {
            addCriterion("sold_count in", values, "soldCount");
            return (Criteria) this;
        }

        public Criteria andSoldCountNotIn(List<BigDecimal> values) {
            addCriterion("sold_count not in", values, "soldCount");
            return (Criteria) this;
        }

        public Criteria andSoldCountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sold_count between", value1, value2, "soldCount");
            return (Criteria) this;
        }

        public Criteria andSoldCountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sold_count not between", value1, value2, "soldCount");
            return (Criteria) this;
        }

        public Criteria andDeliveryCountIsNull() {
            addCriterion("delivery_count is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryCountIsNotNull() {
            addCriterion("delivery_count is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryCountEqualTo(BigDecimal value) {
            addCriterion("delivery_count =", value, "deliveryCount");
            return (Criteria) this;
        }

        public Criteria andDeliveryCountNotEqualTo(BigDecimal value) {
            addCriterion("delivery_count <>", value, "deliveryCount");
            return (Criteria) this;
        }

        public Criteria andDeliveryCountGreaterThan(BigDecimal value) {
            addCriterion("delivery_count >", value, "deliveryCount");
            return (Criteria) this;
        }

        public Criteria andDeliveryCountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("delivery_count >=", value, "deliveryCount");
            return (Criteria) this;
        }

        public Criteria andDeliveryCountLessThan(BigDecimal value) {
            addCriterion("delivery_count <", value, "deliveryCount");
            return (Criteria) this;
        }

        public Criteria andDeliveryCountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("delivery_count <=", value, "deliveryCount");
            return (Criteria) this;
        }

        public Criteria andDeliveryCountIn(List<BigDecimal> values) {
            addCriterion("delivery_count in", values, "deliveryCount");
            return (Criteria) this;
        }

        public Criteria andDeliveryCountNotIn(List<BigDecimal> values) {
            addCriterion("delivery_count not in", values, "deliveryCount");
            return (Criteria) this;
        }

        public Criteria andDeliveryCountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("delivery_count between", value1, value2, "deliveryCount");
            return (Criteria) this;
        }

        public Criteria andDeliveryCountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("delivery_count not between", value1, value2, "deliveryCount");
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

        public Criteria andLocationCodeIsNull() {
            addCriterion("location_code is null");
            return (Criteria) this;
        }

        public Criteria andLocationCodeIsNotNull() {
            addCriterion("location_code is not null");
            return (Criteria) this;
        }

        public Criteria andLocationCodeEqualTo(String value) {
            addCriterion("location_code =", value, "locationCode");
            return (Criteria) this;
        }

        public Criteria andLocationCodeNotEqualTo(String value) {
            addCriterion("location_code <>", value, "locationCode");
            return (Criteria) this;
        }

        public Criteria andLocationCodeGreaterThan(String value) {
            addCriterion("location_code >", value, "locationCode");
            return (Criteria) this;
        }

        public Criteria andLocationCodeGreaterThanOrEqualTo(String value) {
            addCriterion("location_code >=", value, "locationCode");
            return (Criteria) this;
        }

        public Criteria andLocationCodeLessThan(String value) {
            addCriterion("location_code <", value, "locationCode");
            return (Criteria) this;
        }

        public Criteria andLocationCodeLessThanOrEqualTo(String value) {
            addCriterion("location_code <=", value, "locationCode");
            return (Criteria) this;
        }

        public Criteria andLocationCodeLike(String value) {
            addCriterion("location_code like", value, "locationCode");
            return (Criteria) this;
        }

        public Criteria andLocationCodeNotLike(String value) {
            addCriterion("location_code not like", value, "locationCode");
            return (Criteria) this;
        }

        public Criteria andLocationCodeIn(List<String> values) {
            addCriterion("location_code in", values, "locationCode");
            return (Criteria) this;
        }

        public Criteria andLocationCodeNotIn(List<String> values) {
            addCriterion("location_code not in", values, "locationCode");
            return (Criteria) this;
        }

        public Criteria andLocationCodeBetween(String value1, String value2) {
            addCriterion("location_code between", value1, value2, "locationCode");
            return (Criteria) this;
        }

        public Criteria andLocationCodeNotBetween(String value1, String value2) {
            addCriterion("location_code not between", value1, value2, "locationCode");
            return (Criteria) this;
        }

        public Criteria andDeliveryUserIdIsNull() {
            addCriterion("delivery_user_id is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryUserIdIsNotNull() {
            addCriterion("delivery_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryUserIdEqualTo(Integer value) {
            addCriterion("delivery_user_id =", value, "deliveryUserId");
            return (Criteria) this;
        }

        public Criteria andDeliveryUserIdNotEqualTo(Integer value) {
            addCriterion("delivery_user_id <>", value, "deliveryUserId");
            return (Criteria) this;
        }

        public Criteria andDeliveryUserIdGreaterThan(Integer value) {
            addCriterion("delivery_user_id >", value, "deliveryUserId");
            return (Criteria) this;
        }

        public Criteria andDeliveryUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("delivery_user_id >=", value, "deliveryUserId");
            return (Criteria) this;
        }

        public Criteria andDeliveryUserIdLessThan(Integer value) {
            addCriterion("delivery_user_id <", value, "deliveryUserId");
            return (Criteria) this;
        }

        public Criteria andDeliveryUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("delivery_user_id <=", value, "deliveryUserId");
            return (Criteria) this;
        }

        public Criteria andDeliveryUserIdIn(List<Integer> values) {
            addCriterion("delivery_user_id in", values, "deliveryUserId");
            return (Criteria) this;
        }

        public Criteria andDeliveryUserIdNotIn(List<Integer> values) {
            addCriterion("delivery_user_id not in", values, "deliveryUserId");
            return (Criteria) this;
        }

        public Criteria andDeliveryUserIdBetween(Integer value1, Integer value2) {
            addCriterion("delivery_user_id between", value1, value2, "deliveryUserId");
            return (Criteria) this;
        }

        public Criteria andDeliveryUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("delivery_user_id not between", value1, value2, "deliveryUserId");
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

        public Criteria andStatusEqualTo(Byte value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Byte value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Byte value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Byte value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Byte value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Byte> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Byte> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Byte value1, Byte value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Byte value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Byte value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Byte value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Byte value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Byte value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Byte> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Byte> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Byte value1, Byte value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNull() {
            addCriterion("created_time is null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIsNotNull() {
            addCriterion("created_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeEqualTo(Date value) {
            addCriterion("created_time =", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotEqualTo(Date value) {
            addCriterion("created_time <>", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThan(Date value) {
            addCriterion("created_time >", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("created_time >=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThan(Date value) {
            addCriterion("created_time <", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("created_time <=", value, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeIn(List<Date> values) {
            addCriterion("created_time in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotIn(List<Date> values) {
            addCriterion("created_time not in", values, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeBetween(Date value1, Date value2) {
            addCriterion("created_time between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("created_time not between", value1, value2, "createdTime");
            return (Criteria) this;
        }

        public Criteria andCreatedUserIdIsNull() {
            addCriterion("created_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreatedUserIdIsNotNull() {
            addCriterion("created_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedUserIdEqualTo(Integer value) {
            addCriterion("created_user_id =", value, "createdUserId");
            return (Criteria) this;
        }

        public Criteria andCreatedUserIdNotEqualTo(Integer value) {
            addCriterion("created_user_id <>", value, "createdUserId");
            return (Criteria) this;
        }

        public Criteria andCreatedUserIdGreaterThan(Integer value) {
            addCriterion("created_user_id >", value, "createdUserId");
            return (Criteria) this;
        }

        public Criteria andCreatedUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("created_user_id >=", value, "createdUserId");
            return (Criteria) this;
        }

        public Criteria andCreatedUserIdLessThan(Integer value) {
            addCriterion("created_user_id <", value, "createdUserId");
            return (Criteria) this;
        }

        public Criteria andCreatedUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("created_user_id <=", value, "createdUserId");
            return (Criteria) this;
        }

        public Criteria andCreatedUserIdIn(List<Integer> values) {
            addCriterion("created_user_id in", values, "createdUserId");
            return (Criteria) this;
        }

        public Criteria andCreatedUserIdNotIn(List<Integer> values) {
            addCriterion("created_user_id not in", values, "createdUserId");
            return (Criteria) this;
        }

        public Criteria andCreatedUserIdBetween(Integer value1, Integer value2) {
            addCriterion("created_user_id between", value1, value2, "createdUserId");
            return (Criteria) this;
        }

        public Criteria andCreatedUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("created_user_id not between", value1, value2, "createdUserId");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNull() {
            addCriterion("updated_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIsNotNull() {
            addCriterion("updated_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeEqualTo(Date value) {
            addCriterion("updated_time =", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotEqualTo(Date value) {
            addCriterion("updated_time <>", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThan(Date value) {
            addCriterion("updated_time >", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_time >=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThan(Date value) {
            addCriterion("updated_time <", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeLessThanOrEqualTo(Date value) {
            addCriterion("updated_time <=", value, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeIn(List<Date> values) {
            addCriterion("updated_time in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotIn(List<Date> values) {
            addCriterion("updated_time not in", values, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeBetween(Date value1, Date value2) {
            addCriterion("updated_time between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedTimeNotBetween(Date value1, Date value2) {
            addCriterion("updated_time not between", value1, value2, "updatedTime");
            return (Criteria) this;
        }

        public Criteria andUpdatedUserIdIsNull() {
            addCriterion("updated_user_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedUserIdIsNotNull() {
            addCriterion("updated_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedUserIdEqualTo(Integer value) {
            addCriterion("updated_user_id =", value, "updatedUserId");
            return (Criteria) this;
        }

        public Criteria andUpdatedUserIdNotEqualTo(Integer value) {
            addCriterion("updated_user_id <>", value, "updatedUserId");
            return (Criteria) this;
        }

        public Criteria andUpdatedUserIdGreaterThan(Integer value) {
            addCriterion("updated_user_id >", value, "updatedUserId");
            return (Criteria) this;
        }

        public Criteria andUpdatedUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("updated_user_id >=", value, "updatedUserId");
            return (Criteria) this;
        }

        public Criteria andUpdatedUserIdLessThan(Integer value) {
            addCriterion("updated_user_id <", value, "updatedUserId");
            return (Criteria) this;
        }

        public Criteria andUpdatedUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("updated_user_id <=", value, "updatedUserId");
            return (Criteria) this;
        }

        public Criteria andUpdatedUserIdIn(List<Integer> values) {
            addCriterion("updated_user_id in", values, "updatedUserId");
            return (Criteria) this;
        }

        public Criteria andUpdatedUserIdNotIn(List<Integer> values) {
            addCriterion("updated_user_id not in", values, "updatedUserId");
            return (Criteria) this;
        }

        public Criteria andUpdatedUserIdBetween(Integer value1, Integer value2) {
            addCriterion("updated_user_id between", value1, value2, "updatedUserId");
            return (Criteria) this;
        }

        public Criteria andUpdatedUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("updated_user_id not between", value1, value2, "updatedUserId");
            return (Criteria) this;
        }

        public Criteria andVersionIsNull() {
            addCriterion("version is null");
            return (Criteria) this;
        }

        public Criteria andVersionIsNotNull() {
            addCriterion("version is not null");
            return (Criteria) this;
        }

        public Criteria andVersionEqualTo(Integer value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Integer value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Integer value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Integer value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Integer value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Integer value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Integer> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Integer> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Integer value1, Integer value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Integer value1, Integer value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andSourceOrderNoIsNull() {
            addCriterion("source_order_no is null");
            return (Criteria) this;
        }

        public Criteria andSourceOrderNoIsNotNull() {
            addCriterion("source_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andSourceOrderNoEqualTo(String value) {
            addCriterion("source_order_no =", value, "sourceOrderNo");
            return (Criteria) this;
        }

        public Criteria andSourceOrderNoNotEqualTo(String value) {
            addCriterion("source_order_no <>", value, "sourceOrderNo");
            return (Criteria) this;
        }

        public Criteria andSourceOrderNoGreaterThan(String value) {
            addCriterion("source_order_no >", value, "sourceOrderNo");
            return (Criteria) this;
        }

        public Criteria andSourceOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("source_order_no >=", value, "sourceOrderNo");
            return (Criteria) this;
        }

        public Criteria andSourceOrderNoLessThan(String value) {
            addCriterion("source_order_no <", value, "sourceOrderNo");
            return (Criteria) this;
        }

        public Criteria andSourceOrderNoLessThanOrEqualTo(String value) {
            addCriterion("source_order_no <=", value, "sourceOrderNo");
            return (Criteria) this;
        }

        public Criteria andSourceOrderNoLike(String value) {
            addCriterion("source_order_no like", value, "sourceOrderNo");
            return (Criteria) this;
        }

        public Criteria andSourceOrderNoNotLike(String value) {
            addCriterion("source_order_no not like", value, "sourceOrderNo");
            return (Criteria) this;
        }

        public Criteria andSourceOrderNoIn(List<String> values) {
            addCriterion("source_order_no in", values, "sourceOrderNo");
            return (Criteria) this;
        }

        public Criteria andSourceOrderNoNotIn(List<String> values) {
            addCriterion("source_order_no not in", values, "sourceOrderNo");
            return (Criteria) this;
        }

        public Criteria andSourceOrderNoBetween(String value1, String value2) {
            addCriterion("source_order_no between", value1, value2, "sourceOrderNo");
            return (Criteria) this;
        }

        public Criteria andSourceOrderNoNotBetween(String value1, String value2) {
            addCriterion("source_order_no not between", value1, value2, "sourceOrderNo");
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