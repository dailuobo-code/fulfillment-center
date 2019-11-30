package com.mallcai.fulfillment.pe.infrastructure.valueobject;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDOCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderDOCriteria() {
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

        public Criteria andTransOrderIdIsNull() {
            addCriterion("trans_order_id is null");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdIsNotNull() {
            addCriterion("trans_order_id is not null");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdEqualTo(String value) {
            addCriterion("trans_order_id =", value, "transOrderId");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdNotEqualTo(String value) {
            addCriterion("trans_order_id <>", value, "transOrderId");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdGreaterThan(String value) {
            addCriterion("trans_order_id >", value, "transOrderId");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("trans_order_id >=", value, "transOrderId");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdLessThan(String value) {
            addCriterion("trans_order_id <", value, "transOrderId");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdLessThanOrEqualTo(String value) {
            addCriterion("trans_order_id <=", value, "transOrderId");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdLike(String value) {
            addCriterion("trans_order_id like", value, "transOrderId");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdNotLike(String value) {
            addCriterion("trans_order_id not like", value, "transOrderId");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdIn(List<String> values) {
            addCriterion("trans_order_id in", values, "transOrderId");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdNotIn(List<String> values) {
            addCriterion("trans_order_id not in", values, "transOrderId");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdBetween(String value1, String value2) {
            addCriterion("trans_order_id between", value1, value2, "transOrderId");
            return (Criteria) this;
        }

        public Criteria andTransOrderIdNotBetween(String value1, String value2) {
            addCriterion("trans_order_id not between", value1, value2, "transOrderId");
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

        public Criteria andOrderTypeIsNull() {
            addCriterion("order_type is null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIsNotNull() {
            addCriterion("order_type is not null");
            return (Criteria) this;
        }

        public Criteria andOrderTypeEqualTo(Integer value) {
            addCriterion("order_type =", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotEqualTo(Integer value) {
            addCriterion("order_type <>", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThan(Integer value) {
            addCriterion("order_type >", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("order_type >=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThan(Integer value) {
            addCriterion("order_type <", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThanOrEqualTo(Integer value) {
            addCriterion("order_type <=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIn(List<Integer> values) {
            addCriterion("order_type in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotIn(List<Integer> values) {
            addCriterion("order_type not in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeBetween(Integer value1, Integer value2) {
            addCriterion("order_type between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("order_type not between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andCityProductIdIsNull() {
            addCriterion("city_product_id is null");
            return (Criteria) this;
        }

        public Criteria andCityProductIdIsNotNull() {
            addCriterion("city_product_id is not null");
            return (Criteria) this;
        }

        public Criteria andCityProductIdEqualTo(Integer value) {
            addCriterion("city_product_id =", value, "cityProductId");
            return (Criteria) this;
        }

        public Criteria andCityProductIdNotEqualTo(Integer value) {
            addCriterion("city_product_id <>", value, "cityProductId");
            return (Criteria) this;
        }

        public Criteria andCityProductIdGreaterThan(Integer value) {
            addCriterion("city_product_id >", value, "cityProductId");
            return (Criteria) this;
        }

        public Criteria andCityProductIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("city_product_id >=", value, "cityProductId");
            return (Criteria) this;
        }

        public Criteria andCityProductIdLessThan(Integer value) {
            addCriterion("city_product_id <", value, "cityProductId");
            return (Criteria) this;
        }

        public Criteria andCityProductIdLessThanOrEqualTo(Integer value) {
            addCriterion("city_product_id <=", value, "cityProductId");
            return (Criteria) this;
        }

        public Criteria andCityProductIdIn(List<Integer> values) {
            addCriterion("city_product_id in", values, "cityProductId");
            return (Criteria) this;
        }

        public Criteria andCityProductIdNotIn(List<Integer> values) {
            addCriterion("city_product_id not in", values, "cityProductId");
            return (Criteria) this;
        }

        public Criteria andCityProductIdBetween(Integer value1, Integer value2) {
            addCriterion("city_product_id between", value1, value2, "cityProductId");
            return (Criteria) this;
        }

        public Criteria andCityProductIdNotBetween(Integer value1, Integer value2) {
            addCriterion("city_product_id not between", value1, value2, "cityProductId");
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
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

        public Criteria andProductNoIsNull() {
            addCriterion("product_no is null");
            return (Criteria) this;
        }

        public Criteria andProductNoIsNotNull() {
            addCriterion("product_no is not null");
            return (Criteria) this;
        }

        public Criteria andProductNoEqualTo(String value) {
            addCriterion("product_no =", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoNotEqualTo(String value) {
            addCriterion("product_no <>", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoGreaterThan(String value) {
            addCriterion("product_no >", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoGreaterThanOrEqualTo(String value) {
            addCriterion("product_no >=", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoLessThan(String value) {
            addCriterion("product_no <", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoLessThanOrEqualTo(String value) {
            addCriterion("product_no <=", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoLike(String value) {
            addCriterion("product_no like", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoNotLike(String value) {
            addCriterion("product_no not like", value, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoIn(List<String> values) {
            addCriterion("product_no in", values, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoNotIn(List<String> values) {
            addCriterion("product_no not in", values, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoBetween(String value1, String value2) {
            addCriterion("product_no between", value1, value2, "productNo");
            return (Criteria) this;
        }

        public Criteria andProductNoNotBetween(String value1, String value2) {
            addCriterion("product_no not between", value1, value2, "productNo");
            return (Criteria) this;
        }

        public Criteria andOrderSourceIsNull() {
            addCriterion("order_source is null");
            return (Criteria) this;
        }

        public Criteria andOrderSourceIsNotNull() {
            addCriterion("order_source is not null");
            return (Criteria) this;
        }

        public Criteria andOrderSourceEqualTo(Byte value) {
            addCriterion("order_source =", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceNotEqualTo(Byte value) {
            addCriterion("order_source <>", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceGreaterThan(Byte value) {
            addCriterion("order_source >", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceGreaterThanOrEqualTo(Byte value) {
            addCriterion("order_source >=", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceLessThan(Byte value) {
            addCriterion("order_source <", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceLessThanOrEqualTo(Byte value) {
            addCriterion("order_source <=", value, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceIn(List<Byte> values) {
            addCriterion("order_source in", values, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceNotIn(List<Byte> values) {
            addCriterion("order_source not in", values, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceBetween(Byte value1, Byte value2) {
            addCriterion("order_source between", value1, value2, "orderSource");
            return (Criteria) this;
        }

        public Criteria andOrderSourceNotBetween(Byte value1, Byte value2) {
            addCriterion("order_source not between", value1, value2, "orderSource");
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

        public Criteria andCommodityInfoExtIsNull() {
            addCriterion("commodity_info_ext is null");
            return (Criteria) this;
        }

        public Criteria andCommodityInfoExtIsNotNull() {
            addCriterion("commodity_info_ext is not null");
            return (Criteria) this;
        }

        public Criteria andCommodityInfoExtEqualTo(String value) {
            addCriterion("commodity_info_ext =", value, "commodityInfoExt");
            return (Criteria) this;
        }

        public Criteria andCommodityInfoExtNotEqualTo(String value) {
            addCriterion("commodity_info_ext <>", value, "commodityInfoExt");
            return (Criteria) this;
        }

        public Criteria andCommodityInfoExtGreaterThan(String value) {
            addCriterion("commodity_info_ext >", value, "commodityInfoExt");
            return (Criteria) this;
        }

        public Criteria andCommodityInfoExtGreaterThanOrEqualTo(String value) {
            addCriterion("commodity_info_ext >=", value, "commodityInfoExt");
            return (Criteria) this;
        }

        public Criteria andCommodityInfoExtLessThan(String value) {
            addCriterion("commodity_info_ext <", value, "commodityInfoExt");
            return (Criteria) this;
        }

        public Criteria andCommodityInfoExtLessThanOrEqualTo(String value) {
            addCriterion("commodity_info_ext <=", value, "commodityInfoExt");
            return (Criteria) this;
        }

        public Criteria andCommodityInfoExtLike(String value) {
            addCriterion("commodity_info_ext like", value, "commodityInfoExt");
            return (Criteria) this;
        }

        public Criteria andCommodityInfoExtNotLike(String value) {
            addCriterion("commodity_info_ext not like", value, "commodityInfoExt");
            return (Criteria) this;
        }

        public Criteria andCommodityInfoExtIn(List<String> values) {
            addCriterion("commodity_info_ext in", values, "commodityInfoExt");
            return (Criteria) this;
        }

        public Criteria andCommodityInfoExtNotIn(List<String> values) {
            addCriterion("commodity_info_ext not in", values, "commodityInfoExt");
            return (Criteria) this;
        }

        public Criteria andCommodityInfoExtBetween(String value1, String value2) {
            addCriterion("commodity_info_ext between", value1, value2, "commodityInfoExt");
            return (Criteria) this;
        }

        public Criteria andCommodityInfoExtNotBetween(String value1, String value2) {
            addCriterion("commodity_info_ext not between", value1, value2, "commodityInfoExt");
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

        public Criteria andPickupTimeIsNull() {
            addCriterion("pickup_time is null");
            return (Criteria) this;
        }

        public Criteria andPickupTimeIsNotNull() {
            addCriterion("pickup_time is not null");
            return (Criteria) this;
        }

        public Criteria andPickupTimeEqualTo(Date value) {
            addCriterion("pickup_time =", value, "pickupTime");
            return (Criteria) this;
        }

        public Criteria andPickupTimeNotEqualTo(Date value) {
            addCriterion("pickup_time <>", value, "pickupTime");
            return (Criteria) this;
        }

        public Criteria andPickupTimeGreaterThan(Date value) {
            addCriterion("pickup_time >", value, "pickupTime");
            return (Criteria) this;
        }

        public Criteria andPickupTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pickup_time >=", value, "pickupTime");
            return (Criteria) this;
        }

        public Criteria andPickupTimeLessThan(Date value) {
            addCriterion("pickup_time <", value, "pickupTime");
            return (Criteria) this;
        }

        public Criteria andPickupTimeLessThanOrEqualTo(Date value) {
            addCriterion("pickup_time <=", value, "pickupTime");
            return (Criteria) this;
        }

        public Criteria andPickupTimeIn(List<Date> values) {
            addCriterion("pickup_time in", values, "pickupTime");
            return (Criteria) this;
        }

        public Criteria andPickupTimeNotIn(List<Date> values) {
            addCriterion("pickup_time not in", values, "pickupTime");
            return (Criteria) this;
        }

        public Criteria andPickupTimeBetween(Date value1, Date value2) {
            addCriterion("pickup_time between", value1, value2, "pickupTime");
            return (Criteria) this;
        }

        public Criteria andPickupTimeNotBetween(Date value1, Date value2) {
            addCriterion("pickup_time not between", value1, value2, "pickupTime");
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