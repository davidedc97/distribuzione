package it.gis.egeosDCL.client.Layout.LayoutObjects;

import java.util.LinkedHashMap;

import it.gis.egeosDCL.client.egeosDCL;
import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONString;
import com.google.gwt.json.client.JSONValue;
import com.smartgwt.client.rpc.RPCCallback;
import com.smartgwt.client.rpc.RPCManager;
import com.smartgwt.client.rpc.RPCRequest;
import com.smartgwt.client.rpc.RPCResponse;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.TitleOrientation;
import com.smartgwt.client.types.VerticalAlignment;
import com.smartgwt.client.util.SC;
import com.smartgwt.client.widgets.Canvas;
import com.smartgwt.client.widgets.IButton;
import com.smartgwt.client.widgets.Window;
import com.smartgwt.client.widgets.events.ClickEvent;
import com.smartgwt.client.widgets.events.ClickHandler;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.ButtonItem;
import com.smartgwt.client.widgets.form.fields.PasswordItem;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.form.fields.events.ChangeEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangeHandler;
import com.smartgwt.client.widgets.form.fields.events.ChangedEvent;
import com.smartgwt.client.widgets.form.fields.events.ChangedHandler;
import com.smartgwt.client.widgets.form.fields.events.KeyDownEvent;
import com.smartgwt.client.widgets.form.fields.events.KeyDownHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * CREA LA FINESTRA DI GESTIONE DEGLI UTENTI
 * @version 1.0
 * 
 */
public class SetUserWindow extends Window{

	JSONArray 				arr 					= null;
	DynamicForm 			form 					= null;
	DynamicForm				formLDAP				= null;
	SelectItem 				selectLDAP				= null; 
	TextItem 				userItem				= null;
	PasswordItem 			passItem   				= null;
	static IButton 		 	bt_conferma 			= null;
	VLayout 				windowLayout 			= new VLayout();
	HLayout 				formLayout  		 	= new HLayout();
	HLayout 				ldapLayout  		 	= new HLayout();
	HLayout 				btLayout   				= new HLayout();
	public static VLayout 	gridContestiLayout   	= new VLayout();
	public static ListGridContestiJSON 	listGridContesti 		= null;
	ListGridRegioniJSON  	listRegJSON 			= null;
	ListGridProvinceJSON  	listProvJSON 			= null;
	ListGridComuniJSON 		listGridcomuni 			= null;
	String 					numero_elenco 			= "";
	WinLoading 				winLoad = new WinLoading("Elaborazione in corso..."); 
	String appoRuolo								= "";
	static HLayout appo = new HLayout();

	public SetUserWindow()
	{
		super();
		this.setTitle("Elenco Contesti");
		this.setWidth(650);
		this.setHeight(500);
		this.setKeepInParentRect(true);

		this.setMargin(10);
		this.setCanDrag(false);
		this.setCanDragResize(false);
		this.setCanDragReposition(false);
		this.setIsModal(true);
		this.setShowMinimizeButton(false);
		this.setShowCloseButton(false);
		this.setShowModalMask(true);  
		this.centerInPage();  

		formLayout.setHeight("20%");
		formLayout.setTop(50);

		ldapLayout.setHeight("20%");
		ldapLayout.setTop(50);

		btLayout.setHeight("10%");
		gridContestiLayout.setHeight("70%");

		form = new DynamicForm();
		formLDAP = new DynamicForm();


		userItem = new TextItem("Utente");
		passItem = new PasswordItem("Password");
		userItem.setCanFocus(true);

		selectLDAP = new SelectItem("Ruolo");

		selectLDAP.addChangedHandler(new ChangedHandler() {

			
			public void onChanged(ChangedEvent event) {
				// TODO Auto-generated method stub

				// TODO Auto-generated method stub
				int index;
				System.out.println("ChangeHandler - "+event.getItem().getDisplayValue());
				index=Integer.parseInt(""+event.getValue());

				for (int i = 0; i < arr.size(); i++) {
					JSONObject ob = arr.get(i).isObject();
					if(ob!=null)
					{

						if(event.getItem().getDisplayValue().equalsIgnoreCase(ob.get("desc_ruolo").toString().replace("\"","")))
						{
							JSONString str = ob.get("desc_utente").isString();

							egeosDCL.setDescUtente(""+ob.get("desc_utente").toString().replace("\"",""));
							egeosDCL.setIdUtente(""+ob.get("id_utente").toString().replace("\"",""));
							egeosDCL.setRuolo(""+ob.get("desc_ruolo").toString().replace("\"",""));
							egeosDCL.setLivelloRuolo(""+ob.get("livello_ruolo").toString().replace("\"",""));
							egeosDCL.setOrganismo(""+ob.get("organismo").toString().replace("\"",""));
							if(ob.get("desc_azie")!=null)
								egeosDCL.setDescAzie(""+ob.get("desc_azie").toString().replace("\"",""));

							if(egeosDCL.getDescAzie().equalsIgnoreCase("null")||egeosDCL.getDescAzie().equalsIgnoreCase(""))
								egeosDCL.setDescAzie(null);
							MapToolStrip.getUtente_connesso().setContents("Utente:<b>"+egeosDCL.getDescUtente()+"</b>");

							ldapLayout.hide();
							maVaiGO();
						}



					}
				}
			}
		});

		passItem.addKeyDownHandler(new KeyDownHandler() {

			
			public void onKeyDown(KeyDownEvent event) {
				// TODO Auto-generated method stub
				//	//System.out.println("TASTO PREMUTO: "+event.getKeyName());
				if(event.getKeyName().equals("Enter"))
				{
					////System.out.println("ENTRATOOOOOOOOOOOOOO");
					ClickEvent ev = new ClickEvent(bt_conferma.getJsObj());
					bt_conferma.fireEvent(ev);
				}
			}
		});


		form.setFields(userItem,passItem);
		form.setTop(50);

		formLDAP.setFields(selectLDAP);

		formLayout.setAlign(Alignment.CENTER);
		ldapLayout.setAlign(Alignment.CENTER);
		btLayout.setAlign(Alignment.CENTER);

		formLayout.addMember(form);
		ldapLayout.addMember(formLDAP);
		ldapLayout.hide();

		bt_conferma = new IButton("CONFERMA");
		bt_conferma.addClickHandler(new ClickHandler(){
			
			public void onClick(ClickEvent event){

				RPCRequest request = new RPCRequest();
				// Note data could be a String, Map or Record
				request.setData("Some data to send to the client");
				request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=utentiRuoliRefresh&descUtenza="+userItem.getValueAsString());

				RPCManager.sendRequest(request, 
						new RPCCallback () {
					public void execute(RPCResponse response, Object rawData, RPCRequest request) {
						//SC.say("Response from the server:" + rawData);
						JSONValue value = JSONParser.parseLenient(rawData+"");

						System.out.println(value.toString());	

						if(value.isObject()!=null)
						{
							JSONObject obj = value.isObject();
							JSONValue app = obj.get("TodoLista");
							arr = app.isArray();

							if(arr!=null)
							{
								System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>><<"+arr.size());
								if(arr.size()==1){
									
									JSONObject ob = arr.get(0).isObject();
									if(ob!=null)
									{
										JSONString str = ob.get("desc_utente").isString();

										egeosDCL.setDescUtente(""+ob.get("desc_utente").toString().replace("\"",""));
										egeosDCL.setIdUtente(""+ob.get("id_utente").toString().replace("\"",""));
										egeosDCL.setRuolo(""+ob.get("desc_ruolo").toString().replace("\"",""));
										egeosDCL.setLivelloRuolo(""+ob.get("livello_ruolo").toString().replace("\"",""));
										egeosDCL.setOrganismo(""+ob.get("organismo").toString().replace("\"",""));
										if(ob.get("desc_azie")!=null)
											egeosDCL.setDescAzie(""+ob.get("desc_azie").toString().replace("\"",""));

										if(egeosDCL.getDescAzie().equalsIgnoreCase("null")||egeosDCL.getDescAzie().equalsIgnoreCase(""))
											egeosDCL.setDescAzie(null);
										MapToolStrip.getUtente_connesso().setContents("Utente:<b>"+egeosDCL.getDescUtente()+"</b>");
									}

									maVaiGO();

								}else{
									System.out.println("1");
									String[] ldap; 
									for (int i = 0; i < arr.size(); i++) {
										JSONObject ob = arr.get(i).isObject();
										if(ob!=null)
										{
											appoRuolo+=""+ob.get("desc_ruolo").toString().replace("\"","")+",";
											appoRuolo=appoRuolo.replace("AYBA_FOTOINTERPRETE,", "");
										}
									}

									appoRuolo=appoRuolo.substring(0, appoRuolo.trim().length()-1);
									System.out.println(appoRuolo);

									ldap=appoRuolo.split(",");

									System.out.println(">>>>>>>>>>>>>>>>>>>>length "+ldap.length);

									LinkedHashMap<String, String> valueMap = new LinkedHashMap<String, String>();  

									if (ldap.length>1){
										for (int j = 0; j < ldap.length; j++) {
											valueMap.put(""+j, ldap[j]);
										}

										selectLDAP.setValueMap(valueMap);
										ldapLayout.show();
										formLayout.hide();
										btLayout.hide();
									}else{
										for (int i = 0; i < arr.size(); i++) {
											JSONObject ob = arr.get(i).isObject();
											if(ob!=null)
											{
												if(ldap[0].equalsIgnoreCase(ob.get("desc_ruolo").toString().replace("\"","")))
												{
													JSONString str = ob.get("desc_utente").isString();

													egeosDCL.setDescUtente(""+ob.get("desc_utente").toString().replace("\"",""));
													egeosDCL.setIdUtente(""+ob.get("id_utente").toString().replace("\"",""));
													egeosDCL.setRuolo(""+ob.get("desc_ruolo").toString().replace("\"",""));
													egeosDCL.setLivelloRuolo(""+ob.get("livello_ruolo").toString().replace("\"",""));
													egeosDCL.setOrganismo(""+ob.get("organismo").toString().replace("\"",""));
													if(ob.get("desc_azie")!=null)
														egeosDCL.setDescAzie(""+ob.get("desc_azie").toString().replace("\"",""));

													if(egeosDCL.getDescAzie().equalsIgnoreCase("null")||egeosDCL.getDescAzie().equalsIgnoreCase(""))
														egeosDCL.setDescAzie(null);
													MapToolStrip.getUtente_connesso().setContents("Utente:<b>"+egeosDCL.getDescUtente()+"</b>");

													ldapLayout.hide();
													maVaiGO();
												}
											}
										}
									}
									//									JSONObject ob = arr.get(0).isObject();
									//									if(ob!=null)
									//									{
									//										JSONString str = ob.get("desc_utente").isString();
									//	
									//										egeosDCL.setDescUtente(""+ob.get("desc_utente").toString().replace("\"",""));
									//										egeosDCL.setIdUtente(""+ob.get("id_utente").toString().replace("\"",""));
									//										egeosDCL.setRuolo(""+ob.get("desc_ruolo").toString().replace("\"",""));
									//										egeosDCL.setLivelloRuolo(""+ob.get("livello_ruolo").toString().replace("\"",""));
									//										egeosDCL.setOrganismo(""+ob.get("organismo").toString().replace("\"",""));
									//										if(ob.get("desc_azie")!=null)
									//											egeosDCL.setDescAzie(""+ob.get("desc_azie").toString().replace("\"",""));
									//										
									//										if(egeosDCL.getDescAzie().equalsIgnoreCase("null")||egeosDCL.getDescAzie().equalsIgnoreCase(""))
									//											egeosDCL.setDescAzie(null);
									//										MapToolStrip.getUtente_connesso().setContents("Utente:<b>"+egeosDCL.getDescUtente()+"</b>");
									//										
									//										SC.say(egeosDCL.getRuolo());
									//										
									//									}									
								}
							}
						}

						//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						//						maVaiGO();
						//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////						
					}
				}
				);

			}
		});

		btLayout.addMember(bt_conferma);
		windowLayout.addMember(formLayout);
		windowLayout.addMember(ldapLayout);
		windowLayout.addMember(btLayout);
		windowLayout.addMember(gridContestiLayout);
		this.addItem(windowLayout);

		if (egeosDCL.getUser().trim().length()>0){
			formLayout.hide();
			btLayout.hide();	

			userItem.setValue(egeosDCL.getUser()); 
			passItem.setValue("password09");			

			ClickEvent ev = new ClickEvent(bt_conferma.getJsObj());
			bt_conferma.fireEvent(ev);

		}

	}

	public void maVaiGO(){
		if(userItem.getValueAsString()!=null){
			if(!userItem.getValueAsString().trim().equals("")){
				//	System.out.println("-"+userItem.getValueAsString().trim()+"-");
				if(userItem.getValueAsString().trim().equals(egeosDCL.getDescUtente().trim())){
					//					if(passItem.getValueAsString().equals("password09")){
					if(gridContestiLayout.getMember("CONTESTI_GRID_ID")!=null){
						gridContestiLayout.getMember("CONTESTI_GRID_ID").destroy();
						listGridContesti = new ListGridContestiJSON();
						if(egeosDCL.getLivelloRuolo().equals("1")||egeosDCL.getLivelloRuolo().equals("3")){
							gridContestiLayout.addMember(new ToolStripButtonContesti(egeosDCL.getLivelloRuolo()));
							appo = addContextRow();
							gridContestiLayout.addMember(appo);

							appo.hide();
						}

					}else{
						listGridContesti = new ListGridContestiJSON();
						if(egeosDCL.getLivelloRuolo().equals("1")||egeosDCL.getLivelloRuolo().equals("3")){
							gridContestiLayout.addMember(new ToolStripButtonContesti(egeosDCL.getLivelloRuolo()));
							appo = addContextRow();
							gridContestiLayout.addMember(appo);
							appo.hide();
						}
					}
					formLayout.hide();
					btLayout.hide();
					gridContestiLayout.addMember(listGridContesti);
					addMember(gridContestiLayout);
					//					}
					//					else
					//						SC.say("Password errata");
				}else{
					SC.say("Utente non riconosciuto");
				}
			}else{
				SC.say("Completare tutti i campi");
			}
		}//	
		else{
			SC.say("Completare tutti i campi");
		}		
	}

	/**
	 * AGGIUNGE LA RIGA DEL CONTESTO
	 * @return
	 */
	public static HLayout addContextRow()
	{
		Canvas iframe = new Canvas();
		iframe.setContents("<IFRAME NAME='target_' style='width:0;height:0;border:0'></IFRAME>");

		final DynamicForm addContextForm = new DynamicForm();
		addContextForm.setNumCols(10);

		ButtonItem conferma_bt = new ButtonItem("Salva");
		conferma_bt.setTitle("Salva");
		conferma_bt.setTitleOrientation(TitleOrientation.TOP);
		conferma_bt.setTitleVAlign(VerticalAlignment.BOTTOM);
		conferma_bt.setStartRow(false);
		conferma_bt.setTop(50);
		conferma_bt.setEndRow(true);

		final TextItem itemIDelenco = new TextItem("Elenco");
		itemIDelenco.setTitleOrientation(TitleOrientation.TOP);

		final TextItem itemAnnoelenco = new TextItem("Campagna");
		itemAnnoelenco.setWidth(100);
		itemAnnoelenco.setTitleOrientation(TitleOrientation.TOP);
		itemAnnoelenco.setWidth(50);
		itemAnnoelenco.setLength(4);
		itemAnnoelenco.setKeyPressFilter("[0-9.]");  

		final TextItem itemNumelenco = new TextItem("N/�elenco");
		itemNumelenco.setWidth(100);
		itemNumelenco.setKeyPressFilter("[0-9.]");  
		itemNumelenco.setTitleOrientation(TitleOrientation.TOP);

		final TextItem itemDescelenco = new TextItem("Descrizione");
		itemDescelenco.setTitleOrientation(TitleOrientation.TOP);

		//		addContextForm.setTarget("target_");
		//		addContextForm.setMethod(FormMethod.GET);
		addContextForm.setItems(itemIDelenco,itemAnnoelenco,itemDescelenco,itemNumelenco,conferma_bt);
		RPCRequest request = null;
		conferma_bt.addClickHandler(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {

			
			public void onClick(
					com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
				if((itemIDelenco.getValueAsString()!=null && !itemIDelenco.getValueAsString().equals(""))&&
						(itemAnnoelenco.getValueAsString()!=null && !itemAnnoelenco.getValueAsString().equals(""))&&
						(itemNumelenco.getValueAsString()!=null && !itemNumelenco.getValueAsString().equals(""))&&
						(itemDescelenco.getValueAsString()!=null && !itemDescelenco.getValueAsString().equals(""))){

					RPCRequest request = new RPCRequest();
					// Note data could be a String, Map or Record
					request.setData("Some data to send to the client");
					request.setActionURL(GWT.getModuleBaseURL()+"JSONService?TIPO_OPERAZIONE=addContext&idElenco="+itemIDelenco.getValueAsString()+"&numElenco="+itemNumelenco.getValueAsString()+"&descrizione="+itemDescelenco.getValueAsString()+"&campagna="+itemAnnoelenco.getValueAsString()+"&tipoElenco=P&tipoLavo=0");
					RPCManager.sendRequest(request, new RPCCallback() {

						
						public void execute(RPCResponse response, Object rawData, RPCRequest request) {
							// TODO Auto-generated method stub
							SC.say("Salvataggio eseguito con sucesso");
							if(SetUserWindow.getListGridContesti()!=null)
							{

								if(SetUserWindow.getGridContestiLayout().getMember("CONTESTI_GRID_ID")!=null){
									SetUserWindow.getGridContestiLayout().getMember("CONTESTI_GRID_ID").destroy();
									listGridContesti = new ListGridContestiJSON();
									SetUserWindow.getGridContestiLayout().addMember(listGridContesti);
									SetUserWindow.getAppo().hide();
								}
							}

						}
					});

				}
				else
					SC.say("Valorizzare tutti i campi");

			}
		});

		HLayout lay_form_add_context = new HLayout();
		lay_form_add_context.addMember(addContextForm);
		lay_form_add_context.addMember(iframe);

		return lay_form_add_context;
	}

	public static void destroyWindow()
	{

	}

	public static IButton getBt_conferma() {
		return bt_conferma;
	}

	public static void setBt_conferma(IButton bt_conferma) {
		SetUserWindow.bt_conferma = bt_conferma;
	}

	public static ListGridContestiJSON getListGridContesti() {
		return listGridContesti;
	}

	public static void setListGridContesti(ListGridContestiJSON listGridContesti) {
		SetUserWindow.listGridContesti = listGridContesti;
	}

	public static HLayout getAppo() {
		return appo;
	}

	public static void setAppo(HLayout appo) {
		SetUserWindow.appo = appo;
	}

	public static VLayout getGridContestiLayout() {
		return gridContestiLayout;
	}

	public static void setGridContestiLayout(VLayout gridContestiLayout) {
		SetUserWindow.gridContestiLayout = gridContestiLayout;
	}

}
