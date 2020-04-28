// API key for http://openlayers.org. Please get your own at
// http://bingmapsportal.com/ and use that instead.
var apiKey = "AqTGBsziZHIJYYxgivLBf0hVdrAk9mWO5cQcb8Yux8sW5M8c8opEC2lZqKR1ZZXf";

// Inizializzazione MAPPA quando la pagina � pronta
var map;
var gg = new OpenLayers.Projection("EPSG:4326");
var sm = new OpenLayers.Projection("EPSG:900913");
var DEVICE_TYPE_MOBILE = 0;
var myposition;
var style;
var searchposition;
var directionX;

var init = function (onSelectFeatureFunction) {

	myposition = new OpenLayers.Layer.Vector("La mia posizione", {});
	
	searchposition = new OpenLayers.Layer.Vector("Searched Point",{
        styleMap: new OpenLayers.StyleMap({
        	"default": {
                externalGraphic: "marker.png",
                graphicHeight: 25,
                graphicYOffset: -19,
            	fontSize: "12px",
            	fontFamily: "Courier New, monospace",
            	fontWeight: "bold",
        	},
        	"select":{
        		externalGraphic: "marker.png"
        		
        	}
        })
    });
	
    // Creazione MAPPA
    map = new OpenLayers.Map({
        div: "map",
        theme: null,
        projection: sm,
        numZoomLevels: 18,
        tileManager: new OpenLayers.TileManager(),
        controls: [
            new OpenLayers.Control.Attribution(),
            new OpenLayers.Control.TouchNavigation({
                dragPanOptions: {
                    enableKinetic: true
                }
            })
        ],
        layers: [
            new OpenLayers.Layer.OSM("OpenStreetMap", null, {
                     transitionEffect: 'resize'
            })
        ]
    });

    map.setCenter(new OpenLayers.LonLat(12, 42).transform(
			        new OpenLayers.Projection("EPSG:4326"),
			        map.getProjectionObject()
					), 7);
    
 
};
