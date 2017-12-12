import MapReduce.sc
import org.apache.spark.SparkConf
import org.apache.spark.sql.SQLContext
import org.apache.spark.streaming.{Seconds, StreamingContext}

object Streaming {

  // not necessary since Spark 1.3

  // Create a local StreamingContext with two working thread and batch interval of 1 second.
  // The master requires 2 cores to prevent from a starvation scenario.



  def main(args: Array[String]): Unit = {

    val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
    val ssc = new StreamingContext(conf, Seconds(1))



    val lines = ssc.textFileStream("C:\\Users\\sweta\\Desktop\\export.csv")


    lines.foreachRDD(rdd => {
      val trainingheader = rdd.first()

      val rdd2=rdd.filter(row => row != trainingheader).filter(row=>row(18)!=0).map(line =>line.split(",")).map(line=>(line(7),1))
      //
      //    val rdd2=rdd.map(line =>line.split(",")).map(line=>(line(7),1))
      //    //val rdd3=rdd2.filter({case (k,v)=>k._1==("427859") })
      val rdd4=rdd2.reduceByKey(_ + _)
      rdd4.collect.foreach(print)

    }
  )


    ssc.start()
    ssc.awaitTermination()

//
//    val trainingheader = rdd.first()
//
//    val rdd2=rdd.filter(row => row != trainingheader).filter(row=>row(18)!=0).map(line =>line.split(",")).map(line=>(line(7),1))
//    //
//    //    val rdd2=rdd.map(line =>line.split(",")).map(line=>(line(7),1))
//    //    //val rdd3=rdd2.filter({case (k,v)=>k._1==("427859") })
//    val rdd4=rdd2.reduceByKey(_ + _)
//
//    val sqlContext = new SQLContext(sc)
//    import sqlContext.implicits._
//
//    val result=rdd4.toDF("UserId","Booking Count")
//    val complteData=result.toJSON.collect.mkString("[", "," , "]" )
//
//    print(complteData)

  }



}
