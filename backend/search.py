# search.py
import sys
import json
from sentence_transformers import SentenceTransformer
import faiss
import numpy as np

def load_index_and_model():
    model = SentenceTransformer('all-MiniLM-L6-v2')
    index = faiss.read_index("embedding_index.faiss")
    with open("articles.json", "r") as f:
        articles = json.load(f)
    return model, index, articles

def perform_search(query, model, index, articles, top_k=3):
    query_embedding = model.encode([query])
    distances, indices = index.search(np.array(query_embedding), top_k)
    results = [articles[idx] for idx in indices[0]]
    return results

if __name__ == "__main__":
    query = sys.argv[1]
    model, index, articles = load_index_and_model()
    results = perform_search(query, model, index, articles)
    print(json.dumps(results))
