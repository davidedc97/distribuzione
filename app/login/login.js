'use strict';

angular.module('myApp.login', ['ngRoute', 'ngWebSocket'])

.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/login', {
    templateUrl: 'login/login.html',
    controller: 'loginCtrl'
  });
}])

.controller('loginCtrl',  function($scope,$window, $websocket) {
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
    let socket = new WebSocket("ws://18.188.70.134:9080/egeosIbfWS/WSEndpointSIAN");

    socket.onopen = function(event){
      let body = {
        "idRequest":"AUTH_REQUEST-0",
        "dataInput": {
          "username": "dinamik26@gmail.com",
          "password": "1ZkpHv0E"
        }
      }
      let json = JSON.stringify(body);
      let body64 = btoa(json);

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

  $scope.login2 = function(){
    let ws = $websocket("ws://18.188.70.134:9080/egeosIbfWS/WSEndpointSIAN");

    ws.onOpen(function(event){
      let body = {
        "idRequest":"AUTH_REQUEST-0",
        "dataInput": {
          "username": "dinamik26@gmail.com",
          "password": "1ZkpHv0E"
        }
      }
      let json = JSON.stringify(body);
      let body64 = btoa(json);
      ws.send(body64);
    });

    ws.onError(function(error){
      console.log("error");
      console.log(error);
    });

    ws.onMessage(function(message) {
      let blob = message.data;
      new Response(blob).arrayBuffer().then(buffer => {
        console.log(buffer);
        JSZip.loadAsync(blob).then(function (zip) {
          console.log(zip);
          return zip.file("content.txt").async("string");
        }).then(function (text) {
          console.log(text);
        });
      });

    });
  }


});
