package it.gis.egeosDCL.server.servlet;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.gis.egeosDCL.server.DAO.ExcelDAO;


public class RiepilogoEFAXLSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     try{
	           
	            String fname="riepilogo.xls";
	            String reg="";
	            String prov = "";
	            String numeElenco = "";

	            if (request.getParameter("reg")!=null)
	            	reg=request.getParameter("reg");
	            
	            if (request.getParameter("prov")!=null)
	            	prov=request.getParameter("prov");
	            
	            if (request.getParameter("numeElenco")!=null)
	            	numeElenco= request.getParameter("numeElenco");
	            
	            ExcelDAO  excelDAO = new ExcelDAO();
	            
	            byte[] bytes = excelDAO.getRiepilogoEFA(reg,prov,numeElenco).getBytes();
	            
	            response.setContentLength((int) bytes.length);
	            response.setHeader("Content-type" ,"application/vnd.ms-excel"); 
	            String fileNameHeader = "attachment; filename="+fname;
	            response.setHeader("Content-disposition" , fileNameHeader);  
	            ServletOutputStream  stream = response.getOutputStream();    
	            stream.write(bytes);
	        }
	        catch(Exception e){
	            e.printStackTrace();
	        }
	}

}
