'use strict';

angular.module('myApp.login', ['ngRoute'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/login', {
    templateUrl: 'login/login.html',
    controller: 'loginCtrl'
  });
}])

.controller('loginCtrl',  function($scope,$window) {
  document.getElementById("navbar-btn").style.display = "none";
  $scope.labelCampi = {
    login: "Login",
    username: "Username",
    password: "Password",
    loginBtn: "Login"
  };
  $scope.labelErrori = {
    required: "il campo è obbligatorio",
    pattern: "caratteri invalidi"
  };

  $scope.validaUsername = {
    regExp: /^.*$/
  };

  $scope.validaPassword = {
    regExp: /^[\s\S]*$/
  };

  $scope.getErrorLabel = function(fieldName, error){
    if(error.required){
      return fieldName + ": " + $scope.labelErrori.required;
    }
    else if(error.pattern){
      return fieldName + ": " + $scope.labelErrori.pattern;
    }
  };

  $scope.mostraPass = function(){
    if($('#passDiv input').attr("type") == "text"){
      $('#passDiv input').attr('type', 'password');
      $('#passDiv i').addClass( "fa-eye-slash" );
      $('#passDiv i').removeClass( "fa-eye" );
    }else if($('#passDiv input').attr("type") == "password"){
      $('#passDiv input').attr('type', 'text');
      $('#passDiv i').removeClass( "fa-eye-slash" );
      $('#passDiv i').addClass( "fa-eye" );
    }
  }

  $scope.login = function(){
    console.log("TODO");
    //let socket = new WebSocket("ws://localhost:8085/egeosAicFormWS/WSEndpointSian");
    let socket = new WebSocket("ws://localhost:8085/egeosAicFormWS/servletWSDecrypt");

    socket.onopen = function(event){
      let body = {
        username: "pippo",
        password: "pwd"
      }
      let json = JSON.stringify(body);
      let body64 = btoa(json);
      console.log("BODY: ");
      console.log(body);
      console.log("JSON: ");
      console.log(json);
      console.log("BODY64: ");
      console.log(body64);

      socket.send(body64);
    }

    socket.onmessage = function(event){
      console.log(event.data);
    }

    /*
    if(ruoliAdmin.includes(ruolo)) window.location.href = "#!/lista-anagrafiche/";
    else {
      window.location.href = "#!/anagrafica/";
      document.getElementById("navbar-btn").style.display = "";
    }*/
  }

  $scope.loginFinto = function(){
    console.log(ruolo);
    window.location.href = "#!/home";
  }


});
