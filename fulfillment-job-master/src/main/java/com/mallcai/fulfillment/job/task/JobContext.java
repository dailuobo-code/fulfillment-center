package com.mallcai.fulfillment.job.task;

import com.mallcai.fulfillment.job.api.JobContent;
import com.mallcai.fulfillment.job.enums.JobEvent;

/**
 * 任务消息上下文
 * @author bh.zhong
 * @date 2019/10/6 11:08 PM
 */
public interface JobContext {

    /**
     * 获取根任务ID
     */
    String getRootId();

    /**
     *  获取任务ID, 全局唯一
     */
    String getTaskId();

    /**
     *  获取任务父ID
     */
    String getParentId();

    /**
     *  获取任务事件, {@link JobEvent}
     */
    String getEvent();

    /**
     *  获取任务内容
     */
    Object getContent();

    /**
     *  获取任务内容
     */
    <T extends JobContent> T getContent(Class<T> clazz);

}
