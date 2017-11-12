package moblima;

import java.util.Scanner;

public class MainController {
	
	/* defines admin username as "staff" */
	private static final String correctAdminUser = "";
	
	/* defines admin password as "moblima" */
	private static final String correctAdminPassword = "";
	
	private static MainController mainController = null;
	
	public MainController() {}

	public static MainController getInstance() {
		if (mainController == null)
			mainController = new MainController();
		return mainController;
	}
	
	public static void start() {
		AdminController adminController = AdminController.getInstance();
		MovieGoerController movieGoerController = MovieGoerController.getInstance();	
		MovieDataBaseController movieDataBaseController = new MovieDataBaseController();
		ShowtimeDateController showtimeDateController = ShowtimeDateController.getInstance();
		ReviewDataBaseController reviewDataBaseController = ReviewDataBaseController.getInstance();
		RatingDataBaseController ratingDataBaseController = RatingDataBaseController.getInstance();
		BookingDatabaseController bookingDatabaseController = BookingDatabaseController.getInstance();
		TicketController ticketController = TicketController.getInstance();
		CinemaController cinemaController = CinemaController.getInstance();
		
		
//		movieDataBaseController.generateMovieArray();
		//cinemaController.createNewCSV();
		
		/*User currentUser = new User();
		if (currentUser.isMovieGoer()) {
			MovieGoerInterface.start();	
		}
		else
			AdminInterface.start();*/
	}
	
	public boolean login() {
		Scanner sc = new Scanner(System.in);
		String inputAdminUser;
		String inputAdminPassword;
		boolean loginSuccess = false;
		
		while (loginSuccess == false) {
			System.out.println("Please enter username: ");
			inputAdminUser = sc.nextLine();
			System.out.println("Please enter password: ");
			inputAdminPassword = sc.nextLine();
			//System.out.println(inputAdminUser + inputAdminPassword);
			if (inputAdminUser.equals(correctAdminUser) && inputAdminPassword.equals(correctAdminPassword)) {
				loginSuccess = true;
				System.out.println("Login Success.");
			}
			else {
				System.out.println("Incorrect Username or Password");
			}
		}
		return loginSuccess;
	}
}
