Project 5- Mobile Computing

DietPro: A Context-Aware Diet Recommendation System

Overview: Project 5 is an integration of individual contributions from Project 4. The application is designed in Android Studio, and for testing purposes, we have Containerized the Core Recommendation Engine and its corresponding Flask application. DietPro represents a groundbreaking advancement in personalized nutrition and health management, integrating a suite of cutting-edge technologies to deliver user-specific diet recommendations. At its core, DietPro employs a custom-built heart rate calculator using the flash of the mobile, accelerometer to monitor physical activity levels, providing valuable context for dietary needs based on daily movement patterns. The system harnesses the power of Clinical BERT and Transformer models, state-of-the-art in natural language processing, to interpret complex health data and user inputs. This sophisticated approach allows DietPro to understand and analyze user-specific health symptoms and feedback more accurately.

Instructions
1) Clone the Application from GitHub and open it in Android Studio. Run the Flask App in the background. 
2) Run the application in an Emulator, and once opened enter the User information like Age, height, weight, and other physiological information.
3) Use the Heart Rate Calculator and Respiratory Rate Calculator in the application, and also give the symptoms and the ratings for those symptoms. 
4) The data will then be sent to the Flask route automatically which has a loaded pre-trained MLP Classifier and Clinical BERT. 
5) Wait for some time, and the application will send the recommended diets based on the user's health conditions in the format of 'breakfast', 'lunch', and 'dinner'.
6) The User-Specific Data is stored in the Backend, and every time the user gives his information, the model generates the diets based on his contextual health conditions. 


Libraries needed (with their corresponding codes) are: 

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
from flask import Flask, redirect, render_template, request, session, url_for, jsonify
import spacy
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.svm import SVC
import sklearn.metrics
import seaborn as sns
from sklearn.utils import shuffle
import joblib
from joblib import load, dump
import json
from transformers import AutoTokenizer, AutoModel
import torch
import pandas as pd
from scipy.spatial.distance import cosine

