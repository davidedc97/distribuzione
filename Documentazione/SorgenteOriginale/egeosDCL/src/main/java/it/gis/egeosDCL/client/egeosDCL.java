package it.gis.egeosDCL.client;

import java.util.LinkedHashMap;

import it.gis.egeosDCL.client.Layout.LeftLayout;
import it.gis.egeosDCL.client.Layout.MapLayout;
import it.gis.egeosDCL.client.Layout.LayoutObjects.DetailComuPanel;
import it.gis.egeosDCL.client.Layout.LayoutObjects.SetUserWindow;
import it.gis.egeosDCL.client.service.GreetingServiceAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.json.client.JSONParser;
import com.google.gwt.json.client.JSONValue;
import com.google.gwt.user.client.Window;
import com.smartgwt.client.widgets.grid.ListGrid; 
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.VLayout;

/**
 * Entry point del progetto
 * @version 1.0
 * 
 *
 */
public class egeosDCL implements EntryPoint {

	private final static 	GreetingServiceAsync 	greetingService 	= GreetingServiceAsync.Util.getInstance();
	private static				LeftLayout 				left_layout 		= null;



	private					static DetailComuPanel	dett_layout		= null;	
	private 				static String 			urlService;
	private static 			String 					user;
	private static 			String 					role;
	private static 			Boolean 				authenticated;
	private static			Boolean					thereIsAMap			= false;
	
	private static 			String					descUtente			= "";
	private static 			String					idUtente			= "";
	private static 			String					ruolo				= "";
	private static 			String					organismo			= "";
	private static 			String					livelloRuolo		= "";
	private static 			String					numElenco			= "";
	private static			JSONArray				arrRegJSON			= null;
	private static			JSONArray				arrProvJSON			= null;
	public static 			SetUserWindow userWin = null;
	public static 			LinkedHashMap<String,String>  mapSociDaAssegnare = null;
	public static 			ListGrid				listGridProvince	= null;
	private static 			ListGrid				listGridRegioni		= null;
	private static 			String 					URL_SERVER 			= "egeosWs/";
	private static 			String 					descAzie 			= "";
	private static 			boolean  				ho_clikkato_quadranti 			= false;
	private static          String					provXLS										= "";
	
	
	public static void redirectGwt(String url)
	{
		Window.open(url, "_self", ""); 
	}
	
	/**
	 * CREA IL PANNELLO PRINCIPALE
	 */
	public void onModuleLoad() {
		
		
		if(GWT.isProdMode()){
			URL_SERVER = "egeosWs/";
		}else{
			//URL_SERVER = "http://www.sourcedemo.net:7001/egeosWs/";
			URL_SERVER = "http://172.27.30.18:8001/egeosWs/";
		}
		setUser("");
		setRole("");
//		if(!GWT.isProdMode())
//		{
//			setUser("dpetrucci");
//			setRole("AYBA_SOCIO_PROVINCIALE");
//		}
		
		
		/*IN LOCALE COMMENTARE IL SEGUENTE BLOCCO IN TOTO E LASCIARE SOLO IL RICHIAMO AL METODO INITAPP()*/
		///////////////////////////////////////////////////////////////////////////////////
		//setUrlService("/egeosDCLDTO");
 
			/*  scommentare per test - prod */
		if(GWT.isProdMode())
		{
			String paramUs = "14b4d6de4c37fc76";
			String paramRole = "f0ee2e208928847d";
			String user = com.google.gwt.user.client.Window.Location.getParameter(paramUs);
			String role = com.google.gwt.user.client.Window.Location.getParameter(paramRole);
			String descIntIsta = ""+com.google.gwt.user.client.Window.Location.getParameter("descIntIsta");
			
			setUser(SguParam.decUser(user));
			setRole(SguParam.decRole(role));
			
			if (getUser().equalsIgnoreCase("unknown") || getUser().equalsIgnoreCase("")) {
				setAuthenticated(false);
			}
			else 
				setAuthenticated(true);
			if (getRole().equalsIgnoreCase("unknown") || getRole().equalsIgnoreCase("")) {
				setAuthenticated(false);
			}
			else
				setAuthenticated(true);
			if (!getAuthenticated()) 
	          {
	        	  egeosDCL.redirectGwt("/egeosDCLDTO/AutenticazioneServlet");
	        	  return;
	          }
			else
			{
				initApp();
			}
		}
		else{
			initApp();
		}
		
	}
	
	public void initApp()
	{
		VLayout center = new  VLayout();

		/*CREO IL LAYOUT PRINCIPALE*/
		HLayout containerLayout = new HLayout();  
	
		/*CREO IL LAYOUT DI SINISTRA(funzioni)*/
		left_layout = new LeftLayout();
		containerLayout.addMember(left_layout);
		containerLayout.setWidth100();  
		containerLayout.setHeight100();  

		MapLayout.create();
		/*Carico la mappa*/
		center.addMember(MapLayout.getH_lay());
		dett_layout = new DetailComuPanel();
		dett_layout.hide();
		
		center.addMember(dett_layout);
		containerLayout.addMember(center);
		
		containerLayout.draw();  
		userWin = new SetUserWindow();
		userWin.show();
		//String urlLay = GWT.getModuleBaseURL()+"geoserver/wms?req=amartinelli";
	
	}
	
	/**
	 * Funzionalit� per il calcolo del colore da applicare al layer dei comuni
	 * 
	 * @param totale
	 * @param lavorate
	 * @param assegnate
	 * @param no_assegnate
	 * @param validati
	 * @param sel
	 * @return
	 */
	public static String calcolaColoreComune(int totale,int lavorate,int assegnate,int no_assegnate,int validati,int sel)
	{
		////System.out.println("SEL IN CALCOLA COLORE COMUNE:"+sel);
		if(validati>0)
			return "#408747";
		if(lavorate==totale && totale > 0)//TOTALMENTE LAVORATE - VERDE
			return "#408747";
		if(lavorate<totale && lavorate!=0)//PARZ LAVORATE - IN LAVORAZIONE - ROSSO
			return "#FF0000";
		if(assegnate==totale && totale>0)//TUTTE ASSEGNATE - BLUE
		{
			return "#3835FF";
		}
		if(assegnate<totale && assegnate!=0)//PARZ ASSEGNATE - GIALLO
			return "#FFEF42";
	
		
		return "#ffffff";
	}
	
	/**
	 * LANCIA EVENTUALI MESSAGGI
	 * @param rawData
	 * @return
	 */
	public static String fireMessage(Object rawData)
	{
		String message = "";
		
		//System.out.println("RAWDATA STRING::: "+rawData.toString());
		
		JSONValue value = JSONParser.parseLenient(rawData+"");
		
		
		
		if(value.isObject()!=null)
		{
			JSONObject obj = value.isObject();
			JSONValue app = obj.get("TodoLista");
			JSONArray arr = app.isArray();
	
			if(arr!=null)
			{
				JSONObject ob = arr.get(0).isObject();
				if(ob!=null)
				{
					//System.out.println("STATUS CODE:"+ob.get("statuscode").toString());
					//System.out.println("MESSAGE    :"+ob.get("messages").toString());
					if(ob.get("exception")!=null)
						message = ob.get("exception").toString().replace("\"","");
					if(message.trim().length()==0)
						message = ob.get("messages").toString().replace("\"","");
				
				}
			}
		}
		
		return message;
	}
	
	/**
	 * LANCIA EVENTUALI MESSAGGI
	 * @param rawData
	 * @return
	 */
	public static String fireMessageEx(Object rawData)
	{
		String message = "";
		
		JSONValue value = JSONParser.parseLenient(rawData+"");
		
		if(value.isObject()!=null)
		{
			JSONObject obj = value.isObject();
			JSONValue app = obj.get("TodoLista");
			JSONArray arr = app.isArray();
	
			if(arr!=null)
			{
				JSONObject ob = arr.get(0).isObject();
				if(ob!=null)
				{
					//System.out.println("STATUS CODE:"+ob.get("statuscode").toString());
					//System.out.println("MESSAGE    :"+ob.get("messages").toString());

					message = ob.get("exception").toString().replace("\"","");
				}
			}
		}
		
		return message;
	}
	/**
	 * APPLICA UNO STILE AL LAYER DELLE PROVINCE
	 * @param totale
	 * @param lavorate
	 * @param assegnate
	 * @param no_assegnate
	 * @param sel
	 * @param stato
	 * @return
	 */
	public static String calcolaColoreProvince(int totale,int lavorate,int assegnate,int no_assegnate,int sel,int stato)
	{
	//	//System.out.println("DENTRO CALCOLA COLORE -- sel:"+sel+" - totale:"+totale+" - lavorate:"+lavorate+" - assegnate:"+assegnate+" - no_assegnate:"+no_assegnate);
		//System.out.println("STATO IN calcolaColoreProvince "+stato);
		if(egeosDCL.getLivelloRuolo().equals("3"))
		{
			if(lavorate==totale)//VALIDATE - VERDE
				return "#408747";
//			if(lavorate<totale && lavorate!=0)//PARZ LAVORATE - IN LAVORAZIONE - ROSSO
//				return "#FF0000";
			if(assegnate==totale && sel>0)//TUTTE ASSEGNATE - BLUE
			{
				//System.out.println("ENTRO");
				return "#3835FF";
			}
				
			if(assegnate<totale && assegnate>0)//PARZ ASSEGNATE - GIALLO
				return "#FFEF42";
		}
		else if(egeosDCL.getLivelloRuolo().equals("2"))
		{
//			if(lavorate==totale)//VALIDATE - VERDE
//				return "#408747";
//			if(lavorate<totale && lavorate!=0)//PARZ LAVORATE - IN LAVORAZIONE - ROSSO
//				return "#FF0000";
			if(assegnate==totale && sel>0)//TUTTE ASSEGNATE - BLUE
			{
				return "#3835FF";
			}
				
			if((assegnate<totale && assegnate>0)&& stato<2)//PARZ ASSEGNATE - GIALLO
				return "#FFEF42";
			
			if(stato==2)
				return "#408747";
			if(sel>0)
				return "#3835FF";
		}
		else 
		{
			
			if(stato==2)
				return "#408747";
			if(sel>0)
				return "#3835FF";
			
		}
		
		return "#ffffff";
	}
	
	
	/**
	 * APPLICA LO STILE AL LAYER DELLE REGIONI
	 * @param totale
	 * @param lavorate
	 * @param assegnate
	 * @param no_assegnate
	 * @param sel
	 * @return
	 */
	public static String calcolaColoreRegione(int totale,int lavorate,int assegnate,int no_assegnate,int sel)
	{
	//	//System.out.println("DENTRO CALCOLA COLORE -- sel:"+sel+" - totale:"+totale+" - lavorate:"+lavorate+" - assegnate:"+assegnate+" - no_assegnate:"+no_assegnate);
		

			if(lavorate==totale)//VALIDATE - VERDE
				return "#408747";
//			if(lavorate<totale && lavorate!=0)//PARZ LAVORATE - IN LAVORAZIONE - ROSSO
//				return "#FF0000";
			if(assegnate==totale)//TUTTE ASSEGNATE - BLUE
			{
				//System.out.println("ENTRO");
				return "#3835FF";
			}
				
			if(assegnate<totale && assegnate>0)//PARZ ASSEGNATE - GIALLO
				return "#FFEF42";


		
		return "#ffffff";
	}

	public static GreetingServiceAsync getService() {
		return greetingService;
	}




	public static String getUrlService() {
		return urlService;
	}


	public static void setUrlService(String urlService) {
		egeosDCL.urlService = urlService;
	}


	public static String getUser() {
		return user;
	}


	public static void setUser(String user) {
		egeosDCL.user = user;
	}


	public static String getRole() {
		return role;
	}


	public static void setRole(String role) {
		egeosDCL.role = role;
	}


	public static Boolean getAuthenticated() {
		return authenticated;
	}


	public static void setAuthenticated(Boolean authenticated) {
		egeosDCL.authenticated = authenticated;
	}

	public static Boolean getThereIsAMap() {
		return thereIsAMap;
	}

	public static void setThereIsAMap(Boolean thereIsAMap) {
		egeosDCL.thereIsAMap = thereIsAMap;
	}

	public static String getDescUtente() {
		return descUtente;
	}

	public static void setDescUtente(String descUtente) {
		egeosDCL.descUtente = descUtente;
	}

	public static String getIdUtente() {
		return idUtente;
	}

	public static void setIdUtente(String idUtente) {
		egeosDCL.idUtente = idUtente;
	}

	public static String getRuolo() {
		return ruolo;
	}

	public static void setRuolo(String ruolo) {
		egeosDCL.ruolo = ruolo;
	}

	public static String getOrganismo() {
		return organismo;
	}

	public static void setOrganismo(String organismo) {
		egeosDCL.organismo = organismo;
	}

	public static String getLivelloRuolo() {
		return livelloRuolo;
	}

	public static void setLivelloRuolo(String livelloRuolo) {
		egeosDCL.livelloRuolo = livelloRuolo;
	}

	public static String getNumElenco() {
		return numElenco;
	}

	public static void setNumElenco(String numElenco) {
		egeosDCL.numElenco = numElenco;
	}

	public static JSONArray getArrRegJSON() {
		return arrRegJSON;
	}

	public static void setArrRegJSON(JSONArray arrRegJSON) {
		egeosDCL.arrRegJSON = arrRegJSON;
	}

	public static JSONArray getArrProvJSON() {
		return arrProvJSON;
	}

	public static void setArrProvJSON(JSONArray arrProvJSON) {
		egeosDCL.arrProvJSON = arrProvJSON;
	}

	public static SetUserWindow getUserWin() {
		return userWin;
	}

	public static void setUserWin(SetUserWindow userWin) {
		egeosDCL.userWin = userWin;
	}

	public static LinkedHashMap<String, String> getMapSociDaAssegnare() {
		return mapSociDaAssegnare;
	}

	public static void setMapSociDaAssegnare(
			LinkedHashMap<String, String> mapSociDaAssegnare) {
		egeosDCL.mapSociDaAssegnare = mapSociDaAssegnare;
	}

	public static ListGrid getListGridProvince() {
		return listGridProvince;
	}

	public static void setListGridProvince(ListGrid listGridProvince) {
		egeosDCL.listGridProvince = listGridProvince;
	}

	public static ListGrid getListGridRegioni() {
		return listGridRegioni;
	}

	public static void setListGridRegioni(ListGrid listGridRegioni) {
		egeosDCL.listGridRegioni = listGridRegioni;
	}

	public static DetailComuPanel getDett_layout() {
		return dett_layout;
	}

	public static void setDett_layout(DetailComuPanel dett_layout) {
		egeosDCL.dett_layout = dett_layout;
	}

	public static String getURL_SERVER() {
		return URL_SERVER;
	}

	public static void setURL_SERVER(String uRL_SERVER) {
		URL_SERVER = uRL_SERVER;
	}

	public static String getDescAzie() {
		return descAzie;
	}

	public static void setDescAzie(String descAzie) {
		egeosDCL.descAzie = descAzie;
	}

	public static boolean isHo_clikkato_quadranti() {
		return ho_clikkato_quadranti;
	}

	public static void setHo_clikkato_quadranti(boolean ho_clikkato_quadranti) {
		egeosDCL.ho_clikkato_quadranti = ho_clikkato_quadranti;
	}
	public static LeftLayout getLeft_layout() {
		return left_layout;
	}

	public static void setLeft_layout(LeftLayout left_layout) {
		egeosDCL.left_layout = left_layout;
	}

	public static String getProvXLS() {
		return provXLS;
	}

	public static void setProvXLS(String provXLS) {
		egeosDCL.provXLS = provXLS;
	}

}
