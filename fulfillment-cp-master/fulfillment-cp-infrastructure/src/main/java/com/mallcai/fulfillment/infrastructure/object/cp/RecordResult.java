package com.mallcai.fulfillment.infrastructure.object.cp;

import java.io.Serializable;
import java.util.Date;

public class RecordResult implements Serializable {
    private Long id;

    private String checkNo;

    private String bizTag;

    private String checkKey;

    private String leftValue;

    private String rightValue;

    private Boolean isPattern;

    private Date createTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCheckNo() {
        return checkNo;
    }

    public void setCheckNo(String checkNo) {
        this.checkNo = checkNo == null ? null : checkNo.trim();
    }

    public String getBizTag() {
        return bizTag;
    }

    public void setBizTag(String bizTag) {
        this.bizTag = bizTag == null ? null : bizTag.trim();
    }

    public String getCheckKey() {
        return checkKey;
    }

    public void setCheckKey(String checkKey) {
        this.checkKey = checkKey == null ? null : checkKey.trim();
    }

    public String getLeftValue() {
        return leftValue;
    }

    public void setLeftValue(String leftValue) {
        this.leftValue = leftValue == null ? null : leftValue.trim();
    }

    public String getRightValue() {
        return rightValue;
    }

    public void setRightValue(String rightValue) {
        this.rightValue = rightValue == null ? null : rightValue.trim();
    }

    public Boolean getIsPattern() {
        return isPattern;
    }

    public void setIsPattern(Boolean isPattern) {
        this.isPattern = isPattern;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", checkNo=").append(checkNo);
        sb.append(", bizTag=").append(bizTag);
        sb.append(", checkKey=").append(checkKey);
        sb.append(", leftValue=").append(leftValue);
        sb.append(", rightValue=").append(rightValue);
        sb.append(", isPattern=").append(isPattern);
        sb.append(", createTime=").append(createTime);
        sb.append("]");
        return sb.toString();
    }
}