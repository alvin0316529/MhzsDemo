package com.qfh.hbase;

import com.alibaba.fastjson.JSON;
import com.qfh.domain.User;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.*;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class WriteToHbase {
    public static void main(String[] args) {
        //1.read from kafka
        String topics  =  "user_topic";
        String groupId = "group2";
        Properties props = new Properties();
        props.put("bootstrap.servers","slave01.bigdata:9092,slave02.bigdata:9092");
        props.put("key.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer","org.apache.kafka.common.serialization.StringDeserializer");
        props.put("group.id",groupId);
        // 从最早的消息开始读取
        props.put("auto.offset.reset", "earliest");

        KafkaConsumer<String,String> consumer = new KafkaConsumer<String, String>(props);

        consumer.subscribe(Arrays.asList(topics));

        try {
            while (true) {
                List<User> usersList = new ArrayList<>();
                ConsumerRecords<String, String> records = consumer.poll(100);
                System.out.println("records" + records.count());
                for (ConsumerRecord<String, String> record : records) {
                    String userStr = record.value();
                    User user = JSON.parseObject(userStr,User.class);
                    usersList.add(user);
                }

                System.out.println("size:" + usersList.size());
                //2.write to hbase
                if (null != usersList && usersList.size() > 0){
                    Configuration conf = HBaseConfiguration.create();
                    conf.set("hbase.zookeeper.quorum","master01.bigdata:2181,slave01.bigdata:2181,slave02.bigdata:2181");
                    Connection conn = ConnectionFactory.createConnection(conf);

                    //获取表对象
                    Table table = conn.getTable(TableName.valueOf("user_test"));
                   // Filter filter = new RowFilter(CompareFilter.CompareOp.EQUAL,new RegexStringComparator(".*01$"));
                    // Filter filter1 = new RowFilter(CompareFilter.CompareOp.EQUAL,new SubstringComparator("201407"));
                    //Filter filter2 = new RowFilter(CompareFilter.CompareOp.EQUAL,new BinaryPrefixComparator("123".getBytes()));

                    /*String pre = "a";
                    Scan scan = new Scan(pre.getBytes());
                    scan.setFilter(new PrefixFilter(pre.getBytes()));*/


                    //Filter filter = new ValueFilter(CompareFilter.CompareOp.LESS_OR_EQUAL,new BinaryPrefixComparator("foo".getBytes()));

                    List<Put> puts = new ArrayList<>();
                    for (User user : usersList) {
                        //创建put对象，一个put操作一行数据，并设置rowkey名称
                        String key = user.getUserId() + "|" + user.getCity() + "|" + user.getProfessional();
                        Put put = new Put(key.getBytes());
                        //添加列  指定列族和列名称
                        put.addColumn("info".getBytes(),"userId".getBytes(),user.getUserId().getBytes());
                        put.addColumn("info".getBytes(),"userName".getBytes(),user.getUserName().getBytes());
                        put.addColumn("info".getBytes(),"realName".getBytes(),user.getRealName().getBytes());
                        put.addColumn("info".getBytes(),"age".getBytes(), Bytes.toBytes(user.getAge()));
                        put.addColumn("info".getBytes(),"professional".getBytes(),user.getProfessional().getBytes());
                        put.addColumn("info".getBytes(),"city".getBytes(),user.getCity().getBytes());
                        puts.add(put);
                    }
                    table.put(puts);
                    table.close();
                    conn.close();
                }

            }
        } catch (Exception e) {
            //关闭kafkaConsumer
            consumer.close();
        }
    }
}
