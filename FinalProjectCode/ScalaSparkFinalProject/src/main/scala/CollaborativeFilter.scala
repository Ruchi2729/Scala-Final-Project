import DecisionTreeClassifier.{filterAndParseToDataset, parseUserInformation, spark}
import org.apache.spark.ml.PipelineModel
import org.apache.spark.ml.evaluation.{MulticlassClassificationEvaluator, RegressionEvaluator}
import org.apache.spark.ml.recommendation.{ALS, ALSModel}
import org.apache.spark.sql.{Dataset, SparkSession}

import scala.collection.mutable

case class User(userId: Int, hotelClusterId: Int,srch_destination_id:Int,srch_adults_cnt:Int, isBooking: Float)

object CollaborativeFilter {

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
    User(fields(7).toInt, fields(23).toInt, fields(16).toInt,fields(13).toInt, fields(18).toFloat)
  }

  def readFileToDataFrame(filePath:String):Dataset[String]=spark.read.textFile(filePath)

  def filterAndParseToDataset(filePath:String):Dataset[User]={
    val ratings = readFileToDataFrame(filePath)
    val header = ratings.first()
    ratings.filter(row => row != header).map(parseUser)
  }

  def tainCollaborativeFilter(filePath:String):ALSModel = {
    val Array(training, test) = filterAndParseToDataset(filePath).randomSplit(Array(0.8, 0.2))

    val als = new ALS()
      .setMaxIter(5)
      .setRegParam(0.01)
      .setUserCol("userId")
      .setItemCol("hotelClusterId")
      .setRatingCol("isBooking")
    als.fit(training)
  }

  def testTheModel(model:ALSModel,filePath:String):Double={

    val data=filterAndParseToDataset(filePath)
    val Array(trainData, testData) = data.randomSplit(Array(0.8, 0.2))
    // Evaluate the model by computing the RMSE on the test data
    // Note we set cold start strategy to 'drop' to ensure we don't get NaN evaluation metrics
    model.setColdStartStrategy("drop")
    val predictions = model.transform(testData)

    val evaluator = new RegressionEvaluator()
      .setMetricName("rmse")
      .setLabelCol("isBooking")
      .setPredictionCol("prediction")
    val rmse = evaluator.evaluate(predictions)
   rmse*100
  }

  def getUserRecommendationsFor(userId:Int,model:ALSModel,filePath:String) = {
    val data=filterAndParseToDataset(filePath)
    val Array(trainData, testData) = data.randomSplit(Array(0.8, 0.2))

    model.setColdStartStrategy("drop")
    val predictions = model.transform(testData).toDF()

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

  }



}