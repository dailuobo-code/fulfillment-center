package com.mallcai.fulfillment.pe.biz.service.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.metadata.BaseRowModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Date;

@Data
@Slf4j
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AggreGoodXLSModel extends BaseRowModel implements Serializable {
    @ExcelProperty(value = "仓库ID" ,index = 0)
    private Integer warehouse;
    @ExcelProperty(value = "仓库名称" ,index = 1)
    private String warehouseName;
    @ExcelProperty(value = "门店No" ,index = 2)
    private Integer storeNo;
    @ExcelProperty(value = "货品ID" ,index = 3)
    private String goodsId;
    @ExcelProperty(value = "货品名称" ,index = 4)
    private String goodsName;
    @ExcelProperty(value = "推单时间" ,index = 5)
    private String pushTime;
    @ExcelProperty(value = "货品计量" ,index = 6)
    private String goodWeights;
    @ExcelProperty(value = "货品单位" ,index = 7)
    private String unit ;
}
