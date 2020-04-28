package it.gis.egeosDCL.client.utility;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class Utility {

	public static void SystemOut(String text) {
		//System.out.println(text);
	}
	
	public String getSridByRegione(String regione)
	{
		String srid_out = "";
		
		Utility.SystemOut("REGIONE IN INPUT IN getSridByRegione :: "+regione);

		if(regione.equalsIgnoreCase("PIEMONTE")		||
				regione.contains("AOSTA")			||
				regione.equalsIgnoreCase("LOMBARDIA")||
				regione.equalsIgnoreCase("TRENTINO")||
				regione.equalsIgnoreCase("VENETO")	||
				regione.equalsIgnoreCase("LIGURIA")	||
				regione.equalsIgnoreCase("EMILIA ROMAGNA")||
				regione.equalsIgnoreCase("TOSCANA")	||
				regione.equalsIgnoreCase("SARDEGNA")||
				regione.contains("BOLZANO")||
				regione.contains("TRENTO"))
		{
			srid_out = "3003";
		}
		else
		{
			srid_out = "3004";
		}

		return srid_out;
	}
	
	
	public static String restituisciStringaIdComuneDaHashTable(HashMap<String,String> ht)
	{
		String stringa_out = "";
		
		Set list  = ht.keySet();
		Iterator iter = list.iterator();
					
		while(iter.hasNext()) {
		     Object key = iter.next();
		     Object value = ht.get(key);
		     stringa_out = stringa_out+key+",";
		}
		//System.out.println("restituisciStringaIdComuneDaHashTable OUT::: "+stringa_out);
		return stringa_out.substring(0,stringa_out.length()-1);
	}
	
	
	public static String restituisciStringaIdUtenteDaHashTable(HashMap<String,String> ht)
	{
		String stringa_out = "";
		
		Set list  = ht.keySet();
		Iterator iter = list.iterator();
					
		while(iter.hasNext()) {
		     Object key = iter.next();
		     Object value = ht.get(key);
		     stringa_out = stringa_out+value+",";
		}
		//System.out.println("restituisciStringaIdUtenteDaHashTable OUT::: "+stringa_out);
		return stringa_out.substring(0,stringa_out.length()-1);
	}
}
