package com.example.rocketmq.rocketmq;

import com.example.rocketmq.bean.RocketmqMessage;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Yuanping Zhang
 * @date
 */
@Component
public class RocketmqConsumer {

    @Value("${rocketmq.producer.groupName}")
    private String groupName;

    @Value("${rocketmq.producer.namesrvAddr}")
    private String nameServerAddr;

    private RocketmqMessage rocketmqMessage;

    public boolean receive() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer();

        try {
            consumer.start();
        } catch (MQClientException e) {
            e.printStackTrace();
        }
    }
}
