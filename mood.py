
file_path = '/Users/harshitbilla/Downloads/food_data_final1.csv'

import pandas as pd

import random

# Read the CSV file into a pandas DataFrame
df = pd.read_csv(file_path)

# Extract unique labels from the first column
unique_labels = df.iloc[:, 0].unique()

# Print the unique labels
#print("Unique Labels in the First Column:")
#print(unique_labels)

# Create a dictionary to store rows for each unique value in the first column
data_dict = {}

# Iterate through unique values in the first column
for unique_value in df.iloc[:, 0].unique():
    # Filter rows with the current unique value
    rows_for_value = df[df.iloc[:, 0] == unique_value]

    # Convert the filtered rows to a list of dictionaries
    rows_list = rows_for_value.to_dict(orient='records')

    # Store the list of dictionaries in the data_dict with the unique value as the key
    data_dict[unique_value] = rows_list

# Print the resulting dictionary
#print("Dictionary Relating to the CSV File:")
#print(data_dict)

# Iterate through unique values in the first column
for unique_value in df.iloc[:, 0].unique():
    # Filter rows with the current unique value
    rows_for_value = df[df.iloc[:, 0] == unique_value]

    # Convert the filtered rows to a list of dictionaries
    rows_list = rows_for_value.to_dict(orient='records')

    # Store the list of dictionaries in the data_dict with the unique value as the key
    data_dict[unique_value] = rows_list

# Function to randomly select one row for a given unique value and format the output
def get_food_recommend(unique_value):
    # Check if the unique_value exists in the dictionary
    if unique_value in data_dict:
        # Get the list of rows for the unique value
        rows_list = data_dict[unique_value]

        # Randomly select one row from the list
        random_row = random.choice(rows_list)

        # Format the output with each column on a new line along with the header
        formatted_output = " \n ".join([f"{column}: {value}" for column, value in random_row.items()])

        return formatted_output
    else:
        return None
    



print(get_food_recommend('Sad'))