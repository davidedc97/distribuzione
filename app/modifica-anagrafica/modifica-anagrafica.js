'use strict';

angular.module('myApp.modifica-anagrafica', ['ngRoute', 'myApp.factoryModule'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/modifica-anagrafica', {
    templateUrl: 'modifica-anagrafica/modifica-anagrafica.html',
    controller: 'modifica-anagraficaCtrl'
  });
}])
.controller('modifica-anagraficaCtrl', function($scope, $window, factoryAnagrafica) {

  $scope.labelCampi = {
    modificaAnagrafica: "Modifica anagrafica",
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
    salvaBtn: "Salva",
    annullaBtn: "Annulla"
  };

  const fAnag = factoryAnagrafica.getAnagrafica();
  if(!fAnag){
    $window.location.href = "#!/lista-anagrafiche";
    return;
  }

  $scope.ragioneSociale = fAnag.ragioneSociale;
  $scope.partitaIva = fAnag.partitaIva;
  $scope.codiceRea = fAnag.codiceRea;
  $scope.indirizzo = fAnag.indirizzo;
  $scope.civico = fAnag.civico;
  $scope.cap = fAnag.cap;
  $scope.citta = fAnag.citta;
  $scope.provincia = fAnag.provincia;
  $scope.stato = fAnag.stato;

  $scope.salva = function() {
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
  }

  $scope.annulla = function() {
    window.location.href = "#!/anagrafica";
  }

});
