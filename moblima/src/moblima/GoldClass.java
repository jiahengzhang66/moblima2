package moblima;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class GoldClass extends Cinema{
	private String cinemaType;
	private float priceModifier;

	public GoldClass(int cinemaId) throws IOException {
		super(cinemaId); 		
		this.cinemaType = "GoldClass";
		this.priceModifier = 0;
		}

/*	public void createCSV(int cinemaId) throws IOException {
		CinemaSeat[] seat = new CinemaSeat[54];
		BufferedWriter pw = new BufferedWriter(new FileWriter("cinema"+cinemaId+".csv"));
    	StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 54; i++)//for each row
    	{
    			seat[i] = new CinemaSeat((i+1));
    			seat[i].setSeatID(i+1);
    			
    	        sb.append(seat[i].getSeatID()+","+seat[i].isAssigned()+","+seat[i].getMovieGoersID()+"\n");

    			}
    			pw.write(sb.toString());
    	        pw.close();
    	        System.out.println("done!");
	}
	
	public void printCinemaSeats(int cinemaId) {
		CinemaSeat[] tempseat = new CinemaSeat[144];
		CinemaController.CSVToCinema(tempseat, cinemaId);
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

	}*/
	
	public void setPriceModifier(float newPriceModifier) {
		this.priceModifier = newPriceModifier;
	};
	
	public float getPriceModifier() {
		return this.priceModifier;
	}

	public String getCinemaType() {
		return this.cinemaType;
	}

	public void setCinemaType(String cinemaType) {
		this.cinemaType = cinemaType;		
	};

}
