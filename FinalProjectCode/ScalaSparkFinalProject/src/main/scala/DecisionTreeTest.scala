import org.apache.spark.rdd.RDD
import org.apache.spark.sql
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}

//  val spark = SparkSession
//    .builder()
//    .master("local[2]")
//    .appName("DecisionTreeApp")
//    .config("spark.sql.warehouse.dir", "c:\\")
//    .getOrCreate()

case class UserInformation(adultCount: Int,childrenCount:Int,roomCount:Int,hotelContinent:Int,hotelCountry:Int,hotelMarket:Int,hotelCluster:Int)


//def createdataFrmeForSparkSessionForFile(spark:SparkSession,appPath:String) = {
//
//
//  import spark.implicits._
//
//  val trainingDf = spark.read.textFile("C:\\Users\\sweta\\Desktop\\export.csv")
//  val trainingheader = trainingDf.first()
//  val trainingRDD1=trainingDf.filter(row => row != trainingheader).filter(row=>row(18)!=0).map(parseUserInformation)
//  //trainingRDD1.cache
//  trainingRDD1.cache
//}



object DecisionTreeTest {

  def main(args: Array[String])= {

      val spark = SparkSession
        .builder()
        .master("local[2]")
        .appName("MyApp")
        .config("spark.sql.warehouse.dir", "c:\\")
        .getOrCreate()

  //  val trainingdata = createdataFrmeForSparkSessionForFile(spark,"SparkSessionZipsExample")
   // trainingdata.cache

    // For implicit conversions like converting RDDs to DataFrames
    import spark.implicits._
    def parseUserInformation(str: String): UserInformation = {
      val fields = str.split(",")
      assert(fields.size == 24)
      //  val d1=DateTime.parse(fields(11))
      //    val d2=DateTime.parse(fields(12))
      //   val hours = Hours.hoursBetween(d1, d2)
      UserInformation(fields(13).toInt, fields(14).toInt,fields(15).toInt,fields(20).toInt,fields(21).toInt,fields(22).toInt,fields(23).toInt)
    }

    val trainingRDD = spark.read.textFile("C:\\Users\\sweta\\Desktop\\export.csv")
    val trainingheader = trainingRDD.first()
    val trainingRDD1=trainingRDD.filter(row => row != trainingheader).filter(row=>row(18)!=0).map(parseUserInformation)
    trainingRDD1.cache

    //print(trainingRDD1)

    // case class TestInformation(adultCount: Int,childrenCount:Int,roomCount:Int,hotelContinent:Int,hotelCountry:Int,hotelMarket:Int)


    // def parseTestInformation(str: String): TestInformation = {
    //   val fields = str.split(",")
    //   assert(fields.size == 22)
    // //  val d1=DateTime.parse(fields(11))
    // //    val d2=DateTime.parse(fields(12))
    // //   val hours = Hours.hoursBetween(d1, d2)
    //   TestInformation(fields(14).toInt, fields(15).toInt,fields(16).toInt,fields(19).toInt,fields(20).toInt,fields(21).toInt)
    // }



    // val testdata = spark.read.textFile("/FileStore/tables/test.csv")
    // val testheader = testdata.first()
    // val testRDD=ratings.filter(row => row != testheader).map(parseTestInformation).toDF()
    // testRDD.cache

    import org.apache.spark.ml.feature.{StringIndexer, VectorAssembler}


    //tranformor to convert string to category values
    val adultCountIndexer = new StringIndexer().setInputCol("adultCount").setOutputCol("adultCountCat").setHandleInvalid("skip")
    val childrenCountIndexer = new StringIndexer().setInputCol("childrenCount").setOutputCol("childrenCountCat").setHandleInvalid("skip")
    val roomCountIndexer = new StringIndexer().setInputCol("roomCount").setOutputCol("roomCountCat").setHandleInvalid("skip")
    val continentIndexer = new StringIndexer().setInputCol("hotelContinent").setOutputCol("hotelContinentCat").setHandleInvalid("skip")
    val countryIndexer = new StringIndexer().setInputCol("hotelCountry").setOutputCol("hotelCountryCat").setHandleInvalid("skip")
    val marketIndexer = new StringIndexer().setInputCol("hotelMarket").setOutputCol("hotelMarketCat").setHandleInvalid("skip")


    //assemble raw feature
    val assembler = new VectorAssembler()
      .setInputCols(Array("adultCountCat", "childrenCountCat", "roomCountCat", "hotelContinentCat", "hotelCountryCat", "hotelMarketCat"))
      .setOutputCol("rawFeatures")

    import org.apache.spark.ml.Pipeline
    import org.apache.spark.ml.classification.DecisionTreeClassifier
    import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
    import org.apache.spark.ml.feature.{Bucketizer, PCA, VectorIndexer}

    //index category index in raw feature
    val indexer = new VectorIndexer().setInputCol("rawFeatures").setOutputCol("rawFeaturesIndexed").setMaxCategories(100)
    //PCA
    val pca = new PCA().setInputCol("rawFeaturesIndexed").setOutputCol("features").setK(6)
    //label for multi class classifier
    val bucketizer = new Bucketizer().setInputCol("hotelCluster").setOutputCol("multiClassLabel").setSplits(Array(Double.NegativeInfinity, 0.0, 15.0, Double.PositiveInfinity))
    // Train a DecisionTree model.
    val dt = new DecisionTreeClassifier().setLabelCol("multiClassLabel").setFeaturesCol("features")
    // Chain all into a Pipeline
    val dtPipeline = new Pipeline().setStages(Array(adultCountIndexer, childrenCountIndexer, roomCountIndexer, continentIndexer, countryIndexer, marketIndexer,assembler, indexer, pca, bucketizer, dt))
    // Train model.


    // Split the data into training and test sets (30% held out for testing).
    val Array(trainData, testData) = trainingRDD1.randomSplit(Array(0.8, 0.2))

    val dtModel = dtPipeline.fit(trainData)
    

    // Make predictions.
    val dtPredictions = dtModel.transform(testData)
    // Select example rows to display.
    dtPredictions.select("prediction", "multiClassLabel", "features").show(100)

    val evaluator = new MulticlassClassificationEvaluator()
      .setLabelCol("multiClassLabel")
      .setPredictionCol("prediction")
      .setMetricName("accuracy")
    val accuracy = evaluator.evaluate(dtPredictions)
    println("Test set accuracy = " + accuracy)



  }

}
