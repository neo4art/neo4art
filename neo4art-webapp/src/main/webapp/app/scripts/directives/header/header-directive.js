angular.module('neo4art')
    .directive('header', [ /*'rememberService', */ function ( /*rememberService*/ ) {
        return {
            restrict: 'E',
            templateUrl: 'scripts/directives/header/index.html'
                /*,
                            link: function ($scope, $element, $attrs) {
                                $scope.pageTitle = rememberService.getActualPage();
                                $scope.isCurrentPage = function (page) {
                                    return true;
                                };
                            }*/
        };
	}]);