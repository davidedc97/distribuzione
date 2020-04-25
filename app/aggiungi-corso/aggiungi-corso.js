'use strict';

if(localStorage.getItem("lista")==null)localStorage.setItem("lista",JSON.stringify([]));
var listaPersonale = JSON.parse(localStorage.getItem("personale"));

angular.module('myApp.aggiungi-corso', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/aggiungi-corso', {
    templateUrl: 'aggiungi-corso/aggiungi-corso.html',
    controller: 'aggiungi-corsoCtrl'
  });
}])
.controller('aggiungi-corsoCtrl', function($scope,$window,$http) {
  $scope.labelCampi = {
    aggiungiCorso: "Aggiungi corso",
    obbligatorio: "Obbligatorio",
    aggiungiBtn: "Aggiungi",
    annullaBtn: "Annulla",
    scegli: "Scegli",
    titolo: "Titolo",
    descrizione: "Descrizione",
    durata: "Durata(ore)",
    aula: "Aula",
    materiale: "Materiale",
    dataInizio: "Data inizio",
    dataFine: "Data fine",
    modalitaFormative: "Modalità formative"
  };
  $scope.labelErrori = {
    required: "il campo è obbligatorio",
    pattern: "caratteri invalidi",
    number: "il numero deve essere positivo"
  };

  $scope.listaAule = [
    "A01",
    "B04"
  ];

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
  //console.log( request );
  */

  $scope.annulla = function() {
      $window.location.href = "#!/corsi";
  };

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
        $window.location.href = '#!/corsi';
      }
    }
  };

  $scope.validaTitolo = {
    regExp: /^[A-Za-z0-9\s\-_,\.;:()]+$/
  };
  $scope.validaDescrizione = {
    regExp: /^[A-Za-z0-9\s\-_,\.;:()]+$/
  };
  $scope.validaDurata = {
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
      return fieldName + ": " + $scope.labelErrori.number;
    }
  };

  $("#dataInizio").datepicker({
    format: 'dd/mm/yyyy',
    uiLibrary: 'bootstrap4',
    weekStartDay: 1 //parte da lunedì
  });
  $("#dataFine").datepicker({
    format: 'dd/mm/yyyy',
    uiLibrary: 'bootstrap4',
    weekStartDay: 1 //parte da lunedì
  });

});
