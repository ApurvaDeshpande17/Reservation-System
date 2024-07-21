import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReservationSystem implements HotelManager {

	private final List<Guest> guests;
	private final List<Room> rooms;
	private final List<Reservation> reservations;
	private int guestIdCounter;
	private int reservationIdCounter;

	public ReservationSystem() {
		guests = new ArrayList<>();
		rooms = new ArrayList<>();
		reservations = new ArrayList<>();
		guestIdCounter = 1;
		int roomIdCounter = 1;
		reservationIdCounter = 1;
	}

	public static boolean validateDate(String dateStr) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(dateStr);
			return true;
		} catch (ParseException e) {
			return false;
		}
	}

	void initializeRooms() {
		for (int i = 1; i <= 10; i++) {
			Room room = new Room(i, "Room " + i, "Standard");
			rooms.add(room);
		}
	}

	@Override
	public void registerGuest() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter guest details:");

		System.out.print("Name: ");
		String name = scanner.nextLine();

		System.out.print("Address: ");
		String address = scanner.nextLine();

		System.out.print("Phone Number: ");
		String phoneNumber = scanner.nextLine();
		int count = 2;

		if (phoneNumber.length() == 10) {
			Guest guest = new Guest(guestIdCounter++, name, address, phoneNumber);
			guests.add(guest);

			System.out.println("Guest registered successfully!");
			System.out.println(guest);
		}

		if (phoneNumber.length() != 10) {
			System.out.println("Invalid Phone Number! Try again");
			System.out.print("Phone Number: ");
			while (count != 0) {
				System.out.println("Try again!");
				phoneNumber = scanner.nextLine();

				if (phoneNumber.length() == 10) {
					Guest guest = new Guest(guestIdCounter++, name, address, phoneNumber);
					guests.add(guest);

					System.out.println("Guest registered successfully!");
					System.out.println(guest);
				}
				count--;
			}
			System.out.println("Too many attempts! Redirecting to main menu.");
		}
	}

	@Override
	public void createReservation() {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Enter reservation details:");
		System.out.print("Guest ID: ");
		int guestId = scanner.nextInt();
		scanner.nextLine();

		System.out.print("Start Date: ");
		String startDate = scanner.nextLine();
		// LocalDate startDate = LocalDate.parse(start);
		boolean isValid = validateDate(startDate);
		if (!isValid) {
			System.out.println("Invalid date! Try again. Expected format: MM/dd/yyyy");
		}

		System.out.print("End Date: ");
		String endDate = scanner.nextLine();
		// LocalDate endDate = LocalDate.parse(end);
		isValid = validateDate(endDate);
		if (!isValid) {
			System.out.println("Invalid date! Try again. Expected format: MM/dd/yyyy");
		}

		Guest guest = validateGuest(guestId);
		Room room = selectRoom();
		if (guest == null || room == null) {
			System.out.println("Invalid guest ID or room number or Date.");
			return;
		}

		if (!room.isAvailability()) {
			System.out.println("Room is already booked. Try again!");
			return;
		}

		room.setAvailability(false);

		Reservation reservation = new Reservation(reservationIdCounter++, guest, room, startDate, endDate);
		reservations.add(reservation);

		System.out.println("Reservation created successfully!");
		System.out.println(reservation);

	}

	@Override
	public void checkRoomAvailability() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter room number: ");
		String roomNumber = scanner.nextLine();

		Room room = validateRoom(roomNumber);
		if (room == null) {
			System.out.println("Invalid!");
			return;
		}

		if (room.isAvailability()) {
			System.out.println("Room " + roomNumber + " is available.");
		} else {
			System.out.println("Room " + roomNumber + " is not available.");
		}
	}

	@Override
	public void checkIn() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter your Reservation ID: ");
		int reserveID = scanner.nextInt();
		scanner.nextLine();

		Reservation reservation = validateReservation(reserveID);
		if (reservation == null) {
			System.out.println("Invalid ID. Please check again or reserve a room.");
			return;
		}

		if (reservation.isCheckedIn()) {
			System.out.println("Check In is already complete. No need to proceed further.");
			return;
		}

		reservation.setCheckedIn(true);

		System.out.println("Successfully checked-in!");

		Room room = reservation.getRoom();
		System.out.println("Your room is:" + room.getRoomNumber());
	}

	@Override
	public void checkOut() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter reservation ID: ");
		int reserveID = scanner.nextInt();
		scanner.nextLine();

		Reservation reservation = validateReservation(reserveID);
		if (reservation == null) {
			System.out.println("Invalid ID, try again!");
			return;
		}

		if (!reservation.isCheckedIn()) {
			System.out.println("Unable to check-out before anu check-in.");
			return;
		}

		Room room = reservation.getRoom();
		room.setAvailability(true);
		if (reservation != null) {
			reservations.remove(reservation);
		}
		System.out.println("Check-out successful.");
	}

	@Override
	public void showGuestList() {
		System.out.println("\nGuest List:");
		for (Guest guest : guests) {
			System.out.println(guest + "\n");
		}
	}

	@Override
	public void showReservations() {
		System.out.println("\nReservations :");
		for (Reservation reservation : reservations) {
			System.out.println(reservation + "\n");
		}
	}

	@Override
	public void cancelReservation() {
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter reservation ID: ");
		int reserveId = scanner.nextInt();
		scanner.nextLine();

		Reservation reservation = validateReservation(reserveId);
		if (reservation == null) {
			System.out.println("Invalid ID. Try again!");
		}

		if (reservation != null && reservation.isCheckedIn()) {
			System.out.println("Guest is checked in. Cannot cancel reservation.");
		}

		if (reservation != null) {
			Room room = reservation.getRoom();
			room.setAvailability(true);
			reservations.remove(reservation);
			System.out.println("Reservation canceled successfully.");
		}
	}

	private Guest validateGuest(int guestId) {
		for (Guest guest : guests) {
			if (guest.getGuestId() == guestId) {
				return guest;
			}
		}
		return null;
	}

	private Room selectRoom() {
		System.out.println("Available Rooms:");
		for (Room room : rooms) {
			if (room.isAvailability()) {
				System.out.println(room.getRoomNumber());
			}
		}

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter your preference for the reservation: ");
		String roomNumber = scanner.nextLine();

		for (Room room : rooms) {
			if (room.getRoomNumber().equals(roomNumber) && room.isAvailability()) {
				System.out.println("your room is:" + room);
				return room;
			}
		}
		return null;
	}

	private Room validateRoom(String roomNumber) {
		for (Room room : rooms) {
			if (room.getRoomNumber().equals(roomNumber)) {
				return room;
			}
		}
		return null;
	}

	private Reservation validateReservation(int reserveId) {
		for (Reservation reservation : reservations) {
			if (reservation.getReservationId() == reserveId) {
				return reservation;
			}
		}
		return null;
	}
}
