package com.mallcai.fulfillment.biz.object.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecordStatusEnum {
    INIT("初始状态"),
    SUCC("成功"),
    FAIL("失败");

    private String desc;
}
