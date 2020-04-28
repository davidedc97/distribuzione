package it.gis.egeosDCL.client.Layout.LayoutObjects;

import it.gis.egeosDCL.client.egeosDCL;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.widgets.grid.ListGrid;

/**
 * CREA LA GRIGLIA DI DETTAGLIO DEL COMUNE
 * @version 1.0
 * 
 */
public class DettaglioComuniListGrid extends ExportableGrid
{
	
	/*DICHIARO I CAMPI DELLA GRIGLIA*/
	
	
	public DettaglioComuniListGrid(String idProvincia,String idComune)
	{
		super();
		this.setID("DettaglioComuniListGrid_ID");
		
		
		
		DataSource dsDettComu = new DataSource();
		dsDettComu.setDataFormat(DSDataFormat.JSON);  
		
		//DataSourceField comuIdField 	= new DataSourceField("id_comune", FieldType.TEXT,"id_comune");  
		//comuIdField.setPrimaryKey(true);
		DataSourceField utenteIdField 	= new DataSourceField("id_utente", FieldType.TEXT,"id_utente");
		DataSourceField assegnatiField 	= new DataSourceField("assegnati", FieldType.TEXT,"Assegnati");
		DataSourceField validatiField 	= new DataSourceField("validati", FieldType.TEXT,"Validati");
		DataSourceField descUtenteField = new DataSourceField("utente", FieldType.TEXT,"utente");
	//	//System.out.println("ID PROVINCIA DettaglioComuniListGrid:  "+idProvincia);
	//	//System.out.println("ID COMUNE IN DettaglioComuniListGrid:  "+idComune);
		
		if(idComune!=null && !idComune.equals("")){
		//	System.out.println("111111111111111111111111111111111111111111111");
			dsDettComu.setDataURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=listAsseUteComuneProviC&idProvincia="+idProvincia+"&numElenco="+egeosDCL.getNumElenco()+"&idComune="+idComune);
		}
		else{
	//		//System.out.println("222222222222222222222222222222222222222222222");
			dsDettComu.setDataURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=listAsseUteComuneProviP&idProvincia="+idProvincia+"&numElenco="+egeosDCL.getNumElenco());
		}	
			//dsDettComu.setDataURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=listAsseUteComuneProvi&idProvincia="+idProvincia+"&idComune="+idComune);
		
		dsDettComu.setRecordXPath("/elencoAssegnazioni");

		dsDettComu.setFields(descUtenteField,assegnatiField,validatiField);  

		
		this.setShowRecordComponents(true);          
		this.setShowRecordComponentsByCell(true);
		this.setWidth100();  
		this.setHeight100();  
		this.setShowAllRecords(true);  
		this.setDataSource(dsDettComu);  
		this.setAutoFetchData(true); 


	}

	
}
