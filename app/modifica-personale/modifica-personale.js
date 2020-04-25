'use strict';

angular.module('myApp.modifica-personale', ['ngRoute', 'myApp.factoryModule'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/modifica-personale', {
    templateUrl: 'modifica-personale/modifica-personale.html',
    controller: 'modifica-personaleCtrl'
  });
}])
.controller('modifica-personaleCtrl', function($scope,$window,$http, factoryDipendente) {
  $scope.labelCampi = {
    modificaPersonale: "Modifica personale",
    obbligatorio: "Obbligatorio",
    nome: "Nome",
    cognome: "Cognome",
    codiceFiscale: "Codice Fiscale",
    costoOrario: "Costo orario",
    oreLavorative: "Ore lavorative giornaliere",
    inizioRapporto: "Inizio rapporto",
    fineRapporto: "Fine rapporto",
    salvaBtn: "Salva",
    annullaBtn: "Annulla"
  };
  $scope.labelErrori = {
    required: "il campo è obbligatorio",
    pattern: "caratteri invalidi o eccessivi",
    number: "il campo deve essere positivo",
    oreLavorative: "deve essere compreso tra 0 e 16"
  };

  /*
  var request = $http({
    method: "get",
    url: servelet,
    params: {
      action: "GET_RECAPITO"
    },
    data: {
      'L': '1'
    }
  });
  */

  const fDipe = factoryDipendente.getDipendente();
  if(!fDipe){
    $window.location.href = "#!/lista-anagrafiche";
    return;
  }

  $scope.nome = fDipe.nome;
  $scope.cognome = fDipe.cognome;
  $scope.codiceFiscale = fDipe.codiceFiscale;
  $scope.costoOrario = fDipe.costoOrario;
  $scope.oreLavorative = fDipe.oreLavorative;
  $scope.inizioRapporto = fDipe.inizioRapporto;
  $scope.fineRapporto = fDipe.fineRapporto;


  $scope.annulla = function(){
    $window.location.href = "#!/personale";
  }

  // utente = {nome,cognome,cf,costoOrario,oreLavorative};
  $scope.salva = function() {
    if($scope.nome && $scope.cognome && $scope.codiceFiscale && $scope.costoOrario && $scope.oreLavorative){
      console.log("TODO");
      /*
      var valid = true;
      console.log("sono dentro");

      if(!dateNotInFuture($scope.inizioValidita)){
        valid = false;
        const errorStr = "Inizio validità non può essere nel futuro";
        $("#dateError").css("display", "initial");
        $("#dateError").text(errorStr);
      }
      if(!dateDelay($scope.inizioValidita, $scope.fineValidita)){
        valid = false;
        var errorStr = $("#dateError").text();
        if(errorStr != "") errorStr += "<br>";
        errorStr += "Fine validità deve essere futura a Inizio validità"
        $("#dateError").css("display", "initial");
        $("#dateError").html(errorStr);
      }
      if(valid){
        $("#dateError").css("display", "none");
        $("#dateError").text("");
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
      */
    }
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

  $("#inizioRapporto").datepicker({
    format: 'dd/mm/yyyy',
    uiLibrary: 'bootstrap4',
    weekStartDay: 1, //parte da lunedì
    value: $scope.inizioRapporto
  });
  $("#fineRapporto").datepicker({
    format: 'dd/mm/yyyy',
    uiLibrary: 'bootstrap4',
    weekStartDay: 1, //parte da lunedì
    value: $scope.fineRapporto
  });

});
