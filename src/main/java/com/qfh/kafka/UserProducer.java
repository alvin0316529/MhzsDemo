package com.qfh.kafka;

import com.alibaba.fastjson.JSON;
import com.qfh.domain.User;
import com.qfh.utils.MockData;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

public class UserProducer {
    public static void main(String[] args) {
        Properties props  = new Properties();
        props.put("bootstrap.servers","slave01.bigdata:9092,slave02.bigdata:9092");
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        //acks参数用于控制producer生产消息的持久性（durability）。参数可选值，0、1、-1（all）。
        props.put("acks","-1");

        //在producer内部自动实现了消息重新发送。默认值0代表不进行重试。
        props.put("retries","3");

        Producer<String, String> producer = new KafkaProducer(props);
        for (int i = 0; i < 100; i++) {
            User user = MockData.getUser();
            String key = user.getUserId() + "|" + user.getCity() + "|" + user.getProfessional();
            String userJson = JSON.toJSONString(user);
            producer.send(new ProducerRecord<String, String>("user_topic",key,userJson));
            System.out.println("i=" + i);

            System.out.println("key=" + key + "&user=" + userJson);

            User us = JSON.parseObject(userJson,User.class);
            System.out.println(us.getCity());
        }
    }
}
