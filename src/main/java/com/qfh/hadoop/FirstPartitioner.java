package com.qfh.hadoop;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/*
 * @Description
 * @Author alvin
 * @Date 2019-03-18 11:31:27
 */
public class FirstPartitioner extends Partitioner<KeyPairWritable,IntWritable> {
    @Override
    public int getPartition(KeyPairWritable key, IntWritable value, int numPartitions) {
        return (key.getKey1().hashCode() & Integer.MAX_VALUE ) % numPartitions;
    }
}
