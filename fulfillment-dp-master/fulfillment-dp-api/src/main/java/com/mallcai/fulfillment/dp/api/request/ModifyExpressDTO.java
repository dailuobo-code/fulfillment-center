package com.mallcai.fulfillment.dp.api.request;

import lombok.Data;

import java.io.Serializable;

/**
 * @author bh.zhong
 * @date 2019/9/18 3:03 PM
 */
@Data
public class ModifyExpressDTO implements Serializable {
    private static final long serialVersionUID = 1656963176315773913L;
    /**
     * 订单号
     */
    private String orderNo;
    /**
     * 快递公司名称
     */
    private String expressName;
    /**
     * 快递公司编号
     */
    private String expressNo;
}
