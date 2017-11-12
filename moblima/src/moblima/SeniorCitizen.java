package moblima;

public class SeniorCitizen extends Ticket {
	public SeniorCitizen (int seatID, int movieID) {
	super(seatID, movieID); 
	this.price = 6;
	this.ticketType = "Senior Citizen Ticket";
	this.typePriceModifier = 6;
	}
	
	public void setTypePriceModifier(float priceModifier) {
		this.typePriceModifier = priceModifier;
	};
	
	public String getTicketType() {
		return this.ticketType;
	};
	public float getTypePriceModifier() {
		return this.typePriceModifier;
	};
	public float getPrice() {
		return this.price;
	}
}
