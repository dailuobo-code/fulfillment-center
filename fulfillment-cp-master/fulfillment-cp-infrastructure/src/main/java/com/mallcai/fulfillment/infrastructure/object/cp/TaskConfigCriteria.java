package com.mallcai.fulfillment.infrastructure.object.cp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskConfigCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TaskConfigCriteria() {
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

        public Criteria andBizDescIsNull() {
            addCriterion("biz_desc is null");
            return (Criteria) this;
        }

        public Criteria andBizDescIsNotNull() {
            addCriterion("biz_desc is not null");
            return (Criteria) this;
        }

        public Criteria andBizDescEqualTo(String value) {
            addCriterion("biz_desc =", value, "bizDesc");
            return (Criteria) this;
        }

        public Criteria andBizDescNotEqualTo(String value) {
            addCriterion("biz_desc <>", value, "bizDesc");
            return (Criteria) this;
        }

        public Criteria andBizDescGreaterThan(String value) {
            addCriterion("biz_desc >", value, "bizDesc");
            return (Criteria) this;
        }

        public Criteria andBizDescGreaterThanOrEqualTo(String value) {
            addCriterion("biz_desc >=", value, "bizDesc");
            return (Criteria) this;
        }

        public Criteria andBizDescLessThan(String value) {
            addCriterion("biz_desc <", value, "bizDesc");
            return (Criteria) this;
        }

        public Criteria andBizDescLessThanOrEqualTo(String value) {
            addCriterion("biz_desc <=", value, "bizDesc");
            return (Criteria) this;
        }

        public Criteria andBizDescLike(String value) {
            addCriterion("biz_desc like", value, "bizDesc");
            return (Criteria) this;
        }

        public Criteria andBizDescNotLike(String value) {
            addCriterion("biz_desc not like", value, "bizDesc");
            return (Criteria) this;
        }

        public Criteria andBizDescIn(List<String> values) {
            addCriterion("biz_desc in", values, "bizDesc");
            return (Criteria) this;
        }

        public Criteria andBizDescNotIn(List<String> values) {
            addCriterion("biz_desc not in", values, "bizDesc");
            return (Criteria) this;
        }

        public Criteria andBizDescBetween(String value1, String value2) {
            addCriterion("biz_desc between", value1, value2, "bizDesc");
            return (Criteria) this;
        }

        public Criteria andBizDescNotBetween(String value1, String value2) {
            addCriterion("biz_desc not between", value1, value2, "bizDesc");
            return (Criteria) this;
        }

        public Criteria andLastCheckTimeIsNull() {
            addCriterion("last_check_time is null");
            return (Criteria) this;
        }

        public Criteria andLastCheckTimeIsNotNull() {
            addCriterion("last_check_time is not null");
            return (Criteria) this;
        }

        public Criteria andLastCheckTimeEqualTo(Date value) {
            addCriterion("last_check_time =", value, "lastCheckTime");
            return (Criteria) this;
        }

        public Criteria andLastCheckTimeNotEqualTo(Date value) {
            addCriterion("last_check_time <>", value, "lastCheckTime");
            return (Criteria) this;
        }

        public Criteria andLastCheckTimeGreaterThan(Date value) {
            addCriterion("last_check_time >", value, "lastCheckTime");
            return (Criteria) this;
        }

        public Criteria andLastCheckTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("last_check_time >=", value, "lastCheckTime");
            return (Criteria) this;
        }

        public Criteria andLastCheckTimeLessThan(Date value) {
            addCriterion("last_check_time <", value, "lastCheckTime");
            return (Criteria) this;
        }

        public Criteria andLastCheckTimeLessThanOrEqualTo(Date value) {
            addCriterion("last_check_time <=", value, "lastCheckTime");
            return (Criteria) this;
        }

        public Criteria andLastCheckTimeIn(List<Date> values) {
            addCriterion("last_check_time in", values, "lastCheckTime");
            return (Criteria) this;
        }

        public Criteria andLastCheckTimeNotIn(List<Date> values) {
            addCriterion("last_check_time not in", values, "lastCheckTime");
            return (Criteria) this;
        }

        public Criteria andLastCheckTimeBetween(Date value1, Date value2) {
            addCriterion("last_check_time between", value1, value2, "lastCheckTime");
            return (Criteria) this;
        }

        public Criteria andLastCheckTimeNotBetween(Date value1, Date value2) {
            addCriterion("last_check_time not between", value1, value2, "lastCheckTime");
            return (Criteria) this;
        }

        public Criteria andCheckIntelIsNull() {
            addCriterion("check_intel is null");
            return (Criteria) this;
        }

        public Criteria andCheckIntelIsNotNull() {
            addCriterion("check_intel is not null");
            return (Criteria) this;
        }

        public Criteria andCheckIntelEqualTo(Integer value) {
            addCriterion("check_intel =", value, "checkIntel");
            return (Criteria) this;
        }

        public Criteria andCheckIntelNotEqualTo(Integer value) {
            addCriterion("check_intel <>", value, "checkIntel");
            return (Criteria) this;
        }

        public Criteria andCheckIntelGreaterThan(Integer value) {
            addCriterion("check_intel >", value, "checkIntel");
            return (Criteria) this;
        }

        public Criteria andCheckIntelGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_intel >=", value, "checkIntel");
            return (Criteria) this;
        }

        public Criteria andCheckIntelLessThan(Integer value) {
            addCriterion("check_intel <", value, "checkIntel");
            return (Criteria) this;
        }

        public Criteria andCheckIntelLessThanOrEqualTo(Integer value) {
            addCriterion("check_intel <=", value, "checkIntel");
            return (Criteria) this;
        }

        public Criteria andCheckIntelIn(List<Integer> values) {
            addCriterion("check_intel in", values, "checkIntel");
            return (Criteria) this;
        }

        public Criteria andCheckIntelNotIn(List<Integer> values) {
            addCriterion("check_intel not in", values, "checkIntel");
            return (Criteria) this;
        }

        public Criteria andCheckIntelBetween(Integer value1, Integer value2) {
            addCriterion("check_intel between", value1, value2, "checkIntel");
            return (Criteria) this;
        }

        public Criteria andCheckIntelNotBetween(Integer value1, Integer value2) {
            addCriterion("check_intel not between", value1, value2, "checkIntel");
            return (Criteria) this;
        }

        public Criteria andLeftLoaderBeanIsNull() {
            addCriterion("left_loader_bean is null");
            return (Criteria) this;
        }

        public Criteria andLeftLoaderBeanIsNotNull() {
            addCriterion("left_loader_bean is not null");
            return (Criteria) this;
        }

        public Criteria andLeftLoaderBeanEqualTo(String value) {
            addCriterion("left_loader_bean =", value, "leftLoaderBean");
            return (Criteria) this;
        }

        public Criteria andLeftLoaderBeanNotEqualTo(String value) {
            addCriterion("left_loader_bean <>", value, "leftLoaderBean");
            return (Criteria) this;
        }

        public Criteria andLeftLoaderBeanGreaterThan(String value) {
            addCriterion("left_loader_bean >", value, "leftLoaderBean");
            return (Criteria) this;
        }

        public Criteria andLeftLoaderBeanGreaterThanOrEqualTo(String value) {
            addCriterion("left_loader_bean >=", value, "leftLoaderBean");
            return (Criteria) this;
        }

        public Criteria andLeftLoaderBeanLessThan(String value) {
            addCriterion("left_loader_bean <", value, "leftLoaderBean");
            return (Criteria) this;
        }

        public Criteria andLeftLoaderBeanLessThanOrEqualTo(String value) {
            addCriterion("left_loader_bean <=", value, "leftLoaderBean");
            return (Criteria) this;
        }

        public Criteria andLeftLoaderBeanLike(String value) {
            addCriterion("left_loader_bean like", value, "leftLoaderBean");
            return (Criteria) this;
        }

        public Criteria andLeftLoaderBeanNotLike(String value) {
            addCriterion("left_loader_bean not like", value, "leftLoaderBean");
            return (Criteria) this;
        }

        public Criteria andLeftLoaderBeanIn(List<String> values) {
            addCriterion("left_loader_bean in", values, "leftLoaderBean");
            return (Criteria) this;
        }

        public Criteria andLeftLoaderBeanNotIn(List<String> values) {
            addCriterion("left_loader_bean not in", values, "leftLoaderBean");
            return (Criteria) this;
        }

        public Criteria andLeftLoaderBeanBetween(String value1, String value2) {
            addCriterion("left_loader_bean between", value1, value2, "leftLoaderBean");
            return (Criteria) this;
        }

        public Criteria andLeftLoaderBeanNotBetween(String value1, String value2) {
            addCriterion("left_loader_bean not between", value1, value2, "leftLoaderBean");
            return (Criteria) this;
        }

        public Criteria andRightLoaderBeanIsNull() {
            addCriterion("right_loader_bean is null");
            return (Criteria) this;
        }

        public Criteria andRightLoaderBeanIsNotNull() {
            addCriterion("right_loader_bean is not null");
            return (Criteria) this;
        }

        public Criteria andRightLoaderBeanEqualTo(String value) {
            addCriterion("right_loader_bean =", value, "rightLoaderBean");
            return (Criteria) this;
        }

        public Criteria andRightLoaderBeanNotEqualTo(String value) {
            addCriterion("right_loader_bean <>", value, "rightLoaderBean");
            return (Criteria) this;
        }

        public Criteria andRightLoaderBeanGreaterThan(String value) {
            addCriterion("right_loader_bean >", value, "rightLoaderBean");
            return (Criteria) this;
        }

        public Criteria andRightLoaderBeanGreaterThanOrEqualTo(String value) {
            addCriterion("right_loader_bean >=", value, "rightLoaderBean");
            return (Criteria) this;
        }

        public Criteria andRightLoaderBeanLessThan(String value) {
            addCriterion("right_loader_bean <", value, "rightLoaderBean");
            return (Criteria) this;
        }

        public Criteria andRightLoaderBeanLessThanOrEqualTo(String value) {
            addCriterion("right_loader_bean <=", value, "rightLoaderBean");
            return (Criteria) this;
        }

        public Criteria andRightLoaderBeanLike(String value) {
            addCriterion("right_loader_bean like", value, "rightLoaderBean");
            return (Criteria) this;
        }

        public Criteria andRightLoaderBeanNotLike(String value) {
            addCriterion("right_loader_bean not like", value, "rightLoaderBean");
            return (Criteria) this;
        }

        public Criteria andRightLoaderBeanIn(List<String> values) {
            addCriterion("right_loader_bean in", values, "rightLoaderBean");
            return (Criteria) this;
        }

        public Criteria andRightLoaderBeanNotIn(List<String> values) {
            addCriterion("right_loader_bean not in", values, "rightLoaderBean");
            return (Criteria) this;
        }

        public Criteria andRightLoaderBeanBetween(String value1, String value2) {
            addCriterion("right_loader_bean between", value1, value2, "rightLoaderBean");
            return (Criteria) this;
        }

        public Criteria andRightLoaderBeanNotBetween(String value1, String value2) {
            addCriterion("right_loader_bean not between", value1, value2, "rightLoaderBean");
            return (Criteria) this;
        }

        public Criteria andLoadIndexBeanIsNull() {
            addCriterion("load_index_bean is null");
            return (Criteria) this;
        }

        public Criteria andLoadIndexBeanIsNotNull() {
            addCriterion("load_index_bean is not null");
            return (Criteria) this;
        }

        public Criteria andLoadIndexBeanEqualTo(String value) {
            addCriterion("load_index_bean =", value, "loadIndexBean");
            return (Criteria) this;
        }

        public Criteria andLoadIndexBeanNotEqualTo(String value) {
            addCriterion("load_index_bean <>", value, "loadIndexBean");
            return (Criteria) this;
        }

        public Criteria andLoadIndexBeanGreaterThan(String value) {
            addCriterion("load_index_bean >", value, "loadIndexBean");
            return (Criteria) this;
        }

        public Criteria andLoadIndexBeanGreaterThanOrEqualTo(String value) {
            addCriterion("load_index_bean >=", value, "loadIndexBean");
            return (Criteria) this;
        }

        public Criteria andLoadIndexBeanLessThan(String value) {
            addCriterion("load_index_bean <", value, "loadIndexBean");
            return (Criteria) this;
        }

        public Criteria andLoadIndexBeanLessThanOrEqualTo(String value) {
            addCriterion("load_index_bean <=", value, "loadIndexBean");
            return (Criteria) this;
        }

        public Criteria andLoadIndexBeanLike(String value) {
            addCriterion("load_index_bean like", value, "loadIndexBean");
            return (Criteria) this;
        }

        public Criteria andLoadIndexBeanNotLike(String value) {
            addCriterion("load_index_bean not like", value, "loadIndexBean");
            return (Criteria) this;
        }

        public Criteria andLoadIndexBeanIn(List<String> values) {
            addCriterion("load_index_bean in", values, "loadIndexBean");
            return (Criteria) this;
        }

        public Criteria andLoadIndexBeanNotIn(List<String> values) {
            addCriterion("load_index_bean not in", values, "loadIndexBean");
            return (Criteria) this;
        }

        public Criteria andLoadIndexBeanBetween(String value1, String value2) {
            addCriterion("load_index_bean between", value1, value2, "loadIndexBean");
            return (Criteria) this;
        }

        public Criteria andLoadIndexBeanNotBetween(String value1, String value2) {
            addCriterion("load_index_bean not between", value1, value2, "loadIndexBean");
            return (Criteria) this;
        }

        public Criteria andCheckBeanIsNull() {
            addCriterion("check_bean is null");
            return (Criteria) this;
        }

        public Criteria andCheckBeanIsNotNull() {
            addCriterion("check_bean is not null");
            return (Criteria) this;
        }

        public Criteria andCheckBeanEqualTo(String value) {
            addCriterion("check_bean =", value, "checkBean");
            return (Criteria) this;
        }

        public Criteria andCheckBeanNotEqualTo(String value) {
            addCriterion("check_bean <>", value, "checkBean");
            return (Criteria) this;
        }

        public Criteria andCheckBeanGreaterThan(String value) {
            addCriterion("check_bean >", value, "checkBean");
            return (Criteria) this;
        }

        public Criteria andCheckBeanGreaterThanOrEqualTo(String value) {
            addCriterion("check_bean >=", value, "checkBean");
            return (Criteria) this;
        }

        public Criteria andCheckBeanLessThan(String value) {
            addCriterion("check_bean <", value, "checkBean");
            return (Criteria) this;
        }

        public Criteria andCheckBeanLessThanOrEqualTo(String value) {
            addCriterion("check_bean <=", value, "checkBean");
            return (Criteria) this;
        }

        public Criteria andCheckBeanLike(String value) {
            addCriterion("check_bean like", value, "checkBean");
            return (Criteria) this;
        }

        public Criteria andCheckBeanNotLike(String value) {
            addCriterion("check_bean not like", value, "checkBean");
            return (Criteria) this;
        }

        public Criteria andCheckBeanIn(List<String> values) {
            addCriterion("check_bean in", values, "checkBean");
            return (Criteria) this;
        }

        public Criteria andCheckBeanNotIn(List<String> values) {
            addCriterion("check_bean not in", values, "checkBean");
            return (Criteria) this;
        }

        public Criteria andCheckBeanBetween(String value1, String value2) {
            addCriterion("check_bean between", value1, value2, "checkBean");
            return (Criteria) this;
        }

        public Criteria andCheckBeanNotBetween(String value1, String value2) {
            addCriterion("check_bean not between", value1, value2, "checkBean");
            return (Criteria) this;
        }

        public Criteria andResultBeanIsNull() {
            addCriterion("result_bean is null");
            return (Criteria) this;
        }

        public Criteria andResultBeanIsNotNull() {
            addCriterion("result_bean is not null");
            return (Criteria) this;
        }

        public Criteria andResultBeanEqualTo(String value) {
            addCriterion("result_bean =", value, "resultBean");
            return (Criteria) this;
        }

        public Criteria andResultBeanNotEqualTo(String value) {
            addCriterion("result_bean <>", value, "resultBean");
            return (Criteria) this;
        }

        public Criteria andResultBeanGreaterThan(String value) {
            addCriterion("result_bean >", value, "resultBean");
            return (Criteria) this;
        }

        public Criteria andResultBeanGreaterThanOrEqualTo(String value) {
            addCriterion("result_bean >=", value, "resultBean");
            return (Criteria) this;
        }

        public Criteria andResultBeanLessThan(String value) {
            addCriterion("result_bean <", value, "resultBean");
            return (Criteria) this;
        }

        public Criteria andResultBeanLessThanOrEqualTo(String value) {
            addCriterion("result_bean <=", value, "resultBean");
            return (Criteria) this;
        }

        public Criteria andResultBeanLike(String value) {
            addCriterion("result_bean like", value, "resultBean");
            return (Criteria) this;
        }

        public Criteria andResultBeanNotLike(String value) {
            addCriterion("result_bean not like", value, "resultBean");
            return (Criteria) this;
        }

        public Criteria andResultBeanIn(List<String> values) {
            addCriterion("result_bean in", values, "resultBean");
            return (Criteria) this;
        }

        public Criteria andResultBeanNotIn(List<String> values) {
            addCriterion("result_bean not in", values, "resultBean");
            return (Criteria) this;
        }

        public Criteria andResultBeanBetween(String value1, String value2) {
            addCriterion("result_bean between", value1, value2, "resultBean");
            return (Criteria) this;
        }

        public Criteria andResultBeanNotBetween(String value1, String value2) {
            addCriterion("result_bean not between", value1, value2, "resultBean");
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