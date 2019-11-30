package com.mallcai.fulfillment.dp.biz.service.impl.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * ActualOrderDetailDTO
 *
 * @author zhanghao
 * @date 2019年08月13日23:39:04
 */
@Data
public class ActualOrderDetailDTO implements Serializable {

    private static final long serialVersionUID = 4370989524024926052L;

    private String orderId;
    private Integer cityProductId;
    private List<Integer> actualWeight;
    private List<Integer> isOutStock;
    private List<String> sortingStr;
    private Integer operUserId;

}
