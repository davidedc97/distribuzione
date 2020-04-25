'use strict';

angular.module('myApp.personale', ['ngRoute', 'myApp.factoryModule', 'myApp.excelModule'])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/personale', {
    templateUrl: 'personale/personale.html',
    controller: 'personaleCtrl'
  });
}])

.controller('personaleCtrl', function($scope, $window, factoryDipendente, factoryExcel) {
  $scope.ruoloUtente = ruolo;
  console.log($scope.ruoloUtente);

  $scope.labelCampi = {
    personale: "Personale",
    aggiungiDipendenteBtn: "Aggiungi",
    esportaBtn: "Esporta",
    importaBtn: "Importa",
    selezionaFile: "Seleziona file",
    confermaElimina: "Sicuro di voler eliminare il recapito?",
    nome: "Nome",
    cognome: "Cognome",
    codiceFiscale: "Codice Fiscale",
    costoOrario: "Costo orario",
    oreLavorative: "Ore lavorative",
    inizioRapporto: "Inizio rapporto",
    fineRapporto: "Fine rapporto"
  };

  $scope.labelErrori = {
    required: "Nessun file selezionato",
    format: "Il formato scelto non è supportato"
  };

  $scope.personale = [
    { nome: "Gianfranco",
      cognome: "Preziosi",
      codiceFiscale: "PRZGFR77L07H501O",
      costoOrario: 25.6,
      oreLavorative: 10,
      inizioRapporto: "02/04/2012",
      fineRapporto: "01/04/2022",
      oreLavorative: {},
      oreFormazione: {}
    },
    { nome: "Maria",
      cognome: "Lamuta",
      codiceFiscale: "LMTMRA89C43H501K",
      costoOrario: 18.1,
      oreLavorative: 12,
      inizioRapporto: "10/06/2016",
      fineRapporto: "01/06/2021",
      oreLavorative: {},
      oreFormazione: {}
    }
  ];

  $scope.aggiungiDipendente = function(){
    window.location.href = "#!/aggiungi-personale";
  }

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

  $scope.modificaDipendente = function($index){
    let dipe = $scope.personale[$index];
    factoryDipendente.setDipendente(dipe);
    $window.location.href = "#!/modifica-personale";
  }

  $scope.onFileChange = function(){
    //reset degli errori
    document.getElementById("inputError").innerText = "";
    let inputField = event.target;
    let file = inputField.files[0];
    console.log(file);
    let inputLabel = document.getElementById("inputLabel");
    if(file) inputLabel.innerText = file.name;
    else inputLabel.innerText = $scope.labelCampi.selezionaFile;
  }

  $scope.esportaPersonale = function(){
    factoryExcel.exportFile("personale", $scope.personale, "PersonaleExcel", "xlsx", ["Personale"]);
  }

  $scope.importaPersonale = function(){
    let inputField = document.getElementById("importaInput");
    let file = inputField.files[0];
    if(file){
      let rightFormats = ["ods", "xls", "xlsx", "csv"];
      let s = file.name.split(".");
      const fileFormat = s[s.length -1];
      console.log(fileFormat);
      let valid = false;
      for(var i in rightFormats){
        let rf = rightFormats[i];
        if(rf === fileFormat) valid = true;
      }
      if(valid){
        let inputLabel = document.getElementById("inputLabel");
        //inputLabel.innerText = file.name;
        factoryExcel.parseFile(file, "json").then(result => {
          result = JSON.parse(result);
          console.log(result);
          for(var key in result){
            let sheet = result[key];
            let nuoviDipe = [];
            // TODO: devo controllare l'header per essere sicuro sia del personale
            // per ora saltiamo l'header partendo da 1
            for(var i=1; i<sheet.length; i++){
              let dipe = sheet[i];
              var mappaDipe = {
                nome: dipe[0],
                cognome: dipe[1],
                codiceFiscale: dipe[2],
                costoOrario: dipe[3],
                oreLavorative: dipe[4],
                inizioRapporto: dipe[5],
                fineRapporto: dipe[6]
              }
              nuoviDipe.push(mappaDipe);
            }

            $scope.personale = $scope.personale.concat(nuoviDipe);
            console.log($scope.personale);

            //interrompiamo alla prima sheet
            break;
          }
        });
      } else document.getElementById("inputError").innerText = $scope.labelErrori.format;
    } else document.getElementById("inputError").innerText = $scope.labelErrori.required;
  }

});
