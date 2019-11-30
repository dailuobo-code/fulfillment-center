package com.mallcai.fulfillment.pe.biz.service.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

/**
 * @description: 生产单处理消息，分布式集单完成后会发出分割所有生产单，产生此消息用于
 * 分布式创建生产单
 * @author: chentao
 * @create: 2019-10-12 01:54:43
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProduceOrderHandleMessage {

    private List<Map.Entry<String, String>> produceOrderKeyAndSizeList;
}
