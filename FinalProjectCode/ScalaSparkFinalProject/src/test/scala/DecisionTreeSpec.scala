

import org.apache.spark.ml.PipelineModel

import scala.io.{Codec, Source}
import org.scalatest.{FlatSpec, Matchers}



class DecisionTreeSpec extends FlatSpec with Matchers {

  behavior of "Decision Tree Classifier Model"

  it should "Parse User Information correctly from file" in {

  val user= DecisionTreeClassifier.parseUserInformation("2013-05-20 11:59:04,11,3,205,135,34614,959.4875,427859,0,0,9,2013-06-09,2013-06-10,2,3,1,12826,5,1,1,2,50,1230,77")

    user should matchPattern { case User3(2, 3, 1,2,50,1230,77) => }

  }

  it should "Parse all data to dataset of  million records" in {

    val dataset1=DecisionTreeClassifier.filterAndParseToDataset("C:\\Users\\sweta\\Desktop\\export.csv")
    val numOfRecords=dataset1.toDF().count().toInt
    numOfRecords shouldBe 1000000

  }




  it should "be able to train and test the Decision Tree model" in {

    val model=DecisionTreeClassifier.trainDataFromFile("C:\\Users\\sweta\\Desktop\\export.csv")

    val accuracy=DecisionTreeClassifier.testTheModel(model,"C:\\Users\\sweta\\Desktop\\export.csv")

  //  accuracy.toFloat should be > (85)
    assert(accuracy>85)
  }


  it should "successfully load  the trained model " in {
    val model=PipelineModel.load("G:\\7200\\Ruchira\\model1")

    val accuracy=DecisionTreeClassifier.testTheModel(model,"C:\\Users\\sweta\\Desktop\\export.csv")

    //  accuracy.toFloat should be > (85)
    assert(accuracy>85)
  }

  it should "it should recommend hotel clusters for specific user input " in {
    val model=PipelineModel.load("G:\\7200\\Ruchira\\model1")

  }




}

