package moblima;

import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.lang.StringBuffer;
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
	int counter = 0;
	private FileInputStream bookingDatabaseInputFile;
	private FileOutputStream bookingDatabaseOutputFile;
	ArrayList<Booking> bookingList = new ArrayList<Booking>();
	private static BookingDatabaseController bookingDatabaseController = null;

	
	public static BookingDatabaseController getInstance() {
		if (bookingDatabaseController == null)
			bookingDatabaseController = new BookingDatabaseController();
		return bookingDatabaseController;
	}
	
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
				Booking bookingArrayElement = new Booking((int)sheet.getRow(noOfEntries).getCell(0).getNumericCellValue(),
														sheet.getRow(noOfEntries).getCell(1).getStringCellValue(),
														sheet.getRow(noOfEntries).getCell(2).getStringCellValue(),
														sheet.getRow(noOfEntries).getCell(3).getNumericCellValue(),
														sheet.getRow(noOfEntries).getCell(4).getStringCellValue(),
														sheet.getRow(noOfEntries).getCell(5).getStringCellValue(),
														(int)sheet.getRow(noOfEntries).getCell(6).getNumericCellValue());
				bookingList.add(bookingArrayElement);
														
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

	private int generateBookingID() {
		System.out.println(noOfEntries);
			if(noOfEntries == 0) {
				return 10000;
			} else {
				return (10000 + noOfEntries);
		}
	}
	private String generateTransactionID(String cinemaCode) {
		Date timeOfGeneration = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmm");
		StringBuffer test = new StringBuffer(formatter.format(timeOfGeneration));
		return cinemaCode+test.toString();
		
	}
	private String generateTicketIDString(int[] jo) {
		String ticket1 = String.valueOf(jo[0]);
		String ticket2 = String.valueOf(jo[1]);
		String ticket3 = String.valueOf(jo[2]);
		
		return ticket1 + ", " + ticket2 + ", " + ticket3 + ", ";
	}
	public void printBookingHistory(int numToPrint) { //numToPrint is the amount of booking History you want to print (Latest n Bookings)
		for(int i = (noOfEntries-numToPrint-1); i < noOfEntries; i++) {
			System.out.println("============================================");
			System.out.println("Past Booking Information: "+(i+1));
			System.out.print(
							"Booking ID: " +bookingList.get(i).getBookingID() + "\n"
							+"Transaction ID: " + bookingList.get(i).getTransactionID() + "\n"
							+"Name: " + bookingList.get(i).getName() + "\n"
							+"Mobile Number: "+ bookingList.get(i).getMobileNum() + "\n"
							+"Email: "+	bookingList.get(i).getEmail() + "\n"
							+"Ticket ID: "+ bookingList.get(i).getTicketID() + "\n"
							+"Movie ID: "+bookingList.get(i).getMovieID() + "\n");
			System.out.println("============================================");
		}
	}
	
	public void addBooking(String cinemaCode, int[] ticketIDs, String namee, double mobileeNum, String emaile, int movieID) { //Method to call to add a new booking
		Booking newBooking = new Booking(generateBookingID(), generateTransactionID(cinemaCode), namee, mobileeNum, emaile, generateTicketIDString(ticketIDs), movieID);
		bookingList.add(newBooking);
		noOfEntries++;
		counter++;
	}
	public void write() { //Must call at the end of code
		HSSFWorkbook workbook;
		try {
			bookingDatabaseInputFile = new FileInputStream(new File("Booking Database.xls"));
			POIFSFileSystem fs = new POIFSFileSystem(bookingDatabaseInputFile);
			workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			System.out.println(sheet.getPhysicalNumberOfRows());
			//System.out.println(sheet.createRow(noOfEntries).createCell((short)0));
			HSSFCell cell = null;
			for(int x = (noOfEntries - counter - 1); x<noOfEntries; x++) {
			cell = sheet.createRow(x).createCell(0);
			cell.setCellValue(bookingList.get(x).getBookingID());
			cell = sheet.getRow(x).createCell(1);
			cell.setCellValue(bookingList.get(x).getTransactionID());
			cell = sheet.getRow(x).createCell(2);
			cell.setCellValue(bookingList.get(x).getName());
			cell = sheet.getRow(x).createCell(3);
			cell.setCellValue(bookingList.get(x).getMobileNum());
			cell = sheet.getRow(x).createCell(4);
			cell.setCellValue(bookingList.get(x).getEmail());
			cell = sheet.getRow(x).createCell(5);
			cell.setCellValue(bookingList.get(x).getTicketID());
			cell = sheet.getRow(x).createCell(6);
			cell.setCellValue(bookingList.get(x).getMovieID());
			}
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
