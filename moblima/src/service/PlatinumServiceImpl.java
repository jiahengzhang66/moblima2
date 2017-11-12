package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import cinema.CinemaSeat;

public class PlatinumServiceImpl implements CinemaService {
	
	public void cinemaToCSV(CinemaSeat[] seat, int cinemaId, int year, int month, int date , int time) throws IOException {
		File file = new File("cinemas" + File.separator + year + File.separator + month + File.separator + date + File.separator + time + cinemaId + ".csv");
		BufferedWriter pw = new BufferedWriter(new FileWriter(file));
    	StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 40; i++)//for each row
    	{
    			seat[i].setSeatID(i+1);
    			
    	        sb.append(seat[i].getSeatID()+","+seat[i].isAssigned()+","+seat[i].getMovieGoersID()+"\n");

    			}
    			pw.write(sb.toString());
    	        pw.close();
    	        System.out.println("done!");
	}
	
	public void printCinemaSeats(int cinemaId, int year, int month, int date, int time) {
		CinemaSeat[] tempseat = new CinemaSeat[40];
		CSVToCinema(tempseat, cinemaId, year, month, date, time);
		String[][] temphall = new String[5][17];
		for (int i=0;i<5;i++) {
			String letter = null;
			switch (i) {
				case 0: letter = "E";
					break;
				case 1: letter = "D";
					break;
				case 2: letter = "C";
					break;
				case 3: letter = "B";
					break;
				case 4: letter = "A";
					break;		
			}
				
			for (int j=0;j<17;j++) {
				if (j==1||j==2||j==5||j==8||j==11||j==14||j==15) {				//for aisles
					temphall[i][j]=" ";
				} else if (j==3||j==4) {										//for columns 3-4
					if(tempseat[i*8 +(j-3)].isAssigned()==true) {
						temphall[i][j]="X";
					}else temphall[i][j]="O";
				} else if (j==6||j==7) {										//for columns 6-7
					if(tempseat[i*8 +(j-4)].isAssigned()==true) {
						temphall[i][j]="X";
					}else temphall[i][j]="O";
				} else if (j==9||j==10) {										//for columns 9-10
					if(tempseat[i*8 +(j-5)].isAssigned()==true) {
						temphall[i][j]="X";
					}else temphall[i][j]="O";
				} else if (j==12||j==13) {
					if(tempseat[i*8+(j-6)].isAssigned()==true) {				//for columns 12-13
						temphall[i][j]="X";
					}else temphall[i][j]="O";					
				} else { temphall[i][j] = letter;}					
			}
		}
		
		for (int i=0;i<5;i++) {
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

        	
        	for(int i = 0; i < 40&&(line = br.readLine()) != null; i++){
                // use comma as separator
                scinema = line.split(cvsSplitBy); 
                seat[i] = new CinemaSeat((i+1));
                seat[i].setSeatID(Integer.parseInt(scinema[0]));
                seat[i].setAssigned(Boolean.parseBoolean(scinema[1]));
        		seat[i].setMovieGoersID(Integer.parseInt(scinema[2]));
/*              	System.out.println(seat[i].getSeatID() + "," + seat[i].isAssigned() + ","+seat[i].getMovieGoersID());
               	System.out.println(scinema[0] + "," + scinema[1] + ","+scinema[2]);	   */     		
               	};

        } catch (IOException e) {
            e.printStackTrace();
        }
	}

}
