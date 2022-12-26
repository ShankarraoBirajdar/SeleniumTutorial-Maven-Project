package ReadPDFFile;

import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentInformation;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.encryption.AccessPermission;
import org.apache.pdfbox.pdmodel.encryption.StandardProtectionPolicy;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

public class DocumentCreation {

	public static void main(String[] args) throws IOException {

		// Creating PDF document object
		PDDocument document = new PDDocument();


		// Creating a blank page
		PDPage blankPage = new PDPage();

		// Adding the blank page to the document
		document.addPage(blankPage);

		 
		//Creating the PDDocumentInformation object 
	      PDDocumentInformation pdd = document.getDocumentInformation();

	      //Setting the author of the document
	      pdd.setAuthor("Shankarrao Birajdar");
	       
	      // Setting the title of the document
	      pdd.setTitle("Sample document"); 
	       
	      //Setting the creator of the document 
	      pdd.setCreator("Shankarrao Birajdar"); 
	       
	      //Setting the subject of the document 
	      pdd.setSubject("Example document"); 
	       
	      //Setting the created date of the document 
	      Calendar date = new GregorianCalendar();
	      date.set(2022, 12, 25); 
	      pdd.setCreationDate(date);
	      //Setting the modified date of the document 
	      date.set(2023, 1, 1); 
	      pdd.setModificationDate(date); 
	       
	      //Setting keywords for the document 
	      pdd.setKeywords("sample, first example, my pdf"); 
	      
	    //pages of the document 
	      PDPageContentStream contentStream = new PDPageContentStream(document, blankPage);
	      
	      //Begin the Content stream 
	      contentStream.beginText(); 
	       
	      //Setting the font to the Content stream  
	      contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
	      
	      //Setting the leading
	      contentStream.setLeading(14.5f);

	      //Setting the position for the line 
	      contentStream.newLineAtOffset(25, 725);

	      String text = "This is the sample document and we are adding content to it.";
	      String text2 = "as we want like this using the ShowText()  method of the  ContentStream class";

	      //Adding text in the form of string 
	      contentStream.showText(text);   
	      contentStream.newLine();
	      contentStream.showText(text2);    

	      //Ending the content stream
	      contentStream.endText();

	      System.out.println("Content added");

	      //Closing the content stream
	      contentStream.close();
	   
	      //Creating access permission object
	      AccessPermission ap = new AccessPermission();         

	      //Creating StandardProtectionPolicy object
	      StandardProtectionPolicy spp = new StandardProtectionPolicy("1234", "1234", ap);

	      //Setting the length of the encryption key
	      spp.setEncryptionKeyLength(128);

	      //Setting the access permissions
	      spp.setPermissions(ap);

	      //Protecting the document
	      document.protect(spp);

	      System.out.println("Document encrypted");
		
		// Saving the document
		document.save("./resources/my_doc.pdf");

		System.out.println("PDF created");

		// Closing the document
		document.close();

	}

}
