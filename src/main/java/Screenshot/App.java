package Screenshot;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class App {

	public static String getFileName() {
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
		String formattedDate = myDateObj.format(myFormatObj);
		return "./Screenshots/google_" + formattedDate + ".png";
	}
	
	public static void main(String[] args) throws IOException {

		WebDriverManager.chromedriver().browserVersion("108.0.5359.71").setup();
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));
		
		getScreenShot_WebElement(driver);
//		getScreenShot_File(driver);
//		getScreenShot_Base64(driver);
//		getScreenShot_Bytes(driver);
		
		driver.quit();
	}
	
	public static void getScreenShot_WebElement(WebDriver driver) throws IOException {
		File sourceFile = driver.findElement(By.xpath("//img[@class='lnXdpd']")).getScreenshotAs(OutputType.FILE);
		File destinationFile = new File(getFileName());
		FileUtils.copyFile(sourceFile, destinationFile);
		System.out.println("Screenshots Captured");
	}
	
	public static void getScreenShot_File(WebDriver driver) throws IOException {

		TakesScreenshot screenshot=(TakesScreenshot) driver;
		File sourceFile = screenshot.getScreenshotAs(OutputType.FILE);
		File destinationFile = new File(getFileName());
		FileUtils.copyFile(sourceFile, destinationFile);
		System.out.println("Screenshots Captured");
	}
	
	public static void getScreenShot_Bytes(WebDriver driver) throws IOException {
		/* Bytes */
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		byte[] byteArr = takesScreenshot.getScreenshotAs(OutputType.BYTES);
		File destFile = new File(getFileName());
		FileOutputStream fos = new FileOutputStream(destFile);
		fos.write(byteArr);
		fos.close();
		System.out.println("Screenshots Captured");
	}
	public static void getScreenShot_Base64(WebDriver driver) throws IOException {
		/* Base64 */
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
		String base64code = takesScreenshot.getScreenshotAs(OutputType.BASE64);
		byte[] byteArr = Base64.getDecoder().decode(base64code);
		File destFile = new File(getFileName());
		FileOutputStream fos = new FileOutputStream(destFile);
		fos.write(byteArr);
		fos.close();
		System.out.println("Screenshots Captured");
	}

}
