package it.gis.egeosDCL.client.ctx;


import it.gis.egeosDCL.client.SguParam;
import it.gis.egeosDCL.client.egeosDCL;
import it.gis.egeosDCL.client.Layout.LayoutObjects.ExportableGrid;
import it.gis.egeosDCL.client.Layout.LayoutObjects.WinLoading;

import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.rpc.RPCCallback;
import com.smartgwt.client.rpc.RPCManager;
import com.smartgwt.client.rpc.RPCRequest;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.SelectionType;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellHoverEvent;
import com.smartgwt.client.widgets.grid.events.CellHoverHandler;
import com.smartgwt.client.widgets.grid.events.RowEditorExitEvent;
import com.smartgwt.client.widgets.grid.events.RowEditorExitHandler;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;

public class UserAssignGrid extends ExportableGrid {



	protected boolean modifica=false;


	public UserAssignGrid()
	{
		super();
		setShowRecordComponents(true);          
		setShowRecordComponentsByCell(true);
		setShowAllRecords(true); 
		setShowFilterEditor(true); 
		setWidth(400);
		setHeight(300);
		setSelectionType(SelectionStyle.SIMPLE);
		setShowAllRecords(true);  
		setSelectionProperty("assigned");
		setCanHover(true);
		setSelectionAppearance(SelectionAppearance.CHECKBOX);
		ListGridField idUtente = new ListGridField("id_utente","Id Utente");  
		idUtente.setHidden(true);

		ListGridField livelloRuolo = new ListGridField("livello_ruolo", "ctx");  
		livelloRuolo.setHidden(true);
		ListGridField assegnato = new ListGridField("assegnato", "assegnato");  
		assegnato.setHidden(true);
		livelloRuolo.setHidden(true);
		ListGridField descUtente = new ListGridField("desc_utente", "Nome Utente");

		ListGridField organismo = new ListGridField("organismo", "Organismo");
		organismo.setHidden(true);
		setFields(idUtente,descUtente,organismo,assegnato);
		setDataSource(UserDS.getInstance());

		addSelectionChangedHandler(new SelectionChangedHandler() {

			
			public void onSelectionChanged(SelectionEvent event) {
				if(event.isLeftButtonDown()&&modifica) // solo se clicchi altrimenti va qui anche da createRecordComponent 
				{
					WinLoading w =new WinLoading("Aggiornamento...");
					Record rec= event.getRecord();
					int checked = event.getState()?1:0;
					String id_utente = rec.getAttributeAsString("id_utente");
					String codice    = egeosDCL.getOrganismo();
					String desc_azie = egeosDCL.getDescAzie();
					
					updateCodOrganismo(id_utente,codice,desc_azie,checked,w,rec);
				}
			
			}
		});
		addCellHoverHandler(new CellHoverHandler() {
			
			
			public void onCellHover(CellHoverEvent event) {
				UserAssignGrid.this.modifica  = true;
			}
		});
		fetchData();

	}


	protected void updateCodOrganismo(String id_utente, String codice, String desc_azie, int checked, final WinLoading w, final Record rec) {
		w.show();
		RPCRequest request	= new RPCRequest();

		String url = egeosDCL.getURL_SERVER()+"updateUtenteOrganismo?codOrganismo="+codice+"&idUtente="+id_utente+"&checked="+checked+"&desc_azie="+desc_azie+"&id_padre="+egeosDCL.getIdUtente();
		//System.out.println("url::::: "+url);
		request.setActionURL(GWT.getModuleBaseURL()+"Proxy?u="+SguParam.enc(url));

		RPCManager.sendRequest(request, new RPCCallback() {
			
			public void execute(RPCResponse response, Object rawData, RPCRequest request) {
				JSONValue value = JSONParser.parseLenient(rawData+"");
				
				if(value.isObject()!=null){
					JSONObject obj = value.isObject();
					JSONValue app = obj.get("TodoLista");
					JSONArray arr = app.isArray();
					if(arr!=null){
						JSONObject ob = arr.get(0).isObject();
						if(ob!=null){
							String codice = ob.get("statuscode").toString();
							if(!codice.equals("0"))
							{
								modifica = false;
								UserAssignGrid.this.selectRecord(rec, !rec.getAttributeAsBoolean("assigned"));
							}
						}
					}
				}		
				
				SC.say(egeosDCL.fireMessage(rawData));
				UserAssignGrid.this.saveAllEdits();
				w.hide();
			}
		});

	}

	
	protected Canvas createRecordComponent(final ListGridRecord record, final Integer colNum) {
		
		this.setAlign(Alignment.CENTER);

		if(Integer.valueOf(record.getAttributeAsString("assegnato"))>0){
			record.setAttribute("assigned",true);
		}	
		return super.createRecordComponent(record, colNum);

	} 		


	private static class UserDS extends DataSource {

		private static UserDS instance	= null;  


		public static UserDS getInstance() {  

			instance=null;
			instance = new UserDS();  
			return instance;  
		}  

		public UserDS() {  

			setDataFormat(DSDataFormat.JSON);  

			DataSourceField idUtente = new DataSourceField("id_utente", FieldType.INTEGER,"Id Utente");
			idUtente.setPrimaryKey(true);
			DataSourceField livelloRuolo = new DataSourceField("livello_ruolo", FieldType.INTEGER,"ctx");  
			DataSourceField descUtente = new DataSourceField("desc_utente", FieldType.TEXT,"Tipo Utente");
			DataSourceField organismo = new DataSourceField("organismo", FieldType.TEXT,"Organismo"); 
			DataSourceField assegnato = new DataSourceField("assegnato", FieldType.TEXT,"Assegnato");
			
			assegnato.setHidden(true);
			idUtente.setHidden(true);
			livelloRuolo.setHidden(true);
			organismo.setHidden(true);
			assegnato.setHidden(true);

			setFields(idUtente,descUtente,livelloRuolo,organismo,assegnato);  
			String url=egeosDCL.getURL_SERVER()+"utentiFoto?cod_organismo="+egeosDCL.getOrganismo()+"&desc_azie="+egeosDCL.getDescAzie();
			
			setDataURL(GWT.getModuleBaseURL()+"Proxy?u="+SguParam.enc(url));

			setRecordXPath("/TodoLista");
			setClientOnly(true);

		}  

	}  

}
