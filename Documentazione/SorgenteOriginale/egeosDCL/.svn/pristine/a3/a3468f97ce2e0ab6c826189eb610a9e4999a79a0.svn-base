package it.gis.egeosDCL.client.ctx;

import it.gis.egeosDCL.client.SguParam;
import it.gis.egeosDCL.client.egeosDCL;
import it.gis.egeosDCL.client.Layout.LayoutObjects.ExportableGrid;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.SortSpecifier;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.types.GroupStartOpen;
import com.smartgwt.client.types.SelectionAppearance;
import com.smartgwt.client.types.SelectionStyle;
import com.smartgwt.client.types.SortDirection;
import com.smartgwt.client.util.BooleanCallback;
import com.smartgwt.client.util.FileLoader;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.ImgButton;
import com.smartgwt.client.widgets.events.FetchDataEvent;
import com.smartgwt.client.widgets.events.FetchDataHandler;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.DataArrivedEvent;
import com.smartgwt.client.widgets.grid.events.DataArrivedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.grid.events.SelectionUpdatedEvent;
import com.smartgwt.client.widgets.grid.events.SelectionUpdatedHandler;

/**
 * CREA LA GRIGLIA DEI CONTESTI
 * @version 1.0
 * 
 */
public class ContextGrid extends ExportableGrid {
	
	private String numElenco= ""; 
	

	
	protected Canvas createRecordComponent(final ListGridRecord record, final Integer colNum) {
		String fieldName	= this.getFieldName(colNum);
		
		this.setAlign(Alignment.CENTER);

		if(record.getAttributeAsInt("ctx")>0){
			selectRecord(record);
		}	
		return null;
		
	} 		
	
	public ContextGrid(){
		this("0");
	}
	
	public ContextGrid(String numElenco){
		super();
		
		if (numElenco.trim().length()==0){
			numElenco="0";
		}	
		this.numElenco=numElenco;
		
		setShowRecordComponents(true);          
		setShowRecordComponentsByCell(true);
		setWidth100();  
		setHeight100();  
		setShowAllRecords(true); 
		setGroupStartOpen(GroupStartOpen.ALL);  
        setGroupByField("denoRegi");
        
        setSelectionType(SelectionStyle.SIMPLE);  
        setSelectionAppearance(SelectionAppearance.CHECKBOX);
        
        setCanMultiSort(true);  
        setInitialSort(new SortSpecifier[]{  
                new SortSpecifier("denoRegi", SortDirection.ASCENDING),  
                new SortSpecifier("denoProv", SortDirection.ASCENDING)  
        });          
        
		setDataSource(CountryDS.getInstance(this.numElenco));
		setAutoFetchData(true);
		
		addDataArrivedHandler(new DataArrivedHandler() {
			
			public void onDataArrived(DataArrivedEvent event) {
				markForRedraw();
				setShowRecordComponentsByCell(true);
				
			}
		});
		
	}
	
	/**
	 * 
	 * @version 1.0
	 *
	 */
	 private static class CountryDS extends DataSource {
		 
        private static CountryDS instance	= null;  
        private static String url			= "";
        private static String numElenco		= "";
          
        public static CountryDS getInstance(String numE) {  
        	numElenco=numE;
        	instance=null;
            instance = new CountryDS("countryDS_JSON");  
            return instance;  
        }  
  
        public CountryDS(String id) {  
            setDataFormat(DSDataFormat.JSON);  
            
            DataSourceField idProv = new DataSourceField("idProv", FieldType.INTEGER,"idProv");  
            DataSourceField ctx = new DataSourceField("ctx", FieldType.INTEGER,"ctx");  
            DataSourceField denoProv = new DataSourceField("denoProv", FieldType.TEXT,"Privincia");
            DataSourceField idRegi = new DataSourceField("idRegi", FieldType.INTEGER,"idRegi");  
            DataSourceField denoRegi = new DataSourceField("denoRegi", FieldType.TEXT,"Regione");
            
            idProv.setHidden(true);
            idRegi.setHidden(true);
            ctx.setHidden(true);
            
            setFields(ctx,idProv,idRegi,denoProv,denoRegi);  
            url=egeosDCL.getURL_SERVER()+"catalogoProvinceService?numElenco="+numElenco;
            
            setDataURL(GWT.getModuleBaseURL()+"Proxy?u="+SguParam.enc(url));
            setRecordXPath("/TodoLista");
            
        }  
  
    }  
}
