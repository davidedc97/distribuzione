package it.gis.egeosDCL.client.Layout.LayoutObjects;

import java.util.LinkedHashMap;

import it.gis.egeosDCL.client.egeosDCL;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.smartgwt.client.rpc.RPCCallback;
import com.smartgwt.client.rpc.RPCManager;
import com.smartgwt.client.rpc.RPCRequest;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * CREA LA PULSANTIERA DELLA GRIGLIA DELLE REGIONI
 * @version 1.0
 * 
 */
public class ToolStripButtonRegioni extends ToolStrip{

	public static DynamicForm 		form 							= null;
	public static SelectItem    		selctOperatoreRegione 	= null;
	ToolStripButton  	btAssegna 					= null;
	public static TextItem					regioneSel					= null;
	LinkedHashMap<String,String>  mapSociDaAssegnare = null;
	public static HiddenItem idRegiHidden = null;
 	
 	
	public ToolStripButtonRegioni()
	{
		super();
		idRegiHidden = new HiddenItem("hideIdRegi");
		mapSociDaAssegnare = new LinkedHashMap<String, String>();
		
		this.setID("ID_TOOLSTRIP_REGIONI");
		
		//System.out.println("dentro ToolStripButtonRegioni");
		form = new DynamicForm();
		selctOperatoreRegione = new SelectItem("&nbsp;");
		regioneSel = new TextItem("&nbsp;");
		regioneSel.setWidth("80");
		regioneSel.setDisabled(true);
		//System.out.println("DESC UTENTE PROVA GIS: "+egeosDCL.getDescUtente());
		
		String descrizioneUtente = "";
		descrizioneUtente = egeosDCL.getDescUtente().replace("\"", "");
		
		//System.out.println("descrizioneUtente DOPO: "+descrizioneUtente);
		RPCRequest request = new RPCRequest();
		// Note data could be a String, Map or Record
		request.setData("Some data to send to the client");
		request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=socioNazionaleService&descUtente="+egeosDCL.getDescUtente());
		//System.out.println("CHIAMO SERVIZIO UTENTI ASSIOCIATI");
		RPCManager.sendRequest(request, new RPCCallback() {
			
			
			public void execute(RPCResponse response, Object rawData, RPCRequest request) {
				// TODO Auto-generated method stub
				JSONValue value = JSONParser.parseLenient(rawData+"");
				//System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAI");
				if(value.isObject()!=null)
				{
					JSONObject obj = value.isObject();
					JSONValue app = obj.get("TodoLista");
					JSONArray arr = app.isArray();
					//System.out.println("ARRAY UTENTI ASSOCIATI LENGTH: "+arr.size());
					if(arr!=null)
					{
						JSONObject ob = arr.get(0).isObject();
						if(ob!=null)
						{
							for(int i = 0;i<arr.size();i++)
							{
								mapSociDaAssegnare.put(ob.get("id_utente").toString(), ob.get("desc_utente").toString().replace("\"",""));
							}
							selctOperatoreRegione.setValueMap(mapSociDaAssegnare);

						}
					}
				}
			}
		});
		selctOperatoreRegione.setDefaultValue("Selezionare un operatore");
		
		selctOperatoreRegione.setValueMap(mapSociDaAssegnare);
		
		form.setItems(regioneSel,selctOperatoreRegione,idRegiHidden);
		
		btAssegna  = new ToolStripButton("&nbsp;");
		btAssegna.setWidth(25);
		btAssegna.setIcon("salva.jpg");
		
		btAssegna.addClickHandler(new ClickHandler() {
			
			
			public void onClick(ClickEvent event) {
				
				//System.out.println("REGIONE SEL display: "+regioneSel.getDisplayValue());
				//System.out.println("REGIONE SEL ATTRIBUTE ID: "+regioneSel.getAttributeAsString("id_regi"));
				//System.out.println("SOCIO SEL getDisplayValue: "+selctOperatoreRegione.getDisplayValue());
				//System.out.println("SOCIO SEL getValueAsString: "+selctOperatoreRegione.getValueAsString());
				//System.out.println("NUMERO ELENCO: "+egeosDCL.getNumElenco());
				
				RPCRequest request = new RPCRequest();
				
				if((regioneSel.getDisplayValue()!=null && !regioneSel.getDisplayValue().equals(""))&& 
						(selctOperatoreRegione.getValueAsString()!=null && !selctOperatoreRegione.getValueAsString().equals(""))&&
						!selctOperatoreRegione.getValueAsString().equals("Selezionare un operatore")){
			
				request.setData("Some data to send to the client");
				//System.out.println("numero elenco on click::::::::: "+egeosDCL.getNumElenco());
				request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=assegnaUtenteRegioneRefresh&idRegione="+regioneSel.getAttributeAsString("id_regi")+"&numElenco="+egeosDCL.getNumElenco()+"&idUtente="+selctOperatoreRegione.getValueAsString().replace("\"",""));
				//System.out.println("URL DELLA MINCHIA::::::::::::: "+GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=assegnaUtenteRegioneRefresh&idRegione="+regioneSel.getAttributeAsString("id_regi")+"&numElenco="+egeosDCL.getNumElenco()+"&idUtente="+selctOperatoreRegione.getValueAsString().replace("\"",""));
				//System.out.println("CHIAMO SERVIZIO UTENTI ASSIOCIATI");
				RPCManager.sendRequest(request, new RPCCallback() {
					
					
					public void execute(RPCResponse response, Object rawData, RPCRequest request) {
						SC.say(egeosDCL.fireMessage(rawData));
						// TODO Auto-generated method stub
					}
				});
				}
				else
					SC.say("Selezionare una regione ed un operatore");
			}
		});
		
		this.addFormItem(regioneSel);
		this.addFormItem(selctOperatoreRegione);
		this.addButton(btAssegna);
		this.setWidth100();
		this.show();
	}

	public static TextItem getRegioneSel() {
		return regioneSel;
	}

	public static void setRegioneSel(TextItem regioneSel) {
		ToolStripButtonRegioni.regioneSel = regioneSel;
	}

	public static DynamicForm getForm() {
		return form;
	}

	public static void setForm(DynamicForm form) {
		ToolStripButtonRegioni.form = form;
	}

	public static SelectItem getSelctOperatoreRegione() {
		return selctOperatoreRegione;
	}

	public static void setSelctOperatoreRegione(SelectItem selctOperatoreRegione) {
		ToolStripButtonRegioni.selctOperatoreRegione = selctOperatoreRegione;
	}

	public static HiddenItem getIdRegiHidden() {
		return idRegiHidden;
	}

	public static void setIdRegiHidden(HiddenItem idRegiHidden) {
		ToolStripButtonRegioni.idRegiHidden = idRegiHidden;
	}

}
