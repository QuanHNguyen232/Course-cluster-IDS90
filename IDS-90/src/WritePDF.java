import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts.FontName;

public class WritePDF {
	
	public static final String FILE_NAME = "Report.pdf";
	
	public static void main(String args[]) throws IOException {
	       
	      //Creating PDF document object 
	      PDDocument document = new PDDocument();

	      for (int i=0; i<5; i++) {
	         //Creating a blank page 
	         PDPage blankPage = new PDPage();

	         //Adding the blank page to the document
	         document.addPage( blankPage );
	      } 
	     
	      // add Text
	      PDPage page = document.getPage(0);
	      PDPageContentStream contentStream = new PDPageContentStream(document, page);
	      contentStream.beginText();
	      
	      //Setting the position for the line 
	      contentStream.newLineAtOffset(25, 700);
	      
	      // set font
	      PDType1Font font = new PDType1Font(FontName.TIMES_ROMAN);
	      contentStream.setFont(font, 12);

	      contentStream.setLeading(14.5f);
	      
	      // add multiple lines
	      contentStream.showText("Hello");
	      contentStream.newLine();
	      contentStream.showText("world");
	      
	      // end Text
	      contentStream.endText();
	      
	      contentStream.close();
	      
	      //Saving the document
	      document.save(FILE_NAME);
	      System.out.println("PDF created");
	      
	      //Closing the document
	      document.close();

	   }  

}
// Still dont know why PDType1Font.TIMES_ROMAN doesnt work
// Source I am following: https:	//www.tutorialspoint.com/pdfbox/pdfbox_adding_multiple_lines.htm
// PDFBox font guide: http://www.pdfbox.org/pdfbox-user-guide/fonts/
// https://pdfbox.apache.org/1.8/cookbook/workingwithfonts.html
// download jar: https://pdfbox.apache.org/download.html (PDFBox 3.0.0-alpha2 - PDFBox standalone)
