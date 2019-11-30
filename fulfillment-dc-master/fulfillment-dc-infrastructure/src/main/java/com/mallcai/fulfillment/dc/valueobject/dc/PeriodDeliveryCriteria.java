package com.mallcai.fulfillment.dc.valueobject.dc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PeriodDeliveryCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PeriodDeliveryCriteria() {
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

        public Criteria andOrderNoIsNull() {
            addCriterion("order_no is null");
            return (Criteria) this;
        }

        public Criteria andOrderNoIsNotNull() {
            addCriterion("order_no is not null");
            return (Criteria) this;
        }

        public Criteria andOrderNoEqualTo(String value) {
            addCriterion("order_no =", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotEqualTo(String value) {
            addCriterion("order_no <>", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThan(String value) {
            addCriterion("order_no >", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoGreaterThanOrEqualTo(String value) {
            addCriterion("order_no >=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThan(String value) {
            addCriterion("order_no <", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLessThanOrEqualTo(String value) {
            addCriterion("order_no <=", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoLike(String value) {
            addCriterion("order_no like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotLike(String value) {
            addCriterion("order_no not like", value, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoIn(List<String> values) {
            addCriterion("order_no in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotIn(List<String> values) {
            addCriterion("order_no not in", values, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoBetween(String value1, String value2) {
            addCriterion("order_no between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andOrderNoNotBetween(String value1, String value2) {
            addCriterion("order_no not between", value1, value2, "orderNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoIsNull() {
            addCriterion("serial_no is null");
            return (Criteria) this;
        }

        public Criteria andSerialNoIsNotNull() {
            addCriterion("serial_no is not null");
            return (Criteria) this;
        }

        public Criteria andSerialNoEqualTo(String value) {
            addCriterion("serial_no =", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoNotEqualTo(String value) {
            addCriterion("serial_no <>", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoGreaterThan(String value) {
            addCriterion("serial_no >", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoGreaterThanOrEqualTo(String value) {
            addCriterion("serial_no >=", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoLessThan(String value) {
            addCriterion("serial_no <", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoLessThanOrEqualTo(String value) {
            addCriterion("serial_no <=", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoLike(String value) {
            addCriterion("serial_no like", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoNotLike(String value) {
            addCriterion("serial_no not like", value, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoIn(List<String> values) {
            addCriterion("serial_no in", values, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoNotIn(List<String> values) {
            addCriterion("serial_no not in", values, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoBetween(String value1, String value2) {
            addCriterion("serial_no between", value1, value2, "serialNo");
            return (Criteria) this;
        }

        public Criteria andSerialNoNotBetween(String value1, String value2) {
            addCriterion("serial_no not between", value1, value2, "serialNo");
            return (Criteria) this;
        }

        public Criteria andPdtIdIsNull() {
            addCriterion("pdt_id is null");
            return (Criteria) this;
        }

        public Criteria andPdtIdIsNotNull() {
            addCriterion("pdt_id is not null");
            return (Criteria) this;
        }

        public Criteria andPdtIdEqualTo(String value) {
            addCriterion("pdt_id =", value, "pdtId");
            return (Criteria) this;
        }

        public Criteria andPdtIdNotEqualTo(String value) {
            addCriterion("pdt_id <>", value, "pdtId");
            return (Criteria) this;
        }

        public Criteria andPdtIdGreaterThan(String value) {
            addCriterion("pdt_id >", value, "pdtId");
            return (Criteria) this;
        }

        public Criteria andPdtIdGreaterThanOrEqualTo(String value) {
            addCriterion("pdt_id >=", value, "pdtId");
            return (Criteria) this;
        }

        public Criteria andPdtIdLessThan(String value) {
            addCriterion("pdt_id <", value, "pdtId");
            return (Criteria) this;
        }

        public Criteria andPdtIdLessThanOrEqualTo(String value) {
            addCriterion("pdt_id <=", value, "pdtId");
            return (Criteria) this;
        }

        public Criteria andPdtIdLike(String value) {
            addCriterion("pdt_id like", value, "pdtId");
            return (Criteria) this;
        }

        public Criteria andPdtIdNotLike(String value) {
            addCriterion("pdt_id not like", value, "pdtId");
            return (Criteria) this;
        }

        public Criteria andPdtIdIn(List<String> values) {
            addCriterion("pdt_id in", values, "pdtId");
            return (Criteria) this;
        }

        public Criteria andPdtIdNotIn(List<String> values) {
            addCriterion("pdt_id not in", values, "pdtId");
            return (Criteria) this;
        }

        public Criteria andPdtIdBetween(String value1, String value2) {
            addCriterion("pdt_id between", value1, value2, "pdtId");
            return (Criteria) this;
        }

        public Criteria andPdtIdNotBetween(String value1, String value2) {
            addCriterion("pdt_id not between", value1, value2, "pdtId");
            return (Criteria) this;
        }

        public Criteria andProdAmountIsNull() {
            addCriterion("prod_amount is null");
            return (Criteria) this;
        }

        public Criteria andProdAmountIsNotNull() {
            addCriterion("prod_amount is not null");
            return (Criteria) this;
        }

        public Criteria andProdAmountEqualTo(Integer value) {
            addCriterion("prod_amount =", value, "prodAmount");
            return (Criteria) this;
        }

        public Criteria andProdAmountNotEqualTo(Integer value) {
            addCriterion("prod_amount <>", value, "prodAmount");
            return (Criteria) this;
        }

        public Criteria andProdAmountGreaterThan(Integer value) {
            addCriterion("prod_amount >", value, "prodAmount");
            return (Criteria) this;
        }

        public Criteria andProdAmountGreaterThanOrEqualTo(Integer value) {
            addCriterion("prod_amount >=", value, "prodAmount");
            return (Criteria) this;
        }

        public Criteria andProdAmountLessThan(Integer value) {
            addCriterion("prod_amount <", value, "prodAmount");
            return (Criteria) this;
        }

        public Criteria andProdAmountLessThanOrEqualTo(Integer value) {
            addCriterion("prod_amount <=", value, "prodAmount");
            return (Criteria) this;
        }

        public Criteria andProdAmountIn(List<Integer> values) {
            addCriterion("prod_amount in", values, "prodAmount");
            return (Criteria) this;
        }

        public Criteria andProdAmountNotIn(List<Integer> values) {
            addCriterion("prod_amount not in", values, "prodAmount");
            return (Criteria) this;
        }

        public Criteria andProdAmountBetween(Integer value1, Integer value2) {
            addCriterion("prod_amount between", value1, value2, "prodAmount");
            return (Criteria) this;
        }

        public Criteria andProdAmountNotBetween(Integer value1, Integer value2) {
            addCriterion("prod_amount not between", value1, value2, "prodAmount");
            return (Criteria) this;
        }

        public Criteria andItemIsNull() {
            addCriterion("item is null");
            return (Criteria) this;
        }

        public Criteria andItemIsNotNull() {
            addCriterion("item is not null");
            return (Criteria) this;
        }

        public Criteria andItemEqualTo(Integer value) {
            addCriterion("item =", value, "item");
            return (Criteria) this;
        }

        public Criteria andItemNotEqualTo(Integer value) {
            addCriterion("item <>", value, "item");
            return (Criteria) this;
        }

        public Criteria andItemGreaterThan(Integer value) {
            addCriterion("item >", value, "item");
            return (Criteria) this;
        }

        public Criteria andItemGreaterThanOrEqualTo(Integer value) {
            addCriterion("item >=", value, "item");
            return (Criteria) this;
        }

        public Criteria andItemLessThan(Integer value) {
            addCriterion("item <", value, "item");
            return (Criteria) this;
        }

        public Criteria andItemLessThanOrEqualTo(Integer value) {
            addCriterion("item <=", value, "item");
            return (Criteria) this;
        }

        public Criteria andItemIn(List<Integer> values) {
            addCriterion("item in", values, "item");
            return (Criteria) this;
        }

        public Criteria andItemNotIn(List<Integer> values) {
            addCriterion("item not in", values, "item");
            return (Criteria) this;
        }

        public Criteria andItemBetween(Integer value1, Integer value2) {
            addCriterion("item between", value1, value2, "item");
            return (Criteria) this;
        }

        public Criteria andItemNotBetween(Integer value1, Integer value2) {
            addCriterion("item not between", value1, value2, "item");
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

        public Criteria andDeliveryDateIsNull() {
            addCriterion("delivery_date is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateIsNotNull() {
            addCriterion("delivery_date is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateEqualTo(Date value) {
            addCriterion("delivery_date =", value, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateNotEqualTo(Date value) {
            addCriterion("delivery_date <>", value, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateGreaterThan(Date value) {
            addCriterion("delivery_date >", value, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateGreaterThanOrEqualTo(Date value) {
            addCriterion("delivery_date >=", value, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateLessThan(Date value) {
            addCriterion("delivery_date <", value, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateLessThanOrEqualTo(Date value) {
            addCriterion("delivery_date <=", value, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateIn(List<Date> values) {
            addCriterion("delivery_date in", values, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateNotIn(List<Date> values) {
            addCriterion("delivery_date not in", values, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateBetween(Date value1, Date value2) {
            addCriterion("delivery_date between", value1, value2, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryDateNotBetween(Date value1, Date value2) {
            addCriterion("delivery_date not between", value1, value2, "deliveryDate");
            return (Criteria) this;
        }

        public Criteria andDeliveryManIsNull() {
            addCriterion("delivery_man is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryManIsNotNull() {
            addCriterion("delivery_man is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryManEqualTo(String value) {
            addCriterion("delivery_man =", value, "deliveryMan");
            return (Criteria) this;
        }

        public Criteria andDeliveryManNotEqualTo(String value) {
            addCriterion("delivery_man <>", value, "deliveryMan");
            return (Criteria) this;
        }

        public Criteria andDeliveryManGreaterThan(String value) {
            addCriterion("delivery_man >", value, "deliveryMan");
            return (Criteria) this;
        }

        public Criteria andDeliveryManGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_man >=", value, "deliveryMan");
            return (Criteria) this;
        }

        public Criteria andDeliveryManLessThan(String value) {
            addCriterion("delivery_man <", value, "deliveryMan");
            return (Criteria) this;
        }

        public Criteria andDeliveryManLessThanOrEqualTo(String value) {
            addCriterion("delivery_man <=", value, "deliveryMan");
            return (Criteria) this;
        }

        public Criteria andDeliveryManLike(String value) {
            addCriterion("delivery_man like", value, "deliveryMan");
            return (Criteria) this;
        }

        public Criteria andDeliveryManNotLike(String value) {
            addCriterion("delivery_man not like", value, "deliveryMan");
            return (Criteria) this;
        }

        public Criteria andDeliveryManIn(List<String> values) {
            addCriterion("delivery_man in", values, "deliveryMan");
            return (Criteria) this;
        }

        public Criteria andDeliveryManNotIn(List<String> values) {
            addCriterion("delivery_man not in", values, "deliveryMan");
            return (Criteria) this;
        }

        public Criteria andDeliveryManBetween(String value1, String value2) {
            addCriterion("delivery_man between", value1, value2, "deliveryMan");
            return (Criteria) this;
        }

        public Criteria andDeliveryManNotBetween(String value1, String value2) {
            addCriterion("delivery_man not between", value1, value2, "deliveryMan");
            return (Criteria) this;
        }

        public Criteria andDeliveryManPhoneIsNull() {
            addCriterion("delivery_man_phone is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryManPhoneIsNotNull() {
            addCriterion("delivery_man_phone is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryManPhoneEqualTo(String value) {
            addCriterion("delivery_man_phone =", value, "deliveryManPhone");
            return (Criteria) this;
        }

        public Criteria andDeliveryManPhoneNotEqualTo(String value) {
            addCriterion("delivery_man_phone <>", value, "deliveryManPhone");
            return (Criteria) this;
        }

        public Criteria andDeliveryManPhoneGreaterThan(String value) {
            addCriterion("delivery_man_phone >", value, "deliveryManPhone");
            return (Criteria) this;
        }

        public Criteria andDeliveryManPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("delivery_man_phone >=", value, "deliveryManPhone");
            return (Criteria) this;
        }

        public Criteria andDeliveryManPhoneLessThan(String value) {
            addCriterion("delivery_man_phone <", value, "deliveryManPhone");
            return (Criteria) this;
        }

        public Criteria andDeliveryManPhoneLessThanOrEqualTo(String value) {
            addCriterion("delivery_man_phone <=", value, "deliveryManPhone");
            return (Criteria) this;
        }

        public Criteria andDeliveryManPhoneLike(String value) {
            addCriterion("delivery_man_phone like", value, "deliveryManPhone");
            return (Criteria) this;
        }

        public Criteria andDeliveryManPhoneNotLike(String value) {
            addCriterion("delivery_man_phone not like", value, "deliveryManPhone");
            return (Criteria) this;
        }

        public Criteria andDeliveryManPhoneIn(List<String> values) {
            addCriterion("delivery_man_phone in", values, "deliveryManPhone");
            return (Criteria) this;
        }

        public Criteria andDeliveryManPhoneNotIn(List<String> values) {
            addCriterion("delivery_man_phone not in", values, "deliveryManPhone");
            return (Criteria) this;
        }

        public Criteria andDeliveryManPhoneBetween(String value1, String value2) {
            addCriterion("delivery_man_phone between", value1, value2, "deliveryManPhone");
            return (Criteria) this;
        }

        public Criteria andDeliveryManPhoneNotBetween(String value1, String value2) {
            addCriterion("delivery_man_phone not between", value1, value2, "deliveryManPhone");
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