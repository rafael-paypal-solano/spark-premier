from collections import namedtuple

Recommendation = namedtuple('Recommendation','user_id, ratings, rmse')
"""
	user_id (int): User id.
	ratings (pandas.dataframe):
	rmse (float): Root mean squared errors
"""
	