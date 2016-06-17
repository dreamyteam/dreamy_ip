package com.dreamy.domain.ipcool;

import com.dreamy.beans.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class IpBookConditions {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Page page;

    public IpBookConditions() {
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

        public Criteria andCodeIsNull() {
            addCriterion("code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
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

        public Criteria andIndexKeywordIsNull() {
            addCriterion("index_keyword is null");
            return (Criteria) this;
        }

        public Criteria andIndexKeywordIsNotNull() {
            addCriterion("index_keyword is not null");
            return (Criteria) this;
        }

        public Criteria andIndexKeywordEqualTo(String value) {
            addCriterion("index_keyword =", value, "indexKeyword");
            return (Criteria) this;
        }

        public Criteria andIndexKeywordNotEqualTo(String value) {
            addCriterion("index_keyword <>", value, "indexKeyword");
            return (Criteria) this;
        }

        public Criteria andIndexKeywordGreaterThan(String value) {
            addCriterion("index_keyword >", value, "indexKeyword");
            return (Criteria) this;
        }

        public Criteria andIndexKeywordGreaterThanOrEqualTo(String value) {
            addCriterion("index_keyword >=", value, "indexKeyword");
            return (Criteria) this;
        }

        public Criteria andIndexKeywordLessThan(String value) {
            addCriterion("index_keyword <", value, "indexKeyword");
            return (Criteria) this;
        }

        public Criteria andIndexKeywordLessThanOrEqualTo(String value) {
            addCriterion("index_keyword <=", value, "indexKeyword");
            return (Criteria) this;
        }

        public Criteria andIndexKeywordLike(String value) {
            addCriterion("index_keyword like", value, "indexKeyword");
            return (Criteria) this;
        }

        public Criteria andIndexKeywordNotLike(String value) {
            addCriterion("index_keyword not like", value, "indexKeyword");
            return (Criteria) this;
        }

        public Criteria andIndexKeywordIn(List<String> values) {
            addCriterion("index_keyword in", values, "indexKeyword");
            return (Criteria) this;
        }

        public Criteria andIndexKeywordNotIn(List<String> values) {
            addCriterion("index_keyword not in", values, "indexKeyword");
            return (Criteria) this;
        }

        public Criteria andIndexKeywordBetween(String value1, String value2) {
            addCriterion("index_keyword between", value1, value2, "indexKeyword");
            return (Criteria) this;
        }

        public Criteria andIndexKeywordNotBetween(String value1, String value2) {
            addCriterion("index_keyword not between", value1, value2, "indexKeyword");
            return (Criteria) this;
        }

        public Criteria andTiebaKeywordIsNull() {
            addCriterion("tieba_keyword is null");
            return (Criteria) this;
        }

        public Criteria andTiebaKeywordIsNotNull() {
            addCriterion("tieba_keyword is not null");
            return (Criteria) this;
        }

        public Criteria andTiebaKeywordEqualTo(String value) {
            addCriterion("tieba_keyword =", value, "tiebaKeyword");
            return (Criteria) this;
        }

        public Criteria andTiebaKeywordNotEqualTo(String value) {
            addCriterion("tieba_keyword <>", value, "tiebaKeyword");
            return (Criteria) this;
        }

        public Criteria andTiebaKeywordGreaterThan(String value) {
            addCriterion("tieba_keyword >", value, "tiebaKeyword");
            return (Criteria) this;
        }

        public Criteria andTiebaKeywordGreaterThanOrEqualTo(String value) {
            addCriterion("tieba_keyword >=", value, "tiebaKeyword");
            return (Criteria) this;
        }

        public Criteria andTiebaKeywordLessThan(String value) {
            addCriterion("tieba_keyword <", value, "tiebaKeyword");
            return (Criteria) this;
        }

        public Criteria andTiebaKeywordLessThanOrEqualTo(String value) {
            addCriterion("tieba_keyword <=", value, "tiebaKeyword");
            return (Criteria) this;
        }

        public Criteria andTiebaKeywordLike(String value) {
            addCriterion("tieba_keyword like", value, "tiebaKeyword");
            return (Criteria) this;
        }

        public Criteria andTiebaKeywordNotLike(String value) {
            addCriterion("tieba_keyword not like", value, "tiebaKeyword");
            return (Criteria) this;
        }

        public Criteria andTiebaKeywordIn(List<String> values) {
            addCriterion("tieba_keyword in", values, "tiebaKeyword");
            return (Criteria) this;
        }

        public Criteria andTiebaKeywordNotIn(List<String> values) {
            addCriterion("tieba_keyword not in", values, "tiebaKeyword");
            return (Criteria) this;
        }

        public Criteria andTiebaKeywordBetween(String value1, String value2) {
            addCriterion("tieba_keyword between", value1, value2, "tiebaKeyword");
            return (Criteria) this;
        }

        public Criteria andTiebaKeywordNotBetween(String value1, String value2) {
            addCriterion("tieba_keyword not between", value1, value2, "tiebaKeyword");
            return (Criteria) this;
        }

        public Criteria andNewsKeywordIsNull() {
            addCriterion("news_keyword is null");
            return (Criteria) this;
        }

        public Criteria andNewsKeywordIsNotNull() {
            addCriterion("news_keyword is not null");
            return (Criteria) this;
        }

        public Criteria andNewsKeywordEqualTo(String value) {
            addCriterion("news_keyword =", value, "newsKeyword");
            return (Criteria) this;
        }

        public Criteria andNewsKeywordNotEqualTo(String value) {
            addCriterion("news_keyword <>", value, "newsKeyword");
            return (Criteria) this;
        }

        public Criteria andNewsKeywordGreaterThan(String value) {
            addCriterion("news_keyword >", value, "newsKeyword");
            return (Criteria) this;
        }

        public Criteria andNewsKeywordGreaterThanOrEqualTo(String value) {
            addCriterion("news_keyword >=", value, "newsKeyword");
            return (Criteria) this;
        }

        public Criteria andNewsKeywordLessThan(String value) {
            addCriterion("news_keyword <", value, "newsKeyword");
            return (Criteria) this;
        }

        public Criteria andNewsKeywordLessThanOrEqualTo(String value) {
            addCriterion("news_keyword <=", value, "newsKeyword");
            return (Criteria) this;
        }

        public Criteria andNewsKeywordLike(String value) {
            addCriterion("news_keyword like", value, "newsKeyword");
            return (Criteria) this;
        }

        public Criteria andNewsKeywordNotLike(String value) {
            addCriterion("news_keyword not like", value, "newsKeyword");
            return (Criteria) this;
        }

        public Criteria andNewsKeywordIn(List<String> values) {
            addCriterion("news_keyword in", values, "newsKeyword");
            return (Criteria) this;
        }

        public Criteria andNewsKeywordNotIn(List<String> values) {
            addCriterion("news_keyword not in", values, "newsKeyword");
            return (Criteria) this;
        }

        public Criteria andNewsKeywordBetween(String value1, String value2) {
            addCriterion("news_keyword between", value1, value2, "newsKeyword");
            return (Criteria) this;
        }

        public Criteria andNewsKeywordNotBetween(String value1, String value2) {
            addCriterion("news_keyword not between", value1, value2, "newsKeyword");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordIsNull() {
            addCriterion("search_keyword is null");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordIsNotNull() {
            addCriterion("search_keyword is not null");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordEqualTo(String value) {
            addCriterion("search_keyword =", value, "searchKeyword");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordNotEqualTo(String value) {
            addCriterion("search_keyword <>", value, "searchKeyword");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordGreaterThan(String value) {
            addCriterion("search_keyword >", value, "searchKeyword");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordGreaterThanOrEqualTo(String value) {
            addCriterion("search_keyword >=", value, "searchKeyword");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordLessThan(String value) {
            addCriterion("search_keyword <", value, "searchKeyword");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordLessThanOrEqualTo(String value) {
            addCriterion("search_keyword <=", value, "searchKeyword");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordLike(String value) {
            addCriterion("search_keyword like", value, "searchKeyword");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordNotLike(String value) {
            addCriterion("search_keyword not like", value, "searchKeyword");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordIn(List<String> values) {
            addCriterion("search_keyword in", values, "searchKeyword");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordNotIn(List<String> values) {
            addCriterion("search_keyword not in", values, "searchKeyword");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordBetween(String value1, String value2) {
            addCriterion("search_keyword between", value1, value2, "searchKeyword");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordNotBetween(String value1, String value2) {
            addCriterion("search_keyword not between", value1, value2, "searchKeyword");
            return (Criteria) this;
        }

        public Criteria andCodeLikeInsensitive(String value) {
            addCriterion("upper(code) like", value.toUpperCase(), "code");
            return (Criteria) this;
        }

        public Criteria andTitleLikeInsensitive(String value) {
            addCriterion("upper(title) like", value.toUpperCase(), "title");
            return (Criteria) this;
        }

        public Criteria andNameLikeInsensitive(String value) {
            addCriterion("upper(name) like", value.toUpperCase(), "name");
            return (Criteria) this;
        }

        public Criteria andIndexKeywordLikeInsensitive(String value) {
            addCriterion("upper(index_keyword) like", value.toUpperCase(), "indexKeyword");
            return (Criteria) this;
        }

        public Criteria andTiebaKeywordLikeInsensitive(String value) {
            addCriterion("upper(tieba_keyword) like", value.toUpperCase(), "tiebaKeyword");
            return (Criteria) this;
        }

        public Criteria andNewsKeywordLikeInsensitive(String value) {
            addCriterion("upper(news_keyword) like", value.toUpperCase(), "newsKeyword");
            return (Criteria) this;
        }

        public Criteria andSearchKeywordLikeInsensitive(String value) {
            addCriterion("upper(search_keyword) like", value.toUpperCase(), "searchKeyword");
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