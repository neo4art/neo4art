'use strict';

angular.module('neo4art').controller('HomeCtrl', ['d3Service', function (d3Service) {
        d3Service.d3().then(function (d3) {
            console.log(d3.version);
            d3.select('svg').append('ahahah');
        });
    }
]);