
import org.apache.spark.sql.SQLContext
import org.apache.spark.{SparkConf, SparkContext}


object MapReduce {


  // 1. Create Spark configuration
  val conf = new SparkConf()
    .setAppName("SparkMe Application")
    .setMaster("local[*]")  // local mode

  // 2. Create Spark context
  val sc = new SparkContext(conf)


  def getNumberOfBookingsForEachuser()={
    /// Total number of booking for each user

    val rdd = sc.textFile("C:\\Users\\sweta\\Desktop\\export.csv");

    val trainingheader = rdd.first()

    val rdd2=rdd.filter(row => row != trainingheader).filter(row=>row(18)!=0).map(line =>line.split(",")).map(line=>(line(7),1))
    //
    //    val rdd2=rdd.map(line =>line.split(",")).map(line=>(line(7),1))
    //    //val rdd3=rdd2.filter({case (k,v)=>k._1==("427859") })
    val rdd4=rdd2.reduceByKey(_ + _)
    rdd4.collect.foreach(println)
  }


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

  def getNumberOfBookingsEveryMonth() =
{
  val rdd = sc.textFile("C:\\Users\\sweta\\Desktop\\export.csv")

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

  def getNumberOfBookingsTotally(sc: SparkContext, filePath: String) = {
    val rdd = sc.textFile(filePath);
    val trainingheader = rdd.first()
    val rdd2 = rdd.filter(row => row != trainingheader).filter(row => row(18) != 0).map(line => line.split(",")).map(line => (line(7), 1))
    val rdd4 = rdd2.reduceByKey(_ + _)
    rdd4.collect.foreach(println)
  }


  def main(args: Array[String]): Unit = {

    val rdd = sc.textFile("C:\\Users\\sweta\\Desktop\\export.csv");

    val trainingheader = rdd.first()

    val rdd2=rdd.filter(row => row != trainingheader).filter(row=>row(18)!=0).map(line =>line.split(",")).map(line=>(line(7),1))
    //
    //    val rdd2=rdd.map(line =>line.split(",")).map(line=>(line(7),1))
    //    //val rdd3=rdd2.filter({case (k,v)=>k._1==("427859") })
    val rdd4=rdd2.reduceByKey(_ + _)

    val sqlContext = new SQLContext(sc)
    import sqlContext.implicits._

    val result=rdd4.toDF("UserId","Booking Count")
    val complteData=result.toJSON.collect.mkString("[", "," , "]" )

    print(complteData)

  }

}
