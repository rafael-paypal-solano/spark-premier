import os
from datasets import PACKAGE_DIR as PACKAGE_DIR
from spark import SparkEnvironment
from pyspark.sql import Row

FILES_DIR= PACKAGE_DIR + '/ml-latest/ml-latest'

class MovieLens(object):
    """
        This class provides convenience methods to create RDD out of files contained in http://files.grouplens.org/datasets/movielens/ml-latest.zip. 
    """

    @classmethod
    def to_rating_row(clazz, line)
        """
            Args:
                line (string): Non empty/null character string loaded from ratings.csv
            Returns:
                A pyspark.sql.Row whose fields are userId, movieId, rating, timestamp

        """
        array = line.value.split(',')
        row = Row(int(array[0]), int(array[1]), float(array[2]), long(array[3]))

    @classmethod
    def ratingsRDD(clazz, user_id):   
        """
            Reads ratings.csv and returns those records whose userId attribute matches user_id value.
            
            Args:
                user_id (int): Id representing the user whose rating data we want to load.

            Returns:
                An RDD of movie ratings (userId, movieId, rating, timestamp)
        """

        spark = SparkEnvironment.instance().spark
        lines = spark.read.option('header', 'true').csv(FILES_DIR + '/ratings.csv').rdd
        ratingsRDD =  lines \
            .map(MovieLens.to_rating_row) \
            .filter(lambda row: row.userId == user_id)

        return ratingsRDD