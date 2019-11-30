package com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req;

import lombok.Data;

/**
 * 箱框轨迹
 * @author bh.zhong
 * @date 2019/9/2 10:28 AM
 */
@Data
public class BoxTravelRequestDTO {
    /**
     * 箱框编号
     */
    private String boxNo;
    /**
     * 节点时间 yyyy-mm-dd hh:mm:ss
     */
    private String travelNodeTime;

}
