package com.mallcai.fulfillment.pe.api.service.operate.dto.req;

import lombok.Data;

import java.io.Serializable;

/**
 * 删除拣货规则参数
 */
@Data
public class DeletePickingRuleRequestDTO implements Serializable {

    private static final long serialVersionUID = -1;

    /** 主键 */
    private Long id;

    /** 操作人用户id*/
    private Integer userId;
}
