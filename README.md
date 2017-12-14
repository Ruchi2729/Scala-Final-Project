We have two sbt projects :-
A) ScalaSparkFinalProject : Contains code for all data processing and Machine Learning.
 Scala 2.11.8, Spark 2.2.0 
Download the csv file from
 https://drive.google.com/a/husky.neu.edu/file/d/1hfeQWPX-u9dkQDA645A0dcScji2tiX66/view?usp=sharing

And keep it in you local directory.

To run all the spec files: In each spec file Give data path where downloaded  csv file is kept
 val dataPath="C:\\Users\\sweta\\Desktop\\export.csv"
CollaborativeFilteingSpec file give the local directory path where ML model for collaborative filtering will be savedval collaborativeModelPath="G:\\7200\\Ruchira\\collaborativeFilter2“
In DecisionTreeModelSpec give the local directory path where ML model for Decision TreeClassifier will be saved


After Successfully Running the all spec files you will see  bot the machine learning Models are persisted at the give directory
B]HotelRecommend : Scala Play Web Project with Spark 2.1
Go to services package in app directory and give path values as the local directory path where Decision Tree model is saved.


Go to the UserHistoryHotels class file in models package and give data path where csv file is saved.

15. Go to sbt console and execute following commands
Clean
Compile
Run

Launch : http://localhost:9000/  to see the web page.






[![CircleCI](https://circleci.com/gh/Chau-Xochitl/Scala-Final-Project.svg?style=svg)](https://circleci.com/gh/Chau-Xochitl/Scala-Final-Project)
# Scala

Consuming apache spark MLLib for machine learning
Implementing MapReduce for recommendations and for descriptive analysis.
Creating Interactive web Dashboard using Play Framework


Expedia has provided you logs of customer behavior. These include what customers searched for, how they interacted with search results (click/book), whether or not the search result was a travel package. The data in this competition is a random selection from Expedia and is not representative of the overall statistics.

Expedia is interested in predicting which hotel group a user is going to book. Expedia has in-house algorithms to form hotel clusters, where similar hotels for a search (based on historical price, customer star ratings, geographical locations relative to city center, etc) are grouped together. These hotel clusters serve as good identifiers to which types of hotels people are going to book, while avoiding outliers such as new hotels that don't have historical data.

Your goal of this competition is to predict the booking outcome (hotel cluster) for a user event, based on their search and other attributes associated with that user event.

The train and test datasets are split based on time: training data from 2013 and 2014, while test data are from 2015. The public/private leaderboard data are split base on time as well. Training data includes all the users in the logs, including both click events and booking events. Test data only includes booking events. 

destinations.csv data consists of features extracted from hotel reviews text. 

Note that some srch_destination_id's in the train/test files don't exist in the destinations.csv file. This is because some hotels are new and don't have enough features in the latent space. Your algorithm should be able to handle this missing information.

File descriptions

train.csv - the training set
test.csv - the test set
destinations.csv - hotel search latent attributes
sample_submission.csv - a sample submission file in the correct format



#
