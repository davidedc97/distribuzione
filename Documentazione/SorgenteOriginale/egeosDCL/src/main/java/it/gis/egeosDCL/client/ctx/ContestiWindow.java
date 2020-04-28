package it.gis.egeosDCL.client.ctx;

import it.gis.egeosDCL.client.SguParam;
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
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * <code>ContestiWindow</code>  
 * 
 * @see     
 * CREA LA FINESTRA DI GESTIONE DEI CONTESTI
 * @version 2.0
 */ 
public class ContestiWindow extends Window  {
	
	/**
	 * 
	 */
	private VLayout container					= new VLayout();
	private VLayout vContesto					= new VLayout();
	private DynamicForm frmContesto				= new DynamicForm();
	private TextItem codiceItem 				= new TextItem();
	private TextItem descItem 					= new TextItem();
	private TextItem numElencoItem 				= new TextItem();
	private TextItem campagnaItem 				= new TextItem();
	private SelectItem tipoLavoItem 			= new SelectItem();
	private SelectItem tipoEleItem 				= new SelectItem();
	LinkedHashMap<String, String> tipoLavoMap	= new LinkedHashMap<String, String>();
	LinkedHashMap<String, String> tipoEleMap	= new LinkedHashMap<String, String>();
	private HLayout	hGrid						= new HLayout();
	private VLayout vProvince					= new VLayout();
	private VLayout vUser						= new VLayout();
	private HLayout toolProvince				= new HLayout();
	private HLayout toolUser					= new HLayout();
	private HLayout toolGeneric					= new HLayout();
	private IButton btRecProv					= new IButton("Province");
//	private IButton btRecUser					= new IButton("Utenti");
	private IButton btConfirm					= new IButton("Conferma");
	private IButton btClose						= new IButton("Chiudi");	
	private ContextGrid ctxGrid;
	private String numElenco					= "";
	private ContextListner ctxListner;
	private WorkType workType;
	
	public ContestiWindow(ContextListner ctx,WorkType wt){
		this("0",ctx,wt);
	}
	
	public ContestiWindow(String numElenco,ContextListner ctx,WorkType wt){

		super();
		
		RPCRequest request= new RPCRequest();
		String url;		
		
		workType=wt;
		ctxListner=ctx;
		this.numElenco=numElenco;
		
		ctxGrid	= new ContextGrid(this.numElenco);

		codiceItem.setValue("");
		descItem.setValue("");
		numElencoItem.setValue("");
		campagnaItem.setValue("");
		tipoLavoItem.setValue("0");
		
		/** Window **/
		setTitle("Gestione Contesti applicativi");
//		setWidth(600);  
//		setHeight(600);
		setWidth100();
		setHeight100();
		setShowMinimizeButton(false);  
		setIsModal(true);  
		setShowModalMask(true);
		centerInPage();
//		setLayoutMargin(5);
		addCloseClickHandler(new CloseClickHandler() {
			public void onCloseClick(CloseClickEvent event) {
				closeWin();		
				
			}
		});
		
		if(workType==WorkType.UPDATE || workType==WorkType.VIEW){
			/**
			 * 1 - Recupro le informazione del contesto dalla tavola contesti_applicativi filtrando per NUM_ELENCO
			 * 2 - Verificare se � possibile effettuare la modifica del contesto e nel caso in cui il servizio dice no
			 *     impostare su WIEW il workType
			 */
			url=egeosDCL.getURL_SERVER()+"contestoApplicativoService?numElenco="+numElenco;
			if(url.trim().length()>0){
				request.setData("Some data to send to the client");
				request.setActionURL(GWT.getModuleBaseURL()+"Proxy?u="+SguParam.enc(url));
				
				RPCManager.sendRequest(request, new RPCCallback() {
					public void execute(RPCResponse response, Object rawData, RPCRequest request) {
						parserContext(rawData);
					}
				});
			}else{
				SC.say("Impossibile eseguire l'operazione richiesta! Verificare la url del servizio.");
			}			
			
			
		}
		designWindow();
	}
	
	/**
	 * Metodo che disegna la finestra dei contesti
	 */
	private void designWindow(){

	/** VLayout - HLayout classic **/	
		container.setWidth100();
		container.setHeight100();
//		container.setPadding(5);
		
		vContesto.setWidth100();
		vContesto.setHeight("10%");
		vContesto.setGroupTitle("Dettaglio Contesto");
//		vContesto.setIsGroup(true);
		vContesto.setShowEdges(true);

		hGrid.setWidth100();
		hGrid.setHeight("70%");
		
		vProvince.setWidth100();
		vProvince.setHeight100();
		vProvince.setShowEdges(true);
		
		vUser.setWidth100();
		vUser.setHeight100();
		vUser.setShowEdges(true);
		
	/** DynamicForm ctx **/
		frmContesto.setWidth100();
		frmContesto.setHeight100();
		frmContesto.setPadding(5);
//		frmContesto.setColWidths(60, "*");
		frmContesto.setColWidths(60);
		frmContesto.setNumCols(2);
		
		codiceItem.setTitle("Codice");  
		codiceItem.setLength(20);
//		codiceItem.setWidth("*");
		
		descItem.setTitle("Descrizione");  
		descItem.setLength(255);
//		descItem.setWidth("*");

		numElencoItem.setTitle("Numero Elenco");  
		numElencoItem.setLength(100);
//		numElencoItem.setWidth("*");

		campagnaItem.setTitle("Campagna");  
		campagnaItem.setLength(4);
//		campagnaItem.setWidth("*");
		
		tipoLavoMap.put("0", "");
		tipoLavoMap.put("1", "Collaudo");  
		tipoLavoMap.put("2", "Controllo");  
		tipoLavoMap.put("3", "Fotointerpretazione");  

		tipoLavoItem.setTitle("Tipo Lavorazione");  
	    tipoLavoItem.setValueMap(tipoLavoMap);  
		
		frmContesto.setFields(codiceItem,descItem,campagnaItem,tipoLavoItem);
		
	/** Grid Province**/	
		

	/** HLayout - toolBar Province **/
		toolProvince.setHeight(50);
		toolProvince.setWidth100();
		toolProvince.setPadding(5);
		toolProvince.setAlign(Alignment.RIGHT);

		btRecProv.setWidth(60);
		btRecProv.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				SC.say("Recupero Provincia");
			}
		});		
		

	/** HLayout - toolBar User **/	
		toolUser.setHeight(50);
		toolUser.setWidth100();
		toolUser.setPadding(5);
		toolUser.setAlign(Alignment.RIGHT);

//		btRecUser.setWidth(60);
//		btRecUser.addClickHandler(new ClickHandler() {
//			
//			public void onClick(ClickEvent event) {
//				SC.say("Recupero User");
//			}
//		});
		
	/** HLayout - toolBar Generale **/	
		toolGeneric.setHeight("3%");
		toolGeneric.setWidth100();
		toolGeneric.setPadding(5);
		toolGeneric.setAlign(Alignment.RIGHT);
		toolGeneric.setShowEdges(true);
		
		btConfirm.setWidth(60);
		btConfirm.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				if (verfyContext()){
					confirmContext();
					ctxListner.contextRefresh();
				}
			}
		});		
		
		btClose.setWidth(60);
		btClose.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				closeWin();
			}
		});
		
		if(workType==WorkType.VIEW){
			btConfirm.setDisabled(true);
		}
		
	/** **/
		toolGeneric.addMember(btConfirm);
		toolGeneric.addMember(btClose);
		toolProvince.addMember(btRecProv);
//		toolUser.addMember(btRecUser);
		
	/** **/	
		vContesto.addMember(frmContesto);
	
	/** **/		
		vProvince.addMember(ctxGrid);
//		vUser.addMember(new UserAssignGrid());
//		vProvince.addMember(toolProvince);
	
	/** **/		
//		vUser.addMember(grigliauser);
		vUser.addMember(toolUser);

	/** **/		
		hGrid.addMember(vProvince);
		hGrid.addMember(vUser);
	
	/** **/	
		container.addMember(vContesto);
		container.addMember(hGrid);
		container.addMember(toolGeneric);
		
		addItem(container);
		enableContext();
	}		
	
	private void enableContext(){
		
		boolean val=false;
		
		if(workType==WorkType.VIEW){
			val	= true;
		}	
		codiceItem.setDisabled(val);
		descItem.setDisabled(val);
		numElencoItem.setDisabled(val);
		campagnaItem.setDisabled(val);
		tipoLavoItem.setDisabled(val);
		tipoEleItem.setDisabled(val);
		
	}
	
	/**
	 * Chiude la finestra dei contesti
	 */
	private void closeWin(){
		destroy();
	}
	
	/**
	 * Effettua un controllo formale sui dati inseriti 
	 * @return
	 */
	private boolean verfyContext(){
		boolean res	= true;
		String msg	= ""; 
		
		if(codiceItem.getValueAsString().trim().length()==0 ){
			msg+=" - Indicare il codice del contesto<br>";
			res = false;
		}
		if(descItem.getValueAsString().trim().length()==0 ){
			msg+=" - Indicare la descrizione del contesto<br>";
			res = false;
		}
//		if(numElencoItem.getValueAsString().trim().length()==0 ){
//			msg+=" - Indicare il numero elenco<br>";
//			res = false;
//		}
		if(campagnaItem.getValueAsString().trim().length()==0 ){
			msg+=" - Indicare la campagna di riferiento<br>";
			res = false;
		}
			
		if(tipoLavoItem.getValueAsString().equals("0")){
			msg+=" - Indicare il tipo di lavorazione<br>";
			res = false;
		}
		
		if(ctxGrid.getSelectedRecords().length==0){
			msg+=" - Selezionare almeno una provincia<br>";
			res = false;
		}

		
		if(!res){
			SC.say("Attenzione!<br> Controllare i seguenti valori.<br>"+msg);
		}
		
		return res;
	}
	
	private void parserContext(Object rawData){
		
		JSONValue value = JSONParser.parseLenient(rawData+"");
		
		if(value.isObject()!=null){
			JSONObject obj = value.isObject();
			JSONValue app = obj.get("TodoLista");
			JSONArray arr = app.isArray();
			if(arr!=null){
				JSONObject ob = arr.get(0).isObject();
				if(ob!=null){
//					JSONString str = ob.get("desc_utente").isString();
					codiceItem.setValue(""+ob.get("ID_ELENCO").toString().replace("\"",""));
					descItem.setValue(""+ob.get("DESCRIZIONE").toString().replace("\"",""));
//					numElencoItem.setValue("");
					campagnaItem.setValue(""+ob.get("CAMPAGNA").toString().replace("\"",""));
					tipoLavoItem.setValue(ob.get("TIPO_LAVO"));
				}
			}
		}		
	}
	
	/**
	 * Conferma i dati del contesto in base dati
	 */
	private void confirmContext(){
		RPCRequest request	= new RPCRequest();
		String cod			= "";
		String desc			= "";
		String num			= "";
		String campagna		= "";
		String idProv		= "";
		String tipoLavo		= "";
		String url			= "";
		
		for (ListGridRecord record : ctxGrid.getSelectedRecords()) {
			(idProv+=record.getAttribute("idProv")+",").trim();
		}

		cod	= codiceItem.getValueAsString();
		desc = descItem.getValueAsString();
//		num	= numElencoItem.getValueAsString();
		campagna = campagnaItem.getValueAsString();
		tipoLavo = tipoLavoItem.getValueAsString();
		idProv=idProv.substring(0,idProv.length()-1);
		
		if(workType==WorkType.INSERT){
			url=egeosDCL.getURL_SERVER()+"addContextAll?idElenco="+cod+"&descrizione="+desc+"&"+
					"campagna="+campagna+"&"+
					"tipoElenco=0&"+
					"tipoLavo="+tipoLavo+"&"+
					"idProv="+idProv;
		}else if(workType==WorkType.UPDATE){
			url=egeosDCL.getURL_SERVER()+"updateContextAll?idElenco="+cod+"&descrizione="+desc+"&"+
					"campagna="+campagna+"&"+
					"tipoElenco=0&"+
					"tipoLavo="+tipoLavo+"&"+
					"numElenco="+numElenco+"&"+
					"idProv="+idProv;
		}else{
			url="";
		}
		
//		System.out.println(url);
//		http://www.sourcedemo.net:7001/egeosWs/updateContextAll?idElenco=XXX-Modifica&descrizione=Testo Modifica&campagna=2020&tipoElenco=0&tipoLavo=3&numElenco=11&idProv=59,83,81,80
		
		
		if(url.trim().length()>0){
			request.setData("Some data to send to the client");
			request.setActionURL(GWT.getModuleBaseURL()+"Proxy?u="+SguParam.enc(url));
			
			RPCManager.sendRequest(request, new RPCCallback() {
				
				public void execute(RPCResponse response, Object rawData, RPCRequest request) {
					SC.say(egeosDCL.fireMessage(rawData));
				}
			});
		}else{
			SC.say("Impossibile eseguire l'operazione richiesta! Verificare la url del servizio.");
		}
		
	}

}
