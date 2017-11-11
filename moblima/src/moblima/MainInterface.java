package moblima;

public class MainInterface {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MovieDataBaseController test = new MovieDataBaseController();
		test.generateMovieArray();
//		test.displayMovies();
//		System.out.println(test.searchMovie(10009).getTitle());
//		Movies testerMovie = new Movies("ABC",10010,"26/10/2017", "Digital","Action/ Adventure","English(Sub: Chinese)","testing Synopsis","now showing","PG13: Some Violence","Chris Hemsworth, Tom Hiddleston, Cate Blanchett, Idris Elba, Jeff Goldblum, Tessa Thompson, Karl Urban, Mark Ruffalo, Anthony Hopkins","Taika Waititi",true,4.5);
//		test.addMovie(testerMovie);
//		test.displayMovies();
//		test.removeMovie(10010);
//		test.displayMovies();
//		test.write();
		ReviewDataBaseController testReview = new ReviewDataBaseController();
//		Review teste = new Review("ABC", 10004, "blahblahblah");
//		Review teste2 = new Review("ABC", 10004, "blahblahblah");
//		testReview.generateReviewArray();
//		testReview.addReview(teste);
//		testReview.addReview(teste2);
//		testReview.write();
		RatingDataBaseController testRating = new RatingDataBaseController();
		testRating.generateRatingArray();
//		testRating.printRatings();
//		testRating.sortRankingRating();
//		testRating.printRatings();
//		testRating.addRating("My Little Pony: The Movie", 10002, 5.0);
//		testRating.addRating("My Little Pony: The Movie", 10002, 5.0);
		testRating.printRatings();
		testRating.write();
		
		
	}

}
