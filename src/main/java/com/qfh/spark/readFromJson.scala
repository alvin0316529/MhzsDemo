package com.qfh.spark

import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}

/**
  * @Description
  * @Author alvin
  * @Date 2019-12-23 23:03:25
  */
object readFromJson {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf().setMaster("local[2]").setAppName("readFromJson")

    val sc = new SparkContext(conf)

    val spark = new SQLContext(sc)

    import spark.implicits._

    val df = spark.read.json("file:///F:\\data\\spark\\spark2.txt")

    df.show()

    df.filter(df("age") > 20).show()

    //df.filter(df("firstName") > 'C').show()

    //val ss = df.count()

    //println(ss)

    sc.stop()

  }

}
