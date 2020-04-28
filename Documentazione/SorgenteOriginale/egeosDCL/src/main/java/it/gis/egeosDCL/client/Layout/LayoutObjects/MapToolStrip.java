package it.gis.egeosDCL.client.Layout.LayoutObjects;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import it.gis.egeosDCL.client.egeosDCL;
import it.gis.egeosDCL.client.Layout.LeftLayout;
import it.gis.egeosDCL.client.ctx.UserAssignWindow;
import it.gis.egeosDCL.client.map.OLMap_new;
import com.smartgwt.client.widgets.menu.Menu;
import com.smartgwt.client.widgets.menu.MenuButton;
import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.rpc.RPCCallback;
import com.smartgwt.client.rpc.RPCManager;
import com.smartgwt.client.rpc.RPCRequest;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.types.SelectionType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

import com.smartgwt.client.widgets.menu.MenuItem;
import com.smartgwt.client.widgets.menu.events.ItemClickEvent;
import com.smartgwt.client.widgets.menu.events.ItemClickHandler;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * CREA LA PULSANTIERA DELLA MAPPA
 * @version 1.0
 * 
 */
public class MapToolStrip extends ToolStrip{

	
	private static ToolStripButton 	exportXLS_bt;
	private static ToolStripButton 	zoomBOX_bt;
	private static ToolStripButton 	panMove_bt;
	private static ToolStripButton 	info_bt;
	private static ToolStripButton 	drag_bt;
	private static ToolStripButton 	snap_bt;
	private static ToolStripButton 	print_bt;
	private static ToolStripButton 	logout_bt;
	private static ToolStripButton 	back_to_regioni_bt;
	
	private static DynamicForm formCancQuadranti = null;
	
	private static  TextItem quadranteDaCanc = null;
	private static ToolStripButton 	cancAssociazione = null;
	
    private static ToolStripButton upload_csv;
	
	public static  Label 			utente_connesso 	= null;
	public static  Label 			contesto_sel   	 	= null;
	ListGridRegioniJSON 			listRegJSON 		= null;
	WinLoading     					winLoad	   			= new WinLoading();
	private static SelectItem 		selectItem 			= null;
	private static Menu 			menu 				= null;
	
	private static ToolStripButton btMeasure;

	private static ToolStripButton provaWKT_bt;
	
	private static HLayout layCancQuadranti = null;

	public MapToolStrip()
	{
		super();
		this.setWidth100();
		utente_connesso = new Label();
		utente_connesso.setContents("Utente:"+egeosDCL.getDescUtente());
		this.setID("ID_MAP_TOOLSTRIP");
		contesto_sel = new Label();
		contesto_sel.setContents("Contesto:"+egeosDCL.getNumElenco());
		selectItem = new SelectItem();  
		
		logout_bt = new ToolStripButton();
		logout_bt.setIcon("close2.png");
		logout_bt.setTooltip("Logout");
		
		btMeasure = new ToolStripButton();
		
		btMeasure.setIcon("measure.png");
		btMeasure.setTooltip("Misura");
		btMeasure.setCanFocus(false);
		btMeasure.setTitle("");
//		btMeasure.addClickHandler(new ClickHandler() {  
//            public void onClick(ClickEvent event) {
//            	
//            	OLMap_new.mapZoomBox(false);
//            	OLMap_new.panmove(false);
//            	
//            	if (it.gis.egeosDCL.client.map.Measure.getWinMeasure() != null) {
//            		it.gis.egeosDCL.client.map.Measure.delete();
//				} 
//            	it.gis.egeosDCL.client.map.Measure.create(1);
//			}  
//        });
		
		provaWKT_bt= new ToolStripButton();
		zoomBOX_bt = new ToolStripButton();
		panMove_bt = new ToolStripButton();
		info_bt	   = new ToolStripButton();
		drag_bt	   = new ToolStripButton();

		panMove_bt.setIcon("icon_pan.png");
		panMove_bt.setTooltip("Sposta");
		panMove_bt.setWidth(30);
		panMove_bt.setTitle("");
		panMove_bt.setActionType(SelectionType.CHECKBOX); 
		panMove_bt.setRadioGroup("PROVA");
		panMove_bt.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				OLMap_new.panmove(panMove_bt.getSelected());
				if(egeosDCL.getLivelloRuolo().equals("3")){
					OLMap_new.mapZoomBox(false);	
					OLMap_new.panMove();
				}
			}
		});

		selectItem.setTitle("Logo");  

		LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();  
		valueMap.put("sian", "&nbsp;&nbsp;<b>Logo SIAN<br></b>");  
		valueMap.put("sin", "&nbsp;&nbsp;<b>Logo SIN</b>");  
		valueMap.put("agea", "&nbsp;&nbsp;<b>Logo AGEA</b>");  
	

		selectItem.setValueMap(valueMap);  
		selectItem.setImageURLSuffix(".jpg");  
		selectItem.setColSpan(10);
		selectItem.setCellHeight(10);
		selectItem.setValueIconSize(25);
		selectItem.setIconHeight(25);

		LinkedHashMap<String, String> valueIcons = new LinkedHashMap<String, String>();  
		valueIcons.put("sian", "sian");  
		valueIcons.put("sin", "sin");  
		valueIcons.put("agea", "agea");  
		

		selectItem.setValueIcons(valueIcons);
		selectItem.setDefaultValue("sin");

		///////////////////////////////////////////////////////
			///////////////////////////////////////////////////////
		back_to_regioni_bt = new ToolStripButton();
		back_to_regioni_bt.setIcon("italia.jpg");
		back_to_regioni_bt.setWidth(30);
		back_to_regioni_bt.setTooltip("Torna alla mappa");
		back_to_regioni_bt.setTitle("");
		back_to_regioni_bt.setActionType(SelectionType.CHECKBOX); 
		back_to_regioni_bt.setRadioGroup("PROVA");
		back_to_regioni_bt.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				winLoad.show();
				if(egeosDCL.getLivelloRuolo().equals("1"))
				{
					OLMap_new.destroyMap();
					/////////////////////////////////////////////////////
					RPCRequest request = new RPCRequest();
					// Note data could be a String, Map or Record
					
					request.setData("Some data to send to the client");
					request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=regioniRefreshService&numElenco="+egeosDCL.getNumElenco());
					RPCManager.sendRequest(request, new RPCCallback() {
						
						
						public void execute(RPCResponse response, Object rawData, RPCRequest request) {
							// TODO Auto-generated method stub
							JSONValue value = JSONParser.parseLenient(rawData+"");
							
							if(value.isObject()!=null)
							{
								JSONObject obj = value.isObject();
								JSONValue app = obj.get("TodoLista");
								JSONArray arr = app.isArray();
								
								if(arr!=null)
								{
									egeosDCL.setArrRegJSON(arr);

									OLMap_new.loadOLERegioni("map", "", "EPSG:3004");	
									
									for(int i = 0;i<arr.size();i++)
									{
										JSONObject ob = arr.get(i).isObject();
										String srid = "";
										
										String colore = egeosDCL.calcolaColoreRegione(new Integer(ob.get("tot").toString().replace("\"","")),
																				new Integer(ob.get("lavorate").toString().replace("\"","")),
																				new Integer(ob.get("assegnate").toString().replace("\"","")),
																				new Integer(ob.get("noassegnate").toString().replace("\"","")),0);
										////System.out.println("COLORE REGIONE::::: "+colore );
										OLMap_new.addWKTRegioni("Regioni",ob.get("wkt").toString().replace("\"",""),ob.get("fuso").toString().replace("\"",""),ob.get("id_regi").toString().replace("\"",""),ob.get("deno_regi").toString().replace("\"",""),i,colore);
										////System.out.println("CARICO MAP: "+ob.get("id_regi").toString().replace("\"","")+" -- "+ob.get("deno_regi").toString().replace("\"",""));
									}
									
									OLMap_new.attivaSelezione();
									if(LeftLayout.getvLayoutGridRegioni().getMember("REGIONI_GRID_ID")!=null)
									{
										LeftLayout.getvLayoutGridRegioni().getMember("REGIONI_GRID_ID").destroy();
										listRegJSON = new ListGridRegioniJSON();
										LeftLayout.getvLayoutGridRegioni().addMember(listRegJSON);
									}
									else
									{
										listRegJSON = new ListGridRegioniJSON();
										LeftLayout.getvLayoutGridRegioni().addMember(listRegJSON);
									}
									markForRedraw();
									winLoad.hide();
								}
							}
						}
					});
					/////////////////////////////////////////////////////
					for(int i = 0;i<egeosDCL.getArrRegJSON().size();i++)
					{
						JSONObject ob = egeosDCL.getArrRegJSON().get(i).isObject();
						String srid = "";
						String colore = egeosDCL.calcolaColoreRegione(new Integer(ob.get("tot").toString().replace("\"","")),
								new Integer(ob.get("lavorate").toString().replace("\"","")),
								new Integer(ob.get("assegnate").toString().replace("\"","")),
								new Integer(ob.get("noassegnate").toString().replace("\"","")),0);
						////System.out.println("COLORE REGIONE::::: "+colore );
								OLMap_new.addWKTRegioni("Regioni",ob.get("wkt").toString().replace("\"",""),ob.get("fuso").toString().replace("\"",""),ob.get("id_regi").toString().replace("\"",""),ob.get("deno_regi").toString().replace("\"",""),i,colore);
					}
					if(LeftLayout.getvLayoutGridRegioni().getMember("PROVINCE_GRID_ID")!=null){
						
						LeftLayout.getvLayoutGridRegioni().getMember("PROVINCE_GRID_ID").destroy();
						LeftLayout.getvLayoutGridRegioni().getMember("ID_TOOLSTRIP_PROVINCE").destroy();
						ListGridContestiJSON.getComboRegioni().setValue(0);
					}
					OLMap_new.attivaSelezione();
				}
				else if(egeosDCL.getLivelloRuolo().equals("2"))
				{
//					//System.out.println("ruolo 2");
//				
//					OLMap_new.destroyMap();
//					OLMap_new.loadOLERegioni("map","","EPSG:3004");
//					
//					for(int i = 0;i<egeosDCL.getArrRegJSON().size();i++)
//					{
//						JSONObject ob = egeosDCL.getArrRegJSON().get(i).isObject();
//						String srid = "";
//						String colore = egeosDCL.calcolaColore(new Integer(ob.get("tot").toString().replace("\"","")),
//								new Integer(ob.get("lavorate").toString().replace("\"","")),
//								new Integer(ob.get("assegnate").toString().replace("\"","")),
//								new Integer(ob.get("noassegnate").toString().replace("\"","")),0);
//						//System.out.println("COLORE REGIONE::::: "+colore );
//								OLMap_new.addWKTRegioni("Regioni",ob.get("wkt").toString().replace("\"",""),ob.get("fuso").toString().replace("\"",""),ob.get("id_regi").toString().replace("\"",""),ob.get("deno_regi").toString().replace("\"",""),i,colore);
//					}
//					
//					if(LeftLayout.getvLayoutGridRegioni().getMember("COMUNI_GRID_ID")!=null){
//						//System.out.println("DISTRUGGO GRID COMUNI");
//						LeftLayout.getvLayoutGridRegioni().getMember("COMUNI_GRID_ID").destroy();
//						
//					}
//					if(LeftLayout.getvLayoutGridRegioni().getMember("PROVINCE_GRID_ID")!=null){
//						LeftLayout.getvLayoutGridRegioni().getMember("PROVINCE_GRID_ID").destroy();
//					}
//					ListGridContestiJSON.getComboRegioni().setValue(0);
//					ListGridContestiJSON.getComboProvince().setDisabled(true);
//					ListGridContestiJSON.getComboProvince().setValue(0);
//					ToolStripButtonComuni.getProvinciaSel().clearValue(); 
//					OLMap_new.attivaSelezione();
//					winLoad.hide();
				}
				else if(egeosDCL.getLivelloRuolo().equals("3"))
				{					
					
					ListGridContestiJSON.getBt_valida().setDisabled(true);
					OLMap_new.destroyMap();
					OLMap_new.loadOLEProvince("map","","EPSG:3004");

					RPCRequest request = new RPCRequest();
					request.setData("Some data to send to the client");
					request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=provinceSocioProvincialeService&numElenco="+egeosDCL.getNumElenco()+"&idUtente="+egeosDCL.getIdUtente());
					
					if(egeosDCL.getDett_layout().getMember("DettaglioComuniListGrid_ID")!=null){
				
						egeosDCL.getDett_layout().getMember("ID_DETT_TOOLSTRIP_COMUNI").destroy();
						egeosDCL.getDett_layout().getMember("DettaglioComuniListGrid_ID").destroy();
						egeosDCL.getDett_layout().hide();

					}
					
					
					RPCManager.sendRequest(request, new RPCCallback() {
						
						
						public void execute(RPCResponse response, Object rawData, RPCRequest request) {
							JSONValue value = JSONParser.parseLenient(rawData+"");

							if(value.isObject()!=null)
							{
								JSONObject obj = value.isObject();
								JSONValue app = obj.get("TodoLista");
								JSONArray arr = app.isArray();
								
								if(arr!=null)
								{
									winLoad.hide();
									
									for(int i = 0;i<arr.size();i++)
									{
										JSONObject ob = arr.get(i).isObject();
										String colore = egeosDCL.calcolaColoreProvince(new Integer(ob.get("tot").toString().replace("\"","")),
												new Integer(ob.get("lavorate").toString().replace("\"","")),
												new Integer(ob.get("assegnate").toString().replace("\"","")),
												new Integer(ob.get("noassegnate").toString().replace("\"","")),new Integer(ob.get("sel").toString().replace("\"","")),new Integer(ob.get("stato").toString().replace("\"","")));
										
										OLMap_new.addWKTProvince("Province", ob.get("wkt").toString().replace("\"",""), ob.get("fuso").toString().replace("\"",""),ob.get("idProv").toString().replace("\"",""), ob.get("denoProv").toString().replace("\"",""),i,colore,new Integer(ob.get("sel").toString().replace("\"","")),ob.get("figlio")!=null?ob.get("figlio").toString().replace("\"",""):"");
									}
								
									OLMap_new.attivaSelezione();
								
								
								}
								
							}
						}
					});
					if(LeftLayout.getvLayoutGridRegioni().getMember("COMUNI_GRID_ID")!=null){
						
						LeftLayout.getvLayoutGridRegioni().getMember("COMUNI_GRID_ID").destroy();
						ListGridContestiJSON.getComboProvince().setValue("Selezionare una provincia");
					}
					else
					{
						ListGridContestiJSON.getComboProvince().setValue(0);
					}
//					cancAssociazione = new ToolStripButton();
//					cancAssociazione.setTooltip("Resetta assegnazioni");
//					cancAssociazione.setIcon("disassocia.jpg");
//					cancAssociazione.addClickHandler(new ClickHandler() {
//						
//						
//						public void onClick(ClickEvent event) {
//							// TODO Auto-generated method stub
//							System.out.println("PREMUTO RESETTA ASSEGNAZIONI");
//							
//						}
//					});
					

//					ToolStripButtonQuadranti.getBtAssegna_all().setDisabled(true);
//					ToolStripButtonQuadranti.getBtAssegna().setDisabled(true);
					
					
					winLoad.hide();
				}	
				
			}
		});
	
		drag_bt.setIcon("drag_geom.gif");
		drag_bt.setWidth(30);
		drag_bt.setTooltip("Sposta la geometria a mano libera");
		drag_bt.setTitle("");
		drag_bt.setActionType(SelectionType.CHECKBOX); 
		drag_bt.setRadioGroup("PROVA");
		drag_bt.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				OLMap_new.panmove(false);
				OLMap_new.dragGeom(drag_bt.getSelected()) ;	
			}
		});
		drag_bt.setDisabled(true);

		zoomBOX_bt.setIcon("drag-rectangle-on.png");
		
		if(egeosDCL.getLivelloRuolo().equals("3")){
			zoomBOX_bt.setTooltip("Seleziona area di quadranti");
		}
		else
			zoomBOX_bt.setTooltip("Seleziona area");
		
		
		zoomBOX_bt.setWidth(30);
		zoomBOX_bt.setTitle("");
		zoomBOX_bt.setActionType(SelectionType.CHECKBOX); 
		zoomBOX_bt.setRadioGroup("PROVA");
		
		zoomBOX_bt.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				//OLMap_new.mapZoomBox(zoomBOX_bt.getSelected());
				//OLMap_new.panmove(false);
				if(egeosDCL.getLivelloRuolo().equals("3"))
					////System.out.println("zoomBOX_bt.getSelected()"+zoomBOX_bt.getSelected());
					OLMap_new.attivaSelezioneByBox(zoomBOX_bt.getSelected());
				
			//	OLMap_new.print();
			}
		});
		
        print_bt = new ToolStripButton();
        print_bt.setWidth(30);
        print_bt.setIcon("printPDF.jpg");
        print_bt.setTooltip("Stampa in pdf");
        print_bt.addClickHandler(new ClickHandler() {

            
            public void onClick(ClickEvent event) {
                // TODO Auto-generated method stub
                Window.open(
                        "http://www.sourcedemo.net:8001/provaPrint/provaPrint/ServletPrintPDF?grid_id=1173709&X_MIN=41.838597335548&X_MAX=15.442506381374&Y_MIN=15.469285556178&Y_MAX=41.857415713996",
                        "_blank", "width=600,height=550,scrollbars=yes,resizable=yes");
            }
        });

        logout_bt.addClickHandler(new ClickHandler() {

            
            public void onClick(ClickEvent event) {
                ////System.out.println("GWT.getModuleBaseURL()::::::::: "+GWT.getModuleBaseURL());
                //egeosDCL.redirectGwt(GWT.getModuleBaseURL()+"egeosDCL.html?gwt.codesvr=127.0.0.1:9997");
                egeosDCL.redirectGwt(GWT.getModuleBaseURL() + "egeosDCL.html");
            }
        });
        
        print_bt.setDisabled(true);

        //

        upload_csv = new ToolStripButton();
        upload_csv.setWidth(30);
        upload_csv.setIcon("upload_csv.jpg");
        upload_csv.setTooltip("Caricamento Fotointerpreti");
        upload_csv.addClickHandler(new ClickHandler() {

            
            public void onClick(ClickEvent event) {
                // XXX            
                WinFileUpload wfu = new WinFileUpload();
               
//                String content = wfu.iframe.getElement().getInnerText();
//                System.out.println("content = " + content);
//                content = wfu.iframe.getElement().getInnerHTML();
//                System.out.println("content = " + content);
//                content = wfu.iframe.getElement().getString();
//                System.out.println("content = " + content);
//                
//                content = wfu.formUpload.getContents();
//                System.out.println("content = " + content);
            }
        });
        
        //
       
        
        
        this.addButton(back_to_regioni_bt);
        //this.addButton(zoomBOX_bt);
        this.addButton(panMove_bt);
        this.addButton(print_bt);

       	upload_csv.setDisabled(true);

       	this.addButton(upload_csv);
       	
       	VLayout layResetQuad = new VLayout();
       	
       	formCancQuadranti = new DynamicForm();
       	quadranteDaCanc = new TextItem("Grid_id");
       	quadranteDaCanc.setKeyPressFilter("[0-9]");  
       	formCancQuadranti.setItems(quadranteDaCanc);
       	cancAssociazione = new ToolStripButton();
       	
       	cancAssociazione.addClickHandler(new ClickHandler() {
			
			
			public void onClick(ClickEvent event) {
				
				System.out.println("cancAssociazione: "+quadranteDaCanc.getValueAsString());
				
				if(quadranteDaCanc.getValueAsString()!=null && !quadranteDaCanc.getValueAsString().equals("")){
					
					winLoad.show();
					
					RPCRequest request = new RPCRequest();

					request.setData("Some data to send to the client");
					
					request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=RESETTA_QUADRANTI&grid_id="+quadranteDaCanc.getValueAsString()+"&user_id="+egeosDCL.getDescUtente());
					
					RPCManager.sendRequest(request, new RPCCallback() {

						
						public void execute(RPCResponse response, Object rawData, RPCRequest request) {
							
							if (response.getStatus()>0){
							
								JSONValue value = JSONParser.parseLenient(rawData+"");
	
								if(value.isObject()!=null)
								{
									JSONObject obj = value.isObject();
									JSONValue app = obj.get("TodoLista");
									JSONArray arr = app.isArray();
	
									if(arr!=null)
									{
										winLoad.destroy();
										egeosDCL.fireMessage(rawData);
									}
									else
										winLoad.destroy();
								}
							}else{
								winLoad.destroy();
							}
							
						}
					});
				}
				else{
					SC.say("Inserire il grid id da resettare");
				}
			}
		});
       	
       	exportXLS_bt   = new ToolStripButton();
       	exportXLS_bt.setWidth(30);
       	exportXLS_bt.setIcon("images.png");
       	exportXLS_bt.setTooltip("Esporta riepilogo EFA(xls)");
       	exportXLS_bt.addClickHandler(new ClickHandler() {

            
            public void onClick(ClickEvent event) {
              
            

            }
        });
       	
       	
       	/****************************************************************************************************/
        final Menu menu = new Menu();  
        menu.setShowShadow(true);  
        menu.setShadowDepth(10);  
  
        MenuItem item1 = new MenuItem("Export xls riepilogo EFA");  
        item1.setAttribute("RIEPILOGO",true); 
        item1.setIcon("riepilogo.png");
        
        MenuItem item2 = new MenuItem("Export xls dettaglio EFA");  
        item2.setAttribute("DETTAGLIO",true); 
        item2.setIcon("dettaglio.jpg");
        
        menu.setData(item1, item2);  
  
        menu.addItemClickHandler(new ItemClickHandler() {  
            public void onItemClick(ItemClickEvent event) {  
            	
            	/*prendo livello ruolo*/
            	String livelloRuolo = egeosDCL.getLivelloRuolo();
            	
            	
               
            	
                MenuItem clicked = event.getItem();  
                if(clicked.getAttributeAsBoolean("RIEPILOGO")){
                	
                /*	System.out.println("livelloRuolo: "+livelloRuolo);
                	System.out.println("VALORE COMBO REGIONI: "+ListGridContestiJSON.getComboRegioni().getValueAsString());*/
                	if(livelloRuolo.equals("1")){
                	
                	//	System.out.println("VALORE COMBO REGIONI: "+ListGridContestiJSON.getComboRegioni().getValueAsString());
                		
                		if(ListGridContestiJSON.getComboRegioni()!=null){
                			
                			if(ListGridContestiJSON.getComboRegioni().getValueAsString().equals("0")){
                				System.out.println("VALORE COMBO REGIONI: "+ListGridContestiJSON.getComboRegioni().getValueAsString());
                				Window.open(
                                		GWT.getModuleBaseURL()+"RiepilogoEFAXLSServlet?reg="+ListGridContestiJSON.getRegioniLivello_1().substring(0,ListGridContestiJSON.getRegioniLivello_1().length())+"&numeElenco="+egeosDCL.getNumElenco(),
                                        "_blank", "width=600,height=550,scrollbars=yes,resizable=yes");
                			}
                			else{
                				System.out.println("VALORE COMBO REGIONI: "+ListGridContestiJSON.getComboRegioni().getValueAsString());
                				Window.open(
                                		GWT.getModuleBaseURL()+"RiepilogoEFAXLSServlet?numeElenco="+egeosDCL.getNumElenco()+"&reg="+ListGridContestiJSON.getComboRegioni().getValueAsString(),
                                        "_blank", "width=600,height=550,scrollbars=yes,resizable=yes");
                			}
                		}
               
                	}
                	else if(livelloRuolo.equals("2")){
                		
                		System.out.println("RIEPILOGO PROVINCE:"+egeosDCL.getProvXLS());
                		Window.open(
                        		GWT.getModuleBaseURL()+"RiepilogoEFAXLSServlet?numeElenco="+egeosDCL.getNumElenco()+"&prov="+egeosDCL.getProvXLS(),
                                "_blank", "width=600,height=550,scrollbars=yes,resizable=yes");
                		
                	}
                	else if(livelloRuolo.equals("3")){
                		
                		if(!ListGridContestiJSON.getComboProvince().getValueAsString().equals("0"))
                		{
                			Window.open(
                            		GWT.getModuleBaseURL()+"RiepilogoEFAXLSServlet?numeElenco="+egeosDCL.getNumElenco()+"&prov="+ListGridContestiJSON.getComboProvince().getValueAsString(),
                                    "_blank", "width=600,height=550,scrollbars=yes,resizable=yes");
                		}
                		else{
                			System.out.println("DENTRO ELSE LIVELLO 3 RIEPILOGO(province in carico): "+egeosDCL.getProvXLS());
                			Window.open(
                            		GWT.getModuleBaseURL()+"RiepilogoEFAXLSServlet?numeElenco="+egeosDCL.getNumElenco()+"&prov="+egeosDCL.getProvXLS(),
                                    "_blank", "width=600,height=550,scrollbars=yes,resizable=yes");
                		}
                	}
                	
                }
                
                
                if(clicked.getAttributeAsBoolean("DETTAGLIO")){
            /*    	System.out.println("EXPORT DETTAGLIO");
                	System.out.println("livelloRuolo: "+livelloRuolo);
                	System.out.println("VALORE COMBO REGIONI: "+ListGridContestiJSON.getComboRegioni().getValueAsString());*/
                	if(livelloRuolo.equals("1")){
                	
                	//	System.out.println("VALORE COMBO REGIONI: "+ListGridContestiJSON.getComboRegioni().getValueAsString());
                		
                		if(ListGridContestiJSON.getComboRegioni()!=null){
                			
                			if(ListGridContestiJSON.getComboRegioni().getValueAsString().equals("0")){
                		//		System.out.println("VALORE COMBO REGIONI: "+ListGridContestiJSON.getComboRegioni().getValueAsString());
                				SC.say("Selezionare una regione");
                          /*  	Window.open(
                                		GWT.getModuleBaseURL()+"DettaglioEFAXLSServlet",
                                        "_blank", "width=600,height=550,scrollbars=yes,resizable=yes");*/
                			}
                			else{
                			//	System.out.println("VALORE COMBO REGIONI: "+ListGridContestiJSON.getComboRegioni().getValueAsString());
                				
                            	Window.open(
                                		GWT.getModuleBaseURL()+"DettaglioEFAXLSServlet?numeElenco="+egeosDCL.getNumElenco()+"&reg="+ListGridContestiJSON.getComboRegioni().getValueAsString(),
                                        "_blank", "width=600,height=550,scrollbars=yes,resizable=yes");
                			}
                		}
               
                	}
                	else if(livelloRuolo.equals("2")){
                	//	System.out.println("egeosDCL.getProvXLS()"+egeosDCL.getProvXLS());
                	//	System.out.println("egeosDCL.getProvXLS().length()"+egeosDCL.getProvXLS().length());
                		if(egeosDCL.getProvXLS().length()<4){
        		    	Window.open(
                        		GWT.getModuleBaseURL()+"DettaglioEFAXLSServlet?numeElenco="+egeosDCL.getNumElenco()+"&prov="+egeosDCL.getProvXLS(),
                                "_blank", "width=600,height=550,scrollbars=yes,resizable=yes");
                		}
                		else
                			SC.say("Per esportare il dettaglio selezionare una provincia");
                	}
                	else if(livelloRuolo.equals("3")){
                		
                		System.out.println("COMBO PROVINCE:"+ListGridContestiJSON.getComboProvince().getValueAsString());
                		
                		if(!ListGridContestiJSON.getComboProvince().getValueAsString().equals("0"))
                		{
                			Window.open(
                            		GWT.getModuleBaseURL()+"DettaglioEFAXLSServlet?numeElenco="+egeosDCL.getNumElenco()+"&prov="+ListGridContestiJSON.getComboProvince().getValueAsString(),
                                    "_blank", "width=600,height=550,scrollbars=yes,resizable=yes");
                		}
                		else
                			SC.say("Selezionare una provincia");
                		
                	}
                }
            }  
        });  
  
        MenuButton menuButton = new MenuButton("Export xls", menu);  
       	/*******************************************************************************************************/
       	
       	cancAssociazione.setTooltip("Ripristina refresh iniziale");
       	cancAssociazione.setIcon("tasto_cancella.gif");
       	quadranteDaCanc.setDisabled(true);
       	layResetQuad.addMember(formCancQuadranti);
       	layResetQuad.setWidth("100px");
       	layResetQuad.setMargin(5);
       	layResetQuad.setBackgroundColor("#C4C4C4");
        this.addMember(layResetQuad);
       	this.addButton(cancAssociazione);
       	cancAssociazione.setDisabled(true);
       	//this.addMember(exportXLS_bt);
      //  this.addMember(menuButton);
       	this.setPadding(3);

        this.addFill();
        this.addMember(utente_connesso);
        this.addMember(contesto_sel);
        this.addMember(logout_bt);

    }

	public static ToolStripButton getZoomBOX_bt() {
		return zoomBOX_bt;
	}
	public static void setZoomBOX_bt(ToolStripButton zoomBOX_bt) {
		MapToolStrip.zoomBOX_bt = zoomBOX_bt;
	}
	public static ToolStripButton getPanMove_bt() {
		return panMove_bt;
	}
	public static void setPanMove_bt(ToolStripButton panMove_bt) {
		MapToolStrip.panMove_bt = panMove_bt;
	}
	public static ToolStripButton getInfo_bt() {
		return info_bt;
	}
	public static void setInfo_bt(ToolStripButton info_bt) {
		MapToolStrip.info_bt = info_bt;
	}
	public static ToolStripButton getDrag_bt() {
		return drag_bt;
	}
	public static void setDrag_bt(ToolStripButton drag_bt) {
		MapToolStrip.drag_bt = drag_bt;
	}


	

	public static SelectItem getSelectItem() {
		return selectItem;
	}

	public static void setSelectItem(SelectItem selectItem) {
		MapToolStrip.selectItem = selectItem;
	}

	public static Menu getMenu() {
		return menu;
	}

	public static void setMenu(Menu menu) {
		MapToolStrip.menu = menu;
	}

	public static Label getUtente_connesso() {
		return utente_connesso;
	}

	public static void setUtente_connesso(Label utente_connesso) {
		MapToolStrip.utente_connesso = utente_connesso;
	}

	public static Label getContesto_sel() {
		return contesto_sel;
	}

	public static void setContesto_sel(Label contesto_sel) {
		MapToolStrip.contesto_sel = contesto_sel;
	}

	public static ToolStripButton getBack_to_regioni_bt() {
		return back_to_regioni_bt;
	}

	public static void setBack_to_regioni_bt(ToolStripButton back_to_regioni_bt) {
		MapToolStrip.back_to_regioni_bt = back_to_regioni_bt;
	}

	public static ToolStripButton getPrint_bt() {
		return print_bt;
	}



	public static void setPrint_bt(ToolStripButton print_bt) {
		MapToolStrip.print_bt = print_bt;
	}

	public static ToolStripButton getUpload_csv() {
		return upload_csv;
	}

	public static void setUpload_csv(ToolStripButton upload_csv) {
		MapToolStrip.upload_csv = upload_csv;
	}

	public static TextItem getQuadranteDaCanc() {
		return quadranteDaCanc;
	}

	public static void setQuadranteDaCanc(TextItem quadranteDaCanc) {
		MapToolStrip.quadranteDaCanc = quadranteDaCanc;
	}

	public static ToolStripButton getCancAssociazione() {
		return cancAssociazione;
	}

	public static void setCancAssociazione(ToolStripButton cancAssociazione) {
		MapToolStrip.cancAssociazione = cancAssociazione;
	}

	public static HLayout getLayCancQuadranti() {
		return layCancQuadranti;
	}

	public static void setLayCancQuadranti(HLayout layCancQuadranti) {
		MapToolStrip.layCancQuadranti = layCancQuadranti;
	}


}
