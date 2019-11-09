package com.qfh.spark

import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{SparkConf, SparkContext}

object WordCountStreaming {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setAppName("WordCountStreaming").setMaster("local[2]")
    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(sc,Seconds(5))
    //=============================
    val lines = ssc.socketTextStream("localhost",9999)

    val wordcountStream = lines.flatMap(line => line.split(" "))
                               .map(word => (word,1))
                               .reduceByKey(_+_)

    wordcountStream.print()
    //============================

    ssc.start()
    ssc.awaitTermination()

  }
}
