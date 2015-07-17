(function() {
	var app = angular.module('neo4art', []);
	
	var pages = {
			"genesis.html": "The genesis of the project",
			"about.html": "About Us",
			"team.html": "The Team",
			"index.html": "Traversing Art Through Its Connections"
	}

	app.directive("header", function() {
		return {
			restrict : 'E',
			templateUrl : 'header.html'
		};
	});
	app.directive("footer", function() {
		return {
			restrict : 'E',
			templateUrl : 'footer.html'
		};
	});
	
	app.controller('menuController', function($scope, $location, rememberService){
		$scope.isActive = function(route){
			return rememberService.getActualPage() === route;
		};
	});
	app.controller('MainController', function(pageService){
		this.page = pages;
		this.title = pageService.getTitle();
	});
	
	app.factory('rememberService', function($location) {
	    return {
	        getActualPage: function(){
	        	var arr = $location.$$absUrl.split("/");
	        	var pageName = arr[arr.length -1];
	        	return pageName;
	        }
	    };
	});
	app.factory('pageService', function(rememberService) {
	    return {
	        getTitle: function(){
	        	return "neo4Art - "+ pages[rememberService.getActualPage()];
	        }
	    };
	});

})();
