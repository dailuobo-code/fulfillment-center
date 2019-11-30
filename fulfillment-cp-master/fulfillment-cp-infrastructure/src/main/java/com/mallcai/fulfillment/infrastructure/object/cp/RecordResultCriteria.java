package com.mallcai.fulfillment.infrastructure.object.cp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RecordResultCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public RecordResultCriteria() {
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

        public Criteria andCheckNoIsNull() {
            addCriterion("check_no is null");
            return (Criteria) this;
        }

        public Criteria andCheckNoIsNotNull() {
            addCriterion("check_no is not null");
            return (Criteria) this;
        }

        public Criteria andCheckNoEqualTo(String value) {
            addCriterion("check_no =", value, "checkNo");
            return (Criteria) this;
        }

        public Criteria andCheckNoNotEqualTo(String value) {
            addCriterion("check_no <>", value, "checkNo");
            return (Criteria) this;
        }

        public Criteria andCheckNoGreaterThan(String value) {
            addCriterion("check_no >", value, "checkNo");
            return (Criteria) this;
        }

        public Criteria andCheckNoGreaterThanOrEqualTo(String value) {
            addCriterion("check_no >=", value, "checkNo");
            return (Criteria) this;
        }

        public Criteria andCheckNoLessThan(String value) {
            addCriterion("check_no <", value, "checkNo");
            return (Criteria) this;
        }

        public Criteria andCheckNoLessThanOrEqualTo(String value) {
            addCriterion("check_no <=", value, "checkNo");
            return (Criteria) this;
        }

        public Criteria andCheckNoLike(String value) {
            addCriterion("check_no like", value, "checkNo");
            return (Criteria) this;
        }

        public Criteria andCheckNoNotLike(String value) {
            addCriterion("check_no not like", value, "checkNo");
            return (Criteria) this;
        }

        public Criteria andCheckNoIn(List<String> values) {
            addCriterion("check_no in", values, "checkNo");
            return (Criteria) this;
        }

        public Criteria andCheckNoNotIn(List<String> values) {
            addCriterion("check_no not in", values, "checkNo");
            return (Criteria) this;
        }

        public Criteria andCheckNoBetween(String value1, String value2) {
            addCriterion("check_no between", value1, value2, "checkNo");
            return (Criteria) this;
        }

        public Criteria andCheckNoNotBetween(String value1, String value2) {
            addCriterion("check_no not between", value1, value2, "checkNo");
            return (Criteria) this;
        }

        public Criteria andBizTagIsNull() {
            addCriterion("biz_tag is null");
            return (Criteria) this;
        }

        public Criteria andBizTagIsNotNull() {
            addCriterion("biz_tag is not null");
            return (Criteria) this;
        }

        public Criteria andBizTagEqualTo(String value) {
            addCriterion("biz_tag =", value, "bizTag");
            return (Criteria) this;
        }

        public Criteria andBizTagNotEqualTo(String value) {
            addCriterion("biz_tag <>", value, "bizTag");
            return (Criteria) this;
        }

        public Criteria andBizTagGreaterThan(String value) {
            addCriterion("biz_tag >", value, "bizTag");
            return (Criteria) this;
        }

        public Criteria andBizTagGreaterThanOrEqualTo(String value) {
            addCriterion("biz_tag >=", value, "bizTag");
            return (Criteria) this;
        }

        public Criteria andBizTagLessThan(String value) {
            addCriterion("biz_tag <", value, "bizTag");
            return (Criteria) this;
        }

        public Criteria andBizTagLessThanOrEqualTo(String value) {
            addCriterion("biz_tag <=", value, "bizTag");
            return (Criteria) this;
        }

        public Criteria andBizTagLike(String value) {
            addCriterion("biz_tag like", value, "bizTag");
            return (Criteria) this;
        }

        public Criteria andBizTagNotLike(String value) {
            addCriterion("biz_tag not like", value, "bizTag");
            return (Criteria) this;
        }

        public Criteria andBizTagIn(List<String> values) {
            addCriterion("biz_tag in", values, "bizTag");
            return (Criteria) this;
        }

        public Criteria andBizTagNotIn(List<String> values) {
            addCriterion("biz_tag not in", values, "bizTag");
            return (Criteria) this;
        }

        public Criteria andBizTagBetween(String value1, String value2) {
            addCriterion("biz_tag between", value1, value2, "bizTag");
            return (Criteria) this;
        }

        public Criteria andBizTagNotBetween(String value1, String value2) {
            addCriterion("biz_tag not between", value1, value2, "bizTag");
            return (Criteria) this;
        }

        public Criteria andCheckKeyIsNull() {
            addCriterion("check_key is null");
            return (Criteria) this;
        }

        public Criteria andCheckKeyIsNotNull() {
            addCriterion("check_key is not null");
            return (Criteria) this;
        }

        public Criteria andCheckKeyEqualTo(String value) {
            addCriterion("check_key =", value, "checkKey");
            return (Criteria) this;
        }

        public Criteria andCheckKeyNotEqualTo(String value) {
            addCriterion("check_key <>", value, "checkKey");
            return (Criteria) this;
        }

        public Criteria andCheckKeyGreaterThan(String value) {
            addCriterion("check_key >", value, "checkKey");
            return (Criteria) this;
        }

        public Criteria andCheckKeyGreaterThanOrEqualTo(String value) {
            addCriterion("check_key >=", value, "checkKey");
            return (Criteria) this;
        }

        public Criteria andCheckKeyLessThan(String value) {
            addCriterion("check_key <", value, "checkKey");
            return (Criteria) this;
        }

        public Criteria andCheckKeyLessThanOrEqualTo(String value) {
            addCriterion("check_key <=", value, "checkKey");
            return (Criteria) this;
        }

        public Criteria andCheckKeyLike(String value) {
            addCriterion("check_key like", value, "checkKey");
            return (Criteria) this;
        }

        public Criteria andCheckKeyNotLike(String value) {
            addCriterion("check_key not like", value, "checkKey");
            return (Criteria) this;
        }

        public Criteria andCheckKeyIn(List<String> values) {
            addCriterion("check_key in", values, "checkKey");
            return (Criteria) this;
        }

        public Criteria andCheckKeyNotIn(List<String> values) {
            addCriterion("check_key not in", values, "checkKey");
            return (Criteria) this;
        }

        public Criteria andCheckKeyBetween(String value1, String value2) {
            addCriterion("check_key between", value1, value2, "checkKey");
            return (Criteria) this;
        }

        public Criteria andCheckKeyNotBetween(String value1, String value2) {
            addCriterion("check_key not between", value1, value2, "checkKey");
            return (Criteria) this;
        }

        public Criteria andLeftValueIsNull() {
            addCriterion("left_value is null");
            return (Criteria) this;
        }

        public Criteria andLeftValueIsNotNull() {
            addCriterion("left_value is not null");
            return (Criteria) this;
        }

        public Criteria andLeftValueEqualTo(String value) {
            addCriterion("left_value =", value, "leftValue");
            return (Criteria) this;
        }

        public Criteria andLeftValueNotEqualTo(String value) {
            addCriterion("left_value <>", value, "leftValue");
            return (Criteria) this;
        }

        public Criteria andLeftValueGreaterThan(String value) {
            addCriterion("left_value >", value, "leftValue");
            return (Criteria) this;
        }

        public Criteria andLeftValueGreaterThanOrEqualTo(String value) {
            addCriterion("left_value >=", value, "leftValue");
            return (Criteria) this;
        }

        public Criteria andLeftValueLessThan(String value) {
            addCriterion("left_value <", value, "leftValue");
            return (Criteria) this;
        }

        public Criteria andLeftValueLessThanOrEqualTo(String value) {
            addCriterion("left_value <=", value, "leftValue");
            return (Criteria) this;
        }

        public Criteria andLeftValueLike(String value) {
            addCriterion("left_value like", value, "leftValue");
            return (Criteria) this;
        }

        public Criteria andLeftValueNotLike(String value) {
            addCriterion("left_value not like", value, "leftValue");
            return (Criteria) this;
        }

        public Criteria andLeftValueIn(List<String> values) {
            addCriterion("left_value in", values, "leftValue");
            return (Criteria) this;
        }

        public Criteria andLeftValueNotIn(List<String> values) {
            addCriterion("left_value not in", values, "leftValue");
            return (Criteria) this;
        }

        public Criteria andLeftValueBetween(String value1, String value2) {
            addCriterion("left_value between", value1, value2, "leftValue");
            return (Criteria) this;
        }

        public Criteria andLeftValueNotBetween(String value1, String value2) {
            addCriterion("left_value not between", value1, value2, "leftValue");
            return (Criteria) this;
        }

        public Criteria andRightValueIsNull() {
            addCriterion("right_value is null");
            return (Criteria) this;
        }

        public Criteria andRightValueIsNotNull() {
            addCriterion("right_value is not null");
            return (Criteria) this;
        }

        public Criteria andRightValueEqualTo(String value) {
            addCriterion("right_value =", value, "rightValue");
            return (Criteria) this;
        }

        public Criteria andRightValueNotEqualTo(String value) {
            addCriterion("right_value <>", value, "rightValue");
            return (Criteria) this;
        }

        public Criteria andRightValueGreaterThan(String value) {
            addCriterion("right_value >", value, "rightValue");
            return (Criteria) this;
        }

        public Criteria andRightValueGreaterThanOrEqualTo(String value) {
            addCriterion("right_value >=", value, "rightValue");
            return (Criteria) this;
        }

        public Criteria andRightValueLessThan(String value) {
            addCriterion("right_value <", value, "rightValue");
            return (Criteria) this;
        }

        public Criteria andRightValueLessThanOrEqualTo(String value) {
            addCriterion("right_value <=", value, "rightValue");
            return (Criteria) this;
        }

        public Criteria andRightValueLike(String value) {
            addCriterion("right_value like", value, "rightValue");
            return (Criteria) this;
        }

        public Criteria andRightValueNotLike(String value) {
            addCriterion("right_value not like", value, "rightValue");
            return (Criteria) this;
        }

        public Criteria andRightValueIn(List<String> values) {
            addCriterion("right_value in", values, "rightValue");
            return (Criteria) this;
        }

        public Criteria andRightValueNotIn(List<String> values) {
            addCriterion("right_value not in", values, "rightValue");
            return (Criteria) this;
        }

        public Criteria andRightValueBetween(String value1, String value2) {
            addCriterion("right_value between", value1, value2, "rightValue");
            return (Criteria) this;
        }

        public Criteria andRightValueNotBetween(String value1, String value2) {
            addCriterion("right_value not between", value1, value2, "rightValue");
            return (Criteria) this;
        }

        public Criteria andIsPatternIsNull() {
            addCriterion("is_pattern is null");
            return (Criteria) this;
        }

        public Criteria andIsPatternIsNotNull() {
            addCriterion("is_pattern is not null");
            return (Criteria) this;
        }

        public Criteria andIsPatternEqualTo(Boolean value) {
            addCriterion("is_pattern =", value, "isPattern");
            return (Criteria) this;
        }

        public Criteria andIsPatternNotEqualTo(Boolean value) {
            addCriterion("is_pattern <>", value, "isPattern");
            return (Criteria) this;
        }

        public Criteria andIsPatternGreaterThan(Boolean value) {
            addCriterion("is_pattern >", value, "isPattern");
            return (Criteria) this;
        }

        public Criteria andIsPatternGreaterThanOrEqualTo(Boolean value) {
            addCriterion("is_pattern >=", value, "isPattern");
            return (Criteria) this;
        }

        public Criteria andIsPatternLessThan(Boolean value) {
            addCriterion("is_pattern <", value, "isPattern");
            return (Criteria) this;
        }

        public Criteria andIsPatternLessThanOrEqualTo(Boolean value) {
            addCriterion("is_pattern <=", value, "isPattern");
            return (Criteria) this;
        }

        public Criteria andIsPatternIn(List<Boolean> values) {
            addCriterion("is_pattern in", values, "isPattern");
            return (Criteria) this;
        }

        public Criteria andIsPatternNotIn(List<Boolean> values) {
            addCriterion("is_pattern not in", values, "isPattern");
            return (Criteria) this;
        }

        public Criteria andIsPatternBetween(Boolean value1, Boolean value2) {
            addCriterion("is_pattern between", value1, value2, "isPattern");
            return (Criteria) this;
        }

        public Criteria andIsPatternNotBetween(Boolean value1, Boolean value2) {
            addCriterion("is_pattern not between", value1, value2, "isPattern");
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