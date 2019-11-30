package com.mallcai.fulfillment.infrastructure.object.tms;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransportLinePoCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TransportLinePoCriteria() {
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

        public Criteria andPlanNoIsNull() {
            addCriterion("plan_no is null");
            return (Criteria) this;
        }

        public Criteria andPlanNoIsNotNull() {
            addCriterion("plan_no is not null");
            return (Criteria) this;
        }

        public Criteria andPlanNoEqualTo(String value) {
            addCriterion("plan_no =", value, "planNo");
            return (Criteria) this;
        }

        public Criteria andPlanNoNotEqualTo(String value) {
            addCriterion("plan_no <>", value, "planNo");
            return (Criteria) this;
        }

        public Criteria andPlanNoGreaterThan(String value) {
            addCriterion("plan_no >", value, "planNo");
            return (Criteria) this;
        }

        public Criteria andPlanNoGreaterThanOrEqualTo(String value) {
            addCriterion("plan_no >=", value, "planNo");
            return (Criteria) this;
        }

        public Criteria andPlanNoLessThan(String value) {
            addCriterion("plan_no <", value, "planNo");
            return (Criteria) this;
        }

        public Criteria andPlanNoLessThanOrEqualTo(String value) {
            addCriterion("plan_no <=", value, "planNo");
            return (Criteria) this;
        }

        public Criteria andPlanNoLike(String value) {
            addCriterion("plan_no like", value, "planNo");
            return (Criteria) this;
        }

        public Criteria andPlanNoNotLike(String value) {
            addCriterion("plan_no not like", value, "planNo");
            return (Criteria) this;
        }

        public Criteria andPlanNoIn(List<String> values) {
            addCriterion("plan_no in", values, "planNo");
            return (Criteria) this;
        }

        public Criteria andPlanNoNotIn(List<String> values) {
            addCriterion("plan_no not in", values, "planNo");
            return (Criteria) this;
        }

        public Criteria andPlanNoBetween(String value1, String value2) {
            addCriterion("plan_no between", value1, value2, "planNo");
            return (Criteria) this;
        }

        public Criteria andPlanNoNotBetween(String value1, String value2) {
            addCriterion("plan_no not between", value1, value2, "planNo");
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

        public Criteria andOrgCodeIsNull() {
            addCriterion("org_code is null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIsNotNull() {
            addCriterion("org_code is not null");
            return (Criteria) this;
        }

        public Criteria andOrgCodeEqualTo(String value) {
            addCriterion("org_code =", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotEqualTo(String value) {
            addCriterion("org_code <>", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThan(String value) {
            addCriterion("org_code >", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeGreaterThanOrEqualTo(String value) {
            addCriterion("org_code >=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThan(String value) {
            addCriterion("org_code <", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLessThanOrEqualTo(String value) {
            addCriterion("org_code <=", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeLike(String value) {
            addCriterion("org_code like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotLike(String value) {
            addCriterion("org_code not like", value, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeIn(List<String> values) {
            addCriterion("org_code in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotIn(List<String> values) {
            addCriterion("org_code not in", values, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeBetween(String value1, String value2) {
            addCriterion("org_code between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andOrgCodeNotBetween(String value1, String value2) {
            addCriterion("org_code not between", value1, value2, "orgCode");
            return (Criteria) this;
        }

        public Criteria andSequenceIsNull() {
            addCriterion("sequence is null");
            return (Criteria) this;
        }

        public Criteria andSequenceIsNotNull() {
            addCriterion("sequence is not null");
            return (Criteria) this;
        }

        public Criteria andSequenceEqualTo(String value) {
            addCriterion("sequence =", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceNotEqualTo(String value) {
            addCriterion("sequence <>", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceGreaterThan(String value) {
            addCriterion("sequence >", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceGreaterThanOrEqualTo(String value) {
            addCriterion("sequence >=", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceLessThan(String value) {
            addCriterion("sequence <", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceLessThanOrEqualTo(String value) {
            addCriterion("sequence <=", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceLike(String value) {
            addCriterion("sequence like", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceNotLike(String value) {
            addCriterion("sequence not like", value, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceIn(List<String> values) {
            addCriterion("sequence in", values, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceNotIn(List<String> values) {
            addCriterion("sequence not in", values, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceBetween(String value1, String value2) {
            addCriterion("sequence between", value1, value2, "sequence");
            return (Criteria) this;
        }

        public Criteria andSequenceNotBetween(String value1, String value2) {
            addCriterion("sequence not between", value1, value2, "sequence");
            return (Criteria) this;
        }

        public Criteria andMinArriveIsNull() {
            addCriterion("min_arrive is null");
            return (Criteria) this;
        }

        public Criteria andMinArriveIsNotNull() {
            addCriterion("min_arrive is not null");
            return (Criteria) this;
        }

        public Criteria andMinArriveEqualTo(Date value) {
            addCriterion("min_arrive =", value, "minArrive");
            return (Criteria) this;
        }

        public Criteria andMinArriveNotEqualTo(Date value) {
            addCriterion("min_arrive <>", value, "minArrive");
            return (Criteria) this;
        }

        public Criteria andMinArriveGreaterThan(Date value) {
            addCriterion("min_arrive >", value, "minArrive");
            return (Criteria) this;
        }

        public Criteria andMinArriveGreaterThanOrEqualTo(Date value) {
            addCriterion("min_arrive >=", value, "minArrive");
            return (Criteria) this;
        }

        public Criteria andMinArriveLessThan(Date value) {
            addCriterion("min_arrive <", value, "minArrive");
            return (Criteria) this;
        }

        public Criteria andMinArriveLessThanOrEqualTo(Date value) {
            addCriterion("min_arrive <=", value, "minArrive");
            return (Criteria) this;
        }

        public Criteria andMinArriveIn(List<Date> values) {
            addCriterion("min_arrive in", values, "minArrive");
            return (Criteria) this;
        }

        public Criteria andMinArriveNotIn(List<Date> values) {
            addCriterion("min_arrive not in", values, "minArrive");
            return (Criteria) this;
        }

        public Criteria andMinArriveBetween(Date value1, Date value2) {
            addCriterion("min_arrive between", value1, value2, "minArrive");
            return (Criteria) this;
        }

        public Criteria andMinArriveNotBetween(Date value1, Date value2) {
            addCriterion("min_arrive not between", value1, value2, "minArrive");
            return (Criteria) this;
        }

        public Criteria andMaxArriveIsNull() {
            addCriterion("max_arrive is null");
            return (Criteria) this;
        }

        public Criteria andMaxArriveIsNotNull() {
            addCriterion("max_arrive is not null");
            return (Criteria) this;
        }

        public Criteria andMaxArriveEqualTo(Date value) {
            addCriterion("max_arrive =", value, "maxArrive");
            return (Criteria) this;
        }

        public Criteria andMaxArriveNotEqualTo(Date value) {
            addCriterion("max_arrive <>", value, "maxArrive");
            return (Criteria) this;
        }

        public Criteria andMaxArriveGreaterThan(Date value) {
            addCriterion("max_arrive >", value, "maxArrive");
            return (Criteria) this;
        }

        public Criteria andMaxArriveGreaterThanOrEqualTo(Date value) {
            addCriterion("max_arrive >=", value, "maxArrive");
            return (Criteria) this;
        }

        public Criteria andMaxArriveLessThan(Date value) {
            addCriterion("max_arrive <", value, "maxArrive");
            return (Criteria) this;
        }

        public Criteria andMaxArriveLessThanOrEqualTo(Date value) {
            addCriterion("max_arrive <=", value, "maxArrive");
            return (Criteria) this;
        }

        public Criteria andMaxArriveIn(List<Date> values) {
            addCriterion("max_arrive in", values, "maxArrive");
            return (Criteria) this;
        }

        public Criteria andMaxArriveNotIn(List<Date> values) {
            addCriterion("max_arrive not in", values, "maxArrive");
            return (Criteria) this;
        }

        public Criteria andMaxArriveBetween(Date value1, Date value2) {
            addCriterion("max_arrive between", value1, value2, "maxArrive");
            return (Criteria) this;
        }

        public Criteria andMaxArriveNotBetween(Date value1, Date value2) {
            addCriterion("max_arrive not between", value1, value2, "maxArrive");
            return (Criteria) this;
        }

        public Criteria andMinLeaveIsNull() {
            addCriterion("min_leave is null");
            return (Criteria) this;
        }

        public Criteria andMinLeaveIsNotNull() {
            addCriterion("min_leave is not null");
            return (Criteria) this;
        }

        public Criteria andMinLeaveEqualTo(Date value) {
            addCriterion("min_leave =", value, "minLeave");
            return (Criteria) this;
        }

        public Criteria andMinLeaveNotEqualTo(Date value) {
            addCriterion("min_leave <>", value, "minLeave");
            return (Criteria) this;
        }

        public Criteria andMinLeaveGreaterThan(Date value) {
            addCriterion("min_leave >", value, "minLeave");
            return (Criteria) this;
        }

        public Criteria andMinLeaveGreaterThanOrEqualTo(Date value) {
            addCriterion("min_leave >=", value, "minLeave");
            return (Criteria) this;
        }

        public Criteria andMinLeaveLessThan(Date value) {
            addCriterion("min_leave <", value, "minLeave");
            return (Criteria) this;
        }

        public Criteria andMinLeaveLessThanOrEqualTo(Date value) {
            addCriterion("min_leave <=", value, "minLeave");
            return (Criteria) this;
        }

        public Criteria andMinLeaveIn(List<Date> values) {
            addCriterion("min_leave in", values, "minLeave");
            return (Criteria) this;
        }

        public Criteria andMinLeaveNotIn(List<Date> values) {
            addCriterion("min_leave not in", values, "minLeave");
            return (Criteria) this;
        }

        public Criteria andMinLeaveBetween(Date value1, Date value2) {
            addCriterion("min_leave between", value1, value2, "minLeave");
            return (Criteria) this;
        }

        public Criteria andMinLeaveNotBetween(Date value1, Date value2) {
            addCriterion("min_leave not between", value1, value2, "minLeave");
            return (Criteria) this;
        }

        public Criteria andMaxLeaveIsNull() {
            addCriterion("max_leave is null");
            return (Criteria) this;
        }

        public Criteria andMaxLeaveIsNotNull() {
            addCriterion("max_leave is not null");
            return (Criteria) this;
        }

        public Criteria andMaxLeaveEqualTo(Date value) {
            addCriterion("max_leave =", value, "maxLeave");
            return (Criteria) this;
        }

        public Criteria andMaxLeaveNotEqualTo(Date value) {
            addCriterion("max_leave <>", value, "maxLeave");
            return (Criteria) this;
        }

        public Criteria andMaxLeaveGreaterThan(Date value) {
            addCriterion("max_leave >", value, "maxLeave");
            return (Criteria) this;
        }

        public Criteria andMaxLeaveGreaterThanOrEqualTo(Date value) {
            addCriterion("max_leave >=", value, "maxLeave");
            return (Criteria) this;
        }

        public Criteria andMaxLeaveLessThan(Date value) {
            addCriterion("max_leave <", value, "maxLeave");
            return (Criteria) this;
        }

        public Criteria andMaxLeaveLessThanOrEqualTo(Date value) {
            addCriterion("max_leave <=", value, "maxLeave");
            return (Criteria) this;
        }

        public Criteria andMaxLeaveIn(List<Date> values) {
            addCriterion("max_leave in", values, "maxLeave");
            return (Criteria) this;
        }

        public Criteria andMaxLeaveNotIn(List<Date> values) {
            addCriterion("max_leave not in", values, "maxLeave");
            return (Criteria) this;
        }

        public Criteria andMaxLeaveBetween(Date value1, Date value2) {
            addCriterion("max_leave between", value1, value2, "maxLeave");
            return (Criteria) this;
        }

        public Criteria andMaxLeaveNotBetween(Date value1, Date value2) {
            addCriterion("max_leave not between", value1, value2, "maxLeave");
            return (Criteria) this;
        }

        public Criteria andRealArriveIsNull() {
            addCriterion("real_arrive is null");
            return (Criteria) this;
        }

        public Criteria andRealArriveIsNotNull() {
            addCriterion("real_arrive is not null");
            return (Criteria) this;
        }

        public Criteria andRealArriveEqualTo(Date value) {
            addCriterion("real_arrive =", value, "realArrive");
            return (Criteria) this;
        }

        public Criteria andRealArriveNotEqualTo(Date value) {
            addCriterion("real_arrive <>", value, "realArrive");
            return (Criteria) this;
        }

        public Criteria andRealArriveGreaterThan(Date value) {
            addCriterion("real_arrive >", value, "realArrive");
            return (Criteria) this;
        }

        public Criteria andRealArriveGreaterThanOrEqualTo(Date value) {
            addCriterion("real_arrive >=", value, "realArrive");
            return (Criteria) this;
        }

        public Criteria andRealArriveLessThan(Date value) {
            addCriterion("real_arrive <", value, "realArrive");
            return (Criteria) this;
        }

        public Criteria andRealArriveLessThanOrEqualTo(Date value) {
            addCriterion("real_arrive <=", value, "realArrive");
            return (Criteria) this;
        }

        public Criteria andRealArriveIn(List<Date> values) {
            addCriterion("real_arrive in", values, "realArrive");
            return (Criteria) this;
        }

        public Criteria andRealArriveNotIn(List<Date> values) {
            addCriterion("real_arrive not in", values, "realArrive");
            return (Criteria) this;
        }

        public Criteria andRealArriveBetween(Date value1, Date value2) {
            addCriterion("real_arrive between", value1, value2, "realArrive");
            return (Criteria) this;
        }

        public Criteria andRealArriveNotBetween(Date value1, Date value2) {
            addCriterion("real_arrive not between", value1, value2, "realArrive");
            return (Criteria) this;
        }

        public Criteria andRealLeaveIsNull() {
            addCriterion("real_leave is null");
            return (Criteria) this;
        }

        public Criteria andRealLeaveIsNotNull() {
            addCriterion("real_leave is not null");
            return (Criteria) this;
        }

        public Criteria andRealLeaveEqualTo(Date value) {
            addCriterion("real_leave =", value, "realLeave");
            return (Criteria) this;
        }

        public Criteria andRealLeaveNotEqualTo(Date value) {
            addCriterion("real_leave <>", value, "realLeave");
            return (Criteria) this;
        }

        public Criteria andRealLeaveGreaterThan(Date value) {
            addCriterion("real_leave >", value, "realLeave");
            return (Criteria) this;
        }

        public Criteria andRealLeaveGreaterThanOrEqualTo(Date value) {
            addCriterion("real_leave >=", value, "realLeave");
            return (Criteria) this;
        }

        public Criteria andRealLeaveLessThan(Date value) {
            addCriterion("real_leave <", value, "realLeave");
            return (Criteria) this;
        }

        public Criteria andRealLeaveLessThanOrEqualTo(Date value) {
            addCriterion("real_leave <=", value, "realLeave");
            return (Criteria) this;
        }

        public Criteria andRealLeaveIn(List<Date> values) {
            addCriterion("real_leave in", values, "realLeave");
            return (Criteria) this;
        }

        public Criteria andRealLeaveNotIn(List<Date> values) {
            addCriterion("real_leave not in", values, "realLeave");
            return (Criteria) this;
        }

        public Criteria andRealLeaveBetween(Date value1, Date value2) {
            addCriterion("real_leave between", value1, value2, "realLeave");
            return (Criteria) this;
        }

        public Criteria andRealLeaveNotBetween(Date value1, Date value2) {
            addCriterion("real_leave not between", value1, value2, "realLeave");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmtIsNull() {
            addCriterion("delivery_amt is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmtIsNotNull() {
            addCriterion("delivery_amt is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmtEqualTo(Integer value) {
            addCriterion("delivery_amt =", value, "deliveryAmt");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmtNotEqualTo(Integer value) {
            addCriterion("delivery_amt <>", value, "deliveryAmt");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmtGreaterThan(Integer value) {
            addCriterion("delivery_amt >", value, "deliveryAmt");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmtGreaterThanOrEqualTo(Integer value) {
            addCriterion("delivery_amt >=", value, "deliveryAmt");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmtLessThan(Integer value) {
            addCriterion("delivery_amt <", value, "deliveryAmt");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmtLessThanOrEqualTo(Integer value) {
            addCriterion("delivery_amt <=", value, "deliveryAmt");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmtIn(List<Integer> values) {
            addCriterion("delivery_amt in", values, "deliveryAmt");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmtNotIn(List<Integer> values) {
            addCriterion("delivery_amt not in", values, "deliveryAmt");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmtBetween(Integer value1, Integer value2) {
            addCriterion("delivery_amt between", value1, value2, "deliveryAmt");
            return (Criteria) this;
        }

        public Criteria andDeliveryAmtNotBetween(Integer value1, Integer value2) {
            addCriterion("delivery_amt not between", value1, value2, "deliveryAmt");
            return (Criteria) this;
        }

        public Criteria andDeliveryIsNull() {
            addCriterion("delivery is null");
            return (Criteria) this;
        }

        public Criteria andDeliveryIsNotNull() {
            addCriterion("delivery is not null");
            return (Criteria) this;
        }

        public Criteria andDeliveryEqualTo(Boolean value) {
            addCriterion("delivery =", value, "delivery");
            return (Criteria) this;
        }

        public Criteria andDeliveryNotEqualTo(Boolean value) {
            addCriterion("delivery <>", value, "delivery");
            return (Criteria) this;
        }

        public Criteria andDeliveryGreaterThan(Boolean value) {
            addCriterion("delivery >", value, "delivery");
            return (Criteria) this;
        }

        public Criteria andDeliveryGreaterThanOrEqualTo(Boolean value) {
            addCriterion("delivery >=", value, "delivery");
            return (Criteria) this;
        }

        public Criteria andDeliveryLessThan(Boolean value) {
            addCriterion("delivery <", value, "delivery");
            return (Criteria) this;
        }

        public Criteria andDeliveryLessThanOrEqualTo(Boolean value) {
            addCriterion("delivery <=", value, "delivery");
            return (Criteria) this;
        }

        public Criteria andDeliveryIn(List<Boolean> values) {
            addCriterion("delivery in", values, "delivery");
            return (Criteria) this;
        }

        public Criteria andDeliveryNotIn(List<Boolean> values) {
            addCriterion("delivery not in", values, "delivery");
            return (Criteria) this;
        }

        public Criteria andDeliveryBetween(Boolean value1, Boolean value2) {
            addCriterion("delivery between", value1, value2, "delivery");
            return (Criteria) this;
        }

        public Criteria andDeliveryNotBetween(Boolean value1, Boolean value2) {
            addCriterion("delivery not between", value1, value2, "delivery");
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

        public Criteria andUpdateByIsNull() {
            addCriterion("update_by is null");
            return (Criteria) this;
        }

        public Criteria andUpdateByIsNotNull() {
            addCriterion("update_by is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateByEqualTo(String value) {
            addCriterion("update_by =", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotEqualTo(String value) {
            addCriterion("update_by <>", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThan(String value) {
            addCriterion("update_by >", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByGreaterThanOrEqualTo(String value) {
            addCriterion("update_by >=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThan(String value) {
            addCriterion("update_by <", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLessThanOrEqualTo(String value) {
            addCriterion("update_by <=", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByLike(String value) {
            addCriterion("update_by like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotLike(String value) {
            addCriterion("update_by not like", value, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByIn(List<String> values) {
            addCriterion("update_by in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotIn(List<String> values) {
            addCriterion("update_by not in", values, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByBetween(String value1, String value2) {
            addCriterion("update_by between", value1, value2, "updateBy");
            return (Criteria) this;
        }

        public Criteria andUpdateByNotBetween(String value1, String value2) {
            addCriterion("update_by not between", value1, value2, "updateBy");
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