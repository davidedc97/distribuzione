package it.gis.egeosDCL.server.util;

import javax.servlet.ServletContext;
import javax.servlet.http.*;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class Log4jInit extends HttpServlet
{
	static private Logger logger = Logger.getLogger(Log4jInit.class.getName());

	public Log4jInit()
	{
	}

	public void init()
	{
		try
		{
			ServletContext context = getServletContext();
			java.net.URL url = context.getResource("/WEB-INF/classes/log4j.properties");
			PropertyConfigurator.configure(url);
			System.setProperty("org.apache.commons.logging.simplelog.defaultlog", "error");
			
			logger.info("File di log inizializzato con successo....");
		}
		catch(Exception e)
		{
			logger.error(e.getStackTrace());
		}
	}

	public void doGet(HttpServletRequest httpservletrequest, HttpServletResponse httpservletresponse)
	{
	}
}

