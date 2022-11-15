package com.selenium.ui;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverInstance {

	public static WebDriver driver = null;
	public static BufferedReader br = null;
	public static String browser = null;
	
	public static void main(String[] args) {

		WebDriverInstance.openUrl();

	}

	public static WebDriver launchWebDriver() {
		
		br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please Select the Execution Way:");
		System.out.println("Press 1 for Launch Browser Using Traditional Way");
		System.out.println("Press 2 for Launch Browser Using WebDriver Manager");
		System.out.println("Press 3 for Exit");
		try {
			int input = Integer.parseInt(br.readLine());
			switch (input) {
			case 1:
				System.out.println("================Launching Browser Using Traditional Way====================");
				browser = getBrowserName();
				System.out.println(browser);
				driver = WebDriverInstance.launchBrowserUsingTraditionalWay(browser);
				System.out.println("====================================");
				break;
			case 2:
				System.out.println("================Launching Browser Using WebDriver Manager====================");
				browser = getBrowserName();
				System.out.println(browser);
				driver = WebDriverInstance.launchBrowserUsingWebDriverManager(browser);
				System.out.println("====================================");
				break;
			case 3:
				break;
			}

		} catch (Exception e) {
			System.out.println("Invalid Input Please Try Again");
			System.out.println(e.getMessage());
		}
		
		return driver;

	}

	public static String getBrowserName() {
		br = new BufferedReader(new InputStreamReader(System.in));
		boolean go = true;
		while (go) {
			System.out.println("Please Select the Browser:");
			System.out.println("Press 1 for Chrome");
			System.out.println("Press 2 for FireFox");
			System.out.println("Press 3 for Edge");
			System.out.println("Press 4 for Exit");
			try {
				int input = Integer.parseInt(br.readLine());
				switch (input) {
				case 1:
					browser = "Chrome";
					go = false;
					break;
				case 2:
					browser = "FireFox";
					go = false;
					break;
				case 3:
					browser = "Edge";
					go = false;
					break;
				case 4:
					go = false;
					break;
				}

			} catch (Exception e) {
				System.out.println("Invalid Input Please Try Again");
				System.out.println(e.getMessage());

			}
		}
		return browser;

	}

	public static WebDriver launchBrowserUsingWebDriverManager(String browser) {
		if (browser.equals("Chrome")) {
//			WebDriverManager.chromedriver().setup();

			WebDriverManager.chromedriver().browserVersion("102.0.5005.115").setup();
			driver = new ChromeDriver();

//			Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
//			String browserName = caps.getBrowserName();
//			String browserVersion = caps.getVersion();
//			System.out.println(browserName + " " + browserVersion);

		} else if (browser.equals("Firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browser.equals("Edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		
		return driver;
	}

	public static WebDriver launchBrowserUsingTraditionalWay(String browser) {
		if (browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver",
					"C:\\Users\\HP\\Selenium-Java\\BrowserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();

			Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();
			String browserName = caps.getBrowserName();
			String browserVersion = caps.getBrowserVersion();
			System.out.println(browserName + " " + browserVersion);

		} else if (browser.equals("Firefox")) {
			System.setProperty("webdriver.gecko.driver",
					"C:\\Users\\HP\\Selenium-Java\\BrowserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equals("Edge")) {
			System.setProperty("webdriver.edge.driver",
					"C:\\Users\\HP\\Selenium-Java\\BrowserDrivers\\msedgedriver.exe");
			driver = new EdgeDriver();
		}
		return driver;

	}

	@Test(priority = 1)
	public static void openUrl() {
		if (driver != null) {
			driver.get("https://www.saucedemo.com/");
			driver.manage().window().maximize();
			System.out.println(driver.getCurrentUrl());
			System.out.println(driver.getTitle());
			WebElement txtUsername = driver.findElement(By.id("user-name"));
			Actions builder = new Actions(driver);
			Action seriesOfActions = builder
				.moveToElement(txtUsername)
				.click()
				.keyDown(txtUsername, Keys.SHIFT)
				.sendKeys(txtUsername, "hello")
				.keyUp(txtUsername, Keys.SHIFT)
				.doubleClick(txtUsername)
				.contextClick()
				.build();
				
			seriesOfActions.perform() ;
			driver.findElement(By.id("user-name")).sendKeys("standard_user");
			driver.findElement(By.id("password")).sendKeys("secret_sauce");
			driver.findElement(By.xpath("//input[@id='login-button']")).click();

			// to perform scroll on an application using Selenium
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("scrollBy(0, 5000)");
//			js.executeScript("window.scrollTo(0, document.body.scrollHeight);");// Scroll down
//			js.executeScript("window.scrollTo(0, -document.body.scrollHeight);");// Scroll up
//			driver.close();
			driver.quit();

		} else {
			System.out.println("driver is null");
		}

	}

}
