package moblima;

import java.io.*;
import java.util.*;
import java.text.ParseException;
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

public class TicketController {

		int noOfEntries = 0;
		private FileInputStream TicketingDatabaseInputFile;
		private FileOutputStream TicketingDatabaseOutputFile;
		
		private static TicketController ticketController = null;
	
		public static TicketController getInstance() {
			if (ticketController == null)
				ticketController = new TicketController();
			return ticketController;
		}
		
		public TicketController() {
			FileInputStream TicketingDatabaseInputFile;
			FileOutputStream TicketingDatabaseOutputFile;
				try{
					TicketingDatabaseInputFile = new FileInputStream(new File("Ticketing Database.xls"));
					//bookingDatabaseOutputFile = new FileOutputStream(new File("Booking Database.xls"));
					this.TicketingDatabaseInputFile = TicketingDatabaseInputFile;
					//this.bookingDatabaseOutputFile = bookingDatabaseOutputFile;
					POIFSFileSystem fs = new POIFSFileSystem(TicketingDatabaseInputFile);
					HSSFWorkbook workbook = new HSSFWorkbook(fs);
					HSSFSheet sheet = workbook.getSheetAt(0);
					while(sheet.getRow(noOfEntries) != null) {
						noOfEntries++;
				}
					TicketingDatabaseInputFile.close();
			}catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		public void addTicketIntoDatabase(Ticket newTicket) {
			HSSFWorkbook workbook;
			try {
				TicketingDatabaseInputFile = new FileInputStream(new File("Ticketing Database.xls"));
				POIFSFileSystem fs = new POIFSFileSystem(TicketingDatabaseInputFile);
				workbook = new HSSFWorkbook(fs);
				HSSFSheet sheet = workbook.getSheetAt(0);
				System.out.println(sheet.getPhysicalNumberOfRows());
				//System.out.println(sheet.createRow(noOfEntries).createCell((short)0));
				HSSFCell cell = null;
				cell = sheet.createRow(noOfEntries).createCell(0);
				cell.setCellValue(newTicket.getMovieID());
				cell = sheet.getRow(noOfEntries).createCell(1);
				cell.setCellValue(generateTicketingID());
				noOfEntries++; //Number of entries increase by 1
				TicketingDatabaseInputFile.close();
				TicketingDatabaseOutputFile = new FileOutputStream(new File("Ticketing Database.xls"));
				workbook.write(TicketingDatabaseOutputFile);
				TicketingDatabaseOutputFile.close();

		//Reopen Files
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
		
		public int[] countTickets() {
			int[] ticketCount = new int[10];
			for (int i = 0; i < 10; i++) {
				ticketCount[i] = 0;
			}
			try {
				TicketingDatabaseInputFile = new FileInputStream(new File("Ticketing Database.xls"));
				POIFSFileSystem fs = new POIFSFileSystem(TicketingDatabaseInputFile);
				HSSFWorkbook workbook = new HSSFWorkbook(fs);
				HSSFSheet sheet = workbook.getSheetAt(0);
				
				for (int a = 0; a < noOfEntries ; a++) {
					int temp = (int)sheet.getRow(a).getCell(0).getNumericCellValue();
					ticketCount[temp%10] = ticketCount[temp%10] + 1;
					}
					
			}
				
			catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return ticketCount;
		}
		
		public int[] sortedMovieRank() {
			int [] raw = countTickets();
			int [] sorted = sortedNoOfTicketSales();
			int [] sortedMovieID = new int [10];
			for (int i = 0; i < sorted.length; i++) {
				for (int j = 0; j<raw.length;j++) {
					if (sorted[i] == raw[j] ) {
						sortedMovieID[i] = j + 10000;
					}
				}
				
			}
			return sortedMovieID;
			
		}
		
		public int[] sortedNoOfTicketSales () {
			int [] ticketSales = countTickets();
			int n = ticketSales.length;
			for (int i = 1; i<n; ++ i) {
				int key = ticketSales[i];
				int j = i-1;
				
				while (j >= 0 && ticketSales[j] < key) {
					ticketSales[j+1] = ticketSales[j];
					j = j-1;
				}
				ticketSales[j+1] = key;
			}
			return ticketSales;
		}
		
		public static void printArray(int arr[])
	    {
	        int n = arr.length;
	        for (int i=0; i<n; ++i)
	            System.out.print(arr[i] + " ");
	 
	        System.out.println();
	    }
		
		public void getSales (int movieID) {
			int counter = 0;
			for(int i = (noOfEntries-1); i < noOfEntries; i++) {
				
			}
		}
		
		public int generateTicketingID() {
			System.out.println(noOfEntries);
				if(noOfEntries == 0) {
					return 10000;
				} else {
					return (10000 + noOfEntries);
			}
		}
		
		public Date convertHolidayDate(String day, String month, String date, String year) throws ParseException {
			holidayDateList hd = new holidayDateList();
			String a = hd.stringHolidayDate(day, month, date, year);
			Date d = hd.stringToDate(a);
			return d;
		}
		public Date insertDate () throws ParseException{
			System.out.println("Insert Day, month, date, then followed by year");
			String day, month, date, year;
			Scanner sc = new Scanner(System.in);
			day = sc.next();
			month = sc.next();
			date = sc.next();
			year = sc.next();
			return convertHolidayDate(day, month, date, year);
		}

		public boolean checkHoliday (Date a) throws ParseException {
		holidayDateList hd = new holidayDateList();
		for (int i = 0; i < hd.holidayDate.length; i++ ) {
			if (a.equals(hd.holidayDate[i])) {
				return true;
				}
			}
		return false;
		}
		
		public boolean checkWeekend (String day) {
			if (day == "Saturday" || day == "Sunday") {
				return true;
			}
			else return false;
		}
		
		
		
		
}
