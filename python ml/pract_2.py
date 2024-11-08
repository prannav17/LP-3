# -*- coding: utf-8 -*-
"""Pract - 2.ipynb

Automatically generated by Colab.

Original file is located at
    https://colab.research.google.com/drive/1bBBYWMpI0YdQmUdfKD5dDr6H_CjreU6b
"""

pip install numpy pandas scikit-learn seaborn matplotlib nltk

import pandas as pd
import re
import nltk
import seaborn as sns
import matplotlib.pyplot as plt
from nltk.corpus import stopwords
from nltk.tokenize import word_tokenize
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import MultinomialNB
from sklearn.metrics import confusion_matrix, classification_report
from sklearn.feature_extraction.text import CountVectorizer

# Download NLTK resources (if not done before)
nltk.download('stopwords')
nltk.download('punkt')

# Load dataset
df = pd.read_csv("/content/sample_data/spam_ham_dataset.csv")

print("Dataset loaded. First few rows:")
print(df.head())

# Preprocessing function
def preprocess_text(text):
    text = re.sub(r'http\S+|www\S+|https\S+', '', text, flags=re.MULTILINE)  # Remove URLs
    text = re.sub(r'[^A-Za-z0-9\s]', '', text)  # Remove special characters
    text = re.sub(r'\s+', ' ', text).strip()  # Remove extra spaces
    return text.lower()

# Apply preprocessing
df['processed_text'] = df['text'].apply(preprocess_text)

# Display updated DataFrame
print("Data after preprocessing:")
print(df.head())

# Visualize class distribution
plt.figure(figsize=(8, 5))
sns.countplot(x='label', data=df)
plt.title('Class Distribution')
plt.xlabel('Label (ham/spam)')
plt.ylabel('Count')
plt.show()

# Show class distribution
print("Class distribution:")
print(df['label'].value_counts())

# Tokenization and stopword removal
stop_words = set(stopwords.words('english'))

def remove_stopwords(text):
    tokens = word_tokenize(text)
    return ' '.join([word for word in tokens if word not in stop_words])

df['cleaned_text'] = df['processed_text'].apply(remove_stopwords)

# Display cleaned DataFrame
print("Data after removing stopwords:")
print(df[['processed_text', 'cleaned_text']].head())

# Split data into training and testing sets
X = df['cleaned_text']
y = df['label']
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2, random_state=42)

# Vectorization
vectorizer = CountVectorizer()
X_train_vectorized = vectorizer.fit_transform(X_train)
X_test_vectorized = vectorizer.transform(X_test)

# Train Naive Bayes model
model = MultinomialNB()
model.fit(X_train_vectorized, y_train)

# Make predictions
y_pred = model.predict(X_test_vectorized)

# Print confusion matrix and classification report
print("Confusion Matrix:")
print(confusion_matrix(y_test, y_pred))
print("\nClassification Report:")
print(classification_report(y_test, y_pred))

# Visualize confusion matrix
conf_matrix = confusion_matrix(y_test, y_pred)
plt.figure(figsize=(8, 5))
sns.heatmap(conf_matrix, annot=True, fmt='d', cmap='Blues', xticklabels=['ham', 'spam'], yticklabels=['ham', 'spam'])
plt.title('Confusion Matrix')
plt.xlabel('Predicted Label')
plt.ylabel('True Label')
plt.show()

