package models

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object MapReduce {


  // 1. Create Spark configuration
  val conf = new SparkConf()
    .setAppName("SparkMe Application")
    .setMaster("local[*]")  // local mode

  // 2. Create Spark context
  //val sc = new SparkContext(conf)

val spark = SparkSession.builder().appName("My Application").master("local").getOrCreate()


  def getNumberOfBookingsForEachuser()={
    /// Total number of booking for each user

    val rdd = spark.read.textFile("C:\\Users\\sweta\\Desktop\\export.csv").rdd;

    val trainingheader = rdd.first()

    val rdd2=rdd.filter(row => row != trainingheader).filter(row=>row(18)!=0).map(line =>line.split(",")).map(line=>(line(7),1))
    //
    //    val rdd2=rdd.map(line =>line.split(",")).map(line=>(line(7),1))
    //    //val rdd3=rdd2.filter({case (k,v)=>k._1==("427859") })
    val rdd4=rdd2.reduceByKey(_ + _)
    rdd4.collect.foreach(println)
  }

/*
  def getNumberOfBookingsForEachCountry()={
    /// Total number of bookings in each country

    val rdd = sc.textFile("C:\\Users\\sweta\\Desktop\\export.csv");

    val trainingheader = rdd.first()

    val rdd2=rdd.filter(row => row != trainingheader).filter(row=>row(18)!=0).map(line =>line.split(",")).map(line=>(line(19),1))
    //
    //    val rdd2=rdd.map(line =>line.split(",")).map(line=>(line(7),1))
    //    //val rdd3=rdd2.filter({case (k,v)=>k._1==("427859") })
    val rdd4=rdd2.reduceByKey(_ + _)
    rdd4.collect.foreach(println)
  }

*/
  def main(args: Array[String]): Unit = {

   // getNumberOfBookingsForEachCountry()

    val rdd = spark.read.textFile("C:\\Users\\sweta\\Desktop\\export.csv").rdd

    val trainingheader = rdd.first()

    val rdd2=rdd.filter(row => row != trainingheader).
      filter(row=>row(18)!=0).
      map(line =>line.split(",")).
      map(lst=>{val date=lst(0).split("-")
      val year=date(0)+"/"+date(1)
        (year,1)})
    val rdd4=rdd2.reduceByKey(_ + _)
    rdd4.collect.foreach(println)



  }

}
