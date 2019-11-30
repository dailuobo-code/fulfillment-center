package com.mallcai.fulfillment.dc.valueobject.dc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PeriodBuyRecordCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public PeriodBuyRecordCriteria() {
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

        public Criteria andStatusMsgIsNull() {
            addCriterion("status_msg is null");
            return (Criteria) this;
        }

        public Criteria andStatusMsgIsNotNull() {
            addCriterion("status_msg is not null");
            return (Criteria) this;
        }

        public Criteria andStatusMsgEqualTo(String value) {
            addCriterion("status_msg =", value, "statusMsg");
            return (Criteria) this;
        }

        public Criteria andStatusMsgNotEqualTo(String value) {
            addCriterion("status_msg <>", value, "statusMsg");
            return (Criteria) this;
        }

        public Criteria andStatusMsgGreaterThan(String value) {
            addCriterion("status_msg >", value, "statusMsg");
            return (Criteria) this;
        }

        public Criteria andStatusMsgGreaterThanOrEqualTo(String value) {
            addCriterion("status_msg >=", value, "statusMsg");
            return (Criteria) this;
        }

        public Criteria andStatusMsgLessThan(String value) {
            addCriterion("status_msg <", value, "statusMsg");
            return (Criteria) this;
        }

        public Criteria andStatusMsgLessThanOrEqualTo(String value) {
            addCriterion("status_msg <=", value, "statusMsg");
            return (Criteria) this;
        }

        public Criteria andStatusMsgLike(String value) {
            addCriterion("status_msg like", value, "statusMsg");
            return (Criteria) this;
        }

        public Criteria andStatusMsgNotLike(String value) {
            addCriterion("status_msg not like", value, "statusMsg");
            return (Criteria) this;
        }

        public Criteria andStatusMsgIn(List<String> values) {
            addCriterion("status_msg in", values, "statusMsg");
            return (Criteria) this;
        }

        public Criteria andStatusMsgNotIn(List<String> values) {
            addCriterion("status_msg not in", values, "statusMsg");
            return (Criteria) this;
        }

        public Criteria andStatusMsgBetween(String value1, String value2) {
            addCriterion("status_msg between", value1, value2, "statusMsg");
            return (Criteria) this;
        }

        public Criteria andStatusMsgNotBetween(String value1, String value2) {
            addCriterion("status_msg not between", value1, value2, "statusMsg");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNull() {
            addCriterion("user_name is null");
            return (Criteria) this;
        }

        public Criteria andUserNameIsNotNull() {
            addCriterion("user_name is not null");
            return (Criteria) this;
        }

        public Criteria andUserNameEqualTo(String value) {
            addCriterion("user_name =", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotEqualTo(String value) {
            addCriterion("user_name <>", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThan(String value) {
            addCriterion("user_name >", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("user_name >=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThan(String value) {
            addCriterion("user_name <", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("user_name <=", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameLike(String value) {
            addCriterion("user_name like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotLike(String value) {
            addCriterion("user_name not like", value, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameIn(List<String> values) {
            addCriterion("user_name in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotIn(List<String> values) {
            addCriterion("user_name not in", values, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameBetween(String value1, String value2) {
            addCriterion("user_name between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("user_name not between", value1, value2, "userName");
            return (Criteria) this;
        }

        public Criteria andUserPhone1IsNull() {
            addCriterion("user_phone1 is null");
            return (Criteria) this;
        }

        public Criteria andUserPhone1IsNotNull() {
            addCriterion("user_phone1 is not null");
            return (Criteria) this;
        }

        public Criteria andUserPhone1EqualTo(String value) {
            addCriterion("user_phone1 =", value, "userPhone1");
            return (Criteria) this;
        }

        public Criteria andUserPhone1NotEqualTo(String value) {
            addCriterion("user_phone1 <>", value, "userPhone1");
            return (Criteria) this;
        }

        public Criteria andUserPhone1GreaterThan(String value) {
            addCriterion("user_phone1 >", value, "userPhone1");
            return (Criteria) this;
        }

        public Criteria andUserPhone1GreaterThanOrEqualTo(String value) {
            addCriterion("user_phone1 >=", value, "userPhone1");
            return (Criteria) this;
        }

        public Criteria andUserPhone1LessThan(String value) {
            addCriterion("user_phone1 <", value, "userPhone1");
            return (Criteria) this;
        }

        public Criteria andUserPhone1LessThanOrEqualTo(String value) {
            addCriterion("user_phone1 <=", value, "userPhone1");
            return (Criteria) this;
        }

        public Criteria andUserPhone1Like(String value) {
            addCriterion("user_phone1 like", value, "userPhone1");
            return (Criteria) this;
        }

        public Criteria andUserPhone1NotLike(String value) {
            addCriterion("user_phone1 not like", value, "userPhone1");
            return (Criteria) this;
        }

        public Criteria andUserPhone1In(List<String> values) {
            addCriterion("user_phone1 in", values, "userPhone1");
            return (Criteria) this;
        }

        public Criteria andUserPhone1NotIn(List<String> values) {
            addCriterion("user_phone1 not in", values, "userPhone1");
            return (Criteria) this;
        }

        public Criteria andUserPhone1Between(String value1, String value2) {
            addCriterion("user_phone1 between", value1, value2, "userPhone1");
            return (Criteria) this;
        }

        public Criteria andUserPhone1NotBetween(String value1, String value2) {
            addCriterion("user_phone1 not between", value1, value2, "userPhone1");
            return (Criteria) this;
        }

        public Criteria andUserPhone2IsNull() {
            addCriterion("user_phone2 is null");
            return (Criteria) this;
        }

        public Criteria andUserPhone2IsNotNull() {
            addCriterion("user_phone2 is not null");
            return (Criteria) this;
        }

        public Criteria andUserPhone2EqualTo(String value) {
            addCriterion("user_phone2 =", value, "userPhone2");
            return (Criteria) this;
        }

        public Criteria andUserPhone2NotEqualTo(String value) {
            addCriterion("user_phone2 <>", value, "userPhone2");
            return (Criteria) this;
        }

        public Criteria andUserPhone2GreaterThan(String value) {
            addCriterion("user_phone2 >", value, "userPhone2");
            return (Criteria) this;
        }

        public Criteria andUserPhone2GreaterThanOrEqualTo(String value) {
            addCriterion("user_phone2 >=", value, "userPhone2");
            return (Criteria) this;
        }

        public Criteria andUserPhone2LessThan(String value) {
            addCriterion("user_phone2 <", value, "userPhone2");
            return (Criteria) this;
        }

        public Criteria andUserPhone2LessThanOrEqualTo(String value) {
            addCriterion("user_phone2 <=", value, "userPhone2");
            return (Criteria) this;
        }

        public Criteria andUserPhone2Like(String value) {
            addCriterion("user_phone2 like", value, "userPhone2");
            return (Criteria) this;
        }

        public Criteria andUserPhone2NotLike(String value) {
            addCriterion("user_phone2 not like", value, "userPhone2");
            return (Criteria) this;
        }

        public Criteria andUserPhone2In(List<String> values) {
            addCriterion("user_phone2 in", values, "userPhone2");
            return (Criteria) this;
        }

        public Criteria andUserPhone2NotIn(List<String> values) {
            addCriterion("user_phone2 not in", values, "userPhone2");
            return (Criteria) this;
        }

        public Criteria andUserPhone2Between(String value1, String value2) {
            addCriterion("user_phone2 between", value1, value2, "userPhone2");
            return (Criteria) this;
        }

        public Criteria andUserPhone2NotBetween(String value1, String value2) {
            addCriterion("user_phone2 not between", value1, value2, "userPhone2");
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

        public Criteria andExternProdIdIsNull() {
            addCriterion("extern_prod_id is null");
            return (Criteria) this;
        }

        public Criteria andExternProdIdIsNotNull() {
            addCriterion("extern_prod_id is not null");
            return (Criteria) this;
        }

        public Criteria andExternProdIdEqualTo(String value) {
            addCriterion("extern_prod_id =", value, "externProdId");
            return (Criteria) this;
        }

        public Criteria andExternProdIdNotEqualTo(String value) {
            addCriterion("extern_prod_id <>", value, "externProdId");
            return (Criteria) this;
        }

        public Criteria andExternProdIdGreaterThan(String value) {
            addCriterion("extern_prod_id >", value, "externProdId");
            return (Criteria) this;
        }

        public Criteria andExternProdIdGreaterThanOrEqualTo(String value) {
            addCriterion("extern_prod_id >=", value, "externProdId");
            return (Criteria) this;
        }

        public Criteria andExternProdIdLessThan(String value) {
            addCriterion("extern_prod_id <", value, "externProdId");
            return (Criteria) this;
        }

        public Criteria andExternProdIdLessThanOrEqualTo(String value) {
            addCriterion("extern_prod_id <=", value, "externProdId");
            return (Criteria) this;
        }

        public Criteria andExternProdIdLike(String value) {
            addCriterion("extern_prod_id like", value, "externProdId");
            return (Criteria) this;
        }

        public Criteria andExternProdIdNotLike(String value) {
            addCriterion("extern_prod_id not like", value, "externProdId");
            return (Criteria) this;
        }

        public Criteria andExternProdIdIn(List<String> values) {
            addCriterion("extern_prod_id in", values, "externProdId");
            return (Criteria) this;
        }

        public Criteria andExternProdIdNotIn(List<String> values) {
            addCriterion("extern_prod_id not in", values, "externProdId");
            return (Criteria) this;
        }

        public Criteria andExternProdIdBetween(String value1, String value2) {
            addCriterion("extern_prod_id between", value1, value2, "externProdId");
            return (Criteria) this;
        }

        public Criteria andExternProdIdNotBetween(String value1, String value2) {
            addCriterion("extern_prod_id not between", value1, value2, "externProdId");
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

        public Criteria andStartTimeIsNull() {
            addCriterion("start_time is null");
            return (Criteria) this;
        }

        public Criteria andStartTimeIsNotNull() {
            addCriterion("start_time is not null");
            return (Criteria) this;
        }

        public Criteria andStartTimeEqualTo(Date value) {
            addCriterion("start_time =", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotEqualTo(Date value) {
            addCriterion("start_time <>", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThan(Date value) {
            addCriterion("start_time >", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("start_time >=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThan(Date value) {
            addCriterion("start_time <", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeLessThanOrEqualTo(Date value) {
            addCriterion("start_time <=", value, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeIn(List<Date> values) {
            addCriterion("start_time in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotIn(List<Date> values) {
            addCriterion("start_time not in", values, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeBetween(Date value1, Date value2) {
            addCriterion("start_time between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andStartTimeNotBetween(Date value1, Date value2) {
            addCriterion("start_time not between", value1, value2, "startTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNull() {
            addCriterion("end_time is null");
            return (Criteria) this;
        }

        public Criteria andEndTimeIsNotNull() {
            addCriterion("end_time is not null");
            return (Criteria) this;
        }

        public Criteria andEndTimeEqualTo(Date value) {
            addCriterion("end_time =", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotEqualTo(Date value) {
            addCriterion("end_time <>", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThan(Date value) {
            addCriterion("end_time >", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("end_time >=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThan(Date value) {
            addCriterion("end_time <", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeLessThanOrEqualTo(Date value) {
            addCriterion("end_time <=", value, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeIn(List<Date> values) {
            addCriterion("end_time in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotIn(List<Date> values) {
            addCriterion("end_time not in", values, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeBetween(Date value1, Date value2) {
            addCriterion("end_time between", value1, value2, "endTime");
            return (Criteria) this;
        }

        public Criteria andEndTimeNotBetween(Date value1, Date value2) {
            addCriterion("end_time not between", value1, value2, "endTime");
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

        public Criteria andBookRuleIsNull() {
            addCriterion("book_rule is null");
            return (Criteria) this;
        }

        public Criteria andBookRuleIsNotNull() {
            addCriterion("book_rule is not null");
            return (Criteria) this;
        }

        public Criteria andBookRuleEqualTo(String value) {
            addCriterion("book_rule =", value, "bookRule");
            return (Criteria) this;
        }

        public Criteria andBookRuleNotEqualTo(String value) {
            addCriterion("book_rule <>", value, "bookRule");
            return (Criteria) this;
        }

        public Criteria andBookRuleGreaterThan(String value) {
            addCriterion("book_rule >", value, "bookRule");
            return (Criteria) this;
        }

        public Criteria andBookRuleGreaterThanOrEqualTo(String value) {
            addCriterion("book_rule >=", value, "bookRule");
            return (Criteria) this;
        }

        public Criteria andBookRuleLessThan(String value) {
            addCriterion("book_rule <", value, "bookRule");
            return (Criteria) this;
        }

        public Criteria andBookRuleLessThanOrEqualTo(String value) {
            addCriterion("book_rule <=", value, "bookRule");
            return (Criteria) this;
        }

        public Criteria andBookRuleLike(String value) {
            addCriterion("book_rule like", value, "bookRule");
            return (Criteria) this;
        }

        public Criteria andBookRuleNotLike(String value) {
            addCriterion("book_rule not like", value, "bookRule");
            return (Criteria) this;
        }

        public Criteria andBookRuleIn(List<String> values) {
            addCriterion("book_rule in", values, "bookRule");
            return (Criteria) this;
        }

        public Criteria andBookRuleNotIn(List<String> values) {
            addCriterion("book_rule not in", values, "bookRule");
            return (Criteria) this;
        }

        public Criteria andBookRuleBetween(String value1, String value2) {
            addCriterion("book_rule between", value1, value2, "bookRule");
            return (Criteria) this;
        }

        public Criteria andBookRuleNotBetween(String value1, String value2) {
            addCriterion("book_rule not between", value1, value2, "bookRule");
            return (Criteria) this;
        }

        public Criteria andCancelTimeIsNull() {
            addCriterion("cancel_time is null");
            return (Criteria) this;
        }

        public Criteria andCancelTimeIsNotNull() {
            addCriterion("cancel_time is not null");
            return (Criteria) this;
        }

        public Criteria andCancelTimeEqualTo(Date value) {
            addCriterion("cancel_time =", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeNotEqualTo(Date value) {
            addCriterion("cancel_time <>", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeGreaterThan(Date value) {
            addCriterion("cancel_time >", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("cancel_time >=", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeLessThan(Date value) {
            addCriterion("cancel_time <", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeLessThanOrEqualTo(Date value) {
            addCriterion("cancel_time <=", value, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeIn(List<Date> values) {
            addCriterion("cancel_time in", values, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeNotIn(List<Date> values) {
            addCriterion("cancel_time not in", values, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeBetween(Date value1, Date value2) {
            addCriterion("cancel_time between", value1, value2, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andCancelTimeNotBetween(Date value1, Date value2) {
            addCriterion("cancel_time not between", value1, value2, "cancelTime");
            return (Criteria) this;
        }

        public Criteria andRealEndDateIsNull() {
            addCriterion("real_end_date is null");
            return (Criteria) this;
        }

        public Criteria andRealEndDateIsNotNull() {
            addCriterion("real_end_date is not null");
            return (Criteria) this;
        }

        public Criteria andRealEndDateEqualTo(Date value) {
            addCriterion("real_end_date =", value, "realEndDate");
            return (Criteria) this;
        }

        public Criteria andRealEndDateNotEqualTo(Date value) {
            addCriterion("real_end_date <>", value, "realEndDate");
            return (Criteria) this;
        }

        public Criteria andRealEndDateGreaterThan(Date value) {
            addCriterion("real_end_date >", value, "realEndDate");
            return (Criteria) this;
        }

        public Criteria andRealEndDateGreaterThanOrEqualTo(Date value) {
            addCriterion("real_end_date >=", value, "realEndDate");
            return (Criteria) this;
        }

        public Criteria andRealEndDateLessThan(Date value) {
            addCriterion("real_end_date <", value, "realEndDate");
            return (Criteria) this;
        }

        public Criteria andRealEndDateLessThanOrEqualTo(Date value) {
            addCriterion("real_end_date <=", value, "realEndDate");
            return (Criteria) this;
        }

        public Criteria andRealEndDateIn(List<Date> values) {
            addCriterion("real_end_date in", values, "realEndDate");
            return (Criteria) this;
        }

        public Criteria andRealEndDateNotIn(List<Date> values) {
            addCriterion("real_end_date not in", values, "realEndDate");
            return (Criteria) this;
        }

        public Criteria andRealEndDateBetween(Date value1, Date value2) {
            addCriterion("real_end_date between", value1, value2, "realEndDate");
            return (Criteria) this;
        }

        public Criteria andRealEndDateNotBetween(Date value1, Date value2) {
            addCriterion("real_end_date not between", value1, value2, "realEndDate");
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