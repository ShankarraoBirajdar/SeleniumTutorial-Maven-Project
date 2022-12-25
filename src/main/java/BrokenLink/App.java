package BrokenLink;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class App {

	public static void getAllBrokenLinks() throws IOException {
		
		// To disable notifications
		ChromeOptions ops = new ChromeOptions();
		ops.addArguments("--disable-notifications");
		ops.addArguments("--headless");
		ops.addArguments("--incognito");
		// Driver Setup
				WebDriverManager.chromedriver().browserVersion("108.0.5359.125").setup();
				WebDriver driver = new ChromeDriver(ops);

				String homePage="https://www.amazon.in/";
				// Launch amazon Website
				driver.get(homePage);
				// Maximize Window
				driver.manage().window().maximize();

				// Wait 5 Second
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));

				// get WebElement of aTag
				List<WebElement> aTag = driver.findElements(By.tagName("a"));

				System.out.println(aTag.size());


				Iterator<WebElement> itr = aTag.iterator();
				while (itr.hasNext()) {
					WebElement webElement = (WebElement) itr.next();
					String link = webElement.getAttribute("href");
					if (link!=null) {
						if (!link.isEmpty()) {
							if (!link.isBlank()) {
								if (link.startsWith(homePage)) {
									//System.out.println(link);
									brokenLink(link);
								}
								
							}
						}
						
					}


				}

				driver.quit();
	}
	
	public static void brokenLink(String link) throws IOException {

		URL url=new URL(link);
		
		HttpURLConnection connection=(HttpURLConnection) url.openConnection();
		
		connection.setConnectTimeout(5000);
		connection.connect();
		
//		if (connection.getResponseCode()<400) {
//			System.out.println("Its Not Broken Link"+connection.getResponseMessage()+"="+link);
//		}else
		

			if (connection.getResponseCode()>=400) {
			System.out.println("Its Broken Link "+connection.getResponseMessage()+" "+connection.getResponseCode()+"="+link);
		}
			


	}

	public static void main(String[] args) throws IOException {
		getAllBrokenLinks();

	}

}
