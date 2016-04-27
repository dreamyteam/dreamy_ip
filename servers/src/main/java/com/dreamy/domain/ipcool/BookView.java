package com.dreamy.domain.ipcool;

import com.dreamy.domain.BaseDomain;
import java.io.Serializable;
import java.util.Date;

public class BookView extends BaseDomain<Integer> implements Serializable {
    private Integer id;

    private Integer bookId;

    private String name;

    private String author;

    private Integer status;

    private Integer type;

    private Integer compositeIndex;

    private Integer hotIndex;

    private Integer activityIndex;

    private Integer propagateIndex;

    private Integer developIndex;

    private Date createdAt;

    private Date updatedAt;

    private String imageUrl;

    private String remark;

    private String introduction;

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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public Integer getActivityIndex() {
        return activityIndex;
    }

    public void setActivityIndex(Integer activityIndex) {
        this.activityIndex = activityIndex;
    }

    public Integer getPropagateIndex() {
        return propagateIndex;
    }

    public void setPropagateIndex(Integer propagateIndex) {
        this.propagateIndex = propagateIndex;
    }

    public Integer getDevelopIndex() {
        return developIndex;
    }

    public void setDevelopIndex(Integer developIndex) {
        this.developIndex = developIndex;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
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
        BookView other = (BookView) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getBookId() == null ? other.getBookId() == null : this.getBookId().equals(other.getBookId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getAuthor() == null ? other.getAuthor() == null : this.getAuthor().equals(other.getAuthor()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getCompositeIndex() == null ? other.getCompositeIndex() == null : this.getCompositeIndex().equals(other.getCompositeIndex()))
            && (this.getHotIndex() == null ? other.getHotIndex() == null : this.getHotIndex().equals(other.getHotIndex()))
            && (this.getActivityIndex() == null ? other.getActivityIndex() == null : this.getActivityIndex().equals(other.getActivityIndex()))
            && (this.getPropagateIndex() == null ? other.getPropagateIndex() == null : this.getPropagateIndex().equals(other.getPropagateIndex()))
            && (this.getDevelopIndex() == null ? other.getDevelopIndex() == null : this.getDevelopIndex().equals(other.getDevelopIndex()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getImageUrl() == null ? other.getImageUrl() == null : this.getImageUrl().equals(other.getImageUrl()))
            && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark()))
            && (this.getIntroduction() == null ? other.getIntroduction() == null : this.getIntroduction().equals(other.getIntroduction()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getBookId() == null) ? 0 : getBookId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getAuthor() == null) ? 0 : getAuthor().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getCompositeIndex() == null) ? 0 : getCompositeIndex().hashCode());
        result = prime * result + ((getHotIndex() == null) ? 0 : getHotIndex().hashCode());
        result = prime * result + ((getActivityIndex() == null) ? 0 : getActivityIndex().hashCode());
        result = prime * result + ((getPropagateIndex() == null) ? 0 : getPropagateIndex().hashCode());
        result = prime * result + ((getDevelopIndex() == null) ? 0 : getDevelopIndex().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getImageUrl() == null) ? 0 : getImageUrl().hashCode());
        result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
        result = prime * result + ((getIntroduction() == null) ? 0 : getIntroduction().hashCode());
        return result;
    }

    public BookView id(Integer value) {
        this.id = value;
        return this;
    }

    public BookView bookId(Integer value) {
        this.bookId = value;
        return this;
    }

    public BookView name(String value) {
        this.name = value;
        return this;
    }

    public BookView author(String value) {
        this.author = value;
        return this;
    }

    public BookView status(Integer value) {
        this.status = value;
        return this;
    }

    public BookView type(Integer value) {
        this.type = value;
        return this;
    }

    public BookView compositeIndex(Integer value) {
        this.compositeIndex = value;
        return this;
    }

    public BookView hotIndex(Integer value) {
        this.hotIndex = value;
        return this;
    }

    public BookView activityIndex(Integer value) {
        this.activityIndex = value;
        return this;
    }

    public BookView propagateIndex(Integer value) {
        this.propagateIndex = value;
        return this;
    }

    public BookView developIndex(Integer value) {
        this.developIndex = value;
        return this;
    }

    public BookView createdAt(Date value) {
        this.createdAt = value;
        return this;
    }

    public BookView updatedAt(Date value) {
        this.updatedAt = value;
        return this;
    }

    public BookView imageUrl(String value) {
        this.imageUrl = value;
        return this;
    }

    public BookView remark(String value) {
        this.remark = value;
        return this;
    }

    public BookView introduction(String value) {
        this.introduction = value;
        return this;
    }
}