package it.gis.egeosDCL.client.Layout.LayoutObjects;

import it.gis.egeosDCL.client.egeosDCL;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.FileUpload;
import com.google.gwt.user.client.ui.FormPanel;
import com.google.gwt.user.client.ui.FormPanel.SubmitCompleteEvent;
import com.google.gwt.user.client.ui.NamedFrame;
import com.smartgwt.client.data.DSResponse;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Encoding;
import com.smartgwt.client.types.FormMethod;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.events.FormSubmitFailedEvent;
import com.smartgwt.client.widgets.form.events.FormSubmitFailedHandler;
import com.smartgwt.client.widgets.form.fields.UploadItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

public class WinFileUpload extends Window {

    private VLayout     main_layout = null;
    private IButton     BT_conferma = null;


    //private FormItem      descFile    = null;
    //private HiddenItem    descHidden  = null;
    //private Timer timer;
    WinLoading 			winLoad 				= new WinLoading("Caricamento in corso...");

    public WinFileUpload()
    {
        this.setTitle("Seleziona il file da caricare");
        this.setWidth(400);
        this.setHeight(200);
        this.setShowMinimizeButton(false);
        this.setIsModal(true);
        this.setShowCloseButton(true);
        this.setKeepInParentRect(true);
        this.setShowModalMask(true);
        this.centerInPage();
        this.setCanDragResize(true);
        BT_conferma = new IButton("Carica");
        main_layout = new VLayout();
        
        Label appo = new Label("Si consiglia di inserire i dati solo nei seguenti formati:numero testo o data ");
        
        main_layout.setMargin(10);
        main_layout.setHeight(80);
        main_layout.setAlign(Alignment.CENTER);
        String action = GWT.getModuleBaseURL() + "csvFileUpload";
        final FormPanel form = new FormPanel();
        form.setWidth("100%");
        form.setHeight("80px");
        FileUpload fu =  new FileUpload();
        fu.setName("Seleziona");
        fu.setTitle("Seleziona");
        fu.setWidth("320px");
        form.setAction(action);
        
        form.setEncoding(FormPanel.ENCODING_MULTIPART);
    	form.setMethod(FormPanel.METHOD_POST);
    	form.add(fu);
    	
    	form.addSubmitCompleteHandler(new FormPanel.SubmitCompleteHandler() {
    		public void onSubmitComplete(SubmitCompleteEvent event) {
    			
//    			System.out.println("arrivatooooooo"+event.getResults().substring(event.getResults().indexOf(">")+1,event.getResults().indexOf("</pre>")));
    			String response_string = event.getResults().substring(event.getResults().indexOf(">")+1,event.getResults().indexOf("</pre>"));
//    			String resp_mess = event.getResults();
//    			String mess = "";
    			if(response_string.contains("TodoLista"))
    			{
    				winLoad.destroy();
    				SC.say(egeosDCL.fireMessage(response_string));
    			}
    			else if(response_string.contains("non di tipo")){
    				winLoad.destroy();
    				SC.say(response_string);
    			}
    			else
    			{
    				if(response_string.equals("")){
    					winLoad.destroy();
    					SC.say(response_string+"tutto a posto");
    				}
    				else{
    					winLoad.destroy();
    					SC.say(response_string);
    				}
    				
    			}
    		}
    	});
        
    	BT_conferma.addClickHandler(new ClickHandler() {
			
			
			public void onClick(ClickEvent event) {
				winLoad.show();
				form.submit();				
			}
		});

  
    	VLayout taccituia = new VLayout();
    	taccituia.setWidth100();
    	taccituia.addChild(form);

    	taccituia.setHeight("50px");
    	
    	VLayout lay_bt = new VLayout();
    	lay_bt.setWidth100();
    	lay_bt.setAlign(Alignment.CENTER);
    	

    	main_layout.addMember(taccituia);
    	main_layout.setAlign(Alignment.CENTER);
    	
    	main_layout.setAlign(VerticalAlignment.CENTER);

    	lay_bt.setHeight("80px");

    	lay_bt.addMember(BT_conferma);
    	main_layout.addMember(lay_bt);
    	this.setAlign(Alignment.CENTER);
        this.addItem(main_layout);
        this.show();
    }
}
