package BrokenImages;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
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

		String homePage = "https://the-internet.herokuapp.com/broken_images";
		// Launch amazon Website
		driver.get(homePage);
		// Maximize Window
		driver.manage().window().maximize();

		// Wait 5 Second
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5000));

		// get WebElement of aTag
		List<WebElement> aTag = driver.findElements(By.tagName("img"));

		System.out.println("The page under test has " + aTag.size() + " images");


		
		 //Approach 1: Find broken images on a web page using Selenium WebDriver
		System.out.println("Approach 1: Find broken images on a web page using Selenium WebDriver");
		Iterator<WebElement> itr = aTag.iterator();
		while (itr.hasNext()) {
			WebElement webElement = (WebElement) itr.next();

			if (webElement != null)
            {
                HttpClient client = HttpClientBuilder.create().build();
                HttpGet request = new HttpGet(webElement.getAttribute("src"));
                HttpResponse response = client.execute(request);
                /* For valid images, the HttpStatus will be 200 */
                if (response.getStatusLine().getStatusCode() != 200)
                {
                    System.out.println(webElement.getAttribute("outerHTML") + " is broken.");
                }
            }


		}
		
		//Approach 2: Find broken images on a web page using Selenium WebDriver
		System.out.println("Approach 2: Find broken images on a web page using Selenium WebDriver");
		 for (WebElement img : aTag)
         {
             if (img != null)
             {
                 if (img.getAttribute("naturalWidth").equals("0"))
                 {
                     System.out.println(img.getAttribute("outerHTML") + " is broken.");
                 }
             }
         }

		driver.quit();
	}



	public static void main(String[] args) throws IOException {
		getAllBrokenLinks();

	}

}
