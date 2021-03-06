package com.dreamy.domain.ipcool;

import com.dreamy.beans.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BookIndexHistoryConditions {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Page page;

    public BookIndexHistoryConditions() {
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

    public void setPage(Page page) {
        this.page = page;
    }

    public Page getPage() {
        return this.page;
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

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andBookIdIsNull() {
            addCriterion("book_id is null");
            return (Criteria) this;
        }

        public Criteria andBookIdIsNotNull() {
            addCriterion("book_id is not null");
            return (Criteria) this;
        }

        public Criteria andBookIdEqualTo(Integer value) {
            addCriterion("book_id =", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotEqualTo(Integer value) {
            addCriterion("book_id <>", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThan(Integer value) {
            addCriterion("book_id >", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("book_id >=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThan(Integer value) {
            addCriterion("book_id <", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdLessThanOrEqualTo(Integer value) {
            addCriterion("book_id <=", value, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdIn(List<Integer> values) {
            addCriterion("book_id in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotIn(List<Integer> values) {
            addCriterion("book_id not in", values, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdBetween(Integer value1, Integer value2) {
            addCriterion("book_id between", value1, value2, "bookId");
            return (Criteria) this;
        }

        public Criteria andBookIdNotBetween(Integer value1, Integer value2) {
            addCriterion("book_id not between", value1, value2, "bookId");
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

        public Criteria andStatusEqualTo(Integer value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(Integer value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(Integer value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(Integer value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(Integer value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(Integer value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<Integer> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<Integer> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(Integer value1, Integer value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(Integer value1, Integer value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andCompositeIndexIsNull() {
            addCriterion("composite_index is null");
            return (Criteria) this;
        }

        public Criteria andCompositeIndexIsNotNull() {
            addCriterion("composite_index is not null");
            return (Criteria) this;
        }

        public Criteria andCompositeIndexEqualTo(Integer value) {
            addCriterion("composite_index =", value, "compositeIndex");
            return (Criteria) this;
        }

        public Criteria andCompositeIndexNotEqualTo(Integer value) {
            addCriterion("composite_index <>", value, "compositeIndex");
            return (Criteria) this;
        }

        public Criteria andCompositeIndexGreaterThan(Integer value) {
            addCriterion("composite_index >", value, "compositeIndex");
            return (Criteria) this;
        }

        public Criteria andCompositeIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("composite_index >=", value, "compositeIndex");
            return (Criteria) this;
        }

        public Criteria andCompositeIndexLessThan(Integer value) {
            addCriterion("composite_index <", value, "compositeIndex");
            return (Criteria) this;
        }

        public Criteria andCompositeIndexLessThanOrEqualTo(Integer value) {
            addCriterion("composite_index <=", value, "compositeIndex");
            return (Criteria) this;
        }

        public Criteria andCompositeIndexIn(List<Integer> values) {
            addCriterion("composite_index in", values, "compositeIndex");
            return (Criteria) this;
        }

        public Criteria andCompositeIndexNotIn(List<Integer> values) {
            addCriterion("composite_index not in", values, "compositeIndex");
            return (Criteria) this;
        }

        public Criteria andCompositeIndexBetween(Integer value1, Integer value2) {
            addCriterion("composite_index between", value1, value2, "compositeIndex");
            return (Criteria) this;
        }

        public Criteria andCompositeIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("composite_index not between", value1, value2, "compositeIndex");
            return (Criteria) this;
        }

        public Criteria andHotIndexIsNull() {
            addCriterion("hot_index is null");
            return (Criteria) this;
        }

        public Criteria andHotIndexIsNotNull() {
            addCriterion("hot_index is not null");
            return (Criteria) this;
        }

        public Criteria andHotIndexEqualTo(Integer value) {
            addCriterion("hot_index =", value, "hotIndex");
            return (Criteria) this;
        }

        public Criteria andHotIndexNotEqualTo(Integer value) {
            addCriterion("hot_index <>", value, "hotIndex");
            return (Criteria) this;
        }

        public Criteria andHotIndexGreaterThan(Integer value) {
            addCriterion("hot_index >", value, "hotIndex");
            return (Criteria) this;
        }

        public Criteria andHotIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("hot_index >=", value, "hotIndex");
            return (Criteria) this;
        }

        public Criteria andHotIndexLessThan(Integer value) {
            addCriterion("hot_index <", value, "hotIndex");
            return (Criteria) this;
        }

        public Criteria andHotIndexLessThanOrEqualTo(Integer value) {
            addCriterion("hot_index <=", value, "hotIndex");
            return (Criteria) this;
        }

        public Criteria andHotIndexIn(List<Integer> values) {
            addCriterion("hot_index in", values, "hotIndex");
            return (Criteria) this;
        }

        public Criteria andHotIndexNotIn(List<Integer> values) {
            addCriterion("hot_index not in", values, "hotIndex");
            return (Criteria) this;
        }

        public Criteria andHotIndexBetween(Integer value1, Integer value2) {
            addCriterion("hot_index between", value1, value2, "hotIndex");
            return (Criteria) this;
        }

        public Criteria andHotIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("hot_index not between", value1, value2, "hotIndex");
            return (Criteria) this;
        }

        public Criteria andActivityIndexIsNull() {
            addCriterion("activity_index is null");
            return (Criteria) this;
        }

        public Criteria andActivityIndexIsNotNull() {
            addCriterion("activity_index is not null");
            return (Criteria) this;
        }

        public Criteria andActivityIndexEqualTo(Integer value) {
            addCriterion("activity_index =", value, "activityIndex");
            return (Criteria) this;
        }

        public Criteria andActivityIndexNotEqualTo(Integer value) {
            addCriterion("activity_index <>", value, "activityIndex");
            return (Criteria) this;
        }

        public Criteria andActivityIndexGreaterThan(Integer value) {
            addCriterion("activity_index >", value, "activityIndex");
            return (Criteria) this;
        }

        public Criteria andActivityIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("activity_index >=", value, "activityIndex");
            return (Criteria) this;
        }

        public Criteria andActivityIndexLessThan(Integer value) {
            addCriterion("activity_index <", value, "activityIndex");
            return (Criteria) this;
        }

        public Criteria andActivityIndexLessThanOrEqualTo(Integer value) {
            addCriterion("activity_index <=", value, "activityIndex");
            return (Criteria) this;
        }

        public Criteria andActivityIndexIn(List<Integer> values) {
            addCriterion("activity_index in", values, "activityIndex");
            return (Criteria) this;
        }

        public Criteria andActivityIndexNotIn(List<Integer> values) {
            addCriterion("activity_index not in", values, "activityIndex");
            return (Criteria) this;
        }

        public Criteria andActivityIndexBetween(Integer value1, Integer value2) {
            addCriterion("activity_index between", value1, value2, "activityIndex");
            return (Criteria) this;
        }

        public Criteria andActivityIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("activity_index not between", value1, value2, "activityIndex");
            return (Criteria) this;
        }

        public Criteria andPropagateIndexIsNull() {
            addCriterion("propagate_index is null");
            return (Criteria) this;
        }

        public Criteria andPropagateIndexIsNotNull() {
            addCriterion("propagate_index is not null");
            return (Criteria) this;
        }

        public Criteria andPropagateIndexEqualTo(Integer value) {
            addCriterion("propagate_index =", value, "propagateIndex");
            return (Criteria) this;
        }

        public Criteria andPropagateIndexNotEqualTo(Integer value) {
            addCriterion("propagate_index <>", value, "propagateIndex");
            return (Criteria) this;
        }

        public Criteria andPropagateIndexGreaterThan(Integer value) {
            addCriterion("propagate_index >", value, "propagateIndex");
            return (Criteria) this;
        }

        public Criteria andPropagateIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("propagate_index >=", value, "propagateIndex");
            return (Criteria) this;
        }

        public Criteria andPropagateIndexLessThan(Integer value) {
            addCriterion("propagate_index <", value, "propagateIndex");
            return (Criteria) this;
        }

        public Criteria andPropagateIndexLessThanOrEqualTo(Integer value) {
            addCriterion("propagate_index <=", value, "propagateIndex");
            return (Criteria) this;
        }

        public Criteria andPropagateIndexIn(List<Integer> values) {
            addCriterion("propagate_index in", values, "propagateIndex");
            return (Criteria) this;
        }

        public Criteria andPropagateIndexNotIn(List<Integer> values) {
            addCriterion("propagate_index not in", values, "propagateIndex");
            return (Criteria) this;
        }

        public Criteria andPropagateIndexBetween(Integer value1, Integer value2) {
            addCriterion("propagate_index between", value1, value2, "propagateIndex");
            return (Criteria) this;
        }

        public Criteria andPropagateIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("propagate_index not between", value1, value2, "propagateIndex");
            return (Criteria) this;
        }

        public Criteria andDevelopIndexIsNull() {
            addCriterion("develop_index is null");
            return (Criteria) this;
        }

        public Criteria andDevelopIndexIsNotNull() {
            addCriterion("develop_index is not null");
            return (Criteria) this;
        }

        public Criteria andDevelopIndexEqualTo(Integer value) {
            addCriterion("develop_index =", value, "developIndex");
            return (Criteria) this;
        }

        public Criteria andDevelopIndexNotEqualTo(Integer value) {
            addCriterion("develop_index <>", value, "developIndex");
            return (Criteria) this;
        }

        public Criteria andDevelopIndexGreaterThan(Integer value) {
            addCriterion("develop_index >", value, "developIndex");
            return (Criteria) this;
        }

        public Criteria andDevelopIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("develop_index >=", value, "developIndex");
            return (Criteria) this;
        }

        public Criteria andDevelopIndexLessThan(Integer value) {
            addCriterion("develop_index <", value, "developIndex");
            return (Criteria) this;
        }

        public Criteria andDevelopIndexLessThanOrEqualTo(Integer value) {
            addCriterion("develop_index <=", value, "developIndex");
            return (Criteria) this;
        }

        public Criteria andDevelopIndexIn(List<Integer> values) {
            addCriterion("develop_index in", values, "developIndex");
            return (Criteria) this;
        }

        public Criteria andDevelopIndexNotIn(List<Integer> values) {
            addCriterion("develop_index not in", values, "developIndex");
            return (Criteria) this;
        }

        public Criteria andDevelopIndexBetween(Integer value1, Integer value2) {
            addCriterion("develop_index between", value1, value2, "developIndex");
            return (Criteria) this;
        }

        public Criteria andDevelopIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("develop_index not between", value1, value2, "developIndex");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNull() {
            addCriterion("created_at is null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIsNotNull() {
            addCriterion("created_at is not null");
            return (Criteria) this;
        }

        public Criteria andCreatedAtEqualTo(Date value) {
            addCriterion("created_at =", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotEqualTo(Date value) {
            addCriterion("created_at <>", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThan(Date value) {
            addCriterion("created_at >", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("created_at >=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThan(Date value) {
            addCriterion("created_at <", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtLessThanOrEqualTo(Date value) {
            addCriterion("created_at <=", value, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtIn(List<Date> values) {
            addCriterion("created_at in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotIn(List<Date> values) {
            addCriterion("created_at not in", values, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtBetween(Date value1, Date value2) {
            addCriterion("created_at between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andCreatedAtNotBetween(Date value1, Date value2) {
            addCriterion("created_at not between", value1, value2, "createdAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNull() {
            addCriterion("updated_at is null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIsNotNull() {
            addCriterion("updated_at is not null");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtEqualTo(Date value) {
            addCriterion("updated_at =", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotEqualTo(Date value) {
            addCriterion("updated_at <>", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThan(Date value) {
            addCriterion("updated_at >", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtGreaterThanOrEqualTo(Date value) {
            addCriterion("updated_at >=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThan(Date value) {
            addCriterion("updated_at <", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtLessThanOrEqualTo(Date value) {
            addCriterion("updated_at <=", value, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtIn(List<Date> values) {
            addCriterion("updated_at in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotIn(List<Date> values) {
            addCriterion("updated_at not in", values, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtBetween(Date value1, Date value2) {
            addCriterion("updated_at between", value1, value2, "updatedAt");
            return (Criteria) this;
        }

        public Criteria andUpdatedAtNotBetween(Date value1, Date value2) {
            addCriterion("updated_at not between", value1, value2, "updatedAt");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }

        public Criteria addByMap(Map<String, Object> map) {
             for (Map.Entry<String, Object> entry : map.entrySet()) {
                if(entry.getValue().toString().startsWith("%")){
                    addCriterion(entry.getKey()+" like",entry.getValue(),null);
                }else{
                    addCriterion(entry.getKey()+" =",entry.getValue(),null);
                }
            }
            return this;
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