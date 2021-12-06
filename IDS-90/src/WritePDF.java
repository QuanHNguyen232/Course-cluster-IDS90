import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;

public class WritePDF {
	public String imagePath;
	public static final String FILE_NAME = "Report.pdf";
	public ArrayList<Month> months_WritePDF = new ArrayList<Month>();
	public double exchangeRate;
	public String currency;
	public String timeZone = "";
	public String date = "";
	// constructor
	public WritePDF(String imgPath, ArrayList<Month> monthList, String curr, double rate, LocalDateTime now, String zone) {
		this.imagePath = imgPath;
		this.months_WritePDF = monthList;
		this.currency = curr;
		this.exchangeRate = rate;
		String datetime = "MM-dd-yyyy HH:mm";
		String hourtime = "HH:mm:ss";
		this.date = now.format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
		this.timeZone = zone;
		// https://stackabuse.com/how-to-get-current-date-and-time-in-java/#localdatetime
	}
	
	// method
	public void toPDF() throws IOException {
		//Creating PDF document object 
	      PDDocument document = new PDDocument();

	      for (int i=0; i<5; i++) {
	         //Creating a blank page 
	         PDPage blankPage = new PDPage();

	         //Adding the blank page to the document
	         document.addPage( blankPage );
	      } 
	     
	      // add Text
	      PDPage page = document.getPage(1);
	      PDPageContentStream contentStream = new PDPageContentStream(document, page);
	      contentStream.beginText();
	      
	      //Setting the position for the line 
	      contentStream.newLineAtOffset(25, 700);
	      
	      // set font
	      PDType1Font font = new PDType1Font(FontName.TIMES_ROMAN);
	      contentStream.setFont(font, 12);

	      contentStream.setLeading(14.5f);
	      
	      // add data
	      contentStream.showText("Revenue and Expense Report");
	      contentStream.newLine();
	      contentStream.newLine();
	      String note = String.format("Note: exchange rate: 1 USD = %f %s on %s (timezone: %s)",
	    		  this.exchangeRate, this.currency, this.date, this.timeZone);
	      contentStream.showText(note);
	      contentStream.newLine();
	      contentStream.newLine();
	      if (months_WritePDF.size()>0) {
	    	  for (Month m : months_WritePDF) {
	    		  // month
	    		  contentStream.showText("Month no." + m.month);
	    		  contentStream.newLine();
	    		  // write Rev
	    		  String writeTotalRev = String.format("_Revenues: (total: %.2f %s)", this.exchangeRate*m.getTotalRevenue(), this.currency);
	    		  contentStream.showText(writeTotalRev);
	    		  contentStream.newLine();
	    		  if (m.getRevenueList().size() > 0) {
	    			  for (int i=0; i < m.getRevenueList().size(); i++) {
	    				  String writeRev= String.format("__Transaction no.%d: %.2f %s", i, this.exchangeRate*m.getRevenueList().get(i), this.currency);
	    				  contentStream.showText(writeRev);
	    				  contentStream.newLine();
	    			  }
	    		  }
	    		  else {
	    			  contentStream.showText("No record in Revenues");
	    		  }
	    		  contentStream.newLine();
	    		  
	    		// write Exp
	    		  String writeTotalExp = String.format("_Revenues: (total: %.2f %s)", this.exchangeRate*m.getTotalExpense(), this.currency);
	    		  contentStream.showText(writeTotalExp);
	    		  contentStream.newLine();
	    		  if (m.getExpenseList().size() > 0) {
	    			  for (int i=0; i < m.getExpenseList().size(); i++) {
	    				  String writeExp= String.format("__Transaction no.%d: %.2f %s", i, this.exchangeRate*m.getExpenseList().get(i), this.currency);
	    				  contentStream.showText(writeExp);
	    				  contentStream.newLine();
	    			  }
	    		  }
	    		  else {
	    			  contentStream.showText("No record in Expenses");
	    		  }
	    		  contentStream.newLine();
	    		  
	    		  // add space b/w months
	    		  contentStream.newLine();
	    	  }
	      }
	      else {
	    	  contentStream.showText("There is no record.");
	      }
	      
	      // end Text
	      contentStream.endText();
	      
	      contentStream.close();
	      
	      // add image

	      PDPage imgPage = document.getPage(0);
	      PDPageContentStream imgContentStream = new PDPageContentStream(document, imgPage);
	      
	      System.out.println("writePDF imgPath: " + imagePath);
	      PDImageXObject pdImage = PDImageXObject.createFromFile(imagePath, document);
	      imgContentStream.drawImage(pdImage, 70, 250);
	      
	      imgContentStream.close();

	      
	      //Saving the document
	      document.save(FILE_NAME);
	      System.out.println("PDF created");
	      
	      //Closing the document
	      document.close();
	}
	
//	public static void main(String args[]) throws IOException {
//		toPDF();
//	   }  

}
// Still dont know why PDType1Font.TIMES_ROMAN doesnt work
// Source I am following: https:	// www.tutorialspoint.com/pdfbox/pdfbox_adding_multiple_lines.htm
// PDFBox font guide: http://www.pdfbox.org/pdfbox-user-guide/fonts/
// https://pdfbox.apache.org/1.8/cookbook/workingwithfonts.html
// download jar: https://pdfbox.apache.org/download.html (PDFBox 3.0.0-alpha2 - PDFBox standalone)
