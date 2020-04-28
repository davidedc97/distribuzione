/**
 * @(#)DaoManager.java
 */
package it.gis.egeosDCL.server.DAOUtility;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 * DaoManager ï¿½ una classe per recuperare una connessione a SDI_REPOSITORY.
 * La classe cerca di recuperare una connesione al DB tramite il DataSource 'jdbc/SGUSDIDS'.
 * Nel caso in cui fallisce l'utilizzo del DataSource, la connessione al DB ï¿½ recuperata con il sistema classico. 
 *
 * @author  
 * @version 2.0
 */
public abstract class DaoManager{
	
	protected Connection con 	  = null;
	protected String jndiNameTest = " jdbc/GeoMAPPEDS";
	protected String jndiNamePRE  = " jdbc/GeoMAPPEDS";
	protected String jndiRRNIDS   = "jdbc/RRNIDS";
	
	protected Connection getConnectionTEST() throws Exception{

		DataSource	ds			= null;
		Context initialContext	= null;
		String url 				= "jdbc:oracle:thin:@172.21.5.92:1523:prod10";

		try{
		/* Recupero la Connesione al DB tramite datasource con jndiName 'jdbc/SGUSDIDS'. */	
			initialContext = new InitialContext();
			ds=(DataSource)initialContext.lookup(jndiNameTest);
			
			if (ds!=null){
				con = ds.getConnection();
			}else{
			/* se il DS ï¿½ nullo recupero la connessione con il metodo classico.*/	
				Class.forName ("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(url,"SITIPROD","PRE_SITIPROD");
			}
		}catch(NamingException ne){
		/* Recupero la connessione con il metodo classico[nel caso in cui non si riesce a recuperare il DS dal contesto].*/	
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,"SITIPROD","PRE_SITIPROD");
		}
		return con;		
	}
	
	protected Connection getConnectionPOSTGRES() throws Exception{

		DataSource	ds			= null;
		Context initialContext	= null;
		String url 				= "jdbc:postgresql://localhost:5432/postgis_21_sample";//locale lavoro
	//	String url 				= "jdbc:postgresql://localhost:5432/dbgis";//external
		
		try{
		/* Recupero la Connesione al DB tramite datasource con jndiName 'jdbc/SGUSDIDS'. */	
//			initialContext = new InitialContext();
//			ds=(DataSource)initialContext.lookup(jndiNamePRE);
//		
//			//System.out.println("org.postgresql.Driver.getVersion(); "+org.postgresql.Driver.getVersion());
//			
//			if (ds!=null){
//				con = ds.getConnection();
//				
//			}else{
			/* se il DS ï¿½ nullo recupero la connessione con il metodo classico.*/	
				Class.forName ("org.postgresql.Driver");
				con = DriverManager.getConnection(url,"postgres","bulldog77");//locale
				//con = DriverManager.getConnection(url,"gis","$g1s%");//remoto
	//		}
		}catch(Exception ne){
			ne.printStackTrace(); 
		/* Recupero la connessione con il metodo classico[nel caso in cui non si riesce a recuperare il DS dal contesto].*/	
			Class.forName ("org.postgresql.Driver");
			con = DriverManager.getConnection(url,"postgres","bulldog77");//locale
		//	con = DriverManager.getConnection(url,"gis","$g1s%");//remoto
			ne.printStackTrace();
		}
		return con;		
	}
	
	/**
	 * OTTIENE UNA CONNESSIONE IN AMBIENTE DI PREESERCIZIO
	 * @return
	 * @throws Exception
	 */
	protected Connection getConnectionPRE() throws Exception{

		DataSource	ds			= null;
		Context initialContext	= null;
		String url 				= "jdbc:oracle:thin:@172.21.5.140:1523:prod10a";
	//	String url 				=" (DESCRIPTION = (ADDRESS_LIST = (ENABLED = BROKEN) (LOAD_BALANCE = ON)			      (ADDRESS = (PROTOCOL = TCP) (HOST = 172.21.5.125) (PORT = 1523))			      (ADDRESS = (PROTOCOL = TCP) (HOST = 172.21.5.126) (PORT = 1523))			    )			    (CONNECT_DATA =			      (SERVICE_NAME = prod10)			      (FAILOVER_MODE = (TYPE = SELECT) (METHOD = BASIC) (RETRIES = 4) (DELAY = 4))			    )			)";
//		String url 				= "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS_LIST =(LOAD_BALANCE = ON)(FAILOVER = ON)(ADDRESS = (PROTOCOL = TCP)(Host = 172.21.5.78)(Port = 1521))(ADDRESS = (PROTOCOL = TCP)(Host = 172.21.5.79)(Port = 1521)))(SDU = 8192)(TDU = 8192)(CONNECT_DATA =(SERVICE_NAME = prod11)(FAILOVER_MODE = (TYPE = SELECT) (RETRIES = 20) (DELAY = 15))))";

		try{
		/* Recupero la Connesione al DB tramite datasource con jndiName 'jdbc/SGUSDIDS'. */	
			initialContext = new InitialContext();
			ds=(DataSource)initialContext.lookup(jndiNamePRE);
			
			if (ds!=null){
				con = ds.getConnection();
			}else{
			/* se il DS ï¿½ nullo recupero la connessione con il metodo classico.*/	
				Class.forName ("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(url,"SITIPROD","PRE_SITIPROD");
			}
		}catch(NamingException ne){
		/* Recupero la connessione con il metodo classico[nel caso in cui non si riesce a recuperare il DS dal contesto].*/	
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,"SITIPROD","PRE_SITIPROD");
		}
		return con;		
	}
	
	
	
	protected Connection getConnectionRRNIDS() throws Exception{

		DataSource	ds			= null;
		Context initialContext	= null;
		String url 				= "jdbc:oracle:thin:@172.21.1.111:1524:testdss";

		try{
		
			initialContext = new InitialContext();
			ds=(DataSource)initialContext.lookup(jndiRRNIDS);
			
			if (ds!=null){
				con = ds.getConnection();
			}else{
			/* se il DS ï¿½ nullo recupero la connessione con il metodo classico.*/	
				Class.forName ("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(url,"USR_RRNI","USR_RRNI");
			}
		}catch(NamingException ne){
		/* Recupero la connessione con il metodo classico[nel caso in cui non si riesce a recuperare il DS dal contesto].*/	
			Class.forName ("oracle.jdbc.driver.OracleDriver");
			con = DriverManager.getConnection(url,"USR_RRNI","USR_RRNI");
		}
		return con;		
	}
	
	
	protected Connection getConnectionDirect() throws Exception{
		DataSource	ds			= null;
		Context initialContext	= null;
//		String url 				= "jdbc:oracle:thin:@172.21.5.126:1523:prod10b";
		String url 				= "jdbc:oracle:thin:@172.21.5.125:1523:prod10a";
//		String url 				= "jdbc:oracle:thin:@(DESCRIPTION =(ADDRESS_LIST = (ENABLED = BROKEN) (LOAD_BALANCE = ON)(ADDRESS = (PROTOCOL = TCP) (HOST = 172.21.5.125) (PORT = 1523))(ADDRESS = (PROTOCOL = TCP) (HOST = 172.21.5.126) (PORT = 1523)))(CONNECT_DATA =(SERVICE_NAME = prod10)(FAILOVER_MODE = (TYPE = SELECT) (METHOD = BASIC) (RETRIES = 4) (DELAY = 4))))";
		
		
		try{
			/* se il DS è nullo recupero la connessione con il metodo classico.*/
				Class.forName ("oracle.jdbc.driver.OracleDriver");
				con = DriverManager.getConnection(url,"egeosappe1","egeosappe1");
		}catch(Exception ne){
		/* Recupero la connessione con il metodo classico[nel caso in cui non si riesce a recuperare il DS dal contesto].*/
			ne.printStackTrace();
		}
		return con;		
	}	
}
