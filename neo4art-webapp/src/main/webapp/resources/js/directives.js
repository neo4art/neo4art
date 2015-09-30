angular.module('neo4art')
    .directive("header", ['rememberService',function(rememberService) {
		return {
			restrict : 'E',
			templateUrl : 'directives/header.html',
            link: function($scope, $element, $attrs){
                $scope.pageTitle = rememberService.getActualPage();
                $scope.isCurrentPage = function(page){
                    return true;  
                };
            }
		};
	}])
	.directive("footer", [function() {
		return {
			restrict : 'E',
			templateUrl : 'directives/footer.html'
		};
	}]);