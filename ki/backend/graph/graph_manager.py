from collections import defaultdict

# Initialize an empty graph structure as a dictionary of lists
graph_data = defaultdict(list)

def add_to_graph(root: str, keywords: list):
    # Add each keyword as a child node of the root
    for keyword in keywords:
        graph_data[root].append(keyword)

def get_graph():
    # Return the current graph structure as a regular dictionary
    return dict(graph_data)
