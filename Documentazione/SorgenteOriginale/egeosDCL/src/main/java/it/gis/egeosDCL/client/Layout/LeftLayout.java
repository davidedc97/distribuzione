package it.gis.egeosDCL.client.Layout;


import it.gis.egeosDCL.client.Layout.LayoutObjects.ExportForm;
import it.gis.egeosDCL.client.Layout.LayoutObjects.ExportableGrid;
import it.gis.egeosDCL.client.Layout.LayoutObjects.WinLoading;

import java.util.LinkedHashMap;

import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.datepicker.client.DateBox;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.HTMLFlow;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.CheckboxItem;
import com.smartgwt.client.widgets.form.fields.DateItem;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * CREA IL PANNELLO DI SINISTRA
 * @version 1.0
 * 
 */
public class LeftLayout extends VLayout{

	DynamicForm 				 form_ricerca   				= null;
	static SelectItem  			 combo_regioni  				= null;
	SelectItem  				 combo_province 				= null;
	SelectItem  				 combo_comuni 					= null;
	static SelectItem  			 combo_fogli 					= null;
	IButton     				 ricerca_bt						= null;
	LinkedHashMap<String,String> mapRegioni						= null;
	LinkedHashMap<String,String> mapProvince					= null;
	LinkedHashMap<String,String> mapComuni						= null;
	LinkedHashMap<String,String> mapFogli						= null;
	WinLoading    				 winLoad						= null;
	public static VLayout 		 gridLayout 					= null;
	public static HiddenItem 	 codiNaziHidden 				= null; 
	public static HiddenItem 	 codiNaziPagerHidden 			= null;
	public static VLayout 		 dataLayout 					= new VLayout();

	//CAMPI PER LA RICERCA PER CHIAVE CATASTALE
	public static CheckboxItem    checkSearchBy_chiave_catastale= null;
	DynamicForm 				  form_ricerca_per_chiave		= null;
	VLayout		  				  layout_ricerca_per_chiave		= null; 			
	TextItem					  codi_nazi						= null;
	TextItem					  foglio						= null;
	TextItem					  particella					= null;
	TextItem					  subalterno					= null;
	TextItem					  allegato						= null;
	TextItem					  sviluppo						= null;
	DateBox						  dateBox						= null;
	public static				  DateItem dateItem_inizio 		= null;
	public static				  DateItem dateItem_fine 		= null;
	public static String 			codiNaziPerPaginazione		= "";
	public static				 VLayout  containerLayout		= null;
	
	public static				 VLayout  vLayoutGridRegioni		= null;
	public static				 VLayout  vLayoutGridProvince	= null;
	public static				 VLayout  vLayoutGridComuni		= null;
	public static				 VLayout  vLayoutGridQuadranti		= null;
	
	public static 				HTMLFlow 	quadri_legendPanel = null;
	
	public static int 			 numeroFogliLavoro				= 0;
	
	
	
	public LeftLayout()
	{
		super();
		
		final SectionStack sectionStack = new SectionStack();  
        sectionStack.setVisibilityMode(VisibilityMode.MULTIPLE);  
        sectionStack.setWidth(300);  
        sectionStack.setHeight(350);  
  
        SectionStackSection section1 = new SectionStackSection("Legenda");  
        section1.setExpanded(false);  
        section1.setResizeable(false);  
       
        SectionStackSection section2 = new SectionStackSection("Dati");  
        section2.setExpanded(true);  
        section2.setCanCollapse(true);  
  
        sectionStack.setWidth100();
        
		
		HLayout buttonTool 	= new HLayout();
		codiNaziHidden 		= new HiddenItem();
		codiNaziPagerHidden	= new HiddenItem();
		containerLayout		= new VLayout();
		gridLayout			= new VLayout();
		
		vLayoutGridRegioni = new VLayout()
		{
			@Override
			public void addMember(Canvas component) {
				
				super.addMember(component);
				if(component instanceof ExportableGrid)
				{
					ExportableGrid grid = (ExportableGrid) component;
					ExportForm form = grid.getForm()==null? new ExportForm(grid):grid.getForm();
					addMember(form);
					grid.setForm(form);
					
				}
			}
		};
		vLayoutGridProvince = new VLayout();
		vLayoutGridComuni = new VLayout();
		vLayoutGridQuadranti = new VLayout();

		try
		{
			vLayoutGridRegioni.setHeight("50%");
			
			this.setWidth("35%");
			this.setShowEdges(true);

			
			HLayout legendsLay = new HLayout();
			
			legendsLay.addMember(map_legendLayout());
			
			quadri_legendPanel = new HTMLFlow();
			quadri_legendPanel.setWidth("50%");
			
			legendsLay.addMember(quadri_legendPanel);
			
			section1.addItem(legendsLay);
			
			sectionStack.setHeight100();
			sectionStack.addSection(section1);
			gridLayout.setHeight100();
	        section2.addItem(gridLayout);
	        
	        sectionStack.addSection(section2);
	        
			gridLayout.setHeight100();
			gridLayout.setWidth100();
			gridLayout.addMember(vLayoutGridRegioni);

			//this.addMember(tabSet_);
			this.addMember(sectionStack);
			

		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	/**
	 * CREA IL PANNELLO DELLA LEGENDA
	 * @return
	 */
	public VLayout map_legendLayout()
	{
		VLayout legend_lay = new VLayout();
		VLayout color_lay = new VLayout();
		
		DynamicForm appoForm = new DynamicForm();
		appoForm.setNumCols(2);
		
		Label lab_color1= new Label();
		lab_color1.setContents("VALIDATE:");
		lab_color1.setHeight(20);
		
		Label color1= new Label();
		color1.setHeight(10);
		color1.setBackgroundColor("#408747");
		color1.setWidth(30);
		color1.setBorder("1px solid black");
		
		Label lab_color2= new Label();
		lab_color2.setHeight(20);
		lab_color2.setContents("PARZIALMENTE LAVORATE:");
		
		Label color2= new Label();
		color2.setHeight(10);
		color2.setBorder("1px solid black");
		color2.setBackgroundColor("#FF0000");
		color2.setWidth(30);
		
		Label lab_color3= new Label();
		lab_color3.setHeight(20);
		lab_color3.setContents("TOTALMENTE ASSEGNATE:");
		
		Label color3= new Label();
		color3.setHeight(10);
		color3.setBorder("1px solid black");
		color3.setBackgroundColor("#3835FF");
		color3.setWidth(30);
		
		Label lab_color4= new Label();
		lab_color4.setHeight(20);
		lab_color4.setContents("PARZIALMENTE ASSEGNATE:");
		
		Label color4= new Label();
		color4.setHeight(10);
		color4.setBackgroundColor("#FFEF42");
		color4.setBorder("1px solid black");
		color4.setWidth(30);
		
		Label lab_color5= new Label();
		lab_color5.setHeight(20);
		lab_color5.setContents("NON ASSEGNATE:");
		
		Label color5= new Label();
		color5.setHeight(10);
		color5.setBorder("1px solid black");
		color5.setBackgroundColor("#FFFFFF");
		color5.setWidth(30);
		
		legend_lay.setMargin(10);
		legend_lay.addMember(lab_color1);
		legend_lay.addMember(color1);
//		legend_lay.addMember(lab_color2);
//		legend_lay.addMember(color2);
		legend_lay.addMember(lab_color3);
		legend_lay.addMember(color3);
		legend_lay.addMember(lab_color4);
		legend_lay.addMember(color4);
		legend_lay.addMember(lab_color5);
		legend_lay.addMember(color5);
	//	legend_lay.setBackgroundColor("green");
		
		legend_lay.setWidth("50%");
		
		return legend_lay;
	}


	public static VLayout getGridLayout() {
		return gridLayout;
	}

	public static void setGridLayout(VLayout gridLayout) {
		LeftLayout.gridLayout = gridLayout;
	}

	public static SelectItem getCombo_fogli() {
		return combo_fogli;
	}

	public static void setCombo_fogli(SelectItem combo_fogli) {
		LeftLayout.combo_fogli = combo_fogli;
	}

	public static HiddenItem getCodiNaziHidden() {
		return codiNaziHidden;
	}

	public static void setCodiNaziHidden(HiddenItem codiNaziHidden) {
		LeftLayout.codiNaziHidden = codiNaziHidden;
	}

	public static HiddenItem getCodiNaziPagerHidden() {
		return codiNaziPagerHidden;
	}

	public static void setCodiNaziPagerHidden(HiddenItem codiNaziPagerHidden) {
		LeftLayout.codiNaziPagerHidden = codiNaziPagerHidden;
	}

	public static DateItem getDateItem_inizio() {
		return dateItem_inizio;
	}

	public static void setDateItem_inizio(DateItem dateItem_inizio) {
		LeftLayout.dateItem_inizio = dateItem_inizio;
	}

	public static DateItem getDateItem_fine() {
		return dateItem_fine;
	}

	public static void setDateItem_fine(DateItem dateItem_fine) {
		LeftLayout.dateItem_fine = dateItem_fine;
	}

	public static SelectItem getCombo_regioni() {
		return combo_regioni;
	}

	public static void setCombo_regioni(SelectItem combo_regioni) {
		LeftLayout.combo_regioni = combo_regioni;
	}

	public static VLayout getContainerLayout() {
		return containerLayout;
	}

	public static void setContainerLayout(VLayout containerLayout) {
		LeftLayout.containerLayout = containerLayout;
	}

	public static int getNumeroFogliLavoro() {
		return numeroFogliLavoro;
	}

	public static void setNumeroFogliLavoro(int numeroFogliLavoro) {
		LeftLayout.numeroFogliLavoro = numeroFogliLavoro;
	}

	public static String getCodiNaziPerPaginazione() {
		return codiNaziPerPaginazione;
	}

	public static void setCodiNaziPerPaginazione(String codiNaziPerPaginazione) {
		LeftLayout.codiNaziPerPaginazione = codiNaziPerPaginazione;
	}

	public static VLayout getvLayoutGridRegioni() {
		return vLayoutGridRegioni;
	}

	public static void setvLayoutGridRegioni(VLayout vLayoutGridRegioni) {
		LeftLayout.vLayoutGridRegioni = vLayoutGridRegioni;
	}

	public static VLayout getvLayoutGridProvince() {
		return vLayoutGridProvince;
	}

	public static void setvLayoutGridProvince(VLayout vLayoutGridProvince) {
		LeftLayout.vLayoutGridProvince = vLayoutGridProvince;
	}

	public static VLayout getvLayoutGridComuni() {
		return vLayoutGridComuni;
	}

	public static void setvLayoutGridComuni(VLayout vLayoutGridComuni) {
		LeftLayout.vLayoutGridComuni = vLayoutGridComuni;
	}

	public static VLayout getvLayoutGridQuadranti() {
		return vLayoutGridQuadranti;
	}

	public static void setvLayoutGridQuadranti(VLayout vLayoutGridQuadranti) {
		LeftLayout.vLayoutGridQuadranti = vLayoutGridQuadranti;
	}

	public static HTMLFlow getQuadri_legendPanel() {
		return quadri_legendPanel;
	}

	public static void setQuadri_legendPanel(HTMLFlow quadri_legendPanel) {
		LeftLayout.quadri_legendPanel = quadri_legendPanel;
	}









}
