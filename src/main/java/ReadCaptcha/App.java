package ReadCaptcha;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;

public class App {

	public static String getFileName() {
		LocalDateTime myDateObj = LocalDateTime.now();
		DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ddMMyyyy_HHmmss");
		String formattedDate = myDateObj.format(myFormatObj);
		return "./Screenshots/captcha" + formattedDate + ".png";
	}

	public static void openLoginPage() throws IOException, TesseractException  {
		// To disable notifications
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");

		//Driver Setup
		WebDriverManager.chromedriver().browserVersion("108.0.5359.125").setup();
		WebDriver driver = new ChromeDriver(ops);

		//Launch IRCTC Website
		driver.get("https://www.irctc.co.in/nget/train-search");
		//Maximize Window
		driver.manage().window().maximize();

		//Wait 10 Second
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10000));

		//Click to Login Page
		driver.findElement(By.xpath("//a[normalize-space(text())='LOGIN']")).click();

		//get WebElement of Captcha Image
		WebElement captchaImage = driver.findElement(By.xpath("//img[@class='captcha-img']"));

		//get Screenshot of that image
		File sourceFile = captchaImage.getScreenshotAs(OutputType.FILE);
		File destFile = new File(getFileName());
		FileUtils.copyFile(sourceFile, destFile);
		System.out.println("Screenshot saved successfully");
		
		//read image
		readCaptcha();

		driver.quit();
	}

	public static void readCaptcha() throws TesseractException {

		File file = new File(getFileName());

		ITesseract image = new Tesseract();
		//eng.traineddata path
		image.setDatapath("./tessdata");
		image.setOcrEngineMode(1);
		image.setLanguage("eng");

		String imageToText = image.doOCR(file);

		System.out.println("Captch is: " + imageToText);

	}

	public static void main(String[] args) throws Exception {
		openLoginPage();
	}

}
