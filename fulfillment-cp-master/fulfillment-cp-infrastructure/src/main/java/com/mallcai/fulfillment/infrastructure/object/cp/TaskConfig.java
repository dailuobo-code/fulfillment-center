package com.mallcai.fulfillment.infrastructure.object.cp;

import java.io.Serializable;
import java.util.Date;

public class TaskConfig implements Serializable {
    private Long id;

    private String bizTag;

    private String bizDesc;

    private Date lastCheckTime;

    private Integer checkIntel;

    private String leftLoaderBean;

    private String rightLoaderBean;

    private String loadIndexBean;

    private String checkBean;

    private String resultBean;

    private String createBy;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBizTag() {
        return bizTag;
    }

    public void setBizTag(String bizTag) {
        this.bizTag = bizTag == null ? null : bizTag.trim();
    }

    public String getBizDesc() {
        return bizDesc;
    }

    public void setBizDesc(String bizDesc) {
        this.bizDesc = bizDesc == null ? null : bizDesc.trim();
    }

    public Date getLastCheckTime() {
        return lastCheckTime;
    }

    public void setLastCheckTime(Date lastCheckTime) {
        this.lastCheckTime = lastCheckTime;
    }

    public Integer getCheckIntel() {
        return checkIntel;
    }

    public void setCheckIntel(Integer checkIntel) {
        this.checkIntel = checkIntel;
    }

    public String getLeftLoaderBean() {
        return leftLoaderBean;
    }

    public void setLeftLoaderBean(String leftLoaderBean) {
        this.leftLoaderBean = leftLoaderBean == null ? null : leftLoaderBean.trim();
    }

    public String getRightLoaderBean() {
        return rightLoaderBean;
    }

    public void setRightLoaderBean(String rightLoaderBean) {
        this.rightLoaderBean = rightLoaderBean == null ? null : rightLoaderBean.trim();
    }

    public String getLoadIndexBean() {
        return loadIndexBean;
    }

    public void setLoadIndexBean(String loadIndexBean) {
        this.loadIndexBean = loadIndexBean == null ? null : loadIndexBean.trim();
    }

    public String getCheckBean() {
        return checkBean;
    }

    public void setCheckBean(String checkBean) {
        this.checkBean = checkBean == null ? null : checkBean.trim();
    }

    public String getResultBean() {
        return resultBean;
    }

    public void setResultBean(String resultBean) {
        this.resultBean = resultBean == null ? null : resultBean.trim();
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
        sb.append(", id=").append(id);
        sb.append(", bizTag=").append(bizTag);
        sb.append(", bizDesc=").append(bizDesc);
        sb.append(", lastCheckTime=").append(lastCheckTime);
        sb.append(", checkIntel=").append(checkIntel);
        sb.append(", leftLoaderBean=").append(leftLoaderBean);
        sb.append(", rightLoaderBean=").append(rightLoaderBean);
        sb.append(", loadIndexBean=").append(loadIndexBean);
        sb.append(", checkBean=").append(checkBean);
        sb.append(", resultBean=").append(resultBean);
        sb.append(", createBy=").append(createBy);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}