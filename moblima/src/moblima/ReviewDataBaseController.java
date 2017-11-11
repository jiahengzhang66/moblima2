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
	int counter = 0;
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

//	public void displayReview(int movieID) {
//		int i =0;
//		while(B.get(i).getMovieID() != B.size()) {
//			if(B.get(i).getMovieID() == movieID) {
//		
//				System.out.println("============================================");
//				System.out.print(
//						"review: "+B.get(i).getReview() + "\n"
//								);
//				System.out.println("============================================");
//				
//			}
//			i++;
//			if(i == B.size()) {
//				break;
//			}
//			}
//		}
	
	
	public void addReview(Review newReview) {
		B.add(newReview);
		noOfReview++;
		counter++;
	}
	
	public String getReviewFromController(int movieID) { //Return a huge String of reviews (the reviews are added together to form the large string)
		String tempString = "";
		for(int pol = 0; pol<B.size(); pol++) {
			if(B.get(pol).getMovieID() == movieID) {
				tempString = tempString + "Review" + pol + (B.get(pol).getReview()) + "\n" ;
				return tempString;
			} 
			
		}
		return "No Review"; //MovieID cannot be found
	}
	
	public void write() { //Because user would not delete review. Therefore the contents of the review data would not change, except for the new reviews added in. Therefore, we only need to write
		HSSFWorkbook workbook;
		try {
			reviewDataInputFile = new FileInputStream(new File("review.xls"));
			POIFSFileSystem rws = new POIFSFileSystem(reviewDataInputFile);
			workbook = new HSSFWorkbook(rws);
			HSSFSheet sheet = workbook.getSheetAt(0);
			HSSFCell cell = null;
			for(int q = (noOfReview-counter-1); q < noOfReview;q++) {
			cell = sheet.createRow(q).createCell(0);
			cell.setCellValue(B.get(q).getTitle());
			cell = sheet.getRow(q).createCell(1);
			cell.setCellValue(B.get(q).getMovieID());
			cell = sheet.getRow(q).createCell(2);
			cell.setCellValue(B.get(q).getReview());
			}
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
