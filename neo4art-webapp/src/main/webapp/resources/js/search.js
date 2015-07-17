//$(window).load(function(d) {
//	var search = new Search();
//	// search.initialize();
//});
angular.element(window).load(function() {
	var search = new Search();
});

function Search() {

	var addNode = function(nodeToAdd) {
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
	var addLink = function(linkToAdd) {
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

	var nodeList = [];
	var linkList = [];
	$(".container-svg").html("");
	var width = $(".container-svg").width();
	var height = $(".container-svg").height();
	console.log("width:" + width + " - height:"+ height);	
	var container = d3.select(".container-svg").append("g").attr("id", "container");
	var force = d3.layout.force().charge(-2500).linkDistance(60).linkStrength(0.1).size([ width, height ]);
	var color = d3.scale.category20();
	function initialize() {
		force.nodes([ {
			id : 0,
			radius : 90,
			name : "Search",
			thumbnail : "",// "resources/img/center-node.jpg",
			type : "Word"
		} ]).links([]).start();
		nodeList = force.nodes();
		linkList = force.links();
		update();
	}
	var levelOneNodes = [ {
		id : 1,
		radius : 60,
		name : "Colours",
		thumbnail : "",// "resources/img/colours-node.jpg",
		type : ""
	}, {
		id : 2,
		radius : 60,
		name : "Artists",
		thumbnail : "",// "resources/img/artists-node.jpg",
		type : ""
	}, {
		id : 3,
		radius : 60,
		name : "Museums",
		thumbnail : "",// "resources/img/museums-node.jpg",
		type : ""
	}, {
		id : 4,
		radius : 60,
		name : "Emotions",
		thumbnail : "",// "resources/img/emotions-node.jpg",
		type : ""
	}, {
		id : 5,
		radius : 60,
		name : "Artworks",
		thumbnail : "",// "resources/img/museums-node.jpg",
		type : ""
	} ];

	var levelOneLinks = [ {
		source : 1,
		target : 0,
		value : 3,
		linkName : null
	}, {
		source : 2,
		target : 0,
		value : 3,
		linkName : null
	}, {
		source : 3,
		target : 0,
		value : 3,
		linkName : null
	}, {
		source : 4,
		target : 0,
		value : 3,
		linkName : null
	}, {
		source : 5,
		target : 0,
		value : 3,
		linkName : null
	} ];

	function update() {
		$("#container").html("");

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
		});
		// linkEnter.append("title").text(function(d) {
		// return d.linkName
		// });
		link.exit().remove();

		// ########################################
		var nodeContainer = container.append("g").attr("class", "nodes");
		var node = nodeContainer.selectAll(".node").data(nodeList, function(d) {
			return d.name;
		});
		nodeEnter = node.enter().append("g").attr("class", "node").attr("id", function(d) {
			if (d.radius == undefined) {
				d.radius = 60;
			}
			return d.id; // TODO CHECK
		})/* .call(drag) */;

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
				.attr("y", 11).text(function(d) {
					if (d.type == "Word") {
						return d.name;
					} else {
						return "";
					}
				}).attr("clip-path", function(d, i) {
					return "url(#clip" + i + ")"
				});//.style("font-size", "30px");

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
						d3.select(this).select("text").transition().duration(750).style("font-size", "60px").attr("y", 15);
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
						d3.select(this).select("text").transition().duration(750).style("font-size", "30px").attr("y",11);
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
		nodeEnter.on("click", function(d) {
			// $.ajax({
			// method : 'get',
			// url : window.location.protocol + '//' + window.location.host
			// +
			// "/neo4art-services/api/services/search/node-explode.json?nodeId="
			// + d.id,
			// dataType : 'json',
			// success : function(graphExp) {
			// d3.json("explode.json", function(error, graphExp) {
			// $.each(graphExp.nodeList, function(i) {
			// addNode(this);
			// });
			// update();
			// $.each(graphExp.linkList, function(i) {
			// addLink(this);
			// });
			// }
			// });
			if (d.name == "Search" && d.once !== true) {
				$.each(levelOneNodes, function(i) {
					addNode(this);
				});
				update();
				$.each(levelOneLinks, function(i) {
					console.log(this);
					addLink(this);
				});
				d.once = true;
			}
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
	initialize();
}