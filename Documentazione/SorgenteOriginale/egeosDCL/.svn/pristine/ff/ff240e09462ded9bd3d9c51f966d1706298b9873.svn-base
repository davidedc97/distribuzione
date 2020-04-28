package it.gis.egeosDCL.client.Layout.LayoutObjects;

import it.gis.egeosDCL.client.egeosDCL;

import it.gis.egeosDCL.client.Layout.LeftLayout;
import it.gis.egeosDCL.client.map.OLMap_new;

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
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;
import com.smartgwt.client.widgets.Window;

/**
 * CREA LA PULSANTIERA DELLA GRIGLIA DI RIEPILOGO DEGLI UTENTI
 * @version 1.0
 * 
 */
public class ToolStripButtonRiepUsers extends ToolStrip{

	
	static ToolStripButton  				btAssegna 						= null;
	static ToolStripButton  				btAssegna_all					= null;
	
	WinLoading 				winLoad = new WinLoading("Salvataggio in corso...");
	public ToolStripButtonRiepUsers(final Window winVal)
	{
		super();
	
		this.setID("ID_ToolStripButtonRiepUsers");
		
		btAssegna_all = new ToolStripButton("valida tutto");
		btAssegna_all.setWidth(25);
		btAssegna_all.setTooltip("Valida tutto");
		btAssegna_all.setBorder("1px solid");
		btAssegna_all.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				validaUtenteProvincia(ListGridContestiJSON.getComboProvince().getValueAsString());
				winVal.destroy();
			}
		});
		
		btAssegna  = new ToolStripButton("valida selezione");
		btAssegna.setWidth(25);
		btAssegna.setBorder("1px solid");
		btAssegna.setTooltip("Valida selezione");
		btAssegna.addClickHandler(new ClickHandler() {
			
			
			public void onClick(ClickEvent event) {
				if(ValidateWindow.getListGridUsersProv().getSelectedRecords().length>0){
				validaUtenteProvinciaSel(ValidateWindow.getListGridUsersProv().getSelectedRecords());
				winVal.destroy();
				}
				else
					SC.say("Nessun record selezionato");
			}
		});
		
		this.addButton(btAssegna);
		this.addSeparator();
		this.addButton(btAssegna_all);
		
		this.setWidth100();
		this.show();
	}

	/**
	 * VALIA LA PROVINCIA SELEZIONATA 
	 * @param recordArray
	 */
	public void validaUtenteProvinciaSel(ListGridRecord[] recordArray)
	{
		RPCRequest request = new RPCRequest();
		winLoad.show();
		// Note data could be a String, Map or Record
		request.setData("Some data to send to the client");
		String stringone_idComune = "";
		String stringone_idUtente = "";
		for(int i = 0;i< recordArray.length;i++){
			ListGridRecord rec = new ListGridRecord();

			rec = recordArray[i];
			stringone_idComune = stringone_idComune+","+rec.getAttributeAsString("id_comune");
			stringone_idUtente = stringone_idUtente+","+rec.getAttributeAsString("id_utente");
			
		}
		stringone_idUtente = stringone_idUtente.substring(1,stringone_idUtente.length());
		stringone_idComune = stringone_idComune.substring(1,stringone_idComune.length());
		//System.out.println("DENTRO validaProvinciaSel stringone_idUtente "+stringone_idUtente);
		//System.out.println("DENTRO validaProvinciaSel stringone_idComune "+stringone_idComune);
		request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=validaQuadriAsseRefresh&idProvincia="+ListGridContestiJSON.getIdProvinciaRiepilogo()+"&idComune="+stringone_idComune+"&idUtente="+stringone_idUtente+"&numElenco="+egeosDCL.getNumElenco());
		//System.out.println("URL : "+GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=validaQuadriAsseRefresh&idProvincia="+ListGridContestiJSON.getIdProvinciaRiepilogo()+"&idComune="+stringone_idComune+"&idUtente="+stringone_idUtente+"&numElenco="+egeosDCL.getNumElenco());

		RPCManager.sendRequest(request, new RPCCallback() {

			
			public void execute(RPCResponse response, Object rawData, RPCRequest request) {
				// TODO Auto-generated method stub
				JSONValue value = JSONParser.parseLenient(rawData+"");
				//System.out.println("RAWDATA: "+rawData);
				if(value.isObject()!=null)
				{
					JSONObject obj = value.isObject();
					JSONValue app = obj.get("TodoLista");
					JSONArray arr = app.isArray();

					if(arr!=null)
					{
						OLMap_new.destroyMap();
						if(LeftLayout.getvLayoutGridRegioni().getMember("COMUNI_GRID_ID")!=null){

							LeftLayout.getvLayoutGridRegioni().getMember("COMUNI_GRID_ID").destroy();
							//LeftLayout.getvLayoutGridRegioni().getMember("ID_TOOLSTRIP_QUADRANTI").destroy();

							ListGridComuniJSON gridComuni = new ListGridComuniJSON(ListGridContestiJSON.getComboProvince().getValueAsString());
						//	ToolStripButtonQuadranti stripQuadranti = new ToolStripButtonQuadranti();
							ListGridContestiJSON.setListComuJSON(gridComuni);
					//		LeftLayout.getvLayoutGridRegioni().addMember(stripQuadranti);
							LeftLayout.getvLayoutGridRegioni().addMember(gridComuni);
							ListGridContestiJSON.getBt_valida().setDisabled(false);
						}
					
						winLoad.hide();
						
						SC.say(egeosDCL.fireMessage(rawData));
					}
				}
			}
		});
	}

	/**
	 * VALIDA LA ASSOCIAZIONE UTENTE-PROVINCIA
	 * @param idProvincia
	 */
	public void validaUtenteProvincia(String idProvincia)
	{
		RPCRequest request = new RPCRequest();
		winLoad.show();
		// Note data could be a String, Map or Record
		request.setData("Some data to send to the client");
		String stringone_idUtente = "";
		//System.out.println("VALIDO TUTTA LA PROVINCIA: "+idProvincia);
		
		for(int i = 0;i< ValidateWindow.getListGridUsersProv().getRecordList().getLength();i++){
			ListGridRecord rec = new ListGridRecord();

			rec = ValidateWindow.getListGridRiepProv().getRecord(i);
			if(rec.getAttributeAsString("id_utente")!=null)
				stringone_idUtente = stringone_idUtente+","+rec.getAttributeAsString("id_utente");
			
		}
		stringone_idUtente = stringone_idUtente.substring(1,stringone_idUtente.length());
		request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=validaQuadriAsseRefreshP&idProvincia="+ListGridContestiJSON.getComboProvince().getValueAsString()+"&idUtente="+stringone_idUtente+"&numElenco="+egeosDCL.getNumElenco());
		//System.out.println("stringone_idUtente: "+stringone_idUtente);

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
						OLMap_new.destroyMap();
						if(LeftLayout.getvLayoutGridRegioni().getMember("COMUNI_GRID_ID")!=null){

							LeftLayout.getvLayoutGridRegioni().getMember("COMUNI_GRID_ID").destroy();
							//LeftLayout.getvLayoutGridRegioni().getMember("ID_TOOLSTRIP_QUADRANTI").destroy();

							ListGridComuniJSON gridComuni = new ListGridComuniJSON(ListGridContestiJSON.getComboProvince().getValueAsString());
						//	ToolStripButtonQuadranti stripQuadranti = new ToolStripButtonQuadranti();
							ListGridContestiJSON.setListComuJSON(gridComuni);
					//		LeftLayout.getvLayoutGridRegioni().addMember(stripQuadranti);
							LeftLayout.getvLayoutGridRegioni().addMember(gridComuni);
							ListGridContestiJSON.getBt_valida().setDisabled(false);
						}
						winLoad.hide();
						
						SC.say(egeosDCL.fireMessage(rawData));
					}
				}
			}
		});
	}



	public static ToolStripButton getBtAssegna() {
		return btAssegna;
	}


	public void setBtAssegna(ToolStripButton btAssegna) {
		this.btAssegna = btAssegna;
	}



	public static ToolStripButton getBtAssegna_all() {
		return btAssegna_all;
	}


	public static void setBtAssegna_all(ToolStripButton btAssegna_all) {
		ToolStripButtonRiepUsers.btAssegna_all = btAssegna_all;
	}





	

}
