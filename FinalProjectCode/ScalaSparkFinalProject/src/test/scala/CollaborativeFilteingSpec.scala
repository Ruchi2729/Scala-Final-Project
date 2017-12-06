

import org.apache.spark.ml.{Pipeline, PipelineModel}
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.recommendation.ALS.Rating

import scala.io.{Codec, Source}
import org.scalatest.{FlatSpec, Matchers}



class CollaborativeFilteingSpec extends FlatSpec with Matchers {

  behavior of "Collaborative Model"

  it should "Parse User  correctly from file" in {

    val user= CollaborativeFilter.parseUser("2013-05-20 11:59:04,11,3,205,135,34614,959.4875,427859,0,0,9,2013-06-09,2013-06-10,2,3,1,12826,5,1,1,2,50,1230,77")

    user should matchPattern { case Rating(427859, 77, 1) => }

  }

  it should "Parse all data to dataset of  million records" in {

    val dataset1=CollaborativeFilter.filterAndParseToDataset("C:\\Users\\sweta\\Desktop\\export.csv")
    val numOfRecords=dataset1.count().toInt
    numOfRecords shouldBe 1000000

  }




  it should "be able to train and test the Decision Tree model" in {

    val model=CollaborativeFilter.tainCollaborativeFilter("C:\\Users\\sweta\\Desktop\\export.csv")

    val accuracy=CollaborativeFilter .testTheModel(model,"C:\\Users\\sweta\\Desktop\\export.csv")

    //  accuracy.toFloat should be > (85)
    assert(accuracy>85)
  }



}

