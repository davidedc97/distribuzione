'use strict';

angular.module('myApp.aggiungi-personale', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/aggiungi-personale', {
    templateUrl: 'aggiungi-personale/aggiungi-personale.html',
    controller: 'aggiungi-personaleCtrl'
  });
}])
.controller('aggiungi-personaleCtrl', function($scope,$window,$http) {
  $scope.labelCampi = {
    aggiungiPersonale: "Aggiungi personale",
    obbligatorio: "Obbligatorio",
    nome: "Nome",
    cognome: "Cognome",
    codiceFiscale: "Codice Fiscale",
    costoOrario: "Costo orario",
    oreLavorative: "Ore lavorative giornaliere",
    aggiungiBtn: "Aggiungi",
    annullaBtn: "Annulla",
    importaBtn: "Importa",
    selezionaFile: "Seleziona file",
    inizioRapporto: "Inizio rapporto",
    fineRapporto: "Fine rapporto"
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

  $scope.annulla = function(){
    $window.location.href = "#!/personale";
  }

  // utente = {nome,cognome,cf,costoOrario,oreLavorative};
  $scope.aggiungi = function() {
    if($scope.nome && $scope.cognome && $scope.codiceFiscale && $scope.costoOrario && $scope.oreLavorative){
      var valid = true;
      console.log("sono dentro");
      /*
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
      }*/
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
    weekStartDay: 1 //parte da lunedì
  });
  $("#fineRapporto").datepicker({
    format: 'dd/mm/yyyy',
    uiLibrary: 'bootstrap4',
    weekStartDay: 1 //parte da lunedì
  });

});
