package com.dreamy.domain.user;

import com.dreamy.domain.BaseDomain;
import java.io.Serializable;
import java.util.Date;

public class UserAttach extends BaseDomain<Integer> implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer qualification;

    private String major;

    private String work;

    private String company;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getQualification() {
        return qualification;
    }

    public void setQualification(Integer qualification) {
        this.qualification = qualification;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
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
        UserAttach other = (UserAttach) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getQualification() == null ? other.getQualification() == null : this.getQualification().equals(other.getQualification()))
            && (this.getMajor() == null ? other.getMajor() == null : this.getMajor().equals(other.getMajor()))
            && (this.getWork() == null ? other.getWork() == null : this.getWork().equals(other.getWork()))
            && (this.getCompany() == null ? other.getCompany() == null : this.getCompany().equals(other.getCompany()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getQualification() == null) ? 0 : getQualification().hashCode());
        result = prime * result + ((getMajor() == null) ? 0 : getMajor().hashCode());
        result = prime * result + ((getWork() == null) ? 0 : getWork().hashCode());
        result = prime * result + ((getCompany() == null) ? 0 : getCompany().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }

    public UserAttach id(Integer value) {
        this.id = value;
        return this;
    }

    public UserAttach userId(Integer value) {
        this.userId = value;
        return this;
    }

    public UserAttach qualification(Integer value) {
        this.qualification = value;
        return this;
    }

    public UserAttach major(String value) {
        this.major = value;
        return this;
    }

    public UserAttach work(String value) {
        this.work = value;
        return this;
    }

    public UserAttach company(String value) {
        this.company = value;
        return this;
    }

    public UserAttach createdAt(Date value) {
        this.createdAt = value;
        return this;
    }

    public UserAttach updatedAt(Date value) {
        this.updatedAt = value;
        return this;
    }
}