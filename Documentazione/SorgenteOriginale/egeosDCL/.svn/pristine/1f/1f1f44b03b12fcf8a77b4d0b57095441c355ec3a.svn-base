package it.gis.egeosDCL.server.servlet;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ExportGridServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8948188270731933328L;

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException ,IOException 
			{

		try{

			String format = req.getParameter("format");
			resp.setContentType(format);

			if(format.equalsIgnoreCase("application/vnd.ms-excel"))
			{

				resp.setHeader("Content-Disposition", "attachment; filename=\"griglia.xls\"");
				doExcel(req, resp);

			}
			else
				if(format.equalsIgnoreCase("application/pdf"))
				{

					resp.setHeader("Content-Disposition", "attachment; filename=\"griglia.pdf\"");
					doPdf(req, resp);

				}

		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

			}


	private void doPdf(HttpServletRequest req, HttpServletResponse resp) throws DocumentException, IOException {
		String stringone = req.getParameter("content");
		String titolo = req.getParameter("title");
	
	
		String[] split = stringone.split("@");	
		String[] split2 = split[0].split("#");
		Document document = new Document(PageSize.A3.rotate());
		PdfWriter writer = PdfWriter.getInstance(document,resp.getOutputStream());
		
		writer.createXmpMetadata();
		document.open();
		
		document.addHeader("Top", titolo);
		Phrase p = new Phrase(titolo,new Font(Font.FontFamily.HELVETICA  , 14, Font.BOLD));
		
	
		document.add(Chunk.NEWLINE );
		document.add(p);

		
		PdfPTable table = createTable(split2.length,stringone);
		document.add(table);
		document.add( Chunk.NEWLINE );
		document.add( Chunk.NEWLINE );

		document.close();
		resp.getOutputStream().flush();




	}

	public static PdfPTable createTable(int numcols,String stringone) throws DocumentException {
		PdfPTable table = new PdfPTable(numcols);


		table.setWidthPercentage(100);
		
		String[] rows = stringone.split("@");

		for (int i = 0; i < rows.length; i++) {
			String[] cols = rows[i].split("#");
			for (int j = 0; j < cols.length; j++) {
				
				int size = i==0?10:8;
				Font font1 = new Font(Font.FontFamily.HELVETICA  , size, Font.BOLD);
				Phrase p = new Phrase(cols[j],font1);
				
				PdfPCell cell = new PdfPCell(p);
				if(i==0)
					cell.setBackgroundColor(new BaseColor(137, 137, 255));
				table.addCell(cell);
			}
		}



		return table;
	}


	public void doExcel(HttpServletRequest req, HttpServletResponse resp)
	{
		String titolo =req.getParameter("title");
		titolo = titolo.replaceAll(":", " ");
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(titolo);
		String stringone = req.getParameter("content");

		String[] split = stringone.split("@");


		Map<String, Object[]> data = new HashMap<String, Object[]>();
		for(int i =0 ; i< split.length ; i++)
		{

			data.put(""+(i), parseNumericStringArray(split[i].split("#")));
		}

		for(int i = 0 ; i<data.size();i++)
		{
			String key = ""+(i);
			Object [] objArr = data.get(key);
			Row row = sheet.createRow(i);
			for(int j = 0 ; j<objArr.length ; j++)
			{
				Object obj = objArr[j];

				Cell cell = row.createCell(j);
				if(obj instanceof Date) 
					cell.setCellValue((Date)obj);
				else if(obj instanceof Boolean)
					cell.setCellValue((Boolean)obj);
				else if(obj instanceof String)
					cell.setCellValue((String)obj);
				else if(obj instanceof Double)
					cell.setCellValue((Double)obj);
			}

		}

		try {

			workbook.write(resp.getOutputStream());

			resp.getOutputStream().flush();
			resp.getOutputStream().close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private Object parseNumericString(String t)
	{
		try
		{
			Double d = Double.parseDouble(t);	
			return d;
		}
		catch(Exception e)
		{
			return t;
		}
	}
	private Object[] parseNumericStringArray(String[] t)
	{
		Object[] objs = new Object[t.length];
		for(int i = 0 ; i<objs.length;i++)
		{
			objs[i] = parseNumericString(t[i]);
		}
		return objs;
	}
	





}
