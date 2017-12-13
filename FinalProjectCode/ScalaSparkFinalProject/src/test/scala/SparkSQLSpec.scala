

import org.apache.spark.ml.{Pipeline, PipelineModel}
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.recommendation.ALS.Rating
import org.apache.spark.ml.recommendation.ALSModel

import scala.io.{Codec, Source}
import org.scalatest.{FlatSpec, Matchers}



class SparkSQLSpec extends FlatSpec with Matchers {

  val dataPath="C:\\Users\\sweta\\Desktop\\export.csv"

  val collaborativeModelPath="G:\\7200\\Ruchira\\collaborativeFilter2"

  behavior of "Collaborative Model"

    it should "Parse User  correctly from file" in {

      val user= UserHiistoryHotels.parseUserData("2013-05-20 11:59:04,11,3,205,135,34614,959.4875,427859,0,0,9,2013-06-09,2013-06-10,2,3,1,12826,5,1,1,2,50,1230,77")

      user should matchPattern { case DetailUser(_,_,_) => }

    }

    it should "It should get the historical data for user id : 427859" in {

      val dataset1=UserHiistoryHotels.getHistoricalBookingsOfUser(427859.toString,dataPath)
      val numOfRecords=dataset1.count().toInt
      numOfRecords should be > 1

    }





}

