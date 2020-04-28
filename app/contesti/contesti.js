'use strict';

angular.module('myApp.contesti', ['ngRoute', 'myApp.excelModule'])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/contesti', {
    templateUrl: 'contesti/contesti.html',
    controller: 'contestiCtrl'
  });
}])

.controller('contestiCtrl', function($scope, $window, factoryExcel) {
  $scope.ruoloUtente = ruolo;

  $scope.labelCampi = {
    contesti: "Contesti",
    esportaBtn: "Esporta"
  };

  $scope.listaContesti = [
    { numeroElenco: 15729,
      codice: "EFA2014",
      campagna: 2014,
      descrizione: "Lavorazioni EFA foto 2014"
    },
    { numeroElenco: 15730,
      codice: "REQ2014",
      campagna: 2014,
      descrizione: "Controllo Refresh 2014"
    },
    { numeroElenco: 15731,
      codice: "REC2014",
      campagna: 2014,
      descrizione: "Collaudo Refresh 2014"
    },
    { numeroElenco: 15732,
      codice: "REF2014",
      campagna: 2014,
      descrizione: "Lavorazioni Refresh+EFA 2014"
    }
  ];

});
