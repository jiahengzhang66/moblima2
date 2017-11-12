package moblima;

public class Booking {
	private int bookingID;
	private String transactionID;
	private String name;
	private double mobileNum;
	private String email;
	private String ticketID;
	private int movieID;

public Booking(int bookingID, String transactionID, String name, double mobileNum, String email, String ticketID, int movieID) {
	this.bookingID = bookingID;
	this.transactionID = transactionID;
	this.name = name;
	this.mobileNum = mobileNum;
	this.email = email;
	this.movieID= movieID;
	this.ticketID = ticketID;
}

public int getBookingID() {
	return this.bookingID;
}
public String getName() {
	return this.name;
}
public String getTransactionID() {
	return this.transactionID;
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
public String getTicketID() {
	return this.ticketID;
}

}
