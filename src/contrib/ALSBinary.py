from pyspark.ml.recommendation import *
from pyspark.sql.functions import udf, col
from pyspark.mllib.linalg import DenseVector, VectorUDT
from .ALSBinaryModel import ALSBinaryModel
from pyspark.ml.recommendation import ALS

class ALSBinary(ALS):
    def fit(self, df):
        assert self.getImplicitPrefs()
        model = super(ALSBinary, self).fit(df)
        return ALSBinaryModel(model._java_obj)