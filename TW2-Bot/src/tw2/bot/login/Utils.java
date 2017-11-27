package tw2.bot.login;

import java.io.IOException;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.XMLFormatter;

public class Utils {
	private static FileHandler fh= null;
	public static synchronized Logger getLogger(Object obj) throws SecurityException,
	IOException {

final Logger log = Logger.getLogger(obj.toString());
Handler consoleHandler = new ConsoleHandler();
consoleHandler.setLevel(Level.FINER);
Logger.getAnonymousLogger().addHandler(consoleHandler);
log.setLevel(Level.FINER);
if(fh==null)
fh = new FileHandler("Log.log", true);
fh.setFormatter(new XMLFormatter());
log.addHandler(fh);
return log;
}
	public  boolean isNumeric(String str)
	{
	    for (char c : str.toCharArray())
	    {
	        if (!Character.isDigit(c)) return false;
	    }
	    return true;
	}
	
	
public static int[] splitDestinationTime(String element){
		
		String[]tokens = element.split("\\s+|[,:-]");
		
			
		//tokens[0] = tokens[0].substring(0, tokens[0].length());
		int[] tpm = new int[3];
		  tpm[0] =  Integer.parseInt(tokens[0]);
		  tpm[1] =  Integer.parseInt(tokens[1]);
		  tpm[2] = Integer.parseInt(tokens[2]);
		  return tpm;
		  
		
	}

}


