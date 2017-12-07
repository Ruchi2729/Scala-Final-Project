

import org.apache.spark.ml.PipelineModel
import org.apache.spark.sql.{DataFrame, Row}

import scala.io.{Codec, Source}
import org.scalatest.{FlatSpec, Matchers}



class DecisionTreeSpec extends FlatSpec with Matchers {

  val dataPath="C:\\Users\\sweta\\Desktop\\export.csv"

  val decisionTreeModelPath="G:\\7200\\Ruchira\\modelDecisionTree2"

  behavior of "Decision Tree Classifier Model"

//  it should "Parse User Information correctly from file" in {
//
//  val user= DecisionTreeClassifierModel.parseUserInformation("2013-05-20 11:59:04,11,3,205,135,34614,959.4875,427859,0,0,9,2013-06-09,2013-06-10,2,3,1,12826,5,1,1,2,50,1230,77")
//
//    user should matchPattern { case User3(2, 3, 1,2,50,1230,77) => }
//
//  }
//
//  it should "Parse all data to dataset of  million records" in {
//
//    val dataset1=DecisionTreeClassifierModel.filterAndParseToDataset(dataPath)
//    val numOfRecords=dataset1.toDF().count().toInt
//    numOfRecords shouldBe 1000000
//
//  }
//
//
//
//
//  it should "be able to train and test the Decision Tree model" in {
//
//    val model=DecisionTreeClassifierModel.trainDataFromFileWithPCA(dataPath)
//
//    val accuracy=DecisionTreeClassifierModel.testTheModelWithPCA(model,dataPath)
//
//
//  //  accuracy.toFloat should be > (85)
//    assert(accuracy>85)
//  }
//
//
//  it should "successfully load  the trained model " in {
//    val model=DecisionTreeClassifierModel.trainDataFromFile(dataPath)
//
//    val accuracy=DecisionTreeClassifierModel.testTheModel(model,dataPath)
//
//    assert(DecisionTreeClassifierModel.saveTheModel(model,decisionTreeModelPath))
////model.write.save(decisionTreeModelPath)
//  }

  it should "it should recommend hotel clusters ie from 100 expedia hotel clusters for specific user input " in {
    val model=DecisionTreeClassifierModel.loadThemodel(decisionTreeModelPath)
    val adultCount=2
    val childrenCount=1
    val roomCount=1
    val hotelContinent=1
    val hotelCountry=50
    val hotelMarket=696



    //val User3=adultCount+","+childrenCount+","+hotelContinent+","+hotelCountry+",";

    val df = DecisionTreeClassifierModel.getRecommendationsFor(model,User3(adultCount,childrenCount,roomCount,hotelContinent,hotelCountry,hotelMarket,0),dataPath)

assert(df.toInt<100)


  }




}
