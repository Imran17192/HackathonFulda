document.getElementById("postForm").addEventListener("submit", async function(event) {
    event.preventDefault();

    const title = document.getElementById("title").value;
    const content = document.getElementById("content").value;

    try {
        const response = await fetch("http://127.0.0.1:8000/add_post/", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify({ title, content })
        });

        if (response.ok) {
            loadGraph();
        } else {
            console.error("Error adding post");
        }
    } catch (error) {
        console.error("Error:", error);
    }
});

// Funktion zum Laden und Darstellen des Graphen
async function loadGraph() {
    try {
        const response = await fetch("http://127.0.0.1:8000/graph/");
        const graphData = await response.json();

        // Einfache Visualisierung des Graphen (z.B. als Text oder JSON)
        document.getElementById("graph").textContent = JSON.stringify(graphData, null, 2);
    } catch (error) {
        console.error("Error loading graph:", error);
    }
}

// Beim Laden der Seite den Graphen abrufen
loadGraph();