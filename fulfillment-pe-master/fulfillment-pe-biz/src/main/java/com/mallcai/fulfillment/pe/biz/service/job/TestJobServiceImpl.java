/*
package com.mallcai.fulfillment.pe.biz.service.job;


import com.mallcai.fulfillment.job.api.AbstractJobService;
import com.mallcai.fulfillment.job.api.bo.SplitDataBO;
import com.mallcai.fulfillment.job.task.impl.JobExecutorImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.AbstractList;

*/
/**
 * 例：批处理任务执行器
 *
 * @author bh.zhong
 * @date 2019/10/9 5:05 PM
 *//*

@Service("testJobService")
public class TestJobServiceImpl extends AbstractJobService {

    @Resource(name = "jobExecutor")
    private JobExecutorImpl jobExecutor;

    */
/**
     * 数据拆分
     * @return
     *//*


    public void doJob(){
        SplitDataBO splitDataBO = new SplitDataBO();
        splitDataBO.setBusinessContent(1);
        splitDataBO.setTotalCount(30000);
        splitDataBO.setSplitNum(10000);
        jobExecutor.split(splitDataBO);
    }

    */
/**
     * 数据加载
     * @param businessContent
     * @return
     *//*

    @Override
    public Object load(Object businessContent){

        return null;
    }

    */
/**
     * 数据执行
     * @param businessContent
     *//*

    @Override
    public void execute(Object businessContent){

        return;
    }

    @Override
    public void checkOk(Object o) {

    }


}
*/
