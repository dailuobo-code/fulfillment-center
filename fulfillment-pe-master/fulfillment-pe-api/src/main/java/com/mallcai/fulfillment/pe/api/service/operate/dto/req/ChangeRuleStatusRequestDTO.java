package com.mallcai.fulfillment.pe.api.service.operate.dto.req;

import lombok.Data;

import java.io.Serializable;

/**
 * 启用/禁用 生产规则
 * @author zhanghao
 * @date 2019年08月13日23:41:10
 */
@Data
public class ChangeRuleStatusRequestDTO implements Serializable {

    private static final long serialVersionUID = -1;

    /** 数据库id*/
    private Long id;

    /**
     * 状态 0-禁用 1-启用
     */
    private Integer status;

    /** 操作人用户id*/
    private Integer userId;
}
