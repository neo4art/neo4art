// ################################## TIMELINE ##############################à
var timeline;
var bigData;

$(document).ready(function(d) {
	p = parseURLParams();
	if (p != undefined) {
		load(p);
	} else {
		console.log("non faccio niente");
	}
});
function wantsGraph() {
	p = parseURLParams();
	if (p != undefined) {
		window.location.href = "graph.html?query=" + p.query;
	} else {
		window.location.href = "graph.html";
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

function load(p) {
	var parseDate = d3.time.format("%d-%b-%Y %H:%M").parse;
	var serviceUrl = window.location.protocol+'//'+window.location.host+"/neo4art-services/api/services/timeline/colours-analysis.json?searchInput="
			+ p.query.toString().replace(/\+/g, " ");
	$.ajax({
		method : 'get',
		url : window.location.protocol+'//'+window.location.host+"/neo4art-services/api/services/timeline/colours-analysis.json?searchInput="
				+ p.query.toString().replace(/\+/g, " "),
		dataType : 'json',
		success : function(graph) {
			// specify options
			var minData = new Date(graph[0].start);
			minData.setMonth(-1);
			var maxData = new Date(graph[graph.length - 1].start);
			maxData.setMonth(13);
			var options = {
				width : "100%",
				height : "300px",
				editable : false,
				"min" : minData, // lower limit of visible range
				"max" : maxData,
				layout : "box"
			};
			// Instantiate our timeline object.
			timeline = new links.Timeline(document.getElementById('mytimeline'), options);
			// }
			// }
			// d3.json("dati.json", function(error, graph) {
			$.each(graph, function() {
				this.start = parseDate(this.start);
				if (this.end != undefined) {
					this.end = parseDate(this.end);
				}
				if (this.thumbnail != undefined && this.description != undefined) {
					this.content = "<img src='"+this.thumbnail+"' height='50px' width='50px' title='"+this.description+"'/>";
				}
			});
			links.events.addListener(timeline, 'rangechange', onRangeChange);
			links.events.addListener(timeline, 'rangechanged', onRangeChanged);
			// Draw our timeline with the created data and options
			bigData = graph;
			timeline.draw(graph);
			drawColorChart(graph, true);
		},
		error : function(error) {
			console.log(error.status);
		}
	});
}
var actualLeft = null;
function onRangeChange() {
	var range = timeline.getVisibleChartRange();
	if (actualLeft == null) {
		actualLeft = $(".color-chart").position().left;
	}
	var left = timeline.dom.items.frame.style.left;
	$(".color-chart").css("left", (actualLeft + parseFloat(left.substring(0, left.length - 2))));
	$(".emotion-chart").css("left", (actualLeft + parseFloat(left.substring(0, left.length - 2))));
	// vis2.setVisibleChartRange(range.start, range.end);
	// document.getElementById('info').innerHTML += 'rangechanged ' +
	// properties.start + ' - ' + properties.end + '<br>';
}
function onRangeChanged() {
	// $(".color-chart").position().left;
	actualLeft = null;
	drawColorChart(bigData);
}

/**
 * Function used by the two zoom buttons.
 * 
 * @param zoomVal
 *            The value of zoom to be added (both positive or negative).
 */
function zoom(zoomVal) {
	timeline.zoom(zoomVal);
	timeline.trigger("rangechange");
	timeline.trigger("rangechanged");
}

/**
 * Adjust the visible time range such that all events are visible.
 */
function fitAllEventsZoom() {
	timeline.setVisibleChartRangeAuto();
}

/**
 * Function used by the two move buttons to navigate into the timeline
 * 
 * @param moveVal
 *            The value (positive or negative) that is added to the current
 *            position
 */
function move(moveVal) {
	timeline.move(moveVal);
	timeline.trigger("rangechange");
	timeline.trigger("rangechanged");
}

/**
 * Move the visible range such that the current time is located in the center of
 * the timeline.
 */
function moveToCurrentTime() {
	timeline.setVisibleChartRangeNow();
}
// ############################################# END TIMELINE

function drawColorChart(data, parseColors) {
	d3.select(".color-chart").selectAll("*").remove();
	d3.select(".emotion-chart").selectAll("*").remove();
	var firstVisibleMilliseconds = timeline.getVisibleChartRange().start.valueOf();
	var firstDataMilliseconds = timeline.getOptions().min.valueOf();
	var visibleRangeMilliseconds = timeline.getVisibleChartRange().end.valueOf() - timeline.getVisibleChartRange().start.valueOf();
	var containerWidth = d3.select(".color-chart-container").style("width").substr(0,
			d3.select(".color-chart-container").style("width").length - 2) - 1;
	var containerHeight = d3.select(".color-chart-container").style("height").substr(0,
			d3.select(".color-chart-container").style("height").length - 2) - 1;
	var totalRangeMilliseconds = timeline.getOptions().max.valueOf() - timeline.getOptions().min.valueOf();
	var chartWidth = (totalRangeMilliseconds * containerWidth) / visibleRangeMilliseconds;

	var offsetX = ((firstVisibleMilliseconds - firstDataMilliseconds) * chartWidth / totalRangeMilliseconds);
	$(".color-chart").css("left", 2 - offsetX);
	$(".emotion-chart").css("left", 2 - offsetX);

	var margin = {
		top : 20,
		right : 0,
		bottom : 30,
		left : 0
	}, width = chartWidth - margin.left - margin.right, height = containerHeight - margin.top - margin.bottom;

	var parseDate = d3.time.format("%d-%b-%Y %H:%M").parse;

	var x = d3.time.scale().range([ 0, width ]);

	var y = d3.scale.linear().range([ height, 0 ]);

	var xAxis = d3.svg.axis().scale(x).orient("bottom");

	var yAxis = d3.svg.axis().scale(y).orient("left");

	var line = d3.svg.line().x(function(d) {
		return x(d.start);
	}).y(function(d) {
		return y(d.averageRgb);
	});
	function xx(e) {
		return x(e.start);
	}
	function xxt(e) {
		return "translate(" + x(e.start) + ",0)";
	}
	function yy(e) {
		return y(e.averageRgb);
	}
	function emotion(e) {
		var txt;
		if (e.emotion == "frown") {
			txt = "\uf119";
		}
		if (e.emotion == "meh") {
			txt = "\uf11A";
		}
		if (e.emotion == "smile") {
			txt = "\uf118";
		}
		return txt;
	}
	function showData(obj, d) {
		var coord = d3.mouse(obj);
		var infobox = d3.select(".infobox");
		// now we just position the infobox roughly where our mouse is
		infobox.style("left", (coord[0] - 40) + "px");
		infobox.style("top", (coord[1] - 220) + "px");
		$(".infobox").html("&nbsp;&nbsp;");
		$(".infobox").show();
		$(".infobox").css("background-color", "#" + d.toString(16));
	}
	function hideData() {
		$(".infobox").hide();
	}
	var svg = d3.select(".color-chart").append("svg").attr("width", width + margin.left + margin.right).attr("height",
			height + margin.top + margin.bottom).append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");
	var emo = d3.select(".emotion-chart").append("svg").attr("width", width + margin.left + margin.right).attr("height",
			height + margin.top + margin.bottom).append("g").attr("transform", "translate(" + margin.left + "," + margin.top + ")");

	// d3.json("dati.json", function(error, data) {
	var monthNames = [ "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", "Jan" ];
	var dateMin = timeline.getOptions().min;
	var dateMax = timeline.getOptions().max;
	var minMin = (dateMin.getMinutes() < 10) ? ("0" + dateMin.getMinutes()) : dateMin.getMinutes();
	var minMax = (dateMax.getMinutes() < 10) ? ("0" + dateMax.getMinutes()) : dateMax.getMinutes();

	if (parseColors) {
		data.push({
			start : dateMax.getDate() + "-" + monthNames[dateMax.getMonth() - 1] + "-" + dateMax.getFullYear() + " " + dateMax.getHours()
					+ ":" + minMax,
			content : "",
			averageRgb : "0"
		});
		data.splice(0, 0, {
			start : dateMin.getDate() + "-" + monthNames[dateMin.getMonth() - 1] + "-" + dateMin.getFullYear() + " " + dateMin.getHours()
					+ ":" + minMin,
			content : "",
			averageRgb : "FFFFFF"
		});
	}
	$.each(data, function(i, d) {
		if (typeof d.start == "string") {
			d.start = parseDate(d.start);
		}
		if (parseColors) {
			d.averageRgb = parseInt(d.averageRgb, 16);
		}
	});

	x.domain(d3.extent(data, function(d) {
		return d.start;
	}));
	y.domain(d3.extent(data, function(d) {
		return d.averageRgb;
	}));
	var emog = emo.selectAll("g").data(data).enter().append("g").attr("transform", xxt);
	emog.append("circle").attr("fill", "#FFFFFF").attr("r", 15);// .attr("cx",
	// xx);
	emog.append("text").text(emotion).attr("class", "icon-text").attr("x", -13).attr("y", 11).attr("fill", "#444444");
	svg.append("g").attr("class", "x axis").attr("transform", "translate(0," + height + ")").call(xAxis);
	// svg.append("g").attr("class", "y
	// axis").call(yAxis).append("text").attr("transform",
	// "rotate(-90)").attr("y", 6)
	// .attr("dy", ".71em").style("text-anchor", "end").text("Price ($)");
	svg.append("path").datum(data).attr("class", "line").attr("d", line);
	svg.selectAll("circle").data(data).enter().append("circle").attr("fill", function(d) {
		return "#" + d.averageRgb.toString(16);
	}).attr("r", 10).attr("cx", xx).attr("cy", yy).on("mouseover", function(d) {
		showData(this, d.averageRgb);
	}).on("mouseout", function() {
		hideData();
	}).attr("class", "pallino");
	$(".color-chart").append("<div class='infobox' style='display:none;'>Test</div>");

	// });
}
