from datasets import MovieLens
from spark import SparkEnvironment
#
# userId,movieId,rating,timestamp
#
class Movies(object):
    @classmethod
    def explicit(clazz, user_id):        
        return MovieLens.ratingsRDD(user_id)