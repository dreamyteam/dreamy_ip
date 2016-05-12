package com.dreamy.domain.ipcool;

import com.dreamy.domain.BaseDomain;
import java.io.Serializable;
import java.util.Date;

public class KeyWord extends BaseDomain<Integer> implements Serializable {
    private Integer id;

    private Integer source;

    private Integer bookId;

    private Integer indexNum;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getIndexNum() {
        return indexNum;
    }

    public void setIndexNum(Integer indexNum) {
        this.indexNum = indexNum;
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
        KeyWord other = (KeyWord) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getSource() == null ? other.getSource() == null : this.getSource().equals(other.getSource()))
            && (this.getBookId() == null ? other.getBookId() == null : this.getBookId().equals(other.getBookId()))
            && (this.getIndexNum() == null ? other.getIndexNum() == null : this.getIndexNum().equals(other.getIndexNum()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getSource() == null) ? 0 : getSource().hashCode());
        result = prime * result + ((getBookId() == null) ? 0 : getBookId().hashCode());
        result = prime * result + ((getIndexNum() == null) ? 0 : getIndexNum().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }

    public KeyWord id(Integer value) {
        this.id = value;
        return this;
    }

    public KeyWord source(Integer value) {
        this.source = value;
        return this;
    }

    public KeyWord bookId(Integer value) {
        this.bookId = value;
        return this;
    }

    public KeyWord indexNum(Integer value) {
        this.indexNum = value;
        return this;
    }

    public KeyWord createdAt(Date value) {
        this.createdAt = value;
        return this;
    }

    public KeyWord updatedAt(Date value) {
        this.updatedAt = value;
        return this;
    }
}