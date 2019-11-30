package com.mallcai.fulfillment.infrastructure.object.cp;

import java.io.Serializable;
import java.util.Date;

public class TaskRecord implements Serializable {
    private String checkNo;

    private String bizTag;

    private Date checkTime;

    private String checkParam;

    private String status;

    private Integer indexSize;

    private Integer checkSize;

    private Integer matchCnt;

    private Integer dismatchCnt;

    private Integer costTime;

    private String createBy;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

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

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }

    public String getCheckParam() {
        return checkParam;
    }

    public void setCheckParam(String checkParam) {
        this.checkParam = checkParam == null ? null : checkParam.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Integer getIndexSize() {
        return indexSize;
    }

    public void setIndexSize(Integer indexSize) {
        this.indexSize = indexSize;
    }

    public Integer getCheckSize() {
        return checkSize;
    }

    public void setCheckSize(Integer checkSize) {
        this.checkSize = checkSize;
    }

    public Integer getMatchCnt() {
        return matchCnt;
    }

    public void setMatchCnt(Integer matchCnt) {
        this.matchCnt = matchCnt;
    }

    public Integer getDismatchCnt() {
        return dismatchCnt;
    }

    public void setDismatchCnt(Integer dismatchCnt) {
        this.dismatchCnt = dismatchCnt;
    }

    public Integer getCostTime() {
        return costTime;
    }

    public void setCostTime(Integer costTime) {
        this.costTime = costTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy == null ? null : createBy.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", checkNo=").append(checkNo);
        sb.append(", bizTag=").append(bizTag);
        sb.append(", checkTime=").append(checkTime);
        sb.append(", checkParam=").append(checkParam);
        sb.append(", status=").append(status);
        sb.append(", indexSize=").append(indexSize);
        sb.append(", checkSize=").append(checkSize);
        sb.append(", matchCnt=").append(matchCnt);
        sb.append(", dismatchCnt=").append(dismatchCnt);
        sb.append(", costTime=").append(costTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}