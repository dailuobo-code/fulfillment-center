package com.mallcai.fulfillment.dp.api.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 多包裹场景--快递信息查询
 * @author gaoguoming
 * @date 2019-10-28 20:30:25
 */
@Data
public class DistOrderMultiPkgDTO implements Serializable {
    private static final long serialVersionUID = 6876254351960147564L;

    private String orderNo;

    private List<DistOrderExpressInfo> distOrderInfoList;
}
