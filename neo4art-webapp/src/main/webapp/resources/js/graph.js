/** Base radius of each node */
var radius = 25;
/** When a node is clicked I keep a reference to it */
var selectedNode;
/** I keep a global reference of the zoom */
var currentZoom = 1;
/** Global reference to the actual translation */
var currentTranslateX = 0;
/** Global reference to the actual translation */
var currentTranslateY = 0;

/**
 * I call this after the document is ready because I need to know at least the
 * dimensions of the document
 */
$(document).ready(function() {
	
	p = parseURLParams();
	
	if (p != undefined) {
		graph = new theGraph();
	}
	else {
		console.log("non faccio niente");
	}
	
	$("#dialog").dialog({
		autoOpen : false,
		title : "Legend",
		width : 500,
		resizable : false
	});
	
	$("#legendOpener").click(function() {
		$("#dialog").dialog("open");
	});
	
	$(".container-page").scrollbar();
});

function wantsTimeline() {
	p = parseURLParams();
	if (p != undefined) {
		window.location.href = "timeline.html?query=" + p.query;
	} else {
		window.location.href = "timeline.html";
	}
}

function parseURLParams() {
	var url = window.location.search;
	var queryStart = url.indexOf("?") + 1, queryEnd = url.indexOf("#") + 1 || url.length + 1, query = url.slice(queryStart, queryEnd - 1), pairs = query
	/* .replace(/\+/g, " ") */.split("&"), parms = {}, i, n, v, nv;

	if (query === url || query === "") {
		return;
	}

	for (i = 0; i < pairs.length; i++) {
		nv = pairs[i].split("=");
		n = decodeURIComponent(nv[0]);
		v = decodeURIComponent(nv[1]);

		if (!parms.hasOwnProperty(n)) {
			parms[n] = [];
		}

		parms[n].push(nv.length === 2 ? v : null);
	}
	return parms;
}

function legend() {
	alert("")
}

function theGraph() {
	
	var selectedNode, selectedD;
	generateWindow();
	firstUpdate();

	addNode = function(nodeToAdd) {
		nodeList.push({
			"id" : nodeToAdd.id,
			"name" : nodeToAdd.name,
			"group" : nodeToAdd.group,
			"thumbnail" : nodeToAdd.thumbnail,
			"type" : nodeToAdd.type,
			"link" : nodeToAdd.link,
			"description" : nodeToAdd.description
		});
	}
	
	addLink = function(linkToAdd) {
		var sourceNode = findNode(linkToAdd.source);
		var targetNode = findNode(linkToAdd.target);
		if ((sourceNode !== undefined) && (targetNode !== undefined)) {
			linkList.push({
				"source" : linkToAdd.source,
				"target" : linkToAdd.target,
				"value" : linkToAdd.value,
				"linkName" : linkToAdd.linkName
			});
			update();
		}
	}

	this.removeNode = function(id) {
		var i = 0;
		var n = findNode(id);
		while (i < linkList.length) {
			if ((linkList[i]['source'] === n) || (linkList[i]['target'] == n))
				linkList.splice(i, 1);
			else
				i++;
		}
		var index = findNodeIndex(id);
		if (index !== undefined) {
			nodeList.splice(index, 1);
			update();
		}
	}

	var findNode = function(id) {
		for (var i = 0; i < nodeList.length; i++) {
			if (nodeList[i].id === id)
				return nodeList[i]
		}
	}

	var findNodeIndex = function(id) {
		for (var i = 0; i < nodeList.length; i++) {
			if (nodeList[i].id === id)
				return i
		}
	}
	
	clearDiv("graph");
	
	var width = $("#graph").width();
	var height = $("#graph").height();

	var color = d3.scale.category20();
	var force = d3.layout.force().charge(-300).linkDistance(150).size([ width, height ]);
	var zoom = d3.behavior.zoom().on("zoom", redraw);
	var drag = force.drag().on("dragstart", dragstarted);
	var svg = d3.select("#graph").append("svg").attr("class", "svg").append("g").call(zoom).on("dblclick.zoom", null);
	var rect = svg.append("rect").attr("width", "100%").attr("height", "100%").style("fill", "none").style("pointer-events", "all");

	svg.append("text").attr("x", 10).attr("y", 20).attr("font-family", "sans-serif").attr("font-size", "15px").attr("fill", "#333333").text("Right Click to show details");

	/* Contenitore del grafo */
	var container = svg.append("g").attr("id", "container");

	var nodeList = [];
	var linkList = [];

	var nodeEnter, linkEnter;
	
	function firstUpdate(json) {

		console.log("FIRST UPDATE");
		
		$.ajax({
			method : 'get',
			url : encodeURI("http://5.9.211.195/neo4art-services/api/search/test/wikipedia/" + p.query.toString()),
			dataType : 'json',
			success : function(graph) {
				console.log("GRAPH");
				console.log(graph);
				console.log("START GRAPH");
				force.nodes(graph.nodes).links(graph.relationships).start();
				nodeList = force.nodes();
				console.log("NODES");
				console.log(nodeList);
				linkList = force.links();
				console.log("RELS");
				console.log(linkList);
				console.log("UPDATE");
				update();
				console.log("UPDATE DONE");
			}
		});
	}
	
	function update() {
		
		console.log("UPDATE IN");
		
		clearDiv("container");

		function connects(l, n) {
			if (l.source.id == n.id || l.target.id == n.id) {
				return true;
			}
			return false;
		}

		var link = container.append("g").attr("class", "links").selectAll(".link").data(linkList, function(d) {
			return d.source.name + "-" + d.target.name;
		});
		
		linkEnter = link.enter().append("line").attr("class", "link").style("stroke-width", function(d) {
			return Math.sqrt(d.value) * 3;
		}).on("mouseover", function(d) {
			d3.select(this).classed("link-active-green", true);
			node.classed("node-active-green", function(o) {
				thisOpacity = connects(d, o) ? true : false;
				return thisOpacity;
			});
		}).on("mouseout", function(d) {
			node.classed("node-active-green", false);
			link.classed("link-active-green", false);
		});
		
		linkEnter.append("title").text(function(d) {
			return d.linkName
		});
		
		link.exit().remove();

		// ########################################
		
		var nodeContainer = container.append("g").attr("class", "nodes");
		
		var node = nodeContainer.selectAll(".node").data(nodeList, function(d) {
			return d.name;
		});
		
		nodeEnter = node.enter().append("g").attr("class", "node").attr("id", function(d) {
			d.radius = radius;
			return d.id; // TODO CHECK
		}).call(drag);

		nodeEnter.append("circle").attr("r", function(d) {
			return d.radius;
		}).style("fill", function(d) {
			type = d.type;
			if (d.type == "Colour") {
				return "#" + d.thumbnail;
			} else {
				return color(1 / d.id);
			}
		});
		
		nodeEnter.append("clipPath").attr('id', function(d, i) {
			return "clip" + i
		}).append("circle").attr("class", "clip-path").attr("r", function(d) {
			return d.radius;
		});

		nodeEnter.append("clipPath").attr('id', function(d, i) {
			return "clip" + i
		}).append("circle").attr("class", "clip-path").attr("r", function(d) {
			return d.radius;
		}).style("fill", function(d) {
			return color(1 / d.rating);
		});
		
		var img = nodeEnter.append("svg:image").attr("class", "circle").attr("xlink:href", function(d) {
			if (d.type != "Colour") {
				return d.thumbnail;
			}
		}).attr("clip-path", function(d, i) {
			return "url(#clip" + i + ")"
		}).attr("x", function(d) {
			return -d.radius;
		}).attr("y", function(d) {
			return -d.radius;
		}).attr("width", function(d) {
			return d.radius * 2;
		}).attr("height", function(d) {
			return d.radius * 2;
		}).attr("preserveAspectRatio", "xMidYMid slice");
		nodeEnter.append("title").text(function(d) {
			return d.name;
		});

		var textInside = nodeEnter.append("text").attr("class", "text-inside-node").style("text-anchor", "middle").attr("x", 0)
				.attr("y", 2).text(function(d) {
					if (d.type == "Word") {
						return d.name;
					} else {
						return "";
					}
				}).attr("clip-path", function(d, i) {
					return "url(#clip" + i + ")"
				}).style("font-size", "10px");

		var linkedByIndex = {};
		linkList.forEach(function(d) {
			linkedByIndex[d.source.index + "," + d.target.index] = 1;
		});

		function isConnected(a, b) {
			return linkedByIndex[a.index + "," + b.index] || linkedByIndex[b.index + "," + a.index];
		}

		nodeEnter.on(
				"mouseover",
				function(d) {
					if (!d.clicked) {
						node.classed("node-active", function(o) {
							thisOpacity = isConnected(d, o) ? true : false;
							this.setAttribute('fill-opacity', thisOpacity);
							return thisOpacity;
						});

						link.classed("link-active", function(o) {
							return o.source === d || o.target === d ? true : false;
						});

						d3.select(this).classed("node-active", true);
						d3.select(this).selectAll("circle").transition().duration(750).attr("r", function() {
							if (d3.select(this).classed("clip-path"))
								return d.radius * 1.5;
							else
								return (d.radius /* + 2 */) * 1.5
						});
						d3.select(this).select("image").transition().duration(750).attr("x", -d.radius * 1.5).attr("y", -d.radius * 1.5)
								.attr("width", (d.radius * 2) * 1.5).attr("height", (d.radius * 2) * 1.5);
						d3.select(this).select("text").transition().duration(750).style("font-size", "20px").attr("y", 4);
					}
				}).on(
				"mouseout",
				function(d) {
					if (!d.clicked) {
						node.classed("node-active", false);
						link.classed("link-active", false);

						d3.select(this).selectAll("circle").transition().duration(750).attr("r", function() {
							if (d3.select(this).classed("clip-path"))
								return d.radius;
							else
								return d.radius;// + 2
						});
						d3.select(this).select("image").transition().duration(750).attr("x", -d.radius).attr("y", -d.radius).attr("width",
								d.radius * 2).attr("height", d.radius * 2);
						d3.select(this).select("text").transition().duration(750).style("font-size", "10px").attr("y", 2);
					}
				});

		nodeEnter
				.on(
						"contextmenu",
						function(d) {
							closeWindow();
							// PUTS THE NODE IN ORDER SO AS THE
							// CLICKED IS ON TOP
							nodeContainer.selectAll("g").sort(function(a, b) {
								var i = (a.id != d.id) ? -1 : 1;
								return i;
							});
							openWindow(d);
							selectedNode = d3.select(this);
							selectedD = d;
							d.clicked = true;
							node.classed("node-clicked", false);
							link.classed("link-clicked", false);
							d3.select(this).selectAll("circle").transition().duration(750).attr("r", function() {
								if (d3.select(this).classed("clip-path"))
									return d.radius * 5 / currentZoom;
								else
									return d.radius * 5 / currentZoom;// + 2
							});
							d3.select(this).select("image").transition().duration(750).attr("x", -d.radius * 5 / currentZoom).attr("y",
									-d.radius * 5 / currentZoom).attr("width", d.radius * 2 * 5 / currentZoom).attr("height",
									d.radius * 2 * 5 / currentZoom);
							d3.select(this).select("text").transition().duration(750).style("font-size", "50px").attr("y", 15);
							console.log("w:" + width + " d.x:" + d.x + " ctx:" + currentTranslateX + " calc:"
									+ ((width / 4 / currentZoom) - d.x + (currentTranslateX / currentZoom)) + " z:" + currentZoom);
							container
									.transition()
									.duration(750)
									.attr(
											"transform",
											"translate("
													+ (currentTranslateX + ((((width / 4) / currentZoom) - (d.x + (currentTranslateX / currentZoom))) * currentZoom))
													+ ","
													+ (currentTranslateY + ((((height / 2) / currentZoom) - (d.y + (currentTranslateY / currentZoom))) * currentZoom))
													+ ")" + " scale(" + currentZoom + ")");
							d3.event.preventDefault();
						});
		nodeEnter.on("dblclick", function(d) {
			$.ajax({
				method : 'get',
				url : window.location.protocol + '//' + window.location.host
						+ "/neo4art-services/api/services/search/node-explode.json?nodeId=" + d.id,
				dataType : 'json',
				success : function(graphExp) {
					// d3.json("explode.json", function(error, graphExp) {
					$.each(graphExp.nodeList, function(i) {
						addNode(this);
					});
					update();
					$.each(graphExp.linkList, function(i) {
						addLink(this);
					});
				}
			});
		});

		node.exit().remove();
		// ##########################################

		force.on("tick", function() {
			link.attr("x1", function(d) {
				return d.source.x;
			}).attr("y1", function(d) {
				return d.source.y;
			}).attr("x2", function(d) {
				return d.target.x;
			}).attr("y2", function(d) {
				return d.target.y;
			});

			node.attr("transform", function(d) {
				return "translate(" + d.x + "," + d.y + ")";
			});
		});

		// Restart the force layout.
		force.start();
	}
	
	function redraw() {
		currentZoom = d3.event.scale;
		currentTranslateX = d3.event.translate[0];
		currentTranslateY = d3.event.translate[1];
		container.attr("transform", "translate(" + d3.event.translate + ")" + " scale(" + d3.event.scale + ")");
		force.resume();
	}

	function dragstarted(d) {
		d3.event.sourceEvent.stopPropagation();
		d3.select(this).classed("dragging", true);
	}

	function generateWindow() {
		clearDiv("floating");
		var float = d3.select("#floating");
		$("#floating").resizable({
			handles : "w"
		});
		var bttX = float.append("div").attr("class", "ics").text("X");
		bttX.on("click", closeWindow);
		float.append("div").attr("id", "data");
		float.append("iframe").attr("width", "100%");
	}

	function openWindow(d) {
		
		clearDiv("data");
		
		var float = d3.select("#floating").attr("class", "visible").attr("style", null);
		var data = float.select("#data");
		// data.append("div").attr("class", "title").append("h1").text(d.name);
		if (d.type != "Colour") {
			data.append("div").attr("class", "thumbnail").append("img").attr("src", d.thumbnail);
		}
		// data.append("div").attr("class", "description").text(d.description);
		// data.append("div").attr("class", "link").html("</br><a href='" +
		// d.link + "'>" + d.link + "</a>");
		var frame = float.select("iframe");
		frame.attr("src", d.link);
//		$("iframe").scrollbar();
	}

	function closeWindow() {
		
		d3.select("#floating").attr("class", null).style("left", null);
		
		container.transition().duration(750).attr("transform", "translate(" + currentTranslateX + "," + currentTranslateY + ")" + " scale(" + currentZoom + ")");
		nodeEnter.classed("node-active", false);
		linkEnter.classed("link-active", false);

		container.selectAll("circle").transition().duration(750).attr("r", function() {
			if (container.classed("clip-path"))
				return radius;
			else
				return radius;// + 2
		});
		
		if (selectedD != undefined) {
			selectedD.clicked = false;
		}
		
		if (selectedNode != undefined) {
			selectedNode.select("image").transition().duration(750).attr("x", -radius).attr("y", -radius).attr("width", radius * 2).attr(
					"height", radius * 2);
			selectedNode.select("text").transition().duration(750).style("font-size", "10px").attr("y", 2);
		}
	}
}

/**
 * This method remove all other components inside the specified ID
 * 
 * @param elementID
 *            The id of the div
 */
function clearDiv(elementID) {
	document.getElementById(elementID).innerHTML = "";
}
