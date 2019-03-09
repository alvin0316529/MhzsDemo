package com.qfh.kafka;

import kafka.producer.KeyedMessage;
import kafka.producer.Producer;
import kafka.producer.ProducerConfig;
import scala.collection.Seq;

import java.util.Date;
import java.util.Properties;
import java.util.Random;

/*
 * @Description
 * @Author alvin
 * @Date 2019-02-22 10:56:55
 */
public class HelloWorldProducer {
    public static void main(String[] args) {
        long events = Long.parseLong(args[0]);
        Random rnd = new Random();

        Properties props = new Properties();
        //kafka broker地址
        props.put("metadata.broker.list","192.168.137.176:9092,xxx:9092");
        //key的序列化类key.serializer.class可以单独配置，默认使用value的序列类
        props.put("serializer.class","kafka.serializer.StringEncoder");
        //配置partitioner选择策略，可选配置
        props.put("partitioner.class","com.qfh.kafka.SimplePartitioner");
        props.put("request.required.acks","1");

        ProducerConfig config = new ProducerConfig(props);
        Producer<String,String> producer = new Producer<String,String>(config);

        for (long nEvent = 0; nEvent < events; nEvent++) {
            long runtime = new Date().getTime();
            String ip = "192.168.110." + rnd.nextInt(255);
            String msg = runtime + ",www.example.com," + ip;
            KeyedMessage<String,String> data = new KeyedMessage<String,String>("page_visits",ip,msg);

            producer.send((Seq<KeyedMessage<String, String>>) data);
        }

        producer.close();





    }
}
