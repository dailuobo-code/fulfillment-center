package com.mallcai.fulfillment.pe.biz.service.inner;

/**
 * @description: 生鲜数据检验接口
 * @author: liuyang
 * @create: 2019-10-28 20:00:26
 */
public interface FreshDataCheckService {

    /**
     * 生鲜预测命令是否下发至履约的检查时间配置
     */
    void checkFreshData();
}
