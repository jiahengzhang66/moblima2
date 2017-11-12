package moblima;

import java.util.*;
import java.util.ArrayList;
import java.util.Date;

import java.util.Scanner;
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

public class MovieDataBaseController {
	ArrayList <Movies> A = new ArrayList<Movies>();
		int noOfRow = 0; //calculate n;
		private FileInputStream movieDataInputFile;
		private FileOutputStream movieDataOutputFile;
		
		public MovieDataBaseController() {//this will read the file first;
			FileInputStream movieDataInputFile;
			FileOutputStream movieDataOutputFile;
				try{
			
					movieDataInputFile = new FileInputStream(new File("movie.xls"));
					//bookingDatabaseOutputFile = new FileOutputStream(new File("Booking Database.xls")); n
					this.movieDataInputFile = movieDataInputFile;
					//this.bookingDatabaseOutputFile = bookingDatabaseOutputFile;
					POIFSFileSystem ms = new POIFSFileSystem(movieDataInputFile);
					HSSFWorkbook workbook = new HSSFWorkbook(ms);
					HSSFSheet sheet = workbook.getSheetAt(0);
					while(sheet.getRow(noOfRow) != null) {
						noOfRow++;
				}
					movieDataInputFile.close();
			}catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		public int getTotalMovies() {
			return noOfRow;
		}
		public void generateMovieArray(){//this is to print the movie in the data file without ranking
			try {
				movieDataInputFile = new FileInputStream(new File("movie.xls"));
				POIFSFileSystem ms = new POIFSFileSystem(movieDataInputFile);
				HSSFWorkbook workbook = new HSSFWorkbook(ms);
				HSSFSheet sheet = workbook.getSheetAt(0);
				for(int i =0; i < noOfRow; i++) {
					
					if(sheet.getRow(i).getCell(3).getStringCellValue().equals("Digital")) {
					Movies MV = new MovieDigital(sheet.getRow(i).getCell(0).getStringCellValue(), 
											(int)sheet.getRow(i).getCell(1).getNumericCellValue(),
											sheet.getRow(i).getCell(2).getStringCellValue(),
											sheet.getRow(i).getCell(3).getStringCellValue(),
											sheet.getRow(i).getCell(4).getStringCellValue(),
											sheet.getRow(i).getCell(5).getStringCellValue(),
											sheet.getRow(i).getCell(6).getStringCellValue(),
											sheet.getRow(i).getCell(7).getStringCellValue(),
											sheet.getRow(i).getCell(8).getStringCellValue(),
											sheet.getRow(i).getCell(9).getStringCellValue(),
											sheet.getRow(i).getCell(10).getStringCellValue(),
											sheet.getRow(i).getCell(11).getBooleanCellValue());
					A.add(MV);
					} else {
						Movies MV = new Movie3D(sheet.getRow(i).getCell(0).getStringCellValue(), 
								(int)sheet.getRow(i).getCell(1).getNumericCellValue(),
								sheet.getRow(i).getCell(2).getStringCellValue(),
								sheet.getRow(i).getCell(3).getStringCellValue(),
								sheet.getRow(i).getCell(4).getStringCellValue(),
								sheet.getRow(i).getCell(5).getStringCellValue(),
								sheet.getRow(i).getCell(6).getStringCellValue(),
								sheet.getRow(i).getCell(7).getStringCellValue(),
								sheet.getRow(i).getCell(8).getStringCellValue(),
								sheet.getRow(i).getCell(9).getStringCellValue(),
								sheet.getRow(i).getCell(10).getStringCellValue(),
								sheet.getRow(i).getCell(11).getBooleanCellValue()
								);
						A.add(MV);
					}
				
								
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		public void displayOneMovieDetail(int movieID) { //display details for 1 movie, but the rating and review you have to call it from your side and key in the same movieID again because it doenst make sense for me to initialize 1 more controller in my class
			for(int lkj = 0; lkj < A.size(); lkj++) {
				if (movieID == A.get(lkj).getMovieID()) {
					System.out.println("============================================");
					System.out.print("Title: " + A.get(lkj).getTitle() + "\n"
									+"MovieID: " + A.get(lkj).getMovieID() + "\n"
									+"TypeOfMovie" + A.get(lkj).getTypeOfMovie() + "\n"
									+"Genre: "+ A.get(lkj).getGenre() + "\n"
									+"Synopsis: "+A.get(lkj).getSynopsis() + "\n"
									+"Status: "+ A.get(lkj).getStatus() + "\n"
									+"Age Rating: "+ A.get(lkj).getAgeRating() + "\n"
									+"Actors: "+ A.get(lkj).getActors() + "\n"
									+"Directors: "+ A.get(lkj).getDirector() + "\n"
									+"BlockBuster: "+ A.get(lkj).getBlockBuster() + "\n");
				}
			}
			
		}
		public void displayMovies() {
			int i = 0;
			while(A.get(i) != null) {
			System.out.println("============================================");
			System.out.print("Title: " + A.get(i).getTitle() + "\n"
							+"MovieID: " + A.get(i).getMovieID() + "\n"
							+"TypeOfMovie" + A.get(i).getTypeOfMovie() + "\n"
							+"Genre: "+ A.get(i).getGenre() + "\n"
							+"Synopsis: "+A.get(i).getSynopsis() + "\n"
							+"Status: "+ A.get(i).getStatus() + "\n"
							+"Age Rating: "+ A.get(i).getAgeRating() + "\n"
							+"Actors: "+ A.get(i).getActors() + "\n"
							+"Directors: "+ A.get(i).getDirector() + "\n"
							+"BlockBuster: "+ A.get(i).getBlockBuster() + "\n");
			System.out.println("============================================");
			i++;
			if(i == A.size()) {
				break;
			}
			}
		}
		
		public int setMovieID() {
			System.out.println(noOfRow);
				if(noOfRow == 0) {
					return 10000;
				} else {
					return (10000 + noOfRow);
			}
		}
		
	public ArrayList<Movies> getMoviesList() {
		return A;
		}
	
	public Movies searchMovie(int IDToSearch) {
		int o = 0;
		while(A.get(o).getMovieID() != IDToSearch) {
			o++;
		}
		return A.get(o);
	}
	
	public void addMovie(Movies newMovie) {//add to the arraylist
			if(newMovie.getTypeOfMovie().equals("Digital")) {
				MovieDigital newDigitalMovie = new MovieDigital(newMovie.getTitle(),
																newMovie.getMovieID(),
																newMovie.getReleaseDate(),
																newMovie.getTypeOfMovie(),
																newMovie.getGenre(),
																newMovie.getLanguage(),
																newMovie.getSynopsis(),
																newMovie.getStatus(),
																newMovie.getAgeRating(),
																newMovie.getActors(),
																newMovie.getDirector(),
																newMovie.getBlockBuster());
				A.add(newDigitalMovie);
			} else {
				Movie3D new3DMovie = new Movie3D(newMovie.getTitle(),
						newMovie.getMovieID(),
						newMovie.getReleaseDate(),
						newMovie.getTypeOfMovie(),
						newMovie.getGenre(),
						newMovie.getLanguage(),
						newMovie.getSynopsis(),
						newMovie.getStatus(),
						newMovie.getAgeRating(),
						newMovie.getActors(),
						newMovie.getDirector(),
						newMovie.getBlockBuster());
				
				A.add(new3DMovie);
			}
			noOfRow++;
	}
	
	public void removeMovie(int MovieID) {//this is to remove something from the data
		int i=(A.size()-1);
		while (i != -1) {
			if (A.get(i).getMovieID() == MovieID ) {
				A.remove(i);
				break;
			}
			i--;
		}
	}
	
	
	public void write() {
		HSSFWorkbook workbook;
		int z;	
	try {
		movieDataInputFile = new FileInputStream(new File("movie.xls"));
		POIFSFileSystem ms = new POIFSFileSystem(movieDataInputFile);
		workbook = new HSSFWorkbook(ms);
		HSSFSheet sheet = workbook.getSheetAt(0);
		HSSFCell cell = null;
		for (z=0;z<noOfRow;z++) {
		cell = sheet.createRow(noOfRow-1).createCell(0);
		cell.setCellValue(((Movies) A.get(z)).getTitle());
		cell = sheet.getRow(noOfRow-1).createCell(1);
		cell.setCellValue(((Movies) A.get(z)).getMovieID());
		cell = sheet.getRow(noOfRow-1).createCell(2);
		cell.setCellValue(((Movies) A.get(z)).getReleaseDate());
		cell = sheet.getRow(noOfRow-1).createCell(3);
		cell.setCellValue(((Movies) A.get(z)).getTypeOfMovie());
		cell = sheet.getRow(noOfRow-1).createCell(4);
		cell.setCellValue(((Movies) A.get(z)).getGenre());
		cell = sheet.getRow(noOfRow-1).createCell(5);
		cell.setCellValue(((Movies) A.get(z)).getLanguage());
		cell = sheet.getRow(noOfRow-1).createCell(6);
		cell.setCellValue(((Movies) A.get(z)).getSynopsis());
		cell = sheet.getRow(noOfRow-1).createCell(7);
		cell.setCellValue(((Movies) A.get(z)).getStatus());
		cell = sheet.getRow(noOfRow-1).createCell(8);
		cell.setCellValue(((Movies) A.get(z)).getAgeRating());
		cell = sheet.getRow(noOfRow-1).createCell(9);
		cell.setCellValue(((Movies) A.get(z)).getActors());
		cell = sheet.getRow(noOfRow-1).createCell(10);
		cell.setCellValue(((Movies) A.get(z)).getDirector());
		cell = sheet.getRow(noOfRow-1).createCell(11);
		cell.setCellValue(((Movies) A.get(z)).getBlockBuster());
		cell = sheet.getRow(noOfRow-1).createCell(12);
		} 
		movieDataInputFile.close();
		movieDataOutputFile = new FileOutputStream(new File("movie.xls"));
		workbook.write(movieDataOutputFile);
		movieDataOutputFile.close();
	}catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
}
}
