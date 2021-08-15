import pickle
import pandas as pd
import sys
import sklearn.feature_extraction
from sklearn.feature_extraction.text import CountVectorizer
import xgboost
from xgboost.sklearn import XGBClassifier
from sklearn.naive_bayes import MultinomialNB


def load_model(path):
    return pickle.load(open(path, "rb"))


def load_vectorizer():
    return pickle.load(open("ml/vector.pickle", "rb"))


def predict(host, path):
    data = pd.DataFrame({'url': [host]})
    CountVectorizer()
    data['len'] = list(map(len, data['url'].values))
    data['dots'] = list(map(lambda x: x.count('.'), data['url'].values))
    data['hyphen'] = list(map(lambda x: x.count('-'), data['url'].values))
    data['www'] = list(map(lambda x: int('www' in x), data['url'].values))
    data['numbers'] = list(map(lambda x: sum(c.isdigit() for c in x), data['url'].values))

    corpus = data['url'].values
    vectorizer = load_vectorizer()
    X = vectorizer.transform(corpus).toarray()
    data = pd.concat([data, pd.DataFrame(X)], axis=1).drop(['url'], axis=1)

    model = load_model(path)
    return model.predict(data)[0]


if __name__ == "__main__":
    host = sys.argv[1]
    print(predict(host, sys.argv[2]))
