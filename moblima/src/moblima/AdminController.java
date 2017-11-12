package moblima;

import java.util.Scanner;

import java.io.*;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;
import java.util.Calendar;


public class AdminController {
	Scanner sc = new Scanner(System.in);
	
	private static AdminController adminController = null;
	
	
	//public LoginState {LOGINSUCCESS, INCORRECTUSERNAME, INCORRECTPASSWORD};

	public AdminController() {}
	
	public static AdminController getInstance() {
		if (adminController == null)
			adminController = new AdminController();
		return adminController;
	}
	
	public void createMovieListing() {
		MovieDataBaseController movieDataBaseController = MovieDataBaseController.getInstance();
		
		Movies movieToAdd;
		boolean isBlockbuster = false;
		int movieID = 0, i, actorCount = 0, typeOfMovieChoice = 0;
		//float basePrice;
		String title = new String("");
		String releaseDate= new String("");
		String synopsis = new String("");
		String typeOfMovie = new String("");
		String language = new String("");
		String director = new String("");
		String actors = new String("");
		String genre = new String("");
		String ageRating = new String("");
		String showingStatus = new String("");
		String[] actorsArray;
		
		System.out.println("Enter movie title: ");
		title = sc.nextLine();
		movieID = movieDataBaseController.setMovieID();
		
		//do {
		//	try {
			//	movieID = Integer.parseInt(sc.nextLine());
			//} catch (NumberFormatException e)
			//{
			//	System.out.println("Movie ID must be integer. Please enter an integer: ");
			//}
		//} while (movieID == 0);
		System.out.println("Enter release date of movie: ");
		releaseDate = sc.nextLine();
		System.out.println( 
				"What type of movie is this?\n" +
				"(1) Digital" +
				"(2) 3D");
		typeOfMovieChoice = integerCheck();
		do {
		if (typeOfMovieChoice == 1)
			typeOfMovie = "Digital";
		else if (typeOfMovieChoice == 2) {
			typeOfMovie = "ThreeD";
		}
		else
			System.out.println("Invalid choice!: ");
		} while (typeOfMovieChoice != 1 && typeOfMovieChoice != 2);
			
		System.out.println("Enter movie genre: ");
		genre = sc.nextLine();
		System.out.println("Enter movie synopsis: ");
		synopsis = sc.nextLine();
		System.out.println(
				"Select Showing Status:\n" +
				"(1) COMING SOON\n" +
				"(2) PREVIEW\n" +
				"(3) NOW SHOWING\n" +
				"(4) END OF SHOWING");
			switch (integerCheck()) {
			case 1:
				showingStatus = "COMING SOON";
				break;
			case 2:
				showingStatus = "PREVIEW";
				break;
			case 3:
				showingStatus = "NOW SHOWING";
				break;
			case 4:
				showingStatus = "END OF SHOWING";
				break;
			default:
				System.out.println("Invalid choice!");
				break;
			}
		System.out.println(
				"Select Movie Rating:\n" +
				"(1) G\n" +
				"(2) PG13\n" +
				"(3) NC16\n" +
				"(4) M18\n" +
				"(5) R21"); 
			switch (integerCheck()) {
			case 1:
				ageRating = "G";
				break;
			case 2:
				ageRating = "PG13";
				break;
			case 3:
				ageRating = "NC16";
				break;
			case 4:
				ageRating = "M18";
				break;
			case 5:
				ageRating = "R21";
				break;
			default:
				System.out.println("Invalid choice!");
				break;
			}
		System.out.println("Enter number of actors: ");
		actorCount = integerCheck();
		//do {
			//try {
			//	actorCount = Integer.parseInt(sc.nextLine());
			//} catch (NumberFormatException e)
			//{
			//	System.out.println("Number of actors must be integer. Please enter an integer: ");
			//}
		//} while (actorCount == 0);
		actorsArray = new String[actorCount];
		for (i = 0; i < actorCount; i++) {
			System.out.println("Enter name of actor " + (i+1) + ": ");
			actorsArray[i] = sc.nextLine();
		}
		//converts string array of actor names to a string
		StringBuilder strBuilder = new StringBuilder();
		for (int j = 0; j < actorsArray.length; j++) {
			strBuilder.append(actorsArray[j]);
			//adds commas after each actor name besides the last actor
			if (j < actorsArray.length - 1)
				strBuilder.append(", ");
		}
		actors = strBuilder.toString();
		System.out.println(actors);
		System.out.println("Enter movie directors: ");
		director = sc.nextLine();	
		System.out.println( 
				"Is the movie a blockbuster movie?\n" +
				"(1) Yes" +
				"(2) No");
		if (sc.nextInt() == 1)
			isBlockbuster = !isBlockbuster;
		
		//System.out.println("Enter base price of normal ticket for this movie: ");
		//basePrice = sc.nextFloat();
		
		movieToAdd = new Movies(title, movieID, releaseDate, typeOfMovie, genre, language, synopsis, showingStatus, ageRating, actors, director, isBlockbuster);
		
		
			if (typeOfMovie == "Digital") {
				MovieDigital movieDigital = new MovieDigital(title, movieID, releaseDate, typeOfMovie, genre, language, synopsis, showingStatus, ageRating, actors, director, isBlockbuster);
				movieToAdd = movieDigital;
			}
			else if (typeOfMovie == "ThreeD") {
				Movie3D movie3D = new Movie3D(title, movieID, releaseDate, typeOfMovie, genre, language, synopsis, showingStatus, ageRating, actors, director, isBlockbuster);
				movieToAdd = movie3D;
			}
		
		
		movieDataBaseController.addMovie(movieToAdd);
		movieDataBaseController.write();
		System.out.println("Movie Listing has been successfully created!");
	}
	
	public void updateMovieListing() {
		Movies movieToUpdate;
		boolean isBlockbuster = false;
		int choice, typeOfMovieChoice, movieIDtoUpdate, movieID = 0, i, actorCount = 0;
		//float basePrice;
		String title, releaseDate, typeOfMovie, language, synopsis, director, actors, genre, ageRating = null, showingStatus = null;
		String[] actorsArray;
		MovieDataBaseController movieDataBaseController = MovieDataBaseController.getInstance();
		
		//print out list of movies then admin selects one
		movieDataBaseController.generateMovieArray();
		movieDataBaseController.displayMovies();
		
		System.out.println("Enter ID of movie you want to update details for: ");
		movieIDtoUpdate = integerCheck();
		movieToUpdate = movieDataBaseController.searchMovie(movieIDtoUpdate);
		//title, movieID, releaseDate, typeOfMovie, genre, language, synopsis, showingStatus, ageRating, actors, director, isBlockbuster);
		
		do {
			System.out.println(
		        "Select what you wish to update: " +
		        "(1) Title\n" +
		        "(2) Movie ID\n" +
		        "(3) Release Date\n" +
		        "(4) Type of Movie\n" +
		        "(5) Genre\n" +
		        "(6) Language\n" +
		        "(7) Synopsis\n" +
		        "(8) Showing Status\n" +
		        "(9) Age Rating\n" +
		        "(10) Actors\n" +
		        "(11) Director\n" +
		        "(12) Blockbuster Status\n" +
		        "(13) Finish Updating");
		choice = sc.nextInt();
		
		
			switch (choice) {
			case 1:
				System.out.println("Enter movie title: ");
				title = sc.nextLine();
				movieToUpdate.setTitle(title);
				break;
			case 2:
				System.out.println("Enter movie ID: ");
				movieID = integerCheck();
				movieToUpdate.setMovieID(movieID);
				break;
			case 3:
				System.out.println("Enter release date: ");
				releaseDate = sc.nextLine();
				movieToUpdate.setReleaseDate(releaseDate);
				break;
			case 4:
				System.out.println( 
						"What type of movie is this?\n" +
						"(1) Digital" +
						"(2) 3D");
				typeOfMovieChoice = integerCheck();
				do {
				if (typeOfMovieChoice == 1)
					typeOfMovie = "Digital";
				else if (typeOfMovieChoice == 2) {
					typeOfMovie = "ThreeD";
				}
				else
					System.out.println("Invalid choice!: ");
				} while (typeOfMovieChoice != 1 && typeOfMovieChoice != 2);
				break;
			case 5:
				System.out.println("Enter movie genre: ");
				genre = sc.nextLine();
				movieToUpdate.setGenre(genre);
				break;
			case 6:
				System.out.println("Enter language: "); 
				language = sc.nextLine();
				movieToUpdate.setLanguage(language);
				break;
			case 7:
				System.out.println("Enter movie synopsis: ");
				synopsis = sc.nextLine();
				movieToUpdate.setSynopsis(synopsis);
				break;
			case 8:
				System.out.println(
					"Select Showing Status:\n" +
					"(1) COMING SOON\n" +
					"(2) PREVIEW\n" +
					"(3) NOW SHOWING\n" +
					"(4) END OF SHOWING");
				switch (integerCheck()) {
				case 1:
					showingStatus = "COMING SOON";
					break;
				case 2:
					showingStatus = "PREVIEW";
					break;
				case 3:
					showingStatus = "NOW SHOWING";
					break;
				case 4:
					showingStatus = "END OF SHOWING";
					break;
				default:
					System.out.println("Invalid choice!");
					break;
				}
				movieToUpdate.setStatus(showingStatus);
				break;
			case 9:
				System.out.println(
					"Select Movie Rating:\n" +
					"(1) G\n" +
					"(2) PG13\n" +
					"(3) NC16\n" +
					"(4) M18\n" +
					"(5) R21"); 
				switch (integerCheck()) {
				case 1:
					ageRating = "G";
					break;
				case 2:
					ageRating = "PG13";
					break;
				case 3:
					ageRating = "NC16";
					break;
				case 4:
					ageRating = "M18";
					break;
				case 5:
					ageRating = "R21";
					break;
				default:
					System.out.println("Invalid choice!");
					break;
				}
				movieToUpdate.setAgeRating(ageRating);
				break;
			case 10:
				System.out.println("Enter number of actors: ");
				actorCount = integerCheck();
				actorsArray = new String[actorCount];
				for (i = 0; i < actorCount; i++) {
					System.out.println("Enter name of actor " + (i+1) + ": ");
					actorsArray[i] = sc.nextLine();
				}
				//converts string array of actor names to a string
				StringBuilder strBuilder = new StringBuilder();
				for (int j = 0; j < actorsArray.length; j++) {
					strBuilder.append(actorsArray[j]);
					//adds commas after each actor name besides the last actor
					if (j < actorsArray.length - 1)
						strBuilder.append(", ");
				}
				actors = strBuilder.toString();
				movieToUpdate.setActors(actors);
				break;
			case 11:
				System.out.println("Enter movie director: ");
				director = sc.nextLine();
				movieToUpdate.setDirector(director);
				break;
			case 12:
				System.out.println( 
						"Is the movie a blockbuster movie?\n" +
						"(1) Yes" +
						"(2) No");
				if (integerCheck() == 1)
					isBlockbuster = !isBlockbuster;
				movieToUpdate.setBlockBuster(isBlockbuster);
				break;
			case 13:
				System.out.println("Case 13 Clicked");
				break;
			default:
				System.out.println("Invalid choice!");	
			}
		}while (choice != 13);
		//***current assumption is that the information in the original array will be over-written
		System.out.println("Movie Update for movie ID " + movieIDtoUpdate + " is complete.");
		movieDataBaseController.write();
	}
	
	public void removeMovieListing() {
		int movieIDToRemove;
		Movies movieToRemove;
		MovieDataBaseController movieDataBaseController = MovieDataBaseController.getInstance();
		//print out list of movies then admin selects one
				movieDataBaseController.generateMovieArray();
				movieDataBaseController.displayMovies();
				System.out.println("Enter ID of movie you want to remove listing for: : ");
				movieIDToRemove = integerCheck();
				movieDataBaseController.removeMovie(movieIDToRemove);
				movieDataBaseController.write();
	}
	
	public void createCinemaShowtime() {
		MovieDataBaseController movieDataBaseController = MovieDataBaseController.getInstance();
		ShowtimeDateController showtimeDateController = ShowtimeDateController.getInstance();
		
		Movies movieToAddShowtime;
		//Cineplex cineplexToAddShowtime;
		ShowtimeDate showtimeToAdd;
		int movieIDToAddShowtime, cineplexID, cinemaID, seatID, year, month, day;
		
		//select movie
		System.out.println("Enter movie ID of movie you wish to add showtime for: ");
		movieIDToAddShowtime = integerCheck();
		//showtimeDateController.showtimeToAdd(movieIDToAddShowtime);
		movieToAddShowtime = movieDataBaseController.searchMovie(movieIDToAddShowtime);
		
		//select cineplex
		System.out.println("Choose cineplex to add movie showtime to: ");
		System.out.println("(1) Cineplex 1 (2) Cineplex 2 (3) Cineplex 3 ");
		cineplexID = integerCheck();
		
		//select cinema
		System.out.println("Choose cinema to add movie showtime to: ");
		cinemaID = integerCheck();
		
		//input showtime
		System.out.println("Enter year (YYYY): ");
		year = integerCheck();
		System.out.println("Enter month (MM): ");
		month = integerCheck();
		System.out.println("Enter day (DD): ");
		day = integerCheck();
		showtimeToAdd = new ShowtimeDate(movieIDToAddShowtime, cineplexID, cinemaID, year, month, day);
		//the addShowTime function has been coded such that it asks user to input the timings when the function is called
		showtimeDateController.addShowtimeDateIntoDatabase(showtimeToAdd);
		
		System.out.println("Showtime has been successfully added");
	}
	//public void updateCinemaShowtime() {
		
	//}
	
	public void removeCinemaShowtime() {
		MovieDataBaseController movieDataBaseController = MovieDataBaseController.getInstance();
		ShowtimeDateController showtimeDateController = ShowtimeDateController.getInstance();
		
		Movies movieToDeleteShowtime;
		ShowtimeDate showtimeToDelete;
		int movieIDToDeleteShowtime, cineplexID, cinemaID, seatID, year, month, day;
		
		//select movie
		System.out.println("Enter movie ID of movie you wish to add showtime for: ");
		movieIDToDeleteShowtime = integerCheck();
		//showtimeToDelete(movieIDToDeleteShowtime);
		movieToDeleteShowtime = movieDataBaseController.searchMovie(movieIDToDeleteShowtime);
		
		/*Movies movieToAddShowtime;
		//Cineplex cineplexToAddShowtime;
		ShowTimeDate showtimeToAdd;
		int movieIDToAddShowtime, cineplexID, cinemaID, seatID, year, month, day;
		
		//select movie
		System.out.println("Enter movie ID of movie you wish to delete showtime for: ");
		movieIDToDeleteShowtime = integerCheck();
		showtimeToAdd(movieIDToAddShowtime);
		movieToAddShowtime = movieDataBaseController.searchMovie(movieIDToAddShowtime);*/
		
		//select cineplex
		System.out.println("Choose cineplex to add movie showtime to: ");
		System.out.println("(1) Cineplex 1 (2) Cineplex 2 (3) Cineplex 3 ");
		cineplexID = integerCheck();
		
		//select cinema
		System.out.println("Choose cinema to add movie showtime to: ");
		cinemaID = integerCheck();
		
		//input showtime
		System.out.println("Enter year (YYYY): ");
		year = integerCheck();
		System.out.println("Enter month (MM): ");
		month = integerCheck();
		System.out.println("Enter day (DD): ");
		day = integerCheck();
		//the deleteShowTime function has been coded such that it asks user to input the timings when the function is called
		showtimeToDelete = new ShowtimeDate(movieIDToDeleteShowtime, cineplexID, cinemaID, year, month, day);
		showtimeDateController.deleteShowtimeDateIntoDatabase(showtimeToDelete);
		
		System.out.println("Showtime has been successfully added.");
	
	
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

