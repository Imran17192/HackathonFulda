import spacy
from collections import Counter
from typing import List


class KeywordExtractor:
    def __init__(self, model_name="en_core_web_sm"):
        self.nlp = spacy.load(model_name)

    def extract_keywords(self, text: str) -> List[str]:
        doc = self.nlp(text) # tokenize texts

        # filter substantives
        nouns = [token.text.lower() for token in doc if token.pos_ in ["NOUN", "PROPN"] and not token.is_stop]

        noun_freq = Counter(nouns) # frequency of

        # Die zwei häufigsten Wörter zurückgeben
        most_common = noun_freq.most_common(2)

        return [word for word, freq in most_common]
