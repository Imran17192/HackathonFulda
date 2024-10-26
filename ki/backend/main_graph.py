# Import the necessary functions and classes
from collections import Counter

from nlp.keyextractor import extract_keywords
from backend.graph.graph_manager import add_to_graph, get_graph
from models.post_model import PostData
from keybert import KeyBERT
from sentence_transformers import SentenceTransformer, util
import json

# Initialize KeyBERT model
model = KeyBERT("distilbert-base-nli-mean-tokens")


def generate_root(content: str) -> str:
    """
    Uses KeyBERT to generate a three-word root summary of the post content.
    This serves as the root node in the graph.
    """
    keywords = model.extract_keywords(content, keyphrase_ngram_range=(3, 3), stop_words="english", top_n=1)
    root_summary = keywords[0][0] if keywords else "General"
    return root_summary


def determine_common_root(roots):
    """
    Determines the most common word across all roots to serve as the new unified root.
    """
    all_words = []
    for root in roots:
        all_words.extend(root.split())

    most_common_word, _ = Counter(all_words).most_common(1)[0]
    return most_common_word


def merge_all_trees():
    """
    Merges all existing trees in the graph into a nested structure with the most common word as the new root.
    """
    graph = get_graph()

    # Determine the new root based on the most common word
    new_root = determine_common_root(graph.keys())
    print(f"Merging all trees under the new root: {new_root}")

    # Create a new nested structure with the most common root
    nested_graph = {new_root: {}}

    # Add each existing root as a sub-node under the new root
    for existing_root, keywords in graph.items():
        nested_graph[new_root][existing_root] = keywords

    # Clear the graph and update with the nested structure
    graph.clear()
    graph.update(nested_graph)  # Replace with the new nested structure



# Function to add post content to the graph
def process_post(content: str):
    # Step 1: Generate root using KeyBERT
    root = generate_root(content)
    print(f"Generated root: {root}")

    # Step 2: Extract Keywords (to add as branches)
    keywords = extract_keywords(content)

    # Step 3: Add to graph
    add_to_graph(root, keywords)


# Sample posts
sample_post_1 = """
Our company developed a chatbot for an e-commerce platform to enhance customer support.
The chatbot can answer frequently asked questions, manage orders, and offer product recommendations.
This feature has improved customer satisfaction and reduced operational costs.
"""

sample_post_2 = """
We built a recommendation engine to personalize product suggestions for an e-commerce site.
The engine analyzes user behavior and past purchases using machine learning.
This tool enhances customer satisfaction by suggesting products aligned with customer preferences.
"""

sample_post_3 = """
Our team created a data analysis system for an e-commerce business to improve customer experience.
This system tracks user interactions and provides insights into customer preferences, helping tailor product recommendations.
"""

# Process each sample post
print("Processing Sample Post 1:")
process_post(sample_post_1)

print("\nProcessing Sample Post 2:")
process_post(sample_post_2)

print("\nProcessing Sample Post 3:")
process_post(sample_post_3)

# Merge all trees under the most common root word
merge_all_trees()


# Retrieve and display the final graph structure
graph = get_graph()
print("\nGenerated Graph Structure:")
print(graph)


def convert_to_d3_format(graph):
    # Determine the main root node based on the most common word
    main_root = determine_common_root(graph.keys())

    # Create the root structure for D3
    d3_graph = {"name": main_root, "children": []}

    # For each sub-root in the original graph
    for sub_root, keywords in graph.items():
        sub_node = {
            "name": sub_root,
            "children": [{"name": keyword} for keyword in keywords]
        }
        d3_graph["children"].append(sub_node)

    return json.dumps(d3_graph, indent=2)  # Convert to JSON format with indentation

# Assuming you have your `graph` data ready
d3_ready_graph = convert_to_d3_format(graph)
print(d3_ready_graph)  # This JSON can now be used with D3.js