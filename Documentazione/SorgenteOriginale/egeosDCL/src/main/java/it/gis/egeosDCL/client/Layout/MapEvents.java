package it.gis.egeosDCL.client.Layout;

import java.util.HashMap;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.smartgwt.client.rpc.RPCCallback;
import com.smartgwt.client.rpc.RPCManager;
import com.smartgwt.client.rpc.RPCRequest;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.grid.ListGridRecord;

import it.gis.egeosDCL.client.egeosDCL;
import it.gis.egeosDCL.client.Layout.LayoutObjects.DettaglioComuniListGrid;
import it.gis.egeosDCL.client.Layout.LayoutObjects.ListGridComuniJSON;
import it.gis.egeosDCL.client.Layout.LayoutObjects.ListGridContestiJSON;
import it.gis.egeosDCL.client.Layout.LayoutObjects.ListGridProvinceJSON;
import it.gis.egeosDCL.client.Layout.LayoutObjects.ListGridRegioniJSON;
import it.gis.egeosDCL.client.Layout.LayoutObjects.MapToolStrip;
import it.gis.egeosDCL.client.Layout.LayoutObjects.ToolStripButtonComuni;
import it.gis.egeosDCL.client.Layout.LayoutObjects.ToolStripButtonDettaglioComune;
import it.gis.egeosDCL.client.Layout.LayoutObjects.ToolStripButtonProvince;
import it.gis.egeosDCL.client.Layout.LayoutObjects.WinLoading;

import it.gis.egeosDCL.client.map.OLMap_new;

/**
 * CLASSE DI GESTIONE DEGLI EVENTI SCATENATI DALLA MAPPA
 * @version 1.0
 * 
 */
public class MapEvents {
	public static String appo = "";
	static String idQuadranti = "";
	static WinLoading winLoad = new WinLoading("Elaborazione in corso..."); 	
	public static int quadriSel = 0;
	public static HashMap<String,String> hashColoriUtenti = new HashMap<String, String>();
	
	public static void selezionaRegione(String idRegione)
	{
		//System.out.println("ID REGIONE IN selezionaRegione: "+idRegione);
		ListGridContestiJSON.getComboRegioni().setValue(idRegione);
		ListGridRegioniJSON.selectedRow(idRegione);
		ChangedEvent ev = new ChangedEvent(ListGridContestiJSON.getComboRegioni().getJsObj());
		
		ListGridContestiJSON.getComboRegioni().fireEvent(ev);
		
		OLMap_new.attivaSelezione_PROV();
	}
	
	/**
	 * SELEZIONA LA RIGA SULLA GRIGLIA DELLE PROVINCE
	 * @param idProvincia
	 * @param descProv
	 * @param figlio
	 */
	public static void selezionaProvincia(String idProvincia,String descProv,String figlio)
	{
		//System.out.println("EVENTO SELEZIONA PROVINCIA");
		
		//System.out.println("PARAMS IN: IDPROV: "+idProvincia+ " DESC PROV: "+descProv+ " FIGLIO: "+figlio);
		if(egeosDCL.getLivelloRuolo().equals("1")){
			//System.out.println("idProvincia IN selezionaProvincia: "+idProvincia);
			ListGridProvinceJSON.selectedRow(idProvincia, descProv);
			ToolStripButtonProvince.getProvinciaSel().setValue(descProv);
			ToolStripButtonProvince.getSelctOperatoreProvincia().setAttribute("figlio",figlio);
			ToolStripButtonProvince.getProvinciaSel().setAttribute("idProv", idProvincia);
		}
		else if(egeosDCL.getLivelloRuolo().equals("2")){
			ToolStripButtonComuni.getProvinciaSel().setValue(descProv);
			ToolStripButtonComuni.getProvinciaSel().setAttribute("idProvincia", idProvincia);
			ToolStripButtonComuni.getProvinciaSel().setAttribute("figlio",figlio);
		}
		else if(egeosDCL.getLivelloRuolo().equals("3"))
		{
			ListGridContestiJSON.getComboProvince().setValue(idProvincia);
			ChangedEvent ev = new ChangedEvent(ListGridContestiJSON.getComboProvince().getJsObj());
			
			ListGridContestiJSON.getComboProvince().fireEvent(ev);
			
			
			
			OLMap_new.attivaSelezione();
		}
			
		
	
		
	}
	
	/**
	 * SELEZIONA LA RIGA SULLA GRIGLIA DEI COMUNI
	 * @param idComune
	 * @param descComune
	 */
	public static void selezionaComune(String idComune,String descComune)
	{
	//	ListGridComuniJSON.selectedRow(idComune, descComune);
		ListGridComuniJSON.selectedRow(idComune, descComune);
//		caricaQuadrantiSenzaGriglia(idComune,egeosDCL.getNumElenco());
//		if(LeftLayout.getvLayoutGridRegioni().getMember("ID_TOOLSTRIP_QUADRANTI")!=null)
//		{
//			caricaQuadrantiSenzaGriglia(idComune);
//			ToolStripButtonQuadranti.getBtAssegna().setDisabled(false);
//			ToolStripButtonQuadranti.getBtAssegna_all().setDisabled(false);
//			ToolStripButtonQuadranti.getComuneSel().setValue(idComune);
//		}
//		else{
//			ToolStripButtonQuadranti stripQuadranti = new ToolStripButtonQuadranti();
//			stripQuadranti.getBtAssegna().setDisabled(false);
//			stripQuadranti.getBtAssegna_all().setDisabled(false);
//			stripQuadranti.getComuneSel().setValue(idComune);
//			LeftLayout.getvLayoutGridRegioni().addMember(stripQuadranti);
//		}
		
		if(egeosDCL.getDett_layout().getMember("DettaglioComuniListGrid_ID")!=null){
			ListGridRecord selectedRecord = ListGridComuniJSON.getListComuJSON().getSelectedRecord(); 
			egeosDCL.getDett_layout().getMember("ID_DETT_TOOLSTRIP_COMUNI").destroy();
			egeosDCL.getDett_layout().getMember("DettaglioComuniListGrid_ID").destroy();
			
			ToolStripButtonDettaglioComune stripDettComu = 
					new ToolStripButtonDettaglioComune(selectedRecord.getAttributeAsString("idComune"),
							selectedRecord.getAttributeAsString("nomeComune"), 
							selectedRecord.getAttributeAsString("assegnate"), 
							selectedRecord.getAttributeAsString("noassegnate"), 
							selectedRecord.getAttributeAsString("tot"),
							"");

			DettaglioComuniListGrid dettComuGrid = new DettaglioComuniListGrid(ListGridContestiJSON.getIdProvinciaRiepilogo(),selectedRecord.getAttributeAsString("idComune"));
			egeosDCL.getDett_layout().addMember(stripDettComu);
			egeosDCL.getDett_layout().addMember(dettComuGrid);
			egeosDCL.getDett_layout().show();
		}
		else{
			//System.out.println("abracadabra");
			ListGridRecord selectedRecord = ListGridComuniJSON.getListComuJSON().getSelectedRecord(); 
			ToolStripButtonDettaglioComune stripDettComu = 
					new ToolStripButtonDettaglioComune(selectedRecord.getAttributeAsString("idComune"),
							selectedRecord.getAttributeAsString("nomeComune"), 
							selectedRecord.getAttributeAsString("assegnate"), 
							selectedRecord.getAttributeAsString("noassegnate"), 
							selectedRecord.getAttributeAsString("tot"),
							"");

			DettaglioComuniListGrid dettComuGrid = new DettaglioComuniListGrid(ListGridContestiJSON.getIdProvinciaRiepilogo(),selectedRecord.getAttributeAsString("idComune"));
			
			egeosDCL.getDett_layout().addMember(stripDettComu);
			egeosDCL.getDett_layout().addMember(dettComuGrid);
			egeosDCL.getDett_layout().show();
		}
		//SELEZIONO LA FEATURE SULLA MAPPA
		OLMap_new.selezionaFeatureComuni(idComune,descComune);
		
	}
	
	/**
	 * SELEZIONA IL QUADRANTE
	 * @param idQuadrante
	 */
	public static void selezionaQuadrante(String idQuadrante)
	{
		////System.out.println("idQuadrante IN selezionaQuadrante: "+idQuadrante);
		
		appo = appo+","+idQuadrante;
		//System.out.println("APPO::::::: "+appo);
	//	ToolStripButtonQuadranti.getStringQuadrantiSel().setValue(appo); 
		ToolStripButtonDettaglioComune.getStringQuadrantiSel().setValue(appo); 
		String appoSel = appo.substring(1,appo.length());
		setIdQuadranti(appo);	
		//System.out.println("getIdQuadranti::::::: "+getIdQuadranti());
		
		String[] arrSel =appoSel.split(","); 
		//System.out.println("QUADRANTI arrSel:"+arrSel.length);
		//quadriSel = arrSel.length;
		setQuadriSel(arrSel.length);
		ToolStripButtonDettaglioComune.getQuadriSel().setContents("Quadri selezionati: "+ arrSel.length);
		OLMap_new.attivaSelezione_Quadranti();
		
	}
	
	/**
	 * ELIMINA LA SELEZIONE DEL QUADRANTE
	 * @param idQuadrante
	 */
	public static void deselezionaQuadrante(String idQuadrante)
	{
//		//System.out.println("deselezionaQuadrante::::::: "+idQuadrante);
		////System.out.println("idQuadrante IN selezionaQuadrante: "+idQuadrante);
		ToolStripButtonDettaglioComune.getStringQuadrantiSel().setValue(0); 
//		//System.out.println("quadriSel::::::: "+quadriSel);
		quadriSel = 0;
		appo = "";
//		//System.out.println("quadriSel1222::::::: "+quadriSel);
//		appo = "";
//		//System.out.println("APPO::::::: "+appo);
//		ToolStripButtonQuadranti.getStringQuadrantiSel().setValue(appo); 
//		String appoSel = appo.substring(1,appo.length());
//		
//		//System.out.println("appoSel::::::: "+appoSel);
//		
//		String[] arrSel =appoSel.split(","); 
//		//System.out.println("QUADRANTI arrSel:"+arrSel.length);
		
		ToolStripButtonDettaglioComune.getQuadriSel().setContents("Quadri selezionati: "+quadriSel);
		
	}

	/**
	 * CARICA I QUADRANTI SULLA MAPPA
	 * @param idComune
	 */
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
													colore = "#FFFFFF";
													
													if(objSel.get("descUtente")!=null){
														
													//	String[] appoDescUtente = new String[];
														selGrid = "1";
													//	//System.out.println("SEL VALORIZZATO" +objSel.get("descUtente").toString().replace("\"",""));
														colore = "#3835FF";
														
														ListGridComuniJSON.getRandomColor(objSel.get("descUtente").toString().replace("\"",""));
					
													}
												}
											
												if(!ob.get("wkt").toString().replace("\"","").equals(""))
												{
													String wkt=ob.get("wkt").toString().replace("\"","");
													String grid_id=ob.get("grid_id").toString().replace("\"","");
													String fuso=ob.get("fuso").toString().replace("\"","");
													
													String coloreQuadrante = "";
													
													if(objSel.get("descUtente")!=null){
														//System.out.println("11111111111111111"+objSel.get("descUtente"));
														coloreQuadrante = hashColoriUtenti.get(objSel.get("descUtente").toString().replace("\"",""));
													}
													else{
														//System.out.println("11111111111111111");
														coloreQuadrante = "#FFFFFF";
													}
													
													OLMap_new.addWKTQuadranti("Quadranti",wkt,grid_id,fuso,coloreQuadrante,i,selGrid);
					
												}
												else
													SC.say("GEOMETRIA DEI QUADRANTI ASSENTE");
											}
											
											LeftLayout.getQuadri_legendPanel().setContents(ListGridComuniJSON.creaTabellaLegendaQuadri());
					
										}
										//			//System.out.println("111111111111111111111");
										OLMap_new.refreshQuadranti();
										winLoad.hide();
										MapToolStrip.getZoomBOX_bt().setDisabled(false);
										OLMap_new.attivaSelezione_Quadranti();
					
				}
			});
//			egeosDCL.getService().getQuadrantiJSON(idComune,egeosDCL.getNumElenco(),new AsyncCallback<String>() {
//
//				@Override
//				public void onSuccess(String rawData) {
//
//					JSONValue value = JSONParser.parseLenient(rawData+"");
//
//					OLMap_new.removeFeatureWKTQuadranti();
//
//					JSONObject obj = value.isObject();
//					JSONValue app = obj.get("TodoLista");
//					JSONArray arr = app.isArray();
//					
//					//hashColoriUtenti = new HashMap<>();
//
//					if(arr!=null)
//					{  
//						LeftLayout.getQuadri_legendPanel().setContents("");
//						hashColoriUtenti = new HashMap<String, String>();
//						String colore = "#FFFFFF";
//						//										for(int i = 0;i<arr.size();i++)
//						for(int i = 0;i<arr.size();i++)
//						{
//							
//							JSONObject ob = arr.get(i).isObject();
//						
//							JSONObject objSel = (JSONObject) ob.get("sel");
//							String selGrid = "0";
//						
//							if(objSel!=null)
//							{
//								colore = "#FFFFFF";
//								
//								if(objSel.get("descUtente")!=null){
//									
//								//	String[] appoDescUtente = new String[];
//									selGrid = "1";
//								//	//System.out.println("SEL VALORIZZATO" +objSel.get("descUtente").toString().replace("\"",""));
//									colore = "#3835FF";
//									
//									getRandomColor(objSel.get("descUtente").toString().replace("\"",""));
//
//								}
//							}
//						
//							if(!ob.get("wkt").toString().replace("\"","").equals(""))
//							{
//								String wkt=ob.get("wkt").toString().replace("\"","");
//								String grid_id=ob.get("grid_id").toString().replace("\"","");
//								String fuso=ob.get("fuso").toString().replace("\"","");
//								
//								String coloreQuadrante = "";
//								
//								if(objSel.get("descUtente")!=null){
//									//System.out.println("11111111111111111"+objSel.get("descUtente"));
//									coloreQuadrante = hashColoriUtenti.get(objSel.get("descUtente").toString().replace("\"",""));
//								}
//								else{
//									//System.out.println("11111111111111111");
//									coloreQuadrante = "#FFFFFF";
//								}
//								
//								OLMap_new.addWKTQuadranti("Quadranti",wkt,grid_id,fuso,coloreQuadrante,i,selGrid);
//
//							}
//							else
//								SC.say("GEOMETRIA DEI QUADRANTI ASSENTE");
//						}
//						
//						LeftLayout.getQuadri_legendPanel().setContents(creaTabellaLegendaQuadri());
//
//					}
//					//			//System.out.println("111111111111111111111");
//					OLMap_new.refreshQuadranti();
//					winLoad.hide();
//					MapToolStrip.getZoomBOX_bt().setDisabled(false);
//					OLMap_new.attivaSelezione_Quadranti();
//
//				}
//
//				@Override
//				public void onFailure(Throwable arg0) {
//					winLoad.hide();
//
//				}
//			});
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static String getIdQuadranti() {
		return idQuadranti;
	}


	public static void setIdQuadranti(String idQuadranti) {
		MapEvents.idQuadranti = idQuadranti;
	}


	public static String getAppo() {
		return appo;
	}


	public static void setAppo(String appo) {
		MapEvents.appo = appo;
	}


	public static int getQuadriSel() {
		return quadriSel;
	}


	public static void setQuadriSel(int quadriSel) {
		MapEvents.quadriSel = quadriSel;
	}
}
