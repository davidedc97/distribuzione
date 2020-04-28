package it.gis.egeosDCL.client.Layout.LayoutObjects;

import java.util.LinkedHashMap;

import it.gis.egeosDCL.client.egeosDCL;
import it.gis.egeosDCL.client.Layout.MapEvents;
import it.gis.egeosDCL.client.map.OLMap_new;
import it.gis.egeosDCL.server.DAO.ParticelleGeomDAO;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import com.smartgwt.client.rpc.RPCCallback;
import com.smartgwt.client.rpc.RPCManager;
import com.smartgwt.client.rpc.RPCRequest;
import com.smartgwt.client.rpc.RPCResponse;

import com.allen_sauer.gwt.log.client.Log;

/**
 * CREA LA PULSANTIERA DELLA GRIGLIA DI DETTAGLIO DEL COMUNE
 * @version 1.0
 * 
 */
public class ToolStripButtonDettaglioComune extends ToolStrip{


	public static String defaultOrganismoSelectValue = "Selezionare un'organismo";
	
	public 	static  Label 							nomeComune 			= null;
	public 	static  Label 							quadriAss   	 	= null;
	public 	static  Label 							quadriNOAss   	 	= null;
	public	static  Label 							tot		   	 		= null;
	private static  Label 							quadriSel		   	= null;
	public 			String							idFotoALL = "";
	private		static  ToolStripButton					btAssegnaTutti			= null;
	private		static  ToolStripButton					btAssegnaComuneALLFOTO	= null;
	private		static  ToolStripButton					btDisassocia			= null;
	private		static  ToolStripButton					btAssegnaSelezionati	= null;
	private		static  ToolStripButton					btVisualizzaQuadranti	= null;
			static  WinLoading 						winLoad 			= new WinLoading("Elaborazione in corso..."); 
	private	static 	String							nome_comune			= null;
	private static 	String							quadri_ass  		= null;
	private static 	String							quadri_tot			= null;
	private static 	String							quadri_no_ass		= null;
					String							id_comune			= null;
					String 							quadranti 			= "";
    private static  SelectItem    					selelectFotointerprete= null;
    private static  SelectItem    					selelectGruppoFotointerprete= null;
    
    				LinkedHashMap<String,LinkedHashMap<String, String>> mapGruppoFotoInterpreti = null;
    				LinkedHashMap<String,String>  	mapFotointerpreteDaAssegnare 	= null;
    				LinkedHashMap<String,String>  	mapOrganismiFotoInterprete 	= null;
    				DynamicForm 					form 				= null;
   public   static  HiddenItem						stringQuadrantiSel	= null;
   private  static  String 							idComu				= "";
    				
	public ToolStripButtonDettaglioComune(String id_Comune,String nome_Comune,String tot_Assegnate,String tot_NO_Assegnate,final String quadri_tot,String quadri_Lav)
	{
		super();
		this.setID("ID_DETT_TOOLSTRIP_COMUNI");
		idComu = id_Comune;
		this.nome_comune 	= nome_Comune;
		this.quadri_ass		= tot_Assegnate;
		this.quadri_tot		= quadri_tot;
		this.quadri_no_ass	= tot_NO_Assegnate;
		
		Log.debug("ToolStripButtonDettaglioComune");
		
		form = new DynamicForm();
		
		stringQuadrantiSel = new HiddenItem();
		
		quadriSel = new Label();
		quadriSel.setWidth("24%");

		mapGruppoFotoInterpreti = new LinkedHashMap<String, LinkedHashMap<String,String>>();
		mapFotointerpreteDaAssegnare = new LinkedHashMap<String, String>();
		mapOrganismiFotoInterprete = new LinkedHashMap<String, String>();

		nomeComune = new Label();
		nomeComune.setWidth("30%");

		quadriAss = new Label();
		quadriAss.setWidth("12%");

		tot			= new Label();
		tot.setWidth("12%");

		quadriNOAss = new Label();
		quadriNOAss.setWidth("12%");

		nomeComune.setContents("Comune:<b> "+nome_Comune+"</b>");
		quadriAss.setContents("Ass:<b> "+tot_Assegnate+"</b>");
		quadriNOAss.setContents("No ass:<b> "+tot_NO_Assegnate+"</b>");
		tot.setContents("Totale quadri:<b> "+quadri_tot+"</b>");

		selelectGruppoFotointerprete = new SelectItem("organismo");
		selelectFotointerprete = new SelectItem("operatore");
		RPCRequest request = new RPCRequest();
		
		btVisualizzaQuadranti = new ToolStripButton();
		btVisualizzaQuadranti.setIcon("griglia-quadrata.jpg");
		btVisualizzaQuadranti.setTooltip("Carica i quadranti");
		btVisualizzaQuadranti.addClickHandler(new ClickHandler() {
			
			
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				egeosDCL.setHo_clikkato_quadranti(true);
				ListGridComuniJSON.caricaQuadrantiSenzaGriglia(idComu,egeosDCL.getNumElenco());
			}
		});
		
		Log.debug("DENTRO TOOLSTRIP");
		
		request.setData("Some data to send to the client");
		request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=fotoInterpreteService&descUtente="+egeosDCL.getDescUtente());
		RPCManager.sendRequest(request, new RPCCallback() {
			
			
			public void execute(RPCResponse response, Object rawData, RPCRequest request) {
				
				Log.debug("EXECUTE SERVIZIO");
				JSONValue value = JSONParser.parseLenient(rawData+"");
				
				Log.debug("json value "+value);
				
				if(value.isObject()!=null)
				{
					JSONObject obj = value.isObject();
					JSONValue app = obj.get("TodoLista");
					JSONArray arr = app.isArray();
			
					Log.debug("scorro array");
					if(arr!=null)
					{
							Log.debug("array size "+arr.size());
							for(int i = 0;i<arr.size();i++)
							{
								JSONObject ob = arr.get(i).isObject();
								String currOrg = ob.get("desc_azie").toString();
								String currIdUtente = ob.get("id_utente").toString();
								String currDescUtente = ob.get("desc_utente").toString().replace("\"","");
								
								Log.debug("current azie "+currOrg);
								LinkedHashMap<String, String> currentFotoInterpretiMap;
								
								if(mapGruppoFotoInterpreti.containsKey(currOrg)){
									currentFotoInterpretiMap = mapGruppoFotoInterpreti.get(currOrg);
								}else{
									currentFotoInterpretiMap = new LinkedHashMap<String, String>();
								}
								
								mapOrganismiFotoInterprete.put(currOrg, currOrg);
								
								currentFotoInterpretiMap.put(currIdUtente, currDescUtente);
								mapGruppoFotoInterpreti.put(currOrg, currentFotoInterpretiMap);
								
								//mapFotointerpreteDaAssegnare.put(ob.get("id_utente").toString(), ob.get("desc_utente").toString().replace("\"",""));
								idFotoALL+=ob.get("id_utente").toString().replace("\"","")+",";
							}
							Log.debug("size mappa azie "+mapOrganismiFotoInterprete.size());
							
							selelectGruppoFotointerprete.setValueMap(mapOrganismiFotoInterprete);
							//selelectFotointerprete.setValueMap(mapFotointerpreteDaAssegnare);
					}
				}
			}
		});
		
		selelectGruppoFotointerprete.setDefaultValue(defaultOrganismoSelectValue);
		
		selelectGruppoFotointerprete.addChangedHandler(new ChangedHandler() {
			
			public void onChanged(ChangedEvent event) {
				// TODO Auto-generated method stub
				String valueSelected = event.getValue().toString();
				if (valueSelected != defaultOrganismoSelectValue){
					mapFotointerpreteDaAssegnare = mapGruppoFotoInterpreti.get(event.getValue());
					selelectFotointerprete.setValueMap(mapFotointerpreteDaAssegnare);
					egeosDCL.setMapSociDaAssegnare(mapFotointerpreteDaAssegnare);
				}else{
					LinkedHashMap<String,String> dummyMap = new LinkedHashMap<String, String>();
					selelectFotointerprete.setValueMap(dummyMap);
					selelectFotointerprete.setDefaultValue("Selezionare un operatore");
				}
			}
		});
		
		//egeosDCL.setMapSociDaAssegnare(mapFotointerpreteDaAssegnare);
		selelectFotointerprete.setDefaultValue("Selezionare un operatore");
		
		btAssegnaComuneALLFOTO = new ToolStripButton();
		btAssegnaComuneALLFOTO.setTooltip("Assegna il comune a tutti i fotointerpreti associati");
		btAssegnaComuneALLFOTO.setIcon("gruppo.jpg");
	
		
		btAssegnaComuneALLFOTO.addClickHandler(new ClickHandler() {
			
			
			public void onClick(ClickEvent event) {
				winLoad.show();
				// TODO Auto-generated method stub
				RPCRequest request = new RPCRequest();
				
				request.setData("Some data to send to the client");
				
				idFotoALL=idFotoALL.substring(0,idFotoALL.length()-1);
				
			//	System.out.println("ID FOTO ALL: "+idFotoALL);
				
				request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=assegnaComuneTuttiUtenti&idPadre="+egeosDCL.getIdUtente()+"&idComune="+id_comune+"&idUtente="+idFotoALL+"&numElenco="+egeosDCL.getNumElenco());
				RPCManager.sendRequest(request, new RPCCallback() {
					
					
					public void execute(RPCResponse response, Object rawData, RPCRequest request) {
						
						JSONValue value = JSONParser.parseLenient(rawData+"");
						
						if(value.isObject()!=null)
						{
							JSONObject obj = value.isObject();
							JSONValue app = obj.get("TodoLista");
							JSONArray arr = app.isArray();
						//	System.out.println("");
							if(arr!=null)
							{
								winLoad.hide();
								SC.say(egeosDCL.fireMessage(rawData));
								aggiornaListaComuni("TUTTI");
							}
							else{
								winLoad.hide();
							}
								
						}
					}
				});
			}
		});
		
		btDisassocia = new ToolStripButton();
		btDisassocia.setWidth(25);
		btDisassocia.setPrompt("Disassocia i quadranti assegnati");
		btDisassocia.setIcon("disassocia.jpg");
		
		btAssegnaSelezionati = new ToolStripButton();
		btAssegnaSelezionati.setWidth(25);
		btAssegnaSelezionati.setPrompt("Assegna i quadranti selezionati");
		btAssegnaSelezionati.setIcon("salva.jpg");

		btAssegnaTutti  = new ToolStripButton();
		btAssegnaTutti.setWidth(25);
		
		if(new Integer(quadri_ass)==0)
			btAssegnaTutti.setPrompt("Assegna tutti i quadranti per il comune selezionato");
		else if(new Integer(quadri_ass)>0 &&(new Integer(quadri_ass)< new Integer(quadri_tot)))
			btAssegnaTutti.setPrompt("Assegna tutti i restanti quadranti per il comune selezionato");	
		
		btAssegnaTutti.setIcon("salvatutto.jpg");

		this.addMember(nomeComune);
		this.addMember(quadriAss);
		this.addMember(quadriNOAss);
		this.addMember(tot);
		this.addMember(quadriSel);
		this.addSeparator();
		this.addFormItem(stringQuadrantiSel);
		this.addFormItem(selelectGruppoFotointerprete);
		this.addFormItem(selelectFotointerprete);
		this.addMember(btDisassocia);
		this.addMember(btAssegnaTutti);
		this.addMember(btAssegnaComuneALLFOTO);
		this.addMember(btVisualizzaQuadranti);

		this.setWidth100();
		id_comune = 	id_Comune;
		
		btDisassocia.addClickHandler(new ClickHandler() {
			
			
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				winLoad.show();
				RPCRequest request = new RPCRequest();

				request.setData("Some data to send to the client");
				request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=disassociaComuneUtente&idComune="+id_comune+"&numElenco="+egeosDCL.getNumElenco());

				RPCManager.sendRequest(request, new RPCCallback() {

					
					public void execute(RPCResponse response, Object rawData, RPCRequest request) {
						//System.out.println("RAWDATA: "+rawData);

						SC.say(egeosDCL.fireMessage(rawData));
						//MODIFICO I DATI NELLA GRIGLIA DEI COMUNI
						winLoad.hide();
						aggiornaListaComuni("TUTTI");
						
					}
				});
			}
		});
		
		btAssegnaTutti.addClickHandler(new ClickHandler() {

			
			public void onClick(ClickEvent event) {

				winLoad.show();

				if((ListGridContestiJSON.getComboProvince().getValueAsString()!=null && !ListGridContestiJSON.getComboProvince().getValueAsString().equals(""))&&
						selelectFotointerprete.getValueAsString()!=null && !selelectFotointerprete.getValueAsString().equals("Selezionare un operatore")	)
				{
					RPCRequest request = new RPCRequest();

					request.setData("Some data to send to the client");
					
					if(new Integer(quadri_ass)>0)
						request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=assegnaUtentiQuadrantiAll&idPadre="+egeosDCL.getIdUtente()+"&idComune="+id_comune+"&idUtente="+selelectFotointerprete.getValueAsString().replace("\"", "")+"&numElenco="+egeosDCL.getNumElenco()+"&flagOper=1");
					else
						request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=assegnaUtentiQuadrantiAll&idPadre="+egeosDCL.getIdUtente()+"&idComune="+id_comune+"&idUtente="+selelectFotointerprete.getValueAsString().replace("\"", "")+"&numElenco="+egeosDCL.getNumElenco());
					
					
					RPCManager.sendRequest(request, new RPCCallback() {

						
						public void execute(RPCResponse response, Object rawData, RPCRequest request) {
							//System.out.println("RAWDATA: "+rawData);

							SC.say(egeosDCL.fireMessage(rawData));
							//MODIFICO I DATI NELLA GRIGLIA DEI COMUNI
							aggiornaListaComuni("TUTTI");
							winLoad.hide();
						}
					});

				}
				else{
					SC.say("Selezionare i quadranti ed il fotointerprete");
					winLoad.hide();
				}

			}
		});
		
		
		
		btAssegnaSelezionati.addClickHandler(new ClickHandler() {
			
			
			public void onClick(ClickEvent event) {
				
				//System.out.println("MapEvents.getIdQuadranti()::::::: "+MapEvents.getIdQuadranti());
				
				if((selelectFotointerprete.getValueAsString()!=null && 
								!selelectFotointerprete.getValueAsString().equals("")&&
								!selelectFotointerprete.getValueAsString().equals("Selezionare un operatore"))&&
								!MapEvents.getIdQuadranti().equals("")
								&&(MapEvents.getIdQuadranti()!=null && !MapEvents.getIdQuadranti().equals("0"))){
					winLoad.show();
					RPCRequest request = new RPCRequest();
					// Note data could be a String, Map or Record
					request.setData("Some data to send to the client");
					
					quadranti = MapEvents.getIdQuadranti();

					//System.out.println("QUADRANTI: "+quadranti.substring(1,quadranti.length()));
					
					request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=assegnaUtentiQuadrantiAll&idComune="+id_comune+"&idQuadranti="+quadranti.substring(1,quadranti.length())+"&numElenco="+egeosDCL.getNumElenco()+"&idUtente="+selelectFotointerprete.getValueAsString().replace("\"","")+"&idPadre="+egeosDCL.getIdUtente()+"&codUtente="+selelectFotointerprete.getDisplayValue().toString());
					
					RPCManager.sendRequest(request, new RPCCallback() {
						
						
						public void execute(RPCResponse response, Object rawData, RPCRequest request) {
							// TODO Auto-generated method stub
							//System.out.println("RAWDATA: "+rawData);
							
							aggiornaListaComuni("SELEZIONATI");
							winLoad.hide();
							SC.say(egeosDCL.fireMessage(rawData));
							
						}
					});
				}
				else
					SC.say("Selezionare il quadrante e l&apos;operatore da assegnare");
			}
		});
		
		//System.out.println("ASSEGNATE:"+new Integer(tot_Assegnate));
	//	//System.out.println("NO ASSEGNATE:"+new Integer(tot_Assegnate));
		//System.out.println("TOTALI:"+new Integer(quadri_tot));
		
		if(new Integer(tot_Assegnate)==0){
			btDisassocia.setDisabled(true);
		}
		if(new Integer(tot_Assegnate)>0)
		{
			//btAssegnaTutti.setDisabled(true);
		}
		if(tot_Assegnate.trim().equals(quadri_tot.trim()))
		{
			//btAssegnaTutti.setDisabled(true);
			btAssegnaSelezionati.setDisabled(true);
		}
		
	}

	public static void aggiornaListaComuni(String tipo)
	{
		ListGridRecord selectedRecord = ListGridContestiJSON.getListComuJSON().getSelectedRecord();
		ListGridRecord newRecord = new ListGridRecord();  
		
		if(!tipo.equals("TUTTI")){
			
			if(selectedRecord != null) {  
				OLMap_new.destroyMap();
				newRecord.setAttribute("assegnate", new Integer(MapEvents.getQuadriSel()));
				newRecord.setAttribute("noassegnate",new Integer( selectedRecord.getAttribute("assegnate"))-MapEvents.getQuadriSel() );
				
			} 
		}
		else{
			OLMap_new.destroyMap();
				newRecord.setAttribute("assegnate", new Integer(selectedRecord.getAttribute("tot")));
				newRecord.setAttribute("noassegnate",0);
				
		}
		if(egeosDCL.getDett_layout().getMember("DettaglioComuniListGrid_ID")!=null){

			egeosDCL.getDett_layout().getMember("ID_DETT_TOOLSTRIP_COMUNI").destroy();
			egeosDCL.getDett_layout().getMember("DettaglioComuniListGrid_ID").destroy();
			egeosDCL.getDett_layout().hide();
		}
		ListGridContestiJSON.getListComuJSON().updateData(newRecord);  
		
		
	}
	
	


	public static Label getQuadriSel() {
		return quadriSel;
	}

	public static void setQuadriSel(Label quadriSel) {
		ToolStripButtonDettaglioComune.quadriSel = quadriSel;
	}

	public static String getNome_comune() {
		return nome_comune;
	}

	public static void setNome_comune(String nome_comune) {
		ToolStripButtonDettaglioComune.nome_comune = nome_comune;
	}

	public static String getQuadri_ass() {
		return quadri_ass;
	}

	public static void setQuadri_ass(String quadri_ass) {
		ToolStripButtonDettaglioComune.quadri_ass = quadri_ass;
	}

	public static String getQuadri_tot() {
		return quadri_tot;
	}

	public static void setQuadri_tot(String quadri_tot) {
		ToolStripButtonDettaglioComune.quadri_tot = quadri_tot;
	}

	public static String getQuadri_no_ass() {
		return quadri_no_ass;
	}

	public static void setQuadri_no_ass(String quadri_no_ass) {
		ToolStripButtonDettaglioComune.quadri_no_ass = quadri_no_ass;
	}

	public static HiddenItem getStringQuadrantiSel() {
		return stringQuadrantiSel;
	}

	public static void setStringQuadrantiSel(HiddenItem stringQuadrantiSel) {
		ToolStripButtonDettaglioComune.stringQuadrantiSel = stringQuadrantiSel;
	}

	public static ToolStripButton getBtAssegnaTutti() {
		return btAssegnaTutti;
	}

	public static void setBtAssegnaTutti(ToolStripButton btAssegnaTutti) {
		ToolStripButtonDettaglioComune.btAssegnaTutti = btAssegnaTutti;
	}

	public static ToolStripButton getBtDisassocia() {
		return btDisassocia;
	}

	public static void setBtDisassocia(ToolStripButton btDisassocia) {
		ToolStripButtonDettaglioComune.btDisassocia = btDisassocia;
	}

	public static ToolStripButton getBtAssegnaSelezionati() {
		return btAssegnaSelezionati;
	}

	public static void setBtAssegnaSelezionati(ToolStripButton btAssegnaSelezionati) {
		ToolStripButtonDettaglioComune.btAssegnaSelezionati = btAssegnaSelezionati;
	}

}
