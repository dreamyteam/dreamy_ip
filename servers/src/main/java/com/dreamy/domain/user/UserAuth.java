package com.dreamy.domain.user;

import com.dreamy.domain.BaseDomain;
import java.io.Serializable;
import java.util.Date;

public class UserAuth extends BaseDomain<Integer> implements Serializable {
    private Integer id;

    private Integer userId;

    private Integer status;

    private Integer type;

    private String part;

    private String userName;

    private String email;

    private String phone;

    private String cardNo;

    private String cardFront;

    private String cardBack;

    private String professionInfo;

    private String companyName;

    private String businessLicense;

    private String licenseAgreement;

    private String bankNo;

    private String bankName;

    private String bankRealyName;

    private String valideCode;

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

    public String getPart() {
        return part;
    }

    public void setPart(String part) {
        this.part = part;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getCardFront() {
        return cardFront;
    }

    public void setCardFront(String cardFront) {
        this.cardFront = cardFront;
    }

    public String getCardBack() {
        return cardBack;
    }

    public void setCardBack(String cardBack) {
        this.cardBack = cardBack;
    }

    public String getProfessionInfo() {
        return professionInfo;
    }

    public void setProfessionInfo(String professionInfo) {
        this.professionInfo = professionInfo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBusinessLicense() {
        return businessLicense;
    }

    public void setBusinessLicense(String businessLicense) {
        this.businessLicense = businessLicense;
    }

    public String getLicenseAgreement() {
        return licenseAgreement;
    }

    public void setLicenseAgreement(String licenseAgreement) {
        this.licenseAgreement = licenseAgreement;
    }

    public String getBankNo() {
        return bankNo;
    }

    public void setBankNo(String bankNo) {
        this.bankNo = bankNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankRealyName() {
        return bankRealyName;
    }

    public void setBankRealyName(String bankRealyName) {
        this.bankRealyName = bankRealyName;
    }

    public String getValideCode() {
        return valideCode;
    }

    public void setValideCode(String valideCode) {
        this.valideCode = valideCode;
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
        UserAuth other = (UserAuth) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getPart() == null ? other.getPart() == null : this.getPart().equals(other.getPart()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getCardNo() == null ? other.getCardNo() == null : this.getCardNo().equals(other.getCardNo()))
            && (this.getCardFront() == null ? other.getCardFront() == null : this.getCardFront().equals(other.getCardFront()))
            && (this.getCardBack() == null ? other.getCardBack() == null : this.getCardBack().equals(other.getCardBack()))
            && (this.getProfessionInfo() == null ? other.getProfessionInfo() == null : this.getProfessionInfo().equals(other.getProfessionInfo()))
            && (this.getCompanyName() == null ? other.getCompanyName() == null : this.getCompanyName().equals(other.getCompanyName()))
            && (this.getBusinessLicense() == null ? other.getBusinessLicense() == null : this.getBusinessLicense().equals(other.getBusinessLicense()))
            && (this.getLicenseAgreement() == null ? other.getLicenseAgreement() == null : this.getLicenseAgreement().equals(other.getLicenseAgreement()))
            && (this.getBankNo() == null ? other.getBankNo() == null : this.getBankNo().equals(other.getBankNo()))
            && (this.getBankName() == null ? other.getBankName() == null : this.getBankName().equals(other.getBankName()))
            && (this.getBankRealyName() == null ? other.getBankRealyName() == null : this.getBankRealyName().equals(other.getBankRealyName()))
            && (this.getValideCode() == null ? other.getValideCode() == null : this.getValideCode().equals(other.getValideCode()))
            && (this.getCreatedAt() == null ? other.getCreatedAt() == null : this.getCreatedAt().equals(other.getCreatedAt()))
            && (this.getUpdatedAt() == null ? other.getUpdatedAt() == null : this.getUpdatedAt().equals(other.getUpdatedAt()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getPart() == null) ? 0 : getPart().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getCardNo() == null) ? 0 : getCardNo().hashCode());
        result = prime * result + ((getCardFront() == null) ? 0 : getCardFront().hashCode());
        result = prime * result + ((getCardBack() == null) ? 0 : getCardBack().hashCode());
        result = prime * result + ((getProfessionInfo() == null) ? 0 : getProfessionInfo().hashCode());
        result = prime * result + ((getCompanyName() == null) ? 0 : getCompanyName().hashCode());
        result = prime * result + ((getBusinessLicense() == null) ? 0 : getBusinessLicense().hashCode());
        result = prime * result + ((getLicenseAgreement() == null) ? 0 : getLicenseAgreement().hashCode());
        result = prime * result + ((getBankNo() == null) ? 0 : getBankNo().hashCode());
        result = prime * result + ((getBankName() == null) ? 0 : getBankName().hashCode());
        result = prime * result + ((getBankRealyName() == null) ? 0 : getBankRealyName().hashCode());
        result = prime * result + ((getValideCode() == null) ? 0 : getValideCode().hashCode());
        result = prime * result + ((getCreatedAt() == null) ? 0 : getCreatedAt().hashCode());
        result = prime * result + ((getUpdatedAt() == null) ? 0 : getUpdatedAt().hashCode());
        return result;
    }

    public UserAuth id(Integer value) {
        this.id = value;
        return this;
    }

    public UserAuth userId(Integer value) {
        this.userId = value;
        return this;
    }

    public UserAuth status(Integer value) {
        this.status = value;
        return this;
    }

    public UserAuth type(Integer value) {
        this.type = value;
        return this;
    }

    public UserAuth part(String value) {
        this.part = value;
        return this;
    }

    public UserAuth userName(String value) {
        this.userName = value;
        return this;
    }

    public UserAuth email(String value) {
        this.email = value;
        return this;
    }

    public UserAuth phone(String value) {
        this.phone = value;
        return this;
    }

    public UserAuth cardNo(String value) {
        this.cardNo = value;
        return this;
    }

    public UserAuth cardFront(String value) {
        this.cardFront = value;
        return this;
    }

    public UserAuth cardBack(String value) {
        this.cardBack = value;
        return this;
    }

    public UserAuth professionInfo(String value) {
        this.professionInfo = value;
        return this;
    }

    public UserAuth companyName(String value) {
        this.companyName = value;
        return this;
    }

    public UserAuth businessLicense(String value) {
        this.businessLicense = value;
        return this;
    }

    public UserAuth licenseAgreement(String value) {
        this.licenseAgreement = value;
        return this;
    }

    public UserAuth bankNo(String value) {
        this.bankNo = value;
        return this;
    }

    public UserAuth bankName(String value) {
        this.bankName = value;
        return this;
    }

    public UserAuth bankRealyName(String value) {
        this.bankRealyName = value;
        return this;
    }

    public UserAuth valideCode(String value) {
        this.valideCode = value;
        return this;
    }

    public UserAuth createdAt(Date value) {
        this.createdAt = value;
        return this;
    }

    public UserAuth updatedAt(Date value) {
        this.updatedAt = value;
        return this;
    }
}