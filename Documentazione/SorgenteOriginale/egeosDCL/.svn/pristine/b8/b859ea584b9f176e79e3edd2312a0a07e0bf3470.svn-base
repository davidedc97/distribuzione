package it.gis.egeosDCL.client.Layout.LayoutObjects;

import com.google.gwt.user.client.ui.Widget;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.data.RecordList;
import com.smartgwt.client.util.StringUtil;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.layout.VLayout;

public class ExportableGrid extends ListGrid{

	
	private String exportTitle;
	private ExportForm form;
	
	public String toHtmlTable()
	{
		String ret = "<table><tr>";
		RecordList list = getDataAsRecordList();
		ListGridField columns[] = getFields();
		for(int i = 0; i < columns.length; i++)
			ret = (new StringBuilder()).append(ret).append("<th>").append(columns[i].getTitle()).append("</th>").toString();

		ret = (new StringBuilder()).append(ret).append("</tr>").toString();
		for(int j = 0; j < list.getLength(); j++)
		{
			Record rec = list.get(j);
			ret = (new StringBuilder()).append(ret).append("<tr>").toString();
			for(int i = 0; i < columns.length; i++)
				ret = (new StringBuilder()).append(ret).append("<td>").append(rec.getAttribute(columns[i].getName())).append("</td>").toString();

			ret = (new StringBuilder()).append(ret).append("</tr>").toString();
		}

		ret = (new StringBuilder()).append(ret).append("</table>").toString();
		return StringUtil.asHTML(ret);
	}
	
	public String toStringone()
	{
		String ret = "";
		RecordList list = getDataAsRecordList();
		ListGridField columns[] = getFields();
		for(int i = 0; i < columns.length; i++)
		{
			String sep = i != 0 ? "#" : "";
			ret = (new StringBuilder()).append(ret).append(sep).append(columns[i].getTitle()).append("").toString();
		}

		ret = (new StringBuilder()).append(ret).append("@").toString();
		for(int j = 0; j < list.getLength(); j++)
		{
			Record rec = list.get(j);
			String sepRighe = j != 0 ? "@" : "";
			ret = (new StringBuilder()).append(ret).append(sepRighe).toString();
			for(int i = 0; i < columns.length; i++)
			{
				String sep = i != 0 ? "#" : "";
				ret = (new StringBuilder()).append(ret).append(sep).append(rec.getAttribute(columns[i].getName())).append("").toString();
			}

			ret = (new StringBuilder()).append(ret).append("").toString();
		}
		if(exportTitle==null)
			exportTitle="export";
		return ret;
	}
	
	@Override
	public void destroy() {
		super.destroy();
		if(form!=null){
			form.destroy();
			}
	}
	public String getExportTitle() {
		return exportTitle;
	}

	public void setExportTitle(String exportTitle) {
		this.exportTitle = exportTitle;
	}

	public ExportForm getForm() {
		return form;
	}

	public void setForm(ExportForm form) {
		this.form = form;
	}


}
