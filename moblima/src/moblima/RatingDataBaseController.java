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
			int checkTimesRated = 1;
			double cumulativeRating = 0;
			ratingDataInputFile = new FileInputStream(new File("rating.xls"));
			POIFSFileSystem rate = new POIFSFileSystem(ratingDataInputFile);
			HSSFWorkbook workbook = new HSSFWorkbook(rate);
			HSSFSheet sheet = workbook.getSheetAt(0);
			for(int i = 0; i < noOfRating; i++) {
				int p = (int)sheet.getRow(i).getCell(1).getNumericCellValue(); //Getting MovieID
				cumulativeRating += sheet.getRow(i).getCell(2).getNumericCellValue(); //Adding cumulativeRating
				for(int x=(i+1); x < noOfRating; x++ ){
					int o = (int)sheet.getRow(x).getCell(1).getNumericCellValue(); //Getting Second MovieID
					if(o == p) { //If second movieId is equals to First movieID,
						checkTimesRated++; //Increase Check Times Rated
						cumulativeRating += sheet.getRow(x).getCell(2).getNumericCellValue(); //Add the second one to cumulative Rating
					}
				}
				double averageRating = cumulativeRating/checkTimesRated;
				
				int l = 0;
				Rating RATE = new Rating(sheet.getRow(i).getCell(0).getStringCellValue(),
										(int)sheet.getRow(i).getCell(1).getNumericCellValue(),
										averageRating, checkTimesRated, cumulativeRating
										);
				
				int v = 1;
				R.add(RATE);//can call this so that wont read the whole data file again
					if(R.get(v-1).getMovieID() == RATE.getMovieID()) {
					R.remove(RATE);//can call this so that wont read the whole data file again
//					System.out.println()
					v++;
					
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
//		int lop = 0;
//		while(lop != allRatingsArray.size()) {
//			System.out.println(allRatingsArray.get(lop).getTitle() + " " + allRatingsArray.get(lop).getMovieID() + " " + allRatingsArray.get(lop).getRating());
//			lop++;
//		}
		int loi = 0;
		while(loi != R.size()) {
			System.out.println(R.get(loi).getTitle() + " " + R.get(loi).getMovieID() + " " + R.get(loi).getRating() + " " + R.get(loi).getTimesRated() + " " + R.get(loi).getTotalRating());
			loi++;
		}
	}
	public double calculateAverageRating(int movieID) {//new movie
	
		int i = 0;
		int n = noOfRating;
		int counter = 0;
		while(n!=0) {
			if (R.get(i).getMovieID() == movieID) {
				totalRating += R.get(i).getRating();
				counter++;
				}	
			n--;
			i++;
		}
		totalRating = totalRating/counter;
		
		return totalRating;
}
		
		
	public void sortRankingRating() {
		ArrayList<Rating> tempList = new ArrayList<Rating>();
			tempList = (ArrayList<Rating>) R.clone();
			int po = 0;
		    while(tempList.get(po) != null) {
		      if (tempList.get(po).getRating() < tempList.get(po+1).getRating()) {
		      Rating tempRating = tempList.get(po);
		      tempList.set(po, tempList.get(po+1));
		      tempList.set(po+1, tempRating);
		      po++;
		      }
		    }
		}
	
	public void addRating(Rating newRating) {
		allRatingsArray.add(newRating);
		int ni = 0;
		while(R.get(ni) !=null) {
			if(R.get(ni).getMovieID() == newRating.getMovieID()) {
				int timRated = R.get(ni).getTimesRated();
				double totaRating = R.get(ni).getTotalRating();
				R.get(ni).setTimesRated(timRated+1);
				R.get(ni).setTotalRating(totaRating+newRating.getRating());
				R.get(ni).setRating((R.get(ni).getTotalRating())/(R.get(ni).getTimesRated()));
			}
			ni++;
		}
	}
	
	public void write() {
		try {
		ratingDataInputFile = new FileInputStream(new File("rating.xls"));
		POIFSFileSystem fs = new POIFSFileSystem(ratingDataInputFile);
		HSSFWorkbook workbook = new HSSFWorkbook(fs);
		HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFCell cell = null;
		//System.out.println(sheet.createRow(noOfEntries).createCell((short)0));
		cell = sheet.createRow(noOfRating).createCell(0);
		cell.setCellValue(allRatingsArray.get(allRatingsArray.size()-1).getTitle());
		cell = sheet.getRow(noOfRating).createCell(1);
		cell.setCellValue(allRatingsArray.get(allRatingsArray.size()-1).getMovieID());
		cell = sheet.getRow(noOfRating).createCell(2);
		cell.setCellValue(allRatingsArray.get(allRatingsArray.size()-1).getRating());
		noOfRating++; //Number of entries increase by 1
		ratingDataOutputFile.close();
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


//System.out.println("============================================");
//System.out.print("Title: " + sheet.getRow(i).getCell(0) + "\n"
//				+"movieID: " + sheet.getRow(i).getCell(1) + "\n"
//				+"rating: "+sheet.getRow(i).getCell(2) + "\n"
//				);
//System.out.println("============================================");
