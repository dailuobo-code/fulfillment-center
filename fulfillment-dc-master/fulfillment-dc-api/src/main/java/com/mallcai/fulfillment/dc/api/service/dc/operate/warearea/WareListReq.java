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
public class WareListReq implements Serializable {
    /**
     * 开始时间
     */
   private Date start;
    /**
     * 结束时间
     */
   private Date end;
    /**
     * 仓库ID
     */
   private Integer wareHouseId;
   /**
    * 分页参数
    */
   private Integer page;
   private Integer size;
}
