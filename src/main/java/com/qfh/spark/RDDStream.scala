package com.qfh.spark

import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.streaming.{Seconds, StreamingContext}

import scala.collection.mutable

/**
  * @Description
  * @Author alvin
  * @Date 2019-12-24 13:53:51
  */
object RDDStream {
  def main(args: Array[String]): Unit = {
    //1.初始化Spark配置信息
    val conf = new SparkConf().setMaster("local[*]").setAppName("RDDStream")

    //2.初始化StreamingContext
    val ssc = new StreamingContext(conf,Seconds(4))

    //3.创建RDD队列
    val rddQueue = new mutable.Queue[RDD[Int]]()

    //4.初始化InputDStream
    val inputStream = ssc.queueStream(rddQueue,oneAtATime = false)

    //5.处理队列中的RDD数据
    val mappedStream = inputStream.map((_,1))
    val reducedStream = mappedStream.reduceByKey(_+_)



    reducedStream.print()

    ssc.start()

    //循环创建并向 RDD队列中放入 RDD
//    for (i <- 1 to 5){
//      rddQueue += ssc.sparkContext.makeRDD(1 to 30,10)
//      Thread.sleep(2000)
//
//    }
//
    while(true){
      rddQueue += ssc.sparkContext.makeRDD(1 to 30,10)
      Thread.sleep(2000)
    }



    ssc.awaitTermination()








  }

}
