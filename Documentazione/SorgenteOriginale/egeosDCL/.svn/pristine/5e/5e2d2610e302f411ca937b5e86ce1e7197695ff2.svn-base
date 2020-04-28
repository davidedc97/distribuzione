package it.gis.egeosDCL.server.servlet;

import it.gis.egeosDCL.server.Encrypter;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;

import sun.misc.BASE64Encoder;

/**
 * Il servizio <code>Proxy</code> 
 *  
 * @author  
 * @version 1.00
 */ 
public class Proxy extends HttpServlet {

	static final long serialVersionUID = 33;
	String url = "";
	String utente ="";
	String appo = "";
	
	@Override
	public void init(ServletConfig servletconfig) throws ServletException{
        super.init(servletconfig);
    }
	
	@Override
 	public void service(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
    	String strCntTyp = null;
    	
//    	<meta http-equiv="Cache-control" content="public">
//    	<meta http-equiv="cache-control" content="max-age=31536000" />
//    	max-age=31536000
    	
    	try {
    		appo=Encrypter.dec(request.getParameter("u"));
    		if(appo.trim().contains("http://")){
    			url=Encrypter.dec(request.getParameter("u"));
    		}else{
    			url="http://"+request.getServerName()+":"+request.getServerPort()+"/"+Encrypter.dec(request.getParameter("u"));
    		}
        	
        	url=java.net.URLDecoder.decode(url,"UTF-8");
        	
        
        	URL u=new URL(url);
        	
        	HttpURLConnection httpCon = (HttpURLConnection) u.openConnection();
        	
//			BASE64Encoder enc = new sun.misc.BASE64Encoder();
//            String userpassword = utente + ":" + "";
//            String encodedAuthorization = enc.encode(userpassword.getBytes());
//            httpCon.setRequestProperty("Authorization", "Basic " + encodedAuthorization);
            
        	httpCon.setRequestMethod("GET");
            httpCon.connect();

            InputStream is = httpCon.getInputStream();

//			strCntTyp = "application/json";
			
			response.setHeader("Pragma", "no-cache");
			response.setContentType(strCntTyp);
//			if (strCntTyp.equalsIgnoreCase("application/zip")) {
//				response.setHeader("Content-Disposition", "attachment; filename=\"perimetro.zip\"");
//			}
			IOUtils.copy(is, response.getOutputStream());
			
			response.getOutputStream().close();	
					
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}
