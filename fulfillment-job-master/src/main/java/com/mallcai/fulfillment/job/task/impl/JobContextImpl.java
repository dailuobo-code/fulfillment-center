package com.mallcai.fulfillment.job.task.impl;

import com.alibaba.fastjson.JSON;
import com.mallcai.fulfillment.job.api.JobContent;
import com.mallcai.fulfillment.job.enums.JobEvent;
import com.mallcai.fulfillment.job.task.JobContext;

/**
 * 任务消息上下文
 * @author bh.zhong
 * @date 2019/10/6 11:08 PM
 */
public class JobContextImpl implements JobContext {

    /**
     * 根任务ID
     */
    private String rootId;
    /**
     * 任务ID, 全局唯一
     */
    private String taskId;
    /**
     * 任务父ID
     */
    private String parentId;
    /**
     * 任务事件, {@link JobEvent}
     */
    private String event;
    /**
     * 任务内容
     */
    private Object content;

    @Override
    public String getRootId() {
        return rootId;
    }

    public void setRootId(String rootId) {
        this.rootId = rootId;
    }

    @Override
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    @Override
    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    @Override
    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    @Override
    public Object getContent() {
        return content;
    }

    public void setContent(Object content) {
        this.content = content;
    }

    @Override
    public <T extends JobContent> T getContent(Class<T> clazz) {
        if (content == null) {
            return null;
        }
        return JSON.toJavaObject((JSON) content, clazz);
    }

    @Override
    public String toString() {
        return "TaskContextImpl{" + "rootId='" + rootId + '\'' + ", taskId='" + taskId + '\'' + ", parentId='"
               + parentId + '\'' + ", event='" + event + '\'' + ", content=" + content + '}';
    }
}
