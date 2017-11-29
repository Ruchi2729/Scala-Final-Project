import org.apache.spark.sql.SparkSession

case class User(userId: Int, hotelClusterId: Int,srch_destination_id:Int,srch_adults_cnt:Int, isBooking: Float)

object CollaborativeFilteringTest {

  def main(args: Array[String]): Unit = {

    import org.apache.spark.ml.evaluation.RegressionEvaluator
    import org.apache.spark.ml.recommendation.ALS


    val spark = SparkSession
      .builder()
      .master("local[2]")
      .appName("SparkSessionZipsExample")
      .config("spark.sql.warehouse.dir", "c:\\")
      .getOrCreate()

    // For implicit conversions like converting RDDs to DataFrames
    import spark.implicits._

    def parseRating(str: String): User = {
      val fields = str.split(",")
      assert(fields.size == 24)
      User(fields(7).toInt, fields(23).toInt, fields(16).toInt,fields(13).toInt, fields(18).toFloat)
    }


    // val ratings = sc.textFile("/FileStore/tables/export.csv")
    //   .map(parseBooking)

    val ratings = spark.read.textFile("C:\\Users\\sweta\\Desktop\\export.csv")
    val header = ratings.first()
    var ratings2=ratings.filter(row => row != header).map(parseRating).toDF()


    val Array(training, test) = ratings2.randomSplit(Array(0.8, 0.2))

    val als = new ALS()
      .setMaxIter(5)
      .setRegParam(0.01)
      .setUserCol("userId")
      .setItemCol("hotelClusterId")
      .setRatingCol("isBooking")
    val model = als.fit(training)

    // Evaluate the model by computing the RMSE on the test data
    // Note we set cold start strategy to 'drop' to ensure we don't get NaN evaluation metrics
    model.setColdStartStrategy("drop")
    val predictions = model.transform(test)

    val evaluator = new RegressionEvaluator()
      .setMetricName("rmse")
      .setLabelCol("isBooking")
      .setPredictionCol("prediction")
    val rmse = evaluator.evaluate(predictions)
    println(s"Root-mean-square error = $rmse")

    // Generate top 10 movie recommendations for each user
    val userRecs = model.recommendForAllUsers(10)
    // Generate top 10 user recommendations for each movie
    val movieRecs = model.recommendForAllItems(10)
    println(userRecs)
  }

}
