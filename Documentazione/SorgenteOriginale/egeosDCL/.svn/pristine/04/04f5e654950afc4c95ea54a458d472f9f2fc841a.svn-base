package it.gis.egeosDCL.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.List;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;


import it.gis.egeosDCL.client.service.GreetingService;
import it.gis.egeosDCL.server.DAO.ParticelleGeomDAO;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;
////////////////////////////
import com.smartgwt.client.rpc.RPCRequest;


@SuppressWarnings("serial")
public class GreetingServiceImpl extends RemoteServiceServlet implements    GreetingService {
	
	
	private String 				idParticellaPerStampa 	= "";
	
	public GreetingServiceImpl(){
		//System.out.println("Chiamo greet");
		
	}
	
	public String getQuadrantiJSON(String idComune,String numElenco) throws Exception
	{
		String json = "";
		
		try
		{
		StringBuffer jb = new StringBuffer();
		
		System.out.println("DENTRO GET QUADRANTI JSON: PATH - "+this.getServletConfig().getServletContext().getContextPath().toString());
		
		//String urlService = "http://www.sourcedemo.net:7001/"+EgeosDCLctx.URL_SERVER+"CLOBquadriComuniRefreshService?idComune="+idComune+"&numElenco="+numElenco;
		String urlService = "http://172.27.30.18:8001/"+EgeosDCLctx.URL_SERVER+"CLOBquadriComuniRefreshService?idComune="+idComune+"&numElenco="+numElenco;
		
		
		System.out.println("get quadranti json: "+urlService);

		PostMethod httpMethod = new PostMethod(urlService);

		StringRequestEntity reqJb = new StringRequestEntity(jb.toString(), "application/json", "UTF-8");
		httpMethod.setRequestEntity(reqJb);

		URL u = new URL(urlService);
		////System.out.println("urlService: "+urlService);
		HttpClient client = new HttpClient();


		int status =0;
		try {
			status=  client.executeMethod(httpMethod);

		} catch (Exception e) {
			e.printStackTrace();
		}
		InputStream in =null;

		if(status == HttpStatus.SC_OK) {
			in = httpMethod.getResponseBodyAsStream();
		}
		else
		{
			String cause = httpMethod.getResponseBodyAsString();
			String exceptionDetail ="Il server ha risposto "+status+" per la richiesta: "+urlService+" cause: "+cause+" RICHIESTA GET: "+httpMethod.getQueryString();
			NameValuePair[] parameters = httpMethod.getParameters();
			for (int i = 0; i < parameters.length; i++) {

				exceptionDetail +=" PARAMETRO NOME: "+parameters[i].getName();
				exceptionDetail +=" PARAMETRO VALORE: "+parameters[i].getValue();

			}
			exceptionDetail += "REQUEST BODY: "+jb.toString();
			
			throw new IOException(exceptionDetail);
		}
		
		InputStreamReader isr = new InputStreamReader(in);
		BufferedReader reader = new BufferedReader(isr);

		String text = "";

		while ((text = reader.readLine()) != null) {
			json+=text;
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return json;
	}

}
