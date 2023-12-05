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

symp_dis_df = pd.read_csv('symp_dis.csv')
symp_dis_df = shuffle(symp_dis_df,random_state=42)
for col in symp_dis_df.columns:
    symp_dis_df[col] = symp_dis_df[col].str.replace('_',' ')
cols = symp_dis_df.columns
data = symp_dis_df[cols].values.flatten()

s = pd.Series(data)
s = s.str.strip()
s = s.values.reshape(symp_dis_df.shape)

df = pd.DataFrame(s, columns=symp_dis_df.columns)
df = df.fillna(0)
df1 = pd.read_csv('Symptom-severity.csv')
df1['Symptom'] = df1['Symptom'].str.replace('_',' ')



nlp = spacy.load('en_core_sci_lg')

app = Flask(__name__)

@app.route('/',methods=['GET', 'POST'])
def home():
    if request.method == 'POST':
        json_data = request.get_json()
        print(json_data['symptoms'])
        symps = json_data['symptoms']
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


        target_size = 10

        # Extend the list with zeros until it reaches the target size
        while len(actual_symps_sev) < target_size:
            actual_symps_sev.append(0)

        print(actual_symps_sev)

        # random.shuffle(actual_symps_sev)
        print("Actual Symptom weight: ", actual_symps_sev)
        test_sample = []
        test_sample.append(actual_symps_sev)
        clf = load('mlp_classifier.joblib')
        ypred_test=clf.predict(test_sample)
        print(ypred_test)

        
        disease_food = pd.read_csv('dataset.csv')
        disease1 = str(ypred_test[0])
        print(type(disease1))
        print(type(disease_food.at[0, 'Disease']))
        # Process for finding similarities

        max_sim = 0
        max_sim_idx = 0

        df = pd.DataFrame()
        df = disease_food
        simScoreList = []

        for i in range(len(disease_food)):
            disease_food_tab = get_embedding(disease_food.at[i, 'Disease'])
            # print(type(disease1))
            disease_embedding = get_embedding(disease1)
            score = calculate_similarity(disease_embedding, disease_food_tab)
            simScoreList.append(score)

        df['SimScore'] = simScoreList

        print("Actual Disease: ", disease1)

        def recommend_diet(age, weight, heart_rate):
            # Define threshold ranges
            age_groups = {'young': [0, 30], 'middle': [31, 60], 'senior': [61, float('inf')]}
            weight_categories = {'underweight': [0, 50], 'normal': [51, 80], 'overweight': [81, 100], 'obese': [101, float('inf')]}
            heart_rate_zones = {'bradycardia': [0, 60], 'normal': [61, 100], 'elevated': [101, 140], 'tachycardia': [141, float('inf')]}

            # Multi-conditional logic for diet recommendation
            diets = []
            
            # Age-based conditions
            if age_groups['young'][0] <= age <= age_groups['young'][1]:
                if weight <= weight_categories['underweight'][1]:
                    diets += ['high_calorie_diet', 'high_protein_diet']
                elif weight <= weight_categories['normal'][1]:
                    diets += ['balanced_omni_diet', 'paleo_diet']
                else:
                    diets += ['low_carb_diet', 'ketogenic_diet']

            elif age_groups['middle'][0] <= age <= age_groups['middle'][1]:
                if weight_categories['normal'][0] <= weight <= weight_categories['normal'][1]:
                    diets += ['vegan_diet', 'type_a_diet']
                elif weight <= weight_categories['overweight'][1]:
                    diets += ['Mediterranean_diet']
                else:
                    diets += ['low_fat_diet']

            elif age_groups['senior'][0] <= age:
                if weight > weight_categories['obese'][0]:
                    diets += ['low_carb_diet']
                else:
                    diets += ['Mediterranean_diet', 'alkaline_diet']

            # Heart rate-based conditions
            if heart_rate <= heart_rate_zones['bradycardia'][1]:
                diets += ['high_fiber_diet']
            elif heart_rate <= heart_rate_zones['normal'][1]:
                diets += ['omni_diet']
            elif heart_rate <= heart_rate_zones['elevated'][1]:
                diets += ['DASH_diet', 'low_sodium_diet']
            elif heart_rate > heart_rate_zones['tachycardia'][0]:
                diets += ['DASH_diet']

            # Remove duplicates and return the list of recommended diets
            return list(set(diets))

        # Example usage
        user_age = 45
        user_weight = 75
        user_heart_rate = 85

        diet_recommendations = recommend_diet(user_age, user_weight, user_heart_rate)
        print(f'Recommended diets: {diet_recommendations}')
        filtered_df = df[df['Diet'].apply(lambda diets: any(diet in diets for diet in diet_recommendations))].nlargest(3, 'SimScore')

    # return json.dumps(filtered_df.to_json('Testflask.json', orient='records', lines=True))
    return json.dumps(filtered_df.to_json(orient='records', lines=True))

if __name__ == '__main__':
    app.run(debug=True)
    