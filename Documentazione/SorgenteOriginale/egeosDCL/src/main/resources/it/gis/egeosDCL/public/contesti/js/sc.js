var idcmb;
var map;
var sm = new OpenLayers.Projection("EPSG:900913");

var init = function(){
	
    map = new OpenLayers.Map('map');
    layer = new OpenLayers.Layer.OSM("Simple OSM Map");
    map.addLayer(layer);
    map.setCenter(
        new OpenLayers.LonLat(-71.147, 42.472).transform(
            new OpenLayers.Projection("EPSG:4326"),
            map.getProjectionObject()
        ), 12
    ); 
	
//    // Creazione MAPPA
//    map = new OpenLayers.Map({
//        div: "map",
//        theme: null,
//        projection: sm,
//        numZoomLevels: 18,
//        tileManager: new OpenLayers.TileManager(),
//        controls: [
//            new OpenLayers.Control.Attribution(),
//            new OpenLayers.Control.TouchNavigation({
//                dragPanOptions: {
//                    enableKinetic: true
//                }
//            })
//        ],
//        layers: [
//            new OpenLayers.Layer.OSM("OpenStreetMap", null, {
//                     transitionEffect: 'resize'
//            })
//        ]
//    });
//
//    map.setCenter(new OpenLayers.LonLat(12, 42).transform(
//			        new OpenLayers.Projection("EPSG:4326"),
//			        map.getProjectionObject()
//					), 7);
    

};

var todisplay = function(idbolck){
	document.getElementById(idbolck).style.display="block";
};
var displayno = function(idbolck){
	document.getElementById(idbolck).style.display="none";
};

var undoSoc=function(){
	document.getElementById("soc-x").style.display="none";
}


var addSoc=function(){
	var n1=document.getElementById('nome').value;
	var n2=document.getElementById('nome2').value;
	
	if(n1.length>0){
		if(n2.length>0){
			var cbo=document.getElementById(idcmb);
			var option = document.createElement("option");
			option.text = n1+' '+n2;
			cbo.add(option);
			alert("Operazione Terminata.");
//			document.getElementById("soc-x").style.display="none";
		}else{
			alert("Specificare il Cognome.");
		}
	}else{
		alert("Specificare il Nome.");
		
	}
	
	
}

var viewSoc=function(idbolck,cmb,titolo){
	idcmb=cmb;
	todisplaybis(idbolck,cmb);
	document.getElementById('testa').innerHTML=titolo;
	document.getElementById('nome').value="";
	document.getElementById('nome2').value="";
};

var todisplaybis = function(idbolck,cmb){
	document.getElementById(idbolck).style.display="block";
	idcmb=cmb;
}