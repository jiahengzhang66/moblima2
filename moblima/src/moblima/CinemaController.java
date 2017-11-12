package moblima;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

import service.CinemaApp;
import service.CinemaService;
import service.GoldServiceImpl;
import service.NormalServiceImpl;
import service.PlatinumServiceImpl;

public class CinemaController {
	private static Cineplex[] allcineplex =  new Cineplex[3];
	private static Cinema[] allcinemas = new Cinema[15];

	private static CinemaController cinemaController = null;
	
	public static CinemaController getInstance() {
		if (cinemaController == null)
			cinemaController = new CinemaController();
		return cinemaController;
	}
	
	public static void createNewCSV(int cinemaId, int year, int month, int date, int time) throws IOException {
		String temp = CinemaController.cinemaType(cinemaId);
		CinemaSeat[] seat = null;
		CinemaApp abc = null;
		if (Objects.equals(temp, "NormalClass") ) {
			abc = new CinemaApp(new NormalServiceImpl());
			seat = new CinemaSeat[144];
			for (int i = 0; i<144;i++) {
				seat[i] = new CinemaSeat(i+1);
			}
		} else if (Objects.equals(temp, "GoldClass")) {
			abc = new CinemaApp(new GoldServiceImpl());
				seat = new CinemaSeat[54];
				for (int i = 0; i<54;i++) {
					seat[i] = new CinemaSeat(i+1);
				}
		} else if (Objects.equals(temp, "PlatinumClass")) {
			abc = new CinemaApp(new PlatinumServiceImpl());
				seat = new CinemaSeat[40];
				for (int i = 0; i<40;i++) {
					seat[i] = new CinemaSeat(i+1);
				}
		} else 	{System.out.println("Invalid cinemaId");
			}
		abc.createNewCSV(seat, cinemaId, year, month, date, time);
	}
	
	public static void CinematoCSV(CinemaSeat[] seat, int cinemaId, int year, int month, int date, int time) throws IOException {
		String temp = CinemaController.cinemaType(cinemaId);
		CinemaApp abc = null;
		if (Objects.equals(temp, "NormalClass") ) {
			abc = new CinemaApp(new NormalServiceImpl());
			
		} else if (Objects.equals(temp, "GoldClass")) {
			abc = new CinemaApp(new GoldServiceImpl());
				
		} else if (Objects.equals(temp, "PlatinumClass")) {
			abc = new CinemaApp(new PlatinumServiceImpl());

		} else System.out.println("Invalid cinemaId");
		
		abc.saveCinemaToCSV(seat, cinemaId, year, month, date, time);	
	}
	
	public static void CSVToCinema(CinemaSeat[] seat, int cinemaId, int year, int month, int date, int time) {
		String temp = CinemaController.cinemaType(cinemaId);
		CinemaApp abc = null;
		if (Objects.equals(temp, "NormalClass") ) {
			abc = new CinemaApp(new NormalServiceImpl());
			
		} else if (Objects.equals(temp, "GoldClass")) {
			abc = new CinemaApp(new GoldServiceImpl());
				
		} else if (Objects.equals(temp, "PlatinumClass")) {
			abc = new CinemaApp(new PlatinumServiceImpl());

		} else System.out.println("Invalid cinemaId");
		abc.openCSVToCinema(seat, cinemaId, year, month, date, time);				
	}
	
	public static void printHallSeats(int cinemaId,int year, int month, int date, int time) throws IOException {	
		String temp = CinemaController.cinemaType(cinemaId);
		CinemaApp abc = null;
		if (Objects.equals(temp, "NormalClass") ) {
			abc = new CinemaApp(new NormalServiceImpl());
			
		} else if (Objects.equals(temp, "GoldClass")) {
			abc = new CinemaApp(new GoldServiceImpl());
				
		} else if (Objects.equals(temp, "PlatinumClass")) {
			abc = new CinemaApp(new PlatinumServiceImpl());

		} else System.out.println("Invalid cinemaId");
		
		abc.printHallSeats(cinemaId, year, month, date, time);
	}

	public static void cineplexInfo(Cineplex[] allcineplex) {
		String csvFile = "CineplexInfo.csv";
        String line = "";
        String cvsSplitBy = ",";
        String[] temp;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

        	
        	for(int i = 0; i < 4&&(line = br.readLine()) != null; i++){
                // use comma as separator
                temp = line.split(cvsSplitBy); 
                allcineplex[i] = new Cineplex();
                allcineplex[i].setCineplexName(temp[0]);
                allcineplex[i].setCineplexID(Integer.parseInt(temp[1]));
                allcineplex[i].setLocation(temp[3]);
                allcineplex[i].setNumOfCinemas(Integer.parseInt(temp[3]));
               	};

        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public static void cinemaInfo(Cinema[] allcinema) {
		String csvFile = "CinemaInfo.csv";
        String line = "";
        String cvsSplitBy = ",";
        String[] temp;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

        	
        	for(int i = 0; i < 15&&(line = br.readLine()) != null; i++){
                // use comma as separator
                temp = line.split(cvsSplitBy); 
                allcinema[i] = new NormalClass(0);
                allcinema[i].setCineplexID(Integer.parseInt(temp[0]));
                allcinema[i].setCinemaID(Integer.parseInt(temp[1]));
                allcinema[i].setCinemaCode(temp[2]);
                allcinema[i].setCinemaType(temp[3]);
                allcinema[i].setPriceModifier(Float.parseFloat(temp[4]));
                allcinema[i].setDiscount(Float.parseFloat(temp[5]));
               	};

        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static String cineplexName(int cineplexID) {
		cineplexInfo(allcineplex);
		String cineplexName = null;
		for (int i=0;i<3;i++) {
			if (allcineplex[i].getCineplexID() == cineplexID) {
				cineplexName = allcineplex[i].getCineplexName();
			}
		}
		return cineplexName;
	}
	
	public static String cinemaType(int cinemaID) {
		cinemaInfo(allcinemas);
		String cinemaType = null;
		for (int i=0;i<15;i++) {
			if (allcinemas[i].getCinemaID() == cinemaID) {
				cinemaType = allcinemas[i].getCinemaType();
			}
		}
		return cinemaType;
	}
	
	public static String cinemaCode(int cinemaID) {
		cinemaInfo(allcinemas);
		String cinemaCode = null;
		for (int i=0;i<15;i++) {
			if (allcinemas[i].getCinemaID() == cinemaID) {
				cinemaCode = allcinemas[i].getCinemaCode();
			}
		}
		return cinemaCode;
	}

	public static float cinemaPriceModifier(int cinemaID) {
		cinemaInfo(allcinemas);
		float cinemaPriceModifier = 0;
		for (int i=0;i<15;i++) {
			if (allcinemas[i].getCinemaID() == cinemaID) {
				cinemaPriceModifier = allcinemas[i].getPriceModifier();
			}
		}
		return cinemaPriceModifier;
	}
	
	public static float cinemaDiscount(int cinemaID) {
		cinemaInfo(allcinemas);
		float cinemaDiscount = 0;
		for (int i=0;i<15;i++) {
			if (allcinemas[i].getCinemaID() == cinemaID) {
				cinemaDiscount = allcinemas[i].getDiscount();
			}
		}
		return cinemaDiscount;
	}
	
	public static void cinemaID( int cineplexID) {		
		int j = 1;
		cinemaInfo(allcinemas);
		for (int i=0;i<15;i++) {
			if (allcinemas[i].getCineplexID() == cineplexID) {
				System.out.println("Cinema " + j +":" + allcinemas[i].getCinemaID());
				j++;
			}
		}
	}
		
	public static void setcinemaPriceModifier(int cinemaID, float PriceModifier) throws IOException {
		cinemaInfo(allcinemas);
		for (int i=0;i<15;i++) {
			if (allcinemas[i].getCinemaID() == cinemaID) {
					allcinemas[i].setPriceModifier(PriceModifier);
			}
		}
		BufferedWriter pw = new BufferedWriter(new FileWriter("CinemaInfo.csv"));
    	StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 15; i++)//for each row
    	{
 
			sb.append(allcinemas[i].getCinemaID()+","+allcinemas[i].getCinemaType()+","+allcinemas[i].getPriceModifier()+","+allcinemas[i].getDiscount()+"\n");

    			}
    	pw.write(sb.toString());
    	pw.close();
    	System.out.println("done!");
	}
	
	public static void setcinemaDiscount(int cinemaID, float Discount) throws IOException {
		cinemaInfo(allcinemas);
		for (int i=0;i<15;i++) {
			if (allcinemas[i].getCinemaID() == cinemaID) {
					allcinemas[i].setDiscount(Discount);
			}
		}
		BufferedWriter pw = new BufferedWriter(new FileWriter("CinemaInfo.csv"));
    	StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 15; i++)//for each row
    	{
 
			sb.append(allcinemas[i].getCinemaID()+","+allcinemas[i].getCinemaType()+","+allcinemas[i].getPriceModifier()+","+allcinemas[i].getDiscount()+"\n");

    			}
    	pw.write(sb.toString());
    	pw.close();
    	System.out.println("done!");
	}
}
	

