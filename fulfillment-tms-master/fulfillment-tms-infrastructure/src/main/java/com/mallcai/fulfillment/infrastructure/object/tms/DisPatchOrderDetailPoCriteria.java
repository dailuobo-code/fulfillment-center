package com.mallcai.fulfillment.infrastructure.object.tms;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DisPatchOrderDetailPoCriteria {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public DisPatchOrderDetailPoCriteria() {
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

        public Criteria andSkuCodeIsNull() {
            addCriterion("sku_code is null");
            return (Criteria) this;
        }

        public Criteria andSkuCodeIsNotNull() {
            addCriterion("sku_code is not null");
            return (Criteria) this;
        }

        public Criteria andSkuCodeEqualTo(String value) {
            addCriterion("sku_code =", value, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeNotEqualTo(String value) {
            addCriterion("sku_code <>", value, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeGreaterThan(String value) {
            addCriterion("sku_code >", value, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeGreaterThanOrEqualTo(String value) {
            addCriterion("sku_code >=", value, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeLessThan(String value) {
            addCriterion("sku_code <", value, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeLessThanOrEqualTo(String value) {
            addCriterion("sku_code <=", value, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeLike(String value) {
            addCriterion("sku_code like", value, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeNotLike(String value) {
            addCriterion("sku_code not like", value, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeIn(List<String> values) {
            addCriterion("sku_code in", values, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeNotIn(List<String> values) {
            addCriterion("sku_code not in", values, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeBetween(String value1, String value2) {
            addCriterion("sku_code between", value1, value2, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuCodeNotBetween(String value1, String value2) {
            addCriterion("sku_code not between", value1, value2, "skuCode");
            return (Criteria) this;
        }

        public Criteria andSkuNameIsNull() {
            addCriterion("sku_name is null");
            return (Criteria) this;
        }

        public Criteria andSkuNameIsNotNull() {
            addCriterion("sku_name is not null");
            return (Criteria) this;
        }

        public Criteria andSkuNameEqualTo(String value) {
            addCriterion("sku_name =", value, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameNotEqualTo(String value) {
            addCriterion("sku_name <>", value, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameGreaterThan(String value) {
            addCriterion("sku_name >", value, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameGreaterThanOrEqualTo(String value) {
            addCriterion("sku_name >=", value, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameLessThan(String value) {
            addCriterion("sku_name <", value, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameLessThanOrEqualTo(String value) {
            addCriterion("sku_name <=", value, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameLike(String value) {
            addCriterion("sku_name like", value, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameNotLike(String value) {
            addCriterion("sku_name not like", value, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameIn(List<String> values) {
            addCriterion("sku_name in", values, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameNotIn(List<String> values) {
            addCriterion("sku_name not in", values, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameBetween(String value1, String value2) {
            addCriterion("sku_name between", value1, value2, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuNameNotBetween(String value1, String value2) {
            addCriterion("sku_name not between", value1, value2, "skuName");
            return (Criteria) this;
        }

        public Criteria andSkuCountIsNull() {
            addCriterion("sku_count is null");
            return (Criteria) this;
        }

        public Criteria andSkuCountIsNotNull() {
            addCriterion("sku_count is not null");
            return (Criteria) this;
        }

        public Criteria andSkuCountEqualTo(BigDecimal value) {
            addCriterion("sku_count =", value, "skuCount");
            return (Criteria) this;
        }

        public Criteria andSkuCountNotEqualTo(BigDecimal value) {
            addCriterion("sku_count <>", value, "skuCount");
            return (Criteria) this;
        }

        public Criteria andSkuCountGreaterThan(BigDecimal value) {
            addCriterion("sku_count >", value, "skuCount");
            return (Criteria) this;
        }

        public Criteria andSkuCountGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sku_count >=", value, "skuCount");
            return (Criteria) this;
        }

        public Criteria andSkuCountLessThan(BigDecimal value) {
            addCriterion("sku_count <", value, "skuCount");
            return (Criteria) this;
        }

        public Criteria andSkuCountLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sku_count <=", value, "skuCount");
            return (Criteria) this;
        }

        public Criteria andSkuCountIn(List<BigDecimal> values) {
            addCriterion("sku_count in", values, "skuCount");
            return (Criteria) this;
        }

        public Criteria andSkuCountNotIn(List<BigDecimal> values) {
            addCriterion("sku_count not in", values, "skuCount");
            return (Criteria) this;
        }

        public Criteria andSkuCountBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sku_count between", value1, value2, "skuCount");
            return (Criteria) this;
        }

        public Criteria andSkuCountNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sku_count not between", value1, value2, "skuCount");
            return (Criteria) this;
        }

        public Criteria andSkuWeightIsNull() {
            addCriterion("sku_weight is null");
            return (Criteria) this;
        }

        public Criteria andSkuWeightIsNotNull() {
            addCriterion("sku_weight is not null");
            return (Criteria) this;
        }

        public Criteria andSkuWeightEqualTo(BigDecimal value) {
            addCriterion("sku_weight =", value, "skuWeight");
            return (Criteria) this;
        }

        public Criteria andSkuWeightNotEqualTo(BigDecimal value) {
            addCriterion("sku_weight <>", value, "skuWeight");
            return (Criteria) this;
        }

        public Criteria andSkuWeightGreaterThan(BigDecimal value) {
            addCriterion("sku_weight >", value, "skuWeight");
            return (Criteria) this;
        }

        public Criteria andSkuWeightGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sku_weight >=", value, "skuWeight");
            return (Criteria) this;
        }

        public Criteria andSkuWeightLessThan(BigDecimal value) {
            addCriterion("sku_weight <", value, "skuWeight");
            return (Criteria) this;
        }

        public Criteria andSkuWeightLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sku_weight <=", value, "skuWeight");
            return (Criteria) this;
        }

        public Criteria andSkuWeightIn(List<BigDecimal> values) {
            addCriterion("sku_weight in", values, "skuWeight");
            return (Criteria) this;
        }

        public Criteria andSkuWeightNotIn(List<BigDecimal> values) {
            addCriterion("sku_weight not in", values, "skuWeight");
            return (Criteria) this;
        }

        public Criteria andSkuWeightBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sku_weight between", value1, value2, "skuWeight");
            return (Criteria) this;
        }

        public Criteria andSkuWeightNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sku_weight not between", value1, value2, "skuWeight");
            return (Criteria) this;
        }

        public Criteria andSkuUnitIsNull() {
            addCriterion("sku_unit is null");
            return (Criteria) this;
        }

        public Criteria andSkuUnitIsNotNull() {
            addCriterion("sku_unit is not null");
            return (Criteria) this;
        }

        public Criteria andSkuUnitEqualTo(String value) {
            addCriterion("sku_unit =", value, "skuUnit");
            return (Criteria) this;
        }

        public Criteria andSkuUnitNotEqualTo(String value) {
            addCriterion("sku_unit <>", value, "skuUnit");
            return (Criteria) this;
        }

        public Criteria andSkuUnitGreaterThan(String value) {
            addCriterion("sku_unit >", value, "skuUnit");
            return (Criteria) this;
        }

        public Criteria andSkuUnitGreaterThanOrEqualTo(String value) {
            addCriterion("sku_unit >=", value, "skuUnit");
            return (Criteria) this;
        }

        public Criteria andSkuUnitLessThan(String value) {
            addCriterion("sku_unit <", value, "skuUnit");
            return (Criteria) this;
        }

        public Criteria andSkuUnitLessThanOrEqualTo(String value) {
            addCriterion("sku_unit <=", value, "skuUnit");
            return (Criteria) this;
        }

        public Criteria andSkuUnitLike(String value) {
            addCriterion("sku_unit like", value, "skuUnit");
            return (Criteria) this;
        }

        public Criteria andSkuUnitNotLike(String value) {
            addCriterion("sku_unit not like", value, "skuUnit");
            return (Criteria) this;
        }

        public Criteria andSkuUnitIn(List<String> values) {
            addCriterion("sku_unit in", values, "skuUnit");
            return (Criteria) this;
        }

        public Criteria andSkuUnitNotIn(List<String> values) {
            addCriterion("sku_unit not in", values, "skuUnit");
            return (Criteria) this;
        }

        public Criteria andSkuUnitBetween(String value1, String value2) {
            addCriterion("sku_unit between", value1, value2, "skuUnit");
            return (Criteria) this;
        }

        public Criteria andSkuUnitNotBetween(String value1, String value2) {
            addCriterion("sku_unit not between", value1, value2, "skuUnit");
            return (Criteria) this;
        }

        public Criteria andSkuVolumeIsNull() {
            addCriterion("sku_volume is null");
            return (Criteria) this;
        }

        public Criteria andSkuVolumeIsNotNull() {
            addCriterion("sku_volume is not null");
            return (Criteria) this;
        }

        public Criteria andSkuVolumeEqualTo(BigDecimal value) {
            addCriterion("sku_volume =", value, "skuVolume");
            return (Criteria) this;
        }

        public Criteria andSkuVolumeNotEqualTo(BigDecimal value) {
            addCriterion("sku_volume <>", value, "skuVolume");
            return (Criteria) this;
        }

        public Criteria andSkuVolumeGreaterThan(BigDecimal value) {
            addCriterion("sku_volume >", value, "skuVolume");
            return (Criteria) this;
        }

        public Criteria andSkuVolumeGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("sku_volume >=", value, "skuVolume");
            return (Criteria) this;
        }

        public Criteria andSkuVolumeLessThan(BigDecimal value) {
            addCriterion("sku_volume <", value, "skuVolume");
            return (Criteria) this;
        }

        public Criteria andSkuVolumeLessThanOrEqualTo(BigDecimal value) {
            addCriterion("sku_volume <=", value, "skuVolume");
            return (Criteria) this;
        }

        public Criteria andSkuVolumeIn(List<BigDecimal> values) {
            addCriterion("sku_volume in", values, "skuVolume");
            return (Criteria) this;
        }

        public Criteria andSkuVolumeNotIn(List<BigDecimal> values) {
            addCriterion("sku_volume not in", values, "skuVolume");
            return (Criteria) this;
        }

        public Criteria andSkuVolumeBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sku_volume between", value1, value2, "skuVolume");
            return (Criteria) this;
        }

        public Criteria andSkuVolumeNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("sku_volume not between", value1, value2, "skuVolume");
            return (Criteria) this;
        }

        public Criteria andSkuTypeIsNull() {
            addCriterion("sku_type is null");
            return (Criteria) this;
        }

        public Criteria andSkuTypeIsNotNull() {
            addCriterion("sku_type is not null");
            return (Criteria) this;
        }

        public Criteria andSkuTypeEqualTo(String value) {
            addCriterion("sku_type =", value, "skuType");
            return (Criteria) this;
        }

        public Criteria andSkuTypeNotEqualTo(String value) {
            addCriterion("sku_type <>", value, "skuType");
            return (Criteria) this;
        }

        public Criteria andSkuTypeGreaterThan(String value) {
            addCriterion("sku_type >", value, "skuType");
            return (Criteria) this;
        }

        public Criteria andSkuTypeGreaterThanOrEqualTo(String value) {
            addCriterion("sku_type >=", value, "skuType");
            return (Criteria) this;
        }

        public Criteria andSkuTypeLessThan(String value) {
            addCriterion("sku_type <", value, "skuType");
            return (Criteria) this;
        }

        public Criteria andSkuTypeLessThanOrEqualTo(String value) {
            addCriterion("sku_type <=", value, "skuType");
            return (Criteria) this;
        }

        public Criteria andSkuTypeLike(String value) {
            addCriterion("sku_type like", value, "skuType");
            return (Criteria) this;
        }

        public Criteria andSkuTypeNotLike(String value) {
            addCriterion("sku_type not like", value, "skuType");
            return (Criteria) this;
        }

        public Criteria andSkuTypeIn(List<String> values) {
            addCriterion("sku_type in", values, "skuType");
            return (Criteria) this;
        }

        public Criteria andSkuTypeNotIn(List<String> values) {
            addCriterion("sku_type not in", values, "skuType");
            return (Criteria) this;
        }

        public Criteria andSkuTypeBetween(String value1, String value2) {
            addCriterion("sku_type between", value1, value2, "skuType");
            return (Criteria) this;
        }

        public Criteria andSkuTypeNotBetween(String value1, String value2) {
            addCriterion("sku_type not between", value1, value2, "skuType");
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