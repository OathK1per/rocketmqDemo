package com.example.rocketmq.rocketmq;

import com.example.rocketmq.bean.RocketmqMessage;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author Yuanping Zhang
 * @date
 */
@Component
public class RocketmqProducer {

    @Value("${rocketmq.producer.groupName}")
    private String groupName;

    @Value("${rocketmq.producer.namesrvAddr}")
    private String nameServerAddr;

//    private RocketMQTemplate rocketMQTemplate;

    private RocketmqMessage rocketmqMessage;

    public boolean start() {
        DefaultMQProducer producer = new DefaultMQProducer(groupName);
        byte[] bytes = rocketmqMessage.toString().getBytes();
        try {
            producer.setNamesrvAddr(nameServerAddr);
            producer.setVipChannelEnabled(false);
            producer.setSendMsgTimeout(300000);
            producer.start();
            SendResult sendResult = producer.send(new Message(rocketmqMessage.getId(), "tag", "keys", bytes));

        } catch (MQClientException e) {
            e.printStackTrace();
            producer.shutdown();
            return false;
        } catch (InterruptedException e) {
            e.printStackTrace();
            producer.shutdown();
            return false;
        } catch (RemotingException e) {
            e.printStackTrace();
            producer.shutdown();
            return false;
        } catch (MQBrokerException e) {
            e.printStackTrace();
            producer.shutdown();
            return false;
        } finally {
            producer.shutdown();
        }
        return true;
    }

    public void setMessage(RocketmqMessage rocketmqMessage) {
        this.rocketmqMessage = rocketmqMessage;
    }
}
