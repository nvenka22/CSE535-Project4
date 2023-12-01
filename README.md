Project 4- Mobile Computing

Overview: The project is an individual contribution to the final project of the course. This repository consits of the Diet predection based on user given symptoms, heart rate, respiratory rate and other biological information as well. The recommendation system depends upon technologies like Multi Layer Perceptron, Clinical Bert, Transformers,  similarity scores, and Kmeans Clustering algorithms. 

The project Instructions is 
1) Run the Symptoms_disease_scraper.ipynb notebook. Data Collection (using tools like BS4 to scrape the data of various symptoms and their corresponding diseases reported by users) 
2) run the processing.ipynb notebook. Utilzing the data to preprocess, feature engineering using Random Forest Estimator, and finally sending the data with Diseses, and the user reported symptoms (top 10 based on the statistical significance provided by Random Forest Estimator) based on the importance of each symptom given by the symptom severity scoured from CDC.gov. 
3) Run the Symptom_Mc.ipynb notebook: Building the Multi Layer Perceptron model that will predict the "possbile" diseases based on the user symptoms. 
4) (continuation of 3rd step) We then use ClinicalBert, Embedding to find the most related diseases to the one predicted by the model, and recommend multiple food items based on the closest diseases to the symptoms and user's biological data. 

Libraries needed (with their corresponding codes ) are: 

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split,KFold,cross_val_score,GridSearchCV
from sklearn.svm import SVC
import sklearn.metrics
import seaborn as sns
from sklearn.utils import shuffle
from sklearn.preprocessing import StandardScaler
from sklearn.cluster import KMeans
import seaborn as sns
import matplotlib.pyplot as plt
import requests
from bs4 import BeautifulSoup
from bs4 import NavigableString
import time
import pickle

