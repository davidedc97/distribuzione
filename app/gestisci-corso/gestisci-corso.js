'use strict';

angular.module('myApp.gestisci-corso', ['ngRoute', 'myApp.factoryModule', 'myApp.excelModule', 'myApp.wordModule'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/gestisci-corso', {
    templateUrl: 'gestisci-corso/gestisci-corso.html',
    controller: 'gestisci-corsoCtrl'
  });
}])
.controller('gestisci-corsoCtrl', function($scope,$window,$http, factoryAnagrafica, factoryCorso, factoryExcel, factoryWord) {
  $scope.labelCampi = {
    gestisciCorso: "Gestisci corso",
    aggiungiMateriale: "Materiale di supporto",
    aggiungiPartecipanti: "Partecipanti",
    aggiungiDocenti: "Docenti",
    materiale: "Materiale",
    scegli: "Scegli",
    selezionati: "Selezionati",
    nome: "Nome",
    cognome: "Cognome",
    aggiungiBtn: "Aggiungi",
    salvaBtn: "Salva",
    annullaBtn: "Annulla",
    generaDocBtn: "Genera documentazione",
    materialeProd: "Materiale da produrre",
    selezionaFile: "Seleziona file",
    selezionaTutto: "Seleziona tutto",
    obbligatorio: "Obbligatorio",
    titolo: "Titolo",
    descrizione: "Descrizione",
    durata: "Durata(ore)",
    aula: "Aula",
    materiale: "Materiale",
    dataInizio: "Data inizio",
    dataFine: "Data fine",
    modalitaFormative: "Modalità formative",
    dateCorso: "Date corso",
    data: "Data",
    orePrimo: "Orario turno 1",
    oreSecondo: "Orario turno 2",
    attivitaSvolte: "Attività svolte",
    aggiungiDataBtn: "Aggiungi data",
    aggiungiData: "Aggiungi data"
  };

  $scope.labelErrori = {
    required: "il campo è obbligatorio",
    pattern: "caratteri invalidi",
    number: "il numero deve essere positivo"
  };

  $scope.listaPersonale = [
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

  $scope.listaDocenti = [
    { nome: "Lanfranco",
      cognome: "Rossi",
      codiceFiscale: "PRZGFR77L07H501O",
      costoOrario: "25.6",
      oreLavorative: "10",
      inizioRapporto: "02/04/2012",
      fineRapporto: "01/04/2022"
    },
    { nome: "Giulia",
      cognome: "De Chirico",
      codiceFiscale: "LMTMRA89C43H501K",
      costoOrario: "18.1",
      oreLavorative: "12",
      inizioRapporto: "10/06/2016",
      fineRapporto: "01/06/2021"
    }
  ];


  $scope.listaMaterialeProd = [
    "Calendario",
    "Registro presenze giornaliero",
    "Relazione docente",
    "Attestati di formazione",
    "Questionario"
  ];

  $scope.listaAule = [
    "A01",
    "B04"
  ];

  /* Prendo il corso dalla factory anagrafica e lo porto nello scope */

  const fCorso = factoryCorso.getCorso();
  if(!fCorso){
    $window.location.href = "#!/lista-anagrafiche";
    return;
  }

  $scope.idCorso = fCorso.idCorso;
  $scope.titolo = fCorso.titolo;
  $scope.descrizione = fCorso.descrizione;
  $scope.durata = fCorso.durata;
  $scope.aula = fCorso.aula;
  $scope.dateCorso = fCorso.dateCorso;
  $scope.materialeCorso = fCorso.materiale;
  $scope.listaPartecipantiCorso = fCorso.listaPartecipanti;
  $scope.listaDocentiCorso = fCorso.listaDocenti;
  $scope.modalitaFormative = fCorso.modalitaFormative;

  /* ------------------------------------------------------------------ */

  $scope.annulla = function(){
    $window.location.href = "#!/corsi";
  }

  $scope.rimuoviData = function(index){
    console.log(index);
    console.log("TODO");
  }

  $scope.displayModale = function(){
    let modal = document.getElementById("addDateModal");
    modal.style.display = "block";
  }

  $scope.chiudiModale = function(){
    let modal = document.getElementById("addDateModal");
    modal.style.display = "none";
  }

  $window.onclick = function(event) {
    let modal = document.getElementById("addDateModal");
    if (event.target == modal) modal.style.display = "none";
  }

  $scope.generaDoc = function(){
    const fAnag = factoryAnagrafica.getAnagrafica();
    if(!fAnag){
      $window.location.href = "#!/lista-anagrafiche";
      return;
    }

    let listaPartecipanti = $scope.listaPersonale; //TODO non devo passare tutto il personale e li devo prendere da fCorso
    let listaDocenti = $scope.listaDocenti; //TODO non devo passare tutti i docenti e li devo prendere da fCorso

    let jsonData = {
      soggOrg: fAnag.ragioneSociale,
      titoloProg: fCorso.titolo,
      idCorso: "-",
      durataOraria: fCorso.durata,
      sedeLegale: fAnag.indirizzo+", "+fAnag.civico+", "+fAnag.cap+", "+fAnag.citta+" ("+fAnag.provincia+")",
      sedeCorso: fAnag.indirizzo+", "+fAnag.civico+", "+fAnag.cap+", "+fAnag.citta+" ("+fAnag.provincia+")", //TODO non sempre la sede legale è la sede del corso
      dateCorso: $scope.dateCorso,
      listaPartecipanti: listaPartecipanti,
      listaDocenti: listaDocenti,
      modalitaFormative: fCorso.modalitaFormative

    }
    //prendiamo tutti i label dei materiali da produrre
    var arrayMateriali = document.getElementsByClassName("mat-prod");
    for(var i=0; i<arrayMateriali.length; i++){
      let classi = arrayMateriali[i].classList;
      //filtriamo solo quelli selezionati
      if(classi.contains("riga-sel")){
        switch(i){
          case 0: {factoryWord.exportFile("calendario", jsonData, "Calendario"); break;}
          case 1: {factoryWord.exportFile("presenze", jsonData, "Registro didattico presenze"); break;}
          case 2: {factoryWord.exportFile("relazione", jsonData, "Relazione docente"); break;}
          case 3: {factoryWord.exportFile("attestati", jsonData, "Attestati di formazione"); break;}
          case 4: {factoryWord.exportFile("questionario", jsonData, "Questionario"); break;}
          default: console.log("La riga selezionata non è stata immessa");
        }
      }
    }


  }

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

  $scope.salva = function(){
    console.log("TODO");
  }

  $("#dataInizio").datepicker({
    format: 'dd/mm/yyyy',
    uiLibrary: 'bootstrap4',
    weekStartDay: 1, //parte da lunedì
    value: $scope.dataInizio
  });
  $("#dataFine").datepicker({
    format: 'dd/mm/yyyy',
    uiLibrary: 'bootstrap4',
    weekStartDay: 1, //parte da lunedì
    value: $scope.dataFine
  });
  $("#nuovaData").datepicker({
    format: 'dd/mm/yyyy',
    uiLibrary: 'bootstrap4',
    weekStartDay: 1, //parte da lunedì
    value: $scope.dataFine
  });

});
