'use strict';

angular.module('myApp.incarichi', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/incarichi', {
    templateUrl: 'incarichi/incarichi.html',
    controller: 'incarichiCtrl'
  });
}])

.controller('incarichiCtrl', function($scope,$window) {
  $scope.goBack = function() {
    $window.history.back();
  };
});
