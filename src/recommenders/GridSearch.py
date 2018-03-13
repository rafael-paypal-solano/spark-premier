from spark import SparkEnvironment
from pyspark.ml.evaluation import RegressionEvaluator
from pyspark.ml.tuning import CrossValidator, ParamGridBuilder
from pyspark.ml.recommendation import ALS
from pyspark.ml import Pipeline

Penalties = (0.05, 0.1)
Ranks = (10,15)


class GridSearch(object):
	"""
		This class' static function tunup recommendation algoritms.
	"""


	@classmethod
	def explicit_recommender(clazz, ratings, user_col, item_col, rating_col):	
		(training, test) = ratings.randomSplit([0.8, 0.2])
		als = ALS(maxIter=5, implicitPrefs=False, userCol=user_col, itemCol=item_col, ratingCol=rating_col, coldStartStrategy="drop")
		pipeline = Pipeline(stages=[als])
		evaluator = RegressionEvaluator(metricName="rmse", labelCol=rating_col, predictionCol="prediction")

		params = ParamGridBuilder() \
			.addGrid(als.regParam, Penalties) \
			.addGrid(als.rank, Ranks) \
			.build()

		validator = CrossValidator(estimator=pipeline,
			estimatorParamMaps=params,
			evaluator=evaluator,
			numFolds=2)

		validator.fit(training)

		return True

		