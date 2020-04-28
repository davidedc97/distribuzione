package it.gis.egeosDCL.client.Layout.LayoutObjects;

import it.gis.egeosDCL.client.egeosDCL;
import it.gis.egeosDCL.client.ctx.ContestiWindow;
import it.gis.egeosDCL.client.ctx.ContextListner;
import it.gis.egeosDCL.client.ctx.UserAssignWindow;
import it.gis.egeosDCL.client.ctx.WorkType;

import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.toolbar.ToolStrip;
import com.smartgwt.client.widgets.toolbar.ToolStripButton;

/**
 * CREA LA PULSANTIERA DELLA FINESTRA DI GESTIONE DEI CONTESTI
 * @version 1.0
 * 
 */
public class ToolStripButtonContesti extends ToolStrip {

	DynamicForm 		form 				= null;
	ToolStripButton  	btAddContesto		= null;
	ToolStripButton  	btAddProtoContext	= null;
	ListGridContestiJSON listGridContesti	= null;
	private ToolStripButton user_assign;


	public ToolStripButtonContesti(String livello)
	{
		super();

		btAddContesto = new ToolStripButton();
		btAddContesto.setIcon("new_rec.jpg");
		btAddContesto.setTooltip("Aggiungi un nuovo contesto");
		btAddContesto.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				ContestiWindow ctxWin = new ContestiWindow(new ContextListner() {
					
					public void contextRefresh() {
						// TODO Auto-generated method stub
						if(SetUserWindow.getGridContestiLayout().getMember("CONTESTI_GRID_ID")!=null)
						{
							SetUserWindow.getGridContestiLayout().getMember("CONTESTI_GRID_ID").destroy();
							listGridContesti = new ListGridContestiJSON();
							SetUserWindow.getGridContestiLayout().addMember(listGridContesti);
							//							if(egeosDCL.getLivelloRuolo().equals("1")){
							//								SetUserWindow.getGridContestiLayout().addMember(new ToolStripButtonContesti());
							//							}
						}
					}
				},WorkType.INSERT);
				ctxWin.show();				
			}
		});

		btAddProtoContext = new ToolStripButton();
		btAddProtoContext.setIcon("search_by_fogl_lavo.jpg");
		btAddProtoContext.setTooltip("Prototipo nuovo contesto(html)");
		btAddProtoContext.addClickHandler(new ClickHandler() {
			
			public void onClick(ClickEvent event) {
				com.google.gwt.user.client.Window.open("./contesti/dett.html", "contesti","resizable=yes," + 
						"scrollbars=yes," + 
						"width=700,"+
						"height=600");
			}
		});

		user_assign = new ToolStripButton();
		user_assign.setWidth(30);
		user_assign.setIcon("user.png");
		user_assign.setTooltip("Gestione Fotointerpreti");
		user_assign.addClickHandler(new ClickHandler() {

			
			public void onClick(ClickEvent event) {
				UserAssignWindow w = new UserAssignWindow();
				w.show();
			}
		});

		if(livello.equals("1"))
		{
			this.addButton(btAddContesto);
			this.addSeparator();
			this.addButton(btAddProtoContext);
			this.setWidth100();
		}
		if(livello.equals("3"))
			this.addButton(user_assign);
		this.show();
	}
}
