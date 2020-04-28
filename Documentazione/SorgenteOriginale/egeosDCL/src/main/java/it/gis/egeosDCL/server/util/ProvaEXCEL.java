package it.gis.egeosDCL.server.util;

import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.StringTokenizer;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ProvaEXCEL
{
	static String descSiglaSocio 	= "";
	static String descStudio 		= "";
	static String descUtenzaSIAN 	= "";
	static String campagna 			= "";
	static String settore 			= "";
	static String gruppoSGU			= "";
	String stringone_xls			= "";

	static boolean lanciaErrore		= false; 

	//public static void parseXLS()
	public String parseXLS(FileInputStream fis)throws Exception
	{
		String stringone			= "";

		String break_="";
		org.apache.poi.ss.usermodel.Workbook workbook = WorkbookFactory.create(fis);
		org.apache.poi.ss.usermodel.Sheet sheet = workbook.getSheetAt(0);

		try
		{
			Iterator<Row> rowIterator = sheet.iterator();
			while (rowIterator.hasNext())
			{
				Row row = rowIterator.next();

				if(row!=null){
					if(row.getRowNum()>1){

						if((row.getCell(0)!=null && (row.getCell(0).getStringCellValue()!=null && !row.getCell(0).getStringCellValue().equals("")))||
								(row.getCell(1)!=null && (row.getCell(1).getStringCellValue()!=null && !row.getCell(1).getStringCellValue().equals("")))||	
								(row.getCell(2)!=null && (row.getCell(2).getStringCellValue()!=null && !row.getCell(2).getStringCellValue().equals("")))||
								(row.getCell(9)!=null && row.getCell(9).getNumericCellValue()>0)||
								(row.getCell(11)!=null && (row.getCell(11).getStringCellValue()!=null && !row.getCell(11).getStringCellValue().equals("")))||
								(row.getCell(12)!=null && (row.getCell(12).getStringCellValue()!=null && !row.getCell(12).getStringCellValue().equals("")))){

							if(row.getCell(0)!=null && row.getCell(0).getStringCellValue().equalsIgnoreCase("ruolo"))
								break;

							Iterator<Cell> cellIterator = row.cellIterator();
							stringone += rigaValida(row.getRowNum()+1,cellIterator,row.getCell(12).getStringCellValue())+":";
					
							if(!stringone.equals("") && stringone.contains("mancante")){
							//					System.out.println("DENTRO LANCIA ECCEZIONE");
								throw new Exception(getErrorMessage(stringone));
							}
							else if(stringone.contains("non di tipo"))
							{
								//devo pulire lo stringone...come???
							//	System.out.println("DENTRO LANCIA ECCEZIONE 2222");
								throw new Exception(stringone.substring(stringone.indexOf("Cella"),stringone.length()-1));
							}
						}


					}
				}
			}
		}catch(Exception e)
		{
			throw new Exception(e);
		}
		fis.close();

		return stringone.substring(0,stringone.length()-1);
	}

	public static void main(String[] args)
	{
		//parseXLS();
	}


	public String getErrorMessage(String stringa_xls)
	{
		String message = "";
		try
		{
			String array_appo[] = stringa_xls.split(":");

			for(int i = 0;i<array_appo.length;i++)
			{
				String array_2[] = array_appo[i].split(",");

				for(int x = 0;x<array_2.length;x++)
				{
					if(array_2[x].contains("mancante"))
						message = array_2[x]; 
				}
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return message;

	}


	/**
	 * Controllo che la riga del file xls abbia almeno un record
	 * @param rownum
	 * @param cellIterator
	 * @return
	 * @throws Exception 
	 */
	public String rigaValida(int rownum,Iterator<Cell> cellIterator,String gruppoSgu) throws Exception
	{

		String riga_xls = "";
		String break_;
		String stringone_xls="";
		int err = 0;


		try
		{

			if(rownum>1){

				while (cellIterator.hasNext())
				{
					Cell cell = cellIterator.next();
					/* TIPO CELLA	
					 * 0 - numero
					   1 - stringa
					   2 - formula
					   3 - blank 
					*/	
					if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING && cell.getStringCellValue().equalsIgnoreCase("RUOLO"))
					{
						stringone_xls+="BREAK";
						return stringone_xls;
					}
					if(cell.getColumnIndex()==0)//sigla socio
					{
						if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING || cell.getCellType()==HSSFCell.CELL_TYPE_BLANK)
						{
							if(!cell.getStringCellValue().equals("")){
								
								if( !cell.getStringCellValue().equals("ALM")&&
									!cell.getStringCellValue().equals("AGC")&&
									!cell.getStringCellValue().equals("AGF")&&
									!cell.getStringCellValue().equals("AED")&&
									!cell.getStringCellValue().equals("COP")&&
									!cell.getStringCellValue().equals("SOF")&&
									!cell.getStringCellValue().equals("TPZ"))
								{
									stringone_xls+="Sigla socio mancante o non corrispondente ai seguenti valori(ALM-AGC-AGF-AED-COP-SOF-TPZ) alla riga: "+rownum;
								}
								else
									stringone_xls += cell.getStringCellValue()+",";
							}
							else{
								//stringone_xls += ",";
								stringone_xls+="Sigla socio mancante alla riga "+rownum;
								break;
							}
						}
						else{
							stringone_xls="";
							stringone_xls+="cella Sigla socio non di tipo testo alla riga "+rownum;
							break;
						}
					}
					if(cell.getColumnIndex()==1)//studio
					{
						
						if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING || cell.getCellType()==HSSFCell.CELL_TYPE_BLANK)
						{
							if(!cell.getStringCellValue().equals(""))
								stringone_xls += cell.getStringCellValue()+",";
							else{
								//stringone_xls += ",";
								if(gruppoSgu.equalsIgnoreCase("Utenteoperatoresupporto")||gruppoSgu.equalsIgnoreCase("Utenteoperatoreprovinciale")){
									stringone_xls+="Studio mancante alla riga "+rownum;
									break;
								}
							}
						}
						else if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC || cell.getCellType()==HSSFCell.CELL_TYPE_BLANK){
							String studioNum = ""+cell.getNumericCellValue();
				
							if(studioNum.contains("."))
								stringone_xls +=""+studioNum.substring(0,studioNum.indexOf("."))+",";
							else
								stringone_xls +=studioNum;
						}
						else
						{
						//	System.out.println("ECCCCHIIIIMMEEEE : "+stringone_xls);
							stringone_xls="";
							stringone_xls+="Valore non valido nella cella studio alla riga "+rownum;
							break;
						}
					}
					if(cell.getColumnIndex()==2)//utenza sian
					{
						if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING || cell.getCellType()==HSSFCell.CELL_TYPE_BLANK)
						{
							if(!cell.getStringCellValue().equals(""))
								stringone_xls += cell.getStringCellValue()+",";
							else{
								//stringone_xls += ",";
								stringone_xls+="Utenza Sian mancante alla riga "+rownum;
								break;
							}
						}
						else
						{
							stringone_xls="";
							stringone_xls+="Cella utenza sian  non di tipo testo alla riga "+rownum;
							break;
						}
					}
					if(cell.getColumnIndex()==9)//campagna
					{
						String campagna ="";
						if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING || cell.getCellType()==HSSFCell.CELL_TYPE_BLANK)
							campagna = ""+cell.getStringCellValue();
						else{
							if(cell.getCellType()==HSSFCell.CELL_TYPE_NUMERIC)
							{
								campagna = ""+cell.getNumericCellValue();
								if(!campagna.equals(""))
									stringone_xls += campagna.substring(0,campagna.length()-2)+",";
								else{
									//stringone_xls += ",";
									stringone_xls+="Campagna mancante alla riga "+rownum;
									break;
								}
							}
						}	
					}	
					if(cell.getColumnIndex()==11)//settore
					{
						if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING || cell.getCellType()==HSSFCell.CELL_TYPE_BLANK)
						{
							if(!cell.getStringCellValue().equals(""))
								stringone_xls += cell.getStringCellValue().trim()+",";
							else{
								//stringone_xls += ",";
								stringone_xls+="Settore mancante alla riga "+rownum;
								break;
							}
						}
						else
						{
							stringone_xls="";
							stringone_xls+="Cella settore non di tipo testo alla riga "+rownum;
							break;
						}
					}
					if(cell.getColumnIndex()==12)//gruppo sgu
					{
						if(cell.getCellType()==HSSFCell.CELL_TYPE_STRING || cell.getCellType()==HSSFCell.CELL_TYPE_BLANK)
						{
							if(!cell.getStringCellValue().equals("")){
								
								//System.out.println("GRUPPO SGU:::::::::: "+cell.getStringCellValue().replace("/á", ""));
								
								if(cell.getStringCellValue().equalsIgnoreCase("Utenteoperatoresupporto"))
								{
									stringone_xls +="AYBA_FOTOINTERPRETE,";
								}
								else if(cell.getStringCellValue().equalsIgnoreCase("Utentecoordinatoreregionale")){
									stringone_xls +="AYBA_SOCIO_NAZIONALE,";
								}
								else if(cell.getStringCellValue().equalsIgnoreCase("Utenteoperatoreprovinciale")){
									stringone_xls +="AYBA_SOCIO_PROVINCIALE,";
								}
							}
							else{
								//stringone_xls += ",";
								stringone_xls+="Gruppo SGU mancante alla riga "+rownum;
								break;
							}
						}
						else
						{
							stringone_xls="";
							stringone_xls+="Cella gruppo sgu non di tipo testo alla riga "+rownum;
							break;
						}
					}
				}

			}
		}
		catch(Exception e)
		{
			System.out.println("dentro catch rigaValida : err: "+e);
			throw e;
		}
		
		/****************************************/
		stringone_xls=stringone_xls.trim().substring(0,stringone_xls.trim().length()-1);
		//System.out.println(">>>>>>>>>>>>>><RIGA PER RIGA: "+stringone_xls);
		return stringone_xls;
	}
}

