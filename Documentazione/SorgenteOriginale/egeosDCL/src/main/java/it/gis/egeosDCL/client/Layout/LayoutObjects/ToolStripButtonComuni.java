package it.gis.egeosDCL.client.Layout.LayoutObjects;

import it.gis.egeosDCL.client.egeosDCL;
import it.gis.egeosDCL.client.Layout.LeftLayout;
import it.gis.egeosDCL.client.map.OLMap_new;

import java.util.LinkedHashMap;
import java.util.Set;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.smartgwt.client.rpc.RPCCallback;
import com.smartgwt.client.rpc.RPCManager;
import com.smartgwt.client.rpc.RPCRequest;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * PULSANTIERA DELLA GRIGLIA DEI COMUNI
 * @version 1.0
 *
 */
public class ToolStripButtonComuni extends ToolStrip{

	DynamicForm 		form 							= null;
	private static SelectItem    		selctOperatoreComuni 	= null;
	
	public static TextItem					provinciaSel					= null;
	//public static TextItem					provinciaSel					= null;
	LinkedHashMap<String,String>  mapSociDaAssegnare = null;
	WinLoading 				winLoad = new WinLoading("Salvataggio in corso...");
	ToolStripButton  				btAssegna 				= null;
	ToolStripButton  				btValida 				= null;
	ToolStripButton  				btDisassocia			= null;
	String 							valoreUtente;
	public ToolStripButtonComuni(int livello)
	{
		super();
		mapSociDaAssegnare = new LinkedHashMap<String, String>();
		this.setID("ID_TOOLSTRIP_COMUNI");
		
		//System.out.println("dentro ToolStripButtonCOMUNI");
		form = new DynamicForm();
		selctOperatoreComuni = new SelectItem("a");
		provinciaSel = new TextItem("&nbsp;");
		provinciaSel.setDisabled(true);
		provinciaSel.setWidth("80");
		
		btAssegna = new ToolStripButton();
		btAssegna.setWidth(25);
		btAssegna.setTooltip("Assegna la provincia selezionata");
		btAssegna.setIcon("salva.jpg");
		RPCRequest request = new RPCRequest();
		
		request.setData("Some data to send to the client");
		request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=socioProvincialeService&descUtente="+egeosDCL.getDescUtente());
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
						for(int i = 0;i<arr.size();i++)
						{
							JSONObject ob = arr.get(i).isObject();
							if(ob!=null)
							{

								mapSociDaAssegnare.put(ob.get("id_utente").toString(), ob.get("desc_utente").toString().replace("\"",""));
							}
						}
						selctOperatoreComuni.setValueMap(mapSociDaAssegnare);
					}
				}
			}
		});
		egeosDCL.setMapSociDaAssegnare(mapSociDaAssegnare);
		selctOperatoreComuni.setDefaultValue("Selezionare un operatore");
		form.setItems(provinciaSel,selctOperatoreComuni);
		
		
		btAssegna  = new ToolStripButton();
		btAssegna.setWidth(25);
		btAssegna.setTooltip("Assegna la provincia selezionata");
		btAssegna.setIcon("associa.gif");
		btAssegna.addClickHandler(new ClickHandler() {
			
			
			public void onClick(ClickEvent event) {
				
			/*	//System.out.println("REGIONE SEL display: "+provinciaSel.getDisplayValue());
				//System.out.println("REGIONE SEL ATTRIBUTE ID: "+provinciaSel.getAttributeAsString("idProv"));
				//System.out.println("SOCIO SEL getDisplayValue: "+selctOperatoreProvincia.getDisplayValue());
				//System.out.println("SOCIO SEL getValueAsString: "+selctOperatoreProvincia.getValueAsString());
				//System.out.println("NUMERO ELENCO: "+egeosDCL.getNumElenco());
			*/	
				//System.out.println("ATTRIBUTO FIGLIO::: "+provinciaSel.getAttributeAsString("figlio"));
				if(provinciaSel.getAttributeAsString("figlio")==null || provinciaSel.getAttributeAsString("figlio").equals(""))
				{
				if((provinciaSel.getDisplayValue()!=null && !provinciaSel.getDisplayValue().equals(""))&&
						(selctOperatoreComuni.getValueAsString()!=null && !selctOperatoreComuni.getValueAsString().equals("")&&!selctOperatoreComuni.getValueAsString().equals("Selezionare un operatore"))	){
					
					winLoad.show();
					RPCRequest request = new RPCRequest();
					// Note data could be a String, Map or Record
					request.setData("Some data to send to the client");
					request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=assegnaUtenteProvinciaRefresh&idPadre="+egeosDCL.getIdUtente()+"&idProvincia="+provinciaSel.getAttributeAsString("idProvincia")+"&numElenco="+egeosDCL.getNumElenco()+"&idUtente="+selctOperatoreComuni.getValueAsString().replace("\"",""));
			//		//System.out.println("CHIAMO SERVIZIO UTENTI ASSIOCIATI");
					RPCManager.sendRequest(request, new RPCCallback() {
						
						
						public void execute(RPCResponse response, Object rawData, RPCRequest request) {
							//System.out.println("rawData:::: "+rawData);
							SC.say(egeosDCL.fireMessage(rawData));
							OLMap_new.destroyMap();
							/*RICARICO LA GRIGLIA DELLE PROVINCE*/
							LeftLayout.getvLayoutGridRegioni().getMember("PROVINCE_GRID_ID").destroy();
							ListGridProvinceJSON listProvJSON = new ListGridProvinceJSON("0");
							LeftLayout.getvLayoutGridRegioni().addMember(listProvJSON);
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
								request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=assegnaUtenteProvinciaRefresh&idRegione="+ListGridContestiJSON.getComboRegioni().getValueAsString()+"&idPadre="+egeosDCL.getIdUtente()+"&idProvincia="+provinciaSel.getAttributeAsString("idProv")+"&numElenco="+egeosDCL.getNumElenco()+"&idUtente="+selctOperatoreComuni.getValueAsString().replace("\"","")+"&op=0");
								RPCManager.sendRequest(request, new RPCCallback() {

									
									public void execute(RPCResponse response, Object rawData, RPCRequest request) {
							
										LeftLayout.getvLayoutGridRegioni().getMember("REGIONI_GRID_ID").redraw();
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
		
		btValida = new ToolStripButton();
		btValida.setWidth(25);
		btValida.setPrompt("Valida l&apos;associazione");
		btValida.setIcon("valida.png");
		btValida.addClickHandler(new ClickHandler() {
			
			
			public void onClick(ClickEvent event) {
				winLoad.show();
			//	//System.out.println("ID UTENTE A CUI ASSEGNO: "+selctOperatoreProvincia.getValueAsString());
				// TODO Auto-generated method stub
				RPCRequest request = new RPCRequest();
				// Note data could be a String, Map or Record
				request.setData("Some data to send to the client");
				request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=validaUtenteProvinciaService&idProvincia="+provinciaSel.getAttributeAsString("idProv")+"&idUtente="+egeosDCL.getIdUtente()+"&numElenco="+egeosDCL.getNumElenco());
				//		//System.out.println("CHIAMO SERVIZIO UTENTI ASSIOCIATI");
				RPCManager.sendRequest(request, new RPCCallback() {

					
					public void execute(RPCResponse response, Object rawData, RPCRequest request) {
						winLoad.show();
						// TODO Auto-generated method stub
						//System.out.println("rawData:::: "+rawData);
						SC.say(egeosDCL.fireMessage(rawData));
						winLoad.hide();
					}
				});
			}
		});
		
		
		btDisassocia = new ToolStripButton();
		btDisassocia.setWidth(25);
		btDisassocia.setPrompt("Disassocia la provincia");
		btDisassocia.setIcon("disassocia.jpg");
		btDisassocia.addClickHandler(new ClickHandler() {
			
			
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				String appo = ToolStripButtonComuni.getSelctOperatoreComuni().getAttributeAsString("figlio");
				
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
					valoreUtente = selctOperatoreComuni.getValueAsString().replace("\"","");
				}
				
					//System.out.println("VALORE UTENTE FINALE: "+valoreUtente);
				
				RPCRequest request = new RPCRequest();
				// Note data could be a String, Map or Record
				request.setData("Some data to send to the client");
				request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=assegnaUtenteProvinciaRefresh&idRegione="+ListGridContestiJSON.getComboRegioni().getValueAsString()+"&idPadre="+egeosDCL.getIdUtente()+"&idProvincia="+provinciaSel.getAttributeAsString("idProv")+"&numElenco="+egeosDCL.getNumElenco()+"&idUtente="+valoreUtente.replace("\"","")+"&op=1");
				//		//System.out.println("CHIAMO SERVIZIO UTENTI ASSIOCIATI");
				RPCManager.sendRequest(request, new RPCCallback() {

					
					public void execute(RPCResponse response, Object rawData, RPCRequest request) {
						winLoad.show();
						// TODO Auto-generated method stub
						//System.out.println("rawData:::: "+rawData);
						SC.say(egeosDCL.fireMessage(rawData));
						refreshListaProvince("DISASSOCIA");
						winLoad.hide();
					}
				});
			}
		});
		
		this.addFormItem(provinciaSel);
		this.addFormItem(selctOperatoreComuni);
		this.addButton(btAssegna);
		this.addButton(btDisassocia);
		this.addButton(btValida);
		this.setWidth100();
		this.show();
	}
	
	/**
	 * AGGIORNA LA LISTA DELLE PROVINCE
	 * @param tipo
	 */
	public void refreshListaProvince(String tipo)
	{
		try{
		ListGridRecord selectedRecord = ListGridContestiJSON.getListProvJSON().getSelectedRecord();  
        if(selectedRecord != null) {  
            ListGridRecord newRecord = new ListGridRecord();  
            newRecord.setAttribute("idProv", selectedRecord.getAttribute("idProv"));  
           
            if(tipo.equals("ASSOCIA")){
            	newRecord.setAttribute("assegnate", new Integer(selectedRecord.getAttribute("assegnate"))+1);
            	newRecord.setAttribute("figlio",selectedRecord.getAttribute("assegnate"));
            }					 
            else if(tipo.equals("DISASSOCIA")){
            	newRecord.setAttribute("assegnate",new Integer(selectedRecord.getAttribute("assegnate"))-1);
            	newRecord.setAttribute("figlio","");
            }
            
            ListGridContestiJSON.getListProvJSON().updateData(newRecord);  
        } 
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public static TextItem getProvinciaSel() {
		return provinciaSel;
	}


	public static void setProvinciaSel(TextItem provinciaSel) {
		ToolStripButtonComuni.provinciaSel = provinciaSel;
	}

	public static SelectItem getSelctOperatoreComuni() {
		return selctOperatoreComuni;
	}

	public static void setSelctOperatoreComuni(SelectItem selctOperatoreComuni) {
		ToolStripButtonComuni.selctOperatoreComuni = selctOperatoreComuni;
	}





	

}
