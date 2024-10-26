import spacy
from collections import Counter
from typing import List

def extract_keywords(text: str) -> List[str]:
    # Load German spaCy model (adjust language model if needed)
    nlp = spacy.load('en_core_web_sm')
    doc = nlp(text)  # Tokenize the text

    # Filter only nouns and proper nouns, ignoring stop words
    nouns = [token.text.lower() for token in doc if token.pos_ in ["NOUN", "PROPN"] and not token.is_stop]
    noun_freq = Counter(nouns)  # Count frequencies of each noun

    # Return the two most common keywords (can adjust this number)
    most_common = noun_freq.most_common(2)
    print( [word for word, freq in most_common])
    return [word for word, freq in most_common]
