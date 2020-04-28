package it.gis.egeosDCL.server.servlet;

import java.io.BufferedReader;

import it.gis.egeosDCL.server.EgeosDCLctx;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

import com.google.gwt.core.client.GWT;

/**
 * CLASSE DI GESTIONE DEI SERVIZI WEB
 * @version 1.0
 *
 */
public class JSONService  extends HttpServlet {

	public String urlService_regioni;
	public String urlService_province;
	public String urlService_comuni;
	public String urlService;

	public void init(ServletConfig servletconfig) throws ServletException {
		super.init(servletconfig);

//		urlService =EgeosDCLctx.URL_SERVER+"regioniRefreshService";
//		urlService_province=EgeosDCLctx.URL_SERVER+"provinceRefreshService?idRegione=";
	}

	@SuppressWarnings("rawtypes")
	public void service(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException
	{

		/*PER SOURCEDEMO
		if(GWT.isProdMode()){
			urlService = "http://"+req.getServerName()+":"+req.getServerPort()+"/"+EgeosDCLctx.URL_SERVER;
		}else{
			urlService = "http://www.sourcedemo.net:7001/egeosWs/"; 
		}*/
	 
		/*PER PRODUZIONE*/
		urlService = "http://"+req.getServerName()+":"+req.getServerPort()+"/"+EgeosDCLctx.URL_SERVER;

		/*PER LOCALE*/
		//urlService = "http://localhost:7001/egeosWs/";
		
		/*PER SOURCEDEMO*/
		//urlService = "http://www.sourcedemo.net:7001/egeosWs/";
		//urlService = "http://172.27.30.18:8001/egeosWs/";
		
		if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("provinceRefreshService"))//
			urlService +="provinceRefreshService?idRegione="+req.getParameter("idRegione")+"&numElenco="+req.getParameter("numElenco")+"&idUtente="+req.getParameter("idUtente"); 
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("comuniRefreshService"))
			urlService +="comuniRefreshService?idProvincia="+req.getParameter("idProvincia")+"&numElenco="+req.getParameter("numElenco");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("ContestiService"))
			urlService +="ContestiService";
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("utentiRuoliRefresh"))
			urlService +="utentiRuoliRefresh?user="+req.getParameter("descUtenza");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("socioNazionaleService"))
			urlService +="socioNazionaleService?user="+req.getParameter("utentiAssociati");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("assegnaUtenteRegioneRefresh"))
			urlService +="assegnaUtenteRegioneRefresh?idRegione="+req.getParameter("idRegione")+"&numElenco="+req.getParameter("numElenco")+"&idUtente="+req.getParameter("idUtente");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("regioniRefreshService"))
			urlService +="regioniRefreshService?numElenco="+req.getParameter("numElenco");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("regioniSocioNazionaleService"))
			urlService +="regioniSocioNazionaleService?numElenco="+req.getParameter("numElenco")+"&idUtente="+req.getParameter("idUtente");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("socioProvincialeService"))
			urlService +="socioProvincialeService?user="+req.getParameter("descUtente");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("assegnaUtenteProvinciaRefresh"))
			urlService +="assegnaUtenteProvinciaRefresh?idProvincia="+req.getParameter("idProvincia")+"&idUtente="+req.getParameter("idUtente")+"&numElenco="+req.getParameter("numElenco")+"&idRegione="+req.getParameter("idRegione")+"&idPadre="+req.getParameter("idPadre")+"&op="+req.getParameter("op");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("provinceSocioProvService"))
			urlService +="provinceSocioProvService?idUtente="+req.getParameter("idUtente")+"&numElenco="+req.getParameter("numElenco");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("provinceSocioNazionaleService"))
			urlService +="provinceSocioNazionaleService?idUtente="+req.getParameter("idUtente")+"&numElenco="+req.getParameter("numElenco")+"&idRegione="+req.getParameter("idRegione");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("assegnaUtenteComuneRefresh"))
			urlService +="assegnaUtenteComuneRefresh?idUtente="+req.getParameter("idUtente")+"&numElenco="+req.getParameter("numElenco")+"&idComune="+req.getParameter("idComune");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("provinceComuniSocioProvincialeService"))
			urlService +="provinceComuniSocioProvincialeService?idUtente="+req.getParameter("idUtente")+"&numElenco="+req.getParameter("numElenco");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("comuniSocioProvincialeService"))
			urlService +="comuniSocioProvincialeService?idUtente="+req.getParameter("idUtente")+"&numElenco="+req.getParameter("numElenco")+"&idProvincia="+req.getParameter("idProvincia");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("quadriComuneRefreshService"))
			urlService +="quadriComuneRefreshService?idComune="+req.getParameter("idComune")+"&numElenco="+req.getParameter("numElenco");
//		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("quadriComuneRefreshService"))
//			urlService = EgeosDCLctx.URL_SERVER+"CLOBquadriComuneRefreshService?idComune="+req.getParameter("idComune")+"&numElenco="+req.getParameter("numElenco");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("fotoInterpreteService"))
			urlService +="fotoInterpreteService?user="+req.getParameter("descUtente");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("assegnaUtentiQuadrantiAll"))
			urlService +="assegnaUtentiQuadrantiAll?idUtente="+req.getParameter("idUtente")+"&numElenco="+req.getParameter("numElenco")+"&idComune="+req.getParameter("idComune")+"&idPadre="+req.getParameter("idPadre")+"&idQuadranti="+req.getParameter("idQuadranti")+"&flagOper="+req.getParameter("flagOper");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("provinceSocioProvincialeService"))
			urlService +="provinceSocioProvService?idUtente="+req.getParameter("idUtente")+"&numElenco="+req.getParameter("numElenco");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("addContext"))
			urlService +="addContext?idElenco="+req.getParameter("idElenco")+"&numElenco="+req.getParameter("numElenco")+"&campagna="+req.getParameter("campagna")+"&descrizione="+req.getParameter("descrizione")+"&tipoElenco=P&tipoLavo=0";
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("deleteContext"))
			urlService +="deleteContextAll?numElenco="+req.getParameter("numElenco"); 
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("validaUtenteProvinciaService"))
			urlService +="validaUtenteProvinciaService?numElenco="+req.getParameter("numElenco")+"&idUtente="+req.getParameter("idUtente")+"&idProvincia="+req.getParameter("idProvincia");
//		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("listAsseUteComuneProvi"))
//			urlService = EgeosDCLctx.URL_SERVER+"listAsseUteComuneProvi?idProvincia="+req.getParameter("idProvincia")+"&idComune="+req.getParameter("idComune");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("listAsseUteComuneProviP"))
			urlService +="listAsseUteComuneProvi?idProvincia="+req.getParameter("idProvincia")+"&numElenco="+req.getParameter("numElenco")+"&tipoOper="+req.getParameter("tipoOper");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("listAsseUteComuneProviC"))
			urlService +="listAsseUteComuneProvi?idProvincia="+req.getParameter("idProvincia")+"&numElenco="+req.getParameter("numElenco")+"&idComune="+req.getParameter("idComune")+"&tipoOper="+req.getParameter("tipoOper");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("validaQuadriAsseRefresh"))
			urlService +="validaQuadriAsseRefresh?idProvincia="+req.getParameter("idProvincia")+"&numElenco="+req.getParameter("numElenco")+"&idComune="+req.getParameter("idComune")+"&idUtente="+req.getParameter("idUtente");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("validaQuadriAsseRefreshP"))
			urlService +="validaQuadriAsseRefresh?idProvincia="+req.getParameter("idProvincia")+"&numElenco="+req.getParameter("numElenco")+"&idUtente="+req.getParameter("idUtente");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("disassociaComuneUtente"))
			urlService +="disassociaComuneUtente?numElenco="+req.getParameter("numElenco")+"&idComune="+req.getParameter("idComune");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("CLOBquadriComuniRefreshService"))
			urlService +="CLOBquadriComuniRefreshService?numElenco="+req.getParameter("numElenco")+"&idComune="+req.getParameter("idComune");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("RESETTA_QUADRANTI"))
			urlService +="resettaQuadranti?grid_id="+req.getParameter("grid_id")+"&user_id="+req.getParameter("user_id");
		else if(req.getParameter("TIPO_OPERAZIONE")!=null && req.getParameter("TIPO_OPERAZIONE").equalsIgnoreCase("assegnaComuneTuttiUtenti"))
			urlService +="assegnaComuneTuttiUtenti?idPadre="+req.getParameter("idPadre")+"&numElenco="+req.getParameter("numElenco")+"&idUtente="+req.getParameter("idUtente")+"&idComune="+req.getParameter("idComune");
		
		
		System.out.println("URL SERVICE: "+urlService);
		
		try
		{
			StringBuffer jb = new StringBuffer();
			String line = null; 
			try {
				BufferedReader reader = req.getReader();
				while ((line = reader.readLine()) != null)
					jb.append(line);
			} catch (Exception e) { e.printStackTrace(); }

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
			resp.setContentType("application/json");
			InputStreamReader isr = new InputStreamReader(in);
			BufferedReader reader = new BufferedReader(isr);
			PrintWriter writer = resp.getWriter();
			String text = "";

			while ((text = reader.readLine()) != null) {
				writer.println(text);
			}
			isr.close();
			reader.close();
			writer.flush();
			writer.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		urlService = "";
	}
	

}



