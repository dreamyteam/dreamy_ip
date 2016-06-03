package com.dreamy.domain.ipcool;

import com.dreamy.beans.Page;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class PeopleChartConditions {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    private Page page;

    public PeopleChartConditions() {
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

        public Criteria andAgeFirstIsNull() {
            addCriterion("age_first is null");
            return (Criteria) this;
        }

        public Criteria andAgeFirstIsNotNull() {
            addCriterion("age_first is not null");
            return (Criteria) this;
        }

        public Criteria andAgeFirstEqualTo(Double value) {
            addCriterion("age_first =", value, "ageFirst");
            return (Criteria) this;
        }

        public Criteria andAgeFirstNotEqualTo(Double value) {
            addCriterion("age_first <>", value, "ageFirst");
            return (Criteria) this;
        }

        public Criteria andAgeFirstGreaterThan(Double value) {
            addCriterion("age_first >", value, "ageFirst");
            return (Criteria) this;
        }

        public Criteria andAgeFirstGreaterThanOrEqualTo(Double value) {
            addCriterion("age_first >=", value, "ageFirst");
            return (Criteria) this;
        }

        public Criteria andAgeFirstLessThan(Double value) {
            addCriterion("age_first <", value, "ageFirst");
            return (Criteria) this;
        }

        public Criteria andAgeFirstLessThanOrEqualTo(Double value) {
            addCriterion("age_first <=", value, "ageFirst");
            return (Criteria) this;
        }

        public Criteria andAgeFirstIn(List<Double> values) {
            addCriterion("age_first in", values, "ageFirst");
            return (Criteria) this;
        }

        public Criteria andAgeFirstNotIn(List<Double> values) {
            addCriterion("age_first not in", values, "ageFirst");
            return (Criteria) this;
        }

        public Criteria andAgeFirstBetween(Double value1, Double value2) {
            addCriterion("age_first between", value1, value2, "ageFirst");
            return (Criteria) this;
        }

        public Criteria andAgeFirstNotBetween(Double value1, Double value2) {
            addCriterion("age_first not between", value1, value2, "ageFirst");
            return (Criteria) this;
        }

        public Criteria andAgeScondIsNull() {
            addCriterion("age_scond is null");
            return (Criteria) this;
        }

        public Criteria andAgeScondIsNotNull() {
            addCriterion("age_scond is not null");
            return (Criteria) this;
        }

        public Criteria andAgeScondEqualTo(Double value) {
            addCriterion("age_scond =", value, "ageScond");
            return (Criteria) this;
        }

        public Criteria andAgeScondNotEqualTo(Double value) {
            addCriterion("age_scond <>", value, "ageScond");
            return (Criteria) this;
        }

        public Criteria andAgeScondGreaterThan(Double value) {
            addCriterion("age_scond >", value, "ageScond");
            return (Criteria) this;
        }

        public Criteria andAgeScondGreaterThanOrEqualTo(Double value) {
            addCriterion("age_scond >=", value, "ageScond");
            return (Criteria) this;
        }

        public Criteria andAgeScondLessThan(Double value) {
            addCriterion("age_scond <", value, "ageScond");
            return (Criteria) this;
        }

        public Criteria andAgeScondLessThanOrEqualTo(Double value) {
            addCriterion("age_scond <=", value, "ageScond");
            return (Criteria) this;
        }

        public Criteria andAgeScondIn(List<Double> values) {
            addCriterion("age_scond in", values, "ageScond");
            return (Criteria) this;
        }

        public Criteria andAgeScondNotIn(List<Double> values) {
            addCriterion("age_scond not in", values, "ageScond");
            return (Criteria) this;
        }

        public Criteria andAgeScondBetween(Double value1, Double value2) {
            addCriterion("age_scond between", value1, value2, "ageScond");
            return (Criteria) this;
        }

        public Criteria andAgeScondNotBetween(Double value1, Double value2) {
            addCriterion("age_scond not between", value1, value2, "ageScond");
            return (Criteria) this;
        }

        public Criteria andAgeThirdIsNull() {
            addCriterion("age_third is null");
            return (Criteria) this;
        }

        public Criteria andAgeThirdIsNotNull() {
            addCriterion("age_third is not null");
            return (Criteria) this;
        }

        public Criteria andAgeThirdEqualTo(Double value) {
            addCriterion("age_third =", value, "ageThird");
            return (Criteria) this;
        }

        public Criteria andAgeThirdNotEqualTo(Double value) {
            addCriterion("age_third <>", value, "ageThird");
            return (Criteria) this;
        }

        public Criteria andAgeThirdGreaterThan(Double value) {
            addCriterion("age_third >", value, "ageThird");
            return (Criteria) this;
        }

        public Criteria andAgeThirdGreaterThanOrEqualTo(Double value) {
            addCriterion("age_third >=", value, "ageThird");
            return (Criteria) this;
        }

        public Criteria andAgeThirdLessThan(Double value) {
            addCriterion("age_third <", value, "ageThird");
            return (Criteria) this;
        }

        public Criteria andAgeThirdLessThanOrEqualTo(Double value) {
            addCriterion("age_third <=", value, "ageThird");
            return (Criteria) this;
        }

        public Criteria andAgeThirdIn(List<Double> values) {
            addCriterion("age_third in", values, "ageThird");
            return (Criteria) this;
        }

        public Criteria andAgeThirdNotIn(List<Double> values) {
            addCriterion("age_third not in", values, "ageThird");
            return (Criteria) this;
        }

        public Criteria andAgeThirdBetween(Double value1, Double value2) {
            addCriterion("age_third between", value1, value2, "ageThird");
            return (Criteria) this;
        }

        public Criteria andAgeThirdNotBetween(Double value1, Double value2) {
            addCriterion("age_third not between", value1, value2, "ageThird");
            return (Criteria) this;
        }

        public Criteria andAgeFourthIsNull() {
            addCriterion("age_fourth is null");
            return (Criteria) this;
        }

        public Criteria andAgeFourthIsNotNull() {
            addCriterion("age_fourth is not null");
            return (Criteria) this;
        }

        public Criteria andAgeFourthEqualTo(Double value) {
            addCriterion("age_fourth =", value, "ageFourth");
            return (Criteria) this;
        }

        public Criteria andAgeFourthNotEqualTo(Double value) {
            addCriterion("age_fourth <>", value, "ageFourth");
            return (Criteria) this;
        }

        public Criteria andAgeFourthGreaterThan(Double value) {
            addCriterion("age_fourth >", value, "ageFourth");
            return (Criteria) this;
        }

        public Criteria andAgeFourthGreaterThanOrEqualTo(Double value) {
            addCriterion("age_fourth >=", value, "ageFourth");
            return (Criteria) this;
        }

        public Criteria andAgeFourthLessThan(Double value) {
            addCriterion("age_fourth <", value, "ageFourth");
            return (Criteria) this;
        }

        public Criteria andAgeFourthLessThanOrEqualTo(Double value) {
            addCriterion("age_fourth <=", value, "ageFourth");
            return (Criteria) this;
        }

        public Criteria andAgeFourthIn(List<Double> values) {
            addCriterion("age_fourth in", values, "ageFourth");
            return (Criteria) this;
        }

        public Criteria andAgeFourthNotIn(List<Double> values) {
            addCriterion("age_fourth not in", values, "ageFourth");
            return (Criteria) this;
        }

        public Criteria andAgeFourthBetween(Double value1, Double value2) {
            addCriterion("age_fourth between", value1, value2, "ageFourth");
            return (Criteria) this;
        }

        public Criteria andAgeFourthNotBetween(Double value1, Double value2) {
            addCriterion("age_fourth not between", value1, value2, "ageFourth");
            return (Criteria) this;
        }

        public Criteria andAgeFifthIsNull() {
            addCriterion("age_fifth is null");
            return (Criteria) this;
        }

        public Criteria andAgeFifthIsNotNull() {
            addCriterion("age_fifth is not null");
            return (Criteria) this;
        }

        public Criteria andAgeFifthEqualTo(Double value) {
            addCriterion("age_fifth =", value, "ageFifth");
            return (Criteria) this;
        }

        public Criteria andAgeFifthNotEqualTo(Double value) {
            addCriterion("age_fifth <>", value, "ageFifth");
            return (Criteria) this;
        }

        public Criteria andAgeFifthGreaterThan(Double value) {
            addCriterion("age_fifth >", value, "ageFifth");
            return (Criteria) this;
        }

        public Criteria andAgeFifthGreaterThanOrEqualTo(Double value) {
            addCriterion("age_fifth >=", value, "ageFifth");
            return (Criteria) this;
        }

        public Criteria andAgeFifthLessThan(Double value) {
            addCriterion("age_fifth <", value, "ageFifth");
            return (Criteria) this;
        }

        public Criteria andAgeFifthLessThanOrEqualTo(Double value) {
            addCriterion("age_fifth <=", value, "ageFifth");
            return (Criteria) this;
        }

        public Criteria andAgeFifthIn(List<Double> values) {
            addCriterion("age_fifth in", values, "ageFifth");
            return (Criteria) this;
        }

        public Criteria andAgeFifthNotIn(List<Double> values) {
            addCriterion("age_fifth not in", values, "ageFifth");
            return (Criteria) this;
        }

        public Criteria andAgeFifthBetween(Double value1, Double value2) {
            addCriterion("age_fifth between", value1, value2, "ageFifth");
            return (Criteria) this;
        }

        public Criteria andAgeFifthNotBetween(Double value1, Double value2) {
            addCriterion("age_fifth not between", value1, value2, "ageFifth");
            return (Criteria) this;
        }

        public Criteria andMaleIsNull() {
            addCriterion("male is null");
            return (Criteria) this;
        }

        public Criteria andMaleIsNotNull() {
            addCriterion("male is not null");
            return (Criteria) this;
        }

        public Criteria andMaleEqualTo(Double value) {
            addCriterion("male =", value, "male");
            return (Criteria) this;
        }

        public Criteria andMaleNotEqualTo(Double value) {
            addCriterion("male <>", value, "male");
            return (Criteria) this;
        }

        public Criteria andMaleGreaterThan(Double value) {
            addCriterion("male >", value, "male");
            return (Criteria) this;
        }

        public Criteria andMaleGreaterThanOrEqualTo(Double value) {
            addCriterion("male >=", value, "male");
            return (Criteria) this;
        }

        public Criteria andMaleLessThan(Double value) {
            addCriterion("male <", value, "male");
            return (Criteria) this;
        }

        public Criteria andMaleLessThanOrEqualTo(Double value) {
            addCriterion("male <=", value, "male");
            return (Criteria) this;
        }

        public Criteria andMaleIn(List<Double> values) {
            addCriterion("male in", values, "male");
            return (Criteria) this;
        }

        public Criteria andMaleNotIn(List<Double> values) {
            addCriterion("male not in", values, "male");
            return (Criteria) this;
        }

        public Criteria andMaleBetween(Double value1, Double value2) {
            addCriterion("male between", value1, value2, "male");
            return (Criteria) this;
        }

        public Criteria andMaleNotBetween(Double value1, Double value2) {
            addCriterion("male not between", value1, value2, "male");
            return (Criteria) this;
        }

        public Criteria andFemaleIsNull() {
            addCriterion("female is null");
            return (Criteria) this;
        }

        public Criteria andFemaleIsNotNull() {
            addCriterion("female is not null");
            return (Criteria) this;
        }

        public Criteria andFemaleEqualTo(Double value) {
            addCriterion("female =", value, "female");
            return (Criteria) this;
        }

        public Criteria andFemaleNotEqualTo(Double value) {
            addCriterion("female <>", value, "female");
            return (Criteria) this;
        }

        public Criteria andFemaleGreaterThan(Double value) {
            addCriterion("female >", value, "female");
            return (Criteria) this;
        }

        public Criteria andFemaleGreaterThanOrEqualTo(Double value) {
            addCriterion("female >=", value, "female");
            return (Criteria) this;
        }

        public Criteria andFemaleLessThan(Double value) {
            addCriterion("female <", value, "female");
            return (Criteria) this;
        }

        public Criteria andFemaleLessThanOrEqualTo(Double value) {
            addCriterion("female <=", value, "female");
            return (Criteria) this;
        }

        public Criteria andFemaleIn(List<Double> values) {
            addCriterion("female in", values, "female");
            return (Criteria) this;
        }

        public Criteria andFemaleNotIn(List<Double> values) {
            addCriterion("female not in", values, "female");
            return (Criteria) this;
        }

        public Criteria andFemaleBetween(Double value1, Double value2) {
            addCriterion("female between", value1, value2, "female");
            return (Criteria) this;
        }

        public Criteria andFemaleNotBetween(Double value1, Double value2) {
            addCriterion("female not between", value1, value2, "female");
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