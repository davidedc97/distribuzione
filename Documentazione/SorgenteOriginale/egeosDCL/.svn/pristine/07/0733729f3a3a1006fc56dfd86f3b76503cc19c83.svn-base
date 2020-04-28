package it.gis.egeosDCL.client.map;

import com.google.gwt.core.client.JavaScriptObject;

/**
 * WRAPPER PER OPENLAYERS
 * @version 1.0
 *
 */
public class OLMap_new {
	
	/**
	 * CREA IL LAYER DELLE REGIONI
	 * @param divID_map1
	 * @param url
	 * @param srid
	 */
	public static native void loadOLERegioni(String divID_map1,String url,String srid) /*-{
 	
 		var exportMapControl;
 		//alert("LOAD OL REGIONI");
 		var esci_da_ciclo = "";
 		$wnd.esci_da_ciclo = esci_da_ciclo;
 		
 		var style_regioni = new $wnd.OpenLayers.StyleMap(
 			{"default":{label:"${l}",
 			fill:true,fillColor:"${C}",strokeColor:'#000000',strokeWidth:1.0}
 				,
 			//"select":{strokeColor:'#000000',strokeWidth:1.0,fill:true,fillColor:"#FF2853",fillOpacity:0.5},	
 			"temporary":{strokeColor:'#000000',strokeWidth:1.0,fill:true,fillColor:"#FF2853",fillOpacity:0.5}
 			}
 		);

		/// DEFINIZIONE MAPPA //////////////////////////////////////////////////////////////
		var bounds_3003 = new $wnd.OpenLayers.Bounds(1280000.0,4250000.0,1996800.0,5683600.0);
	//	var bounds_3004 = new $wnd.OpenLayers.Bounds(2200000.0,3900000.0,2850000.0,5230000.0);
	 	
	 	var bounds_3004 = new $wnd.OpenLayers.Bounds(1280000.0,4250000.0,2850000.0,5230000.0);
		var bounds_mappa;
		  
	 	if(srid=='EPSG:3003'){
			bounds_mappa = bounds_3003;
		}else{
			bounds_mappa = bounds_3004;
		}
		
		$wnd.OpenLayers.DOTS_PER_INCH = 90.71428571428572;
		
		options = {         
	        projection: new $wnd.OpenLayers.Projection("EPSG:4326"),
			resolutions: [0.0439453125, 0.02197265625, 0.010986328125, 0.0054931640625, 0.00274658203125, 0.001373291015625, 6.866455078125E-4, 3.4332275390625E-4, 1.71661376953125E-4, 8.58306884765625E-5, 4.291534423828125E-5, 2.1457672119140625E-5, 1.0728836059570312E-5, 5.364418029785156E-6, 2.682209014892578E-6, 1.341104507446289E-6], 
	        displayProjection: new $wnd.OpenLayers.Projection("EPSG:4326"),           
	        controls: [
	        				new $wnd.OpenLayers.Control.TouchNavigation(
	        				{
	          					dragPanOptions: {
	              					enableKinetic: true
	          					}
	      					}),
	        				new $wnd.OpenLayers.Control.PanZoomBar(),
	        				new $wnd.OpenLayers.Control.ZoomBox(),
	        				new $wnd.OpenLayers.Control.Scale(),
	        				new $wnd.OpenLayers.Control.Attribution(),
							new $wnd.OpenLayers.Control.Navigation(),
	                    	new $wnd.OpenLayers.Control.LayerSwitcher(),
	        				new $wnd.OpenLayers.Control.MousePosition(),
		    ],
	        numZoomLevels: 15,		
	 		zoom:3,
	 		//units: 'm',
	        maxExtent: new $wnd.OpenLayers.Bounds(-180, -90, 180, 90)
	    };

     	var map = new $wnd.OpenLayers.Map("map",  options);

	
	
			var vectors_comuni = new $wnd.OpenLayers.Layer.Vector("Comuni", {
		          projection: "EPSG:4326",
		          transparent:true,
		          isBaseLayer:false,
		      	  units: 'm' 
		    
		    });

		var vectors_province = new $wnd.OpenLayers.Layer.Vector("Province", {
		          projection: "EPSG:4326",
		          transparent:true,
		          isBaseLayer:false,
		      	  units: 'm' ,
		     	  styleMap:style_regioni
		    });
		    
		vectors_province.events.on({
               "featureselected": function(e) {
               		$wnd.e = e;
               		if($wnd.esci_da_ciclo==""){
               			 @it.gis.egeosDCL.client.Layout.MapEvents::selezionaProvincia(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(e.feature.id,e.feature.fid,e.feature.figlio);
                   	}
                   	else
                   		$wnd.esci_da_ciclo="";
               }
           });

     	var vectors_regioni = new $wnd.OpenLayers.Layer.Vector("Regioni", {
              projection: "EPSG:4326",
              transparent:true,
              isBaseLayer:true,
          	  units: 'm' ,
          	  styleMap:style_regioni
          });
        
        
		 vectors_regioni.events.on({
               "featureselected": function(e) {
               		$wnd.e = e;
               		if($wnd.esci_da_ciclo==""){
               			  @it.gis.egeosDCL.client.Layout.MapEvents::selezionaRegione(Ljava/lang/String;)(e.feature.id);
                   		//@it.gis.egeosDCL.client.Layout.LayoutObjects.ListGridRegioniJSON::selectedRow(Ljava/lang/String;Ljava/lang/String;)(e.feature.id,e.feature.fid);
                   	}
                   	else
                   		$wnd.esci_da_ciclo="";
               }
           });
           

    	 //var mapCenter = new $wnd.OpenLayers.LonLat(12.347621366018, 41.439418241018);
		//mapCenter.transform(wgs84, googleMercator);

			var report = function(e) {
				$wnd.console.log(e.type, e.feature.id);
              
            };
            
            var highlightCtrl = new $wnd.OpenLayers.Control.SelectFeature(vectors_regioni, {
                hover: true,
                highlightOnly: true,
                renderIntent: "temporary"
              
            });

            var selectCtrlc = new $wnd.OpenLayers.Control.SelectFeature(vectors_regioni,
                {clickout: true

                }
            );
         /////////////////////////////////////////////////////////////////////////////////////////////////
            
             var highlightCtrl_PROV = new $wnd.OpenLayers.Control.SelectFeature(vectors_province, {
                hover: true,
                highlightOnly: true,
                renderIntent: "temporary"
              
            });

            var selectCtrlc_PROV = new $wnd.OpenLayers.Control.SelectFeature(vectors_province,
                {clickout: true

                }
            );
            $wnd.highlightCtrl_PROV = highlightCtrl_PROV;
			$wnd.selectCtrlc_PROV = selectCtrlc_PROV;
  
            map.addControl($wnd.highlightCtrl_PROV);
            map.addControl($wnd.selectCtrlc_PROV);
            
			$wnd.highlightCtrl = highlightCtrl;
			$wnd.selectCtrlc = selectCtrlc;
            map.addControl($wnd.highlightCtrl);
            map.addControl($wnd.selectCtrlc);

    	//map.setCenter(mapCenter,4);
		$wnd.map = map;
		$wnd.vectors_comuni = vectors_comuni;
        $wnd.vectors_regioni = vectors_regioni;
 		$wnd.vectors_province = vectors_province;
 		
        $wnd.map.addLayers([$wnd.vectors_regioni,$wnd.vectors_province,$wnd.vectors_comuni]);

}-*/;
	
	
	/**
	 * CREA IL LAYER DELLE PROVINCE
	 * @param divID_map1
	 * @param url
	 * @param srid
	 */
	public static native void loadOLEProvince(String divID_map1,String url,String srid) /*-{

		var esci_da_ciclo = "";
		$wnd.esci_da_ciclo = esci_da_ciclo;
		
		var style_province = new $wnd.OpenLayers.StyleMap(
 			{"default":{label:"${l}",labelAlign:"cc",fill:true,fillColor:"${C}",strokeColor:'#000000',strokeWidth:1.0}
 				,
 			"select":{strokeColor:'#000000',strokeWidth:1.0,fill:true,fillColor:"#FF2853",fillOpacity:0.5},	
 			"temporary":{fontSize:'12px',fontFamily:'Courier New', strokeColor:'#000000',strokeWidth:1.0,fill:true,fillColor:"#FF2853",fillOpacity:0.5}
 			}
 		);

	/// DEFINIZIONE MAPPA //////////////////////////////////////////////////////////////
		var bounds_3003 = new $wnd.OpenLayers.Bounds(1280000.0,4250000.0,1996800.0,5683600.0);
	//	var bounds_3004 = new $wnd.OpenLayers.Bounds(2200000.0,3900000.0,2850000.0,5230000.0);
	 	
	 	var bounds_3004 = new $wnd.OpenLayers.Bounds(2251632.4276, 4054757.3924, 2835795.3321, 5221314.5428);
		var bounds_mappa;
	  
	  
 	if(srid=='EPSG:3003'){
 		
		bounds_mappa = bounds_3003;
	}else{
		
		bounds_mappa = bounds_3004;
	}
	
	$wnd.OpenLayers.DOTS_PER_INCH = 90.71428571428572;
	
	options = {         
        projection: new $wnd.OpenLayers.Projection("EPSG:4326"),
		resolutions: [0.0439453125, 0.02197265625, 0.010986328125, 0.0054931640625, 0.00274658203125, 0.001373291015625, 6.866455078125E-4, 3.4332275390625E-4, 1.71661376953125E-4, 8.58306884765625E-5, 4.291534423828125E-5, 2.1457672119140625E-5, 1.0728836059570312E-5, 5.364418029785156E-6, 2.682209014892578E-6, 1.341104507446289E-6], 
        displayProjection: new $wnd.OpenLayers.Projection("EPSG:4326"),           
        controls: [
        				new $wnd.OpenLayers.Control.TouchNavigation(
        				{
          					dragPanOptions: {
              					enableKinetic: true
          					}
      					}),
        				new $wnd.OpenLayers.Control.PanZoomBar(),
        				new $wnd.OpenLayers.Control.ZoomBox(),
        				new $wnd.OpenLayers.Control.Scale(),
        				new $wnd.OpenLayers.Control.Attribution(),
						new $wnd.OpenLayers.Control.Navigation(),
                    	new $wnd.OpenLayers.Control.LayerSwitcher(),
        				new $wnd.OpenLayers.Control.MousePosition()
	    ],

        numZoomLevels: 15,		
 		zoom:3,
 		//units: 'm',
        maxExtent: new $wnd.OpenLayers.Bounds(-180, -90, 180, 90)
    };

 	var map = new $wnd.OpenLayers.Map("map",  options);


 	var vectors_province = new $wnd.OpenLayers.Layer.Vector("Province", {
          projection: "EPSG:4326",
          transparent:true,
          isBaseLayer:true,
          styleMap:style_province,
      	  units: 'm' 
    
    });
    
    
	vectors_province.events.on({
           "featureselected": function(e) {
           		$wnd.e = e;
           		//alert("e.feature.id "+e.feature.id);

	           		if($wnd.esci_da_ciclo==""){
	           			@it.gis.egeosDCL.client.Layout.MapEvents::selezionaProvincia(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(e.feature.id,e.feature.fid,e.feature.figlio);
	           		}else
	               		$wnd.esci_da_ciclo="";
           }
       });

     
		var mapCenter = new $wnd.OpenLayers.LonLat(12,42);
	
		var report = function(e) {
			$wnd.console.log(e.type, e.feature.id);
          
        };
        
        var highlightCtrl = new $wnd.OpenLayers.Control.SelectFeature(vectors_province, {
            hover: true,
            highlightOnly: true,
            renderIntent: "temporary"
          
        });

        var selectCtrlc = new $wnd.OpenLayers.Control.SelectFeature(vectors_province,
            {clickout: true

            }
        );
        
        
		$wnd.highlightCtrl = highlightCtrl;
		$wnd.selectCtrlc = selectCtrlc;
        map.addControl($wnd.highlightCtrl);
        map.addControl($wnd.selectCtrlc);

	$wnd.map = map;

    $wnd.vectors_province = vectors_province;

    
 $wnd.map.addLayers([$wnd.vectors_province]);

}-*/;
	
	public static native void startJSTSOperation(String operation)/*-{
    $wnd.geomAp = $wnd.map.getExtent().toGeometry();
	///////////////////////////////////////////////////////////////
      if ($wnd.historytrace===undefined || $wnd.historytrace.length===30){
          $wnd.historytrace = new Array();
      }
      i = $wnd.historytrace.length;
      $wnd.historytrace[i] = new $wnd.traceOperation($wnd.geomAp,operation);
      
	///////////////////////////////////////////////////////////////    
    var ol_ft  = new $wnd.OpenLayers.Feature.Vector($wnd.geomAp, {name:'afeature', description:'Test', modul:'JSTS'});
    $wnd.sourceLyr.addFeatures([ol_ft])
    if ($wnd.sourceLyr.features.length > 1) {
        //eseguiamo il [Within] fra le due geometrie per verificare se la nuova è inclusa nella precedente
        @it.gis.egeosDCL.client.map.OLMap_new::executeJSTS(Ljava/lang/String;)("Within");
        //eseguiamo la [Union] fra le due geometrie 
        @it.gis.egeosDCL.client.map.OLMap_new::executeJSTS(Ljava/lang/String;)(operation);
    }    
}-*/;
	
	public static native void executeJSTS(String jsts_operations)/*-{
    
    var errFlag=false;
    var fts = $wnd.vectors_quadranti.features;
    var creationFlag = true;

    if(fts.length > 0)
    {
        var JSTSfunc = jsts_operations;
        console.log(JSTSfunc);

        if(JSTSfunc=="Boundary" || JSTSfunc=="Buffer" || JSTSfunc=="Centroid" || JSTSfunc=="ConvexHull")
        {
            if(creationFlag && fts.length > 1)
                var jsts_geomA  = createUnionFromAllFeatures();
            else
                var jsts_geomA  = $wnd.jsts_parser.read(fts[0].geometry);

            try{
                if(JSTSfunc=="Boundary")
                    $wnd.jsts_result_geom = jsts_geomA.getBoundary();
                else if(JSTSfunc=="Buffer")
                    $wnd.jsts_result_geom = jsts_geomA.buffer(jsts_buffer_tolerance);
                else if(JSTSfunc=="Centroid")
                    $wnd.jsts_result_geom = jsts_geomA.getCentroid();
                else if(JSTSfunc=="ConvexHull")
                    $wnd.jsts_result_geom = jsts_geomA.convexHull();
                }catch(err){
                    alert(err.message);
                    errFlag=true;
                }
        }
        else if((JSTSfunc=="Within" || JSTSfunc=="Union" || JSTSfunc=="Intersection" || JSTSfunc=="SymDifference" || JSTSfunc=="Difference") && $wnd.vectors_quadranti.features.length > 1 )
        {
            if(creationFlag)
            {
                for(var i=0;i<fts.length;i++)
                {
                    if(i==0)
                    {
                        var jsts_geomA  = $wnd.jsts_parser.read(fts[0].geometry);
                    }
                    else
                    {
                        var jsts_geomB  = $wnd.jsts_parser.read(fts[i].geometry);

                        if(JSTSfunc=="Union")
                            $wnd.jsts_result_geom = jsts_geomA.union(jsts_geomB);
                        else if(JSTSfunc=="Intersection")
                            $wnd.jsts_result_geom = jsts_geomA.intersection(jsts_geomB);
                        else if(JSTSfunc=="SymDifference")
                            $wnd.jsts_result_geom = jsts_geomA.symDifference(jsts_geomB);
                        else if(JSTSfunc=="Difference")
                            $wnd.jsts_result_geom = jsts_geomA.difference(jsts_geomB);                                
                        else if(JSTSfunc=="Within"){
                           // @it.gis.client.objects.TraceMapBar::diplayTrace(Z)(jsts_geomB.within(jsts_geomA));
                            return;
                        }

                        jsts_geomA = $wnd.jsts_result_geom;
                    }
                    }


                }
            }
            else
            {
                var jsts_geomA = $wnd.jsts_parser.read(fts[0].geometry);
                var jsts_geomB = $wnd.jsts_parser.read(fts[1].geometry);

                if(JSTSfunc=="Union")
                    $wnd.jsts_result_geom = jsts_geomA.union(jsts_geomB);
                else if(JSTSfunc=="Intersection")
                    $wnd.jsts_result_geom = jsts_geomA.intersection(jsts_geomB);
                else if(JSTSfunc=="SymDifference")
                    $wnd.jsts_result_geom = jsts_geomA.symDifference(jsts_geomB);
                else if(JSTSfunc=="Difference")
                    $wnd.jsts_result_geom = jsts_geomA.difference(jsts_geomB);       
                else if(JSTSfunc=="Within"){
                    $wnd.alert(jsts_geomB.within(jsts_geomA));
                    return;
                }                                    
                                     
            }
        }
        
        $wnd.ol_geom   = $wnd.jsts_parser.write($wnd.jsts_result_geom);
        var ol_ft      = new $wnd.OpenLayers.Feature.Vector($wnd.ol_geom, {name:'a2feature', description:JSTSfunc, modul:'JSTS'});
        
        $wnd.vectors_quadranti.removeAllFeatures();
        if(errFlag==false)
            $wnd.vectors_quadranti.addFeatures([ol_ft]);

   // }//end if fts.length>0
    
}-*/;
	
	/**
	 * CREA IL LAYER DEI COMUNI
	 * @param divID_map1
	 * @param url
	 * @param srid
	 */
	public static native void loadOLEComuni(String divID_map1,String url,String srid) /*-{
 	
 	var jsts_parser = new $wnd.jsts.io.OpenLayersParser();
    $wnd.jsts_parser = jsts_parser;
    $wnd.jsts_result_geom;
    $wnd.ol_geom;
    $wnd.tracemap=false;
    $wnd.JSTSOperation=""; 
 	
 	//////////////////////////////////////////////////////
 	
	var esci_da_ciclo = "";
	$wnd.esci_da_ciclo = esci_da_ciclo;

		var style_comuni = new $wnd.OpenLayers.StyleMap(
 			{"default":{label:"${l}",fill:true,fillColor:"${C}",fillOpacity:0.5,strokeColor:'#000000',strokeWidth:1.0}
 				,
 			 "select":{strokeColor:'#000000',strokeWidth:1.0,fill:true,fillColor:"#FFFFFF",fillOpacity:0.5},	
 			"temporary":{strokeColor:'#000000',strokeWidth:1.0,fill:true,fillColor:"#FF2853",fillOpacity:0.5}
 			}
 		);  
 		 		

 		var style_quadranti = new $wnd.OpenLayers.StyleMap(
 			{"default":{fill:true,fillColor:"${C1}",strokeColor:'#000000',strokeWidth:0.5}
 				,
 			 "select":{strokeColor:'#000000',strokeWidth:0.5,fill:true,fillColor:"#0705ab",fillOpacity:0.5}	
 			//"temporary":{strokeColor:'#000000',strokeWidth:0.5,fill:true,fillColor:"#342dff",fillOpacity:0.5}
 			}
 		);  
 		
		$wnd.OpenLayers.DOTS_PER_INCH = 90.71428571428572;
		
		options = {         
		    projection: new $wnd.OpenLayers.Projection("EPSG:4326"),
			resolutions: [0.0439453125, 0.02197265625, 0.010986328125, 0.0054931640625, 0.00274658203125, 0.001373291015625, 6.866455078125E-4, 3.4332275390625E-4, 1.71661376953125E-4, 8.58306884765625E-5, 4.291534423828125E-5, 2.1457672119140625E-5, 1.0728836059570312E-5, 5.364418029785156E-6, 2.682209014892578E-6, 1.341104507446289E-6], 
		    displayProjection: new $wnd.OpenLayers.Projection("EPSG:4326"),           
		    controls: [
		    				new $wnd.OpenLayers.Control.TouchNavigation(
		    				{
		      					dragPanOptions: {
		          					enableKinetic: true
		      					}
		  					}),
		    				new $wnd.OpenLayers.Control.PanZoomBar(),
		    				new $wnd.OpenLayers.Control.ZoomBox(),
		    				new $wnd.OpenLayers.Control.Scale(),
		    				new $wnd.OpenLayers.Control.Attribution(),
							new $wnd.OpenLayers.Control.Navigation(),
		                	new $wnd.OpenLayers.Control.LayerSwitcher(),
		    				new $wnd.OpenLayers.Control.MousePosition(),
		    ],
		
		    numZoomLevels: 15,		
				zoom:3,
				//units: 'm',
		    maxExtent: new $wnd.OpenLayers.Bounds(-180, -90, 180, 90)
		};
		
			var map = new $wnd.OpenLayers.Map("map",  options);
		
		
			var vectors_comuni = new $wnd.OpenLayers.Layer.Vector("Comuni", {
		      projection: "EPSG:4326",
		      transparent:true,
		      isBaseLayer:true,
		      styleMap:style_comuni
		  	//  units: 'm' 
		
		});
		
		var vectors_quadranti = new $wnd.OpenLayers.Layer.Vector("Quadranti", {
		      projection: "EPSG:4326",
		      transparent:true,
		      isBaseLayer:false,
		      styleMap:style_quadranti
		    //  units: 'm' 
		
		});
		
		vectors_comuni.events.on({
		       "featureselected": function(e) {
		       		$wnd.e = e;
		       		
		       		if($wnd.esci_da_ciclo==""){
		       			//if(e.feature.maVai==0)
		       				@it.gis.egeosDCL.client.Layout.MapEvents::selezionaComune(Ljava/lang/String;Ljava/lang/String;)(e.feature.id,e.feature.fid);
//		       			else{
//		       				alert("Quadranti tutti assegnati");
//		       				e.toState("default");
//		       			}	
		            }else
		           		$wnd.esci_da_ciclo="";
		       }
		   });
		   
//		  vectors_quadranti.events.on({
//		       "featureselected": function(e) {
//		       		$wnd.e = e;
//		       		
//		       		if($wnd.esci_da_ciclo==""){
//		       			if(e.feature.sel=="0"){
//		       				//alert("seleziono quadrante");
//		       				@it.gis.egeosDCL.client.Layout.MapEvents::selezionaQuadrante(Ljava/lang/String;)(e.feature.id);
//		       				//e.feature.sel="1";
//		       			}
////		       			else
////		       				alert("Quadrante assegnato");
//		       	    }else
//		           		$wnd.esci_da_ciclo="";
//		       },
//		       "featureunselected": function(e) {
//		       		$wnd.e = e;
//		       	
//		       		if($wnd.esci_da_ciclo==""){
//		       			@it.gis.egeosDCL.client.Layout.MapEvents::deselezionaQuadrante(Ljava/lang/String;)(e.feature.id);
//		       	    }else
//		           		$wnd.esci_da_ciclo="";
//		       }
//		   });
		
		 
			var mapCenter = new $wnd.OpenLayers.LonLat(12, 42);
		
			var report = function(e) {
				$wnd.console.log(e.type, e.feature.id);
		      
		    };
		    
		    var highlightCtrl = new $wnd.OpenLayers.Control.SelectFeature(vectors_comuni, {
		        hover: true,
		        highlightOnly: true,
		        renderIntent: "temporary"
		      
		    });
		
		    var selectCtrlc = new $wnd.OpenLayers.Control.SelectFeature(vectors_comuni,
		        {clickout: true,
		         multipleKey:"ctrlKey"
		        }
		    );
		    
		     var highlightCtrl_Q = new $wnd.OpenLayers.Control.SelectFeature(vectors_quadranti, {
		        hover: true,
		        highlightOnly: true
		        //renderIntent: "temporary"
		      
		    });
		
		    var selectCtrlc_Q = new $wnd.OpenLayers.Control.SelectFeature(vectors_quadranti,
		        {clickout: true,
		      	 box: true,
			     multipleKey:"ctrlKey"
		        }
		    );
		    
		    
			$wnd.highlightCtrl = highlightCtrl;
			$wnd.selectCtrlc = selectCtrlc;
//			$wnd.highlightCtrl_Q = highlightCtrl_Q;
//			$wnd.selectCtrlc_Q = selectCtrlc_Q;
			
//			map.addControl($wnd.highlightCtrl_Q);
//		    map.addControl($wnd.selectCtrlc_Q);
		    map.addControl($wnd.highlightCtrl);
		    map.addControl($wnd.selectCtrlc);
		
		$wnd.map = map;
		$wnd.vectors_quadranti = vectors_quadranti;
		
		$wnd.vectors_comuni = vectors_comuni;
		
		
		$wnd.map.addLayers([$wnd.vectors_comuni,$wnd.vectors_quadranti]);

}-*/;
	
	/**
	 * DISATTIVA LA SELEZIONE AD AREA SULLA MAPPA
	 */
	public static native void disattivaSelezioneByBox()  /*-{
	
	
	$wnd.selectCtrlc_Q.box = false;
    $wnd.selectCtrlc_Q.deactivate();
  	$wnd.selectCtrlc_Q.activate();
}-*/;
	

	   
	public static native void toggleControl(String comando,String idComune)  /*-{
     
        alert("comando"+comando);
 		var ctls = $wnd.map.getControl("selecthover");     
 		ctls.activate();
	}-*/;
	
	/**
	 * ATTIVA LA SELEZIONE AD AREA SULLA MAPPA
	 * @param active
	 */
	public static native void attivaSelezioneByBox(boolean active)  /*-{
    	
    	
//    	$wnd.selectCtrlc_Q.box = active;
// 	  	
//        $wnd.highlightCtrl_Q.activate();
// 
//        $wnd.selectCtrlc_Q.deactivate();
//	  	$wnd.selectCtrlc_Q.activate();
}-*/;
	
	
	/**
	 * POSIZIONA LA MAPPA PER LE COORDINATE PASSATE IN INPUT
	 * @param minX
	 * @param minY
	 * @param maxX
	 * @param maxY
	 */
	public static native void mapZoomToExtent(Float minX, Float minY, Float maxX, Float maxY) /*-{
		bounds = new $wnd.OpenLayers.Bounds(minX,minY,maxX,maxY);
		$wnd.map.zoomToExtent(bounds);
}-*/;
	
	/**
	 * RIDIMENSIONA LA MAPPA
	 * @param nameMap
	 * @param cheight
	 * @param cwidth
	 */
	public static native void resizePanel(String nameMap, int cheight, int cwidth) /*-{
	var mapdiv = $wnd.document.getElementById(nameMap);
	if(mapdiv != undefined)
	{
		mapdiv.style.height = cheight + 'px';
		mapdiv.style.width = (cwidth-2000) + 'px';
	}

}-*/;
	
	/**
	 * ATTIVA LA FUNZIONE DI SPOSTAMENTO DELLE MAPPA
	 * @param pan
	 */
	public static native void panmove(boolean pan) /*-{
	var ctls = $wnd.map.getControlsByClass('OpenLayers.Control.PanZoomBar');
	if (pan) {
			ctls[0].activate();										
		} else {
			ctls[0].deactivate();
		}
	}-*/;

	/**
	 * ATTIVA LO ZOOM
	 * @param attivo
	 */
	public static native void mapZoomBox(boolean attivo) /*-{	

		var ctls = $wnd.map.getControlsByClass('OpenLayers.Control.ZoomBox');		

		if (attivo) {

			ctls[0].activate();										
		} else {

			ctls[0].deactivate();
		}	
}-*/;

	/**
	 * ATTIVA IL LIVELLO TRAMITE WKT
	 * @param titoloLivello
	 * @param is_checked
	 */
	public native static void attivaLivelloWKT(String titoloLivello, boolean is_checked) /*-{	


		var layer = $wnd.map.getLayersByName(titoloLivello);     		 	
	 	if (layer != "") 
		{
		 	layer[0].setVisibility(is_checked);
		}	 	
		layer[0].redraw({force:true});
	}-*/;

	/**
	 * DISTRUGGE LA MAPPA
	 */
	public static native void destroyMap() /*-{
	//alert("distruggo mappa");
	// alert("mappa "+$wnd.map);
	app = $wnd.document.getElementById("map");
	app.innerHTML="";
//	if($wnd.map!=undefined)
//		$wnd.map.destroy();
}-*/;
	
	
	/**
	 * SELEZIONA IL COMUNE
	 * 
	 * @param idComune
	 * @param descComune
	 */
	public static native void selezionaFeatureComuni(String idComune,String descComune)
	/*-{
	    $wnd.esci_da_ciclo = "OK";
	 	var layer =  $wnd.map.getLayersByName("Comuni");   
	 	
	 	
	 	var featureSelected = layer[0].getFeatureById(idComune);
	 
	 	$wnd.featureSelected = featureSelected;
	 	
	 	//alert("ID FEATURE SELECTED: "+$wnd.featureSelected.id);
	 	
	 	$wnd.selectCtrlc.select(featureSelected);
		$wnd.layer = layer;
	  	
	}-*/;
	
	
	/**
	 * SELEZIONA LA PROVINCIA
	 * @param idProvincia
	 */
	public static native void selezionaFeatureProvince(String idProvincia)
	/*-{
	    $wnd.esci_da_ciclo = "OK";
	 	var layer =  $wnd.map.getLayersByName("Province");   
	 	
	 	
	 	var featureSelected = layer[0].getFeatureById(idProvincia);
	 	//alert("ID REGIONE "+idProvincia);
	 	$wnd.featureSelected = featureSelected;
	 	$wnd.selectCtrlc.select(featureSelected);
		$wnd.layer = layer;
        $wnd.highlightCtrl.activate();
	  	$wnd.selectCtrlc.activate();
	  	 
	  	
	}-*/;
	
	public static native void attivaSelezione()
	/*-{
        $wnd.highlightCtrl.activate();
	  	$wnd.selectCtrlc.activate();
	}-*/;
	
	public static native void panMove()
	/*-{
        $wnd.highlightCtrl.deactivate();
	  	$wnd.selectCtrlc.deactivate();
//	  	$wnd.selectCtrlc_Q.deactivate();
//	  	$wnd.highlightCtrl_Q.deactivate();
	}-*/;
	
	public static native void attivaSelezione_PROV()
	/*-{
	 	$wnd.highlightCtrl.deactivate();
	  	$wnd.selectCtrlc.deactivate();
        $wnd.highlightCtrl_PROV.activate();
	  	$wnd.selectCtrlc_PROV.activate();
	}-*/;

	
	/**
	 * ATTIVA LA SELEZIONE DEI QUADRANTI
	 */
	public static native void attivaSelezione_Quadranti()
	/*-{
	 	$wnd.highlightCtrl.deactivate();
	  	$wnd.selectCtrlc.deactivate();
//        $wnd.highlightCtrl_Q.activate();
//	  	$wnd.selectCtrlc_Q.activate();
	}-*/;
	
	
	/**
	 * AGGUINGE IL WKT DELLE REGIONI
	 * @param nomeLivello
	 * @param string_wkt
	 * @param fuso
	 * @param idRegi
	 * @param denoRegi
	 * @param conta
	 * @param colore
	 */
	public static native void addWKTRegioni(String nomeLivello,String string_wkt,String fuso,String idRegi,String denoRegi,int conta,String colore) /*-{

	  try
	  {
	  		
			var appo_srid = ""; 
			
 			var wkt_1 = new $wnd.OpenLayers.Format.WKT();		
 			var polygonFeature = wkt_1.read(string_wkt);
 			var color = "#FFFFFF";
 			var centroid = polygonFeature.geometry.getCentroid().toString();
 			
			polygonFeature.id = idRegi;
		
		 	if(fuso=="W") {
		 		
		 		appo_srid = new $wnd.OpenLayers.Projection("EPSG:3003");
			}
    		else{
    			appo_srid = new $wnd.OpenLayers.Projection("EPSG:3004");
    			   
			}
				polygonFeature.geometry = polygonFeature.geometry.transform(appo_srid, $wnd.map.getProjectionObject()); 
				
	 			$wnd.vectors_regioni.addFeatures(polygonFeature); 

			  $wnd.vectors_regioni.features[conta].attributes={l:denoRegi,C:colore};
			  

  	  		  $wnd.vectors_regioni.redraw({force:true});
  	  		  
        	$wnd.map.zoomToExtent($wnd.vectors_regioni.getDataExtent());
        }
	  catch(exc)
	  {
	  	 alert("Eccezione" +exc.message);
	
	  }  

}-*/;
	
	/**
	 * AGGIUNGE IL WKT DELLE PROVINCE
	 * @param nomeLivello
	 * @param string_wkt
	 * @param fuso
	 * @param idProv
	 * @param denoProv
	 * @param conta
	 * @param colore
	 * @param sel
	 * @param figlio
	 */
	public static native void addWKTProvince(String nomeLivello,String string_wkt,String fuso,String idProv,String denoProv,int conta,String colore,int sel,String figlio) /*-{

//	  try
//	  {
	  		
	  		var appo_srid = "";
			var wkt_1 = new $wnd.OpenLayers.Format.WKT();		
			
			var polygonFeature = wkt_1.read(string_wkt);
		
			polygonFeature.id = idProv;
			
			polygonFeature.fid = denoProv;
			
			polygonFeature.sel = sel;
		//	alert("FIGLIO IN ADDWKT PROV_ "+figlio);
			polygonFeature.figlio = figlio;
			
			
			
		//	var x_label = centroid.substring(0,centroid.indexOf(" "));
		//	var y_label = centroid.substring(centroid.indexOf(" "),centroid.length);
		 	
		 	var x_label = 0;
		 	var y_label = 0;
		//	alert("POSIZIONE X: "+x_label);
		//	alert("POSIZIONE Y: "+y_label);
			
			
			if(fuso=="W") {
		 		appo_srid = new $wnd.OpenLayers.Projection("EPSG:3003");
				
    		}
    		else
    			appo_srid = new $wnd.OpenLayers.Projection("EPSG:3004");      
			
			polygonFeature.geometry = polygonFeature.geometry.transform(appo_srid, $wnd.map.getProjectionObject()); 
			var centroid = polygonFeature.geometry.getCentroid().toString();
			
			
			
			//alert("CENTROID: "+centroid);
			$wnd.vectors_province.addFeatures(polygonFeature); 
			
      		//$wnd.vectors_province.features[conta].attributes={l:denoProv,C:colore,xOffset:x_label,yOffset:y_label};
			$wnd.vectors_province.features[conta].attributes={l:denoProv,C:colore};
			$wnd.vectors_province.redraw({force:true});
      		
      		$wnd.map.addLayer($wnd.vectors_province);

   			$wnd.map.zoomToExtent($wnd.vectors_province.getDataExtent());
      		        
 //     }
//	  catch(exc)
//	  {
//	  	 alert("Eccezione"+exc.message);
//	
//	  }  

}-*/;
	
	/**
	 * RIMUOVE IL LAYER DELLE PROVINCE
	 */
	public static native void removeFeatureWKTProvince() /*-{

	  try
	  {
	
			$wnd.vectors_province.removeAllFeatures();
    		$wnd.vectors_province.redraw({force:true});
    		        
    }
	  catch(exc)
	  {
	  	 alert("Eccezione"+exc.message);
	
	  }  

}-*/;
	
	
	/**
	 * RIMUOVE IL LAYER DEI COMUNI
	 */
	public static native void removeFeatureWKTComuni() /*-{

	  try
	  {
	
			$wnd.vectors_comuni.removeAllFeatures();
  			$wnd.vectors_comuni.redraw({force:true});
  		        
  		}
	  catch(exc)
	  {
	  	 alert("Eccezione"+exc.message);
	
	  }  

}-*/;
	
	/**
	 * RIMUOVE IL LAYER DEI QUADRANTI
	 */
	public static native void removeFeatureWKTQuadranti() /*-{

	  try
	  {
	
			$wnd.vectors_quadranti.removeAllFeatures();
		//	$wnd.vectors_quadranti.redraw({force:true});
		        
		}
	  catch(exc)
	  {
	  	 alert("Eccezione"+exc.message);
	
	  }  

}-*/;
	
	
	/**
	 * EFFETTUA LO ZOOM SUL LAYER DELLE REGIONI
	 */
	public static native void zoomToRegione() /*-{

	  try
	  {
	
			$wnd.vectors_comuni.removeAllFeatures();
			$wnd.vectors_comuni.redraw({force:true});
		        
		}
	  catch(exc)
	  {
	  	 alert("Eccezione"+exc.message);
	
	  }  

}-*/;
	
	/**
	 * AGGIUNGE IL LAYER DEI COMUNI
	 * @param nomeLivello
	 * @param string_wkt
	 * @param idComune
	 * @param descComune
	 * @param fuso
	 * @param conta
	 * @param colore
	 */
	public static native void addWKTComuni(String nomeLivello,String string_wkt,String idComune,String descComune,String fuso,int conta,String colore) /*-{


	  		var appo_srid;
			var wkt_1 = new $wnd.OpenLayers.Format.WKT();		
			var polygonFeature = wkt_1.read(string_wkt);
		//	alert("id comune: "+idComune);
			polygonFeature.id = idComune;
			polygonFeature.fid = descComune;
			
			if(colore=="#3835FF"){
				polygonFeature.maVai = 1; 
			}
			else
				polygonFeature.maVai = 0;
				
			if(fuso=="W") {
		 		appo_srid = new $wnd.OpenLayers.Projection("EPSG:3003");
				
    		}
    		else
    			appo_srid = new $wnd.OpenLayers.Projection("EPSG:3004");      
			
			polygonFeature.geometry = polygonFeature.geometry.transform(appo_srid, $wnd.map.getProjectionObject()); 
			
			$wnd.vectors_comuni.addFeatures(polygonFeature); 
    				     
    		$wnd.vectors_comuni.features[conta].attributes={l:descComune,C:colore};
    		$wnd.vectors_comuni.redraw({force:true});		     
    		$wnd.map.zoomToExtent($wnd.vectors_comuni.getDataExtent());
    		$wnd.map.addLayer($wnd.vectors_comuni);        
 

}-*/;
	
	/**
	 * RIMUOVE IL LAYER DEI QUADRANTI
	 */
	public static native void removeAllFeaturesQuadranti() /*-{
		$wnd.vectors_quadranti.removeAllFeatures();
	}-*/;
	
	/**
	 * AGGIUNGE IL LAYER DEI QUADRANTI
	 * @param nomeLivello
	 * @param string_wkt
	 * @param idQuadrante
	 * @param fuso
	 * @param colore
	 * @param conta
	 * @param sel
	 */
	public static native void addWKTQuadranti(String nomeLivello,String string_wkt,String idQuadrante,String fuso,String colore,int conta,String sel) /*-{
	  try
	  {
	  		
	  		
	  		
	  		var appo_srid ="";
			var wkt_1 = new $wnd.OpenLayers.Format.WKT();		
			var polygonFeature = wkt_1.read(string_wkt);
			
			polygonFeature.id = idQuadrante;
			polygonFeature.sel=sel;
			
			if(fuso=="W") {
		 		appo_srid = new $wnd.OpenLayers.Projection("EPSG:3003");
			}
    		else
    			appo_srid = new $wnd.OpenLayers.Projection("EPSG:3004");      
			
			polygonFeature.geometry = polygonFeature.geometry.transform(appo_srid, $wnd.map.getProjectionObject()); 
			
			$wnd.vectors_quadranti.addFeatures(polygonFeature); 
  			  
//  		if ($wnd.vectors_quadranti.features.length > 1) {
//		        //eseguiamo la [Union] fra le due geometrie 
//		        if(sel=="1"){
//		        	@it.gis.egeosDCL.client.map.OLMap_new::executeJSTS(Ljava/lang/String;)("Union");
//		        }
//		        
//		    }    
  			//alert(1);  
  			$wnd.vectors_quadranti.features[$wnd.vectors_quadranti.features.length-1].attributes={C1:colore};

  			$wnd.map.zoomToExtent($wnd.vectors_quadranti.getDataExtent());
  		//	$wnd.map.addLayer($wnd.vectors_quadranti);        
  	
  }
	  catch(exc)
	  {
	  	 alert("Eccezione"+exc.message);
	
	  }  

}-*/;
	
	
	public static native void addWKTQuadrantiBUONO(String nomeLivello,String string_wkt,String idQuadrante,String fuso,String colore,int conta) /*-{
	  try
	  {
	  
	  
	  		var appo_srid ="";
			var wkt_1 = new $wnd.OpenLayers.Format.WKT();		
			var polygonFeature = wkt_1.read(string_wkt);
			
			polygonFeature.id = idQuadrante;
			
			if(fuso=="W") {
		 		appo_srid = new $wnd.OpenLayers.Projection("EPSG:3003");
			}
  		else
  			appo_srid = new $wnd.OpenLayers.Projection("EPSG:3004");      
			
			polygonFeature.geometry = polygonFeature.geometry.transform(appo_srid, $wnd.map.getProjectionObject()); 
			
			$wnd.vectors_quadranti.addFeatures(polygonFeature); 
			  
			
			$wnd.vectors_quadranti.features[conta].attributes={C1:colore,l:idQuadrante};

			$wnd.map.zoomToExtent($wnd.vectors_quadranti.getDataExtent());
			$wnd.map.addLayer($wnd.vectors_quadranti);        
	
}
	  catch(exc)
	  {
	  	 alert("Eccezione"+exc.message);
	
	  }  

}-*/;
	/**
	 * AGGIORNA IL LAYER DEI QUADRANTI
	 */
	public static native void refreshQuadranti() /*-{
	
	
//	$wnd.map.getLayersByName('Quadranti')[0].setVisibility(false);
//	$wnd.map.getLayersByName('Quadranti')[0].setVisibility(true);
//	$wnd.map.zoomOut(2);

	
	$wnd.map.getLayersByName('Quadranti')[0].redraw();
	}-*/;
	

	
	public static native void cleanAllFeatures()
	 /*-{

		$wnd.vectors.removeAllFeatures();
		// $wnd.map.setCenter($wnd.map.getCenter(),$wnd.map.getZoom()-1);
	}-*/;
	


	public native static void dragGeom(boolean is_checked) /*-{	
		$wnd.drag.activate();
}-*/;
	
	public static native void openUrl(String url) /*-{
	var w = $wnd.window.open(url);	
}-*/;
	public static native void measureLine(boolean active) /*-{
	   var ctls = $wnd.map.getControl("ol_measureLine");     
	    if (active) {               
	        ctls.activate();       
	        ctls.geodesic = true;                                   
	    } else {
	        ctls.deactivate();
	    }   
	}-*/;

	public static native void measureArea(boolean active) /*-{
	    var ctls = $wnd.map.getControl("ol_measureArea");                
	    //alert("DENTRO measureArea");
	    if (active) {       
	        ctls.activate();
	        ctls.geodesic = true;                                       
	    } else {
	        ctls.deactivate();
	    }   
	}-*/;

}