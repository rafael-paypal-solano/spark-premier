from datasets import MovieLens
from spark import SparkEnvironment
from .GridSearch import GridSearch

#
# userId,movieId,rating,timestamp
#
class Movies(object):
	"""
	    This class methods create explicit and implicit recommendations related to movie ratings (http://files.grouplens.org/datasets/movielens/ml-latest.zip).
	"""	


	@classmethod
	def explicit(clazz):        
		"""
			This method creates movie recommenations for a given user.

			Args:
				user_id(int): Id representing the user.

			Returns:
				recommenders.Recommendation
		"""

		ratings = MovieLens.ratingsRDD()
		return GridSearch.explicit_recommender(ratings, "userId", "movieId", "rating")
		