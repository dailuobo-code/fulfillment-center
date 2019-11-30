package com.mallcai.fulfillment.dp.api.request;

import lombok.Data;

import java.io.Serializable;

/**
 * 快递商家自配送结构体
 * @author bh.zhong
 * @date 2019/9/6 10:27 AM
 */
@Data
public class ExpressMerchantDTO implements Serializable {


    private static final long serialVersionUID = -3931318760372232813L;
    /**
     * 商家填写的快递单号
     */
    private String expressNo;
    /**
     * 承运公司名称
     */
    private String expressName;
}
