package it.gis.egeosDCL.client.Layout.LayoutObjects;

import it.gis.egeosDCL.client.egeosDCL;
import it.gis.egeosDCL.client.Layout.LeftLayout;
import it.gis.egeosDCL.client.Layout.MapEvents;
import it.gis.egeosDCL.client.map.OLMap_new;

import java.util.HashMap;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.rpc.RPCCallback;
import com.smartgwt.client.rpc.RPCManager;
import com.smartgwt.client.rpc.RPCRequest;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.types.RecordComponentPoolingMode;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.DataArrivedEvent;
import com.smartgwt.client.widgets.grid.events.DataArrivedHandler;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * CREA LA GRIGLIA DEI COMUNI TRAMITE UNO SCRIPT JSON
 * @version 1.0
 */
public class ListGridComuniJSON extends ExportableGrid{

					String idComuneSel = "";
			static 	ListGrid 			grid;
			static 	WinLoading 			winLoad = new WinLoading("Elaborazione in corso..."); 
					String 				valoreUtente;
					String 				idProvincia_ = "";
	public 	static 	HashMap<String,String> hashColoriUtenti = new HashMap<String, String>();
	
	
	private static 	ListGridComuniJSON  	listComuJSON 			= null;
		
		
		protected Canvas createRecordComponent(final ListGridRecord record, Integer colNum) {  
	
			String fieldName = this.getFieldName(colNum);  
			
			if(!record.getAttributeAsString("validate").equals("0"))
			{
				record.setCustomStyle("changedCell2");
			}
			
			if (fieldName.equals("Sele")) {  
				HLayout recordCanvas = new HLayout(3);  
				recordCanvas.setHeight(22);  
				recordCanvas.setWidth(20);
				recordCanvas.setAlign(Alignment.CENTER);  
				
				recordCanvas.setMargin(5);
				recordCanvas.setLeft(10);
			
				return recordCanvas;  
			} else {  
				return null;  
			}  
	
		}  
	/**
	 * AGGIORNA LA LISTA DEI COMUNI	
	 */
	public void refreshListaComuni()
	{
		if(LeftLayout.getvLayoutGridRegioni().getMember("COMUNI_GRID_ID")!=null){

			LeftLayout.getvLayoutGridRegioni().getMember("COMUNI_GRID_ID").destroy();
			LeftLayout.getvLayoutGridRegioni().getMember("ID_TOOLSTRIP_QUADRANTI").destroy();


			ListGridComuniJSON gridComuni = new ListGridComuniJSON(ListGridContestiJSON.getComboProvince().getValueAsString());
			ToolStripButtonQuadranti stripQuadranti = new ToolStripButtonQuadranti();

			LeftLayout.getvLayoutGridRegioni().addMember(stripQuadranti);
			LeftLayout.getvLayoutGridRegioni().addMember(gridComuni);

		}
		else{
			if(LeftLayout.getvLayoutGridRegioni().getMember("ID_TOOLSTRIP_QUADRANTI")!=null){
				LeftLayout.getvLayoutGridRegioni().getMember("ID_TOOLSTRIP_QUADRANTI").destroy();
			}
			ListGridComuniJSON gridComuni = new ListGridComuniJSON(ListGridContestiJSON.getComboProvince().getValueAsString());
			ToolStripButtonQuadranti stripQuadranti = new ToolStripButtonQuadranti();

			LeftLayout.getvLayoutGridRegioni().addMember(stripQuadranti);
			LeftLayout.getvLayoutGridRegioni().addMember(gridComuni);
		}
	}

	public ListGridComuniJSON(String idProvincia)
	{
		super();
		this.idProvincia_ =idProvincia;
		setListComuJSON(this);
		setShowAllRecords(true);
		setShowAllColumns(true);
		this.setID("COMUNI_GRID_ID");
		
		grid=this;
		/*CREO LA TABELLA DELLE PROVINCE*/
		DataSource dsProv = new DataSource();
		dsProv.setDataFormat(DSDataFormat.JSON);  

		DataSourceField noAssComuDSField = new DataSourceField("noassegnate", FieldType.TEXT,"No Ass.",5);  
		DataSourceField assComuDSField = new DataSourceField("assegnate", FieldType.TEXT,"Ass.",5);  
		DataSourceField totComuDSField = new DataSourceField("tot", FieldType.TEXT,"Tot",5);  
		DataSourceField lavComuDSField = new DataSourceField("lavorate", FieldType.TEXT,"Lav.",5);
		
		DataSourceField validatiDSField = new DataSourceField("validate", FieldType.TEXT,"Val.",5);

	//	DataSourceField seleComuDSField = new DataSourceField("Sele", FieldType.TEXT,"Sele",12);  
		//	seleComuDSField.setHidden(true);
		DataSourceField descComuDSField = new DataSourceField("nomeComune", FieldType.TEXT,"Comune",40);  
		//descComuDSField.setCanFilter(true);
		DataSourceField idComuCodeField = new DataSourceField("idComune", FieldType.TEXT, "idComune");  
		idComuCodeField.setPrimaryKey(true);
		idComuCodeField.setHidden(true);
		DataSourceField wktidComuCodeField = new DataSourceField("wkt", FieldType.TEXT,"wkt");  
		wktidComuCodeField.setHidden(true);

		dsProv.setFields(descComuDSField,assComuDSField,noAssComuDSField,lavComuDSField,validatiDSField,totComuDSField,idComuCodeField,wktidComuCodeField);  
		
		dsProv.setDataURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=comuniRefreshService&idProvincia="+idProvincia+"&numElenco="+egeosDCL.getNumElenco());  

		dsProv.setRecordXPath("/TodoLista");

		this.setWidth100();  
		this.setHeight100();  

		this.setDataSource(dsProv);  
		this.setAutoFetchData(true);  
		this.setShowRecordComponents(true);          
		this.setShowRecordComponentsByCell(true);
		setRecordComponentPoolingMode(RecordComponentPoolingMode.RECYCLE);

		this.addDataArrivedHandler(new DataArrivedHandler() {

			
			public void onDataArrived(DataArrivedEvent event) {
				//System.out.println("DATA ARRIVED::::::::::::::::::::::::::::: ");
				OLMap_new.loadOLEComuni("map","", "EPSG:3004");
				OLMap_new.removeFeatureWKTComuni();
				// TODO Auto-generated method stub
				ListGridRecord[] llistRec = new ListGridRecord[getRecords().length];

				llistRec = getRecords();

				for(int i = 0;i<llistRec.length;i++)
				{
					String colore = egeosDCL.calcolaColoreComune(new Integer(llistRec[i].getAttributeAsString("tot")),
							new Integer(llistRec[i].getAttributeAsString("lavorate")),
							new Integer(llistRec[i].getAttributeAsString("assegnate")),
							new Integer(llistRec[i].getAttributeAsString("noassegnate")),
							new Integer(llistRec[i].getAttributeAsString("validate")),
							0);
					
					OLMap_new.addWKTComuni("Comuni",llistRec[i].getAttributeAsString("wkt"), llistRec[i].getAttributeAsString("idComune"),llistRec[i].getAttributeAsString("nomeComune"), llistRec[i].getAttributeAsString("fuso"),i,colore);			
				}	
				MapToolStrip.getZoomBOX_bt().setDisabled(true);
				OLMap_new.attivaSelezione();

			}
		});


		this.addCellClickHandler(new CellClickHandler() {

			
			public void onCellClick(CellClickEvent event) {
				// TODO Auto-generated method stub
				MapEvents.setAppo("");
				
				if(egeosDCL.isHo_clikkato_quadranti())
				{
					egeosDCL.setHo_clikkato_quadranti(false);
					ListGridContestiJSON.getComboProvince().setValue(idProvincia_);
					ChangedEvent ev = new ChangedEvent(ListGridContestiJSON.getComboProvince().getJsObj());
					
					ListGridContestiJSON.getComboProvince().fireEvent(ev);
				}
				//OLMap_new.destroyMap();
			//	caricaQuadrantiSenzaGriglia(event.getRecord().getAttributeAsString("idComune"),egeosDCL.getNumElenco());
				egeosDCL.getDett_layout().show();
				
				if(egeosDCL.getDett_layout().getMember("DettaglioComuniListGrid_ID")!=null){

					egeosDCL.getDett_layout().getMember("ID_DETT_TOOLSTRIP_COMUNI").destroy();
					egeosDCL.getDett_layout().getMember("DettaglioComuniListGrid_ID").destroy();

					ToolStripButtonDettaglioComune stripDettComu = 
							new ToolStripButtonDettaglioComune(event.getRecord().getAttributeAsString("idComune"),
									event.getRecord().getAttributeAsString("nomeComune"), 
									event.getRecord().getAttributeAsString("assegnate"), 
									event.getRecord().getAttributeAsString("noassegnate"), 
									event.getRecord().getAttributeAsString("tot"),
									"");
					//System.out.println("ListGridContestiJSON.getComboProvince().getValueAsString(): "+ListGridContestiJSON.getIdProvinciaRiepilogo());
					//System.out.println("event.getRecord().getAttributeAsString(idComune): "+event.getRecord().getAttributeAsString("idComune"));
					DettaglioComuniListGrid dettComuGrid = new DettaglioComuniListGrid(ListGridContestiJSON.getIdProvinciaRiepilogo(),event.getRecord().getAttributeAsString("idComune"));
					egeosDCL.getDett_layout().addMember(stripDettComu);
					egeosDCL.getDett_layout().addMember(dettComuGrid);
					//ListGridContestiJSON.getBt_valida().setDisabled(false);
					//MapLayout.getHtmlPanel().resizeTo("99%", "99%");
				}
				else{
					//System.out.println("ListGridContestiJSON.getComboProvince().getValueAsString(): "+ListGridContestiJSON.getIdProvinciaRiepilogo());
					//System.out.println("event.getRecord().getAttributeAsString(idComune): "+event.getRecord().getAttributeAsString("idComune"));
					ToolStripButtonDettaglioComune stripDettComu = 
							new ToolStripButtonDettaglioComune(event.getRecord().getAttributeAsString("idComune"),
									event.getRecord().getAttributeAsString("nomeComune"), 
									event.getRecord().getAttributeAsString("assegnate"), 
									event.getRecord().getAttributeAsString("noassegnate"), 
									event.getRecord().getAttributeAsString("tot"),
									"");

				
					DettaglioComuniListGrid dettComuGrid = new DettaglioComuniListGrid(ListGridContestiJSON.getIdProvinciaRiepilogo(),event.getRecord().getAttributeAsString("idComune"));
					egeosDCL.getDett_layout().addMember(stripDettComu);
					egeosDCL.getDett_layout().addMember(dettComuGrid);
					//ListGridContestiJSON.getBt_valida().setDisabled(false);
				//	MapLayout.getHtmlPanel().resizeTo("100%", "100%");
				}

			}
		});
	}

	/**
	 * CARICA I QUADRANTI DEL COMUNE SELEZIONATO SULLA MAPPA
	 * @param idComune
	 */
	public static void caricaQuadrantiSenzaGriglia(String idComune,String numElenco)
	{
		winLoad = new WinLoading("Caricamento quadranti in corso..."); 

		winLoad.show();
		
		//OLMap_new.loadOLEComuni("map","","EPSG:4326");
		
		try {
			
			RPCRequest request = new RPCRequest();
			// Note data could be a String, Map or Record
			request.setData("Some data to send to the client");
			request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=CLOBquadriComuniRefreshService&idComune="+idComune+"&numElenco="+numElenco);
			//String urlService = "http://www.sourcedemo.net:7001/"+EgeosDCLctx.URL_SERVER+"CLOBquadriComuniRefreshService?idComune="+idComune+"&numElenco="+numElenco;
			
			RPCManager.sendRequest(request,new RPCCallback() {
				
				public void execute(RPCResponse response, Object rawData, RPCRequest request) {
					JSONValue value = JSONParser.parseLenient(rawData+"");
					
										OLMap_new.removeFeatureWKTQuadranti();
					
										JSONObject obj = value.isObject();
										JSONValue app = obj.get("TodoLista");
										JSONArray arr = app.isArray();
										
										//hashColoriUtenti = new HashMap<>();
					
										if(arr!=null)
										{  
											LeftLayout.getQuadri_legendPanel().setContents("");
											hashColoriUtenti = new HashMap<String, String>();
											String colore = "#FFFFFF";
											//										for(int i = 0;i<arr.size();i++)
											for(int i = 0;i<arr.size();i++)
											{
												
												JSONObject ob = arr.get(i).isObject();
											
												JSONObject objSel = (JSONObject) ob.get("sel");
												String selGrid = "0";
											
												if(objSel!=null)
												{
												//	System.out.println("obj sel esiste");
													colore = "#FFFFFF";
												//	System.out.println("DESC UTENTE: "+objSel.get("descUtente"));
													if(objSel.get("descUtente")!=null){
												//		System.out.println("ENTRATO OBJ SEL stato::::::: "+objSel.get("fl_stato"));
													//	String[] appoDescUtente = new String[];
														
														if(objSel.get("fl_stato").toString().replace("\"","").equals("0"))
														{
															System.out.println("FLAG STATO 11");
															colore = "#3835FF";//blue - assegnato
														}
														else if(objSel.get("fl_stato").toString().replace("\"","").equals("4")){
															colore = "#408747";//verde - validato
														}
														else if(objSel.get("fl_stato").toString().replace("\"","").equals("5")){
															colore = "#FF0000";//rosso - lavorato
														}
														
													}
												}
											
												if(!ob.get("wkt").toString().replace("\"","").equals(""))
												{
													String wkt=ob.get("wkt").toString().replace("\"","");
													String grid_id=ob.get("grid_id").toString().replace("\"","");
													String fuso=ob.get("fuso").toString().replace("\"","");
													
													String coloreQuadrante = "";
													//System.out.println("11111111111111111"+objSel.get("descUtente"));
//													if(objSel.get("descUtente")!=null){
//														//
//														coloreQuadrante = hashColoriUtenti.get(objSel.get("descUtente").toString().replace("\"",""));
//													}
//													else{
//														System.out.println("22222222222222");
//														coloreQuadrante = "#FFFFFF";
//													}
												//	System.out.println("COLORE QUADRANTI DEMMERDA: "+colore);
													OLMap_new.addWKTQuadranti("Quadranti",wkt,grid_id,fuso,colore,i,selGrid);
					
												}
												else
													SC.say("GEOMETRIA DEI QUADRANTI ASSENTE");
											}
											
											//LeftLayout.getQuadri_legendPanel().setContents(creaTabellaLegendaQuadri());
					
										}
										//			//System.out.println("111111111111111111111");
										OLMap_new.refreshQuadranti();
										winLoad.hide();
										MapToolStrip.getZoomBOX_bt().setDisabled(false);
										OLMap_new.attivaSelezione_Quadranti();
					
				}
			});

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * SELEZIONA LA RIGA DEL COMUNE SELEZIONATO TRAMITE CLICK SULLA MAPPA
	 * @param idComu
	 * @param descComu
	 */
	public static void selectedRow(String idComu,String descComu){

		try
		{
//			ToolStripButtonQuadranti.getComuneSel().setValue(descComu);
//			ToolStripButtonQuadranti.getComuneSel().setAttribute("idComune",idComu);
			for(int i = 0;i<grid.getRecords().length;i++)
			{
				if(grid.getRecords()[i].getAttributeAsString("idComune").equals(idComu)){
					grid.selectRecord(i);
					//System.out.println("seleziono riga comuni");
//					ToolStripButtonQuadranti.getComuneSel().setValue(descComu);
//					ToolStripButtonQuadranti.getComuneSel().setAttribute("idComune",idComu);
				}

			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}


	}
	
	/**
	 * ASSOCIA UN COLORE GENERATO IN MANIERA RANDOMINCA AI QUADRANTI 
	 * @param descUtente
	 * @return
	 */
	public static String getRandomColor (String descUtente) { 

	        String[] letters = "0123456789ABCDEF".split("");
	        String color = "#";
	        for (int i = 0; i < 6; i++ ) {
	        	int mat_rand = (int) Math.round(Math.random() * 15);
	        	//System.out.println("mat_rand "+mat_rand);
	            color += letters[mat_rand];
	            
	            
	        }
	        //System.out.println("COLORE: "+color);
	        
	        if(color.length()<7)
	        	color = color+1;
	        
	        if(!hashColoriUtenti.containsKey(descUtente)&& !hashColoriUtenti.containsKey(color))
            {
	        	
	        	hashColoriUtenti.put(descUtente, color);
            }
	        //System.out.println("map colori:"+hashColoriUtenti.toString());
//	        //System.out.println("HASHMAP COLORI: "+hashColoriUtenti.toString());
//	        //System.out.println("HASHMAP COLORI SIZE: "+hashColoriUtenti.size());
//	        //System.out.println("COLOR: "+color);
	        return color;
	    
	}
	
	/**
	 * LEGENDA DEL LAYER DEI QUADRANTI
	 * @return
	 */
	public static String creaTabellaLegendaQuadri()
	{
		LeftLayout.getQuadri_legendPanel().setContents("");
		String tabella = "<table width='70%'>";
				tabella +="<tr><td colspan='2'>Legenda quadri per utente</td>";
			for(Map.Entry<String, String> entry : hashColoriUtenti.entrySet()){
	         
				tabella +="<tr>";
				
				tabella +="<td width='50%'>"+entry.getKey()+"</td><td width='50%' style='background-color:"+entry.getValue()+"'>"+"</td>";
				tabella +="</tr>";
		//  //System.out.println(entry.getKey() + " - " + entry.getValue());
	            
	        }
				tabella +="</table>";
		
		
		return tabella;
	}
	

	public static ListGridComuniJSON getListComuJSON() {
		return listComuJSON;
	}

	public static void setListComuJSON(ListGridComuniJSON listComuJSON) {
		ListGridComuniJSON.listComuJSON = listComuJSON;
	}
	public static HashMap<String, String> getHashColoriUtenti() {
		return hashColoriUtenti;
	}
	public static void setHashColoriUtenti(HashMap<String, String> hashColoriUtenti) {
		ListGridComuniJSON.hashColoriUtenti = hashColoriUtenti;
	}
}
