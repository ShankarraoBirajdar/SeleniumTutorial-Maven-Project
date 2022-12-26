package ReadPDFFile;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class downloadFIleFromURL {
  
  public void download(String urlLink, File fileLoc) {
    try {
      
      byte[] buffer = new byte[1024];
      double TotalDownload = 0.00;
      int readbyte = 0; //Stores the number of bytes written in each iteration.
      double percentOfDownload = 0.00;
      
      URL url = new URL(urlLink);
      HttpURLConnection http = (HttpURLConnection)url.openConnection();
      double filesize = (double)http.getContentLengthLong();
      
      BufferedInputStream input = new BufferedInputStream(http.getInputStream());
      FileOutputStream ouputfile = new FileOutputStream(fileLoc);
      BufferedOutputStream bufferOut = new BufferedOutputStream(ouputfile, 1024);

      while((readbyte = input.read(buffer, 0, 1024)) >= 0) {
        //Writing the content onto the file.
        bufferOut.write(buffer,0,readbyte);
        //TotalDownload is the total bytes written onto the file.
        TotalDownload += readbyte;
        //Calculating the percentage of download. 
        percentOfDownload = (TotalDownload*100)/filesize;
        //Formatting the percentage up to 2 decimal points.
        String percent = String.format("%.2f", percentOfDownload);
        System.out.println("Downloaded "+ percent + "%");
      }

      System.out.println("Your download is now complete.");
      bufferOut.close();
      input.close();
    }
    catch(IOException e){
      e.printStackTrace();
    }
      
  }
  public static void main(String[] args) {
    
//	  String url = "https://www.adobe.com/content/dam/acom/en/devnet/pdf/pdf_reference_archive/pdf_reference_1-7.pdf";
//	  String saveLocation = "~/Downloads/download.pdf";
//	  InputStream in = new URL(url).openStream();
//	  Files.copy(in, Paths.get(saveLocation), StandardCopyOption.REPLACE_EXISTING);
	  
	  
    //Please provide the correct URL of what you want to download, and the correct directory with a name and extension to save the downloaded file in.
    String link = "https://training.github.com/downloads/github-git-cheat-sheet.pdf";
    String path="./resources/github-git-cheat-sheet.pdf";
    File fileLoc = new File(path);
    
    downloadFIleFromURL d = new downloadFIleFromURL();
    d.download(link, fileLoc);
  }
}