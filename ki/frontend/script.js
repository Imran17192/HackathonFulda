// Breite und Höhe für das SVG-Element festlegen
const width = 928;
const height = 600;

// Farbskala definieren
const color = d3.scaleOrdinal(d3.schemeCategory10);

// JSON-Datei laden und Daten verwenden
d3.json("graph.json").then(data => {
    // Simulation erstellen und Kräfte hinzufügen
    const links = data.links.map(d => ({ ...d }));
    const nodes = data.nodes.map(d => ({ ...d }));

    const simulation = d3.forceSimulation(nodes)
        .force("link", d3.forceLink(links).id(d => d.id))
        .force("charge", d3.forceManyBody())
        .force("center", d3.forceCenter(width / 2, height / 2))
        .on("tick", ticked);

    // SVG-Container erstellen
    const svg = d3.select("body").append("svg")
        .attr("width", width)
        .attr("height", height)
        .attr("viewBox", [0, 0, width, height])
        .attr("style", "max-width: 100%; height: auto;");

    // Linien für jede Verbindung und Kreise für jeden Knoten hinzufügen
    const link = svg.append("g")
        .attr("stroke", "#999")
        .attr("stroke-opacity", 0.6)
        .selectAll("line")
        .data(links)
        .join("line")
        .attr("stroke-width", d => Math.sqrt(d.value));

    const node = svg.append("g")
        .attr("stroke", "#fff")
        .attr("stroke-width", 1.5)
        .selectAll("circle")
        .data(nodes)
        .join("circle")
        .attr("r", 5)
        .attr("fill", d => color(d.group));

    // Labels für jedes Knoten-Id hinzufügen
    const label = svg.append("g")
        .attr("class", "labels")
        .selectAll("text")
        .data(nodes)
        .join("text")
        .attr("dy", -10)  // Position slightly above the nodes
        .attr("text-anchor", "middle")
        .attr("font-size", 10)
        .text(d => d.id);

    // Drag-Verhalten hinzufügen
    node.call(d3.drag()
        .on("start", dragstarted)
        .on("drag", dragged)
        .on("end", dragended));

    // Position der Links und Nodes bei jedem Takt aktualisieren
    function ticked() {
      link
          .attr("x1", d => d.source.x)
          .attr("y1", d => d.source.y)
          .attr("x2", d => d.target.x)
          .attr("y2", d => d.target.y);

      node
          .attr("cx", d => d.x)
          .attr("cy", d => d.y);

      label  // Label position mit Nodes synchronisieren
          .attr("x", d => d.x)
          .attr("y", d => d.y);
    }

    // Funktionen für das Dragging definieren
    function dragstarted(event) {
      if (!event.active) simulation.alphaTarget(0.3).restart();
      event.subject.fx = event.subject.x;
      event.subject.fy = event.subject.y;
    }

    function dragged(event) {
      event.subject.fx = event.x;
      event.subject.fy = event.y;
    }

    function dragended(event) {
      if (!event.active) simulation.alphaTarget(0);
      event.subject.fx = null;
      event.subject.fy = null;
    }
}).catch(error => console.error("Fehler beim Laden der Daten:", error));
