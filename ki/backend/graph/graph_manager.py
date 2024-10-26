from collections import defaultdict

graph_data = defaultdict(list)

def add_to_graph(root, keywords):
    for keyword in keywords:
        graph_data[root].append(keyword)

def get_graph():
    return dict(graph_data)