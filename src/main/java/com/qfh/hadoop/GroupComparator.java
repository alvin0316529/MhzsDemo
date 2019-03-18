package com.qfh.hadoop;


import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/*
 * @Description
 * @Author alvin
 * @Date 2019-03-18 15:23:23
 */
public class GroupComparator extends WritableComparator {
    public GroupComparator() {
        super(KeyPairWritable.class,true);
    }

    @Override
    public int compare(WritableComparable first, WritableComparable second) {
        if(first == null || second == null){
            return 0;
        }

        KeyPairWritable newKey1 = (KeyPairWritable) first;
        KeyPairWritable newKey2 = (KeyPairWritable) second;
        return newKey1.getKey1().compareTo(newKey2.getKey1());
    }
}
