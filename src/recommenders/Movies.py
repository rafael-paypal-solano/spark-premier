from datasets import MovieLens
from spark import SparkEnvironment
#
# userId,movieId,rating,timestamp
#
class Movies(object):
    """
        This class methods create explicit and implicit recommendations related to movie ratings (http://files.grouplens.org/datasets/movielens/ml-latest.zip).
    """	
    @classmethod
    def explicit(clazz, user_id):        
    	"""
    		This method creates movie recommenations for a given user.

    		Args:
    			user_id(int): Id representing the user.

    		Returns:
    			recommenders.Recommendation
    	"""
        return MovieLens.ratingsRDD(user_id)