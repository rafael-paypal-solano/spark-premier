import os
from datasets import PACKAGE_DIR as PACKAGE_DIR
from spark import SparkEnvironment
from pyspark.mllib.recommendation import Rating

FILES_DIR= PACKAGE_DIR + '/ml-latest/ml-latest'

class MovieLens(object):
    """
        This class provides convenience methods to create RDD out of files contained in http://files.grouplens.org/datasets/movielens/ml-latest.zip. 
    """

    @classmethod
    def ratingsRDD(clazz, user_id):   
        """
            Reads ratings.csv and returns those records whose userId attribute matches user_id value.
            
            Args:
                user_id (int): Id representing the user whose rating data we want to load.

            Returns:
                A pyspark.sql.dataframe.DataFrame of movie ratings (userId, movieId, rating, timestamp)
        """

        spark = SparkEnvironment.instance().spark
        all = spark.read.option('header', 'true').csv(FILES_DIR + '/ratings.csv')
        filtered = all.filter(all['userId'] == user_id)
        return filtered