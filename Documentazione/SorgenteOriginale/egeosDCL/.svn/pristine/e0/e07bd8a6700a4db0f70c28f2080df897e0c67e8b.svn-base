package it.gis.egeosDCL.client.utility;



import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.CloseClickEvent;
import com.smartgwt.client.widgets.events.CloseClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;

public class WinLoading {
	
	final String pathImg = "";
	private static Window winLoading = null;
	
	public void create(String testo)
	{
		if (winLoading != null) {
			winLoading.destroy();
		}
		
		winLoading = new Window();
		

		winLoading.setShowFooter(false);
		winLoading.setShowHeader(false);
		winLoading.setShowEdges(false);
		winLoading.setBackgroundColor("white");
		winLoading.setWidth(230);  
        winLoading.setHeight(110);
        winLoading.setKeepInParentRect(true);
        winLoading.setShowMinimizeButton(false);  
        winLoading.setIsModal(true);
        winLoading.setShowCloseButton(false);
        winLoading.setShowModalMask(true);  
        winLoading.centerInPage();  
        winLoading.addCloseClickHandler(new CloseClickHandler() {  
            //@Override
			public void onCloseClick(CloseClickEvent event) {
				winLoading.destroy(); 
				
			}  
        });
        
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
        label.draw();  

        content.addMember(label);
        
        winLoading.addItem(content);
        winLoading.show();
        
	}
	
	public void delete()
	{
		if (winLoading != null) {
			winLoading.destroy();
		}
	}
	
	

	public static void setWinLoading(Window winLoading) {
		WinLoading.winLoading = winLoading;
	}

	public static Window getWinLoading() {
		return winLoading;
	}

}
