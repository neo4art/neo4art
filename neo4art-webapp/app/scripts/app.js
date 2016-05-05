'use strict';

/**
 * @ngdoc overview
 * @name neo4Art
 * @description
 * # neo4Art
 *
 * Main module of the application.
 */
angular
    .module('neo4art', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngMaterial',
    'd3'
  ])
    .config(function ($mdIconProvider) {
        $mdIconProvider.defaultIconSet('images/mdi.svg');
    });