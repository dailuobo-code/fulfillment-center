package com.mallcai.test;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

public class MqProduceTest {

    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("Fulfillment_consumer");
        producer.setNamesrvAddr("10.111.27.4:9876");
        producer.start();

        for (int i = 0; i < 3; i++)
            try {
                {
                    Message msg = new Message("City_StoreProduct_N",
                            "forecast_purchase",
                            "liuyang",
                            "{\"categoryType\":1,\"cityId\":30,\"forecastDate\":\"2019-10-16\",\"version\":0,\"warehouseId\":11}".getBytes(RemotingHelper.DEFAULT_CHARSET));
                    SendResult sendResult = producer.send(msg);
                    System.out.printf("%s%n", sendResult);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        producer.shutdown();
    }
}
