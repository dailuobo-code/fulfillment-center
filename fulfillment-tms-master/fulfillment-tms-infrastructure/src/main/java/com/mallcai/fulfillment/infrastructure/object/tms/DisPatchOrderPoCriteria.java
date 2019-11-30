package com.mallcai.fulfillment.infrastructure.object.tms;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DisPatchOrderPoCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DisPatchOrderPoCriteria() {
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

        public Criteria andDispatchNoIsNull() {
            addCriterion("dispatch_no is null");
            return (Criteria) this;
        }

        public Criteria andDispatchNoIsNotNull() {
            addCriterion("dispatch_no is not null");
            return (Criteria) this;
        }

        public Criteria andDispatchNoEqualTo(String value) {
            addCriterion("dispatch_no =", value, "dispatchNo");
            return (Criteria) this;
        }

        public Criteria andDispatchNoNotEqualTo(String value) {
            addCriterion("dispatch_no <>", value, "dispatchNo");
            return (Criteria) this;
        }

        public Criteria andDispatchNoGreaterThan(String value) {
            addCriterion("dispatch_no >", value, "dispatchNo");
            return (Criteria) this;
        }

        public Criteria andDispatchNoGreaterThanOrEqualTo(String value) {
            addCriterion("dispatch_no >=", value, "dispatchNo");
            return (Criteria) this;
        }

        public Criteria andDispatchNoLessThan(String value) {
            addCriterion("dispatch_no <", value, "dispatchNo");
            return (Criteria) this;
        }

        public Criteria andDispatchNoLessThanOrEqualTo(String value) {
            addCriterion("dispatch_no <=", value, "dispatchNo");
            return (Criteria) this;
        }

        public Criteria andDispatchNoLike(String value) {
            addCriterion("dispatch_no like", value, "dispatchNo");
            return (Criteria) this;
        }

        public Criteria andDispatchNoNotLike(String value) {
            addCriterion("dispatch_no not like", value, "dispatchNo");
            return (Criteria) this;
        }

        public Criteria andDispatchNoIn(List<String> values) {
            addCriterion("dispatch_no in", values, "dispatchNo");
            return (Criteria) this;
        }

        public Criteria andDispatchNoNotIn(List<String> values) {
            addCriterion("dispatch_no not in", values, "dispatchNo");
            return (Criteria) this;
        }

        public Criteria andDispatchNoBetween(String value1, String value2) {
            addCriterion("dispatch_no between", value1, value2, "dispatchNo");
            return (Criteria) this;
        }

        public Criteria andDispatchNoNotBetween(String value1, String value2) {
            addCriterion("dispatch_no not between", value1, value2, "dispatchNo");
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

        public Criteria andOutbondTypeIsNull() {
            addCriterion("outbond_type is null");
            return (Criteria) this;
        }

        public Criteria andOutbondTypeIsNotNull() {
            addCriterion("outbond_type is not null");
            return (Criteria) this;
        }

        public Criteria andOutbondTypeEqualTo(String value) {
            addCriterion("outbond_type =", value, "outbondType");
            return (Criteria) this;
        }

        public Criteria andOutbondTypeNotEqualTo(String value) {
            addCriterion("outbond_type <>", value, "outbondType");
            return (Criteria) this;
        }

        public Criteria andOutbondTypeGreaterThan(String value) {
            addCriterion("outbond_type >", value, "outbondType");
            return (Criteria) this;
        }

        public Criteria andOutbondTypeGreaterThanOrEqualTo(String value) {
            addCriterion("outbond_type >=", value, "outbondType");
            return (Criteria) this;
        }

        public Criteria andOutbondTypeLessThan(String value) {
            addCriterion("outbond_type <", value, "outbondType");
            return (Criteria) this;
        }

        public Criteria andOutbondTypeLessThanOrEqualTo(String value) {
            addCriterion("outbond_type <=", value, "outbondType");
            return (Criteria) this;
        }

        public Criteria andOutbondTypeLike(String value) {
            addCriterion("outbond_type like", value, "outbondType");
            return (Criteria) this;
        }

        public Criteria andOutbondTypeNotLike(String value) {
            addCriterion("outbond_type not like", value, "outbondType");
            return (Criteria) this;
        }

        public Criteria andOutbondTypeIn(List<String> values) {
            addCriterion("outbond_type in", values, "outbondType");
            return (Criteria) this;
        }

        public Criteria andOutbondTypeNotIn(List<String> values) {
            addCriterion("outbond_type not in", values, "outbondType");
            return (Criteria) this;
        }

        public Criteria andOutbondTypeBetween(String value1, String value2) {
            addCriterion("outbond_type between", value1, value2, "outbondType");
            return (Criteria) this;
        }

        public Criteria andOutbondTypeNotBetween(String value1, String value2) {
            addCriterion("outbond_type not between", value1, value2, "outbondType");
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