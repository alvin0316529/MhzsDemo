package com.qfh.spark

import org.apache.spark.{SparkConf, SparkContext}

object WordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("wc").setMaster("local[2]")
    val sc  = new SparkContext(conf)

    val wcRDD = sc.textFile("file:///F:\\data\\spark\\spark1.txt")

    val resultRDD = wcRDD.flatMap(_.split(" ")).map((_,1)).reduceByKey((a,b) => a + b)
    resultRDD.sortBy(_._2,false,2).collect().foreach(x => println(x))

    sc.stop()
  }
}
