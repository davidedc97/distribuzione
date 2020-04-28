package it.gis.egeosDCL.client.Layout.LayoutObjects;


import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.NamedFrame;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.Encoding;
import com.smartgwt.client.types.FormMethod;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.FormItem;
import com.smartgwt.client.widgets.form.fields.UploadItem;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * 
 * @version 1.0 
 *
 */
public class SearchByFogliLavoLayout extends VLayout{

	HLayout         gestioneFogliLay 	= null;
	IButton 		downloadTemplate_bt = null;
	IButton 		uploadTemplate_bt 	= null;
	DynamicForm		formUpload			= null;
	DynamicForm		formDownload		= null;
	WinFileUpload	fileUploadWin		= null;
	UploadItem  	uploadItem			= null;
	FormItem    	descFile			= null;
	IButton     	BT_conferma			= null;
	String 			action 				= "";
	VLayout 		uploadLay 			= null;
	HLayout			layButton			= null;
	
	public SearchByFogliLavoLayout()
	{
		HLayout layBt_download = new HLayout();
		layButton = new HLayout();
		
		gestioneFogliLay  = new HLayout();
		gestioneFogliLay.setWidth100();
		gestioneFogliLay.setMargin(20);
		
		NamedFrame iframe = new NamedFrame("uploadFrameCSV");
		iframe.setWidth("1px");
		iframe.setHeight("1px");
		iframe.setVisible(false);
		
		downloadTemplate_bt = new IButton("Scarica template");	
		
		
		
		uploadLay  = new VLayout();
		uploadItem = new UploadItem("Seleziona");
		descFile   = new FormItem("Descrizione");
		descFile.setName("Descrizione");

		BT_conferma= new IButton("Carica");
		
		layButton.addMember(downloadTemplate_bt);
		layButton.addMember(BT_conferma);
		layButton.setMargin(10);
		layButton.setPadding(10);
		layButton.setAlign(Alignment.CENTER);
		
		formUpload  = new DynamicForm();
		formUpload.setEncoding(Encoding.MULTIPART);
		formUpload.setMethod(FormMethod.POST);
		
		action = GWT.getModuleBaseURL()+"csvFileUpload";

		formUpload.setAction(action);
		formUpload.setGroupTitle("Upload file");
		formUpload.setIsGroup(true);
		formUpload.setTarget("uploadFrameCSV");
		formUpload.setAlign(Alignment.LEFT);
		formUpload.setItems(descFile,uploadItem);
		
		uploadLay.setAlign(Alignment.CENTER);
		uploadLay.addMember(formUpload);

		
		
		BT_conferma.addClickHandler(new ClickHandler() {

			public void onClick(ClickEvent event) {
				//System.out.println("ACTION DEL FORM: "+action);

				formUpload.submitForm();
			}
		});
		
		uploadLay.setAlign(Alignment.LEFT);
		uploadLay.setPadding(4);
		uploadLay.setMargin(4);
		uploadLay.addMember(iframe);

		downloadTemplate_bt.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				//OLMap.openUrl(GWT.getModuleBaseURL() + "download/templateElencoFogliLavorazione.csv");
			}
		});
		
		
		this.addMember(uploadLay);
		this.addMember(layButton);
		this.addMember(gestioneFogliLay);
		
		
	}
}
