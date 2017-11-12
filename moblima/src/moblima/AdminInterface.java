package moblima;

import java.util.Scanner;

public class AdminInterface {
	
	public AdminInterface() {}
	
	private static AdminInterface adminInterface = null;
	
	public static AdminInterface getInstance() {
		if (adminInterface == null)
			adminInterface = new AdminInterface();
		return adminInterface;
	}

	public void start() {
		Scanner sc = new Scanner(System.in);
		int choice = 0;
		
		AdminController adminController = AdminController.getInstance();
		
		while (choice != 7) {
			System.out.println(
		        "=========Admin Menu========\n" +
		        "(1) Create movie listing\n" +
		        "(2) Update movie listing\n" +
		        "(3) Remove movie listing\n" +
		        "(4) Create cinema showtime\n" +
		        "(5) Update cinema showtime\n" +
		        "(6) Remove cinema showtime\n" +
		        "(7) Logout of admin module");
			System.out.println("Enter your choice: ");
			choice = sc.nextInt();
		
			switch (choice) {
			case 1:
				adminController.createMovieListing();
				break;
			case 2:
				adminController.updateMovieListing();
				break;
			case 3:
				adminController.removeMovieListing();
				break;
			case 4:
				adminController.createCinemaShowtime();
				break;
//			case 5:
//				adminController.CinemaShowtime();
//				break;
			case 6:
				adminController.removeCinemaShowtime();
				break;
			case 7:
				return;
			default:
				System.out.println("Invalid choice!");
				break;
			}
		}
	}
}



