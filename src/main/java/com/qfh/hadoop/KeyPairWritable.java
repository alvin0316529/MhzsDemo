package com.qfh.hadoop;

import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/*
 * @Description
 * @Author alvin
 * @Date 2019-03-15 20:05:11
 */
public class KeyPairWritable implements WritableComparable<KeyPairWritable> {
    private String key1;
    private int key2;
    public KeyPairWritable(){};

    public KeyPairWritable(String key1, int key2) {
        this.set(key1, key2);
    }

    public void set(String key1, int key2) {
        this.key1 = key1;
        this.key2 = key2;
    }

    /**
     * 当map端写出的时候的序列化方法
     * @param dataOutput
     * @throws IOException
     */
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(key1);
        dataOutput.writeInt(key2);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.key1 = dataInput.readUTF();
        this.key2 =  dataInput.readInt();
    }

    @Override
    public int compareTo(KeyPairWritable o) {
        int compare = this.key1.compareTo(o.key1);
        if(compare != 0){
            return compare;
        }else{
            //降序排列，将o放到前边即可
            return Integer.valueOf(o.key2).compareTo(Integer.valueOf(this.key2));
        }
    }

    public String getKey1() {
        return key1;
    }

    public void setKey1(String key1) {
        this.key1 = key1;
    }

    public int getKey2() {
        return key2;
    }

    public void setKey2(int key2) {
        this.key2 = key2;
    }
}
