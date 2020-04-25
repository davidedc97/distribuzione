'use strict';

angular.module('myApp.visualizza-corso', ['ngRoute', 'myApp.factoryModule'])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/visualizza-corso', {
    templateUrl: 'visualizza-corso/visualizza-corso.html',
    controller: 'visualizza-corsoCtrl'
  });
}])

.controller('visualizza-corsoCtrl', function($scope, $window, factoryCorso) {
  $scope.labelCampi = {
    visualizzaCorso: "Visualizza corso",
    titolo: "Titolo",
    descrizione: "Descrizione",
    durata: "Durata(ore)",
    aula: "Aula",
    dataInizio: "Data inizio",
    dataFine: "Data fine",
    presenti: "Presenti",
    salvaBtn: "Salva",
    scegli: "Scegli",
    selezionati: "Selezionati",
    materialeScans: "Materiale da scansionare",
    materiale: "Materiale",
    selezionaFile: "Seleziona file",
    selezionaTutto: "Seleziona tutto",
    acquisisciDocBtn: "Acquisisci documentazione"
  };

  $scope.ruoloUtente = ruolo;

  $scope.corso = factoryCorso.getCorso();
  if(!$scope.corso){
    $window.location.href = "#!/lista-anagrafiche";
    return;
  }

  $scope.listaPartecipanti = ($scope.corso.listaPartecipanti && $scope.corso.listaPartecipanti.length > 0) ? $scope.corso.listaPartecipanti : [
    { nome: "Gianfranco",
      cognome: "Preziosi",
      codiceFiscale: "PRZGFR77L07H501O",
      costoOrario: "25.6",
      oreLavorative: "10",
      inizioRapporto: "02/04/2012",
      fineRapporto: "01/04/2022"
    },
    { nome: "Maria",
      cognome: "Lamuta",
      codiceFiscale: "LMTMRA89C43H501K",
      costoOrario: "18.1",
      oreLavorative: "12",
      inizioRapporto: "10/06/2016",
      fineRapporto: "01/06/2021"
    }
  ];

  $scope.listaMaterialeScans = [
    "Registro presenze giornaliero",
    "Relazione docente",
    "Questionario"
  ];

  $scope.parseFile = function(){
    const files = event.target.files;
    let container = document.getElementById("inputLabelContainer");
    // decommentare per eliminare i file caricati prima una nuova browse
    //var containerCpy = container.cloneNode(false);
    //container.parentNode.replaceChild(containerCpy, container);
    //container = document.getElementById("inputLabelContainer");
    for(var i=0; i < files.length; i++){
      let html = "<div id='" + files[i].name + "' class='text-left'>";
      html += "<label fname='" + files[i].name + "' class='removeBtn' onclick='angular.element(this).scope().removeFile()'>&times&nbsp</label>"
      html += files[i].name;
      html += "</div>";
      $("#inputLabelContainer").append(html);
    }
  }

  $scope.removeFile = function(){
    const filename = event.target.getAttribute("fname");
    console.log("REMOVING:" + filename);
    var elem = document.getElementById(filename);
    elem.parentNode.removeChild(elem);
  };

  $scope.selezionaRiga = function(){
    let riga = event.target;
    let parent = riga.parentNode.classList;
    var classi = riga.classList;
    if(classi.contains("riga-sel")){
      classi.remove("riga-sel");
      parent.remove("riga-sel-wrapper");
    }
    else{
      if(!classi.contains("riga")){
        classi.add("riga-sel");
        parent.add("riga-sel-wrapper");
      }
    }
  }

  $scope.selezionaTutto = function(){
    let divPartecipanti = $(".riga-dipe");
    for(let i=0; i< divPartecipanti.length; i++){
      let riga = divPartecipanti[i];
      let parent = riga.parentNode.classList;
      let classi = riga.classList;
      if(!classi.contains("riga-sel")){
        classi.add("riga-sel");
        parent.add("riga-sel-wrapper");
      }
    }
  }

  $scope.acquisisciDoc = function(){
    console.log("TODO");
  }

});
