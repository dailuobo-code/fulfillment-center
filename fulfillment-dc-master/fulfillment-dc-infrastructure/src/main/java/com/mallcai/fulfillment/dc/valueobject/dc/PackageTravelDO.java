package com.mallcai.fulfillment.dc.valueobject.dc;

import lombok.Data;

import java.util.Date;

/**
 * 包裹轨迹信息
 * @author bh.zhong
 * @date 2019/9/1 8:59 PM
 */
@Data
public class PackageTravelDO {

    private Integer id;

    private String packageNo;

    private Integer status;

    private String statusMsg;

    private String travelTime;

    private Date createTime;

    private Date updateTime;
}
