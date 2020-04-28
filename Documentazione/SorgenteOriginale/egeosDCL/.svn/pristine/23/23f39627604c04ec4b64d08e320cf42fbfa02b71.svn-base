package it.gis.egeosDCL.client.Layout.LayoutObjects;

import it.gis.egeosDCL.client.egeosDCL;
import it.gis.egeosDCL.client.Layout.LeftLayout;
import java.util.LinkedHashMap;
import java.util.Set;

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
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * CREA LA PULSANTIERA DELLA GRIGLIA DELLE PROVINCE
 * @version 1.0
 * 
 */
public class ToolStripButtonProvince extends ToolStrip{

	DynamicForm 					form 					= null;
	public static SelectItem    	selctOperatoreProvincia = null;
	ToolStripButton  				btAssegna 				= null;
	ToolStripButton  				btValida 				= null;
	ToolStripButton  				btDisassocia			= null;
	public static TextItem			provinciaSel			= null;
	LinkedHashMap<String,String>  	mapSociDaAssegnare 		= null;
	WinLoading 						winLoad 				= new WinLoading("Salvataggio in corso...");
	String 							valoreUtente;
	ListGridProvinceJSON  			listProvJSON 			= null;
	
	public ToolStripButtonProvince()
	{
		super();
		mapSociDaAssegnare = new LinkedHashMap<String, String>();
		this.setID("ID_TOOLSTRIP_PROVINCE");

		//System.out.println("dentro ToolStripButtonPROVINCE");
		form = new DynamicForm();
		selctOperatoreProvincia = new SelectItem("a");
		provinciaSel = new TextItem("&nbsp;");
		provinciaSel.setDisabled(true);
		provinciaSel.setWidth("80");

		RPCRequest request = new RPCRequest();

		request.setData("Some data to send to the client");
		
		if(egeosDCL.getLivelloRuolo().equals("1"))
			request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=socioNazionaleService&descUtente="+egeosDCL.getDescUtente());
		else if(egeosDCL.getLivelloRuolo().equals("2"))
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

								mapSociDaAssegnare.put(ob.get("id_utente").toString().replace("\"",""), ob.get("desc_utente").toString().replace("\"",""));
						}
							selctOperatoreProvincia.setValueMap(mapSociDaAssegnare);
							
					}
				}
			}
		});
		ButtonItem exportRiepXLSItem = new ButtonItem("Riepilogo&nbsp;XLS"); 
		exportRiepXLSItem.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
			
			
			public void onClick(
					com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				Window.open(
                		GWT.getModuleBaseURL()+"RiepilogoEFAXLSServlet?numeElenco="+egeosDCL.getNumElenco()+"&prov="+egeosDCL.getProvXLS(),
                        "_blank", "width=600,height=550,scrollbars=yes,resizable=yes");
				
			}
		});
		egeosDCL.setMapSociDaAssegnare(mapSociDaAssegnare);
		selctOperatoreProvincia.setDefaultValue("Selezionare un operatore");
		form.setItems(provinciaSel,selctOperatoreProvincia);

		btAssegna  = new ToolStripButton();

		this.setMargin(5);
		this.setPadding(5);
		this.addFormItem(provinciaSel);
		this.addFormItem(selctOperatoreProvincia);
		this.addFormItem(exportRiepXLSItem);
		this.setWidth100();
		this.show();
	}

	/**
	 * AGGIORNA LA GRIGLIA DELLE PROVINCE
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
	
	
	
	/**
	 * AGGIORNA LA GRIGLIA DELLE REGIONI
	 * @param tipo
	 */
	public void refreshListaRegioni(String tipo)
	{
		try{
		ListGridRecord selectedRecord = ListGridContestiJSON.getListRegJSON().getSelectedRecord();
        if(selectedRecord != null) {  
            ListGridRecord newRecord = new ListGridRecord();  
            newRecord.setAttribute("id_regi", selectedRecord.getAttribute("id_regi"));  
           
            if(tipo.equals("ASSOCIA")){
            	newRecord.setAttribute("assegnate", new Integer(selectedRecord.getAttribute("assegnate"))+1);
            	//newRecord.setAttribute("figlio",selectedRecord.getAttribute("assegnate"));
            }					 
            else if(tipo.equals("DISASSOCIA")){
            	newRecord.setAttribute("assegnate",new Integer(selectedRecord.getAttribute("assegnate"))-1);
            	//newRecord.setAttribute("figlio","");
            }
            
            ListGridContestiJSON.getListRegJSON().updateData(newRecord);  
        } 
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void modificaListGridData(String idRegione)
	{
		
	}
	
	public static TextItem getProvinciaSel() {
		return provinciaSel;
	}

	public static void setProvinciaSel(TextItem provinciaSel) {
		ToolStripButtonProvince.provinciaSel = provinciaSel;
	}

	public static SelectItem getSelctOperatoreProvincia() {
		return selctOperatoreProvincia;
	}

	public static void setSelctOperatoreProvincia(SelectItem selctOperatoreProvincia) {
		ToolStripButtonProvince.selctOperatoreProvincia = selctOperatoreProvincia;
	}



}
