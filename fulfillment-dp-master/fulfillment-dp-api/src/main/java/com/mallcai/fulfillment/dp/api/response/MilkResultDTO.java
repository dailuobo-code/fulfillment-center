package com.mallcai.fulfillment.dp.api.response;

import com.mallcai.fulfillment.dp.api.common.dto.SupplierOutstockDTO;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 取奶返回DTO.
 * @author zhanghao
 * @date 2019-08-15 20:23:45
 */
@ToString
@Data
public class MilkResultDTO implements Serializable {
    private static final long serialVersionUID = -5488359895585043664L;

    /**
     * 取奶数据组装
     */
    List<SupplierOutstockDTO> milkDtoList;

    /**
     * 偏移量
     */
    private Integer offset;

    /**
     * 总记录数量
     */
    private Integer count;

    /**
     * 是否需要下一页面
     */
    private Boolean hasNext;
}
