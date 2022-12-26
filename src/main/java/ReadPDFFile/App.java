package ReadPDFFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

//PDF Portable Document Format
//3rd Party Jar to Connect/manipulate PDF File:- Apache PDFBox(OpenSource jar), Open PDF(OpenSource jar), Itext(paid jar)
//3rd Party Jar to Connect/manipulate MS Office File word, ppt, excel:- Apache POI
public class App {

	public static void fileFromLocalMachine() throws Exception {
		// Create a File Object and pass file path
		File file = new File("./resources/my_doc.pdf");

		// Create FileInputStream Object and pass File object
		FileInputStream fis = new FileInputStream(file);

		// To Load Existing PDF use Load method from PDDocument
		PDDocument pdfDocument = PDDocument.load(fis);

//		String password = "1234";
		// For Encrypted PDF
//		PDDocument pdfDocument = PDDocument.load(fis, password);

		// Remove Password Code
		if (pdfDocument.isEncrypted()) {
			try {
				pdfDocument.setAllSecurityToBeRemoved(true);
				pdfDocument.save("./resources/my_doc.pdf");
			} catch (Exception e) {
				throw new Exception("The document is encrypted and we can't decrypt it.", e);
			}
		}

		System.out.println("Print No Of Pages: " + pdfDocument.getNumberOfPages());
//		System.out.println("Print No Of Pages: "+pdfDocument.getPages().getCount());
//		System.out.println("get Version: "+pdfDocument.getVersion());
//		System.out.println("Is All Security To Be Removed: "+pdfDocument.isAllSecurityToBeRemoved());
//		System.out.println("Is Encrypted: "+pdfDocument.isEncrypted());

		// To Read Content From PDF Create a PDFTextStripper Object
		PDFTextStripper pts = new PDFTextStripper();

		// Mention page from
		pts.setStartPage(1);
		// Mention page to
		pts.setEndPage(2);

		// get Text from pdf
		String docText = pts.getText(pdfDocument);

		System.out.println(docText);
//		System.out.println(docText.startsWith("Lorem ipsum"));

		// close PDDocument Object
		pdfDocument.close();

		// close FileInputStream Object
		fis.close();

	}

	public static void fileFromLocalInternet() throws Exception {

//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		
//		driver.get("https://file-examples.com/storage/fefe3c760763a87999556e8/2017/10/file-sample_150kB.pdf");
//		driver.manage().window().maximize();

//		driver.findElement(By.xpath("//div//h3[contains(.,'Documents')]/following-sibling::a[contains(.,'Browse')]")).click();
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3000));
//		String id=driver.getWindowHandle();
//		driver.switchTo().window(id);
////		https://file-examples.com/#google_vignette
//		driver.findElement(By.xpath("//div[@id='dismiss-button']")).click();
//		driver.switchTo().defaultContent();
//		driver.findElement(By.xpath("(//td[@class='file-ext' and contains(.,'PDF')]/following::td//a[contains(.,'Select file size & download')])[1]")).click();
//		driver.findElement(By.xpath("(//td[@class='file-ext' and contains(.,'150 kB')]/following::td//a[contains(.,'Download sample pdf file')])[1]")).click();

//		String getUrl=driver.getCurrentUrl();

//		URL url=new URL("https://file-examples.com/storage/fe332cf53a63a4bd5991eb4/2017/10/file-sample_150kB.pdf");
//		URL url = new URL("https://www.dropbox.com/s/ic04eojpyhqm2kt/00054_CCH_Annual%20Report_2016-82-85.pdf?dl=0");
		
		URL url = new URL("https://www.dropbox.com/s/ic04eojpyhqm2kt/00054_CCH_Annual%20Report_2016-82-85.pdf");
	
		URLConnection connection = url.openConnection();
		connection.setRequestProperty("User-Agent", "MyClient/1.0");
		InputStream inputStream = connection.getInputStream();
		
		PDDocument pdfDocument = PDDocument.load(inputStream);
       
		System.out.println(pdfDocument.getNumberOfPages());

		PDFTextStripper pts = new PDFTextStripper();
		pts.setStartPage(1);

		String text = pts.getText(pdfDocument);
		System.out.println(text);

	}

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
//		fileFromLocalMachine();
		fileFromLocalInternet();

	}

}
