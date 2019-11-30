package com.mallcai.fulfillment.dp.infrastructure.dal.trade.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author yl
 * @description SettlementQueryDTO
 * @date 2019-08-14
 */
@Data
public class SettlementQueryDTO {

  private List<String> orderIdList;
  private Date startTime;
  private Date endTime;
  private Long lastId;
  private Integer limit;

}
