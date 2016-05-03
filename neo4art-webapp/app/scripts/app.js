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
    'ngMaterial'
  ])
    .config(function ($mdIconProvider) {
        $mdIconProvider.defaultIconSet('images/mdi.svg')
    });