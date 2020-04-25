'use strict';

angular.module('myApp.riepilogo', ['ngRoute', 'myApp.excelModule'])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/riepilogo', {
    templateUrl: 'riepilogo/riepilogo.html',
    controller: 'riepilogoCtrl'
  });
}])

.controller('riepilogoCtrl', function($scope, $window, factoryExcel) {
  $scope.ruoloUtente = ruolo;

  $scope.labelCampi = {
    riepilogo: "Riepilogo",
    esportaBtn: "Esporta"
  };

  $scope.riepilogoPersonale = [
    {
      nome: "Gianfranco",
      cognome: "Preziosi",
      lavGen: 60,
      formGen: 16,
      lavFeb: 64,
      formFeb: 24,
      lavMar: 60,
      formMar: 20,
      lavApr: 52,
      formApr: 20,
      lavMag: 56,
      formMag: 20,
      lavGiu: 52,
      formGiu: 24,
      lavLug: 76,
      formLug: 32,
      lavAgo: 28,
      formAgo: 0,
      lavSet: 52,
      formSet: 0,
      lavOtt: 56,
      formOtt: 20,
      lavNov: 0,
      formNov: 0,
      lavDic: 0,
      formDic: 0,
      lavTot: 556,
      formTot: 176,
      percTot: 31.66,
      costoOrario: 25.6,
      costoForm: 4505.6,
      creditoImposta: 2252.8
    },{
      nome: "Maria",
      cognome: "Lamuta",
      lavGen: 60,
      formGen: 16,
      lavFeb: 64,
      formFeb: 24,
      lavMar: 60,
      formMar: 20,
      lavApr: 52,
      formApr: 20,
      lavMag: 56,
      formMag: 20,
      lavGiu: 52,
      formGiu: 24,
      lavLug: 76,
      formLug: 32,
      lavAgo: 28,
      formAgo: 0,
      lavSet: 52,
      formSet: 0,
      lavOtt: 56,
      formOtt: 20,
      lavNov: 0,
      formNov: 0,
      lavDic: 0,
      formDic: 0,
      lavTot: 556,
      formTot: 176,
      percTot: 31.66,
      costoOrario: 18.1,
      costoForm: 3185.6,
      creditoImposta: 1592.8
    }
  ];

  $scope.esportaPersonale = function(){
    var table = document.getElementById("table-container");
    factoryExcel.exportFile("riepilogo", table, "RiepilogoExcel", "xlsx", ["Riepilogo"]);
  }

});
