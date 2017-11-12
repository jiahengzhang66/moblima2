package moblima;

public class Cineplex {
	private String cineplexName;
	private int cineplexID;
	private Cinema[] cinemas;
	private String location;
	private int numofCinemas;

	
	public Cineplex() {
	}
	
	public int getCineplexID() {
		return cineplexID;		
	}
	public String getCineplexName() {
		return cineplexName;		
	}
	public String getLocation() {
		return location;		
	}
	public int getNoOfCinemas() {
		return numofCinemas;		
	}
	
	public void setCineplexName(String cineplexName) {
		this.cineplexName = cineplexName;		
	}

	public void setCineplexID(int cineplexID) {
		this.cineplexID = cineplexID;		
	}

	public void setLocation(String location) {
		this.location = location;	
	}

	public void setNumOfCinemas(int numofCinemas) {
		this.numofCinemas = numofCinemas;		
	}
	
	
}
