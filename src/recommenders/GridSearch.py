from spark import SparkEnvironment
from collections import namedtuple
import itertools
from pathos.multiprocessing import Pool, cpu_count
from pyspark.ml.evaluation import RegressionEvaluator
from pyspark.ml.recommendation import ALS

Penalties = (0.05, 0.1, 0.5, 0.95, 1.0)
Ranks = (10,15)
ALSParams = namedtuple('ALSExplicitParams', 'rank, penalty');
ALSParamSpace = tuple(list(itertools.chain(*[(lambda r : [(lambda p : ALSParams(r, p) )(p) for p in Penalties] )(r) for r in range(10,21)]))) 
ExplicitStepResult = namedtuple('ExplicitStepResult', 'rmse, model, rank, penalty');


class GridSearch(object):
	"""
		This class' static function tunup recommendation algoritms.
	"""

	@classmethod
	def explicit_step(clazz, training, test, user_col, item_col, rating_col, rank, penalty):
		als = ALS(maxIter=5, regParam=penalty, rank=rank, userCol=user_col, itemCol=item_col, ratingCol=rating_col, coldStartStrategy="drop")		
		model = als.fit(training)
		predictions = model.transform(test)
		evaluator = RegressionEvaluator(metricName="rmse", labelCol="rating", predictionCol="prediction")
		rmse = evaluator.evaluate(predictions)
		return ExplicitStepResult(rmse, model, rank, penalty)

	@classmethod
	def explicit_recommender(clazz, ratings, user_col, item_col, rating_col):	
		(training, test) = ratings.randomSplit([0.8, 0.2])
		results = tuple(map(lambda p: GridSearch.explicit_step(training, test, user_col, item_col, rating_col, p.rank, p.penalty), ALSParamSpace))
		#reduce((lambda x, y: x if x.rmse < y.rmse else y), results)