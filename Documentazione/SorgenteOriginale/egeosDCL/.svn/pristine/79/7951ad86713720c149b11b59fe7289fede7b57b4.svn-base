package it.gis.egeosDCL.client.Layout.LayoutObjects;

import it.gis.egeosDCL.client.egeosDCL;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.CellClickEvent;
import com.smartgwt.client.widgets.grid.events.CellClickHandler;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.HLayout;

/**
 * CREA LA GRIGLIA DELLE REGIONI TRAMITE UNO SCRIPT JSON
 * @version 1.0
 * 
 */
public class ListGridRegioniJSON extends ExportableGrid{

	ListGridProvinceJSON         listProvJSON					= null;
	
	static ListGrid grid;
	
	public ListGridRegioniJSON()
	{
		
		grid=this;
		DataSource ds = new DataSource();
		ds.setDataFormat(DSDataFormat.JSON);  
		
		DataSourceField countryCodeField = new DataSourceField("deno_regi", FieldType.TEXT,"deno_regi");  
		
		DataSourceField idCodeField = new DataSourceField("id_regi", FieldType.TEXT, "id_regi");  
		idCodeField.setPrimaryKey(true);
		idCodeField.setHidden(true);
		
		DataSourceField wktCodeField = new DataSourceField("wkt", FieldType.TEXT,"wkt");  
		
		DataSourceField fusoCodeField = new DataSourceField("fuso", FieldType.TEXT,"fuso");  
		DataSourceField xlsCodeField = new DataSourceField("xls", FieldType.TEXT,"xls");  
		
		countryCodeField.setAttribute("wkt",wktCodeField);
		
		DataSourceField noAssegnateCodeField = new DataSourceField("noassegnate", FieldType.TEXT,"NON ASSEGNATE");  
		DataSourceField assegnateCodeField = new DataSourceField("assegnate", FieldType.TEXT,"ASSEGNATE");
		DataSourceField totCodeField = new DataSourceField("tot", FieldType.TEXT,"TOT");
		DataSourceField lavorateCodeField = new DataSourceField("lavorate", FieldType.TEXT,"LAVORATE");
		
		ds.setFields(idCodeField,countryCodeField,noAssegnateCodeField,assegnateCodeField,totCodeField,lavorateCodeField,xlsCodeField);  
		ds.setDataURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=regioniRefreshService&numElenco="+egeosDCL.getNumElenco());  

		ds.setRecordXPath("/TodoLista");

		this.setWidth100();  
		this.setID("REGIONI_GRID_ID");
		this.setHeight("55%");  
		this.setShowAllRecords(true);  
		this.setDataSource(ds);  
		this.setAutoFetchData(true);  
		
		setShowRecordComponents(true); 
		setShowRecordComponentsByCell(true);
		
		this.addCellClickHandler(new CellClickHandler() {
			
			
			public void onCellClick(CellClickEvent event) {
				ChangedEvent ev = new ChangedEvent(ListGridContestiJSON.getComboRegioni().getJsObj());
				ListGridContestiJSON.getComboRegioni().setValue(event.getRecord().getAttributeAsString("id_regi"));
				ListGridContestiJSON.getComboRegioni().fireEvent(ev);
				
			}
		});
	}
	
	public static void selectedRow(String idRegi){
	//SC.say("ID regione "+idRegi+ "NOME REGIONE:"+descRegi);
		grid.deselectAllRecords();
		
		for(int i = 0;i<grid.getRecords().length;i++)
		{
			if(grid.getRecords()[i].getAttributeAsString("id_regi").equals(idRegi)){
				grid.selectRecord(i);
				//ToolStripButtonRegioni.getRegioneSel().setValue(descRegi);
			}
			else{
				//ToolStripButtonRegioni.getRegioneSel().setValue(grid.getRecords()[i].getAttributeAsString("deno_regi"));
				
				//grid.deselectRecord(i);
			}
		}	
		
		
	}
	
	
	protected Canvas createRecordComponent(final ListGridRecord record, Integer colNum) {  
		
		String fieldName = this.getFieldName(colNum);  
		
		if (fieldName.equals("xls")){
			final ImgButton chartImg = new ImgButton();  
			chartImg.setShowDown(false);  
			chartImg.setShowRollOver(false);  
			chartImg.setAlign(Alignment.CENTER);  		
			chartImg.setSrc("excel.png");
			chartImg.setPrompt("Esporta dettaglio in XLS");
			chartImg.setHeight(16);  
			chartImg.setWidth(16);

			chartImg.addClickHandler(new com.smartgwt.client.widgets.events.ClickHandler() {
				
				public void onClick(com.smartgwt.client.widgets.events.ClickEvent event) {
					Window.open(
                    		GWT.getModuleBaseURL()+"DettaglioEFAXLSServlet?numeElenco="+egeosDCL.getNumElenco()+"&reg="+record.getAttributeAsString("id_regi"),
                            "_blank", "width=600,height=550,scrollbars=yes,resizable=yes");
				}
			});
			return chartImg;
		} else {  
			return null;  
		}  

	}  
}
