import os
from datasets import PACKAGE_DIR as PACKAGE_DIR
from spark import SparkEnvironment
from pyspark.mllib.recommendation import Rating

FILES_DIR= PACKAGE_DIR + '/ml-latest-small/ml-latest-small'

class MovieLens(object):
    """
        This class provides convenience methods to create RDD out of files contained in http://files.grouplens.org/datasets/movielens/ml-latest-small. 
    """

    @classmethod
    def ratingsRDD(clazz):   
        """
            Reads ratings.csv and returns those records whose userId attribute matches user_id value.
            
            Returns:
                A pyspark.sql.dataframe.DataFrame of movie ratings (userId, movieId, rating, timestamp)
        """

        spark = SparkEnvironment.instance().spark
        all = spark.read.format("com.databricks.spark.csv").option('header', 'true').option('inferSchema', 'true').load(FILES_DIR + '/ratings.csv')   
        return all