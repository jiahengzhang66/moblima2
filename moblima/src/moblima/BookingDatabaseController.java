package moblima;

//import java.util.Date;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class BookingDatabaseController {
	int noOfEntries = 0; //Cannot be double
	private FileInputStream bookingDatabaseInputFile;
	private FileOutputStream bookingDatabaseOutputFile;
	
public BookingDatabaseController() {
	FileInputStream bookingDatabaseInputFile;
	FileOutputStream bookingDatabaseOutputFile;
		try{
			bookingDatabaseInputFile = new FileInputStream(new File("Booking Database.xls"));
			//bookingDatabaseOutputFile = new FileOutputStream(new File("Booking Database.xls"));
			this.bookingDatabaseInputFile = bookingDatabaseInputFile;
			//this.bookingDatabaseOutputFile = bookingDatabaseOutputFile;
			POIFSFileSystem fs = new POIFSFileSystem(bookingDatabaseInputFile);
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			while(sheet.getRow(noOfEntries) != null) {
				noOfEntries++;
		}
			bookingDatabaseInputFile.close();
	}catch (IOException e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
}

//	public void createExcelFile() {
//		HSSFWorkbook bookingDatabase = new HSSFWorkbook();
//	    FileOutputStream fileOut;
//		try {
//			fileOut = new FileOutputStream("Booking Database.xls");
//			bookingDatabase.write(fileOut);
//		    fileOut.close();
//		} catch (IOException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//	}
	public int generateBookingID() {
		System.out.println(noOfEntries);
			if(noOfEntries == 0) {
				return 10000;
			} else {
				return (10000 + noOfEntries);
		}
	}
	public void printBookingHistory(int numToPrint) {
		try {
			bookingDatabaseInputFile = new FileInputStream(new File("Booking Database.xls"));
			POIFSFileSystem fs = new POIFSFileSystem(bookingDatabaseInputFile);
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			for(int i = (noOfEntries-numToPrint-1); i < noOfEntries; i++) {
				System.out.println("============================================");
				System.out.print("Movie: " + sheet.getRow(i).getCell(6) + "\n"
								+"Booking ID: " + sheet.getRow(i).getCell(0) + "\n"
								+"Mobile Number: "+sheet.getRow(i).getCell(1) + "\n"
								+"Email: "+sheet.getRow(i).getCell(2) + "\n"
								+"Ticket ID: "+sheet.getRow(i).getCell(3) + "\n"
								+"Cinema Code: "+sheet.getRow(i).getCell(4) + "\n"
								+"Date and Time of Booking: "+sheet.getRow(i).getCell(5) + "\n");
				System.out.println("============================================");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void addBookingIntoDatabase(Booking newBooking) {
		HSSFWorkbook workbook;
		try {
			bookingDatabaseInputFile = new FileInputStream(new File("Booking Database.xls"));
			POIFSFileSystem fs = new POIFSFileSystem(bookingDatabaseInputFile);
			workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			System.out.println(sheet.getPhysicalNumberOfRows());
			//System.out.println(sheet.createRow(noOfEntries).createCell((short)0));
			HSSFCell cell = null;
			cell = sheet.createRow(noOfEntries).createCell(0);
			cell.setCellValue(newBooking.getBookingID());
			cell = sheet.getRow(noOfEntries).createCell(1);
			cell.setCellValue(newBooking.getMobileNum());
			cell = sheet.getRow(noOfEntries).createCell(2);
			cell.setCellValue(newBooking.getEmail());
			cell = sheet.getRow(noOfEntries).createCell(3);
			cell.setCellValue(newBooking.getTicketID());
			cell = sheet.getRow(noOfEntries).createCell(4);
			cell.setCellValue(newBooking.getCinemaCode());
			cell = sheet.getRow(noOfEntries).createCell(5);
			cell.setCellValue(newBooking.getDateTime());
			cell = sheet.getRow(noOfEntries).createCell(6);
			cell.setCellValue(newBooking.getMovieID());
			noOfEntries++; //Number of entries increase by 1
			bookingDatabaseInputFile.close();
			bookingDatabaseOutputFile = new FileOutputStream(new File("Booking Database.xls"));
			workbook.write(bookingDatabaseOutputFile);
			bookingDatabaseOutputFile.close();

	//Reopen Files
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
