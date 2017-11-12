package moblima;

public class ChildTicket extends Ticket {
	
	public ChildTicket (int seatID, int movieID) {
	super(seatID,movieID); 
	this.price = 8;
	this.ticketType = "ChildTicket";
	this.typePriceModifier = 8;
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