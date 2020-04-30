'use strict';

angular.module('myApp.home', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/home', {
    templateUrl: 'home/home.html',
    controller: 'homeCtrl'
  });
}])

.controller('homeCtrl', function($scope, $window) {

  $scope.toggleRotation = function(event) {
    $(event.target).parents(".card").find(".fa-chevron-down").toggleClass("rotate180");
    $(event.target).parents(".card").find(".fa-chevron-down").addClass("not-clickable");
    $(event.target).parents(".card").find(".collapse").collapse("toggle");

    // Prevengo il click per il tempo della transizione
    setTimeout(function() {
      $(event.target).parents(".card").find(".fa-chevron-down").removeClass("not-clickable");
    }, 400);

  }

  $scope.displayModale = function(){
    let modal = document.getElementById("elencoContestiModal");
    modal.style.display = "block";
  }

  $scope.chiudiModale = function(){
    let modal = document.getElementById("elencoContestiModal");
    modal.style.display = "none";
  }

  $window.onclick = function(event) {
    let modal = document.getElementById("elencoContestiModal");
    if (event.target == modal) modal.style.display = "none";
  }

  $scope.modaleAperta = false;

  $scope.listaContesti = [
    { numeroElenco: 15729,
      codice: "EFA2014",
      campagna: 2014,
      descrizione: "Lavorazioni EFA foto 2014"
    },
    { numeroElenco: 15730,
      codice: "REQ2014",
      campagna: 2014,
      descrizione: "Controllo Refresh 2014"
    },
    { numeroElenco: 15731,
      codice: "REC2014",
      campagna: 2014,
      descrizione: "Collaudo Refresh 2014"
    },
    { numeroElenco: 15732,
      codice: "REF2014",
      campagna: 2014,
      descrizione: "Lavorazioni Refresh+EFA 2014"
    }
  ];

  $(document).ready(function() {

    $("#left-container .card-header").click( function(event) {
      let flag = $(event.target).find(".fa-chevron-down").hasClass("not-clickable");
      if(flag) return;
      $scope.toggleRotation(event);
    });

    $scope.displayModale();
    $scope.modaleAperta = true;

    var map = new ol.Map({
      target: 'map',
      layers: [
        new ol.layer.Tile({
          source: new ol.source.OSM()
        })
      ],
      view: new ol.View({
        center: ol.proj.fromLonLat([37.41, 8.82]),
        zoom: 4
      })
    });

  });

});
