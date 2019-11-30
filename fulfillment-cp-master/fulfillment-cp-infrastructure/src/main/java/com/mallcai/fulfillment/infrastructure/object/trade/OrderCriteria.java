package com.mallcai.fulfillment.infrastructure.object.trade;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OrderCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public OrderCriteria() {
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

        public Criteria andOrderNameIsNull() {
            addCriterion("order_name is null");
            return (Criteria) this;
        }

        public Criteria andOrderNameIsNotNull() {
            addCriterion("order_name is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNameEqualTo(String value) {
            addCriterion("order_name =", value, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameNotEqualTo(String value) {
            addCriterion("order_name <>", value, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameGreaterThan(String value) {
            addCriterion("order_name >", value, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameGreaterThanOrEqualTo(String value) {
            addCriterion("order_name >=", value, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameLessThan(String value) {
            addCriterion("order_name <", value, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameLessThanOrEqualTo(String value) {
            addCriterion("order_name <=", value, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameLike(String value) {
            addCriterion("order_name like", value, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameNotLike(String value) {
            addCriterion("order_name not like", value, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameIn(List<String> values) {
            addCriterion("order_name in", values, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameNotIn(List<String> values) {
            addCriterion("order_name not in", values, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameBetween(String value1, String value2) {
            addCriterion("order_name between", value1, value2, "orderName");
            return (Criteria) this;
        }

        public Criteria andOrderNameNotBetween(String value1, String value2) {
            addCriterion("order_name not between", value1, value2, "orderName");
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

        public Criteria andAsynPayStatusIsNull() {
            addCriterion("asyn_pay_status is null");
            return (Criteria) this;
        }

        public Criteria andAsynPayStatusIsNotNull() {
            addCriterion("asyn_pay_status is not null");
            return (Criteria) this;
        }

        public Criteria andAsynPayStatusEqualTo(String value) {
            addCriterion("asyn_pay_status =", value, "asynPayStatus");
            return (Criteria) this;
        }

        public Criteria andAsynPayStatusNotEqualTo(String value) {
            addCriterion("asyn_pay_status <>", value, "asynPayStatus");
            return (Criteria) this;
        }

        public Criteria andAsynPayStatusGreaterThan(String value) {
            addCriterion("asyn_pay_status >", value, "asynPayStatus");
            return (Criteria) this;
        }

        public Criteria andAsynPayStatusGreaterThanOrEqualTo(String value) {
            addCriterion("asyn_pay_status >=", value, "asynPayStatus");
            return (Criteria) this;
        }

        public Criteria andAsynPayStatusLessThan(String value) {
            addCriterion("asyn_pay_status <", value, "asynPayStatus");
            return (Criteria) this;
        }

        public Criteria andAsynPayStatusLessThanOrEqualTo(String value) {
            addCriterion("asyn_pay_status <=", value, "asynPayStatus");
            return (Criteria) this;
        }

        public Criteria andAsynPayStatusLike(String value) {
            addCriterion("asyn_pay_status like", value, "asynPayStatus");
            return (Criteria) this;
        }

        public Criteria andAsynPayStatusNotLike(String value) {
            addCriterion("asyn_pay_status not like", value, "asynPayStatus");
            return (Criteria) this;
        }

        public Criteria andAsynPayStatusIn(List<String> values) {
            addCriterion("asyn_pay_status in", values, "asynPayStatus");
            return (Criteria) this;
        }

        public Criteria andAsynPayStatusNotIn(List<String> values) {
            addCriterion("asyn_pay_status not in", values, "asynPayStatus");
            return (Criteria) this;
        }

        public Criteria andAsynPayStatusBetween(String value1, String value2) {
            addCriterion("asyn_pay_status between", value1, value2, "asynPayStatus");
            return (Criteria) this;
        }

        public Criteria andAsynPayStatusNotBetween(String value1, String value2) {
            addCriterion("asyn_pay_status not between", value1, value2, "asynPayStatus");
            return (Criteria) this;
        }

        public Criteria andOrderPicIsNull() {
            addCriterion("order_pic is null");
            return (Criteria) this;
        }

        public Criteria andOrderPicIsNotNull() {
            addCriterion("order_pic is not null");
            return (Criteria) this;
        }

        public Criteria andOrderPicEqualTo(String value) {
            addCriterion("order_pic =", value, "orderPic");
            return (Criteria) this;
        }

        public Criteria andOrderPicNotEqualTo(String value) {
            addCriterion("order_pic <>", value, "orderPic");
            return (Criteria) this;
        }

        public Criteria andOrderPicGreaterThan(String value) {
            addCriterion("order_pic >", value, "orderPic");
            return (Criteria) this;
        }

        public Criteria andOrderPicGreaterThanOrEqualTo(String value) {
            addCriterion("order_pic >=", value, "orderPic");
            return (Criteria) this;
        }

        public Criteria andOrderPicLessThan(String value) {
            addCriterion("order_pic <", value, "orderPic");
            return (Criteria) this;
        }

        public Criteria andOrderPicLessThanOrEqualTo(String value) {
            addCriterion("order_pic <=", value, "orderPic");
            return (Criteria) this;
        }

        public Criteria andOrderPicLike(String value) {
            addCriterion("order_pic like", value, "orderPic");
            return (Criteria) this;
        }

        public Criteria andOrderPicNotLike(String value) {
            addCriterion("order_pic not like", value, "orderPic");
            return (Criteria) this;
        }

        public Criteria andOrderPicIn(List<String> values) {
            addCriterion("order_pic in", values, "orderPic");
            return (Criteria) this;
        }

        public Criteria andOrderPicNotIn(List<String> values) {
            addCriterion("order_pic not in", values, "orderPic");
            return (Criteria) this;
        }

        public Criteria andOrderPicBetween(String value1, String value2) {
            addCriterion("order_pic between", value1, value2, "orderPic");
            return (Criteria) this;
        }

        public Criteria andOrderPicNotBetween(String value1, String value2) {
            addCriterion("order_pic not between", value1, value2, "orderPic");
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

        public Criteria andPayCompleteTimeIsNull() {
            addCriterion("pay_complete_time is null");
            return (Criteria) this;
        }

        public Criteria andPayCompleteTimeIsNotNull() {
            addCriterion("pay_complete_time is not null");
            return (Criteria) this;
        }

        public Criteria andPayCompleteTimeEqualTo(Date value) {
            addCriterion("pay_complete_time =", value, "payCompleteTime");
            return (Criteria) this;
        }

        public Criteria andPayCompleteTimeNotEqualTo(Date value) {
            addCriterion("pay_complete_time <>", value, "payCompleteTime");
            return (Criteria) this;
        }

        public Criteria andPayCompleteTimeGreaterThan(Date value) {
            addCriterion("pay_complete_time >", value, "payCompleteTime");
            return (Criteria) this;
        }

        public Criteria andPayCompleteTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("pay_complete_time >=", value, "payCompleteTime");
            return (Criteria) this;
        }

        public Criteria andPayCompleteTimeLessThan(Date value) {
            addCriterion("pay_complete_time <", value, "payCompleteTime");
            return (Criteria) this;
        }

        public Criteria andPayCompleteTimeLessThanOrEqualTo(Date value) {
            addCriterion("pay_complete_time <=", value, "payCompleteTime");
            return (Criteria) this;
        }

        public Criteria andPayCompleteTimeIn(List<Date> values) {
            addCriterion("pay_complete_time in", values, "payCompleteTime");
            return (Criteria) this;
        }

        public Criteria andPayCompleteTimeNotIn(List<Date> values) {
            addCriterion("pay_complete_time not in", values, "payCompleteTime");
            return (Criteria) this;
        }

        public Criteria andPayCompleteTimeBetween(Date value1, Date value2) {
            addCriterion("pay_complete_time between", value1, value2, "payCompleteTime");
            return (Criteria) this;
        }

        public Criteria andPayCompleteTimeNotBetween(Date value1, Date value2) {
            addCriterion("pay_complete_time not between", value1, value2, "payCompleteTime");
            return (Criteria) this;
        }

        public Criteria andGenerateTimeIsNull() {
            addCriterion("generate_time is null");
            return (Criteria) this;
        }

        public Criteria andGenerateTimeIsNotNull() {
            addCriterion("generate_time is not null");
            return (Criteria) this;
        }

        public Criteria andGenerateTimeEqualTo(Date value) {
            addCriterion("generate_time =", value, "generateTime");
            return (Criteria) this;
        }

        public Criteria andGenerateTimeNotEqualTo(Date value) {
            addCriterion("generate_time <>", value, "generateTime");
            return (Criteria) this;
        }

        public Criteria andGenerateTimeGreaterThan(Date value) {
            addCriterion("generate_time >", value, "generateTime");
            return (Criteria) this;
        }

        public Criteria andGenerateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("generate_time >=", value, "generateTime");
            return (Criteria) this;
        }

        public Criteria andGenerateTimeLessThan(Date value) {
            addCriterion("generate_time <", value, "generateTime");
            return (Criteria) this;
        }

        public Criteria andGenerateTimeLessThanOrEqualTo(Date value) {
            addCriterion("generate_time <=", value, "generateTime");
            return (Criteria) this;
        }

        public Criteria andGenerateTimeIn(List<Date> values) {
            addCriterion("generate_time in", values, "generateTime");
            return (Criteria) this;
        }

        public Criteria andGenerateTimeNotIn(List<Date> values) {
            addCriterion("generate_time not in", values, "generateTime");
            return (Criteria) this;
        }

        public Criteria andGenerateTimeBetween(Date value1, Date value2) {
            addCriterion("generate_time between", value1, value2, "generateTime");
            return (Criteria) this;
        }

        public Criteria andGenerateTimeNotBetween(Date value1, Date value2) {
            addCriterion("generate_time not between", value1, value2, "generateTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeIsNull() {
            addCriterion("close_time is null");
            return (Criteria) this;
        }

        public Criteria andCloseTimeIsNotNull() {
            addCriterion("close_time is not null");
            return (Criteria) this;
        }

        public Criteria andCloseTimeEqualTo(Date value) {
            addCriterion("close_time =", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeNotEqualTo(Date value) {
            addCriterion("close_time <>", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeGreaterThan(Date value) {
            addCriterion("close_time >", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("close_time >=", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeLessThan(Date value) {
            addCriterion("close_time <", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeLessThanOrEqualTo(Date value) {
            addCriterion("close_time <=", value, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeIn(List<Date> values) {
            addCriterion("close_time in", values, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeNotIn(List<Date> values) {
            addCriterion("close_time not in", values, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeBetween(Date value1, Date value2) {
            addCriterion("close_time between", value1, value2, "closeTime");
            return (Criteria) this;
        }

        public Criteria andCloseTimeNotBetween(Date value1, Date value2) {
            addCriterion("close_time not between", value1, value2, "closeTime");
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
            addCriterionForJDBCDate("pickup_time =", value, "pickupTime");
            return (Criteria) this;
        }

        public Criteria andPickupTimeNotEqualTo(Date value) {
            addCriterionForJDBCDate("pickup_time <>", value, "pickupTime");
            return (Criteria) this;
        }

        public Criteria andPickupTimeGreaterThan(Date value) {
            addCriterionForJDBCDate("pickup_time >", value, "pickupTime");
            return (Criteria) this;
        }

        public Criteria andPickupTimeGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("pickup_time >=", value, "pickupTime");
            return (Criteria) this;
        }

        public Criteria andPickupTimeLessThan(Date value) {
            addCriterionForJDBCDate("pickup_time <", value, "pickupTime");
            return (Criteria) this;
        }

        public Criteria andPickupTimeLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("pickup_time <=", value, "pickupTime");
            return (Criteria) this;
        }

        public Criteria andPickupTimeIn(List<Date> values) {
            addCriterionForJDBCDate("pickup_time in", values, "pickupTime");
            return (Criteria) this;
        }

        public Criteria andPickupTimeNotIn(List<Date> values) {
            addCriterionForJDBCDate("pickup_time not in", values, "pickupTime");
            return (Criteria) this;
        }

        public Criteria andPickupTimeBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("pickup_time between", value1, value2, "pickupTime");
            return (Criteria) this;
        }

        public Criteria andPickupTimeNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("pickup_time not between", value1, value2, "pickupTime");
            return (Criteria) this;
        }

        public Criteria andOrderPriceIsNull() {
            addCriterion("order_price is null");
            return (Criteria) this;
        }

        public Criteria andOrderPriceIsNotNull() {
            addCriterion("order_price is not null");
            return (Criteria) this;
        }

        public Criteria andOrderPriceEqualTo(BigDecimal value) {
            addCriterion("order_price =", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceNotEqualTo(BigDecimal value) {
            addCriterion("order_price <>", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceGreaterThan(BigDecimal value) {
            addCriterion("order_price >", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("order_price >=", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceLessThan(BigDecimal value) {
            addCriterion("order_price <", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("order_price <=", value, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceIn(List<BigDecimal> values) {
            addCriterion("order_price in", values, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceNotIn(List<BigDecimal> values) {
            addCriterion("order_price not in", values, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_price between", value1, value2, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andOrderPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("order_price not between", value1, value2, "orderPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIsNull() {
            addCriterion("total_price is null");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIsNotNull() {
            addCriterion("total_price is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPriceEqualTo(BigDecimal value) {
            addCriterion("total_price =", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotEqualTo(BigDecimal value) {
            addCriterion("total_price <>", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceGreaterThan(BigDecimal value) {
            addCriterion("total_price >", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("total_price >=", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceLessThan(BigDecimal value) {
            addCriterion("total_price <", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceLessThanOrEqualTo(BigDecimal value) {
            addCriterion("total_price <=", value, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceIn(List<BigDecimal> values) {
            addCriterion("total_price in", values, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotIn(List<BigDecimal> values) {
            addCriterion("total_price not in", values, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_price between", value1, value2, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPriceNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("total_price not between", value1, value2, "totalPrice");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNull() {
            addCriterion("create_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIsNotNull() {
            addCriterion("create_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdEqualTo(Integer value) {
            addCriterion("create_user_id =", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotEqualTo(Integer value) {
            addCriterion("create_user_id <>", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThan(Integer value) {
            addCriterion("create_user_id >", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("create_user_id >=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThan(Integer value) {
            addCriterion("create_user_id <", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("create_user_id <=", value, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdIn(List<Integer> values) {
            addCriterion("create_user_id in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotIn(List<Integer> values) {
            addCriterion("create_user_id not in", values, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("create_user_id between", value1, value2, "createUserId");
            return (Criteria) this;
        }

        public Criteria andCreateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("create_user_id not between", value1, value2, "createUserId");
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

        public Criteria andUpdateUserIdIsNull() {
            addCriterion("update_user_id is null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIsNotNull() {
            addCriterion("update_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdEqualTo(Integer value) {
            addCriterion("update_user_id =", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotEqualTo(Integer value) {
            addCriterion("update_user_id <>", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThan(Integer value) {
            addCriterion("update_user_id >", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("update_user_id >=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThan(Integer value) {
            addCriterion("update_user_id <", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("update_user_id <=", value, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdIn(List<Integer> values) {
            addCriterion("update_user_id in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotIn(List<Integer> values) {
            addCriterion("update_user_id not in", values, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdBetween(Integer value1, Integer value2) {
            addCriterion("update_user_id between", value1, value2, "updateUserId");
            return (Criteria) this;
        }

        public Criteria andUpdateUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("update_user_id not between", value1, value2, "updateUserId");
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

        public Criteria andPickupStartTimeIsNull() {
            addCriterion("pickup_start_time is null");
            return (Criteria) this;
        }

        public Criteria andPickupStartTimeIsNotNull() {
            addCriterion("pickup_start_time is not null");
            return (Criteria) this;
        }

        public Criteria andPickupStartTimeEqualTo(String value) {
            addCriterion("pickup_start_time =", value, "pickupStartTime");
            return (Criteria) this;
        }

        public Criteria andPickupStartTimeNotEqualTo(String value) {
            addCriterion("pickup_start_time <>", value, "pickupStartTime");
            return (Criteria) this;
        }

        public Criteria andPickupStartTimeGreaterThan(String value) {
            addCriterion("pickup_start_time >", value, "pickupStartTime");
            return (Criteria) this;
        }

        public Criteria andPickupStartTimeGreaterThanOrEqualTo(String value) {
            addCriterion("pickup_start_time >=", value, "pickupStartTime");
            return (Criteria) this;
        }

        public Criteria andPickupStartTimeLessThan(String value) {
            addCriterion("pickup_start_time <", value, "pickupStartTime");
            return (Criteria) this;
        }

        public Criteria andPickupStartTimeLessThanOrEqualTo(String value) {
            addCriterion("pickup_start_time <=", value, "pickupStartTime");
            return (Criteria) this;
        }

        public Criteria andPickupStartTimeLike(String value) {
            addCriterion("pickup_start_time like", value, "pickupStartTime");
            return (Criteria) this;
        }

        public Criteria andPickupStartTimeNotLike(String value) {
            addCriterion("pickup_start_time not like", value, "pickupStartTime");
            return (Criteria) this;
        }

        public Criteria andPickupStartTimeIn(List<String> values) {
            addCriterion("pickup_start_time in", values, "pickupStartTime");
            return (Criteria) this;
        }

        public Criteria andPickupStartTimeNotIn(List<String> values) {
            addCriterion("pickup_start_time not in", values, "pickupStartTime");
            return (Criteria) this;
        }

        public Criteria andPickupStartTimeBetween(String value1, String value2) {
            addCriterion("pickup_start_time between", value1, value2, "pickupStartTime");
            return (Criteria) this;
        }

        public Criteria andPickupStartTimeNotBetween(String value1, String value2) {
            addCriterion("pickup_start_time not between", value1, value2, "pickupStartTime");
            return (Criteria) this;
        }

        public Criteria andPickupEndTimeIsNull() {
            addCriterion("pickup_end_time is null");
            return (Criteria) this;
        }

        public Criteria andPickupEndTimeIsNotNull() {
            addCriterion("pickup_end_time is not null");
            return (Criteria) this;
        }

        public Criteria andPickupEndTimeEqualTo(String value) {
            addCriterion("pickup_end_time =", value, "pickupEndTime");
            return (Criteria) this;
        }

        public Criteria andPickupEndTimeNotEqualTo(String value) {
            addCriterion("pickup_end_time <>", value, "pickupEndTime");
            return (Criteria) this;
        }

        public Criteria andPickupEndTimeGreaterThan(String value) {
            addCriterion("pickup_end_time >", value, "pickupEndTime");
            return (Criteria) this;
        }

        public Criteria andPickupEndTimeGreaterThanOrEqualTo(String value) {
            addCriterion("pickup_end_time >=", value, "pickupEndTime");
            return (Criteria) this;
        }

        public Criteria andPickupEndTimeLessThan(String value) {
            addCriterion("pickup_end_time <", value, "pickupEndTime");
            return (Criteria) this;
        }

        public Criteria andPickupEndTimeLessThanOrEqualTo(String value) {
            addCriterion("pickup_end_time <=", value, "pickupEndTime");
            return (Criteria) this;
        }

        public Criteria andPickupEndTimeLike(String value) {
            addCriterion("pickup_end_time like", value, "pickupEndTime");
            return (Criteria) this;
        }

        public Criteria andPickupEndTimeNotLike(String value) {
            addCriterion("pickup_end_time not like", value, "pickupEndTime");
            return (Criteria) this;
        }

        public Criteria andPickupEndTimeIn(List<String> values) {
            addCriterion("pickup_end_time in", values, "pickupEndTime");
            return (Criteria) this;
        }

        public Criteria andPickupEndTimeNotIn(List<String> values) {
            addCriterion("pickup_end_time not in", values, "pickupEndTime");
            return (Criteria) this;
        }

        public Criteria andPickupEndTimeBetween(String value1, String value2) {
            addCriterion("pickup_end_time between", value1, value2, "pickupEndTime");
            return (Criteria) this;
        }

        public Criteria andPickupEndTimeNotBetween(String value1, String value2) {
            addCriterion("pickup_end_time not between", value1, value2, "pickupEndTime");
            return (Criteria) this;
        }

        public Criteria andMemoIsNull() {
            addCriterion("memo is null");
            return (Criteria) this;
        }

        public Criteria andMemoIsNotNull() {
            addCriterion("memo is not null");
            return (Criteria) this;
        }

        public Criteria andMemoEqualTo(String value) {
            addCriterion("memo =", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotEqualTo(String value) {
            addCriterion("memo <>", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThan(String value) {
            addCriterion("memo >", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoGreaterThanOrEqualTo(String value) {
            addCriterion("memo >=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThan(String value) {
            addCriterion("memo <", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLessThanOrEqualTo(String value) {
            addCriterion("memo <=", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoLike(String value) {
            addCriterion("memo like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotLike(String value) {
            addCriterion("memo not like", value, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoIn(List<String> values) {
            addCriterion("memo in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotIn(List<String> values) {
            addCriterion("memo not in", values, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoBetween(String value1, String value2) {
            addCriterion("memo between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andMemoNotBetween(String value1, String value2) {
            addCriterion("memo not between", value1, value2, "memo");
            return (Criteria) this;
        }

        public Criteria andPickupCodeIsNull() {
            addCriterion("pickup_code is null");
            return (Criteria) this;
        }

        public Criteria andPickupCodeIsNotNull() {
            addCriterion("pickup_code is not null");
            return (Criteria) this;
        }

        public Criteria andPickupCodeEqualTo(String value) {
            addCriterion("pickup_code =", value, "pickupCode");
            return (Criteria) this;
        }

        public Criteria andPickupCodeNotEqualTo(String value) {
            addCriterion("pickup_code <>", value, "pickupCode");
            return (Criteria) this;
        }

        public Criteria andPickupCodeGreaterThan(String value) {
            addCriterion("pickup_code >", value, "pickupCode");
            return (Criteria) this;
        }

        public Criteria andPickupCodeGreaterThanOrEqualTo(String value) {
            addCriterion("pickup_code >=", value, "pickupCode");
            return (Criteria) this;
        }

        public Criteria andPickupCodeLessThan(String value) {
            addCriterion("pickup_code <", value, "pickupCode");
            return (Criteria) this;
        }

        public Criteria andPickupCodeLessThanOrEqualTo(String value) {
            addCriterion("pickup_code <=", value, "pickupCode");
            return (Criteria) this;
        }

        public Criteria andPickupCodeLike(String value) {
            addCriterion("pickup_code like", value, "pickupCode");
            return (Criteria) this;
        }

        public Criteria andPickupCodeNotLike(String value) {
            addCriterion("pickup_code not like", value, "pickupCode");
            return (Criteria) this;
        }

        public Criteria andPickupCodeIn(List<String> values) {
            addCriterion("pickup_code in", values, "pickupCode");
            return (Criteria) this;
        }

        public Criteria andPickupCodeNotIn(List<String> values) {
            addCriterion("pickup_code not in", values, "pickupCode");
            return (Criteria) this;
        }

        public Criteria andPickupCodeBetween(String value1, String value2) {
            addCriterion("pickup_code between", value1, value2, "pickupCode");
            return (Criteria) this;
        }

        public Criteria andPickupCodeNotBetween(String value1, String value2) {
            addCriterion("pickup_code not between", value1, value2, "pickupCode");
            return (Criteria) this;
        }

        public Criteria andWeightIsNull() {
            addCriterion("weight is null");
            return (Criteria) this;
        }

        public Criteria andWeightIsNotNull() {
            addCriterion("weight is not null");
            return (Criteria) this;
        }

        public Criteria andWeightEqualTo(BigDecimal value) {
            addCriterion("weight =", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotEqualTo(BigDecimal value) {
            addCriterion("weight <>", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThan(BigDecimal value) {
            addCriterion("weight >", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("weight >=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThan(BigDecimal value) {
            addCriterion("weight <", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("weight <=", value, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightIn(List<BigDecimal> values) {
            addCriterion("weight in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotIn(List<BigDecimal> values) {
            addCriterion("weight not in", values, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("weight between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andWeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("weight not between", value1, value2, "weight");
            return (Criteria) this;
        }

        public Criteria andPickupTypeIdIsNull() {
            addCriterion("pickup_type_id is null");
            return (Criteria) this;
        }

        public Criteria andPickupTypeIdIsNotNull() {
            addCriterion("pickup_type_id is not null");
            return (Criteria) this;
        }

        public Criteria andPickupTypeIdEqualTo(Byte value) {
            addCriterion("pickup_type_id =", value, "pickupTypeId");
            return (Criteria) this;
        }

        public Criteria andPickupTypeIdNotEqualTo(Byte value) {
            addCriterion("pickup_type_id <>", value, "pickupTypeId");
            return (Criteria) this;
        }

        public Criteria andPickupTypeIdGreaterThan(Byte value) {
            addCriterion("pickup_type_id >", value, "pickupTypeId");
            return (Criteria) this;
        }

        public Criteria andPickupTypeIdGreaterThanOrEqualTo(Byte value) {
            addCriterion("pickup_type_id >=", value, "pickupTypeId");
            return (Criteria) this;
        }

        public Criteria andPickupTypeIdLessThan(Byte value) {
            addCriterion("pickup_type_id <", value, "pickupTypeId");
            return (Criteria) this;
        }

        public Criteria andPickupTypeIdLessThanOrEqualTo(Byte value) {
            addCriterion("pickup_type_id <=", value, "pickupTypeId");
            return (Criteria) this;
        }

        public Criteria andPickupTypeIdIn(List<Byte> values) {
            addCriterion("pickup_type_id in", values, "pickupTypeId");
            return (Criteria) this;
        }

        public Criteria andPickupTypeIdNotIn(List<Byte> values) {
            addCriterion("pickup_type_id not in", values, "pickupTypeId");
            return (Criteria) this;
        }

        public Criteria andPickupTypeIdBetween(Byte value1, Byte value2) {
            addCriterion("pickup_type_id between", value1, value2, "pickupTypeId");
            return (Criteria) this;
        }

        public Criteria andPickupTypeIdNotBetween(Byte value1, Byte value2) {
            addCriterion("pickup_type_id not between", value1, value2, "pickupTypeId");
            return (Criteria) this;
        }

        public Criteria andIsWillingIsNull() {
            addCriterion("is_willing is null");
            return (Criteria) this;
        }

        public Criteria andIsWillingIsNotNull() {
            addCriterion("is_willing is not null");
            return (Criteria) this;
        }

        public Criteria andIsWillingEqualTo(Byte value) {
            addCriterion("is_willing =", value, "isWilling");
            return (Criteria) this;
        }

        public Criteria andIsWillingNotEqualTo(Byte value) {
            addCriterion("is_willing <>", value, "isWilling");
            return (Criteria) this;
        }

        public Criteria andIsWillingGreaterThan(Byte value) {
            addCriterion("is_willing >", value, "isWilling");
            return (Criteria) this;
        }

        public Criteria andIsWillingGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_willing >=", value, "isWilling");
            return (Criteria) this;
        }

        public Criteria andIsWillingLessThan(Byte value) {
            addCriterion("is_willing <", value, "isWilling");
            return (Criteria) this;
        }

        public Criteria andIsWillingLessThanOrEqualTo(Byte value) {
            addCriterion("is_willing <=", value, "isWilling");
            return (Criteria) this;
        }

        public Criteria andIsWillingIn(List<Byte> values) {
            addCriterion("is_willing in", values, "isWilling");
            return (Criteria) this;
        }

        public Criteria andIsWillingNotIn(List<Byte> values) {
            addCriterion("is_willing not in", values, "isWilling");
            return (Criteria) this;
        }

        public Criteria andIsWillingBetween(Byte value1, Byte value2) {
            addCriterion("is_willing between", value1, value2, "isWilling");
            return (Criteria) this;
        }

        public Criteria andIsWillingNotBetween(Byte value1, Byte value2) {
            addCriterion("is_willing not between", value1, value2, "isWilling");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNull() {
            addCriterion("balance is null");
            return (Criteria) this;
        }

        public Criteria andBalanceIsNotNull() {
            addCriterion("balance is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceEqualTo(Integer value) {
            addCriterion("balance =", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotEqualTo(Integer value) {
            addCriterion("balance <>", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThan(Integer value) {
            addCriterion("balance >", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceGreaterThanOrEqualTo(Integer value) {
            addCriterion("balance >=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThan(Integer value) {
            addCriterion("balance <", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceLessThanOrEqualTo(Integer value) {
            addCriterion("balance <=", value, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceIn(List<Integer> values) {
            addCriterion("balance in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotIn(List<Integer> values) {
            addCriterion("balance not in", values, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceBetween(Integer value1, Integer value2) {
            addCriterion("balance between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andBalanceNotBetween(Integer value1, Integer value2) {
            addCriterion("balance not between", value1, value2, "balance");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNull() {
            addCriterion("is_del is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("is_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(String value) {
            addCriterion("is_del =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(String value) {
            addCriterion("is_del <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(String value) {
            addCriterion("is_del >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(String value) {
            addCriterion("is_del >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(String value) {
            addCriterion("is_del <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(String value) {
            addCriterion("is_del <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLike(String value) {
            addCriterion("is_del like", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotLike(String value) {
            addCriterion("is_del not like", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<String> values) {
            addCriterion("is_del in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<String> values) {
            addCriterion("is_del not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(String value1, String value2) {
            addCriterion("is_del between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(String value1, String value2) {
            addCriterion("is_del not between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andExtrasIsNull() {
            addCriterion("extras is null");
            return (Criteria) this;
        }

        public Criteria andExtrasIsNotNull() {
            addCriterion("extras is not null");
            return (Criteria) this;
        }

        public Criteria andExtrasEqualTo(String value) {
            addCriterion("extras =", value, "extras");
            return (Criteria) this;
        }

        public Criteria andExtrasNotEqualTo(String value) {
            addCriterion("extras <>", value, "extras");
            return (Criteria) this;
        }

        public Criteria andExtrasGreaterThan(String value) {
            addCriterion("extras >", value, "extras");
            return (Criteria) this;
        }

        public Criteria andExtrasGreaterThanOrEqualTo(String value) {
            addCriterion("extras >=", value, "extras");
            return (Criteria) this;
        }

        public Criteria andExtrasLessThan(String value) {
            addCriterion("extras <", value, "extras");
            return (Criteria) this;
        }

        public Criteria andExtrasLessThanOrEqualTo(String value) {
            addCriterion("extras <=", value, "extras");
            return (Criteria) this;
        }

        public Criteria andExtrasLike(String value) {
            addCriterion("extras like", value, "extras");
            return (Criteria) this;
        }

        public Criteria andExtrasNotLike(String value) {
            addCriterion("extras not like", value, "extras");
            return (Criteria) this;
        }

        public Criteria andExtrasIn(List<String> values) {
            addCriterion("extras in", values, "extras");
            return (Criteria) this;
        }

        public Criteria andExtrasNotIn(List<String> values) {
            addCriterion("extras not in", values, "extras");
            return (Criteria) this;
        }

        public Criteria andExtrasBetween(String value1, String value2) {
            addCriterion("extras between", value1, value2, "extras");
            return (Criteria) this;
        }

        public Criteria andExtrasNotBetween(String value1, String value2) {
            addCriterion("extras not between", value1, value2, "extras");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentIsNull() {
            addCriterion("evaluate_content is null");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentIsNotNull() {
            addCriterion("evaluate_content is not null");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentEqualTo(String value) {
            addCriterion("evaluate_content =", value, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentNotEqualTo(String value) {
            addCriterion("evaluate_content <>", value, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentGreaterThan(String value) {
            addCriterion("evaluate_content >", value, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentGreaterThanOrEqualTo(String value) {
            addCriterion("evaluate_content >=", value, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentLessThan(String value) {
            addCriterion("evaluate_content <", value, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentLessThanOrEqualTo(String value) {
            addCriterion("evaluate_content <=", value, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentLike(String value) {
            addCriterion("evaluate_content like", value, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentNotLike(String value) {
            addCriterion("evaluate_content not like", value, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentIn(List<String> values) {
            addCriterion("evaluate_content in", values, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentNotIn(List<String> values) {
            addCriterion("evaluate_content not in", values, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentBetween(String value1, String value2) {
            addCriterion("evaluate_content between", value1, value2, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andEvaluateContentNotBetween(String value1, String value2) {
            addCriterion("evaluate_content not between", value1, value2, "evaluateContent");
            return (Criteria) this;
        }

        public Criteria andCouponIdIsNull() {
            addCriterion("coupon_id is null");
            return (Criteria) this;
        }

        public Criteria andCouponIdIsNotNull() {
            addCriterion("coupon_id is not null");
            return (Criteria) this;
        }

        public Criteria andCouponIdEqualTo(Integer value) {
            addCriterion("coupon_id =", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotEqualTo(Integer value) {
            addCriterion("coupon_id <>", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThan(Integer value) {
            addCriterion("coupon_id >", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_id >=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThan(Integer value) {
            addCriterion("coupon_id <", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_id <=", value, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdIn(List<Integer> values) {
            addCriterion("coupon_id in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotIn(List<Integer> values) {
            addCriterion("coupon_id not in", values, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdBetween(Integer value1, Integer value2) {
            addCriterion("coupon_id between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponIdNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_id not between", value1, value2, "couponId");
            return (Criteria) this;
        }

        public Criteria andCouponDescIsNull() {
            addCriterion("coupon_desc is null");
            return (Criteria) this;
        }

        public Criteria andCouponDescIsNotNull() {
            addCriterion("coupon_desc is not null");
            return (Criteria) this;
        }

        public Criteria andCouponDescEqualTo(String value) {
            addCriterion("coupon_desc =", value, "couponDesc");
            return (Criteria) this;
        }

        public Criteria andCouponDescNotEqualTo(String value) {
            addCriterion("coupon_desc <>", value, "couponDesc");
            return (Criteria) this;
        }

        public Criteria andCouponDescGreaterThan(String value) {
            addCriterion("coupon_desc >", value, "couponDesc");
            return (Criteria) this;
        }

        public Criteria andCouponDescGreaterThanOrEqualTo(String value) {
            addCriterion("coupon_desc >=", value, "couponDesc");
            return (Criteria) this;
        }

        public Criteria andCouponDescLessThan(String value) {
            addCriterion("coupon_desc <", value, "couponDesc");
            return (Criteria) this;
        }

        public Criteria andCouponDescLessThanOrEqualTo(String value) {
            addCriterion("coupon_desc <=", value, "couponDesc");
            return (Criteria) this;
        }

        public Criteria andCouponDescLike(String value) {
            addCriterion("coupon_desc like", value, "couponDesc");
            return (Criteria) this;
        }

        public Criteria andCouponDescNotLike(String value) {
            addCriterion("coupon_desc not like", value, "couponDesc");
            return (Criteria) this;
        }

        public Criteria andCouponDescIn(List<String> values) {
            addCriterion("coupon_desc in", values, "couponDesc");
            return (Criteria) this;
        }

        public Criteria andCouponDescNotIn(List<String> values) {
            addCriterion("coupon_desc not in", values, "couponDesc");
            return (Criteria) this;
        }

        public Criteria andCouponDescBetween(String value1, String value2) {
            addCriterion("coupon_desc between", value1, value2, "couponDesc");
            return (Criteria) this;
        }

        public Criteria andCouponDescNotBetween(String value1, String value2) {
            addCriterion("coupon_desc not between", value1, value2, "couponDesc");
            return (Criteria) this;
        }

        public Criteria andPickupTimeIdIsNull() {
            addCriterion("pickup_time_id is null");
            return (Criteria) this;
        }

        public Criteria andPickupTimeIdIsNotNull() {
            addCriterion("pickup_time_id is not null");
            return (Criteria) this;
        }

        public Criteria andPickupTimeIdEqualTo(Integer value) {
            addCriterion("pickup_time_id =", value, "pickupTimeId");
            return (Criteria) this;
        }

        public Criteria andPickupTimeIdNotEqualTo(Integer value) {
            addCriterion("pickup_time_id <>", value, "pickupTimeId");
            return (Criteria) this;
        }

        public Criteria andPickupTimeIdGreaterThan(Integer value) {
            addCriterion("pickup_time_id >", value, "pickupTimeId");
            return (Criteria) this;
        }

        public Criteria andPickupTimeIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("pickup_time_id >=", value, "pickupTimeId");
            return (Criteria) this;
        }

        public Criteria andPickupTimeIdLessThan(Integer value) {
            addCriterion("pickup_time_id <", value, "pickupTimeId");
            return (Criteria) this;
        }

        public Criteria andPickupTimeIdLessThanOrEqualTo(Integer value) {
            addCriterion("pickup_time_id <=", value, "pickupTimeId");
            return (Criteria) this;
        }

        public Criteria andPickupTimeIdIn(List<Integer> values) {
            addCriterion("pickup_time_id in", values, "pickupTimeId");
            return (Criteria) this;
        }

        public Criteria andPickupTimeIdNotIn(List<Integer> values) {
            addCriterion("pickup_time_id not in", values, "pickupTimeId");
            return (Criteria) this;
        }

        public Criteria andPickupTimeIdBetween(Integer value1, Integer value2) {
            addCriterion("pickup_time_id between", value1, value2, "pickupTimeId");
            return (Criteria) this;
        }

        public Criteria andPickupTimeIdNotBetween(Integer value1, Integer value2) {
            addCriterion("pickup_time_id not between", value1, value2, "pickupTimeId");
            return (Criteria) this;
        }

        public Criteria andCompleteUserIdIsNull() {
            addCriterion("complete_user_id is null");
            return (Criteria) this;
        }

        public Criteria andCompleteUserIdIsNotNull() {
            addCriterion("complete_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteUserIdEqualTo(Integer value) {
            addCriterion("complete_user_id =", value, "completeUserId");
            return (Criteria) this;
        }

        public Criteria andCompleteUserIdNotEqualTo(Integer value) {
            addCriterion("complete_user_id <>", value, "completeUserId");
            return (Criteria) this;
        }

        public Criteria andCompleteUserIdGreaterThan(Integer value) {
            addCriterion("complete_user_id >", value, "completeUserId");
            return (Criteria) this;
        }

        public Criteria andCompleteUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("complete_user_id >=", value, "completeUserId");
            return (Criteria) this;
        }

        public Criteria andCompleteUserIdLessThan(Integer value) {
            addCriterion("complete_user_id <", value, "completeUserId");
            return (Criteria) this;
        }

        public Criteria andCompleteUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("complete_user_id <=", value, "completeUserId");
            return (Criteria) this;
        }

        public Criteria andCompleteUserIdIn(List<Integer> values) {
            addCriterion("complete_user_id in", values, "completeUserId");
            return (Criteria) this;
        }

        public Criteria andCompleteUserIdNotIn(List<Integer> values) {
            addCriterion("complete_user_id not in", values, "completeUserId");
            return (Criteria) this;
        }

        public Criteria andCompleteUserIdBetween(Integer value1, Integer value2) {
            addCriterion("complete_user_id between", value1, value2, "completeUserId");
            return (Criteria) this;
        }

        public Criteria andCompleteUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("complete_user_id not between", value1, value2, "completeUserId");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeIsNull() {
            addCriterion("complete_time is null");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeIsNotNull() {
            addCriterion("complete_time is not null");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeEqualTo(Date value) {
            addCriterion("complete_time =", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeNotEqualTo(Date value) {
            addCriterion("complete_time <>", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeGreaterThan(Date value) {
            addCriterion("complete_time >", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("complete_time >=", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeLessThan(Date value) {
            addCriterion("complete_time <", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeLessThanOrEqualTo(Date value) {
            addCriterion("complete_time <=", value, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeIn(List<Date> values) {
            addCriterion("complete_time in", values, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeNotIn(List<Date> values) {
            addCriterion("complete_time not in", values, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeBetween(Date value1, Date value2) {
            addCriterion("complete_time between", value1, value2, "completeTime");
            return (Criteria) this;
        }

        public Criteria andCompleteTimeNotBetween(Date value1, Date value2) {
            addCriterion("complete_time not between", value1, value2, "completeTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeIsNull() {
            addCriterion("lock_time is null");
            return (Criteria) this;
        }

        public Criteria andLockTimeIsNotNull() {
            addCriterion("lock_time is not null");
            return (Criteria) this;
        }

        public Criteria andLockTimeEqualTo(Date value) {
            addCriterion("lock_time =", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeNotEqualTo(Date value) {
            addCriterion("lock_time <>", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeGreaterThan(Date value) {
            addCriterion("lock_time >", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("lock_time >=", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeLessThan(Date value) {
            addCriterion("lock_time <", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeLessThanOrEqualTo(Date value) {
            addCriterion("lock_time <=", value, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeIn(List<Date> values) {
            addCriterion("lock_time in", values, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeNotIn(List<Date> values) {
            addCriterion("lock_time not in", values, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeBetween(Date value1, Date value2) {
            addCriterion("lock_time between", value1, value2, "lockTime");
            return (Criteria) this;
        }

        public Criteria andLockTimeNotBetween(Date value1, Date value2) {
            addCriterion("lock_time not between", value1, value2, "lockTime");
            return (Criteria) this;
        }

        public Criteria andCancelTypeIsNull() {
            addCriterion("cancel_type is null");
            return (Criteria) this;
        }

        public Criteria andCancelTypeIsNotNull() {
            addCriterion("cancel_type is not null");
            return (Criteria) this;
        }

        public Criteria andCancelTypeEqualTo(Boolean value) {
            addCriterion("cancel_type =", value, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeNotEqualTo(Boolean value) {
            addCriterion("cancel_type <>", value, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeGreaterThan(Boolean value) {
            addCriterion("cancel_type >", value, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("cancel_type >=", value, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeLessThan(Boolean value) {
            addCriterion("cancel_type <", value, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeLessThanOrEqualTo(Boolean value) {
            addCriterion("cancel_type <=", value, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeIn(List<Boolean> values) {
            addCriterion("cancel_type in", values, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeNotIn(List<Boolean> values) {
            addCriterion("cancel_type not in", values, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeBetween(Boolean value1, Boolean value2) {
            addCriterion("cancel_type between", value1, value2, "cancelType");
            return (Criteria) this;
        }

        public Criteria andCancelTypeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("cancel_type not between", value1, value2, "cancelType");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIsNull() {
            addCriterion("balance_type is null");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIsNotNull() {
            addCriterion("balance_type is not null");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeEqualTo(Boolean value) {
            addCriterion("balance_type =", value, "balanceType");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeNotEqualTo(Boolean value) {
            addCriterion("balance_type <>", value, "balanceType");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeGreaterThan(Boolean value) {
            addCriterion("balance_type >", value, "balanceType");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeGreaterThanOrEqualTo(Boolean value) {
            addCriterion("balance_type >=", value, "balanceType");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeLessThan(Boolean value) {
            addCriterion("balance_type <", value, "balanceType");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeLessThanOrEqualTo(Boolean value) {
            addCriterion("balance_type <=", value, "balanceType");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeIn(List<Boolean> values) {
            addCriterion("balance_type in", values, "balanceType");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeNotIn(List<Boolean> values) {
            addCriterion("balance_type not in", values, "balanceType");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeBetween(Boolean value1, Boolean value2) {
            addCriterion("balance_type between", value1, value2, "balanceType");
            return (Criteria) this;
        }

        public Criteria andBalanceTypeNotBetween(Boolean value1, Boolean value2) {
            addCriterion("balance_type not between", value1, value2, "balanceType");
            return (Criteria) this;
        }

        public Criteria andChangeIsNull() {
            addCriterion("change is null");
            return (Criteria) this;
        }

        public Criteria andChangeIsNotNull() {
            addCriterion("change is not null");
            return (Criteria) this;
        }

        public Criteria andChangeEqualTo(Integer value) {
            addCriterion("change =", value, "change");
            return (Criteria) this;
        }

        public Criteria andChangeNotEqualTo(Integer value) {
            addCriterion("change <>", value, "change");
            return (Criteria) this;
        }

        public Criteria andChangeGreaterThan(Integer value) {
            addCriterion("change >", value, "change");
            return (Criteria) this;
        }

        public Criteria andChangeGreaterThanOrEqualTo(Integer value) {
            addCriterion("change >=", value, "change");
            return (Criteria) this;
        }

        public Criteria andChangeLessThan(Integer value) {
            addCriterion("change <", value, "change");
            return (Criteria) this;
        }

        public Criteria andChangeLessThanOrEqualTo(Integer value) {
            addCriterion("change <=", value, "change");
            return (Criteria) this;
        }

        public Criteria andChangeIn(List<Integer> values) {
            addCriterion("change in", values, "change");
            return (Criteria) this;
        }

        public Criteria andChangeNotIn(List<Integer> values) {
            addCriterion("change not in", values, "change");
            return (Criteria) this;
        }

        public Criteria andChangeBetween(Integer value1, Integer value2) {
            addCriterion("change between", value1, value2, "change");
            return (Criteria) this;
        }

        public Criteria andChangeNotBetween(Integer value1, Integer value2) {
            addCriterion("change not between", value1, value2, "change");
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

        public Criteria andVersionEqualTo(Byte value) {
            addCriterion("version =", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotEqualTo(Byte value) {
            addCriterion("version <>", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThan(Byte value) {
            addCriterion("version >", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionGreaterThanOrEqualTo(Byte value) {
            addCriterion("version >=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThan(Byte value) {
            addCriterion("version <", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionLessThanOrEqualTo(Byte value) {
            addCriterion("version <=", value, "version");
            return (Criteria) this;
        }

        public Criteria andVersionIn(List<Byte> values) {
            addCriterion("version in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotIn(List<Byte> values) {
            addCriterion("version not in", values, "version");
            return (Criteria) this;
        }

        public Criteria andVersionBetween(Byte value1, Byte value2) {
            addCriterion("version between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andVersionNotBetween(Byte value1, Byte value2) {
            addCriterion("version not between", value1, value2, "version");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNull() {
            addCriterion("pay_type is null");
            return (Criteria) this;
        }

        public Criteria andPayTypeIsNotNull() {
            addCriterion("pay_type is not null");
            return (Criteria) this;
        }

        public Criteria andPayTypeEqualTo(Byte value) {
            addCriterion("pay_type =", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotEqualTo(Byte value) {
            addCriterion("pay_type <>", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThan(Byte value) {
            addCriterion("pay_type >", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("pay_type >=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThan(Byte value) {
            addCriterion("pay_type <", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeLessThanOrEqualTo(Byte value) {
            addCriterion("pay_type <=", value, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeIn(List<Byte> values) {
            addCriterion("pay_type in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotIn(List<Byte> values) {
            addCriterion("pay_type not in", values, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeBetween(Byte value1, Byte value2) {
            addCriterion("pay_type between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andPayTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("pay_type not between", value1, value2, "payType");
            return (Criteria) this;
        }

        public Criteria andCouponValueIsNull() {
            addCriterion("coupon_value is null");
            return (Criteria) this;
        }

        public Criteria andCouponValueIsNotNull() {
            addCriterion("coupon_value is not null");
            return (Criteria) this;
        }

        public Criteria andCouponValueEqualTo(Integer value) {
            addCriterion("coupon_value =", value, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueNotEqualTo(Integer value) {
            addCriterion("coupon_value <>", value, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueGreaterThan(Integer value) {
            addCriterion("coupon_value >", value, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueGreaterThanOrEqualTo(Integer value) {
            addCriterion("coupon_value >=", value, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueLessThan(Integer value) {
            addCriterion("coupon_value <", value, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueLessThanOrEqualTo(Integer value) {
            addCriterion("coupon_value <=", value, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueIn(List<Integer> values) {
            addCriterion("coupon_value in", values, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueNotIn(List<Integer> values) {
            addCriterion("coupon_value not in", values, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueBetween(Integer value1, Integer value2) {
            addCriterion("coupon_value between", value1, value2, "couponValue");
            return (Criteria) this;
        }

        public Criteria andCouponValueNotBetween(Integer value1, Integer value2) {
            addCriterion("coupon_value not between", value1, value2, "couponValue");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeIsNull() {
            addCriterion("delivery_mode is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeIsNotNull() {
            addCriterion("delivery_mode is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeEqualTo(Byte value) {
            addCriterion("delivery_mode =", value, "deliveryMode");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeNotEqualTo(Byte value) {
            addCriterion("delivery_mode <>", value, "deliveryMode");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeGreaterThan(Byte value) {
            addCriterion("delivery_mode >", value, "deliveryMode");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeGreaterThanOrEqualTo(Byte value) {
            addCriterion("delivery_mode >=", value, "deliveryMode");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeLessThan(Byte value) {
            addCriterion("delivery_mode <", value, "deliveryMode");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeLessThanOrEqualTo(Byte value) {
            addCriterion("delivery_mode <=", value, "deliveryMode");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeIn(List<Byte> values) {
            addCriterion("delivery_mode in", values, "deliveryMode");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeNotIn(List<Byte> values) {
            addCriterion("delivery_mode not in", values, "deliveryMode");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeBetween(Byte value1, Byte value2) {
            addCriterion("delivery_mode between", value1, value2, "deliveryMode");
            return (Criteria) this;
        }

        public Criteria andDeliveryModeNotBetween(Byte value1, Byte value2) {
            addCriterion("delivery_mode not between", value1, value2, "deliveryMode");
            return (Criteria) this;
        }

        public Criteria andLockOrderUserIdIsNull() {
            addCriterion("lock_order_user_id is null");
            return (Criteria) this;
        }

        public Criteria andLockOrderUserIdIsNotNull() {
            addCriterion("lock_order_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andLockOrderUserIdEqualTo(Integer value) {
            addCriterion("lock_order_user_id =", value, "lockOrderUserId");
            return (Criteria) this;
        }

        public Criteria andLockOrderUserIdNotEqualTo(Integer value) {
            addCriterion("lock_order_user_id <>", value, "lockOrderUserId");
            return (Criteria) this;
        }

        public Criteria andLockOrderUserIdGreaterThan(Integer value) {
            addCriterion("lock_order_user_id >", value, "lockOrderUserId");
            return (Criteria) this;
        }

        public Criteria andLockOrderUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("lock_order_user_id >=", value, "lockOrderUserId");
            return (Criteria) this;
        }

        public Criteria andLockOrderUserIdLessThan(Integer value) {
            addCriterion("lock_order_user_id <", value, "lockOrderUserId");
            return (Criteria) this;
        }

        public Criteria andLockOrderUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("lock_order_user_id <=", value, "lockOrderUserId");
            return (Criteria) this;
        }

        public Criteria andLockOrderUserIdIn(List<Integer> values) {
            addCriterion("lock_order_user_id in", values, "lockOrderUserId");
            return (Criteria) this;
        }

        public Criteria andLockOrderUserIdNotIn(List<Integer> values) {
            addCriterion("lock_order_user_id not in", values, "lockOrderUserId");
            return (Criteria) this;
        }

        public Criteria andLockOrderUserIdBetween(Integer value1, Integer value2) {
            addCriterion("lock_order_user_id between", value1, value2, "lockOrderUserId");
            return (Criteria) this;
        }

        public Criteria andLockOrderUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("lock_order_user_id not between", value1, value2, "lockOrderUserId");
            return (Criteria) this;
        }

        public Criteria andRecipientsIsNull() {
            addCriterion("recipients is null");
            return (Criteria) this;
        }

        public Criteria andRecipientsIsNotNull() {
            addCriterion("recipients is not null");
            return (Criteria) this;
        }

        public Criteria andRecipientsEqualTo(String value) {
            addCriterion("recipients =", value, "recipients");
            return (Criteria) this;
        }

        public Criteria andRecipientsNotEqualTo(String value) {
            addCriterion("recipients <>", value, "recipients");
            return (Criteria) this;
        }

        public Criteria andRecipientsGreaterThan(String value) {
            addCriterion("recipients >", value, "recipients");
            return (Criteria) this;
        }

        public Criteria andRecipientsGreaterThanOrEqualTo(String value) {
            addCriterion("recipients >=", value, "recipients");
            return (Criteria) this;
        }

        public Criteria andRecipientsLessThan(String value) {
            addCriterion("recipients <", value, "recipients");
            return (Criteria) this;
        }

        public Criteria andRecipientsLessThanOrEqualTo(String value) {
            addCriterion("recipients <=", value, "recipients");
            return (Criteria) this;
        }

        public Criteria andRecipientsLike(String value) {
            addCriterion("recipients like", value, "recipients");
            return (Criteria) this;
        }

        public Criteria andRecipientsNotLike(String value) {
            addCriterion("recipients not like", value, "recipients");
            return (Criteria) this;
        }

        public Criteria andRecipientsIn(List<String> values) {
            addCriterion("recipients in", values, "recipients");
            return (Criteria) this;
        }

        public Criteria andRecipientsNotIn(List<String> values) {
            addCriterion("recipients not in", values, "recipients");
            return (Criteria) this;
        }

        public Criteria andRecipientsBetween(String value1, String value2) {
            addCriterion("recipients between", value1, value2, "recipients");
            return (Criteria) this;
        }

        public Criteria andRecipientsNotBetween(String value1, String value2) {
            addCriterion("recipients not between", value1, value2, "recipients");
            return (Criteria) this;
        }

        public Criteria andRecipientsPhoneIsNull() {
            addCriterion("recipients_phone is null");
            return (Criteria) this;
        }

        public Criteria andRecipientsPhoneIsNotNull() {
            addCriterion("recipients_phone is not null");
            return (Criteria) this;
        }

        public Criteria andRecipientsPhoneEqualTo(String value) {
            addCriterion("recipients_phone =", value, "recipientsPhone");
            return (Criteria) this;
        }

        public Criteria andRecipientsPhoneNotEqualTo(String value) {
            addCriterion("recipients_phone <>", value, "recipientsPhone");
            return (Criteria) this;
        }

        public Criteria andRecipientsPhoneGreaterThan(String value) {
            addCriterion("recipients_phone >", value, "recipientsPhone");
            return (Criteria) this;
        }

        public Criteria andRecipientsPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("recipients_phone >=", value, "recipientsPhone");
            return (Criteria) this;
        }

        public Criteria andRecipientsPhoneLessThan(String value) {
            addCriterion("recipients_phone <", value, "recipientsPhone");
            return (Criteria) this;
        }

        public Criteria andRecipientsPhoneLessThanOrEqualTo(String value) {
            addCriterion("recipients_phone <=", value, "recipientsPhone");
            return (Criteria) this;
        }

        public Criteria andRecipientsPhoneLike(String value) {
            addCriterion("recipients_phone like", value, "recipientsPhone");
            return (Criteria) this;
        }

        public Criteria andRecipientsPhoneNotLike(String value) {
            addCriterion("recipients_phone not like", value, "recipientsPhone");
            return (Criteria) this;
        }

        public Criteria andRecipientsPhoneIn(List<String> values) {
            addCriterion("recipients_phone in", values, "recipientsPhone");
            return (Criteria) this;
        }

        public Criteria andRecipientsPhoneNotIn(List<String> values) {
            addCriterion("recipients_phone not in", values, "recipientsPhone");
            return (Criteria) this;
        }

        public Criteria andRecipientsPhoneBetween(String value1, String value2) {
            addCriterion("recipients_phone between", value1, value2, "recipientsPhone");
            return (Criteria) this;
        }

        public Criteria andRecipientsPhoneNotBetween(String value1, String value2) {
            addCriterion("recipients_phone not between", value1, value2, "recipientsPhone");
            return (Criteria) this;
        }

        public Criteria andRecipientsAddrIsNull() {
            addCriterion("recipients_addr is null");
            return (Criteria) this;
        }

        public Criteria andRecipientsAddrIsNotNull() {
            addCriterion("recipients_addr is not null");
            return (Criteria) this;
        }

        public Criteria andRecipientsAddrEqualTo(String value) {
            addCriterion("recipients_addr =", value, "recipientsAddr");
            return (Criteria) this;
        }

        public Criteria andRecipientsAddrNotEqualTo(String value) {
            addCriterion("recipients_addr <>", value, "recipientsAddr");
            return (Criteria) this;
        }

        public Criteria andRecipientsAddrGreaterThan(String value) {
            addCriterion("recipients_addr >", value, "recipientsAddr");
            return (Criteria) this;
        }

        public Criteria andRecipientsAddrGreaterThanOrEqualTo(String value) {
            addCriterion("recipients_addr >=", value, "recipientsAddr");
            return (Criteria) this;
        }

        public Criteria andRecipientsAddrLessThan(String value) {
            addCriterion("recipients_addr <", value, "recipientsAddr");
            return (Criteria) this;
        }

        public Criteria andRecipientsAddrLessThanOrEqualTo(String value) {
            addCriterion("recipients_addr <=", value, "recipientsAddr");
            return (Criteria) this;
        }

        public Criteria andRecipientsAddrLike(String value) {
            addCriterion("recipients_addr like", value, "recipientsAddr");
            return (Criteria) this;
        }

        public Criteria andRecipientsAddrNotLike(String value) {
            addCriterion("recipients_addr not like", value, "recipientsAddr");
            return (Criteria) this;
        }

        public Criteria andRecipientsAddrIn(List<String> values) {
            addCriterion("recipients_addr in", values, "recipientsAddr");
            return (Criteria) this;
        }

        public Criteria andRecipientsAddrNotIn(List<String> values) {
            addCriterion("recipients_addr not in", values, "recipientsAddr");
            return (Criteria) this;
        }

        public Criteria andRecipientsAddrBetween(String value1, String value2) {
            addCriterion("recipients_addr between", value1, value2, "recipientsAddr");
            return (Criteria) this;
        }

        public Criteria andRecipientsAddrNotBetween(String value1, String value2) {
            addCriterion("recipients_addr not between", value1, value2, "recipientsAddr");
            return (Criteria) this;
        }

        public Criteria andFreightIsNull() {
            addCriterion("freight is null");
            return (Criteria) this;
        }

        public Criteria andFreightIsNotNull() {
            addCriterion("freight is not null");
            return (Criteria) this;
        }

        public Criteria andFreightEqualTo(Integer value) {
            addCriterion("freight =", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotEqualTo(Integer value) {
            addCriterion("freight <>", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThan(Integer value) {
            addCriterion("freight >", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightGreaterThanOrEqualTo(Integer value) {
            addCriterion("freight >=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThan(Integer value) {
            addCriterion("freight <", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightLessThanOrEqualTo(Integer value) {
            addCriterion("freight <=", value, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightIn(List<Integer> values) {
            addCriterion("freight in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotIn(List<Integer> values) {
            addCriterion("freight not in", values, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightBetween(Integer value1, Integer value2) {
            addCriterion("freight between", value1, value2, "freight");
            return (Criteria) this;
        }

        public Criteria andFreightNotBetween(Integer value1, Integer value2) {
            addCriterion("freight not between", value1, value2, "freight");
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

        public Criteria andResidenceIdIsNull() {
            addCriterion("residence_id is null");
            return (Criteria) this;
        }

        public Criteria andResidenceIdIsNotNull() {
            addCriterion("residence_id is not null");
            return (Criteria) this;
        }

        public Criteria andResidenceIdEqualTo(Integer value) {
            addCriterion("residence_id =", value, "residenceId");
            return (Criteria) this;
        }

        public Criteria andResidenceIdNotEqualTo(Integer value) {
            addCriterion("residence_id <>", value, "residenceId");
            return (Criteria) this;
        }

        public Criteria andResidenceIdGreaterThan(Integer value) {
            addCriterion("residence_id >", value, "residenceId");
            return (Criteria) this;
        }

        public Criteria andResidenceIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("residence_id >=", value, "residenceId");
            return (Criteria) this;
        }

        public Criteria andResidenceIdLessThan(Integer value) {
            addCriterion("residence_id <", value, "residenceId");
            return (Criteria) this;
        }

        public Criteria andResidenceIdLessThanOrEqualTo(Integer value) {
            addCriterion("residence_id <=", value, "residenceId");
            return (Criteria) this;
        }

        public Criteria andResidenceIdIn(List<Integer> values) {
            addCriterion("residence_id in", values, "residenceId");
            return (Criteria) this;
        }

        public Criteria andResidenceIdNotIn(List<Integer> values) {
            addCriterion("residence_id not in", values, "residenceId");
            return (Criteria) this;
        }

        public Criteria andResidenceIdBetween(Integer value1, Integer value2) {
            addCriterion("residence_id between", value1, value2, "residenceId");
            return (Criteria) this;
        }

        public Criteria andResidenceIdNotBetween(Integer value1, Integer value2) {
            addCriterion("residence_id not between", value1, value2, "residenceId");
            return (Criteria) this;
        }

        public Criteria andResidenceNameIsNull() {
            addCriterion("residence_name is null");
            return (Criteria) this;
        }

        public Criteria andResidenceNameIsNotNull() {
            addCriterion("residence_name is not null");
            return (Criteria) this;
        }

        public Criteria andResidenceNameEqualTo(String value) {
            addCriterion("residence_name =", value, "residenceName");
            return (Criteria) this;
        }

        public Criteria andResidenceNameNotEqualTo(String value) {
            addCriterion("residence_name <>", value, "residenceName");
            return (Criteria) this;
        }

        public Criteria andResidenceNameGreaterThan(String value) {
            addCriterion("residence_name >", value, "residenceName");
            return (Criteria) this;
        }

        public Criteria andResidenceNameGreaterThanOrEqualTo(String value) {
            addCriterion("residence_name >=", value, "residenceName");
            return (Criteria) this;
        }

        public Criteria andResidenceNameLessThan(String value) {
            addCriterion("residence_name <", value, "residenceName");
            return (Criteria) this;
        }

        public Criteria andResidenceNameLessThanOrEqualTo(String value) {
            addCriterion("residence_name <=", value, "residenceName");
            return (Criteria) this;
        }

        public Criteria andResidenceNameLike(String value) {
            addCriterion("residence_name like", value, "residenceName");
            return (Criteria) this;
        }

        public Criteria andResidenceNameNotLike(String value) {
            addCriterion("residence_name not like", value, "residenceName");
            return (Criteria) this;
        }

        public Criteria andResidenceNameIn(List<String> values) {
            addCriterion("residence_name in", values, "residenceName");
            return (Criteria) this;
        }

        public Criteria andResidenceNameNotIn(List<String> values) {
            addCriterion("residence_name not in", values, "residenceName");
            return (Criteria) this;
        }

        public Criteria andResidenceNameBetween(String value1, String value2) {
            addCriterion("residence_name between", value1, value2, "residenceName");
            return (Criteria) this;
        }

        public Criteria andResidenceNameNotBetween(String value1, String value2) {
            addCriterion("residence_name not between", value1, value2, "residenceName");
            return (Criteria) this;
        }

        public Criteria andLimitFeeIsNull() {
            addCriterion("limit_fee is null");
            return (Criteria) this;
        }

        public Criteria andLimitFeeIsNotNull() {
            addCriterion("limit_fee is not null");
            return (Criteria) this;
        }

        public Criteria andLimitFeeEqualTo(Integer value) {
            addCriterion("limit_fee =", value, "limitFee");
            return (Criteria) this;
        }

        public Criteria andLimitFeeNotEqualTo(Integer value) {
            addCriterion("limit_fee <>", value, "limitFee");
            return (Criteria) this;
        }

        public Criteria andLimitFeeGreaterThan(Integer value) {
            addCriterion("limit_fee >", value, "limitFee");
            return (Criteria) this;
        }

        public Criteria andLimitFeeGreaterThanOrEqualTo(Integer value) {
            addCriterion("limit_fee >=", value, "limitFee");
            return (Criteria) this;
        }

        public Criteria andLimitFeeLessThan(Integer value) {
            addCriterion("limit_fee <", value, "limitFee");
            return (Criteria) this;
        }

        public Criteria andLimitFeeLessThanOrEqualTo(Integer value) {
            addCriterion("limit_fee <=", value, "limitFee");
            return (Criteria) this;
        }

        public Criteria andLimitFeeIn(List<Integer> values) {
            addCriterion("limit_fee in", values, "limitFee");
            return (Criteria) this;
        }

        public Criteria andLimitFeeNotIn(List<Integer> values) {
            addCriterion("limit_fee not in", values, "limitFee");
            return (Criteria) this;
        }

        public Criteria andLimitFeeBetween(Integer value1, Integer value2) {
            addCriterion("limit_fee between", value1, value2, "limitFee");
            return (Criteria) this;
        }

        public Criteria andLimitFeeNotBetween(Integer value1, Integer value2) {
            addCriterion("limit_fee not between", value1, value2, "limitFee");
            return (Criteria) this;
        }

        public Criteria andLimitFeeDescIsNull() {
            addCriterion("limit_fee_desc is null");
            return (Criteria) this;
        }

        public Criteria andLimitFeeDescIsNotNull() {
            addCriterion("limit_fee_desc is not null");
            return (Criteria) this;
        }

        public Criteria andLimitFeeDescEqualTo(String value) {
            addCriterion("limit_fee_desc =", value, "limitFeeDesc");
            return (Criteria) this;
        }

        public Criteria andLimitFeeDescNotEqualTo(String value) {
            addCriterion("limit_fee_desc <>", value, "limitFeeDesc");
            return (Criteria) this;
        }

        public Criteria andLimitFeeDescGreaterThan(String value) {
            addCriterion("limit_fee_desc >", value, "limitFeeDesc");
            return (Criteria) this;
        }

        public Criteria andLimitFeeDescGreaterThanOrEqualTo(String value) {
            addCriterion("limit_fee_desc >=", value, "limitFeeDesc");
            return (Criteria) this;
        }

        public Criteria andLimitFeeDescLessThan(String value) {
            addCriterion("limit_fee_desc <", value, "limitFeeDesc");
            return (Criteria) this;
        }

        public Criteria andLimitFeeDescLessThanOrEqualTo(String value) {
            addCriterion("limit_fee_desc <=", value, "limitFeeDesc");
            return (Criteria) this;
        }

        public Criteria andLimitFeeDescLike(String value) {
            addCriterion("limit_fee_desc like", value, "limitFeeDesc");
            return (Criteria) this;
        }

        public Criteria andLimitFeeDescNotLike(String value) {
            addCriterion("limit_fee_desc not like", value, "limitFeeDesc");
            return (Criteria) this;
        }

        public Criteria andLimitFeeDescIn(List<String> values) {
            addCriterion("limit_fee_desc in", values, "limitFeeDesc");
            return (Criteria) this;
        }

        public Criteria andLimitFeeDescNotIn(List<String> values) {
            addCriterion("limit_fee_desc not in", values, "limitFeeDesc");
            return (Criteria) this;
        }

        public Criteria andLimitFeeDescBetween(String value1, String value2) {
            addCriterion("limit_fee_desc between", value1, value2, "limitFeeDesc");
            return (Criteria) this;
        }

        public Criteria andLimitFeeDescNotBetween(String value1, String value2) {
            addCriterion("limit_fee_desc not between", value1, value2, "limitFeeDesc");
            return (Criteria) this;
        }

        public Criteria andFFlagIsNull() {
            addCriterion("f_flag is null");
            return (Criteria) this;
        }

        public Criteria andFFlagIsNotNull() {
            addCriterion("f_flag is not null");
            return (Criteria) this;
        }

        public Criteria andFFlagEqualTo(Byte value) {
            addCriterion("f_flag =", value, "fFlag");
            return (Criteria) this;
        }

        public Criteria andFFlagNotEqualTo(Byte value) {
            addCriterion("f_flag <>", value, "fFlag");
            return (Criteria) this;
        }

        public Criteria andFFlagGreaterThan(Byte value) {
            addCriterion("f_flag >", value, "fFlag");
            return (Criteria) this;
        }

        public Criteria andFFlagGreaterThanOrEqualTo(Byte value) {
            addCriterion("f_flag >=", value, "fFlag");
            return (Criteria) this;
        }

        public Criteria andFFlagLessThan(Byte value) {
            addCriterion("f_flag <", value, "fFlag");
            return (Criteria) this;
        }

        public Criteria andFFlagLessThanOrEqualTo(Byte value) {
            addCriterion("f_flag <=", value, "fFlag");
            return (Criteria) this;
        }

        public Criteria andFFlagIn(List<Byte> values) {
            addCriterion("f_flag in", values, "fFlag");
            return (Criteria) this;
        }

        public Criteria andFFlagNotIn(List<Byte> values) {
            addCriterion("f_flag not in", values, "fFlag");
            return (Criteria) this;
        }

        public Criteria andFFlagBetween(Byte value1, Byte value2) {
            addCriterion("f_flag between", value1, value2, "fFlag");
            return (Criteria) this;
        }

        public Criteria andFFlagNotBetween(Byte value1, Byte value2) {
            addCriterion("f_flag not between", value1, value2, "fFlag");
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

        public Criteria andOrderTypeEqualTo(Byte value) {
            addCriterion("order_type =", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotEqualTo(Byte value) {
            addCriterion("order_type <>", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThan(Byte value) {
            addCriterion("order_type >", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("order_type >=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThan(Byte value) {
            addCriterion("order_type <", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeLessThanOrEqualTo(Byte value) {
            addCriterion("order_type <=", value, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeIn(List<Byte> values) {
            addCriterion("order_type in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotIn(List<Byte> values) {
            addCriterion("order_type not in", values, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeBetween(Byte value1, Byte value2) {
            addCriterion("order_type between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andOrderTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("order_type not between", value1, value2, "orderType");
            return (Criteria) this;
        }

        public Criteria andPresellIdIsNull() {
            addCriterion("presell_id is null");
            return (Criteria) this;
        }

        public Criteria andPresellIdIsNotNull() {
            addCriterion("presell_id is not null");
            return (Criteria) this;
        }

        public Criteria andPresellIdEqualTo(Integer value) {
            addCriterion("presell_id =", value, "presellId");
            return (Criteria) this;
        }

        public Criteria andPresellIdNotEqualTo(Integer value) {
            addCriterion("presell_id <>", value, "presellId");
            return (Criteria) this;
        }

        public Criteria andPresellIdGreaterThan(Integer value) {
            addCriterion("presell_id >", value, "presellId");
            return (Criteria) this;
        }

        public Criteria andPresellIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("presell_id >=", value, "presellId");
            return (Criteria) this;
        }

        public Criteria andPresellIdLessThan(Integer value) {
            addCriterion("presell_id <", value, "presellId");
            return (Criteria) this;
        }

        public Criteria andPresellIdLessThanOrEqualTo(Integer value) {
            addCriterion("presell_id <=", value, "presellId");
            return (Criteria) this;
        }

        public Criteria andPresellIdIn(List<Integer> values) {
            addCriterion("presell_id in", values, "presellId");
            return (Criteria) this;
        }

        public Criteria andPresellIdNotIn(List<Integer> values) {
            addCriterion("presell_id not in", values, "presellId");
            return (Criteria) this;
        }

        public Criteria andPresellIdBetween(Integer value1, Integer value2) {
            addCriterion("presell_id between", value1, value2, "presellId");
            return (Criteria) this;
        }

        public Criteria andPresellIdNotBetween(Integer value1, Integer value2) {
            addCriterion("presell_id not between", value1, value2, "presellId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNull() {
            addCriterion("group_id is null");
            return (Criteria) this;
        }

        public Criteria andGroupIdIsNotNull() {
            addCriterion("group_id is not null");
            return (Criteria) this;
        }

        public Criteria andGroupIdEqualTo(Integer value) {
            addCriterion("group_id =", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotEqualTo(Integer value) {
            addCriterion("group_id <>", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThan(Integer value) {
            addCriterion("group_id >", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("group_id >=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThan(Integer value) {
            addCriterion("group_id <", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdLessThanOrEqualTo(Integer value) {
            addCriterion("group_id <=", value, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdIn(List<Integer> values) {
            addCriterion("group_id in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotIn(List<Integer> values) {
            addCriterion("group_id not in", values, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdBetween(Integer value1, Integer value2) {
            addCriterion("group_id between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andGroupIdNotBetween(Integer value1, Integer value2) {
            addCriterion("group_id not between", value1, value2, "groupId");
            return (Criteria) this;
        }

        public Criteria andTotalVipIsNull() {
            addCriterion("total_vip is null");
            return (Criteria) this;
        }

        public Criteria andTotalVipIsNotNull() {
            addCriterion("total_vip is not null");
            return (Criteria) this;
        }

        public Criteria andTotalVipEqualTo(Integer value) {
            addCriterion("total_vip =", value, "totalVip");
            return (Criteria) this;
        }

        public Criteria andTotalVipNotEqualTo(Integer value) {
            addCriterion("total_vip <>", value, "totalVip");
            return (Criteria) this;
        }

        public Criteria andTotalVipGreaterThan(Integer value) {
            addCriterion("total_vip >", value, "totalVip");
            return (Criteria) this;
        }

        public Criteria andTotalVipGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_vip >=", value, "totalVip");
            return (Criteria) this;
        }

        public Criteria andTotalVipLessThan(Integer value) {
            addCriterion("total_vip <", value, "totalVip");
            return (Criteria) this;
        }

        public Criteria andTotalVipLessThanOrEqualTo(Integer value) {
            addCriterion("total_vip <=", value, "totalVip");
            return (Criteria) this;
        }

        public Criteria andTotalVipIn(List<Integer> values) {
            addCriterion("total_vip in", values, "totalVip");
            return (Criteria) this;
        }

        public Criteria andTotalVipNotIn(List<Integer> values) {
            addCriterion("total_vip not in", values, "totalVip");
            return (Criteria) this;
        }

        public Criteria andTotalVipBetween(Integer value1, Integer value2) {
            addCriterion("total_vip between", value1, value2, "totalVip");
            return (Criteria) this;
        }

        public Criteria andTotalVipNotBetween(Integer value1, Integer value2) {
            addCriterion("total_vip not between", value1, value2, "totalVip");
            return (Criteria) this;
        }

        public Criteria andTotalCouponIsNull() {
            addCriterion("total_coupon is null");
            return (Criteria) this;
        }

        public Criteria andTotalCouponIsNotNull() {
            addCriterion("total_coupon is not null");
            return (Criteria) this;
        }

        public Criteria andTotalCouponEqualTo(Integer value) {
            addCriterion("total_coupon =", value, "totalCoupon");
            return (Criteria) this;
        }

        public Criteria andTotalCouponNotEqualTo(Integer value) {
            addCriterion("total_coupon <>", value, "totalCoupon");
            return (Criteria) this;
        }

        public Criteria andTotalCouponGreaterThan(Integer value) {
            addCriterion("total_coupon >", value, "totalCoupon");
            return (Criteria) this;
        }

        public Criteria andTotalCouponGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_coupon >=", value, "totalCoupon");
            return (Criteria) this;
        }

        public Criteria andTotalCouponLessThan(Integer value) {
            addCriterion("total_coupon <", value, "totalCoupon");
            return (Criteria) this;
        }

        public Criteria andTotalCouponLessThanOrEqualTo(Integer value) {
            addCriterion("total_coupon <=", value, "totalCoupon");
            return (Criteria) this;
        }

        public Criteria andTotalCouponIn(List<Integer> values) {
            addCriterion("total_coupon in", values, "totalCoupon");
            return (Criteria) this;
        }

        public Criteria andTotalCouponNotIn(List<Integer> values) {
            addCriterion("total_coupon not in", values, "totalCoupon");
            return (Criteria) this;
        }

        public Criteria andTotalCouponBetween(Integer value1, Integer value2) {
            addCriterion("total_coupon between", value1, value2, "totalCoupon");
            return (Criteria) this;
        }

        public Criteria andTotalCouponNotBetween(Integer value1, Integer value2) {
            addCriterion("total_coupon not between", value1, value2, "totalCoupon");
            return (Criteria) this;
        }

        public Criteria andTotalFullIsNull() {
            addCriterion("total_full is null");
            return (Criteria) this;
        }

        public Criteria andTotalFullIsNotNull() {
            addCriterion("total_full is not null");
            return (Criteria) this;
        }

        public Criteria andTotalFullEqualTo(Integer value) {
            addCriterion("total_full =", value, "totalFull");
            return (Criteria) this;
        }

        public Criteria andTotalFullNotEqualTo(Integer value) {
            addCriterion("total_full <>", value, "totalFull");
            return (Criteria) this;
        }

        public Criteria andTotalFullGreaterThan(Integer value) {
            addCriterion("total_full >", value, "totalFull");
            return (Criteria) this;
        }

        public Criteria andTotalFullGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_full >=", value, "totalFull");
            return (Criteria) this;
        }

        public Criteria andTotalFullLessThan(Integer value) {
            addCriterion("total_full <", value, "totalFull");
            return (Criteria) this;
        }

        public Criteria andTotalFullLessThanOrEqualTo(Integer value) {
            addCriterion("total_full <=", value, "totalFull");
            return (Criteria) this;
        }

        public Criteria andTotalFullIn(List<Integer> values) {
            addCriterion("total_full in", values, "totalFull");
            return (Criteria) this;
        }

        public Criteria andTotalFullNotIn(List<Integer> values) {
            addCriterion("total_full not in", values, "totalFull");
            return (Criteria) this;
        }

        public Criteria andTotalFullBetween(Integer value1, Integer value2) {
            addCriterion("total_full between", value1, value2, "totalFull");
            return (Criteria) this;
        }

        public Criteria andTotalFullNotBetween(Integer value1, Integer value2) {
            addCriterion("total_full not between", value1, value2, "totalFull");
            return (Criteria) this;
        }

        public Criteria andTotalPointPriceIsNull() {
            addCriterion("total_point_price is null");
            return (Criteria) this;
        }

        public Criteria andTotalPointPriceIsNotNull() {
            addCriterion("total_point_price is not null");
            return (Criteria) this;
        }

        public Criteria andTotalPointPriceEqualTo(Integer value) {
            addCriterion("total_point_price =", value, "totalPointPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPointPriceNotEqualTo(Integer value) {
            addCriterion("total_point_price <>", value, "totalPointPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPointPriceGreaterThan(Integer value) {
            addCriterion("total_point_price >", value, "totalPointPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPointPriceGreaterThanOrEqualTo(Integer value) {
            addCriterion("total_point_price >=", value, "totalPointPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPointPriceLessThan(Integer value) {
            addCriterion("total_point_price <", value, "totalPointPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPointPriceLessThanOrEqualTo(Integer value) {
            addCriterion("total_point_price <=", value, "totalPointPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPointPriceIn(List<Integer> values) {
            addCriterion("total_point_price in", values, "totalPointPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPointPriceNotIn(List<Integer> values) {
            addCriterion("total_point_price not in", values, "totalPointPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPointPriceBetween(Integer value1, Integer value2) {
            addCriterion("total_point_price between", value1, value2, "totalPointPrice");
            return (Criteria) this;
        }

        public Criteria andTotalPointPriceNotBetween(Integer value1, Integer value2) {
            addCriterion("total_point_price not between", value1, value2, "totalPointPrice");
            return (Criteria) this;
        }

        public Criteria andPlatformIsNull() {
            addCriterion("platform is null");
            return (Criteria) this;
        }

        public Criteria andPlatformIsNotNull() {
            addCriterion("platform is not null");
            return (Criteria) this;
        }

        public Criteria andPlatformEqualTo(Integer value) {
            addCriterion("platform =", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotEqualTo(Integer value) {
            addCriterion("platform <>", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformGreaterThan(Integer value) {
            addCriterion("platform >", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformGreaterThanOrEqualTo(Integer value) {
            addCriterion("platform >=", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLessThan(Integer value) {
            addCriterion("platform <", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformLessThanOrEqualTo(Integer value) {
            addCriterion("platform <=", value, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformIn(List<Integer> values) {
            addCriterion("platform in", values, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotIn(List<Integer> values) {
            addCriterion("platform not in", values, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformBetween(Integer value1, Integer value2) {
            addCriterion("platform between", value1, value2, "platform");
            return (Criteria) this;
        }

        public Criteria andPlatformNotBetween(Integer value1, Integer value2) {
            addCriterion("platform not between", value1, value2, "platform");
            return (Criteria) this;
        }

        public Criteria andSplitTypeIsNull() {
            addCriterion("split_type is null");
            return (Criteria) this;
        }

        public Criteria andSplitTypeIsNotNull() {
            addCriterion("split_type is not null");
            return (Criteria) this;
        }

        public Criteria andSplitTypeEqualTo(Byte value) {
            addCriterion("split_type =", value, "splitType");
            return (Criteria) this;
        }

        public Criteria andSplitTypeNotEqualTo(Byte value) {
            addCriterion("split_type <>", value, "splitType");
            return (Criteria) this;
        }

        public Criteria andSplitTypeGreaterThan(Byte value) {
            addCriterion("split_type >", value, "splitType");
            return (Criteria) this;
        }

        public Criteria andSplitTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("split_type >=", value, "splitType");
            return (Criteria) this;
        }

        public Criteria andSplitTypeLessThan(Byte value) {
            addCriterion("split_type <", value, "splitType");
            return (Criteria) this;
        }

        public Criteria andSplitTypeLessThanOrEqualTo(Byte value) {
            addCriterion("split_type <=", value, "splitType");
            return (Criteria) this;
        }

        public Criteria andSplitTypeIn(List<Byte> values) {
            addCriterion("split_type in", values, "splitType");
            return (Criteria) this;
        }

        public Criteria andSplitTypeNotIn(List<Byte> values) {
            addCriterion("split_type not in", values, "splitType");
            return (Criteria) this;
        }

        public Criteria andSplitTypeBetween(Byte value1, Byte value2) {
            addCriterion("split_type between", value1, value2, "splitType");
            return (Criteria) this;
        }

        public Criteria andSplitTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("split_type not between", value1, value2, "splitType");
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