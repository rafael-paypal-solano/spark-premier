from recommenders import Movies

if __name__ == "__main__":
    recommendation = Movies.explicit()
    print(recommendation.validator_model.bestModel.extractParamMap()) # PipelineModel
    