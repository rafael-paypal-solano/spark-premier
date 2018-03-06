from pyspark.sql import SparkSession

class SparkEnvironment(object):
    __instance__ = None

    def __init__(self):
        self.__spark__ = SparkSession.builder \
            .master("local") \
            .appName("Spark-Premier") \
            .config("spark.executor.memory", "1gb") \
            .getOrCreate()
        self.__context__ = self.__spark__.sparkContext

    @classmethod
    def instance(clazz):
        if clazz.__instance__ is None:
            clazz.__instance__ = SparkEnvironment() 
        return clazz.__instance__

    @property
    def spark(self):
        return self.__spark__

    @property
    def context(self):
        return self.__context__        