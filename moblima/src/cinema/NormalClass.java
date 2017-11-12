package cinema;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class NormalClass extends Cinema{
	private String cinemaType;
	private float priceModifier;

	public NormalClass(int cinemaId) throws IOException {
		super(cinemaId); 		
		this.cinemaType = "NormalClass";
		this.priceModifier = 0;
		}
	
	/*public void createCSV(int cinemaId) throws IOException {
		CinemaSeat[] seat = new CinemaSeat[144];
		BufferedWriter pw = new BufferedWriter(new FileWriter("cinema"+cinemaId+".csv"));
    	StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 144; i++)//for each row
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

	}		*/
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
