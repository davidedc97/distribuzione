'use strict';

angular.module('myApp.aggiungi-anagrafica', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/aggiungi-anagrafica', {
    templateUrl: 'aggiungi-anagrafica/aggiungi-anagrafica.html',
    controller: 'aggiungi-anagraficaCtrl'
  });
}])
.controller('aggiungi-anagraficaCtrl', function($scope, $window) {

  $scope.labelCampi = {
    aggiungiAnagrafica: "Aggiungi anagrafica",
    obbligatorio: "Obbligatorio",
    ragioneSociale: "Ragione sociale",
    partitaIva: "Partita Iva",
    codiceRea: "Codice REA",
    indirizzo: "Indirizzo",
    civico: "Civico",
    cap: "CAP",
    citta: "Città",
    provincia: "Provincia",
    stato: "Stato",
    aggiungiBtn: "Aggiungi",
    annullaBtn: "Annulla"
  };

  $scope.aggiungi = function() {
    console.log("TODO");
    /*
    if($scope.ragioneSociale && $scope.partitaIva && $scope.codiceRea && $scope.indirizzo && $scope.civico && $scope.cap && $scope.citta && $scope.provincia && $scope.stato){
      var valid = true;

      if(valid){
        anagrafica = {
          ragioneSociale: $scope.ragioneSociale,
          partitaIva: $scope.partitaIva,
          codiceRea: $scope.codiceRea,
          indirizzo: $scope.indirizzo,
          civico: $scope.civico,
          cap: $scope.cap,
          citta: $scope.citta,
          provincia: $scope.provincia,
          stato: $scope.stato
        };

        window.location.href = "#!/anagrafica";
      }
    }
    */
  };

  $scope.annulla = function() {
    window.location.href = "#!/lista-anagrafiche";
  }

});
