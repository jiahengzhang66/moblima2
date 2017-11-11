package moblima;

import java.util.*;
import java.util.ArrayList;

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


public class ReviewDataBaseController {
	ArrayList <Review> B = new ArrayList<Review>();
	int noOfReview=0;//calculate number of reviews
	private FileInputStream reviewDataInputFile;
	private FileOutputStream reviewDataOutputFile;
	
	public  ReviewDataBaseController() {//this will read the file first;
		FileInputStream reviewDataInputFile;
		FileOutputStream reviewDataOutputFile;
			try{
		
				reviewDataInputFile = new FileInputStream(new File("review.xls"));
				//bookingDatabaseOutputFile = new FileOutputStream(new File("Booking Database.xls")); n
				this.reviewDataInputFile = reviewDataInputFile;
				//this.bookingDatabaseOutputFile = bookingDatabaseOutputFile;
				POIFSFileSystem rws= new POIFSFileSystem(reviewDataInputFile);
				HSSFWorkbook workbook = new HSSFWorkbook(rws);
				HSSFSheet sheet = workbook.getSheetAt(0);
				while(sheet.getRow(noOfReview) != null) {
					noOfReview++;
			}
				reviewDataInputFile.close();
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void generateReviewArray(){//this is to print the movie in the data file without ranking
		try {
			reviewDataInputFile = new FileInputStream(new File("review.xls"));
			POIFSFileSystem ms = new POIFSFileSystem(reviewDataInputFile);
			HSSFWorkbook workbook = new HSSFWorkbook(ms);
			HSSFSheet sheet = workbook.getSheetAt(0);
			for(int i =0; i < noOfReview; i++) {
				
				Review RWS = new Review(sheet.getRow(i).getCell(0).getStringCellValue(), 
										(int)sheet.getRow(i).getCell(1).getNumericCellValue(),
										sheet.getRow(i).getCell(2).getStringCellValue());
	
			B.add(RWS);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void displayReview(int movieID) {
		int i =0;
		while(B.get(i).getMovieID() != B.size()) {
			if(B.get(i).getMovieID() == movieID) {
		
				System.out.println("============================================");
				System.out.print(
						"review: "+B.get(i).getReview() + "\n"
								);
				System.out.println("============================================");
				
			}
			i++;
			if(i == B.size()) {
				break;
			}
			}
		}
	
	
	public void addReview(Review newReview) {
		B.add(newReview);
	}
	
	public void write(Review newReview) {
		HSSFWorkbook workbook;
		try {
			reviewDataInputFile = new FileInputStream(new File("review.xls"));
			POIFSFileSystem rws = new POIFSFileSystem(reviewDataInputFile);
			workbook = new HSSFWorkbook(rws);
			HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFCell cell = null;
			cell = sheet.createRow(noOfReview-1).createCell(0);
			cell.setCellValue(newReview.getTitle());
			cell = sheet.getRow(noOfReview-1).createCell(1);
			cell.setCellValue(newReview.getMovieID());
			cell = sheet.getRow(noOfReview-1).createCell(2);
			cell.setCellValue(newReview.getReview());
			noOfReview++; //Number of entries increase by 1
			reviewDataInputFile.close();
			reviewDataOutputFile = new FileOutputStream(new File("review.xls"));
			workbook.write(reviewDataOutputFile);
			reviewDataOutputFile.close();
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
