package it.gis.egeosDCL.client.Layout.LayoutObjects;

import it.gis.egeosDCL.client.egeosDCL;
import java.util.LinkedHashMap;

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
 * CREA LA PULSANTIERA DELLA GRIGLIA DEI QUADRANTI
 * @version 1.0
 * 
 */
public class ToolStripButtonQuadranti extends ToolStrip{

	DynamicForm 					form 							= null;
	public static SelectItem    					selectFotointerprete 			= null;
	static ToolStripButton  				btAssegna 						= null;
	static ToolStripButton  				btAssegna_all					= null;
	public static TextItem			quadranteSel					= null;
	LinkedHashMap<String,String>  	mapFotointerpreteDaAssegnare 	= null;
	public static HiddenItem						comuneSel						= null;
	public static HiddenItem						stringQuadrantiSel				= null;
	WinLoading 				winLoad = new WinLoading("Salvataggio in corso...");
	public ToolStripButtonQuadranti()
	{
		super();
		mapFotointerpreteDaAssegnare = new LinkedHashMap<String, String>();
		this.setID("ID_TOOLSTRIP_QUADRANTI");
		
		comuneSel = new HiddenItem();
		stringQuadrantiSel = new HiddenItem();
		
		//System.out.println("dentro ToolStripButtonQuadranti");
		form = new DynamicForm();
		selectFotointerprete = new SelectItem("FotoInterprete");
		quadranteSel = new TextItem("&nbsp;");
		quadranteSel.setDisabled(true);
		quadranteSel.setWidth("80");
		
		btAssegna_all = new ToolStripButton();
		btAssegna_all.setWidth(25);
		btAssegna_all.setIcon("salvatutto.jpg");
		btAssegna_all.setTooltip("Assegna tutti i quadranti");
		
		btAssegna_all.addClickHandler(new ClickHandler() {
			
			
			public void onClick(ClickEvent event) {
				 

			}
		});
		

		RPCRequest request = new RPCRequest();
		
		request.setData("Some data to send to the client");
		request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=fotoInterpreteService&descUtente="+egeosDCL.getDescUtente());
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
						JSONObject ob = arr.get(0).isObject();
						if(ob!=null)
						{
							
							for(int i = 0;i<arr.size();i++)
							{
								mapFotointerpreteDaAssegnare.put(ob.get("id_utente").toString(), ob.get("desc_utente").toString().replace("\"",""));
							}
							selectFotointerprete.setValueMap(mapFotointerpreteDaAssegnare);

						}
					}
				}
			}
		});
		
		selectFotointerprete.setDefaultValue("Selezionare un operatore");
		form.setItems(selectFotointerprete,comuneSel,stringQuadrantiSel);
		
		
		btAssegna  = new ToolStripButton("&nbsp;");
		btAssegna.setWidth(25);
		btAssegna.setIcon("salva.jpg");
		btAssegna.setTooltip("Assegna i quadranti selezionati");
		btAssegna.addClickHandler(new ClickHandler() {
			
			
			public void onClick(ClickEvent event) {
				
			/*	//System.out.println("REGIONE SEL display: "+provinciaSel.getDisplayValue());
				//System.out.println("REGIONE SEL ATTRIBUTE ID: "+provinciaSel.getAttributeAsString("idProv"));
				//System.out.println("SOCIO SEL getDisplayValue: "+selctOperatoreProvincia.getDisplayValue());
				//System.out.println("SOCIO SEL getValueAsString: "+selctOperatoreProvincia.getValueAsString());
				//System.out.println("NUMERO ELENCO: "+egeosDCL.getNumElenco());
			*/	
				if((ToolStripButtonQuadranti.getStringQuadrantiSel().getValue().toString()!=null && !ToolStripButtonQuadranti.getStringQuadrantiSel().getValue().toString().equals(""))&&
						(selectFotointerprete.getValueAsString()!=null && !selectFotointerprete.getValueAsString().equals("")&&!selectFotointerprete.getValueAsString().equals("Selezionare un operatore"))	){
					winLoad.show();
					RPCRequest request = new RPCRequest();
					// Note data could be a String, Map or Record
					request.setData("Some data to send to the client");
					request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=assegnaUtentiQuadrantiAll&idComune="+getComuneSel().getValue().toString()+"&idQuadranti="+ToolStripButtonQuadranti.getStringQuadrantiSel().getValue().toString().substring(1,ToolStripButtonQuadranti.getStringQuadrantiSel().getValue().toString().length())+"&numElenco="+egeosDCL.getNumElenco()+"&idUtente="+selectFotointerprete.getValueAsString().replace("\"","")+"&idPadre="+egeosDCL.getIdUtente()+"&codUtente="+selectFotointerprete.getDisplayValue().toString());
					
					RPCManager.sendRequest(request, new RPCCallback() {
						
						
						public void execute(RPCResponse response, Object rawData, RPCRequest request) {
							// TODO Auto-generated method stub
							//System.out.println("RAWDATA: "+rawData);
							winLoad.hide();
							SC.say(egeosDCL.fireMessage(rawData));
						}
					});
				}
				else
					SC.say("Selezionare il quadrante e l&apos;operatore da assegnare");
			}
		});
		btAssegna.setDisabled(true);
		btAssegna_all.setDisabled(true);
		
		//this.addFormItem(quadranteSel);
		this.addFormItem(selectFotointerprete);
//		this.addButton(btAssegna);
//		this.addButton(btAssegna_all);
		this.setWidth100();
		this.show();
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
		ToolStripButtonQuadranti.btAssegna_all = btAssegna_all;
	}


	public static TextItem getQuadranteSel() {
		return quadranteSel;
	}


	public static void setQuadranteSel(TextItem quadranteSel) {
		ToolStripButtonQuadranti.quadranteSel = quadranteSel;
	}


	public static HiddenItem getStringQuadrantiSel() {
		return stringQuadrantiSel;
	}


	public static void setStringQuadrantiSel(HiddenItem stringQuadrantiSel) {
		ToolStripButtonQuadranti.stringQuadrantiSel = stringQuadrantiSel;
	}


	public static HiddenItem getComuneSel() {
		return comuneSel;
	}


	public static void setComuneSel(HiddenItem comuneSel) {
		ToolStripButtonQuadranti.comuneSel = comuneSel;
	}






	public static SelectItem getSelectFotointerprete() {
		return selectFotointerprete;
	}






	public static void setSelectFotointerprete(SelectItem selectFotointerprete) {
		ToolStripButtonQuadranti.selectFotointerprete = selectFotointerprete;
	}





	

}
