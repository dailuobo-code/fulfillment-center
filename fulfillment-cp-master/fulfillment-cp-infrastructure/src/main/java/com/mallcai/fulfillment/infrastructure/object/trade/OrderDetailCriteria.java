package com.mallcai.fulfillment.infrastructure.object.trade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OrderDetailCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderDetailCriteria() {
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNull() {
            addCriterion("order_id is null");
            return (Criteria) this;
        }

        public Criteria andOrderIdIsNotNull() {
            addCriterion("order_id is not null");
            return (Criteria) this;
        }

        public Criteria andOrderIdEqualTo(String value) {
            addCriterion("order_id =", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotEqualTo(String value) {
            addCriterion("order_id <>", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThan(String value) {
            addCriterion("order_id >", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdGreaterThanOrEqualTo(String value) {
            addCriterion("order_id >=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThan(String value) {
            addCriterion("order_id <", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLessThanOrEqualTo(String value) {
            addCriterion("order_id <=", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdLike(String value) {
            addCriterion("order_id like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotLike(String value) {
            addCriterion("order_id not like", value, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdIn(List<String> values) {
            addCriterion("order_id in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotIn(List<String> values) {
            addCriterion("order_id not in", values, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdBetween(String value1, String value2) {
            addCriterion("order_id between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andOrderIdNotBetween(String value1, String value2) {
            addCriterion("order_id not between", value1, value2, "orderId");
            return (Criteria) this;
        }

        public Criteria andSpecIdIsNull() {
            addCriterion("spec_id is null");
            return (Criteria) this;
        }

        public Criteria andSpecIdIsNotNull() {
            addCriterion("spec_id is not null");
            return (Criteria) this;
        }

        public Criteria andSpecIdEqualTo(Integer value) {
            addCriterion("spec_id =", value, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdNotEqualTo(Integer value) {
            addCriterion("spec_id <>", value, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdGreaterThan(Integer value) {
            addCriterion("spec_id >", value, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("spec_id >=", value, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdLessThan(Integer value) {
            addCriterion("spec_id <", value, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdLessThanOrEqualTo(Integer value) {
            addCriterion("spec_id <=", value, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdIn(List<Integer> values) {
            addCriterion("spec_id in", values, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdNotIn(List<Integer> values) {
            addCriterion("spec_id not in", values, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdBetween(Integer value1, Integer value2) {
            addCriterion("spec_id between", value1, value2, "specId");
            return (Criteria) this;
        }

        public Criteria andSpecIdNotBetween(Integer value1, Integer value2) {
            addCriterion("spec_id not between", value1, value2, "specId");
            return (Criteria) this;
        }

        public Criteria andProductNumIsNull() {
            addCriterion("product_num is null");
            return (Criteria) this;
        }

        public Criteria andProductNumIsNotNull() {
            addCriterion("product_num is not null");
            return (Criteria) this;
        }

        public Criteria andProductNumEqualTo(Integer value) {
            addCriterion("product_num =", value, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumNotEqualTo(Integer value) {
            addCriterion("product_num <>", value, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumGreaterThan(Integer value) {
            addCriterion("product_num >", value, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("product_num >=", value, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumLessThan(Integer value) {
            addCriterion("product_num <", value, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumLessThanOrEqualTo(Integer value) {
            addCriterion("product_num <=", value, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumIn(List<Integer> values) {
            addCriterion("product_num in", values, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumNotIn(List<Integer> values) {
            addCriterion("product_num not in", values, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumBetween(Integer value1, Integer value2) {
            addCriterion("product_num between", value1, value2, "productNum");
            return (Criteria) this;
        }

        public Criteria andProductNumNotBetween(Integer value1, Integer value2) {
            addCriterion("product_num not between", value1, value2, "productNum");
            return (Criteria) this;
        }

        public Criteria andPriceIsNull() {
            addCriterion("price is null");
            return (Criteria) this;
        }

        public Criteria andPriceIsNotNull() {
            addCriterion("price is not null");
            return (Criteria) this;
        }

        public Criteria andPriceEqualTo(BigDecimal value) {
            addCriterion("price =", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotEqualTo(BigDecimal value) {
            addCriterion("price <>", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThan(BigDecimal value) {
            addCriterion("price >", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("price >=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThan(BigDecimal value) {
            addCriterion("price <", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("price <=", value, "price");
            return (Criteria) this;
        }

        public Criteria andPriceIn(List<BigDecimal> values) {
            addCriterion("price in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotIn(List<BigDecimal> values) {
            addCriterion("price not in", values, "price");
            return (Criteria) this;
        }

        public Criteria andPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price between", value1, value2, "price");
            return (Criteria) this;
        }

        public Criteria andPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("price not between", value1, value2, "price");
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

        public Criteria andIsPayIsNull() {
            addCriterion("is_pay is null");
            return (Criteria) this;
        }

        public Criteria andIsPayIsNotNull() {
            addCriterion("is_pay is not null");
            return (Criteria) this;
        }

        public Criteria andIsPayEqualTo(String value) {
            addCriterion("is_pay =", value, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayNotEqualTo(String value) {
            addCriterion("is_pay <>", value, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayGreaterThan(String value) {
            addCriterion("is_pay >", value, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayGreaterThanOrEqualTo(String value) {
            addCriterion("is_pay >=", value, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayLessThan(String value) {
            addCriterion("is_pay <", value, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayLessThanOrEqualTo(String value) {
            addCriterion("is_pay <=", value, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayLike(String value) {
            addCriterion("is_pay like", value, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayNotLike(String value) {
            addCriterion("is_pay not like", value, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayIn(List<String> values) {
            addCriterion("is_pay in", values, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayNotIn(List<String> values) {
            addCriterion("is_pay not in", values, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayBetween(String value1, String value2) {
            addCriterion("is_pay between", value1, value2, "isPay");
            return (Criteria) this;
        }

        public Criteria andIsPayNotBetween(String value1, String value2) {
            addCriterion("is_pay not between", value1, value2, "isPay");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNull() {
            addCriterion("pay_time is null");
            return (Criteria) this;
        }

        public Criteria andPayTimeIsNotNull() {
            addCriterion("pay_time is not null");
            return (Criteria) this;
        }

        public Criteria andPayTimeEqualTo(Date value) {
            addCriterion("pay_time =", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotEqualTo(Date value) {
            addCriterion("pay_time <>", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThan(Date value) {
            addCriterion("pay_time >", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_time >=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThan(Date value) {
            addCriterion("pay_time <", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeLessThanOrEqualTo(Date value) {
            addCriterion("pay_time <=", value, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeIn(List<Date> values) {
            addCriterion("pay_time in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotIn(List<Date> values) {
            addCriterion("pay_time not in", values, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeBetween(Date value1, Date value2) {
            addCriterion("pay_time between", value1, value2, "payTime");
            return (Criteria) this;
        }

        public Criteria andPayTimeNotBetween(Date value1, Date value2) {
            addCriterion("pay_time not between", value1, value2, "payTime");
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

        public Criteria andProdShowNameIsNull() {
            addCriterion("prod_show_name is null");
            return (Criteria) this;
        }

        public Criteria andProdShowNameIsNotNull() {
            addCriterion("prod_show_name is not null");
            return (Criteria) this;
        }

        public Criteria andProdShowNameEqualTo(String value) {
            addCriterion("prod_show_name =", value, "prodShowName");
            return (Criteria) this;
        }

        public Criteria andProdShowNameNotEqualTo(String value) {
            addCriterion("prod_show_name <>", value, "prodShowName");
            return (Criteria) this;
        }

        public Criteria andProdShowNameGreaterThan(String value) {
            addCriterion("prod_show_name >", value, "prodShowName");
            return (Criteria) this;
        }

        public Criteria andProdShowNameGreaterThanOrEqualTo(String value) {
            addCriterion("prod_show_name >=", value, "prodShowName");
            return (Criteria) this;
        }

        public Criteria andProdShowNameLessThan(String value) {
            addCriterion("prod_show_name <", value, "prodShowName");
            return (Criteria) this;
        }

        public Criteria andProdShowNameLessThanOrEqualTo(String value) {
            addCriterion("prod_show_name <=", value, "prodShowName");
            return (Criteria) this;
        }

        public Criteria andProdShowNameLike(String value) {
            addCriterion("prod_show_name like", value, "prodShowName");
            return (Criteria) this;
        }

        public Criteria andProdShowNameNotLike(String value) {
            addCriterion("prod_show_name not like", value, "prodShowName");
            return (Criteria) this;
        }

        public Criteria andProdShowNameIn(List<String> values) {
            addCriterion("prod_show_name in", values, "prodShowName");
            return (Criteria) this;
        }

        public Criteria andProdShowNameNotIn(List<String> values) {
            addCriterion("prod_show_name not in", values, "prodShowName");
            return (Criteria) this;
        }

        public Criteria andProdShowNameBetween(String value1, String value2) {
            addCriterion("prod_show_name between", value1, value2, "prodShowName");
            return (Criteria) this;
        }

        public Criteria andProdShowNameNotBetween(String value1, String value2) {
            addCriterion("prod_show_name not between", value1, value2, "prodShowName");
            return (Criteria) this;
        }

        public Criteria andIsLockIsNull() {
            addCriterion("is_lock is null");
            return (Criteria) this;
        }

        public Criteria andIsLockIsNotNull() {
            addCriterion("is_lock is not null");
            return (Criteria) this;
        }

        public Criteria andIsLockEqualTo(String value) {
            addCriterion("is_lock =", value, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockNotEqualTo(String value) {
            addCriterion("is_lock <>", value, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockGreaterThan(String value) {
            addCriterion("is_lock >", value, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockGreaterThanOrEqualTo(String value) {
            addCriterion("is_lock >=", value, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockLessThan(String value) {
            addCriterion("is_lock <", value, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockLessThanOrEqualTo(String value) {
            addCriterion("is_lock <=", value, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockLike(String value) {
            addCriterion("is_lock like", value, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockNotLike(String value) {
            addCriterion("is_lock not like", value, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockIn(List<String> values) {
            addCriterion("is_lock in", values, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockNotIn(List<String> values) {
            addCriterion("is_lock not in", values, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockBetween(String value1, String value2) {
            addCriterion("is_lock between", value1, value2, "isLock");
            return (Criteria) this;
        }

        public Criteria andIsLockNotBetween(String value1, String value2) {
            addCriterion("is_lock not between", value1, value2, "isLock");
            return (Criteria) this;
        }

        public Criteria andProdIconIsNull() {
            addCriterion("prod_icon is null");
            return (Criteria) this;
        }

        public Criteria andProdIconIsNotNull() {
            addCriterion("prod_icon is not null");
            return (Criteria) this;
        }

        public Criteria andProdIconEqualTo(String value) {
            addCriterion("prod_icon =", value, "prodIcon");
            return (Criteria) this;
        }

        public Criteria andProdIconNotEqualTo(String value) {
            addCriterion("prod_icon <>", value, "prodIcon");
            return (Criteria) this;
        }

        public Criteria andProdIconGreaterThan(String value) {
            addCriterion("prod_icon >", value, "prodIcon");
            return (Criteria) this;
        }

        public Criteria andProdIconGreaterThanOrEqualTo(String value) {
            addCriterion("prod_icon >=", value, "prodIcon");
            return (Criteria) this;
        }

        public Criteria andProdIconLessThan(String value) {
            addCriterion("prod_icon <", value, "prodIcon");
            return (Criteria) this;
        }

        public Criteria andProdIconLessThanOrEqualTo(String value) {
            addCriterion("prod_icon <=", value, "prodIcon");
            return (Criteria) this;
        }

        public Criteria andProdIconLike(String value) {
            addCriterion("prod_icon like", value, "prodIcon");
            return (Criteria) this;
        }

        public Criteria andProdIconNotLike(String value) {
            addCriterion("prod_icon not like", value, "prodIcon");
            return (Criteria) this;
        }

        public Criteria andProdIconIn(List<String> values) {
            addCriterion("prod_icon in", values, "prodIcon");
            return (Criteria) this;
        }

        public Criteria andProdIconNotIn(List<String> values) {
            addCriterion("prod_icon not in", values, "prodIcon");
            return (Criteria) this;
        }

        public Criteria andProdIconBetween(String value1, String value2) {
            addCriterion("prod_icon between", value1, value2, "prodIcon");
            return (Criteria) this;
        }

        public Criteria andProdIconNotBetween(String value1, String value2) {
            addCriterion("prod_icon not between", value1, value2, "prodIcon");
            return (Criteria) this;
        }

        public Criteria andSpecNameIsNull() {
            addCriterion("spec_name is null");
            return (Criteria) this;
        }

        public Criteria andSpecNameIsNotNull() {
            addCriterion("spec_name is not null");
            return (Criteria) this;
        }

        public Criteria andSpecNameEqualTo(String value) {
            addCriterion("spec_name =", value, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameNotEqualTo(String value) {
            addCriterion("spec_name <>", value, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameGreaterThan(String value) {
            addCriterion("spec_name >", value, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameGreaterThanOrEqualTo(String value) {
            addCriterion("spec_name >=", value, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameLessThan(String value) {
            addCriterion("spec_name <", value, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameLessThanOrEqualTo(String value) {
            addCriterion("spec_name <=", value, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameLike(String value) {
            addCriterion("spec_name like", value, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameNotLike(String value) {
            addCriterion("spec_name not like", value, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameIn(List<String> values) {
            addCriterion("spec_name in", values, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameNotIn(List<String> values) {
            addCriterion("spec_name not in", values, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameBetween(String value1, String value2) {
            addCriterion("spec_name between", value1, value2, "specName");
            return (Criteria) this;
        }

        public Criteria andSpecNameNotBetween(String value1, String value2) {
            addCriterion("spec_name not between", value1, value2, "specName");
            return (Criteria) this;
        }

        public Criteria andStarNumIsNull() {
            addCriterion("star_num is null");
            return (Criteria) this;
        }

        public Criteria andStarNumIsNotNull() {
            addCriterion("star_num is not null");
            return (Criteria) this;
        }

        public Criteria andStarNumEqualTo(Boolean value) {
            addCriterion("star_num =", value, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumNotEqualTo(Boolean value) {
            addCriterion("star_num <>", value, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumGreaterThan(Boolean value) {
            addCriterion("star_num >", value, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumGreaterThanOrEqualTo(Boolean value) {
            addCriterion("star_num >=", value, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumLessThan(Boolean value) {
            addCriterion("star_num <", value, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumLessThanOrEqualTo(Boolean value) {
            addCriterion("star_num <=", value, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumIn(List<Boolean> values) {
            addCriterion("star_num in", values, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumNotIn(List<Boolean> values) {
            addCriterion("star_num not in", values, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumBetween(Boolean value1, Boolean value2) {
            addCriterion("star_num between", value1, value2, "starNum");
            return (Criteria) this;
        }

        public Criteria andStarNumNotBetween(Boolean value1, Boolean value2) {
            addCriterion("star_num not between", value1, value2, "starNum");
            return (Criteria) this;
        }

        public Criteria andChangeFlagIsNull() {
            addCriterion("change_flag is null");
            return (Criteria) this;
        }

        public Criteria andChangeFlagIsNotNull() {
            addCriterion("change_flag is not null");
            return (Criteria) this;
        }

        public Criteria andChangeFlagEqualTo(Byte value) {
            addCriterion("change_flag =", value, "changeFlag");
            return (Criteria) this;
        }

        public Criteria andChangeFlagNotEqualTo(Byte value) {
            addCriterion("change_flag <>", value, "changeFlag");
            return (Criteria) this;
        }

        public Criteria andChangeFlagGreaterThan(Byte value) {
            addCriterion("change_flag >", value, "changeFlag");
            return (Criteria) this;
        }

        public Criteria andChangeFlagGreaterThanOrEqualTo(Byte value) {
            addCriterion("change_flag >=", value, "changeFlag");
            return (Criteria) this;
        }

        public Criteria andChangeFlagLessThan(Byte value) {
            addCriterion("change_flag <", value, "changeFlag");
            return (Criteria) this;
        }

        public Criteria andChangeFlagLessThanOrEqualTo(Byte value) {
            addCriterion("change_flag <=", value, "changeFlag");
            return (Criteria) this;
        }

        public Criteria andChangeFlagIn(List<Byte> values) {
            addCriterion("change_flag in", values, "changeFlag");
            return (Criteria) this;
        }

        public Criteria andChangeFlagNotIn(List<Byte> values) {
            addCriterion("change_flag not in", values, "changeFlag");
            return (Criteria) this;
        }

        public Criteria andChangeFlagBetween(Byte value1, Byte value2) {
            addCriterion("change_flag between", value1, value2, "changeFlag");
            return (Criteria) this;
        }

        public Criteria andChangeFlagNotBetween(Byte value1, Byte value2) {
            addCriterion("change_flag not between", value1, value2, "changeFlag");
            return (Criteria) this;
        }

        public Criteria andMinWeightIsNull() {
            addCriterion("min_weight is null");
            return (Criteria) this;
        }

        public Criteria andMinWeightIsNotNull() {
            addCriterion("min_weight is not null");
            return (Criteria) this;
        }

        public Criteria andMinWeightEqualTo(BigDecimal value) {
            addCriterion("min_weight =", value, "minWeight");
            return (Criteria) this;
        }

        public Criteria andMinWeightNotEqualTo(BigDecimal value) {
            addCriterion("min_weight <>", value, "minWeight");
            return (Criteria) this;
        }

        public Criteria andMinWeightGreaterThan(BigDecimal value) {
            addCriterion("min_weight >", value, "minWeight");
            return (Criteria) this;
        }

        public Criteria andMinWeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("min_weight >=", value, "minWeight");
            return (Criteria) this;
        }

        public Criteria andMinWeightLessThan(BigDecimal value) {
            addCriterion("min_weight <", value, "minWeight");
            return (Criteria) this;
        }

        public Criteria andMinWeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("min_weight <=", value, "minWeight");
            return (Criteria) this;
        }

        public Criteria andMinWeightIn(List<BigDecimal> values) {
            addCriterion("min_weight in", values, "minWeight");
            return (Criteria) this;
        }

        public Criteria andMinWeightNotIn(List<BigDecimal> values) {
            addCriterion("min_weight not in", values, "minWeight");
            return (Criteria) this;
        }

        public Criteria andMinWeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_weight between", value1, value2, "minWeight");
            return (Criteria) this;
        }

        public Criteria andMinWeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("min_weight not between", value1, value2, "minWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightIsNull() {
            addCriterion("max_weight is null");
            return (Criteria) this;
        }

        public Criteria andMaxWeightIsNotNull() {
            addCriterion("max_weight is not null");
            return (Criteria) this;
        }

        public Criteria andMaxWeightEqualTo(BigDecimal value) {
            addCriterion("max_weight =", value, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightNotEqualTo(BigDecimal value) {
            addCriterion("max_weight <>", value, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightGreaterThan(BigDecimal value) {
            addCriterion("max_weight >", value, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("max_weight >=", value, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightLessThan(BigDecimal value) {
            addCriterion("max_weight <", value, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("max_weight <=", value, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightIn(List<BigDecimal> values) {
            addCriterion("max_weight in", values, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightNotIn(List<BigDecimal> values) {
            addCriterion("max_weight not in", values, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("max_weight between", value1, value2, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMaxWeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("max_weight not between", value1, value2, "maxWeight");
            return (Criteria) this;
        }

        public Criteria andMinNumIsNull() {
            addCriterion("min_num is null");
            return (Criteria) this;
        }

        public Criteria andMinNumIsNotNull() {
            addCriterion("min_num is not null");
            return (Criteria) this;
        }

        public Criteria andMinNumEqualTo(Integer value) {
            addCriterion("min_num =", value, "minNum");
            return (Criteria) this;
        }

        public Criteria andMinNumNotEqualTo(Integer value) {
            addCriterion("min_num <>", value, "minNum");
            return (Criteria) this;
        }

        public Criteria andMinNumGreaterThan(Integer value) {
            addCriterion("min_num >", value, "minNum");
            return (Criteria) this;
        }

        public Criteria andMinNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("min_num >=", value, "minNum");
            return (Criteria) this;
        }

        public Criteria andMinNumLessThan(Integer value) {
            addCriterion("min_num <", value, "minNum");
            return (Criteria) this;
        }

        public Criteria andMinNumLessThanOrEqualTo(Integer value) {
            addCriterion("min_num <=", value, "minNum");
            return (Criteria) this;
        }

        public Criteria andMinNumIn(List<Integer> values) {
            addCriterion("min_num in", values, "minNum");
            return (Criteria) this;
        }

        public Criteria andMinNumNotIn(List<Integer> values) {
            addCriterion("min_num not in", values, "minNum");
            return (Criteria) this;
        }

        public Criteria andMinNumBetween(Integer value1, Integer value2) {
            addCriterion("min_num between", value1, value2, "minNum");
            return (Criteria) this;
        }

        public Criteria andMinNumNotBetween(Integer value1, Integer value2) {
            addCriterion("min_num not between", value1, value2, "minNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumIsNull() {
            addCriterion("max_num is null");
            return (Criteria) this;
        }

        public Criteria andMaxNumIsNotNull() {
            addCriterion("max_num is not null");
            return (Criteria) this;
        }

        public Criteria andMaxNumEqualTo(Integer value) {
            addCriterion("max_num =", value, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumNotEqualTo(Integer value) {
            addCriterion("max_num <>", value, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumGreaterThan(Integer value) {
            addCriterion("max_num >", value, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumGreaterThanOrEqualTo(Integer value) {
            addCriterion("max_num >=", value, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumLessThan(Integer value) {
            addCriterion("max_num <", value, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumLessThanOrEqualTo(Integer value) {
            addCriterion("max_num <=", value, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumIn(List<Integer> values) {
            addCriterion("max_num in", values, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumNotIn(List<Integer> values) {
            addCriterion("max_num not in", values, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumBetween(Integer value1, Integer value2) {
            addCriterion("max_num between", value1, value2, "maxNum");
            return (Criteria) this;
        }

        public Criteria andMaxNumNotBetween(Integer value1, Integer value2) {
            addCriterion("max_num not between", value1, value2, "maxNum");
            return (Criteria) this;
        }

        public Criteria andNumUnitIsNull() {
            addCriterion("num_unit is null");
            return (Criteria) this;
        }

        public Criteria andNumUnitIsNotNull() {
            addCriterion("num_unit is not null");
            return (Criteria) this;
        }

        public Criteria andNumUnitEqualTo(String value) {
            addCriterion("num_unit =", value, "numUnit");
            return (Criteria) this;
        }

        public Criteria andNumUnitNotEqualTo(String value) {
            addCriterion("num_unit <>", value, "numUnit");
            return (Criteria) this;
        }

        public Criteria andNumUnitGreaterThan(String value) {
            addCriterion("num_unit >", value, "numUnit");
            return (Criteria) this;
        }

        public Criteria andNumUnitGreaterThanOrEqualTo(String value) {
            addCriterion("num_unit >=", value, "numUnit");
            return (Criteria) this;
        }

        public Criteria andNumUnitLessThan(String value) {
            addCriterion("num_unit <", value, "numUnit");
            return (Criteria) this;
        }

        public Criteria andNumUnitLessThanOrEqualTo(String value) {
            addCriterion("num_unit <=", value, "numUnit");
            return (Criteria) this;
        }

        public Criteria andNumUnitLike(String value) {
            addCriterion("num_unit like", value, "numUnit");
            return (Criteria) this;
        }

        public Criteria andNumUnitNotLike(String value) {
            addCriterion("num_unit not like", value, "numUnit");
            return (Criteria) this;
        }

        public Criteria andNumUnitIn(List<String> values) {
            addCriterion("num_unit in", values, "numUnit");
            return (Criteria) this;
        }

        public Criteria andNumUnitNotIn(List<String> values) {
            addCriterion("num_unit not in", values, "numUnit");
            return (Criteria) this;
        }

        public Criteria andNumUnitBetween(String value1, String value2) {
            addCriterion("num_unit between", value1, value2, "numUnit");
            return (Criteria) this;
        }

        public Criteria andNumUnitNotBetween(String value1, String value2) {
            addCriterion("num_unit not between", value1, value2, "numUnit");
            return (Criteria) this;
        }

        public Criteria andWeightUnitIsNull() {
            addCriterion("weight_unit is null");
            return (Criteria) this;
        }

        public Criteria andWeightUnitIsNotNull() {
            addCriterion("weight_unit is not null");
            return (Criteria) this;
        }

        public Criteria andWeightUnitEqualTo(String value) {
            addCriterion("weight_unit =", value, "weightUnit");
            return (Criteria) this;
        }

        public Criteria andWeightUnitNotEqualTo(String value) {
            addCriterion("weight_unit <>", value, "weightUnit");
            return (Criteria) this;
        }

        public Criteria andWeightUnitGreaterThan(String value) {
            addCriterion("weight_unit >", value, "weightUnit");
            return (Criteria) this;
        }

        public Criteria andWeightUnitGreaterThanOrEqualTo(String value) {
            addCriterion("weight_unit >=", value, "weightUnit");
            return (Criteria) this;
        }

        public Criteria andWeightUnitLessThan(String value) {
            addCriterion("weight_unit <", value, "weightUnit");
            return (Criteria) this;
        }

        public Criteria andWeightUnitLessThanOrEqualTo(String value) {
            addCriterion("weight_unit <=", value, "weightUnit");
            return (Criteria) this;
        }

        public Criteria andWeightUnitLike(String value) {
            addCriterion("weight_unit like", value, "weightUnit");
            return (Criteria) this;
        }

        public Criteria andWeightUnitNotLike(String value) {
            addCriterion("weight_unit not like", value, "weightUnit");
            return (Criteria) this;
        }

        public Criteria andWeightUnitIn(List<String> values) {
            addCriterion("weight_unit in", values, "weightUnit");
            return (Criteria) this;
        }

        public Criteria andWeightUnitNotIn(List<String> values) {
            addCriterion("weight_unit not in", values, "weightUnit");
            return (Criteria) this;
        }

        public Criteria andWeightUnitBetween(String value1, String value2) {
            addCriterion("weight_unit between", value1, value2, "weightUnit");
            return (Criteria) this;
        }

        public Criteria andWeightUnitNotBetween(String value1, String value2) {
            addCriterion("weight_unit not between", value1, value2, "weightUnit");
            return (Criteria) this;
        }

        public Criteria andUnitTypeIsNull() {
            addCriterion("unit_type is null");
            return (Criteria) this;
        }

        public Criteria andUnitTypeIsNotNull() {
            addCriterion("unit_type is not null");
            return (Criteria) this;
        }

        public Criteria andUnitTypeEqualTo(Boolean value) {
            addCriterion("unit_type =", value, "unitType");
            return (Criteria) this;
        }

        public Criteria andUnitTypeNotEqualTo(Boolean value) {
            addCriterion("unit_type <>", value, "unitType");
            return (Criteria) this;
        }

        public Criteria andUnitTypeGreaterThan(Boolean value) {
            addCriterion("unit_type >", value, "unitType");
            return (Criteria) this;
        }

        public Criteria andUnitTypeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("unit_type >=", value, "unitType");
            return (Criteria) this;
        }

        public Criteria andUnitTypeLessThan(Boolean value) {
            addCriterion("unit_type <", value, "unitType");
            return (Criteria) this;
        }

        public Criteria andUnitTypeLessThanOrEqualTo(Boolean value) {
            addCriterion("unit_type <=", value, "unitType");
            return (Criteria) this;
        }

        public Criteria andUnitTypeIn(List<Boolean> values) {
            addCriterion("unit_type in", values, "unitType");
            return (Criteria) this;
        }

        public Criteria andUnitTypeNotIn(List<Boolean> values) {
            addCriterion("unit_type not in", values, "unitType");
            return (Criteria) this;
        }

        public Criteria andUnitTypeBetween(Boolean value1, Boolean value2) {
            addCriterion("unit_type between", value1, value2, "unitType");
            return (Criteria) this;
        }

        public Criteria andUnitTypeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("unit_type not between", value1, value2, "unitType");
            return (Criteria) this;
        }

        public Criteria andPackageMaxWeightIsNull() {
            addCriterion("package_max_weight is null");
            return (Criteria) this;
        }

        public Criteria andPackageMaxWeightIsNotNull() {
            addCriterion("package_max_weight is not null");
            return (Criteria) this;
        }

        public Criteria andPackageMaxWeightEqualTo(Integer value) {
            addCriterion("package_max_weight =", value, "packageMaxWeight");
            return (Criteria) this;
        }

        public Criteria andPackageMaxWeightNotEqualTo(Integer value) {
            addCriterion("package_max_weight <>", value, "packageMaxWeight");
            return (Criteria) this;
        }

        public Criteria andPackageMaxWeightGreaterThan(Integer value) {
            addCriterion("package_max_weight >", value, "packageMaxWeight");
            return (Criteria) this;
        }

        public Criteria andPackageMaxWeightGreaterThanOrEqualTo(Integer value) {
            addCriterion("package_max_weight >=", value, "packageMaxWeight");
            return (Criteria) this;
        }

        public Criteria andPackageMaxWeightLessThan(Integer value) {
            addCriterion("package_max_weight <", value, "packageMaxWeight");
            return (Criteria) this;
        }

        public Criteria andPackageMaxWeightLessThanOrEqualTo(Integer value) {
            addCriterion("package_max_weight <=", value, "packageMaxWeight");
            return (Criteria) this;
        }

        public Criteria andPackageMaxWeightIn(List<Integer> values) {
            addCriterion("package_max_weight in", values, "packageMaxWeight");
            return (Criteria) this;
        }

        public Criteria andPackageMaxWeightNotIn(List<Integer> values) {
            addCriterion("package_max_weight not in", values, "packageMaxWeight");
            return (Criteria) this;
        }

        public Criteria andPackageMaxWeightBetween(Integer value1, Integer value2) {
            addCriterion("package_max_weight between", value1, value2, "packageMaxWeight");
            return (Criteria) this;
        }

        public Criteria andPackageMaxWeightNotBetween(Integer value1, Integer value2) {
            addCriterion("package_max_weight not between", value1, value2, "packageMaxWeight");
            return (Criteria) this;
        }

        public Criteria andIsGiftIsNull() {
            addCriterion("is_gift is null");
            return (Criteria) this;
        }

        public Criteria andIsGiftIsNotNull() {
            addCriterion("is_gift is not null");
            return (Criteria) this;
        }

        public Criteria andIsGiftEqualTo(Byte value) {
            addCriterion("is_gift =", value, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftNotEqualTo(Byte value) {
            addCriterion("is_gift <>", value, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftGreaterThan(Byte value) {
            addCriterion("is_gift >", value, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_gift >=", value, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftLessThan(Byte value) {
            addCriterion("is_gift <", value, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftLessThanOrEqualTo(Byte value) {
            addCriterion("is_gift <=", value, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftIn(List<Byte> values) {
            addCriterion("is_gift in", values, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftNotIn(List<Byte> values) {
            addCriterion("is_gift not in", values, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftBetween(Byte value1, Byte value2) {
            addCriterion("is_gift between", value1, value2, "isGift");
            return (Criteria) this;
        }

        public Criteria andIsGiftNotBetween(Byte value1, Byte value2) {
            addCriterion("is_gift not between", value1, value2, "isGift");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceIsNull() {
            addCriterion("original_price is null");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceIsNotNull() {
            addCriterion("original_price is not null");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceEqualTo(BigDecimal value) {
            addCriterion("original_price =", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceNotEqualTo(BigDecimal value) {
            addCriterion("original_price <>", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceGreaterThan(BigDecimal value) {
            addCriterion("original_price >", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("original_price >=", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceLessThan(BigDecimal value) {
            addCriterion("original_price <", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("original_price <=", value, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceIn(List<BigDecimal> values) {
            addCriterion("original_price in", values, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceNotIn(List<BigDecimal> values) {
            addCriterion("original_price not in", values, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("original_price between", value1, value2, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andOriginalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("original_price not between", value1, value2, "originalPrice");
            return (Criteria) this;
        }

        public Criteria andCouponPriceIsNull() {
            addCriterion("coupon_price is null");
            return (Criteria) this;
        }

        public Criteria andCouponPriceIsNotNull() {
            addCriterion("coupon_price is not null");
            return (Criteria) this;
        }

        public Criteria andCouponPriceEqualTo(BigDecimal value) {
            addCriterion("coupon_price =", value, "couponPrice");
            return (Criteria) this;
        }

        public Criteria andCouponPriceNotEqualTo(BigDecimal value) {
            addCriterion("coupon_price <>", value, "couponPrice");
            return (Criteria) this;
        }

        public Criteria andCouponPriceGreaterThan(BigDecimal value) {
            addCriterion("coupon_price >", value, "couponPrice");
            return (Criteria) this;
        }

        public Criteria andCouponPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("coupon_price >=", value, "couponPrice");
            return (Criteria) this;
        }

        public Criteria andCouponPriceLessThan(BigDecimal value) {
            addCriterion("coupon_price <", value, "couponPrice");
            return (Criteria) this;
        }

        public Criteria andCouponPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("coupon_price <=", value, "couponPrice");
            return (Criteria) this;
        }

        public Criteria andCouponPriceIn(List<BigDecimal> values) {
            addCriterion("coupon_price in", values, "couponPrice");
            return (Criteria) this;
        }

        public Criteria andCouponPriceNotIn(List<BigDecimal> values) {
            addCriterion("coupon_price not in", values, "couponPrice");
            return (Criteria) this;
        }

        public Criteria andCouponPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coupon_price between", value1, value2, "couponPrice");
            return (Criteria) this;
        }

        public Criteria andCouponPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("coupon_price not between", value1, value2, "couponPrice");
            return (Criteria) this;
        }

        public Criteria andVipPriceIsNull() {
            addCriterion("vip_price is null");
            return (Criteria) this;
        }

        public Criteria andVipPriceIsNotNull() {
            addCriterion("vip_price is not null");
            return (Criteria) this;
        }

        public Criteria andVipPriceEqualTo(BigDecimal value) {
            addCriterion("vip_price =", value, "vipPrice");
            return (Criteria) this;
        }

        public Criteria andVipPriceNotEqualTo(BigDecimal value) {
            addCriterion("vip_price <>", value, "vipPrice");
            return (Criteria) this;
        }

        public Criteria andVipPriceGreaterThan(BigDecimal value) {
            addCriterion("vip_price >", value, "vipPrice");
            return (Criteria) this;
        }

        public Criteria andVipPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("vip_price >=", value, "vipPrice");
            return (Criteria) this;
        }

        public Criteria andVipPriceLessThan(BigDecimal value) {
            addCriterion("vip_price <", value, "vipPrice");
            return (Criteria) this;
        }

        public Criteria andVipPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("vip_price <=", value, "vipPrice");
            return (Criteria) this;
        }

        public Criteria andVipPriceIn(List<BigDecimal> values) {
            addCriterion("vip_price in", values, "vipPrice");
            return (Criteria) this;
        }

        public Criteria andVipPriceNotIn(List<BigDecimal> values) {
            addCriterion("vip_price not in", values, "vipPrice");
            return (Criteria) this;
        }

        public Criteria andVipPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("vip_price between", value1, value2, "vipPrice");
            return (Criteria) this;
        }

        public Criteria andVipPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("vip_price not between", value1, value2, "vipPrice");
            return (Criteria) this;
        }

        public Criteria andRemark1IsNull() {
            addCriterion("remark1 is null");
            return (Criteria) this;
        }

        public Criteria andRemark1IsNotNull() {
            addCriterion("remark1 is not null");
            return (Criteria) this;
        }

        public Criteria andRemark1EqualTo(String value) {
            addCriterion("remark1 =", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1NotEqualTo(String value) {
            addCriterion("remark1 <>", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1GreaterThan(String value) {
            addCriterion("remark1 >", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1GreaterThanOrEqualTo(String value) {
            addCriterion("remark1 >=", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1LessThan(String value) {
            addCriterion("remark1 <", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1LessThanOrEqualTo(String value) {
            addCriterion("remark1 <=", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1Like(String value) {
            addCriterion("remark1 like", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1NotLike(String value) {
            addCriterion("remark1 not like", value, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1In(List<String> values) {
            addCriterion("remark1 in", values, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1NotIn(List<String> values) {
            addCriterion("remark1 not in", values, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1Between(String value1, String value2) {
            addCriterion("remark1 between", value1, value2, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark1NotBetween(String value1, String value2) {
            addCriterion("remark1 not between", value1, value2, "remark1");
            return (Criteria) this;
        }

        public Criteria andRemark2IsNull() {
            addCriterion("remark2 is null");
            return (Criteria) this;
        }

        public Criteria andRemark2IsNotNull() {
            addCriterion("remark2 is not null");
            return (Criteria) this;
        }

        public Criteria andRemark2EqualTo(String value) {
            addCriterion("remark2 =", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2NotEqualTo(String value) {
            addCriterion("remark2 <>", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2GreaterThan(String value) {
            addCriterion("remark2 >", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2GreaterThanOrEqualTo(String value) {
            addCriterion("remark2 >=", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2LessThan(String value) {
            addCriterion("remark2 <", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2LessThanOrEqualTo(String value) {
            addCriterion("remark2 <=", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2Like(String value) {
            addCriterion("remark2 like", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2NotLike(String value) {
            addCriterion("remark2 not like", value, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2In(List<String> values) {
            addCriterion("remark2 in", values, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2NotIn(List<String> values) {
            addCriterion("remark2 not in", values, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2Between(String value1, String value2) {
            addCriterion("remark2 between", value1, value2, "remark2");
            return (Criteria) this;
        }

        public Criteria andRemark2NotBetween(String value1, String value2) {
            addCriterion("remark2 not between", value1, value2, "remark2");
            return (Criteria) this;
        }

        public Criteria andFullPriceIsNull() {
            addCriterion("full_price is null");
            return (Criteria) this;
        }

        public Criteria andFullPriceIsNotNull() {
            addCriterion("full_price is not null");
            return (Criteria) this;
        }

        public Criteria andFullPriceEqualTo(Integer value) {
            addCriterion("full_price =", value, "fullPrice");
            return (Criteria) this;
        }

        public Criteria andFullPriceNotEqualTo(Integer value) {
            addCriterion("full_price <>", value, "fullPrice");
            return (Criteria) this;
        }

        public Criteria andFullPriceGreaterThan(Integer value) {
            addCriterion("full_price >", value, "fullPrice");
            return (Criteria) this;
        }

        public Criteria andFullPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("full_price >=", value, "fullPrice");
            return (Criteria) this;
        }

        public Criteria andFullPriceLessThan(Integer value) {
            addCriterion("full_price <", value, "fullPrice");
            return (Criteria) this;
        }

        public Criteria andFullPriceLessThanOrEqualTo(Integer value) {
            addCriterion("full_price <=", value, "fullPrice");
            return (Criteria) this;
        }

        public Criteria andFullPriceIn(List<Integer> values) {
            addCriterion("full_price in", values, "fullPrice");
            return (Criteria) this;
        }

        public Criteria andFullPriceNotIn(List<Integer> values) {
            addCriterion("full_price not in", values, "fullPrice");
            return (Criteria) this;
        }

        public Criteria andFullPriceBetween(Integer value1, Integer value2) {
            addCriterion("full_price between", value1, value2, "fullPrice");
            return (Criteria) this;
        }

        public Criteria andFullPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("full_price not between", value1, value2, "fullPrice");
            return (Criteria) this;
        }

        public Criteria andFullIdIsNull() {
            addCriterion("full_id is null");
            return (Criteria) this;
        }

        public Criteria andFullIdIsNotNull() {
            addCriterion("full_id is not null");
            return (Criteria) this;
        }

        public Criteria andFullIdEqualTo(Integer value) {
            addCriterion("full_id =", value, "fullId");
            return (Criteria) this;
        }

        public Criteria andFullIdNotEqualTo(Integer value) {
            addCriterion("full_id <>", value, "fullId");
            return (Criteria) this;
        }

        public Criteria andFullIdGreaterThan(Integer value) {
            addCriterion("full_id >", value, "fullId");
            return (Criteria) this;
        }

        public Criteria andFullIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("full_id >=", value, "fullId");
            return (Criteria) this;
        }

        public Criteria andFullIdLessThan(Integer value) {
            addCriterion("full_id <", value, "fullId");
            return (Criteria) this;
        }

        public Criteria andFullIdLessThanOrEqualTo(Integer value) {
            addCriterion("full_id <=", value, "fullId");
            return (Criteria) this;
        }

        public Criteria andFullIdIn(List<Integer> values) {
            addCriterion("full_id in", values, "fullId");
            return (Criteria) this;
        }

        public Criteria andFullIdNotIn(List<Integer> values) {
            addCriterion("full_id not in", values, "fullId");
            return (Criteria) this;
        }

        public Criteria andFullIdBetween(Integer value1, Integer value2) {
            addCriterion("full_id between", value1, value2, "fullId");
            return (Criteria) this;
        }

        public Criteria andFullIdNotBetween(Integer value1, Integer value2) {
            addCriterion("full_id not between", value1, value2, "fullId");
            return (Criteria) this;
        }

        public Criteria andPointPriceIsNull() {
            addCriterion("point_price is null");
            return (Criteria) this;
        }

        public Criteria andPointPriceIsNotNull() {
            addCriterion("point_price is not null");
            return (Criteria) this;
        }

        public Criteria andPointPriceEqualTo(Integer value) {
            addCriterion("point_price =", value, "pointPrice");
            return (Criteria) this;
        }

        public Criteria andPointPriceNotEqualTo(Integer value) {
            addCriterion("point_price <>", value, "pointPrice");
            return (Criteria) this;
        }

        public Criteria andPointPriceGreaterThan(Integer value) {
            addCriterion("point_price >", value, "pointPrice");
            return (Criteria) this;
        }

        public Criteria andPointPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("point_price >=", value, "pointPrice");
            return (Criteria) this;
        }

        public Criteria andPointPriceLessThan(Integer value) {
            addCriterion("point_price <", value, "pointPrice");
            return (Criteria) this;
        }

        public Criteria andPointPriceLessThanOrEqualTo(Integer value) {
            addCriterion("point_price <=", value, "pointPrice");
            return (Criteria) this;
        }

        public Criteria andPointPriceIn(List<Integer> values) {
            addCriterion("point_price in", values, "pointPrice");
            return (Criteria) this;
        }

        public Criteria andPointPriceNotIn(List<Integer> values) {
            addCriterion("point_price not in", values, "pointPrice");
            return (Criteria) this;
        }

        public Criteria andPointPriceBetween(Integer value1, Integer value2) {
            addCriterion("point_price between", value1, value2, "pointPrice");
            return (Criteria) this;
        }

        public Criteria andPointPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("point_price not between", value1, value2, "pointPrice");
            return (Criteria) this;
        }

        public Criteria andTotalDiscountIsNull() {
            addCriterion("total_discount is null");
            return (Criteria) this;
        }

        public Criteria andTotalDiscountIsNotNull() {
            addCriterion("total_discount is not null");
            return (Criteria) this;
        }

        public Criteria andTotalDiscountEqualTo(BigDecimal value) {
            addCriterion("total_discount =", value, "totalDiscount");
            return (Criteria) this;
        }

        public Criteria andTotalDiscountNotEqualTo(BigDecimal value) {
            addCriterion("total_discount <>", value, "totalDiscount");
            return (Criteria) this;
        }

        public Criteria andTotalDiscountGreaterThan(BigDecimal value) {
            addCriterion("total_discount >", value, "totalDiscount");
            return (Criteria) this;
        }

        public Criteria andTotalDiscountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_discount >=", value, "totalDiscount");
            return (Criteria) this;
        }

        public Criteria andTotalDiscountLessThan(BigDecimal value) {
            addCriterion("total_discount <", value, "totalDiscount");
            return (Criteria) this;
        }

        public Criteria andTotalDiscountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_discount <=", value, "totalDiscount");
            return (Criteria) this;
        }

        public Criteria andTotalDiscountIn(List<BigDecimal> values) {
            addCriterion("total_discount in", values, "totalDiscount");
            return (Criteria) this;
        }

        public Criteria andTotalDiscountNotIn(List<BigDecimal> values) {
            addCriterion("total_discount not in", values, "totalDiscount");
            return (Criteria) this;
        }

        public Criteria andTotalDiscountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_discount between", value1, value2, "totalDiscount");
            return (Criteria) this;
        }

        public Criteria andTotalDiscountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_discount not between", value1, value2, "totalDiscount");
            return (Criteria) this;
        }

        public Criteria andRemark6IsNull() {
            addCriterion("remark6 is null");
            return (Criteria) this;
        }

        public Criteria andRemark6IsNotNull() {
            addCriterion("remark6 is not null");
            return (Criteria) this;
        }

        public Criteria andRemark6EqualTo(BigDecimal value) {
            addCriterion("remark6 =", value, "remark6");
            return (Criteria) this;
        }

        public Criteria andRemark6NotEqualTo(BigDecimal value) {
            addCriterion("remark6 <>", value, "remark6");
            return (Criteria) this;
        }

        public Criteria andRemark6GreaterThan(BigDecimal value) {
            addCriterion("remark6 >", value, "remark6");
            return (Criteria) this;
        }

        public Criteria andRemark6GreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("remark6 >=", value, "remark6");
            return (Criteria) this;
        }

        public Criteria andRemark6LessThan(BigDecimal value) {
            addCriterion("remark6 <", value, "remark6");
            return (Criteria) this;
        }

        public Criteria andRemark6LessThanOrEqualTo(BigDecimal value) {
            addCriterion("remark6 <=", value, "remark6");
            return (Criteria) this;
        }

        public Criteria andRemark6In(List<BigDecimal> values) {
            addCriterion("remark6 in", values, "remark6");
            return (Criteria) this;
        }

        public Criteria andRemark6NotIn(List<BigDecimal> values) {
            addCriterion("remark6 not in", values, "remark6");
            return (Criteria) this;
        }

        public Criteria andRemark6Between(BigDecimal value1, BigDecimal value2) {
            addCriterion("remark6 between", value1, value2, "remark6");
            return (Criteria) this;
        }

        public Criteria andRemark6NotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("remark6 not between", value1, value2, "remark6");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIsNull() {
            addCriterion("merchant_id is null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIsNotNull() {
            addCriterion("merchant_id is not null");
            return (Criteria) this;
        }

        public Criteria andMerchantIdEqualTo(Integer value) {
            addCriterion("merchant_id =", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotEqualTo(Integer value) {
            addCriterion("merchant_id <>", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThan(Integer value) {
            addCriterion("merchant_id >", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("merchant_id >=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThan(Integer value) {
            addCriterion("merchant_id <", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdLessThanOrEqualTo(Integer value) {
            addCriterion("merchant_id <=", value, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdIn(List<Integer> values) {
            addCriterion("merchant_id in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotIn(List<Integer> values) {
            addCriterion("merchant_id not in", values, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdBetween(Integer value1, Integer value2) {
            addCriterion("merchant_id between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andMerchantIdNotBetween(Integer value1, Integer value2) {
            addCriterion("merchant_id not between", value1, value2, "merchantId");
            return (Criteria) this;
        }

        public Criteria andProductInfoIsNull() {
            addCriterion("product_info is null");
            return (Criteria) this;
        }

        public Criteria andProductInfoIsNotNull() {
            addCriterion("product_info is not null");
            return (Criteria) this;
        }

        public Criteria andProductInfoEqualTo(String value) {
            addCriterion("product_info =", value, "productInfo");
            return (Criteria) this;
        }

        public Criteria andProductInfoNotEqualTo(String value) {
            addCriterion("product_info <>", value, "productInfo");
            return (Criteria) this;
        }

        public Criteria andProductInfoGreaterThan(String value) {
            addCriterion("product_info >", value, "productInfo");
            return (Criteria) this;
        }

        public Criteria andProductInfoGreaterThanOrEqualTo(String value) {
            addCriterion("product_info >=", value, "productInfo");
            return (Criteria) this;
        }

        public Criteria andProductInfoLessThan(String value) {
            addCriterion("product_info <", value, "productInfo");
            return (Criteria) this;
        }

        public Criteria andProductInfoLessThanOrEqualTo(String value) {
            addCriterion("product_info <=", value, "productInfo");
            return (Criteria) this;
        }

        public Criteria andProductInfoLike(String value) {
            addCriterion("product_info like", value, "productInfo");
            return (Criteria) this;
        }

        public Criteria andProductInfoNotLike(String value) {
            addCriterion("product_info not like", value, "productInfo");
            return (Criteria) this;
        }

        public Criteria andProductInfoIn(List<String> values) {
            addCriterion("product_info in", values, "productInfo");
            return (Criteria) this;
        }

        public Criteria andProductInfoNotIn(List<String> values) {
            addCriterion("product_info not in", values, "productInfo");
            return (Criteria) this;
        }

        public Criteria andProductInfoBetween(String value1, String value2) {
            addCriterion("product_info between", value1, value2, "productInfo");
            return (Criteria) this;
        }

        public Criteria andProductInfoNotBetween(String value1, String value2) {
            addCriterion("product_info not between", value1, value2, "productInfo");
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