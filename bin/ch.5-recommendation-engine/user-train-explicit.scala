/*
In this case, we would like to generate recommended items for a given user. This usually
takes the form of a top-K list, that is, the K items that our model predicts will have the
highest probability of the user liking htem.

In this script, we will use explicit rating data without additional user information, item meta data or other information
related to the user-item interactions. Hence the features we need as input are simply userID, placeID and rating from rating_final.csv
*/
import org.apache.spark.{sql, SparkConf}
import org.apache.spark.mllib.recommendation.{ALS, MatrixFactorizationModel, Rating}
import org.apache.spark.sql.{Row, DataFrame, DataFrameWriter}

// https://www.programcreek.com/scala/org.apache.spark.mllib.recommendation.MatrixFactorizationModel
def parseRating(str: String): Rating = {
    val fields = str.split("\t")
    val userID = fields(0).toInt
    val placeID = fields(1).toInt
    val rating = fields(2).toDouble
    Rating(userID, placeID, rating)
}

def getFeatures() = {
    import spark.implicits._    
    spark.read.textFile("movie-dataset/sample_movielens_ratings.txt").map(parseRating).rdd
}

 def createALSModel() {
    val ratings = getFeatures()
    val Array(training, test) = ratings.randomSplit(Array(0.8, 0.2))

    val model = ALS.train(
        training,
    /* rank:
    This refers to the number of factor s in our ALS Model, that is, the number of hidden features in our low-rank approximation. Generally,
    the greater the number of factors, the better, but this has direct impact on memory usage, both for computation and to store models forserving, particularly
    for large numbers of users or items. Hence, this is often a trade-off in real-world use cases. It also impacts the amount of training required             
    */            
        6,
    /* iterations
    This refer to the number of iterations to run. While each iteration in ALS is guaranteed to decrease the reconstruction error of the rating matrix,
    ALS models will converge to a reasonably good solution after relative little iterations. So, we won't need to run too many iterations in most cases
    --around 10 is often a good result.
    */
        5,

    /* λ
    This specifies the regularization param in ALS (defaults to 1.0). The constant λ is called regularization parameter and essentially penalizes
    the components for the user and item matrices if they get too large in magnitude. This is important for numerical stability , and some kind
    of regularization is almost always used.
    */ 
        0.01
    )

    val userFeatures = model.userFeatures.count()
    val productFeatures = model.productFeatures.count()
    val userId = 789
    val predictedRating = model.predict(userId, 123)
    val K = 10
    val topKRecs = model.recommendProducts(userId, K)
    val moviesForUser = ratings.keyBy(_.user).lookup(K)
    
    println(s"users=${userFeatures}, products=${productFeatures}")
    println(s"predictedRating=${predictedRating}")
    //TODO: Display recommended titles sorted by rating (see example on page 201)
}

createALSModel()
System.exit(0)
