package com.mallcai.fulfillment.infrastructure.object.wms;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OubDeliveryOrderDetailCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OubDeliveryOrderDetailCriteria() {
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

        public Criteria andDeliveryOrderNoIsNull() {
            addCriterion("delivery_order_no is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderNoIsNotNull() {
            addCriterion("delivery_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderNoEqualTo(String value) {
            addCriterion("delivery_order_no =", value, "deliveryOrderNo");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderNoNotEqualTo(String value) {
            addCriterion("delivery_order_no <>", value, "deliveryOrderNo");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderNoGreaterThan(String value) {
            addCriterion("delivery_order_no >", value, "deliveryOrderNo");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_order_no >=", value, "deliveryOrderNo");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderNoLessThan(String value) {
            addCriterion("delivery_order_no <", value, "deliveryOrderNo");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderNoLessThanOrEqualTo(String value) {
            addCriterion("delivery_order_no <=", value, "deliveryOrderNo");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderNoLike(String value) {
            addCriterion("delivery_order_no like", value, "deliveryOrderNo");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderNoNotLike(String value) {
            addCriterion("delivery_order_no not like", value, "deliveryOrderNo");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderNoIn(List<String> values) {
            addCriterion("delivery_order_no in", values, "deliveryOrderNo");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderNoNotIn(List<String> values) {
            addCriterion("delivery_order_no not in", values, "deliveryOrderNo");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderNoBetween(String value1, String value2) {
            addCriterion("delivery_order_no between", value1, value2, "deliveryOrderNo");
            return (Criteria) this;
        }

        public Criteria andDeliveryOrderNoNotBetween(String value1, String value2) {
            addCriterion("delivery_order_no not between", value1, value2, "deliveryOrderNo");
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

        public Criteria andSkuCodeIsNull() {
            addCriterion("sku_code is null");
            return (Criteria) this;
        }

        public Criteria andSkuCodeIsNotNull() {
            addCriterion("sku_code is not null");
            return (Criteria) this;
        }

        public Criteria andSkuCodeEqualTo(String value) {
            addCriterion("sku_code =", value, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeNotEqualTo(String value) {
            addCriterion("sku_code <>", value, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeGreaterThan(String value) {
            addCriterion("sku_code >", value, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sku_code >=", value, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeLessThan(String value) {
            addCriterion("sku_code <", value, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeLessThanOrEqualTo(String value) {
            addCriterion("sku_code <=", value, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeLike(String value) {
            addCriterion("sku_code like", value, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeNotLike(String value) {
            addCriterion("sku_code not like", value, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeIn(List<String> values) {
            addCriterion("sku_code in", values, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeNotIn(List<String> values) {
            addCriterion("sku_code not in", values, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeBetween(String value1, String value2) {
            addCriterion("sku_code between", value1, value2, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeNotBetween(String value1, String value2) {
            addCriterion("sku_code not between", value1, value2, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuNameIsNull() {
            addCriterion("sku_name is null");
            return (Criteria) this;
        }

        public Criteria andSkuNameIsNotNull() {
            addCriterion("sku_name is not null");
            return (Criteria) this;
        }

        public Criteria andSkuNameEqualTo(String value) {
            addCriterion("sku_name =", value, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameNotEqualTo(String value) {
            addCriterion("sku_name <>", value, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameGreaterThan(String value) {
            addCriterion("sku_name >", value, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameGreaterThanOrEqualTo(String value) {
            addCriterion("sku_name >=", value, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameLessThan(String value) {
            addCriterion("sku_name <", value, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameLessThanOrEqualTo(String value) {
            addCriterion("sku_name <=", value, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameLike(String value) {
            addCriterion("sku_name like", value, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameNotLike(String value) {
            addCriterion("sku_name not like", value, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameIn(List<String> values) {
            addCriterion("sku_name in", values, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameNotIn(List<String> values) {
            addCriterion("sku_name not in", values, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameBetween(String value1, String value2) {
            addCriterion("sku_name between", value1, value2, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameNotBetween(String value1, String value2) {
            addCriterion("sku_name not between", value1, value2, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuBarcodeIsNull() {
            addCriterion("sku_barcode is null");
            return (Criteria) this;
        }

        public Criteria andSkuBarcodeIsNotNull() {
            addCriterion("sku_barcode is not null");
            return (Criteria) this;
        }

        public Criteria andSkuBarcodeEqualTo(String value) {
            addCriterion("sku_barcode =", value, "skuBarcode");
            return (Criteria) this;
        }

        public Criteria andSkuBarcodeNotEqualTo(String value) {
            addCriterion("sku_barcode <>", value, "skuBarcode");
            return (Criteria) this;
        }

        public Criteria andSkuBarcodeGreaterThan(String value) {
            addCriterion("sku_barcode >", value, "skuBarcode");
            return (Criteria) this;
        }

        public Criteria andSkuBarcodeGreaterThanOrEqualTo(String value) {
            addCriterion("sku_barcode >=", value, "skuBarcode");
            return (Criteria) this;
        }

        public Criteria andSkuBarcodeLessThan(String value) {
            addCriterion("sku_barcode <", value, "skuBarcode");
            return (Criteria) this;
        }

        public Criteria andSkuBarcodeLessThanOrEqualTo(String value) {
            addCriterion("sku_barcode <=", value, "skuBarcode");
            return (Criteria) this;
        }

        public Criteria andSkuBarcodeLike(String value) {
            addCriterion("sku_barcode like", value, "skuBarcode");
            return (Criteria) this;
        }

        public Criteria andSkuBarcodeNotLike(String value) {
            addCriterion("sku_barcode not like", value, "skuBarcode");
            return (Criteria) this;
        }

        public Criteria andSkuBarcodeIn(List<String> values) {
            addCriterion("sku_barcode in", values, "skuBarcode");
            return (Criteria) this;
        }

        public Criteria andSkuBarcodeNotIn(List<String> values) {
            addCriterion("sku_barcode not in", values, "skuBarcode");
            return (Criteria) this;
        }

        public Criteria andSkuBarcodeBetween(String value1, String value2) {
            addCriterion("sku_barcode between", value1, value2, "skuBarcode");
            return (Criteria) this;
        }

        public Criteria andSkuBarcodeNotBetween(String value1, String value2) {
            addCriterion("sku_barcode not between", value1, value2, "skuBarcode");
            return (Criteria) this;
        }

        public Criteria andRawSkuCodeIsNull() {
            addCriterion("raw_sku_code is null");
            return (Criteria) this;
        }

        public Criteria andRawSkuCodeIsNotNull() {
            addCriterion("raw_sku_code is not null");
            return (Criteria) this;
        }

        public Criteria andRawSkuCodeEqualTo(String value) {
            addCriterion("raw_sku_code =", value, "rawSkuCode");
            return (Criteria) this;
        }

        public Criteria andRawSkuCodeNotEqualTo(String value) {
            addCriterion("raw_sku_code <>", value, "rawSkuCode");
            return (Criteria) this;
        }

        public Criteria andRawSkuCodeGreaterThan(String value) {
            addCriterion("raw_sku_code >", value, "rawSkuCode");
            return (Criteria) this;
        }

        public Criteria andRawSkuCodeGreaterThanOrEqualTo(String value) {
            addCriterion("raw_sku_code >=", value, "rawSkuCode");
            return (Criteria) this;
        }

        public Criteria andRawSkuCodeLessThan(String value) {
            addCriterion("raw_sku_code <", value, "rawSkuCode");
            return (Criteria) this;
        }

        public Criteria andRawSkuCodeLessThanOrEqualTo(String value) {
            addCriterion("raw_sku_code <=", value, "rawSkuCode");
            return (Criteria) this;
        }

        public Criteria andRawSkuCodeLike(String value) {
            addCriterion("raw_sku_code like", value, "rawSkuCode");
            return (Criteria) this;
        }

        public Criteria andRawSkuCodeNotLike(String value) {
            addCriterion("raw_sku_code not like", value, "rawSkuCode");
            return (Criteria) this;
        }

        public Criteria andRawSkuCodeIn(List<String> values) {
            addCriterion("raw_sku_code in", values, "rawSkuCode");
            return (Criteria) this;
        }

        public Criteria andRawSkuCodeNotIn(List<String> values) {
            addCriterion("raw_sku_code not in", values, "rawSkuCode");
            return (Criteria) this;
        }

        public Criteria andRawSkuCodeBetween(String value1, String value2) {
            addCriterion("raw_sku_code between", value1, value2, "rawSkuCode");
            return (Criteria) this;
        }

        public Criteria andRawSkuCodeNotBetween(String value1, String value2) {
            addCriterion("raw_sku_code not between", value1, value2, "rawSkuCode");
            return (Criteria) this;
        }

        public Criteria andRawSkuNameIsNull() {
            addCriterion("raw_sku_name is null");
            return (Criteria) this;
        }

        public Criteria andRawSkuNameIsNotNull() {
            addCriterion("raw_sku_name is not null");
            return (Criteria) this;
        }

        public Criteria andRawSkuNameEqualTo(String value) {
            addCriterion("raw_sku_name =", value, "rawSkuName");
            return (Criteria) this;
        }

        public Criteria andRawSkuNameNotEqualTo(String value) {
            addCriterion("raw_sku_name <>", value, "rawSkuName");
            return (Criteria) this;
        }

        public Criteria andRawSkuNameGreaterThan(String value) {
            addCriterion("raw_sku_name >", value, "rawSkuName");
            return (Criteria) this;
        }

        public Criteria andRawSkuNameGreaterThanOrEqualTo(String value) {
            addCriterion("raw_sku_name >=", value, "rawSkuName");
            return (Criteria) this;
        }

        public Criteria andRawSkuNameLessThan(String value) {
            addCriterion("raw_sku_name <", value, "rawSkuName");
            return (Criteria) this;
        }

        public Criteria andRawSkuNameLessThanOrEqualTo(String value) {
            addCriterion("raw_sku_name <=", value, "rawSkuName");
            return (Criteria) this;
        }

        public Criteria andRawSkuNameLike(String value) {
            addCriterion("raw_sku_name like", value, "rawSkuName");
            return (Criteria) this;
        }

        public Criteria andRawSkuNameNotLike(String value) {
            addCriterion("raw_sku_name not like", value, "rawSkuName");
            return (Criteria) this;
        }

        public Criteria andRawSkuNameIn(List<String> values) {
            addCriterion("raw_sku_name in", values, "rawSkuName");
            return (Criteria) this;
        }

        public Criteria andRawSkuNameNotIn(List<String> values) {
            addCriterion("raw_sku_name not in", values, "rawSkuName");
            return (Criteria) this;
        }

        public Criteria andRawSkuNameBetween(String value1, String value2) {
            addCriterion("raw_sku_name between", value1, value2, "rawSkuName");
            return (Criteria) this;
        }

        public Criteria andRawSkuNameNotBetween(String value1, String value2) {
            addCriterion("raw_sku_name not between", value1, value2, "rawSkuName");
            return (Criteria) this;
        }

        public Criteria andRawSkuBarcodeIsNull() {
            addCriterion("raw_sku_barcode is null");
            return (Criteria) this;
        }

        public Criteria andRawSkuBarcodeIsNotNull() {
            addCriterion("raw_sku_barcode is not null");
            return (Criteria) this;
        }

        public Criteria andRawSkuBarcodeEqualTo(String value) {
            addCriterion("raw_sku_barcode =", value, "rawSkuBarcode");
            return (Criteria) this;
        }

        public Criteria andRawSkuBarcodeNotEqualTo(String value) {
            addCriterion("raw_sku_barcode <>", value, "rawSkuBarcode");
            return (Criteria) this;
        }

        public Criteria andRawSkuBarcodeGreaterThan(String value) {
            addCriterion("raw_sku_barcode >", value, "rawSkuBarcode");
            return (Criteria) this;
        }

        public Criteria andRawSkuBarcodeGreaterThanOrEqualTo(String value) {
            addCriterion("raw_sku_barcode >=", value, "rawSkuBarcode");
            return (Criteria) this;
        }

        public Criteria andRawSkuBarcodeLessThan(String value) {
            addCriterion("raw_sku_barcode <", value, "rawSkuBarcode");
            return (Criteria) this;
        }

        public Criteria andRawSkuBarcodeLessThanOrEqualTo(String value) {
            addCriterion("raw_sku_barcode <=", value, "rawSkuBarcode");
            return (Criteria) this;
        }

        public Criteria andRawSkuBarcodeLike(String value) {
            addCriterion("raw_sku_barcode like", value, "rawSkuBarcode");
            return (Criteria) this;
        }

        public Criteria andRawSkuBarcodeNotLike(String value) {
            addCriterion("raw_sku_barcode not like", value, "rawSkuBarcode");
            return (Criteria) this;
        }

        public Criteria andRawSkuBarcodeIn(List<String> values) {
            addCriterion("raw_sku_barcode in", values, "rawSkuBarcode");
            return (Criteria) this;
        }

        public Criteria andRawSkuBarcodeNotIn(List<String> values) {
            addCriterion("raw_sku_barcode not in", values, "rawSkuBarcode");
            return (Criteria) this;
        }

        public Criteria andRawSkuBarcodeBetween(String value1, String value2) {
            addCriterion("raw_sku_barcode between", value1, value2, "rawSkuBarcode");
            return (Criteria) this;
        }

        public Criteria andRawSkuBarcodeNotBetween(String value1, String value2) {
            addCriterion("raw_sku_barcode not between", value1, value2, "rawSkuBarcode");
            return (Criteria) this;
        }

        public Criteria andSoldUnitIsNull() {
            addCriterion("sold_unit is null");
            return (Criteria) this;
        }

        public Criteria andSoldUnitIsNotNull() {
            addCriterion("sold_unit is not null");
            return (Criteria) this;
        }

        public Criteria andSoldUnitEqualTo(String value) {
            addCriterion("sold_unit =", value, "soldUnit");
            return (Criteria) this;
        }

        public Criteria andSoldUnitNotEqualTo(String value) {
            addCriterion("sold_unit <>", value, "soldUnit");
            return (Criteria) this;
        }

        public Criteria andSoldUnitGreaterThan(String value) {
            addCriterion("sold_unit >", value, "soldUnit");
            return (Criteria) this;
        }

        public Criteria andSoldUnitGreaterThanOrEqualTo(String value) {
            addCriterion("sold_unit >=", value, "soldUnit");
            return (Criteria) this;
        }

        public Criteria andSoldUnitLessThan(String value) {
            addCriterion("sold_unit <", value, "soldUnit");
            return (Criteria) this;
        }

        public Criteria andSoldUnitLessThanOrEqualTo(String value) {
            addCriterion("sold_unit <=", value, "soldUnit");
            return (Criteria) this;
        }

        public Criteria andSoldUnitLike(String value) {
            addCriterion("sold_unit like", value, "soldUnit");
            return (Criteria) this;
        }

        public Criteria andSoldUnitNotLike(String value) {
            addCriterion("sold_unit not like", value, "soldUnit");
            return (Criteria) this;
        }

        public Criteria andSoldUnitIn(List<String> values) {
            addCriterion("sold_unit in", values, "soldUnit");
            return (Criteria) this;
        }

        public Criteria andSoldUnitNotIn(List<String> values) {
            addCriterion("sold_unit not in", values, "soldUnit");
            return (Criteria) this;
        }

        public Criteria andSoldUnitBetween(String value1, String value2) {
            addCriterion("sold_unit between", value1, value2, "soldUnit");
            return (Criteria) this;
        }

        public Criteria andSoldUnitNotBetween(String value1, String value2) {
            addCriterion("sold_unit not between", value1, value2, "soldUnit");
            return (Criteria) this;
        }

        public Criteria andSoldQtyIsNull() {
            addCriterion("sold_qty is null");
            return (Criteria) this;
        }

        public Criteria andSoldQtyIsNotNull() {
            addCriterion("sold_qty is not null");
            return (Criteria) this;
        }

        public Criteria andSoldQtyEqualTo(BigDecimal value) {
            addCriterion("sold_qty =", value, "soldQty");
            return (Criteria) this;
        }

        public Criteria andSoldQtyNotEqualTo(BigDecimal value) {
            addCriterion("sold_qty <>", value, "soldQty");
            return (Criteria) this;
        }

        public Criteria andSoldQtyGreaterThan(BigDecimal value) {
            addCriterion("sold_qty >", value, "soldQty");
            return (Criteria) this;
        }

        public Criteria andSoldQtyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sold_qty >=", value, "soldQty");
            return (Criteria) this;
        }

        public Criteria andSoldQtyLessThan(BigDecimal value) {
            addCriterion("sold_qty <", value, "soldQty");
            return (Criteria) this;
        }

        public Criteria andSoldQtyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sold_qty <=", value, "soldQty");
            return (Criteria) this;
        }

        public Criteria andSoldQtyIn(List<BigDecimal> values) {
            addCriterion("sold_qty in", values, "soldQty");
            return (Criteria) this;
        }

        public Criteria andSoldQtyNotIn(List<BigDecimal> values) {
            addCriterion("sold_qty not in", values, "soldQty");
            return (Criteria) this;
        }

        public Criteria andSoldQtyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sold_qty between", value1, value2, "soldQty");
            return (Criteria) this;
        }

        public Criteria andSoldQtyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sold_qty not between", value1, value2, "soldQty");
            return (Criteria) this;
        }

        public Criteria andSoldContainerQtyIsNull() {
            addCriterion("sold_container_qty is null");
            return (Criteria) this;
        }

        public Criteria andSoldContainerQtyIsNotNull() {
            addCriterion("sold_container_qty is not null");
            return (Criteria) this;
        }

        public Criteria andSoldContainerQtyEqualTo(Short value) {
            addCriterion("sold_container_qty =", value, "soldContainerQty");
            return (Criteria) this;
        }

        public Criteria andSoldContainerQtyNotEqualTo(Short value) {
            addCriterion("sold_container_qty <>", value, "soldContainerQty");
            return (Criteria) this;
        }

        public Criteria andSoldContainerQtyGreaterThan(Short value) {
            addCriterion("sold_container_qty >", value, "soldContainerQty");
            return (Criteria) this;
        }

        public Criteria andSoldContainerQtyGreaterThanOrEqualTo(Short value) {
            addCriterion("sold_container_qty >=", value, "soldContainerQty");
            return (Criteria) this;
        }

        public Criteria andSoldContainerQtyLessThan(Short value) {
            addCriterion("sold_container_qty <", value, "soldContainerQty");
            return (Criteria) this;
        }

        public Criteria andSoldContainerQtyLessThanOrEqualTo(Short value) {
            addCriterion("sold_container_qty <=", value, "soldContainerQty");
            return (Criteria) this;
        }

        public Criteria andSoldContainerQtyIn(List<Short> values) {
            addCriterion("sold_container_qty in", values, "soldContainerQty");
            return (Criteria) this;
        }

        public Criteria andSoldContainerQtyNotIn(List<Short> values) {
            addCriterion("sold_container_qty not in", values, "soldContainerQty");
            return (Criteria) this;
        }

        public Criteria andSoldContainerQtyBetween(Short value1, Short value2) {
            addCriterion("sold_container_qty between", value1, value2, "soldContainerQty");
            return (Criteria) this;
        }

        public Criteria andSoldContainerQtyNotBetween(Short value1, Short value2) {
            addCriterion("sold_container_qty not between", value1, value2, "soldContainerQty");
            return (Criteria) this;
        }

        public Criteria andSoldLeftWeightIsNull() {
            addCriterion("sold_left_weight is null");
            return (Criteria) this;
        }

        public Criteria andSoldLeftWeightIsNotNull() {
            addCriterion("sold_left_weight is not null");
            return (Criteria) this;
        }

        public Criteria andSoldLeftWeightEqualTo(BigDecimal value) {
            addCriterion("sold_left_weight =", value, "soldLeftWeight");
            return (Criteria) this;
        }

        public Criteria andSoldLeftWeightNotEqualTo(BigDecimal value) {
            addCriterion("sold_left_weight <>", value, "soldLeftWeight");
            return (Criteria) this;
        }

        public Criteria andSoldLeftWeightGreaterThan(BigDecimal value) {
            addCriterion("sold_left_weight >", value, "soldLeftWeight");
            return (Criteria) this;
        }

        public Criteria andSoldLeftWeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sold_left_weight >=", value, "soldLeftWeight");
            return (Criteria) this;
        }

        public Criteria andSoldLeftWeightLessThan(BigDecimal value) {
            addCriterion("sold_left_weight <", value, "soldLeftWeight");
            return (Criteria) this;
        }

        public Criteria andSoldLeftWeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sold_left_weight <=", value, "soldLeftWeight");
            return (Criteria) this;
        }

        public Criteria andSoldLeftWeightIn(List<BigDecimal> values) {
            addCriterion("sold_left_weight in", values, "soldLeftWeight");
            return (Criteria) this;
        }

        public Criteria andSoldLeftWeightNotIn(List<BigDecimal> values) {
            addCriterion("sold_left_weight not in", values, "soldLeftWeight");
            return (Criteria) this;
        }

        public Criteria andSoldLeftWeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sold_left_weight between", value1, value2, "soldLeftWeight");
            return (Criteria) this;
        }

        public Criteria andSoldLeftWeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sold_left_weight not between", value1, value2, "soldLeftWeight");
            return (Criteria) this;
        }

        public Criteria andUpwardFloatingRatioIsNull() {
            addCriterion("upward_floating_ratio is null");
            return (Criteria) this;
        }

        public Criteria andUpwardFloatingRatioIsNotNull() {
            addCriterion("upward_floating_ratio is not null");
            return (Criteria) this;
        }

        public Criteria andUpwardFloatingRatioEqualTo(BigDecimal value) {
            addCriterion("upward_floating_ratio =", value, "upwardFloatingRatio");
            return (Criteria) this;
        }

        public Criteria andUpwardFloatingRatioNotEqualTo(BigDecimal value) {
            addCriterion("upward_floating_ratio <>", value, "upwardFloatingRatio");
            return (Criteria) this;
        }

        public Criteria andUpwardFloatingRatioGreaterThan(BigDecimal value) {
            addCriterion("upward_floating_ratio >", value, "upwardFloatingRatio");
            return (Criteria) this;
        }

        public Criteria andUpwardFloatingRatioGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("upward_floating_ratio >=", value, "upwardFloatingRatio");
            return (Criteria) this;
        }

        public Criteria andUpwardFloatingRatioLessThan(BigDecimal value) {
            addCriterion("upward_floating_ratio <", value, "upwardFloatingRatio");
            return (Criteria) this;
        }

        public Criteria andUpwardFloatingRatioLessThanOrEqualTo(BigDecimal value) {
            addCriterion("upward_floating_ratio <=", value, "upwardFloatingRatio");
            return (Criteria) this;
        }

        public Criteria andUpwardFloatingRatioIn(List<BigDecimal> values) {
            addCriterion("upward_floating_ratio in", values, "upwardFloatingRatio");
            return (Criteria) this;
        }

        public Criteria andUpwardFloatingRatioNotIn(List<BigDecimal> values) {
            addCriterion("upward_floating_ratio not in", values, "upwardFloatingRatio");
            return (Criteria) this;
        }

        public Criteria andUpwardFloatingRatioBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("upward_floating_ratio between", value1, value2, "upwardFloatingRatio");
            return (Criteria) this;
        }

        public Criteria andUpwardFloatingRatioNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("upward_floating_ratio not between", value1, value2, "upwardFloatingRatio");
            return (Criteria) this;
        }

        public Criteria andDeliveryQtyIsNull() {
            addCriterion("delivery_qty is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryQtyIsNotNull() {
            addCriterion("delivery_qty is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryQtyEqualTo(BigDecimal value) {
            addCriterion("delivery_qty =", value, "deliveryQty");
            return (Criteria) this;
        }

        public Criteria andDeliveryQtyNotEqualTo(BigDecimal value) {
            addCriterion("delivery_qty <>", value, "deliveryQty");
            return (Criteria) this;
        }

        public Criteria andDeliveryQtyGreaterThan(BigDecimal value) {
            addCriterion("delivery_qty >", value, "deliveryQty");
            return (Criteria) this;
        }

        public Criteria andDeliveryQtyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("delivery_qty >=", value, "deliveryQty");
            return (Criteria) this;
        }

        public Criteria andDeliveryQtyLessThan(BigDecimal value) {
            addCriterion("delivery_qty <", value, "deliveryQty");
            return (Criteria) this;
        }

        public Criteria andDeliveryQtyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("delivery_qty <=", value, "deliveryQty");
            return (Criteria) this;
        }

        public Criteria andDeliveryQtyIn(List<BigDecimal> values) {
            addCriterion("delivery_qty in", values, "deliveryQty");
            return (Criteria) this;
        }

        public Criteria andDeliveryQtyNotIn(List<BigDecimal> values) {
            addCriterion("delivery_qty not in", values, "deliveryQty");
            return (Criteria) this;
        }

        public Criteria andDeliveryQtyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("delivery_qty between", value1, value2, "deliveryQty");
            return (Criteria) this;
        }

        public Criteria andDeliveryQtyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("delivery_qty not between", value1, value2, "deliveryQty");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerQtyIsNull() {
            addCriterion("delivery_container_qty is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerQtyIsNotNull() {
            addCriterion("delivery_container_qty is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerQtyEqualTo(BigDecimal value) {
            addCriterion("delivery_container_qty =", value, "deliveryContainerQty");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerQtyNotEqualTo(BigDecimal value) {
            addCriterion("delivery_container_qty <>", value, "deliveryContainerQty");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerQtyGreaterThan(BigDecimal value) {
            addCriterion("delivery_container_qty >", value, "deliveryContainerQty");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerQtyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("delivery_container_qty >=", value, "deliveryContainerQty");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerQtyLessThan(BigDecimal value) {
            addCriterion("delivery_container_qty <", value, "deliveryContainerQty");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerQtyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("delivery_container_qty <=", value, "deliveryContainerQty");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerQtyIn(List<BigDecimal> values) {
            addCriterion("delivery_container_qty in", values, "deliveryContainerQty");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerQtyNotIn(List<BigDecimal> values) {
            addCriterion("delivery_container_qty not in", values, "deliveryContainerQty");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerQtyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("delivery_container_qty between", value1, value2, "deliveryContainerQty");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerQtyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("delivery_container_qty not between", value1, value2, "deliveryContainerQty");
            return (Criteria) this;
        }

        public Criteria andPickProQtyIsNull() {
            addCriterion("pick_pro_qty is null");
            return (Criteria) this;
        }

        public Criteria andPickProQtyIsNotNull() {
            addCriterion("pick_pro_qty is not null");
            return (Criteria) this;
        }

        public Criteria andPickProQtyEqualTo(BigDecimal value) {
            addCriterion("pick_pro_qty =", value, "pickProQty");
            return (Criteria) this;
        }

        public Criteria andPickProQtyNotEqualTo(BigDecimal value) {
            addCriterion("pick_pro_qty <>", value, "pickProQty");
            return (Criteria) this;
        }

        public Criteria andPickProQtyGreaterThan(BigDecimal value) {
            addCriterion("pick_pro_qty >", value, "pickProQty");
            return (Criteria) this;
        }

        public Criteria andPickProQtyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pick_pro_qty >=", value, "pickProQty");
            return (Criteria) this;
        }

        public Criteria andPickProQtyLessThan(BigDecimal value) {
            addCriterion("pick_pro_qty <", value, "pickProQty");
            return (Criteria) this;
        }

        public Criteria andPickProQtyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pick_pro_qty <=", value, "pickProQty");
            return (Criteria) this;
        }

        public Criteria andPickProQtyIn(List<BigDecimal> values) {
            addCriterion("pick_pro_qty in", values, "pickProQty");
            return (Criteria) this;
        }

        public Criteria andPickProQtyNotIn(List<BigDecimal> values) {
            addCriterion("pick_pro_qty not in", values, "pickProQty");
            return (Criteria) this;
        }

        public Criteria andPickProQtyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pick_pro_qty between", value1, value2, "pickProQty");
            return (Criteria) this;
        }

        public Criteria andPickProQtyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pick_pro_qty not between", value1, value2, "pickProQty");
            return (Criteria) this;
        }

        public Criteria andPickBoxQtyIsNull() {
            addCriterion("pick_box_qty is null");
            return (Criteria) this;
        }

        public Criteria andPickBoxQtyIsNotNull() {
            addCriterion("pick_box_qty is not null");
            return (Criteria) this;
        }

        public Criteria andPickBoxQtyEqualTo(BigDecimal value) {
            addCriterion("pick_box_qty =", value, "pickBoxQty");
            return (Criteria) this;
        }

        public Criteria andPickBoxQtyNotEqualTo(BigDecimal value) {
            addCriterion("pick_box_qty <>", value, "pickBoxQty");
            return (Criteria) this;
        }

        public Criteria andPickBoxQtyGreaterThan(BigDecimal value) {
            addCriterion("pick_box_qty >", value, "pickBoxQty");
            return (Criteria) this;
        }

        public Criteria andPickBoxQtyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("pick_box_qty >=", value, "pickBoxQty");
            return (Criteria) this;
        }

        public Criteria andPickBoxQtyLessThan(BigDecimal value) {
            addCriterion("pick_box_qty <", value, "pickBoxQty");
            return (Criteria) this;
        }

        public Criteria andPickBoxQtyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("pick_box_qty <=", value, "pickBoxQty");
            return (Criteria) this;
        }

        public Criteria andPickBoxQtyIn(List<BigDecimal> values) {
            addCriterion("pick_box_qty in", values, "pickBoxQty");
            return (Criteria) this;
        }

        public Criteria andPickBoxQtyNotIn(List<BigDecimal> values) {
            addCriterion("pick_box_qty not in", values, "pickBoxQty");
            return (Criteria) this;
        }

        public Criteria andPickBoxQtyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pick_box_qty between", value1, value2, "pickBoxQty");
            return (Criteria) this;
        }

        public Criteria andPickBoxQtyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("pick_box_qty not between", value1, value2, "pickBoxQty");
            return (Criteria) this;
        }

        public Criteria andCollectQtyIsNull() {
            addCriterion("collect_qty is null");
            return (Criteria) this;
        }

        public Criteria andCollectQtyIsNotNull() {
            addCriterion("collect_qty is not null");
            return (Criteria) this;
        }

        public Criteria andCollectQtyEqualTo(Long value) {
            addCriterion("collect_qty =", value, "collectQty");
            return (Criteria) this;
        }

        public Criteria andCollectQtyNotEqualTo(Long value) {
            addCriterion("collect_qty <>", value, "collectQty");
            return (Criteria) this;
        }

        public Criteria andCollectQtyGreaterThan(Long value) {
            addCriterion("collect_qty >", value, "collectQty");
            return (Criteria) this;
        }

        public Criteria andCollectQtyGreaterThanOrEqualTo(Long value) {
            addCriterion("collect_qty >=", value, "collectQty");
            return (Criteria) this;
        }

        public Criteria andCollectQtyLessThan(Long value) {
            addCriterion("collect_qty <", value, "collectQty");
            return (Criteria) this;
        }

        public Criteria andCollectQtyLessThanOrEqualTo(Long value) {
            addCriterion("collect_qty <=", value, "collectQty");
            return (Criteria) this;
        }

        public Criteria andCollectQtyIn(List<Long> values) {
            addCriterion("collect_qty in", values, "collectQty");
            return (Criteria) this;
        }

        public Criteria andCollectQtyNotIn(List<Long> values) {
            addCriterion("collect_qty not in", values, "collectQty");
            return (Criteria) this;
        }

        public Criteria andCollectQtyBetween(Long value1, Long value2) {
            addCriterion("collect_qty between", value1, value2, "collectQty");
            return (Criteria) this;
        }

        public Criteria andCollectQtyNotBetween(Long value1, Long value2) {
            addCriterion("collect_qty not between", value1, value2, "collectQty");
            return (Criteria) this;
        }

        public Criteria andStockoutQtyIsNull() {
            addCriterion("stockout_qty is null");
            return (Criteria) this;
        }

        public Criteria andStockoutQtyIsNotNull() {
            addCriterion("stockout_qty is not null");
            return (Criteria) this;
        }

        public Criteria andStockoutQtyEqualTo(Long value) {
            addCriterion("stockout_qty =", value, "stockoutQty");
            return (Criteria) this;
        }

        public Criteria andStockoutQtyNotEqualTo(Long value) {
            addCriterion("stockout_qty <>", value, "stockoutQty");
            return (Criteria) this;
        }

        public Criteria andStockoutQtyGreaterThan(Long value) {
            addCriterion("stockout_qty >", value, "stockoutQty");
            return (Criteria) this;
        }

        public Criteria andStockoutQtyGreaterThanOrEqualTo(Long value) {
            addCriterion("stockout_qty >=", value, "stockoutQty");
            return (Criteria) this;
        }

        public Criteria andStockoutQtyLessThan(Long value) {
            addCriterion("stockout_qty <", value, "stockoutQty");
            return (Criteria) this;
        }

        public Criteria andStockoutQtyLessThanOrEqualTo(Long value) {
            addCriterion("stockout_qty <=", value, "stockoutQty");
            return (Criteria) this;
        }

        public Criteria andStockoutQtyIn(List<Long> values) {
            addCriterion("stockout_qty in", values, "stockoutQty");
            return (Criteria) this;
        }

        public Criteria andStockoutQtyNotIn(List<Long> values) {
            addCriterion("stockout_qty not in", values, "stockoutQty");
            return (Criteria) this;
        }

        public Criteria andStockoutQtyBetween(Long value1, Long value2) {
            addCriterion("stockout_qty between", value1, value2, "stockoutQty");
            return (Criteria) this;
        }

        public Criteria andStockoutQtyNotBetween(Long value1, Long value2) {
            addCriterion("stockout_qty not between", value1, value2, "stockoutQty");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerUnitIsNull() {
            addCriterion("delivery_container_unit is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerUnitIsNotNull() {
            addCriterion("delivery_container_unit is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerUnitEqualTo(String value) {
            addCriterion("delivery_container_unit =", value, "deliveryContainerUnit");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerUnitNotEqualTo(String value) {
            addCriterion("delivery_container_unit <>", value, "deliveryContainerUnit");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerUnitGreaterThan(String value) {
            addCriterion("delivery_container_unit >", value, "deliveryContainerUnit");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerUnitGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_container_unit >=", value, "deliveryContainerUnit");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerUnitLessThan(String value) {
            addCriterion("delivery_container_unit <", value, "deliveryContainerUnit");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerUnitLessThanOrEqualTo(String value) {
            addCriterion("delivery_container_unit <=", value, "deliveryContainerUnit");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerUnitLike(String value) {
            addCriterion("delivery_container_unit like", value, "deliveryContainerUnit");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerUnitNotLike(String value) {
            addCriterion("delivery_container_unit not like", value, "deliveryContainerUnit");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerUnitIn(List<String> values) {
            addCriterion("delivery_container_unit in", values, "deliveryContainerUnit");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerUnitNotIn(List<String> values) {
            addCriterion("delivery_container_unit not in", values, "deliveryContainerUnit");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerUnitBetween(String value1, String value2) {
            addCriterion("delivery_container_unit between", value1, value2, "deliveryContainerUnit");
            return (Criteria) this;
        }

        public Criteria andDeliveryContainerUnitNotBetween(String value1, String value2) {
            addCriterion("delivery_container_unit not between", value1, value2, "deliveryContainerUnit");
            return (Criteria) this;
        }

        public Criteria andDeliverLeftWeightIsNull() {
            addCriterion("deliver_left_weight is null");
            return (Criteria) this;
        }

        public Criteria andDeliverLeftWeightIsNotNull() {
            addCriterion("deliver_left_weight is not null");
            return (Criteria) this;
        }

        public Criteria andDeliverLeftWeightEqualTo(BigDecimal value) {
            addCriterion("deliver_left_weight =", value, "deliverLeftWeight");
            return (Criteria) this;
        }

        public Criteria andDeliverLeftWeightNotEqualTo(BigDecimal value) {
            addCriterion("deliver_left_weight <>", value, "deliverLeftWeight");
            return (Criteria) this;
        }

        public Criteria andDeliverLeftWeightGreaterThan(BigDecimal value) {
            addCriterion("deliver_left_weight >", value, "deliverLeftWeight");
            return (Criteria) this;
        }

        public Criteria andDeliverLeftWeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("deliver_left_weight >=", value, "deliverLeftWeight");
            return (Criteria) this;
        }

        public Criteria andDeliverLeftWeightLessThan(BigDecimal value) {
            addCriterion("deliver_left_weight <", value, "deliverLeftWeight");
            return (Criteria) this;
        }

        public Criteria andDeliverLeftWeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("deliver_left_weight <=", value, "deliverLeftWeight");
            return (Criteria) this;
        }

        public Criteria andDeliverLeftWeightIn(List<BigDecimal> values) {
            addCriterion("deliver_left_weight in", values, "deliverLeftWeight");
            return (Criteria) this;
        }

        public Criteria andDeliverLeftWeightNotIn(List<BigDecimal> values) {
            addCriterion("deliver_left_weight not in", values, "deliverLeftWeight");
            return (Criteria) this;
        }

        public Criteria andDeliverLeftWeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deliver_left_weight between", value1, value2, "deliverLeftWeight");
            return (Criteria) this;
        }

        public Criteria andDeliverLeftWeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("deliver_left_weight not between", value1, value2, "deliverLeftWeight");
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

        public Criteria andWeighingTimeIsNull() {
            addCriterion("weighing_time is null");
            return (Criteria) this;
        }

        public Criteria andWeighingTimeIsNotNull() {
            addCriterion("weighing_time is not null");
            return (Criteria) this;
        }

        public Criteria andWeighingTimeEqualTo(Date value) {
            addCriterion("weighing_time =", value, "weighingTime");
            return (Criteria) this;
        }

        public Criteria andWeighingTimeNotEqualTo(Date value) {
            addCriterion("weighing_time <>", value, "weighingTime");
            return (Criteria) this;
        }

        public Criteria andWeighingTimeGreaterThan(Date value) {
            addCriterion("weighing_time >", value, "weighingTime");
            return (Criteria) this;
        }

        public Criteria andWeighingTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("weighing_time >=", value, "weighingTime");
            return (Criteria) this;
        }

        public Criteria andWeighingTimeLessThan(Date value) {
            addCriterion("weighing_time <", value, "weighingTime");
            return (Criteria) this;
        }

        public Criteria andWeighingTimeLessThanOrEqualTo(Date value) {
            addCriterion("weighing_time <=", value, "weighingTime");
            return (Criteria) this;
        }

        public Criteria andWeighingTimeIn(List<Date> values) {
            addCriterion("weighing_time in", values, "weighingTime");
            return (Criteria) this;
        }

        public Criteria andWeighingTimeNotIn(List<Date> values) {
            addCriterion("weighing_time not in", values, "weighingTime");
            return (Criteria) this;
        }

        public Criteria andWeighingTimeBetween(Date value1, Date value2) {
            addCriterion("weighing_time between", value1, value2, "weighingTime");
            return (Criteria) this;
        }

        public Criteria andWeighingTimeNotBetween(Date value1, Date value2) {
            addCriterion("weighing_time not between", value1, value2, "weighingTime");
            return (Criteria) this;
        }

        public Criteria andWeightingUserIdIsNull() {
            addCriterion("weighting_user_id is null");
            return (Criteria) this;
        }

        public Criteria andWeightingUserIdIsNotNull() {
            addCriterion("weighting_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andWeightingUserIdEqualTo(Integer value) {
            addCriterion("weighting_user_id =", value, "weightingUserId");
            return (Criteria) this;
        }

        public Criteria andWeightingUserIdNotEqualTo(Integer value) {
            addCriterion("weighting_user_id <>", value, "weightingUserId");
            return (Criteria) this;
        }

        public Criteria andWeightingUserIdGreaterThan(Integer value) {
            addCriterion("weighting_user_id >", value, "weightingUserId");
            return (Criteria) this;
        }

        public Criteria andWeightingUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("weighting_user_id >=", value, "weightingUserId");
            return (Criteria) this;
        }

        public Criteria andWeightingUserIdLessThan(Integer value) {
            addCriterion("weighting_user_id <", value, "weightingUserId");
            return (Criteria) this;
        }

        public Criteria andWeightingUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("weighting_user_id <=", value, "weightingUserId");
            return (Criteria) this;
        }

        public Criteria andWeightingUserIdIn(List<Integer> values) {
            addCriterion("weighting_user_id in", values, "weightingUserId");
            return (Criteria) this;
        }

        public Criteria andWeightingUserIdNotIn(List<Integer> values) {
            addCriterion("weighting_user_id not in", values, "weightingUserId");
            return (Criteria) this;
        }

        public Criteria andWeightingUserIdBetween(Integer value1, Integer value2) {
            addCriterion("weighting_user_id between", value1, value2, "weightingUserId");
            return (Criteria) this;
        }

        public Criteria andWeightingUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("weighting_user_id not between", value1, value2, "weightingUserId");
            return (Criteria) this;
        }

        public Criteria andSorterTimeIsNull() {
            addCriterion("sorter_time is null");
            return (Criteria) this;
        }

        public Criteria andSorterTimeIsNotNull() {
            addCriterion("sorter_time is not null");
            return (Criteria) this;
        }

        public Criteria andSorterTimeEqualTo(Date value) {
            addCriterion("sorter_time =", value, "sorterTime");
            return (Criteria) this;
        }

        public Criteria andSorterTimeNotEqualTo(Date value) {
            addCriterion("sorter_time <>", value, "sorterTime");
            return (Criteria) this;
        }

        public Criteria andSorterTimeGreaterThan(Date value) {
            addCriterion("sorter_time >", value, "sorterTime");
            return (Criteria) this;
        }

        public Criteria andSorterTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("sorter_time >=", value, "sorterTime");
            return (Criteria) this;
        }

        public Criteria andSorterTimeLessThan(Date value) {
            addCriterion("sorter_time <", value, "sorterTime");
            return (Criteria) this;
        }

        public Criteria andSorterTimeLessThanOrEqualTo(Date value) {
            addCriterion("sorter_time <=", value, "sorterTime");
            return (Criteria) this;
        }

        public Criteria andSorterTimeIn(List<Date> values) {
            addCriterion("sorter_time in", values, "sorterTime");
            return (Criteria) this;
        }

        public Criteria andSorterTimeNotIn(List<Date> values) {
            addCriterion("sorter_time not in", values, "sorterTime");
            return (Criteria) this;
        }

        public Criteria andSorterTimeBetween(Date value1, Date value2) {
            addCriterion("sorter_time between", value1, value2, "sorterTime");
            return (Criteria) this;
        }

        public Criteria andSorterTimeNotBetween(Date value1, Date value2) {
            addCriterion("sorter_time not between", value1, value2, "sorterTime");
            return (Criteria) this;
        }

        public Criteria andSorterUserIdIsNull() {
            addCriterion("sorter_user_id is null");
            return (Criteria) this;
        }

        public Criteria andSorterUserIdIsNotNull() {
            addCriterion("sorter_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andSorterUserIdEqualTo(Integer value) {
            addCriterion("sorter_user_id =", value, "sorterUserId");
            return (Criteria) this;
        }

        public Criteria andSorterUserIdNotEqualTo(Integer value) {
            addCriterion("sorter_user_id <>", value, "sorterUserId");
            return (Criteria) this;
        }

        public Criteria andSorterUserIdGreaterThan(Integer value) {
            addCriterion("sorter_user_id >", value, "sorterUserId");
            return (Criteria) this;
        }

        public Criteria andSorterUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("sorter_user_id >=", value, "sorterUserId");
            return (Criteria) this;
        }

        public Criteria andSorterUserIdLessThan(Integer value) {
            addCriterion("sorter_user_id <", value, "sorterUserId");
            return (Criteria) this;
        }

        public Criteria andSorterUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("sorter_user_id <=", value, "sorterUserId");
            return (Criteria) this;
        }

        public Criteria andSorterUserIdIn(List<Integer> values) {
            addCriterion("sorter_user_id in", values, "sorterUserId");
            return (Criteria) this;
        }

        public Criteria andSorterUserIdNotIn(List<Integer> values) {
            addCriterion("sorter_user_id not in", values, "sorterUserId");
            return (Criteria) this;
        }

        public Criteria andSorterUserIdBetween(Integer value1, Integer value2) {
            addCriterion("sorter_user_id between", value1, value2, "sorterUserId");
            return (Criteria) this;
        }

        public Criteria andSorterUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("sorter_user_id not between", value1, value2, "sorterUserId");
            return (Criteria) this;
        }

        public Criteria andLeftWeightQrcodeIsNull() {
            addCriterion("left_weight_qrcode is null");
            return (Criteria) this;
        }

        public Criteria andLeftWeightQrcodeIsNotNull() {
            addCriterion("left_weight_qrcode is not null");
            return (Criteria) this;
        }

        public Criteria andLeftWeightQrcodeEqualTo(String value) {
            addCriterion("left_weight_qrcode =", value, "leftWeightQrcode");
            return (Criteria) this;
        }

        public Criteria andLeftWeightQrcodeNotEqualTo(String value) {
            addCriterion("left_weight_qrcode <>", value, "leftWeightQrcode");
            return (Criteria) this;
        }

        public Criteria andLeftWeightQrcodeGreaterThan(String value) {
            addCriterion("left_weight_qrcode >", value, "leftWeightQrcode");
            return (Criteria) this;
        }

        public Criteria andLeftWeightQrcodeGreaterThanOrEqualTo(String value) {
            addCriterion("left_weight_qrcode >=", value, "leftWeightQrcode");
            return (Criteria) this;
        }

        public Criteria andLeftWeightQrcodeLessThan(String value) {
            addCriterion("left_weight_qrcode <", value, "leftWeightQrcode");
            return (Criteria) this;
        }

        public Criteria andLeftWeightQrcodeLessThanOrEqualTo(String value) {
            addCriterion("left_weight_qrcode <=", value, "leftWeightQrcode");
            return (Criteria) this;
        }

        public Criteria andLeftWeightQrcodeLike(String value) {
            addCriterion("left_weight_qrcode like", value, "leftWeightQrcode");
            return (Criteria) this;
        }

        public Criteria andLeftWeightQrcodeNotLike(String value) {
            addCriterion("left_weight_qrcode not like", value, "leftWeightQrcode");
            return (Criteria) this;
        }

        public Criteria andLeftWeightQrcodeIn(List<String> values) {
            addCriterion("left_weight_qrcode in", values, "leftWeightQrcode");
            return (Criteria) this;
        }

        public Criteria andLeftWeightQrcodeNotIn(List<String> values) {
            addCriterion("left_weight_qrcode not in", values, "leftWeightQrcode");
            return (Criteria) this;
        }

        public Criteria andLeftWeightQrcodeBetween(String value1, String value2) {
            addCriterion("left_weight_qrcode between", value1, value2, "leftWeightQrcode");
            return (Criteria) this;
        }

        public Criteria andLeftWeightQrcodeNotBetween(String value1, String value2) {
            addCriterion("left_weight_qrcode not between", value1, value2, "leftWeightQrcode");
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

        public Criteria andLackStatusIsNull() {
            addCriterion("lack_status is null");
            return (Criteria) this;
        }

        public Criteria andLackStatusIsNotNull() {
            addCriterion("lack_status is not null");
            return (Criteria) this;
        }

        public Criteria andLackStatusEqualTo(Byte value) {
            addCriterion("lack_status =", value, "lackStatus");
            return (Criteria) this;
        }

        public Criteria andLackStatusNotEqualTo(Byte value) {
            addCriterion("lack_status <>", value, "lackStatus");
            return (Criteria) this;
        }

        public Criteria andLackStatusGreaterThan(Byte value) {
            addCriterion("lack_status >", value, "lackStatus");
            return (Criteria) this;
        }

        public Criteria andLackStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("lack_status >=", value, "lackStatus");
            return (Criteria) this;
        }

        public Criteria andLackStatusLessThan(Byte value) {
            addCriterion("lack_status <", value, "lackStatus");
            return (Criteria) this;
        }

        public Criteria andLackStatusLessThanOrEqualTo(Byte value) {
            addCriterion("lack_status <=", value, "lackStatus");
            return (Criteria) this;
        }

        public Criteria andLackStatusIn(List<Byte> values) {
            addCriterion("lack_status in", values, "lackStatus");
            return (Criteria) this;
        }

        public Criteria andLackStatusNotIn(List<Byte> values) {
            addCriterion("lack_status not in", values, "lackStatus");
            return (Criteria) this;
        }

        public Criteria andLackStatusBetween(Byte value1, Byte value2) {
            addCriterion("lack_status between", value1, value2, "lackStatus");
            return (Criteria) this;
        }

        public Criteria andLackStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("lack_status not between", value1, value2, "lackStatus");
            return (Criteria) this;
        }

        public Criteria andWeightStartTimeIsNull() {
            addCriterion("weight_start_time is null");
            return (Criteria) this;
        }

        public Criteria andWeightStartTimeIsNotNull() {
            addCriterion("weight_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andWeightStartTimeEqualTo(Date value) {
            addCriterion("weight_start_time =", value, "weightStartTime");
            return (Criteria) this;
        }

        public Criteria andWeightStartTimeNotEqualTo(Date value) {
            addCriterion("weight_start_time <>", value, "weightStartTime");
            return (Criteria) this;
        }

        public Criteria andWeightStartTimeGreaterThan(Date value) {
            addCriterion("weight_start_time >", value, "weightStartTime");
            return (Criteria) this;
        }

        public Criteria andWeightStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("weight_start_time >=", value, "weightStartTime");
            return (Criteria) this;
        }

        public Criteria andWeightStartTimeLessThan(Date value) {
            addCriterion("weight_start_time <", value, "weightStartTime");
            return (Criteria) this;
        }

        public Criteria andWeightStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("weight_start_time <=", value, "weightStartTime");
            return (Criteria) this;
        }

        public Criteria andWeightStartTimeIn(List<Date> values) {
            addCriterion("weight_start_time in", values, "weightStartTime");
            return (Criteria) this;
        }

        public Criteria andWeightStartTimeNotIn(List<Date> values) {
            addCriterion("weight_start_time not in", values, "weightStartTime");
            return (Criteria) this;
        }

        public Criteria andWeightStartTimeBetween(Date value1, Date value2) {
            addCriterion("weight_start_time between", value1, value2, "weightStartTime");
            return (Criteria) this;
        }

        public Criteria andWeightStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("weight_start_time not between", value1, value2, "weightStartTime");
            return (Criteria) this;
        }

        public Criteria andWeightConfirmTimeIsNull() {
            addCriterion("weight_confirm_time is null");
            return (Criteria) this;
        }

        public Criteria andWeightConfirmTimeIsNotNull() {
            addCriterion("weight_confirm_time is not null");
            return (Criteria) this;
        }

        public Criteria andWeightConfirmTimeEqualTo(Date value) {
            addCriterion("weight_confirm_time =", value, "weightConfirmTime");
            return (Criteria) this;
        }

        public Criteria andWeightConfirmTimeNotEqualTo(Date value) {
            addCriterion("weight_confirm_time <>", value, "weightConfirmTime");
            return (Criteria) this;
        }

        public Criteria andWeightConfirmTimeGreaterThan(Date value) {
            addCriterion("weight_confirm_time >", value, "weightConfirmTime");
            return (Criteria) this;
        }

        public Criteria andWeightConfirmTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("weight_confirm_time >=", value, "weightConfirmTime");
            return (Criteria) this;
        }

        public Criteria andWeightConfirmTimeLessThan(Date value) {
            addCriterion("weight_confirm_time <", value, "weightConfirmTime");
            return (Criteria) this;
        }

        public Criteria andWeightConfirmTimeLessThanOrEqualTo(Date value) {
            addCriterion("weight_confirm_time <=", value, "weightConfirmTime");
            return (Criteria) this;
        }

        public Criteria andWeightConfirmTimeIn(List<Date> values) {
            addCriterion("weight_confirm_time in", values, "weightConfirmTime");
            return (Criteria) this;
        }

        public Criteria andWeightConfirmTimeNotIn(List<Date> values) {
            addCriterion("weight_confirm_time not in", values, "weightConfirmTime");
            return (Criteria) this;
        }

        public Criteria andWeightConfirmTimeBetween(Date value1, Date value2) {
            addCriterion("weight_confirm_time between", value1, value2, "weightConfirmTime");
            return (Criteria) this;
        }

        public Criteria andWeightConfirmTimeNotBetween(Date value1, Date value2) {
            addCriterion("weight_confirm_time not between", value1, value2, "weightConfirmTime");
            return (Criteria) this;
        }

        public Criteria andWeightUserScoreIsNull() {
            addCriterion("weight_user_score is null");
            return (Criteria) this;
        }

        public Criteria andWeightUserScoreIsNotNull() {
            addCriterion("weight_user_score is not null");
            return (Criteria) this;
        }

        public Criteria andWeightUserScoreEqualTo(Integer value) {
            addCriterion("weight_user_score =", value, "weightUserScore");
            return (Criteria) this;
        }

        public Criteria andWeightUserScoreNotEqualTo(Integer value) {
            addCriterion("weight_user_score <>", value, "weightUserScore");
            return (Criteria) this;
        }

        public Criteria andWeightUserScoreGreaterThan(Integer value) {
            addCriterion("weight_user_score >", value, "weightUserScore");
            return (Criteria) this;
        }

        public Criteria andWeightUserScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("weight_user_score >=", value, "weightUserScore");
            return (Criteria) this;
        }

        public Criteria andWeightUserScoreLessThan(Integer value) {
            addCriterion("weight_user_score <", value, "weightUserScore");
            return (Criteria) this;
        }

        public Criteria andWeightUserScoreLessThanOrEqualTo(Integer value) {
            addCriterion("weight_user_score <=", value, "weightUserScore");
            return (Criteria) this;
        }

        public Criteria andWeightUserScoreIn(List<Integer> values) {
            addCriterion("weight_user_score in", values, "weightUserScore");
            return (Criteria) this;
        }

        public Criteria andWeightUserScoreNotIn(List<Integer> values) {
            addCriterion("weight_user_score not in", values, "weightUserScore");
            return (Criteria) this;
        }

        public Criteria andWeightUserScoreBetween(Integer value1, Integer value2) {
            addCriterion("weight_user_score between", value1, value2, "weightUserScore");
            return (Criteria) this;
        }

        public Criteria andWeightUserScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("weight_user_score not between", value1, value2, "weightUserScore");
            return (Criteria) this;
        }

        public Criteria andSkuPercentIsNull() {
            addCriterion("sku_percent is null");
            return (Criteria) this;
        }

        public Criteria andSkuPercentIsNotNull() {
            addCriterion("sku_percent is not null");
            return (Criteria) this;
        }

        public Criteria andSkuPercentEqualTo(BigDecimal value) {
            addCriterion("sku_percent =", value, "skuPercent");
            return (Criteria) this;
        }

        public Criteria andSkuPercentNotEqualTo(BigDecimal value) {
            addCriterion("sku_percent <>", value, "skuPercent");
            return (Criteria) this;
        }

        public Criteria andSkuPercentGreaterThan(BigDecimal value) {
            addCriterion("sku_percent >", value, "skuPercent");
            return (Criteria) this;
        }

        public Criteria andSkuPercentGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sku_percent >=", value, "skuPercent");
            return (Criteria) this;
        }

        public Criteria andSkuPercentLessThan(BigDecimal value) {
            addCriterion("sku_percent <", value, "skuPercent");
            return (Criteria) this;
        }

        public Criteria andSkuPercentLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sku_percent <=", value, "skuPercent");
            return (Criteria) this;
        }

        public Criteria andSkuPercentIn(List<BigDecimal> values) {
            addCriterion("sku_percent in", values, "skuPercent");
            return (Criteria) this;
        }

        public Criteria andSkuPercentNotIn(List<BigDecimal> values) {
            addCriterion("sku_percent not in", values, "skuPercent");
            return (Criteria) this;
        }

        public Criteria andSkuPercentBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sku_percent between", value1, value2, "skuPercent");
            return (Criteria) this;
        }

        public Criteria andSkuPercentNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sku_percent not between", value1, value2, "skuPercent");
            return (Criteria) this;
        }

        public Criteria andScanStatusIsNull() {
            addCriterion("scan_status is null");
            return (Criteria) this;
        }

        public Criteria andScanStatusIsNotNull() {
            addCriterion("scan_status is not null");
            return (Criteria) this;
        }

        public Criteria andScanStatusEqualTo(Byte value) {
            addCriterion("scan_status =", value, "scanStatus");
            return (Criteria) this;
        }

        public Criteria andScanStatusNotEqualTo(Byte value) {
            addCriterion("scan_status <>", value, "scanStatus");
            return (Criteria) this;
        }

        public Criteria andScanStatusGreaterThan(Byte value) {
            addCriterion("scan_status >", value, "scanStatus");
            return (Criteria) this;
        }

        public Criteria andScanStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("scan_status >=", value, "scanStatus");
            return (Criteria) this;
        }

        public Criteria andScanStatusLessThan(Byte value) {
            addCriterion("scan_status <", value, "scanStatus");
            return (Criteria) this;
        }

        public Criteria andScanStatusLessThanOrEqualTo(Byte value) {
            addCriterion("scan_status <=", value, "scanStatus");
            return (Criteria) this;
        }

        public Criteria andScanStatusIn(List<Byte> values) {
            addCriterion("scan_status in", values, "scanStatus");
            return (Criteria) this;
        }

        public Criteria andScanStatusNotIn(List<Byte> values) {
            addCriterion("scan_status not in", values, "scanStatus");
            return (Criteria) this;
        }

        public Criteria andScanStatusBetween(Byte value1, Byte value2) {
            addCriterion("scan_status between", value1, value2, "scanStatus");
            return (Criteria) this;
        }

        public Criteria andScanStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("scan_status not between", value1, value2, "scanStatus");
            return (Criteria) this;
        }

        public Criteria andAccountingUnitIsNull() {
            addCriterion("accounting_unit is null");
            return (Criteria) this;
        }

        public Criteria andAccountingUnitIsNotNull() {
            addCriterion("accounting_unit is not null");
            return (Criteria) this;
        }

        public Criteria andAccountingUnitEqualTo(String value) {
            addCriterion("accounting_unit =", value, "accountingUnit");
            return (Criteria) this;
        }

        public Criteria andAccountingUnitNotEqualTo(String value) {
            addCriterion("accounting_unit <>", value, "accountingUnit");
            return (Criteria) this;
        }

        public Criteria andAccountingUnitGreaterThan(String value) {
            addCriterion("accounting_unit >", value, "accountingUnit");
            return (Criteria) this;
        }

        public Criteria andAccountingUnitGreaterThanOrEqualTo(String value) {
            addCriterion("accounting_unit >=", value, "accountingUnit");
            return (Criteria) this;
        }

        public Criteria andAccountingUnitLessThan(String value) {
            addCriterion("accounting_unit <", value, "accountingUnit");
            return (Criteria) this;
        }

        public Criteria andAccountingUnitLessThanOrEqualTo(String value) {
            addCriterion("accounting_unit <=", value, "accountingUnit");
            return (Criteria) this;
        }

        public Criteria andAccountingUnitLike(String value) {
            addCriterion("accounting_unit like", value, "accountingUnit");
            return (Criteria) this;
        }

        public Criteria andAccountingUnitNotLike(String value) {
            addCriterion("accounting_unit not like", value, "accountingUnit");
            return (Criteria) this;
        }

        public Criteria andAccountingUnitIn(List<String> values) {
            addCriterion("accounting_unit in", values, "accountingUnit");
            return (Criteria) this;
        }

        public Criteria andAccountingUnitNotIn(List<String> values) {
            addCriterion("accounting_unit not in", values, "accountingUnit");
            return (Criteria) this;
        }

        public Criteria andAccountingUnitBetween(String value1, String value2) {
            addCriterion("accounting_unit between", value1, value2, "accountingUnit");
            return (Criteria) this;
        }

        public Criteria andAccountingUnitNotBetween(String value1, String value2) {
            addCriterion("accounting_unit not between", value1, value2, "accountingUnit");
            return (Criteria) this;
        }

        public Criteria andReceivingUnitIsNull() {
            addCriterion("receiving_unit is null");
            return (Criteria) this;
        }

        public Criteria andReceivingUnitIsNotNull() {
            addCriterion("receiving_unit is not null");
            return (Criteria) this;
        }

        public Criteria andReceivingUnitEqualTo(String value) {
            addCriterion("receiving_unit =", value, "receivingUnit");
            return (Criteria) this;
        }

        public Criteria andReceivingUnitNotEqualTo(String value) {
            addCriterion("receiving_unit <>", value, "receivingUnit");
            return (Criteria) this;
        }

        public Criteria andReceivingUnitGreaterThan(String value) {
            addCriterion("receiving_unit >", value, "receivingUnit");
            return (Criteria) this;
        }

        public Criteria andReceivingUnitGreaterThanOrEqualTo(String value) {
            addCriterion("receiving_unit >=", value, "receivingUnit");
            return (Criteria) this;
        }

        public Criteria andReceivingUnitLessThan(String value) {
            addCriterion("receiving_unit <", value, "receivingUnit");
            return (Criteria) this;
        }

        public Criteria andReceivingUnitLessThanOrEqualTo(String value) {
            addCriterion("receiving_unit <=", value, "receivingUnit");
            return (Criteria) this;
        }

        public Criteria andReceivingUnitLike(String value) {
            addCriterion("receiving_unit like", value, "receivingUnit");
            return (Criteria) this;
        }

        public Criteria andReceivingUnitNotLike(String value) {
            addCriterion("receiving_unit not like", value, "receivingUnit");
            return (Criteria) this;
        }

        public Criteria andReceivingUnitIn(List<String> values) {
            addCriterion("receiving_unit in", values, "receivingUnit");
            return (Criteria) this;
        }

        public Criteria andReceivingUnitNotIn(List<String> values) {
            addCriterion("receiving_unit not in", values, "receivingUnit");
            return (Criteria) this;
        }

        public Criteria andReceivingUnitBetween(String value1, String value2) {
            addCriterion("receiving_unit between", value1, value2, "receivingUnit");
            return (Criteria) this;
        }

        public Criteria andReceivingUnitNotBetween(String value1, String value2) {
            addCriterion("receiving_unit not between", value1, value2, "receivingUnit");
            return (Criteria) this;
        }

        public Criteria andInventoryUnitIsNull() {
            addCriterion("inventory_unit is null");
            return (Criteria) this;
        }

        public Criteria andInventoryUnitIsNotNull() {
            addCriterion("inventory_unit is not null");
            return (Criteria) this;
        }

        public Criteria andInventoryUnitEqualTo(String value) {
            addCriterion("inventory_unit =", value, "inventoryUnit");
            return (Criteria) this;
        }

        public Criteria andInventoryUnitNotEqualTo(String value) {
            addCriterion("inventory_unit <>", value, "inventoryUnit");
            return (Criteria) this;
        }

        public Criteria andInventoryUnitGreaterThan(String value) {
            addCriterion("inventory_unit >", value, "inventoryUnit");
            return (Criteria) this;
        }

        public Criteria andInventoryUnitGreaterThanOrEqualTo(String value) {
            addCriterion("inventory_unit >=", value, "inventoryUnit");
            return (Criteria) this;
        }

        public Criteria andInventoryUnitLessThan(String value) {
            addCriterion("inventory_unit <", value, "inventoryUnit");
            return (Criteria) this;
        }

        public Criteria andInventoryUnitLessThanOrEqualTo(String value) {
            addCriterion("inventory_unit <=", value, "inventoryUnit");
            return (Criteria) this;
        }

        public Criteria andInventoryUnitLike(String value) {
            addCriterion("inventory_unit like", value, "inventoryUnit");
            return (Criteria) this;
        }

        public Criteria andInventoryUnitNotLike(String value) {
            addCriterion("inventory_unit not like", value, "inventoryUnit");
            return (Criteria) this;
        }

        public Criteria andInventoryUnitIn(List<String> values) {
            addCriterion("inventory_unit in", values, "inventoryUnit");
            return (Criteria) this;
        }

        public Criteria andInventoryUnitNotIn(List<String> values) {
            addCriterion("inventory_unit not in", values, "inventoryUnit");
            return (Criteria) this;
        }

        public Criteria andInventoryUnitBetween(String value1, String value2) {
            addCriterion("inventory_unit between", value1, value2, "inventoryUnit");
            return (Criteria) this;
        }

        public Criteria andInventoryUnitNotBetween(String value1, String value2) {
            addCriterion("inventory_unit not between", value1, value2, "inventoryUnit");
            return (Criteria) this;
        }

        public Criteria andDeliveryUnitIsNull() {
            addCriterion("delivery_unit is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryUnitIsNotNull() {
            addCriterion("delivery_unit is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryUnitEqualTo(String value) {
            addCriterion("delivery_unit =", value, "deliveryUnit");
            return (Criteria) this;
        }

        public Criteria andDeliveryUnitNotEqualTo(String value) {
            addCriterion("delivery_unit <>", value, "deliveryUnit");
            return (Criteria) this;
        }

        public Criteria andDeliveryUnitGreaterThan(String value) {
            addCriterion("delivery_unit >", value, "deliveryUnit");
            return (Criteria) this;
        }

        public Criteria andDeliveryUnitGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_unit >=", value, "deliveryUnit");
            return (Criteria) this;
        }

        public Criteria andDeliveryUnitLessThan(String value) {
            addCriterion("delivery_unit <", value, "deliveryUnit");
            return (Criteria) this;
        }

        public Criteria andDeliveryUnitLessThanOrEqualTo(String value) {
            addCriterion("delivery_unit <=", value, "deliveryUnit");
            return (Criteria) this;
        }

        public Criteria andDeliveryUnitLike(String value) {
            addCriterion("delivery_unit like", value, "deliveryUnit");
            return (Criteria) this;
        }

        public Criteria andDeliveryUnitNotLike(String value) {
            addCriterion("delivery_unit not like", value, "deliveryUnit");
            return (Criteria) this;
        }

        public Criteria andDeliveryUnitIn(List<String> values) {
            addCriterion("delivery_unit in", values, "deliveryUnit");
            return (Criteria) this;
        }

        public Criteria andDeliveryUnitNotIn(List<String> values) {
            addCriterion("delivery_unit not in", values, "deliveryUnit");
            return (Criteria) this;
        }

        public Criteria andDeliveryUnitBetween(String value1, String value2) {
            addCriterion("delivery_unit between", value1, value2, "deliveryUnit");
            return (Criteria) this;
        }

        public Criteria andDeliveryUnitNotBetween(String value1, String value2) {
            addCriterion("delivery_unit not between", value1, value2, "deliveryUnit");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachIsNull() {
            addCriterion("weight_per_each is null");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachIsNotNull() {
            addCriterion("weight_per_each is not null");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachEqualTo(BigDecimal value) {
            addCriterion("weight_per_each =", value, "weightPerEach");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachNotEqualTo(BigDecimal value) {
            addCriterion("weight_per_each <>", value, "weightPerEach");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachGreaterThan(BigDecimal value) {
            addCriterion("weight_per_each >", value, "weightPerEach");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("weight_per_each >=", value, "weightPerEach");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachLessThan(BigDecimal value) {
            addCriterion("weight_per_each <", value, "weightPerEach");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachLessThanOrEqualTo(BigDecimal value) {
            addCriterion("weight_per_each <=", value, "weightPerEach");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachIn(List<BigDecimal> values) {
            addCriterion("weight_per_each in", values, "weightPerEach");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachNotIn(List<BigDecimal> values) {
            addCriterion("weight_per_each not in", values, "weightPerEach");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("weight_per_each between", value1, value2, "weightPerEach");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("weight_per_each not between", value1, value2, "weightPerEach");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachUnitIsNull() {
            addCriterion("weight_per_each_unit is null");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachUnitIsNotNull() {
            addCriterion("weight_per_each_unit is not null");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachUnitEqualTo(String value) {
            addCriterion("weight_per_each_unit =", value, "weightPerEachUnit");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachUnitNotEqualTo(String value) {
            addCriterion("weight_per_each_unit <>", value, "weightPerEachUnit");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachUnitGreaterThan(String value) {
            addCriterion("weight_per_each_unit >", value, "weightPerEachUnit");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachUnitGreaterThanOrEqualTo(String value) {
            addCriterion("weight_per_each_unit >=", value, "weightPerEachUnit");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachUnitLessThan(String value) {
            addCriterion("weight_per_each_unit <", value, "weightPerEachUnit");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachUnitLessThanOrEqualTo(String value) {
            addCriterion("weight_per_each_unit <=", value, "weightPerEachUnit");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachUnitLike(String value) {
            addCriterion("weight_per_each_unit like", value, "weightPerEachUnit");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachUnitNotLike(String value) {
            addCriterion("weight_per_each_unit not like", value, "weightPerEachUnit");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachUnitIn(List<String> values) {
            addCriterion("weight_per_each_unit in", values, "weightPerEachUnit");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachUnitNotIn(List<String> values) {
            addCriterion("weight_per_each_unit not in", values, "weightPerEachUnit");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachUnitBetween(String value1, String value2) {
            addCriterion("weight_per_each_unit between", value1, value2, "weightPerEachUnit");
            return (Criteria) this;
        }

        public Criteria andWeightPerEachUnitNotBetween(String value1, String value2) {
            addCriterion("weight_per_each_unit not between", value1, value2, "weightPerEachUnit");
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