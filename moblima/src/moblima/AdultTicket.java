package moblima;

public class AdultTicket extends Ticket {
	public AdultTicket (int seatID, int movieID) {
	super(seatID, movieID); 
	this.price = 10;
	this.ticketType = "AdultTicket";
	this.typePriceModifier = 10;
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

