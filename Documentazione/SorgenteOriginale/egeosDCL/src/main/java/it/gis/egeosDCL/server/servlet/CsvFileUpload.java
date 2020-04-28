package it.gis.egeosDCL.server.servlet;

import it.gis.egeosDCL.server.util.ProvaEXCEL;
import it.gis.egeosDCL.server.EgeosDCLctx;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import com.google.gwt.core.client.GWT;
//import com.sun.org.apache.xml.internal.serializer.utils.StringToIntTable;

public class CsvFileUpload extends HttpServlet {

	private static final long serialVersionUID = 1L;

	//private boolean           isMultipart;
	//private String            filePath;
	//private File              file;
	private int               maxFileSize      = 50 * 1024;
	private int               maxMemSize       = 4 * 1024;
	String 					message_str="";

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, java.io.IOException {
		processRequest(request, response);

	}

	protected void processRequest(HttpServletRequest req, HttpServletResponse res)
	throws ServletException, java.io.IOException {


		//res.setContentType("text/html");
		java.io.PrintWriter out = res.getWriter();

		DiskFileItemFactory factory = new DiskFileItemFactory();

		// maximum size that will be stored in memory
		factory.setSizeThreshold(maxMemSize);
		// Create a new file upload handler
		ServletFileUpload upload = new ServletFileUpload(factory);

		// maximum file size to be uploaded.
		upload.setSizeMax(maxFileSize);

		try {
			List fileItems = upload.parseRequest(req);

			FileItem fi = (FileItem) fileItems.get(0);
			if (!fi.isFormField()) {
				FileInputStream fis = (FileInputStream) fi.getInputStream();

				//out.println(fileContent);
				
				ProvaEXCEL pe = new ProvaEXCEL();

				String stringone_xls = "";

				String urlService = null;

				HttpClient client = new HttpClient();

					urlService = "http://" + req.getServerName() + ":" + req.getServerPort() + "/"
					+ EgeosDCLctx.URL_SERVER;
	
				urlService += "addUserCtx?p=";
				stringone_xls = pe.parseXLS(fis);


//shttp://www.sourcedemo.net:7001/egeosWs/addUserCtx?p=COP,3,rrato,2014,Refresh,AYBA_SOCIO_NAZIONALE#COP,3,rrato,2014,Refresh,AYBA_SOCIO_PROVINCIALE#COP,3,rrato,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,ptripodi,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,smauro,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,gicostantino,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,antassone,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,atvalenti,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,argiampa,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,vguido,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,ggiuffrida,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,fremanuele,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,sfurci1,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,aferrelli,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,mrmartini,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,vgnagni,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,tmartinelli,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,mtomassoli,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,lpaparelli,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,cpompetti,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,cbecchetti,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,fcolasurdo,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,psantori,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,gdongiovanni,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,eleone,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,ccasagrande,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,cvinciarelli,2014,Refresh,AYBA_FOTOINTERPRETE#COP,3,cragnacci,2014,Refresh,AYBA_FOTOINTERPRETE
//ALM,Sigeo,dcolaiocco,Refresh,AYBA_SOCIO_PROVINCIALE#ALM,Sigeo,amelchiorri,Refresh,AYBA_SOCIO_PROVINCIALE#ALM,Sigeo,spuppio,Refresh,AYBA_FOTOINTERPRETE#ALM,Sigeo,mceraudo,Refresh,AYBA_FOTOINTERPRETE#ALM,Sigeo,cceraudo,Refresh,AYBA_FOTOINTERPRETE#ALM,Sigeo,asarr,Refresh,AYBA_FOTOINTERPRETE#ALM,Sigeo,inwosu,Refresh,AYBA_FOTOINTERPRETE#ALM,Sigeo,icatini,Refresh,AYBA_FOTOINTERPRETE#ALM,Sigeo,azanatta,Refresh,AYBA_FOTOINTERPRETE#ALM,Sigeo,bpapacci,Refresh,AYBA_FOTOINTERPRETE#ALM,Sigeo,dcolaiocco,Refresh,AYBA_FOTOINTERPRETE#ALM,Sigeo,amelchiorri,Refresh,AYBA_FOTOINTERPRETE#ALM,Sigeo,faantonucci,Refresh,AYBA_FOTOINTERPRETE
				PostMethod httpMethod = new PostMethod(urlService +stringone_xls);

				int status = 0;
				try {
					status = client.executeMethod(httpMethod);

					String resp = httpMethod.getResponseBodyAsString();
					System.out.println("status = " + status + " :: resp = " + resp);

					message_str+=resp;	

				} catch (Exception e) {
					e.printStackTrace();
				}
			}


		} catch (Exception ex) {
			System.out.println("DENTRO CATCH::::: "+ex);
			message_str = ex.getMessage();	
		//	res.getWriter().write(ex.getMessage());
		//	message_str = "";
		//	ex.printStackTrace();
		}finally{
			//	throw new ServletException(message_str);ù
		
				res.getWriter().write(message_str);
				message_str = "";
		}

	}
}
