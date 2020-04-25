'use strict';

angular.module('myApp.modifica-aula', ['ngRoute', 'myApp.factoryModule'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/modifica-aula', {
    templateUrl: 'modifica-aula/modifica-aula.html',
    controller: 'modifica-aulaCtrl'
  });
}])
.controller('modifica-aulaCtrl', function($scope,$window,$http, factoryAula) {
  $scope.labelCampi = {
    modificaAula: "Modifica aula",
    obbligatorio: "Obbligatorio",
    descrizione: "Descrizione",
    numero: "Numero",
    postiMax: "Posti massimi",
    numeroCivico: "Numero Civico",
    cap: "CAP",
    piano: "Piano",
    note: "Note",
    salvaBtn: "Salva",
    annullaBtn: "Annulla",
  };

  $scope.labelErrori = {
    required: "il campo è obbligatorio",
    pattern: "caratteri invalidi o eccessivi",
    number: "il campo deve essere positivo",
    oreLavorative: "deve essere compreso tra 0 e 16"
  };

  const fAula = factoryAula.getAula();
  if(!fAula){
    $window.location.href = "#!/lista-anagrafiche";
    return;
  }

  $scope.descrizione = fAula.descrizione;
  $scope.numero = fAula.numero;
  $scope.postiMax = fAula.postiMax;
  $scope.numeroCivico = fAula.numeroCivico;
  $scope.cap = fAula.cap;
  $scope.piano = fAula.piano;
  $scope.note = fAula.note;

  $scope.annulla = function(){
    $window.location.href = "#!/aule";
  }

  // utente = {nome,cognome,cf,costoOrario,oreLavorative};
  $scope.salva = function() {
    console.log("TODO");
    /*
    if($scope.numero && $scope.postiMax && $scope.nuemroCivico && $scope.cap && $scope.piano){
      var valid = true;
      console.log("sono dentro");
      if(valid){
        const map = {
          nome: $scope.nome,
          cognome: $scope.cognome,
          codiceFiscale: $scope.codiceFiscale,
          costoOrario: $scope.costoOrario,
          oreLavorative: $scope.oreLavorative
        };
        console.log("NUOVO DIPENDENTE:", map);
        listaPersonale.push(map);
        localStorage.setItem("personale", JSON.stringify(listaPersonale))
        $window.location.href = '#!/personale';
      }
    }
    */
  }

  $scope.validaNome = {
    regExp: /^[a-zA-Z][a-zA-Z\s]*$/
  };
  $scope.validaCognome = {
    regExp: /^[a-zA-Z][a-zA-Z\s]*$/
  };
  $scope.validaCF = {
    regExp: /^[A-Z]{6}[0-9]{2}[ABCDEHLMPRST][0-9]{2}([A-Z][0-9]{3})[A-Z]$/
  };
  $scope.validaCosto = {
    regExp: /^\d+$/
  };
  $scope.validaOre = {
    regExp: /^\d+$/
  };

  $scope.getErrorLabel = function(fieldName, error){
    if(error.required){
      return fieldName + ": " + $scope.labelErrori.required;
    }
    else if(error.pattern){
      return fieldName + ": " + $scope.labelErrori.pattern;
    }
    else if(error.number){
      if(fieldName == $scope.labelCampi.oreLavorative) return fieldName + ": " + $scope.labelErrori.oreLavorative;
      return fieldName + ": " + $scope.labelErrori.number;
    }
  };

});
