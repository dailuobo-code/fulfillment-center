package com.mallcai.fulfillment.infrastructure.object.erp;

import java.io.Serializable;
import java.util.Date;

public class OmsStandardOrder implements Serializable {
    private Integer id;

    private String invoiceNo;

    private Integer storeId;

    private Integer cityId;

    private String pickupTime;

    private String recipients;

    private String recipientsPhone;

    private Integer standardWarehouseId;

    private Integer freshWarehouseId;

    private Integer status;

    private String result;

    private Date gmtCreate;

    private Date gmtPostBack;

    private Integer creatorId;

    private String creator;

    private Integer updaterId;

    private String updater;

    private Date gmtModified;

    private Integer orderType;

    private Integer groupType;

    private Boolean inventoryStatus;

    private Boolean isDelete;

    private Integer itemNum;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo == null ? null : invoiceNo.trim();
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(String pickupTime) {
        this.pickupTime = pickupTime == null ? null : pickupTime.trim();
    }

    public String getRecipients() {
        return recipients;
    }

    public void setRecipients(String recipients) {
        this.recipients = recipients == null ? null : recipients.trim();
    }

    public String getRecipientsPhone() {
        return recipientsPhone;
    }

    public void setRecipientsPhone(String recipientsPhone) {
        this.recipientsPhone = recipientsPhone == null ? null : recipientsPhone.trim();
    }

    public Integer getStandardWarehouseId() {
        return standardWarehouseId;
    }

    public void setStandardWarehouseId(Integer standardWarehouseId) {
        this.standardWarehouseId = standardWarehouseId;
    }

    public Integer getFreshWarehouseId() {
        return freshWarehouseId;
    }

    public void setFreshWarehouseId(Integer freshWarehouseId) {
        this.freshWarehouseId = freshWarehouseId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Date getGmtPostBack() {
        return gmtPostBack;
    }

    public void setGmtPostBack(Date gmtPostBack) {
        this.gmtPostBack = gmtPostBack;
    }

    public Integer getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(Integer creatorId) {
        this.creatorId = creatorId;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    public Integer getUpdaterId() {
        return updaterId;
    }

    public void setUpdaterId(Integer updaterId) {
        this.updaterId = updaterId;
    }

    public String getUpdater() {
        return updater;
    }

    public void setUpdater(String updater) {
        this.updater = updater == null ? null : updater.trim();
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Integer getGroupType() {
        return groupType;
    }

    public void setGroupType(Integer groupType) {
        this.groupType = groupType;
    }

    public Boolean getInventoryStatus() {
        return inventoryStatus;
    }

    public void setInventoryStatus(Boolean inventoryStatus) {
        this.inventoryStatus = inventoryStatus;
    }

    public Boolean getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Boolean isDelete) {
        this.isDelete = isDelete;
    }

    public Integer getItemNum() {
        return itemNum;
    }

    public void setItemNum(Integer itemNum) {
        this.itemNum = itemNum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", invoiceNo=").append(invoiceNo);
        sb.append(", storeId=").append(storeId);
        sb.append(", cityId=").append(cityId);
        sb.append(", pickupTime=").append(pickupTime);
        sb.append(", recipients=").append(recipients);
        sb.append(", recipientsPhone=").append(recipientsPhone);
        sb.append(", standardWarehouseId=").append(standardWarehouseId);
        sb.append(", freshWarehouseId=").append(freshWarehouseId);
        sb.append(", status=").append(status);
        sb.append(", result=").append(result);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtPostBack=").append(gmtPostBack);
        sb.append(", creatorId=").append(creatorId);
        sb.append(", creator=").append(creator);
        sb.append(", updaterId=").append(updaterId);
        sb.append(", updater=").append(updater);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", orderType=").append(orderType);
        sb.append(", groupType=").append(groupType);
        sb.append(", inventoryStatus=").append(inventoryStatus);
        sb.append(", isDelete=").append(isDelete);
        sb.append(", itemNum=").append(itemNum);
        sb.append("]");
        return sb.toString();
    }
}