package com.mallcai.fulfillment.dp.api.response;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @author gaoguoming
 * @date 2019-11-08 15:29:14
 */
@Data
@AllArgsConstructor
public class ErrorOrderInfo implements Serializable {
    private static final long serialVersionUID = 6478124919997011360L;

    private String orderNo;

    private String message;
}
