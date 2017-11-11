package moblima;

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
import java.util.Scanner;

public class ShowtimeDateController {
		int noOfEntries = 0; //Cannot be double
		private FileInputStream bookingDatabaseInputFile;
		private FileOutputStream bookingDatabaseOutputFile;
		
	public ShowtimeDateController() {
		FileInputStream bookingDatabaseInputFile;
		FileOutputStream bookingDatabaseOutputFile;
			try{
		
				bookingDatabaseInputFile = new FileInputStream(new File("Showtime Database.xls"));
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
	
	
	public void printShowTimes(int cineplexNum, ShowtimeDate Showtime) {
		
		try {
			bookingDatabaseInputFile = new FileInputStream(new File("Showtime Database.xls"));
			POIFSFileSystem fs = new POIFSFileSystem(bookingDatabaseInputFile);
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			
			for (int a = 0; a < noOfEntries ; a++) {
				if ((int)sheet.getRow(a).getCell(0).getNumericCellValue() == Showtime.getMovieID()) {
				if ((int)sheet.getRow(a).getCell(1).getNumericCellValue() == cineplexNum) {
					System.out.println("============================================");
					System.out.print("Year: " +(int)sheet.getRow(a).getCell(3).getNumericCellValue() + " ");
					System.out.print("Month: " +(int)sheet.getRow(a).getCell(4).getNumericCellValue()+ 1 + " ");
					System.out.print("Day: " +(int)sheet.getRow(a).getCell(5).getNumericCellValue() + " ");
					System.out.print("Timings: ");
					
					for (int b = 6; b < 16; b++) {
					if (sheet.getRow(a).getCell(b) != null ) {
					System.out.print((int)sheet.getRow(a).getCell(b).getNumericCellValue());
					System.out.print(" ");
					}
				}
					System.out.print("\n");
				}
			}
		}
		}
			
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	
	public void addShowtimeDateIntoDatabase(ShowtimeDate newShowtime, int cineplexNumber, int cinemanumber, int year, int month, int day) {
		HSSFWorkbook workbook;
		try {
			bookingDatabaseInputFile = new FileInputStream(new File("Showtime Database.xls"));
			POIFSFileSystem fs = new POIFSFileSystem(bookingDatabaseInputFile);
			workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFCell cell = null;
			System.out.println(sheet.getPhysicalNumberOfRows());
			//System.out.println(sheet.createRow(noOfEntries).createCell((short)0));
			cell = sheet.createRow(noOfEntries).createCell(0);
			cell.setCellValue(newShowtime.getMovieID());
			cell = sheet.getRow(noOfEntries).createCell(1);
			cell.setCellValue(cineplexNumber);
			cell = sheet.getRow(noOfEntries).createCell(2);
			cell.setCellValue(year);
			cell = sheet.getRow(noOfEntries).createCell(3);
			cell.setCellValue(month);
			cell = sheet.getRow(noOfEntries).createCell(4);
			cell.setCellValue(day);
			System.out.println("Insert how many timings you want to add:");
			Scanner sc = new Scanner(System.in);
			int numTiming = sc.nextInt();
			for (int i = 0; i < numTiming; i++) {
			System.out.println("Insert timings you want to add:");
			int timings = sc.nextInt();
			cell = sheet.getRow(noOfEntries).createCell(5+i);
			cell.setCellValue(timings);

			}
			noOfEntries++; //Number of entries increase by 1
			bookingDatabaseInputFile.close();
			bookingDatabaseOutputFile = new FileOutputStream(new File("Showtime Database.xls"));
			workbook.write(bookingDatabaseOutputFile);
			bookingDatabaseOutputFile.close();

	//Reopen Files
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
		}

