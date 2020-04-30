'use strict';

angular.module('myApp.contesti', ['ngRoute', 'myApp.excelModule'])
.config(['$routeProvider', function($routeProvider) {
  $routeProvider.when('/contesti', {
    templateUrl: 'contesti/contesti.html',
    controller: 'contestiCtrl'
  });
}])

.controller('contestiCtrl', function($scope, $window, factoryExcel) {
  $scope.ruoloUtente = ruolo;

  $scope.labelCampi = {
    contesti: "Contesti",
    esportaBtn: "Esporta"
  };

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

  $scope.addButtons = function (d) {
    // `d` is the original data object for the row
    return '<table cellspacing="0" border="0">'+
        '<tr>'+
            '<td><button class="btn btn-primary" ng-click="">btn1</button></td>'+
            '<td><button class="btn btn-primary" ng-click="">btn2</button></td>'+
            '<td><button class="btn btn-primary" ng-click="">btn3</button></td>'+
        '</tr>'+
    '</table>';
  }

  $scope.creaDatatable = function(){
    let table = document.getElementById("datatable");
    let html = '<thead>\
      <tr>\
        <th scope="col">Numero Elenco</th>\
        <th scope="col">Codice</th>\
        <th scope="col">Campagna</th>\
        <th scope="col">Descrizione</th>\
      </tr>\
    </thead>\
    <tbody>';

    for(var i=0; i<$scope.listaContesti.length; i++){
      let context = $scope.listaContesti[i];
      html += '<tr>\
        <td>' + context.numeroElenco + '</td>\
        <td>' + context.codice + '</td>\
        <td>' + context.campagna + '</td>\
        <td>' + context.descrizione + '</td>\
      </tr>';
    }
    html += '</tbody>';

    table.innerHTML = html;
  }

  $(document).ready(function() {
    $scope.creaDatatable();
    let dt = $('#datatable').DataTable({
      select: true,
      paging: false,
      searching: false,
      info: false,
      columnDefs: [
        {  targets: -1,
          className: 'dt-left'
        }
      ]
    });

    $('#datatable tbody').on('click', 'td', function () {
        var tr = $(this).closest('tr');
        var row = dt.row( tr );

        if ( row.child.isShown() ) {
            // This row is already open - close it
            row.child.hide();
            tr.removeClass('shown');
        }
        else {
            // Open this row
            row.child( $scope.addButtons(row.data()) ).show();
            tr.addClass('shown');
        }
    } );
} );

});
