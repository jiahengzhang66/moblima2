package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import cinema.CinemaSeat;

public class GoldServiceImpl implements CinemaService {
	
	public void cinemaToCSV(CinemaSeat[] seat, int cinemaId, int year, int month, int date , int time) throws IOException {
		File file = new File("cinemas" + File.separator + year + File.separator + month + File.separator + date + File.separator + time + cinemaId + ".csv");
		BufferedWriter pw = new BufferedWriter(new FileWriter(file));
    	StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 54; i++)//for each row
    	{
    			    			
    	        sb.append(seat[i].getSeatID()+","+seat[i].isAssigned()+","+seat[i].getMovieGoersID()+"\n");

    			}
    			pw.write(sb.toString());
    	        pw.close();
    	        System.out.println("done!");
	}
	
	public void printCinemaSeats(int cinemaId, int year, int month, int date, int time) {
		CinemaSeat[] tempseat = new CinemaSeat[144];
		CSVToCinema(tempseat, cinemaId, year, month, date, time);
		String[][] temphall = new String[6][17];
		for (int i=0;i<6;i++) {
			String letter = null;
			switch (i) {
			case 0: letter = "F";
				break;
			case 1: letter = "E";
				break;
			case 2: letter = "D";
				break;
			case 3: letter = "C";
				break;
			case 4: letter = "B";
				break;
			case 5: letter = "A";
				break;		
			}
			
			for (int j=0;j<17;j++) {
				if (j==1||j==4||j==5||j==11||j==12||j==15) {					//for aisles
					temphall[i][j]=" ";
				} else if (j>=2&&j<=3) {										//for columns 2-3
				if(tempseat[i*8 +(j-2)].isAssigned()==true) {
					temphall[i][j]="X";
					}else temphall[i][j]="O";
				} else if (j>=6&&j<=10) {										//for columns 6-10
					if(tempseat[i*8 +(j-4)].isAssigned()==true) {
						temphall[i][j]="X";
					}else temphall[i][j]="O";
				} else if (j>=13&&j<=14) {
					if(tempseat[i*8+(j-7)].isAssigned()==true) {				//for columns 12-13
					temphall[i][j]="X";
					}else temphall[i][j]="O";					
				} else { temphall[i][j] = letter;}					
			}
		}
	
		for (int i=0;i<6;i++) {
			String temp = "";		
			for(int j=0;j<17;j++) {
				temp += temphall[i][j];
			}
		System.out.println(temp);
		}


	}
	
	public void CSVToCinema(CinemaSeat[] seat,int cinemaId,int year, int month, int date, int time) {
		String csvFile ="cinemas" + File.separator + year + File.separator + month + File.separator + date + File.separator + time + cinemaId + ".csv";
        String line = "";
        String cvsSplitBy = ",";
        String[] scinema;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {

        	
        	for(int i = 0; i < 54&&(line = br.readLine()) != null; i++){
                // use comma as separator
                scinema = line.split(cvsSplitBy); 
                seat[i] = new CinemaSeat((i+1));
                seat[i].setSeatID(Integer.parseInt(scinema[0]));
                seat[i].setAssigned(Boolean.parseBoolean(scinema[1]));
        		seat[i].setMovieGoersID(Integer.parseInt(scinema[2]));
/*              	System.out.println(seat[i].getSeatID() + "," + seat[i].isAssigned() + ","+seat[i].getMovieGoersID());
               	System.out.println(scinema[0] + "," + scinema[1] + ","+scinema[2]);	    */    		
               	};

        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
