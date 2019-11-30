package com.mallcai.fulfillment.dp.api.response;

import java.io.Serializable;
import lombok.Data;

/**
 * 快递商家自配送结构体
 * @author Liu Yangß
 * @date 2019/9/17 19:27 PM
 */
@Data
public class ExpressInfoResultDTO implements Serializable {

    private static final long serialVersionUID = -6867154839535220493L;
    /**
     * 商家填写的快递单号
     */
    private String expressNo;
    /**
     * 承运公司名称
     */
    private String expressName;
}
