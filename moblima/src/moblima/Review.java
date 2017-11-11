package moblima;

public class Review {
	private String title;
	private int movieID;
	private String review;
	
	public Review(String title, int movieID, String review) {
		this.title = title;
		this.movieID = movieID;
		this.review = review;
	}
	public String getTitle() {
		return title;
	}
	public int getMovieID() {
		return movieID;
	}
	
	public String getReview() {
			return review;
	}
	
	public void setReview(String RW) {
		review = RW;
	}

}
