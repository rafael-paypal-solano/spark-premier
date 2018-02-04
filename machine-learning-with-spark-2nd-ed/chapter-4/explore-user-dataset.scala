import org.apache.spark.SparkConf
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.types.{IntegerType, StringType, StructField, StructType}
import org.apache.spark.sql.{DataFrame, SparkSession}
val PATH = ".."
val PATH_MOVIES = PATH + "/data/ml-100k/u.item"
val PATH_USERS = PATH + "/data/ml-100k/u.user"
val config = (new SparkConf).setMaster("local").setAppName("SparkApp")
val spark = SparkSession.builder().appName("SparkUserData").config(config).getOrCreate()


val customSchema = StructType(Array(
    StructField("no", IntegerType, true),
    StructField("age", StringType, true),
    StructField("gender", StringType, true),
    StructField("occupation", StringType, true),
    StructField("zipCode", StringType, true)
))

val dataSet = spark.read.format("com.databricks.spark.csv").option("delimiter","|").schema(customSchema).load(PATH_USERS)

val numOccupation = dataSet.groupBy("occupation").count().count()
val numZipCodes = dataSet.groupBy("zipCode").count().count()
println(dataSet.groupBy("occupation").count().show())
System.exit(0)