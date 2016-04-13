(function () {
	var pages = {
			"genesis.html": "The genesis of the project",
			"about.html": "About Us",
			"team.html": "The Team",
			"index.html": "Traversing Art Through Its Connections"
	}
	
    angular.module('neo4art', ['ngRoute'])
	.config(['$routeProvider',function($routeProvider){
        $routeProvider.when('/', {
            templateUrl: 'views/home.html'
        })
        .when('/about/',{
           templateUrl: 'views/about.html' 
        })
        .when('/genesis/',{
           templateUrl: 'views/genesis.html' 
        })
        .when('/team/',{
           templateUrl: 'views/team.html' 
        });
        $routeProvider.otherwise({
            redirectTo: '/'
        });
    }])
	
	.controller('menuController', ['$scope','$location','rememberService',function($scope, $location, rememberService){
		$scope.isActive = function(route){
			return rememberService.getActualPage() === route;
		};
	}])
	.controller('MainController', function(pageService){
		this.page = pages;
		this.title = pageService.getTitle();
	})
	
	.factory('rememberService', function($location) {
	    return {
	        getActualPage: function(){
	        	var arr = $location.$$absUrl.split("/");
	        	var pageName = arr[arr.length -1];
                console.log(arr);
                console.log(pageName);
	        	return pageName;
	        }
	    };
	})
	.factory('pageService', function(rememberService) {
	    return {
	        getTitle: function(){
	        	return "neo4Art - "+ pages[rememberService.getActualPage()];
	        }
	    };
	});

})();
