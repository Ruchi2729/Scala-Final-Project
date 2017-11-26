package utils

import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.{IndexToString, StringIndexer, VectorIndexer}
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.ml.attribute.NominalAttribute
import org.apache.spark.sql.Row
import org.apache.spark.sql.types.{StructType,StructField,StringType}
import org.apache.spark.mllib.evaluation.MulticlassMetrics


case class UserInformation(adultCount: Int,childrenCount:Int,roomCount:Int,hotelContinent:Int,hotelCountry:Int,hotelMarket:Int,hotelCluster:Int)
object ML{
  def simpleApp {
    def main
        val logFile = "public/data/README.md" // Should be some file on your system
        val conf = new SparkConf(false) // skip loading external settings
          .setMaster("local[4]") // run locally with enough threads
          .setAppName("firstSparkApp")
          .set("spark.logConf", "true")
          .set("spark.driver.host", "localhost")
        val sc = new SparkContext(conf)
        val logData = sc.textFile(logFile, 4).cache()
        val numSparks = logData.filter(line => line.contains("Spark")).count()
}

def parseUserInformation(str: String): UserInformation = {
val fields = str.split(",")
assert(fields.size == 24)
//  val d1=DateTime.parse(fields(11))
//    val d2=DateTime.parse(fields(12))
//   val hours = Hours.hoursBetween(d1, d2)
UserInformation(fields(13).toInt, fields(14).toInt,fields(15).toInt,fields(20).toInt,fields(21).toInt,fields(22).toInt,fields(23).toInt)
}

val trainingRDD = spark.read.textFile("C:\\Users\\Chau_\\Desktop\\export.csv")
val trainingheader = trainingRDD.first()
val trainingRDD1=trainingRDD.filter(row => row != trainingheader).filter(row=>row(18)!=0).map(parseUserInformation)
trainingRDD1.cache

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

import org.apache.spark.ml.feature.StringIndexer
import org.apache.spark.ml.feature.VectorAssembler


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

import org.apache.spark.ml.feature.Bucketizer
import org.apache.spark.ml.Pipeline
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.mllib.regression.LabeledPoint

import org.apache.spark.ml.feature.VectorIndexer
import org.apache.spark.ml.feature.PCA
import org.apache.spark.sql.functions.avg
import spark.implicits._
import org.apache.spark.mllib.evaluation.BinaryClassificationMetrics

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
val Array(trainingData, testData) = trainingRDD1.randomSplit(Array(0.8, 0.2))

val dtModel = dtPipeline.fit(trainingData)

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


//object ML {
//
//  def simpleApp {
//    val logFile = "public/data/README.md" // Should be some file on your system
//    val conf = new SparkConf(false) // skip loading external settings
//      .setMaster("local[4]") // run locally with enough threads
//      .setAppName("firstSparkApp")
//      .set("spark.logConf", "true")
//      .set("spark.driver.host", "localhost")
//    val sc = new SparkContext(conf)
//    val logData = sc.textFile(logFile, 4).cache()
//    val numSparks = logData.filter(line => line.contains("Spark")).count()
//  }

}