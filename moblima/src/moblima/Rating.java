package moblima;

public class Rating {
	private String title;
	private int movieID;
	private double rating;
	private int timesRated;
	private double totalRating;

	
	public Rating(String title, int movieID, double rating, int timesRated, double totalRating) {
		this.title = title;
		this.movieID = movieID;
		this.rating = rating;
		this.timesRated = timesRated;
		this.totalRating = totalRating;
	}
	
	public double getTotalRating() {
		return totalRating;
	}
	public void setTotalRating(double setTR) {
		this.totalRating = setTR;
		
	}
	
	public int getTimesRated() {
		return timesRated;
	}
	public void setTimesRated(int TimesRated) {
		this.timesRated = TimesRated;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getMovieID() {
		return movieID;
	}
	
	public double getRating() {
		return rating;
	}
		
	public void setRating(double d) {
		rating = d;
		
	}
	
	
	
	
}


