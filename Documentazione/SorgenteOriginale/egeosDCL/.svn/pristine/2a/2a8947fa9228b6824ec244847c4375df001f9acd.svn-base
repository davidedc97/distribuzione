package it.gis.egeosDCL.client.Layout;

import it.gis.egeosDCL.client.Layout.LayoutObjects.MapToolStrip;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Overflow;
import com.smartgwt.client.widgets.HTMLPane;
import com.smartgwt.client.widgets.events.ResizedEvent;
import com.smartgwt.client.widgets.events.ResizedHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * CREA IL PANNELLO DELLA MAPPA
 * @version 1.0
 * 
 */
public class MapLayout {
	
			static HTMLPane htmlPanel 	= null;
	private static HLayout H_lay 		= null;
	private static VLayout V_lay 		= null;
	

	public static void create()
	{
		H_lay = new HLayout();
		V_lay = new VLayout();
		
		V_lay.setWidth100();
		V_lay.setHeight100();
		
		H_lay.setWidth100();  
		H_lay.setHeight100();
		H_lay.setShowEdges(true);
		
		htmlPanel = new HTMLPane();
		htmlPanel.setWidth100();
		htmlPanel.setID("ID_HTML_PANE");
		htmlPanel.setHeight100();
		htmlPanel.setZIndex(0);
		htmlPanel.setAlign(Alignment.CENTER);
		htmlPanel.setCanFocus(true);
		htmlPanel.setOverflow(Overflow.HIDDEN);
		
		htmlPanel.addResizedHandler(new ResizedHandler() {
			
			public void onResized(ResizedEvent event) {

				////System.out.println("limortacci tuaaaaa");
				//htmlPanel.resizeTo(htmlPanel.getWidth()+"px", htmlPanel.getHeight()+"px");
				//OLMap_new.resizePanel("map",htmlPanel.getHeight(), htmlPanel.getWidth()-10);
			}
		});
		V_lay.addMember(new MapToolStrip());
		V_lay.addMember(htmlPanel);
		H_lay.addMember(V_lay);
	
		htmlPanel.setContents("<div id='map' style =' width:" + (htmlPanel.getWidth()-5000) + "px; height:" + htmlPanel.getHeight() + "px'></div>");
		htmlPanel.fireEvent(new ResizedEvent(htmlPanel.getJsObj()));
		
	}

	public static HTMLPane getHtmlPanel() {
		return htmlPanel;
	}

	public void setHtmlPanel(HTMLPane htmlPanel) {
		this.htmlPanel = htmlPanel;
	}

	public static HLayout getH_lay() {
		return H_lay;
	}

	public static void setH_lay(HLayout h_lay) {
		H_lay = h_lay;
	}

	public static VLayout getV_lay() {
		return V_lay;
	}

	public static void setV_lay(VLayout v_lay) {
		V_lay = v_lay;
	}



}
