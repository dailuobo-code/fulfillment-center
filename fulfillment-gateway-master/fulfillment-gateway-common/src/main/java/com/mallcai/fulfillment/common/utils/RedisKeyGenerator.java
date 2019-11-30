package com.mallcai.fulfillment.common.utils;

import java.util.List;

/**
 * @author wangtao
 * @description: TODO
 * @title: RedisKeyGenerator
 * @date 2019-06-11 16:48
 */
public class RedisKeyGenerator {

    /**
     * 生成用户充值券key
     */
    public static String generateRechargePointsKey(Integer userId) {
        return "rechargePoints:user_id:" + userId;
    }

    /**
     * 订单回滚开关key
     */
    public static String orderRollbackSwitchKey() {
        return "orderRollback:batchRollback:switch";
    }

    /**
     * 订单回滚异常数据
     */
    public static String orderExceptionKey() {
        return "orderRollback:exception:key";
    }

    /**
     * 订单迁移批量开关key
     */
    public static String orderTransferBatchSwitchKey() {
        return "orderTransfer:batchTransfer:switch";
    }

    /**
     * 订单迁移每日开关key
     */
    public static String orderTransferDaySwitchKey() {
        return "orderTransfer:dayTransfer:switch";
    }

    /**
     * 订单删除批量开关key
     */
    public static String orderDeleteBatchSwitchKey() {
        return "orderTransfer:batchDelete:switch";
    }

    /**
     * 订单删除每日开关key
     */
    public static String orderDeleteDaySwitchKey() {
        return "orderTransfer:dayDelete:switch";
    }

    /**
     * 每日增量订单迁移任务分布式锁key
     */
    public static String orderTransferDayTaskLockKey() {
        return "orderTransfer:dayTask:lock";
    }

    /**
     * 每日增量订单迁移任务执行结果key
     */
    public static String orderTransferDayTaskResultKey() {
        return "orderTransfer:dayTask:result";
    }

    /**
     * 历史库复原主库开关
     */
    public static String recoverOrderMainSwitchKey() {
        return "order:recovery:switch";
    }


    /**
     * 批量生成订单索引开关key
     */
    public static String generateOrderIndexSwitchKey() {
        return "orderIndex:batch:switch";
    }

    /**
     * 每日充值券迁移增量分布式锁
     */
    public static String rechargePointDayTaskLockKey() {
        return "rechargePoint_dayTask_lock";
    }

    /**
     * 充值券迁移批量执行开关锁
     */
    public static String rechargePointSwitchKey() {
        return "rechargePoint:batch:switch";
    }

    /**
     * 取货订单并发锁
     */
    public static String deliveredOrderLockKey() {
        return "lock:delivered:order:";
    }

    /**
     * 发票申请锁
     */
    public static String invoiceApplyLockKey() {
        return "lock:invoice:apply:";
    }

    /**
     * 全局发票控制开关
     */
    public static String invoiceApplySwitchKey() {
        return "invoice:apply:switch";
    }

    /**
     * 商家商品销售额
     */
    public static String merchantProductSalesAmountKey(List<Long> productIds, String day) {
        StringBuffer key = new StringBuffer("merchant:" + day + ":");
        productIds.forEach(productId -> {
            key.append(productId.toString() + ":");
        });
        return key.toString();
    }

    /**
     * 余额充值记录迁移-批量开关key
     */
    public static String depositLogTransferBatchSwitchKey() {
        return "depositLogTransfer:batchTransfer:switch";
    }

    /**
     * 余额充值记录迁移-每日开关key
     */
    public static String depositLogTransferDaySwitchKey() {
        return "depositLogTransfer:dayTransfer:switch";
    }

    /**
     * 余额充值记录删除-批量开关key
     */
    public static String depositLogDeleteBatchSwitchKey() {
        return "depositLogTransfer:batchDelete:switch";
    }

    /**
     * 余额充值记录删除-每日开关key
     */
    public static String depositLogDeleteDaySwitchKey() {
        return "depositLogTransfer:dayDelete:switch";
    }

    /**
     * 余额充值记录每日增量迁移任务-分布式锁key
     */
    public static String depositLogTransferDayTaskLockKey() {
        return "depositLogTransfer:dayTask:lock";
    }

    /**
     * 余额充值记录每日增量迁移任务-执行结果key
     */
    public static String depositLogTransferDayTaskResultKey() {
        return "depositLogTransfer:dayTask:result";
    }

    /**
     * 评价回访记录迁移-批量开关key
     */
    public static String evaluateFeedbackTransferBatchSwitchKey() {
        return "evaluateFeedbackTransfer:batchTransfer:switch";
    }


    /**
     * 异常结算数据处理key
     */
    public static String orderPickUpLockKey() {
        return "order:pickUp:lock";
    }

    /**
     * 异常结算数据处理key
     */
    public static String orderPickUpSwitchKey() {
        return "order:pickUp:switch:key";
    }

    /**
     * 线上取菜入口控制开关
     */
    public static String onlinePickUpSwitchKey() {
        return "orderOnline:pickup:switch";
    }

    /**
     * mongo订单迁移批量开关key
     */
    public static String mongoOrderTransferBatchSwitchKey() {
        return "mongoOrderTransfer:batchTransfer:switch";
    }

    /**
     * mongo订单迁移每日开关key
     */
    public static String mongoOrderTransferDaySwitchKey() {
        return "mongoOrderTransfer:dayTransfer:switch";
    }

    /**
     * mongo订单删除批量开关key
     */
    public static String mongoOrderDeleteBatchSwitchKey() {
        return "mongoOrderTransfer:batchDelete:switch";
    }

    /**
     * mongo订单删除每日开关key
     */
    public static String mongoOrderDeleteDaySwitchKey() {
        return "mongoOrderTransfer:dayDelete:switch";
    }

    /**
     * mongo每日增量订单迁移任务分布式锁key
     */
    public static String mongoOrderTransferDayTaskLockKey() {
        return "mongoOrderTransfer:dayTask:lock";
    }

    /**
     * mongo每日增量订单迁移任务执行结果key
     */
    public static String mongoOrderTransferDayTaskResultKey() {
        return "mongoOrderTransfer:dayTask:result";
    }

    /**
     * mongo订单恢复批量开关key
     */
    public static String mongoOrderRecoverBatchSwitchKey() {
        return "mongoOrderTransfer:batchRecover:switch";
    }

    /**
     * 开票数据同步索引开关key
     */
    public static String invoiceSyncDataSwitchKey() {
        return "invoice:syncData:switch";
    }

    /**
     * 消息订单消息控制开关
     */
    public static String sendOrderMsgSwitch() {
        return "sendMsg:order:switch";
    }

}
