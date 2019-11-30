package com.mallcai.fulfillment.infrastructure.object.cp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TaskRecordCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TaskRecordCriteria() {
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

        public Criteria andCheckTimeIsNull() {
            addCriterion("check_time is null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIsNotNull() {
            addCriterion("check_time is not null");
            return (Criteria) this;
        }

        public Criteria andCheckTimeEqualTo(Date value) {
            addCriterion("check_time =", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotEqualTo(Date value) {
            addCriterion("check_time <>", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThan(Date value) {
            addCriterion("check_time >", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("check_time >=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThan(Date value) {
            addCriterion("check_time <", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeLessThanOrEqualTo(Date value) {
            addCriterion("check_time <=", value, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeIn(List<Date> values) {
            addCriterion("check_time in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotIn(List<Date> values) {
            addCriterion("check_time not in", values, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeBetween(Date value1, Date value2) {
            addCriterion("check_time between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckTimeNotBetween(Date value1, Date value2) {
            addCriterion("check_time not between", value1, value2, "checkTime");
            return (Criteria) this;
        }

        public Criteria andCheckParamIsNull() {
            addCriterion("check_param is null");
            return (Criteria) this;
        }

        public Criteria andCheckParamIsNotNull() {
            addCriterion("check_param is not null");
            return (Criteria) this;
        }

        public Criteria andCheckParamEqualTo(String value) {
            addCriterion("check_param =", value, "checkParam");
            return (Criteria) this;
        }

        public Criteria andCheckParamNotEqualTo(String value) {
            addCriterion("check_param <>", value, "checkParam");
            return (Criteria) this;
        }

        public Criteria andCheckParamGreaterThan(String value) {
            addCriterion("check_param >", value, "checkParam");
            return (Criteria) this;
        }

        public Criteria andCheckParamGreaterThanOrEqualTo(String value) {
            addCriterion("check_param >=", value, "checkParam");
            return (Criteria) this;
        }

        public Criteria andCheckParamLessThan(String value) {
            addCriterion("check_param <", value, "checkParam");
            return (Criteria) this;
        }

        public Criteria andCheckParamLessThanOrEqualTo(String value) {
            addCriterion("check_param <=", value, "checkParam");
            return (Criteria) this;
        }

        public Criteria andCheckParamLike(String value) {
            addCriterion("check_param like", value, "checkParam");
            return (Criteria) this;
        }

        public Criteria andCheckParamNotLike(String value) {
            addCriterion("check_param not like", value, "checkParam");
            return (Criteria) this;
        }

        public Criteria andCheckParamIn(List<String> values) {
            addCriterion("check_param in", values, "checkParam");
            return (Criteria) this;
        }

        public Criteria andCheckParamNotIn(List<String> values) {
            addCriterion("check_param not in", values, "checkParam");
            return (Criteria) this;
        }

        public Criteria andCheckParamBetween(String value1, String value2) {
            addCriterion("check_param between", value1, value2, "checkParam");
            return (Criteria) this;
        }

        public Criteria andCheckParamNotBetween(String value1, String value2) {
            addCriterion("check_param not between", value1, value2, "checkParam");
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

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andIndexSizeIsNull() {
            addCriterion("index_size is null");
            return (Criteria) this;
        }

        public Criteria andIndexSizeIsNotNull() {
            addCriterion("index_size is not null");
            return (Criteria) this;
        }

        public Criteria andIndexSizeEqualTo(Integer value) {
            addCriterion("index_size =", value, "indexSize");
            return (Criteria) this;
        }

        public Criteria andIndexSizeNotEqualTo(Integer value) {
            addCriterion("index_size <>", value, "indexSize");
            return (Criteria) this;
        }

        public Criteria andIndexSizeGreaterThan(Integer value) {
            addCriterion("index_size >", value, "indexSize");
            return (Criteria) this;
        }

        public Criteria andIndexSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("index_size >=", value, "indexSize");
            return (Criteria) this;
        }

        public Criteria andIndexSizeLessThan(Integer value) {
            addCriterion("index_size <", value, "indexSize");
            return (Criteria) this;
        }

        public Criteria andIndexSizeLessThanOrEqualTo(Integer value) {
            addCriterion("index_size <=", value, "indexSize");
            return (Criteria) this;
        }

        public Criteria andIndexSizeIn(List<Integer> values) {
            addCriterion("index_size in", values, "indexSize");
            return (Criteria) this;
        }

        public Criteria andIndexSizeNotIn(List<Integer> values) {
            addCriterion("index_size not in", values, "indexSize");
            return (Criteria) this;
        }

        public Criteria andIndexSizeBetween(Integer value1, Integer value2) {
            addCriterion("index_size between", value1, value2, "indexSize");
            return (Criteria) this;
        }

        public Criteria andIndexSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("index_size not between", value1, value2, "indexSize");
            return (Criteria) this;
        }

        public Criteria andCheckSizeIsNull() {
            addCriterion("check_size is null");
            return (Criteria) this;
        }

        public Criteria andCheckSizeIsNotNull() {
            addCriterion("check_size is not null");
            return (Criteria) this;
        }

        public Criteria andCheckSizeEqualTo(Integer value) {
            addCriterion("check_size =", value, "checkSize");
            return (Criteria) this;
        }

        public Criteria andCheckSizeNotEqualTo(Integer value) {
            addCriterion("check_size <>", value, "checkSize");
            return (Criteria) this;
        }

        public Criteria andCheckSizeGreaterThan(Integer value) {
            addCriterion("check_size >", value, "checkSize");
            return (Criteria) this;
        }

        public Criteria andCheckSizeGreaterThanOrEqualTo(Integer value) {
            addCriterion("check_size >=", value, "checkSize");
            return (Criteria) this;
        }

        public Criteria andCheckSizeLessThan(Integer value) {
            addCriterion("check_size <", value, "checkSize");
            return (Criteria) this;
        }

        public Criteria andCheckSizeLessThanOrEqualTo(Integer value) {
            addCriterion("check_size <=", value, "checkSize");
            return (Criteria) this;
        }

        public Criteria andCheckSizeIn(List<Integer> values) {
            addCriterion("check_size in", values, "checkSize");
            return (Criteria) this;
        }

        public Criteria andCheckSizeNotIn(List<Integer> values) {
            addCriterion("check_size not in", values, "checkSize");
            return (Criteria) this;
        }

        public Criteria andCheckSizeBetween(Integer value1, Integer value2) {
            addCriterion("check_size between", value1, value2, "checkSize");
            return (Criteria) this;
        }

        public Criteria andCheckSizeNotBetween(Integer value1, Integer value2) {
            addCriterion("check_size not between", value1, value2, "checkSize");
            return (Criteria) this;
        }

        public Criteria andMatchCntIsNull() {
            addCriterion("match_cnt is null");
            return (Criteria) this;
        }

        public Criteria andMatchCntIsNotNull() {
            addCriterion("match_cnt is not null");
            return (Criteria) this;
        }

        public Criteria andMatchCntEqualTo(Integer value) {
            addCriterion("match_cnt =", value, "matchCnt");
            return (Criteria) this;
        }

        public Criteria andMatchCntNotEqualTo(Integer value) {
            addCriterion("match_cnt <>", value, "matchCnt");
            return (Criteria) this;
        }

        public Criteria andMatchCntGreaterThan(Integer value) {
            addCriterion("match_cnt >", value, "matchCnt");
            return (Criteria) this;
        }

        public Criteria andMatchCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("match_cnt >=", value, "matchCnt");
            return (Criteria) this;
        }

        public Criteria andMatchCntLessThan(Integer value) {
            addCriterion("match_cnt <", value, "matchCnt");
            return (Criteria) this;
        }

        public Criteria andMatchCntLessThanOrEqualTo(Integer value) {
            addCriterion("match_cnt <=", value, "matchCnt");
            return (Criteria) this;
        }

        public Criteria andMatchCntIn(List<Integer> values) {
            addCriterion("match_cnt in", values, "matchCnt");
            return (Criteria) this;
        }

        public Criteria andMatchCntNotIn(List<Integer> values) {
            addCriterion("match_cnt not in", values, "matchCnt");
            return (Criteria) this;
        }

        public Criteria andMatchCntBetween(Integer value1, Integer value2) {
            addCriterion("match_cnt between", value1, value2, "matchCnt");
            return (Criteria) this;
        }

        public Criteria andMatchCntNotBetween(Integer value1, Integer value2) {
            addCriterion("match_cnt not between", value1, value2, "matchCnt");
            return (Criteria) this;
        }

        public Criteria andDismatchCntIsNull() {
            addCriterion("dismatch_cnt is null");
            return (Criteria) this;
        }

        public Criteria andDismatchCntIsNotNull() {
            addCriterion("dismatch_cnt is not null");
            return (Criteria) this;
        }

        public Criteria andDismatchCntEqualTo(Integer value) {
            addCriterion("dismatch_cnt =", value, "dismatchCnt");
            return (Criteria) this;
        }

        public Criteria andDismatchCntNotEqualTo(Integer value) {
            addCriterion("dismatch_cnt <>", value, "dismatchCnt");
            return (Criteria) this;
        }

        public Criteria andDismatchCntGreaterThan(Integer value) {
            addCriterion("dismatch_cnt >", value, "dismatchCnt");
            return (Criteria) this;
        }

        public Criteria andDismatchCntGreaterThanOrEqualTo(Integer value) {
            addCriterion("dismatch_cnt >=", value, "dismatchCnt");
            return (Criteria) this;
        }

        public Criteria andDismatchCntLessThan(Integer value) {
            addCriterion("dismatch_cnt <", value, "dismatchCnt");
            return (Criteria) this;
        }

        public Criteria andDismatchCntLessThanOrEqualTo(Integer value) {
            addCriterion("dismatch_cnt <=", value, "dismatchCnt");
            return (Criteria) this;
        }

        public Criteria andDismatchCntIn(List<Integer> values) {
            addCriterion("dismatch_cnt in", values, "dismatchCnt");
            return (Criteria) this;
        }

        public Criteria andDismatchCntNotIn(List<Integer> values) {
            addCriterion("dismatch_cnt not in", values, "dismatchCnt");
            return (Criteria) this;
        }

        public Criteria andDismatchCntBetween(Integer value1, Integer value2) {
            addCriterion("dismatch_cnt between", value1, value2, "dismatchCnt");
            return (Criteria) this;
        }

        public Criteria andDismatchCntNotBetween(Integer value1, Integer value2) {
            addCriterion("dismatch_cnt not between", value1, value2, "dismatchCnt");
            return (Criteria) this;
        }

        public Criteria andCostTimeIsNull() {
            addCriterion("cost_time is null");
            return (Criteria) this;
        }

        public Criteria andCostTimeIsNotNull() {
            addCriterion("cost_time is not null");
            return (Criteria) this;
        }

        public Criteria andCostTimeEqualTo(Integer value) {
            addCriterion("cost_time =", value, "costTime");
            return (Criteria) this;
        }

        public Criteria andCostTimeNotEqualTo(Integer value) {
            addCriterion("cost_time <>", value, "costTime");
            return (Criteria) this;
        }

        public Criteria andCostTimeGreaterThan(Integer value) {
            addCriterion("cost_time >", value, "costTime");
            return (Criteria) this;
        }

        public Criteria andCostTimeGreaterThanOrEqualTo(Integer value) {
            addCriterion("cost_time >=", value, "costTime");
            return (Criteria) this;
        }

        public Criteria andCostTimeLessThan(Integer value) {
            addCriterion("cost_time <", value, "costTime");
            return (Criteria) this;
        }

        public Criteria andCostTimeLessThanOrEqualTo(Integer value) {
            addCriterion("cost_time <=", value, "costTime");
            return (Criteria) this;
        }

        public Criteria andCostTimeIn(List<Integer> values) {
            addCriterion("cost_time in", values, "costTime");
            return (Criteria) this;
        }

        public Criteria andCostTimeNotIn(List<Integer> values) {
            addCriterion("cost_time not in", values, "costTime");
            return (Criteria) this;
        }

        public Criteria andCostTimeBetween(Integer value1, Integer value2) {
            addCriterion("cost_time between", value1, value2, "costTime");
            return (Criteria) this;
        }

        public Criteria andCostTimeNotBetween(Integer value1, Integer value2) {
            addCriterion("cost_time not between", value1, value2, "costTime");
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