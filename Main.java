import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		Main main = new Main();
		main.startReservation();
	}

	public void startReservation() {
		ReservationSystem hotelReservationSystem = new ReservationSystem();
		hotelReservationSystem.initializeRooms();

		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.println("Welcome to Hotel Reservation Page!");
			System.out.println("Kindly select any one from below.");
			System.out.println("1. Register Guest");
			System.out.println("2. Create Reservation");
			System.out.println("3. Check Room Availability");
			System.out.println("4. Check-In");
			System.out.println("5. Check-Out");
			System.out.println("6. Show guest list");
			System.out.println("7. Show reservations");
			System.out.println("8. Cancel Reservation");
			System.out.println("0. Exit");
			System.out.println("Enter your choice: ");

			if (scanner.hasNextInt()) {
				int choice = scanner.nextInt();
				scanner.nextLine();

				switch (choice) {
				case 1:
					hotelReservationSystem.registerGuest();
					break;
				case 2:
					hotelReservationSystem.createReservation();
					break;
				case 3:
					hotelReservationSystem.checkRoomAvailability();
					break;
				case 4:
					hotelReservationSystem.checkIn();
					break;
				case 5:
					hotelReservationSystem.checkOut();
					break;
				case 6:
					hotelReservationSystem.showGuestList();
					break;
				case 7:
					hotelReservationSystem.showReservations();
					break;
				case 8:
					hotelReservationSystem.cancelReservation();
					break;
				case 0:
					System.out.println("Exiting...");
					return;
				default:
					System.out.println("Invalid choice. Try again!");
					break;
				}
				System.out.println();
			} else {
				System.out.println("Invalid input. Please choose a valid number from the menu.\n");
				scanner.nextLine();
			}
		}
	}
}
