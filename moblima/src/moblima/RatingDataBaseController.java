package moblima;

import java.util.ArrayList;
import java.util.*;

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

public class RatingDataBaseController {
	ArrayList <Rating> R = new ArrayList<Rating>(); //Array with rating already averaged
	ArrayList <Rating> allRatingsArray = new ArrayList<Rating>(); //Array with all the ratings
	double totalRating = 0.0;
	int	noOfRating=0;
	int counter = 0;
	private FileInputStream ratingDataInputFile;
	private FileOutputStream ratingDataOutputFile;

	public  RatingDataBaseController() {//this will read the file first;
		FileInputStream rateDataInputFile;
		FileOutputStream rateDataOutputFile;
			try{
		
				ratingDataInputFile = new FileInputStream(new File("rating.xls"));
				//bookingDatabaseOutputFile = new FileOutputStream(new File("Booking Database.xls")); n
				this.ratingDataInputFile = ratingDataInputFile;
				//this.bookingDatabaseOutputFile = ratingDataOutputFile;
				POIFSFileSystem rate= new POIFSFileSystem(ratingDataInputFile);
				HSSFWorkbook workbook = new HSSFWorkbook(rate);
				HSSFSheet sheet = workbook.getSheetAt(0);
				while(sheet.getRow(noOfRating) != null) {
					noOfRating++;
			}
				ratingDataInputFile.close();
				
		}catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void generateRatingArray() {
		try {
			double averageRating = 0;
			int checkTimesRated = 1;
			double cumulativeRating = 0;
			ratingDataInputFile = new FileInputStream(new File("rating.xls"));
			POIFSFileSystem rate = new POIFSFileSystem(ratingDataInputFile);
			HSSFWorkbook workbook = new HSSFWorkbook(rate);
			HSSFSheet sheet = workbook.getSheetAt(0);
			int p = 0;
			for(int i = 0; i < noOfRating; i++) {
				int lu = (int)sheet.getRow(i).getCell(1).getNumericCellValue(); //Getting MovieID
				if(lu != p) {
				p = (int)sheet.getRow(i).getCell(1).getNumericCellValue(); //Getting MovieID
				cumulativeRating += sheet.getRow(i).getCell(2).getNumericCellValue(); //Adding cumulativeRating
				for(int x=(i+1); x < noOfRating; x++ ){ //Loop to check rest of rating file
					if(p == (int)sheet.getRow(x).getCell(1).getNumericCellValue()) { //If second movieId is equals to First movieID,
						checkTimesRated++; //Increase Check Times Rated
						cumulativeRating += sheet.getRow(x).getCell(2).getNumericCellValue(); //Add the second one to cumulative Rating
					}
					
				averageRating = cumulativeRating/checkTimesRated;
				}
				
				Rating RATE = new Rating(sheet.getRow(i).getCell(0).getStringCellValue(),
										(int)sheet.getRow(i).getCell(1).getNumericCellValue(),
										averageRating, checkTimesRated, cumulativeRating
										);
//				System.out.println(RATE.getTitle() + RATE.getRating() +" " + RATE.getTimesRated() + " " + RATE.getTotalRating() + " ");
				
				R.add(RATE);//can call this so that wont read the whole data file again
				}
				
				
				checkTimesRated = 1;
				cumulativeRating = 0;
				
				Rating multipleRatings = new Rating(sheet.getRow(i).getCell(0).getStringCellValue(),
						(int)sheet.getRow(i).getCell(1).getNumericCellValue(),
						(double)sheet.getRow(i).getCell(2).getNumericCellValue(), 0, 0
						);
				
				allRatingsArray.add(multipleRatings);
				
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void printRatings(){
		int lop = 0;
		while(lop != allRatingsArray.size()) {
			System.out.println(allRatingsArray.get(lop).getTitle() + " " + allRatingsArray.get(lop).getMovieID() + " " + allRatingsArray.get(lop).getRating());
			lop++;
		}
		int loi = 0;
		while(loi != R.size()) {
			System.out.println(R.get(loi).getTitle() + " " + R.get(loi).getMovieID() + " " + R.get(loi).getRating() + " " + R.get(loi).getTimesRated() + " " + R.get(loi).getTotalRating());
			loi++;
		}
	}
//	public double calculateAverageRating(int movieID) {//new movie
//	
//		int i = 0;
//		int n = noOfRating;
//		int counter = 0;
//		while(n!=0) {
//			if (R.get(i).getMovieID() == movieID) {
//				totalRating += R.get(i).getRating();
//				counter++;
//				}	
//			n--;
//			i++;
//		}
//		totalRating = totalRating/counter;
//		
//		return totalRating;
//}
		
		
	public void sortRankingRating() {
		ArrayList<Rating> tempList = new ArrayList<Rating>();
			tempList = (ArrayList<Rating>) R.clone();
			int po = 1;
		    for(int x = 1; x<tempList.size(); x++) {
		    	
		    	for(int y = x; y > 0; y--) {
		    		if (tempList.get(y).getRating() > tempList.get(y-1).getRating()) {
		      Rating tempRating = tempList.get(y-1);
		      tempList.set(y-1, tempList.get(y));
		      tempList.set(y, tempRating); 
		      }
		    	}
		    }
		    System.out.print("===============================================");
		    System.out.print("Top 5 Movies In Terms Of Ratings");
		    System.out.print("===============================================");
		    for(int x = 0; x<5; x++) {
		    	if(tempList.get(x).getTimesRated() != 1) {
		    System.out.print(x+") "+"Title: " + tempList.get(x).getTitle()+"\n"+"Movie ID: " + tempList.get(x).getMovieID() + "\n" + "Average Rating " + tempList.get(x).getRating() + "\n");
		    	} else {
		    	System.out.print(x+") "+"Title: " + tempList.get(x).getTitle()+"\n"+"Movie ID: " + tempList.get(x).getMovieID() + "\n" + "Average Rating " + "NA" + "\n");
		    	}
		    }
		}
	
	public boolean moreThanOneRating (int movieID) { //To determine whether Times Rated is more than one
		for(int poi = 0; poi<R.size(); poi++) {
			if(R.get(poi).getMovieID() == movieID) {
				if(R.get(poi).getTimesRated()>1) {
					return true;
				} 
			}
		} 
		return false;
	}
	
	public double getRatingFromID (int movieID) { //Method to get rating from movieID from other classes
		for(int po = 0; po<R.size(); po++) {
			if(R.get(po).getMovieID() == movieID) {
				return (R.get(po).getRating());
			} 
			
		}
		return 0; //MovieID cannot be found
	}
	public void addRating(String movieName, int movieID, double rating) { //This function will auto update the average rating for that movie
		Rating newRating = new Rating(movieName, movieID, rating, 0, 0);
		allRatingsArray.add(newRating);
		int ni = 0;
		while(ni != R.size()) {
			if(R.get(ni).getMovieID() == newRating.getMovieID()) {
				int timRated = R.get(ni).getTimesRated();
				double totaRating = R.get(ni).getTotalRating();
				R.get(ni).setTimesRated(timRated+1);
				R.get(ni).setTotalRating(totaRating+newRating.getRating());
				R.get(ni).setRating((R.get(ni).getTotalRating())/(R.get(ni).getTimesRated()));
			}
			ni++;
		}
		noOfRating++;
		counter++;
	}
	
	public void write() { //write function only writes new ratings because we do not need to delete ratings
		try {
		ratingDataInputFile = new FileInputStream(new File("rating.xls"));
		POIFSFileSystem fs = new POIFSFileSystem(ratingDataInputFile);
		HSSFWorkbook workbook = new HSSFWorkbook(fs);
		HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFCell cell = null;
		//System.out.println(sheet.createRow(noOfEntries).createCell((short)0));
		for(int x= (noOfRating-counter-1); x<noOfRating; x++) {
		cell = sheet.createRow(x).createCell(0);
		cell.setCellValue(allRatingsArray.get(x).getTitle());
		cell = sheet.getRow(x).createCell(1);
		cell.setCellValue(allRatingsArray.get(x).getMovieID());
		cell = sheet.getRow(x).createCell(2);
		cell.setCellValue(allRatingsArray.get(x).getRating());
		}
		ratingDataInputFile.close();
		ratingDataOutputFile = new FileOutputStream(new File("rating.xls"));
		workbook.write(ratingDataOutputFile);
		ratingDataOutputFile.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

