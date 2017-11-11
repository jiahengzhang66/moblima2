package moblima;

public class Booking {
	private int bookingID;
	//private String Name;
	private double mobileNum;
	private String email;
	private double ticketID;
	private double cinemaCode;
	private String dateTime;
	private int movieID;
	
public Booking(int bookingID, /*String name*/ double mobileNum, String email, double ticketID, double cinemaCode, String dateTime, int movieID) {
	this.bookingID = bookingID;
	this.mobileNum = mobileNum;
	this.email = email;
	this.movieID= movieID;
	this.cinemaCode = cinemaCode;
	this.dateTime = dateTime;
	this.ticketID = ticketID;
}

public int getBookingID() {
	return this.bookingID;
}
public double getMobileNum() {
	return this.mobileNum;
}
public String getEmail() {
	return this.email;
}
public int getMovieID() {
	return this.movieID;
}
public double getCinemaCode() {
	return this.cinemaCode;
}
public double getTicketID() {
	return this.ticketID;
}
public String getDateTime() {
	return this.dateTime;
}
}
