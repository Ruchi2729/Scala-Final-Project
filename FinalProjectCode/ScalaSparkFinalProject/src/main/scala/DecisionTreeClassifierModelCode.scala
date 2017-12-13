import org.apache.spark.ml.PipelineModel
import org.apache.spark.ml.classification.DecisionTreeClassificationModel
import org.apache.spark.ml.evaluation.MulticlassClassificationEvaluator
import org.apache.spark.ml.feature.IndexToString
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{DataFrame, Dataset, SparkSession}
case class User3(adultCount: Int,childrenCount:Int,roomCount:Int,hotelContinent:Int,hotelCountry:Int,hotelMarket:Int,hotelCluster:Int)

object DecisionTreeClassifierModel {
  def saveTheModel(model: PipelineModel,mlModelPath:String): Boolean = try {
    model.save(mlModelPath)
    true
  }


  val spark= SparkSession
      .builder()
      .master("local[2]")
      .appName("MyApp")
      .config("spark.sql.warehouse.dir", "c:\\")
      .getOrCreate()

    def getSparkSession=spark

  def parseUserInformation(str: String): User3= {
    val fields = str.split(",")
    print(fields(0))
    assert(fields.size == 24)
    User3(fields(13).toInt, fields(14).toInt,fields(15).toInt,fields(20).toInt,fields(21).toInt,fields(22).toInt,fields(23).toInt)
  }





  import spark.implicits._
  def readFileToDataFrame(filePath:String):Dataset[String]=spark.read.textFile(filePath)

  def filterAndParseToDataset(filePath:String):Dataset[User3]={
    val trainingRDD =readFileToDataFrame(filePath);
    val trainingheader = trainingRDD.first()

    trainingRDD.filter(row => row != trainingheader).filter(row=>row(18)!=0).map(parseUserInformation)

  }

  def trainDataFromFile(filePath:String):PipelineModel={
//    val trainingRDD =readFileToDataFrame(filePath)
//    val trainingheader = trainingRDD.first()
    val trainingRDD1=filterAndParseToDataset(filePath)
    trainingRDD1.cache
    //trainingRDD1.selectExpr()


    // Split the data into training and test sets (30% held out for testing).
    val Array(trainData, testData) = trainingRDD1.randomSplit(Array(0.8, 0.2))

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
   // val bucketizer = new Bucketizer().setInputCol("hotelCluster").setOutputCol("multiClassLabel").setSplits(Array(Double.NegativeInfinity, 0.0, 15.0, Double.PositiveInfinity))
   val labelIndexer = new StringIndexer()
     .setInputCol("hotelCluster")
     .setOutputCol("indexedLabel")
     .fit(trainData)
    // Convert indexed labels back to original labels.
    val labelConverter = new IndexToString()
      .setInputCol("prediction")
      .setOutputCol("predictedLabel")
      .setLabels(labelIndexer.labels)
    // Train a DecisionTree model.
    val dt = new DecisionTreeClassifier().setLabelCol("indexedLabel").setFeaturesCol("features")
    // Chain all into a Pipeline
    val dtPipeline = new Pipeline().setStages(Array(labelIndexer,adultCountIndexer, childrenCountIndexer, roomCountIndexer, continentIndexer, countryIndexer, marketIndexer,assembler, indexer, pca, dt,labelConverter))
    // Train model.
    dtPipeline.fit(trainData)
  }

  def testTheModel(model:PipelineModel,filePath:String):Float={

    val data=filterAndParseToDataset(filePath)
    val Array(trainData, testData) = data.randomSplit(Array(0.8, 0.2))

    val predictions = model.transform(testData)

    // Select example rows to display.
    predictions.select("predictedLabel", "hotelCluster", "features").show(5)

    // Select (prediction, true label) and compute test error.
    val evaluator = new MulticlassClassificationEvaluator()
      .setLabelCol("indexedLabel")
      .setPredictionCol("prediction")
      .setMetricName("accuracy")
    val accuracy = evaluator.evaluate(predictions)
    println("Test Error = " + (1.0 - accuracy))

//    val treeModel = model.stages(2).asInstanceOf[DecisionTreeClassificationModel]
    //println("Learned classification tree model:\n" + treeModel.toDebugString)
    accuracy.toFloat*100
  }

  def trainDataFromFileWithPCA(filePath:String):PipelineModel={
    //    val trainingRDD =readFileToDataFrame(filePath)
    //    val trainingheader = trainingRDD.first()
    val trainingRDD1=filterAndParseToDataset(filePath)
    trainingRDD1.cache
    //trainingRDD1.selectExpr()

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
    dtPipeline.fit(trainData)
  }

  def testTheModelWithPCA(model:PipelineModel,filePath:String):Float={

    val data=filterAndParseToDataset(filePath)
    val Array(trainData, testData) = data.randomSplit(Array(0.8, 0.2))
    // Make predictions.
    val dtPredictions = model.transform(testData)
    // Select example rows to display.
    dtPredictions.select("prediction", "multiClassLabel", "features").show(100)

    val evaluator = new MulticlassClassificationEvaluator()
      .setLabelCol("multiClassLabel")
      .setPredictionCol("prediction")
      .setMetricName("accuracy")
    val accuracy = evaluator.evaluate(dtPredictions)
    println("Test set accuracy = " + accuracy)
    accuracy.toFloat*100
  }



  def getRecommendationsFor(model:PipelineModel,user:User3,modelPath:String):String={
    val df = List(user)

    val spark=DecisionTreeClassifierModel.getSparkSession
    import spark.implicits._

    val rdd=spark.createDataset(df).
      toDF("adultCount","childrenCount","roomCount","hotelContinent","hotelCountry","hotelMarket","hotelCluster")

    //val rdd2=rdd.as[User3]

    val dtPredictions = model.transform(rdd)
    // Select example rows to display.
    // Select example rows to display.
    dtPredictions.select("predictedLabel", "hotelCluster", "features").show(5)

    // val x=userRecs.select($"recommendations").where($"userId"===57020).rdd.first().get(0)
    val y=dtPredictions.select($"predictedLabel").rdd.first().get(0).toString()

    print(y)
    y
  }
  def loadThemodel(path:String):PipelineModel={
    PipelineModel.read.load(path)
  }

  def main(args: Array[String]): Unit = {
    val model=trainDataFromFile("C:\\Users\\sweta\\Desktop\\export.csv")
//    testTheModel(model,"C:\\Users\\sweta\\Desktop\\export.csv")
//
//    model.save("G:\\7200\\Ruchira\\modelDecisionTree")

    //val model=loadThemodel("G:\\7200\\Ruchira\\modelDecisionTree")
    testTheModel(model,"C:\\Users\\sweta\\Desktop\\export.csv")

  }

}
