package com.dreamy.domain.admin;

import com.dreamy.domain.BaseDomain;
import java.io.Serializable;
import java.util.Date;

public class Role extends BaseDomain<Integer> implements Serializable {
    private Integer id;

    private String name;

    private Integer status;

    private Integer isSys;

    private Integer displayOrder;

    private Date createdAt;

    private String indexUrl;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsSys() {
        return isSys;
    }

    public void setIsSys(Integer isSys) {
        this.isSys = isSys;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getIndexUrl() {
        return indexUrl;
    }

    public void setIndexUrl(String indexUrl) {
        this.indexUrl = indexUrl;
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
        Role other = (Role) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getIsSys() == null ? other.getIsSys() == null : this.getIsSys().equals(other.getIsSys()))
            && (this.getDisplayOrder() == null ? other.getDisplayOrder() == null : this.getDisplayOrder().equals(other.getDisplayOrder()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getIndexUrl() == null ? other.getIndexUrl() == null : this.getIndexUrl().equals(other.getIndexUrl()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getIsSys() == null) ? 0 : getIsSys().hashCode());
        result = prime * result + ((getDisplayOrder() == null) ? 0 : getDisplayOrder().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getIndexUrl() == null) ? 0 : getIndexUrl().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }

    public Role id(Integer value) {
        this.id = value;
        return this;
    }

    public Role name(String value) {
        this.name = value;
        return this;
    }

    public Role status(Integer value) {
        this.status = value;
        return this;
    }

    public Role isSys(Integer value) {
        this.isSys = value;
        return this;
    }

    public Role displayOrder(Integer value) {
        this.displayOrder = value;
        return this;
    }

    public Role createdAt(Date value) {
        this.createdAt = value;
        return this;
    }

    public Role indexUrl(String value) {
        this.indexUrl = value;
        return this;
    }

    public Role updatedAt(Date value) {
        this.updatedAt = value;
        return this;
    }
}