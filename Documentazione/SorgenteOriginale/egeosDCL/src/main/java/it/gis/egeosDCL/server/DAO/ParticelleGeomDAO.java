package it.gis.egeosDCL.server.DAO;

import it.gis.egeosDCL.server.DAOUtility.DaoManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class ParticelleGeomDAO extends DaoManager{
	
	static private Logger logger = Logger.getLogger(ParticelleGeomDAO.class.getName());

	public String getWKTParticellaByChiaveCatastale(String data,String foglio,String numePart,String codiceNaz,String allegato,String sviluppo,String subalterno)
	{
		String 				wkt_particella 	= "";
		Connection 			conn			= null;
		PreparedStatement 	ps 				= null;
		ResultSet 			rs 				= null;
		StringBuffer 		sb 				= null;
		
		try
		{
			sb = new StringBuffer();
		
			conn = getConnectionPOSTGRES();
			//conn = getConnectionRRNIDS();
			
			sb.append(" SELECT  ST_AsTEXT(t.geom)as wkt_out from d785_4326 t                                                 ");
			//sb.append("   WHERE TO_DATE('"+data+"', 'DD/MM/YYYY') BETWEEN T.DATA_INIZIO_VAL AND T.DATA_FINE_VAL   ");
			sb.append(" 	WHERE T.FOGLIO = "+foglio+"                  ");
			sb.append(" 	AND t.___nazionale = '"+codiceNaz+"'      ");
			sb.append(" 	AND t.PARTICELLA = '"+numePart+"'          ");
		
			
			ps = conn.prepareStatement(sb.toString());
			
			logger.error("query getWKTParticellaByChiaveCatastale::: "+sb.toString());
		
			rs = ps.executeQuery();
			
			
			while(rs.next()){
				wkt_particella = rs.getString("wkt_out");
			}
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
			logger.error(e.getStackTrace());
		}
		finally
		{
			try {
				rs.close();
				ps.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				logger.error(e.getStackTrace());
			}
		}
		//System.out.println("WKT OUT: "+wkt_particella);
		return wkt_particella;
	}
	
}
