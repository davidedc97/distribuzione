'use strict';

angular.module('myApp.corsi', ['ngRoute', 'myApp.factoryModule'])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/corsi', {
    templateUrl: 'corsi/corsi.html',
    controller: 'corsiCtrl'
  });
}])

.controller('corsiCtrl', function($scope, $window, factoryCorso) {
  $scope.labelCampi = {
    corsi: "Corsi",
    aggiungiCorsoBtn: "Aggiungi",
    confermaEliminazione: "Sicuro di voler eliminare il corso?",
    titolo: "Titolo",
    descrizione: "Descrizione",
    durata: "Durata(ore)",
    aula: "Aula",
    dataInizio: "Data inizio",
    dataFine: "Data fine"
  };

  $scope.ruoloUtente = ruolo;
  console.log($scope.ruoloUtente);

  $scope.listaCorsi = [
    { idCorso: "-",
      titolo: "Basi di informatica",
      descrizione: "Nozioni fondamentali di computer e suite per l'ufficio",
      durata: 50,
      aula: "B04",
      dateCorso: [
        {
          data: "28/02/2019",
          orePrimo: "",
          oreSecondo: "",
          attivitaSvolte: "Slide ed esempi pratici"
        },
        {
          data:"01/03/2019",
          orePrimo: "09:00-12:00",
          oreSecondo: "15:00-18:00",
          attivitaSvolte: "Slide ed esempi pratici"
        },
        {
          data:"02/03/2019",
          orePrimo: "09:00-12:00",
          oreSecondo: "15:00-18:00",
          attivitaSvolte: "Slide ed esempi pratici"
        },
        {
          data:"03/03/2019",
          orePrimo: "09:00-12:00",
          oreSecondo: "15:00-18:00",
          attivitaSvolte: "Slide ed esempi pratici"
        },
        {
          data: "04/03/2019",
          orePrimo: "",
          oreSecondo: "",
          attivitaSvolte: "Slide ed esempi pratici"
        }
      ],
      materiale: [],
      listaDocenti: [],
      listaPartecipanti: [],
      modalitaFormative: "Illustrazione in aula con l’ausilio di proiezioni di slide dei vari argomenti trattati. Esempi pratici. Risposte ai quesiti posti dagli allievi."
    }
  ];

  $scope.aggiungiCorso = function(){
    window.location.href = "#!/aggiungi-corso";
  }

  $scope.rimuovi = function($index){
    console.log("TODO");
    /*
    if($index > -1) {
      if(confirm($scope.labelCampi.confermaEliminazione)){
      $scope.corsi.splice($index, 1);
      localStorage.setItem("corsi", JSON.stringify($scope.corsi));}
    }
    */
  };

  $scope.gestisciCorso = function($index){
    let corso = $scope.listaCorsi[$index];
    factoryCorso.setCorso(corso);
    window.location.href = "#!/gestisci-corso";
  }

  $scope.visualizzaCorso = function($index){
    let corso = $scope.listaCorsi[$index];
    factoryCorso.setCorso(corso);
    window.location.href = "#!/visualizza-corso";
  }
});
