'use strict';

angular.module('neo4art')
    .controller('HomeCtrl', ['d3Service', function (d3Service) {
            var self = this;
            /*self.d3 = d3Service.d3().then(function (d3) {
                self.d3 = d3;
                self.drag = d3.behavior.drag()
                    .origin(function (d) {
                        debugger;
                        return d;
                    })
                    .on("dragstart", dragstarted)
                    .on("drag", dragged)
                    .on("dragend", dragended);

                function dragstarted(d) {
                    d3.event.sourceEvent.stopPropagation();
                    d3.select(this).classed("dragging", true);
                    console.log("ciao");
                }

                function dragged(d) {
                    d3.select(this).attr("cx", d.x = d3.event.x).attr("cy", d.y = d3.event.y);
                }

                function dragended(d) {
                    d3.select(this).classed("dragging", false);
                }
            }); //.then(function (d3) {*/

            /*self.addNode = function () {
                //                d3Service.d3().then(function (d3) {
                var drag = d3.behavior.drag();

                var mainSvg = d3.select('.main-svg').append('g');
                var cx = mainSvg.style('width').replace('px', '') / 2;
                var cy = mainSvg.style('height').replace('px', '') / 2;

                drawNode(mainSvg, cx, cy, drag);
                //                });

                d3.select("body").append("svg")
                    .attr("width", 500)
                    .attr("height", 500)
                    .append("g")
                    .attr("transform", "translate(" + 5 + "," + 5 + ")")
                    .call(zoom)
                ;
            };
            var drawNode = function (mainSvg, cx, cy, drag) {
                var circle = mainSvg.append('circle');
                circle.call(drag);
                circle.attr('cx', cx)
                    .attr('cy', cy)
                    .attr('r', 35)
                    .style('fill', 'purple');
            };

            //});*/

            var i = 0;

            var zoom = d3.behavior.zoom()
                .scaleExtent([-10, 10])
                .on("zoom", zoomed);

            var drag = d3.behavior.drag()
                .on("dragstart", function (d) {
                    d3.event.sourceEvent.stopPropagation();
                    d3.select(this).classed("dragging", true);
                });

            var mainSvg = d3.select('.main-svg');

            var svg = mainSvg.append("g")
                .style('width', '100%')
                .style('height', '100%')
                .call(zoom);

            var width = mainSvg.style('width').replace('px', '');
            var height = mainSvg.style('height').replace('px', '');
            var cx = width / 2;
            var cy = height / 2;

            /***  BACKGROUND  ***/
            var rect = svg.append("rect")
                .attr("width", width)
                .attr("height", height)
                .style("fill", "none")
                .style("pointer-events", "all");

            var container = svg.append("g");

            /*container.append("g").append('circle').attr('cx', cx)
                .attr('cy', cy)
                .attr('r', 35)
                .style('fill', 'purple').call(drag);*/

            function zoomed() {
                container.attr("transform", "translate(" + d3.event.translate + ")scale(" + d3.event.scale + ")");
            }

            self.addNode = function () {
                container.append('g').append('circle')
                    .attr('cx', (i * 100) + cx)
                    .attr('cy', (i * 100) + cy)
                    .attr('r', 35)
                    .style('fill', 'purple');
                i++;
                //.call(drag);
            };

    }
]);