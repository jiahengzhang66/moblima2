package service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import moblima.CinemaSeat;

public class NormalServiceImpl implements CinemaService {
	
	public void cinemaToCSV(CinemaSeat[] seat, int cinemaId, int year, int month, int date , int time) throws IOException {
		File file = new File("cinemas" + File.separator + year + File.separator + month + File.separator + date + File.separator + time + cinemaId + ".csv");
		BufferedWriter pw = new BufferedWriter(new FileWriter(file));
    	StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 144; i++)//for each row
    	{
    			seat[i].setSeatID(i+1);
    			
    	        sb.append(seat[i].getSeatID()+","+seat[i].isAssigned()+","+seat[i].getMovieGoersID()+"\n");

    			}
    			pw.write(sb.toString());
    	        pw.close();
    	        System.out.println("done!");
	}
	
	public void printCinemaSeats(int cinemaId, int year, int month, int date, int time) {
		CinemaSeat[] tempseat = new CinemaSeat[144];
		CSVToCinema(tempseat, cinemaId, year, month, date, time);
		String[][] temphall = new String[9][23];
		for (int i=0;i<9;i++) {
			String letter = null;
			switch (i) {
				case 0: letter = "J";
					break;
				case 1: letter = "H";
					break;
				case 2: letter = "G";
					break;
				case 3: letter = "F";
					break;
				case 4: letter = "E";
					break;
				case 5: letter = "D";
					break;
				case 6: letter = "C";
					break;
				case 7: letter = "B";
					break;
				case 8: letter = "A";
					break;		
			}
				
			for (int j=0;j<23;j++) {
				if (j==1||j==2||j==11||j==20||j==21) {							//for aisles
					temphall[i][j]=" ";
				} else if (j>=3&&j<=10) {										//for columns 3-11
					if(tempseat[i*16 +(j-3)].isAssigned()==true) {
						temphall[i][j]="X";
					}else temphall[i][j]="O";
				} else if (j>=12&&j<=19) {
					if(tempseat[i*16+(j-4)].isAssigned()==true) {
						temphall[i][j]="X";
					}else temphall[i][j]="O";					
				} else { temphall[i][j] = letter;}					
			}
		}
		
		for (int i=0;i<9;i++) {
			String temp = "";		
			for(int j=0;j<23;j++) {
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

        	
        	for(int i = 0; i < 144&&(line = br.readLine()) != null; i++){
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
