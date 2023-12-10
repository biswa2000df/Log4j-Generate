package Loggertest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Test2  {
	
	static final Logger logger = LogManager.getLogger(Test2.class);
	
	public static void m1() {
		
		 logger.info("WebDriver started  1");

	        // Perform Selenium actions...

	        // Log another message
	        logger.info("Selenium actions completed  1");

	        // Log a final message
	        logger.info("WebDriver closed  1");
		
	}

}
