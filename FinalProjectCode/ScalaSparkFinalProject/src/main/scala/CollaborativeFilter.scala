import java.util

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.ml.PipelineModel
import org.apache.spark.ml.evaluation.{MulticlassClassificationEvaluator, RegressionEvaluator}
import org.apache.spark.ml.recommendation.{ALS, ALSModel}
import org.apache.spark.mllib.recommendation.{MatrixFactorizationModel, Rating}
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{Dataset, SparkSession}

import scala.collection.mutable
import scala.collection.mutable.ListBuffer

case class User(userId: Int, hotelClusterId: Int, isBooking: Float)

object CollaborativeFilter {

  // 1. Create Spark configuration
  val conf = new SparkConf()
    .setAppName("SparkMe Application")
    .setMaster("local[*]")  // local mode

  // 2. Create Spark context
  val sc = new SparkContext(conf)

  val spark= SparkSession
    .builder()
    .master("local[2]")
    .appName("MyApp")
    .config("spark.sql.warehouse.dir", "c:\\")
    .getOrCreate()

  def getSparkSession=spark

  // For implicit conversions like converting RDDs to DataFrames
  import spark.implicits._

  def parseUser(str: String): User = {
    val fields = str.split(",")
    assert(fields.size == 24)
    User(fields(7).toInt, fields(23).toInt, fields(18).toFloat)
  }

  def readFileToDataFrame(filePath:String):Dataset[String]=spark.read.textFile(filePath)

  def filterAndParseToDataset(filePath:String):Dataset[User]={
    val ratings = readFileToDataFrame(filePath)
    val header = ratings.first()
    ratings.filter(row => row != header).map(parseUser)
  }

  def tainCollaborativeFilter(filePath:String):ALSModel = {

    val rating=filterAndParseToDataset(filePath).toDF("userId","hotelClusterId","isBooking")

    val als = new ALS()
      .setMaxIter(5)
      .setRegParam(0.01)
      .setUserCol("userId")
      .setItemCol("hotelClusterId")
      .setRatingCol("isBooking")
    als.fit(rating)

  }




  def testTheModel(model:ALSModel,filePath:String):Double={

    val data=filterAndParseToDataset(filePath).toDF("userId","hotelClusterId","isBooking")
    val Array(trainData, testData) = data.randomSplit(Array(0.8, 0.2))
    // Evaluate the model by computing the RMSE on the test data
    // Note we set cold start strategy to 'drop' to ensure we don't get NaN evaluation metrics
    model.setColdStartStrategy("drop")
    val predictions = model.transform(testData)

    predictions.show(50)
        val userRecs = model.recommendForAllUsers(5)
        val userSpecificrecos=userRecs.select($"recommendations").where($"userId"===57020).show()
    val evaluator = new RegressionEvaluator()
      .setMetricName("rmse")
      .setLabelCol("isBooking")
      .setPredictionCol("prediction")
    val rmse = evaluator.evaluate(predictions)
   rmse
  }


    def getRecommendationsForAllUsers(model:ALSModel,filePath:String,noOfrecos:Int):Int = {
      val data=filterAndParseToDataset(filePath)
      val Array(trainData, testData) = data.randomSplit(Array(0.8, 0.2))

      model.setColdStartStrategy("drop")

      val predictions = model.transform(testData)

      predictions.show(50)
      val userRecs = model.recommendForAllUsers(noOfrecos)

      userRecs.count().toInt

    }

  def getUserRecommendationsFor(userId:Int,model:ALSModel):Array[String] = {
   // val model=ALSModel.load(modelPath)

  //  val predictions = model.transform(testData)
  //  predictions.show(50)

    // Generate top 10 hotel cluster recommendations for each user
    val userRecs = model.recommendForAllUsers(5)

    val userSpecificrecos=userRecs.select($"recommendations").where($"userId"===userId).show()

    // val x=userRecs.select($"recommendations").where($"userId"===57020).rdd.first().get(0)
    val y=userRecs.select($"recommendations").where($"userId"===userId).rdd.first().getList(0).toArray()

    val allrecos=y.map(x=>{
      val r=x.toString.split(",")
      r(0).substring(1)
    })



    y.foreach(print)
    print(" ")
    allrecos.foreach(x=>print(x+" "))

    allrecos

  }
def loadThemodel(path:String):ALSModel={
  ALSModel.read.load(path)
}
  def main(args: Array[String]): Unit = {

    val data=filterAndParseToDataset("C:\\Users\\sweta\\Desktop\\export.csv")
    val Array(trainData, testData) = data.randomSplit(Array(0.8, 0.2))
val model=tainCollaborativeFilter("C:\\Users\\sweta\\Desktop\\export.csv")

    val predictions = model.transform(testData)
    predictions.show(50)

    // Generate top 10 hotel cluster recommendations for each user
    val userRecs = model.recommendForAllUsers(5)

    val userSpecificrecos=userRecs.select($"recommendations").where($"userId"===57020).show()

   // val x=userRecs.select($"recommendations").where($"userId"===57020).rdd.first().get(0)
    val y=userRecs.select($"recommendations").where($"userId"===57020).rdd.first().getList(0).toArray()


    val allrecos=y.map(x=>{
      val r=x.toString.split(",")
      r(0).substring(1)
      })



y.foreach(print)
    print(" ")
  allrecos.foreach(x=>print(x+" "))

  }



}

