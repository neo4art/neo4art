'use strict';

angular.module('neo4art').config(['$routeProvider', function ($routeProvider) {
    $routeProvider.when('/', {
            templateUrl: 'views/main.html'
        })
        .when('/about/', {
            templateUrl: 'views/about.html'
        })
        .when('/genesis/', {
            templateUrl: 'views/genesis.html'
        })
        .when('/team/', {
            templateUrl: 'views/team.html'
        });
    $routeProvider.otherwise({
        redirectTo: '/'
    });
}]);