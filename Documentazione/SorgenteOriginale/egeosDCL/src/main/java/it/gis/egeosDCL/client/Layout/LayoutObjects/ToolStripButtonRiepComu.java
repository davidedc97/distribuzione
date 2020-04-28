package it.gis.egeosDCL.client.Layout.LayoutObjects;

import it.gis.egeosDCL.client.egeosDCL;
import it.gis.egeosDCL.client.Layout.LeftLayout;
import it.gis.egeosDCL.client.map.OLMap_new;
import it.gis.egeosDCL.client.utility.Utility;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

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
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * CREA LA PULSANTIERA DELLA GRIGLIA DI RIEPILOGO DEI COMUNI
 * @version 1.0
 * 
 */
public class ToolStripButtonRiepComu extends ToolStrip{

	DynamicForm 					form 							= null;
	String stringone_idUtente = "";
	public static SelectItem    					selectFotointerprete 			= null;
	static ToolStripButton  				btAssegna 						= null;
	static ToolStripButton  				btAssegna_all					= null;
	public static TextItem			quadranteSel					= null;
	LinkedHashMap<String,String>  	mapFotointerpreteDaAssegnare 	= null;
	public static HiddenItem						comuneSel						= null;
	public static HiddenItem						stringQuadrantiSel				= null;
	WinLoading 				winLoad = new WinLoading("Salvataggio in corso...");
	public static String selezionaTemp = "";

	public static HashMap<String, String> ht_appo_sel = new HashMap<String, String>();



	public ToolStripButtonRiepComu(final ValidateWindow winVal)
	{
		super();

		this.setID("ID_ToolStripButtonRiepComu");

		btAssegna_all = new ToolStripButton("valida tutto");
		btAssegna_all.setWidth(25);
		btAssegna_all.setBorder("1px solid");
		btAssegna_all.setTooltip("Valida tutto");

		btAssegna_all.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				
				//System.out.println("SIZE RIEP COMU: "+ValidateWindow.getListGridRiepProv().getRecords().length);
				if(ValidateWindow.getListGridRiepProv().getRecords().length>0){
					//validaProvinciaSel(ValidateWindow.getListGridRiepProv().getSelectedRecords());
					validaProvincia(RiepilogoComuniProvListGrid.getComuniSel());
					
					RiepilogoComuniProvListGrid.getComuniSel().clear();
				}
				else{
					SC.say("Nessun record selezionato");
				}
				winVal.destroy();
			}
		});

		btAssegna  = new ToolStripButton("valida selezione");
		btAssegna.setWidth(25);
		btAssegna.setBorder("1px solid");
		btAssegna.setTooltip("Valida selezione");
		btAssegna.addClickHandler(new ClickHandler() {

			
			public void onClick(ClickEvent event) {	

				System.out.println("111111111111111"+ToolStripButtonRiepComu.getHt_appo_sel().size());
				if(ToolStripButtonRiepComu.getHt_appo_sel().size()>0){
					System.out.println("2222222222222222 "+ToolStripButtonRiepComu.getHt_appo_sel());
					//	System.out.println("HASHTABLE:::::::::::::::::::: "+RiepilogoComuniProvListGrid.getComuniSel().toString());
					//	System.out.println("getSelectedRecords:::::::::::::::::::: "+ValidateWindow.getListGridRiepProv().getSelectedRecords());
					//					Utility.restituisciStringaIdComuneDaHashTable(RiepilogoComuniProvListGrid.getComuniSel());
					//					Utility.restituisciStringaIdUtenteDaHashTable(RiepilogoComuniProvListGrid.getComuniSel());record.getAttributeAsString("id_comune")
					//ValidateWindow.getListGridRiepProv().getSelectedRecord().getAttributeAsString("id_comune")
					//	System.out.println("SELECTED RECORD: "+ValidateWindow.getListGridRiepProv().getSelectedRecord().getAttributeAsString("id_comune"));
					Iterator it = ToolStripButtonRiepComu.getHt_appo_sel().entrySet().iterator();
					while (it.hasNext()) {
						System.out.println("aaaaaaaaaa");
						Map.Entry pairs = (Map.Entry)it.next();
						String idComune =(String) pairs.getKey();
						String idUtente =  (String) pairs.getValue();

						System.out.println("ID COMUNE "+idComune+"  ID UTENTE:"+idUtente+" has next?: "+it.hasNext());
						
						validaProvinciaOnSelection(winVal,idComune,idUtente,it.hasNext());
					}
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

	

	public void validaProvinciaOnSelection(final ValidateWindow win,String stringone_idComune,String stringone_idUtente,final boolean i)
	{
		RPCRequest request = new RPCRequest();
		winLoad.show();
		// Note data could be a String, Map or Record
		request.setData("Some data to send to the client");
		System.out.println("UUUUUUUUUUUUUUUURL: "+GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=validaQuadriAsseRefresh&idProvincia="+ListGridContestiJSON.getIdProvinciaRiepilogo()+"&idComune="+stringone_idComune+"&idUtente="+stringone_idUtente+"&numElenco="+egeosDCL.getNumElenco());
		System.out.println("STRINGONE ID COMUNE: "+stringone_idComune);
		System.out.println("STRINGONE ID UTENTE: "+stringone_idUtente);
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
						System.out.println("HAS NEXT???????????? "+i);
					if(!i)
					{
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
								ToolStripButtonRiepComu.setSelezionaTemp("");
							}

							//ricarico la lista

						}
						winLoad.hide();
						SC.say(egeosDCL.fireMessage(rawData));
						RiepilogoComuniProvListGrid.getComuniSel().clear();
						win.destroy();
					}
				}
			}
		});
		stringone_idComune = "";
		stringone_idUtente = "";
		//ToolStripButtonRiepComu.getHt_appo_sel().clear();
	}

	/**
	 * VALIDA LA PROVINCIA PER INTERO
	 * @param idProvincia
	 */
	public void validaProvincia(HashMap<String,String> ht)
	{
		RPCRequest request = new RPCRequest();
		winLoad.show();
		request.setData("Some data to send to the client");
		
		final Iterator it = ht.entrySet().iterator();
		
		System.out.println("DENTRO VALIDA TUTTO: size hashmap: "+ht.size());
		
		while (it.hasNext()) {
			//System.out.println("aaaaaaaaaa");
			Map.Entry pairs = (Map.Entry)it.next();
			String idComune =(String) pairs.getKey();
			String idUtente =  (String) pairs.getValue();
			
			System.out.println("DENTRO WHILE: idComune: "+idComune);
			System.out.println("DENTRO WHILE: idUtente: "+idUtente);
			
			System.out.println("UUUUUUUUUURL::: "+GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=validaQuadriAsseRefresh&idProvincia="+ListGridContestiJSON.getIdProvinciaRiepilogo()+"&idComune="+idComune+"&idUtente="+idUtente+"&numElenco="+egeosDCL.getNumElenco());
			
			request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=validaQuadriAsseRefresh&idProvincia="+ListGridContestiJSON.getIdProvinciaRiepilogo()+"&idComune="+idComune+"&idUtente="+idUtente+"&numElenco="+egeosDCL.getNumElenco());
			RPCManager.sendRequest(request, new RPCCallback() {

				
				public void execute(RPCResponse response, Object rawData, RPCRequest request) {
					// TODO Auto-generated method stub
					JSONValue value = JSONParser.parseLenient(rawData+"");
					if(!it.hasNext())
					{
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

								SC.say(egeosDCL.fireMessage(rawData));
								winLoad.hide();
							}
						}
					}
				}

			});
		}//for
		
		RiepilogoComuniProvListGrid.getComuniSel().clear();
		stringone_idUtente = "";
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
		ToolStripButtonRiepComu.btAssegna_all = btAssegna_all;
	}

	public static String getSelezionaTemp() {
		return selezionaTemp;
	}

	public static void setSelezionaTemp(String selezionaTemp) {
		ToolStripButtonRiepComu.selezionaTemp = selezionaTemp;
	}

	public static HashMap<String, String> getHt_appo_sel() {
		return ht_appo_sel;
	}

	public static void setHt_appo_sel(HashMap<String, String> ht_appo_sel) {
		ToolStripButtonRiepComu.ht_appo_sel = ht_appo_sel;
	}





}
