from sentence_transformers import SentenceTransformer
import numpy as np
import faiss
import json

def load_articles():
    with open('posts.json') as posts_data:
        data = json.load(posts_data)
    return data


model = SentenceTransformer('all-MiniLM-L6-v2')

data = load_articles()

texts = [f"{entry['title']} {entry['content']}" for entry in data]
embeddings = model.encode(texts)

dimension = embeddings.shape[1]
index = faiss.IndexFlatL2(dimension)  # L2 distance metric
index.add(np.array(embeddings))

faiss.write_index(index, "embedding_index.faiss")