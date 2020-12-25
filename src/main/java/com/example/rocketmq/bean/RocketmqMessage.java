package com.example.rocketmq.bean;

import java.io.Serializable;

/**
 * @author Yuanping Zhang
 * @date
 */
public class RocketmqMessage implements Serializable {
    private String id;
    private String message;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RocketmqMessage{" +
                "id='" + id + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
