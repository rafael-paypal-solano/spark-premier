import os
from datasets import PACKAGE_DIR as PACKAGE_DIR
from spark import SparkEnvironment
from pyspark.sql import Row

FILES_DIR= PACKAGE_DIR + '/ml-latest/ml-latest'
class MovieLens(object):

    @classmethod
    def ratingsRDD(clazz, user_id):   
        spark = SparkEnvironment.instance().spark
        lines = spark.read.option("header","true").csv(FILES_DIR +"/ratings.csv").rdd
        ratingsRDD =  lines \
            .map(lambda row: row.value.split(",")) \
            .map(lambda p: Row( \
                        userId=int(p[0]), \
                        movieId=int(p[1]), \
                        rating=float(p[2]), \
                        timestamp=long(p[3]) \
                    ) \
                )
        return ratingsRDD