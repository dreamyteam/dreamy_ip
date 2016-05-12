package com.dreamy.domain.ipcool;

import com.dreamy.domain.BaseDomain;
import java.io.Serializable;
import java.util.Date;

public class BookIndexTaskLog extends BaseDomain<Integer> implements Serializable {
    private Integer id;

    private Integer indexType;

    private Integer runTime;

    private Integer status;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIndexType() {
        return indexType;
    }

    public void setIndexType(Integer indexType) {
        this.indexType = indexType;
    }

    public Integer getRunTime() {
        return runTime;
    }

    public void setRunTime(Integer runTime) {
        this.runTime = runTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
        BookIndexTaskLog other = (BookIndexTaskLog) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIndexType() == null ? other.getIndexType() == null : this.getIndexType().equals(other.getIndexType()))
            && (this.getRunTime() == null ? other.getRunTime() == null : this.getRunTime().equals(other.getRunTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIndexType() == null) ? 0 : getIndexType().hashCode());
        result = prime * result + ((getRunTime() == null) ? 0 : getRunTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }

    public BookIndexTaskLog id(Integer value) {
        this.id = value;
        return this;
    }

    public BookIndexTaskLog indexType(Integer value) {
        this.indexType = value;
        return this;
    }

    public BookIndexTaskLog runTime(Integer value) {
        this.runTime = value;
        return this;
    }

    public BookIndexTaskLog status(Integer value) {
        this.status = value;
        return this;
    }

    public BookIndexTaskLog createdAt(Date value) {
        this.createdAt = value;
        return this;
    }

    public BookIndexTaskLog updatedAt(Date value) {
        this.updatedAt = value;
        return this;
    }
}