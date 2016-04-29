package com.dreamy.domain.ipcool;

import com.dreamy.beans.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class BookViewConditions {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Page page;

    public BookViewConditions() {
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

        public Criteria andNameIsNull() {
            addCriterion("name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNull() {
            addCriterion("author is null");
            return (Criteria) this;
        }

        public Criteria andAuthorIsNotNull() {
            addCriterion("author is not null");
            return (Criteria) this;
        }

        public Criteria andAuthorEqualTo(String value) {
            addCriterion("author =", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotEqualTo(String value) {
            addCriterion("author <>", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThan(String value) {
            addCriterion("author >", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("author >=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThan(String value) {
            addCriterion("author <", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLessThanOrEqualTo(String value) {
            addCriterion("author <=", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorLike(String value) {
            addCriterion("author like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotLike(String value) {
            addCriterion("author not like", value, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorIn(List<String> values) {
            addCriterion("author in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotIn(List<String> values) {
            addCriterion("author not in", values, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorBetween(String value1, String value2) {
            addCriterion("author between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andAuthorNotBetween(String value1, String value2) {
            addCriterion("author not between", value1, value2, "author");
            return (Criteria) this;
        }

        public Criteria andScoreIsNull() {
            addCriterion("score is null");
            return (Criteria) this;
        }

        public Criteria andScoreIsNotNull() {
            addCriterion("score is not null");
            return (Criteria) this;
        }

        public Criteria andScoreEqualTo(Integer value) {
            addCriterion("score =", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotEqualTo(Integer value) {
            addCriterion("score <>", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThan(Integer value) {
            addCriterion("score >", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreGreaterThanOrEqualTo(Integer value) {
            addCriterion("score >=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThan(Integer value) {
            addCriterion("score <", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreLessThanOrEqualTo(Integer value) {
            addCriterion("score <=", value, "score");
            return (Criteria) this;
        }

        public Criteria andScoreIn(List<Integer> values) {
            addCriterion("score in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotIn(List<Integer> values) {
            addCriterion("score not in", values, "score");
            return (Criteria) this;
        }

        public Criteria andScoreBetween(Integer value1, Integer value2) {
            addCriterion("score between", value1, value2, "score");
            return (Criteria) this;
        }

        public Criteria andScoreNotBetween(Integer value1, Integer value2) {
            addCriterion("score not between", value1, value2, "score");
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
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

        public Criteria andReputationIndexIsNull() {
            addCriterion("reputation_index is null");
            return (Criteria) this;
        }

        public Criteria andReputationIndexIsNotNull() {
            addCriterion("reputation_index is not null");
            return (Criteria) this;
        }

        public Criteria andReputationIndexEqualTo(Integer value) {
            addCriterion("reputation_index =", value, "reputationIndex");
            return (Criteria) this;
        }

        public Criteria andReputationIndexNotEqualTo(Integer value) {
            addCriterion("reputation_index <>", value, "reputationIndex");
            return (Criteria) this;
        }

        public Criteria andReputationIndexGreaterThan(Integer value) {
            addCriterion("reputation_index >", value, "reputationIndex");
            return (Criteria) this;
        }

        public Criteria andReputationIndexGreaterThanOrEqualTo(Integer value) {
            addCriterion("reputation_index >=", value, "reputationIndex");
            return (Criteria) this;
        }

        public Criteria andReputationIndexLessThan(Integer value) {
            addCriterion("reputation_index <", value, "reputationIndex");
            return (Criteria) this;
        }

        public Criteria andReputationIndexLessThanOrEqualTo(Integer value) {
            addCriterion("reputation_index <=", value, "reputationIndex");
            return (Criteria) this;
        }

        public Criteria andReputationIndexIn(List<Integer> values) {
            addCriterion("reputation_index in", values, "reputationIndex");
            return (Criteria) this;
        }

        public Criteria andReputationIndexNotIn(List<Integer> values) {
            addCriterion("reputation_index not in", values, "reputationIndex");
            return (Criteria) this;
        }

        public Criteria andReputationIndexBetween(Integer value1, Integer value2) {
            addCriterion("reputation_index between", value1, value2, "reputationIndex");
            return (Criteria) this;
        }

        public Criteria andReputationIndexNotBetween(Integer value1, Integer value2) {
            addCriterion("reputation_index not between", value1, value2, "reputationIndex");
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

        public Criteria andImageUrlIsNull() {
            addCriterion("image_url is null");
            return (Criteria) this;
        }

        public Criteria andImageUrlIsNotNull() {
            addCriterion("image_url is not null");
            return (Criteria) this;
        }

        public Criteria andImageUrlEqualTo(String value) {
            addCriterion("image_url =", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotEqualTo(String value) {
            addCriterion("image_url <>", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThan(String value) {
            addCriterion("image_url >", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlGreaterThanOrEqualTo(String value) {
            addCriterion("image_url >=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThan(String value) {
            addCriterion("image_url <", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLessThanOrEqualTo(String value) {
            addCriterion("image_url <=", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlLike(String value) {
            addCriterion("image_url like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotLike(String value) {
            addCriterion("image_url not like", value, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlIn(List<String> values) {
            addCriterion("image_url in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotIn(List<String> values) {
            addCriterion("image_url not in", values, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlBetween(String value1, String value2) {
            addCriterion("image_url between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andImageUrlNotBetween(String value1, String value2) {
            addCriterion("image_url not between", value1, value2, "imageUrl");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andAuthorLikeInsensitive(String value) {
            addCriterion("upper(author) like", value.toUpperCase(), "author");
            return (Criteria) this;
        }

        public Criteria andImageUrlLikeInsensitive(String value) {
            addCriterion("upper(image_url) like", value.toUpperCase(), "imageUrl");
            return (Criteria) this;
        }

        public Criteria andRemarkLikeInsensitive(String value) {
            addCriterion("upper(remark) like", value.toUpperCase(), "remark");
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