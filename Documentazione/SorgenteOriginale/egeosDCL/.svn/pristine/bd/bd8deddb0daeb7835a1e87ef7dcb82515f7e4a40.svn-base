package it.gis.egeosDCL.client.Layout.LayoutObjects;

import com.google.gwt.user.client.Timer;

import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * CREA LA FINESTRA DI VALIDAZIONE
 * @version 1.0
 * 
 */
public class ValidateWindow extends Window{

	HLayout mainLayout		 		= null;
	private static VLayout riepilogoProvinciaLay 	= null;
	private static VLayout riepilogoUtenti 			= null;
	private static RiepilogoComuniProvListGrid     listGridRiepProv = null;
	private static RiepilogoUtentiProvListGrid     listGridUsersProv= null;
	public Window win = new Window();
	int i = 0;
	
	public ValidateWindow()
	{
		
		super();
		win = this;
		this.setTitle("Validazione della provincia - "+ListGridContestiJSON.getComboProvince().getDisplayValue().toString());
		this.setWidth(700);
		this.setHeight(500);
		this.setKeepInParentRect(true);
		this.setMargin(10);
		this.setCanDrag(true);
		this.setCanDragResize(false);
		this.setCanDragReposition(false);
		this.setIsModal(true);
		this.setShowMinimizeButton(false);
		this.setShowCloseButton(true);
		this.setShowModalMask(true);  
		this.centerInPage();  

		mainLayout = new HLayout();

		riepilogoProvinciaLay 	= new VLayout();
		riepilogoProvinciaLay.setShowEdges(true);

		riepilogoUtenti			= new VLayout();
		riepilogoUtenti.setShowEdges(true);

		listGridRiepProv = new RiepilogoComuniProvListGrid(ListGridContestiJSON.getIdProvinciaRiepilogo(),null);
		riepilogoProvinciaLay.addMember(new ExportForm(listGridRiepProv));
		riepilogoProvinciaLay.addMember(listGridRiepProv);
		riepilogoProvinciaLay.addMember(new ToolStripButtonRiepComu(this));


		mainLayout.addMember(riepilogoProvinciaLay);

//		Timer t = new Timer() {
//			
//			public void run() {
//				i++;
//				listGridUsersProv = new RiepilogoUtentiProvListGrid(ListGridContestiJSON.getIdProvinciaRiepilogo(), "");
//				riepilogoUtenti.addMember(listGridUsersProv);
//				riepilogoUtenti.addMember(new ToolStripButtonRiepUsers(win));
//				mainLayout.addMember(riepilogoUtenti);
//				if(i>0)
//					cancel();
//			}
//		};
//		t.scheduleRepeating(300); 


		this.addItem(mainLayout);

		this.addCloseClickHandler(new CloseClickHandler() {

			
			public void onCloseClick(CloseClickEvent event) {
				// TODO Auto-generated method stub
				destroy();
			}
		});


	}



	public void closeWindow()
	{
		destroy();
	}




	public static VLayout getRiepilogoProvinciaLay() {
		return riepilogoProvinciaLay;
	}




	public static void setRiepilogoProvinciaLay(VLayout riepilogoProvinciaLay) {
		ValidateWindow.riepilogoProvinciaLay = riepilogoProvinciaLay;
	}




	public static VLayout getRiepilogoUtenti() {
		return riepilogoUtenti;
	}




	public static void setRiepilogoUtenti(VLayout riepilogoUtenti) {
		ValidateWindow.riepilogoUtenti = riepilogoUtenti;
	}








	public static RiepilogoComuniProvListGrid getListGridRiepProv() {
		return listGridRiepProv;
	}








	public static void setListGridRiepProv(
			RiepilogoComuniProvListGrid listGridRiepProv) {
		ValidateWindow.listGridRiepProv = listGridRiepProv;
	}








	public static RiepilogoUtentiProvListGrid getListGridUsersProv() {
		return listGridUsersProv;
	}








	public static void setListGridUsersProv(
			RiepilogoUtentiProvListGrid listGridUsersProv) {
		ValidateWindow.listGridUsersProv = listGridUsersProv;
	}

}
