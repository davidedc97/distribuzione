package it.gis.egeosDCL.client.Layout.LayoutObjects;

import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * CREA IL PANNELLO DI DETTAGLIO DEL COMUNE
 * @version 1.0
 * 
 */
public class DetailComuPanel extends VLayout{

	

		private static VLayout 		    layButton 		= null;
		private static VLayout 		    layGrid 		= null;
		private static ListGrid 		part 			= null;
		private static ListGrid 		part_appo;
		
		
		public DetailComuPanel()
		{
			super();
			layGrid  		= new VLayout();
			
			part = new ListGrid();

			this.addMember(layGrid);
			this.setWidth100();
			this.setHeight("30%");

			this.setCanDragResize(true);
			this.setShowEdges(true);
			
		}


		public static VLayout getLayButton() {
			return layButton;
		}


		public static void setLayButton(VLayout layButton) {
			DetailComuPanel.layButton = layButton;
		}


		public static VLayout getLayGrid() {
			return layGrid;
		}


		public static void setLayGrid(VLayout layGrid) {
			DetailComuPanel.layGrid = layGrid;
		}


		public static ListGrid getPart() {
			return part;
		}


		public static void setPart(ListGrid part) {
			DetailComuPanel.part = part;
		}


		public static ListGrid getPart_appo() {
			return part_appo;
		}


		public static void setPart_appo(ListGrid part_appo) {
			DetailComuPanel.part_appo = part_appo;
		}


}
