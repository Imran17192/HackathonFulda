# Import the necessary functions and classes
from nlp.keyextractor import extract_keywords
from backend.graph.graph_manager import add_to_graph, get_graph
from models.post_model import PostData

def find_similar_root(keywords, existing_roots, threshold=0.5):
    """
    Find a similar root in the existing graph based on keyword overlap.
    :param keywords: List of keywords from the new post
    :param existing_roots: List of existing roots in the graph
    :param threshold: Similarity threshold to consider two topics the same
    :return: The similar root or None if no similar root is found
    """
    keyword_set = set(keywords)
    for root in existing_roots:
        root_set = set(root.split())  # Split root words for comparison
        similarity = len(keyword_set & root_set) / len(keyword_set | root_set)  # Jaccard similarity
        if similarity >= threshold:
            return root
    return None

# Function to add post content to the graph
def process_post(content: str):
    # Step 1: Extract Keywords
    keywords = extract_keywords(content)
    root = keywords[0] if keywords else "General"

    # Step 2: Check for similar root in existing graph
    graph = get_graph()
    similar_root = find_similar_root(keywords, graph.keys())

    if similar_root:
        print(f"Adding to existing topic tree: {similar_root}")
        add_to_graph(similar_root, keywords[1:])  # Add keywords under similar root
    else:
        print(f"Creating new tree for topic: {root}")
        add_to_graph(root, keywords[1:])  # Start a new tree

# Sample post data
sample_post_1 = """
I recently developed a fully functional e-commerce system for a startup.
It includes a user-friendly frontend, a secure backend, and a reliable database structure.
The platform integrates payment providers, customer management, and inventory tracking.
This solution improves user experience and simplifies the management of products and orders.
"""

sample_post_2 = """
Our team created a recommendation engine to personalize product suggestions for an online store.
This system analyzes customer behavior and purchase history using machine learning techniques.
It enhances the shopping experience by suggesting products customers are more likely to buy.
"""

sample_post_3 = """
We deployed a chatbot to improve customer service for an e-commerce platform.
The chatbot can handle common inquiries, manage orders, and provide personalized product recommendations.
This feature has significantly increased customer satisfaction and reduced support costs.
"""

# Process each sample post
print("Processing Sample Post 1:")
process_post(sample_post_1)

print("\nProcessing Sample Post 2:")
process_post(sample_post_2)

print("\nProcessing Sample Post 3:")
process_post(sample_post_3)

# Retrieve and display the final graph structure
graph = get_graph()
print("\nGenerated Graph Structure:")
print(graph)
