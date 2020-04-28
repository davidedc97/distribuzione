package it.gis.egeosDCL.client.Layout.LayoutObjects;



import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.HLayout;

	/**
	 * CREA LA FINESTRA DI CARICAMENTO
	 * @version 1.0
	 * 
	 */
	public class WinLoading extends Window {
		
		
		
		public WinLoading()
		{
			this("Loading...");
		}
		
		public WinLoading(String testo)
		{
			super();
			setShowFooter(false);
			setShowHeader(false);
			setShowEdges(false);
			setWidth(250);
			//setAutoSize(true);
	        setKeepInParentRect(true);
	        setShowMinimizeButton(false);  
	        setIsModal(true);
	        setShowCloseButton(false);
	        setShowModalMask(true);  
	        centerInPage();  
	        setOpacity(70);
	        setBackgroundColor("#FFFFFF");
	        HLayout content = new HLayout();
	        content.setAlign(Alignment.CENTER);
	        content.setMembersMargin(10);
	        Label label = new Label();  
	        label.setHeight100();
	        label.setWidth100();
	        label.setPadding(10);  
	        label.setAlign(Alignment.CENTER);  
	        label.setValign(VerticalAlignment.CENTER);  
	        label.setWrap(false);  
	        label.setIcon("load.gif");  
	        label.setShowEdges(true);  
	        label.setContents("<b>"+testo+"</b>");  
	        
	        content.addMember(label);
	        
	        addItem(content);
	       
		}
		@Override
		public void show() {
			bringToFront();
			super.show();
		}

	}

