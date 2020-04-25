'use strict';

angular.module('myApp.conti-correnti', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/conti-correnti', {
    templateUrl: 'conti-correnti/conti-correnti.html',
    controller: 'conti-correntiCtrl'
  });
}])

.controller('conti-correntiCtrl', function($scope,$window) {
  $scope.goBack = function() {
    $window.history.back();
  };
});
