
import com.google.protobuf.ByteString.Output
import org.apache.spark.sql.{SQLContext, SparkSession}
import org.apache.spark.sql.execution.streaming.FileStreamSource.Timestamp
import org.apache.spark.sql.types._
import org.apache.spark.sql.types._
case class Userq(date_time:String,site_name:Int,posa_continent:Int,user_location_country:Int,user_location_region:Int,user_location_city:Int,orig_destination_distance:String,user_id:Int,is_mobile:Int,is_package:Int,channel:Int,srch_ci:String,srch_co:String,srch_adults_cnt:Int,srch_children_cnt:Int,srch_rm_cnt:Int,srch_destination_id:String,srch_destination_type_id:Int,hotel_continent:Int,hotel_country:Int,hotel_market:Int,is_booking:Int,cnt:Int,hotel_cluster:Int)

object Streaming {

  // not necessary since Spark 1.3

  // Create a local StreamingContext with two working thread and batch interval of 1 second.
  // The master requires 2 cores to prevent from a starvation scenario.



  def main(args: Array[String]): Unit = {

    val spark = SparkSession
      .builder
      .appName("Spark-Kafka-Integration")
      .master("local")
      .getOrCreate()
//
//
//
//    val mySchema = StructType(Array(
//      StructField("date_time", StringType),
//      StructField("site_name", IntegerType),
//      StructField("posa_continent", IntegerType),
//      StructField("user_location_country", IntegerType),
//      StructField("user_location_region", IntegerType),
//      StructField("user_location_city", IntegerType),
//      StructField("orig_destination_distance", StringType),
//      StructField("user_id", IntegerType),
//      StructField("is_mobile", IntegerType),
//      StructField("is_package", IntegerType),
//      StructField("channel", IntegerType),
//      StructField("srch_ci", StringType),
//      StructField("srch_co", StringType),
//      StructField("srch_adults_cnt", IntegerType),
//      StructField("srch_children_cnt", IntegerType),
//      StructField("srch_rm_cnt", IntegerType),
//      StructField("srch_destination_id ", StringType),
//      StructField("srch_destination_type_id", IntegerType),
//      StructField("hotel_continent", IntegerType),
//      StructField("hotel_country", IntegerType),
//      StructField("hotel_market", IntegerType),
//      StructField("is_booking", IntegerType),
//      StructField("cnt", IntegerType),
//      StructField("hotel_cluster", IntegerType)
//     ))


    import org.apache.spark.sql.Encoders
    val schema = Encoders.product[Userq].schema
    import spark.implicits._
    val users = spark.
      readStream.
      schema(schema).
      csv("C:\\Users\\sweta\\Desktop\\export.csv").
      as[Userq]

   val totalCount= users.groupBy("is_booking").count()

    // Start the streaming query
    // Write the result using console format, i.e. print to the console
    // Only Complete output mode supported by groupBy
//    import scala.concurrent.duration._
//    import org.apache.spark.sql.streaming.{OutputMode, Trigger}
//    val populationStream = totalCount.
//      writeStream.
//      format("console").
//      trigger(Trigger.ProcessingTime(30.seconds)).
//      outputMode(OutputMode.Complete).
//      queryName("textStream").
//      start
//
//
//    spark.streams.active.foreach(println)


    import scala.concurrent.duration._
    import org.apache.spark.sql.streaming.{OutputMode, Trigger}
    val out = users.
      writeStream.
      format("console").
      option("truncate", false).
      trigger(Trigger.ProcessingTime("5 seconds")).
      queryName("consoleStream").outputMode(OutputMode.Append).
      start


    spark.streams
      .active
      .foreach(println)

//    users.createOrReplaceTempView("allusers")
//    val sqlDF = spark.sql("SELECT * FROM allusers where is_booking=1 ")
//    sqlDF.show()

    //val bookings = users.where($'is_booking'==1)

    //    val streamingDataFrame = spark.readStream.schema(mySchema).csv("C:\\Users\\sweta\\Desktop\\export.csv")
//
////    streamingDataFrame.selectExpr("CAST(id AS STRING) AS key", "to_json(struct(*)) AS value").
////      writeStream
////      .format("kafka")
////      .option("topic", "topicName")
////      .option("kafka.bootstrap.servers", "localhost:9092")
////      .option("checkpointLocation", "C:\\Users\\sweta\\Desktop\\ruchira")
////      .start()
//
//    import spark.implicits._
//
////    val df = spark
////      .readStream
////      .format("kafka")
////      .option("kafka.bootstrap.servers", "localhost:9092")
////      .option("subscribe", "topicName")
////      .load()
//
//  //  val df1 = df.selectExpr("CAST(value AS STRING)", "CAST(timestamp AS TIMESTAMP)").as[(String, Timestamp)]
////      .select(($"value", mySchema).as("data"), $"timestamp")
////      .select("data.*", "timestamp")
//
////    df1.writeStream
////      .format("console")
////      .option("truncate","false")
////      .start()
////      .awaitTermination()

//    val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
//    val ssc = new StreamingContext(conf, Seconds(1))
//
//
//
//    val lines = ssc.textFileStream("C:\\Users\\sweta\\Desktop\\export.csv")
//
//
//    lines.foreachRDD(rdd => {
//      val trainingheader = rdd.first()
//
//      val rdd2=rdd.filter(row => row != trainingheader).filter(row=>row(18)!=0).map(line =>line.split(",")).map(line=>(line(7),1))
//      //
//      //    val rdd2=rdd.map(line =>line.split(",")).map(line=>(line(7),1))
//      //    //val rdd3=rdd2.filter({case (k,v)=>k._1==("427859") })
//      val rdd4=rdd2.reduceByKey(_ + _)
//      rdd4.collect.foreach(print)
//
//    }
//  )
//
//
//    ssc.start()
//    ssc.awaitTermination()

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
