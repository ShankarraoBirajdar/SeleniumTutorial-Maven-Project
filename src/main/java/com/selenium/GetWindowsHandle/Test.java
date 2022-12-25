package com.selenium.GetWindowsHandle;

import java.io.File;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.selenium.ui.WebDriverInstance;

public class Test {

	public static WebDriver driver;

	public static WebDriver launchBrowserUsingTraditionalWay(String browser) {
		String driverPath = "C:\\Users\\HP\\Selenium-Java\\BrowserDrivers\\chromedriver.exe";
		if (browser.equals("Chrome")) {
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();

		} 
		else if (browser.equals("Firefox")) {
			driverPath = "C:\\Users\\HP\\Selenium-Java\\BrowserDrivers\\geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", driverPath);
			driver = new FirefoxDriver();
		} 
		else if (browser.equals("Edge")) {
			driverPath = "C:\\Users\\HP\\Selenium-Java\\BrowserDrivers\\msedgedriver.exe";
			System.setProperty("webdriver.edge.driver", driverPath);
			driver = new EdgeDriver();
		}
		return driver;

	}

	public void scrollTo() {
		WebElement linkText = driver.findElement(By.xpath("//div//a[contains(.,'Sign up')]"));
		
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("window.scrollTo(0,1000)");
		executor.executeScript("window.scrollTo(0,document.body.scrollHeight)");
		executor.executeScript("arguments[0],arguments[1]",linkText.getLocation().getX(),linkText.getLocation().getY());
		executor.executeScript("window.scrollTo(0,1000)");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		executor.executeScript("window.scrollBy(0,1000)");
		executor.executeScript("window.scrollIntoView(0,1000)");
		
		executor.executeScript("arguments[0].scrollIntoView()",linkText);
		
	}
	public static void main(String[] args) {

		driver = WebDriverInstance.launchWebDriver();

		driver.get("https://phptravels.com/demo/");
		System.out.println(driver.getCurrentUrl());
		System.out.println(driver.getTitle());

		WebElement linkText = driver.findElement(By.xpath("//div//a[contains(.,'Sign up')]"));
		linkText.click();

		Set<String> windowsIds = driver.getWindowHandles();

		for (String str : windowsIds) {
			driver.switchTo().window(str);
			System.out.println(driver.getCurrentUrl());
			System.out.println(driver.getTitle());
			driver.switchTo().window(str).close();
		}

		driver.get("www.google.com");
		driver.getCurrentUrl();
		driver.getPageSource();
		driver.getTitle();
		driver.getWindowHandle();
		driver.getWindowHandles();

		driver.manage().window().maximize();
		driver.manage().window().minimize();

		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(1000));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1000));
		

		driver.navigate().to("www.facebook.com");
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();

		driver.switchTo().newWindow(WindowType.TAB);
		driver.switchTo().alert().accept();
		driver.switchTo().alert().dismiss();
		driver.switchTo().alert().getText();
		driver.switchTo().alert().sendKeys("Shankar");
		driver.switchTo().window("parentWindow");

		Select select = new Select(linkText);

		List<WebElement> webElements = select.getAllSelectedOptions();
		select.deselectAll();

		select.selectByIndex(0);
		select.selectByValue("");
		select.selectByVisibleText("");

		select.deselectByIndex(0);
		select.deselectByValue("");
		select.deselectByVisibleText("");

		List<WebElement> options = select.getOptions();
		boolean isMultiple =select.isMultiple();
		WebElement firstElement=select.getFirstSelectedOption();

		driver.get("https://www.saucedemo.com/");
		WebElement txtUsername = driver.findElement(By.id("user-name"));
		
		Assert.assertEquals(false, false);
		Assert.assertNotEquals(false, false);
		
		Assert.assertNull(txtUsername);
		Assert.assertNotNull(txtUsername);
		
		Assert.assertTrue(false);
		Assert.assertFalse(false);
		
		Actions builder = new Actions(driver);
		Action seriesOfActions =  
				builder
				.moveToElement(txtUsername)
				.click()
				.dragAndDrop(firstElement, txtUsername)
				.keyDown(txtUsername, Keys.SHIFT)
				.sendKeys(txtUsername, "hello")
				.keyUp(txtUsername, Keys.SHIFT)
				.doubleClick(txtUsername)
				.contextClick()
				.build();

		WebDriverWait driverWait = new WebDriverWait(driver, Duration.ofSeconds(1000));
		WebElement element = driverWait.until(ExpectedConditions.visibilityOf(linkText));
		element.click();

		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
				.withTimeout(Duration.ofSeconds(10000))
				.pollingEvery(Duration.ofSeconds(1000)).
				ignoring(NoSuchElementException.class);

		wait.until(new Function<WebDriver, WebElement>() {
			@Override
			public WebElement apply(WebDriver driver) {
				// TODO Auto-generated method stub
				return driver.findElement(By.xpath(""));
			}

		});

		try {
			/* File */
			TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
			File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
			File destFile = new File("./Screenshots/img1.jpg");
			FileUtils.copyFile(sourceFile, destFile);
			System.out.println("Screenshot saved successfully");

		} catch (Exception e) {
			// TODO: handle exception
		}

		driver.close();
		driver.quit();

	}

}


