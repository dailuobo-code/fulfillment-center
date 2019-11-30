package com.mallcai.fulfillment.dc.valueobject.dc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FlowStepDataInfoCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FlowStepDataInfoCriteria() {
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

        public Criteria andWorkFlowIdIsNull() {
            addCriterion("work_flow_id is null");
            return (Criteria) this;
        }

        public Criteria andWorkFlowIdIsNotNull() {
            addCriterion("work_flow_id is not null");
            return (Criteria) this;
        }

        public Criteria andWorkFlowIdEqualTo(Long value) {
            addCriterion("work_flow_id =", value, "workFlowId");
            return (Criteria) this;
        }

        public Criteria andWorkFlowIdNotEqualTo(Long value) {
            addCriterion("work_flow_id <>", value, "workFlowId");
            return (Criteria) this;
        }

        public Criteria andWorkFlowIdGreaterThan(Long value) {
            addCriterion("work_flow_id >", value, "workFlowId");
            return (Criteria) this;
        }

        public Criteria andWorkFlowIdGreaterThanOrEqualTo(Long value) {
            addCriterion("work_flow_id >=", value, "workFlowId");
            return (Criteria) this;
        }

        public Criteria andWorkFlowIdLessThan(Long value) {
            addCriterion("work_flow_id <", value, "workFlowId");
            return (Criteria) this;
        }

        public Criteria andWorkFlowIdLessThanOrEqualTo(Long value) {
            addCriterion("work_flow_id <=", value, "workFlowId");
            return (Criteria) this;
        }

        public Criteria andWorkFlowIdIn(List<Long> values) {
            addCriterion("work_flow_id in", values, "workFlowId");
            return (Criteria) this;
        }

        public Criteria andWorkFlowIdNotIn(List<Long> values) {
            addCriterion("work_flow_id not in", values, "workFlowId");
            return (Criteria) this;
        }

        public Criteria andWorkFlowIdBetween(Long value1, Long value2) {
            addCriterion("work_flow_id between", value1, value2, "workFlowId");
            return (Criteria) this;
        }

        public Criteria andWorkFlowIdNotBetween(Long value1, Long value2) {
            addCriterion("work_flow_id not between", value1, value2, "workFlowId");
            return (Criteria) this;
        }

        public Criteria andWorkFlowStepIdIsNull() {
            addCriterion("work_flow_step_id is null");
            return (Criteria) this;
        }

        public Criteria andWorkFlowStepIdIsNotNull() {
            addCriterion("work_flow_step_id is not null");
            return (Criteria) this;
        }

        public Criteria andWorkFlowStepIdEqualTo(Long value) {
            addCriterion("work_flow_step_id =", value, "workFlowStepId");
            return (Criteria) this;
        }

        public Criteria andWorkFlowStepIdNotEqualTo(Long value) {
            addCriterion("work_flow_step_id <>", value, "workFlowStepId");
            return (Criteria) this;
        }

        public Criteria andWorkFlowStepIdGreaterThan(Long value) {
            addCriterion("work_flow_step_id >", value, "workFlowStepId");
            return (Criteria) this;
        }

        public Criteria andWorkFlowStepIdGreaterThanOrEqualTo(Long value) {
            addCriterion("work_flow_step_id >=", value, "workFlowStepId");
            return (Criteria) this;
        }

        public Criteria andWorkFlowStepIdLessThan(Long value) {
            addCriterion("work_flow_step_id <", value, "workFlowStepId");
            return (Criteria) this;
        }

        public Criteria andWorkFlowStepIdLessThanOrEqualTo(Long value) {
            addCriterion("work_flow_step_id <=", value, "workFlowStepId");
            return (Criteria) this;
        }

        public Criteria andWorkFlowStepIdIn(List<Long> values) {
            addCriterion("work_flow_step_id in", values, "workFlowStepId");
            return (Criteria) this;
        }

        public Criteria andWorkFlowStepIdNotIn(List<Long> values) {
            addCriterion("work_flow_step_id not in", values, "workFlowStepId");
            return (Criteria) this;
        }

        public Criteria andWorkFlowStepIdBetween(Long value1, Long value2) {
            addCriterion("work_flow_step_id between", value1, value2, "workFlowStepId");
            return (Criteria) this;
        }

        public Criteria andWorkFlowStepIdNotBetween(Long value1, Long value2) {
            addCriterion("work_flow_step_id not between", value1, value2, "workFlowStepId");
            return (Criteria) this;
        }

        public Criteria andBizTypeIsNull() {
            addCriterion("biz_type is null");
            return (Criteria) this;
        }

        public Criteria andBizTypeIsNotNull() {
            addCriterion("biz_type is not null");
            return (Criteria) this;
        }

        public Criteria andBizTypeEqualTo(Byte value) {
            addCriterion("biz_type =", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeNotEqualTo(Byte value) {
            addCriterion("biz_type <>", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeGreaterThan(Byte value) {
            addCriterion("biz_type >", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("biz_type >=", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeLessThan(Byte value) {
            addCriterion("biz_type <", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeLessThanOrEqualTo(Byte value) {
            addCriterion("biz_type <=", value, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeIn(List<Byte> values) {
            addCriterion("biz_type in", values, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeNotIn(List<Byte> values) {
            addCriterion("biz_type not in", values, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeBetween(Byte value1, Byte value2) {
            addCriterion("biz_type between", value1, value2, "bizType");
            return (Criteria) this;
        }

        public Criteria andBizTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("biz_type not between", value1, value2, "bizType");
            return (Criteria) this;
        }

        public Criteria andFlowGroupIsNull() {
            addCriterion("flow_group is null");
            return (Criteria) this;
        }

        public Criteria andFlowGroupIsNotNull() {
            addCriterion("flow_group is not null");
            return (Criteria) this;
        }

        public Criteria andFlowGroupEqualTo(String value) {
            addCriterion("flow_group =", value, "flowGroup");
            return (Criteria) this;
        }

        public Criteria andFlowGroupNotEqualTo(String value) {
            addCriterion("flow_group <>", value, "flowGroup");
            return (Criteria) this;
        }

        public Criteria andFlowGroupGreaterThan(String value) {
            addCriterion("flow_group >", value, "flowGroup");
            return (Criteria) this;
        }

        public Criteria andFlowGroupGreaterThanOrEqualTo(String value) {
            addCriterion("flow_group >=", value, "flowGroup");
            return (Criteria) this;
        }

        public Criteria andFlowGroupLessThan(String value) {
            addCriterion("flow_group <", value, "flowGroup");
            return (Criteria) this;
        }

        public Criteria andFlowGroupLessThanOrEqualTo(String value) {
            addCriterion("flow_group <=", value, "flowGroup");
            return (Criteria) this;
        }

        public Criteria andFlowGroupLike(String value) {
            addCriterion("flow_group like", value, "flowGroup");
            return (Criteria) this;
        }

        public Criteria andFlowGroupNotLike(String value) {
            addCriterion("flow_group not like", value, "flowGroup");
            return (Criteria) this;
        }

        public Criteria andFlowGroupIn(List<String> values) {
            addCriterion("flow_group in", values, "flowGroup");
            return (Criteria) this;
        }

        public Criteria andFlowGroupNotIn(List<String> values) {
            addCriterion("flow_group not in", values, "flowGroup");
            return (Criteria) this;
        }

        public Criteria andFlowGroupBetween(String value1, String value2) {
            addCriterion("flow_group between", value1, value2, "flowGroup");
            return (Criteria) this;
        }

        public Criteria andFlowGroupNotBetween(String value1, String value2) {
            addCriterion("flow_group not between", value1, value2, "flowGroup");
            return (Criteria) this;
        }

        public Criteria andNodeIsNull() {
            addCriterion("node is null");
            return (Criteria) this;
        }

        public Criteria andNodeIsNotNull() {
            addCriterion("node is not null");
            return (Criteria) this;
        }

        public Criteria andNodeEqualTo(String value) {
            addCriterion("node =", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeNotEqualTo(String value) {
            addCriterion("node <>", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeGreaterThan(String value) {
            addCriterion("node >", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeGreaterThanOrEqualTo(String value) {
            addCriterion("node >=", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeLessThan(String value) {
            addCriterion("node <", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeLessThanOrEqualTo(String value) {
            addCriterion("node <=", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeLike(String value) {
            addCriterion("node like", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeNotLike(String value) {
            addCriterion("node not like", value, "node");
            return (Criteria) this;
        }

        public Criteria andNodeIn(List<String> values) {
            addCriterion("node in", values, "node");
            return (Criteria) this;
        }

        public Criteria andNodeNotIn(List<String> values) {
            addCriterion("node not in", values, "node");
            return (Criteria) this;
        }

        public Criteria andNodeBetween(String value1, String value2) {
            addCriterion("node between", value1, value2, "node");
            return (Criteria) this;
        }

        public Criteria andNodeNotBetween(String value1, String value2) {
            addCriterion("node not between", value1, value2, "node");
            return (Criteria) this;
        }

        public Criteria andStepNameIsNull() {
            addCriterion("step_name is null");
            return (Criteria) this;
        }

        public Criteria andStepNameIsNotNull() {
            addCriterion("step_name is not null");
            return (Criteria) this;
        }

        public Criteria andStepNameEqualTo(String value) {
            addCriterion("step_name =", value, "stepName");
            return (Criteria) this;
        }

        public Criteria andStepNameNotEqualTo(String value) {
            addCriterion("step_name <>", value, "stepName");
            return (Criteria) this;
        }

        public Criteria andStepNameGreaterThan(String value) {
            addCriterion("step_name >", value, "stepName");
            return (Criteria) this;
        }

        public Criteria andStepNameGreaterThanOrEqualTo(String value) {
            addCriterion("step_name >=", value, "stepName");
            return (Criteria) this;
        }

        public Criteria andStepNameLessThan(String value) {
            addCriterion("step_name <", value, "stepName");
            return (Criteria) this;
        }

        public Criteria andStepNameLessThanOrEqualTo(String value) {
            addCriterion("step_name <=", value, "stepName");
            return (Criteria) this;
        }

        public Criteria andStepNameLike(String value) {
            addCriterion("step_name like", value, "stepName");
            return (Criteria) this;
        }

        public Criteria andStepNameNotLike(String value) {
            addCriterion("step_name not like", value, "stepName");
            return (Criteria) this;
        }

        public Criteria andStepNameIn(List<String> values) {
            addCriterion("step_name in", values, "stepName");
            return (Criteria) this;
        }

        public Criteria andStepNameNotIn(List<String> values) {
            addCriterion("step_name not in", values, "stepName");
            return (Criteria) this;
        }

        public Criteria andStepNameBetween(String value1, String value2) {
            addCriterion("step_name between", value1, value2, "stepName");
            return (Criteria) this;
        }

        public Criteria andStepNameNotBetween(String value1, String value2) {
            addCriterion("step_name not between", value1, value2, "stepName");
            return (Criteria) this;
        }

        public Criteria andBizKeyIsNull() {
            addCriterion("biz_key is null");
            return (Criteria) this;
        }

        public Criteria andBizKeyIsNotNull() {
            addCriterion("biz_key is not null");
            return (Criteria) this;
        }

        public Criteria andBizKeyEqualTo(String value) {
            addCriterion("biz_key =", value, "bizKey");
            return (Criteria) this;
        }

        public Criteria andBizKeyNotEqualTo(String value) {
            addCriterion("biz_key <>", value, "bizKey");
            return (Criteria) this;
        }

        public Criteria andBizKeyGreaterThan(String value) {
            addCriterion("biz_key >", value, "bizKey");
            return (Criteria) this;
        }

        public Criteria andBizKeyGreaterThanOrEqualTo(String value) {
            addCriterion("biz_key >=", value, "bizKey");
            return (Criteria) this;
        }

        public Criteria andBizKeyLessThan(String value) {
            addCriterion("biz_key <", value, "bizKey");
            return (Criteria) this;
        }

        public Criteria andBizKeyLessThanOrEqualTo(String value) {
            addCriterion("biz_key <=", value, "bizKey");
            return (Criteria) this;
        }

        public Criteria andBizKeyLike(String value) {
            addCriterion("biz_key like", value, "bizKey");
            return (Criteria) this;
        }

        public Criteria andBizKeyNotLike(String value) {
            addCriterion("biz_key not like", value, "bizKey");
            return (Criteria) this;
        }

        public Criteria andBizKeyIn(List<String> values) {
            addCriterion("biz_key in", values, "bizKey");
            return (Criteria) this;
        }

        public Criteria andBizKeyNotIn(List<String> values) {
            addCriterion("biz_key not in", values, "bizKey");
            return (Criteria) this;
        }

        public Criteria andBizKeyBetween(String value1, String value2) {
            addCriterion("biz_key between", value1, value2, "bizKey");
            return (Criteria) this;
        }

        public Criteria andBizKeyNotBetween(String value1, String value2) {
            addCriterion("biz_key not between", value1, value2, "bizKey");
            return (Criteria) this;
        }

        public Criteria andDataIsNull() {
            addCriterion("data is null");
            return (Criteria) this;
        }

        public Criteria andDataIsNotNull() {
            addCriterion("data is not null");
            return (Criteria) this;
        }

        public Criteria andDataEqualTo(String value) {
            addCriterion("data =", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotEqualTo(String value) {
            addCriterion("data <>", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataGreaterThan(String value) {
            addCriterion("data >", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataGreaterThanOrEqualTo(String value) {
            addCriterion("data >=", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataLessThan(String value) {
            addCriterion("data <", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataLessThanOrEqualTo(String value) {
            addCriterion("data <=", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataLike(String value) {
            addCriterion("data like", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotLike(String value) {
            addCriterion("data not like", value, "data");
            return (Criteria) this;
        }

        public Criteria andDataIn(List<String> values) {
            addCriterion("data in", values, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotIn(List<String> values) {
            addCriterion("data not in", values, "data");
            return (Criteria) this;
        }

        public Criteria andDataBetween(String value1, String value2) {
            addCriterion("data between", value1, value2, "data");
            return (Criteria) this;
        }

        public Criteria andDataNotBetween(String value1, String value2) {
            addCriterion("data not between", value1, value2, "data");
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