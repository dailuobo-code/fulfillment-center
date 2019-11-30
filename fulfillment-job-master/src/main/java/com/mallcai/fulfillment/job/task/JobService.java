package com.mallcai.fulfillment.job.task;

import com.mallcai.fulfillment.job.api.JobContent;

/**
 * 任务处理器
 * @author bh.zhong
 * @date 2019/10/6 10:58 PM
 */
public interface JobService {

    /**
     * 分割
     *
     * @param context 上下文
     */
    void split(JobContent context);

    /**
     * 加载
     *
     * @param context 上下文
     */
    void load(JobContent context);

    /**
     * 处理
     *
     * @param context 上下文
     */
    void execute(JobContent context);

}
