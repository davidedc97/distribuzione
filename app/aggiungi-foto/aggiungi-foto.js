'use strict';
if(localStorage.getItem("immagini")==null)localStorage.setItem("immagini",JSON.stringify([]));
var listaImmagini=JSON.parse(localStorage.getItem("immagini"));
angular.module('myApp.aggiungi-foto', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/aggiungi-foto', {
    templateUrl: 'aggiungi-foto/aggiungi-foto.html',
    controller: 'aggiungi-fotoCtrl'
  });
}])
.controller('aggiungi-fotoCtrl', function($scope,$window) {
  $scope.listaImmagini = listaImmagini;

    $scope.goBack = function() {
      $window.history.back();
    };


  $scope.aggiungi = function() {
    if($scope.file){
      $scope.descrizione = $scope.descrizione || "";
      var valid = true;
      if($scope.fileErrorMessage != ''){
        valid = false;
        $("#imgError").css("display", "initial");
        $("#imgError").text($scope.fileErrorMessage);
      }
      if(valid){
        $("#imgError").css("display", "none");
        $("#imgError").text("");
        const map = {
          immagine: $scope.file,
          descrizione: $scope.descrizione
        };
        console.log(map);
        listaImmagini.push(map);
        localStorage.setItem("immagini",JSON.stringify(listaImmagini))
        $window.location.href = '#!/photogallery';
      }
    }
  };

  $scope.setFile = function(element) {
    $scope.$apply(function($scope) {
      const file = element.files[0];
      $scope.fileErrorMessage = '';
      var filename = file.name;
      console.log(filename)
      var index = filename.lastIndexOf(".");
      var ext = filename.substring(index).toLowerCase();
      if (ext == '.png' || ext == '.jpeg' || ext == '.gif') {
        console.log('File Uploaded sucessfully');
        var reader = new FileReader();

        reader.onload = (function(theFile) {
          return function(e) {
          // Render thumbnail.
            var span = document.createElement('span');
            span.innerHTML = ['<img class="thumb" src="', e.target.result, '" title="', escape(theFile.name), '"/>'].join('');
            $scope.file = e.target.result;
          };
        })(file);

        reader.readAsDataURL(file);
      }
      else {
        $scope.fileErrorMessage = 'Estensione del file non valida';
        console.log($scope.fileErrorMessage);
      }
    });
  };
  $scope.validaDescrizione = {
    regExp: /^[a-zA-Z\d][a-zA-Z\s\d]*$/
  };
  $scope.getErrorLabel = function(fieldName, error){
    if(error.required){
      return "Il campo " + fieldName + " è obbligatorio";
    }
    else if(error.pattern){
      if(fieldName == "descrizione"){
        return "Il campo " + fieldName + " deve contenere solo lettere, numeri e spazi";
      }
    }
  };
});
