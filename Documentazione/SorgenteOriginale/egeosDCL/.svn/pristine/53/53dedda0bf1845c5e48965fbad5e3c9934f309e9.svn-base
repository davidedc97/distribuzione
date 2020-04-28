package it.gis.egeosDCL.client.Layout.LayoutObjects;



import java.util.LinkedHashMap;

import com.google.gwt.core.client.GWT;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.FormMethod;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.HiddenItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.SubmitItem;
import com.smartgwt.client.widgets.form.fields.events.ClickEvent;
import com.smartgwt.client.widgets.form.fields.events.ClickHandler;

public class ExportForm extends DynamicForm {

	private HiddenItem html_field;
	private HiddenItem title_field;

	public ExportForm(final ExportableGrid grid) 
	{
		super();
		setID("form_"+grid.getID());

		setAction(GWT.getModuleBaseURL()+"exportGrid");
		if(!SC.isIE())
			setTarget("_blank");
		setMethod(FormMethod.POST);
		setWidth100();
		setNumCols(8);
		setCanSubmit(true);
		
		final HiddenItem format = new HiddenItem("format");
		html_field = new HiddenItem("content");
		title_field = new HiddenItem("title");
		
		SubmitItem export = new SubmitItem("submit_form","Esporta");
		export.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				
				html_field.setValue(grid.toStringone());
				title_field.setValue(grid.getExportTitle());
				format.setValue("application/vnd.ms-excel");
			}
		});
		export.setIcon("excel.png");
		export.setStartRow(false);
		export.setEndRow(true);
		export.setColSpan(2);

		setItems(format,export,html_field,title_field);
	
		
	}
	
	public HiddenItem getHtml_field() {
		return html_field;
	}

	public void setHtml_field(HiddenItem html_field) {
		this.html_field = html_field;
	}
}
