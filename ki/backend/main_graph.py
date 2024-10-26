from fastapi import FastAPI, HTTPException
from pydantic import BaseModel
from nlp.keyextractor import extract_keywords
from graph.graph_manager import add_to_graph, get_graph

# FastAPI App initialisieren
app = FastAPI()


# Pydantic-Modell für den Post-Request
class PostData(BaseModel):
    title: str
    content: str


# API-Endpunkt für das Hinzufügen eines neuen Posts
@app.post("/add_post/")
async def add_post(post: PostData):
    # Keywords mit spaCy extrahieren
    keywords = extract_keywords(post.content)

    # Festlegen des Root-Knotens als erstes Keyword, oder Standard-Wert verwenden
    root = keywords[0] if keywords else "General"

    # Keywords in die Baumstruktur hinzufügen
    add_to_graph(root, keywords[1:])  # Verbindungen vom Root zu den weiteren Keywords

    return {
        "message": "Post added successfully",
        "root": root,
        "keywords": keywords,
        "graph": get_graph()
    }


# API-Endpunkt zur Abfrage der gesamten Baumstruktur
@app.get("/graph/")
async def graph():
    return get_graph()


# Optionale Route zum Abrufen des Graphen in JSON-Format für den Export
@app.get("/export_graph/")
async def export_graph():
    return {"graph": get_graph()}
