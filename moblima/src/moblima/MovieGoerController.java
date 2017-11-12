package moblima;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Calendar;
import java.util.Date;

public class MovieGoerController {
	Scanner sc = new Scanner(System.in);
	
	private static MovieGoerController movieGoerController = null;
	
	
	public MovieGoerController() {}

	public static MovieGoerController getInstance() {
		if (movieGoerController == null)
			movieGoerController = new MovieGoerController();
		return movieGoerController;
	}
	
	public void listAllMovies() {
		MovieDataBaseController movieDataBaseController = MovieDataBaseController.getInstance();
		
		//print out list of movies
		movieDataBaseController.generateMovieArray();
		movieDataBaseController.displayMovies();
	}
	public void searchMovieDetails() {
		MovieDataBaseController movieDataBaseController = MovieDataBaseController.getInstance();
		RatingDataBaseController ratingDataBaseController = RatingDataBaseController.getInstance();
		ReviewDataBaseController reviewDataBaseController = ReviewDataBaseController.getInstance();
		
		int movieIDToSearch;
		double averageRating;
		String allReviews;
		Movies movieToSearch;
		
		ratingDataBaseController.generateRatingArray();
		reviewDataBaseController.generateReviewArray();
		
		//user inputs ID of movie he wishes to search for
		System.out.println("Enter movie ID of movie you wish to search for: ");
		movieIDToSearch = integerCheck();
		movieDataBaseController.generateMovieArray();
		
		//retrieves average rating and all reviews from the respective controllers
		averageRating = ratingDataBaseController.getRatingFromID(movieIDToSearch);
		allReviews = reviewDataBaseController.getReviewFromController(movieIDToSearch);
		
		//prints movie details + average rating + all reviews
		movieDataBaseController.displayOneMovieDetail(movieIDToSearch);
		System.out.println("Average rating for this movie: " + averageRating);
		System.out.println("Reviews for this movie: " + allReviews);
	}
	
	public void bookAndPurchaseTicket() throws IOException, ParseException {
		MovieDataBaseController movieDataBaseController = MovieDataBaseController.getInstance();
		ShowtimeDateController showtimeDateController = ShowtimeDateController.getInstance();
		BookingDatabaseController bookingDatabaseController = BookingDatabaseController.getInstance();
		TicketController ticketController = TicketController.getInstance();
		CinemaController cinemaController = CinemaController.getInstance();
		
		int movieIDToBook, cineplexID, cinemaID, row, column, seatID, seatArrayID, choice = 0, noOfTickets = 0, typeOfMovieGoer, bookingID, mobile;
		//int childTickets, seniorTickets, adultTickets;
		boolean endBooking = false;
		float totalPrice = 0;
		String name, email, cinemaCode;

		ArrayList<Integer> seatIDArray = new ArrayList<Integer>();
		
		
		ShowtimeDate showtimes = new ShowtimeDate(0, 0, 0, 0, 0, 0);
		Booking newBooking;
		Cinema cinemaSelected;
		Ticket ticketBooked, ticketBooked1, ticketBooked2, ticketBooked3, ticketBooked4, ticketBooked5;
		
		
		CinemaSeat[] platinumSeats = new CinemaSeat[40];
		int i;
		for (i = 0; i < 40; i++)
			platinumSeats[i] = new CinemaSeat(i+1);
		
		CinemaSeat[] goldSeats = new CinemaSeat[54];
		for (i = 0; i < 54; i++)
			goldSeats[i] = new CinemaSeat(i+1);
		
		CinemaSeat[] normalSeats= new CinemaSeat[144];
		for (i = 0; i < 144; i++)
			normalSeats[i] = new CinemaSeat(i+1);
		

	
		//ask user for movie ID of movie he wants to book
		System.out.println("Enter movie ID of movie you wish to book: ");
		movieIDToBook = integerCheck();
		showtimes.setMovieID(movieIDToBook);
		//prints out ALL showtimes for this movie
		showtimeDateController.printMovieShowTimes(showtimes);
		//user selects his desired cineplex and the search narrows down based on his choice
		System.out.println("(1) GV Paya Lebar (2) GC Tiong Bahru (3) GV Suntec City");
		cineplexID = integerCheck();
		showtimes.setCineplexID(cineplexID);
		showtimeDateController.printCineplexShowTimes(showtimes);
		//user enters his desired showtimedate
		System.out.println("Insert the year, month, day and time of choice (YYYY MM DD TTTT");
		showtimes.setYear(sc.nextInt());
		showtimes.setMonth(sc.nextInt());
		showtimes.setDay(sc.nextInt());
		showtimes.setTime(sc.nextInt());
		//this function searches for the corresponding cinema to that specific showtime
		cinemaID = showtimeDateController.searchCinemaID(showtimes);
		//this function retrieves cinema seat information from the CSV file
		cinemaController.CSVToCinema(platinumSeats, cinemaID, showtimes.getYear(), showtimes.getMonth(), showtimes.getDay(), showtimes.getTime());
		//prints out the cinema seats for that cinema ID
		cinemaController.printHallSeats(cinemaID, showtimes.getYear(), showtimes.getMonth(), showtimes.getDay(), showtimes.getTime());
		
		//select seat
		// do-while loop to check if user wants to continue booking seats
		do {
			//System.out.println("Select moviegoer you are booking the ticket for: ");
			//System.out.println("(1) Child (2) Senior Citizen (3) Adult");
			//typeOfMovieGoer = integerCheck();
			//switch (typeOfMovieGoer) {
			//case 1:
			//	typeOfMovieGoer = "Child";
			//}
			System.out.println("Enter row of seat: ");
			row = sc.nextInt();
			System.out.println("Enter column of seat: ");
			column = sc.nextInt();
			
			if (cinemaController.cinemaType(cinemaID) == "PlatinumClass") {
				cinemaSelected = new PlatinumClass(cinemaID); 		//upcasting
				seatID = row * 8 + column;
				seatIDArray.add(seatID);
				seatArrayID = seatID - 1;
				if (platinumSeats[seatArrayID].isAssigned() == false) {
					platinumSeats[seatArrayID].setAssigned(true);
					platinumSeats[seatID - 1].assign(100);
				}
				else
					System.out.println("Seat has already been taken!");
			}
			else if (cinemaController.cinemaType(cinemaID) == "GoldClass") {
				cinemaSelected = new GoldClass(cinemaID);
				seatID = row * 9 + column;
				seatIDArray.add(seatID);
				seatArrayID = seatID - 1;
				if (goldSeats[seatArrayID].isAssigned() == false) {
					goldSeats[seatArrayID].setAssigned(true);
					goldSeats[seatID - 1].assign(100);
				}
				else
					System.out.println("Seat has already been taken!");
			}
			else {
				cinemaSelected = new NormalClass(cinemaID);
				seatID = row * 16 + column;
				seatIDArray.add(seatID);
				seatArrayID = seatID - 1;
				if (goldSeats[seatArrayID].isAssigned() == false) {
				goldSeats[seatArrayID].setAssigned(true);
				goldSeats[seatID - 1].assign(100);
			}
			else
				System.out.println("Seat has already been taken!");
			}
			noOfTickets++;
			System.out.println("You have successfully reserved Seat " + seatID + " Row: " + row + " Column: " + column);
			
			System.out.println("Do you want to continue booking?");
			System.out.println("(1) Yes (2) No");
			//do-while loop checks if user wants to continue booking as well as checks that user input is either 1 or 2
			do {
				try {
					choice = Integer.parseInt(sc.nextLine());
					if (choice == 2) {
						endBooking = !endBooking;
						return;
					}
					if (choice < 1 || choice > 2)
						throw new Exception("Choice must be between 1 to 2!");
				} catch (NumberFormatException e) {
					System.out.println("Input must be integer. Please enter an integer: ");
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			} while (choice < 1 || choice > 2);
		} while (endBooking == false);
			
		
		//class of cinema
		totalPrice += (cinemaSelected.getPriceModifier() * noOfTickets);
		//3d or digital + blockbuster or not
		Movies movieToBook = movieDataBaseController.searchMovie(movieIDToBook);
		for (i = 0; i < noOfTickets; i++)
			totalPrice += movieToBook.getPriceModifier();

		//age of moviegoer
		Ticket[] childTicketArray = null, adultTicketArray = null, seniorTicketArray = null;
		System.out.println("How many of your tickets are child tickets?");
		int childTickets = integerCheck();
		if (childTickets != 0)
			childTicketArray = new Ticket[childTickets];
		System.out.println("How many of your tickets are senior citizen tickets?");
		int seniorTickets = integerCheck();
		if (seniorTickets != 0)
			seniorTicketArray = new Ticket[seniorTickets];
		int adultTickets = noOfTickets - childTickets - seniorTickets;
		if (adultTickets != 0)
			adultTicketArray = new Ticket[adultTickets];
		
		int currentTicketNo = 0; 
		Date dateBooked = ticketController.insertDate();
		//Date dateBooked = ticketController.convertHolidayDate(showtimes.getYear(), showtimes.getMonth(), showtimes.getDay(), showtimes.getTime());
		Boolean isHoliday = ticketController.checkHoliday(dateBooked);
		String day = sc.nextLine();
		//Boolean isWeekend = ticketController.checkWeekend();
		
		for (i = 0; i < childTickets; i++) {
			childTicketArray[i] = new ChildTicket(seatIDArray.get(i), showtimes.getMovieID());	
			totalPrice += childTicketArray[i].getTypePriceModifier();
			if (isHoliday == true)
				totalPrice += childTicketArray[i].getHolidayPriceModifier();
			//if (isWeekend == true)
				//totalPrice += childTicketArray[i].getWeekendPriceModifier();
			childTicketArray[i].setAssigned(true);
			ticketController.addTicketIntoDatabase(childTicketArray[i]);
			currentTicketNo++;
		}
		for (i = 0; i < seniorTickets; i++) {
			seniorTicketArray[i] = new SeniorCitizen(seatIDArray.get(currentTicketNo), showtimes.getMovieID());	
			totalPrice += seniorTicketArray[i].getTypePriceModifier();
			if (isHoliday == true)
				totalPrice += childTicketArray[i].getHolidayPriceModifier();
			//if (isWeekend == true)
				//totalPrice += childTicketArray[i].getWeekendPriceModifier();
			seniorTicketArray[i].setAssigned(true);
			ticketController.addTicketIntoDatabase(seniorTicketArray[i]);
			currentTicketNo++;
		}
		for (i = 0; i < adultTickets; i++) {
			adultTicketArray[i] = new AdultTicket(seatIDArray.get(currentTicketNo), showtimes.getMovieID());
			totalPrice += adultTicketArray[i].getTypePriceModifier();
			if (isHoliday == true)
				totalPrice += childTicketArray[i].getHolidayPriceModifier();
			//if (isWeekend == true)
				//totalPrice += childTicketArray[i].getWeekendPriceModifier();
			adultTicketArray[i].setAssigned(true);
			ticketController.addTicketIntoDatabase(adultTicketArray[i]);
			currentTicketNo++;
		}
		
		System.out.println("Your total ticket cost is: " + totalPrice);
		
		/*Upon booking, the application will capture the movie-goer�s name, mobile number
		and email address. Each payment will have a transaction id (TID). The TID is of the
		format XXXYYYYMMDDhhmm (Y : year, M : month, D : day, h : hour, m :
		minutes, XXX : cinema code in letters)*/
		
		System.out.println("Please enter your name: ");
		name = sc.nextLine();
		System.out.println("Please enter your mobile number ");
		mobile = integerCheck();
		System.out.println("Pleaes enter your email address: ");
		email = sc.nextLine();
		cinemaCode = cinemaController.cinemaCode(movieIDToBook);
		//bookingID = bookingDatabaseController.generateBookingID();
		
		
		//create ticketID array
		int[] ticketIDs = new int[noOfTickets];
		for (i = 0; i < noOfTickets; i++)
			ticketIDs[i] = ticketController.generateTicketingID();
		
		//add booking into database
		bookingDatabaseController.addBooking(cinemaCode, ticketIDs, name, mobile, email, movieIDToBook);
		bookingDatabaseController.write();
	}
	
	public void viewBookingHistory() {
		BookingDatabaseController bookingDatabaseController = BookingDatabaseController.getInstance();
		bookingDatabaseController.printBookingHistory(20);
	}
	
	public void addMovieReview() {
		MovieDataBaseController movieDataBaseController = MovieDataBaseController.getInstance();
		ReviewDataBaseController reviewDataBaseController = ReviewDataBaseController.getInstance();
		RatingDataBaseController ratingDataBaseController = RatingDataBaseController.getInstance();
		
		Movies movieToRate;
		int movieIDToRate, rating = 0;
		String movieTitleToRate, reviewString;
		Review review;
		
		//searches for movie object based on movie ID input by user
		System.out.println("Enter the ID of the movie you wish to rate: ");
		movieIDToRate = integerCheck();
		movieToRate = movieDataBaseController.searchMovie(movieIDToRate);
		movieTitleToRate = movieToRate.getTitle();
		
		//tag rating to movie
		System.out.println("Enter your rating from 0 - 5: ");
		do {
			try {
				rating = Integer.parseInt(sc.nextLine());
				if (rating < 0 || rating > 5)
					throw new Exception("Rating must be between 0 to 5!");
			} catch (NumberFormatException e) {
				System.out.println("Input must be integer. Please enter an integer: ");
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		} while (rating < 0 || rating > 5);
		
		ratingDataBaseController.generateRatingArray();
		ratingDataBaseController.addRating(movieTitleToRate, movieIDToRate, rating);
		ratingDataBaseController.write();
		
		System.out.println("Enter your review: ");
		reviewString = sc.nextLine();
		review = new Review(movieTitleToRate, movieIDToRate, reviewString);
		reviewDataBaseController.addReview(review);
		reviewDataBaseController.write();
		
		System.out.println("Your rating and review for" + movieTitleToRate+ "has been successfully created");
		System.out.println(rating + " rating || " + reviewString);
	}
	
	public void rankBySale() {
		TicketController ticketController = TicketController.getInstance();
		
		int[] moviesRankedBySale;
		
		moviesRankedBySale = ticketController.sortedMovieRank();
		ticketController.printArray(moviesRankedBySale);
		//problem with this is that movieIDs are printed and it would be more useful to print the actual movie name but whatever la
	}
	
	public void rankByRatings() {
		RatingDataBaseController ratingDataBaseController = RatingDataBaseController.getInstance();
		
		ratingDataBaseController.generateRatingArray();
		ratingDataBaseController.sortRankingRating();
	}
	
	public int integerCheck() {
		int input = 0;
		do {
			try {
				input = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e)
			{
				System.out.println("Input must be integer. Please enter an integer: ");
			}
		} while (input == 0);
		return input;
	}
}
