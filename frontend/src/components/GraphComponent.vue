<template>
  <div ref="graphContainer"></div>
</template>

<script>
import * as d3 from 'd3';

export default {
  name: 'GraphComponent',
  mounted() {
    this.renderGraph();
  },
  methods: {
    renderGraph() {
      const data = {
        "nodes": [
          {"id": "customer", "group": 3},
          {"id": "customer support chatbot", "group": 2},
          {"id": "chatbot", "group": 3},
          {"id": "engine analyzes user", "group": 2},
          {"id": "engine", "group": 3},
          {"id": "business improve customer", "group": 2},
          {"id": "system", "group": 3}
        ],
        "links": [
          {"source": "customer", "target": "customer support chatbot", "value": 1},
          {"source": "customer support chatbot", "target": "chatbot", "value": 1},
          {"source": "customer support chatbot", "target": "customer", "value": 1},
          {"source": "customer", "target": "engine analyzes user", "value": 1},
          {"source": "engine analyzes user", "target": "engine", "value": 1},
          {"source": "engine analyzes user", "target": "customer", "value": 1},
          {"source": "customer", "target": "business improve customer", "value": 1},
          {"source": "business improve customer", "target": "system", "value": 1},
          {"source": "business improve customer", "target": "customer", "value": 1}
        ]
      };

      const width = 928;
      const height = 600;

      const colorByGroup = {
        1: "#1f77b4",
        2: "#ff7f0e",
        3: "#2ca02c",
        4: "#d62728",
        5: "#9467bd",
      };

      const links = data.links.map(d => ({...d}));
      const nodes = data.nodes.map(d => ({...d}));

      const nodeSizeScale = d3.scaleLinear()
          .domain([0, d3.max(nodes, node => links.filter(link => link.source === node.id || link.target === node.id).length)])
          .range([5, 15]);

      const svg = d3.select(this.$refs.graphContainer).append("svg")
          .attr("width", width)
          .attr("height", height)
          .attr("viewBox", [0, 0, width, height])
          .attr("style", "max-width: 100%; height: auto;")
          .call(d3.zoom().on("zoom", (event) => svg.attr("transform", event.transform)))
          .append("g");

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
          .attr("r", d => nodeSizeScale(links.filter(link => link.source === d.id || link.target === d.id).length))
          .attr("fill", d => colorByGroup[d.group] || "#ccc")
          .on("click", (event, d) => {
            const relatedNodes = new Set(links
                .filter(link => link.source === d.id || link.target === d.id)
                .flatMap(link => [link.source, link.target]));
            node.attr("fill", n => relatedNodes.has(n.id) ? colorByGroup[n.group] : "#ccc");
            link.attr("stroke", l => l.source === d.id || l.target === d.id ? "#333" : "#999");
          });

      const label = svg.append("g")
          .attr("class", "labels")
          .selectAll("text")
          .data(nodes)
          .join("text")
          .attr("dy", -10)
          .attr("text-anchor", "middle")
          .attr("font-size", 14)
          .attr("font-weight", "bold")
          .text(d => d.id);

      node.call(d3.drag()
          .on("start", dragstarted)
          .on("drag", dragged)
          .on("end", dragended));

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

      const simulation = d3.forceSimulation(nodes)
          .force("link", d3.forceLink(links).id(d => d.id).distance(150))
          .force("charge", d3.forceManyBody().strength(-400))
          .force("center", d3.forceCenter(width / 2, height / 2))
          .on("tick", ticked);
    }
  }
};
</script>

<style scoped>
/* Tooltip styling removed since the tooltip is no longer used */
</style>
