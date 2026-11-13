import csv
import importlib
import os
import string
import numpy as np

filename = "lyrics.csv"
if not os.path.exists(filename):
    if os.path.exists("Lyrics Sample Dataset.csv"):
        filename = "Lyrics Sample Dataset.csv"
    elif os.path.exists("Lyrics Sample Dataset.xlsx"):
        pd = importlib.import_module("pandas")
        df_ex = pd.read_excel("Lyrics Sample Dataset.xlsx")
        df_ex.to_csv("lyrics.csv", index=False)
        filename = "lyrics.csv"

with open(filename, newline="", encoding="utf-8") as f:
    rows = list(csv.reader(f))

header = rows[0]
data = np.array(rows[1:], dtype=object)

print("Columns:", header)
print("Shape:", data.shape)

LYRICS_COL = 0

lyrics = data[:, LYRICS_COL]
print("Total lyrics:", len(lyrics))

remove_punct = str.maketrans("", "", string.punctuation)

def clean_lyric(text):
    return text.lower().translate(remove_punct)

clean_all = np.vectorize(clean_lyric, otypes=[object])
clean_lyrics = clean_all(lyrics)

print("\nBefore:", lyrics[0][:100])
print("After: ", clean_lyrics[0][:100])

rng = np.random.default_rng()  
sample_size = min(10, len(clean_lyrics))  
sample_indexes = rng.choice(len(clean_lyrics), size=sample_size, replace=False)
sample = clean_lyrics[sample_indexes]

print("\nRandom sample of", sample_size, "lyrics:")
for i, lyric in enumerate(sample, start=1):
    print(i, "-", lyric[:80])
