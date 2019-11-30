package com.mallcai.fulfillment.dc.api.service.dc.operate.dto.req;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * ERP回传参数
 * @author bh.zhong
 * @date 2019/8/31 5:55 PM
 */
@Data
public class ErpCallbackRequestDTO {
    /**
     * 履约链路状态
     * @see com.mallcai.fulfillment.dc.api.service.dc.enums.CallbackStatusEnum
     */
    private Integer status;
    /**
     * key值容器编号 boxNo，value数据值
     */
    private Map<@NotNull BoxTravelRequestDTO,List<BoxDetailRequestDTO>> boxRequestDTOS;

}
