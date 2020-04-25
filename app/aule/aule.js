'use strict';

angular.module('myApp.aule', ['ngRoute', 'myApp.factoryModule', 'myApp.excelModule'])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/aule', {
    templateUrl: 'aule/aule.html',
    controller: 'auleCtrl'
  });
}])

.controller('auleCtrl', function($scope, $window, factoryAula, factoryExcel) {
  $scope.ruoloUtente = ruolo;
  console.log($scope.ruoloUtente);

  $scope.labelCampi = {
    aule: "Aule",
    aggiungiAulaBtn: "Aggiungi",
    esportaBtn: "Esporta",
    importaBtn: "Importa",
    selezionaFile: "Seleziona file",
    confermaElimina: "Sicuro di voler eliminare il recapito?",
    descrizione: "Descrizione",
    numero: "Numero",
    postiMax: "Posti massimi",
    numeroCivico: "Numero Civico",
    cap: "CAP",
    piano: "Piano",
    note: "Note"
  };

  $scope.fileSelezionatoLabel = $scope.fileSelezionato || $scope.labelCampi.selezionaFile;
  $scope.aule = [
    { descrizione: "-",
      numero: "B04",
      postiMax: 120,
      numeroCivico: 13,
      cap: "00150",
      piano: 2,
      note: "-"
    },
    { descrizione: "-",
      numero: "A01",
      postiMax: 80,
      numeroCivico: 13,
      cap: "00150",
      piano: 0,
      note: "Dopo la segreteria"
    }
  ];

  /*
  $scope.aggiungiDipendente = function(){
    window.location.href = "#!/aggiungi-aule";
  }
  */

  $scope.rimuovi = function($index){
    if($index > -1) {
      if(confirm($scope.labelCampi.confermaElimina)){
        console.log("TODO");
        /*$scope.aule.splice($index, 1);
        localStorage.setItem("aule", JSON.stringify($scope.aule));
        */
      }
    }
  };

  $scope.modificaAula = function($index){
    factoryAula.setAula($scope.aule[$index]);
    $window.location.href = "#!/modifica-aula";
  }

  $scope.aggiungiAula = function(){
    $window.location.href = "#!/aggiungi-aula";
  }

  $scope.esportaAule = function(){
    factoryExcel.exportFile("aule", $scope.aule, "auleExcel", "xlsx", ["Aule"]);
  }
});
