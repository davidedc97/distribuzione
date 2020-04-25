'use strict';

angular.module('myApp.lista-anagrafiche', ['ngRoute', 'myApp.factoryModule'])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/lista-anagrafiche', {
    templateUrl: 'lista-anagrafiche/lista-anagrafiche.html',
    controller: 'lista-anagraficheCtrl'
  });
}])

.controller('lista-anagraficheCtrl', function($scope, $window, factoryAnagrafica) {
  document.getElementById("navbar-btn").style.display = "none";
  $scope.ruoloUtente = ruolo;
  if(!(ruoliAdmin.includes($scope.ruoloUtente))) window.location.href = "#!/anagrafica";
  console.log($scope.ruoloUtente);

  $scope.labelCampi = {
    listaAnagrafiche: "Lista anagrafiche",
    aggiungiAnagraficaBtn: "Aggiungi",
    ragioneSociale: "Ragione sociale",
    partitaIva: "Partita Iva",
    codiceRea: "Codice REA",
    indirizzo: "Indirizzo",
    civico: "Civico",
    cap: "CAP",
    citta: "Città",
    provincia: "Provincia",
    stato: "Stato"
  };

  $scope.listaAnagrafiche = [
    { ragioneSociale: "Tiuke S.R.L.",
      partitaIva: "04955990652",
      codiceRea: "SA-407914",
      indirizzo: "Via Prenestina",
      civico: 29,
      cap: "00177",
      citta: "Roma",
      provincia: "RM",
      stato: "IT"
    },
    { ragioneSociale: "EvilCorp S.p.a.",
      partitaIva: "03416990832",
      codiceRea: "NY-315639",
      indirizzo: "Via Anagni",
      civico: 987,
      cap: "00132",
      citta: "Roma",
      provincia: "RM",
      stato: "IT"
    }
  ];

  $scope.selezionaAnagrafica = function($index){
    var anag = $scope.listaAnagrafiche[$index];
    factoryAnagrafica.setAnagrafica(anag);
    window.location.href = "#!/anagrafica";
    document.getElementById("navbar-btn").style.display = "";
  }

  $scope.aggiungiAnagrafica = function(){
    window.location.href = "#!/aggiungi-anagrafica";
  }

});
