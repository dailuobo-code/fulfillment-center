package com.mallcai.fulfillment.dp.biz.service.impl.execute;

import com.google.common.collect.Lists;
import com.mallcai.backend.common.utils.Arith;
import com.mallcai.fulfillment.common.constants.Constants;
import com.mallcai.fulfillment.dp.infrastructure.dal.caicai.dataobject.OrderDO;
import com.mallcai.fulfillment.dp.api.request.DeliveredDetailDTO;
import com.mallcai.service.code.api.mgr.ISOAOrderService;
import com.mallcai.service.code.vo.mgr.SOAOrderVO;
import com.mallcai.support.commun.api.IPushService;
import com.mallcai.support.commun.vo.push.SOAPushMsg;
import com.mallcai.support.commun.vo.push.SOAPushMsgMould;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * @author yl
 * @description 订单消息变更消息发送
 * @date 2019-07-18
 */
@Service
@Slf4j
public class OrderMsgService {

    @Resource
    private IPushService iPushService;
    @Resource
    private ISOAOrderService isoaOrderService;

    /**
     * 消息推送处理
     */
    public void handleMsgPush(Integer totalChange, Integer userId) {
        SOAPushMsgMould msgMould = iPushService
                .getMould(Constants.PUSH_MOULD_TYPE_1, Constants.PUSH_MOULD_MSGTYPE_4);

        if (msgMould != null) {
            com.mallcai.support.commun.vo.push.SOAPushMsg msg = new SOAPushMsg();
            msg.setMouldId(msgMould.getId());
            msg.setTitle(msgMould.getTitle());
            msg.setLinkContent(msgMould.getLinkContent());
            msg.setLinkMode(msgMould.getLinkMode());
            msg.setLinkType(msgMould.getLinkType());
            msg.setType(msgMould.getType());
            msg.setCityId(msgMould.getCityId());
            msg.setMsgType(msgMould.getMsgType());
            msg.setScheme(msgMould.getScheme() + "&linkNme=" + msgMould.getTitle());
            String content = msgMould.getContent();
            String beforeContent = content.substring(0, content.indexOf("###"));
            String afterContent = content.substring(content.indexOf("###") + 3);
            beforeContent = beforeContent.replace("${time}", new SimpleDateFormat("M月d日H点m分").format(
                    Calendar.getInstance().getTime()));

            if (totalChange > 0) {
                afterContent = afterContent
                        .replace("${amount}", String.valueOf(Arith.mul(totalChange, 0.01f)));
                beforeContent = beforeContent + afterContent;
            }
            String title = msgMould.getTitle();
            iPushService.push(beforeContent, title, msg, userId);
        }
    }

    public boolean sendUpdateMsg(OrderDO order, DeliveredDetailDTO deliveredDetail) {
        try {
            List<SOAOrderVO> soaOrderVOList = Lists.newArrayList();
            SOAOrderVO soaOrderVO = new SOAOrderVO();
            soaOrderVO.setChange(order.getChange());
            soaOrderVO.setCityId(order.getCityId());
            soaOrderVO.setOrderId(order.getOrderId());
            soaOrderVO.setCompleteUserId(deliveredDetail.getOperatorId());
            soaOrderVOList.add(soaOrderVO);
            isoaOrderService.sendRefundRepair(soaOrderVOList);
            return true;
        } catch (Exception e) {
            log.error("消息发送失败:orderId:" + order.getOrderId(), e);
            return false;
        }
    }

}