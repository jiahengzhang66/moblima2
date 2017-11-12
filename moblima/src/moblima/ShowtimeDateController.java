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
		
		private static ShowtimeDateController showtimeDateController = null;
		
		public static ShowtimeDateController getInstance() {
			if (showtimeDateController == null)
				showtimeDateController = new ShowtimeDateController();
			return showtimeDateController;
		}
		
		
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
	
	
	public void printMovieShowTimes(ShowtimeDate Showtime) {
		
		try {
			bookingDatabaseInputFile = new FileInputStream(new File("Showtime Database.xls"));
			POIFSFileSystem fs = new POIFSFileSystem(bookingDatabaseInputFile);
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			int[] tempArr = new int [10];
			int[] sortArr = new int[10];
			
	
			
			for (int cineplexNum = 1; cineplexNum < 4; cineplexNum++) {
				for (int day = 0; day <= 31; day++) {
					for (int a = 0; a < noOfEntries ; a++) {
						if ((int)sheet.getRow(a).getCell(0).getNumericCellValue() == Showtime.getMovieID()) {
							if ((int)sheet.getRow(a).getCell(1).getNumericCellValue() == cineplexNum) {
					
								if ((int)sheet.getRow(a).getCell(5).getNumericCellValue() == day) {
									System.out.println("============================================");
									System.out.println("cineplex number:" + (int)sheet.getRow(a).getCell(1).getNumericCellValue() + " ");
									System.out.print("Year: " +(int)sheet.getRow(a).getCell(3).getNumericCellValue() + " ");
									System.out.print("Month: " +(int)sheet.getRow(a).getCell(4).getNumericCellValue() + " ");
									System.out.print("Day: " +(int)sheet.getRow(a).getCell(5).getNumericCellValue() + " ");
									System.out.print("Timings: ");
					
									for (int b = 6; b < 16; b++) {
										if (sheet.getRow(a).getCell(b) != null ) {
											tempArr[b - 6] = (int)sheet.getRow(a).getCell(b).getNumericCellValue();
										}
										
									}
									sortArr = tempArr.clone();
									sortArr = sortArray(sortArr);
									printArray(sortArr);
									System.out.print("\n");	
						
								}
						
							}
			
						}
						
					}	
				
				
			}
				
			}
		}
			
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void printCineplexShowTimes(ShowtimeDate Showtime) {
		
		try {
			bookingDatabaseInputFile = new FileInputStream(new File("Showtime Database.xls"));
			POIFSFileSystem fs = new POIFSFileSystem(bookingDatabaseInputFile);
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			int[] tempArr = new int [10];
			int[] sortArr = new int[10];
			int cineplexNum = Showtime.getCineplexID();
			for (int a = 0; a < noOfEntries ; a++) {
				if ((int)sheet.getRow(a).getCell(0).getNumericCellValue() == Showtime.getMovieID()) {
				if ((int)sheet.getRow(a).getCell(1).getNumericCellValue() == cineplexNum) {
					System.out.println("============================================");
					System.out.print("Year: " +(int)sheet.getRow(a).getCell(3).getNumericCellValue() + " ");
					System.out.print("Month: " +(int)sheet.getRow(a).getCell(4).getNumericCellValue() + " ");
					System.out.print("Day: " +(int)sheet.getRow(a).getCell(5).getNumericCellValue() + " ");
					System.out.print("Timings: ");
					
					for (int b = 6; b < 16; b++) {
					if (sheet.getRow(a).getCell(b) != null ) {
						tempArr[b - 6] = (int)sheet.getRow(a).getCell(b).getNumericCellValue();
					}
					}
				
					
					sortArr = tempArr.clone();
					sortArr = sortArray(sortArr);
					printArray(sortArr);
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
		
	private static int[] sortArray(int[] nonSortedArray) {
        int[] sortedArray = new int[nonSortedArray.length];
        int temp;
        for (int j = 0; j < nonSortedArray.length - 1; j++) {// added this for loop, think about logic why do we have to add this to make it work

        for (int i = 0; i < nonSortedArray.length - 1; i++) {
            if (nonSortedArray[i] > nonSortedArray[i + 1]) {
                temp = nonSortedArray[i];
                nonSortedArray[i] = nonSortedArray[i + 1];
                nonSortedArray[i + 1] = temp;
                sortedArray = nonSortedArray;

            }
        }
        }
        return sortedArray;
    }

private static void printArray(int arr[])
{
    int n = arr.length;
    for (int i=0; i<n; ++i)
    	if (arr[i] != 0) {
        System.out.print(arr[i] + " ");
    	}
    System.out.println();
}

	public void addShowtimeDateIntoDatabase(ShowtimeDate newShowtime) {
		HSSFWorkbook workbook;
		int cineplexNumber = newShowtime.getCineplexID();
		int cinemanumber = newShowtime.getCinemaID();
		int year = newShowtime.getYear();
		int month = newShowtime.getMonth();
		int day = newShowtime.getDay();
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
	
	public void deleteShowtimeDateIntoDatabase(ShowtimeDate Showtime) {
		try {
			bookingDatabaseInputFile = new FileInputStream(new File("Showtime Database.xls"));
			POIFSFileSystem fs = new POIFSFileSystem(bookingDatabaseInputFile);
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			int cineplexNumber = Showtime.getCineplexID();
			int cinemanumber = Showtime.getCinemaID();
			int year = Showtime.getYear();
			int month = Showtime.getMonth();
			int day = Showtime.getDay();
			int time = Showtime.getTime();
			for (int a = 0; a < noOfEntries ; a++) {
				if ((int)sheet.getRow(a).getCell(0).getNumericCellValue() == Showtime.getMovieID()) {
				if ((int)sheet.getRow(a).getCell(1).getNumericCellValue() == cineplexNumber && (int)sheet.getRow(a).getCell(2).getNumericCellValue()== cinemanumber && (int)sheet.getRow(a).getCell(3).getNumericCellValue() == year && (int)sheet.getRow(a).getCell(4).getNumericCellValue() == month && (int)sheet.getRow(a).getCell(5).getNumericCellValue()==day) {
					for (int b = 6; b < 16; b++) {
						if (sheet.getRow(a).getCell(b)!=null) {
						if (sheet.getRow(a).getCell(b).getNumericCellValue() == time) {
							sheet.getRow(a).removeCell(sheet.getRow(a).getCell(b));
							System.out.print("removed");
							bookingDatabaseInputFile.close();
							bookingDatabaseOutputFile = new FileOutputStream(new File("Showtime Database.xls"));
							workbook.write(bookingDatabaseOutputFile);
							bookingDatabaseOutputFile.close();
						}
						}
					}
				}
				}
			}
			
		
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int searchCinemaID(ShowtimeDate Showtime) {
		try {
			bookingDatabaseInputFile = new FileInputStream(new File("Showtime Database.xls"));
			POIFSFileSystem fs = new POIFSFileSystem(bookingDatabaseInputFile);
			HSSFWorkbook workbook = new HSSFWorkbook(fs);
			HSSFSheet sheet = workbook.getSheetAt(0);
			for (int a = 0; a < noOfEntries ; a++) {
				if ((int)sheet.getRow(a).getCell(0).getNumericCellValue() == Showtime.getMovieID()) {
				if ((int)sheet.getRow(a).getCell(1).getNumericCellValue() == Showtime.getCineplexID()){
				if ((int) sheet.getRow(a).getCell(3).getNumericCellValue() == Showtime.getYear()) {
				if ((int)sheet.getRow(a).getCell(4).getNumericCellValue() == Showtime.getMonth()) {
				if ((int)sheet.getRow(a).getCell(5).getNumericCellValue()==Showtime.getDay()) {
					
					for (int b = 6; b < 16; b++) {
						if (sheet.getRow(a).getCell(b)!=null) {
						if (sheet.getRow(a).getCell(b).getNumericCellValue() == Showtime.getTime()) {
					return (int)sheet.getRow(a).getCell(2).getNumericCellValue();
							}
						}
						}
				}
				}
					}
				}
				}
			}
		
		
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return -1;
	}

}
