package moblima;

import java.util.Scanner;

public class MainInterface {

	public static void main(String[] args) {

		int choice;
		boolean adminLogin = false;
		Scanner sc = new Scanner(System.in);
		
		MainController mainController = MainController.getInstance();
		MainController.start();
		
		System.out.println("Are you an admin or user?");
		System.out.println("(1): Admin");
		System.out.println("(2): User");
		System.out.println("(3): Exit MOBLIMA");
		System.out.println("Enter your choice: ");
		choice = sc.nextInt();
		
		switch (choice) {
		case 1:
			if (mainController.login() == true) {
				AdminInterface.getInstance().start();
			}
			break;
		case 2:	
			MovieGoerInterface.getInstance().start();
			break;
		case 3:
			return;
		default:
			System.out.println("Invalid choice!");
			break;
		}
	}
}
		




