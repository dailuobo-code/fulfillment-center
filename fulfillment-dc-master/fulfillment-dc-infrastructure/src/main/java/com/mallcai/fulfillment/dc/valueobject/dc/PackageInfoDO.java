package com.mallcai.fulfillment.dc.valueobject.dc;

import lombok.Data;

import java.util.Date;

/**
 * 包裹信息
 * @author bh.zhong
 * @date 2019/8/16 11:55 AM
 */
@Data
public class PackageInfoDO {
    private Integer id;

    private String packageNo;

    private Integer status;

    private String statusMsg;

    private String produceOrderNo;

    private String productNo;

    private Integer productNum;

    private Integer cityId;

    private Date createTime;

    private Date updateTime;
}
