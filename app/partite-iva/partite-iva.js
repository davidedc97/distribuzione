'use strict';

angular.module('myApp.partite-iva', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/partite-iva', {
    templateUrl: 'partite-iva/partite-iva.html',
    controller: 'partite-ivaCtrl'
  });
}])

.controller('partite-ivaCtrl', function($scope,$window) {
  $scope.goBack = function() {
    $window.history.back();
  };
});

