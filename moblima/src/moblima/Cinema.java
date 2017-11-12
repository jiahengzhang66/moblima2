package moblima;

import java.io.IOException;

public abstract class Cinema {
	private CinemaSeat[] seat;
	private int cineplexID;
	private int cinemaId;
	private String cinemaCode;
	private static int numEmptySeat;
	private String cinemaType;
	private float priceModifier;
	private float discount;
	
	
	public Cinema(int cinemaId) throws IOException{	
    }
	
	public void setCineplexID(int cineplexId) {
		this.cineplexID = cineplexId;
	}
	
	public int getCineplexID() {
		return this.cineplexID;
	}

	public void setCinemaID(int cinemaId) {
		this.cinemaId = cinemaId;
	}
	
	public int getCinemaID() {
		return this.cinemaId;
	}
	
	public void setCinemaCode(String cinemaCode) {
		this.cinemaCode = cinemaCode;
	}
	
	public String getCinemaCode() {
		return this.cinemaCode;
	}
	
	public void setDiscount(float discount) {
		this.discount = discount;
	}
	
	public float getDiscount() {
		return this.discount;
	}

	public static int numEmptySeats(CinemaSeat[] seat) {
		for(int i = 0; i < 144; i++){
			if (seat[i].isAssigned() == false)
       					numEmptySeat++;
       		};
       	return numEmptySeat;
    }

	public void assignSeat(CinemaSeat[] seat,int seatID, int cust_id) {
		int i = seatID-1;
		if (seat[i].isAssigned() == true) {
			System.out.println("Seat already assigned to a customer.");
		}
		else {seat[i].assign(cust_id);
			System.out.println("Seat Assigned!");
		}	
	}
	
	public void unAssignSeat(CinemaSeat[] seat,int seatID) {
		int i = seatID-1;
		seat[i].unAssign();
		
	}
	
	public void unassignAllSeats(CinemaSeat[] seat) {
	for(int i = 0; i < 144; i++) {
			seat[i].unAssign();
		}
	}
	public String getCinemaType() {
		return this.cinemaType;
	};
	


	public abstract float getPriceModifier();
	public abstract void setPriceModifier(float priceModifier);
	public abstract void setCinemaType(String cinemaType);
}
