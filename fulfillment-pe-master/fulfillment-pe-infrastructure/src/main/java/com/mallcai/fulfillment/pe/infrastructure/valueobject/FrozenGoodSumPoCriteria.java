package com.mallcai.fulfillment.pe.infrastructure.valueobject;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FrozenGoodSumPoCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FrozenGoodSumPoCriteria() {
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

        public Criteria andGoodIdIsNull() {
            addCriterion("good_id is null");
            return (Criteria) this;
        }

        public Criteria andGoodIdIsNotNull() {
            addCriterion("good_id is not null");
            return (Criteria) this;
        }

        public Criteria andGoodIdEqualTo(Integer value) {
            addCriterion("good_id =", value, "goodId");
            return (Criteria) this;
        }

        public Criteria andGoodIdNotEqualTo(Integer value) {
            addCriterion("good_id <>", value, "goodId");
            return (Criteria) this;
        }

        public Criteria andGoodIdGreaterThan(Integer value) {
            addCriterion("good_id >", value, "goodId");
            return (Criteria) this;
        }

        public Criteria andGoodIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("good_id >=", value, "goodId");
            return (Criteria) this;
        }

        public Criteria andGoodIdLessThan(Integer value) {
            addCriterion("good_id <", value, "goodId");
            return (Criteria) this;
        }

        public Criteria andGoodIdLessThanOrEqualTo(Integer value) {
            addCriterion("good_id <=", value, "goodId");
            return (Criteria) this;
        }

        public Criteria andGoodIdIn(List<Integer> values) {
            addCriterion("good_id in", values, "goodId");
            return (Criteria) this;
        }

        public Criteria andGoodIdNotIn(List<Integer> values) {
            addCriterion("good_id not in", values, "goodId");
            return (Criteria) this;
        }

        public Criteria andGoodIdBetween(Integer value1, Integer value2) {
            addCriterion("good_id between", value1, value2, "goodId");
            return (Criteria) this;
        }

        public Criteria andGoodIdNotBetween(Integer value1, Integer value2) {
            addCriterion("good_id not between", value1, value2, "goodId");
            return (Criteria) this;
        }

        public Criteria andGoodSumIsNull() {
            addCriterion("good_sum is null");
            return (Criteria) this;
        }

        public Criteria andGoodSumIsNotNull() {
            addCriterion("good_sum is not null");
            return (Criteria) this;
        }

        public Criteria andGoodSumEqualTo(BigDecimal value) {
            addCriterion("good_sum =", value, "goodSum");
            return (Criteria) this;
        }

        public Criteria andGoodSumNotEqualTo(BigDecimal value) {
            addCriterion("good_sum <>", value, "goodSum");
            return (Criteria) this;
        }

        public Criteria andGoodSumGreaterThan(BigDecimal value) {
            addCriterion("good_sum >", value, "goodSum");
            return (Criteria) this;
        }

        public Criteria andGoodSumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("good_sum >=", value, "goodSum");
            return (Criteria) this;
        }

        public Criteria andGoodSumLessThan(BigDecimal value) {
            addCriterion("good_sum <", value, "goodSum");
            return (Criteria) this;
        }

        public Criteria andGoodSumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("good_sum <=", value, "goodSum");
            return (Criteria) this;
        }

        public Criteria andGoodSumIn(List<BigDecimal> values) {
            addCriterion("good_sum in", values, "goodSum");
            return (Criteria) this;
        }

        public Criteria andGoodSumNotIn(List<BigDecimal> values) {
            addCriterion("good_sum not in", values, "goodSum");
            return (Criteria) this;
        }

        public Criteria andGoodSumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("good_sum between", value1, value2, "goodSum");
            return (Criteria) this;
        }

        public Criteria andGoodSumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("good_sum not between", value1, value2, "goodSum");
            return (Criteria) this;
        }

        public Criteria andSumDateIsNull() {
            addCriterion("sum_date is null");
            return (Criteria) this;
        }

        public Criteria andSumDateIsNotNull() {
            addCriterion("sum_date is not null");
            return (Criteria) this;
        }

        public Criteria andSumDateEqualTo(String value) {
            addCriterion("sum_date =", value, "sumDate");
            return (Criteria) this;
        }

        public Criteria andSumDateNotEqualTo(String value) {
            addCriterion("sum_date <>", value, "sumDate");
            return (Criteria) this;
        }

        public Criteria andSumDateGreaterThan(String value) {
            addCriterion("sum_date >", value, "sumDate");
            return (Criteria) this;
        }

        public Criteria andSumDateGreaterThanOrEqualTo(String value) {
            addCriterion("sum_date >=", value, "sumDate");
            return (Criteria) this;
        }

        public Criteria andSumDateLessThan(String value) {
            addCriterion("sum_date <", value, "sumDate");
            return (Criteria) this;
        }

        public Criteria andSumDateLessThanOrEqualTo(String value) {
            addCriterion("sum_date <=", value, "sumDate");
            return (Criteria) this;
        }

        public Criteria andSumDateLike(String value) {
            addCriterion("sum_date like", value, "sumDate");
            return (Criteria) this;
        }

        public Criteria andSumDateNotLike(String value) {
            addCriterion("sum_date not like", value, "sumDate");
            return (Criteria) this;
        }

        public Criteria andSumDateIn(List<String> values) {
            addCriterion("sum_date in", values, "sumDate");
            return (Criteria) this;
        }

        public Criteria andSumDateNotIn(List<String> values) {
            addCriterion("sum_date not in", values, "sumDate");
            return (Criteria) this;
        }

        public Criteria andSumDateBetween(String value1, String value2) {
            addCriterion("sum_date between", value1, value2, "sumDate");
            return (Criteria) this;
        }

        public Criteria andSumDateNotBetween(String value1, String value2) {
            addCriterion("sum_date not between", value1, value2, "sumDate");
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

        public Criteria andProductSumIsNull() {
            addCriterion("product_sum is null");
            return (Criteria) this;
        }

        public Criteria andProductSumIsNotNull() {
            addCriterion("product_sum is not null");
            return (Criteria) this;
        }

        public Criteria andProductSumEqualTo(BigDecimal value) {
            addCriterion("product_sum =", value, "productSum");
            return (Criteria) this;
        }

        public Criteria andProductSumNotEqualTo(BigDecimal value) {
            addCriterion("product_sum <>", value, "productSum");
            return (Criteria) this;
        }

        public Criteria andProductSumGreaterThan(BigDecimal value) {
            addCriterion("product_sum >", value, "productSum");
            return (Criteria) this;
        }

        public Criteria andProductSumGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("product_sum >=", value, "productSum");
            return (Criteria) this;
        }

        public Criteria andProductSumLessThan(BigDecimal value) {
            addCriterion("product_sum <", value, "productSum");
            return (Criteria) this;
        }

        public Criteria andProductSumLessThanOrEqualTo(BigDecimal value) {
            addCriterion("product_sum <=", value, "productSum");
            return (Criteria) this;
        }

        public Criteria andProductSumIn(List<BigDecimal> values) {
            addCriterion("product_sum in", values, "productSum");
            return (Criteria) this;
        }

        public Criteria andProductSumNotIn(List<BigDecimal> values) {
            addCriterion("product_sum not in", values, "productSum");
            return (Criteria) this;
        }

        public Criteria andProductSumBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_sum between", value1, value2, "productSum");
            return (Criteria) this;
        }

        public Criteria andProductSumNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("product_sum not between", value1, value2, "productSum");
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