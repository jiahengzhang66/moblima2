package cinema;

public class CinemaSeat {
	private int seatID = 999;
	private boolean assigned = false;
	private int movieGoersID = 999999;


	public CinemaSeat(int seatID) {}
	
	public CinemaSeat() {}
	
	public int getSeatID() {
		return this.seatID;
	}
	public void setSeatID(int seatID) {
		this.seatID = seatID;
	}
	
	
	public void assign(int cust_id) {
		setAssigned(true);
		setMovieGoersID(cust_id);
	}
	
	public void unAssign() {
		setAssigned(false);
		setMovieGoersID(999999);
	}

	public int getMovieGoersID() {
		return movieGoersID;
	}

	public void setMovieGoersID(int movieGoersID) {
		this.movieGoersID = movieGoersID;
	}

	public boolean isAssigned() {
		return assigned;
	}

	public void setAssigned(boolean assigned) {
		this.assigned = assigned;
	}

}

