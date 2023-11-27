from flask import Flask, redirect, render_template, request, session, url_for, jsonify
import spacy
import scispacy
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from sklearn.model_selection import train_test_split,KFold,cross_val_score,GridSearchCV
from sklearn.svm import SVC
import sklearn.metrics
import seaborn as sns
from sklearn.utils import shuffle
from sklearn.linear_model import LogisticRegression, Perceptron, RidgeClassifier, SGDClassifier
from sklearn.ensemble import RandomForestClassifier, GradientBoostingClassifier, ExtraTreesClassifier
from sklearn.ensemble import BaggingClassifier, AdaBoostClassifier, VotingClassifier
from sklearn.neighbors import KNeighborsClassifier
from sklearn.naive_bayes import GaussianNB
from sklearn.tree import DecisionTreeClassifier
from sklearn import metrics
import joblib
from joblib import load, dump
from sklearn.metrics import accuracy_score, f1_score
import json
from transformers import AutoTokenizer, AutoModel
import torch
import pandas as pd
from scipy.spatial.distance import cosine

df = pd.read_csv('symp_dis.csv')
df = shuffle(df,random_state=42)
for col in df.columns:
    df[col] = df[col].str.replace('_',' ')
cols = df.columns
data = df[cols].values.flatten()

s = pd.Series(data)
s = s.str.strip()
s = s.values.reshape(df.shape)

df = pd.DataFrame(s, columns=df.columns)
df = df.fillna(0)
df1 = pd.read_csv('Symptom-severity.csv')
df1['Symptom'] = df1['Symptom'].str.replace('_',' ')

# symptoms = df1['Symptom'].unique()

# vals = df.values
# for i in range(len(symptoms)):
#     vals[vals == symptoms[i]] = df1[df1['Symptom'] == symptoms[i]]['weight'].values[0]

# d = pd.DataFrame(vals, columns=cols)

# d = d.replace('dischromic  patches', 0)
# d = d.replace('spotting  urination',0)
# df = d.replace('foul smell of urine',0)
# data = df.iloc[:,1:].values
# labels = df['Disease'].values
# x_train, x_test, y_train, y_test = train_test_split(data, labels, train_size = 0.8,random_state=42)
# print(x_train.shape, x_test.shape, y_train.shape, y_test.shape)
# from sklearn.neural_network import MLPClassifier
# clf = MLPClassifier(hidden_layer_sizes=(6,5),
#                     random_state=5,
#                     verbose=True,
#                     learning_rate_init=0.01)
# clf.fit(x_train, y_train)
# # Make prediction on test dataset
# ypred=clf.predict(x_test)

# # Calcuate accuracy
# accuracy_score(y_test,ypred)
# print('F1-score% =', f1_score(y_test, ypred, average='macro')*100, '|', 'Accuracy% =', accuracy_score(y_test, ypred)*100)

nlp = spacy.load('en_core_sci_lg')

app = Flask(__name__)

@app.route('/',methods=['GET', 'POST'])
def home():
    if request.method == 'POST':
        json_data = request.get_json()
        print(json_data['symptoms'])
        symps = json_data['symptoms']
        # symps = ['stomach ache','headache', 'fever', 'cold']
        # actual_symps = []
        # actual_symps_sev = []
        # max_sim=0
        # max_sim_idx = 0
        # for s in symps:
        #     for i in range(0, len(df1)):
        #         tmp_text = nlp(df1.at[i, 'Symptom'])
        #         tmp_text1 = nlp(s)
        #         score = tmp_text1.similarity(tmp_text)
        #         if(max_sim < score):
        #             max_sim = score
        #             max_sim_idx = i
        #     actual_symps.append(df1.at[max_sim_idx, 'Symptom'])
        #     actual_symps_sev.append(df1.at[max_sim_idx, 'weight'])
        #     max_sim=0
        #     max_sim_idx = 0
        # print("Actual Symptoms: ", actual_symps)
        # Load ClinicalBERT
        tokenizer = AutoTokenizer.from_pretrained("emilyalsentzer/Bio_ClinicalBERT")
        model = AutoModel.from_pretrained("emilyalsentzer/Bio_ClinicalBERT")

        def get_embedding(text):
            inputs = tokenizer(text, return_tensors="pt", padding=True, truncation=True, max_length=512)
            with torch.no_grad():
                outputs = model(**inputs)
            input_mask_expanded = inputs['attention_mask'].unsqueeze(-1).expand(outputs.last_hidden_state.size()).float()
            sum_embeddings = torch.sum(outputs.last_hidden_state * input_mask_expanded, 1)
            sum_mask = torch.clamp(input_mask_expanded.sum(1), min=1e-9)
            return sum_embeddings / sum_mask

        def calculate_similarity(embedding1, embedding2):
        # Flatten the embeddings to 1-D
            embedding1_flat = embedding1.flatten()
            embedding2_flat = embedding2.flatten()

            return 1 - cosine(embedding1_flat, embedding2_flat)

        # symps = ['Nausea', 'Headache', 'diarrhea', 'Sore Throat', 'Fever', 'Muscle Ache', 'Loss of Smell or Taste', 'Cough', 'Shortness of Breath', 'Feeling tired']
        # symps = ['Nausea', 'Headache', 'diarrhea', 'Sore Throat', 'Fever', 'Muscle Ache']
        # Process for finding similarities
        actual_symps = []
        actual_symps_sev = []

        for s in symps:
            max_sim = 0
            max_sim_idx = 0
            s_embedding = get_embedding(s)
            
            for i in range(len(df1)):
                df1_symptom_embedding = get_embedding(df1.at[i, 'Symptom'])
                score = calculate_similarity(s_embedding, df1_symptom_embedding)
                
                if max_sim < score:
                    max_sim = score
                    max_sim_idx = i

            actual_symps.append(df1.at[max_sim_idx, 'Symptom'])
            actual_symps_sev.append(df1.at[max_sim_idx, 'weight'])

        print("Actual Symptoms: ", actual_symps)
        print("Actual Symptom Severity: ", actual_symps_sev)


        target_size = 17

        # Extend the list with zeros until it reaches the target size
        while len(actual_symps_sev) < target_size:
            actual_symps_sev.append(0)

        # random.shuffle(actual_symps_sev)
        print("Actual Symptom weight: ", actual_symps_sev)
        test_sample = []
        test_sample.append(actual_symps_sev)
        clf = load('mlp_classifier.joblib')
        ypred_test=clf.predict(test_sample)
        print(ypred_test)


    return json.dumps(ypred_test.tolist())

if __name__ == '__main__':
    app.run(debug=True)
    