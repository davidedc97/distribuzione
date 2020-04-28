package it.gis.egeosDCL.server.servlet;


import java.io.BufferedReader;
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
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;


public class ServletPrintPDF  extends HttpServlet {

	public String urlService_regioni;
	public String urlService_province;
	public String urlService_comuni;
	public String urlService;

	public void init(ServletConfig servletconfig) throws ServletException {
		super.init(servletconfig);

		urlService ="http://www.sourcedemo.net/prova-web/prova/regioniRefreshService";
		urlService_province="http://www.sourcedemo.net/prova-web/prova/provinceRefreshService?idRegione=";
	}

	@SuppressWarnings("rawtypes")
	public void service(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException
	{
		urlService = "";

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
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	

}



