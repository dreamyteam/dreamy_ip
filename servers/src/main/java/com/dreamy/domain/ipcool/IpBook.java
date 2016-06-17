package com.dreamy.domain.ipcool;

import com.dreamy.domain.BaseDomain;
import java.io.Serializable;
import java.util.Date;

public class IpBook extends BaseDomain<Integer> implements Serializable {
    private Integer id;

    private String code;

    private String title;

    private String name;

    private Integer type;

    private Integer status;

    private Date createdAt;

    private Date updatedAt;

    private String indexKeyword;

    private String tiebaKeyword;

    private String newsKeyword;

    private String searchKeyword;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getIndexKeyword() {
        return indexKeyword;
    }

    public void setIndexKeyword(String indexKeyword) {
        this.indexKeyword = indexKeyword;
    }

    public String getTiebaKeyword() {
        return tiebaKeyword;
    }

    public void setTiebaKeyword(String tiebaKeyword) {
        this.tiebaKeyword = tiebaKeyword;
    }

    public String getNewsKeyword() {
        return newsKeyword;
    }

    public void setNewsKeyword(String newsKeyword) {
        this.newsKeyword = newsKeyword;
    }

    public String getSearchKeyword() {
        return searchKeyword;
    }

    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
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
        IpBook other = (IpBook) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()))
            && (this.getIndexKeyword() == null ? other.getIndexKeyword() == null : this.getIndexKeyword().equals(other.getIndexKeyword()))
            && (this.getTiebaKeyword() == null ? other.getTiebaKeyword() == null : this.getTiebaKeyword().equals(other.getTiebaKeyword()))
            && (this.getNewsKeyword() == null ? other.getNewsKeyword() == null : this.getNewsKeyword().equals(other.getNewsKeyword()))
            && (this.getSearchKeyword() == null ? other.getSearchKeyword() == null : this.getSearchKeyword().equals(other.getSearchKeyword()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        result = prime * result + ((getIndexKeyword() == null) ? 0 : getIndexKeyword().hashCode());
        result = prime * result + ((getTiebaKeyword() == null) ? 0 : getTiebaKeyword().hashCode());
        result = prime * result + ((getNewsKeyword() == null) ? 0 : getNewsKeyword().hashCode());
        result = prime * result + ((getSearchKeyword() == null) ? 0 : getSearchKeyword().hashCode());
        return result;
    }

    public IpBook id(Integer value) {
        this.id = value;
        return this;
    }

    public IpBook code(String value) {
        this.code = value;
        return this;
    }

    public IpBook title(String value) {
        this.title = value;
        return this;
    }

    public IpBook name(String value) {
        this.name = value;
        return this;
    }

    public IpBook type(Integer value) {
        this.type = value;
        return this;
    }

    public IpBook status(Integer value) {
        this.status = value;
        return this;
    }

    public IpBook createdAt(Date value) {
        this.createdAt = value;
        return this;
    }

    public IpBook updatedAt(Date value) {
        this.updatedAt = value;
        return this;
    }

    public IpBook indexKeyword(String value) {
        this.indexKeyword = value;
        return this;
    }

    public IpBook tiebaKeyword(String value) {
        this.tiebaKeyword = value;
        return this;
    }

    public IpBook newsKeyword(String value) {
        this.newsKeyword = value;
        return this;
    }

    public IpBook searchKeyword(String value) {
        this.searchKeyword = value;
        return this;
    }
}