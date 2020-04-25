'use strict';
if(localStorage.getItem("immagini")==null)localStorage.setItem("immagini",JSON.stringify([]));
var listaImmagini=JSON.parse(localStorage.getItem("immagini"));

angular.module('myApp.photogallery', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/photogallery', {
    templateUrl: 'photogallery/photogallery.html',
    controller: 'photogalleryCtrl'
  });
}])

.controller('photogalleryCtrl', function($scope,$window) {
  $scope.ruoloUtente = ruolo;
  $scope.lista=listaImmagini;

    $scope.goBack = function() {
      $window.history.back();
    };
  $scope.goToLink=function(url){
    window.open(url, "_blank");
  }
  $scope.rimuovi = function($index){
    if($index > -1) {
      if(confirm("Sicuro di voler eliminare il recapito?")){
      $scope.lista.splice($index, 1);
      localStorage.setItem("immagini", JSON.stringify($scope.lista));}
    }
  };
  /*
  link gallery responsive -> https://www.w3schools.com/howto/howto_css_image_grid_responsive.asp
  function onFileSelected(event) {
    var selectedFile = event.target.files[0];
    var reader = new FileReader();

    var imgtag = document.getElementById("myimage");
    imgtag.title = selectedFile.name;

    reader.onload = function(event) {
    imgtag.src = event.target.result;
    };

    reader.readAsDataURL(selectedFile);
  }
  */
});
