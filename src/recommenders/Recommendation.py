from collections import namedtuple

Recommendation = namedtuple('Recommendation','validator_model, training, test')
"""
	movalidator_modeldel (org.apache.spark.ml.tuning.CrossValidatorModel): 
	training (DataFrame): Training dataset
	test (DataFrame): Test dataset.
"""
	