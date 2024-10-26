// Set width and height for the SVG element
const width = 928;
const height = 600;

// Define color scale
const color = d3.scaleOrdinal(d3.schemeCategory10);

// Load JSON data
d3.json("graph.json").then(data => {
    // Map links and nodes data
    const links = data.links.map(d => ({ ...d }));
    const nodes = data.nodes.map(d => ({ ...d }));

    // Dynamic node size based on the number of links
    const nodeSizeScale = d3.scaleLinear()
        .domain([0, d3.max(nodes, node => links.filter(link => link.source === node.id || link.target === node.id).length)])
        .range([5, 15]);

    // Tooltip div
    const tooltip = d3.select("body").append("div")
        .attr("class", "tooltip")
        .style("position", "absolute")
        .style("background", "#f9f9f9")
        .style("padding", "5px")
        .style("border", "1px solid #ccc")
        .style("border-radius", "5px")
        .style("display", "none");

    // Zoom and pan support
    const svg = d3.select("body").append("svg")
        .attr("width", width)
        .attr("height", height)
        .attr("viewBox", [0, 0, width, height])
        .attr("style", "max-width: 100%; height: auto;")
        .call(d3.zoom().on("zoom", (event) => svg.attr("transform", event.transform)))
        .append("g");

    // Link elements
    const link = svg.append("g")
        .attr("stroke", "#999")
        .attr("stroke-opacity", 0.6)
        .selectAll("line")
        .data(links)
        .join("line")
        .attr("stroke-width", d => Math.sqrt(d.value));

    // Node elements with dynamic size
    const node = svg.append("g")
        .attr("stroke", "#fff")
        .attr("stroke-width", 1.5)
        .selectAll("circle")
        .data(nodes)
        .join("circle")
        .attr("r", d => nodeSizeScale(links.filter(link => link.source === d.id || link.target === d.id).length))
        .attr("fill", d => color(d.group))
        .on("mouseover", (event, d) => {
            tooltip.style("display", "block").html(`Node: ${d.id}<br>Group: ${d.group}`);
        })
        .on("mousemove", (event) => {
            tooltip.style("left", (event.pageX + 10) + "px").style("top", (event.pageY - 20) + "px");
        })
        .on("mouseout", () => tooltip.style("display", "none"))
        .on("click", (event, d) => {
            // Highlight connections on click
            const relatedNodes = new Set(links
                .filter(link => link.source === d.id || link.target === d.id)
                .flatMap(link => [link.source, link.target]));
            node.attr("fill", n => relatedNodes.has(n.id) ? color(n.group) : "#ccc");
            link.attr("stroke", l => l.source === d.id || l.target === d.id ? "#333" : "#999");
        });

    // Node labels
    const label = svg.append("g")
        .attr("class", "labels")
        .selectAll("text")
        .data(nodes)
        .join("text")
        .attr("dy", -10)
        .attr("text-anchor", "middle")
        .attr("font-size", 10)
        .text(d => d.id);

    // Drag behavior for nodes
    node.call(d3.drag()
        .on("start", dragstarted)
        .on("drag", dragged)
        .on("end", dragended));

    // Update position of nodes and links at each tick
    function ticked() {
        link
            .attr("x1", d => d.source.x)
            .attr("y1", d => d.source.y)
            .attr("x2", d => d.target.x)
            .attr("y2", d => d.target.y);

        node
            .attr("cx", d => d.x)
            .attr("cy", d => d.y);

        label
            .attr("x", d => d.x)
            .attr("y", d => d.y);
    }

    // Drag functions
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

    // Run the simulation
    const simulation = d3.forceSimulation(nodes)
        .force("link", d3.forceLink(links).id(d => d.id))
        .force("charge", d3.forceManyBody())
        .force("center", d3.forceCenter(width / 2, height / 2))
        .on("tick", ticked);

}).catch(error => console.error("Error loading data:", error));
