package it.gis.egeosDCL.client.ctx;

import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.layout.VLayout;

public class UserAssignWindow extends Window {

	private UserAssignGrid grid;
	private VLayout content;
	
	public UserAssignWindow()
	{
		super();
		setShowMinimizeButton(false);
		setTitle("Gestione Fotointerpreti");
		setAutoCenter(true);
		setAutoSize(true);
		setIsModal(true);
		setShowModalMask(true);
		setModalMaskOpacity(40);
		
		content = new VLayout(0);
		grid = new UserAssignGrid();
		
		content.addMember(grid);
		addItem(content);
		
	}
	
}
