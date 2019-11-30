package com.mallcai.fulfillment.pe.api.service.operate.dto.rsp;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * 示例
 * @author zhanghao
 * @date 2019年08月13日23:43:53
 */
@Getter
@Setter
@ToString
public class ExampleResponseDTO implements Serializable {

    private static final long serialVersionUID = -637640421086025898L;
    /**
     * 例子
     */
    private String example;

}
