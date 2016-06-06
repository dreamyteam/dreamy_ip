package com.dreamy.domain.ipcool;

import com.dreamy.domain.BaseDomain;
import java.io.Serializable;
import java.util.Date;

public class PeopleChart extends BaseDomain<Integer> implements Serializable {
    private Integer id;

    private Integer bookId;

    private Double ageFirst;

    private Double ageScond;

    private Double ageThird;

    private Double ageFourth;

    private Double ageFifth;

    private Double male;

    private Double female;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Double getAgeFirst() {
        return ageFirst;
    }

    public void setAgeFirst(Double ageFirst) {
        this.ageFirst = ageFirst;
    }

    public Double getAgeScond() {
        return ageScond;
    }

    public void setAgeScond(Double ageScond) {
        this.ageScond = ageScond;
    }

    public Double getAgeThird() {
        return ageThird;
    }

    public void setAgeThird(Double ageThird) {
        this.ageThird = ageThird;
    }

    public Double getAgeFourth() {
        return ageFourth;
    }

    public void setAgeFourth(Double ageFourth) {
        this.ageFourth = ageFourth;
    }

    public Double getAgeFifth() {
        return ageFifth;
    }

    public void setAgeFifth(Double ageFifth) {
        this.ageFifth = ageFifth;
    }

    public Double getMale() {
        return male;
    }

    public void setMale(Double male) {
        this.male = male;
    }

    public Double getFemale() {
        return female;
    }

    public void setFemale(Double female) {
        this.female = female;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        PeopleChart other = (PeopleChart) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBookId() == null ? other.getBookId() == null : this.getBookId().equals(other.getBookId()))
            && (this.getAgeFirst() == null ? other.getAgeFirst() == null : this.getAgeFirst().equals(other.getAgeFirst()))
            && (this.getAgeScond() == null ? other.getAgeScond() == null : this.getAgeScond().equals(other.getAgeScond()))
            && (this.getAgeThird() == null ? other.getAgeThird() == null : this.getAgeThird().equals(other.getAgeThird()))
            && (this.getAgeFourth() == null ? other.getAgeFourth() == null : this.getAgeFourth().equals(other.getAgeFourth()))
            && (this.getAgeFifth() == null ? other.getAgeFifth() == null : this.getAgeFifth().equals(other.getAgeFifth()))
            && (this.getMale() == null ? other.getMale() == null : this.getMale().equals(other.getMale()))
            && (this.getFemale() == null ? other.getFemale() == null : this.getFemale().equals(other.getFemale()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBookId() == null) ? 0 : getBookId().hashCode());
        result = prime * result + ((getAgeFirst() == null) ? 0 : getAgeFirst().hashCode());
        result = prime * result + ((getAgeScond() == null) ? 0 : getAgeScond().hashCode());
        result = prime * result + ((getAgeThird() == null) ? 0 : getAgeThird().hashCode());
        result = prime * result + ((getAgeFourth() == null) ? 0 : getAgeFourth().hashCode());
        result = prime * result + ((getAgeFifth() == null) ? 0 : getAgeFifth().hashCode());
        result = prime * result + ((getMale() == null) ? 0 : getMale().hashCode());
        result = prime * result + ((getFemale() == null) ? 0 : getFemale().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }

    public PeopleChart id(Integer value) {
        this.id = value;
        return this;
    }

    public PeopleChart bookId(Integer value) {
        this.bookId = value;
        return this;
    }

    public PeopleChart ageFirst(Double value) {
        this.ageFirst = value;
        return this;
    }

    public PeopleChart ageScond(Double value) {
        this.ageScond = value;
        return this;
    }

    public PeopleChart ageThird(Double value) {
        this.ageThird = value;
        return this;
    }

    public PeopleChart ageFourth(Double value) {
        this.ageFourth = value;
        return this;
    }

    public PeopleChart ageFifth(Double value) {
        this.ageFifth = value;
        return this;
    }

    public PeopleChart male(Double value) {
        this.male = value;
        return this;
    }

    public PeopleChart female(Double value) {
        this.female = value;
        return this;
    }

    public PeopleChart createdAt(Date value) {
        this.createdAt = value;
        return this;
    }

    public PeopleChart updatedAt(Date value) {
        this.updatedAt = value;
        return this;
    }
}