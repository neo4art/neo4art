angular.module('neo4art')
    .directive('footer', [function () {
        return {
            restrict: 'E',
            templateUrl: 'scripts/directives/footer/index.html'
        };
	}]);