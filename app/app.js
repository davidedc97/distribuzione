'use strict';
// Declare global variables accessible from all controllers
var ruolo = "nazionale";
const ruoli = ["nazionale", "nazionale-provinciale", "provinciale"];

// Declare app level module which depends on views, and core components
var myApp = angular.module('myApp', [
  'ngRoute',
  'ngMaterial',
  'ngMessages',
  'ngWebSocket',
  'myApp.login',
  'myApp.contesti',
  'myApp.aggiungi-anagrafica',
  'myApp.home',
  'myApp.anagrafica',
  'myApp.modifica-anagrafica',
  'myApp.personale',
  'myApp.aggiungi-personale',
  'myApp.modifica-personale',
  'myApp.corsi',
  'myApp.aggiungi-corso',
  'myApp.gestisci-corso',
  'myApp.visualizza-corso',
  'myApp.aule',
  'myApp.aggiungi-aula',
  'myApp.modifica-aula',
  'myApp.riepilogo',
  'myApp.factoryModule',
  'myApp.excelModule',
  'myApp.wordModule'
])
.config(['$locationProvider', '$routeProvider','$compileProvider', function($locationProvider, $routeProvider, $compileProvider) {
  $locationProvider.hashPrefix('!');
  $compileProvider.imgSrcSanitizationWhitelist(/^\s*(https?|data):/);
  $compileProvider.aHrefSanitizationWhitelist(/^\s*(https?|data):/);
  $routeProvider.otherwise({redirectTo: '/login'});
}]);

myApp.factory('globalService', function() {
  return {
    labelCampi: {
      lista_anagrafiche: "Lista Anagrafiche",
      anagrafica: "Anagrafica",
      personale: "Personale",
      corsi: "Corsi",
      aule: "Aule",
      riepilogo: "Riepilogo"
    },

    getMenuLabel: function() {
      let url = window.location.href
      let label = url.split("#!/")[1];
      // se ci sono più parametri nella route, prendo il primo
      let res = label.split("/")[0];
      res = res[0].toUpperCase() + res.slice(1);
      if(res.includes("-")) res = res.replace("-", " ");
      return res;
    },
    isAdmin: ruoli.includes(ruolo)

  };
});

myApp.controller('MainCtrl', ['$scope', 'globalService', function($scope, globalService) {
  $scope.getMenuLabel = function() {
    return globalService.getMenuLabel();
  };

  $scope.labelCampi = globalService.labelCampi;
  $scope.isAdmin = globalService.isAdmin;

}]);
