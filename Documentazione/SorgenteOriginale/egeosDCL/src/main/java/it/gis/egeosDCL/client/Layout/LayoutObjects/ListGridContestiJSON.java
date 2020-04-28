package it.gis.egeosDCL.client.Layout.LayoutObjects;

import java.util.LinkedHashMap;

import it.gis.egeosDCL.client.SguParam;
import it.gis.egeosDCL.client.egeosDCL;
import it.gis.egeosDCL.client.Layout.LeftLayout;
import it.gis.egeosDCL.client.ctx.ContestiWindow;
import it.gis.egeosDCL.client.ctx.ContextListner;
import it.gis.egeosDCL.client.ctx.WorkType;
import it.gis.egeosDCL.client.map.OLMap_new;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.rpc.RPCCallback;
import com.smartgwt.client.rpc.RPCManager;
import com.smartgwt.client.rpc.RPCRequest;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.DataArrivedEvent;
import com.smartgwt.client.widgets.grid.events.DataArrivedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 *  CREA LA GRIGLIA DEI CONTESTI TRAMITE UNO SCRIPT JSON
 * @version 1.0
 *
 *
 */
public class ListGridContestiJSON extends ExportableGrid{

					
					String 					srs		 				= "";
					DynamicForm 			form 					= null;
					TextItem 				userItem				= null;
					PasswordItem 			passItem   				= null;
	static 			IButton 		 		bt_conferma 			= null;
	private static	IButton 		 		bt_valida	 			= null;
	private static  String					regioniLivello_1		= "";

					VLayout 				windowLayout 			= new VLayout();
					HLayout 				formLayout  		 	= new HLayout();
					HLayout 				btLayout   				= new HLayout();
	public static 	VLayout 				gridContestiLayout   	= new VLayout();
	static 			ListGridContestiJSON 	listGridContesti 		= null;
	private static 	ListGridRegioniJSON  	listRegJSON 			= null;
	private static 	ListGridComuniJSON  	listComuJSON 			= null;
	private static 	ListGridProvinceJSON  	listProvJSON 			= null;
					ListGridComuniJSON 		listGridcomuni 			= null;
					String 					numero_elenco 			= "";
					LinkedHashMap<String ,String> mapRegioni 		= null;
					LinkedHashMap<String ,String> mapProvince 		= null;
					LinkedHashMap<String ,String> mapComuni 		= null;
	private static 			SelectItem 				comboProvince 			= null;
	static 			SelectItem 				comboComuni 			= null;
	static 			SelectItem 				comboRegioni 			= null;
					DynamicForm 			formCombo 				= null; 
					DynamicForm 			formComboRegioni		= null; 
					WinLoading 				winLoad 				= new WinLoading("Elaborazione in corso..."); 
					boolean 				continua 				= false;
	private static 	String  				idProvinciaRiepilogo	= null;		
	private static 	String  				idComuneRiepilogo		= null;		
	private static  ToolStrip				toolStripLivello3		= null;
	private static  ToolStripButton			toolStripButtonExportXLSRiep = null;
	private static  ToolStripButton			toolStripButtonExportXLSDett = null;
	

	String appo = "";

	
	protected Canvas createRecordComponent(final ListGridRecord record, final Integer colNum) {
		String fieldName	= this.getFieldName(colNum);
		
		this.setAlign(Alignment.CENTER);

//		modifica_cons.gif
		
		if (fieldName.equals("Del")){
			final ImgButton chartImg = new ImgButton();  
			chartImg.setShowDown(false);  
			chartImg.setShowRollOver(false);  
			chartImg.setAlign(Alignment.CENTER);  		
			chartImg.setSrc("delrow.jpg");
			chartImg.setPrompt("Elimina Contesto");
			chartImg.setHeight(16);  
			chartImg.setWidth(16);

			chartImg.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
				
				public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
					/**
					 * Richiamare il metodo per deletare il contsto selezionato
					 */
					SC.confirm("Sei sicuro di voler cancellare il contesto?",new BooleanCallback() {
						
						public void execute(Boolean value) {
							if(value){
								cancellaContesto(record.getAttribute("NUM_ELENCO"));			
							}
						}
					});
					
				}
			});
			return chartImg;
		}else if(fieldName.equals("Mod")){
			final ImgButton modImg = new ImgButton();  
			modImg.setShowDown(false);  
			modImg.setShowRollOver(false);  
			modImg.setAlign(Alignment.CENTER);  		
			modImg.setSrc("modifica_cons.gif");
			modImg.setPrompt("Modifica Contesto");
			modImg.setHeight(16);  
			modImg.setWidth(16);

			modImg.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
				
				public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
					/**
					 * Richiamare il metodo per la modifica del contesto selezionato
					 */
					gotoContesto(record.getAttribute("NUM_ELENCO"),WorkType.UPDATE);
					
				}
			});
			return modImg;
			
		}else if(fieldName.equals("View")){
			final ImgButton viewImg = new ImgButton();  
			viewImg.setShowDown(false);  
			viewImg.setShowRollOver(false);  
			viewImg.setAlign(Alignment.CENTER);  		
			viewImg.setSrc("dettComu.jpg");
			viewImg.setPrompt("Visualizza Contesto");
			viewImg.setHeight(16);  
			viewImg.setWidth(16);

			viewImg.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
				
				public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
					/**
					 * Richiamare il metodo per la modifica del contesto selezionato
					 */
					gotoContesto(record.getAttribute("NUM_ELENCO"),WorkType.VIEW);
					
				}
			});
			return viewImg;
			
		}else{
			return null;
		}	
	} 	

	public ListGridContestiJSON()
	{
		this.setID("CONTESTI_GRID_ID");

		/*CREO LA TABELLA DELLE PROVINCE*/
		DataSource dsContesti = new DataSource();
		dsContesti.setDataFormat(DSDataFormat.JSON);  

		formCombo = new DynamicForm();
		comboRegioni = new SelectItem("Regione");
		mapRegioni = new LinkedHashMap<String,String>();
		form = new DynamicForm();

		DataSourceField idContestoDSField = new DataSourceField("ID_ELENCO", FieldType.TEXT,"Codice",10);  
		DataSourceField numElencoDSField = new DataSourceField("NUM_ELENCO", FieldType.TEXT,"Numero Elenco",10);
		DataSourceField campagnaContestoDSField = new DataSourceField("CAMPAGNA", FieldType.TEXT,"Campagna",10);  
		DataSourceField descrizioneContestoDSField = new DataSourceField("DESCRIZIONE", FieldType.TEXT,"Descrizione",15);
		DataSourceField delRowDSField = new DataSourceField("Del", FieldType.TEXT," ",4);
		DataSourceField modDSField = new DataSourceField("Mod", FieldType.TEXT," ",4);
		DataSourceField viewDSField = new DataSourceField("View", FieldType.TEXT," ",4);

		if(egeosDCL.getLivelloRuolo().equals("1")){
			dsContesti.setFields(viewDSField,modDSField,delRowDSField,numElencoDSField,idContestoDSField,campagnaContestoDSField,descrizioneContestoDSField);
		}
		else{
			dsContesti.setFields(numElencoDSField,idContestoDSField,campagnaContestoDSField,descrizioneContestoDSField);
		}	

		dsContesti.setDataURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=ContestiService");  

		dsContesti.setRecordXPath("/TodoLista");

		this.setShowRecordComponents(true);          
		this.setShowRecordComponentsByCell(true);
		this.setWidth100();  
		this.setHeight100();  
		this.setShowAllRecords(true);  
		this.setDataSource(dsContesti);  
		this.setAutoFetchData(true);  


		setShowRecordComponents(true);          


		this.addDataArrivedHandler(new DataArrivedHandler() {

			
			public void onDataArrived(DataArrivedEvent event) {
			
				markForRedraw();
				setShowRecordComponentsByCell(true);
				
				
			}
		});


		this.addCellClickHandler(new CellClickHandler() {

			
			public void onCellClick(CellClickEvent event) {
				winLoad = new WinLoading();
				egeosDCL.setNumElenco(event.getRecord().getAttributeAsString("NUM_ELENCO"));
				MapToolStrip.getContesto_sel().setContents("Contesto:<b>"+egeosDCL.getNumElenco()+"</b>");
				//System.out.println("elenco selezionato: "+event.getRecord().getAttributeAsString("NUM_ELENCO"));

				System.out.println("egeosDCL.getLivelloRuolo()::::::::: "+egeosDCL.getLivelloRuolo());

				if(egeosDCL.getLivelloRuolo().equals("1")){
					formComboRegioni = new DynamicForm();
					MapToolStrip.getUpload_csv().setDisabled(false);
					winLoad.show();
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
									mapRegioni.put("0","Selezionare una regione");
									for(int i = 0;i<arr.size();i++)
									{
										JSONObject ob = arr.get(i).isObject();
										regioniLivello_1 += ob.get("id_regi").toString().replace("\"","")+",";

										if(ob.get("wkt").toString().replace("\"","")!=null && !ob.get("wkt").toString().replace("\"","").equals(""))
										{
											continua = true;
											destroy();
											String srid = "";
											mapRegioni.put(ob.get("id_regi").toString().replace("\"",""), ob.get("deno_regi").toString().replace("\"",""));
											String colore = egeosDCL.calcolaColoreRegione(new Integer(ob.get("tot").toString().replace("\"","")),
													new Integer(ob.get("lavorate").toString().replace("\"","")),
													new Integer(ob.get("assegnate").toString().replace("\"","")),
													new Integer(ob.get("noassegnate").toString().replace("\"","")),0);

											//System.out.println("COLORE IN ADDWKT REGIONI: "+colore);
											OLMap_new.addWKTRegioni("Regioni",ob.get("wkt").toString().replace("\"",""),ob.get("fuso").toString().replace("\"",""),ob.get("id_regi").toString().replace("\"",""),ob.get("deno_regi").toString().replace("\"",""),i,colore);
											////System.out.println("CARICO MAP: "+ob.get("id_regi").toString().replace("\"","")+" -- "+ob.get("deno_regi").toString().replace("\"",""));
										}
										else{
											continua = false;
											SC.say("Non ci sono dati assegnati all&apos;utente");
											winLoad.hide();
											break;
										}
											
									}
									if(continua){
									OLMap_new.attivaSelezione();
									comboRegioni.setValue("0");
									comboRegioni.setValueMap(mapRegioni);
									comboRegioni.setSortField(2);
									

									comboRegioni.addChangedHandler(new ChangedHandler() {

										
										public void onChanged(ChangedEvent event) {
											OLMap_new.destroyMap();
											if(LeftLayout.getvLayoutGridRegioni().getMember("PROVINCE_GRID_ID")!=null)
											{
												LeftLayout.getvLayoutGridRegioni().getMember("PROVINCE_GRID_ID").destroy();
												LeftLayout.getvLayoutGridRegioni().getMember("ID_TOOLSTRIP_PROVINCE").destroy();
											}

											if(LeftLayout.getvLayoutGridRegioni().getMember("QUADRANTI_COMUNI_GRID_ID")!=null)
											{
												LeftLayout.getvLayoutGridRegioni().getMember("QUADRANTI_COMUNI_GRID_ID").destroy();
												LeftLayout.getvLayoutGridRegioni().getMember("ID_TOOLSTRIP_QUADRANTI").destroy();
												ToolStripButtonProvince stripProvince = new ToolStripButtonProvince();
												listProvJSON = new ListGridProvinceJSON(comboRegioni.getValue().toString());
												
												egeosDCL.setListGridProvince(listProvJSON);
												
												LeftLayout.getvLayoutGridRegioni().addMember(stripProvince);
												LeftLayout.getvLayoutGridRegioni().addMember(listProvJSON);
											}
											else{
												listProvJSON = new ListGridProvinceJSON(comboRegioni.getValue().toString());
												ToolStripButtonProvince stripProvince = new ToolStripButtonProvince();
												LeftLayout.getvLayoutGridRegioni().addMember(stripProvince);
												LeftLayout.getvLayoutGridRegioni().addMember(listProvJSON);
											}

										}
									});
									
									
									formCombo.setNumCols(6);
									
									ButtonItem exportRiepXLSItem = new ButtonItem("Riepilogo&nbsp;XLS"); 
									formCombo.setItems(comboRegioni,exportRiepXLSItem);
									LeftLayout.getvLayoutGridRegioni().addMember(formCombo);
									exportRiepXLSItem.addClickHandler(new ClickHandler() {
										
										
										public void onClick(ClickEvent event) {
											Window.open(
			                                		GWT.getModuleBaseURL()+"RiepilogoEFAXLSServlet?reg="+ListGridContestiJSON.getRegioniLivello_1().substring(0,ListGridContestiJSON.getRegioniLivello_1().length()-1)+"&numeElenco="+egeosDCL.getNumElenco(),
			                                        "_blank", "width=600,height=550,scrollbars=yes,resizable=yes");
											
										}
									});
									
								//	LeftLayout.getvLayoutGridRegioni().addMember(BT);
									if(LeftLayout.getvLayoutGridRegioni().getMember("REGIONI_GRID_ID")!=null)
									{
										LeftLayout.getvLayoutGridRegioni().getMember("REGIONI_GRID_ID").destroy();
										listRegJSON = new ListGridRegioniJSON();
										egeosDCL.setListGridRegioni(listRegJSON);
										LeftLayout.getvLayoutGridRegioni().addMember(listRegJSON);
									}
									else
									{
										listRegJSON = new ListGridRegioniJSON();
										egeosDCL.setListGridRegioni(listRegJSON);
										LeftLayout.getvLayoutGridRegioni().addMember(listRegJSON);
									}

									winLoad.hide();
									markForRedraw();

									}//continua
								}
							}
						}
					});
				}
				else if(egeosDCL.getLivelloRuolo().equals("2"))
				{

					winLoad = new WinLoading("Elaborazione in corso..."); 	
					winLoad.show();
					
					
					//comboProvince = new SelectItem("Provincia");

					if(LeftLayout.getvLayoutGridRegioni().getMember("COMUNI_GRID_ID")!=null)
					{
						LeftLayout.getvLayoutGridRegioni().getMember("COMUNI_GRID_ID").destroy();
					}
					/*CARICO LA GRIGLIA DELLE PROVINCE*/

					if(LeftLayout.getvLayoutGridRegioni().getMember("PROVINCE_GRID_ID")!=null)
					{
						LeftLayout.getvLayoutGridRegioni().getMember("PROVINCE_GRID_ID").destroy();
						listProvJSON = new ListGridProvinceJSON("0");
						LeftLayout.getvLayoutGridRegioni().getMember("ID_TOOLSTRIP_COMUNI").destroy();
						ToolStripButtonProvince stripProvince = new ToolStripButtonProvince();
						LeftLayout.getvLayoutGridRegioni().addMember(stripProvince);
						LeftLayout.getvLayoutGridRegioni().addMember(listProvJSON);
					}
					else
					{
						listProvJSON = new ListGridProvinceJSON("0");
						ToolStripButtonProvince stripProvince = new ToolStripButtonProvince();
						LeftLayout.getvLayoutGridRegioni().addMember(stripProvince);
						LeftLayout.getvLayoutGridRegioni().addMember(listProvJSON);
					}
					MapToolStrip.getBack_to_regioni_bt().setDisabled(true);
					winLoad.hide();
				}
				else if(egeosDCL.getLivelloRuolo().equals("3"))
				{
					MapToolStrip.getCancAssociazione().setDisabled(false);
					MapToolStrip.getQuadranteDaCanc().setDisabled(false);
					winLoad.show();
					
					comboProvince = new SelectItem("Provincia");
					bt_valida = new IButton("VALIDA");
					
					toolStripLivello3				= new ToolStrip();
					toolStripButtonExportXLSRiep 	= new ToolStripButton("Export riepilogo");
					toolStripButtonExportXLSDett 	= new ToolStripButton("Export dettaglio");
					
					
					toolStripButtonExportXLSRiep.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
						
						
						public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
							Window.open(
                            		GWT.getModuleBaseURL()+"RiepilogoEFAXLSServlet?numeElenco="+egeosDCL.getNumElenco()+"&prov="+egeosDCL.getProvXLS(),
                                    "_blank", "width=600,height=550,scrollbars=yes,resizable=yes");
						}
					});
					
					toolStripButtonExportXLSDett.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
						
						
						public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
							Window.open(
                            		GWT.getModuleBaseURL()+"DettaglioEFAXLSServlet?numeElenco="+egeosDCL.getNumElenco()+"&prov="+comboProvince.getValueAsString(),
                                    "_blank", "width=600,height=550,scrollbars=yes,resizable=yes");
						}
					});
					
					
					toolStripLivello3.addButton(toolStripButtonExportXLSRiep);
					toolStripLivello3.addButton(toolStripButtonExportXLSDett);
					
					comboComuni   = new SelectItem("Comune");
					RPCRequest request = new RPCRequest();
					request.setData("Some data to send to the client");
					request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=provinceSocioProvincialeService&numElenco="+egeosDCL.getNumElenco()+"&idUtente="+egeosDCL.getIdUtente());
					
					OLMap_new.loadOLEProvince("map","", "EPSG:3004");
					RPCManager.sendRequest(request, new RPCCallback() {

						
						public void execute(RPCResponse response, Object rawData, RPCRequest request) {
							JSONValue value = JSONParser.parseLenient(rawData+"");

							if(value.isObject()!=null)
							{
								mapProvince = new LinkedHashMap<String, String>();
								mapProvince.put("0","Selezionare una provincia");
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

										//System.out.println("COLORE PROVINCIA::::: "+colore );
										
										
										appo += ob.get("idProv")+",";
										
										OLMap_new.addWKTProvince("Province", ob.get("wkt").toString().replace("\"",""), ob.get("fuso").toString().replace("\"",""),ob.get("idProv").toString().replace("\"",""), ob.get("denoProv").toString().replace("\"",""),i,colore,new Integer(ob.get("sel").toString().replace("\"","")),"");
										mapProvince.put(ob.get("idProv").toString().replace("\"",""), ob.get("denoProv").toString().replace("\"",""));
									}
									appo = appo.substring(0,appo.length()-1);
									egeosDCL.setProvXLS(appo);
									
									egeosDCL.setArrProvJSON(arr);
									OLMap_new.attivaSelezione();
									comboProvince.setValue(0);
									comboProvince.setValueMap(mapProvince);
									formCombo.setNumCols(4);
									//formCombo.setBackgroundColor("red");
									
									formCombo.setItems(comboProvince);
									bt_valida.setHeight("25px");
									HLayout formleft = new HLayout();
									formleft.setHeight("70px");
									formleft.addMember(formCombo);
									
									VLayout layToolStripXLS = new  VLayout();
									//layToolStripXLS.addMember(toolStripLivello3);
									
								
									formleft.addMember(bt_valida);
								//	formleft.addMember(layToolStripXLS);
									MapToolStrip.getPrint_bt().setDisabled(false);
									LeftLayout.getvLayoutGridRegioni().addMember(formleft);
									toolStripLivello3.setID("ID_toolStripLivello3");
									toolStripLivello3.setVisible(false);
									LeftLayout.getvLayoutGridRegioni().addMember(toolStripLivello3);
									

								}

							}
						}
					});							
					bt_valida.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
						
						
						public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
							// TODO Auto-generated method stub
							if(egeosDCL.getDett_layout().getMember("DettaglioComuniListGrid_ID")!=null){

								egeosDCL.getDett_layout().getMember("ID_DETT_TOOLSTRIP_COMUNI").destroy();
								egeosDCL.getDett_layout().getMember("DettaglioComuniListGrid_ID").destroy();
								egeosDCL.getDett_layout().hide();
							}
							ValidateWindow winVal = new ValidateWindow();
							winVal.show();
						}
					});
					bt_valida.setDisabled(true);
					comboProvince.addChangedHandler(new ChangedHandler() {
						
						
						public void onChanged(ChangedEvent event) {
							winLoad.show();
							LeftLayout.getvLayoutGridRegioni().getMember("ID_toolStripLivello3").setVisible(true);
							
							setIdProvinciaRiepilogo(comboProvince.getValueAsString());
							if(egeosDCL.getDett_layout().getMember("ID_DETT_TOOLSTRIP_COMUNI")!=null){

								egeosDCL.getDett_layout().getMember("ID_DETT_TOOLSTRIP_COMUNI").destroy();
								egeosDCL.getDett_layout().getMember("DettaglioComuniListGrid_ID").destroy();
							}
							
							OLMap_new.destroyMap();
							if(LeftLayout.getvLayoutGridRegioni().getMember("COMUNI_GRID_ID")!=null){

								LeftLayout.getvLayoutGridRegioni().getMember("COMUNI_GRID_ID").destroy();
								//LeftLayout.getvLayoutGridRegioni().getMember("ID_TOOLSTRIP_QUADRANTI").destroy();

								ListGridComuniJSON gridComuni = new ListGridComuniJSON(comboProvince.getValueAsString());
							//	ToolStripButtonQuadranti stripQuadranti = new ToolStripButtonQuadranti();
								setListComuJSON(gridComuni);
								//CARICO MAPPA
						//		LeftLayout.getvLayoutGridRegioni().addMember(stripQuadranti);
								LeftLayout.getvLayoutGridRegioni().addMember(gridComuni);
								bt_valida.setDisabled(false);
							}
							else{
//								if(LeftLayout.getvLayoutGridRegioni().getMember("ID_TOOLSTRIP_QUADRANTI")!=null){
//									LeftLayout.getvLayoutGridRegioni().getMember("ID_TOOLSTRIP_QUADRANTI").destroy();
//								}
								ListGridComuniJSON gridComuni = new ListGridComuniJSON(comboProvince.getValueAsString());
								//ToolStripButtonQuadranti stripQuadranti = new ToolStripButtonQuadranti();
								setListComuJSON(gridComuni);
								//CARICO MAPPA
								//LeftLayout.getvLayoutGridRegioni().addMember(stripQuadranti);
								LeftLayout.getvLayoutGridRegioni().addMember(gridComuni);
								bt_valida.setDisabled(false);
							}
							egeosDCL.getDett_layout().hide();
							winLoad.hide();
						}

					});
					comboComuni.addChangedHandler(new ChangedHandler() {

						
						public void onChanged(ChangedEvent event) {
							//System.out.println("CARICO QUADRANTI");

							winLoad = new WinLoading("Caricamento dei quadranti in corso..."); 
							winLoad.show();
							if(LeftLayout.getvLayoutGridRegioni().getMember("ID_TOOLSTRIP_QUADRANTI")!=null){
								LeftLayout.getvLayoutGridRegioni().getMember("ID_TOOLSTRIP_QUADRANTI").destroy();
									
								ToolStripButtonQuadranti stripQuadranti = new ToolStripButtonQuadranti();

								LeftLayout.getvLayoutGridRegioni().addMember(stripQuadranti);
							}else{
								ToolStripButtonQuadranti stripQuadranti = new ToolStripButtonQuadranti();

								LeftLayout.getvLayoutGridRegioni().addMember(stripQuadranti);

							}
							winLoad.hide();
						}
					});
				}

				egeosDCL.getUserWin().destroy();
			}
		});
	}
	
	private void gotoContesto(String numElenco,WorkType wt){
		ContestiWindow ctxWin = new ContestiWindow(numElenco,new ContextListner() {
			
			public void contextRefresh() {
				// TODO Auto-generated method stub
				if(SetUserWindow.getGridContestiLayout().getMember("CONTESTI_GRID_ID")!=null)
				{
					SetUserWindow.getGridContestiLayout().getMember("CONTESTI_GRID_ID").destroy();
					listGridContesti = new ListGridContestiJSON();
					SetUserWindow.getGridContestiLayout().addMember(listGridContesti);
//					if(egeosDCL.getLivelloRuolo().equals("1")){
//						SetUserWindow.getGridContestiLayout().addMember(new ToolStripButtonContesti());
//					}
				}
			}
		},wt);
    	ctxWin.show();		
	}
	
	private void cancellaContesto(String numElenco){
		RPCRequest request = new RPCRequest();
		request.setData("Some data to send to the client");
		request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=deleteContext&numElenco="+numElenco);

		RPCManager.sendRequest(request, new RPCCallback() {
			
			public void execute(RPCResponse response, Object rawData, RPCRequest request) {
				SC.say(egeosDCL.fireMessage(rawData));
				if(SetUserWindow.getListGridContesti()!=null){
					if(SetUserWindow.getGridContestiLayout().getMember("CONTESTI_GRID_ID")!=null){
						SetUserWindow.getGridContestiLayout().getMember("CONTESTI_GRID_ID").destroy();
						listGridContesti = new ListGridContestiJSON();
						SetUserWindow.getGridContestiLayout().addMember(listGridContesti);
						SetUserWindow.getAppo().hide();
					}
				}
			}
		});
	}
	
	public void caricaMappaComuni(){
		
	}



	public static SelectItem getComboComuni() {
		return comboComuni;
	}

	public static void setComboComuni(SelectItem comboComuni) {
		ListGridContestiJSON.comboComuni = comboComuni;
	}

	public static SelectItem getComboRegioni() {
		return comboRegioni;
	}

	public static void setComboRegioni(SelectItem comboRegioni) {
		ListGridContestiJSON.comboRegioni = comboRegioni;
	}

	public static ListGridProvinceJSON getListProvJSON() {
		return listProvJSON;
	}

	public static void setListProvJSON(ListGridProvinceJSON listProvJSON) {
		ListGridContestiJSON.listProvJSON = listProvJSON;
	}

	public static ListGridRegioniJSON getListRegJSON() {
		return listRegJSON;
	}

	public static void setListRegJSON(ListGridRegioniJSON listRegJSON) {
		ListGridContestiJSON.listRegJSON = listRegJSON;
	}

	public static ListGridComuniJSON getListComuJSON() {
		return listComuJSON;
	}

	public static void setListComuJSON(ListGridComuniJSON listComuJSON) {
		ListGridContestiJSON.listComuJSON = listComuJSON;
	}

	public static String getIdProvinciaRiepilogo() {
		return idProvinciaRiepilogo;
	}

	public static void setIdProvinciaRiepilogo(String idProvinciaRiepilogo) {
		ListGridContestiJSON.idProvinciaRiepilogo = idProvinciaRiepilogo;
	}

	public static String getIdComuneRiepilogo() {
		return idComuneRiepilogo;
	}

	public static void setIdComuneRiepilogo(String idComuneRiepilogo) {
		ListGridContestiJSON.idComuneRiepilogo = idComuneRiepilogo;
	}

	public static IButton getBt_valida() {
		return bt_valida;
	}

	public static void setBt_valida(IButton bt_valida) {
		ListGridContestiJSON.bt_valida = bt_valida;
	}
	
	public static SelectItem getComboProvince() {
		return comboProvince;
	}

	public static void setComboProvince(SelectItem comboProvince) {
		ListGridContestiJSON.comboProvince = comboProvince;
	}

	public static String getRegioniLivello_1() {
		return regioniLivello_1;
	}

	public static void setRegioniLivello_1(String regioniLivello_1) {
		ListGridContestiJSON.regioniLivello_1 = regioniLivello_1;
	}

}
