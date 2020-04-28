package it.gis.egeosDCL.client.Layout.LayoutObjects;

import it.gis.egeosDCL.client.Layout.LeftLayout;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * CREA LA PULSANTIERA DEL PANNELLO DI SINISTRA
 * @version 1.0
 * 
 */
public class LeftPanelToolStrip extends ToolStrip{
	
	private static ToolStripButton 	caricaFogliDiLavorazione_bt;
	private static ToolStripButton 	visualizzaPanRicerca_bt;
	private static ToolStripButton 	winUpload_bt;
	
	public LeftPanelToolStrip()
	{
		super();
		this.setWidth100();
		this.setHeight("30px");
		this.setMembersMargin(10);
		
		caricaFogliDiLavorazione_bt = new ToolStripButton();
		caricaFogliDiLavorazione_bt.setIcon("search_form.jpg");
		caricaFogliDiLavorazione_bt.setTooltip("Carica particelle da elenco di lavorazione");
		caricaFogliDiLavorazione_bt.setWidth("25px");
		caricaFogliDiLavorazione_bt.setHeight("30px");
		
		visualizzaPanRicerca_bt = new ToolStripButton();
		visualizzaPanRicerca_bt.setIcon("search_by_fogl_lavo.jpg");
		visualizzaPanRicerca_bt.setTooltip("Apri form di ricerca delle particelle");
		visualizzaPanRicerca_bt.setWidth("30px");
		visualizzaPanRicerca_bt.setHeight("30px");
		
		winUpload_bt = new ToolStripButton();
		winUpload_bt.setIcon("upload_csv.jpg");
		winUpload_bt.setTooltip("Apri gestione file upload");
		winUpload_bt.setWidth("25px");
		winUpload_bt.setHeight("30px");
		
		winUpload_bt.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				
			}
		});
		
		visualizzaPanRicerca_bt.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				SearchByComboLayout sbcl = new SearchByComboLayout();
				sbcl.draw();
				LeftLayout.getContainerLayout().addMember(sbcl);
			}
		});
		
		
		caricaFogliDiLavorazione_bt.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				
			}
		});
		
		this.addButton(winUpload_bt);
		this.addButton(caricaFogliDiLavorazione_bt);
		this.addButton(visualizzaPanRicerca_bt);
		
	}

}
