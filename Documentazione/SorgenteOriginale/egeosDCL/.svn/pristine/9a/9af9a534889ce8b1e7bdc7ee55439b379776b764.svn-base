package it.gis.egeosDCL.client.Layout.LayoutObjects;

import it.gis.egeosDCL.client.egeosDCL;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.SummaryFunctionType;
import com.smartgwt.client.widgets.grid.ListGrid;

/**
 * CREA LA GRIGLIA DEGLI UTENTI ASSOCIATI AD UN COMUNE TRAMITE UNO SCRIPT JSON
 * @version 1.0
 * 
 */
public class RiepilogoUtentiProvListGrid extends ExportableGrid
{
	
	
	public RiepilogoUtentiProvListGrid(String idProvincia,String idComune)
	{
		super();
		this.setID("RiepilogoUtentiiProvListGrid_ID");
		
		String  numElenco = "";
		
		numElenco = egeosDCL.getNumElenco();
		
		//System.out.println("ID PROVINCIA: 	"+idProvincia);
		//System.out.println("NUM ELENCO: 		"+numElenco);
		
		DataSource dsUtentiProv = new DataSource();
		dsUtentiProv.setDataFormat(DSDataFormat.JSON);  
		dsUtentiProv.setCacheAllData(true);
		DataSourceField comuIdField 	= new DataSourceField("id_comune", FieldType.TEXT,"id_comune");  
		comuIdField.setPrimaryKey(true);
		DataSourceField utenteIdField 	= new DataSourceField("utente", FieldType.TEXT,"utente");
		
		DataSourceField assegnatiField 	= new DataSourceField("assegnati", FieldType.TEXT,"Assegnati");
		assegnatiField.setSummaryFunction(SummaryFunctionType.SUM); 
		
	//	DataSourceField validatiField 	= new DataSourceField("validati", FieldType.TEXT,"Validati");
	//	validatiField.setSummaryFunction(SummaryFunctionType.SUM); 
		
		DataSourceField nomeComuneField 	= new DataSourceField("nome_comune", FieldType.TEXT,"Comune");
		
		DataSourceField totaliField 	= new DataSourceField("totali", FieldType.TEXT,"totali");
		totaliField.setSummaryFunction(SummaryFunctionType.SUM); 
		
		dsUtentiProv.setDataURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=listAsseUteComuneProviP&tipoOper=RIEP&idProvincia="+idProvincia+"&numElenco="+numElenco);
			//dsDettComu.setDataURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=listAsseUteComuneProvi&idProvincia="+idProvincia+"&idComune="+idComune);
		
		dsUtentiProv.setRecordXPath("/elencoAssegnazioni");

		dsUtentiProv.setFields(utenteIdField,nomeComuneField,assegnatiField,totaliField);  

		this.setGroupByField("utente");
		this.setGroupStartOpen(GroupStartOpen.ALL);   
		this.setShowRecordComponents(true);          
		this.setShowRecordComponentsByCell(true);
		this.setWidth100();  
		this.setHeight100();  
		this.setShowAllRecords(true);  
		this.setDataSource(dsUtentiProv);  
		this.setAutoFetchData(true); 
		this.setShowGridSummary(true);
		this.setShowGroupSummary(true);  
		this.setSelectionType(SelectionStyle.SIMPLE);  
		this.setSelectionAppearance(SelectionAppearance.CHECKBOX); 
		
//		this.addSelectionUpdatedHandler(new SelectionUpdatedHandler() {  
//	            public void onSelectionUpdated(SelectionUpdatedEvent event) {  
//	                selectedCountriesGrid.setData(countryGrid.getSelection());  
//	            }  
//	        }); 
	}

	
}
