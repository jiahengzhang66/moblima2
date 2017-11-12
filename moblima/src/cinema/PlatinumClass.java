package cinema;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PlatinumClass extends Cinema{
	private String cinemaType;
	private float priceModifier;

	public PlatinumClass(int cinemaId) throws IOException {
		super(cinemaId); 		
		this.cinemaType = "PlatinumClass";
		this.priceModifier = 0;
		}

/*	public void createCSV(int cinemaId) throws IOException {
		CinemaSeat[] seat = new CinemaSeat[40];
		BufferedWriter pw = new BufferedWriter(new FileWriter("cinema"+cinemaId+".csv"));
    	StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 40; i++)//for each row
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
		CinemaSeat[] tempseat = new CinemaSeat[40];
		CinemaController.CSVToCinema(tempseat, cinemaId);
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
