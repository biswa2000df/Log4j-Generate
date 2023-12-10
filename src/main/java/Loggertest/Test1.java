package Loggertest;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.FileAppender;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.layout.PatternLayout;


public class Test1 {

	  static final Logger logger = LogManager.getLogger(Test1.class);
	
	public static void main(String[] args) {
		
		Test2 t2 = new Test2();
		 configureLog4j();
		
		 logger.info("WebDriver started");

	        // Perform Selenium actions...

	        // Log another message
	        logger.info("Selenium actions completed");

	        // Log a final message
	        logger.info("WebDriver closed");
	        
	       t2.m1();
		

	}
	
	private static void configureLog4j() {
        // Get the current LoggerContext
        LoggerContext loggerContext = (LoggerContext) LogManager.getContext(false);

        // Get the current Log4j configuration
        Configuration config = loggerContext.getConfiguration();

        // Create a FileAppender with a dynamically generated file name
        String logFileName = generateLogFileName();
        FileAppender appender = FileAppender.newBuilder()
                .setName("File")
                .withFileName(logFileName)
//                .withAppend(false)//here  write the append false me u can not append the new run logs only overwrite if u want then make false to true
                .withAppend(true)
                .setLayout(PatternLayout.newBuilder().withPattern("%d{yyyy-MM-dd HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n").build())
                .build();

        // Add the appender to the configuration
        appender.start();
        config.addAppender(appender);

        // Update the LoggerConfig to use the new appender
        LoggerConfig loggerConfig = config.getLoggerConfig(LogManager.ROOT_LOGGER_NAME);
        loggerConfig.addAppender(appender, null, null);

        // Update the configuration
        config.getRootLogger().addAppender(appender, null, null);

        // Update the Log4j context
        loggerContext.updateLoggers();

        // Log a message indicating the new log file
        logger.info("Logging to dynamically created file: {}", logFileName);
    }

    private static String generateLogFileName() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
    	  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        LocalDateTime now = LocalDateTime.now();
        return "logs/selenium_" + now.format(formatter) + ".log";
    }
}
