package com.mallcai.fulfillment.dc.valueobject.bigData;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class WarehouseSalesForecastCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public WarehouseSalesForecastCriteria() {
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

        public Criteria andLv1ClassifyTypeIsNull() {
            addCriterion("lv1_classify_type is null");
            return (Criteria) this;
        }

        public Criteria andLv1ClassifyTypeIsNotNull() {
            addCriterion("lv1_classify_type is not null");
            return (Criteria) this;
        }

        public Criteria andLv1ClassifyTypeEqualTo(Integer value) {
            addCriterion("lv1_classify_type =", value, "lv1ClassifyType");
            return (Criteria) this;
        }

        public Criteria andLv1ClassifyTypeNotEqualTo(Integer value) {
            addCriterion("lv1_classify_type <>", value, "lv1ClassifyType");
            return (Criteria) this;
        }

        public Criteria andLv1ClassifyTypeGreaterThan(Integer value) {
            addCriterion("lv1_classify_type >", value, "lv1ClassifyType");
            return (Criteria) this;
        }

        public Criteria andLv1ClassifyTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("lv1_classify_type >=", value, "lv1ClassifyType");
            return (Criteria) this;
        }

        public Criteria andLv1ClassifyTypeLessThan(Integer value) {
            addCriterion("lv1_classify_type <", value, "lv1ClassifyType");
            return (Criteria) this;
        }

        public Criteria andLv1ClassifyTypeLessThanOrEqualTo(Integer value) {
            addCriterion("lv1_classify_type <=", value, "lv1ClassifyType");
            return (Criteria) this;
        }

        public Criteria andLv1ClassifyTypeIn(List<Integer> values) {
            addCriterion("lv1_classify_type in", values, "lv1ClassifyType");
            return (Criteria) this;
        }

        public Criteria andLv1ClassifyTypeNotIn(List<Integer> values) {
            addCriterion("lv1_classify_type not in", values, "lv1ClassifyType");
            return (Criteria) this;
        }

        public Criteria andLv1ClassifyTypeBetween(Integer value1, Integer value2) {
            addCriterion("lv1_classify_type between", value1, value2, "lv1ClassifyType");
            return (Criteria) this;
        }

        public Criteria andLv1ClassifyTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("lv1_classify_type not between", value1, value2, "lv1ClassifyType");
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

        public Criteria andForecastQtyIsNull() {
            addCriterion("forecast_qty is null");
            return (Criteria) this;
        }

        public Criteria andForecastQtyIsNotNull() {
            addCriterion("forecast_qty is not null");
            return (Criteria) this;
        }

        public Criteria andForecastQtyEqualTo(Integer value) {
            addCriterion("forecast_qty =", value, "forecastQty");
            return (Criteria) this;
        }

        public Criteria andForecastQtyNotEqualTo(Integer value) {
            addCriterion("forecast_qty <>", value, "forecastQty");
            return (Criteria) this;
        }

        public Criteria andForecastQtyGreaterThan(Integer value) {
            addCriterion("forecast_qty >", value, "forecastQty");
            return (Criteria) this;
        }

        public Criteria andForecastQtyGreaterThanOrEqualTo(Integer value) {
            addCriterion("forecast_qty >=", value, "forecastQty");
            return (Criteria) this;
        }

        public Criteria andForecastQtyLessThan(Integer value) {
            addCriterion("forecast_qty <", value, "forecastQty");
            return (Criteria) this;
        }

        public Criteria andForecastQtyLessThanOrEqualTo(Integer value) {
            addCriterion("forecast_qty <=", value, "forecastQty");
            return (Criteria) this;
        }

        public Criteria andForecastQtyIn(List<Integer> values) {
            addCriterion("forecast_qty in", values, "forecastQty");
            return (Criteria) this;
        }

        public Criteria andForecastQtyNotIn(List<Integer> values) {
            addCriterion("forecast_qty not in", values, "forecastQty");
            return (Criteria) this;
        }

        public Criteria andForecastQtyBetween(Integer value1, Integer value2) {
            addCriterion("forecast_qty between", value1, value2, "forecastQty");
            return (Criteria) this;
        }

        public Criteria andForecastQtyNotBetween(Integer value1, Integer value2) {
            addCriterion("forecast_qty not between", value1, value2, "forecastQty");
            return (Criteria) this;
        }

        public Criteria andForecastDateIsNull() {
            addCriterion("forecast_date is null");
            return (Criteria) this;
        }

        public Criteria andForecastDateIsNotNull() {
            addCriterion("forecast_date is not null");
            return (Criteria) this;
        }

        public Criteria andForecastDateEqualTo(Date value) {
            addCriterionForJDBCDate("forecast_date =", value, "forecastDate");
            return (Criteria) this;
        }

        public Criteria andForecastDateNotEqualTo(Date value) {
            addCriterionForJDBCDate("forecast_date <>", value, "forecastDate");
            return (Criteria) this;
        }

        public Criteria andForecastDateGreaterThan(Date value) {
            addCriterionForJDBCDate("forecast_date >", value, "forecastDate");
            return (Criteria) this;
        }

        public Criteria andForecastDateGreaterThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("forecast_date >=", value, "forecastDate");
            return (Criteria) this;
        }

        public Criteria andForecastDateLessThan(Date value) {
            addCriterionForJDBCDate("forecast_date <", value, "forecastDate");
            return (Criteria) this;
        }

        public Criteria andForecastDateLessThanOrEqualTo(Date value) {
            addCriterionForJDBCDate("forecast_date <=", value, "forecastDate");
            return (Criteria) this;
        }

        public Criteria andForecastDateIn(List<Date> values) {
            addCriterionForJDBCDate("forecast_date in", values, "forecastDate");
            return (Criteria) this;
        }

        public Criteria andForecastDateNotIn(List<Date> values) {
            addCriterionForJDBCDate("forecast_date not in", values, "forecastDate");
            return (Criteria) this;
        }

        public Criteria andForecastDateBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("forecast_date between", value1, value2, "forecastDate");
            return (Criteria) this;
        }

        public Criteria andForecastDateNotBetween(Date value1, Date value2) {
            addCriterionForJDBCDate("forecast_date not between", value1, value2, "forecastDate");
            return (Criteria) this;
        }

        public Criteria andForecastTypeIsNull() {
            addCriterion("forecast_type is null");
            return (Criteria) this;
        }

        public Criteria andForecastTypeIsNotNull() {
            addCriterion("forecast_type is not null");
            return (Criteria) this;
        }

        public Criteria andForecastTypeEqualTo(Byte value) {
            addCriterion("forecast_type =", value, "forecastType");
            return (Criteria) this;
        }

        public Criteria andForecastTypeNotEqualTo(Byte value) {
            addCriterion("forecast_type <>", value, "forecastType");
            return (Criteria) this;
        }

        public Criteria andForecastTypeGreaterThan(Byte value) {
            addCriterion("forecast_type >", value, "forecastType");
            return (Criteria) this;
        }

        public Criteria andForecastTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("forecast_type >=", value, "forecastType");
            return (Criteria) this;
        }

        public Criteria andForecastTypeLessThan(Byte value) {
            addCriterion("forecast_type <", value, "forecastType");
            return (Criteria) this;
        }

        public Criteria andForecastTypeLessThanOrEqualTo(Byte value) {
            addCriterion("forecast_type <=", value, "forecastType");
            return (Criteria) this;
        }

        public Criteria andForecastTypeIn(List<Byte> values) {
            addCriterion("forecast_type in", values, "forecastType");
            return (Criteria) this;
        }

        public Criteria andForecastTypeNotIn(List<Byte> values) {
            addCriterion("forecast_type not in", values, "forecastType");
            return (Criteria) this;
        }

        public Criteria andForecastTypeBetween(Byte value1, Byte value2) {
            addCriterion("forecast_type between", value1, value2, "forecastType");
            return (Criteria) this;
        }

        public Criteria andForecastTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("forecast_type not between", value1, value2, "forecastType");
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