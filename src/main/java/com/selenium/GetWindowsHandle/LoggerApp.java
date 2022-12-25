package com.selenium.GetWindowsHandle;

import java.io.IOException;
import java.time.Duration;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoggerApp {
	
	private static final Logger logger=Logger.getLogger(LoggerApp.class.getName());
	// Creating FileHandler
	static FileHandler fileHandler;

	public static void main(String[] args) throws SecurityException, IOException, InterruptedException {
		 // We are setting handler to true = append data to file
		fileHandler = new FileHandler("C:/Users/HP/eclipse-workspace/SeleniumTutorial Maven Project/App-log.log", true);
		// Assigning handler to logger
		logger.addHandler(fileHandler);
		
		 // Print a brief summary of the LogRecord in a human readable format.
        SimpleFormatter formatter = new SimpleFormatter();    
        fileHandler.setFormatter(formatter);
     // Format a LogRecord into a standard XML format. Uncomment below 2 lines to see XML result.
        
        // XMLFormatter formatter2 = new XMLFormatter();
        // fileHandler.setFormatter(formatter2);
        
        int n = 1;
        // infinite loop
        while (n<5) {
            // Log an INFO message.
        	logger.info("Adding App Log line: " + n);
            Thread.sleep(1000);
            n++;
        }
        
//		String driverPath = "C:\\Users\\HP\\Selenium-Java\\BrowserDrivers\\chromedriver.exe";
//		String driverName="webdriver.chrome.driver";
//		System.setProperty(driverName, driverPath);
//		logger.info("Driver Path"+driverPath);
//		logger.info(""+driverName);

		
//		WebDriver webDriver = new ChromeDriver();
//		WebDriverManager.chromedriver().setup();
		
//		webDriver.get("https://www.amazon.in/ap/signin?openid.pape.max_auth_age=900&openid.return_to=https%3A%2F%2Fwww.amazon.in%2Fgp%2Fyourstore%2Fhome%3Fpath%3D%252Fgp%252Fyourstore%252Fhome%26useRedirectOnSuccess%3D1%26signIn%3D1%26action%3Dsign-out%26ref_%3Dnav_AccountFlyout_signout&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0");
//		logger.info("URL: "+webDriver.getCurrentUrl());
//		logger.info("Title: "+webDriver.getTitle());
//		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
//		webDriver.findElement(By.xpath("//input[@type='email' and @id='ap_email']")).sendKeys("skbirajdar10@gmail.com");
//		webDriver.findElement(By.xpath("//input[@type='submit' and @id='continue']")).click();
//		webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10000));
//		logger.info("Browser Close");
//		logger.warning("This is warning");
//		
//		webDriver.quit();
	}
}
