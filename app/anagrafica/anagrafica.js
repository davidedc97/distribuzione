'use strict';

angular.module('myApp.anagrafica', ['ngRoute', 'myApp.factoryModule'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/anagrafica', {
    templateUrl: 'anagrafica/anagrafica.html',
    controller: 'anagraficaCtrl'
  });
}])

.controller('anagraficaCtrl', function($scope,$window, factoryAnagrafica) {

  $scope.labelCampi = {
    anagrafica: "Anagrafica",
    ragioneSociale: "Ragione sociale",
    partitaIva: "Partita Iva",
    codiceRea: "Codice REA",
    indirizzo: "Indirizzo",
    civico: "Civico",
    cap: "CAP",
    citta: "Città",
    provincia: "Provincia",
    stato: "Stato",
    modificaBtn: "Modifica"
  };

  const anag = factoryAnagrafica.getAnagrafica();
  $scope.anagrafica = anag ? anag : {
    ragioneSociale: "Tiuke S.R.L.",
    partitaIva: "04955990652",
    codiceRea: "SA-407914",
    indirizzo: "Via Prenestina",
    civico: 29,
    cap: "00177",
    citta: "Roma",
    provincia: "RM",
    stato: "IT"
  };

  $scope.modificaAnagrafica = function(){
    factoryAnagrafica.setAnagrafica($scope.anagrafica);
    window.location.href = "#!/modifica-anagrafica";
  }
});
