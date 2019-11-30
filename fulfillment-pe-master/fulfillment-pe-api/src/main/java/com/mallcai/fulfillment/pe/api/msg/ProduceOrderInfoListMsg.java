package com.mallcai.fulfillment.pe.api.msg;

import lombok.Data;

import java.util.List;

/**
 * 生产单集合
 * @author bh.zhong
 * @date 2019/8/23 7:08 PM
 */
@Data
public class ProduceOrderInfoListMsg {

    List<ProduceOrderInfoMsg> produceOrderInfoMsgList;

    public ProduceOrderInfoListMsg(){}

    public ProduceOrderInfoListMsg(List<ProduceOrderInfoMsg> produceOrderInfoMsgList){
        this.produceOrderInfoMsgList  = produceOrderInfoMsgList;
    }
}
