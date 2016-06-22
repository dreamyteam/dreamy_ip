package com.dreamy.domain.ipcool;

import com.dreamy.domain.BaseDomain;
import java.io.Serializable;
import java.util.Date;

public class BookViewCalculateResult extends BaseDomain<Integer> implements Serializable {
    private Integer id;

    private Integer bookId;

    private String name;

    private Integer type;

    private Integer compositeIndex;

    private Integer hotIndex;

    private Integer propagateIndex;

    private Integer activityIndex;

    private Integer developIndex;

    private Integer reputationIndex;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getCompositeIndex() {
        return compositeIndex;
    }

    public void setCompositeIndex(Integer compositeIndex) {
        this.compositeIndex = compositeIndex;
    }

    public Integer getHotIndex() {
        return hotIndex;
    }

    public void setHotIndex(Integer hotIndex) {
        this.hotIndex = hotIndex;
    }

    public Integer getPropagateIndex() {
        return propagateIndex;
    }

    public void setPropagateIndex(Integer propagateIndex) {
        this.propagateIndex = propagateIndex;
    }

    public Integer getActivityIndex() {
        return activityIndex;
    }

    public void setActivityIndex(Integer activityIndex) {
        this.activityIndex = activityIndex;
    }

    public Integer getDevelopIndex() {
        return developIndex;
    }

    public void setDevelopIndex(Integer developIndex) {
        this.developIndex = developIndex;
    }

    public Integer getReputationIndex() {
        return reputationIndex;
    }

    public void setReputationIndex(Integer reputationIndex) {
        this.reputationIndex = reputationIndex;
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
        BookViewCalculateResult other = (BookViewCalculateResult) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBookId() == null ? other.getBookId() == null : this.getBookId().equals(other.getBookId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getCompositeIndex() == null ? other.getCompositeIndex() == null : this.getCompositeIndex().equals(other.getCompositeIndex()))
            && (this.getHotIndex() == null ? other.getHotIndex() == null : this.getHotIndex().equals(other.getHotIndex()))
            && (this.getPropagateIndex() == null ? other.getPropagateIndex() == null : this.getPropagateIndex().equals(other.getPropagateIndex()))
            && (this.getActivityIndex() == null ? other.getActivityIndex() == null : this.getActivityIndex().equals(other.getActivityIndex()))
            && (this.getDevelopIndex() == null ? other.getDevelopIndex() == null : this.getDevelopIndex().equals(other.getDevelopIndex()))
            && (this.getReputationIndex() == null ? other.getReputationIndex() == null : this.getReputationIndex().equals(other.getReputationIndex()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBookId() == null) ? 0 : getBookId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCompositeIndex() == null) ? 0 : getCompositeIndex().hashCode());
        result = prime * result + ((getHotIndex() == null) ? 0 : getHotIndex().hashCode());
        result = prime * result + ((getPropagateIndex() == null) ? 0 : getPropagateIndex().hashCode());
        result = prime * result + ((getActivityIndex() == null) ? 0 : getActivityIndex().hashCode());
        result = prime * result + ((getDevelopIndex() == null) ? 0 : getDevelopIndex().hashCode());
        result = prime * result + ((getReputationIndex() == null) ? 0 : getReputationIndex().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }

    public BookViewCalculateResult id(Integer value) {
        this.id = value;
        return this;
    }

    public BookViewCalculateResult bookId(Integer value) {
        this.bookId = value;
        return this;
    }

    public BookViewCalculateResult name(String value) {
        this.name = value;
        return this;
    }

    public BookViewCalculateResult type(Integer value) {
        this.type = value;
        return this;
    }

    public BookViewCalculateResult compositeIndex(Integer value) {
        this.compositeIndex = value;
        return this;
    }

    public BookViewCalculateResult hotIndex(Integer value) {
        this.hotIndex = value;
        return this;
    }

    public BookViewCalculateResult propagateIndex(Integer value) {
        this.propagateIndex = value;
        return this;
    }

    public BookViewCalculateResult activityIndex(Integer value) {
        this.activityIndex = value;
        return this;
    }

    public BookViewCalculateResult developIndex(Integer value) {
        this.developIndex = value;
        return this;
    }

    public BookViewCalculateResult reputationIndex(Integer value) {
        this.reputationIndex = value;
        return this;
    }

    public BookViewCalculateResult createdAt(Date value) {
        this.createdAt = value;
        return this;
    }

    public BookViewCalculateResult updatedAt(Date value) {
        this.updatedAt = value;
        return this;
    }
}