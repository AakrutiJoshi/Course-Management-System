package MyLogger;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.Level;

public class loggerClass {
	private static Logger logger=Logger.getLogger("loggerClass");
	static{
		try {
			String path=System.getProperty("user.dir") + System.getProperty("file.separator") + "logFile.log";
			System.out.println(path+"createdfile");
			FileHandler fh = new FileHandler(path, true);

			/* FileHandler fh=new FileHandler("logFile.log",true); */
			System.out.println("createdfile");
			/*
			 * FileHandler fh= new
			 * FileHandler(System.getProperty("user.dir")+System.getProperty(
			 * "file.separator")+"logFile.log",true);
			 */
		logger.addHandler(fh);
		SimpleFormatter format=new SimpleFormatter();
		fh.setFormatter(format);
		logger.info("logger Initialized");
		}catch(Exception e){
			logger.log(Level.WARNING,"Exception ::",e);
			
		}
	}
	public static void writeToLog(String s)
	{
		logger.info(s);
	}
	public static void writeToLog(String s,Exception e)
	{
		logger.log(Level.WARNING,"s",e);
	}
	
}
