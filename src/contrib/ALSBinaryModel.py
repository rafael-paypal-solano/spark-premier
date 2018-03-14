from pyspark.ml.recommendation import *
from pyspark.sql.functions import udf, col
from pyspark.mllib.linalg import DenseVector, VectorUDT
from pyspark.ml.recommendation import ALSModel

class ALSBinaryModel(ALSModel):
    def transform(self, df):
        transformed = super(ALSBinaryModel, self).transform(df)
        as_vector = udf(lambda x: DenseVector([1 - x, x]), VectorUDT())
        return transformed.withColumn("rawPrediction", as_vector(col("prediction")))