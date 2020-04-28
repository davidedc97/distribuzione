package it.gis.egeosDCL.server.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
import org.json.JSONArray;
import org.json.JSONObject;
*/
import it.gis.egeosDCL.server.DAOUtility.DaoManager;

public class ExcelDAO  extends DaoManager{
	
	public String getRiepilogo() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		StringBuffer sb = null;
		
		String html_string = "<html>";
		html_string += "<body>";
		html_string +="<table>";
		
		sb = new StringBuffer();
		
//		sb.append(" select deno_prov \"Provincia\",                                                      ");
//		sb.append("        count(distinct b.grid_id) \"Totale Quadranti\",                               ");
//		sb.append("        count(distinct(decode(a.fl_stato,4,a.grid_id,null))) \"Quadranti Validati\",  ");
//		sb.append("        count(distinct(decode(a.fl_stato,5,a.grid_id,null))) \"Quadranti Lavorati\",  ");
//		sb.append("        count(distinct(decode(a.fl_stato,4,null,5,null,a.grid_id)))                 ");
//		sb.append("                              /*(select z.grid_id                                   ");
//		sb.append("                                 from desegeosdbae.quadri_utente_asse_refresh z     ");
//		sb.append("                                where z.id_asse_utente_refresh is not null          ");
//		sb.append("                                  and z.grid_id = a.grid_id                         ");
//		sb.append("                                  and z.data_fine > sysdate)                        ");
//		sb.append("                                 ))) */  \"Quadranti Assegnati\"                      ");
//		sb.append("   from egeosdbae.quadri_lavor a,                                                   ");
//		sb.append("        egeosdbae.grid_table_comu b,                                                ");
//		sb.append("        cata_prov c                                                                 ");
//		sb.append("  where a.grid_id = b.grid_id                                                       ");
//		sb.append("    and a.data_fine > sysdate                                                       ");
//		sb.append("    and B.DATA_FINE > sysdate                                                       ");
//		sb.append("    and b.id_prov = c.id_prov                                                       ");
//		sb.append("    and exists (select 1                                                            ");
//		sb.append("                  from egeosdbae.elenchi_header_amministrazioni z                   ");
//		sb.append("                 where z.id_provincia = c.id_prov                                   ");
//		sb.append("                   )                                                                ");
//		sb.append("   group by deno_prov                                                               ");
//		sb.append("  order by 3 desc                                                                   ");

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		sb.append("  select deno_prov \"Provincia\",  "); 
		sb.append("  count(distinct b.grid_id) \"Totale Quadranti\",  ");
		sb.append("  count(distinct(decode(a.fl_stato,4,a.grid_id,null))) \"Quadranti Validati\",  ");  
		sb.append("  count(distinct(decode(a.fl_stato,5,a.grid_id,null))) \"Quadranti Lavorati\",  ");
		sb.append("  count(distinct(decode(a.fl_stato,4,null,5,null,  ");
		sb.append("  (select  max(z.grid_id)    ");
		sb.append("  				from  egeosdbae.quadri_utente_asse_refresh z  ");
		sb.append("  				where z.grid_id = a.grid_id  ");
		sb.append("  						and z.data_fine > sysdate)  ");
		sb.append("  		)))   \"Quadranti Assegnati\"  ");
		sb.append("  				from egeosdbae.quadri_lavor a,  ");
		sb.append("  		egeosdbae.grid_table_comu b,  ");
		sb.append("  cata_prov c  ");
		sb.append("  		where a.grid_id = b.grid_id  ");
		sb.append("  and a.data_fine > sysdate  ");
		sb.append("  		and B.DATA_FINE > sysdate  ");
		sb.append("  		and b.id_prov = c.id_prov  ");
		sb.append("  		and exists (select 1   ");
		sb.append("  						from egeosdbae.elenchi_header_amministrazioni z  ");
		sb.append("  						where z.id_provincia = c.id_prov  ");
		sb.append("  				and z.num_elenco = 15728  ");
		sb.append("  				)  ");
		sb.append("  		group by deno_prov  ");
		sb.append("  				order by 3 desc  ");		
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////		
		
		try {
//			conn = getConnectionPRE();
			conn = getConnectionDirect();
			
			ps = conn.prepareStatement(sb.toString());

		//	log.debug("QUERY DENOMINAZIONE:  " + sb.toString());
			rs = ps.executeQuery();
			
			html_string +="<tr><th>Provincia</th>";
			html_string +="<th>Totale Quadranti</th>";
			html_string +="<th>Quadranti Validati</th>";
			html_string +="<th>Quadranti Lavorati</th>";
			html_string +="<th>Quadranti Assegnati</th></tr>";
			
			while (rs.next()) {
				html_string +="<tr>";
	
				html_string +="<td>"+rs.getString(1)+"</td>";
				html_string +="<td>"+rs.getString(2)+"</td>";
				html_string +="<td>"+rs.getString(3)+"</td>";
				html_string +="<td>"+rs.getString(4)+"</td>";
				html_string +="<td>"+rs.getString(5)+"</td>";
				
				html_string +="</tr>";
			}
			html_string +="</table></body></html>";
			
		} catch (Exception e) {
		/*	log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	    	log.error(">>>>>>>>>>>>>>>ERRORE NELLA GET DATI CUAA ",e);
	    	log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");*/
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return html_string;

	}
	
	
	
	public String getDettaglio() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Connection conn = null;
		StringBuffer sb = null;
		
		String html_string = "<html>";
		html_string += "<body>";
		html_string +="<table>";
		
		sb = new StringBuffer();

		try {
//			conn = getConnectionPRE();
			conn = getConnectionDirect();


//////////////////////////////////////////////////////////////////////////////////////////////
//			sb.append("  select c.deno_prov \"Provincia\", e.nome \"Comune\",   ");
//			sb.append("     b.grid_id \"Id Quadrante\",   ");
//			sb.append("     decode(A.FL_STATO,0,'Assegnato',4,'Validato',5,'Lavorato','N/A') \"Stato Quadrante\"  ,   ");
//			sb.append("        utente_sosp \"Utente Lavorazione\",   ");
//			sb.append("        to_char((select max(data_inizio_val) from egeosdbae.rf_quadro_suolo_mia where grid_id = a.grid_id and data_fine_val > sysdate),'dd/mm/yyyy hh24:mi:ss') \"Data Lavorazione\"   ");
//			sb.append("     from egeosdbae.quadri_lavor a,   ");
//			sb.append("        egeosdbae.grid_table_comu b,   ");
//			sb.append("        egeosdbae.utenti_comuni_refresh d,   ");
//			sb.append("        EGEOSDBAE.UTENTI_REFRESH y,   ");
//			sb.append("        cata_prov c,   ");
//			sb.append("        cata_comu e   ");
//			sb.append("     where a.grid_id = b.grid_id   ");
//			sb.append("     and a.data_fine > sysdate   ");
//			sb.append("     and Y.DATA_FINE > sysdate   ");
//			sb.append("     and Y.DESC_UTENTE = A.UTENTE_SOSP   ");
//			sb.append("     and B.DATA_FINE > sysdate   ");
//			sb.append("     and b.id_prov = c.id_prov   ");
//			sb.append("     and e.fine > sysdate   ");
//			sb.append("     and e.id_comune = b.id_comune   ");
//			sb.append("     and e.id_prov = c.id_prov   ");
//			sb.append("     and D.DATA_FINE > sysdate   ");
//			sb.append("     and D.ID_COMUNE_REFRESH = B.ID_COMUNE   ");
//			sb.append("     and D.NUM_ELENCO = 15728   ");
//			sb.append("     and A.FL_STATO = 5   ");
//			sb.append("     order by 1 desc			   ");
/////////////////////////////////////////////////////////////////////////////////			
			sb.append(" select    c.deno_prov \"Provincia\", e.nome \"Comune\",                                                                                                                          ");
			sb.append("            b.grid_id \"Id Quadrante\",                                                                                                                                         ");
			sb.append("            decode(A.FL_STATO,0,'Assegnato',4,'Validato',5,'Lavorato','N/A') \"Stato Quadrante\"  ,                                                                             ");
			sb.append("               utente_sosp \"Utente Lavorazione\",                                                                                                                              ");
			sb.append("               to_char((select min(data_inizio) from egeosdbae.quadri_lavor ff where ff.grid_id = a.grid_id and ff.fl_stato = 5),'dd/mm/yyyy hh24:mi:ss') \"Data Lavorazione\"  "); 
			sb.append("            from egeosdbae.quadri_lavor a,                                                                                                                                    ");
			sb.append("               egeosdbae.grid_table_comu b,                                                                                                                                   ");
			sb.append("               egeosdbae.utenti_comuni_refresh d,                                                                                                                             ");
			sb.append("               EGEOSDBAE.UTENTI_REFRESH y,                                                                                                                                    ");
			sb.append("               cata_prov c,                                                                                                                                                   ");
			sb.append("               cata_comu e                                                                                                                                                    ");
			sb.append("            where a.grid_id = b.grid_id                                                                                                                                       ");
			sb.append("            and a.data_fine > sysdate                                                                                                                                         ");
			sb.append("            and Y.DATA_FINE > sysdate                                                                                                                                         ");
			sb.append("            and Y.DESC_UTENTE = A.UTENTE_SOSP                                                                                                                                 ");
			sb.append("            and Y.ID_UTENTE_REFRESH = D.ID_UTENTE_REFRESH                                                                                                                     ");
			sb.append("            and B.DATA_FINE > sysdate                                                                                                                                         ");
			sb.append("            and b.id_prov = c.id_prov                                                                                                                                         ");
			sb.append("            and e.fine > sysdate                                                                                                                                              ");
			sb.append("            and e.id_comune = b.id_comune                                                                                                                                     ");
			sb.append("           and e.id_prov = c.id_prov                                                                                                                                          ");
			sb.append("            and D.DATA_FINE > sysdate                                                                                                                                         ");
			sb.append("            and D.ID_COMUNE_REFRESH = B.ID_COMUNE                                                                                                                             ");
			sb.append("            and D.NUM_ELENCO = 15728                                                                                                                                          ");
			sb.append("            and A.FL_STATO = 5                                                                                                                                                ");
			sb.append("            and c.fine > sysdate                                                                                                                                              ");
			sb.append("            order by 1 desc                                                                                                                                                   ");
			
			ps = conn.prepareStatement(sb.toString());
			
		
			rs = ps.executeQuery();
			
			html_string +="<tr><th>Provincia</th>";
			html_string +="<th>Comune</th>";
			html_string +="<th>ID Quadrante</th>";
			html_string +="<th>Stato quadrante</th>";
			html_string +="<th>Utente lavorazione</th>";
			html_string +="<th>Data lavorazione</th></tr>";
			
			while (rs.next()) {
				html_string +="<tr>";
	
				html_string +="<td>"+rs.getString(1)+"</td>";
				html_string +="<td>"+rs.getString(2)+"</td>";
				html_string +="<td>"+rs.getString(3)+"</td>";
				html_string +="<td>"+rs.getString(4)+"</td>";
				html_string +="<td>"+rs.getString(5)+"</td>";
				html_string +="<td>"+rs.getString(6)+"</td>";
				
				html_string +="</tr>";
			}
			html_string +="</table></body></html>";
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return html_string;

	}

	
	public String getRiepilogoEFA(String reg,String prov,String numeElenco) {
		PreparedStatement ps = null;
		ResultSet 	rs = null;
		Connection conn = null;
		StringBuffer sb = null;
		
		String html_string = "<html>";
		html_string += "<body>";
		html_string +="<table>";
		
		sb = new StringBuffer();
		
		/*
		sb.append("  select decode(a.num_elenco,15729,'Efa foto 2014',15732,'Efa foto 2012',15733, 'Efa foto 2013','Altro') as  \"Elenco rif.\",                 ");
		sb.append("         deno_prov \"Provincia\",                                                                                                             ");
		sb.append("         count(distinct b.grid_id) as \"Totale Quadranti\",                                                                                      ");
		sb.append("         count(distinct(decode(a.fl_stato,4,a.grid_id,null))) as \"Quadranti Validati\",                                                         ");
		sb.append("         count(distinct(decode(a.fl_stato,5,a.grid_id,null))) as \"Quadranti Lavorati\",                                                         ");
		sb.append("         count(distinct(decode(a.fl_stato,4,null,5,null,    (                                                                               ");
		sb.append("         select max(z.grid_id)                                                                                                              ");
		sb.append("         from  egeosdbae.quadri_utente_asse_refresh z                                                                                       ");
		sb.append("         where z.grid_id = a.grid_id                                                                                                        ");
		sb.append("         and z.data_fine > sysdate)              )))   \"Quadranti Assegnati\"                                                                ");
		sb.append("         from egeosdbae.quadri_lavor a,                                                                                                     ");
		sb.append("         egeosdbae.grid_table_comu b,                                                                                                       ");
		sb.append("         cata_prov c                                                                                                                        ");
		sb.append("         where a.grid_id = b.grid_id                                                                                                        ");
		sb.append("         and b.flag_azie_efa = 1                                                                                                            ");
		sb.append("         and a.data_fine > sysdate                                                                                                          ");
		sb.append("         and B.DATA_FINE > sysdate                                                                                                          ");
		sb.append("         and b.id_prov = c.id_prov                                                                                                          ");
		
		if(reg.trim().length()>0){
			sb.append("         and c.id_regi in( " + reg +" )                                                                                                    ");			
		}
		if(prov!=null && prov.trim().length()>0){
			sb.append("         and c.id_prov in(" + prov.replace("\"","")  +" )                                                                                                 ");			
		}
		sb.append("         and exists (select 1                                                                                                               ");
		sb.append("         from egeosdbae.elenchi_header_amministrazioni z                                                                                    ");
		sb.append("         where z.id_provincia = c.id_prov                                                                                                   ");
		sb.append("         and z.num_elenco = a.num_elenco                                                                                                    ");
		sb.append("         and z.num_elenco = "+numeElenco+"                                                                                           ");
		sb.append("         group by decode(a.num_elenco,15729,'Efa foto 2014',15732,'Efa foto 2012',15733, 'Efa foto 2013','Altro'), deno_prov                ");                   
		sb.append("         order by 3 desc ");
		*/
		sb.append(" select decode(a.num_elenco,15729,'Efa foto 2014',15732,'Efa foto 2012',15733, 'Efa foto 2013','Altro') as  \"Elenco rif.\",				  ");
		sb.append(" deno_prov \"Provincia\",                                                                                                                    ");
		sb.append(" count(distinct b.grid_id) as \"Totale Quadranti\",                                                                                          ");
		sb.append(" count(distinct(decode(a.fl_stato,4,a.grid_id,null))) as \"Quadranti Validati\",                                                             ");
		sb.append(" count(distinct(decode(a.fl_stato,5,a.grid_id,null))) as \"Quadranti Lavorati\",                                                             ");
		sb.append(" count(distinct(decode(a.fl_stato,4,null,5,null,(select max(z.grid_id)                                                                     ");
		sb.append(" from  egeosdbae.quadri_utente_asse_refresh z                                                                                              ");
		sb.append(" where z.grid_id = a.grid_id                                                                                                               ");
		sb.append(" and z.data_fine > sysdate))))   \"Quadranti Assegnati\"                                                                                     ");
		sb.append(" from egeosdbae.quadri_lavor a,                                                                                                            ");
		sb.append(" egeosdbae.grid_table_comu b,                                                                                                              ");
		sb.append(" SITIPROD.cata_prov c                                                                                                                      ");
		sb.append(" where a.grid_id = b.grid_id                                                                                                               ");
		sb.append(" and b.flag_azie_efa = 1                                                                                                                   ");
		sb.append(" and a.data_fine > sysdate                                                                                                                 ");
		sb.append(" and B.DATA_FINE > sysdate                                                                                                                 ");
		sb.append(" and b.id_prov = c.id_prov                                                                                                                 ");
		
		if(reg.trim().length()>0){
			sb.append("         and c.id_regi in( " + reg +" )                                                                                                ");			
		}
		if(prov!=null && prov.trim().length()>0){
			sb.append("         and c.id_prov in(" + prov.replace("\"","")  +" )                                                                              ");			
		}
		
		sb.append(" and exists (select 1 from egeosdbae.elenchi_header_amministrazioni z                                                                      ");
		sb.append("             where z.id_provincia = c.id_prov                                                                                              ");
		sb.append("             and z.num_elenco = a.num_elenco                                                                                               ");
		sb.append("             and z.num_elenco = "+numeElenco+")                                                                                            ");
		sb.append("             group by decode(a.num_elenco,15729,'Efa foto 2014',15732,'Efa foto 2012',15733, 'Efa foto 2013','Altro'), deno_prov           ");
		sb.append(" order by 3 desc                                                                                                                           ");
		
		//System.out.println("QUERY getRiepilogoEFA: "+sb.toString());
		
		try {
			conn = getConnectionDirect();
			
			ps = conn.prepareStatement(sb.toString());

			rs = ps.executeQuery();
			
			html_string +="<tr><th>Elenco rif.</th>";
			html_string +="<th>Provincia</th>";
			html_string +="<th>Totale Quadranti</th>";
			html_string +="<th>Quadranti Validati</th>";
			html_string +="<th>Quadranti Lavorati</th>";
			html_string +="<th>Quadranti Assegnati</th></tr>";
			
			while (rs.next()) {
				html_string +="<tr>";
	
				html_string +="<td>"+rs.getString(1)+"</td>";
				html_string +="<td>"+rs.getString(2)+"</td>";
				html_string +="<td>"+rs.getString(3)+"</td>";
				html_string +="<td>"+rs.getString(4)+"</td>";
				html_string +="<td>"+rs.getString(5)+"</td>";
				html_string +="<td>"+rs.getString(6)+"</td>";
				
				html_string +="</tr>";
			}
			html_string +="</table></body></html>";
			
		} catch (Exception e) {
		/*	log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
	    	log.error(">>>>>>>>>>>>>>>ERRORE NELLA GET DATI CUAA ",e);
	    	log.error(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");*/
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return html_string;

	}
	
	public String getDettaglioEFA(String reg,String prov,String numeElenco) {
		
		PreparedStatement 	ps 		= null;
		ResultSet 			rs 		= null;
		Connection 			conn 	= null;
		StringBuffer 		sb 		= null;
		
		String html_string = "<html>";
		html_string += "<body>";
		html_string +="<table>";
		
		sb = new StringBuffer();

		try {

			conn = getConnectionDirect();
/*
			sb.append("  select c.deno_prov \"Provincia\", e.nome \"Comune\",																  ");
			sb.append("  decode(a.num_elenco,15729,'Efa foto 2014',15732,'Efa foto 2012',15733, 'Efa foto 2013','Altro') as  \"Elenco rif.\", ");       
			sb.append("  b.grid_id \"Id Quadrante\",                                                                                          ");
			sb.append("  decode(A.FL_STATO,0,'Assegnato',4,'Validato',5,'Lavorato','N/A') \"Stato Quadrante\"  ,                              ");
			sb.append("  utente_sosp \"Utente Lavorazione\",                                                                                  ");
			sb.append("  to_char(a.data_inizio,'dd/mm/yyyy hh24:mi:ss') \"Data Lavorazione\"                                                  ");
			sb.append("  from egeosdbae.quadri_lavor a,                                                                                     ");
			sb.append("       egeosdbae.grid_table_comu b,                                                                                  ");
			sb.append("       EGEOSDBAE.UTENTI_REFRESH y,                                                                                   ");
			sb.append("       cata_prov c,                                                                                                  ");
			sb.append("       cata_comu e                                                                                                   ");
			sb.append("  where a.grid_id = b.grid_id                                                                                        ");
			sb.append("  and a.data_fine > sysdate                                                                                          ");
			sb.append("  and B.DATA_FINE > sysdate                                                                                          ");
			//sb.append("  and b.flag_azie_efa = 1                                                                                            ");
			sb.append("  and A.NUM_ELENCO = "+numeElenco+"                                                                            ");
			sb.append("  and A.FL_STATO = 5                                                                                                 ");
			sb.append("  and Y.DATA_FINE > sysdate                                                                                          ");
			sb.append("  and b.id_prov = c.id_prov                                                                                          ");
			sb.append("  and e.fine > sysdate                                                                                               ");
			sb.append("  and e.id_comune = b.id_comune                                                                                      ");
			sb.append("  and e.id_prov = c.id_prov                                                                                          ");
			
			if(reg.trim().length()>0){
				
				sb.append("         and c.id_regi = " + reg +"                                                                              ");			
			}			
			if(prov.trim().length()>0){
				sb.append("         and c.id_prov = " + prov +"                                                                                                  ");			
			}
			
			sb.append("  and Y.DESC_UTENTE = A.UTENTE_SOSP                                                                                  ");                                                                                                                 
			*/
			
		/*
		 *  ultima
		 * 	sb.append(" select c.deno_prov \"Provincia\", e.nome \"Comune\",											");
			sb.append(" cc.descrizione as  \"Elenco rif.\",                                                           ");
			sb.append(" b.grid_id \"Id Quadrante\",                                                                   ");
			sb.append(" decode(A.FL_STATO,0,'Assegnato',4,'Validato',5,'Lavorato','N/A') \"Stato Quadrante\"  ,       ");   
			sb.append(" utente_sosp \"Utente Lavorazione\",                                                           ");
			sb.append(" to_char(a.data_inizio,'dd/mm/yyyy hh24:mi:ss') \"Data Lavorazione\"                           ");
			sb.append(" from egeosdbae.quadri_lavor a,                                                              ");
			sb.append("      egeosdbae.grid_table_comu b,                                                           ");
			sb.append("      EGEOSDBAE.UTENTI_REFRESH y,                                                            ");
			sb.append("      sitiprod.cata_prov c,                                                                  ");
			sb.append("      sitiprod.cata_comu e,                                                                  ");
			sb.append("      egeosdbae.contesti_applicativi cc                                                      ");
			sb.append(" where a.grid_id = b.grid_id                                                                 ");
			sb.append(" and a.data_fine > sysdate                                                                   ");
			sb.append(" and B.DATA_FINE > sysdate                                                                   ");
			sb.append(" and (b.flag_azie_efa = 1 and "+numeElenco+" in (15729,15732,15733))                                  ");
			sb.append(" and A.NUM_ELENCO = "+numeElenco+"                                                                    ");
			sb.append(" and cc.num_elenco = a.num_elenco                                                            ");
			sb.append(" and A.FL_STATO = 5                                                                          ");
			sb.append(" and Y.DATA_FINE > sysdate                                                                   ");
			sb.append(" and b.id_prov = c.id_prov                                                                   ");
			
			if(reg.trim().length()>0){
				
				sb.append("         and c.id_regi = " + reg +"                                                     ");			
			}			
			
			if(prov.trim().length()>0){
				sb.append("         and c.id_prov = " + prov +"                                                     ");			
			}
			
			sb.append(" and e.fine > sysdate                                                                        ");
			sb.append(" and e.id_comune = b.id_comune                                                               ");
			sb.append(" and e.id_prov = c.id_prov                                                                   ");
			sb.append(" and Y.DESC_UTENTE = A.UTENTE_SOSP                                                           ");*/
			
			sb.append(" select id_prov, id_comune, deno_prov provincia, (select max(nome) from  sitiprod.cata_comu z where z.id_comune = appo.id_comune) nome,   "); 
			sb.append("        count(distinct appo.grid_id) tot_quad,                                                                                            ");
			sb.append("        count(distinct(decode(appo.fl_stato,4,appo.grid_id,null))) quad_vali,                                                             ");
			sb.append("        count(distinct(decode(appo.fl_stato,5,appo.grid_id,null))) quad_lavo,                                                             ");
			sb.append("        count(distinct(decode(appo.fl_stato,-1,appo.grid_id,null))) quad_asse,                                                            ");
			sb.append("        count(distinct(decode(appo.fl_stato,-2,appo.grid_id,null))) quad_da_asse                                                          ");
			sb.append("        from  (                                                                                                                           ");
			sb.append(" select id_prov, id_comune, b.deno_prov ,id_regi,                                                                                         ");
			sb.append("         decode(a.fl_stato,4,4,5,5,(case when                                                                                             ");
			sb.append("                              (select distinct z.grid_id                                                                                  ");
			sb.append("                                 from  egeosdbae.quadri_utente_asse_refresh z                                                             ");
			sb.append("                                where z.grid_id = a.grid_id                                                                               ");
			sb.append("                                  and exists (select 1                                                                                    ");
			sb.append("                                                from EGEOSDBAE.UTENTI_COMUNI_REFRESH ff                                                   ");
			sb.append("                                               where FF.ID_ASSE_UTENTE_REFRESH = Z.ID_UTENTE_ASSE_REFRESH                                 ");
			sb.append("                                                 and ff.num_elenco = a.num_elenco                                                         ");
			sb.append("                                                 and ff.data_fine > sysdate)                                                              ");
			sb.append("                                  and z.data_fine > sysdate                                                                               ");
			sb.append("                                 ) >0 then -1 else -2 end)) fl_stato,                                                                     ");
			sb.append("                                 a.grid_id                                                                                                ");
			sb.append("   from egeosdbae.quadri_lavor a,                                                                                                         ");
			sb.append("        (select distinct b.grid_id , b.id_comune,b.id_prov, c.deno_prov, c.id_regi                                                        ");
			sb.append("           from egeosdbae.grid_table_comu b,sitiprod.cata_prov c                                                                          ");
			sb.append("           where b.data_fine > sysdate                                                                                                    ");
			sb.append("             and b.id_prov = c.id_prov                                                                                                    ");
			sb.append("             and nvl(buffered,0) = 0                                                                                                      ");
			if(reg.trim().length()>0){
				
				sb.append("         and c.id_regi = " + reg +"                                                     ");			
			}			
			
			if(prov.trim().length()>0){
				sb.append("         and c.id_prov = " + prov +"                                                     ");			
			}
			sb.append("             and exists (select 1                                                                                                         ");
			sb.append("                           from egeosdbae.elenchi_header_amministrazioni z                                                                ");
			sb.append("                          where z.id_provincia = c.id_prov                                                                                ");
			sb.append("                            and z.num_elenco =  "+numeElenco+" )) b                                                                                  ");
			sb.append("  where a.grid_id = b.grid_id                                                                                                             ");
			sb.append("    and a.num_elenco =  "+numeElenco+"                                                                                                               ");
			sb.append("    and a.data_fine > sysdate                                                                                                             ");
			sb.append("    and exists (select 1 from sitiv2.elenchi_quadri gg where gg.id_quadro = a.grid_id and gg.num_elenco = A.NUM_ELENCO)) appo             ");
			sb.append("   group by id_regi,deno_prov, id_prov, id_comune                                                                                         ");
			sb.append("  order by 8 desc                                                                                                                         ");
			
			
			//System.out.println("QUERY getDettaglioEFA: "+sb.toString());
			
			ps = conn.prepareStatement(sb.toString());
			ps.setFetchSize(5000);
			
			rs = ps.executeQuery();
			
			
			html_string +="<tr><th>Provincia</th>";
			html_string +="<th>Comune</th>";
			html_string +="<th>Totale Quadranti</th>";
			html_string +="<th>Quadranti Validati</th>";
			html_string +="<th>Quadranti Lavorati</th>";
			html_string +="<th>Quadranti Assegnati</th>";
			html_string +="<th>Quadranti da assegnare</th></tr>";
			
			
			while (rs.next()) {
				html_string +="<tr>";
	
				html_string +="<td>"+rs.getString(1)+"</td>";
				html_string +="<td>"+rs.getString(2)+"</td>";
				html_string +="<td>"+rs.getString(3)+"</td>";
				html_string +="<td>"+rs.getString(4)+"</td>";
				html_string +="<td>"+rs.getString(5)+"</td>";
				html_string +="<td>"+rs.getString(6)+"</td>";
				html_string +="<td>"+rs.getString(7)+"</td>";
				
				html_string +="</tr>";
			}
			html_string +="</table></body></html>";
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return html_string;

	}
}
