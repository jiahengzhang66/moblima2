package moblima;

import java.io.IOException;
import java.text.ParseException;
import java.util.Scanner;

public class MovieGoerInterface {
	
	public MovieGoerInterface() {}
	
	private static MovieGoerInterface movieGoerInterface = null;
	
	public static MovieGoerInterface getInstance() {
		if (movieGoerInterface == null)
			movieGoerInterface = new MovieGoerInterface();
		return movieGoerInterface;
	}
	
	public void start() {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		MovieGoerController movieGoerController= MovieGoerController.getInstance();
		while (choice != 8) {
			System.out.println(
					"=========User Menu========\n" +
			        "(1) List all movies\n" +
			        "(2) Search movie details\n" +
			        "(3) Book and purchase ticket\n" +
			        "(4) View booking history\n" +
			        "(5) Add movie review\n" +
			        "(6) List Top 5 rankings by sale\n" +
			        "(7) List Top 5 rankings by rating\n" +
			        "(8) Exit MOBLIMA");  
			System.out.println("Enter your choice: ");
			choice = sc.nextInt();

			switch (choice) {
			case 1:
				movieGoerController.listAllMovies();
				break;
			case 2:
				movieGoerController.searchMovieDetails();
				break;
			case 3:
				try {
					movieGoerController.bookAndPurchaseTicket();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
			case 4:
				movieGoerController.viewBookingHistory();
				break;
			case 5:
				movieGoerController.addMovieReview();
				break;
			case 6:
				movieGoerController.rankBySale();
				break;
			case 7:
				movieGoerController.rankByRatings();
				break;
			case 8:
				return;
			default:
				System.out.println("Invalid choice!");
				break;
			}
		}
	}

}

