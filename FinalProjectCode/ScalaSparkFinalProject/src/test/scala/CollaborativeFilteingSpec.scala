

import org.apache.spark.ml.{Pipeline, PipelineModel}
import org.apache.spark.ml.classification.DecisionTreeClassifier
import org.apache.spark.ml.recommendation.ALS.Rating
import org.apache.spark.ml.recommendation.ALSModel

import scala.io.{Codec, Source}
import org.scalatest.{FlatSpec, Matchers}



class CollaborativeFilteingSpec extends FlatSpec with Matchers {

  val dataPath="C:\\Users\\sweta\\Desktop\\export.csv"

  val collaborativeModelPath="G:\\7200\\Ruchira\\collaborativeFilter2"

  behavior of "Collaborative Model"

  it should "Parse User  correctly from file" in {

    val user= CollaborativeFilter.parseUser("2013-05-20 11:59:04,11,3,205,135,34614,959.4875,427859,0,0,9,2013-06-09,2013-06-10,2,3,1,12826,5,1,1,2,50,1230,77")

    user should matchPattern { case User(427859, 77, 1.0) => }

  }

  it should "Parse all data to dataset of  999999 records" in {

    val dataset1=CollaborativeFilter.filterAndParseToDataset(dataPath)
    val numOfRecords=dataset1.count().toInt
    numOfRecords shouldBe 999999

  }




  it should "be able to train and test and save the Decision Tree model" in {

    val model=CollaborativeFilter.tainCollaborativeFilter(dataPath)

    val accuracy=CollaborativeFilter .testTheModel(model,dataPath)

    model.save(collaborativeModelPath)

    //  accuracy.toFloat should be > (85)
   // assert(accuracy>85)
  }

  it should "be able to load the saved model and test it for root mean square value less than 0.3" in {


    val model=CollaborativeFilter.loadThemodel(collaborativeModelPath)
    val rmse=CollaborativeFilter.testTheModel(model,dataPath)

    rmse should be < 0.3
    // assert(accuracy>85)
  }


  it should "it should get 5 recommendations for all test users" in {


    val model=CollaborativeFilter.loadThemodel(collaborativeModelPath)
   val numberOfRecos=CollaborativeFilter.getRecommendationsForAllUsers(model,dataPath,5)

    numberOfRecos equals(5)
    // assert(accuracy>85)
  }

  it should "it should be able to get the recommendations for user with usrrId " in {


    val model=CollaborativeFilter.loadThemodel(collaborativeModelPath)
    val recomendations=CollaborativeFilter.getUserRecommendationsFor(57020,model)

    recomendations.size equals(5)

    recomendations.foreach(x=>x.toInt should be < 100)

    // assert(accuracy>85)
  }




}

