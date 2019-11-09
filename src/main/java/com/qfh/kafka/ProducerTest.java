package com.qfh.kafka;



import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

/*
 * @Description
 * @Author alvin
 * @Date 2019-02-22 10:56:55
 */
public class ProducerTest {
    public static void main(String[] args) {
        Random rnd = new Random();

        Properties props = new Properties();
        //kafka broker地址   必填
        props.put("bootstrap.servers","slave01.bigdata:9092,slave02.bigdata:9092");
        //key的序列化类key.serializer.class可以单独配置，默认使用value的序列类  必填
        //该参数就是为消息的key做序列化只用的。
        props.put("key.serializer","org.apache.kafka.common.serialization.StringSerializer");
        // 将消息value部分转换成字节数组。
        props.put("value.serializer","org.apache.kafka.common.serialization.StringSerializer");

        //acks参数用于控制producer生产消息的持久性（durability）。参数可选值，0、1、-1（all）。
        props.put("acks","-1");

        //在producer内部自动实现了消息重新发送。默认值0代表不进行重试。
        props.put("retries","3");



        Producer<String,String> producer = new KafkaProducer<String, String>(props);

        for (int nEvent = 0; nEvent < 100; nEvent++) {
            long runtime = new Date().getTime();
            String ip = "192.168.110." + rnd.nextInt(255);
            String msg = runtime + ",www.jd.com," + ip + "," + nEvent;

            producer.send(new ProducerRecord<String, String>("kfk_topic",Integer.toString(nEvent),msg));
        }

        producer.close();

    }
}
