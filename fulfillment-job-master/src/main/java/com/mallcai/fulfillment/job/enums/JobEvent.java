package com.mallcai.fulfillment.job.enums;

import lombok.Getter;

/**
 * 处理事件
 *
 * @author bh.zhong
 * @date 2019/10/6 11:05 PM
 */
public enum JobEvent {

    SPLITOR("分割"),
    LOADER("加载"),
    EXECUTOR("处理");

    @Getter
    private String desc;

    JobEvent(String desc) {
        this.desc = desc;
    }



    public static JobEvent of(String name) {
        for (JobEvent e : values()) {
            if (e.name().equals(name)) {
                return e;
            }
        }
        return null;
    }

}
