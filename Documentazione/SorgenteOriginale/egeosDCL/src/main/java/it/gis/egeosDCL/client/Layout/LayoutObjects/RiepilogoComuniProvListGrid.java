package it.gis.egeosDCL.client.Layout.LayoutObjects;

import java.util.HashMap;
import java.util.Set;

import it.gis.egeosDCL.client.egeosDCL;
import it.gis.egeosDCL.client.Layout.LeftLayout;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.SortSpecifier;
import com.smartgwt.client.rpc.RPCCallback;
import com.smartgwt.client.rpc.RPCManager;
import com.smartgwt.client.rpc.RPCRequest;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.SortDirection;
import com.smartgwt.client.types.SummaryFunctionType;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.DataArrivedEvent;
import com.smartgwt.client.widgets.grid.events.DataArrivedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.CheckBox;
/**
 * CREA LA GRIGLIA DEI COMUNI FILTRATI PER PROVINCIA TRAMITE UNO SCRIPT JSON
 * @version 1.0
 * 
 */
public class RiepilogoComuniProvListGrid extends ExportableGrid
{
	
	/*DICHIARO I CAMPI DELLA GRIGLIA*/
	int conta = 0;
	int numeroRecord = 0;
	static String appoComune = "";
	public static HashMap<String,String> comuniSel = new  HashMap<String, String>();
	String appo_id_utente = "";

	public RiepilogoComuniProvListGrid(String idProvincia,String idComune)
	{
		super();
		this.setID("RiepilogoComuniProvListGrid_ID");
		//this.setTitle("sdsdsdsd");
	
		String numElenco = "";
		
		numElenco = egeosDCL.getNumElenco();
		
		DataSource dsComuniProv 		= new DataSource();
		dsComuniProv.setDataFormat(DSDataFormat.JSON);  
		
		DataSourceField comuIdField 	= new DataSourceField("id_comune", FieldType.TEXT,"id_comune");  
		comuIdField.setPrimaryKey(true);
		
		DataSourceField utenteIdField 	= new DataSourceField("id_utente", 	FieldType.TEXT,"id_utente");
		DataSourceField comuneField 	= new DataSourceField("nome_comune", 	FieldType.TEXT,"Comune",40);
		DataSourceField descUtenteField	= new DataSourceField("utente", 	FieldType.TEXT,"utente",20);
		DataSourceField assegnatiField 	= new DataSourceField("assegnati", 	FieldType.TEXT,"Assegnati",15);
		DataSourceField checkField 		= new DataSourceField("Sel", 	FieldType.TEXT,"",10);
		
		assegnatiField.setSummaryFunction(SummaryFunctionType.SUM);
		//DataSourceField validatiField 	= new DataSourceField("validati", 	FieldType.TEXT,"validati");
		DataSourceField idProvField		= new DataSourceField("id_prov", 	FieldType.TEXT,"id_prov");
		DataSourceField totField		= new DataSourceField("totali", 	FieldType.TEXT,"totali",15);
		totField.setSummaryFunction(SummaryFunctionType.SUM);
		ListGridRecord selectedRecord = ListGridComuniJSON.getListComuJSON().getSelectedRecord(); 
		//dsComuniProv.setDataURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=listAsseUteComuneProviC&idProvincia="+idProvincia+"&numElenco="+numElenco+"&idComune="+selectedRecord.getAttributeAsString("idComune"));
		dsComuniProv.setDataURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=listAsseUteComuneProviP&tipoOper=RIEP&idProvincia="+idProvincia+"&numElenco="+numElenco);
		dsComuniProv.setRecordXPath("/elencoAssegnazioni");
		
		dsComuniProv.setFields(checkField,comuneField,descUtenteField,assegnatiField,totField);  
		 setCanMultiSort(true);  
	        setInitialSort(new SortSpecifier[]{  
	                new SortSpecifier("nome_comune", SortDirection.ASCENDING)  
	                //new SortSpecifier("denoProv", SortDirection.ASCENDING)  
	        });

//		this.setGroupByField("nome_comune");
//		this.setGroupStartOpen(GroupStartOpen.ALL);   
		this.setShowRecordComponents(true);          
		this.setShowRecordComponentsByCell(true);
		this.setWidth100();  
		this.setHeight100();  
		this.setShowAllRecords(true);  
		this.setDataSource(dsComuniProv);  
		this.setAutoFetchData(true); 
//		this.setShowGridSummary(true);
//		this.setShowGroupSummary(true);  
//		this.setSelectionType(SelectionStyle.SIMPLE);  
//		this.setSelectionAppearance(SelectionAppearance.CHECKBOX); 
		
		this.filterData(new Criteria("validati", "<>"));
//		this.setCanMultiSort(true);
//		this.sort("nome_comune", SortDirection.ASCENDING);
	
	}

	protected Canvas createRecordComponent(final ListGridRecord record, Integer colNum) {  

		String fieldName = this.getFieldName(colNum);  
		String nomecomune = record.getAttributeAsString("nome_comune");
		
		comuniSel.put(record.getAttributeAsString("id_comune"),record.getAttributeAsString("id_utente"));
		
//		System.out.println("nomecomune: "+nomecomune);
//		System.out.println("FIELD NAME: "+fieldName);
		
		if (fieldName.equals("Sel")) {  
			HLayout recordCanvas = new HLayout(3);  
			recordCanvas.setHeight(22);  
			recordCanvas.setWidth(20);
			recordCanvas.setAlign(Alignment.RIGHT);  
		
			final CheckBox check = new CheckBox();
	//		System.out.println("appo comune::  "+appoComune+"    fieldname: "+nomecomune);
			
//			if(!appoComune.equals(nomecomune)){
//				appo_id_utente = "";
//				appo_id_utente = record.getAttributeAsString("id_utente");
				recordCanvas.addMember(check);
//			}
//			else
//			{
//				appo_id_utente = appo_id_utente+","+record.getAttributeAsString("id_utente");
//			}
			
			check.addClickHandler(new ClickHandler() {
				
				
				public void onClick(ClickEvent event) {
					// TODO Auto-generated method stub
					if(check.getValue()){
//						System.out.println("----------- "+record.getAttributeAsString("id_comune")+"#"+record.getAttributeAsString("id_utente"));
//						if(record.getAttributeAsString("id_utente")!=null && !record.getAttributeAsString("id_utente").equals(""))
//						comuniSel.put(record.getAttributeAsString("id_comune"),record.getAttributeAsString("id_utente"));
						ToolStripButtonRiepComu.setSelezionaTemp(record.getAttributeAsString("id_comune"));
						ToolStripButtonRiepComu.getHt_appo_sel().put(record.getAttributeAsString("id_comune"), record.getAttributeAsString("id_utente"));
//						System.out.println("IS CHECKED: "+record.getAttributeAsString("id_comune"));
//						comuniSel.put(record.getAttributeAsString("id_comune"),record.getAttributeAsString("id_utente")+"#"+appo_id_utente);
//						if(!appoComune.equals(record.getAttributeAsString("nome_comune")))
//							comuniSel.put(record.getAttributeAsString("id_comune"),record.getAttributeAsString("id_utente"));
//						else
//							comuniSel.put(record.getAttributeAsString("id_comune"),appo_id_utente);
					}
					else
					{
						if(comuniSel.containsKey(record.getAttributeAsString("id_comune"))){
							comuniSel.remove(record.getAttributeAsString("id_comune"));
						}
						if(ToolStripButtonRiepComu.getHt_appo_sel().containsKey(record.getAttributeAsString("id_comune"))){
							ToolStripButtonRiepComu.getHt_appo_sel().remove(record.getAttributeAsString("id_comune"));
						}
					//	System.out.println("NOT CHECKED");
					}
					//comuniSel.put("", value)
				}
			});
			
			return recordCanvas;  
		} else {  
			if(fieldName.equals("nome_comune")&&record.getAttributeAsString("nome_comune")!=null){
				appoComune = nomecomune;
			}
			
			return null;  
		}  

	}
	
	public static HashMap<String, String> getComuniSel() {
		return comuniSel;
	}

	public static void setComuniSel(HashMap<String, String> comuniSel) {
		RiepilogoComuniProvListGrid.comuniSel = comuniSel;
	}

}
