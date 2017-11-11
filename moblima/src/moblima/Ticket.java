package moblima;

import java.io.*;
import java.util.*;
import java.text.ParseException;


public abstract class Ticket {

	public int seatID;
	public boolean assigned;
	public int movieID;
	public int cust_id; 
	public String ticketType; 
	public float typePriceModifier;
	public float weekendPriceModifier = 5;
	public float holidayPriceModifier = 6;
	public boolean datePriceStatus;
	public float price = 10;


	
	public Ticket(int seatID, int movieID) { 
		this.seatID = seatID;
		this.movieID = movieID;
		this.assigned = false;
		this.cust_id = -1;
		this.datePriceStatus = false;
		
		

	}
	

	public void setSeatID(int seatID) { 
		this.seatID = seatID;
	}
	
	public void setMovieID(int movieID ) {
		this.movieID = movieID;
	}
	
	public void setCustomerID (int cust_id) {
		this.cust_id = cust_id;
		
	}
	
	public void setAssigned(boolean assigned) {
		this.assigned=assigned;
	}
	
	public void setdatePriceStatus(boolean datePriceStatus) {
		this.datePriceStatus=datePriceStatus;
	}
	public void setWeekendPriceModifier(float weekendPriceModifier) {
		this.weekendPriceModifier = weekendPriceModifier;
	}
	
	public void setHolidayPriceModifier(float HolidayPriceModifier) {
		this.holidayPriceModifier = HolidayPriceModifier;
	}
	
	public int getSeatID() {
		return this.seatID;
	}
	
	public int getMovieID() {
		return this.movieID;
	}
	
	
	public int getCustomerID() { 
		return this.cust_id;
	}
	
	public boolean getAssigned() {
		return this.assigned;
	}
	
	public boolean getdatePriceStatus() {
		return this.datePriceStatus;
	}
	
	public float getWeekendPriceModifier() {
		return this.weekendPriceModifier ;
	}
	
	public float getHolidayPriceModifier() {
		return this.holidayPriceModifier;
	}

	
	
	public void assign(int cust_id) {
		this.cust_id = cust_id;
		assigned = true;
	}
	

	public void unAssign() {
		this.cust_id = -1;
		assigned = false;
	}

	public float getPrice() {
		return this.price;
	}
	

	 //add everything to class diagram
	public abstract void setTypePriceModifier(float priceModifier);
	public abstract String getTicketType();
	public abstract float getTypePriceModifier();
}

