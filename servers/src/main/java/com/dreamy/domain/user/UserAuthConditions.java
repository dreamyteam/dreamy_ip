package com.dreamy.domain.user;

import com.dreamy.beans.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class UserAuthConditions {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Page page;

    public UserAuthConditions() {
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

        public Criteria andUserIdIsNull() {
            addCriterion("user_id is null");
            return (Criteria) this;
        }

        public Criteria andUserIdIsNotNull() {
            addCriterion("user_id is not null");
            return (Criteria) this;
        }

        public Criteria andUserIdEqualTo(Integer value) {
            addCriterion("user_id =", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotEqualTo(Integer value) {
            addCriterion("user_id <>", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThan(Integer value) {
            addCriterion("user_id >", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("user_id >=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThan(Integer value) {
            addCriterion("user_id <", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("user_id <=", value, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdIn(List<Integer> values) {
            addCriterion("user_id in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotIn(List<Integer> values) {
            addCriterion("user_id not in", values, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdBetween(Integer value1, Integer value2) {
            addCriterion("user_id between", value1, value2, "userId");
            return (Criteria) this;
        }

        public Criteria andUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("user_id not between", value1, value2, "userId");
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

        public Criteria andPartIsNull() {
            addCriterion("part is null");
            return (Criteria) this;
        }

        public Criteria andPartIsNotNull() {
            addCriterion("part is not null");
            return (Criteria) this;
        }

        public Criteria andPartEqualTo(String value) {
            addCriterion("part =", value, "part");
            return (Criteria) this;
        }

        public Criteria andPartNotEqualTo(String value) {
            addCriterion("part <>", value, "part");
            return (Criteria) this;
        }

        public Criteria andPartGreaterThan(String value) {
            addCriterion("part >", value, "part");
            return (Criteria) this;
        }

        public Criteria andPartGreaterThanOrEqualTo(String value) {
            addCriterion("part >=", value, "part");
            return (Criteria) this;
        }

        public Criteria andPartLessThan(String value) {
            addCriterion("part <", value, "part");
            return (Criteria) this;
        }

        public Criteria andPartLessThanOrEqualTo(String value) {
            addCriterion("part <=", value, "part");
            return (Criteria) this;
        }

        public Criteria andPartLike(String value) {
            addCriterion("part like", value, "part");
            return (Criteria) this;
        }

        public Criteria andPartNotLike(String value) {
            addCriterion("part not like", value, "part");
            return (Criteria) this;
        }

        public Criteria andPartIn(List<String> values) {
            addCriterion("part in", values, "part");
            return (Criteria) this;
        }

        public Criteria andPartNotIn(List<String> values) {
            addCriterion("part not in", values, "part");
            return (Criteria) this;
        }

        public Criteria andPartBetween(String value1, String value2) {
            addCriterion("part between", value1, value2, "part");
            return (Criteria) this;
        }

        public Criteria andPartNotBetween(String value1, String value2) {
            addCriterion("part not between", value1, value2, "part");
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

        public Criteria andEmailIsNull() {
            addCriterion("email is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("email is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("email =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("email <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("email >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("email >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("email <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("email <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("email like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("email not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("email in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("email not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("email between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("email not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("phone is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("phone is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("phone =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("phone <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("phone >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("phone >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("phone <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("phone <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("phone like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("phone not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("phone in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("phone not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("phone between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("phone not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andCardNoIsNull() {
            addCriterion("card_no is null");
            return (Criteria) this;
        }

        public Criteria andCardNoIsNotNull() {
            addCriterion("card_no is not null");
            return (Criteria) this;
        }

        public Criteria andCardNoEqualTo(String value) {
            addCriterion("card_no =", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotEqualTo(String value) {
            addCriterion("card_no <>", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoGreaterThan(String value) {
            addCriterion("card_no >", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoGreaterThanOrEqualTo(String value) {
            addCriterion("card_no >=", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLessThan(String value) {
            addCriterion("card_no <", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLessThanOrEqualTo(String value) {
            addCriterion("card_no <=", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoLike(String value) {
            addCriterion("card_no like", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotLike(String value) {
            addCriterion("card_no not like", value, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoIn(List<String> values) {
            addCriterion("card_no in", values, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotIn(List<String> values) {
            addCriterion("card_no not in", values, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoBetween(String value1, String value2) {
            addCriterion("card_no between", value1, value2, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardNoNotBetween(String value1, String value2) {
            addCriterion("card_no not between", value1, value2, "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardFrontIsNull() {
            addCriterion("card_front is null");
            return (Criteria) this;
        }

        public Criteria andCardFrontIsNotNull() {
            addCriterion("card_front is not null");
            return (Criteria) this;
        }

        public Criteria andCardFrontEqualTo(String value) {
            addCriterion("card_front =", value, "cardFront");
            return (Criteria) this;
        }

        public Criteria andCardFrontNotEqualTo(String value) {
            addCriterion("card_front <>", value, "cardFront");
            return (Criteria) this;
        }

        public Criteria andCardFrontGreaterThan(String value) {
            addCriterion("card_front >", value, "cardFront");
            return (Criteria) this;
        }

        public Criteria andCardFrontGreaterThanOrEqualTo(String value) {
            addCriterion("card_front >=", value, "cardFront");
            return (Criteria) this;
        }

        public Criteria andCardFrontLessThan(String value) {
            addCriterion("card_front <", value, "cardFront");
            return (Criteria) this;
        }

        public Criteria andCardFrontLessThanOrEqualTo(String value) {
            addCriterion("card_front <=", value, "cardFront");
            return (Criteria) this;
        }

        public Criteria andCardFrontLike(String value) {
            addCriterion("card_front like", value, "cardFront");
            return (Criteria) this;
        }

        public Criteria andCardFrontNotLike(String value) {
            addCriterion("card_front not like", value, "cardFront");
            return (Criteria) this;
        }

        public Criteria andCardFrontIn(List<String> values) {
            addCriterion("card_front in", values, "cardFront");
            return (Criteria) this;
        }

        public Criteria andCardFrontNotIn(List<String> values) {
            addCriterion("card_front not in", values, "cardFront");
            return (Criteria) this;
        }

        public Criteria andCardFrontBetween(String value1, String value2) {
            addCriterion("card_front between", value1, value2, "cardFront");
            return (Criteria) this;
        }

        public Criteria andCardFrontNotBetween(String value1, String value2) {
            addCriterion("card_front not between", value1, value2, "cardFront");
            return (Criteria) this;
        }

        public Criteria andCardBackIsNull() {
            addCriterion("card_back is null");
            return (Criteria) this;
        }

        public Criteria andCardBackIsNotNull() {
            addCriterion("card_back is not null");
            return (Criteria) this;
        }

        public Criteria andCardBackEqualTo(String value) {
            addCriterion("card_back =", value, "cardBack");
            return (Criteria) this;
        }

        public Criteria andCardBackNotEqualTo(String value) {
            addCriterion("card_back <>", value, "cardBack");
            return (Criteria) this;
        }

        public Criteria andCardBackGreaterThan(String value) {
            addCriterion("card_back >", value, "cardBack");
            return (Criteria) this;
        }

        public Criteria andCardBackGreaterThanOrEqualTo(String value) {
            addCriterion("card_back >=", value, "cardBack");
            return (Criteria) this;
        }

        public Criteria andCardBackLessThan(String value) {
            addCriterion("card_back <", value, "cardBack");
            return (Criteria) this;
        }

        public Criteria andCardBackLessThanOrEqualTo(String value) {
            addCriterion("card_back <=", value, "cardBack");
            return (Criteria) this;
        }

        public Criteria andCardBackLike(String value) {
            addCriterion("card_back like", value, "cardBack");
            return (Criteria) this;
        }

        public Criteria andCardBackNotLike(String value) {
            addCriterion("card_back not like", value, "cardBack");
            return (Criteria) this;
        }

        public Criteria andCardBackIn(List<String> values) {
            addCriterion("card_back in", values, "cardBack");
            return (Criteria) this;
        }

        public Criteria andCardBackNotIn(List<String> values) {
            addCriterion("card_back not in", values, "cardBack");
            return (Criteria) this;
        }

        public Criteria andCardBackBetween(String value1, String value2) {
            addCriterion("card_back between", value1, value2, "cardBack");
            return (Criteria) this;
        }

        public Criteria andCardBackNotBetween(String value1, String value2) {
            addCriterion("card_back not between", value1, value2, "cardBack");
            return (Criteria) this;
        }

        public Criteria andProfessionInfoIsNull() {
            addCriterion("profession_info is null");
            return (Criteria) this;
        }

        public Criteria andProfessionInfoIsNotNull() {
            addCriterion("profession_info is not null");
            return (Criteria) this;
        }

        public Criteria andProfessionInfoEqualTo(String value) {
            addCriterion("profession_info =", value, "professionInfo");
            return (Criteria) this;
        }

        public Criteria andProfessionInfoNotEqualTo(String value) {
            addCriterion("profession_info <>", value, "professionInfo");
            return (Criteria) this;
        }

        public Criteria andProfessionInfoGreaterThan(String value) {
            addCriterion("profession_info >", value, "professionInfo");
            return (Criteria) this;
        }

        public Criteria andProfessionInfoGreaterThanOrEqualTo(String value) {
            addCriterion("profession_info >=", value, "professionInfo");
            return (Criteria) this;
        }

        public Criteria andProfessionInfoLessThan(String value) {
            addCriterion("profession_info <", value, "professionInfo");
            return (Criteria) this;
        }

        public Criteria andProfessionInfoLessThanOrEqualTo(String value) {
            addCriterion("profession_info <=", value, "professionInfo");
            return (Criteria) this;
        }

        public Criteria andProfessionInfoLike(String value) {
            addCriterion("profession_info like", value, "professionInfo");
            return (Criteria) this;
        }

        public Criteria andProfessionInfoNotLike(String value) {
            addCriterion("profession_info not like", value, "professionInfo");
            return (Criteria) this;
        }

        public Criteria andProfessionInfoIn(List<String> values) {
            addCriterion("profession_info in", values, "professionInfo");
            return (Criteria) this;
        }

        public Criteria andProfessionInfoNotIn(List<String> values) {
            addCriterion("profession_info not in", values, "professionInfo");
            return (Criteria) this;
        }

        public Criteria andProfessionInfoBetween(String value1, String value2) {
            addCriterion("profession_info between", value1, value2, "professionInfo");
            return (Criteria) this;
        }

        public Criteria andProfessionInfoNotBetween(String value1, String value2) {
            addCriterion("profession_info not between", value1, value2, "professionInfo");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNull() {
            addCriterion("company_name is null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIsNotNull() {
            addCriterion("company_name is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyNameEqualTo(String value) {
            addCriterion("company_name =", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotEqualTo(String value) {
            addCriterion("company_name <>", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThan(String value) {
            addCriterion("company_name >", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameGreaterThanOrEqualTo(String value) {
            addCriterion("company_name >=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThan(String value) {
            addCriterion("company_name <", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLessThanOrEqualTo(String value) {
            addCriterion("company_name <=", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLike(String value) {
            addCriterion("company_name like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotLike(String value) {
            addCriterion("company_name not like", value, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameIn(List<String> values) {
            addCriterion("company_name in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotIn(List<String> values) {
            addCriterion("company_name not in", values, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameBetween(String value1, String value2) {
            addCriterion("company_name between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andCompanyNameNotBetween(String value1, String value2) {
            addCriterion("company_name not between", value1, value2, "companyName");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseIsNull() {
            addCriterion("business_license is null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseIsNotNull() {
            addCriterion("business_license is not null");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseEqualTo(String value) {
            addCriterion("business_license =", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNotEqualTo(String value) {
            addCriterion("business_license <>", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseGreaterThan(String value) {
            addCriterion("business_license >", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseGreaterThanOrEqualTo(String value) {
            addCriterion("business_license >=", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseLessThan(String value) {
            addCriterion("business_license <", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseLessThanOrEqualTo(String value) {
            addCriterion("business_license <=", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseLike(String value) {
            addCriterion("business_license like", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNotLike(String value) {
            addCriterion("business_license not like", value, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseIn(List<String> values) {
            addCriterion("business_license in", values, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNotIn(List<String> values) {
            addCriterion("business_license not in", values, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseBetween(String value1, String value2) {
            addCriterion("business_license between", value1, value2, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseNotBetween(String value1, String value2) {
            addCriterion("business_license not between", value1, value2, "businessLicense");
            return (Criteria) this;
        }

        public Criteria andLicenseAgreementIsNull() {
            addCriterion("license_agreement is null");
            return (Criteria) this;
        }

        public Criteria andLicenseAgreementIsNotNull() {
            addCriterion("license_agreement is not null");
            return (Criteria) this;
        }

        public Criteria andLicenseAgreementEqualTo(String value) {
            addCriterion("license_agreement =", value, "licenseAgreement");
            return (Criteria) this;
        }

        public Criteria andLicenseAgreementNotEqualTo(String value) {
            addCriterion("license_agreement <>", value, "licenseAgreement");
            return (Criteria) this;
        }

        public Criteria andLicenseAgreementGreaterThan(String value) {
            addCriterion("license_agreement >", value, "licenseAgreement");
            return (Criteria) this;
        }

        public Criteria andLicenseAgreementGreaterThanOrEqualTo(String value) {
            addCriterion("license_agreement >=", value, "licenseAgreement");
            return (Criteria) this;
        }

        public Criteria andLicenseAgreementLessThan(String value) {
            addCriterion("license_agreement <", value, "licenseAgreement");
            return (Criteria) this;
        }

        public Criteria andLicenseAgreementLessThanOrEqualTo(String value) {
            addCriterion("license_agreement <=", value, "licenseAgreement");
            return (Criteria) this;
        }

        public Criteria andLicenseAgreementLike(String value) {
            addCriterion("license_agreement like", value, "licenseAgreement");
            return (Criteria) this;
        }

        public Criteria andLicenseAgreementNotLike(String value) {
            addCriterion("license_agreement not like", value, "licenseAgreement");
            return (Criteria) this;
        }

        public Criteria andLicenseAgreementIn(List<String> values) {
            addCriterion("license_agreement in", values, "licenseAgreement");
            return (Criteria) this;
        }

        public Criteria andLicenseAgreementNotIn(List<String> values) {
            addCriterion("license_agreement not in", values, "licenseAgreement");
            return (Criteria) this;
        }

        public Criteria andLicenseAgreementBetween(String value1, String value2) {
            addCriterion("license_agreement between", value1, value2, "licenseAgreement");
            return (Criteria) this;
        }

        public Criteria andLicenseAgreementNotBetween(String value1, String value2) {
            addCriterion("license_agreement not between", value1, value2, "licenseAgreement");
            return (Criteria) this;
        }

        public Criteria andBankNoIsNull() {
            addCriterion("bank_no is null");
            return (Criteria) this;
        }

        public Criteria andBankNoIsNotNull() {
            addCriterion("bank_no is not null");
            return (Criteria) this;
        }

        public Criteria andBankNoEqualTo(String value) {
            addCriterion("bank_no =", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoNotEqualTo(String value) {
            addCriterion("bank_no <>", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoGreaterThan(String value) {
            addCriterion("bank_no >", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoGreaterThanOrEqualTo(String value) {
            addCriterion("bank_no >=", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoLessThan(String value) {
            addCriterion("bank_no <", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoLessThanOrEqualTo(String value) {
            addCriterion("bank_no <=", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoLike(String value) {
            addCriterion("bank_no like", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoNotLike(String value) {
            addCriterion("bank_no not like", value, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoIn(List<String> values) {
            addCriterion("bank_no in", values, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoNotIn(List<String> values) {
            addCriterion("bank_no not in", values, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoBetween(String value1, String value2) {
            addCriterion("bank_no between", value1, value2, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNoNotBetween(String value1, String value2) {
            addCriterion("bank_no not between", value1, value2, "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("bank_name is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("bank_name =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("bank_name <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("bank_name >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_name >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("bank_name <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("bank_name <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("bank_name like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("bank_name not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("bank_name in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("bank_name not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("bank_name between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("bank_name not between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankRealyNameIsNull() {
            addCriterion("bank_realy_name is null");
            return (Criteria) this;
        }

        public Criteria andBankRealyNameIsNotNull() {
            addCriterion("bank_realy_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankRealyNameEqualTo(String value) {
            addCriterion("bank_realy_name =", value, "bankRealyName");
            return (Criteria) this;
        }

        public Criteria andBankRealyNameNotEqualTo(String value) {
            addCriterion("bank_realy_name <>", value, "bankRealyName");
            return (Criteria) this;
        }

        public Criteria andBankRealyNameGreaterThan(String value) {
            addCriterion("bank_realy_name >", value, "bankRealyName");
            return (Criteria) this;
        }

        public Criteria andBankRealyNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_realy_name >=", value, "bankRealyName");
            return (Criteria) this;
        }

        public Criteria andBankRealyNameLessThan(String value) {
            addCriterion("bank_realy_name <", value, "bankRealyName");
            return (Criteria) this;
        }

        public Criteria andBankRealyNameLessThanOrEqualTo(String value) {
            addCriterion("bank_realy_name <=", value, "bankRealyName");
            return (Criteria) this;
        }

        public Criteria andBankRealyNameLike(String value) {
            addCriterion("bank_realy_name like", value, "bankRealyName");
            return (Criteria) this;
        }

        public Criteria andBankRealyNameNotLike(String value) {
            addCriterion("bank_realy_name not like", value, "bankRealyName");
            return (Criteria) this;
        }

        public Criteria andBankRealyNameIn(List<String> values) {
            addCriterion("bank_realy_name in", values, "bankRealyName");
            return (Criteria) this;
        }

        public Criteria andBankRealyNameNotIn(List<String> values) {
            addCriterion("bank_realy_name not in", values, "bankRealyName");
            return (Criteria) this;
        }

        public Criteria andBankRealyNameBetween(String value1, String value2) {
            addCriterion("bank_realy_name between", value1, value2, "bankRealyName");
            return (Criteria) this;
        }

        public Criteria andBankRealyNameNotBetween(String value1, String value2) {
            addCriterion("bank_realy_name not between", value1, value2, "bankRealyName");
            return (Criteria) this;
        }

        public Criteria andValideCodeIsNull() {
            addCriterion("valide_code is null");
            return (Criteria) this;
        }

        public Criteria andValideCodeIsNotNull() {
            addCriterion("valide_code is not null");
            return (Criteria) this;
        }

        public Criteria andValideCodeEqualTo(String value) {
            addCriterion("valide_code =", value, "valideCode");
            return (Criteria) this;
        }

        public Criteria andValideCodeNotEqualTo(String value) {
            addCriterion("valide_code <>", value, "valideCode");
            return (Criteria) this;
        }

        public Criteria andValideCodeGreaterThan(String value) {
            addCriterion("valide_code >", value, "valideCode");
            return (Criteria) this;
        }

        public Criteria andValideCodeGreaterThanOrEqualTo(String value) {
            addCriterion("valide_code >=", value, "valideCode");
            return (Criteria) this;
        }

        public Criteria andValideCodeLessThan(String value) {
            addCriterion("valide_code <", value, "valideCode");
            return (Criteria) this;
        }

        public Criteria andValideCodeLessThanOrEqualTo(String value) {
            addCriterion("valide_code <=", value, "valideCode");
            return (Criteria) this;
        }

        public Criteria andValideCodeLike(String value) {
            addCriterion("valide_code like", value, "valideCode");
            return (Criteria) this;
        }

        public Criteria andValideCodeNotLike(String value) {
            addCriterion("valide_code not like", value, "valideCode");
            return (Criteria) this;
        }

        public Criteria andValideCodeIn(List<String> values) {
            addCriterion("valide_code in", values, "valideCode");
            return (Criteria) this;
        }

        public Criteria andValideCodeNotIn(List<String> values) {
            addCriterion("valide_code not in", values, "valideCode");
            return (Criteria) this;
        }

        public Criteria andValideCodeBetween(String value1, String value2) {
            addCriterion("valide_code between", value1, value2, "valideCode");
            return (Criteria) this;
        }

        public Criteria andValideCodeNotBetween(String value1, String value2) {
            addCriterion("valide_code not between", value1, value2, "valideCode");
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

        public Criteria andPartLikeInsensitive(String value) {
            addCriterion("upper(part) like", value.toUpperCase(), "part");
            return (Criteria) this;
        }

        public Criteria andUserNameLikeInsensitive(String value) {
            addCriterion("upper(user_name) like", value.toUpperCase(), "userName");
            return (Criteria) this;
        }

        public Criteria andEmailLikeInsensitive(String value) {
            addCriterion("upper(email) like", value.toUpperCase(), "email");
            return (Criteria) this;
        }

        public Criteria andPhoneLikeInsensitive(String value) {
            addCriterion("upper(phone) like", value.toUpperCase(), "phone");
            return (Criteria) this;
        }

        public Criteria andCardNoLikeInsensitive(String value) {
            addCriterion("upper(card_no) like", value.toUpperCase(), "cardNo");
            return (Criteria) this;
        }

        public Criteria andCardFrontLikeInsensitive(String value) {
            addCriterion("upper(card_front) like", value.toUpperCase(), "cardFront");
            return (Criteria) this;
        }

        public Criteria andCardBackLikeInsensitive(String value) {
            addCriterion("upper(card_back) like", value.toUpperCase(), "cardBack");
            return (Criteria) this;
        }

        public Criteria andProfessionInfoLikeInsensitive(String value) {
            addCriterion("upper(profession_info) like", value.toUpperCase(), "professionInfo");
            return (Criteria) this;
        }

        public Criteria andCompanyNameLikeInsensitive(String value) {
            addCriterion("upper(company_name) like", value.toUpperCase(), "companyName");
            return (Criteria) this;
        }

        public Criteria andBusinessLicenseLikeInsensitive(String value) {
            addCriterion("upper(business_license) like", value.toUpperCase(), "businessLicense");
            return (Criteria) this;
        }

        public Criteria andLicenseAgreementLikeInsensitive(String value) {
            addCriterion("upper(license_agreement) like", value.toUpperCase(), "licenseAgreement");
            return (Criteria) this;
        }

        public Criteria andBankNoLikeInsensitive(String value) {
            addCriterion("upper(bank_no) like", value.toUpperCase(), "bankNo");
            return (Criteria) this;
        }

        public Criteria andBankNameLikeInsensitive(String value) {
            addCriterion("upper(bank_name) like", value.toUpperCase(), "bankName");
            return (Criteria) this;
        }

        public Criteria andBankRealyNameLikeInsensitive(String value) {
            addCriterion("upper(bank_realy_name) like", value.toUpperCase(), "bankRealyName");
            return (Criteria) this;
        }

        public Criteria andValideCodeLikeInsensitive(String value) {
            addCriterion("upper(valide_code) like", value.toUpperCase(), "valideCode");
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