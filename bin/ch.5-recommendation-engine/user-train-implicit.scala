/*
In this case, we would like to generate recommended items for a given user. This usually
takes the form of a top-K list, that is, the K items that our model predicts will have the
highest probability of the user liking them.

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

def parseItem(line: String): Tuple2[Int,String] = {
    val fields = line.split("\\|")
    val itemId = fields(0).toInt
    val text = fields(1)
    return Tuple2(itemId, text)
}

def getFeatures() = {
    import spark.implicits._    
    spark.read.textFile("movie-dataset/u.data").map(parseRating).rdd
}

def getItems() = {
    import spark.implicits._    
    spark.read.textFile("movie-dataset/u.item").map(parseItem).collect().toMap
}

 def createALSModel(userId: Int, K: Int): Unit = {
    val ratings = getFeatures()
    val Array(training, test) = ratings.randomSplit(Array(0.8, 0.2))

    val model = ALS.trainImplicit(
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
        0.01,
    /* α
     baseline confidence in preference observations 
    */ 
       1.0  

    )

    val userFeatures = model.userFeatures.count()
    val productFeatures = model.productFeatures.count()
    val topKRecs = model.recommendProducts(userId, K)
    val moviesForUser = ratings.keyBy(_.user).lookup(K)
    val items = getItems()
    val itemsForUser = ratings.keyBy(_.user).lookup(userId)

    println(s"users=${userFeatures}, products=${productFeatures}")
    topKRecs.map(rating => (items(rating.product), rating.rating)).foreach(println)
    
}


createALSModel(/* userId */ 789, /* K */ 10)
System.exit(0)

