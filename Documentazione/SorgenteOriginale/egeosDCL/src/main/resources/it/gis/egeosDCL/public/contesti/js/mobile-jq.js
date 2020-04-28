// Start with the map page
//window.location.replace(window.location.href.split("#")[0] + "#ter-ctx");
window.location.replace(window.location.href.split("#")[0] + "#rec-ctx");

var selectedFeature = null;
var wktX='';
var tool=[];

// fix height of content
function fixContentHeight() {

    var footer = $("div[data-role='footer']:visible"),
        content = $("div[data-role='content']:visible:visible"),
        header	= $("div[data-role='header']:visible:visible"),
        navbar = $("div[data-role='navbar']:visible:visible"),
        direction=$("#contdir:visible:visible"),
        viewHeight = $(window).height();
        contentHeight = viewHeight - footer.outerHeight() - header.outerHeight() - navbar.outerHeight();

    if ((content.outerHeight() + footer.outerHeight()+header.outerHeight()+navbar.outerHeight()) !== viewHeight) {
        contentHeight -= (content.outerHeight() - content.height()+ 1);
        content.height(contentHeight);
    }

//    if (window.map && window.map instanceof OpenLayers.Map) {
//        map.updateSize();
//    }else{
//        // initialize map
//        init(function(feature) { 
//            selectedFeature = feature; 
//            $.mobile.changePage("#popup", "pop"); 
//        });
//    }
}

/** 
 * PAG. Recupero Contesto 
 **/
$('#rec-ctx').on('pageshow',function (){
	alert("Ciao")
	/* Dimensione Pag */
	fixContentHeight();
	$('#rec-ctx').die('pageshow', arguments.callee);
});

/** 
 * PAG. Mappa
 **/
$('#ter-ctx').on('pageshow',function (){
	alert("Ciao")
	/* Dimensione Pag */
	fixContentHeight();
	$('#ter-ctx').die('pageshow', arguments.callee);
});

$('#ter-ctx').on('pagecreate',function(){
	alert("create");
	$.mobile.changePage("#rec-ctx", "pop");
	$('#ter-ctx').die('pagecreate', arguments.callee);
});

$(window).bind("orientationchange resize pageshow", fixContentHeight);

