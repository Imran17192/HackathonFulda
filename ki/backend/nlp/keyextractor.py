import spacy
from collections import Counter
from typing import List

def extract_keywords(text: str) -> List[str]:
    nlp = spacy.load('de_core_web_sm')
    doc = nlp(text) # tokenize texts

    # filter substantives
    nouns = [token.text.lower() for token in doc if token.pos_ in ["NOUN", "PROPN"] and not token.is_stop]

    noun_freq = Counter(nouns) # frequency of

    # return most common two words
    most_common = noun_freq.most_common(2)

    return [word for word, freq in most_common]
