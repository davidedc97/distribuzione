package it.gis.egeosDCL.client.Layout.LayoutObjects;

import it.gis.egeosDCL.client.Layout.LeftLayout;
import it.gis.egeosDCL.client.Layout.LayoutObjects.SearchByComboLayout;
import it.gis.egeosDCL.client.map.OLMap_new;

import com.google.gwt.user.datepicker.client.DateBox;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * CREA IL PANNELLO DI RICERCA
 * @version 1.0
 * 
 */
public class SearchByComboLayout extends VLayout{


	
	ListGrid provinceGrid = null;
	DynamicForm 				  	form_ricerca_per_chiave			= null;
	VLayout		  				    layout_ricerca_per_chiave		= null; 	
	public 	static		  			DateItem dateItem_inizio 		= null;
	public  VLayout 		 	 	dataLayout 						= new VLayout();
	IButton     				 	ricerca_bt						= null;
	WinLoading    				 	winLoad							= null;


	ListGridRegioniJSON           listRegJSON					= null;
	public  HiddenItem 	 		 codiNaziPagerHidden 			= null;
	public  CheckboxItem          checkSearchBy_chiave_catastale= null;
	
	DateBox						  dateBox						= null;

	public SearchByComboLayout()
	{

		this.setWidth100();


		ricerca_bt = new IButton("Ricerca");
		ricerca_bt.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				OLMap_new.destroyMap();
				if(LeftLayout.getvLayoutGridComuni().getMember("COMUNI_GRID_ID")!=null){
					LeftLayout.getvLayoutGridComuni().getMember("COMUNI_GRID_ID").destroy();
				}
				if(LeftLayout.getvLayoutGridProvince().getMember("PROVINCE_GRID_ID")!=null){
					LeftLayout.getvLayoutGridProvince().getMember("PROVINCE_GRID_ID").destroy();
					LeftLayout.getvLayoutGridProvince().getMember("ID_TOOLSTRIP_PROVINCE").destroy();
				}
				if(LeftLayout.getvLayoutGridRegioni().getMember("REGIONI_GRID_ID")!=null){
					LeftLayout.getvLayoutGridRegioni().getMember("REGIONI_GRID_ID").destroy();
					LeftLayout.getvLayoutGridRegioni().getMember("ID_TOOLSTRIP_REGIONI").destroy();
					listRegJSON = new ListGridRegioniJSON();
					LeftLayout.getvLayoutGridRegioni().addMember(listRegJSON);
					//System.out.println("CREO TOOLSTRIP");
					ToolStripButtonRegioni stripRegioni = new ToolStripButtonRegioni();
					LeftLayout.getvLayoutGridRegioni().addMember(stripRegioni);
				}
				if(LeftLayout.getvLayoutGridRegioni().getMember("REGIONI_GRID_ID")==null){
					listRegJSON = new ListGridRegioniJSON();
					LeftLayout.getvLayoutGridRegioni().addMember(listRegJSON);
					ToolStripButtonRegioni stripRegioni = new ToolStripButtonRegioni();
					LeftLayout.getvLayoutGridRegioni().addMember(stripRegioni);
				}

				
				
			}
		});

		this.setPadding(1);
		HLayout buttonTool 	= new HLayout();
		buttonTool.setAlign(Alignment.CENTER);
		buttonTool.setHeight(30);
		buttonTool.addMember(ricerca_bt);
		buttonTool.setTop(10);
		buttonTool.setMargin(5);

	}

	public static DateItem getDateItem_inizio() {
		return dateItem_inizio;
	}

	public static void setDateItem_inizio(DateItem dateItem_inizio) {
		SearchByComboLayout.dateItem_inizio = dateItem_inizio;
	}



}
