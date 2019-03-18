package com.qfh.hadoop;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

/*
 * @Description 二次排序
 * @Author alvin
 * @Date 2019-03-15 19:36:26
 */
public class SecondarySort {
    /**
     * mapper
     */
    public static class LineProcessMapper extends Mapper<Object,Text,KeyPairWritable,IntWritable>{
        private KeyPairWritable outputKey = new KeyPairWritable();
        private IntWritable outputValue = new IntWritable();

        @Override
        protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            if (line != null && line.trim().length() > 0) {
                String[] columnArray = line.split("\\s");
                outputKey.set(columnArray[0],Integer.parseInt(columnArray[2]));
                outputValue.set(Integer.parseInt(columnArray[1]));
                context.write(outputKey,outputValue);
            }
        }
    }

    /**
     * reducer
     */
    public static class SortReducer extends Reducer<KeyPairWritable,IntWritable,Text,IntWritable>{
        private Text outputKey = new Text();
        @Override
        protected void reduce(KeyPairWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            //进来时已经排序完成
            outputKey.set(key.getKey1());
            for (IntWritable val : values) {
                context.write(outputKey,val);
            }
        }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //得到集群参数
        Configuration conf = new Configuration();
        //参数解析器
        String[] otherArgs = new GenericOptionsParser(conf,args).getRemainingArgs();

        if (otherArgs.length != 2) {
            System.err.println("Usage: secondarysort <in> <out>");
            System.exit(2);
        }

        Job job = new Job(conf,"secondarySort");
        //指定本次执行的主类
        job.setJarByClass(SecondarySort.class);

        //指定map类
        job.setMapperClass(LineProcessMapper.class);
        //指定partition类
        job.setPartitionerClass(FirstPartitioner.class);
        job.setGroupingComparatorClass(GroupComparator.class);

        //指定reduce类
        job.setReducerClass(SortReducer.class);
        //指定job 输出map和reduce类型
        job.setMapOutputKeyClass(KeyPairWritable.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //指定数据输入路径
        FileInputFormat.addInputPath(job,new Path(otherArgs[0]));
        //指定输出路径
        FileOutputFormat.setOutputPath(job,new Path(otherArgs[1]));
        //指定job执行模式
        System.exit(job.waitForCompletion(true) ? 0:1);
    }
}
























