package com.mallcai.fulfillment.infrastructure.object.pe;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProduceOrderCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ProduceOrderCriteria() {
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

        public Criteria andProduceOrderNoIsNull() {
            addCriterion("produce_order_no is null");
            return (Criteria) this;
        }

        public Criteria andProduceOrderNoIsNotNull() {
            addCriterion("produce_order_no is not null");
            return (Criteria) this;
        }

        public Criteria andProduceOrderNoEqualTo(String value) {
            addCriterion("produce_order_no =", value, "produceOrderNo");
            return (Criteria) this;
        }

        public Criteria andProduceOrderNoNotEqualTo(String value) {
            addCriterion("produce_order_no <>", value, "produceOrderNo");
            return (Criteria) this;
        }

        public Criteria andProduceOrderNoGreaterThan(String value) {
            addCriterion("produce_order_no >", value, "produceOrderNo");
            return (Criteria) this;
        }

        public Criteria andProduceOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("produce_order_no >=", value, "produceOrderNo");
            return (Criteria) this;
        }

        public Criteria andProduceOrderNoLessThan(String value) {
            addCriterion("produce_order_no <", value, "produceOrderNo");
            return (Criteria) this;
        }

        public Criteria andProduceOrderNoLessThanOrEqualTo(String value) {
            addCriterion("produce_order_no <=", value, "produceOrderNo");
            return (Criteria) this;
        }

        public Criteria andProduceOrderNoLike(String value) {
            addCriterion("produce_order_no like", value, "produceOrderNo");
            return (Criteria) this;
        }

        public Criteria andProduceOrderNoNotLike(String value) {
            addCriterion("produce_order_no not like", value, "produceOrderNo");
            return (Criteria) this;
        }

        public Criteria andProduceOrderNoIn(List<String> values) {
            addCriterion("produce_order_no in", values, "produceOrderNo");
            return (Criteria) this;
        }

        public Criteria andProduceOrderNoNotIn(List<String> values) {
            addCriterion("produce_order_no not in", values, "produceOrderNo");
            return (Criteria) this;
        }

        public Criteria andProduceOrderNoBetween(String value1, String value2) {
            addCriterion("produce_order_no between", value1, value2, "produceOrderNo");
            return (Criteria) this;
        }

        public Criteria andProduceOrderNoNotBetween(String value1, String value2) {
            addCriterion("produce_order_no not between", value1, value2, "produceOrderNo");
            return (Criteria) this;
        }

        public Criteria andExpectPushTimeIsNull() {
            addCriterion("expect_push_time is null");
            return (Criteria) this;
        }

        public Criteria andExpectPushTimeIsNotNull() {
            addCriterion("expect_push_time is not null");
            return (Criteria) this;
        }

        public Criteria andExpectPushTimeEqualTo(Date value) {
            addCriterion("expect_push_time =", value, "expectPushTime");
            return (Criteria) this;
        }

        public Criteria andExpectPushTimeNotEqualTo(Date value) {
            addCriterion("expect_push_time <>", value, "expectPushTime");
            return (Criteria) this;
        }

        public Criteria andExpectPushTimeGreaterThan(Date value) {
            addCriterion("expect_push_time >", value, "expectPushTime");
            return (Criteria) this;
        }

        public Criteria andExpectPushTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("expect_push_time >=", value, "expectPushTime");
            return (Criteria) this;
        }

        public Criteria andExpectPushTimeLessThan(Date value) {
            addCriterion("expect_push_time <", value, "expectPushTime");
            return (Criteria) this;
        }

        public Criteria andExpectPushTimeLessThanOrEqualTo(Date value) {
            addCriterion("expect_push_time <=", value, "expectPushTime");
            return (Criteria) this;
        }

        public Criteria andExpectPushTimeIn(List<Date> values) {
            addCriterion("expect_push_time in", values, "expectPushTime");
            return (Criteria) this;
        }

        public Criteria andExpectPushTimeNotIn(List<Date> values) {
            addCriterion("expect_push_time not in", values, "expectPushTime");
            return (Criteria) this;
        }

        public Criteria andExpectPushTimeBetween(Date value1, Date value2) {
            addCriterion("expect_push_time between", value1, value2, "expectPushTime");
            return (Criteria) this;
        }

        public Criteria andExpectPushTimeNotBetween(Date value1, Date value2) {
            addCriterion("expect_push_time not between", value1, value2, "expectPushTime");
            return (Criteria) this;
        }

        public Criteria andAggregateTypeIsNull() {
            addCriterion("aggregate_type is null");
            return (Criteria) this;
        }

        public Criteria andAggregateTypeIsNotNull() {
            addCriterion("aggregate_type is not null");
            return (Criteria) this;
        }

        public Criteria andAggregateTypeEqualTo(Byte value) {
            addCriterion("aggregate_type =", value, "aggregateType");
            return (Criteria) this;
        }

        public Criteria andAggregateTypeNotEqualTo(Byte value) {
            addCriterion("aggregate_type <>", value, "aggregateType");
            return (Criteria) this;
        }

        public Criteria andAggregateTypeGreaterThan(Byte value) {
            addCriterion("aggregate_type >", value, "aggregateType");
            return (Criteria) this;
        }

        public Criteria andAggregateTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("aggregate_type >=", value, "aggregateType");
            return (Criteria) this;
        }

        public Criteria andAggregateTypeLessThan(Byte value) {
            addCriterion("aggregate_type <", value, "aggregateType");
            return (Criteria) this;
        }

        public Criteria andAggregateTypeLessThanOrEqualTo(Byte value) {
            addCriterion("aggregate_type <=", value, "aggregateType");
            return (Criteria) this;
        }

        public Criteria andAggregateTypeIn(List<Byte> values) {
            addCriterion("aggregate_type in", values, "aggregateType");
            return (Criteria) this;
        }

        public Criteria andAggregateTypeNotIn(List<Byte> values) {
            addCriterion("aggregate_type not in", values, "aggregateType");
            return (Criteria) this;
        }

        public Criteria andAggregateTypeBetween(Byte value1, Byte value2) {
            addCriterion("aggregate_type between", value1, value2, "aggregateType");
            return (Criteria) this;
        }

        public Criteria andAggregateTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("aggregate_type not between", value1, value2, "aggregateType");
            return (Criteria) this;
        }

        public Criteria andAggregateValueIsNull() {
            addCriterion("aggregate_value is null");
            return (Criteria) this;
        }

        public Criteria andAggregateValueIsNotNull() {
            addCriterion("aggregate_value is not null");
            return (Criteria) this;
        }

        public Criteria andAggregateValueEqualTo(String value) {
            addCriterion("aggregate_value =", value, "aggregateValue");
            return (Criteria) this;
        }

        public Criteria andAggregateValueNotEqualTo(String value) {
            addCriterion("aggregate_value <>", value, "aggregateValue");
            return (Criteria) this;
        }

        public Criteria andAggregateValueGreaterThan(String value) {
            addCriterion("aggregate_value >", value, "aggregateValue");
            return (Criteria) this;
        }

        public Criteria andAggregateValueGreaterThanOrEqualTo(String value) {
            addCriterion("aggregate_value >=", value, "aggregateValue");
            return (Criteria) this;
        }

        public Criteria andAggregateValueLessThan(String value) {
            addCriterion("aggregate_value <", value, "aggregateValue");
            return (Criteria) this;
        }

        public Criteria andAggregateValueLessThanOrEqualTo(String value) {
            addCriterion("aggregate_value <=", value, "aggregateValue");
            return (Criteria) this;
        }

        public Criteria andAggregateValueLike(String value) {
            addCriterion("aggregate_value like", value, "aggregateValue");
            return (Criteria) this;
        }

        public Criteria andAggregateValueNotLike(String value) {
            addCriterion("aggregate_value not like", value, "aggregateValue");
            return (Criteria) this;
        }

        public Criteria andAggregateValueIn(List<String> values) {
            addCriterion("aggregate_value in", values, "aggregateValue");
            return (Criteria) this;
        }

        public Criteria andAggregateValueNotIn(List<String> values) {
            addCriterion("aggregate_value not in", values, "aggregateValue");
            return (Criteria) this;
        }

        public Criteria andAggregateValueBetween(String value1, String value2) {
            addCriterion("aggregate_value between", value1, value2, "aggregateValue");
            return (Criteria) this;
        }

        public Criteria andAggregateValueNotBetween(String value1, String value2) {
            addCriterion("aggregate_value not between", value1, value2, "aggregateValue");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIsNull() {
            addCriterion("group_type is null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIsNotNull() {
            addCriterion("group_type is not null");
            return (Criteria) this;
        }

        public Criteria andGroupTypeEqualTo(Byte value) {
            addCriterion("group_type =", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNotEqualTo(Byte value) {
            addCriterion("group_type <>", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeGreaterThan(Byte value) {
            addCriterion("group_type >", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("group_type >=", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeLessThan(Byte value) {
            addCriterion("group_type <", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeLessThanOrEqualTo(Byte value) {
            addCriterion("group_type <=", value, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeIn(List<Byte> values) {
            addCriterion("group_type in", values, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNotIn(List<Byte> values) {
            addCriterion("group_type not in", values, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeBetween(Byte value1, Byte value2) {
            addCriterion("group_type between", value1, value2, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("group_type not between", value1, value2, "groupType");
            return (Criteria) this;
        }

        public Criteria andGroupValueIsNull() {
            addCriterion("group_value is null");
            return (Criteria) this;
        }

        public Criteria andGroupValueIsNotNull() {
            addCriterion("group_value is not null");
            return (Criteria) this;
        }

        public Criteria andGroupValueEqualTo(String value) {
            addCriterion("group_value =", value, "groupValue");
            return (Criteria) this;
        }

        public Criteria andGroupValueNotEqualTo(String value) {
            addCriterion("group_value <>", value, "groupValue");
            return (Criteria) this;
        }

        public Criteria andGroupValueGreaterThan(String value) {
            addCriterion("group_value >", value, "groupValue");
            return (Criteria) this;
        }

        public Criteria andGroupValueGreaterThanOrEqualTo(String value) {
            addCriterion("group_value >=", value, "groupValue");
            return (Criteria) this;
        }

        public Criteria andGroupValueLessThan(String value) {
            addCriterion("group_value <", value, "groupValue");
            return (Criteria) this;
        }

        public Criteria andGroupValueLessThanOrEqualTo(String value) {
            addCriterion("group_value <=", value, "groupValue");
            return (Criteria) this;
        }

        public Criteria andGroupValueLike(String value) {
            addCriterion("group_value like", value, "groupValue");
            return (Criteria) this;
        }

        public Criteria andGroupValueNotLike(String value) {
            addCriterion("group_value not like", value, "groupValue");
            return (Criteria) this;
        }

        public Criteria andGroupValueIn(List<String> values) {
            addCriterion("group_value in", values, "groupValue");
            return (Criteria) this;
        }

        public Criteria andGroupValueNotIn(List<String> values) {
            addCriterion("group_value not in", values, "groupValue");
            return (Criteria) this;
        }

        public Criteria andGroupValueBetween(String value1, String value2) {
            addCriterion("group_value between", value1, value2, "groupValue");
            return (Criteria) this;
        }

        public Criteria andGroupValueNotBetween(String value1, String value2) {
            addCriterion("group_value not between", value1, value2, "groupValue");
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

        public Criteria andWarehouseIdIsNull() {
            addCriterion("warehouse_id is null");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdIsNotNull() {
            addCriterion("warehouse_id is not null");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdEqualTo(Integer value) {
            addCriterion("warehouse_id =", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdNotEqualTo(Integer value) {
            addCriterion("warehouse_id <>", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdGreaterThan(Integer value) {
            addCriterion("warehouse_id >", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("warehouse_id >=", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdLessThan(Integer value) {
            addCriterion("warehouse_id <", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdLessThanOrEqualTo(Integer value) {
            addCriterion("warehouse_id <=", value, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdIn(List<Integer> values) {
            addCriterion("warehouse_id in", values, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdNotIn(List<Integer> values) {
            addCriterion("warehouse_id not in", values, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdBetween(Integer value1, Integer value2) {
            addCriterion("warehouse_id between", value1, value2, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andWarehouseIdNotBetween(Integer value1, Integer value2) {
            addCriterion("warehouse_id not between", value1, value2, "warehouseId");
            return (Criteria) this;
        }

        public Criteria andOrderCountIsNull() {
            addCriterion("order_count is null");
            return (Criteria) this;
        }

        public Criteria andOrderCountIsNotNull() {
            addCriterion("order_count is not null");
            return (Criteria) this;
        }

        public Criteria andOrderCountEqualTo(Integer value) {
            addCriterion("order_count =", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountNotEqualTo(Integer value) {
            addCriterion("order_count <>", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountGreaterThan(Integer value) {
            addCriterion("order_count >", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_count >=", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountLessThan(Integer value) {
            addCriterion("order_count <", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountLessThanOrEqualTo(Integer value) {
            addCriterion("order_count <=", value, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountIn(List<Integer> values) {
            addCriterion("order_count in", values, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountNotIn(List<Integer> values) {
            addCriterion("order_count not in", values, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountBetween(Integer value1, Integer value2) {
            addCriterion("order_count between", value1, value2, "orderCount");
            return (Criteria) this;
        }

        public Criteria andOrderCountNotBetween(Integer value1, Integer value2) {
            addCriterion("order_count not between", value1, value2, "orderCount");
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

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andSucTimeIsNull() {
            addCriterion("suc_time is null");
            return (Criteria) this;
        }

        public Criteria andSucTimeIsNotNull() {
            addCriterion("suc_time is not null");
            return (Criteria) this;
        }

        public Criteria andSucTimeEqualTo(Date value) {
            addCriterion("suc_time =", value, "sucTime");
            return (Criteria) this;
        }

        public Criteria andSucTimeNotEqualTo(Date value) {
            addCriterion("suc_time <>", value, "sucTime");
            return (Criteria) this;
        }

        public Criteria andSucTimeGreaterThan(Date value) {
            addCriterion("suc_time >", value, "sucTime");
            return (Criteria) this;
        }

        public Criteria andSucTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("suc_time >=", value, "sucTime");
            return (Criteria) this;
        }

        public Criteria andSucTimeLessThan(Date value) {
            addCriterion("suc_time <", value, "sucTime");
            return (Criteria) this;
        }

        public Criteria andSucTimeLessThanOrEqualTo(Date value) {
            addCriterion("suc_time <=", value, "sucTime");
            return (Criteria) this;
        }

        public Criteria andSucTimeIn(List<Date> values) {
            addCriterion("suc_time in", values, "sucTime");
            return (Criteria) this;
        }

        public Criteria andSucTimeNotIn(List<Date> values) {
            addCriterion("suc_time not in", values, "sucTime");
            return (Criteria) this;
        }

        public Criteria andSucTimeBetween(Date value1, Date value2) {
            addCriterion("suc_time between", value1, value2, "sucTime");
            return (Criteria) this;
        }

        public Criteria andSucTimeNotBetween(Date value1, Date value2) {
            addCriterion("suc_time not between", value1, value2, "sucTime");
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