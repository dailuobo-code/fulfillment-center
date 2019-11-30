package com.mallcai.fulfillment.dc.api.service.dc.operate.warearea;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WareAreaInfo implements Serializable {

    /**
     * 仓库ID
     */
    private Integer warehouseId;
    /**
     * 仓库名称
     */
    private String warehouseName;
    /**
     * 1,生鲜仓,2标品仓,3虚拟仓
     */
    private Integer warehouseType;
    /**
     * 城市列表
     */
    private List<Integer> areas;
    /**
     * gps-经度
     */
    private String gpsLongitude;

    /**
     * gps-维度
     */
    private String gpsDimension;

    /**
     * 生效时间
     */
    private Date effectTime;

    /**
     * 创建人 (id)
     */
    private String createBy;

    /**
     * 创建时间
     */
    private Date createTime;
}
