package com.mallcai.fulfillment.job.api;

import lombok.Data;

/**
 * 任务内容  业务方自己制定，暂不支持配置化
 * @author bh.zhong
 * @date 2019/10/6 11:11 PM
 */
@Data
public class JobContent {
    /**
     * 偏移量
     */
    private Integer offset;
    /**
     * 起始位置
     */
    private Integer startPosition;
    /**
     * 结束位置
     */
    private Integer endPosition;
    /**
     * 是否关注结束为止 如果无法确认最终，那么建议设置为false
     */
    private Boolean needEnd;

}
