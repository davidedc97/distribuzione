package it.gis.egeosDCL.client.Layout.LayoutObjects;


import java.util.Set;

import it.gis.egeosDCL.client.egeosDCL;
import it.gis.egeosDCL.client.Layout.LeftLayout;
import it.gis.egeosDCL.client.map.OLMap_new;

import com.google.gwt.core.client.GWT;
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
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.DataArrivedEvent;
import com.smartgwt.client.widgets.grid.events.DataArrivedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * CREA LA GRIGLIA DELLE PROVINCE TRAMITE UNO SCRIPT JSON
 * @version 1.0
 * 
 */
public class ListGridProvinceJSON extends ExportableGrid{

	ListGridComuniJSON 	listComuJSON = null;
	String 				fusoProvince = "";
	static 						ListGrid 			grid;
	String 				srs = "";
	String 				valoreUtente;
	WinLoading 			winLoad 				= new WinLoading("Salvataggio in corso...");
	private static 				ListGridRegioniJSON  	listRegJSON 			= null;
	private static 				ListGridProvinceJSON  	listProvJSON 			= null;

	


	public ListGridProvinceJSON(String idRegione)
	{
		this.setID("PROVINCE_GRID_ID");
		grid=this;
		/*CREO LA TABELLA DELLE PROVINCE*/
		final DataSource dsProv = new DataSource();
		dsProv.setDataFormat(DSDataFormat.JSON);  

		/*		DataSourceField associaDSField = new DataSourceField("denoProv", FieldType.TEXT,"Provincia",20);  
		DataSourceField disassociaDSField = new DataSourceField("denoProv", FieldType.TEXT,"Provincia",20);  
		DataSourceField validaDSField = new DataSourceField("denoProv", FieldType.TEXT,"Provincia",20);  
		 */		

		DataSourceField descProvDSField = new DataSourceField("denoProv", FieldType.TEXT,"Provincia",20);  

		DataSourceField siglaProvDSField = new DataSourceField("siglaProv", FieldType.TEXT,"siglaProv"); 
		DataSourceField dataAssProvDSField = new DataSourceField("dataAss", FieldType.TEXT,"Ass.ta il"); 
		DataSourceField fusoDSField = new DataSourceField("fuso", FieldType.TEXT,"fuso"); 
		fusoDSField.setHidden(true);

		DataSourceField figlioDSField = new DataSourceField("figlio", FieldType.TEXT,"Ass.ta A"); 
		////////////////////////TOTALI 
		DataSourceField assegnatoProvDSField = new DataSourceField("assegnate", FieldType.TEXT,"Ass.te",20);  
		DataSourceField statoDSField = new DataSourceField("stato", FieldType.TEXT,"Ass.te",20);  
		statoDSField.setHidden(true);
		DataSourceField noAssegnatoDSField = new DataSourceField("noassegnate", FieldType.TEXT,"Non Ass"); 
		DataSourceField totaliDSField = new DataSourceField("tot", FieldType.TEXT,"Tot"); 
		DataSourceField lavorateDSField = new DataSourceField("lavorate", FieldType.TEXT,"Lavorate");
		DataSourceField xlsCodeField = new DataSourceField("xls", FieldType.TEXT,"xls");  
		//		assegnatoProvDSField.setHidden(f);
		//		noAssegnatoDSField.setHidden(true);
		//		totaliDSField.setHidden(true);
		//		lavorateDSField.setHidden(true);
		////////////////////////////////
		DataSourceField seleDSField = new DataSourceField("Sele", FieldType.TEXT,"Sele"); 
		DataSourceField provinciaSelDSField = new DataSourceField("sel", FieldType.TEXT,"sel"); 
		provinciaSelDSField.setHidden(true);
		DataSourceField idProvCodeField = new DataSourceField("idProv", FieldType.TEXT, "idProv");  
		idProvCodeField.setPrimaryKey(true);
		idProvCodeField.setAttribute("denoProv",descProvDSField);
		idProvCodeField.setAttribute("fuso",fusoDSField);
		idProvCodeField.setAttribute("figlio",figlioDSField);
		idProvCodeField.setHidden(true);
		DataSourceField wktProvCodeField = new DataSourceField("wkt", FieldType.TEXT,"wkt");  
		wktProvCodeField.setHidden(true);

		dsProv.setFields(descProvDSField,idProvCodeField,wktProvCodeField,totaliDSField,fusoDSField,provinciaSelDSField,
				assegnatoProvDSField,noAssegnatoDSField,lavorateDSField,figlioDSField,dataAssProvDSField,seleDSField,xlsCodeField,statoDSField);  

		if(egeosDCL.getLivelloRuolo().equals("1")){
			dsProv.setDataURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=provinceRefreshService&idRegione="+idRegione+"&idUtente="+egeosDCL.getIdUtente()+"&numElenco="+egeosDCL.getNumElenco());  
		}
		else if(egeosDCL.getLivelloRuolo().equals("2")){
			dsProv.setDataURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=provinceSocioNazionaleService&idRegione=0&numElenco="+egeosDCL.getNumElenco()+"&idUtente="+egeosDCL.getIdUtente());
		}
		else if(egeosDCL.getLivelloRuolo().equals("3")){
			dsProv.setDataURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=provinceSocioProvincialeService&numElenco="+egeosDCL.getNumElenco()+"&idUtente="+egeosDCL.getIdUtente());
		}
		dsProv.setRecordXPath("/TodoLista");
		
		
		

		this.setShowRecordComponents(true);          
		this.setShowRecordComponentsByCell(true);
		this.setWidth100();  
		this.setHeight("50%");  
		this.setShowAllRecords(true);  
		this.setDataSource(dsProv);  
		this.setAutoFetchData(true);  
		
		
		
		
		this.addSelectionChangedHandler(new SelectionChangedHandler() {

			
			public void onSelectionChanged(SelectionEvent event) {
				// TODO Auto-generated method stub
				//System.out.println("CHANGED ID prv"+event.getRecord().getAttributeAsString("idProv"));
				//System.out.println("CHANGED DESC prov"+event.getRecord().getAttributeAsString("denoProv"));
				//System.out.println("ToolStripButtonProvince attr figlio: "+event.getRecord().getAttributeAsString("figlio"));
				//ToolStripButtonProvince.getProvinciaSel().setValue(event.getRecord().getAttributeAsString("denoProv"));
				//	ToolStripButtonProvince.getProvinciaSel().setAttribute("idProv",event.getRecord().getAttributeAsString("idProv"));
				if(egeosDCL.getLivelloRuolo().equals("1")){
					ToolStripButtonProvince.getProvinciaSel().setValue(event.getRecord().getAttributeAsString("denoProv"));
					ToolStripButtonProvince.getProvinciaSel().setAttribute("idProv",event.getRecord().getAttributeAsString("idProv"));
					ToolStripButtonProvince.getProvinciaSel().setAttribute("figlio",event.getRecord().getAttributeAsString("figlio"));
				}
				else if(egeosDCL.getLivelloRuolo().equals("2")){
					ToolStripButtonProvince.getProvinciaSel().setValue(event.getRecord().getAttributeAsString("denoProv"));
					ToolStripButtonProvince.getProvinciaSel().setAttribute("idProv",event.getRecord().getAttributeAsString("idProv"));
					ToolStripButtonProvince.getProvinciaSel().setAttribute("figlio",event.getRecord().getAttributeAsString("figlio"));

				}

			}
		});

		this.addCellClickHandler(new CellClickHandler() {

			
			public void onCellClick(CellClickEvent event) {
				//OLMap_new.destroyMap();
				if(egeosDCL.getLivelloRuolo().equals("1"))
					ToolStripButtonProvince.getSelctOperatoreProvincia().setAttribute("figlio",event.getRecord().getAttributeAsString("figlio"));
				else if(egeosDCL.getLivelloRuolo().equals("2")){
					egeosDCL.setProvXLS(event.getRecord().getAttributeAsString("idProv"));
					ToolStripButtonProvince.getSelctOperatoreProvincia().setAttribute("figlio",event.getRecord().getAttributeAsString("figlio"));
				}	

				ListGridRecord[] listrecord = new ListGridRecord[getRecords().length];
				listrecord = getRecords();

				String[] wkt = new String[listrecord.length];

				for(int i = 0;i<listrecord.length;i++)
				{
					wkt[i] = listrecord[i].getAttributeAsString("wkt");
				}
			}
		});
	


		//		try{
		this.addDataArrivedHandler(new DataArrivedHandler() {

			
			public void onDataArrived(DataArrivedEvent event) {
				String idProvPDF="";
				
				ListGridRecord[] llistRec = new ListGridRecord[getRecords().length];
				OLMap_new.destroyMap();
				llistRec = getRecords();
				
				for(int i = 0;i<llistRec.length;i++){
					idProvPDF+=llistRec[i].getAttributeAsString("idProv")+",";
					//System.out.println("ID PROV: "+llistRec[i].getAttributeAsString("idProv"));
					
				}
				if(idProvPDF.trim().length()>0){
					idProvPDF=idProvPDF.substring(0, idProvPDF.trim().length()-1);
				}
				
				System.out.println("Province de Merda"+idProvPDF);
				egeosDCL.setProvXLS(idProvPDF);
				
				//System.out.println("DENTRO DATA ARRIVED HANDLER");
				// TODO Auto-generated method stub
				
				if(llistRec[0].getAttributeAsString("wkt")!=null && !llistRec[0].getAttributeAsString("wkt").equals("")){

					if(llistRec[0].getAttributeAsString("fuso").equalsIgnoreCase("w"))
						fusoProvince = "EPSG:3003";
					else
						fusoProvince = "EPSG:3004";

					OLMap_new.loadOLEProvince("map","",fusoProvince);
					for(int i = 0;i<llistRec.length;i++)
					{

						String colore = egeosDCL.calcolaColoreProvince(new Integer(llistRec[i].getAttributeAsString("tot")),
								new Integer(llistRec[i].getAttributeAsString("lavorate")!=null?llistRec[i].getAttributeAsString("lavorate"):"0"),
								new Integer(llistRec[i].getAttributeAsString("assegnate")!=null?llistRec[i].getAttributeAsString("assegnate"):"0"),
								new Integer(llistRec[i].getAttributeAsString("noassegnate")!=null?llistRec[i].getAttributeAsString("noassegnate"):"0"),
								new Integer(llistRec[i].getAttributeAsString("sel")!=null?llistRec[i].getAttributeAsString("sel"):"0"),
								new Integer(llistRec[i].getAttributeAsString("stato")!=null?llistRec[i].getAttributeAsString("stato"):"0"));

						OLMap_new.addWKTProvince("map",llistRec[i].getAttributeAsString("wkt"),
																		llistRec[i].getAttributeAsString("fuso"),
																		llistRec[i].getAttributeAsString("idProv"),
																		llistRec[i].getAttributeAsString("denoProv"),
																		i,
																		colore,
																		new Integer(llistRec[i].getAttributeAsString("sel")!=null?llistRec[i].getAttributeAsString("sel"):"0"),
																		llistRec[i].getAttributeAsString("figlio"));		
					}	
					OLMap_new.attivaSelezione();
					//	markForRedraw();
					setShowRecordComponentsByCell(true);

				}//if wkt vuoto
				else{
					SC.say("Non vi sono dati associati per l&apos;utente selezionato");
					//egeosDCL.redirectGwt(GWT.getModuleBaseURL()+"egeosDCL.html?gwt.codesvr=127.0.0.1:9997");
				}

			}
		});
		//		}
		//		catch(Exception e)
		//		{
		//			
		//		}
	}
	
	/**
	 * AGGIORNA LA LISTA DELLE PROVINCE
	 * @param tipo
	 */
	public void refreshListaProvince(String tipo)
	{
		try{
//			ListGridRecord selectedRecord = ListGridContestiJSON.getListProvJSON().getSelectedRecord();  
//			if(selectedRecord != null) {  
//				ListGridRecord newRecord = new ListGridRecord();  
//				newRecord.setAttribute("idProv", selectedRecord.getAttribute("idProv"));  
//
//				if(tipo.equals("ASSOCIA")){
//					newRecord.setAttribute("assegnate", new Integer(selectedRecord.getAttribute("assegnate"))+1);
//					newRecord.setAttribute("figlio",selectedRecord.getAttribute("assegnate"));
//				}					 
//				else if(tipo.equals("DISASSOCIA")){
//					newRecord.setAttribute("assegnate",new Integer(selectedRecord.getAttribute("assegnate"))-1);
//					newRecord.setAttribute("figlio","");
//				}
//
//				ListGridContestiJSON.getListProvJSON().updateData(newRecord);  
//			} 
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
				listProvJSON = new ListGridProvinceJSON(ListGridContestiJSON.getComboRegioni().getValue().toString());
				
				egeosDCL.setListGridProvince(listProvJSON);
				
				LeftLayout.getvLayoutGridRegioni().addMember(stripProvince);
				LeftLayout.getvLayoutGridRegioni().addMember(listProvJSON);
			}
			else{
				if(egeosDCL.getLivelloRuolo().equals("1"))
					listProvJSON = new ListGridProvinceJSON(ListGridContestiJSON.getComboRegioni().getValue().toString());
				else if(egeosDCL.getLivelloRuolo().equals("2"))
					listProvJSON = new ListGridProvinceJSON("0");
					
				ToolStripButtonProvince stripProvince = new ToolStripButtonProvince();
				LeftLayout.getvLayoutGridRegioni().addMember(stripProvince);
				LeftLayout.getvLayoutGridRegioni().addMember(listProvJSON);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}



	/**
	 * AGGIORNA LA LISTA DELLE REGIONI
	 * @param tipo
	 */
	public void refreshListaRegioni(String tipo)
	{
		try{
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
			


		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * SELEZIONA LA RIGA DELLA PROVINCIA TRAMITE SELEZIONE SULLA MAPPA
	 * @param idProv
	 * @param descProv
	 */
	public static void selectedRow(String idProv,String descProv){

		try
		{
			//System.out.println("DENTRO SELECTED ROW: idProv: "+idProv+" - descProv: "+descProv);
			grid.deselectAllRecords();

			for(int i = 0;i<grid.getRecords().length;i++)
			{
				if(grid.getRecords()[i].getAttributeAsString("idProv").equals(idProv)){
					grid.selectRecord(i);
					//					//System.out.println("setto il valore nella text box: "+grid.getRecords()[i].getAttributeAsString("idProv"));
					//					//System.out.println("id prov ::::: "+idProv);
					//					//System.out.println("desc prov ::::: "+descProv);
					if(egeosDCL.getLivelloRuolo().equals("1"))
						ToolStripButtonProvince.getProvinciaSel().setValue(descProv);
					else if(egeosDCL.getLivelloRuolo().equals("2"))
						ToolStripButtonProvince.getProvinciaSel().setValue(descProv);
				}

			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


	}

	
	protected Canvas createRecordComponent(final ListGridRecord record, Integer colNum) {  

		String fieldName = this.getFieldName(colNum);  
		
		if(record.getAttributeAsString("stato")!=null && record.getAttributeAsString("stato").equals("2"))
		{
			record.setCustomStyle("changedCell"+record.getAttributeAsString("stato"));
		}
		if (fieldName.equals("xls")){
			final ImgButton chartImg = new ImgButton();  
			chartImg.setShowDown(false);  
			chartImg.setShowRollOver(false);  
			chartImg.setAlign(Alignment.CENTER);  		
			chartImg.setSrc("excel.png");
			chartImg.setPrompt("Esporta dettaglio in XLS");
			chartImg.setHeight(16);  
			chartImg.setWidth(16);

			chartImg.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
				
				public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
					Window.open(
                    		GWT.getModuleBaseURL()+"DettaglioEFAXLSServlet?numeElenco="+egeosDCL.getNumElenco()+"&prov="+record.getAttributeAsString("idProv"),
                            "_blank", "width=600,height=550,scrollbars=yes,resizable=yes");
				}
			});
			return chartImg;
		} else if (fieldName.equals("Sele")) {  
			HLayout recordCanvas = new HLayout(3);  
			recordCanvas.setHeight(22);  
			recordCanvas.setWidth(20);
			recordCanvas.setAlign(Alignment.CENTER);  
			ImgButton disassImg = new ImgButton();  
			disassImg.setShowDown(false);  
			disassImg.setShowRollOver(false);  
			disassImg.setLayoutAlign(Alignment.CENTER);  
			disassImg.setSrc("disassocia.jpg");
			disassImg.setPrompt("Disassocia la provincia");  
			disassImg.setHeight(16);  
			disassImg.setWidth(16);  

			disassImg.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {

				
				public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
					// TODO Auto-generated method stub
					//selectedRow(record.getAttributeAsString("idProv"),record.getAttributeAsString("denoProv"));
					String appo = ToolStripButtonProvince.getSelctOperatoreProvincia().getAttributeAsString("figlio");

					//System.out.println("OPERATORE PROVINCIA FIGLIO: "+appo);
					//System.out.println("MAP SOCI SIZE: "+egeosDCL.getMapSociDaAssegnare().size());
					String figlio = "";

					Set<String> keys = egeosDCL.getMapSociDaAssegnare().keySet();
					for(String k:keys){
						//System.out.println(k+" -- "+egeosDCL.getMapSociDaAssegnare().get(k));
						if(egeosDCL.getMapSociDaAssegnare().get(k).equals(appo))
						{
							figlio = k;
						}
					}
					// //System.out.println("ID FIGLIO: "+figlio);
					if(figlio!=null && !figlio.equals(""))
					{
						valoreUtente = figlio;
					}
					else{
						valoreUtente = ToolStripButtonProvince.getSelctOperatoreProvincia().getValueAsString().replace("\"","");
					}

					//System.out.println("VALORE UTENTE FINALE: "+valoreUtente);

					RPCRequest request = new RPCRequest();
					// Note data could be a String, Map or Record
					request.setData("Some data to send to the client");
					request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=assegnaUtenteProvinciaRefresh&idRegione="+ListGridContestiJSON.getComboRegioni().getValueAsString()+"&idPadre="+egeosDCL.getIdUtente()+"&idProvincia="+record.getAttributeAsString("idProv")+"&numElenco="+egeosDCL.getNumElenco()+"&idUtente="+egeosDCL.getIdUtente()+"&op=1");
					//		//System.out.println("CHIAMO SERVIZIO UTENTI ASSIOCIATI");
					RPCManager.sendRequest(request, new RPCCallback() {

						
						public void execute(RPCResponse response, Object rawData, RPCRequest request) {
							winLoad.show();
							// TODO Auto-generated method stub
							//System.out.println("rawData:::: "+rawData);
							SC.say(egeosDCL.fireMessage(rawData));
							
							if(egeosDCL.getLivelloRuolo().equals("1")){
								refreshListaRegioni("DISASSOCIA");
								refreshListaProvince("DISASSOCIA");
							}
							else if(egeosDCL.getLivelloRuolo().equals("2"))
								//refreshListaRegioni("DISASSOCIA");
								refreshListaProvince("DISASSOCIA");
							winLoad.hide();
						}
					});
				}
			});

			ImgButton assImg = new ImgButton();  
			assImg.setShowDown(false);  
			assImg.setShowRollOver(false);  
			assImg.setLayoutAlign(Alignment.CENTER);  
			assImg.setSrc("associa.gif");
			assImg.setPrompt("Associa la provincia");  
			assImg.setHeight(16);  
			assImg.setWidth(16);  

			assImg.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {

				
				public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
					//selectedRow(record.getAttributeAsString("idProv"),record.getAttributeAsString("denoProv"));
					if(record.getAttributeAsString("figlio")==null || record.getAttributeAsString("figlio").equals(""))
					{
						if((record.getAttributeAsString("idProv")!=null && !record.getAttributeAsString("idProv").equals(""))&&
								(ToolStripButtonProvince.getSelctOperatoreProvincia().getValueAsString()!=null && !ToolStripButtonProvince.getSelctOperatoreProvincia().getValueAsString().equals("")&&!ToolStripButtonProvince.getSelctOperatoreProvincia().getValueAsString().equals("Selezionare un operatore"))	){
							winLoad.show();
							RPCRequest request = new RPCRequest();
							// Note data could be a String, Map or Record
							request.setData("Some data to send to the client");
							//System.out.println("STO CAZZO ");
							request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=assegnaUtenteProvinciaRefresh&idRegione="+ListGridContestiJSON.getComboRegioni().getValueAsString()+"&idPadre="+egeosDCL.getIdUtente()+"&idProvincia="+record.getAttributeAsString("idProv")+"&numElenco="+egeosDCL.getNumElenco()+"&idUtente="+ToolStripButtonProvince.getSelctOperatoreProvincia().getValueAsString().replace("\"","")+"&op=0");
							//		//System.out.println("CHIAMO SERVIZIO UTENTI ASSIOCIATI");
							RPCManager.sendRequest(request, new RPCCallback() {

								
								public void execute(RPCResponse response, Object rawData, RPCRequest request) {
									// TODO Auto-generated method stub
									//System.out.println("rawData:::: "+rawData);
									SC.say(egeosDCL.fireMessage(rawData));
									if(egeosDCL.getLivelloRuolo().equals("1")){
										refreshListaRegioni("DISASSOCIA");
										refreshListaProvince("DISASSOCIA");
									}
									else if(egeosDCL.getLivelloRuolo().equals("2"))
										//refreshListaRegioni("DISASSOCIA");
										refreshListaProvince("DISASSOCIA");
									winLoad.hide();
								}
							});
						}
						else
							SC.say("Selezionare la provincia e l&apos;operatore da assegnare");
					}
					else
					{
						SC.confirm("Provincia gi&agrave; assegnata,Eseguire una nuova assegnazione?", new BooleanCallback() {

							
							public void execute(Boolean value) {
								winLoad.show();
								// TODO Auto-generated method stub
								if(value){
									RPCRequest request = new RPCRequest();
									// Note data could be a String, Map or Record
									request.setData("Some data to send to the client");
									request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=assegnaUtenteProvinciaRefresh&idRegione="+ListGridContestiJSON.getComboRegioni().getValueAsString()+"&idPadre="+egeosDCL.getIdUtente()+"&idProvincia="+record.getAttributeAsString("idProv")+"&numElenco="+egeosDCL.getNumElenco()+"&idUtente="+ToolStripButtonProvince.getSelctOperatoreProvincia().getValueAsString().replace("\"","")+"&op=0");
									RPCManager.sendRequest(request, new RPCCallback() {

										
										public void execute(RPCResponse response, Object rawData, RPCRequest request) {
											// TODO Auto-generated method stub
											//System.out.println("rawData:::: "+rawData);
											LeftLayout.getvLayoutGridRegioni().getMember("REGIONI_GRID_ID").redraw();
											
											if(egeosDCL.getLivelloRuolo().equals("1")){
												refreshListaRegioni("DISASSOCIA");
												refreshListaProvince("DISASSOCIA");
											}
											else if(egeosDCL.getLivelloRuolo().equals("2"))
												//refreshListaRegioni("DISASSOCIA");
												refreshListaProvince("DISASSOCIA");
												
											SC.say(egeosDCL.fireMessage(rawData));
											winLoad.hide();
										}
									});
								}
								else{
									winLoad.hide();
								}
							}
						});
					}
				}
			});


			ImgButton validaImg = new ImgButton();  
			validaImg.setShowDown(false);  
			validaImg.setShowRollOver(false);  
			validaImg.setLayoutAlign(Alignment.CENTER);  
			validaImg.setSrc("valida.png");
			validaImg.setLeft(20);
			validaImg.setPrompt("Valida la provincia");  
			validaImg.setHeight(16);  
			validaImg.setWidth(16);  

			validaImg.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {

				
				public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {

					//selectedRow(record.getAttributeAsString("idProv"),record.getAttributeAsString("denoProv"));
					winLoad.show();
					//System.out.println("ID UTENTE A CUI ASSEGNO: "+ToolStripButtonProvince.getSelctOperatoreProvincia().getValueAsString());
					// TODO Auto-generated method stub
					RPCRequest request = new RPCRequest();
					// Note data could be a String, Map or Record
					request.setData("Some data to send to the client");
					request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=validaUtenteProvinciaService&idProvincia="+record.getAttributeAsString("idProv")+"&idUtente="+egeosDCL.getIdUtente()+"&numElenco="+egeosDCL.getNumElenco());
					//		//System.out.println("CHIAMO SERVIZIO UTENTI ASSIOCIATI");
					RPCManager.sendRequest(request, new RPCCallback() {

						
						public void execute(RPCResponse response, Object rawData, RPCRequest request) {
							winLoad.show();
							// TODO Auto-generated method stub
							//System.out.println("rawData:::: "+rawData);
							
							SC.say(egeosDCL.fireMessage(rawData));
							if(egeosDCL.getLivelloRuolo().equals("1")){
								refreshListaRegioni("DISASSOCIA");
								refreshListaProvince("DISASSOCIA");
							}
							else if(egeosDCL.getLivelloRuolo().equals("2"))
								refreshListaProvince("DISASSOCIA");
							winLoad.hide();
						}
					});

				}
			});
			recordCanvas.setMargin(5);
			recordCanvas.setLeft(10);
			//recordCanvas.setPadding(10);
			if(!egeosDCL.getLivelloRuolo().equals("3"))
			if(record.getAttributeAsString("figlio")!=null && !record.getAttributeAsString("figlio").equals("")){
				recordCanvas.addMember(disassImg);
				if(!record.getAttributeAsString("stato").equals("2"))
					recordCanvas.addMember(validaImg);
			}
			else{
				recordCanvas.addMember(assImg);
			}

			//	recordCanvas.addMember(disassImg);


			return recordCanvas;  
		} else {  
			return null;  
		}  

	}  
}
