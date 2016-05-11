package com.dreamy.domain.user;

import com.dreamy.domain.BaseDomain;
import java.io.Serializable;
import java.util.Date;

public class IntentionVote extends BaseDomain<Integer> implements Serializable {
    private Integer id;

    private Integer ipId;

    private Integer status;

    private Integer type;

    private Integer userChoice;

    private Integer userId;

    private String ipAddress;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIpId() {
        return ipId;
    }

    public void setIpId(Integer ipId) {
        this.ipId = ipId;
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

    public Integer getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(Integer userChoice) {
        this.userChoice = userChoice;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
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
        IntentionVote other = (IntentionVote) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIpId() == null ? other.getIpId() == null : this.getIpId().equals(other.getIpId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getUserChoice() == null ? other.getUserChoice() == null : this.getUserChoice().equals(other.getUserChoice()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getIpAddress() == null ? other.getIpAddress() == null : this.getIpAddress().equals(other.getIpAddress()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIpId() == null) ? 0 : getIpId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getUserChoice() == null) ? 0 : getUserChoice().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getIpAddress() == null) ? 0 : getIpAddress().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }

    public IntentionVote id(Integer value) {
        this.id = value;
        return this;
    }

    public IntentionVote ipId(Integer value) {
        this.ipId = value;
        return this;
    }

    public IntentionVote status(Integer value) {
        this.status = value;
        return this;
    }

    public IntentionVote type(Integer value) {
        this.type = value;
        return this;
    }

    public IntentionVote userChoice(Integer value) {
        this.userChoice = value;
        return this;
    }

    public IntentionVote userId(Integer value) {
        this.userId = value;
        return this;
    }

    public IntentionVote ipAddress(String value) {
        this.ipAddress = value;
        return this;
    }

    public IntentionVote createdAt(Date value) {
        this.createdAt = value;
        return this;
    }

    public IntentionVote updatedAt(Date value) {
        this.updatedAt = value;
        return this;
    }
}